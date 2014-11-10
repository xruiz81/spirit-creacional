package com.spirit.rrhh.session;

import java.util.Collection;
import java.util.Vector;

import com.spirit.exception.GenericBusinessException;

import com.spirit.rrhh.entity.EmpleadoOrganizacionIf;
import com.spirit.rrhh.session.generated._EmpleadoOrganizacionSessionService;
import com.spirit.util.FileInputStreamSerializable;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EmpleadoOrganizacionSessionService extends _EmpleadoOrganizacionSessionService{
	public void procesarEmpleadoOrganizacion(Vector<EmpleadoOrganizacionIf> empleadoOrganizacionVector, Vector<EmpleadoOrganizacionIf> empleadoOrganizacionEliminadasVector, Collection<FileInputStreamSerializable> archivosColeccion, String rutaDestino, String rutaSistema) throws GenericBusinessException;
}
