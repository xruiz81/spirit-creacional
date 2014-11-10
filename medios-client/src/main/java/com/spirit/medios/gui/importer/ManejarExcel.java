package com.spirit.medios.gui.importer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.spirit.client.Parametros;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.medios.entity.ComercialIf;
import com.spirit.medios.entity.MapaComercialData;
import com.spirit.medios.entity.MapaComercialIf;
import com.spirit.medios.entity.PlanMedioDetalleData;
import com.spirit.medios.entity.PlanMedioDetalleIf;
import com.spirit.nomina.handler.DatoObservacion;
import com.spirit.util.ExtensionFileFilter;
import com.spirit.util.LabelAccessory;
import com.spirit.util.Utilitarios;


public class ManejarExcel {

	private java.util.Date fechaInicio = new java.util.Date();
	private java.util.Date fechaFin = new java.util.Date();
	
	private ArrayList<java.util.Date> listFechasConComercial = new ArrayList<java.util.Date>();
	int status = 1;
	private static final String PAUTA_REGULAR = "P";
	private static final String AUSPICIO = "A";
	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	private static final String ESTADO_INACTIVO = "I";
	
	
	public ManejarExcel(){
		cargarMapas();
	}
	
	public void cargarMapas(){
		try{
			mapaClienteOficina.clear();
			Collection clientesOficina = SessionServiceLocator
					.getClienteOficinaSessionService().findClienteOficinaByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while (clientesOficinaIt.hasNext()) {
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf) clientesOficinaIt
						.next();
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
			}
		}catch(GenericBusinessException g){
			g.printStackTrace();
		}
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Collection<DatoObservacion> convertirInformacionDesdeExcel(String nombreHoja,
			Collection<PlanMedioDetalleIf> detalles,
			Map<PlanMedioDetalleIf, Collection<MapaComercialIf>> mapasComerciales,
			Map<PlanMedioDetalleIf, Collection<InfoComercialMultiple>> mapasComercialesMultiples,
			ComercialIf comercialSeleccionado, String tipoMedio, GenericoIf generico)
			throws GenericBusinessException, OfficeXmlFileException, Exception{
		
		Collection<DatoObservacion> observaciones = new ArrayList<DatoObservacion>();
		
		Set<String> identificacionesRevisadas = new HashSet<String>();
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAccessory(new LabelAccessory(fileChooser));

		FileFilter filterExcel = new ExtensionFileFilter(null, new String[] {"xls,xlsx", "xls,xlsx" });
		fileChooser.addChoosableFileFilter(filterExcel);
		fileChooser.setFileFilter(fileChooser.getAcceptAllFileFilter());
		status = fileChooser.showOpenDialog(Parametros.getMainFrame());
		
		Map<String,Medio> mapaMedios = new LinkedHashMap<String, Medio>();
		
		if (status == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
		
			InputStream fis = null;
			
			try {
				fis = new FileInputStream(file);
				
				Collection<FilaComercial> comerciales; 
				
				if (comercialSeleccionado == null ) 
					comerciales = procesarArchivoExcel(fis, nombreHoja, null, tipoMedio);	
				else 
					comerciales = procesarArchivoExcel(fis, nombreHoja, comercialSeleccionado.getVersion(), tipoMedio); //se procesa el archivo excel
				
				Map observacionesMapa = new HashMap();
				
				filaComercial:
				
				for ( FilaComercial comercial : comerciales ){
					
					String identificacion = comercial.getIdentificacionMedio().trim();
					
					ClienteOficinaIf clienteOficina = null;
					Medio medio = mapaMedios.get(identificacion);
					
					if ( medio == null ){
						
						if ( identificacionesRevisadas.contains(identificacion) )
							continue filaComercial;
						
						identificacionesRevisadas.add(identificacion);
						Collection<ClienteIf> clientes = SessionServiceLocator.getClienteSessionService().findClienteByIdentificacion(identificacion);
						
						if ( clientes.size() == 0 ){
							DatoObservacion dao = new DatoObservacion();
							dao.setEtiquetaTitulo("Identificacion: ");
							dao.setContenidoTitulo(identificacion);
							dao.agregarDescripcion("No existe Medio con esta identificación.");
							observaciones.add(dao);
							continue filaComercial;
						} 
						else if ( clientes.size() > 1 ){
							DatoObservacion dao = new DatoObservacion();
							dao.setEtiquetaTitulo("Identificacion: ");
							dao.setContenidoTitulo(identificacion);
							dao.agregarDescripcion("Existe mas de un Medio con esta identificación.");
							observaciones.add(dao);
							continue filaComercial;
						}
						
						if ( clientes.size() == 1 ){
							ClienteIf clienteIf = (ClienteIf)clientes.iterator().next();
							if(clienteIf.getEstado().equals(ESTADO_INACTIVO)){
								DatoObservacion dao = new DatoObservacion();
								dao.setEtiquetaTitulo("Identificacion: ");
								dao.setContenidoTitulo(identificacion);
								dao.agregarDescripcion("El Medio con esta identificación esta INACTIVO, verificar si el RUC esta actualizado.");
								observaciones.add(dao);
								continue filaComercial;
							}
						}
						
						medio = new Medio();
						ClienteIf cliente = clientes.iterator().next();
						medio.setIdentificacion(cliente.getIdentificacion());
						medio.setClienteif(cliente);

						mapaMedios.put(cliente.getIdentificacion(), medio);
						
						clienteOficina = verificarClienteOficina(cliente, comercial.getCodigoMedio().trim(), observaciones, medio);
						
						if (clienteOficina == null)
							continue filaComercial;
																	
						Map mapProductoPautaRegularTv = new HashMap();
						mapProductoPautaRegularTv.put("genericoId", generico.getId());
						mapProductoPautaRegularTv.put("proveedorId", clienteOficina.getId());
						
						Collection genericoProveedor = SessionServiceLocator.getProductoSessionService().findProductoByQuery(mapProductoPautaRegularTv);
						//si esta vacio creo el mapa para luego mostrar problema en pantalla de observaciones
						if(genericoProveedor.isEmpty()){
							ClienteOficinaIf proveedor = mapaClienteOficina.get(clienteOficina.getId());
							observacionesMapa.put(proveedor.getId(), generico.getNombre());
						}				
												
					} else {
						clienteOficina = verificarClienteOficina(medio.getClienteif(), 
							comercial.getCodigoMedio().trim(),observaciones, medio);
						
						if (clienteOficina == null)
							continue filaComercial;						
						
						Map mapProductoPautaRegularTv = new HashMap();
						mapProductoPautaRegularTv.put("genericoId", generico.getId());
						mapProductoPautaRegularTv.put("proveedorId", clienteOficina.getId());
						
						Collection genericoProveedor = SessionServiceLocator.getProductoSessionService().findProductoByQuery(mapProductoPautaRegularTv);
						//si esta vacio creo el mapa para luego mostrar problema en pantalla de observaciones
						if(genericoProveedor.isEmpty()){
							ClienteOficinaIf proveedor = mapaClienteOficina.get(clienteOficina.getId());
							observacionesMapa.put(proveedor.getId(), generico.getNombre());
						}
					}					
					
					PlanMedioDetalleData detalle = new PlanMedioDetalleData();
					detalle.setProveedorId(clienteOficina.getId());
					detalle.setPrograma(comercial.getPrograma());
					detalle.setVersion(comercial.getVersion());
					detalle.setComercial(comercial.getComercial() + "," + String.valueOf(comercial.getNumeroOrden()));
					detalle.setPauta(comercial.getPauta());
					detalle.setAuspicioDescripcion(comercial.getAuspicioDescripcion());
					detalle.setHoraInicio(comercial.getHora());
					BigDecimal raiting1 = new BigDecimal(comercial.getRaiting1());
					detalle.setRaiting1(Utilitarios.redondeoValor(raiting1));
					BigDecimal raiting2 = new BigDecimal(comercial.getRaiting2());
					detalle.setRaiting2(Utilitarios.redondeoValor(raiting2));
					BigDecimal raitingPonderado = new BigDecimal(comercial.getRaitingPonderado());
					detalle.setRaitingPonderado(Utilitarios.redondeoValor(raitingPonderado));
					detalle.setTotalCunias(comercial.getTotalCunias());
					detalle.setNumeroOrdenAgrupacion(comercial.getNumeroOrden());
					BigDecimal valorTarifa = new BigDecimal(comercial.getTarifa());
					detalle.setValorTarifa(valorTarifa);
					BigDecimal valorSubTotal = new BigDecimal(comercial.getTarifaTotal());//TarifaTotal=detalle.getTarifa()) * detalle.getTotalCunias()
					detalle.setValorSubtotal(valorSubTotal);
					
					Collection<InfoComercial> infos = comercial.getComerciales();
					//Mapas de Comerciales con las fechas correctas(mismas del excel)
					ArrayList<MapaComercialIf> mapas = new ArrayList<MapaComercialIf>();
					ArrayList<InfoComercialMultiple> listaMultiples = new ArrayList<InfoComercialMultiple>();
					
					for ( InfoComercial info : infos ){
						
						if ( info.getTipo() == TipoInfoComercial.NUMERICO ){
							MapaComercialIf mapaComercial = new MapaComercialData();
							mapaComercial.setFecha(new Date(info.getFecha().getTime()));
							Integer frecuencia = (Integer)info.getLista().iterator().next();
							mapaComercial.setFrecuencia(frecuencia);
							mapas.add(mapaComercial);
							
							if (!fechaEnLista(mapaComercial.getFecha()))
								listFechasConComercial.add(mapaComercial.getFecha());
						
						} else if ( info.getTipo() == TipoInfoComercial.MULTIPLE ){
							InfoComercialMultiple icm = new InfoComercialMultiple();
							icm.setFecha(info.getFecha());
							icm.setLista(info.getLista());
							icm.setFrecuencia(info.getFrecuencia().iterator().next());
							listaMultiples.add(icm);
							
							if (!fechaEnLista(icm.getFecha()))
								listFechasConComercial.add(icm.getFecha());
						}
					}
					if ( mapasComerciales != null )
						mapasComerciales.put(detalle, mapas);
					else 
						mapasComercialesMultiples.put(detalle, listaMultiples);
					detalles.add(detalle);
				}
				
				//para mostrar problemas encontrados
				Iterator observacionesMapaIt = observacionesMapa.keySet().iterator();
				while(observacionesMapaIt.hasNext()){
					Long proveedorId = (Long)observacionesMapaIt.next();
					ClienteOficinaIf proveedor = mapaClienteOficina.get(proveedorId);
					DatoObservacion dao = new DatoObservacion();
					dao.setEtiquetaTitulo("Producto: ");
					dao.setContenidoTitulo(generico.getNombre());
					dao.agregarDescripcion("El Proveedor " + proveedor.getDescripcion() + " no está asociado al Producto.");
					observaciones.add(dao);
				}
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				fis.close();
				throw new GenericBusinessException(e.getMessage());
			} 
			
			try {
				if ( fis != null ){
					fis.close();
					System.out.println("-ARCHIVO CERRADO-");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			ordenarASCFechas();			
		}
		
		return observaciones;
	}
	
	//ADD 21 ABRIL
	
	public java.util.Date getFechaMenor(){
		fechaInicio = listFechasConComercial.get(0); 
		return fechaInicio;
	}
	
	public java.util.Date getFechaMayor(){
		
		if (listFechasConComercial.size() > 0)
			fechaFin = listFechasConComercial.get(listFechasConComercial.size()-1);
		else
			fechaFin = listFechasConComercial.get(0);
		return fechaFin;
	}
	
	//Ordena las fechas ascendentemente de Fechas Menores a Mayores
	private void ordenarASCFechas(){
		Collections.sort(listFechasConComercial);
	}
	
	//Verifica que la fecha esta o no esta en la lista de fechas con comerciales
	private boolean fechaEnLista(Date fecha){
		boolean isFecha = false;
		Timestamp timeStampDate = new Timestamp(fecha.getTime());
		//seteamos los valores hora, minutos y segundos en la cero hora
		timeStampDate = Utilitarios.resetTimestampStartDate(timeStampDate);
		java.util.Date fechaTime = new java.util.Date(timeStampDate.getTime());
				
		for (int i = 0; i < listFechasConComercial.size(); i++){
			java.util.Date date = listFechasConComercial.get(i);
			Timestamp timeStampDateTemp = new Timestamp(date.getTime());
			timeStampDateTemp = Utilitarios.resetTimestampStartDate(timeStampDateTemp);
			java.util.Date fechaTimeTemp = new java.util.Date(timeStampDateTemp.getTime());
			int comp = fechaTime.compareTo(fechaTimeTemp);
			if (comp == 0)
				isFecha = true;
		}
		return isFecha;
	} 
	
	//END 21 ABRIL
	
	
	
	private ClienteOficinaIf verificarClienteOficina(ClienteIf cliente,String codigoMedio,
			Collection<DatoObservacion> observaciones,Medio medio) throws GenericBusinessException{
		
		Map<String,ClienteOficinaIf> oficinasE = medio.getOficinas();
		ClienteOficinaIf clienteOficina = oficinasE.get(codigoMedio);
		if ( clienteOficina == null &&
			 !medio.getOficinasRevisadas().contains(codigoMedio) ){
			
			Map<String,Object> mapaOficina = new LinkedHashMap<String, Object>();
			mapaOficina.put("clienteId", cliente.getId());
			mapaOficina.put("codigo", codigoMedio);
			Collection<ClienteOficinaIf> oficinas = SessionServiceLocator.getClienteOficinaSessionService()
				.findClienteOficinaByQuery(mapaOficina);
			if ( oficinas.size() == 0 ){
				DatoObservacion dao = new DatoObservacion();
				dao.setEtiquetaTitulo("Codigo: ");
				dao.setContenidoTitulo(codigoMedio);
				dao.agregarDescripcion("No existe Oficina con este codigo del cliente con identificacion "+cliente.getIdentificacion());
				observaciones.add(dao);
				medio.getOficinasRevisadas().add(codigoMedio);
			} else if ( oficinas.size() > 1 ){
				DatoObservacion dao = new DatoObservacion();
				dao.setEtiquetaTitulo("Codigo: ");
				dao.setContenidoTitulo(codigoMedio);
				dao.agregarDescripcion("Existe mas de una Oficina con este codigo del cliente con identificacion "+cliente.getIdentificacion());
				observaciones.add(dao);
				medio.getOficinasRevisadas().add(codigoMedio);
			} else {
				ClienteOficinaIf oficina = oficinas.iterator().next();
				medio.agregarOficina(oficina.getCodigo(), oficina);
				return oficina;
			}
		}
		return clienteOficina;
	}
	
	//MODIFIED 29 AGOSTO
	//private ArrayList<FilaComercial> procesarArchivoExcel(InputStream input,String nombreHoja) 
	private ArrayList<FilaComercial> procesarArchivoExcel(InputStream input,String nombreHoja, String version, String tipoMedio) 
	throws IOException, GenericBusinessException,OfficeXmlFileException,Exception {
		ArrayList<FilaComercial> detalles = new ArrayList<FilaComercial>();
		
		//int columnaA = 0,columnaB = 1;
		int columnaNumeroOrden = 0;
		int columnaInfoGeneral = 2;
		int columnaInfoMedios = 1;
		int columnaPrograma = 2, columnaVersion = 3, columnaHora = 4; 
		int columnaInicioDias = 5;
		//suponiendo que el mes tenga 31 dias
		int columnaFinDias = 35;
		int columnaCunias = 36,columnaRaiting1 = 37,columnaRaiting2 = 38,columnaPonderada = 39;
		//MODIFIED 24 AGOSTO
		//int columnaTarifa = 39,columnaTarifaTotal = 40;
		int columnaTarifa = 43,columnaTarifaTotal = 44;
		int filaFecha = 6;
		
		String filaTotalCanal = "TOTAL CANAL";
		if(tipoMedio.equals("RADIO")){
			filaTotalCanal = "TOTAL RADIO";
		}else if(tipoMedio.equals("TV")){
			filaTotalCanal = "TOTAL CANAL";
		}else{
			filaTotalCanal = "TOTAL CINE";
		}
				
		String filaTotalPauta = "TOTAL PAUTA:";
		//ADD 31 MAYO
		String filaAuspicio = "AUSPICIO";
		//END 31 MAYO
		//ADD 1 JUNIO
		ArrayList<String> auspiciosList = new ArrayList<String>();
		//END 1 JUNIO
		
		Calendar calActual = new GregorianCalendar();
		//Calendario para conocer el ultimo dia del mes del excel
		Calendar calTemp   = new GregorianCalendar();
		
		POIFSFileSystem fs = new POIFSFileSystem(input);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet(nombreHoja);
		
		if (sheet!=null){
			
			//FECHA DE REPORTE
			HSSFRow fechaRow = sheet.getRow(filaFecha);
			HSSFCell celdaFecha = fechaRow.getCell(columnaInfoGeneral);
			HSSFRichTextString textoCelda = celdaFecha.getRichStringCellValue();
			String fechaCelda = textoCelda.getString();
			fechaCelda = fechaCelda.replaceAll(":", "").replaceAll(" ", "");
			//System.out.println("*Fecha Reporte: "+fechaCelda);
			String datosFechas[] = fechaCelda.split("/");
			if ( datosFechas.length != 2 )
				throw new GenericBusinessException("Error en formato de fecha en celda B7");
			
			String mes = datosFechas[0];
			Integer mesInt = 0; 
			try{
				mesInt = Utilitarios.getMesInt(mes.toUpperCase());
			} catch(Exception e){
				throw new GenericBusinessException("Nombre de mes incorrecto en celda B7");
			}
			
			String anio = datosFechas[1];
			Integer anioInt = 0;
			try{
				anioInt = Integer.valueOf(anio);
			} catch(Exception e){
				throw new GenericBusinessException("Año incorrecto en celda B7");
			}
			
			calActual.set(Calendar.MONTH, mesInt);
			calActual.set(Calendar.YEAR, anioInt);
							
			//Seteando los datos del CalendarioTemporal
			calTemp.set(Calendar.DATE, 1);
			calTemp.set(Calendar.MONTH, mesInt);
			calTemp.set(Calendar.YEAR, anioInt);
				
			//Obteniendo el ultimo dia del Mes del Calendario 
			int ultimoDia = calTemp.getActualMaximum(Calendar.DATE);
			
			if (ultimoDia < 31) {
				columnaFinDias = ultimoDia + 4;
			}
			
			int ultimaFilaInfoMedio = 11;
			//Se empieza desde la linea 11 de excel, donde se ubican los primeros dias. 
			//for(int i = 11; i <= sheet.getLastRowNum(); i++)
			String identificacionMedio = null;
			String codigoOficinaMedio = null;
			int numeroOrden = 0;
			System.out.println("FILAS TOTALES: "+ sheet.getLastRowNum());
			
			RecorrerFilas:
			for(int i = 11; i <= sheet.getLastRowNum(); i++) {
				
				System.out.println("MES FOR "+calActual.get(Calendar.MONTH)+" , "+ "ANIO FOR"+calActual.get(Calendar.YEAR));	
				System.out.println("Fila Nº"+ i);
				
				if(i == 13){
					System.out.println("aqui");
				}
			
								
				//Luego del nombre del canal debe haber una linea en blanco antes de empezar a listar los programas.
				if ( i == (ultimaFilaInfoMedio+1) )
					continue RecorrerFilas;
				
				FilaComercial detalle = new FilaComercial();
				HSSFRow row = sheet.getRow(i);
				/*HSSFCell celdaUno = row.getCell(columnaA);
				HSSFCell celdaDos = row.getCell(columnaB);*/	
				/*if ( celdaUno!=null && celdaDos != null && celdaUno.getCellType() == HSSFCell.CELL_TYPE_BLANK &&
					 celdaDos.getCellType() == HSSFCell.CELL_TYPE_BLANK )
					continue RecorrerFilas;*/
				
				
				//INFORMACION DE MEDIO (NOMBRE, IDENTIFICACION, CODIGO OFICINA)
				HSSFCell celdaMedio = row.getCell(columnaInfoMedios);
				HSSFCell celdaNumeroOrden = row.getCell(columnaNumeroOrden);
				
				if ( celdaMedio!= null && celdaMedio.getCellType() != HSSFCell.CELL_TYPE_BLANK ) {
					HSSFRichTextString medioTexto = celdaMedio.getRichStringCellValue();
					//System.out.println("***********************************************");
					//System.out.println("* Info Medio: "+medioTexto);
					String infoMedioCelda = medioTexto.getString().trim();
					
					if (celdaNumeroOrden != null && celdaNumeroOrden.getCellType() != HSSFCell.CELL_TYPE_BLANK) {
						numeroOrden = Double.valueOf(celdaNumeroOrden.getNumericCellValue()).intValue();
					} else {
						numeroOrden = 1;
					}
					
					if (infoMedioCelda.compareTo(filaTotalCanal) != 0 && infoMedioCelda.compareTo(filaTotalPauta) != 0){
						String[] infoMedio = infoMedioCelda.split("-");
						
						if (infoMedio.length == 3){
							String nombreMedio = infoMedio[0];
							//System.out.println("* Nombre Medio: "+nombreMedio);
							identificacionMedio = infoMedio[1];
							//System.out.println("* Identificacion: "+identificacionMedio);
							codigoOficinaMedio = infoMedio[2];
							//System.out.println("* Codigo: "+codigoOficinaMedio);
							//System.out.println("***********************************************");
							//Luego del nombre del canal debe haber una linea en blanco antes de empezar a listar los programas.
							ultimaFilaInfoMedio = i;
						}else{//ADD 14 ABRIL
							throw new GenericBusinessException("Formato de la Información del Medio o Canal en la Celda A"+(i + 1) + " es Incorrecto"+
									 							"\nVerifique el Formato, Cierre del Canal y Cierre de Pauta en el archivo Excel  " +
									 							"\ncargado, realice los cambios respectivos para evitar este inconveniente. ");
						}//ADD 1 JUNIO	
					}else if (infoMedioCelda.compareTo(filaTotalCanal) == 0){
						if (auspiciosList.size() > 0)
							auspiciosList.remove(auspiciosList.size() - 1);
						continue RecorrerFilas;
					}//END 1 JUNIO
					else{
						if (infoMedioCelda.compareTo(filaTotalPauta) == 0)
							i = sheet.getLastRowNum();
						continue RecorrerFilas;
					}
				} else {//PARA LOS PROGRAMAS DEL PLAN MEDIO 
					detalle.setNumeroOrden(numeroOrden);
					detalle.setIdentificacionMedio(identificacionMedio);
					detalle.setCodigoMedio(codigoOficinaMedio);
					
					//NOMBRE DEL PROGRAMA CON COMERCIAL
					HSSFCell celdaPrograma = row.getCell(columnaPrograma);
										
					//VERIFICA SI LA LINEA INDICA QUE ES UN AUSPICIO DE AHI EN ADELANTE 
					//LOS DETALLES SERAN DEL AUSPICIO HASTA QUE ENCUENTRE UNA LINEA EN BLANCO 
									
					if ( (celdaPrograma != null && celdaPrograma.getCellType() != HSSFCell.CELL_TYPE_BLANK ) ){
						
						HSSFRichTextString auspicioTexto = celdaPrograma.getRichStringCellValue();
						String infoProgramaCelda = auspicioTexto.getString();
						String[] infoProgramaCeldaSplit = infoProgramaCelda.split("-");
						
						//para verificar si es un FORMATO DE AUSPICIO: AUSPICIO - AUSPICIO_DESCRIPCION
						if (infoProgramaCeldaSplit.length == 2){
							//AUSPICIO
							if (infoProgramaCeldaSplit[0].trim().compareTo(filaAuspicio) == 0 ){
								if (infoProgramaCeldaSplit[1].trim().length() > 0){
									//se agrega la descripcion del auspicio a una lista temporal
									//para indicar que este programa es un auspicio y los programas que vienen despues
									//son auspicios hasta que se encuentre una linea en blanco para cerrar el auspicio
									auspiciosList.add(infoProgramaCeldaSplit[1]);
									continue RecorrerFilas;
								}//else
								//	throw new GenericBusinessException("Existen Auspicios que no cumplen el Formato, por favor verificar el archivo Excel" );
							}						
						}else if (infoProgramaCeldaSplit[0].trim().compareTo(filaAuspicio) == 0 ){
							//cuando en la linea de AUSPICIO no colocan alguna descripcion
							//temporalmente se coloca sin descripcion para luego colocar la del programa
							auspiciosList.add("SIN_DESCRIPCION");
							continue RecorrerFilas;
						}
					}//END ADD 3 JUNIO
					else if ( (celdaPrograma == null || celdaPrograma.getCellType() == HSSFCell.CELL_TYPE_BLANK) &&  auspiciosList.size() > 0){
						//remueve de la lista la ultima descripcion de auspicio pa indicar el cierre de auspicio
						auspiciosList.remove(auspiciosList.size() - 1);	
						continue RecorrerFilas;
					}//END 1 JUNIO	
					else{		
						//Si no hay nombre de programa no se hace nada con esa fila
						//MODIFIED 1 JUNIO
						/*if ( celdaPrograma == null || 
						 celdaPrograma.getCellType() == HSSFCell.CELL_TYPE_BLANK )*/
						continue RecorrerFilas;
					}
					
					//AKI GIOMY!!!!!!
					//MOVED 2 JUNIO
					/*HSSFRichTextString programaTexto = celdaPrograma.getRichStringCellValue();
					String nombreProgramaCelda = programaTexto.getString().trim();*/
					//System.out.println("Nombre Programa: "+nombreProgramaCelda);
					
					
					//3 JUNIO
					//AKI GIOMY!!!!!!
					HSSFRichTextString programaTexto = celdaPrograma.getRichStringCellValue();
					String nombreProgramaCelda = programaTexto.getString().trim();
					//System.out.println("Nombre Programa: "+nombreProgramaCelda);
					
					int abreParentesis = nombreProgramaCelda.indexOf("(");
					int cierraParentesis = nombreProgramaCelda.indexOf(")");
					int longNombre = abreParentesis>0 ? abreParentesis : nombreProgramaCelda.length();
					String nombrePrograma = nombreProgramaCelda.substring(0, longNombre);

					//String comercial = "CUÑA-P";
					String comercial = "CUÑA";
					//ADD 1 JUNIO
					String pauta = ""; //A si es Aupicio o P si es Pauta Regular
					String auspicioDescripcion = null;//Descripcion del Auspicio
					//END 1 JUNIO
					//MODIFIED 2 JUNIO
					if ( abreParentesis > 0 && cierraParentesis > 0 && cierraParentesis > abreParentesis ){
						comercial = nombreProgramaCelda.substring(abreParentesis+1,cierraParentesis);
						comercial = comercial.trim();
					}
					
					if (auspiciosList.size() > 0){
						pauta = AUSPICIO;
						auspicioDescripcion = auspiciosList.get(auspiciosList.size() - 1);//Descripcion del Ultimo Auspicio
						//SI ENCUENTRA QUE EL ULTIMO AUSPICIO ERA SIN_DESCRIPCION EN LA LISTA TEMPORAL ENTONCES 
						//SE COLOCA COMO DESCRIPCION DEL PROGRAMA
						if (auspicioDescripcion.compareTo("SIN_DESCRIPCION") == 0)
							auspicioDescripcion = nombrePrograma;
					}else{
						pauta = PAUTA_REGULAR;
					}			
										
					detalle.setPrograma(nombrePrograma);
					detalle.setComercial(comercial);
					detalle.setPauta(pauta);
					detalle.setAuspicioDescripcion(auspicioDescripcion);
					
					//HORA DEL PROGRAMA
					HSSFCell celdaHora = row.getCell(columnaHora);
					HSSFRichTextString horaTexto = celdaHora.getRichStringCellValue();
					String hora = horaTexto.getString().trim();
					detalle.setHora(hora);
										
					//CUÑAS CELDA 34 TOTAL DE CUÑAS DE ESA FILA
					HSSFCell celdaCunias = row.getCell(columnaCunias);
					Integer cunias = null;
					if (celdaCunias!=null && 
						( celdaCunias.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || 
						  celdaCunias.getCellType() == HSSFCell.CELL_TYPE_FORMULA ) ){
						cunias = Double.valueOf( celdaCunias.getNumericCellValue() ).intValue();
						if (cunias > 0){
							detalle.setTotalCunias(cunias);
						}else{
							continue RecorrerFilas;
							/*throw new GenericBusinessException("La cantidad de Cuñas en Celda AI"+ (i + 1) + " debe ser mayor a 0."+
									                            "\nVerifique el archivo Excel cargado, realice los cambios respectivos " +
																"\ny también en casos similares para evitar este inconveniente. ");*/
						}
					}
					
					if ( (celdaCunias == null && nombrePrograma != null) ||
						 (Double.valueOf( celdaCunias.getNumericCellValue()) == 0D && nombrePrograma != null))
							continue RecorrerFilas;
										
					HSSFCell celdaRaiting1 = row.getCell(columnaRaiting1);
					Double raiting1 = null;
					if(!tipoMedio.equals("TV")){
						raiting1 = 0D;
						detalle.setRaiting1(raiting1);
					}
					else if (celdaRaiting1 != null 
							&& (celdaRaiting1.getCellType() == HSSFCell.CELL_TYPE_NUMERIC 
									|| celdaRaiting1.getCellType() == HSSFCell.CELL_TYPE_FORMULA) 
							&& (cunias != null && cunias > 0)){
						raiting1 = celdaRaiting1.getNumericCellValue();
						detalle.setRaiting1(raiting1);
					}else{
						raiting1 = 0D;
						detalle.setRaiting1(raiting1);
						/*throw new GenericBusinessException("Falta el valor del Raiting de GYE en Celda AJ"+ (i + 1) +
														   "\nVerifique el archivo Excel cargado, realice los cambios respectivos " +
														   "\ny también en casos similares para evitar este inconveniente. ");*/
					}
					
					HSSFCell celdaRaiting2 = row.getCell(columnaRaiting2);
					Double raiting2 = null;
					if(!tipoMedio.equals("TV")){
						raiting2 = 0D;
						detalle.setRaiting2(raiting2);
					}
					else if (celdaRaiting2 != null 
							&& (celdaRaiting2.getCellType() == HSSFCell.CELL_TYPE_NUMERIC 
									|| celdaRaiting2.getCellType() == HSSFCell.CELL_TYPE_FORMULA)
							&& (cunias != null && cunias >0)){	
						raiting2 = celdaRaiting2.getNumericCellValue();
						detalle.setRaiting2(raiting2);
					}else{
						raiting2 = 0D;
						detalle.setRaiting2(raiting2);
						/*throw new GenericBusinessException("Falta el valor del Raiting de UIO  en Celda AK"+ (i + 1) +  
														   "\nVerifique el archivo Excel cargado, realice los cambios respectivos " +
														   "\ny también en casos similares para evitar este inconveniente. ");*/
					}
					
					//RAITING PONDERADO
					HSSFCell celdaRaitingPonderado = row.getCell(columnaPonderada);
					Double raitingPonderado = null;
					
					if(!tipoMedio.equals("TV")){
						raitingPonderado = 0D;
						detalle.setRaitingPonderado(raitingPonderado);
					}
					else if (celdaRaitingPonderado != null 
							&& (celdaRaitingPonderado.getCellType() == HSSFCell.CELL_TYPE_NUMERIC 
									|| celdaRaitingPonderado.getCellType() == HSSFCell.CELL_TYPE_FORMULA)
							&& (cunias != null && cunias >0)){
						raitingPonderado = celdaRaitingPonderado.getNumericCellValue();
						detalle.setRaitingPonderado(raitingPonderado);
					}else{//ADD 30 JUNIO
						throw new GenericBusinessException("Falta el valor del Raiting Ponderado en Celda AL"+ (i + 1) +
														   "\nVerifique el archivo Excel cargado, realice los cambios respectivos " +
															"\ny también en casos similares para evitar este inconveniente. ");
					}//ADD 30 JUNIO
					//System.out.println("Raiting P: "+detalle.getRaitingPonderado() );
					
					
					//TARIFA
					HSSFCell celdaTarifa = row.getCell(columnaTarifa);
					Double tarifa = null;
					//MODIFIED 30 JUNIO
					/*if (celdaTarifa!=null && celdaTarifa.getCellType()==0){
						tarifa = celdaTarifa.getNumericCellValue();
						detalle.setTarifa(tarifa);
					}*/
					if (celdaTarifa != null 
							&& (celdaTarifa.getCellType() == HSSFCell.CELL_TYPE_NUMERIC 
									|| celdaTarifa.getCellType() == HSSFCell.CELL_TYPE_FORMULA) 
							&& (cunias != null && cunias >0)){
						tarifa = celdaTarifa.getNumericCellValue();
						detalle.setTarifa(tarifa);
					}else{//ADD 30 JUNIO
						throw new GenericBusinessException("Falta el valor de la TARIFA en Celda AR"+ (i + 1) +
														   "\nVerifique el archivo Excel cargado, realice los cambios respectivos " +
															"\ny también en casos similares para evitar este inconveniente. ");
					}//ADD 30 JUNIO
					
					System.out.println("Tarifa : "+detalle.getTarifa());
					
					
					//TARIFA TOTAL
					HSSFCell celdaTarifaTotal = row.getCell(columnaTarifaTotal);
					Double tarifaTotal = null;
					if (celdaTarifaTotal!=null && 
						 ( celdaTarifaTotal.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ||
						   celdaTarifaTotal.getCellType() == HSSFCell.CELL_TYPE_FORMULA	 ) ){
						tarifaTotal = celdaTarifaTotal.getNumericCellValue();
						//detalle.setTarifaTotal(tarifaTotal);
						//MODIFIED 19 ABRIL
						detalle.setTarifaTotal(detalle.getTarifa() * detalle.getTotalCunias());
						//detalle.setTarifaTotal(Utilitarios.redondeoValor(detalle.getTarifa()) * detalle.getTotalCunias());
						//END MODIFIED 19 ABRIL
					}
					//System.out.println("Tarifa Total: "+detalle.getTarifaTotal());
					
					//ADD 14 ABRIL
					if ( cunias != null && !"".equals(cunias) && cunias > 0 ){
						if (nombreProgramaCelda == null || "".equals(nombreProgramaCelda)){
							throw new GenericBusinessException("Error no se especifica el Programa en la celda B"+ (i + 1)+
															   "\nVerifique el archivo Excel cargado, realice los cambios respectivos " +
																"\ny también en casos similares para evitar este inconveniente. ");}
						if (comercial == null || "".equals(comercial)){
							throw new GenericBusinessException("Error no se especifica el tipo de Comercial en la celda B"+ (i + 1)+
																"\nVerifique el archivo Excel cargado, realice los cambios respectivos " +
																"\ny también en casos similares para evitar este inconveniente. ");}
						if (hora == null || "".equals(hora)){							
							throw new GenericBusinessException("Error no se especifica la Hora para el Programa en la celda C"+ (i + 1)+
																"\nVerifique el archivo Excel cargado, realice los cambios respectivos " +
																"\ny también en casos similares para evitar este inconveniente. ");}
						/*if (tipoMedio.equals("TV") && (raiting1 == null || "".equals(raiting1) || !(raiting1 > 0))){
							throw new GenericBusinessException("Error en valor del Raiting en la celda AJ"+ (i + 1)+
																"\nVerifique el archivo Excel cargado, realice los cambios respectivos " +
																"\ny también en casos similares para evitar este inconveniente. ");}
						if (tipoMedio.equals("TV") && (raiting2 == null || "".equals(raiting2) || !(raiting2 > 0))){
							throw new GenericBusinessException("Error en valor del Raiting en la celda AK"+ (i + 1)+
																"\nVerifique el archivo Excel cargado, realice los cambios respectivos " +
																"\ny también en casos similares para evitar este inconveniente. ");}*/
						
						if (tarifa == null || "".equals(tarifa) || !(tarifa >= 0D)){
							throw new GenericBusinessException("Error en valor de la Tarifa en la celda AP"+ (i + 1)+
																"\nVerifique el archivo Excel cargado, realice los cambios respectivos " +
																"\ny también en casos similares para evitar este inconveniente. ");}
						if (tarifaTotal == null || "".equals(tarifaTotal) || !(tarifaTotal >= 0D)){
							throw new GenericBusinessException("Error en Valor Total en la celda AQ"+ (i + 1)+
																"\nVerifique el archivo Excel cargado, realice los cambios respectivos " +
																"\ny también en casos similares para evitar este inconveniente. ");}
						
						
						/*if ( ( nombreProgramaCelda!=null && !"".equals(nombreProgramaCelda) ) && 
						 ( hora!=null && !"".equals(hora) ) &&
						 ( cunias != null && !"".equals(cunias) && cunias > 0 ) &&
						 ( raiting1 != null && !"".equals(raiting1) ) &&
						 ( raiting2 != null && !"".equals(raiting2) ) &&
						 ( raitingPonderado != null && !"".equals(raitingPonderado) ) &&
						 ( tarifa != null && !"".equals(tarifa) ) &&
						 ( tarifaTotal != null && !"".equals(tarifaTotal) && tarifaTotal > 0))*/
					
						//MAPA COMERCIAL
						ArrayList<InfoComercial> dias = detalle.getComerciales();
						int contadorDias = 1;
						//if ( "CINE DEL DOMINGO".equals(nombrePrograma) )
						//	System.out.println("");
						//if ( "NOCHES DEL OSCAR".equals(nombrePrograma) )
						//	System.out.println("");
						HSSFCell celdaVersion = row.getCell(columnaVersion);
						HSSFRichTextString richTextCeldaVersion = celdaVersion.getRichStringCellValue();
						if ( richTextCeldaVersion != null ) {
							version = richTextCeldaVersion.getString();
							if ( version != null && !"".equalsIgnoreCase(version.trim()) )
								version = version.trim();
						}
						detalle.setVersion(version);
						int totalCunias = 0;
						for(int j = columnaInicioDias; j <= columnaFinDias; j++)
						{
							System.out.println("DIA: " + contadorDias);
							
							HSSFCell cell = row.getCell(j);
							calActual.set(Calendar.DATE, contadorDias);
							//System.out.println(calActual.get(Calendar.MONTH));
							
							Date fecha = new Date(calActual.getTime().getTime());
							//este seteo de mes mesInt es muy importante porque hay un error que cuando es junio le pone julio
							//entonces mejor refrescar el mes.
							fecha.setMonth(mesInt);
							fecha.setTime(Utilitarios.resetTimestampStartDate(new Timestamp(fecha.getTime())).getTime());
							
							//ADD
							//Calendar c1 = Calendar.getInstance();
							//c1.setTime(fecha);
							//System.out.println("fecha::: DIA "+c1.get(Calendar.DATE)+" , "+ "MES "+c1.get(Calendar.MONTH)+" , "+ "ANIO "+c1.get(Calendar.YEAR));
							//Date fecha = new Date(calActual.getTime().getTime());
							
							//COMENTED 29 AGOSTO
							/*if (cell!=null && cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC &&
								cell.getCellType()!=HSSFCell.CELL_TYPE_BLANK	){
								int valor = Double.valueOf( cell.getNumericCellValue() ).intValue();
								InfoComercial dia = new InfoMapaComercial();
								dia.setFecha(fecha);
								//System.out.println("IF InfoComercial fecha::: DIA "+c1.get(Calendar.DATE)+" , "+ "MES "+c1.get(Calendar.MONTH)+" , "+ "ANIO "+c1.get(Calendar.YEAR));
								dia.setValor(valor);
								dias.add(dia);
							}*/ 
							//ADD 29 AGOSTO se convierte la Pauta Basica a Multiple con la Versión seleccionada
							if(cell != null){
								System.out.println("CELL TYPE >>>>>>" + String.valueOf(cell.getCellType()));
							}							
							System.out.println(HSSFCell.CELL_TYPE_STRING);
							
							if (cell!=null && cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC &&
								cell.getCellType()!=HSSFCell.CELL_TYPE_BLANK	){
								int valor = Double.valueOf( cell.getNumericCellValue() ).intValue();
									
								if (valor > 0 ){
									String texto = "";
									//Se concatena la version tantas veces indique el numero de la celda numerica	
									/*for (int t = 0; t < valor; t ++)
										texto+= version;*/ 										
										
									InfoComercial dia = new InfoComercialMultiple();
									dia.setFecha(fecha);
									System.out.println("VERSION: " + version + " FRECUENCIA: " + String.valueOf(valor));
									dia.setFrecuencia(valor);
									dia.setVersion(version);
									dias.add(dia);
									totalCunias += valor;
								}		
							}
							contadorDias++;
						}
						detalle.setTotalCunias(totalCunias);
						detalles.add(detalle);
					}
				}
			}
			//ADD 1 JUNIO
			if (auspiciosList.size() > 0){
				throw new GenericBusinessException("Existen Auspicios sin Cerrar "+
													"\nVerifique el archivo Excel cargado, realice los cambios respectivos " +
													"\nen casos similares para evitar este inconveniente. ");
			}
			//END 1 JUNIO
		} else {
			System.out.println("No existe hoja con nombre: "+nombreHoja);
			detalles = null;
		}
		return detalles;
	}
}