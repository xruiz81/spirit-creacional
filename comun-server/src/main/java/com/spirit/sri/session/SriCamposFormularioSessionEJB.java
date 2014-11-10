package com.spirit.sri.session;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ejb.*;
import javax.persistence.Query;

import com.spirit.server.QueryBuilder;
import com.spirit.sri.session.generated._SriCamposFormularioSession;
import com.spirit.sri.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class SriCamposFormularioSessionEJB extends _SriCamposFormularioSession implements SriCamposFormularioSessionRemote,SriCamposFormularioSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public Collection findCamposFormularioByQueryOrderCodigo(java.lang.String observacion,java.lang.String impuesto) throws com.spirit.exception.GenericBusinessException {
		String queryString = "Select distinct sa from SriCamposFormularioEJB sa "+
		"where sa.observacion = :observacion and sa.impuesto = :impuesto "+		
		"order by sa.codigo desc";		
		Query query = manager.createQuery(queryString);
		query.setParameter("observacion", observacion);
		query.setParameter("impuesto", impuesto);
		return query.getResultList();
		
		/*String objectName = "sa";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct sa from SriCamposFormularioEJB " + objectName + " " ;
		queryString += " order by sa.codigo asc";
		Query query = manager.createQuery(queryString);
		query.setParameter("observacion",observacion);
		query.setParameter("impuesto",impuesto);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}		
		return query.getResultList();*/
	}

}
