package com.spirit.general.session;

import com.spirit.general.session.generated._TipoParametroSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TipoParametroSessionService extends _TipoParametroSessionService{
	java.util.Collection findTipoParametroByEmpresaIdAndbyCodigo(java.lang.Long empresaId,String codigo) throws com.spirit.exception.GenericBusinessException;

}
