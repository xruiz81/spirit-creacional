package com.spirit.compras.session;

import java.util.Map;

import com.spirit.compras.session.generated._CompraDetalleGastoSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CompraDetalleGastoSessionService extends _CompraDetalleGastoSessionService{

	public java.util.Collection<Object[]> findCompraDetalleGastoCompraDetalleByQuery(Map aMap) throws GenericBusinessException;
}
