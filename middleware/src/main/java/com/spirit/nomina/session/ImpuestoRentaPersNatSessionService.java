package com.spirit.nomina.session;

import java.sql.Date;
import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.ImpuestoRentaPersNatIf;
import com.spirit.nomina.session.generated._ImpuestoRentaPersNatSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ImpuestoRentaPersNatSessionService extends _ImpuestoRentaPersNatSessionService{
	
	public Collection<ImpuestoRentaPersNatIf> findImpuestoRentaPersNatByQueryByFechaInicioByFechFin(Map<String,Object> aMap)
	throws GenericBusinessException;
	
	
}
