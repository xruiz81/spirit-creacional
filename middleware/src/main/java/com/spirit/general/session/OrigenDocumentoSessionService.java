package com.spirit.general.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.general.session.generated._OrigenDocumentoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OrigenDocumentoSessionService extends _OrigenDocumentoSessionService{
	Collection findOrigenDocumentoByQuery(int startIndex,int endIndex,Map aMap) throws com.spirit.exception.GenericBusinessException;
	int getOrigenDocumentoListSize(Map aMap) throws com.spirit.exception.GenericBusinessException;
}
