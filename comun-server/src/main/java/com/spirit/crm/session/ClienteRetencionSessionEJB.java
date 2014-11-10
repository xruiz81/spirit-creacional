package com.spirit.crm.session;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.spirit.crm.entity.ClienteRetencionEJB;
import com.spirit.crm.entity.ClienteRetencionIf;
import com.spirit.crm.session.generated._ClienteRetencionSession;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class ClienteRetencionSessionEJB extends _ClienteRetencionSession implements ClienteRetencionSessionRemote,ClienteRetencionSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ClienteRetencionEJB registrarClienteRetencion(ClienteRetencionIf modelClienteRetencion) {
		ClienteRetencionEJB clienteRetencion = new ClienteRetencionEJB();
		clienteRetencion.setId(modelClienteRetencion.getId());
		clienteRetencion.setClienteId(modelClienteRetencion.getClienteId());
		clienteRetencion.setSriAirId(modelClienteRetencion.getSriAirId());
		clienteRetencion.setSriIvaRetencionId(modelClienteRetencion.getSriIvaRetencionId());
		
		return clienteRetencion;
	}
}
