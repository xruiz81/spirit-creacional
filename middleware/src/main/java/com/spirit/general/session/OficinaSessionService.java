package com.spirit.general.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.general.session.generated._OficinaSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OficinaSessionService extends _OficinaSessionService{
	Collection findOficinaByQuery(int startIndex,int endIndex,Map aMap) throws com.spirit.exception.GenericBusinessException;
	int getOficinaListSize(Map aMap) throws com.spirit.exception.GenericBusinessException;
	public Map mapearOficinas(Long idEmpresa);
}
