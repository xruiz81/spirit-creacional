package com.spirit.medios.session;

import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.session.generated._OrdenTrabajoDetalleSessionService;

/**
 * 
 * @author www.versality.com.ec
 * 
 */
public interface OrdenTrabajoDetalleSessionService extends
		_OrdenTrabajoDetalleSessionService {

	java.util.Collection findOrdenTrabajoDetalleByTipoOrdenAndOrdenTrabajoAndTipoProveedorAndEstado(
			java.lang.Long tipoOrdenId, java.lang.Long ordenTrabajoId,
			java.lang.Long tipoProveedorId)
			throws com.spirit.exception.GenericBusinessException;

	java.util.Collection findOrdenTrabajoDetalleByTipoOrdenAndOrdenTrabajo(
			java.lang.Long tipoOrdenId, java.lang.Long ordenTrabajoId)
			throws com.spirit.exception.GenericBusinessException;

	java.util.Collection findOrdenTrabajoDetalleByTipoOrdenAndOrdenTrabajoAndEstado(
			java.lang.Long tipoOrdenId, java.lang.Long ordenTrabajoId)
			throws com.spirit.exception.GenericBusinessException;

	java.util.Collection findOrdenTrabajoDetalleBySubTipoIdAndOrdenTrabajoId(
			java.lang.Long subTipoId, java.lang.Long ordenTrabajoId)
			throws com.spirit.exception.GenericBusinessException;

	java.util.Collection findOrdenTrabajoDetalleByEquipo(
			OrdenTrabajoIf ordenTrabajoIf,Long idEmpleado,String... estados)
			throws com.spirit.exception.GenericBusinessException;
	
	java.util.Collection findOrdenTrabajoDetalleByAsignado(
			OrdenTrabajoIf ordenTrabajoIf,Long idEmpleado,String... estados)
			throws com.spirit.exception.GenericBusinessException;
}
