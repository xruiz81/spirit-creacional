package com.spirit.sri.session;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.sri.session.generated._SriClienteRetencionSession;

/**
 * The <code>SriClienteRetencionSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:17 $
 *
 */
@Stateless
public class SriClienteRetencionSessionEJB extends _SriClienteRetencionSession implements SriClienteRetencionSessionRemote, SriClienteRetencionSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(SriClienteRetencionSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public Collection findSriClienteRetencionByQueryAndFecha(Map aMap, java.sql.Date fecha) throws com.spirit.exception.GenericBusinessException {
		String objectName = "scr";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct scr from SriClienteRetencionEJB " + objectName + " where " + where + " and scr.fechaInicio <= :fecha and (scr.fechaFin is null or scr.fechaFin >= :fecha)";
		Query query = manager.createQuery(queryString);
		query.setParameter("fecha",fecha);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		
		return query.getResultList();
	}
	
	public Collection findPorcentajesRetencionByQuery(Map aMap) throws com.spirit.exception.GenericBusinessException {
		String objectName = "scr";
		String queryString = "select distinct scr from SriClienteRetencionEJB " + objectName + " where scr.porcentajeRetencion > 0.0";
		String where = (aMap.size() > 0)?QueryBuilder.buildWhere(aMap, objectName):"";
		if (!where.equals(""))
			queryString += " and " + where;
		queryString += " order by scr.porcentajeRetencion";
		Query query = manager.createQuery(queryString);
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		return query.getResultList();
	}
}
