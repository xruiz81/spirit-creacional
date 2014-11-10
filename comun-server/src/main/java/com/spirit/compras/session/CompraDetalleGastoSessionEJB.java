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

import com.spirit.compras.entity.CompraDetalleGastoEJB;
import com.spirit.compras.session.generated._CompraDetalleGastoSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>CompraDetalleGastoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class CompraDetalleGastoSessionEJB extends _CompraDetalleGastoSession implements CompraDetalleGastoSessionRemote,CompraDetalleGastoSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

     public java.util.Collection<Object[]> findCompraDetalleGastoCompraDetalleByQuery(Map aMap) throws GenericBusinessException {
	   try{
		   String objectName = "e";
		   String where = QueryBuilder.buildWhere(aMap, objectName);
		   String queryString = "select cd,e from CompraDetalleGastoEJB " + objectName + ",CompraDetalleEJB cd where "
		   +" e.compraDetalleId = cd.id and " + where;
		   Query query = manager.createQuery(queryString);
	
		   Set keys = aMap.keySet();
		   Iterator it = keys.iterator();
	
		   while (it.hasNext()) {
			   String propertyKey = (String) it.next();
			   Object property = aMap.get(propertyKey);
			   query.setParameter(propertyKey, property);
	
		   }
	
		   return query.getResultList();
	   } catch( Exception e ){
		   e.printStackTrace();
		   throw new GenericBusinessException("Error al consultar Gastos por detalle de Compra !!");
	   }
   }

}
