package com.spirit.general.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.generated._CiudadSession;
import com.spirit.server.QueryBuilder;

/**
 * The <code>CiudadSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 * 
 */
@Stateless
public class CiudadSessionEJB extends _CiudadSession implements CiudadSessionRemote,CiudadSessionLocal {

	public Collection findCiudadByQueryAndProvinciaId(Map aMap, Long idProvincia)
			throws GenericBusinessException {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from CiudadEJB " + objectName + " where " + where
				+ " and provinciaId = " + idProvincia;
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			String property = (String) aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		return query.getResultList();

	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCiudadByQuery(int startIndex, int endIndex,Map aMap) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from CiudadEJB " + objectName + " where " + where;
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = (Object) aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();

	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getCiudadListSize(Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct count(*) from CiudadEJB " + objectName + " where " + where;
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = (Object) aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();
	}

}
