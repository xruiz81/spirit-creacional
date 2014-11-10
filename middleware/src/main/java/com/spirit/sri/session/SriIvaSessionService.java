package com.spirit.sri.session;

import java.sql.Date;

import com.spirit.sri.session.generated._SriIvaSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SriIvaSessionService extends _SriIvaSessionService{
	public java.util.Collection getIvaByFecha(Date fecha) throws com.spirit.exception.GenericBusinessException;

}
