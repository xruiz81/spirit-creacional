package com.spirit.general.session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.generated._DepartamentoSession;
import com.spirit.server.QueryBuilder;

/**
 * The <code>DepartamentoSession</code> session bean, which acts as a facade
 * to the underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 * 
 */
@Stateless
public class DepartamentoSessionEJB extends _DepartamentoSession implements DepartamentoSessionRemote,DepartamentoSessionLocal {

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findDepartamentoByQuery(Map aMap,int startIndex,int endIndex) throws GenericBusinessException {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		try{
			String objectName = "e";
			String where = QueryBuilder.buildWhere(aMap, objectName);
			String queryString = "from DepartamentoEJB " + objectName + " where " + where;
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
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error en la busqueda de Departamento");
		}
	}
	
	public int getDepartamentoListSizeByQuery(Map aMap) throws GenericBusinessException {
		try{
			String objectName = "e";
			String where = QueryBuilder.buildWhere(aMap, objectName);
			String queryString = "select count(*) from DepartamentoEJB " + objectName + " where " + where;
			Query query = manager.createQuery(queryString);
	
			Set keys = aMap.keySet();
			Iterator it = keys.iterator();
	
			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = (Object) aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
	
			}
			List countQueryResult = query.getResultList();
			Long countResult = (Long)countQueryResult.get(0);
			return countResult.intValue();
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error en la busqueda de Departamento");
		}
	}
	
}
