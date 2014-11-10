package com.spirit.crm.session;

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

import com.spirit.crm.entity.CorporacionEJB;
import com.spirit.crm.session.generated._CorporacionSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>CorporacionSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:20 $
 *
 */
@Stateless
public class CorporacionSessionEJB extends _CorporacionSession implements CorporacionSessionRemote,CorporacionSessionLocal  {

   @PersistenceContext(unitName="spirit")
   EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(CorporacionSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCorporacionList(int startIndex, int endIndex,Long idEmpresa) {
      if (startIndex < 1) {
         startIndex = 1;
      }
      if ( (endIndex - startIndex) < 0) {
         // Just return an empty list.
         return new ArrayList();
      }
      String queryString = "from CorporacionEJB e"  + " where e.empresaId = " + idEmpresa;
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = " order by e.nombre asc";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setFirstResult(startIndex - 1);
      query.setMaxResults(endIndex - startIndex + 1);
      return query.getResultList();
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCorporacionList(int startIndex, int endIndex, Map aMap) throws GenericBusinessException {
		if (startIndex < 1) {
			startIndex = 1;
		}
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from CorporacionEJB " + objectName + " where " + where;
	    queryString += " order by e.nombre asc"; 
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		query.setFirstResult(startIndex - 1);
		query.setMaxResults(endIndex - startIndex + 1);
		return query.getResultList();
	}

   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCorporacionList(int startIndex, int endIndex, Map aMap, Long idEmpresa) throws GenericBusinessException {
		if (startIndex < 1) {
			startIndex = 1;
		}
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from CorporacionEJB " + objectName + " where " + where  + " and e.empresaId = " + idEmpresa;
		queryString += " order by e.nombre asc";
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		query.setFirstResult(startIndex - 1);
		query.setMaxResults(endIndex - startIndex + 1);
		return query.getResultList();
	}


   /**
    * Obtains the total number of corporacion objects in the database.
    *
    * @return an integer value.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCorporacionListSize(Long idEmpresa) {
      Query countQuery = manager.createQuery("select count(*) from CorporacionEJB e where e.empresaId = " + idEmpresa);
      List countQueryResult = countQuery.getResultList();
      Long countResult = (Long) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }
   
   /**
    * Obtains the total number of corporacion objects in the database.
    *
    * @return an integer value.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCorporacionListSize(Map aMap) {
		String objectName = "e";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "select count(*) from CorporacionEJB " + objectName + " where " + where;
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
   
   /**
    * Obtains the total number of corporacion objects in the database.
    *
    * @return an integer value.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCorporacionListSize(Map aMap,Long idEmpresa) {
		String objectName = "e";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "select count(*) from CorporacionEJB " + objectName + " where " + where + " and e.empresaId = " + idEmpresa;
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
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCorporacionList(int startIndex, int endIndex) {
      if (startIndex < 1) {
         startIndex = 1;
      }
      if ( (endIndex - startIndex) < 0) {
         // Just return an empty list.
         return new ArrayList();
      }
      String queryString = "from CorporacionEJB e";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = " order by e.nombre asc";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setFirstResult(startIndex - 1);
      query.setMaxResults(endIndex - startIndex + 1);
      return query.getResultList();
   }

}
