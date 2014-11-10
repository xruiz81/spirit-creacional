package com.spirit.general.session;

import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.generated._DepartamentoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface DepartamentoSessionService extends _DepartamentoSessionService{
	public java.util.Collection findDepartamentoByQuery(Map aMap,int startIndex,int endIndex) throws GenericBusinessException;
	public int getDepartamentoListSizeByQuery(Map aMap) throws GenericBusinessException;

}
