package com.spirit.sri.session;

import java.util.Map;

import com.spirit.sri.session.generated._SriAirSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SriAirSessionService extends _SriAirSessionService{
	java.util.Collection findSriAirByFecha(java.sql.Date fecha) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findSriAirByQueryAndFecha(Map aMap, java.sql.Date fecha) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection getPorcentajes() throws com.spirit.exception.GenericBusinessException;
	
}
