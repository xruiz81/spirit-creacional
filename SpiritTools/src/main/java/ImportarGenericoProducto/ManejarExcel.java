package ImportarGenericoProducto;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class ManejarExcel {

	public static ArrayList<ProductoGenerico> procesarArchivoExcel(InputStream input,String nombreHoja) throws IOException {
		ArrayList<ProductoGenerico> detalles = new ArrayList<ProductoGenerico>();
		int columnaid = -1;
		int columnaProveedor=0,columnaCodigo=0,columnaGenerico=0, columnaFamId=0, columnaCobraIva=0;		
		int columnaTipoProd =0,columnaUsaLote =0,columnaLineaId =0,columnaIva =0,columnaIce =0;
		int columnaOtroImp =0,columnaServ =0,columnaSerie =0,columnaAfectaStock =0,columnaEstado =0;
		int columnaCobraIce =0,columnaMediosProd =0,columnaLlevaInv =0,columnaAceptaDscto=0;
		
		POIFSFileSystem fs = new POIFSFileSystem(input);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet(nombreHoja);
		if (sheet!=null){
			HSSFRow cabeceraRow = sheet.getRow(0);
			System.out.println("cabeceraRow.getLastCellNum():"+cabeceraRow.getLastCellNum());
			for(int i = 0; i < 23; i++){
				HSSFCell celdaTitulo = cabeceraRow.getCell((short)i);
				if (celdaTitulo!=null){
					try{
						String nombreColumna = celdaTitulo.getStringCellValue();
						System.out.println("nombre columan:"+nombreColumna);
						if ("ID".equalsIgnoreCase(nombreColumna)) columnaid = i;
						if ("PROVEEDOR".equalsIgnoreCase(nombreColumna)) columnaProveedor = i;
						if ("CODIGO".equalsIgnoreCase(nombreColumna)) columnaCodigo = i;
						if ("PRODUCTO/SERVICIO/CODIGO/GENERICO".equalsIgnoreCase(nombreColumna)) columnaGenerico = i;
						if ("FAMID".equalsIgnoreCase(nombreColumna))	columnaFamId = i;
						if ("COBRA IVA".equalsIgnoreCase(nombreColumna))	columnaCobraIva = i;

						if ("TIPOPRODUCTO_ID".equalsIgnoreCase(nombreColumna))	columnaTipoProd = i;
						if ("USALOTE".equalsIgnoreCase(nombreColumna))	columnaUsaLote = i;
						if ("LINEAID".equalsIgnoreCase(nombreColumna))	columnaLineaId = i;
						if ("IVA".equalsIgnoreCase(nombreColumna))	columnaIva = i;
						if ("ICE".equalsIgnoreCase(nombreColumna))	columnaIce = i;
						if ("OTROIMP".equalsIgnoreCase(nombreColumna))	columnaOtroImp = i;
						if ("SERV".equalsIgnoreCase(nombreColumna))	columnaServ = i;
						if ("SERIE".equalsIgnoreCase(nombreColumna))	columnaSerie = i;
						
						if ("AFECTA_STOCK".equalsIgnoreCase(nombreColumna))	columnaAfectaStock = i;
						if ("ESTADO".equalsIgnoreCase(nombreColumna))	columnaEstado = i;
						if ("COBRA_IVA".equalsIgnoreCase(nombreColumna))	columnaCobraIva = i;
						if ("COBRAICE".equalsIgnoreCase(nombreColumna))	columnaCobraIce = i;
						if ("MEDIOS_PROD".equalsIgnoreCase(nombreColumna))	columnaMediosProd = i;
						if ("LLEVA_INVENTARIO".equalsIgnoreCase(nombreColumna))	columnaLlevaInv = i;
						if ("ACEPTA_DSCTO".equalsIgnoreCase(nombreColumna))	columnaAceptaDscto = i;
						
					}catch(Exception e){}
				}	//System.out.print((new StringBuilder(" ")).append(cabeceraRow.getCell((short)i).getStringCellValue()).toString());
			}
			
			if ( columnaid== -1 || columnaProveedor == 0|| columnaGenerico == 0 || columnaFamId == 0 || 
					columnaCobraIva==0 || columnaCodigo==0 || columnaTipoProd==0){
				System.out.println("columnaid: "+columnaid);					
				System.out.println("columnaFamId: "+columnaFamId);
				System.out.println("columnaProveedor: "+columnaProveedor);	
				System.out.println("columnaCobraIva: "+columnaCobraIva);
				System.out.println("columnaGenerico: "+columnaGenerico);
				System.out.println("columnaTipoProd: "+columnaTipoProd);
				return null;
			}
			
			for(int i = 2; i <= 29; i++)
			{
				HSSFRow row = sheet.getRow(i);
				ProductoGenerico detalle = new ProductoGenerico();				
				//celltype=1 string					//celltype=0 num
				for(int j = columnaid; j <= columnaAceptaDscto; j++)
				{				
					HSSFCell cell = row.getCell((short)j);
					if (j==columnaid){
						int id = -1;
						if (cell!=null && cell.getCellType()==0)
							id = Double.valueOf( cell.getNumericCellValue() ).intValue();
						detalle.setId(new Long(id));
						
						
						
					}
					 
					else if (j==columnaProveedor){
						String proveedorRuc = "";
						if (cell!=null)	proveedorRuc = cell.getStringCellValue();
						 
						if(proveedorRuc.length()==12) proveedorRuc="0"+proveedorRuc;
						detalle.setProveedorIdentificacion(proveedorRuc.toUpperCase());						
					}
					else if (j==columnaCodigo){
						String autoriz = "";
						if (cell!=null) autoriz = cell.getStringCellValue();					
						detalle.setCodigogen(autoriz.toUpperCase());
						
					}
					else if (j==columnaGenerico){
						String autoriz = "";
						if (cell!=null) autoriz = cell.getStringCellValue();					
						detalle.setNombregen(autoriz.toUpperCase());
						detalle.setNombreGenerico(autoriz.toUpperCase());
						
						if(autoriz.length()>20) autoriz=autoriz.substring(0,19);
						detalle.setAbreviado(autoriz.toUpperCase());
						
						
					}
					else if (j==columnaFamId){												
						int id = -1;
						
						if (cell!=null && cell.getCellType()==0)	id = Double.valueOf( cell.getNumericCellValue() ).intValue();
						if (cell!=null && cell.getCellType()==1)	id = new Integer(cell.getStringCellValue()).intValue();
						if (cell!=null && cell.getCellType()==2)	id = Double.valueOf( cell.getNumericCellValue() ).intValue();
						
						detalle.setFamiliaGenericoId(new Long(id));
						
						
						
					}
					else if (j==columnaCobraIva){
						String preimpreso = "N";
						if (cell!=null) preimpreso = cell.getStringCellValue();
						detalle.setCobraIva(preimpreso.toUpperCase());
					}					
					else if (j==columnaTipoProd){												
						int id = -1;						
												
						if (cell!=null && cell.getCellType()==0)	id = Double.valueOf( cell.getNumericCellValue() ).intValue();
						if (cell!=null && cell.getCellType()==2) id = Double.valueOf( cell.getNumericCellValue() ).intValue();
						if (cell!=null && cell.getCellType()==1)	id = new Integer(cell.getStringCellValue()).intValue();
						
						detalle.setTipoproductoId(new Long(id));						
						
					}
					else if (j==columnaUsaLote){
						String preimpreso = "N";
						if (cell!=null) preimpreso = cell.getStringCellValue();
						detalle.setUsaLote(preimpreso.toUpperCase());
					}
					else if (j==columnaLineaId){												
						int id = -1;
						if (cell!=null && cell.getCellType()==0)	id = Double.valueOf( cell.getNumericCellValue() ).intValue();
						if (cell!=null && cell.getCellType()==1)	id = new Integer(cell.getStringCellValue()).intValue();
						
						detalle.setLineaId(new Long(id));						
					}
					else if (j==columnaIva){	
						String saldo="00.0";												
						if (cell!=null) saldo=cell.getStringCellValue();						
						saldo= saldo.replace(',','.');
						if(saldo.equals("")) saldo="00.0";
						detalle.setIva(new BigDecimal(saldo));
					}
					else if (j==columnaIce){	
						String saldo="00.0";												
						if (cell!=null) saldo=cell.getStringCellValue();						
						saldo= saldo.replace(',','.');
						if(saldo.equals("")) saldo="00.0";
						detalle.setIce(new BigDecimal(saldo));
					}
					else if (j==columnaOtroImp){	
						String saldo="00.0";												
						if (cell!=null) saldo=cell.getStringCellValue();						
						saldo= saldo.replace(',','.');
						if(saldo.equals("")) saldo="00.0";
						detalle.setOtroImpuesto(new BigDecimal(saldo));
					}
					else if (j==columnaServ){
						String preimpreso = "S";
						if (cell!=null) preimpreso = cell.getStringCellValue();
						detalle.setServicio(preimpreso.toUpperCase());
					}
					else if (j==columnaSerie){
						String preimpreso = "S";
						if (cell!=null) preimpreso = cell.getStringCellValue();
						detalle.setSerie(preimpreso.toUpperCase());
					}
					else if (j==columnaAfectaStock){
						String preimpreso = "S";
						if (cell!=null) preimpreso = cell.getStringCellValue();
						detalle.setAfectastock(preimpreso.toUpperCase());
					}
					else if (j==columnaEstado){
						String preimpreso = "A";
						if (cell!=null) preimpreso = cell.getStringCellValue();
						detalle.setEstado(preimpreso.toUpperCase());
						detalle.setEstadoprod(preimpreso.toUpperCase());
					}
					else if (j==columnaCobraIce){
						String preimpreso = "N";
						if (cell!=null) preimpreso = cell.getStringCellValue();
						detalle.setCobraIce(preimpreso.toUpperCase());
					}
					else if (j==columnaMediosProd){
						String preimpreso = "M";
						if (cell!=null) preimpreso = cell.getStringCellValue();
						detalle.setMediosProduccion(preimpreso.toUpperCase());
					}
					else if (j==columnaLlevaInv){
						String preimpreso = "N";
						if (cell!=null) preimpreso = cell.getStringCellValue();
						detalle.setLlevaInventario(preimpreso.toUpperCase());
					}
					else if (j==columnaAceptaDscto){
						String preimpreso = "S";
						if (cell!=null) preimpreso = cell.getStringCellValue();
						detalle.setAceptaDescuento(preimpreso.toUpperCase());
					}
					 
				
				}			
				
				if ( detalle.getId()!=null && !"".equals(detalle.getId())) 
					detalles.add(detalle);
				else
					detalle = null;
				
				
				
			}
		} else {
			System.out.println("No existe hoja con nombre: "+nombreHoja);
			detalles = null;
		}
		return detalles;
	}
}