package com.spirit.sri.session;

import java.util.Collection;

import com.spirit.exception.GenericBusinessException;
import com.spirit.sri.session.generated._ImpuestoRentaSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ImpuestoRentaSessionService extends _ImpuestoRentaSessionService{
	public Collection findImpuestoRentaByFechaInicioAndFechaFin(java.sql.Date fechaInicio, java.sql.Date fechaFin) throws GenericBusinessException;
}
