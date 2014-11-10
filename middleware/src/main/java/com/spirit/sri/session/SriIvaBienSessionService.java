package com.spirit.sri.session;

import java.util.Collection;

import com.spirit.sri.session.generated._SriIvaBienSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SriIvaBienSessionService extends _SriIvaBienSessionService{
	Collection getDescripcionPorcentaje() throws com.spirit.exception.GenericBusinessException;
	
}
