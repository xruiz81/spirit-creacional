package com.spirit.general.session;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.spirit.general.entity.OficinaIf;
import com.spirit.general.session.generated._OficinaSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>OficinaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:16 $
 *
 */
@Stateless
public class OficinaSessionEJB extends _OficinaSession implements OficinaSessionRemote, OficinaSessionLocal {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(OficinaSessionEJB.class);

	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findOficinaByQuery(int startIndex,int endIndex,Map aMap) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from OficinaEJB " + objectName + " where " + where;
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
	public int getOficinaListSize(Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select count(*) from OficinaEJB " + objectName + " where " + where;
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
	
	public Map mapearOficinas(Long idEmpresa) {
		Map oficinasMap = new HashMap();	
		Iterator oficinasIterator = findOficinaByEmpresaId(idEmpresa).iterator();
		while (oficinasIterator.hasNext()) {
			OficinaIf oficina = (OficinaIf) oficinasIterator.next();
			oficinasMap.put(oficina.getId(), oficina);
		}
		return oficinasMap;
	}
}
