package com.spirit.contabilidad.session;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.contabilidad.session.generated._EventoContableSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>EventoContableSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class EventoContableSessionEJB extends _EventoContableSession implements EventoContableSessionRemote, EventoContableSessionLocal  {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(EventoContableSessionEJB.class);

	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/

	/**
	 *
	 * Retrieves a list of data object for the specified query Map.
	 * MODIFICADO PARA QUE DEVUELVA EVENTOS ORDENADOS POR NOMBRE
	 *
	 * //@param Map $field.Name the field
	 * @return Collection of EventoContableIf data objects, empty list in case no results were found.
	 */
	
	/*@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findEventoContableByQuery(Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from EventoContableEJB " + objectName + " where " + where + " order by e.nombre asc";
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		return query.getResultList();

	}*/

}
