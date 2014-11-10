package com.spirit.cartera.session;

import com.spirit.cartera.session.generated._FormaPagoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface FormaPagoSessionService extends _FormaPagoSessionService{

	java.util.Collection findFormaPagoByClienteId(java.lang.Long idCliente) throws com.spirit.exception.GenericBusinessException;

}
