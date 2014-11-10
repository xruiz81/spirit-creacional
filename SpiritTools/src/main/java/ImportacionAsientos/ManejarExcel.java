package ImportacionAsientos;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class ManejarExcel {

	public static ArrayList<DetalleAsiento> procesarArchivoExcel(InputStream input,String nombreHoja) throws IOException {
		ArrayList<DetalleAsiento> detalles = new ArrayList<DetalleAsiento>();
		int columnaCuenta = -1;
		int columnaNumero=0, columnaReferencia=0, columnaDebe=0, columnaHaber=0;
		int columnaGlosa=0;
		
		POIFSFileSystem fs = new POIFSFileSystem(input);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet(nombreHoja);
		if (sheet!=null){
			HSSFRow cabeceraRow = sheet.getRow(0);
			for(int i = 0; i < cabeceraRow.getLastCellNum(); i++){
				HSSFCell celdaTitulo = cabeceraRow.getCell((short)i);
				if (celdaTitulo!=null){
					try{
						String nombreColumna = celdaTitulo.getStringCellValue();
						if ("cuenta".equalsIgnoreCase(nombreColumna)) columnaCuenta = i;
						if ("numero".equalsIgnoreCase(nombreColumna))	columnaNumero = i;
						if ("referencia".equalsIgnoreCase(nombreColumna))	columnaReferencia = i;
						if ("debe".equalsIgnoreCase(nombreColumna))	columnaDebe = i;
						if ("haber".equalsIgnoreCase(nombreColumna))	columnaHaber = i;
						if ("glosa".equalsIgnoreCase(nombreColumna))	columnaGlosa = i;
					}catch(Exception e){}
				}
					//System.out.print((new StringBuilder(" ")).append(cabeceraRow.getCell((short)i).getStringCellValue()).toString());
			}
			
			if ( columnaCuenta == -1 || columnaNumero == 0 || columnaReferencia==0
				 || columnaDebe == 0 || columnaHaber==0 || columnaGlosa==0 ){
				System.out.println("columnaCuenta: "+columnaCuenta);
				System.out.println("columnaNumero: "+columnaNumero);
				System.out.println("columnaReferencia: "+columnaReferencia);
				System.out.println("columnaDebe: "+columnaDebe);
				System.out.println("columnaHaber: "+columnaHaber);
				System.out.println("columnaGlosa: "+columnaGlosa);
				return null;
			}
			
			for(int i = 1; i <= sheet.getLastRowNum(); i++)
			{
				HSSFRow row = sheet.getRow(i);
				DetalleAsiento detalle = new DetalleAsiento();
				for(int j = columnaCuenta; j <= columnaGlosa; j++)
				{
					HSSFCell cell = row.getCell((short)j);
					if (j==columnaCuenta){
						String cuenta = "";
						if (cell!=null && cell.getCellType()==0)
							cuenta = String.valueOf( Double.valueOf( cell.getNumericCellValue() ).longValue() );
						detalle.setCuenta(cuenta);
					}
					if (j==columnaNumero){
						int numero = -1;
						if (cell!=null && cell.getCellType()==0)
							numero = Double.valueOf( cell.getNumericCellValue() ).intValue();
						detalle.setNumero(numero);
					}else if (j==columnaReferencia){
						String referencia = "NINGUNA";
						if (cell!=null && cell.getCellType()==0)
							referencia = String.valueOf(Double.valueOf(cell.getNumericCellValue()).intValue());
						//else
						//	referencia = cell.getStringCellValue();
						detalle.setReferencia(referencia);
					}else if (j==columnaDebe){
						double debe = 0.0;
						if (cell!=null && cell.getCellType()==0)
							debe = cell.getNumericCellValue();
						
						detalle.setDebe(Math.abs(debe));
					}else if (j==columnaHaber){
						double haber = 0.0;
						if (cell!=null && cell.getCellType()==0)
							haber = cell.getNumericCellValue();
						
						detalle.setHaber(Math.abs(haber));
					}else if (j==columnaGlosa){
						String glosa = "";
						if (cell!=null) 
							glosa = cell.getStringCellValue();
						detalle.setGlosa(glosa.toUpperCase());
					}
				}
				if ( detalle.getCuenta()!=null && !"".equals(detalle.getCuenta())) 
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