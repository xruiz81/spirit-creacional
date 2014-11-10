package com.spirit.inventario.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.client.SpiritConstants;
import com.spirit.comun.querys.QueryHelperServerLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.mdb.messages.bo.CierreStockMessageLocal;
import com.spirit.general.session.TipoDocumentoSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.general.webservice.consumer.SpiritWebServiceConsumerLocal;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.LoteIf;
import com.spirit.inventario.entity.MovimientoDetalleIf;
import com.spirit.inventario.entity.MovimientoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.PromedioUnitarioIf;
import com.spirit.inventario.entity.StockEJB;
import com.spirit.inventario.entity.StockIf;
import com.spirit.inventario.handler.StockValorizadoData;
import com.spirit.inventario.helper.StockException;
import com.spirit.inventario.helper.StockInventarioData;
import com.spirit.inventario.session.generated._StockSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.util.DeepCopy;

/**
 * The <code>StockSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:18 $
 * 
 */
@Stateless
public class StockSessionEJB extends _StockSession implements
		StockSessionRemote, StockSessionLocal {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	@EJB
	private UtilitariosSessionLocal utilitariosLocal;

	@EJB
	private TipoDocumentoSessionLocal tipoDocumentoLocal;

	@EJB
	private LoteSessionLocal loteSessionLocal;

	@EJB
	private CierreStockMessageLocal stockMessageLocal;

	@EJB
	private BodegaSessionLocal bodegaSessionLocal;

	@EJB
	private ProductoSessionLocal productoSessionLocal;

	@EJB
	private GenericoSessionLocal genericoSessionLocal;

	@EJB
	private QueryHelperServerLocal queryHelperServerLocal;
	
	@EJB private SpiritWebServiceConsumerLocal swsLocal;

	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(StockSessionEJB.class);

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 **************************************************************************/

	public static final String ESTADO_ACTIVO = "A";
	public static final String ESTADO_PARCIAL = "P";
	public static final String ESTADO_CERRADO = "C";

	public static final HashMap<String, String> mapaEstado = new HashMap<String, String>();
	static {
		mapaEstado.put(ESTADO_ACTIVO, "ACTIVO");
		mapaEstado.put(ESTADO_PARCIAL, "PARCIAL");
		mapaEstado.put(ESTADO_CERRADO, "PARCIAL");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findStockByIdLoteAndIdBodega(
			java.lang.Long loteId, java.lang.Long bodegaId) {
		String queryString = "from StockEJB e where e.loteId = " + loteId
				+ " and e.bodegaId = " + bodegaId;
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findStockByOficinaId(java.lang.Long idOficina)
			throws GenericBusinessException {
		String objectName = "s";
		String queryString = "select s from StockEJB " + objectName
				+ ", BodegaEJB b where s.bodegaId = b.id and b.oficinaId = "
				+ idOficina + "";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public void chequearStock(Long idBodega, Long idProducto,
			BigDecimal cantidad) throws StockException,
			GenericBusinessException {
		List<LoteIf> listaLote = (List) loteSessionLocal
				.findLoteByProductoIdAndEstadoAndFecha(idProducto,
						utilitariosLocal.getServerDateSql());

		LoteIf loteIf = (listaLote.size() > 0) ? listaLote.get(0) : null;
		if (loteIf == null) {
			throw new StockException(
					"No se encuentra definido lote para el producto");
		}

		StockIf stock = getStockActualQuery(loteIf.getId(), idBodega);

		if (stock == null) {
			throw new StockException(
					"No se encuentran registros de stock para el producto");
		} else if (stock.getCantidad().compareTo(BigDecimal.ZERO) <= 0) {
			throw new StockException("No hay stock disponible");
		} else if (stock.getCantidad().compareTo(cantidad) < 0) {
			throw new StockException(
					"Esta solicitando una cantidad mayor a la disponible: "
							+ stock.getCantidad() + " unidades disponibles ");
		} else
			return;
	}

	private List getAllStockJoins(Long idBodega, Long productoId,
			Long idGenerico, Long idPresentacion, Long idModelo, Long idColor,
			String mes, String anio, boolean filtarPorEstado) {

		String queryString = "select "
				+ "bodega.nombre, "
				+ "stock.cantidad,"
				+ "stock.estado,"
				+ "stock.mes, "
				+ "stock.anio, "
				+ "producto.id, "
				+ "lote.codigo,"
				+ "lote.id, "
				+ "bodega.id "

				+ "from "
				+ "StockEJB stock, "
				+ "BodegaEJB bodega, "
				+ "ModeloEJB modelo, "
				+ "GenericoEJB generico, "
				+ "PresentacionEJB presentacion, "
				+ "ProductoEJB producto, "
				+ "LoteEJB lote "
				+ "where "
				+ "stock.loteId=lote.id and "
				+ "stock.bodegaId=bodega.id and "
				+ "stock.productoId=producto.id and "
				+ "producto.modeloId=modelo.id and "
				+ "producto.genericoId=generico.id and "
				+ "producto.presentacionId=presentacion.id and "
				+ "(stock.bodegaId = :idBodega or :idBodega is null) and "
				+ "(stock.productoId = :productoId or :productoId is null) and "
				+ "(producto.modeloId = :modeloId or :modeloId is null) and "
				+ "(producto.genericoId = :genericoId or :genericoId is null) and "
				+ "(producto.colorId = :colorId or :colorId is null) and "
				+ "(producto.presentacionId = :presentacionId or :presentacionId is null) and "
				+ "stock.mes = :mes and stock.anio = :anio ";
		if (filtarPorEstado) {
			queryString += "and (stock.estado='" + ESTADO_ACTIVO
					+ "' OR stock.estado='" + ESTADO_PARCIAL + "')";
		}
		String orderByPart = "";
		orderByPart += " order by producto.genericoId, producto.modeloId, producto.colorId, producto.presentacionId ";
		queryString += orderByPart;

		Query query = manager.createQuery(queryString);

		query.setParameter("idBodega", idBodega);
		query.setParameter("productoId", productoId);
		query.setParameter("modeloId", idModelo);
		query.setParameter("genericoId", idGenerico);
		query.setParameter("colorId", idColor);
		query.setParameter("presentacionId", idPresentacion);
		query.setParameter("mes", mes);
		query.setParameter("anio", anio);
		List listaRetorno = query.getResultList();
		System.out.println("LA LISTA---------> " + listaRetorno.size());
		return listaRetorno;
	}

	private List<StockIf> getAllStock(Long idBodega, String mesStock,
			String anioStock) {
		String queryString = "from StockEJB e where" + " e.bodegaId = "
				+ idBodega + " and e.mes = '" + mesStock + "' and e.anio = '"
				+ anioStock + "' and (e.estado='" + ESTADO_ACTIVO
				+ "' OR e.estado='" + ESTADO_PARCIAL + "')";

		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	public List<StockInventarioData> getConsultaCierreStock(Long idBodega,
			Long idProducto, Long idGenerico, Long idPresentacion,
			Long idModelo, Long idColor, String mes, String anio)
			throws GenericBusinessException {
		return getConsultaCierreStock(idBodega, idProducto, idGenerico,
				idPresentacion, idModelo, idColor, mes, anio, true);
	}

	public List<StockInventarioData> getConsultaCierreStock(Long idBodega,
			Long idProducto, Long idGenerico, Long idPresentacion,
			Long idModelo, Long idColor, String mes, String anio,
			boolean filtarPorEstado) throws GenericBusinessException {
		Date fechaHoy = new Date();

		List<StockInventarioData> listaCierre = new ArrayList<StockInventarioData>();
		List listaQuery = null;
		try {
			listaQuery = getAllStockJoins(idBodega, idProducto, idGenerico,
					idPresentacion, idModelo, idColor, mes, anio,
					filtarPorEstado);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("No hay registros disponibles..");
		}

		Object[] temp = null;
		for (int i = 0; i < listaQuery.size(); i++) {
			temp = (Object[]) listaQuery.get(i);
			StockInventarioData cierreInventarioData = new StockInventarioData();
			cierreInventarioData.setBodega((String) temp[0]);
			cierreInventarioData.setCantidad((BigDecimal) temp[1]);
			cierreInventarioData.setEstado(mapaEstado.get((String) temp[2]));
			cierreInventarioData.setMesAnio(utilitariosLocal
					.getNombreMes((String) temp[3])
					+ " / " + (String) temp[4]);

			cierreInventarioData.setProducto(queryHelperServerLocal
					.getDescripcionProducto((Long) temp[5]));
			cierreInventarioData.setCantidadFisica(cierreInventarioData
					.getCantidad());

			cierreInventarioData.setLote((String) temp[6]);
			cierreInventarioData.setIdProducto((Long) temp[5]);
			cierreInventarioData.setIdLote((Long) temp[7]);
			cierreInventarioData.setIdBodega((Long) temp[8]);
			listaCierre.add(cierreInventarioData);
		}
		System.out.println("tamaño final: ------------> " + listaCierre.size());
		return listaCierre;

	}

	public List<StockInventarioData> getConsultaCierreStock(Long idBodega,
			Long idProducto, Long idGenerico, Long idPresentacion,
			Long idModelo, Long idColor) throws GenericBusinessException {
		String queryAnioString = "select max(anio) from StockEJB where (bodegaId=:bodegaId or :bodegaId is null)";

		Query query = manager.createQuery(queryAnioString);
		query.setParameter("bodegaId", idBodega);

		System.out.println(idBodega);
		String anio = (String) query.getSingleResult();

		String queryMesString = "select max(mes) from StockEJB where anio = '"
				+ anio + "' and (bodegaId=:bodegaId or :bodegaId is null)";

		query = manager.createQuery(queryMesString);
		query.setParameter("bodegaId", idBodega);
		String mes = (String) query.getSingleResult();
		System.out.println("EL MES---------> " + mes);
		System.out.println("EL ANIO---------> " + anio);
		if (mes.length() == 1)
			mes = "0" + mes;
		return getConsultaCierreStock(idBodega, idProducto, idGenerico,
				idPresentacion, idModelo, idColor, mes, anio);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cerrarStock(Long idBodega) throws GenericBusinessException {

		Calendar cal = Calendar.getInstance();
		Date fechaHoy = new Date();
		cal.setTime(fechaHoy);
		cal.add(Calendar.MONTH, -1);
		Date fechaAnterior = cal.getTime();
		String mesAnteriorStock = utilitariosLocal.getMonthFromDate(fechaAnterior);
		String anioAnteriorStock = utilitariosLocal.getYearFromDate(fechaAnterior);

		String mesActualStock = utilitariosLocal.getMonthFromDate(fechaHoy);
		String anioActualStock = utilitariosLocal.getYearFromDate(fechaHoy);

		List<StockIf> listaStock = getAllStock(idBodega, mesAnteriorStock, anioAnteriorStock);
		for (StockIf stockAnterior : listaStock) {
			StockIf stockCunsulta = getStockQuery(stockAnterior.getLoteId(), idBodega, mesActualStock, anioActualStock);

			if (stockCunsulta == null) {
				StockEJB stockEJB = new StockEJB();
				stockEJB.setAnio(anioActualStock);
				stockEJB.setMes(mesActualStock);
				stockEJB.setBodegaId(stockAnterior.getBodegaId());
				stockEJB.setCantidad(stockAnterior.getCantidad());
				stockEJB.setLoteId(stockAnterior.getLoteId());
				stockEJB.setProductoId(stockAnterior.getProductoId());
				stockEJB.setReserva(stockAnterior.getReserva());
				stockEJB.setTransito(stockAnterior.getTransito());
				stockEJB.setFhUtlModificacion(utilitariosLocal.getServerDateTimeStamp());
				stockEJB.setEstado(ESTADO_ACTIVO);
				addStock(stockEJB);
			} else {
				stockCunsulta.setCantidad(stockAnterior.getCantidad().add(stockCunsulta.getCantidad()));
				stockCunsulta.setFhUtlModificacion(utilitariosLocal.getServerDateTimeStamp());
				stockCunsulta.setEstado(ESTADO_ACTIVO);
				saveStock(stockCunsulta);
			}
			stockAnterior.setEstado(ESTADO_CERRADO);
			stockAnterior.setFhUtlModificacion(utilitariosLocal.getServerDateTimeStamp());
			saveStock(stockAnterior);
		}
		stockMessageLocal.setIdBodega(idBodega);
		BodegaIf bodegaIf = bodegaSessionLocal.getBodega(idBodega);
		try {
			stockMessageLocal.sendToPosIfPrincipal(bodegaIf.getOficinaId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<StockIf> afectarStock(MovimientoIf model,
			MovimientoDetalleIf modelDetalle) throws GenericBusinessException {

		List<StockIf> stockAfectados = new ArrayList<StockIf>();

		TipoDocumentoIf tipoDocumentoIf = tipoDocumentoLocal
				.getTipoDocumento(model.getTipodocumentoId());
		String signo = tipoDocumentoIf.getSignostock();

		System.out.println("Afectando stock: " + tipoDocumentoIf.getCodigo());

		StockIf stockActual = getStockMesActual(model, modelDetalle);

		if (stockActual != null) {
			aplicarAfectacionSaldo(stockActual, signo, modelDetalle
					.getCantidad());
			stockAfectados.add(stockActual);
		}

		List<StockIf> stockMesesSiguientes = getStockMesesSiguientes(model,
				modelDetalle);

		for (StockIf stock : stockMesesSiguientes) {
			aplicarAfectacionSaldo(stock, signo, modelDetalle.getCantidad());
			if (stock.getCantidad().compareTo(BigDecimal.ZERO) >= 0) {
				stockAfectados.add(stock);
			}

		}
		return stockAfectados;
	}

	private List<StockIf> getStockMesesSiguientes(MovimientoIf model,
			MovimientoDetalleIf modelDetalle) {

		String mesStock = utilitariosLocal.getMonthFromDate(model
				.getFechaDocumento());
		String anioStock = utilitariosLocal.getYearFromDate(model
				.getFechaDocumento());

		String queryString = "from StockEJB e where e.loteId = "
				+ modelDetalle.getLoteId() + " and e.bodegaId = "
				+ model.getBodegaId() + " and e.mes > '" + mesStock
				+ "' and e.anio >= '" + anioStock + "'";

		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();

	}

	public List<StockIf> getStockActualyMesesSiguientes(Long idLote,
			Long idBodega, String mesStock, String anioStock) {

		String queryString = "from StockEJB e where e.loteId = " + idLote
				+ " and e.bodegaId = " + idBodega + " and e.mes >= '"
				+ mesStock + "' and e.anio >= '" + anioStock + "'";

		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();

	}

	public void aplicarAfectacionSaldo(StockIf stock, String signo,
			BigDecimal cantidad) throws GenericBusinessException {
		if ("+".equals(signo)) {

			stock.setCantidad(stock.getCantidad().add(cantidad));
		} else if ("-".equals(signo)) {

			stock.setCantidad(stock.getCantidad().subtract(cantidad));
			stock.setFhUtlModificacion(utilitariosLocal
					.getServerDateTimeStamp());
		} else {

			throw new GenericBusinessException(
					"Signo del Tipo Documento no determinado" + signo);
		}
	}

	public List getStockModificado(int intervaloTiempo, boolean obtenerTodos,
			String codigoBodega) {
		Date now = new Date();
		String mesStock = utilitariosLocal.getMonthFromDate(now);
		String anioStock = utilitariosLocal.getYearFromDate(now);

		String queryString = "SELECT distinct producto.codigoBarras,stock.cantidad,stock.fhUtlModificacion "
				+ "FROM StockEJB stock, ProductoEJB producto,BodegaEJB bodega "
				+ "WHERE stock.productoId=producto.id and stock.bodegaId = bodega.id"
				+ " and stock.mes=:mes and stock.anio=:anio and stock.estado <> :estado "
				+ " and bodega.codigo = :codigoBodega ";

		if (!obtenerTodos)
			queryString += "and stock.fhUtlModificacion is not null";

		Query query = manager.createQuery(queryString);
		query.setParameter("mes", mesStock);
		query.setParameter("anio", anioStock);
		query.setParameter("estado", "C");
		query.setParameter("codigoBodega", codigoBodega);
		// query.setParameter("minutos", intervaloTiempo);
		List lista = query.getResultList();
		if (!obtenerTodos) {
			Object[] objetoTmp;
			Date tempDate;
			Iterator itLista = lista.iterator();
			// for(int i=0;i<lista.size();i++)
			while (itLista.hasNext()) {
				// objetoTmp=(Object[])lista.get(i);
				objetoTmp = (Object[]) itLista.next();
				// System.out.println("codigo: "+objetoTmp[0]);
				tempDate = (Date) objetoTmp[2];
				long diff = now.getTime() - tempDate.getTime();
				long minutos = diff / (1000 * 60);
				if (minutos > intervaloTiempo) {
					itLista.remove();
				}
			}
		}
		return lista;

	}
	
	public Collection getStockModificadoWebService(Long empresaId, int intervaloMinutos, boolean obtenerTodos) throws GenericBusinessException {
		return swsLocal.getWSStockModificado(empresaId, intervaloMinutos, obtenerTodos);
	}

	public StockIf getStockQuery(Long idLote, Long idBodega, String mesStock,
			String anioStock) {
		String queryString = "from StockEJB e where e.loteId = " + idLote
				+ " and e.bodegaId = " + idBodega + " and e.mes = '" + mesStock
				+ "' and e.anio = '" + anioStock + "'";

		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		List<StockIf> listaStock = query.getResultList();
		return (listaStock.size() > 0) ? listaStock.get(0) : null;
	}

	private StockIf getStockActualQuery(Long idLote, Long idBodega) {
		String mesStock = utilitariosLocal.getMonthFromDate(new Date());
		String anioStock = utilitariosLocal.getYearFromDate(new Date());
		return getStockQuery(idLote, idBodega, mesStock, anioStock);
	}

	public StockIf getStockMesActual(MovimientoIf model,
			MovimientoDetalleIf modelDetalle) throws GenericBusinessException {

		System.out.println("Afectando stock de bodega: " + model.getBodegaId());

		StockIf stock = getStockActualQuery(modelDetalle.getLoteId(), model
				.getBodegaId());
		if (stock != null) {
			System.out.println("Encontro stock: " + stock.getBodegaId());
			stock.setProductoId(loteSessionLocal.getLote(
					modelDetalle.getLoteId()).getProductoId());
		} else {
			stock = new StockEJB();
			System.out.println("Nuevo stock: " + model.getBodegaId());
			stock.setMes(utilitariosLocal.getMonthFromDate(model
					.getFechaDocumento()));
			stock.setAnio(utilitariosLocal.getYearFromDate(new Date()));
			stock.setBodegaId(model.getBodegaId());
			stock.setCantidad(BigDecimal.ZERO);
			stock.setLoteId(modelDetalle.getLoteId());
			stock.setProductoId(loteSessionLocal.getLote(
					modelDetalle.getLoteId()).getProductoId());
			stock.setEstado(ESTADO_PARCIAL);// O INICIAL???

		}

		return stock;
	}

	public Vector<StockValorizadoData> getStockValorizado(Long idEmpresa, Long idBodega, java.util.Date fechaCorte) throws GenericBusinessException {
		Vector<StockValorizadoData> stockValorizadoVector = new Vector<StockValorizadoData>();
		Map<Long, ProductoIf> productosMap = mapearProductos(idEmpresa);
		Map<String, StockIf> stockMap = mapearStock(idBodega, fechaCorte, productosMap);
		String queryString = "select pu from PromedioUnitarioEJB pu";
		Query query = manager.createQuery(queryString);
		Iterator<PromedioUnitarioIf> promedioUnitarioIterator = query.getResultList().iterator();
		String lastSku = SpiritConstants.getEmptyCharacter();
		while (promedioUnitarioIterator.hasNext()) {
			PromedioUnitarioIf promedioUnitario = (PromedioUnitarioIf) DeepCopy.copy(promedioUnitarioIterator.next());
			if (!lastSku.equals(promedioUnitario.getSku()) && utilitariosLocal.fromTimestampToUtilDate(utilitariosLocal.resetTimestampStartDate(promedioUnitario.getFecha())).compareTo(fechaCorte) <= 0) {
				StockValorizadoData stockValorizado = new StockValorizadoData();
				stockValorizado.setSku(promedioUnitario.getSku());
				stockValorizado.setModelo(promedioUnitario.getModelo());
				stockValorizado.setPresentacion(promedioUnitario.getTalla());
				stockValorizado.setColor(promedioUnitario.getColor());
				if (stockMap.get(promedioUnitario.getSku()) != null)
					stockValorizado.setStock(stockMap.get(promedioUnitario.getSku()).getCantidad());
				else
					stockValorizado.setStock(BigDecimal.ZERO);
				if (promedioUnitario.getPromedioUnitario() != null)
					stockValorizado.setPromedioUnitario(promedioUnitario.getPromedioUnitario());
				else
					stockValorizado.setPromedioUnitario(BigDecimal.ZERO);
				BigDecimal sv = stockValorizado.getPromedioUnitario().multiply(stockValorizado.getStock());
				stockValorizado.setStockValorizado(sv);
				lastSku = promedioUnitario.getSku();
				stockValorizadoVector.add(stockValorizado);
			}
		}
		return stockValorizadoVector;
	}
	
	private Map<Long, ProductoIf> mapearProductos(Long idEmpresa) throws GenericBusinessException {
		Map<Long, ProductoIf> productosMap = new HashMap<Long, ProductoIf>();
		Iterator<ProductoIf> productosIterator = productoSessionLocal.findProductoByEmpresaId(idEmpresa).iterator();
		while (productosIterator.hasNext()) {
			ProductoIf producto = productosIterator.next();
			productosMap.put(producto.getId(), producto);
		}
		return productosMap;
	}
	
	private Map<String, StockIf> mapearStock(Long idBodega, java.util.Date fechaCorte, Map<Long, ProductoIf> productosMap) throws GenericBusinessException {
		Map<String, StockIf> stockMap = new HashMap<String, StockIf>();
		String year = utilitariosLocal.getYearFromDate(fechaCorte);
		String month = utilitariosLocal.getMonthFromDate(fechaCorte);
		Map queryMap = new HashMap();
		queryMap.put("bodegaId", idBodega);
		queryMap.put("anio", year);
		queryMap.put("mes", month);
		Iterator<StockIf> stockIterator = findStockByQuery(queryMap).iterator();
		while (stockIterator.hasNext()) {
			StockIf stock = stockIterator.next();
			stockMap.put(productosMap.get(stock.getProductoId()).getCodigoBarras(), stock);
		}
		return stockMap;
	}
}
