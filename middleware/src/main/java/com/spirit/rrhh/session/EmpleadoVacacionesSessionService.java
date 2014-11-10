package com.spirit.rrhh.session;

import java.sql.Date;
import java.util.Collection;
import java.util.Vector;

import com.spirit.exception.GenericBusinessException;
import com.spirit.rrhh.entity.EmpleadoVacacionesIf;
import com.spirit.rrhh.session.generated._EmpleadoVacacionesSessionService;
import com.spirit.util.FileInputStreamSerializable;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EmpleadoVacacionesSessionService extends _EmpleadoVacacionesSessionService{
	
	public void procesarEmpleadoVacaciones(Vector<EmpleadoVacacionesIf> empleadoVacacionesVector, Vector<EmpleadoVacacionesIf> empleadoVacacionesEliminadasVector, Collection<FileInputStreamSerializable> archivosColeccion, String rutaDestino, String rutaSistema) throws GenericBusinessException;
	public java.util.Collection findEmpleadoVacacionesByOficinaIdByDepartamentoIdAndByFechas(Long idOficina, Long idDepartamento, Date fechaInicio, Date fechaFin) throws GenericBusinessException;
}
