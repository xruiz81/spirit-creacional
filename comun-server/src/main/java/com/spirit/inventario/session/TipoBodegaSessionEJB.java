package com.spirit.inventario.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.general.entity.EmpresaEJB;
import com.spirit.inventario.entity.TipoBodegaEJB;
import com.spirit.inventario.session.generated._TipoBodegaSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>TipoBodegaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:18 $
 *
 */
@Stateless
public class TipoBodegaSessionEJB extends _TipoBodegaSession implements TipoBodegaSessionRemote  {

	@PersistenceContext(unitName="spirit")
    private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(TipoBodegaSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection getTipoBodegaList(int startIndex,int endIndex,Map aMap) {
	   if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
	String objectName = "e";
	String where = QueryBuilder.buildWhere(aMap, objectName);
	String queryString = "from TipoBodegaEJB " + objectName + " where "
				+ where;
	Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
     
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTipoBodegaListSize(Map aMap) {
	String objectName = "e";
	String where = QueryBuilder.buildWhere(aMap, objectName);
	String queryString = "select count(*) from TipoBodegaEJB " + objectName + " where "
				+ where;
	Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
   }
   
}
