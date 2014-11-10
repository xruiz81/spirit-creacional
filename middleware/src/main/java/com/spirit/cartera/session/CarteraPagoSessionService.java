package com.spirit.cartera.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.cartera.entity.CarteraPagoIf;
import com.spirit.cartera.session.generated._CarteraPagoSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CarteraPagoSessionService extends _CarteraPagoSessionService{
	
	public Collection<CarteraPagoIf> findCarteraPagoByMapByFechaInicioByFechaFin(Map aMap 
			, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin, java.lang.Long idEmpresa) throws GenericBusinessException;
}
