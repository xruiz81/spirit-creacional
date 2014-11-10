package com.spirit.compras.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.compras.session.generated._GastoSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>GastoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class GastoSessionEJB extends _GastoSession implements GastoSessionRemote  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(GastoSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   public Collection getGastoByQueryList(int startIndex, int endIndex,Map aMap) throws GenericBusinessException {
	   try{
		   if ( (endIndex - startIndex) < 0) {
			   return new ArrayList();
		   }
		   String where = QueryBuilder.buildWhere(aMap, "e");
		   String queryString = "from GastoEJB e where "+where;
		   // Add a an order by on all primary keys to assure reproducable results.
		   String orderByPart = "";
		   orderByPart += " order by e.id";
		   queryString += orderByPart;
		   Query query = manager.createQuery(queryString);
	
		   Set keys = aMap.keySet();
		   Iterator it = keys.iterator();
	
		   while (it.hasNext()) {
			   String propertyKey = (String) it.next();
			   Object property = aMap.get(propertyKey);
			   query.setParameter(propertyKey, property);
	
		   }
	
		   query.setFirstResult(startIndex );
		   query.setMaxResults(endIndex - startIndex);
	
		   return query.getResultList();
	   } catch( Exception e ){
		   e.printStackTrace();
		   throw new GenericBusinessException("Error en la consulta de Gastos !!");
	   }
   }
   
   public int getGastoListSizeByQuery(Map aMap) throws GenericBusinessException {
	   try{
		   String where = QueryBuilder.buildWhere(aMap, "e");
		   Query countQuery = manager.createQuery("select count(*) from GastoEJB e where "+where);
	
		   Set keys = aMap.keySet();
		   Iterator it = keys.iterator();
	
		   while (it.hasNext()) {
			   String propertyKey = (String) it.next();
			   Object property = aMap.get(propertyKey);
			   countQuery.setParameter(propertyKey, property);
	
		   }
	
		   List countQueryResult = countQuery.getResultList();
		   Long countResult = (Long) countQueryResult.get(0);
		   log.debug("The list size is: " + countResult.intValue());
		   return countResult.intValue();
	   } catch( Exception e){
		   e.printStackTrace();
		   throw new GenericBusinessException("Error en la búsqueda de gastos !!");
	   }
   }

}
