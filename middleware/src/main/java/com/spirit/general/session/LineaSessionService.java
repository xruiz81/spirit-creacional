package com.spirit.general.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.general.session.generated._LineaSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface LineaSessionService extends _LineaSessionService{
	Collection findLineaByQuery(int startIndex,int endIndex,Map aMap) throws com.spirit.exception.GenericBusinessException;
	int getLineaListSize(Map aMap) throws com.spirit.exception.GenericBusinessException;
	
}
