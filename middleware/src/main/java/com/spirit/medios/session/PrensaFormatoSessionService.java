package com.spirit.medios.session;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.session.generated._PrensaFormatoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PrensaFormatoSessionService extends _PrensaFormatoSessionService{

	public String getMaximoCodigoPrensaFormato(Long clienteId) throws GenericBusinessException;
}
