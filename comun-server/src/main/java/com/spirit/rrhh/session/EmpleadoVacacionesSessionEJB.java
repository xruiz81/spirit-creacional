package com.spirit.rrhh.session;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.FileManagerSessionLocal;
import com.spirit.rrhh.entity.EmpleadoVacacionesEJB;
import com.spirit.rrhh.entity.EmpleadoVacacionesIf;
import com.spirit.rrhh.session.generated._EmpleadoVacacionesSession;
import com.spirit.server.QueryBuilder;
import com.spirit.util.FileInputStreamSerializable;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class EmpleadoVacacionesSessionEJB extends _EmpleadoVacacionesSession implements EmpleadoVacacionesSessionRemote,EmpleadoVacacionesSessionLocal  {

	@EJB private FileManagerSessionLocal fileManagerLocal;
	
   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public void procesarEmpleadoVacaciones(Vector<EmpleadoVacacionesIf> empleadoVacacionesVector, 
			Vector<EmpleadoVacacionesIf> empleadoVacacionesEliminadasVector, 
			Collection<FileInputStreamSerializable> archivosColeccion, 
			String rutaDestino, String rutaSistema) throws GenericBusinessException{
		
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
		for(EmpleadoVacacionesIf empleadoVacacionesIf : empleadoVacacionesVector){
			EmpleadoVacacionesIf empleadoVacaciones = registarEmpleadoVacaciones(empleadoVacacionesIf, rutaSistema);
			manager.merge(empleadoVacaciones);
		}
		
		//elimino si fuera el caso el ultimo registro
		for(EmpleadoVacacionesIf empleadoVacacionesEliminadoIf : empleadoVacacionesEliminadasVector){
			EmpleadoVacacionesIf empleadoVacacionesEliminado = getEmpleadoVacaciones(empleadoVacacionesEliminadoIf.getId());
			manager.remove(empleadoVacacionesEliminado);
		}
		manager.flush();
	}
	
	public EmpleadoVacacionesEJB registarEmpleadoVacaciones(EmpleadoVacacionesIf empleadoVacacionesIf, String urlCarpetaServidor){
		EmpleadoVacacionesEJB empleadoVacaciones = new EmpleadoVacacionesEJB();
		
		empleadoVacaciones.setId(empleadoVacacionesIf.getId());
		empleadoVacaciones.setArchivoAdjunto(empleadoVacacionesIf.getArchivoAdjunto());
		empleadoVacaciones.setEmpleadoId(empleadoVacacionesIf.getEmpleadoId());
		empleadoVacaciones.setFechaFin(empleadoVacacionesIf.getFechaFin());
		empleadoVacaciones.setFechaInicio(empleadoVacacionesIf.getFechaInicio());
		empleadoVacaciones.setObservacion(empleadoVacacionesIf.getObservacion());
		empleadoVacaciones.setSaldoDias(empleadoVacacionesIf.getSaldoDias());
		
		int posicionUltimoSlash = -1;
		if(empleadoVacacionesIf.getArchivoAdjunto() != null) 
			posicionUltimoSlash = empleadoVacacionesIf.getArchivoAdjunto().lastIndexOf("\\");
		
		String nombreArchivo = ""; 
		if(posicionUltimoSlash != -1)
			nombreArchivo= empleadoVacacionesIf.getArchivoAdjunto().substring(posicionUltimoSlash + 1);
		
		if (!nombreArchivo.equals("") ){
			int puntoExt = nombreArchivo.lastIndexOf(".");
	    	String strFilename = nombreArchivo.substring(0, puntoExt) + ".zip";	    	
	    	empleadoVacaciones.setArchivoAdjunto(urlCarpetaServidor + strFilename.replaceAll(" ", "_"));
		} else 
			empleadoVacaciones.setArchivoAdjunto(null);
								
		return empleadoVacaciones;		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findEmpleadoVacacionesByOficinaIdByDepartamentoIdAndByFechas(Long idOficina, Long idDepartamento, Date fechaInicio, Date fechaFin)  {
		
		String queryString = "select distinct ev from EmpleadoVacacionesEJB ev, EmpleadoEJB e where ev.empleadoId = e.id ";

		if(idOficina != 0L){
			queryString = queryString + " and e.oficinaId = " + idOficina + "";
		}
		
		if(idDepartamento != 0L){
			queryString = queryString + " and e.departamentoId = " + idDepartamento + "";
		}
		
		if(fechaInicio != null && fechaFin != null){
			//este query solo tiene de referencia la fecha inicio
			//queryString = queryString + " and ev.fechaInicio >= '" + fechaInicio + "' and ev.fechaInicio <= '" + fechaFin + "'";
			//este nuevo query comprende cualquier cruce entre el rango de vacaciones que tomo el empleado y el rango de vacaciones que se busca
			queryString = queryString + " and '" + fechaInicio + "' <= ev.fechaFin and '" + fechaFin + "' >= ev.fechaInicio";
		}

		Query query = manager.createQuery(queryString);

		return query.getResultList();
	}
}
