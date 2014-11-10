package com.spirit.crm.session;

import com.spirit.crm.entity.ClienteRetencionEJB;
import com.spirit.crm.entity.ClienteRetencionIf;
import com.spirit.crm.session.generated._ClienteRetencionSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ClienteRetencionSessionService extends _ClienteRetencionSessionService{
	
	public ClienteRetencionEJB registrarClienteRetencion(ClienteRetencionIf modelClienteRetencion) throws com.spirit.exception.GenericBusinessException;
}
