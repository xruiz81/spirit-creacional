package com.spirit.medios.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.medios.session.generated._PlanMedioDetalleSession;

/**
 * The <code>PlanMedioDetalleSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class PlanMedioDetalleSessionEJB extends _PlanMedioDetalleSession implements PlanMedioDetalleSessionRemote, PlanMedioDetalleSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;
   

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

 	public java.util.Collection findPlanMedioDetalleByPlanMedioMesIdAndByProveedorId(java.lang.Long idPlanMedioMes, java.lang.Long idProveedor) {
	     String queryString = "from PlanMedioDetalleEJB e where e.planmediomesId = " + idPlanMedioMes + " and e.proveedorId = " + idProveedor;
	     // Add a an order by on all primary keys to assure reproducable results.
	     String orderByPart = "";
	     orderByPart += " order by e.id";
	     queryString += orderByPart;
	     Query query = manager.createQuery(queryString);
	     return query.getResultList();
	   }

}
