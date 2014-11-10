package com.spirit.general.session;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spirit.exception.GenericBusinessException;
import com.spirit.util.FileInputStreamSerializable;

/**
 * The <code>FileManagerSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class FileManagerSessionEJB implements FileManagerSessionRemote,FileManagerSessionLocal  {

	private static final long serialVersionUID = 4640874398418404557L;
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	/**
	 * The logger object.
	 */
	//private static Logger log = LogService.getLogger(JbpmManagerSessionEJB.class);

	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean existeArchivo(String rutaDestino,String nombreArchivo) throws GenericBusinessException{
		try {
			File nuevoArchivo = new File(rutaDestino+nombreArchivo);
			if ( nuevoArchivo.exists() )
				return true;
			return false;
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error en la verificaci\u00f3n de archivo: \""+nombreArchivo+"\" en el servidor");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int guardarArchivo(FileInputStreamSerializable archivoFuente, String rutaDestino,String nombreArchivo)
	throws GenericBusinessException{
		//1 - Exito
		//2 - Existe
		//3 - No hay Archivo Fuente
		FileOutputStream archivoDestino = null;
		try {
			if (archivoFuente==null)
				return 3;
			File nuevoArchivo = new File(rutaDestino+nombreArchivo.replaceAll(" ", "_"));
			//if ( nuevoArchivo.exists() )
			//	return 2;
			archivoDestino = new FileOutputStream( nuevoArchivo ) ;
			archivoFuente.crearArchivoServidor(archivoDestino);
			archivoDestino.close();
			return 1;
		} catch(Exception e){
			try{
				if (archivoDestino != null) {
					archivoDestino.close();
				}
			} catch(IOException ex){}
			e.printStackTrace();
			if (e instanceof FileNotFoundException)
				throw new GenericBusinessException("Error en la creaci\u00f3n de archivo: \""+nombreArchivo+"\" en el servidor");
			throw new GenericBusinessException("Error al guardar el archivo en el servidor");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int guardarArchivoZip(FileInputStreamSerializable archivoFuente, String rutaDestino,String nombreArchivo)
	throws GenericBusinessException{
		//1 - Exito
		//2 - Existe
		//3 - No hay Archivo Fuente
		ZipOutputStream out = null;
		try {
			if(!(new File(rutaDestino).exists())){
	    		new File(rutaDestino).mkdir();
	    	}
			
			if (archivoFuente==null)
				return 3;
			
			int puntoExt = nombreArchivo.lastIndexOf(".");
	    	String strFilename = nombreArchivo.substring(0, puntoExt) + ".zip";
	        String zipFilename = rutaDestino+strFilename.replaceAll(" ", "_");
	        out = new ZipOutputStream(new FileOutputStream(zipFilename));
	        out.setLevel(Deflater.DEFAULT_COMPRESSION);
	        out.putNextEntry(new ZipEntry(nombreArchivo));
			archivoFuente.crearArchivoServidorZip(out);
			out.closeEntry();
			out.close();
			return 1;
		} catch(Exception e){
			try{
				if (out != null) {
					out.close();
				}
			} catch(IOException ex){
				ex.printStackTrace();
			}
			e.printStackTrace();
			if (e instanceof FileNotFoundException)
				throw new GenericBusinessException("Error en la creaci\u00f3n de archivo: \""+nombreArchivo+"\" en el servidor");
			throw new GenericBusinessException("Error al guardar el archivo en el servidor");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int guardarArchivo(File archivoFuente, String rutaDestino, String nombreArchivo) throws GenericBusinessException{
		//1 - Exito
		//2 - Existe
		//3 - No hay Archivo Fuente
		byte[] buffer = new byte[18024];

	    try {
	    	if(!(new File(rutaDestino).exists())){
	    		new File(rutaDestino).mkdir();
	    	}	    	
	    	
	    	if (archivoFuente == null){
	    		return 3;
	    	}
	    	
	    	//String zipFileName = "c:\\example.zip";
	    	int puntoExt = nombreArchivo.lastIndexOf(".");
	    	String strFilename = nombreArchivo.substring(0, puntoExt) + ".zip";
	        String zipFilename = rutaDestino+strFilename.replaceAll(" ", "_");
	        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFilename));
	        out.setLevel(Deflater.DEFAULT_COMPRESSION);
	        
	        FileInputStream in = new FileInputStream(archivoFuente);
	        out.putNextEntry(new ZipEntry(nombreArchivo));
	        int len;
	        while ((len = in.read(buffer)) > 0){
		        out.write(buffer, 0, len);
		    }
	        
	        out.closeEntry();
	        in.close();
	        out.close();
	        
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    return 1;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public FileInputStreamSerializable obtenerArchivo(String rutaDestino,String nombreArchivo)
	throws GenericBusinessException{
		FileOutputStream archivoDestino = null;
		try {
			File archivo = new File(rutaDestino+nombreArchivo);
			FileInputStreamSerializable archivoSerializable =
				new FileInputStreamSerializable( archivo );
			return archivoSerializable;
		} catch(Exception e){
			try{
				if (archivoDestino != null) {
					archivoDestino.close();
				}
			} catch(IOException ex){}
			e.printStackTrace();
			if (e instanceof FileNotFoundException)
				throw new GenericBusinessException("Error en la creaci\u00f3n de archivo: \""+nombreArchivo+"\" en el servidor");
			throw new GenericBusinessException("Error al guardar el archivo en el servidor");
		}
	}
	
}
