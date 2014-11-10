package com.spirit.nomina.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.session.generated._TipoRolSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TipoRolSessionService extends _TipoRolSessionService{
	public int getTipoRolListSize(Map aMap) throws GenericBusinessException;
	public java.util.Collection findTipoRolByQuery(int startIndex,int endIndex,Map aMap) throws GenericBusinessException;

}
