package com.spirit.compras.session;

import java.util.Collection;

import com.spirit.compras.session.generated._CompraDetalleSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CompraDetalleSessionService extends _CompraDetalleSessionService{

	Collection findCompraDetalleByDocumentoIdAndByCompraId(Long documentoId, Long compraId) throws com.spirit.exception.GenericBusinessException;
	public Collection findSriAirCompraDetalle() throws com.spirit.exception.GenericBusinessException;
	public Collection findSriIvaRetencionCompraDetalle() throws com.spirit.exception.GenericBusinessException;
}
