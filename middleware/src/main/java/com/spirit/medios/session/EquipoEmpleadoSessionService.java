package com.spirit.medios.session;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.session.generated._EquipoEmpleadoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EquipoEmpleadoSessionService extends _EquipoEmpleadoSessionService{

	java.util.Collection findEquipoEmpleadoByEmpresaId(Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findEmpleadoByEquipoTrabajoId(Long idEquipoTrabajo) throws com.spirit.exception.GenericBusinessException;
	public void eliminarEquipoEmpleado(Long equipoEmpleadoId, String usuario) throws GenericBusinessException;
	java.util.Collection findEquipoEmpleadoByCodigoTipoOrdenAndByEmpresaId(String codigoTipoOrden, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
}
