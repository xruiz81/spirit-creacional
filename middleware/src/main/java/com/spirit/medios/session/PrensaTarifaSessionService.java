package com.spirit.medios.session;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.session.generated._PrensaTarifaSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PrensaTarifaSessionService extends _PrensaTarifaSessionService{

	public String getMaximoCodigoPrensaTarifa(Long clienteId) throws GenericBusinessException;
}
