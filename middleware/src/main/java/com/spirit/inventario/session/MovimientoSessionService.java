package com.spirit.inventario.session;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.PedidoDetalleIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.MovimientoDetalleIf;
import com.spirit.inventario.entity.MovimientoIf;
import com.spirit.inventario.helper.GuiaRemision;
import com.spirit.inventario.helper.MovimientoConsultaData;
import com.spirit.inventario.helper.StockInventarioData;
import com.spirit.inventario.session.generated._MovimientoSessionService;

/**
 * The <code>MovimientoSessionService</code> bean exposes the business methods
 * in the interface.
 * 
 * @author Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:44 $
 */
public interface MovimientoSessionService extends _MovimientoSessionService {

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 **************************************************************************/
	
	public List getTomasInventario(Long idEmpresa,Long idSucursal,Long idBodega,Long idProducto,Date fechaInicio,Date fechaFin);
	public String procesarMovimiento(MovimientoIf model, List<? super MovimientoDetalleIf> modelDetalleList, UsuarioIf usuario) throws GenericBusinessException;
	public String procesarMovimiento(MovimientoIf model, List<? super MovimientoDetalleIf> modelDetalleList, boolean replicar, boolean aprobarAsiento, UsuarioIf usuario) throws GenericBusinessException;
	public boolean eliminarMovimiento(MovimientoIf model) throws GenericBusinessException;
	public void actualizarMovimiento(MovimientoIf model, List<MovimientoDetalleIf> modelDetalleList, List<MovimientoDetalleIf> modelDetalleEliminados) throws GenericBusinessException;
	public Collection getMovimientoList(int startIndex, int endIndex, Map aMap) throws GenericBusinessException;
	public int getMovimientoListSize(Map aMap) throws GenericBusinessException;
	public void aprobarMovimiento(MovimientoIf model, List<MovimientoDetalleIf> modelDetalleList, UsuarioIf usuario) throws GenericBusinessException;
	public void procesarFactura(FacturaIf modelFactura, List<FacturaDetalleIf> listaDetalleFactura, UsuarioIf usuario) throws GenericBusinessException;
	public void procesarAnulacionFactura(FacturaIf modelFactura, List<FacturaDetalleIf> listaDetalleFactura, UsuarioIf usuario) throws GenericBusinessException;
	public void procesarMovimientoVentaGiftcard(CarteraIf reciboCajaGiftcard, List<PedidoDetalleIf> detallesGiftcard, UsuarioIf usuario, BodegaIf bodega) throws GenericBusinessException;
	public List generarKardex(Long idEmpresa, Long idSucursal, Long idBodega, Long idProducto, java.util.Date fechaInicio, java.util.Date fechaFin) throws GenericBusinessException;
	public void procesarDevolucion(FacturaIf model, List<FacturaDetalleIf> modelDetalleList,UsuarioIf usuario) throws GenericBusinessException;
	public void procesarCompra(CompraIf model, List<CompraDetalleIf> modelDetalleList,UsuarioIf usuario) throws GenericBusinessException;
	public List<MovimientoConsultaData> getMovimientosPorAprobar();
	public GuiaRemision generarGuiaRemision(MovimientoIf movimientoIf, List<MovimientoDetalleIf> movimientoDetalleIfList, HashMap<String, Object> parametros) throws GenericBusinessException;
	public void generarAjustes(UsuarioIf usuarioIf,List<StockInventarioData> ajustesPositivos, List<StockInventarioData> ajustesNegativos) throws GenericBusinessException;
	public void procesarFixAsientoCostoVenta(Long idEmpresa, UsuarioIf usuario) throws GenericBusinessException;
	public void procesarTransferenciaTarjetaAfiliacionBodegaVirtual(OficinaIf sucursal, UsuarioIf usuario) throws GenericBusinessException;
	public MovimientoIf getMovimientoTransferenciaTarjetaAfiliacion(Long oficinaId, Long empresaId, Long usuarioId, String tipoTransferencia);
	public List<MovimientoDetalleIf> getMovimientoDetalleTransferenciaTarjetaAfiliacion(Long oficinaId, Long empresaId, String tipoTransferencia);
	public void recalcularStock(String anioQuery, String mesInicial, String mesLimite);
	public void recalcularStockPorCierre(Long idBodega);
}
