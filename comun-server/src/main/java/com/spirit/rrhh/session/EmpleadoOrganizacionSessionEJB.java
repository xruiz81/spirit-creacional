package com.spirit.rrhh.session;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.ejb.*;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.FileManagerSessionLocal;
import com.spirit.rrhh.session.generated._EmpleadoOrganizacionSession;
import com.spirit.rrhh.session.EmpleadoOrganizacionSessionLocal;
import com.spirit.rrhh.session.EmpleadoOrganizacionSessionRemote;
import com.spirit.rrhh.entity.*;
import com.spirit.util.FileInputStreamSerializable;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class EmpleadoOrganizacionSessionEJB extends _EmpleadoOrganizacionSession implements EmpleadoOrganizacionSessionRemote,EmpleadoOrganizacionSessionLocal  {
	@EJB private FileManagerSessionLocal fileManagerLocal;
   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	@Override
	public void procesarEmpleadoOrganizacion(Vector<EmpleadoOrganizacionIf> empleadoOrganizacionVector, Vector<EmpleadoOrganizacionIf> empleadoOrganizacionEliminadasVector, Collection<FileInputStreamSerializable> archivosColeccion, String rutaDestino, String rutaSistema) throws GenericBusinessException{
		
		//archivos
		Iterator archivosColeccionIt = archivosColeccion.iterator();
		while(archivosColeccionIt.hasNext()){
			FileInputStreamSerializable archivo = (FileInputStreamSerializable)archivosColeccionIt.next();
			try{
				if(archivo != null)
					fileManagerLocal.guardarArchivoZip(archivo, rutaDestino, archivo.getNombreArchivo());
			
			} catch(Exception exa){
				System.out.println("No se guardo archivo con nombre: " + archivo.getNombreArchivo());
			}
		}
		
		//guardo registros
		for(EmpleadoOrganizacionIf empleadoOrganizacionIf : empleadoOrganizacionVector){
			EmpleadoOrganizacionIf empleadoOrganizacion = registarEmpleadoOrganizacion(empleadoOrganizacionIf, rutaSistema);
			manager.merge(empleadoOrganizacion);
		}
		
		//elimino si fuera el caso el ultimo registro
		for(EmpleadoOrganizacionIf empleadoOrganizacionEliminadoIf : empleadoOrganizacionEliminadasVector){
			EmpleadoOrganizacionIf empleadoOrganizacionEliminado = getEmpleadoOrganizacion(empleadoOrganizacionEliminadoIf.getId());
			manager.remove(empleadoOrganizacionEliminado);
		}
		manager.flush();
	}

	public EmpleadoOrganizacionEJB registarEmpleadoOrganizacion(EmpleadoOrganizacionIf empleadoOrganizacionIf, String urlCarpetaServidor){
		EmpleadoOrganizacionEJB empleadoOrganizacion = new EmpleadoOrganizacionEJB();
		
		empleadoOrganizacion.setId(empleadoOrganizacionIf.getId());
		empleadoOrganizacion.setArchivoAdjunto(empleadoOrganizacionIf.getArchivoAdjunto());
		empleadoOrganizacion.setEmpleadoId(empleadoOrganizacionIf.getEmpleadoId());
		empleadoOrganizacion.setFechaFin(empleadoOrganizacionIf.getFechaFin());
		empleadoOrganizacion.setFechaInicio(empleadoOrganizacionIf.getFechaInicio());
		empleadoOrganizacion.setDescripcion(empleadoOrganizacionIf.getDescripcion());
		empleadoOrganizacion.setDepartamento(empleadoOrganizacionIf.getDepartamento());
		empleadoOrganizacion.setTipoEmpleadoId(empleadoOrganizacionIf.getTipoEmpleadoId());
		
		int posicionUltimoSlash = -1;
		if(empleadoOrganizacionIf.getArchivoAdjunto() != null) 
			posicionUltimoSlash = empleadoOrganizacionIf.getArchivoAdjunto().lastIndexOf("\\");
		
		String nombreArchivo = ""; 
		if(posicionUltimoSlash != -1)
			nombreArchivo= empleadoOrganizacionIf.getArchivoAdjunto().substring(posicionUltimoSlash + 1);
		
		if (!nombreArchivo.equals("") ){
			int puntoExt = nombreArchivo.lastIndexOf(".");
	    	String strFilename = nombreArchivo.substring(0, puntoExt) + ".zip";	    	
	    	empleadoOrganizacion.setArchivoAdjunto(urlCarpetaServidor + strFilename.replaceAll(" ", "_"));
		} else 
			empleadoOrganizacion.setArchivoAdjunto(null);
								
		return empleadoOrganizacion;		
	}

	
}


