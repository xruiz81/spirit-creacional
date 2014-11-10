package com.spirit.general.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileSaver {
	
	//public static void copy(File src, File dst) throws IOException { 
	public static void copy(FileInputStream src, File dst) throws IOException { 	
		//InputStream in = new FileInputStream(src); 
		InputStream in = src; 
        OutputStream out = new FileOutputStream(dst); 
                
        byte[] buf = new byte[1024]; 
        int len; 
        while ((len = in.read(buf)) > 0) { 
            out.write(buf, 0, len); 
        } 
        in.close(); 
        out.close(); 
    }	
	
	/*
	public static String carpetaServidorArchivos = "";
	
	public static void save(File archivo) {
		
		boolean existeCarpetaServidor = (new File(carpetaServidorArchivos)).exists();

		if (!existeCarpetaServidor) {
			boolean success = (new File(carpetaServidorArchivos)).mkdirs();
			if (!success) { 

			}
		}

		//File archivoOrigen = new File(urlArchivo);
		File archivoDestino = new File(carpetaServidorArchivos + archivo.getName());

		try {
			InputStream in = new FileInputStream(archivo);
			OutputStream out = new FileOutputStream(archivoDestino);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}
