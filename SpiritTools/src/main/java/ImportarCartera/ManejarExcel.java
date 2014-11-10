package ImportarCartera;

 
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

	public static ArrayList procesarArchivoExcel(InputStream input,String nombreHoja) throws IOException {
		ArrayList detalles = new ArrayList();
		int columnaid = -1;
		int columnaFecha=0,columnaOficina=0, columnaPreimpreso=0, columnaCliente=0, columnaObservacion=0;
		int columnaFactura=0, columnaNotadeVenta=0, columnaSaldo=0,columnaAutorizacion=0,columnaEmpresa=0;
		
		POIFSFileSystem fs = new POIFSFileSystem(input);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet(nombreHoja);
		
		if (sheet!=null){
			HSSFRow cabeceraRow = sheet.getRow(0);
			System.out.println("cabeceraRow.getLastCellNum():"+cabeceraRow.getLastCellNum());
			
			for(int i = 0; i < 11; i++){
				HSSFCell celdaTitulo = cabeceraRow.getCell((short)i);
				if (celdaTitulo!=null){
					try{
						String nombreColumna = celdaTitulo.getStringCellValue();
						System.out.println("nombre columan:"+nombreColumna);
						if ("ID".equalsIgnoreCase(nombreColumna)) columnaid = i;
						//if ("EMPRESA".equalsIgnoreCase(nombreColumna)) columnaEmpresa = i;
						if ("FECHA".equalsIgnoreCase(nombreColumna)) columnaFecha = i;
						if ("OFICINA".equalsIgnoreCase(nombreColumna))	columnaOficina = i;
						if ("PREIMPRESO".equalsIgnoreCase(nombreColumna))	columnaPreimpreso = i;
						if ("AUTORIZACION".equalsIgnoreCase(nombreColumna))	columnaAutorizacion = i;
						
						if(nombreHoja.equals("ctasxCOB")){
							if ("CLIENTE".equalsIgnoreCase(nombreColumna))	columnaCliente = i;
						}else if(nombreHoja.equals("ctasXpag")){
							if ("PROVEEDOR".equalsIgnoreCase(nombreColumna))	columnaCliente = i;
						}
						
						if ("OBSERVACION".equalsIgnoreCase(nombreColumna))	columnaObservacion = i;
						if ("FACTURA".equalsIgnoreCase(nombreColumna))	columnaFactura = i;
						if ("NOTA VENTA".equalsIgnoreCase(nombreColumna))	columnaNotadeVenta = i;
						if ("SALDO".equalsIgnoreCase(nombreColumna))	columnaSaldo = i;
					}catch(Exception e){
						e.printStackTrace();
					}
				}	
			}
			
			if ( columnaid== -1 || columnaAutorizacion == 0|| columnaFecha == 0 || columnaOficina == 0 || columnaPreimpreso==0
					 || columnaCliente == 0 || columnaObservacion==0 || columnaFactura==0 || columnaNotadeVenta==0|| columnaSaldo==0){
				System.out.println("columnaid: "+columnaid);						System.out.println("columnaCuenta: "+columnaFecha);
				System.out.println("columnaNumero: "+columnaOficina);				System.out.println("columnaReferencia: "+columnaPreimpreso);
				System.out.println("columnaDebe: "+columnaCliente);					System.out.println("columnaHaber: "+columnaObservacion);
				System.out.println("columnaGlosa: "+columnaFactura);				System.out.println("columnaGlosa: "+columnaNotadeVenta);
				System.out.println("columnaGlosa: "+columnaSaldo);
				return null;
			}	
			System.out.println("sheet.getLastRowNum()------>:"+sheet.getLastRowNum());
			
			for(int i = 1; i <= sheet.getLastRowNum(); i++){
				HSSFRow row = sheet.getRow(i);
				CarteraDetalleImportadaData detalle = new CarteraDetalleImportadaData();				
				
				HSSFCell cellValor =  sheet.getRow(i).getCell((short)0);
				//cell tipo 3 indica sin valor en casilla de id
				if(cellValor != null && cellValor.getCellType() != 3){
					
					for(int j = columnaid; j <= columnaSaldo; j++)
					{				
						HSSFCell cell = row.getCell((short)j);
						if (j==columnaid){
							int id = -1;
							if (cell!=null && cell.getCellType()==0)
								id = Double.valueOf( cell.getNumericCellValue() ).intValue();
							detalle.setId(new Long(id));
							
							System.out.println("NUMERO COLUMNA:"+detalle.getId());
							
						}else if (j==columnaEmpresa){
							String empresa = "";
							if (cell!=null) 
								empresa = cell.getStringCellValue();
							detalle.setEmpresa(empresa.toUpperCase());
						}
						else if (j==columnaFecha){						
							String dat1=null;
							if(cell.getCellType()==0)
							{							
								java.util.Date fec =null;
								if (cell!=null)			fec = cell.getDateCellValue();						
								Date fechaInicio = new Date(fec.getTime());
								detalle.setFechaCartera(fechaInicio);							
							}
							if(cell.getCellType()==1)
							{	
								if (cell!=null) dat1=cell.getStringCellValue();	
								Date fec =null;
								String dia=dat1;
								String mes=dat1;
								String anio=dat1;
								mes=mes.substring(mes.indexOf("-"));
								dia=dia.substring(0,dia.indexOf("-"));
								mes=mes.substring(mes.indexOf("-")+1,dat1.lastIndexOf("-")-2);							
								anio=anio.substring(dat1.lastIndexOf("-")+1);
								int suma=0;
								if(new Integer(anio).intValue()<=99)			suma=1900;
								if(new Integer(anio).intValue()>00 && new Integer(anio).intValue()<80)			suma=2000;
								Calendar c= Calendar.getInstance();
								c.set(new Integer(anio).intValue()+suma, new Integer(mes).intValue()-1, new Integer(dia).intValue());							
								java.util.Date fechaInicio = c.getTime();
								Date fechasql = new Date(fechaInicio.getTime());
								detalle.setFechaCartera(fechasql);
							}	
						}
						else if (j==columnaOficina){
							String codOficina = "";
							
							if (cell!=null && cell.getCellType()==1)	
								codOficina = cell.getStringCellValue();			//referencia = String.valueOf(Double.valueOf(cell.getNumericCellValue()).intValue());
							detalle.setOficina(codOficina);
						}
						else if (j==columnaPreimpreso){
							String preimpreso = "";
							if (cell!=null) 
								preimpreso = cell.getStringCellValue();
							detalle.setPreimpreso(preimpreso.toUpperCase());
						}
						else if (j==columnaAutorizacion){
							String autoriz = "";
							if (cell!=null){
								if(cell.getCellType() == 0)
									autoriz = String.valueOf(cell.getNumericCellValue());
								else
									autoriz = cell.getStringCellValue();
							}
							detalle.setAutorizacion(autoriz.toUpperCase());
							
							System.out.println(detalle.getAutorizacion()+"detalle auto");
						}
						else if (j==columnaCliente){
							String clienteruc = null;
							if (cell!=null) 		clienteruc =cell.getStringCellValue();
							detalle.setClienteruc(clienteruc.toString());	
						}
						else if (j==columnaObservacion){
							String observ = "";
							if (cell!=null) 		observ = cell.getStringCellValue();
							detalle.setObservacion(observ.toUpperCase());
						}
						else if (j==columnaFactura){
							String fact = "";
							if (cell!=null) 		fact = cell.getStringCellValue();						
							if(fact.equals("X")) detalle.setTipodoc("FACTURA");
							detalle.setFactura(fact.toUpperCase());
						}
						else if (j==columnaNotadeVenta){
							String nv = "";
							if (cell!=null) 		nv = cell.getStringCellValue();
							if(nv.equals("X")) detalle.setTipodoc("NOTA DE VENTA");
							detalle.setNotaventa(nv.toUpperCase());
						}
						else if (j==columnaSaldo){						
							//double saldo = 0.0;
							Double saldo = 0D;
							//if (cell!=null && cell.getCellType()==0)			saldo = cell.getNumericCellValue();						
							if (cell!=null) 
								saldo=cell.getNumericCellValue();						
							//saldo= saldo.replace(',','.');
							if(saldo.equals("")) saldo = 0D;
							detalle.setSaldo(new BigDecimal(saldo));//detalle.setSaldo(new BigDecimal(Math.abs(saldo)));
						}
					}			
					
					if ( detalle.getId()!=null && !"".equals(detalle.getId())) 
						detalles.add(detalle);
					else
						detalle = null;
				}
			}				
		}
		
		return detalles;
	}
}