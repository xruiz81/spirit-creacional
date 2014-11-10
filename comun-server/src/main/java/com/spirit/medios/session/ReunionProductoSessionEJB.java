package com.spirit.medios.session;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.entity.ReunionProductoEJB;
import com.spirit.medios.entity.ReunionProductoIf;
import com.spirit.medios.session.generated._ReunionProductoSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 * The <code>ReunionProductoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class ReunionProductoSessionEJB extends _ReunionProductoSession implements ReunionProductoSessionRemote, ReunionProductoSessionLocal {

	@PersistenceContext(unitName="spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(ReunionProductoSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ReunionProductoEJB registrarReunionProductoCliente(ReunionProductoIf modelReunionProducto, ProductoClienteIf modelProductoCliente) {
		ReunionProductoEJB reunionProducto = new ReunionProductoEJB();
		
		reunionProducto.setId(modelReunionProducto.getId());
		reunionProducto.setReunionId(modelReunionProducto.getReunionId());
		reunionProducto.setProductoClienteId(modelProductoCliente.getId());
		reunionProducto.setProductoCliente(modelReunionProducto.getProductoCliente());
		
		return reunionProducto;
	}
	
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ReunionProductoEJB registrarReunionProductoProspectoCliente(ReunionProductoIf modelReunionProducto, String modelProductoProspectoCliente) {
		ReunionProductoEJB reunionProducto = new ReunionProductoEJB();
		
		reunionProducto.setId(modelReunionProducto.getId());
		reunionProducto.setReunionId(modelReunionProducto.getReunionId());
		reunionProducto.setProductoClienteId(modelReunionProducto.getId());
		reunionProducto.setProductoCliente(modelProductoProspectoCliente);
		
		return reunionProducto;
	}

}
