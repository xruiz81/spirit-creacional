package com.spirit.cartera.session;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.cartera.session.generated._FormaPagoSession;
import com.spirit.general.session.UtilitariosSessionLocal;

/**
 * The <code>FormaPagoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:20 $
 *
 */
@Stateless
public class FormaPagoSessionEJB extends _FormaPagoSession implements FormaPagoSessionRemote,FormaPagoSessionLocal  {
	
	@EJB
	private UtilitariosSessionLocal utilitariosLocal;

	@PersistenceContext(unitName="spirit")
	private EntityManager manager;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findFormaPagoByClienteId(java.lang.Long idCliente) {
	   //select distinct f.* from forma_pago f, cliente_condicion cc where cc.FORMAPAGO_ID = f.ID and cc.CLIENTE_ID = 1572864
	   String queryString = "select distinct f from FormaPagoEJB f, ClienteCondicionEJB cc where cc.formapagoId = f.id and cc.clienteId = " + idCliente;
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }   
   
}
