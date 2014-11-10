package com.spirit.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.spirit.client.SpiritAlert;

public class Archivos {

	private static final String PATH_MICROSOFT_PAINT = "C:\\WINDOWS\\system32\\MSPAINT.exe \"";
	private static final String PATH_MICROSOFT_POWERPOINT = "C:\\Archivos de programa\\Microsoft Office\\OFFICE11\\POWERPNT.exe \"";
	private static final String PATH_MICROSOFT_EXCEL = "C:\\Archivos de programa\\Microsoft Office\\OFFICE11\\EXCEL.exe \"";
	private static final String PATH_MICROSOFT_WORD = "C:\\Archivos de programa\\Microsoft Office\\OFFICE11\\WINWORD.exe \"";
	private static final String PATH_ADOBE_READER = "C:\\Archivos de programa\\Adobe\\Reader 8.0\\Reader\\AcroRd32.exe \"";

	public static void abrirArchivoDesdeServidor(String urlArchivo)
			throws IOException {
		/*
		 * String ruta = ""; if ( urlArchivo.toLowerCase().contains(":\\") )
		 * ruta = urlArchivo; else if ( urlArchivo.lastIndexOf("\\\\") > 0 ){
		 * ruta =
		 * Parametros.getRutaWindowsCarpetaServidor()+urlArchivo.substring
		 * (urlArchivo.lastIndexOf("\\")+1); } else if (
		 * urlArchivo.lastIndexOf("/") > 0 ) ruta =
		 * Parametros.getRutaWindowsCarpetaServidor
		 * ()+urlArchivo.substring(urlArchivo.lastIndexOf("/")+1); else ruta =
		 * Parametros.getRutaWindowsCarpetaServidor()+urlArchivo;
		 */

		String ruta = urlArchivo;

		//viewFileInExternalApp(ruta);
		open(ruta);
	}

	public static void abrirArchivoLocal(String urlArchivo) throws IOException {
		viewFileInExternalApp(urlArchivo);
	}

	private static void viewFileInExternalApp(String ruta) throws IOException {
		if (Desktop.isDesktopSupported()) {
			try {
				/*String urlstr = ruta.replaceAll("\\\\zeus", "").replaceAll(
						"\\\\", "/");
				URL url = new URL("ftp", "zeus", "pub/" + urlstr);
				InputStream inputStream = url.openStream();
				String[] split = urlstr.split("/");
				File temp = File.createTempFile(
						split[split.length - 1].replaceAll(".zip", ""), ".zip");
				inputStreamAFile(inputStream, temp);
				Desktop.getDesktop().open(temp);*/
				
				Desktop.getDesktop().open(new File(ruta));
				
			} catch (Exception e) {
				e.printStackTrace();
				try {
					//Desktop.getDesktop().open(new File(ruta));
					String urlstr = ruta.replaceAll("zeus", "192.168.100.9");
					Desktop.getDesktop().open(new File(urlstr));
					
				} catch (Exception e2) {
					e2.printStackTrace();
					throw new IOException("ERROR EN EL URI");
				}

			}
		} else {
			Runtime rt = Runtime.getRuntime();
			if (ruta.endsWith(".doc"))
				rt.exec(PATH_MICROSOFT_WORD + ruta + "\" ");
			else if (ruta.endsWith(".xls"))
				rt.exec(PATH_MICROSOFT_EXCEL + ruta + "\" ");
			else if (ruta.endsWith(".ppt"))
				rt.exec(PATH_MICROSOFT_POWERPOINT + ruta + "\" ");
			else if (ruta.endsWith(".gif") || ruta.endsWith(".jpg")
					|| ruta.endsWith(".jpeg") || ruta.endsWith(".bmp"))
				rt.exec(PATH_MICROSOFT_PAINT + ruta + "\" ");
			else if (ruta.endsWith(".pdf"))
				rt.exec(PATH_ADOBE_READER + ruta + "\" ");
			else if (ruta.endsWith(".txt"))
				rt.exec("notepad \"" + ruta + "\" ");
			else {
				SpiritAlert
						.createAlert(
								"No hay aplicaci\u00f3n asociada para abrir el archivo",
								SpiritAlert.WARNING);
			}
		}
	}

	public static void inputStreamAFile(InputStream entrada, File f) {
		try {
			OutputStream salida = new FileOutputStream(f);
			byte[] buf = new byte[1024];
			int len;
			while ((len = entrada.read(buf)) > 0) {
				salida.write(buf, 0, len);
			}
			salida.close();
			entrada.close();
		} catch (IOException e) {
			System.out.println("Se produjo el error : " + e.toString());
		}
	}

	public static void main(String[] args) throws Exception {
		viewFileInExternalApp("\\\\zeus\\creacional\\0990006792001\\anuncio-mayo.zip");
		// Desktop.getDesktop().open(new File(new
		// URI("file","//zeus","//creacional//0990006792001//anuncio-mayo.zip",null)));
	}
	
	
	//OTROS METODOS TOMADOS DE LA WEB
	/*public int OSDetector(){
	    int isWindows = 1;
	    int isLinux = 2;
	    int isMac = 3;

	    String os = System.getProperty("os.name").toLowerCase();
	    
	    if(os.contains("win")){
	    	return isWindows;
	    }else if(os.contains("nux") || os.contains("nix")){
	    	return isLinux;
	    }else if(os.contains("mac")){
	    	return isMac;
	    }
	    return 0;
	}*/
	
	public static void open(String ruta){
	    try{
	    	System.out.println("pasa 1");
	        if (OSDetector.isLinux() || OSDetector.isMac()){
	        	System.out.println("pasa 2");
	        	File file = new File(ruta);
	        	FileWriter writer = null;
	        	try {
	        	    writer = new FileWriter(file);
	        	    writer.write("test");
	        	} finally {
	        	    if (writer != null) writer.close();
	        	}
	        	Desktop.getDesktop().open(file);
	        }
	        else /*if(Desktop.isDesktopSupported())*/{
	        	System.out.println("pasa 3");
	        	Desktop.getDesktop().open(new File(ruta));
	        }	            
	    }catch (Exception e){
	        e.printStackTrace(System.err);
	        try {
	        	System.out.println("pasa 4");
	    		String urlstr = ruta.replaceAll("zeus", "192.168.100.9");
	    		Desktop.getDesktop().open(new File(urlstr));
	    		
	    	} catch (Exception e2) {
	    		e2.printStackTrace();
	    	}
	    }
	}
}

class OSDetector
{
    private static boolean isWindows = false;
    private static boolean isLinux = false;
    private static boolean isMac = false;

    static
    {
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println("os: " + os);
        isWindows = os.contains("win");
        isLinux = os.contains("nux") || os.contains("nix");
        isMac = os.contains("mac");
    }

    public static boolean isWindows() { return isWindows; }
    public static boolean isLinux() { return isLinux; }
    public static boolean isMac() { return isMac; };

}
