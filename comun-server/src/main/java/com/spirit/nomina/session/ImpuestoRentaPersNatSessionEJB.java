package com.spirit.nomina.session;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.ImpuestoRentaPersNatIf;
import com.spirit.nomina.session.generated._ImpuestoRentaPersNatSession;
import com.spirit.server.QueryBuilder;
import com.spirit.util.FindQuery;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class ImpuestoRentaPersNatSessionEJB extends _ImpuestoRentaPersNatSession implements ImpuestoRentaPersNatSessionRemote,ImpuestoRentaPersNatSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
 * @throws GenericBusinessException 
    *******************************************************************************************************************/

	public Collection<ImpuestoRentaPersNatIf> findImpuestoRentaPersNatByQueryByFechaInicioByFechFin(Map<String,Object> aMap)
	throws GenericBusinessException{
		
		String objectName = "e";
		//Query query = manager.createQuery(queryString);
		
		Date fechaInicio = (Date)aMap.remove("fechaInicio");
		Date fechaFin = (Date)aMap.remove("fechaFin");
		
		if ( fechaInicio == null )
			throw new GenericBusinessException("Fecha Inicio deben tener valor para consulta de Impuesto a la Renta !!");
		
		String where = QueryBuilder.buildWhere(aMap, objectName);
		
		Calendar calInicio = new GregorianCalendar();
		calInicio.setTimeInMillis(fechaInicio.getTime());
		calInicio.set(Calendar.DATE, 1);
		
		Calendar calFin =  new GregorianCalendar();
		if ( fechaFin != null ){
			calFin.setTimeInMillis(fechaFin.getTime());
			
		} else {
			calFin.setTimeInMillis(fechaInicio.getTime());
			calFin.set(Calendar.DATE, calFin.getActualMaximum(Calendar.DATE));
		}
		
		String queryString = "select e from ImpuestoRentaPersNatEJB " + objectName + " " +
		"where e.fechaInicio <= :fechaInicio ";
		
		if ( fechaFin == null ){
			fechaFin = new Date( calFin.getTimeInMillis() );
			queryString += " and ( e.fechaFin is null or e.fechaFin >= :fechaFin )";
		} else {
			queryString += " and e.fechaFin >= :fechaFin ";
		}
		
		if ( where.trim().length() > 0 ){
			queryString += (" and "+where);
		}
		
		Query query = manager.createQuery(queryString);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}
		query.setParameter("fechaInicio", fechaInicio);
		if ( fechaFin != null )
			query.setParameter("fechaFin", fechaFin);
		else 
			query.setParameter("fechaFin", new Date(calFin.getTimeInMillis()));
		
		List<ImpuestoRentaPersNatIf> countQueryResult = query.getResultList();
		
		return countQueryResult;
	}

}
