package com.spirit.facturacion.session;

import java.util.Collection;

import com.spirit.facturacion.session.generated._FacturaDetalleSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface FacturaDetalleSessionService extends _FacturaDetalleSessionService{

	Collection findFacturaDetalleByDocumentoIdAndByFacturaId(Long documentoId, Long facturaId) throws com.spirit.exception.GenericBusinessException;
	Collection findFacturaDetalleByFacturaIdAndByProductoId(Long facturaId, Long productoId) throws com.spirit.exception.GenericBusinessException;
}
