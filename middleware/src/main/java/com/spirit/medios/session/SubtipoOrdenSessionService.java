package com.spirit.medios.session;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.session.generated._SubtipoOrdenSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SubtipoOrdenSessionService extends _SubtipoOrdenSessionService{

	java.util.Collection findSubtipoOrdenByEmpresaId(Long idEmpresa) throws GenericBusinessException;
}
