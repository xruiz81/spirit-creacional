package com.spirit.general.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.generated._CuentaBancariaSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CuentaBancariaSessionService extends _CuentaBancariaSessionService{
	
	int getCuentaBancariaByQueryListSize(Map aMap) throws com.spirit.exception.GenericBusinessException;
	public Object[] getCuentaBancariaPlusByCuentaBancariaId(Long cuentaBancariaId) throws GenericBusinessException;
	public Collection<CuentaIf> getCuentaDeCuentaBancariaByCuentaBancariaId(Long cuentaBancariaId) throws GenericBusinessException;	
		
}
