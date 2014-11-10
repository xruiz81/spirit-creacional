package com.spirit.sri.session;

import java.util.Map;

import com.spirit.sri.session.generated._SriProveedorRetencionSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SriProveedorRetencionSessionService extends _SriProveedorRetencionSessionService{
	java.util.Collection findSriProveedorRetencionByQueryAndFecha(Map aMap, java.sql.Date fecha) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findPorcentajesRetencionByQuery(Map aMap) throws com.spirit.exception.GenericBusinessException;
}
