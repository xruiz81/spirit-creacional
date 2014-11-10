package com.spirit.medios.gui.importer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;

public class PautaImporter {

	public static Vector retornarPautas(){
		try{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			//fileChooser.setAccessory(new LabelAccessory(fileChooser));

			FileFilter filterXls = new ExtensionFileFilterExcel("Excel (.xls)",
					new String[]{"xls"} );
			fileChooser.addChoosableFileFilter(filterXls);
			
			int status = fileChooser.showOpenDialog(Parametros.getMainFrame());

			if (status == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				return procesarArchivoExcel(new FileInputStream(selectedFile));
			}
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}

		return null;
	}

	private static Vector procesarArchivoExcel(InputStream input) throws IOException {
		Vector<Pauta> pautas = new Vector<Pauta>();
		int columnaId = 0;
		int columnaCanal=0, columnaPrograma=0, columnaGrupo=0, columnaHoraInicio=0;
		int columnaHoraFinal=0, columnaDias=0, columnaDuracion=0, columnaRating=0;
		
		POIFSFileSystem fs = new POIFSFileSystem(input);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet("PAUTA");
		if (sheet!=null){
			HSSFRow cabeceraRow = sheet.getRow(2);
			for(int i = 0; i < cabeceraRow.getLastCellNum(); i++){
				HSSFCell celdaTitulo = cabeceraRow.getCell((short)i);
				if (celdaTitulo!=null){
					try{
						String nombreColumna = celdaTitulo.getStringCellValue();
						if ("canal".equalsIgnoreCase(nombreColumna)) columnaCanal = i;
						if ("programa".equalsIgnoreCase(nombreColumna))	columnaPrograma = i;
						if ("grupo".equalsIgnoreCase(nombreColumna))	columnaGrupo = i;
						if ("inicio".equalsIgnoreCase(nombreColumna))	columnaHoraInicio = i;
						if ("final".equalsIgnoreCase(nombreColumna))	columnaHoraFinal = i;
						if ("d\u00edas".equalsIgnoreCase(nombreColumna))	columnaDias = i;
						if ("duraci\u00f3n".equalsIgnoreCase(nombreColumna))	columnaDuracion = i;
						if ("rating".equalsIgnoreCase(nombreColumna))	columnaRating = i;
					}catch(Exception e){}
				}
					//System.out.print((new StringBuilder(" ")).append(cabeceraRow.getCell((short)i).getStringCellValue()).toString());
			}
			
			for(int i = 3; i <= sheet.getLastRowNum(); i++)
			{
				HSSFRow row = sheet.getRow(i);
				Pauta pauta = new Pauta();
				for(int j = 0; j < row.getLastCellNum(); j++)
				{
					HSSFCell cell = row.getCell((short)j);
					if (j==0){
						if (cell.getCellType()==0)
							pauta.setId(Double.valueOf(cell.getNumericCellValue()).intValue());
						else
							pauta.setId(-1);
					}
					if (j==columnaCanal){
						String canal = "";
						if (cell.getCellType()==0)
							canal = String.valueOf(cell.getNumericCellValue());
						else
							canal = cell.getStringCellValue();
						pauta.setCanal(canal);
					}else if (j==columnaPrograma){
						String nombre = "";
						if (cell.getCellType()==0)
							nombre = String.valueOf(Double.valueOf(cell.getNumericCellValue()).intValue());
						else
							nombre = cell.getStringCellValue();
						pauta.setPrograma(nombre);
					}else if (j==columnaGrupo){
						
					}
					if ( j==columnaHoraInicio){
						pauta.setHoraInicio(cell.getDateCellValue());
					} else if ( j==columnaHoraFinal ){
						pauta.setHoraFinal(cell.getDateCellValue());
					} else if (j==columnaDias){
						pauta.setDias(cell.getStringCellValue().toCharArray());
					} else if (j==columnaDuracion){
						pauta.setDuracion(cell.getNumericCellValue());
					} else if (j==columnaRating){
						pauta.setRating(cell.getNumericCellValue());
					}
				}
				if (!pauta.getCanal().equals("")) 
					pautas.add(pauta);
				else
					pauta = null;
			}
		} else {
			SpiritAlert.createAlert("No existe hoja con nombre: PAUTA", SpiritAlert.WARNING);
		}
		return pautas;
	}
}
