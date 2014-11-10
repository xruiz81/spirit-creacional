package ImportacionPlanMedios;

import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.MapaComercialData;
import com.spirit.medios.entity.MapaComercialIf;
import com.spirit.medios.entity.PlanMedioDetalleData;
import com.spirit.medios.entity.PlanMedioDetalleIf;
import com.spirit.util.ExtensionFileFilter;
import com.spirit.util.LabelAccessory;
import com.spirit.util.Utilitarios;


public class ManejarExcel {

	
	public static void main(String[] args) {
		
		String nombreArchivoExcel = "c:\\medios\\plantilla.xls";
		String nombreHoja = "Pauta";
		
		Collection<PlanMedioDetalleIf> detalles = new ArrayList<PlanMedioDetalleIf>();
		Map<PlanMedioDetalleIf,Collection<MapaComercialIf>> mapasComerciales = new HashMap<PlanMedioDetalleIf, Collection<MapaComercialIf>>();
		

		ManejarExcel me = new ManejarExcel();
		me.convertirInformacionDesdeExcel(nombreArchivoExcel, nombreHoja,
				detalles, mapasComerciales);
		
	}


	public void convertirInformacionDesdeExcel(
			String nombreArchivoExcel, String nombreHoja,
			Collection<PlanMedioDetalleIf> detalles,
			Map<PlanMedioDetalleIf, Collection<MapaComercialIf>> mapasComerciales) {
		
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAccessory(new LabelAccessory(fileChooser));

		FileFilter filterExcel = new ExtensionFileFilter(null, new String[] {"xls,xlsx", "xls,xlsx" });
		fileChooser.addChoosableFileFilter(filterExcel);
		fileChooser.setFileFilter(fileChooser.getAcceptAllFileFilter());
		int status = fileChooser.showOpenDialog(new Frame("AAA"));

		if (status == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
		
			//File file = new File(nombreArchivoExcel);
			InputStream fis = null;
			try {
				fis = new FileInputStream(file);
				Collection<FilaComercial> comerciales = procesarArchivoExcel(fis, nombreHoja);
				
				for ( FilaComercial comercial : comerciales ){
					PlanMedioDetalleIf detalle = new PlanMedioDetalleData();
					detalle.setPrograma(comercial.getPrograma());
					detalle.setComercial(comercial.getComercial());
					detalle.setHoraInicio(comercial.getHora());
					BigDecimal raiting1 = new BigDecimal(comercial.getRaiting1());
					detalle.setRaiting1(Utilitarios.redondeoValor(raiting1));
					BigDecimal raiting2 = new BigDecimal(comercial.getRaiting2());
					detalle.setRaiting2(Utilitarios.redondeoValor(raiting2));
					BigDecimal raitingPonderado = new BigDecimal(comercial.getRaitingPonderado());
					detalle.setRaitingPonderado(Utilitarios.redondeoValor(raitingPonderado));
					detalle.setTotalCunias(comercial.getTotalCunias());
					BigDecimal valorTarifa = new BigDecimal(comercial.getTarifa());
					detalle.setValorTarifa(Utilitarios.redondeoValor(valorTarifa));
					BigDecimal valorTotal = new BigDecimal(comercial.getTarifaTotal());
					detalle.setValorTotal(Utilitarios.redondeoValor(valorTotal));
					
					Collection<InfoMapaComercial> infos = comercial.getComerciales();
					ArrayList<MapaComercialIf> mapas = new ArrayList<MapaComercialIf>();
					for ( InfoMapaComercial info : infos ){
						MapaComercialIf mapaComercial = new MapaComercialData();
						mapaComercial.setFecha(new Date(info.getFecha().getTime()));
						mapaComercial.setFrecuencia(info.getFrecuencia());
						mapas.add(mapaComercial);
					}
					mapasComerciales.put(detalle, mapas);
					detalles.add(detalle);
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			} catch (Exception e){
				e.printStackTrace();
			}
			
			try {
				if ( fis != null ){
					fis.close();
					System.out.println("-ARCHIVO CERRADO-");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private ArrayList<FilaComercial> procesarArchivoExcel(InputStream input,String nombreHoja) 
	throws IOException, GenericBusinessException,Exception {
		ArrayList<FilaComercial> detalles = new ArrayList<FilaComercial>();
		
		int columnaA = 0,columnaB = 1;
		
		int columnaInfoGeneral = 1;
		int columnaInfoMedios = 0;
		int columnaPrograma = 1, columnaHora = 2; 
		int columnaInicioDias = 3;
		int columnaFinDias = 33;
		int columnaCunias = 34,columnaRaiting1 = 35,columnaRaiting2 = 36,columnaPonderada = 37;
		int columnaTarifa = 39,columnaTarifaTotal = 40;
		int filaFecha = 6;
		
		Calendar calActual = new GregorianCalendar();
		
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
			System.out.println("Fecha Reporte: "+mes+"/"+anio);
			
			int ultimaFilaInfoMedio = 11;
			//Se empieza desde la linea 11 de excel, donde se ubican los primeros dias. 
			//for(int i = 11; i <= sheet.getLastRowNum(); i++)
			String identificacionMedio = null;
			String codigoOficinaMedio = null;
			
			RecorrerFilas:
			for(int i = 11; i <= sheet.getLastRowNum(); i++) {
				
				//System.out.println("*** FILA: "+i);
				
				if ( i == (ultimaFilaInfoMedio+1) )
					continue RecorrerFilas;
				
				FilaComercial detalle = new FilaComercial();
				HSSFRow row = sheet.getRow(i);
				HSSFCell celdaUno = row.getCell(columnaA);
				HSSFCell celdaDos = row.getCell(columnaB);	
				if ( celdaUno!=null && celdaDos != null && celdaUno.getCellType() == HSSFCell.CELL_TYPE_BLANK &&
					 celdaDos.getCellType() == HSSFCell.CELL_TYPE_BLANK )
					continue RecorrerFilas;
				
				//INFORMACION DE MEDIO (NOMBRE, IDENTIFICACION, CODIGO OFICINA)
				//if ( i == 32 )
				//	System.out.println("");
				HSSFCell celdaMedio = row.getCell(columnaInfoMedios);
				
				if ( celdaMedio!= null && celdaMedio.getCellType() != HSSFCell.CELL_TYPE_BLANK ){
					HSSFRichTextString medioTexto = celdaMedio.getRichStringCellValue();
					//System.out.println("***********************************************");
					//System.out.println("* Info Medio: "+medioTexto);
					String infoMedioCelda = medioTexto.getString();
					String[] infoMedio = infoMedioCelda.split("-");
					
					if (infoMedio.length == 3){
						String nombreMedio = infoMedio[0];
						//System.out.println("* Nombre Medio: "+nombreMedio);
						identificacionMedio = infoMedio[1];
						//System.out.println("* Identificacion: "+identificacionMedio);
						codigoOficinaMedio = infoMedio[2];
						//System.out.println("* Codigo: "+codigoOficinaMedio);
						//System.out.println("***********************************************");
						ultimaFilaInfoMedio = i;
					}
					
				} else {
					detalle.setIdentificacionMedio(identificacionMedio);
					detalle.setCodigoMedio(codigoOficinaMedio);
					
					//NOMBRE DEL PROGRAMA CON COMERCIAL
					HSSFCell celdaPrograma = row.getCell(columnaPrograma);
					//   Si no hay nobre de programa no se hace nada con esa fila
					if ( celdaPrograma == null || 
						 celdaPrograma.getCellType() == HSSFCell.CELL_TYPE_BLANK )
						continue RecorrerFilas;
					
					HSSFRichTextString programaTexto = celdaPrograma.getRichStringCellValue();
					String nombreProgramaCelda = programaTexto.getString().trim();
					//System.out.println("Nombre Programa: "+nombreProgramaCelda);
					
					int abreParentesis = nombreProgramaCelda.indexOf("(");
					int cierraParentesis = nombreProgramaCelda.indexOf(")");
					int longNombre = abreParentesis>0 ? abreParentesis : nombreProgramaCelda.length();
					String nombrePrograma = nombreProgramaCelda.substring(0, longNombre);
					String comercial = "CUÑAS";
					if ( abreParentesis > 0 && cierraParentesis > 0 && cierraParentesis > abreParentesis ){
						comercial = nombreProgramaCelda.substring(abreParentesis+1,cierraParentesis);
						//System.out.println("comercial: "+comercial);
					}
					detalle.setPrograma(nombrePrograma);
					detalle.setComercial(comercial);
					
					
					//HORA DEL PROGRAMA
					HSSFCell celdaHora = row.getCell(columnaHora);
					HSSFRichTextString horaTexto = celdaHora.getRichStringCellValue();
					String hora = horaTexto.getString().trim();
					detalle.setHora(hora);
					//System.out.println("Hora: "+hora);
					
					
					//CUÑAS
					HSSFCell celdaCunias = row.getCell(columnaCunias);
					Integer cunias = null;
					if (celdaCunias!=null && 
						( celdaCunias.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || 
						  celdaCunias.getCellType() == HSSFCell.CELL_TYPE_FORMULA ) ){
						cunias = Double.valueOf( celdaCunias.getNumericCellValue() ).intValue();
						detalle.setTotalCunias(cunias);
					}
					//System.out.println("Cuñas: "+cunias);
					
					
					//RAITING1
					HSSFCell celdaRaiting1 = row.getCell(columnaRaiting1);
					Double raiting1 = null;
					if (celdaRaiting1!=null && celdaRaiting1.getCellType()==0){
						raiting1 = celdaRaiting1.getNumericCellValue();
						detalle.setRaiting1(raiting1);
					}
					//System.out.println("Raiting 1: "+detalle.getRaiting1());
					
					
					//RAITING2
					HSSFCell celdaRaiting2 = row.getCell(columnaRaiting2);
					Double raiting2 = null;
					if (celdaRaiting2!=null && celdaRaiting2.getCellType()==0){
						raiting2 = celdaRaiting2.getNumericCellValue();
						detalle.setRaiting2(raiting2);
					}
					//System.out.println("Raiting 2: "+detalle.getRaiting2() );
					
					
					//RAITING PONDERADO
					HSSFCell celdaRaitingPonderado = row.getCell(columnaPonderada);
					Double raitingPonderado = null;
					if (celdaRaitingPonderado!=null && 
						( celdaRaitingPonderado.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || 
						  celdaCunias.getCellType() == HSSFCell.CELL_TYPE_FORMULA ) ){
						raitingPonderado = celdaRaitingPonderado.getNumericCellValue();
						detalle.setRaitingPonderado(raitingPonderado);
					}
					//System.out.println("Raiting P: "+detalle.getRaitingPonderado() );
					
					
					//TARIFA
					HSSFCell celdaTarifa = row.getCell(columnaTarifa);
					Double tarifa = null;
					if (celdaTarifa!=null && celdaTarifa.getCellType()==0){
						tarifa = celdaTarifa.getNumericCellValue();
						detalle.setTarifa(tarifa);
					}
					//System.out.println("Tarifa : "+detalle.getTarifa());
					
					
					//TARIFA TOTAL
					HSSFCell celdaTarifaTotal = row.getCell(columnaTarifaTotal);
					Double tarifaTotal = null;
					if (celdaTarifaTotal!=null && 
						 ( celdaTarifaTotal.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ||
						   celdaTarifaTotal.getCellType() == HSSFCell.CELL_TYPE_FORMULA	 ) ){
						tarifaTotal = celdaTarifaTotal.getNumericCellValue();
						detalle.setTarifaTotal(tarifaTotal);
					}
					//System.out.println("Tarifa Total: "+detalle.getTarifaTotal());
					
					if ( ( nombreProgramaCelda!=null && !"".equals(nombreProgramaCelda) ) && 
						 ( hora!=null && !"".equals(hora) ) &&
						 cunias != null && raiting1 != null && raiting2 != null &&
						 raitingPonderado != null && tarifa != null && tarifaTotal != null )
					{
						//MAPA COMERCIAL
						ArrayList<InfoMapaComercial> dias = detalle.getComerciales();
						int contadorDias = 1;
						//if ( "CINE DEL DOMINGO".equals(nombrePrograma) )
						//	System.out.println("");
						//if ( "NOCHES DEL OSCAR".equals(nombrePrograma) )
						//	System.out.println("");
						for(int j = columnaInicioDias; j <= columnaFinDias; j++)
						{
							HSSFCell cell = row.getCell(j);
							
							if (cell!=null && cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC &&
								cell.getCellType()!=HSSFCell.CELL_TYPE_BLANK	){
								InfoMapaComercial dia = new InfoMapaComercial();
								int valor = Double.valueOf( cell.getNumericCellValue() ).intValue();
								calActual.set(Calendar.DAY_OF_MONTH, contadorDias);
								Date fecha = new Date(calActual.getTime().getTime());
								dia.setFecha(fecha);
								dia.setFrecuencia(valor);
								dias.add(dia);
							}
							contadorDias++;
						}
						
						detalles.add(detalle);
					}
				}
			}
		} else {
			System.out.println("No existe hoja con nombre: "+nombreHoja);
			detalles = null;
		}
		
		System.out.println("\n\n\n");
		for ( int i = 0 ; i < detalles.size() ; i++ ){
			FilaComercial fc = detalles.get(i);
			System.out.println(fc.getIdentificacionMedio()+"-"+fc.getCodigoMedio()+" - Programa: "+fc.getPrograma()+" : "+fc.getComercial());
			ArrayList<InfoMapaComercial> comerciales = fc.getComerciales();
			for ( InfoMapaComercial dc : comerciales ){
				System.out.println("   Fecha "+dc.getFecha()+"   Frecuencia: "+dc.getFrecuencia());
			}
		}
			
		
		return detalles;
	}
}