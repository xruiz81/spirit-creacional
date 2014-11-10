package com.spirit.medios.session;

import java.util.List;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.PrensaSeccionIf;
import com.spirit.medios.session.generated._PrensaSeccionSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PrensaSeccionSessionService extends _PrensaSeccionSessionService{

	public void procesarPrensa(List<PrensaSeccionIf> prensaSeccionList) throws GenericBusinessException;
	public String getMaximoCodigoPrensaSeccion(Long clienteId) throws GenericBusinessException;
}
