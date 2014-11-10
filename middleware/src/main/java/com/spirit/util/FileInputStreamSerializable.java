package com.spirit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.zip.ZipOutputStream;

import com.spirit.exception.GenericBusinessException;

public class FileInputStreamSerializable implements Serializable{

	private static final long serialVersionUID = 6259156942325633588L;
	private String nombreArchivo="";
	private byte[] data;

	public FileInputStreamSerializable(File archivo) throws GenericBusinessException{
		leerArchivo(archivo);
	}

	public FileInputStreamSerializable(File archivo,String nombreArchivo) throws GenericBusinessException{
		leerArchivo(archivo);
		this.nombreArchivo = nombreArchivo;
	}

	void leerArchivo(File archivo) throws GenericBusinessException{
		try{
			data = new byte[ (int)(archivo.length()) ];
			FileInputStream fis = new FileInputStream(archivo);
			fis.read( data );
			fis.close();
		}catch( Exception e ){
			e.printStackTrace();
			throw new GenericBusinessException("Error al tratar de enviar archivo: "+archivo.getName()+" al servidor");
		}
	}

	public void crearArchivoServidor( OutputStream out  ){
		try{
			out.write( data );
		}catch( Exception e ){
			e.printStackTrace();
		}
	}
	
	public void crearArchivoServidorZip(ZipOutputStream out){
		try{
			out.write( data );
		}catch( Exception e ){
			e.printStackTrace();
		}
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
}