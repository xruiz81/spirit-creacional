package com.spirit.sri.session;

import java.util.Map;

import com.spirit.sri.session.generated._SriClienteRetencionSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SriClienteRetencionSessionService extends _SriClienteRetencionSessionService{
	java.util.Collection findSriClienteRetencionByQueryAndFecha(Map aMap, java.sql.Date fecha) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findPorcentajesRetencionByQuery(Map aMap) throws com.spirit.exception.GenericBusinessException;
}
