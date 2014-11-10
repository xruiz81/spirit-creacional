package com.spirit.nomina.session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.session.generated._GastoDeducibleSession;
import com.spirit.server.QueryBuilder;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class GastoDeducibleSessionEJB extends _GastoDeducibleSession implements GastoDeducibleSessionRemote,GastoDeducibleSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	
	public int getGastoDeducibleListSize(Map aMap) throws GenericBusinessException {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select count(*) from RubroEJB " + objectName
		+ " where " + where;
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
		return countResult.intValue();

	}
	
	public java.util.Collection<RubroIf> findGastoDeducibleByQuery(int startIndex,
			int endIndex, Map aMap) throws GenericBusinessException{
		if ((endIndex - startIndex) < 0) {
			return new ArrayList<RubroIf>();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from GastoDeducibleEJB " + objectName + " where " + where;
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

}
