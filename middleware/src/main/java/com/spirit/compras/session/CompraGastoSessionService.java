package com.spirit.compras.session;

import java.util.Collection;

import com.spirit.compras.gastos.CompraGastoClase;
import com.spirit.compras.session.generated._CompraGastoSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CompraGastoSessionService extends _CompraGastoSessionService{
	public CompraGastoClase procesarCompraGasto( CompraGastoClase compraGastoClase ) throws GenericBusinessException;
	public CompraGastoClase getMapaGastoMapaDetalleGasto(Long compraId) throws GenericBusinessException;
	public Collection findGastoByCompraId(Long idCompra) throws GenericBusinessException;
}
