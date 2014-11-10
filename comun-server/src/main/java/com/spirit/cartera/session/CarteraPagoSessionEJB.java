package com.spirit.cartera.session;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ejb.*;
import javax.persistence.Query;

import com.spirit.cartera.session.generated._CarteraPagoSession;
import com.spirit.cartera.session.CarteraPagoSessionLocal;
import com.spirit.cartera.session.CarteraPagoSessionRemote;
import com.spirit.cartera.entity.*;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.server.QueryBuilder;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class CarteraPagoSessionEJB extends _CarteraPagoSession implements CarteraPagoSessionRemote,CarteraPagoSessionLocal  {

	@EJB
	private UtilitariosSessionLocal utilitariosLocal;
	
   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public Collection<CarteraPagoIf> findCarteraPagoByMapByFechaInicioByFechaFin(Map aMap 
			, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin, java.lang.Long idEmpresa)
			throws GenericBusinessException {
		try {
			String objectName = "cp";
			String where = "";
			if (!aMap.isEmpty()) {
				where = QueryBuilder.buildWhere(aMap, objectName) + " and";
			}


			String queryString = "select distinct cp from CarteraPagoEJB cp where "+ where;
		
			if (fechaInicio != null && fechaFin != null) {
				queryString += " cp.fechaPago >= :fechaInicio and cp.fechaPago <= :fechaFin ";
				
			}
			Query query = manager.createQuery(queryString);


			//fechaInicio = utilitariosLocal.resetTimestampStartDate(fechaInicio);
			query.setParameter("fechaInicio", fechaInicio);
			//fechaFin = utilitariosLocal.resetTimestampEndDate(fechaFin);
			query.setParameter("fechaFin", fechaFin);


			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}
			
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException(
			"Se ha producido un error en la consulta.");
		}
	}
}
