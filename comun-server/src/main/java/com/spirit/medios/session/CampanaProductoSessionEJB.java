package com.spirit.medios.session;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.CampanaProductoEJB;
import com.spirit.medios.entity.CampanaProductoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.session.generated._CampanaProductoSession;

/**
 * The <code>CampanaProductoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class CampanaProductoSessionEJB extends _CampanaProductoSession implements CampanaProductoSessionRemote, CampanaProductoSessionLocal {

	   @PersistenceContext(unitName="spirit")
   private EntityManager manager;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
   	//select pc.* from campana_producto cp,producto_Cliente pc where cp.PRODUCTO_CLIENTE_ID = pc.ID and cp.CAMPANA_ID = '1245184'
   /**
    * devuelve los productos clientes por campaña
    * **/	
   public java.util.Collection findProductosCampanaByCampanaId(java.lang.Long idCampana) throws GenericBusinessException {
		 
		 String objectName = "m";
	 		String queryString = "select distinct m from ProductoClienteEJB " + objectName + ", CampanaProductoEJB cp where  cp.productoClienteId = m.id and cp.campanaId = " + idCampana + " order by m.id desc";
	 		Query query = manager.createQuery(queryString);
	 		return query.getResultList();
	  }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CampanaProductoEJB registrarCampanaProducto(CampanaProductoIf modelCampanaProducto, ProductoClienteIf modelProducto) {
		CampanaProductoEJB campanaProducto = new CampanaProductoEJB();
		
		//campanaProducto.setId(modelProducto.getId());
		campanaProducto.setProductoClienteId(modelProducto.getId());
		campanaProducto.setCampanaId(modelCampanaProducto.getCampanaId());
		
		return campanaProducto;
	}
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findCampanaProductosByCampanaIdAndProductoClienteId(java.lang.Long idCampana,java.lang.Long idProductoCliente) throws GenericBusinessException {
		 
	   String objectName = "m";
	   String queryString = "select distinct m from CampanaProductoEJB " + objectName + " where  m.productoClienteId = " + idProductoCliente +
	   					    " and m.campanaId = " + idCampana + " order by m.id desc";
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCampanaProductoByEmpresaId(Long idEmpresa)throws GenericBusinessException {
					
		 String cadenaQuery = "select distinct cp from CampanaProductoEJB cp, CampanaEJB ca, "  +
		 				      "ClienteEJB c,TipoClienteEJB tc where " +
		 					  "cp.campanaId = ca.id and ca.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + "";
				 							    		
		Query query = manager.createQuery(cadenaQuery);
		return query.getResultList();
	}
}
