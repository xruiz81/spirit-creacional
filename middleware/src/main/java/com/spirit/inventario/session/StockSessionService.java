package com.spirit.inventario.session;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.MovimientoDetalleIf;
import com.spirit.inventario.entity.MovimientoIf;
import com.spirit.inventario.entity.StockIf;
import com.spirit.inventario.handler.StockValorizadoData;
import com.spirit.inventario.helper.StockException;
import com.spirit.inventario.helper.StockInventarioData;
import com.spirit.inventario.session.generated._StockSessionService;

/**
 * The <code>StockSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:44 $
 */
public interface StockSessionService extends _StockSessionService{

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	java.util.Collection findStockByIdLoteAndIdBodega(java.lang.Long loteId, java.lang.Long bodegaId) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findStockByOficinaId(java.lang.Long idOficina) throws com.spirit.exception.GenericBusinessException;
	public List<StockIf> afectarStock(MovimientoIf model, MovimientoDetalleIf modelDetalle) throws com.spirit.exception.GenericBusinessException;
	public void chequearStock(Long idBodega, Long idProducto,
			BigDecimal cantidad) throws StockException,
			GenericBusinessException;
	public List<StockInventarioData> getConsultaCierreStock(Long idBodega,Long productoId,Long idGenerico,Long idPresentacion,Long idModelo,Long idColor) throws GenericBusinessException;
	public void cerrarStock(Long idBodega) throws GenericBusinessException;
	public StockIf getStockMesActual(MovimientoIf model,
			MovimientoDetalleIf modelDetalle) throws GenericBusinessException;
	public List getStockModificado(int intervaloTiempo,boolean obtenerTodos,String codigoBodega) throws GenericBusinessException;
	public Collection getStockModificadoWebService(Long empresaId, int intervaloMinutos, boolean obtenerTodos) throws GenericBusinessException;
	public List<StockInventarioData> getConsultaCierreStock(Long idBodega,
			Long idProducto, Long idGenerico, Long idPresentacion,
			Long idModelo, Long idColor, String mes, String anio)
			throws GenericBusinessException;
	
	public List<StockInventarioData> getConsultaCierreStock(Long idBodega,
			Long idProducto, Long idGenerico, Long idPresentacion,
			Long idModelo, Long idColor, String mes, String anio,
			boolean filtarPorEstado) throws GenericBusinessException;
	
	public List<StockIf> getStockActualyMesesSiguientes(Long idLote, Long idBodega, String mesStock,
			String anioStock);
	public void aplicarAfectacionSaldo(StockIf stock, String signo, BigDecimal cantidad) throws GenericBusinessException;	
	public Vector<StockValorizadoData> getStockValorizado(Long idEmpresa, Long idBodega, java.util.Date fechaCorte) throws GenericBusinessException;
}
