package com.spirit.general.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.general.session.generated._CiudadSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CiudadSessionService extends _CiudadSessionService{
	Collection findCiudadByQueryAndProvinciaId(Map aMap,Long idProvincia) throws com.spirit.exception.GenericBusinessException;
	Collection findCiudadByQuery(int startIndex, int endIndex,Map aMap) throws com.spirit.exception.GenericBusinessException;
	int getCiudadListSize(Map aMap)  throws com.spirit.exception.GenericBusinessException;
   
}
