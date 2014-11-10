package com.spirit.inventario.session;

import java.sql.Date;
import java.util.Collection;
import java.util.Map;

import com.spirit.inventario.session.generated._LoteSessionService;

/**
 * The <code>LoteSessionService</code> bean exposes the business methods in
 * the interface.
 * 
 * @author Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:44 $
 */
public interface LoteSessionService extends _LoteSessionService{

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 **************************************************************************/

	java.util.Collection findLoteByProductoIdAndEstadoAndFecha(
			java.lang.Long idProducto, Date fecha)
			throws com.spirit.exception.GenericBusinessException;

	java.util.Collection findLoteByProductoIdAndByStockCantidadMinimaAndByEstadoAndByFecha(
			java.lang.Long idProducto, int cantidadMinimaStock, Date fecha)
			throws com.spirit.exception.GenericBusinessException;

	// PedidoDetalleIf verificarStockPorLote(PedidoDetalleIf pedidoDetalle, Long
	// idBodega) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findLoteByEmpresaId(java.lang.Long idEmpresa)
			throws com.spirit.exception.GenericBusinessException;

	public java.util.Collection findLoteByProductoIdAndBodegaIdAndEstadoAndFecha(
			java.lang.Long idProducto, Long idBodega, Date fecha);

}
