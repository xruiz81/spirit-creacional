package com.spirit.inventario.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.inventario.entity.ProductoRetencionEJB;
import com.spirit.inventario.session.generated._ProductoRetencionSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>ProductoRetencionSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:18 $
 *
 */
@Stateless
public class ProductoRetencionSessionEJB extends _ProductoRetencionSession implements ProductoRetencionSessionRemote  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;
   
   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(ProductoRetencionSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findProductoRetencionByEmpresaId(Long idEmpresa) throws GenericBusinessException {
	   String queryString = "select distinct pr from ProductoRetencionEJB pr, ProductoEJB p, GenericoEJB g where pr.productoId = p.id and p.genericoId = g.id and g.empresaId = " + idEmpresa + " order by pr.id";
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();  
   }
   
   public Collection findProductoRetencionByQueryAndFecha(Map aMap, java.sql.Date fecha) throws com.spirit.exception.GenericBusinessException {
		String objectName = "pr";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct pr from ProductoRetencionEJB " + objectName + " where " + where + " and pr.fechaInicio <= :fecha and pr.fechaFin >= :fecha";
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

}
