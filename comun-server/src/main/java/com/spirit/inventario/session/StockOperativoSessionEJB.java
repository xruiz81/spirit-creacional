package com.spirit.inventario.session;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.*;

import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.comun.querys.QueryHelperServerLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.inventario.session.generated._StockOperativoSession;
import com.spirit.inventario.session.StockOperativoSessionLocal;
import com.spirit.inventario.session.StockOperativoSessionRemote;
import com.spirit.inventario.entity.*;
import com.spirit.inventario.helper.ConsultaStockOperativoData;
import com.spirit.inventario.helper.EnumSemaforo;
import com.spirit.inventario.helper.ParametrosStockOperativoNotFound;
import com.spirit.inventario.helper.StockInventarioData;
import com.spirit.inventario.helper.StockNotFound;
import com.spirit.inventario.helper.StockOperativoDataModel;
import com.spirit.poscola.entity.PosColaIf;
import com.truemesh.squiggle.SelectQuery;
import com.truemesh.squiggle.Table;
import com.truemesh.squiggle.criteria.ParameterCriteria;

/**
 * 
 * @author www.versality.com.ec
 * 
 */
@Stateless
public class StockOperativoSessionEJB extends _StockOperativoSession implements
		StockOperativoSessionRemote, StockOperativoSessionLocal {

	@EJB
	private UtilitariosSessionLocal utilitariosSessionLocal;

	@EJB
	private JPAManagerLocal jpaManagerLocal;

	@EJB
	private QueryHelperServerLocal queryHelperServerLocal;

	@EJB
	private StockSessionLocal stockSessionLocal;
	
	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 * 
	 * @throws GenericBusinessException
	 **************************************************************************/

	private List<StockOperativoEJB> consultaBDStockOperativo(Long bodegaId,
			Date mesAnio) {
		String mes = fillMes(utilitariosSessionLocal.getMonthFromDate(mesAnio));
		String anio = utilitariosSessionLocal.getYearFromDate(mesAnio);
		System.out.println("consultando: " + mes + " " + anio);

		SelectQuery select = new SelectQuery();
		Table stockOperativo = new Table(StockOperativoEJB.class);
		select.addObject(stockOperativo);
		select.addCriteria(new ParameterCriteria(stockOperativo, "mes"));
		select.addCriteria(new ParameterCriteria(stockOperativo, "anio"));
		select.addCriteria(new ParameterCriteria(stockOperativo, "bodegaId"));

		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("mes", mes);
		parametros.put("anio", anio);
		parametros.put("bodegaId", bodegaId);

		return (List<StockOperativoEJB>) jpaManagerLocal.executeQueryList(
				select.getQueryString(), parametros);

	}

	public List<StockOperativoDataModel> consultaStockOperativo(Long bodegaId,
			Date mesAnio) throws GenericBusinessException {

		List<StockOperativoEJB> listaStockOperativoEJB = consultaBDStockOperativo(
				bodegaId, mesAnio);
		List<StockOperativoDataModel> listaStockOperativoDataModel = new ArrayList<StockOperativoDataModel>();
		if (listaStockOperativoEJB.isEmpty()) {
			List<StockInventarioData> stockInventarioDataList = stockSessionLocal
					.getConsultaCierreStock(bodegaId, null, null, null, null,
							null);
			for (StockInventarioData stockInventarioData : stockInventarioDataList) {
				StockOperativoDataModel stockOperativoDataModel = new StockOperativoDataModel();
				stockOperativoDataModel.setBodegaId(stockOperativoDataModel
						.getBodegaId());
				stockOperativoDataModel.setProductoId(stockInventarioData
						.getIdProducto());
				stockOperativoDataModel.setProducto(stockInventarioData
						.getProducto());
				stockOperativoDataModel.setBodega(stockInventarioData
						.getBodega());
				listaStockOperativoDataModel.add(stockOperativoDataModel);
			}

		} else {
			for (StockOperativoEJB stockOperativoEJB : listaStockOperativoEJB) {
				StockOperativoDataModel stockOperativoDataModel = new StockOperativoDataModel();
				stockOperativoDataModel.setBodegaId(stockOperativoEJB
						.getBodegaId());
				stockOperativoDataModel.setMax(stockOperativoEJB.getMax());
				stockOperativoDataModel.setMin(stockOperativoEJB.getMin());

				Integer tmin = stockOperativoEJB.getTiempoMinimoReposicion();
				stockOperativoDataModel
						.setTiempoMinimoReposision(tmin != null ? new Long(tmin)
								: null);
				stockOperativoDataModel.setProductoId(stockOperativoEJB
						.getProductoId());
				stockOperativoDataModel.setProducto(queryHelperServerLocal
						.getDescripcionProducto(stockOperativoDataModel
								.getProductoId()));
				stockOperativoDataModel.setBodega(queryHelperServerLocal
						.getDescipcionBodega(bodegaId));
				listaStockOperativoDataModel.add(stockOperativoDataModel);
			}
		}

		return listaStockOperativoDataModel;
	}

	public void procesar(List<StockOperativoDataModel> dataList, Date mesAnio,
			Long bodegaId) {
		SelectQuery select = new SelectQuery();
		Table stockOperativo = new Table(StockOperativoEJB.class);
		select.addObject(stockOperativo);
		select.addCriteria(new ParameterCriteria(stockOperativo, "mes"));
		select.addCriteria(new ParameterCriteria(stockOperativo, "anio"));
		select.addCriteria(new ParameterCriteria(stockOperativo, "bodegaId"));
		select.addCriteria(new ParameterCriteria(stockOperativo, "productoId"));

		HashMap<String, Object> parametros = new HashMap<String, Object>();
		String mes = fillMes(utilitariosSessionLocal.getMonthFromDate(mesAnio));
		String anio = utilitariosSessionLocal.getYearFromDate(mesAnio);
		parametros.put("mes", mes);
		parametros.put("anio", anio);
		parametros.put("bodegaId", bodegaId);
		// System.out.println("consultando: " + mes + " " + anio);
		StockOperativoEJB stockOperativoEJB;
		for (StockOperativoDataModel stockOperativoDataModel : dataList) {

			parametros.put("productoId", stockOperativoDataModel
					.getProductoId());
			stockOperativoEJB = (StockOperativoEJB) jpaManagerLocal
					.executeQuerySingle(select.getQueryString(), parametros);
			if (stockOperativoEJB == null) {
				stockOperativoEJB = new StockOperativoEJB();
				stockOperativoEJB.setBodegaId(bodegaId);
				stockOperativoEJB.setProductoId(stockOperativoDataModel
						.getProductoId());
				stockOperativoEJB.setMes(mes);
				stockOperativoEJB.setAnio(anio);
				stockOperativoEJB.setMin(stockOperativoDataModel.getMin());
				stockOperativoEJB.setMax(stockOperativoDataModel.getMax());
				Long tmin = stockOperativoDataModel.getTiempoMinimoReposision();
				stockOperativoEJB.setTiempoMinimoReposicion(tmin != null ? tmin
						.intValue() : null);
				jpaManagerLocal.persist(stockOperativoEJB);
			} else {
				stockOperativoEJB.setMin(stockOperativoDataModel.getMin());
				stockOperativoEJB.setMax(stockOperativoDataModel.getMax());
				Long tmin = stockOperativoDataModel.getTiempoMinimoReposision();
				stockOperativoEJB.setTiempoMinimoReposicion(tmin != null ? tmin
						.intValue() : null);
				jpaManagerLocal.save(stockOperativoEJB);
			}

		}
	}

	private String fillMes(String mes) {
		if (mes.length() == 1)
			return "0" + mes;
		else
			return mes;
	}

	public List<ConsultaStockOperativoData> consultaStockOperativo(
			Long oficinaId, Long bodegaId, Long genericoId, Long modeloId,
			Long tallaId, Long colorId, Long productoId, Date fechaInicial,
			Date fechaFinal) throws GenericBusinessException {
		Calendar c=Calendar.getInstance();
		c.setTime(fechaInicial);
		c.add(Calendar.MONTH, -1);
		fechaInicial=c.getTime();
		List<ConsultaStockOperativoData> consultaStockOperativoDataList = new ArrayList<ConsultaStockOperativoData>();

		List<StockOperativoDataModel> stockOperativoDataModeList = consultaStockOperativo(
				bodegaId, fechaFinal);

		List<StockOperativoEJB> listaStockOperativoEJB = consultaBDStockOperativo(
				bodegaId, fechaFinal);
		if (listaStockOperativoEJB.isEmpty()) {
			throw new ParametrosStockOperativoNotFound("Parametros de stock operativo para la bodega seleccionada no fueron encontrados..");
		}

		String mes = fillMes(utilitariosSessionLocal
				.getMonthFromDate(fechaFinal));
		String anio = utilitariosSessionLocal.getYearFromDate(fechaFinal);

		List<StockInventarioData> consultaStockActual = stockSessionLocal
				.getConsultaCierreStock(bodegaId, productoId, genericoId,
						tallaId, modeloId, colorId, mes, anio);

		if (consultaStockActual.isEmpty()) {
			throw new StockNotFound("Consulta de Stock no obtuvo resultados");
		}

		HashMap<Long, BigDecimal> mapaProductoCantidadActual = new HashMap<Long, BigDecimal>();
		HashMap<Long, Long> mapaProductoLote = new HashMap<Long, Long>();
		
		for (StockInventarioData stockInventarioData : consultaStockActual) {
			mapaProductoCantidadActual.put(stockInventarioData.getIdProducto(),
					stockInventarioData.getCantidad());
			mapaProductoLote.put(stockInventarioData.getIdProducto(), stockInventarioData.getIdLote());
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaFinal);
		int diasFechaFinal = cal.get(Calendar.DAY_OF_YEAR);
		cal.setTime(fechaInicial);
		int diasFechaInicial = cal.get(Calendar.DAY_OF_YEAR);
		System.out.println("-------" + diasFechaFinal + " " + diasFechaInicial);
		BigDecimal numeroDias = new BigDecimal(diasFechaFinal
				- diasFechaInicial);
		// CONSULTAR MES ANTERIOR, Y ESE ES EL STOCK INICIAL..

		mes = fillMes(utilitariosSessionLocal.getMonthFromDate(fechaInicial));
		anio = utilitariosSessionLocal.getYearFromDate(fechaInicial);

		List<StockInventarioData> consultaStockInicial = stockSessionLocal
				.getConsultaCierreStock(bodegaId, productoId, genericoId,
						tallaId, modeloId, colorId, mes, anio, false);

		HashMap<Long, BigDecimal> mapaProductoCantidadInicial = new HashMap<Long, BigDecimal>();
		

		for (StockInventarioData stockInventarioData : consultaStockInicial) {
			mapaProductoCantidadInicial.put(
					stockInventarioData.getIdProducto(), stockInventarioData
							.getCantidad());
		}

		for (StockOperativoDataModel stockOperativoDataModel : stockOperativoDataModeList) {
			ConsultaStockOperativoData consultaStockOperativoData = new ConsultaStockOperativoData();
			if (!mapaProductoCantidadActual.containsKey(stockOperativoDataModel
					.getProductoId())) {
				// System.out.println("NO HAY REGISTRO DE STOCK ACTUAL");
				continue;
			}
			consultaStockOperativoData.setBodega(stockOperativoDataModel
					.getBodega());
			consultaStockOperativoData.setBodegaId(stockOperativoDataModel
					.getBodegaId());
			consultaStockOperativoData.setMax(stockOperativoDataModel.getMax());
			consultaStockOperativoData.setMin(stockOperativoDataModel.getMin());
			consultaStockOperativoData.setProducto(stockOperativoDataModel
					.getProducto());
			consultaStockOperativoData.setProductoId(stockOperativoDataModel
					.getProductoId());
			consultaStockOperativoData.setLoteId(mapaProductoLote.get(stockOperativoDataModel
					.getProductoId()));
			consultaStockOperativoData
					.setTiempoMinimoReposision(stockOperativoDataModel
							.getTiempoMinimoReposision());

			BigDecimal stockActual = mapaProductoCantidadActual
					.get(stockOperativoDataModel.getProductoId()) != null ? mapaProductoCantidadActual
					.get(stockOperativoDataModel.getProductoId())
					: BigDecimal.ZERO;

			BigDecimal stockAnterior = mapaProductoCantidadInicial
					.get(stockOperativoDataModel.getProductoId()) != null ? mapaProductoCantidadInicial
					.get(stockOperativoDataModel.getProductoId())
					: BigDecimal.ZERO;

			System.out.println("MES ANTERIOR " + mes);
			System.out.println("PRODUCTO: "
					+ stockOperativoDataModel.getProductoId());
			System.out.println("STOCK ACTUAL: " + stockActual);
			System.out.println("STOCK ANTERIOR: " + stockAnterior);

			consultaStockOperativoData.setStockActual(stockActual);
			BigDecimal promedioDiario = BigDecimal.ZERO;
			if (numeroDias.compareTo(BigDecimal.ZERO) > 0) {
				promedioDiario = stockActual.subtract(stockAnterior).divide(
						numeroDias, RoundingMode.HALF_UP);
			}
			consultaStockOperativoData.setPromedioDiario(promedioDiario);
			BigDecimal rotacion = BigDecimal.ZERO;
			if (promedioDiario.compareTo(BigDecimal.ZERO) != 0) {
				rotacion = stockActual.divide(promedioDiario,
						RoundingMode.HALF_UP);
			}else
			{
				rotacion=new BigDecimal(100000);
			}

			consultaStockOperativoData.setRotacion(rotacion);

			// SEMAFORO DE STOCK
			if (stockActual.compareTo(consultaStockOperativoData.getMin()) > 0) {
				consultaStockOperativoData.setSemaforoStock(EnumSemaforo.VERDE);
			} else if (stockActual.compareTo(consultaStockOperativoData
					.getMin()) == 0) {
				consultaStockOperativoData
						.setSemaforoStock(EnumSemaforo.AMARILLO);
			} else {
				consultaStockOperativoData.setSemaforoStock(EnumSemaforo.ROJO);
			}
			// SEMAFORO DE ROTACION
			if (rotacion.compareTo(BigDecimal.ZERO) == 0) {

			}
			if (consultaStockOperativoData.getTiempoMinimoReposision() != null
					&& consultaStockOperativoData.getTiempoMinimoReposision() > 0) {
				if (rotacion
						.compareTo(new BigDecimal(consultaStockOperativoData
								.getTiempoMinimoReposision())) > 0) {
					consultaStockOperativoData
							.setSemaforoRotacion(EnumSemaforo.VERDE);
				} else if (stockActual
						.compareTo(new BigDecimal(consultaStockOperativoData
								.getTiempoMinimoReposision())) == 0) {
					consultaStockOperativoData
							.setSemaforoRotacion(EnumSemaforo.AMARILLO);
				} else {
					consultaStockOperativoData
							.setSemaforoRotacion(EnumSemaforo.ROJO);
				}
			}else
			{
				consultaStockOperativoData
				.setSemaforoRotacion(EnumSemaforo.VERDE);
			}

			consultaStockOperativoDataList.add(consultaStockOperativoData);
		}

		return consultaStockOperativoDataList;
	}
	

	


}
