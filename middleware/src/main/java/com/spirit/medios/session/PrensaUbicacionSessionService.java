package com.spirit.medios.session;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.session.generated._PrensaUbicacionSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PrensaUbicacionSessionService extends _PrensaUbicacionSessionService{

	public String getMaximoCodigoPrensaUbicacion(Long clienteId) throws GenericBusinessException;
}
