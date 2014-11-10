package com.spirit.sri.session;

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

import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.sri.entity.SriAirEJB;
import com.spirit.sri.session.generated._SriAirSession;

/**
 * The <code>SriAirSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:17 $
 *
 */
@Stateless
public class SriAirSessionEJB extends _SriAirSession implements SriAirSessionRemote,SriAirSessionLocal  {
	
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(SriAirSessionEJB.class);
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	public Collection findSriAirByFecha(java.sql.Date fecha) throws com.spirit.exception.GenericBusinessException {
		String queryString = "select distinct sa from SriAirEJB sa where sa.fechaInicio <= :fecha and (sa.fechaFin is null or sa.fechaFin >= :fecha)";
		queryString += " order by sa.codigo asc";
		Query query = manager.createQuery(queryString);
		query.setParameter("fecha", fecha);
		return query.getResultList();
	}
	
	public Collection findSriAirByQueryAndFecha(Map aMap, java.sql.Date fecha) throws com.spirit.exception.GenericBusinessException {
		String objectName = "sa";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct sa from SriAirEJB " + objectName + " where " + where + " and sa.fechaInicio <= :fecha and (sa.fechaFin is null or sa.fechaFin >= :fecha)";
		queryString += " order by sa.codigo asc";
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
		//select distinct porcentaje from sri_air
		String queryString = "select distinct porcentaje from SriAirEJB";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	public java.util.Collection findSriAirCodigoFechaActual() {
		//select distinct porcentaje from sri_air
		String queryString = "select distinct e from SriAirEJB e where (e.fechaFin is null or e.fechaFin='')";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
}
