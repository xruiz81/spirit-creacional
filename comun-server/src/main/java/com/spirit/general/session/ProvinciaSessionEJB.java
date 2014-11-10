package com.spirit.general.session;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.generated._ProvinciaSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>ProvinciaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:16 $
 *
 */
@Stateless
public class ProvinciaSessionEJB extends _ProvinciaSession implements ProvinciaSessionRemote  {

   @PersistenceContext(unitName="spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(ProvinciaSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	public Collection findProvinciaByQueryAndPaisId(Map aMap, Long idPais) throws GenericBusinessException {
	 	String objectName = "e";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "from ProvinciaEJB " + objectName + " where " + where + " and paisId = " + idPais;
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
