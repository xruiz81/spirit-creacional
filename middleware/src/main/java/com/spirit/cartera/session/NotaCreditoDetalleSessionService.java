package com.spirit.cartera.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.cartera.session.generated._NotaCreditoDetalleSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface NotaCreditoDetalleSessionService extends _NotaCreditoDetalleSessionService{
	
	public Collection findNotaCreditoDetalleByQueryAndByEstados(Map aMap, String[] tiposNota) throws GenericBusinessException;
}
