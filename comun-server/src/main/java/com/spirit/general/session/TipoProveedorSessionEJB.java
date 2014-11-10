package com.spirit.general.session;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.general.session.generated._TipoProveedorSession;
import com.spirit.server.QueryBuilder;

/**
 * The <code>TipoProveedorSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:16 $
 *
 */
@Stateless
public class TipoProveedorSessionEJB extends _TipoProveedorSession implements TipoProveedorSessionRemote  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	public java.util.Collection findTipoProveedorByEmpresaIdAndClase(java.lang.Long idEmpresa, String tipo) {
	      String queryString = "select e from TipoProveedorEJB e where e.tipo = '" + tipo + "' and e.empresaId = " + idEmpresa;
	      // Add a an order by on all primary keys to assure reproducable results.
	      String orderByPart = "";
	      orderByPart += " order by e.id";
	      queryString += orderByPart;
	      Query query = manager.createQuery(queryString);
	      return query.getResultList();
	}
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findTipoProveedorByQueryAndEmpresaId(Map aMap,Long idEmpresa) {
	String objectName = "e";
	String where = QueryBuilder.buildWhere(aMap, objectName);
	String queryString = "from TipoProveedorEJB " + objectName + " where "	+ where + " and empresaId = " + idEmpresa;
	Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			String property = (String) aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		return query.getResultList();
     
   }


}
