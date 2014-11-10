package com.spirit.compras.session;

import com.spirit.compras.session.generated._CompraRetencionSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CompraRetencionSessionService extends _CompraRetencionSessionService{

	java.util.Collection findCompraRetencionByEmpresaId(Long idEmpresa) throws GenericBusinessException;
}
