package com.spirit.general.session;

import com.spirit.general.session.generated._CruceDocumentoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CruceDocumentoSessionService extends _CruceDocumentoSessionService{
	java.util.Collection findCruceDocumentoByEmpresaId(java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	
}
