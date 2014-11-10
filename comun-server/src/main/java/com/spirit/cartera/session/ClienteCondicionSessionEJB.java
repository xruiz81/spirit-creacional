package com.spirit.cartera.session;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spirit.cartera.entity.ClienteCondicionEJB;
import com.spirit.cartera.entity.ClienteCondicionIf;
import com.spirit.cartera.session.generated._ClienteCondicionSession;

/**
 * The <code>ClienteCondicionSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:20 $
 *
 */
@Stateless
public class ClienteCondicionSessionEJB extends _ClienteCondicionSession implements ClienteCondicionSessionRemote, ClienteCondicionSessionLocal {

	@PersistenceContext(unitName="spirit")
	   private EntityManager manager;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public ClienteCondicionEJB registrarClienteCondicion(ClienteCondicionIf modelClienteCondicion) {
		ClienteCondicionEJB clienteCondicion = new ClienteCondicionEJB();
		
		clienteCondicion.setId(modelClienteCondicion.getId());
		clienteCondicion.setClienteId(modelClienteCondicion.getClienteId());
		clienteCondicion.setSubtipoordenId(modelClienteCondicion.getSubtipoordenId());
		clienteCondicion.setFormapagoId(modelClienteCondicion.getFormapagoId());
		clienteCondicion.setObservaciones(modelClienteCondicion.getObservaciones());
		clienteCondicion.setFechaini(modelClienteCondicion.getFechaini());
		clienteCondicion.setFechafin(modelClienteCondicion.getFechafin());
		
		return clienteCondicion;
	}

}
