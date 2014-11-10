package com.spirit.general.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.general.session.generated._TipoProveedorSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TipoProveedorSessionService extends _TipoProveedorSessionService{

	Collection findTipoProveedorByEmpresaIdAndClase(java.lang.Long idEmpresa, String clase) throws com.spirit.exception.GenericBusinessException;
	Collection findTipoProveedorByQueryAndEmpresaId(Map aMap,Long idEmpresa) throws com.spirit.exception.GenericBusinessException;

}
