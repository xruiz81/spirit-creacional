package com.spirit.compras.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.compras.session.generated._GastoSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface GastoSessionService extends _GastoSessionService{

	public Collection getGastoByQueryList(int startIndex, int endIndex,Map aMap) throws GenericBusinessException;
	public int getGastoListSizeByQuery(Map aMap) throws GenericBusinessException;
}
