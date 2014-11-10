package com.spirit.contabilidad.session;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.contabilidad.session.generated._PlantillaSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 * The <code>PlantillaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class PlantillaSessionEJB extends _PlantillaSession implements PlantillaSessionRemote, PlantillaSessionLocal {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(PlantillaSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
   	
   	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getPlantillaByPlanCuentaIdList(int startIndex, int endIndex, java.lang.Long idPlanCuenta) {
   		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		//select distinct p.* from plantilla p, cuenta c where p.CUENTA_ID = c.ID and c.PLANCUENTA_ID = 1
   		String queryString = "select distinct p from PlantillaEJB p, CuentaEJB c where p.cuentaId = c.id and c.plancuentaId = " + idPlanCuenta + "";
		Query query = manager.createQuery(queryString);
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}
   	
   	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findPlantillaByEventoContableIdAndByPlanCuentaId(java.lang.Long idEventoContable, java.lang.Long idPlanCuenta) {
		//select distinct p.* from plantilla p, cuenta c where p.CUENTA_ID = c.ID and p.EVENTOCONTABLE_ID = 1 and c.PLANCUENTA_ID = 1
   		String queryString = "select distinct p from PlantillaEJB p, CuentaEJB c where p.cuentaId = c.id and p.eventocontableId = " + idEventoContable + " and c.plancuentaId = " + idPlanCuenta + "";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
   	
   	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getPlantillaByEventoContableIdAndByPlanCuentaIdList(int startIndex, int endIndex, java.lang.Long idEventoContable, java.lang.Long idPlanCuenta) {
   		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
   		//select distinct p.* from plantilla p, cuenta c where p.CUENTA_ID = c.ID and p.EVENTOCONTABLE_ID = 1 and c.PLANCUENTA_ID = 1
   		String queryString = "select distinct p from PlantillaEJB p, CuentaEJB c where p.cuentaId = c.id and p.eventocontableId = " + idEventoContable + " and c.plancuentaId = " + idPlanCuenta + "";
		Query query = manager.createQuery(queryString);
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}
   	   	
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection getPlantillaByEventocontableIdList(int startIndex, int endIndex, java.lang.Long eventocontableId) {
    	if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
      String queryString = "from PlantillaEJB e where e.eventocontableId = :eventocontableId ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("eventocontableId", eventocontableId);
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
      return query.getResultList();
    }
   	
   	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findPlantillaByEventoContableIdAndPlanCuentaId(java.lang.Long eventoContableId, java.lang.Long planCuentaId) {
   		String queryString = "select distinct p from PlantillaEJB p, CuentaEJB c where p.eventocontableId = " + eventoContableId + " and p.cuentaId = c.id and c.plancuentaId = " + planCuentaId;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

   	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findNemonicosDePlantillas() {
		//select distinct p.* from plantilla p, cuenta c where p.CUENTA_ID = c.ID and p.EVENTOCONTABLE_ID = 1 and c.PLANCUENTA_ID = 1
   		String queryString = "select distinct p.nemonico from PlantillaEJB p order by p.nemonico asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
   	
}
