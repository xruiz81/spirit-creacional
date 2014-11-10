package com.spirit.sri.session;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.sri.session.generated._SriIvaRetencionSession;

/**
 * The <code>SriIvaRetencionSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:17 $
 *
 */
@Stateless
public class SriIvaRetencionSessionEJB extends _SriIvaRetencionSession implements SriIvaRetencionSessionRemote, SriIvaRetencionSessionLocal  {
	
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(SriIvaRetencionSessionEJB.class);
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	public Collection findSriIvaRetencionByFecha(java.sql.Date fecha) throws com.spirit.exception.GenericBusinessException {
		String queryString = "select distinct sir from SriIvaRetencionEJB sir where sir.fechaInicio <= :fecha and (sir.fechaFin is null or sir.fechaFin >= :fecha)";
		queryString += " order by sir.codigo asc";
		Query query = manager.createQuery(queryString);
		query.setParameter("fecha",fecha);
		return query.getResultList();
	}
	
	public Collection findSriIvaRetencionByQueryAndFecha(Map aMap, java.sql.Date fecha) throws com.spirit.exception.GenericBusinessException {
		String objectName = "sir";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct sir from SriIvaRetencionEJB " + objectName + " where " + where + " and sir.fechaInicio <= :fecha and (sir.fechaFin is null or sir.fechaFin >= :fecha)";
		queryString += " order by sir.codigo asc";
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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection getPorcentajes() {
		//select distinct porcentaje from sri_iva_retencion
		String queryString = "select distinct porcentaje from SriIvaRetencionEJB";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
}
