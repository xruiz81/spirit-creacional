package com.spirit.nomina.session;

import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.session.generated._GastoDeducibleSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface GastoDeducibleSessionService extends _GastoDeducibleSessionService{
	
	public int getGastoDeducibleListSize(Map aMap) throws GenericBusinessException;
	
	public java.util.Collection<RubroIf> findGastoDeducibleByQuery(int startIndex,
			int endIndex, Map aMap) throws GenericBusinessException;
	
}
