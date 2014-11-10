package com.spirit.sri.session;

import java.util.Map;

import com.spirit.sri.session.generated._SriIvaRetencionSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SriIvaRetencionSessionService extends _SriIvaRetencionSessionService{
	java.util.Collection findSriIvaRetencionByFecha(java.sql.Date fecha) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findSriIvaRetencionByQueryAndFecha(Map aMap, java.sql.Date fecha) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection getPorcentajes() throws com.spirit.exception.GenericBusinessException;
	
}
