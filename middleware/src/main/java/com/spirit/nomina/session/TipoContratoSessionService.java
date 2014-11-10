package com.spirit.nomina.session;

import java.util.Collection;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.session.generated._TipoContratoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TipoContratoSessionService extends _TipoContratoSessionService{
	public Collection<TipoContratoIf> findTiposContratosUsados(Long empresaId) throws GenericBusinessException;
	
}
