package com.spirit.contabilidad.session;

import com.spirit.contabilidad.session.generated._SubtipoAsientoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SubTipoAsientoSessionService extends _SubtipoAsientoSessionService{

	java.util.Collection findSubtipoAsientoByEmpresaId(Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
}
