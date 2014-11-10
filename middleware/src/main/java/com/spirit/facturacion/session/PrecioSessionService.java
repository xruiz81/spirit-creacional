package com.spirit.facturacion.session;

import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.session.generated._PrecioSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PrecioSessionService extends _PrecioSessionService{
	public java.util.Collection  findPrecioProductosActivosByListaPrecioId(Long listaPrecioId) throws GenericBusinessException;
}
