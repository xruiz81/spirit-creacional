package com.spirit.crm.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.crm.session.generated._CorporacionSessionService;

public interface CorporacionSessionService extends _CorporacionSessionService{

	Collection getCorporacionList(int startIndex, int endIndex, Long idEmpresa)	throws com.spirit.exception.GenericBusinessException;
	Collection getCorporacionList(int startIndex, int endIndex, Map aMap) throws com.spirit.exception.GenericBusinessException;
	Collection getCorporacionList(int startIndex, int endIndex, Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int getCorporacionListSize(Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
	int getCorporacionListSize(Map aMap)throws com.spirit.exception.GenericBusinessException;
	int getCorporacionListSize(Map aMap , Long idEmpresa)throws com.spirit.exception.GenericBusinessException;

}