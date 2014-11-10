package com.spirit.sri.session;

import java.util.Map;

import com.spirit.sri.session.generated._SriSustentoTributarioSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SriSustentoTributarioSessionService extends _SriSustentoTributarioSessionService{
	
	java.util.Collection findSriSustentoTributarioByQuery(int startIndex, int endIndex, Map aMap) throws com.spirit.exception.GenericBusinessException;
	int getSriSustentoTributarioListSize(Map aMap) throws com.spirit.exception.GenericBusinessException;
	
}
