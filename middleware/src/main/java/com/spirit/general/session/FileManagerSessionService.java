package com.spirit.general.session;

import java.io.File;
import java.io.Serializable;

import com.spirit.exception.GenericBusinessException;
import com.spirit.util.FileInputStreamSerializable;


/**
 * The <code>FileManagerSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:33 $
 */
public interface FileManagerSessionService extends Serializable {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	//public long iniciarProceso(String nombreProceso, String nombrePanelSiguiente) throws ComprasBpmException;
	public boolean existeArchivo(String rutaDestino,String nombreArchivo) throws GenericBusinessException;
	public int guardarArchivo(FileInputStreamSerializable archivoFuente,String rutaDestino,String nombreArchivo) throws GenericBusinessException;
	public FileInputStreamSerializable obtenerArchivo(String rutaDestino,String nombreArchivo) throws GenericBusinessException;
	public int guardarArchivo(File archivoFuente,String rutaDestino,String nombreArchivo) throws GenericBusinessException;
	public int guardarArchivoZip(FileInputStreamSerializable archivoFuente,String rutaDestino,String nombreArchivo) throws GenericBusinessException;
}
