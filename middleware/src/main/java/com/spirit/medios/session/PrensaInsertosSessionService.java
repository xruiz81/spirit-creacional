package com.spirit.medios.session;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.session.generated._PrensaInsertosSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PrensaInsertosSessionService extends _PrensaInsertosSessionService{

	public String getMaximoCodigoPrensaInsertos(Long clienteId) throws GenericBusinessException;
}
