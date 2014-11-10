package com.spirit.sri.session;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.sri.session.generated._ImpuestoRentaSession;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class ImpuestoRentaSessionEJB extends _ImpuestoRentaSession implements ImpuestoRentaSessionRemote,ImpuestoRentaSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public Collection findImpuestoRentaByFechaInicioAndFechaFin(java.sql.Date fechaInicio, java.sql.Date fechaFin) throws GenericBusinessException {
		String queryString = "select ir from ImpuestoRentaEJB ir where ir.fechaInicio <= :fechaInicio and ir.fechaFin >= :fechaFin";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		System.out.println("FECHA INICIO: " + fechaInicio.toString());
		System.out.println("FECHA FIN: " + fechaFin.toString());
		return query.getResultList();
	}
}
