package com.spirit.inventario.session;

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
import com.spirit.inventario.entity.ModeloEJB;
import com.spirit.inventario.session.generated._ModeloSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>ModeloSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:18 $
 *
 */
@Stateless
public class ModeloSessionEJB extends _ModeloSession implements ModeloSessionRemote,ModeloSessionLocal  {
	
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(ModeloSessionEJB.class);
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findModeloByEmpresaId(java.lang.Long empresaId) {
		String queryString = "select distinct mo from ModeloEJB mo, MarcaProductoEJB ma where mo.marcaId = ma.id and ma.empresaId = " + empresaId;
		String orderByPart = " order by mo.codigo";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findModeloByEmpresaId(int startIndex, int endIndex, Map aMap)
			throws GenericBusinessException {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		Long empresaId=null;
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);		
			if(propertyKey!=null && propertyKey.equals("empresaId"))		empresaId=new Long(property.toString());
		}
		
		String objectName = "mo";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select mo from ModeloEJB mo, MarcaProductoEJB ma where mo.marcaId = ma.id ";
		if(empresaId!=null) 
			{
			String orderByPart = " and ma.empresaId="+empresaId+"";
			queryString += orderByPart;
			}
		
		Query query = manager.createQuery(queryString);	 
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}

	
}
