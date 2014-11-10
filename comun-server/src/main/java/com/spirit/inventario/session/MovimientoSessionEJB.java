package com.spirit.inventario.session;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.compras.entity.CompraDetalleGastoIf;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.session.CompraDetalleGastoSessionLocal;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.comun.querys.QueryHelperServerLocal;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.session.AsientoSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.PedidoDetalleIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.PuntoImpresionIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.mdb.messages.bo.StockMessageLocal;
import com.spirit.general.session.DocumentoSessionLocal;
import com.spirit.general.session.OficinaSessionLocal;
import com.spirit.general.session.TipoDocumentoSessionLocal;
import com.spirit.general.session.UsuarioSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.general.webservice.consumer.SpiritWebServiceConsumerLocal;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.FuncionBodegaIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.KardexIf;
import com.spirit.inventario.entity.LoteIf;
import com.spirit.inventario.entity.MovimientoData;
import com.spirit.inventario.entity.MovimientoDetalleData;
import com.spirit.inventario.entity.MovimientoDetalleEJB;
import com.spirit.inventario.entity.MovimientoDetalleIf;
import com.spirit.inventario.entity.MovimientoEJB;
import com.spirit.inventario.entity.MovimientoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.StockEJB;
import com.spirit.inventario.entity.StockIf;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.inventario.handler.MovimientoAsientoAutomaticoHandlerLocal;
import com.spirit.inventario.helper.GuiaRemision;
import com.spirit.inventario.helper.GuiaRemisionDetalle;
import com.spirit.inventario.helper.KardexData;
import com.spirit.inventario.helper.MovimientoConsultaData;
import com.spirit.inventario.helper.StockInventarioData;
import com.spirit.inventario.session.generated._MovimientoSession;
import com.spirit.poscola.entity.PosColaIf;
import com.spirit.poscola.session.PosColaSessionLocal;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.util.DeepCopy;

/**
 * The <code>MovimientoSession</code> session bean, which acts as a facade to
 * the underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:18 $
 * 
 */
@Stateless
public class MovimientoSessionEJB extends _MovimientoSession implements
		MovimientoSessionRemote, MovimientoSessionLocal {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	@Resource
	private SessionContext ctx;

	@EJB
	private StockSessionLocal stockLocal;

	@EJB
	private UtilitariosSessionLocal utilitariosSessionLocal;

	@EJB
	private MovimientoDetalleSessionLocal movimientoDetalleSessionLocal;

	@EJB
	private CompraDetalleGastoSessionLocal compraDetalleGastoLocal;

	@EJB
	private MovimientoAsientoAutomaticoHandlerLocal movimientoAsientoAutomaticoHandlerLocal;

	@EJB
	private TipoDocumentoSessionLocal tipoDocumentoLocal;

	@EJB
	private DocumentoSessionLocal documentoSessionLocal;

	@EJB
	private FuncionBodegaSessionLocal funcionBodegaLocal;

	@EJB
	private BodegaSessionLocal bodegaSessionLocal;

	@EJB
	private PosColaSessionLocal posColaSessionLocal;

	@EJB
	private StockMessageLocal masterDetailObjectMessageLocal;

	@EJB
	private TipoProductoSessionLocal tipoProductoSessionLocal;

	@EJB
	private ProductoSessionLocal productoSessionLocal;

	@EJB
	private GenericoSessionLocal genericoSessionLocal;

	@EJB
	private QueryHelperServerLocal queryHelperServerLocal;

	@EJB
	private LoteSessionLocal loteSessionLocal;

	@EJB
	private OficinaSessionLocal oficinaSessionLocal;

	@EJB
	private UsuarioSessionLocal usuarioSessionLocal;

	@EJB
	private AsientoSessionLocal asientoSessionLocal;

	@EJB
	private SpiritWebServiceConsumerLocal swsl;

	@EJB
	private JPAManagerLocal jpaManagerLocal;

	/**
	 * The logger object.
	 */
	private static Logger log = LogService
			.getLogger(MovimientoSessionEJB.class);

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 * 
	 * @throws GenericBusinessException
	 * @throws GenericBusinessException
	 * 
	 * @throws GenericBusinessException
	 * 
	 * @throws GenericBusinessException
	 **************************************************************************/

	public List getTomasInventario(Long idEmpresa, Long idSucursal,
			Long idBodega, Long idProducto, Date fechaInicio, Date fechaFin) {
		String queryString = "FROM KardexEJB kardex " + "where ";
		String parametrosComunes = "(kardex.empresaId=:idEmpresa) and "
				+ "(kardex.oficinaId=:idOficina OR :idOficina is null) and "
				+ "(kardex.bodegaId=:idBodega OR :idBodega is null) and "
				+ "(kardex.productoId=:idProducto OR :idProducto is null) and "
				+ "(kardex.documentoCodigo=:documentoAJP or kardex.documentoCodigo=:documentoAJN) ";

		String queryKardexActual = " and kardex.fechaMovimiento > :fechaInicial and "
				+ "(kardex.fechaMovimiento <= :fechaFinal) ";
		String queryOrderBy = " order by " + "kardex.empresaId, "
				+ "kardex.oficinaId, " + "kardex.bodegaId, "
				+ "kardex.productoId, " + "kardex.fechaMovimiento";
		Query query = manager.createQuery(queryString + parametrosComunes
				+ queryKardexActual + queryOrderBy);
		query.setParameter("idEmpresa", idEmpresa);
		query.setParameter("idOficina", idSucursal);
		query.setParameter("idBodega", idBodega);
		query.setParameter("idProducto", idProducto);
		query.setParameter("fechaInicial", fechaInicio);
		query.setParameter("fechaFinal", fechaFin);
		query.setParameter("documentoAJP", "STI");
		query.setParameter("documentoAJN", "FTI");

		List<KardexIf> listaQueryKardex = query.getResultList();
		List<KardexData> listaKardex = new ArrayList<KardexData>();

		HashMap<Long, String> mapaSucursales = new HashMap<Long, String>();
		HashMap<Long, String> mapaBodegas = new HashMap<Long, String>();
		HashMap<Long, String> mapaLote = new HashMap<Long, String>();
		HashMap<Long, String> mapaProductos = new HashMap<Long, String>();

		for (KardexIf kardexTemp : listaQueryKardex) {
			KardexData kardexData = new KardexData();
			kardexData.setMovimiento(kardexTemp.getMovimientoCodigo() + " ["
					+ kardexTemp.getTipodocumentoCodigo() + "] "
					+ kardexTemp.getDocumentoCodigo() + " - "
					+ kardexTemp.getDocumentoDescripcion());
			kardexData.setIdSucursal(kardexTemp.getOficinaId());
			kardexData.setSucursal(kardexTemp.getOficinaCodigo() + " "
					+ (String) kardexTemp.getOficinaNombre());
			kardexData.setIdBodega(kardexTemp.getBodegaId());
			kardexData.setBodega(kardexTemp.getBodegaCodigo() + " "
					+ kardexTemp.getBodegaNombre());
			kardexData.setDifAnterior(kardexTemp.getStockAnterior());

			kardexData.setIdProducto(kardexTemp.getProductoId());
			kardexData.setProducto(queryHelperServerLocal
					.getDescripcionProducto(kardexTemp.getProductoId()));

			kardexData.setIdLote(kardexTemp.getLoteId());
			kardexData.setLote(kardexTemp.getLoteCodigo());
			kardexData.setFecha(kardexTemp.getFechaMovimiento());
			kardexData.setCantidad(setCantidad(
					kardexTemp.getTipodocumentoSignostock(),
					kardexTemp.getCantidadDetalle()));

			mapaSucursales.put(kardexData.getIdSucursal(),
					kardexData.getSucursal());
			mapaBodegas.put(kardexData.getIdBodega(), kardexData.getBodega());
			mapaProductos.put(kardexData.getIdProducto(),
					kardexData.getProducto());
			mapaLote.put(kardexData.getIdLote(), kardexData.getLote());
			listaKardex.add(kardexData);
		}
		return listaKardex;
	}

	private void generarAjuste(UsuarioIf usuarioIf,
			List<StockInventarioData> ajustes, String tipoDocumentoCodigo,
			String documentoCodigo) throws GenericBusinessException {
		MovimientoData movimientoData = null;
		List<MovimientoDetalleIf> listaMovimientoDetalle = null;
		if (ajustes.size() > 0) {
			movimientoData = new MovimientoData();
			listaMovimientoDetalle = new ArrayList<MovimientoDetalleIf>();
			StockInventarioData stockInventario = ajustes.get(0);
			movimientoData.setBodegaId(stockInventario.getIdBodega());
			movimientoData.setEstado("A");

			movimientoData.setFechaCreacion(utilitariosSessionLocal
					.getServerDateTimeStamp());
			movimientoData.setFechaDocumento(utilitariosSessionLocal
					.getServerDateTimeStamp());
			TipoDocumentoIf tipoDocumentoIf = queryHelperServerLocal
					.getTipoDocumento(tipoDocumentoCodigo);
			movimientoData.setTipodocumentoId(tipoDocumentoIf.getId());
			if (tipoDocumentoIf.getSignostock().equalsIgnoreCase("+")) {
				movimientoData.setObservacion("TOMA DE INVENTARIO/AJP: ");
			} else {
				movimientoData.setObservacion("TOMA DE INVENTARIO/AJN: ");

			}

			for (StockInventarioData stockInventarioData : ajustes) {
				MovimientoDetalleData movimientoDetalleData = new MovimientoDetalleData();
				BigDecimal diferencia = stockInventarioData.getCantidadFisica()
						.subtract(stockInventarioData.getCantidad());
				movimientoDetalleData.setCantidad(diferencia.abs());
				movimientoDetalleData.setStockAnterior(stockInventarioData
						.getCantidad());
				movimientoDetalleData.setDocumentoId(queryHelperServerLocal
						.getDocumento(documentoCodigo).getId());
				movimientoDetalleData
						.setLoteId(stockInventarioData.getIdLote());
				listaMovimientoDetalle.add(movimientoDetalleData);
			}
			procesarMovimiento(movimientoData, listaMovimientoDetalle,
					usuarioIf);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void generarAjustes(UsuarioIf usuarioIf,
			List<StockInventarioData> ajustesPositivos,
			List<StockInventarioData> ajustesNegativos)
			throws GenericBusinessException {
		generarAjuste(usuarioIf, ajustesPositivos, "AJP", "STI");
		generarAjuste(usuarioIf, ajustesNegativos, "AJN", "FTI");
	}

	public GuiaRemision generarGuiaRemision(MovimientoIf movimientoIf,
			List<MovimientoDetalleIf> movimientoDetalleIfList,
			HashMap<String, Object> parametros) throws GenericBusinessException {
		// SOLO TRANSFERENCIAS
		BodegaIf bodega = bodegaSessionLocal.getBodega(movimientoIf
				.getBodegaId());
		BodegaIf bodegaRef = bodegaSessionLocal.getBodega(movimientoIf
				.getBodegarefId());

		OficinaIf oficinaOrigen = oficinaSessionLocal.getOficina(bodega
				.getOficinaId());
		OficinaIf oficinaDestino = oficinaSessionLocal.getOficina(bodegaRef
				.getOficinaId());

		GuiaRemision guiaRemision = new GuiaRemision();

		guiaRemision.setPuntoPartida(oficinaOrigen.getCodigo() + " "
				+ oficinaOrigen.getNombre());
		guiaRemision.setDestino(oficinaDestino.getCodigo() + " - "
				+ oficinaDestino.getNombre());

		List<GuiaRemisionDetalle> guiaRemisionDetalleList = new ArrayList<GuiaRemisionDetalle>();
		for (MovimientoDetalleIf moviDetalleIf : movimientoDetalleIfList) {
			GuiaRemisionDetalle guiaRemisionDetalle = new GuiaRemisionDetalle();
			guiaRemisionDetalle.setCantidad(moviDetalleIf.getCantidad());
			LoteIf lote = loteSessionLocal.getLote(moviDetalleIf.getLoteId());
			ProductoIf producto = productoSessionLocal.getProducto(lote
					.getProductoId());
			GenericoIf generico = genericoSessionLocal.getGenerico(producto
					.getGenericoId());
			guiaRemisionDetalle.setDescripcion(generico.getNombre());
			guiaRemisionDetalle.setUnidad("U");
			guiaRemisionDetalleList.add(guiaRemisionDetalle);
		}
		guiaRemision.setGuiaRemisiondetalle(guiaRemisionDetalleList);
		return guiaRemision;

	}

	private BigDecimal getPromedioUnitarioAnterior(Long idLote) {
		String queryString = // "SELECT movimientoDetalle.promedioUnitario "+
		"from "
				+ "MovimientoDetalleEJB movimientoDetalle "
				+ "where "
				+ "movimientoDetalle.loteId=:idLote and movimientoDetalle.promedioUnitario is not null "
				+ "order by movimientoDetalle.id desc";

		Query query = manager.createQuery(queryString);
		query.setParameter("idLote", idLote);
		try {
			List resultado = query.getResultList();
			MovimientoDetalleIf movimientoDetalleIf = (MovimientoDetalleIf) resultado
					.get(0);
			return movimientoDetalleIf.getPromedioUnitario();
		} catch (Exception e) {
			// e.printStackTrace();
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal nullToZero(BigDecimal param1) {
		if (param1 == null)
			return BigDecimal.ZERO;
		else
			return param1;
	}

	private void actualizarTotales(MovimientoIf movimientoIf,
			List<MovimientoDetalleIf> listaRow) {
		BigDecimal costoTotal = BigDecimal.ZERO;
		BigDecimal precioTotal = BigDecimal.ZERO;
		for (MovimientoDetalleIf movimientoDetalleIf : listaRow) {
			if (movimientoDetalleIf.getCosto() != null)
				costoTotal = costoTotal.add(movimientoDetalleIf.getCosto());
			if (movimientoDetalleIf.getPrecio() != null)
				precioTotal = precioTotal.add(movimientoDetalleIf.getPrecio());
		}
		movimientoIf.setCosto(costoTotal);
		movimientoIf.setPrecio(precioTotal);
	}

	private BigDecimal calcularPromedioUnitario(BigDecimal stockAnterior,
			BigDecimal promedioUnitarioAnterior, BigDecimal cantidad,
			BigDecimal valorUnitario) {
		// (STOCK ANTERIOR*PROMEDIO UNITARIO ANTEIOR)
		// +
		// (CANTIDAD NUEVA * VALOR UNITARIO)
		// /
		// STOCK NUEVO
		promedioUnitarioAnterior = nullToZero(promedioUnitarioAnterior);
		stockAnterior = nullToZero(stockAnterior);
		cantidad = nullToZero(cantidad);
		valorUnitario = nullToZero(valorUnitario);

		BigDecimal var1 = stockAnterior.multiply(promedioUnitarioAnterior);

		BigDecimal var2 = cantidad.multiply(valorUnitario);

		BigDecimal var3 = stockAnterior.add(cantidad);

		BigDecimal var4 = var1.add(var2);

		return var4.divide(var3, RoundingMode.HALF_UP);
	}

	private BigDecimal aplicarSigno(BigDecimal cantidad, String signo)
			throws GenericBusinessException {
		if ("-".equalsIgnoreCase(signo)) {
			return cantidad.negate();
		}
		return cantidad;
	}

	private void calcularCosteo(MovimientoIf movimientoIf,
			MovimientoDetalleIf movimientoDetalle,
			TipoDocumentoIf tipoDocumentoIf) {
		try {
			BigDecimal promedioUnitarioAnterior = getPromedioUnitarioAnterior(movimientoDetalle
					.getLoteId());
			BigDecimal cantidad = aplicarSigno(movimientoDetalle.getCantidad(),
					tipoDocumentoIf.getSignostock());

			StockIf stockActual = stockLocal.getStockMesActual(movimientoIf,
					movimientoDetalle);
			DocumentoIf documentoIf = documentoSessionLocal
					.getDocumento(movimientoDetalle.getDocumentoId());

			if (documentoIf.getCodigo().equalsIgnoreCase("INMC")) {
				// TODO: ESTO TAMBIEN APLICA PARA PRODUCCION PERO NO HAY
				// DOCUMENTO
				// DEFINIDO TODAVIA
				BigDecimal valorUnitario = movimientoDetalle.getValorUnitario();
				BigDecimal promedioActualCalculado = calcularPromedioUnitario(
						stockActual.getCantidad(), promedioUnitarioAnterior,
						cantidad, valorUnitario);

				movimientoDetalle.setPromedioUnitario(promedioActualCalculado);
				movimientoDetalle.setStockValorizado(cantidad
						.multiply(valorUnitario));
			} else {
				movimientoDetalle.setPromedioUnitario(promedioUnitarioAnterior);
				movimientoDetalle.setStockValorizado(cantidad
						.multiply(movimientoDetalle.getPromedioUnitario()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void procesarCompra(CompraIf model,
			List<CompraDetalleIf> modelDetalleList, UsuarioIf usuario)
			throws GenericBusinessException {
		MovimientoEJB movimientoEJB = new MovimientoEJB();
		List<BodegaIf> bodegaList = (List) bodegaSessionLocal
				.findBodegaByOficinaId(model.getOficinaId());
		Long idBodega = null;
		if (bodegaList != null && bodegaList.size() > 0) {
			idBodega = bodegaList.get(0).getId();
		}
		movimientoEJB.setCompraId(model.getId());
		movimientoEJB.setBodegaId(idBodega);
		movimientoEJB.setEstado("A");
		movimientoEJB.setFechaDocumento(utilitariosSessionLocal
				.getTimeStamp(model.getFecha()));
		movimientoEJB.setObservacion("F: " + model.getPreimpreso() + " "
				+ model.getObservacion() + " INM: ");
		movimientoEJB.setTipodocumentoId(queryHelperServerLocal
				.getTipoDocumento("INM").getId());// INGRESO MERCADERIA
		movimientoEJB.setUsuarioId(usuario.getId());
		DocumentoIf documentoIf = queryHelperServerLocal.getDocumento("INMC");// INGRESO
		// POR
		// COMPRAS
		movimientoEJB.setReferenciaId(model.getId());
		movimientoEJB.setTipodocumentoOrigenId(model.getTipodocumentoId());
		List<MovimientoDetalleIf> listaMovimientoDetalle = new ArrayList<MovimientoDetalleIf>();
		ProductoIf producto = null;
		GenericoIf generico = null;
		for (CompraDetalleIf compraDetalleIf : modelDetalleList) {
			MovimientoDetalleEJB movimientoDetalleIf = new MovimientoDetalleEJB();
			producto = (ProductoIf) productoSessionLocal
					.getProducto(compraDetalleIf.getProductoId());
			generico = (GenericoIf) genericoSessionLocal.getGenerico(producto
					.getGenericoId());
			if (generico.getLlevaInventario().equals("S")) {
				movimientoDetalleIf.setCantidad(new BigDecimal(compraDetalleIf
						.getCantidad()));
				movimientoDetalleIf.setDocumentoId(documentoIf.getId());
				List<LoteIf> listaLote = (List) loteSessionLocal
						.findLoteByProductoId(compraDetalleIf.getProductoId());
				if (listaLote != null && listaLote.size() > 0) {
					movimientoDetalleIf.setLoteId(listaLote.get(0).getId());
				}
				movimientoDetalleIf.setPrecio(BigDecimal.ZERO);

				// TODO: Costo y valor unitario deben considerar tambi�n los
				// gastos asociados
				// movimientoDetalleIf.setCosto(compraDetalleIf.getValor());
				// TODO: NOTA: EL IVA SE LO DIVIDE PARA LA CANTIDAD PUESTO QUE
				// EN ESE CAMPO SE
				// HA PUESTO EL IVA TOTAL(INCLUYENDO LAS CANTIDADES), asi
				// tambien el ICE.
				Double valorDetalle = compraDetalleIf.getValor().doubleValue();
				if (generico.getCobraIva().equals("N"))
					valorDetalle += (compraDetalleIf.getIva().doubleValue() / compraDetalleIf
							.getCantidad().doubleValue());
				if (generico.getCobraIce().equals("N"))
					valorDetalle += (compraDetalleIf.getIce().doubleValue() / compraDetalleIf
							.getCantidad().doubleValue());
				valorDetalle += (compraDetalleIf.getOtroImpuesto()
						.doubleValue() / compraDetalleIf.getCantidad()
						.doubleValue())
						- (compraDetalleIf.getDescuento().doubleValue() / compraDetalleIf
								.getCantidad().doubleValue());

				// valorDetalle =
				// utilitariosSessionLocal.redondeoValor(valorDetalle);
				Double totalCostos = 0D;
				Collection<CompraDetalleGastoIf> detalleGastos = compraDetalleGastoLocal
						.findCompraDetalleGastoByCompraDetalleId(compraDetalleIf
								.getId());
				for (CompraDetalleGastoIf cdg : detalleGastos) {
					totalCostos += cdg.getValor().doubleValue()
							/ compraDetalleIf.getCantidad().doubleValue();
				}
				// totalCostos =
				// utilitariosSessionLocal.redondeoValor(totalCostos);

				Double costo = valorDetalle + totalCostos;
				movimientoDetalleIf.setCosto(new BigDecimal(costo));
				movimientoDetalleIf.setValorUnitario(new BigDecimal(costo));

				// movimientoDetalleIf.setValorUnitario(compraDetalleIf.getValor());
				listaMovimientoDetalle.add(movimientoDetalleIf);
			}
		}
		if (listaMovimientoDetalle.size() > 0)
			procesarMovimiento((MovimientoIf) movimientoEJB,
					listaMovimientoDetalle, usuario);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void procesarDevolucion(FacturaIf model,
			List<FacturaDetalleIf> modelDetalleList, UsuarioIf usuario)
			throws GenericBusinessException {
		MovimientoEJB movimientoEJB = new MovimientoEJB();
		movimientoEJB.setBodegaId(model.getBodegaId());
		movimientoEJB.setEstado("A");
		movimientoEJB.setFechaDocumento(utilitariosSessionLocal
				.getTimeStamp(model.getFechaFactura()));
		movimientoEJB.setObservacion("DEV/INM: ");
		movimientoEJB.setTipodocumentoId(queryHelperServerLocal
				.getTipoDocumento("INM").getId());// INGRESO MERCADERIA
		movimientoEJB.setUsuarioId(usuario.getId());
		DocumentoIf documentoIf = queryHelperServerLocal.getDocumento("INGD");// INGRESO
		// DEVOLUCION
		movimientoEJB.setReferenciaId(model.getId());
		movimientoEJB.setTipodocumentoOrigenId(model.getTipodocumentoId());
		List<MovimientoDetalleIf> listaMovimientoDetalle = new ArrayList<MovimientoDetalleIf>();

		for (FacturaDetalleIf facturaDetalleIf : modelDetalleList) {
			ProductoIf producto = productoSessionLocal
					.getProducto(facturaDetalleIf.getProductoId());
			GenericoIf generico = genericoSessionLocal.getGenerico(producto
					.getGenericoId());
			if (generico.getUsaLote().equals("S")) {
				MovimientoDetalleEJB movimientoDetalleIf = new MovimientoDetalleEJB();
				movimientoDetalleIf.setCantidad(facturaDetalleIf
						.getCantidadDevuelta());
				movimientoDetalleIf.setDocumentoId(documentoIf.getId());
				movimientoDetalleIf.setLoteId(facturaDetalleIf.getLoteId());
				movimientoDetalleIf.setPrecio(facturaDetalleIf.getPrecio());
				movimientoDetalleIf.setCosto(facturaDetalleIf.getCosto());
				listaMovimientoDetalle.add(movimientoDetalleIf);
			}
		}

		procesarMovimiento((MovimientoIf) movimientoEJB,
				listaMovimientoDetalle, false, true, usuario);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String procesarMovimiento(MovimientoIf model,
			List<? super MovimientoDetalleIf> modelDetalleList,
			UsuarioIf usuario) throws GenericBusinessException {
		return procesarMovimiento(model, modelDetalleList, true, true, usuario);
	}

	public String procesarMovimiento(MovimientoIf model,
			List<? super MovimientoDetalleIf> modelDetalleList,
			boolean replicar, boolean aprobarAsiento, UsuarioIf usuario)
			throws GenericBusinessException {
		MovimientoIf movimiento = null;
		try {
			model.setId(null);
			model.setFechaCreacion(utilitariosSessionLocal
					.getServerDateTimeStamp());
			model.setUsuarioId(usuario.getId());
			movimiento = addMovimiento(model);
			String codigo = generarCodigo(model.getCodigo(),
					movimiento.getId(), model.getTipodocumentoId());
			movimiento.setCodigo(codigo);
			String codigoTipoDocumento = codigo.substring(11, codigo.length());
			movimiento.setObservacion(codigoTipoDocumento + ": " + codigo + " "
					+ movimiento.getObservacion());
			saveMovimiento(movimiento);
			List<MovimientoDetalleIf> listaMovimientoDetalle = (List<MovimientoDetalleIf>) modelDetalleList;
			List<MovimientoDetalleIf> listaMovimientoDetalleGuardados = new ArrayList<MovimientoDetalleIf>();
			for (MovimientoDetalleIf modelDetalle : listaMovimientoDetalle) {
				modelDetalle.setMovimientoId(movimiento.getId());
				listaMovimientoDetalleGuardados
						.add(movimientoDetalleSessionLocal
								.addMovimientoDetalle(modelDetalle));
			}

			if (movimiento.getEstado().equalsIgnoreCase("A")) {
				movimiento.setEstado("INT");// ESTADO INTERMEDIO PARA APROBAR
				aprobarMovimiento(movimiento, listaMovimientoDetalleGuardados,
						replicar, aprobarAsiento, usuario);
			} else if (movimiento.getEstado().equalsIgnoreCase("R")) {
				aprobarMovimiento(movimiento, listaMovimientoDetalleGuardados,
						replicar, aprobarAsiento, usuario);
			}
			model = movimiento;
			return "" + movimiento.getId();
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(
					"Se ha producido un error al guardar el Movimiento");
		}
	}

	private DecimalFormat formatoSerial4 = new DecimalFormat("0000000000");

	private String getCodigoSerial() {
		String queryString = "Select max(movimiento.id) from MovimientoEJB movimiento";
		Query query = manager.createQuery(queryString);
		Long lastId = null;
		try {
			lastId = (Long) query.getSingleResult();
			return formatoSerial4.format(lastId + 1);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return formatoSerial4.format(0);
	}

	private String generarCodigo(String ingresado, Long idMovimiento,
			Long idTipoDocumento) {
		if (ingresado == null || ingresado.trim().equalsIgnoreCase(""))
			ingresado = formatoSerial4.format(idMovimiento);
		TipoDocumentoIf tipoDocumentoIf = null;
		String tipoDoc = "XXX";
		try {
			tipoDocumentoIf = tipoDocumentoLocal
					.getTipoDocumento(idTipoDocumento);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		if (tipoDocumentoIf != null)
			tipoDoc = tipoDocumentoIf.getCodigo();
		return ingresado + "-" + tipoDoc;
	}

	private String getCodigoIngresado(String cadenaCompuesta) {
		return cadenaCompuesta.split("\\-")[0];
	}

	public List<MovimientoConsultaData> getMovimientosPorAprobar() {

		String queryString = "Select distinct " + "movimiento.codigo,"
				+ "tipodocumento.codigo, " + "tipodocumento.nombre, "
				+ "movimiento.fechaCreacion, " + "bodega.codigo, "
				+ "bodega.nombre, " + "movimiento.bodegarefId, "
				+ "movimiento.estado," + "movimiento.id,  "
				+ "movimiento.observacion  " +

				"from " + "MovimientoEJB movimiento, "
				+ "TipoDocumentoEJB tipodocumento, " + "BodegaEJB bodega " +

				"where " + "bodega.id=movimiento.bodegaId and "
				+ "tipodocumento.id=movimiento.tipodocumentoId and "
				+ "movimiento.estado <> 'A'";

		Query query = manager.createQuery(queryString);
		List listaResultados = query.getResultList();
		Object[] objectmp = null;
		List<MovimientoConsultaData> listaMovimientoConsultaData = new ArrayList<MovimientoConsultaData>();
		Long idBodegaRef = null;
		BodegaIf bodegaRef = null;
		for (int i = 0; i < listaResultados.size(); i++) {
			objectmp = (Object[]) listaResultados.get(i);
			MovimientoConsultaData movimientoConsultaData = new MovimientoConsultaData();
			movimientoConsultaData.setCodigo((String) objectmp[0]);
			movimientoConsultaData.setTipoDocumento((String) objectmp[1]
					+ " - " + (String) objectmp[2]);
			movimientoConsultaData.setFechaIngreso((Timestamp) objectmp[3]);
			movimientoConsultaData.setBodega((String) objectmp[4] + " - "
					+ (String) objectmp[5]);
			idBodegaRef = (Long) objectmp[6];
			if (idBodegaRef != null) {
				try {
					bodegaRef = bodegaSessionLocal.getBodega(idBodegaRef);
					movimientoConsultaData.setBodegaRef(bodegaRef.getCodigo()
							+ " - " + bodegaRef.getNombre());
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					movimientoConsultaData.setBodegaRef("-");
				}
			} else {
				movimientoConsultaData.setBodegaRef("-");
			}
			movimientoConsultaData.setEstado((String) objectmp[7]);
			movimientoConsultaData.setId((Long) objectmp[8]);
			movimientoConsultaData.setObservacion((String) objectmp[9]);
			listaMovimientoConsultaData.add(movimientoConsultaData);
		}

		return listaMovimientoConsultaData;

	}
	
	public void procesarAnulacionFactura(FacturaIf modelFactura, List<FacturaDetalleIf> listaDetalleFactura, UsuarioIf usuario) throws GenericBusinessException {
		MovimientoEJB movimiento = new MovimientoEJB();
		PuntoImpresionIf puntoImpresion = null;
		TipoDocumentoIf tipoDocumentoAjustePositivoInventario = queryHelperServerLocal.getTipoDocumento("AJP");
		if (tipoDocumentoAjustePositivoInventario != null) {
			movimiento.setTipodocumentoId(tipoDocumentoAjustePositivoInventario.getId());
			movimiento.setBodegaId(modelFactura.getBodegaId());
			movimiento.setFechaDocumento(utilitariosSessionLocal.getServerDateTimeStamp());
			movimiento.setUsuarioId(modelFactura.getUsuarioId());
			movimiento.setObservacion("AJP: ");
			movimiento.setEstado("A");
			movimiento.setReferenciaId(modelFactura.getId());
			movimiento.setTipodocumentoOrigenId(modelFactura.getTipodocumentoId());
			if (puntoImpresion != null) {
				movimiento.setPreimpreso(puntoImpresion.getSerie() + "-" + modelFactura.getPreimpresoNumero());
			}
			// DETALLE
			List<MovimientoDetalleIf> listaMovimientoDetalle = new ArrayList<MovimientoDetalleIf>();
			ProductoIf producto;
			GenericoIf generico;
			for (FacturaDetalleIf facturaDetalleIf : listaDetalleFactura) {
				producto = (ProductoIf) productoSessionLocal.getProducto(facturaDetalleIf.getProductoId());
				generico = (GenericoIf) genericoSessionLocal.getGenerico(producto.getGenericoId());
				if (generico.getLlevaInventario().equals("S")) {
					MovimientoDetalleEJB movimientoDetalle = crearMovimientoDetalleAnulacionFactura(facturaDetalleIf);
					listaMovimientoDetalle.add(movimientoDetalle);
				}
			}
			if (listaMovimientoDetalle.size() > 0)
				procesarMovimiento(movimiento, listaMovimientoDetalle, false, true, usuario);
		}
	}

	public void procesarFactura(FacturaIf modelFactura,
			List<FacturaDetalleIf> listaDetalleFactura, UsuarioIf usuario)
			throws GenericBusinessException {
		MovimientoEJB movimiento = new MovimientoEJB();
		PuntoImpresionIf puntoImpresion = null;
		movimiento.setTipodocumentoId(queryHelperServerLocal.getTipoDocumento(
				"EGM").getId());
		movimiento.setBodegaId(modelFactura.getBodegaId());
		movimiento.setFechaDocumento(utilitariosSessionLocal
				.getTimeStamp(modelFactura.getFechaFactura()));
		movimiento.setUsuarioId(modelFactura.getUsuarioId());
		movimiento.setObservacion("EGM: ");
		movimiento.setEstado("A");
		movimiento.setReferenciaId(modelFactura.getId());
		movimiento.setTipodocumentoOrigenId(modelFactura.getTipodocumentoId());
		if (puntoImpresion != null) {
			movimiento.setPreimpreso(puntoImpresion.getSerie() + "-"
					+ modelFactura.getPreimpresoNumero());
		}
		// DETALLE
		List<MovimientoDetalleIf> listaMovimientoDetalle = new ArrayList<MovimientoDetalleIf>();
		ProductoIf producto;
		GenericoIf generico;
		for (FacturaDetalleIf facturaDetalleIf : listaDetalleFactura) {
			producto = (ProductoIf) productoSessionLocal
					.getProducto(facturaDetalleIf.getProductoId());
			generico = (GenericoIf) genericoSessionLocal.getGenerico(producto
					.getGenericoId());
			if (generico.getLlevaInventario().equals("S")) {
				MovimientoDetalleEJB movimientoDetalle = crearMovimientoDetalle(facturaDetalleIf);
				listaMovimientoDetalle.add(movimientoDetalle);
			}
		}

		if (listaMovimientoDetalle.size() > 0)
			procesarMovimiento(movimiento, listaMovimientoDetalle, false, true,
					usuario);
	}

	public void procesarMovimientoVentaGiftcard(CarteraIf reciboCajaGiftcard,
			List<PedidoDetalleIf> detallesGiftcard, UsuarioIf usuario,
			BodegaIf bodega) throws GenericBusinessException {
		MovimientoEJB movimiento = new MovimientoEJB();
		PuntoImpresionIf puntoImpresion = null;
		movimiento.setTipodocumentoId(queryHelperServerLocal.getTipoDocumento(
				"EGM").getId());
		movimiento.setBodegaId(bodega.getId());
		movimiento.setFechaDocumento(utilitariosSessionLocal
				.getTimeStamp(reciboCajaGiftcard.getFechaEmision()));
		movimiento.setUsuarioId(reciboCajaGiftcard.getUsuarioId());
		movimiento.setObservacion("RCG: " + reciboCajaGiftcard.getCodigo());
		movimiento.setEstado("A");
		movimiento.setReferenciaId(reciboCajaGiftcard.getId());
		movimiento.setTipodocumentoOrigenId(reciboCajaGiftcard
				.getTipodocumentoId());
		// DETALLE
		List<MovimientoDetalleIf> listaMovimientoDetalle = new ArrayList<MovimientoDetalleIf>();
		Iterator it = detallesGiftcard.iterator();
		while (it.hasNext()) {
			PedidoDetalleIf pedidoDetalle = (PedidoDetalleIf) it.next();
			ProductoIf producto = (ProductoIf) productoSessionLocal
					.getProducto(pedidoDetalle.getProductoId());
			GenericoIf generico = (GenericoIf) genericoSessionLocal
					.getGenerico(producto.getGenericoId());
			if (generico.getLlevaInventario().equals("S")) {
				MovimientoDetalleEJB movimientoDetalle = crearMovimientoDetalle(pedidoDetalle);
				listaMovimientoDetalle.add(movimientoDetalle);
			}
		}

		if (listaMovimientoDetalle.size() > 0)
			procesarMovimiento(movimiento, listaMovimientoDetalle, false, true,
					usuario);
	}

	@SuppressWarnings("unchecked")
	public List generarKardex(Long idEmpresa, Long idSucursal, Long idBodega,
			Long idProducto, java.util.Date fechaInicio, java.util.Date fechaFin)
			throws GenericBusinessException {
		Query query = null;
		String queryString = "FROM KardexEJB kardex " + "where ";
		String parametrosComunes = "(kardex.empresaId=:idEmpresa) and "
				+ "(kardex.oficinaId=:idOficina OR :idOficina is null) and "
				+ "(kardex.bodegaId=:idBodega OR :idBodega is null) and "
				+ "(kardex.productoId=:idProducto OR :idProducto is null) ";

		String queryKardexActual = " and kardex.fechaMovimiento > :fechaInicial and "
				+ "(kardex.fechaMovimiento <= :fechaFinal) ";
		String queryOrderBy = " order by " + "kardex.empresaId, "
				+ "kardex.oficinaId, " + "kardex.bodegaId, "
				+ "kardex.productoId, " + "kardex.fechaMovimiento";
		query = manager.createQuery(queryString + parametrosComunes
				+ queryKardexActual + " order by kardex.fechaMovimiento");
		query.setParameter("idEmpresa", idEmpresa);
		query.setParameter("idOficina", idSucursal);
		query.setParameter("idBodega", idBodega);
		query.setParameter("idProducto", idProducto);
		query.setParameter("fechaInicial", fechaInicio);
		query.setParameter("fechaFinal", fechaFin);

		List<KardexIf> listaQueryKardex = query.getResultList();
		List<KardexData> listaKardex = new ArrayList<KardexData>();

		HashMap<Long, String> mapaSucursales = new HashMap<Long, String>();
		HashMap<Long, String> mapaBodegas = new HashMap<Long, String>();
		HashMap<Long, String> mapaLote = new HashMap<Long, String>();
		HashMap<Long, String> mapaProductos = new HashMap<Long, String>();

		for (KardexIf kardexTemp : listaQueryKardex) {
			KardexData kardexData = new KardexData();
			kardexData.setMovimiento(kardexTemp.getMovimientoCodigo() + " ["
					+ kardexTemp.getTipodocumentoCodigo() + "] "
					+ kardexTemp.getDocumentoCodigo() + " - "
					+ kardexTemp.getDocumentoDescripcion());
			kardexData.setIdSucursal(kardexTemp.getOficinaId());
			kardexData.setSucursal(kardexTemp.getOficinaCodigo() + " "
					+ (String) kardexTemp.getOficinaNombre());
			kardexData.setIdBodega(kardexTemp.getBodegaId());
			kardexData.setBodega(kardexTemp.getBodegaCodigo() + " "
					+ kardexTemp.getBodegaNombre());

			kardexData.setIdProducto(kardexTemp.getProductoId());
			kardexData.setValor(kardexTemp.getValorUnitario());
			kardexData.setTotal(kardexTemp.getPromedioUnitario());
			kardexData.setDiferencia(kardexTemp.getStockValorizado());
			kardexData.setProducto(queryHelperServerLocal
					.getDescripcionProducto(kardexTemp.getProductoId()));

			kardexData.setIdLote(kardexTemp.getLoteId());
			kardexData.setLote(kardexTemp.getLoteCodigo());
			kardexData.setFecha(kardexTemp.getFechaMovimiento());
			kardexData.setCantidad(setCantidad(
					kardexTemp.getTipodocumentoSignostock(),
					kardexTemp.getCantidadDetalle()));
			kardexData.setIdentificador(1l);

			mapaSucursales.put(kardexData.getIdSucursal(),
					kardexData.getSucursal());
			mapaBodegas.put(kardexData.getIdBodega(), kardexData.getBodega());
			mapaProductos.put(kardexData.getIdProducto(),
					kardexData.getProducto());
			mapaLote.put(kardexData.getIdLote(), kardexData.getLote());
			listaKardex.add(kardexData);
		}

		// SALDO INICIAL

		queryString = "SELECT "
				+ "kardex.empresaId, "
				+ "kardex.oficinaId, "
				+ "kardex.bodegaId, "
				+ "kardex.loteId, "
				+ "kardex.productoId, "
				+ "sum(CONCAT(kardex.tipodocumentoSignostock,1)*kardex.cantidadDetalle) AS kardexAnt,"
				+ "stock.cantidad " + "FROM "
				+ "KardexEJB kardex,StockEJB stock where "
				+ "stock.productoId=kardex.productoId and "
				+ "stock.bodegaId=kardex.bodegaId and " + "stock.mes=:mes and "
				+ "stock.anio=:anio and ";

		String queryKardexAnteriorGroup = "" + "group by kardex.empresaId, "
				+ "kardex.oficinaId, " + "kardex.bodegaId, "
				+ "kardex.loteId, " + "kardex.productoId ";

		query = manager.createQuery(queryString + parametrosComunes
				+ queryKardexActual + queryKardexAnteriorGroup
				+ " order by kardex.fechaMovimiento");

		java.util.Date today = new java.util.Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);

		int anio = cal.get(Calendar.YEAR);
		int mes = cal.get(Calendar.MONTH) + 1;
		String mesStr = String.valueOf(mes);
		if (mesStr.length() < 2) {
			mesStr = "0" + mesStr;
		}

		query.setParameter("idEmpresa", idEmpresa);
		query.setParameter("idOficina", idSucursal);
		query.setParameter("idBodega", idBodega);
		query.setParameter("idProducto", idProducto);
		query.setParameter("fechaInicial", fechaInicio);
		query.setParameter("fechaFinal", today);

		query.setParameter("mes", mesStr);
		query.setParameter("anio", String.valueOf(anio));

		Object[] temp = null;
		List listaQuery = query.getResultList();
		for (int i = 0; i < listaQuery.size(); i++) {
			temp = (Object[]) listaQuery.get(i);
			// DATA
			KardexData kardexData = new KardexData();
			kardexData.setIdSucursal((Long) temp[1]);
			kardexData.setIdBodega((Long) temp[2]);
			kardexData.setIdLote((Long) temp[3]);
			kardexData.setIdProducto((Long) temp[4]);
			kardexData.setMovimiento("SALDO INICIAL");
			kardexData.setFecha(null);
			// kardexData.setSaldoInicial(((BigDecimal)temp[5]).subtract((BigDecimal)temp[4]));
			kardexData.setCantidad(utilitariosSessionLocal
					.redondeoValor(((BigDecimal) temp[6])
							.subtract((BigDecimal) temp[5])));
			kardexData.setIdentificador(0l);
			// kardexData.setStockActual((BigDecimal)temp[5]);
			kardexData.setTotal(getPromedioUnitarioAnterior(kardexData
					.getIdLote()));
			kardexData.setSucursal(mapaSucursales.get(kardexData
					.getIdSucursal()));
			kardexData.setBodega(mapaBodegas.get(kardexData.getIdBodega()));

			kardexData
					.setProducto(mapaProductos.get(kardexData.getIdProducto()));
			kardexData.setLote(mapaLote.get(kardexData.getIdLote()));
			listaKardex.add(kardexData);
		}

		Collections.sort((ArrayList) listaKardex, comparadorMapaNombre);
		BigDecimal difAnterior = BigDecimal.ZERO;
		Long idEmpresaTMP = null, idOficinaTMP = null, idBodegaTMP = null, idProductoTMP = null;
		for (KardexData data : listaKardex) {
			if (data.getDiferencia() == null) {
				difAnterior = data.getCantidad().multiply(data.getTotal());
				data.setDifAnterior(utilitariosSessionLocal
						.redondeoValor(difAnterior));
				continue;
			}
			if (idEmpresaTMP != null && idOficinaTMP != null
					&& idBodegaTMP != null && idProductoTMP != null) {
				if (idOficinaTMP.compareTo(data.getIdSucursal()) != 0
						|| idBodegaTMP.compareTo(data.getIdBodega()) != 0
						|| idProductoTMP.compareTo(data.getIdProducto()) != 0) {
					difAnterior = BigDecimal.ZERO;
					data.setDifAnterior(utilitariosSessionLocal
							.redondeoValor(data.getDiferencia()));
				} else {
					data.setDifAnterior(utilitariosSessionLocal.redondeoValor(difAnterior
							.add(data.getDiferencia())));
					difAnterior = data.getDifAnterior();

				}
			} else {
				data.setDifAnterior(utilitariosSessionLocal
						.redondeoValor(difAnterior.add(data.getDiferencia())));
				difAnterior = data.getDifAnterior();
			}
			idOficinaTMP = data.getIdSucursal();
			idBodegaTMP = data.getIdBodega();
			idProductoTMP = data.getIdProducto();

		}
		Collections.sort((ArrayList) listaKardex, comparadorMapaNombre);

		return listaKardex;
	}

	private Comparator<KardexData> comparadorMapaNombre = new Comparator<KardexData>() {
		public int compare(KardexData o1, KardexData o2) {
			Long idSucursal1 = o1.getIdSucursal();
			Long idSucursal2 = o2.getIdSucursal();

			Long idBodega1 = o1.getIdBodega();
			Long idBodega2 = o2.getIdBodega();

			Long idProducto1 = o1.getIdProducto();
			Long idProducto2 = o2.getIdProducto();

			Long identificador1 = o1.getIdentificador();
			Long identificador2 = o2.getIdentificador();

			return idSucursal1.compareTo(idSucursal2)
					+ idBodega1.compareTo(idBodega2)
					+ idProducto1.compareTo(idProducto2)
					+ identificador1.compareTo(identificador2);
		}
	};

	private BigDecimal setCantidad(String signo, BigDecimal cantidad) {
		if (signo.equalsIgnoreCase("-")) {
			cantidad = cantidad.negate();
		}
		return utilitariosSessionLocal.redondeoValor(cantidad);
	}

	private MovimientoDetalleEJB crearMovimientoDetalle(
			FacturaDetalleIf modelFacturaDetalle)
			throws GenericBusinessException {
		MovimientoDetalleEJB movimientoDetalle = new MovimientoDetalleEJB();
		movimientoDetalle.setDocumentoId(queryHelperServerLocal.getDocumento(
				"EGMV").getId());
		movimientoDetalle.setLoteId(modelFacturaDetalle.getLoteId());
		movimientoDetalle.setCantidad(utilitariosSessionLocal
				.redondeoValor(modelFacturaDetalle.getCantidad()));
		return movimientoDetalle;
	}
	
	private MovimientoDetalleEJB crearMovimientoDetalleAnulacionFactura(
			FacturaDetalleIf modelFacturaDetalle)
			throws GenericBusinessException {
		MovimientoDetalleEJB movimientoDetalle = new MovimientoDetalleEJB();
		movimientoDetalle.setDocumentoId(queryHelperServerLocal.getDocumento(
				"AAF").getId());
		movimientoDetalle.setLoteId(modelFacturaDetalle.getLoteId());
		movimientoDetalle.setCantidad(utilitariosSessionLocal
				.redondeoValor(modelFacturaDetalle.getCantidad()));
		return movimientoDetalle;
	}

	private MovimientoDetalleEJB crearMovimientoDetalle(
			PedidoDetalleIf modelPedidoDetalle) throws GenericBusinessException {
		MovimientoDetalleEJB movimientoDetalle = new MovimientoDetalleEJB();
		movimientoDetalle.setDocumentoId(queryHelperServerLocal.getDocumento(
				"EGMV").getId());
		movimientoDetalle.setLoteId(modelPedidoDetalle.getLoteId());
		movimientoDetalle.setCantidad(utilitariosSessionLocal
				.redondeoValor(modelPedidoDetalle.getCantidad()));
		return movimientoDetalle;
	}

	private void enviarMovimiento(MovimientoIf movimiento,
			List<MovimientoDetalleIf> modelDetalleList,
			TipoDocumentoIf tipoDocumentoIf) throws Exception {

		MovimientoEJB movimientoData = registrarMovimiento(movimiento);
		List<MovimientoDetalleIf> movimientoDetalleList = new ArrayList<MovimientoDetalleIf>();

		movimientoData.setCodigo(movimientoData.getCodigo().split("\\-")[0]);

		Long idDocIngresoTransferencia = null;
		Long idTipoDocTransferencia = null;
		boolean isIngresoTransferencia = false;
		boolean isEgresoTransferencia = false;
		BodegaIf bodega = bodegaSessionLocal
				.getBodega(movimiento.getBodegaId());
		// TODO: LOGICA DE SEND PUEDE IR DENTRO DE LOS MENSAJES
		PosColaIf posColaYO = posColaSessionLocal.obtenerInfoColaYO();

		System.out.println("POS COLA YO: " + posColaYO);

		if (tipoDocumentoIf.getCodigo().equalsIgnoreCase("ETR")) {
			// SI ES EGRESO ENVIAR INGRESO A DESTINO..
			idTipoDocTransferencia = getTipoDocumento("ITR").getId();
			idDocIngresoTransferencia = getDocumento("ITRF").getId();

			isEgresoTransferencia = true;
			movimientoData.setBodegaId(movimiento.getBodegarefId());
			movimientoData.setBodegarefId(movimiento.getBodegaId());
			movimientoData.setTipodocumentoId(idTipoDocTransferencia);
			movimientoData.setEstado("P");

			BodegaIf bodegaRef = bodegaSessionLocal.getBodega(movimiento
					.getBodegarefId());
			if (bodegaRef.getOficinaId().equals(bodega.getOficinaId())) {
				// TRANSFERENCIA LOCAL

				movimientoData.setId(null);
				movimientoData.setEstado("R");
				for (MovimientoDetalleIf modelDetalle : modelDetalleList) {
					MovimientoDetalleIf movimientoDetalle = registrarMovimientoDetalle(modelDetalle);
					movimientoDetalleList.add(movimientoDetalle);
				}
				procesarMovimiento(movimientoData, movimientoDetalleList,
						false, true, usuarioSessionLocal.getUsuario(movimiento
								.getUsuarioId()));
				masterDetailObjectMessageLocal.clear();
				return;
			}

		} else if (tipoDocumentoIf.getCodigo().equalsIgnoreCase("ITR")
				&& posColaYO.getTipoServer().equalsIgnoreCase("E")) {
			// SI ES INGRESO Y SOY MATRIZ GENERAR EGRESO DE LA BODEGA CAMBIADA Y
			// NO ENVIAR
			idTipoDocTransferencia = getTipoDocumento("ITR").getId();
			idDocIngresoTransferencia = getDocumento("ITRF").getId();
			movimientoData.setTipodocumentoId(idTipoDocTransferencia);
			movimientoData.setEstado("R");
			isIngresoTransferencia = true;
		} else if (tipoDocumentoIf.getCodigo().equalsIgnoreCase("ITR")
				&& posColaYO.getTipoServer().equalsIgnoreCase("P")) {
			// SI ES INGRESO Y SOY MATRIZ GENERAR EGRESO DE LA BODEGA CAMBIADA Y
			// NO ENVIAR
			idTipoDocTransferencia = getTipoDocumento("ETR").getId();
			idDocIngresoTransferencia = getDocumento("ETRF").getId();
			movimientoData.setTipodocumentoId(idTipoDocTransferencia);
			movimientoData.setBodegaId(movimiento.getBodegarefId());
			movimientoData.setBodegarefId(movimiento.getBodegaId());
			movimientoData.setEstado("R");
			isIngresoTransferencia = true;
		}

		else {
			// ENVIAR SI ES NECESARIO
			movimientoData.setEstado("A");
		}

		for (MovimientoDetalleIf modelDetalle : modelDetalleList) {
			MovimientoDetalleIf movimientoDetalle = registrarMovimientoDetalle(modelDetalle);
			if (isIngresoTransferencia || isEgresoTransferencia) {
				movimientoDetalle.setDocumentoId(idDocIngresoTransferencia);
			}
			movimientoDetalleList.add(movimientoDetalle);
			masterDetailObjectMessageLocal.addDetail(movimientoDetalle);
		}

		masterDetailObjectMessageLocal.setCabecera(movimientoData,
				"movimientoId");

		// SI NO SOY PRINCIPAL
		if (!posColaYO.getTipoServer().equalsIgnoreCase("P")) {
			// SI SOY POS ENVIAR A CENTRAL Y REPROCESAR..
			masterDetailObjectMessageLocal.sendToPrincipal();
			masterDetailObjectMessageLocal.clear();
			return;
		} else {
			// SI SOY CENTRAL Y ES TRANSFERENCIA -> NO ENVIAR A CENTRAL, SOLO AL
			// POS
			BodegaIf bodegaDestino = null;
			if (isEgresoTransferencia) {

				bodegaDestino = bodegaSessionLocal.getBodega(movimientoData
						.getBodegaId());
				masterDetailObjectMessageLocal.sendToPos(bodegaDestino
						.getOficinaId());
				masterDetailObjectMessageLocal.clear();
				return;
			}
			if (isIngresoTransferencia) {
				movimientoData.setId(null);
				procesarMovimiento(movimientoData, movimientoDetalleList,
						false, true, usuarioSessionLocal.getUsuario(movimiento
								.getUsuarioId()));
				masterDetailObjectMessageLocal.clear();
				return;
			}
			if (!bodega.getOficinaId().equals(posColaYO.getOficinaId())) {
				// SI ES OTRA COSA PARA OTRA OFICINA ENVIO???
				// masterDetailObjectMessageLocal.sendToPos(bodega
				// .getOficinaId());
				masterDetailObjectMessageLocal.clear();
				return;
			}
		}
		masterDetailObjectMessageLocal.clear();
	}

	private DocumentoIf getDocumento(String codigo)
			throws GenericBusinessException {
		List c = (List) documentoSessionLocal.findDocumentoByCodigo(codigo);
		if (c != null && c.size() > 0)
			return (DocumentoIf) c.get(0);
		else
			return null;
	}

	private TipoDocumentoIf getTipoDocumento(String codigo)
			throws GenericBusinessException {
		List c = (List) tipoDocumentoLocal.findTipoDocumentoByCodigo(codigo);
		if (c != null && c.size() > 0)
			return (TipoDocumentoIf) c.get(0);
		else
			return null;
	}

	public void aprobarMovimiento(MovimientoIf movimiento,
			List<MovimientoDetalleIf> modelDetalleList, UsuarioIf usuario)
			throws GenericBusinessException {
		aprobarMovimiento(movimiento, modelDetalleList, true, true, usuario);
	}

	private void aprobarMovimiento(MovimientoIf movimientoModel,
			List<MovimientoDetalleIf> modelDetalleList, boolean replicar,
			boolean generarAsiento, UsuarioIf usuario)
			throws GenericBusinessException {
		if (!movimientoModel.getEstado().equalsIgnoreCase("A")) {
			Vector<MovimientoDetalleIf> movimientoDetalleList = new Vector<MovimientoDetalleIf>();
			TipoDocumentoIf tipoDocumentoIf = tipoDocumentoLocal
					.getTipoDocumento(movimientoModel.getTipodocumentoId());
			String estadoAnterior = movimientoModel.getEstado();
			if (movimientoModel.getFechaDocumento() == null) {
				movimientoModel.setFechaDocumento(utilitariosSessionLocal
						.getServerDateTimeStamp());
			}
			saveMovimiento(movimientoModel);
			for (MovimientoDetalleIf modelDetalle : modelDetalleList) {
				calcularCosteo(movimientoModel, modelDetalle, tipoDocumentoIf);
				actualizarStock(movimientoModel, modelDetalle);
				movimientoDetalleSessionLocal
						.saveMovimientoDetalle(modelDetalle);
				movimientoDetalleList.add(modelDetalle);
			}
			movimientoModel.setEstado("A");
			saveMovimiento(movimientoModel);
			MovimientoEJB movimiento = registrarMovimiento(movimientoModel);
			if (generarAsiento) {
				AsientoIf asiento = generarAsientoMovimiento(movimiento,
						movimientoDetalleList, null, false, usuario);
			}
			// PARA EVITAR QUE SE REENVIE EL MENSAJE INFINITO
			if (estadoAnterior.equalsIgnoreCase("R")) {
				return;
			}

			if (!replicar)
				return;

			try {
				enviarMovimiento(movimientoModel, modelDetalleList,
						tipoDocumentoIf);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void generarEgresoTransferencia(MovimientoIf movimientoIf,
			List<MovimientoDetalleIf> listaMovimientoDetalle) {
		MovimientoData movimientoData = new MovimientoData();
		movimientoData.setCodigo(movimientoIf.getCodigo());

	}

	private AsientoIf generarAsientoMovimiento(MovimientoEJB movimiento,
			Vector<MovimientoDetalleIf> movimientoDetalleColeccion,
			MovimientoIf movimientoAnterior, boolean actualizar,
			UsuarioIf usuario) throws GenericBusinessException {
		AsientoIf asiento = null;
		movimientoAsientoAutomaticoHandlerLocal
				.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
		for (int i = 0; i < movimientoDetalleColeccion.size(); i++) {
			MovimientoDetalleIf movimientoDetalle = (MovimientoDetalleIf) movimientoDetalleColeccion
					.get(i);
			if (i != movimientoDetalleColeccion.size() - 1)
				asiento = movimientoAsientoAutomaticoHandlerLocal
						.generarAsientoAutomatico(movimiento,
								movimientoDetalle, movimientoAnterior, false,
								actualizar, usuario);
			else if (i == movimientoDetalleColeccion.size() - 1)
				asiento = movimientoAsientoAutomaticoHandlerLocal
						.generarAsientoAutomatico(movimiento,
								movimientoDetalle, movimientoAnterior, true,
								actualizar, usuario);
		}
		return asiento;
	}

	public void procesarFixAsientoCostoVenta(Long idEmpresa, UsuarioIf usuario) {
		try {
			Map costosMap = mapearCostosPorLoteProducto();
			Iterator asientosIt = asientoSessionLocal
					.findAsientoCostoVentaByEmpresaId(idEmpresa).iterator();
			while (asientosIt.hasNext()) {
				AsientoIf asiento = (AsientoIf) asientosIt.next();
				Map parameterMap = new HashMap();
				parameterMap.put("tipodocumentoId",
						asiento.getTipoDocumentoId());
				parameterMap.put("id", asiento.getTransaccionId());
				MovimientoIf movimientoAnterior = (MovimientoIf) findMovimientoByQuery(
						parameterMap).iterator().next();
				System.out.println(movimientoAnterior.getId() + " ///");
				MovimientoEJB movimiento = (MovimientoEJB) DeepCopy
						.copy(movimientoAnterior);
				Vector<MovimientoDetalleIf> movimientoDetalleVector = new Vector<MovimientoDetalleIf>();
				Iterator it = movimientoDetalleSessionLocal
						.findMovimientoDetalleByMovimientoId(
								movimientoAnterior.getId()).iterator();
				while (it.hasNext()) {
					MovimientoDetalleIf movimientoDetalle = (MovimientoDetalleIf) it
							.next();
					BigDecimal costo = (BigDecimal) costosMap
							.get(movimientoDetalle.getLoteId());
					movimientoDetalle.setCosto(costo);
					movimientoDetalleVector.add(movimientoDetalle);
				}
				fixAsientoCostoVenta(movimiento, movimientoDetalleVector,
						movimientoAnterior, usuario);
				System.out.println(movimientoAnterior.getId() + " /// OK");
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private Map mapearCostosPorLoteProducto() {
		Map costosMap = new HashMap();
		Iterator it = ((ArrayList) findCostosPorLoteProducto()).iterator();
		while (it.hasNext()) {
			Object[] o = (Object[]) it.next();
			LoteIf lote = (LoteIf) o[0];
			MovimientoDetalleIf movimientoDetalle = (MovimientoDetalleIf) o[1];
			costosMap
					.put(lote.getId(), movimientoDetalle.getPromedioUnitario());
		}

		return costosMap;
	}

	private Collection findCostosPorLoteProducto() {
		// select distinct g.NOMBRE_GENERICO, m.NOMBRE, pr.NOMBRE, c.NOMBRE,
		// movdet.PROMEDIO_UNITARIO
		// from MOVIMIENTO_DETALLE movdet, LOTE l, GENERICO g, PRODUCTO p,
		// PRESENTACION pr, MODELO m, COLOR c where movdet.ID in (select
		// distinct md.ID from MOVIMIENTO_DETALLE md, DOCUMENTO d where
		// md.PROMEDIO_UNITARIO is not null and md.DOCUMENTO_ID = d.ID and
		// d.CODIGO = 'INMC' order by md.ID desc) and g.ID = p.GENERICO_ID and
		// p.PRESENTACION_ID = pr.ID and p.MODELO_ID = m.ID and p.COLOR_ID =
		// c.ID and movdet.LOTE_ID = l.ID and l.PRODUCTO_ID = p.ID

		String queryString = "select distinct l, movdet from MovimientoDetalleEJB movdet, LoteEJB l where movdet.id in (select distinct md.id from MovimientoDetalleEJB md, DocumentoEJB d where md.promedioUnitario is not null and md.documentoId = d.id and d.codigo = 'INMC' order by md.id desc) and movdet.loteId = l.id";
		// Add a an order by on all primary keys to assure reproducable results.
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	private void fixAsientoCostoVenta(MovimientoEJB movimiento,
			Vector<MovimientoDetalleIf> movimientoDetalleColeccion,
			MovimientoIf movimientoAnterior, UsuarioIf usuario)
			throws GenericBusinessException {
		generarAsientoMovimiento(movimiento, movimientoDetalleColeccion,
				movimientoAnterior, true, usuario);
	}

	private void actualizarStock(MovimientoIf movimiento,
			MovimientoDetalleIf movimientoDetalle)
			throws GenericBusinessException {
		List<StockIf> stockList = stockLocal.afectarStock(movimiento,
				movimientoDetalle);
		System.out.println("----------------LISTA A AFECTAR: "
				+ stockList.size());
		for (StockIf stock : stockList) {
			stockLocal.saveStock(stock);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarMovimiento(MovimientoIf model,
			List<MovimientoDetalleIf> modelDetalleList,
			List<MovimientoDetalleIf> modelDetalleEliminados)
			throws GenericBusinessException {
		try {
			MovimientoIf movimientoAnterior = getMovimiento(model.getId());
			// COMPARAR FECHAS
			Date fechaAnterior = movimientoAnterior.getFechaDocumento();
			Date fechaNueva = model.getFechaDocumento();

			System.out.println("Fecha anterior: " + fechaAnterior);
			System.out.println("Fecha nueva: " + fechaNueva);

			String mesAnterior = utilitariosSessionLocal
					.getMonthFromDate(fechaAnterior);
			String anioAnterior = utilitariosSessionLocal
					.getYearFromDate(fechaAnterior);

			String mesNuevo = utilitariosSessionLocal
					.getMonthFromDate(fechaNueva);
			String anioNuevo = utilitariosSessionLocal
					.getYearFromDate(fechaNueva);
			TipoDocumentoIf tipoDocumentoIf = tipoDocumentoLocal
					.getTipoDocumento(movimientoAnterior.getTipodocumentoId());
			String signoCambiado = tipoDocumentoIf.getSignostock()
					.equalsIgnoreCase("+") ? "-" : "+";

			modelDetalleList = (List<MovimientoDetalleIf>) movimientoDetalleSessionLocal
					.findMovimientoDetalleByMovimientoId(movimientoAnterior
							.getId());
			List<MovimientoDetalleIf> movimientoDetalleList = new ArrayList<MovimientoDetalleIf>();

			for (MovimientoDetalleIf md : modelDetalleList) {
				MovimientoDetalleIf modelDetalle = new MovimientoDetalleEJB(md);
				modelDetalle.setId(null);
				List<StockIf> stocksAnteriores = stockLocal
						.getStockActualyMesesSiguientes(
								modelDetalle.getLoteId(),
								movimientoAnterior.getBodegaId(), mesAnterior,
								anioAnterior);
				for (StockIf stockIf : stocksAnteriores) {
					stockLocal.aplicarAfectacionSaldo(stockIf, signoCambiado,
							modelDetalle.getCantidad());
					stockLocal.saveStock(stockIf);
					System.out.println("AFECTADO: "
							+ stockIf.getProductoId()
							+ " "
							+ signoCambiado
							+ " "
							+ queryHelperServerLocal
									.getDescripcionProducto(stockIf
											.getProductoId()) + " "
							+ modelDetalle.getCantidad() + " mes:"
							+ stockIf.getMes() + " " + stockIf.getAnio());
				}

				movimientoDetalleList.add(modelDetalle);
			}
			System.out.println("ID " + movimientoAnterior.getId());
			UsuarioIf usuarioIf = usuarioSessionLocal
					.getUsuario(movimientoAnterior.getUsuarioId());
			HashMap<String, Object> paramAsiento = new HashMap<String, Object>();
			paramAsiento.put("tipoDocumentoId", tipoDocumentoIf.getId());
			paramAsiento.put("transaccionId", movimientoAnterior.getId());
			Iterator asientosIt = asientoSessionLocal.findAsientoByQuery(
					paramAsiento).iterator();

			eliminarMovimiento(movimientoAnterior);
			String codigoAnterior = model.getCodigo();
			model.setCodigo(codigoAnterior.substring(0,
					codigoAnterior.indexOf("-")));

			String id = procesarMovimiento(model, movimientoDetalleList, false,
					false, usuarioIf);

			System.out.println("NUEVO ID " + model.getPrimaryKey() + " " + id);
			AsientoIf asientoIf = null;
			if (asientosIt.hasNext()) {
				asientoIf = (AsientoIf) asientosIt.next();
				asientoIf.setTransaccionId(new Long(id));
				asientoSessionLocal.saveAsiento(asientoIf);
			}

			Vector<MovimientoDetalleIf> movimientoDetalleVector = new Vector<MovimientoDetalleIf>();
			Iterator movimientoDetalleIterator = movimientoDetalleSessionLocal
					.findMovimientoDetalleByMovimientoId(Long.parseLong(id))
					.iterator();
			while (movimientoDetalleIterator.hasNext()) {
				MovimientoDetalleIf movimientoDetalle = (MovimientoDetalleIf) movimientoDetalleIterator
						.next();
				movimientoDetalleVector.add(movimientoDetalle);
			}

			generarAsientoMovimiento(
					(MovimientoEJB) getMovimiento(Long.parseLong(id)),
					movimientoDetalleVector, movimientoAnterior, true,
					usuarioIf);

		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(
					"Se ha producido un error al actualizar el Movimiento");
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean eliminarMovimiento(MovimientoIf model)
			throws GenericBusinessException {

		try {
			List<MovimientoDetalleIf> listaMovimientoDetalleIf = (List<MovimientoDetalleIf>) movimientoDetalleSessionLocal
					.findMovimientoDetalleByMovimientoId(model.getId());
			for (MovimientoDetalleIf movimientoDetalleIf : listaMovimientoDetalleIf) {
				movimientoDetalleSessionLocal
						.deleteMovimientoDetalle(movimientoDetalleIf.getId());
			}
			deleteMovimiento(model.getId());
			return true;
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(
					"Se ha producido un error al eliminar el Movimiento");
		}

	}

	private MovimientoEJB registrarMovimiento(MovimientoIf model)
			throws GenericBusinessException {
		MovimientoEJB movimiento = new MovimientoEJB();
		movimiento.setCodigo(model.getCodigo());
		movimiento.setBodegaId(model.getBodegaId());
		movimiento.setBodegarefId(model.getBodegarefId());
		movimiento.setCompraId(model.getId());
		movimiento.setCosto(model.getCosto());
		movimiento.setEstado(model.getEstado());
		movimiento.setId(model.getId());
		movimiento.setFechaCreacion(utilitariosSessionLocal
				.getServerDateTimeStamp());
		movimiento.setFechaDocumento(model.getFechaDocumento());
		movimiento.setObservacion(model.getObservacion());
		movimiento.setOrdencompraId(model.getOrdencompraId());
		movimiento.setPrecio(model.getPrecio());
		movimiento.setPreimpreso(model.getPreimpreso());
		movimiento.setReferenciaId(model.getReferenciaId());
		movimiento.setTipodocumentoId(model.getTipodocumentoId());
		// movimiento.setUsuarioautId(model.getUsuarioautId());
		movimiento.setUsuarioId(model.getUsuarioId());

		return movimiento;
	}

	private MovimientoDetalleIf registrarMovimientoDetalle(
			MovimientoDetalleIf modelDetalle) {
		MovimientoDetalleEJB movimientoDetalle = new MovimientoDetalleEJB();
		movimientoDetalle.setCantidad(modelDetalle.getCantidad());
		movimientoDetalle.setCosto(modelDetalle.getCosto());
		movimientoDetalle.setDocumentoId(modelDetalle.getDocumentoId());
		movimientoDetalle.setLoteId(modelDetalle.getLoteId());
		movimientoDetalle.setPrecio(modelDetalle.getPrecio());
		return movimientoDetalle;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getMovimientoList(int startIndex, int endIndex, Map aMap) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}

		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from MovimientoEJB " + objectName + " where "
				+ where;

		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.codigo,e.fechaDocumento";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getMovimientoListSize(Map aMap) {

		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select count(*) from MovimientoEJB " + objectName
				+ " where " + where;

		Query countQuery = manager.createQuery(queryString);
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			countQuery.setParameter(propertyKey, property);
		}

		List countQueryResult = countQuery.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}

	public void procesarTransferenciaTarjetaAfiliacionBodegaVirtual(
			OficinaIf sucursal, UsuarioIf usuario)
			throws GenericBusinessException {
		swsl.procesarEgresoTransferenciaTarjetaAfiliacion(sucursal.getId(),
				sucursal.getEmpresaId(), usuario.getId());
		MovimientoIf movimientoIngresoTransferenciaLocal = getMovimientoTransferenciaTarjetaAfiliacion(
				sucursal.getId(), sucursal.getEmpresaId(), usuario.getId(), "I");
		List<MovimientoDetalleIf> movimientoDetalleIngresoTransferenciaLocal = getMovimientoDetalleTransferenciaTarjetaAfiliacion(
				sucursal.getId(), sucursal.getEmpresaId(), "I");
		procesarMovimiento(movimientoIngresoTransferenciaLocal,
				movimientoDetalleIngresoTransferenciaLocal, false, false,
				usuario);
	}

	public MovimientoIf getMovimientoTransferenciaTarjetaAfiliacion(
			Long oficinaId, Long empresaId, Long usuarioId,
			String tipoTransferencia) {
		MovimientoIf movimiento = new MovimientoData();
		java.sql.Timestamp fechaDocumento = utilitariosSessionLocal
				.getServerDateTimeStamp();
		movimiento.setFechaDocumento(fechaDocumento);
		Map parameterMap = new HashMap();
		parameterMap.put("codigo", (tipoTransferencia.equals("E")) ? "ETR"
				: "ITR");
		parameterMap.put("empresaId", empresaId);
		try {
			Iterator it = tipoDocumentoLocal.findTipoDocumentoByQuery(
					parameterMap).iterator();
			if (it.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) it.next();
				movimiento.setTipodocumentoId(tipoDocumento.getId());
				parameterMap.clear();
				parameterMap.put("codigo", "VI");
				parameterMap.put("empresaId", empresaId);
				it = funcionBodegaLocal.findFuncionBodegaByQuery(parameterMap)
						.iterator();
				if (it.hasNext()) {
					FuncionBodegaIf funcionBodega = (FuncionBodegaIf) it.next();
					it = bodegaSessionLocal.findBodegaByFuncionBodegaId(
							funcionBodega.getId()).iterator();
					if (it.hasNext()) {
						BodegaIf bodegaVirtual = (BodegaIf) it.next();
						if (tipoTransferencia.equals("E"))
							movimiento.setBodegaId(bodegaVirtual.getId());
						else
							movimiento.setBodegarefId(bodegaVirtual.getId());
					}
				}
				it = bodegaSessionLocal.findBodegaByOficinaId(oficinaId)
						.iterator();
				if (it.hasNext()) {
					BodegaIf bodegaReferencia = (BodegaIf) it.next();
					if (tipoTransferencia.equals("E"))
						movimiento.setBodegarefId(bodegaReferencia.getId());
					else
						movimiento.setBodegaId(bodegaReferencia.getId());
				}
				movimiento.setUsuarioId(usuarioId);
				movimiento
						.setObservacion("TRANSFERENCIA DE TARJETA AFILIACION DESDE BODEGA VIRTUAL");
				movimiento.setEstado("A");

			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return movimiento;
	}

	public List<MovimientoDetalleIf> getMovimientoDetalleTransferenciaTarjetaAfiliacion(
			Long oficinaId, Long empresaId, String tipoTransferencia) {
		List<MovimientoDetalleIf> movimientoDetalleList = new ArrayList<MovimientoDetalleIf>();
		MovimientoDetalleIf movimientoDetalleIf = null;
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("codigo", "TA");
			parameterMap.put("empresaId", empresaId);
			Iterator it = tipoProductoSessionLocal.findTipoProductoByQuery(
					parameterMap).iterator();
			if (it.hasNext()) {
				TipoProductoIf tipoProducto = (TipoProductoIf) it.next();
				it = genericoSessionLocal.findGenericoByTipoproductoId(
						tipoProducto.getId()).iterator();
				if (it.hasNext()) {
					GenericoIf generico = (GenericoIf) it.next();
					it = productoSessionLocal.findProductoByGenericoId(
							generico.getId()).iterator();
					if (it.hasNext()) {
						ProductoIf producto = (ProductoIf) it.next();
						it = loteSessionLocal.findLoteByProductoId(
								producto.getId()).iterator();
						if (it.hasNext()) {
							LoteIf lote = (LoteIf) it.next();
							movimientoDetalleIf = new MovimientoDetalleData();
							movimientoDetalleIf.setCantidad(BigDecimal.ONE);
							movimientoDetalleIf.setLoteId(lote.getId());
							parameterMap = new HashMap();
							parameterMap.put("codigo", (tipoTransferencia
									.equals("E")) ? "EGMT" : "INMT");
							it = documentoSessionLocal
									.findDocumentoByQueryAndEmpresaId(
											parameterMap, empresaId).iterator();
							if (it.hasNext()) {
								DocumentoIf documento = (DocumentoIf) it.next();
								movimientoDetalleIf.setDocumentoId(documento
										.getId());
								movimientoDetalleList.add(movimientoDetalleIf);
							}
						}
					}
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return movimientoDetalleList;
	}

	public StockIf getStockQuery(EntityManager manager, Long idLote,
			Long idProducto, Long idBodega, String mesStock, String anioStock) {
		String queryString = "from StockEJB e where e.loteId = " + idLote
				+ " and e.bodegaId = " + idBodega + " and e.mes = '" + mesStock
				+ "' and e.anio = '" + anioStock + "' and e.productoId = " + idProducto;
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		List<StockIf> listaStock = query.getResultList();
		return (listaStock.size() > 0) ? listaStock.get(0) : null;
	}

	private DecimalFormat formatoMes = new DecimalFormat("00");
	
	public void recalcularStockPorCierre(Long idBodega) {
		String queryAnioString = "select max(anio) from StockEJB where (bodegaId=:bodegaId or :bodegaId is null)";
		Query query = manager.createQuery(queryAnioString);
		query.setParameter("bodegaId", idBodega);
		System.out.println(idBodega);
		String anio = (String) query.getSingleResult();
		String queryMesString = "select max(mes) from StockEJB where anio = '" + anio + "' and (bodegaId=:bodegaId or :bodegaId is null)";
		query = manager.createQuery(queryMesString);
		query.setParameter("bodegaId", idBodega);
		String mes = (String) query.getSingleResult();
		System.out.println("EL MES---------> " + mes);
		System.out.println("EL ANIO---------> " + anio);
		if (mes.length() == 1)
			mes = "0" + mes;
		recalcularStock(anio, mes, mes);
		String queryUpdateStatus = "update StockEJB set estado = 'A' where anio=:anio and bodegaId=:bodegaId and mes=:mes";
		query = manager.createQuery(queryUpdateStatus);
		query.setParameter("bodegaId", idBodega);
		query.setParameter("anio", anio);
		query.setParameter("mes", mes);
		query.executeUpdate();
	}

	public void recalcularStock(String anioQuery, String mesInicial, String mesLimiteStr) {
		String queryString = "SELECT "
				+ "M.bodegaId, "
				+ "L.productoId, "
				+ "MD.loteId, "
				+ "YEAR(M.fechaDocumento) AS anio, "
				+ "MONTH(M.fechaDocumento) AS mes, "
				+ "SUM(CONCAT(T.signostock,1)*MD.cantidad) AS CANTIDAD, "
				+ "M.fechaDocumento "
				+ "FROM "
				+ "MovimientoDetalleEJB MD, DocumentoEJB D, TipoDocumentoEJB T,MovimientoEJB M,LoteEJB L "
				+ "WHERE "
				+ "MD.documentoId=D.id AND M.tipodocumentoId=T.id AND M.id=MD.movimientoId AND MD.loteId=L.id "
				+ "AND YEAR(M.fechaDocumento)=:anio AND MONTH(M.fechaDocumento) =:mes AND M.estado='A' "
				+ "GROUP BY M.bodegaId,L.productoId,MD.loteId,YEAR(M.fechaDocumento),MONTH(M.fechaDocumento) "
				+ "ORDER BY M.bodegaId,YEAR(M.fechaDocumento),MONTH(M.fechaDocumento),L.productoId,MD.loteId";

		int mesLimite = Integer.valueOf(mesLimiteStr);
		// ELIMINAR REGISTROS DE STOCK SUPERIORES AL MES INICIAL
		EntityManager manager = jpaManagerLocal.getManager();
		String queryStock = "SELECT s FROM StockEJB s where s.anio=:anio and s.mes = :mes";
		HashMap<String, Object> parametroStock = new HashMap<String, Object>();
		parametroStock.put("anio", anioQuery);
		parametroStock.put("mes", mesInicial);
		List<StockEJB> stockList = jpaManagerLocal.executeQueryList("SELECT s FROM StockEJB s where s.anio=:anio and s.mes >= :mes",parametroStock);
		for (StockEJB stockEJB : stockList) {
			manager.remove(stockEJB);
		}
		manager.flush();
		manager.clear();

		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("anio", Integer.valueOf(anioQuery));
		Calendar cal = Calendar.getInstance();
		for (int i = Integer.valueOf(mesInicial); i <= mesLimite; i++) {
			parametros.put("mes", i);
			List<Object[]> lista = jpaManagerLocal.executeQueryList(queryString, parametros);
			String mesAnterior="";
			String anioAnterior ="";
			// RECONSTRUIR STOCK CONSIDERANDO MES ANTERIOR
			if (lista.size() > 0) {
				for (Object[] o : lista) {
					Long bodegaId = (Long) o[0];
					Long productoId = (Long) o[1];
					Long loteId = (Long) o[2];
					BigDecimal cantidad = (BigDecimal) o[5];
					Date fechaMovimiento = (Date) o[6];
					String mes = utilitariosSessionLocal.getMonthFromDate(fechaMovimiento);
					String anio = utilitariosSessionLocal.getYearFromDate(fechaMovimiento);
					cal.setTime(fechaMovimiento);
					cal.add(Calendar.MONTH, -1);
					mesAnterior = utilitariosSessionLocal.getMonthFromDate(cal.getTime());
					anioAnterior = utilitariosSessionLocal.getYearFromDate(cal.getTime());
					System.out.println("---BODEGA: " + bodegaId);
					System.out.println("---ANIO: " + anio);
					System.out.println("---MES: " + mes);
					System.out.println("---LOTE_ID: " + loteId);
					System.out.println("---CANTIDAD: " + cantidad);
					StockIf stockAnterior = getStockQuery(manager, loteId, productoId, bodegaId, mesAnterior, anioAnterior);
					StockEJB stockEJB = new StockEJB();
					stockEJB.setAnio(anio);
					stockEJB.setProductoId(productoId);
					stockEJB.setBodegaId(bodegaId);
					stockEJB.setCantidad(stockAnterior != null ? cantidad.add(stockAnterior.getCantidad()) : cantidad);
					stockEJB.setEstado("C");
					stockEJB.setFhUtlModificacion(utilitariosSessionLocal.getServerDateTimeStamp());
					stockEJB.setLoteId(loteId);
					stockEJB.setMes(mes);
					stockEJB.setReserva(BigDecimal.ZERO);
					stockEJB.setTransito(BigDecimal.ZERO);
					manager.persist(stockEJB);
				}
			} else {
				if (i == 1) {
					mesAnterior = "12";
					anioAnterior = String.valueOf(Integer.parseInt(anioQuery) - 1);
				} else {
					mesAnterior = String.valueOf(i - 1);
					if (mesAnterior.length() == 1)
						mesAnterior = "0" + mesAnterior;
					anioAnterior = anioQuery;
				}
			}
			manager.flush();
			manager.clear();
			parametroStock.put("mes", mesAnterior);
			parametroStock.put("anio", anioAnterior);
			stockList = jpaManagerLocal.executeQueryList(queryStock, parametroStock);
			for (StockEJB stockAnterior : stockList) {
				cal.set(Calendar.MONTH, Integer.parseInt(stockAnterior.getMes()) - 1);
				cal.set(Calendar.YEAR, Integer.parseInt(stockAnterior.getAnio()));
				System.out.println(cal.getTime());
				cal.add(Calendar.MONTH, +1);
				String mesSiguiente = utilitariosSessionLocal.getMonthFromDate(cal.getTime());
				String anioSiguiente = utilitariosSessionLocal.getYearFromDate(cal.getTime());
				StockIf stockSiguiente = getStockQuery(manager, stockAnterior.getLoteId(),
						stockAnterior.getProductoId(), stockAnterior.getBodegaId(), mesSiguiente, anioSiguiente);
				if (stockSiguiente == null) {
					StockEJB stockEJB = new StockEJB();
					stockEJB.setAnio(anioSiguiente);
					stockEJB.setMes(mesSiguiente);
					stockEJB.setBodegaId(stockAnterior.getBodegaId());
					stockEJB.setCantidad(stockAnterior.getCantidad());
					stockEJB.setLoteId(stockAnterior.getLoteId());
					stockEJB.setProductoId(stockAnterior.getProductoId());
					stockEJB.setReserva(stockAnterior.getReserva());
					stockEJB.setTransito(stockAnterior.getTransito());
					stockEJB.setFhUtlModificacion(utilitariosSessionLocal.getServerDateTimeStamp());
					stockEJB.setEstado("C");
					stockEJB.setReserva(BigDecimal.ZERO);
					stockEJB.setTransito(BigDecimal.ZERO);
					manager.persist(stockEJB);
					manager.flush();
					manager.clear();
				}
			}
		}
	}

	public void fixStock() {
		// DETERMINAR CUALES TIENEN PROBLEMAS
		String querySaldoInicialDifCero = "";
		String querySumaKardex = "";
		String queryStock = "SELECT s FROM StockEJB s where s.anio=:anio and s.mes = :mes and bodegaId:bodegaId and productoId:productoId";
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		List<Object[]> listaQuerySaldoInicialDifCero = jpaManagerLocal.executeQueryList(querySaldoInicialDifCero, parametros);
		Long idBodega, idProducto, idLote;
		int anio = 0, mes = 0;
		BigDecimal cantidad;
		for (Object[] o : listaQuerySaldoInicialDifCero) {
			List<Object[]> listaQuerySumaKardex = jpaManagerLocal.executeQueryList(querySumaKardex, parametros);
			for (Object[] k2 : listaQuerySumaKardex) {
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, anio);
				cal.set(Calendar.MONTH, mes);
				Date fecha = cal.getTime();
				cantidad = BigDecimal.ZERO;
				StockEJB stock = (StockEJB) jpaManagerLocal.executeQuerySingle(queryStock, parametros);
				if (stock == null) {
					// NO EXISTE!!
				} else {
					stock.setCantidad(cantidad);
				}
			}
		}
	}
}
