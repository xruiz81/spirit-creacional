package com.spirit.general.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.general.session.generated._ProvinciaSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ProvinciaSessionService extends _ProvinciaSessionService{
	Collection findProvinciaByQueryAndPaisId(Map aMap,Long idPais) throws com.spirit.exception.GenericBusinessException;

}
