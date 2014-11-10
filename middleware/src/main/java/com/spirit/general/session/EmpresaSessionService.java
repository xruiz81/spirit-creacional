package com.spirit.general.session;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.NumeradoresIf;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.general.session.generated._EmpresaSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EmpresaSessionService extends _EmpresaSessionService{
	Collection findEmpresaByQuery(int startIndex, int endIndex,Map aMap)throws com.spirit.exception.GenericBusinessException;
	int getEmpresaListSize(Map aMap)throws com.spirit.exception.GenericBusinessException;
	public void procesarEmpresa(EmpresaIf model,List<NumeradoresIf> modelNumeradoresList,List<TipoClienteIf> modelOperadoresNegocioList) throws GenericBusinessException;
	public void eliminarEmpresa(Long idEmpresa) throws GenericBusinessException;
	
}
