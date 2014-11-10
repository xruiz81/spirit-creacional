package com.spirit.medios.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.session.generated._OrdenTrabajoProductoSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 * The <code>OrdenTrabajoProductoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class OrdenTrabajoProductoSessionEJB extends _OrdenTrabajoProductoSession implements OrdenTrabajoProductoSessionRemote, OrdenTrabajoProductoSessionLocal {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(OrdenTrabajoProductoSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   public java.util.Collection findProductosClienteOrdenByOrdenTrabajoId(java.lang.Long idOrdenTrabajo) throws GenericBusinessException {
	   String objectName = "m";
	   String queryString = "select distinct m from ProductoClienteEJB " + objectName + ", OrdenTrabajoProductoEJB cp where  cp.productoClienteId = m.id and cp.ordenTrabajoId = " + idOrdenTrabajo + " order by m.id desc";
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }

}
