package ImportarBasicosYdatClientes;

 
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class ManejarExcel {

	public static ArrayList<ClientesCOficina> procesarArchivoExcel(InputStream input,String nombreHoja) throws IOException {
		ArrayList<ClientesCOficina> detalles = new ArrayList<ClientesCOficina>();
		int columnaid = -1;
		int columnaTipoIdentId=0,columnaIdentif=0, columnaNombreLeg=0, columnaRazonSoc=0, columnaRepres=0;
		int columnaCorporId=0, columnaFechaCrea=0, columnaEstado=0,columnaTipoCliente=0,columnaTipoProve=0;
		int columnaTipoNegocio=0, columnaObservacion=0, columnaUsuarioFinal=0,columnaContrib=0,columnaTipoPersona=0,columnaLlevaCont=0;
		
		
		int columnaidDet=0,columnaCodigo=0, columnaCiudadid=0, columnaDireccion=19;
		int columnaTelefono=0,columnaFax=0, columnaEmail=0, columnaFecha=0, columnaMonCredito=0;
		int columnaMonGarantia=0,columnaCalif=26, columnaEstadoDet=0, columnaObservDetalle=0, columnaCodigoProv=0, columnaDescrip=0;
	 
	

		
		POIFSFileSystem fs = new POIFSFileSystem(input);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet(nombreHoja);
		if (sheet!=null){
			HSSFRow cabeceraRow = sheet.getRow(0);
			
			for(int i = 0; i < 32; i++){
				HSSFCell celdaTitulo = cabeceraRow.getCell((short)i);
				if (celdaTitulo!=null){
					try{
						String nombreColumna = celdaTitulo.getStringCellValue();					
						
						if ("ID".equalsIgnoreCase(nombreColumna)) columnaid = i;
						if ("TIPOIDENTIFICACION_ID".equalsIgnoreCase(nombreColumna)) columnaTipoIdentId = i;
						if ("IDENTIFICACION".equalsIgnoreCase(nombreColumna)) columnaIdentif = i;
						if ("NOMBRE_LEGAL".equalsIgnoreCase(nombreColumna))	columnaNombreLeg= i;
						if ("RAZON_SOCIAL".equalsIgnoreCase(nombreColumna))	columnaRazonSoc = i;
						if ("REPRESENTANTE".equalsIgnoreCase(nombreColumna))	columnaRepres = i;
						if ("CORPORACION_ID".equalsIgnoreCase(nombreColumna))	columnaCorporId = i;						
												
						if (nombreColumna.length()>13 )
						{
							String nombre2=nombreColumna.substring(0,14);
							if ("FECHA_CREACION".equalsIgnoreCase(nombre2))	columnaFechaCrea = i; 
							//nombreColumna = celdaTitulo.getStringCellValue();
						}
						
						if (nombreColumna.length()>5 )
						{
							String nombre2=nombreColumna.substring(0,6);
							if("ESTADO".equalsIgnoreCase(nombre2)) 
								columnaEstado = i; 
							//nombreColumna = celdaTitulo.getStringCellValue();
						}
							
						if ("TIPOCLIENTE_ID".equalsIgnoreCase(nombreColumna))	columnaTipoCliente = i;
						if ("TIPOPROVEEDOR_ID".equalsIgnoreCase(nombreColumna))	columnaTipoProve = i;
						if ("TIPONEGOCIO_ID".equalsIgnoreCase(nombreColumna))	columnaTipoNegocio = i;												
						if ("OBSERVACION".equalsIgnoreCase(nombreColumna))	columnaObservacion = i;
					
						
						if ("USUARIOFINAL".equalsIgnoreCase(nombreColumna))	columnaUsuarioFinal = i;
						if ("CONTRIBUYENTE_ESPECIAL".equalsIgnoreCase(nombreColumna))	columnaContrib = i;
						if ("TIPO_PERSONA".equalsIgnoreCase(nombreColumna))	columnaTipoPersona = i;
						if ("LLEVA_CONTABILIDAD".equalsIgnoreCase(nombreColumna))	columnaLlevaCont = i;
						
						if ("CODIGO".equalsIgnoreCase(nombreColumna))	columnaCodigo = i;						
						if ("CIUDADID".equalsIgnoreCase(nombreColumna))	columnaCiudadid = i;
						if ("DIRECCION".equalsIgnoreCase(nombreColumna))	columnaDireccion = i;
						if ("TELEFONO".equalsIgnoreCase(nombreColumna))	columnaTelefono = i;
						if ("FAX".equalsIgnoreCase(nombreColumna))	columnaFax = i;
						if ("EMAIL".equalsIgnoreCase(nombreColumna))	columnaEmail = i;

						if (nombreColumna.length()>4 )
						{
							String nombre2=nombreColumna.substring(0,5);
							if("FECHA(".equalsIgnoreCase(nombre2)) 
								columnaEstado = i; 
							//nombreColumna = celdaTitulo.getStringCellValue();
						}
						
						//if ("FECHA(".equalsIgnoreCase(nombreColumna.substring(0,5)))	columnaFecha = i;
						if ("MONTOCREDITO".equalsIgnoreCase(nombreColumna))	columnaMonCredito = i;
						if ("MONTOGARANTIA".equalsIgnoreCase(nombreColumna))	columnaMonGarantia = i;
						if ("CALIFICACION".equalsIgnoreCase(nombreColumna))	columnaCalif = i;
						if ("OBSERVACIONDET".equalsIgnoreCase(nombreColumna))	columnaObservDetalle = i;
						if ("ESTADODET".equalsIgnoreCase(nombreColumna))	columnaEstadoDet = i;
						if ("DESCRIPCION".equalsIgnoreCase(nombreColumna))	columnaDescrip = i;
						if ("CODIGOPROV_AUTO".equalsIgnoreCase(nombreColumna))	columnaCodigoProv = i;
				

						 
					}catch(Exception e){
						
						System.out.println("eeeeeee--"+e);
					}
				}	//System.out.print((new StringBuilder(" ")).append(cabeceraRow.getCell((short)i).getStringCellValue()).toString());
			}
					
			
			
			if ( columnaid== -1 || columnaTipoIdentId == 0|| columnaIdentif == 0 || columnaNombreLeg == 0 || columnaRazonSoc==0
				 || columnaRepres == 0 || columnaCorporId==0 || columnaFechaCrea==0 || columnaEstado==0|| columnaTipoCliente==0
				 || columnaTipoProve == 0 || columnaTipoNegocio==0 || columnaObservacion==0 || columnaUsuarioFinal==0|| columnaContrib==0
				 || columnaTipoPersona==0|| columnaLlevaCont==0){
				
				System.out.println("<<<---->>>>");
				 
				return null;
				
			}	
			
			System.out.println("sheet.getLastRowNum()------>:"+sheet.getLastRowNum());
			for(int i = 1; i <= sheet.getLastRowNum(); i++){
				
				HSSFCell cellValor =  sheet.getRow(i).getCell((short)1);
				//cell tipo 3 indica sin valor en casilla de id
				if(cellValor != null && cellValor.getCellType() != 3){
					//System.out.println("fin del listado");
					
					HSSFRow row = sheet.getRow(i);
					ClientesCOficina detalle = new ClientesCOficina();				
					
					for(int j = columnaid; j <= columnaCodigoProv; j++)
					{
						HSSFCell cell = row.getCell((short)j);
						
						if (j==columnaid){
							int id = -1;
							if (cell!=null && cell.getCellType()==0)	id = Double.valueOf( cell.getNumericCellValue() ).intValue();
							detalle.setId(new Long(id));
							
							 
							
						}else if (j==columnaTipoIdentId){
							int tipoIdentid = -1;						
							if (cell!=null && cell.getCellType()==0)	
								tipoIdentid = Double.valueOf( cell.getNumericCellValue() ).intValue();
							if (cell!=null && cell.getCellType()==1)
								tipoIdentid = new Integer(cell.getStringCellValue()).intValue();	
							
							detalle.setTipoidentificacionId(new Long(tipoIdentid));						
						}
						else if (j==columnaIdentif){
							String empresa = "";
							if (cell!=null) 
								empresa = cell.getStringCellValue();
							
							
							if(empresa.length()==12) empresa="0"+empresa;
							detalle.setIdentificacion(empresa.toUpperCase());
						}
						else if (j==columnaNombreLeg){
							String empresa = "";
							if (cell!=null) 
								empresa = cell.getStringCellValue();
							detalle.setNombreLegal(empresa.toUpperCase());
						}
						else if (j==columnaRazonSoc){
							String empresa = "";
							if (cell!=null) 
								empresa = cell.getStringCellValue();
							detalle.setRazonSocial(empresa.toUpperCase());
						}
						else if (j==columnaRepres){
							String empresa = "";
							if (cell!=null) 
								empresa = cell.getStringCellValue();
							detalle.setRepresentante(empresa.toUpperCase());
						}
						
						else if (j==columnaCorporId){
							
							int idCorp = -1;
							if (cell!=null && cell.getCellType()==0)	idCorp = Double.valueOf( cell.getNumericCellValue() ).intValue();
							detalle.setCorporacionId(new Long(idCorp));
							
						}
						
						else if (j==columnaFechaCrea){						
							String dat1=null;
							System.out.println(i+"<-ceell"+cell);
							//System.out.println(j+"<---ceell-->"+cell.getCellType());
							if(cell != null && cell.getCellType()==0)
							{							
								java.util.Date fec =null;
								if (cell!=null)			fec = cell.getDateCellValue();						
								Date fechaInicio = new Date(fec.getTime());
								detalle.setFechaCreacion(new Timestamp(fechaInicio.getTime()));						
							}
							if(cell != null && cell.getCellType()==1)
							{	
								if (cell!=null) dat1=cell.getStringCellValue();	
								
								Date fec =null;
								String dia=dat1;
								String mes=dat1;
								String anio=dat1;
								dia=dia.substring(0,dia.indexOf("-"));
								mes=mes.substring(mes.indexOf("-"));							
								mes=mes.substring(mes.indexOf("-")+1,dat1.lastIndexOf("-")-2);		
														
								String aniolength=anio.substring(dat1.lastIndexOf("-")+1);
								int suma=0;
								if(aniolength.length()==4)
									anio=aniolength;
								else if(aniolength.length()==2)
								{
									anio=anio.substring(dat1.lastIndexOf("-")+1,dat1.lastIndexOf("-")+3);
									if(new Integer(anio).intValue()<=99)			suma=1900;
									if(new Integer(anio).intValue()>00 && new Integer(anio).intValue()<80)			suma=2000;								
								}
								
								
								
								Calendar c= Calendar.getInstance();
								c.set(new Integer(anio).intValue()+suma, new Integer(mes).intValue()-1, new Integer(dia).intValue());							
								java.util.Date fechaInicio = c.getTime();
								Date fechasql = new Date(fechaInicio.getTime());
								detalle.setFechaCreacion(new Timestamp(fechasql.getTime()));		
								
							}	
						}
						else if (j==columnaEstado){
							String codOficina = "";
							
							
							
							if (cell!=null && cell.getCellType()==0)
								codOficina = new Double(cell.getNumericCellValue()).toString();
							if (cell!=null && cell.getCellType()==1)
								codOficina = cell.getStringCellValue();	
							
							detalle.setEstado(codOficina);
							detalle.setEstadodet(codOficina);
						}
						
						else if (j==columnaTipoCliente){
							int idTipoC = -1;
							if (cell!=null && cell.getCellType()==0)	idTipoC = Double.valueOf( cell.getNumericCellValue() ).intValue();
							detalle.setTipoclienteId(new Long(idTipoC));
						}
						else if (j==columnaTipoProve){
							int idTipoProv = -1;
							if (cell!=null && cell.getCellType()==0)	
								idTipoProv = Double.valueOf( cell.getNumericCellValue() ).intValue();
							detalle.setTipoproveedorId(new Long(idTipoProv));
						}
						else if (j==columnaTipoNegocio){
							int idTipoNeg = -1;
							if (cell!=null && cell.getCellType()==0)	idTipoNeg = Double.valueOf( cell.getNumericCellValue() ).intValue();
							detalle.setTiponegocioId(new Long(idTipoNeg));
						}
						else if (j==columnaObservacion){
							String observ = "";
							if (cell!=null) 		observ = cell.getStringCellValue();
							detalle.setObservacion(observ.toUpperCase());
						}
						else if (j==columnaUsuarioFinal){
							String fact = "";
							if (cell!=null) 		fact = cell.getStringCellValue();
							detalle.setUsuariofinal(fact.toUpperCase());
						}
						else if (j==columnaContrib){
							String nv = "";
							if (cell!=null) 		nv = cell.getStringCellValue();						
							detalle.setContribuyenteEspecial(nv.toUpperCase());
						}
						else if (j==columnaTipoPersona){						
							String tipoPers = "";
							if (cell!=null) 		tipoPers = cell.getStringCellValue();						
							detalle.setTipoPersona(tipoPers.toUpperCase());
						}
						else if (j==columnaLlevaCont){						
							String llevaCont = "";
							if (cell!=null) 		llevaCont = cell.getStringCellValue();						
							detalle.setLlevaContabilidad(llevaCont.toUpperCase());
						}
						
						
						else if (j==columnaCodigo){
							String observ = "";
								
							if (cell!=null && cell.getCellType()==0)			observ = new Double(cell.getNumericCellValue()).toString();
							else if (cell!=null) 		observ = cell.getStringCellValue();
							
							detalle.setCodigo(observ.toUpperCase());
						}
						else if (j==columnaCiudadid){
							int idTipoNeg = -1;
							if (cell!=null && cell.getCellType()==0)	idTipoNeg = Double.valueOf( cell.getNumericCellValue() ).intValue();
							detalle.setCiudadId(new Long(idTipoNeg));
						}
						 else if (j==columnaDireccion){
							String observ = "";
							if (cell!=null) 		observ = cell.getStringCellValue();
							detalle.setDireccion(observ.toUpperCase());
						}
						else if (j==columnaTelefono){
							String observ = "";
							//if (cell!=null) 		observ = cell.getStringCellValue();
							if (cell!=null && cell.getCellType()==0)			observ = new Double(cell.getNumericCellValue()).toString();
							detalle.setTelefono(observ.toUpperCase());
						}
						else if (j==columnaFax){
							String observ = "";
							
							if (cell!=null && cell.getCellType()==0)			
								{
								Double observd = new Double(cell.getNumericCellValue());
								int ob=observd.intValue();
								observ=new Integer(ob).toString();							
								}
							//if (cell!=null) 		observ = cell.getStringCellValue();
							detalle.setFax(observ.toUpperCase());
							
							 
						}
						else if (j==columnaEmail){
							String observ = "";
							if (cell!=null) 		observ = cell.getStringCellValue();
							detalle.setEmail(observ.toUpperCase());
						}
						else if (j==columnaFecha){
							/*
							String observ = "";
							if (cell!=null && cell.getCellType()==0)			observ = new Double(cell.getNumericCellValue()).toString();
							detalle.setFechaCreaciondet(detalle.getFechaCreacion());
							*/
						}
						else if (j==columnaMonCredito){
							//double saldo = 0.0;
							String saldo="0.00";
							if (cell!=null && cell.getCellType()==0)			saldo = new Double(cell.getNumericCellValue()).toString();						
							//if (cell!=null) saldo=cell.getStringCellValue();						
							saldo= saldo.replace(',','.');
							if(saldo.equals("")) saldo="0.00";
							
							detalle.setMontoCredito(new BigDecimal(saldo));//detalle.setSaldo(new BigDecimal(Math.abs(saldo)));
						}
						else if (j==columnaMonGarantia){
							//double saldo = 0.0;
							String saldo="0.00";
							//if (cell!=null && cell.getCellType()==0)			saldo = cell.getNumericCellValue();
							if (cell!=null && cell.getCellType()==0)			saldo = new Double(cell.getNumericCellValue()).toString();
							saldo= saldo.replace(',','.');
							if(saldo.equals("")) saldo="0.00";
							detalle.setMontoGarantia(new BigDecimal(saldo));//detalle.setSaldo(new BigDecimal(Math.abs(saldo)));
						}
						
						else if (j==columnaCalif){
							String calificacion = "";
							//if (cell!=null) 		observ = cell.getStringCellValue();
							if (cell!=null)			
								calificacion = cell.getStringCellValue();
							
							detalle.setCalificacion(calificacion.toUpperCase());
							System.out.println("d");
						}
						else if (j==columnaObservacion){
							String observ = "";
							if (cell!=null) 		observ = cell.getStringCellValue();
							detalle.setObservacion(observ.toUpperCase());
						}
						else if (j==columnaEstadoDet){
							String observ = "";
							//if (cell!=null) 		observ = cell.getStringCellValue();
							if (cell!=null && cell.getCellType()==0)			observ = new Double(cell.getNumericCellValue()).toString();

							
							
							detalle.setEstadodet(observ.toUpperCase());
						}
						else if (j==columnaDescrip){
							String observ = "";
							if (cell!=null) 		observ = cell.getStringCellValue();
							detalle.setDescripcion(observ.toUpperCase());
						}
						else if (j==columnaCodigoProv){
							String observ = "";
							if (cell!=null) 		observ = cell.getStringCellValue();
							detalle.setCodigoProveedorAuto(observ.toUpperCase());
						}

					}			
					
					if ( detalle.getId()!=null && !"".equals(detalle.getId()) && !detalle.getNombreLegal().equals("")) 
						detalles.add(detalle);
					else
						detalle = null;
				}else{
					break;
				}
			}
		} else {
			System.out.println("No existe hoja con nombre: "+nombreHoja);
			detalles = null;
		}
		return detalles;
	}
}