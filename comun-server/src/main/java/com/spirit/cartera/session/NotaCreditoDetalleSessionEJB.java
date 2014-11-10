package com.spirit.cartera.session;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.spirit.cartera.session.generated._NotaCreditoDetalleSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.QueryBuilder;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class NotaCreditoDetalleSessionEJB extends _NotaCreditoDetalleSession implements NotaCreditoDetalleSessionRemote,NotaCreditoDetalleSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public Collection findNotaCreditoDetalleByQueryAndByEstados(Map aMap, String[] tiposNota) throws GenericBusinessException{
		String objectName = "ncd";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		
		String queryString = "select distinct ncd from NotaCreditoDetalleEJB ncd " +
				"where " +  where + " ";
				
		for(int i = 0; i < tiposNota.length; i++){
			if(i == 0 && tiposNota.length == 1){
				queryString += " and ncd.tipoNota = '" + tiposNota[i] + "'";
			}else if (i == 0 && tiposNota.length > 1){
				queryString += " and (ncd.tipoNota = '" + tiposNota[i] + "'";
			}else if (i > 0 && i <= (tiposNota.length -2)){
				queryString += " or ncd.tipoNota = '" + tiposNota[i] + "'";
			}else if (i > 0 && i == (tiposNota.length -1)){
				queryString += " or ncd.tipoNota = '" + tiposNota[i] + "')";
			}
		}
		
		queryString += " order by ncd.id asc";
		
		Query query = manager.createQuery(queryString);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		/*String tipoReferencia = (String) aMap.get("tipoReferencia");
		String tipoPresupuesto = (String) aMap.get("tipoPresupuesto");
		Long presupuestoId = (Long) aMap.get("presupuestoId");
		Long ordenId = (Long) aMap.get("ordenId");
		query.setParameter("tipoReferencia", tipoReferencia);
		query.setParameter("tipoPresupuesto", tipoPresupuesto);
		query.setParameter("presupuestoId", presupuestoId);
		query.setParameter("ordenId", ordenId);*/
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
				
		return query.getResultList();
	}
}
