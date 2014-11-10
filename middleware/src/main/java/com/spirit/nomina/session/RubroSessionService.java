package com.spirit.nomina.session;

import java.math.BigDecimal;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.session.generated._RubroSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface RubroSessionService extends _RubroSessionService{
	public int getRubroListSize(Map aMap) throws GenericBusinessException;

	public java.util.Collection<RubroIf> findRubroByQuery(int startIndex,int endIndex,Map aMap) throws GenericBusinessException;

	public java.util.Collection<RubroIf> findRubroByQueryByTiposRubro(Map aMap,String... tiposRubro);

	public BigDecimal getRubroTotalByRubroCodigo(String codigoRubro,Long contratoId,String queryRolesPago) throws GenericBusinessException;
	
}
