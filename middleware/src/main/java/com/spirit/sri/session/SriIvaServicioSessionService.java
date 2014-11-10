package com.spirit.sri.session;

import java.util.Collection;

import com.spirit.sri.session.generated._SriIvaServicioSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SriIvaServicioSessionService extends _SriIvaServicioSessionService{
	Collection getDescripcionPorcentaje() throws com.spirit.exception.GenericBusinessException;
	
}
