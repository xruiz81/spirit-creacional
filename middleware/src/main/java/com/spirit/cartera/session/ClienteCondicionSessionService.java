package com.spirit.cartera.session;

import com.spirit.cartera.entity.ClienteCondicionEJB;
import com.spirit.cartera.entity.ClienteCondicionIf;
import com.spirit.cartera.session.generated._ClienteCondicionSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ClienteCondicionSessionService extends _ClienteCondicionSessionService{

	public ClienteCondicionEJB registrarClienteCondicion(ClienteCondicionIf modelClienteCondicion) throws com.spirit.exception.GenericBusinessException;
}
