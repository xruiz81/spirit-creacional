package com.spirit.nomina.session;

import java.sql.Date;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.SalarioMinimoVitalIf;
import com.spirit.nomina.session.generated._SalarioMinimoVitalSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SalarioMinimoVitalSessionService extends _SalarioMinimoVitalSessionService{
	
	public SalarioMinimoVitalIf findSalarioMinimoVitalByFechaMedia(Date fechaMedia) throws GenericBusinessException;
}
