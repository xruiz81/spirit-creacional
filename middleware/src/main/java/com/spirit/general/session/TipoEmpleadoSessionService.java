package com.spirit.general.session;

import com.spirit.general.session.generated._TipoEmpleadoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TipoEmpleadoSessionService extends _TipoEmpleadoSessionService{
	java.util.Collection findTipoEmpleadoByCodigoAndByEmpresaId(java.lang.String codigo, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	
}
