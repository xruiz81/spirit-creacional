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

import com.spirit.inventario.entity.BodegaEJB;
import com.spirit.inventario.session.generated._BodegaSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>BodegaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:18 $
 *
 */
@Stateless
public class BodegaSessionEJB extends _BodegaSession implements BodegaSessionRemote,BodegaSessionLocal  {

	@PersistenceContext(unitName="spirit")
	   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(BodegaSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   /**
    *
    * Retrieves a list of data object for the specified query Map.
    *
    * //@param Map $field.Name the field
    * @return Collection of BodegaIf data objects, empty list in case no results were found.
    */
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findBodegaByEmpresaId(Long idEmpresa) {
 	  String objectName = "e";
 	  String queryString = "select e from BodegaEJB " + objectName + ", FuncionBodegaEJB i where e.funcionBodegaId = i.id and i.empresaId = " + idEmpresa;
 	  Query query = manager.createQuery(queryString);
 	  return query.getResultList();
    }
   
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public java.util.Collection findBodegaByQuery(Map aMap, Long idEmpresa) {
	  String objectName = "e";
	  String where = QueryBuilder.buildWhere(aMap, objectName);
	  String queryString = "select e from BodegaEJB " + objectName + ", FuncionBodegaEJB i where " + where + " and e.funcionBodegaId = i.id and i.empresaId = " + idEmpresa;
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
  
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public java.util.Collection getBodegaList(int startIndex,int endIndex,Map aMap, Long idEmpresa) {
	  if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
	  String objectName = "e";
	  String where = QueryBuilder.buildWhere(aMap, objectName);
	  String queryString = "select e from BodegaEJB " + objectName + ", FuncionBodegaEJB i where " + where + " and e.funcionBodegaId = i.id and i.empresaId = " + idEmpresa;
	  //String queryString = "from BodegaEJB where codigo=:codigo";
	  Query query = manager.createQuery(queryString);

	  Set keys = aMap.keySet();
	  Iterator it = keys.iterator();

	  while (it.hasNext()) {
		  String propertyKey = (String) it.next();
		  Object property = aMap.get(propertyKey);
		  query.setParameter(propertyKey, property);
	  }
	  query.setFirstResult(startIndex);
	  //	query.setMaxResults(endIndex - startIndex);
	  return query.getResultList();
   }
  
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public int getBodegaListSize(Map aMap, Long idEmpresa) {
	  String objectName = "e";
	  String where = QueryBuilder.buildWhere(aMap, objectName);
	  String queryString = "select distinct count(*) from BodegaEJB " + objectName + ", FuncionBodegaEJB i where " + where + " and e.funcionBodegaId = i.id and i.empresaId = " + idEmpresa;
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
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
   }
 

}
