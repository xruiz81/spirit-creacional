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
import com.spirit.sri.session.generated._SriProveedorRetencionSession;

/**
 * The <code>SriProveedorRetencionSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:17 $
 *
 */
@Stateless
public class SriProveedorRetencionSessionEJB extends _SriProveedorRetencionSession implements SriProveedorRetencionSessionRemote, SriProveedorRetencionSessionLocal  {
	
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(SriProveedorRetencionSessionEJB.class);
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	public Collection findSriProveedorRetencionByQueryAndFecha(Map aMap, java.sql.Date fecha) throws com.spirit.exception.GenericBusinessException {
		String objectName = "spr";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct spr from SriProveedorRetencionEJB " + objectName + " where " + where + " and spr.fechaInicio <= :fecha and (spr.fechaFin is null or spr.fechaFin >= :fecha)";
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
	
	public Collection findSriProveedorRetencionByQueryAndEmpresaId(Long empresaId) throws com.spirit.exception.GenericBusinessException {
		String objectName = "spr";
		String queryString = "select distinct spr from SriProveedorRetencionEJB " + objectName + " where "	+ 
		" spr.idCuentaRetefuente in (select c.id from CuentaEJB c,PlanCuentaEJB pc where c.plancuentaId=pc.id and pc.empresaId='"+empresaId+"')"+
		"  or spr.idCuentaReteiva in (select c.id from CuentaEJB c,PlanCuentaEJB pc where c.plancuentaId=pc.id and pc.empresaId='"+empresaId+"')";
		
		Query query = manager.createQuery(queryString);
		
		return query.getResultList();
	}
	
	public Collection findPorcentajesRetencionByQuery(Map aMap) throws com.spirit.exception.GenericBusinessException {
		String objectName = "spr";
		String queryString = "select distinct spr.retefuente from SriProveedorRetencionEJB " + objectName + " where spr.retefuente > 0.0";
		String where = (aMap.size() > 0)?QueryBuilder.buildWhere(aMap, objectName):"";
		if (!where.equals(""))
			queryString += " and " + where;
		queryString += " order by spr.retefuente";
		Query retefuenteQuery = manager.createQuery(queryString);
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			retefuenteQuery.setParameter(propertyKey, property);
		}
		Collection retefuentes = retefuenteQuery.getResultList();
		queryString = "select distinct spr.reteiva from SriProveedorRetencionEJB " + objectName + " where spr.reteiva > 0.0";
		where = (aMap.size() > 0)?QueryBuilder.buildWhere(aMap, objectName):"";
		if (!where.equals(""))
			queryString += " and " + where;
		queryString += " order by spr.reteiva";
		Query reteivaQuery = manager.createQuery(queryString);
		keys = aMap.keySet();
		it = keys.iterator();
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			reteivaQuery.setParameter(propertyKey, property);
		}
		Collection reteivas = reteivaQuery.getResultList();
		retefuentes.addAll(reteivas);
		
		return retefuentes;
	}
}
