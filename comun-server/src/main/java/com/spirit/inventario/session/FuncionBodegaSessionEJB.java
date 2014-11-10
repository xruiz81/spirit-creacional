package com.spirit.inventario.session;

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

import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.session.generated._FuncionBodegaSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>FuncionBodegaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:18 $
 *
 */
@Stateless
public class FuncionBodegaSessionEJB extends _FuncionBodegaSession implements FuncionBodegaSessionRemote, FuncionBodegaSessionLocal  {

	@PersistenceContext(unitName="spirit")
	private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(FuncionBodegaSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   public Collection findFuncionBodegaByQueryAndByIdEmpresa(Map aMap, Long idEmpresa) throws GenericBusinessException {
		String objectName = "e";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "from FuncionBodegaEJB " + objectName + " where " + where + " and empresaId = " + idEmpresa;
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
   
   public Collection getFuncionBodegaList(int startIndex,int endIndex,Map aMap, Long idEmpresa) throws GenericBusinessException {
	   if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
	   String objectName = "e";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "from FuncionBodegaEJB " + objectName + " where " + where + " and empresaId = " + idEmpresa;
	 	Query query = manager.createQuery(queryString);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				String property = (String) aMap.get(propertyKey);
				query.setParameter(propertyKey, property);

			}
			query.setFirstResult(startIndex);
			query.setMaxResults(endIndex - startIndex);
			return query.getResultList();
	}
   public int getFuncionBodegaListSize(Map aMap, Long idEmpresa) throws GenericBusinessException {
	   String objectName = "e";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "select distinct count(*) from FuncionBodegaEJB " + objectName + " where " + where + " and empresaId = " + idEmpresa;
	 	Query query = manager.createQuery(queryString);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				String property = (String) aMap.get(propertyKey);
				query.setParameter(propertyKey, property);

			}
			List countQueryResult = query.getResultList();
			Long countResult = (Long) countQueryResult.get(0);
			log.debug("The list size is: " + countResult.intValue());
			return countResult.intValue();
	}
   
}
