package com.spirit.compras.session;

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

import com.spirit.compras.entity.CompraRetencionEJB;
import com.spirit.compras.session.generated._CompraRetencionSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>CompraRetencionSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:13 $
 *
 */
@Stateless
public class CompraRetencionSessionEJB extends _CompraRetencionSession implements CompraRetencionSessionRemote, CompraRetencionSessionLocal  {
	
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	public Collection findCompraRetencionByEmpresaId(Long idEmpresa) throws GenericBusinessException {
		String queryString = "select distinct cr from CompraRetencionEJB cr, CompraEJB c, TipoDocumentoEJB td where cr.compraId = c.id and c.tipodocumentoId = td.id and td.empresaId = " + idEmpresa;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	/*public Collection findCompraRetencionByEmpresaId(Long idEmpresa) throws GenericBusinessException {
		String queryString = "select distinct com from CompraEJB com where com.id not in (select cr.compraId from CompraRetencionEJB cr, CompraEJB c, TipoDocumentoEJB td where cr.compraId = c.id and c.tipodocumentoId = td.id and td.empresaId = " + idEmpresa + ") and com.estado = 'A'";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}*/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getCompraRetencionListSize(Map aMap, java.lang.Long idEmpresa) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct count(*) from CompraRetencionEJB " + objectName + ", TipoDocumentoEJB td, CompraEJB c where " + where;
		queryString += " e.compraId = c.id and c.tipodocumentoId = td.id and td.empresaId = " + idEmpresa+ " order by e.secuencial desc";
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


	   

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection getCompraRetencionByQueryList2(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		
		String queryString = "select distinct e from CompraRetencionEJB " + objectName + ", TipoDocumentoEJB td, CompraEJB c where " + where;
		queryString += "e.compraId = c.id and c.tipodocumentoId = td.id and td.empresaId = " + idEmpresa+ " order by e.secuencial desc";
		
		/*String queryString = "select distinct e from PedidoEJB " + objectName + ", TipoDocumentoEJB td, EmpresaEJB em where " + where;
		queryString += " and e.tipodocumentoId = td.id and td.empresaId = em.id and em.id = " + idEmpresa + " order by e.codigo desc";*/
		
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
