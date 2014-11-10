package com.spirit.cartera.session;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
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

import com.spirit.cartera.entity.CarteraAfectaEJB;
import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleData;
import com.spirit.cartera.entity.CarteraDetalleEJB;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraEJB;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.NotaCreditoDetalleEJB;
import com.spirit.cartera.entity.NotaCreditoDetalleIf;
import com.spirit.cartera.entity.NotaCreditoEJB;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.cartera.handler.NotaCreditoAsientoAutomaticoHandlerLocal;
import com.spirit.cartera.session.generated._NotaCreditoSession;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.session.AsientoDetalleSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.session.FacturaSessionLocal;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.ServidorIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.session.DocumentoSessionLocal;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.OficinaSessionLocal;
import com.spirit.general.session.ServidorSessionLocal;
import com.spirit.general.session.TipoDocumentoSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.medios.entity.PlanMedioFacturacionIf;
import com.spirit.medios.entity.PlanMedioFormaPagoIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoFacturacionIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.session.PlanMedioFacturacionSessionLocal;
import com.spirit.medios.session.PlanMedioFormaPagoSessionLocal;
import com.spirit.medios.session.PlanMedioSessionLocal;
import com.spirit.medios.session.PresupuestoFacturacionSessionLocal;
import com.spirit.medios.session.PresupuestoSessionLocal;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>NotaCreditoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:20 $
 *
 */
@Stateless
public class NotaCreditoSessionEJB extends _NotaCreditoSession implements NotaCreditoSessionRemote, NotaCreditoSessionLocal {
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	@EJB private TipoDocumentoSessionLocal tipoDocumentoLocal;
	@EJB private CarteraDetalleSessionLocal carteraDetalleLocal;
	@EJB private NotaCreditoDetalleSessionLocal notaCreditoDetalleLocal;
	@EJB private DocumentoSessionLocal documentoLocal; 
	@EJB private EmpresaSessionLocal empresaLocal;
	@EJB private OficinaSessionLocal oficinaLocal;
	@EJB private ServidorSessionLocal servidorLocal;
	@EJB private AsientoDetalleSessionLocal asientoDetalleLocal;
	@EJB private UtilitariosSessionLocal utilitariosLocal;
	@EJB private NotaCreditoAsientoAutomaticoHandlerLocal notaCreditoAsientoAutomaticoHandlerLocal;
	@EJB private PresupuestoFacturacionSessionLocal presupuestoFacturacionLocal;
	@EJB private PlanMedioFacturacionSessionLocal planMedioFacturacionLocal;
	@EJB private PlanMedioFormaPagoSessionLocal planMedioFormaPagoLocal;
	@EJB private FacturaSessionLocal facturaLocal;
	@EJB private PresupuestoSessionLocal presupuestoLocal;
	@EJB private PlanMedioSessionLocal planMedioLocal;
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(NotaCreditoSessionEJB.class);
	private static String REDONDEO_TOTAL = "T";
	private static String REDONDEO_PARCIAL = "P";
	boolean nuevaCodificacionActiva = true;
	@Resource private SessionContext ctx;

	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NotaCreditoIf procesarNotaCredito(NotaCreditoIf model,List<NotaCreditoDetalleIf> modelDetalleList,Long idEmpresa,Long idOficina) throws GenericBusinessException {
		NotaCreditoEJB notaCredito;
		Vector<NotaCreditoDetalleIf> notaCreditoDetalleColeccion = new Vector<NotaCreditoDetalleIf>(); 
		DecimalFormat formatoSerial = new DecimalFormat("00000");
		try {
			if ( verificarPreimpreso(model.getOperadorNegocioId(),model.getPreimpreso(),model.getAutorizacion(), model.getTipoCartera()) ){
				Collection<NotaCreditoIf> notasCredito = findNotaCreditoByPreimpreso(model.getPreimpreso());
				NotaCreditoIf notaCreditoTemp = notasCredito.iterator().next();
				throw new GenericBusinessException("Preimpreso ya existe en nota de crédito con código "+notaCreditoTemp.getCodigo()+" !!");
			}

			int codigoNotaCredito = getCodigoNotaCredito(model, idEmpresa);
			model.setCodigo(model.getCodigo() + formatoSerial.format(codigoNotaCredito));
			notaCredito = registrarNotaCredito(model);
			manager.persist(notaCredito);

			for (NotaCreditoDetalleIf modelDetalle : modelDetalleList) {				
				modelDetalle.setNotaCreditoId(notaCredito.getPrimaryKey());
				NotaCreditoDetalleEJB notaCreditoDetalle = registrarNotaCreditoDetalle(modelDetalle);
				manager.merge(notaCreditoDetalle);
				notaCreditoDetalleColeccion.add(notaCreditoDetalle);
			}

			Object[] carteraSumaValores = registroCartera(notaCredito, modelDetalleList, idOficina);
			CarteraEJB cartera = (CarteraEJB)carteraSumaValores[0]; 
			Double sumaValoresNotaCreditoDetallesRedondeoTotal = (Double) carteraSumaValores[1];
			Double sumaValoresNotaCreditoDetallesRedondeoParcial = (Double) carteraSumaValores[2];
			int year = notaCredito.getFechaEmision().getYear() + 1900;
			int month = notaCredito.getFechaEmision().getMonth() + 1;
			AsientoIf asiento = null;
			long etapa = 1L;
			if ((year == 2008 && month >= 9) || (year > 2008))
				asiento = generarAsientoNotaCredito(notaCredito.getTipoDocumentoId(), notaCredito, notaCreditoDetalleColeccion, etapa, null, 0D, notaCredito.getTipoCartera(), null);
			//verificacionValores(notaCredito, cartera, sumaValoresNotaCreditoDetallesRedondeoTotal, sumaValoresNotaCreditoDetallesRedondeoParcial, asiento);
		} catch(GenericBusinessException e){ 
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al guardar la Nota de Crédito");
		}

		return notaCredito;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarNotaCredito(NotaCreditoIf model, List<NotaCreditoDetalleIf> modelDetalleList) throws GenericBusinessException {
		try {
			//solo se actualizar estado y observacion de la orden (seteados en el update)
			manager.merge(model);

			for (NotaCreditoDetalleIf modelDetalle : modelDetalleList) {
				
				//solo se puede actualizar detalles previamente guardados (tienen id)
				if(modelDetalle.getId() != null){
					manager.merge(modelDetalle);
				}				
			}

		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al guardar la Nota de Crédito");
		}
	}

	private AsientoIf generarAsientoNotaCredito(Long tipoDocumentoId, NotaCreditoEJB notaCredito, Vector<NotaCreditoDetalleIf> notaCreditoDetalleColeccion, long etapa, java.sql.Date fechaAplicacion, double valorAplica, String tipoCartera, CarteraIf carteraAfectada) throws GenericBusinessException {
		AsientoIf asiento = null;
		notaCreditoAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
		for (int i = 0; i < notaCreditoDetalleColeccion.size(); i++) {
			NotaCreditoDetalleIf notaCreditoDetalle = (NotaCreditoDetalleIf) notaCreditoDetalleColeccion.get(i);
			if (i != notaCreditoDetalleColeccion.size() - 1)
				asiento = notaCreditoAsientoAutomaticoHandlerLocal.generarAsientoAutomatico(notaCredito, notaCreditoDetalle, false, tipoDocumentoId, etapa, fechaAplicacion, valorAplica, tipoCartera, carteraAfectada);
			else if (i == notaCreditoDetalleColeccion.size() - 1)
				asiento = notaCreditoAsientoAutomaticoHandlerLocal.generarAsientoAutomatico(notaCredito, notaCreditoDetalle, true, tipoDocumentoId, etapa, fechaAplicacion, valorAplica, tipoCartera, carteraAfectada);
		}
		return asiento;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NotaCreditoEJB registrarNotaCredito(NotaCreditoIf model) {
		NotaCreditoEJB notaCredito = new NotaCreditoEJB();

		notaCredito.setId(model.getId());
		notaCredito.setOficinaId(model.getOficinaId());
		notaCredito.setTipoDocumentoId(model.getTipoDocumentoId());
		notaCredito.setCodigo(model.getCodigo());
		notaCredito.setOperadorNegocioId(model.getOperadorNegocioId());
		notaCredito.setMonedaId(model.getMonedaId());
		notaCredito.setUsuarioId(model.getUsuarioId());
		notaCredito.setFechaEmision(model.getFechaEmision());
		notaCredito.setFechaVencimiento(model.getFechaVencimiento());
		notaCredito.setFechaCaducidad(model.getFechaCaducidad());
		notaCredito.setPreimpreso(model.getPreimpreso());
		notaCredito.setAutorizacion(model.getAutorizacion());
		notaCredito.setReferencia(model.getReferencia());
		notaCredito.setValor(utilitariosLocal.redondeoValor(model.getValor()));
		notaCredito.setIva(utilitariosLocal.redondeoValor(model.getIva()));
		notaCredito.setIce(utilitariosLocal.redondeoValor(model.getIce()));
		notaCredito.setOtroImpuesto(utilitariosLocal.redondeoValor(model.getOtroImpuesto()));
		notaCredito.setObservacion(model.getObservacion());
		notaCredito.setEstado(model.getEstado());
		notaCredito.setTipoCartera(model.getTipoCartera());
		notaCredito.setReferenciaId(model.getReferenciaId());

		return notaCredito;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NotaCreditoDetalleEJB registrarNotaCreditoDetalle(NotaCreditoDetalleIf modelDetalle) {
		NotaCreditoDetalleEJB notaCreditoDetalle = new NotaCreditoDetalleEJB();

		notaCreditoDetalle.setId(modelDetalle.getId());
		notaCreditoDetalle.setDocumentoId(modelDetalle.getDocumentoId());
		notaCreditoDetalle.setNotaCreditoId(modelDetalle.getNotaCreditoId());
		notaCreditoDetalle.setProductoId(modelDetalle.getProductoId());
		notaCreditoDetalle.setCantidad(modelDetalle.getCantidad());
		notaCreditoDetalle.setValor(utilitariosLocal.redondeoValor(modelDetalle.getValor()));
		notaCreditoDetalle.setIva(utilitariosLocal.redondeoValor(modelDetalle.getIva()));
		notaCreditoDetalle.setIce(utilitariosLocal.redondeoValor(modelDetalle.getIce()));
		notaCreditoDetalle.setOtroImpuesto(utilitariosLocal.redondeoValor(modelDetalle.getOtroImpuesto()));
		notaCreditoDetalle.setDescripcion(modelDetalle.getDescripcion());
		notaCreditoDetalle.setTipoReferencia(modelDetalle.getTipoReferencia());
		notaCreditoDetalle.setReferenciaId(modelDetalle.getReferenciaId());
		notaCreditoDetalle.setOrdenId(modelDetalle.getOrdenId());
		notaCreditoDetalle.setTipoNota(modelDetalle.getTipoNota());
		notaCreditoDetalle.setObservacion(modelDetalle.getObservacion());
		notaCreditoDetalle.setTipoPresupuesto(modelDetalle.getTipoPresupuesto());
		notaCreditoDetalle.setPresupuestoId(modelDetalle.getPresupuestoId());
		notaCreditoDetalle.setOrdenId(modelDetalle.getOrdenId());
		
		return notaCreditoDetalle;
	}

	private Object[] registroCartera(NotaCreditoEJB notaCredito,List<NotaCreditoDetalleIf> modelDetalleList, Long idOficina) throws GenericBusinessException{
		CarteraEJB cartera = registrarCartera(notaCredito, idOficina);
		manager.persist(cartera);

		Map sumaValoresNotaCreditoDetallesMap = registrarCarteraDetalle(modelDetalleList, notaCredito ,cartera);
		Double sumaValoresNotaCreditoDetallesRedondeoTotal = (Double) sumaValoresNotaCreditoDetallesMap.get(REDONDEO_TOTAL);
		Double sumaValoresNotaCreditoDetallesRedondeoParcial = (Double) sumaValoresNotaCreditoDetallesMap.get(REDONDEO_PARCIAL);

		return new Object[]{cartera, sumaValoresNotaCreditoDetallesRedondeoTotal, sumaValoresNotaCreditoDetallesRedondeoParcial};
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CarteraEJB registrarCartera(NotaCreditoIf notaCredito, Long idOficina) {
		CarteraEJB cartera = new CarteraEJB();

		try {
			cartera.setTipo(notaCredito.getTipoCartera());
			cartera.setOficinaId(notaCredito.getOficinaId());
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(notaCredito.getTipoDocumentoId());
			cartera.setTipodocumentoId(tipoDocumento.getId());
			EmpresaIf empresa = empresaLocal.getEmpresa(tipoDocumento.getEmpresaId());
			OficinaIf oficina = oficinaLocal.getOficina(idOficina);
			ServidorIf servidor = (oficina.getServidorId()!=null)?servidorLocal.getServidor(oficina.getServidorId()):null;
			String monthCartera = utilitariosLocal.getMonthFromDate(notaCredito.getFechaEmision());
			String anioCartera = utilitariosLocal.getYearFromDate(notaCredito.getFechaEmision());
			String codigo = empresa.getCodigo() + "-";
			if (servidor!=null)
				codigo += servidor.getCodigo() + "-";
			codigo += tipoDocumento.getCodigo() + "-";
			nuevaCodificacionActiva = (Double.parseDouble(anioCartera) <= 2008)?false:true;
			if (nuevaCodificacionActiva)
				codigo += monthCartera + "-";
			//codigo += anioCartera + "-";
			codigo += notaCredito.getCodigo();
			cartera.setCodigo(codigo);
			cartera.setReferenciaId(notaCredito.getId());
			cartera.setClienteoficinaId(notaCredito.getOperadorNegocioId());
			cartera.setPreimpreso(notaCredito.getPreimpreso());
			cartera.setUsuarioId(notaCredito.getUsuarioId());
			cartera.setMonedaId(notaCredito.getMonedaId());
			cartera.setFechaEmision(notaCredito.getFechaEmision());

			double valor = notaCredito.getValor().doubleValue();
			double iva = notaCredito.getIva().doubleValue();
			double ice = notaCredito.getIce().doubleValue();
			double otrosImpuestos = notaCredito.getOtroImpuesto().doubleValue();
			double valorCartera = valor + iva + ice + otrosImpuestos;

			cartera.setValor(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorCartera)));
			cartera.setSaldo(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorCartera)));
			cartera.setEstado("N");
			cartera.setComentario(notaCredito.getObservacion());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}

		return cartera;
	}

	private Map registrarCarteraDetalle(List<NotaCreditoDetalleIf> modelDetalleList, NotaCreditoEJB notaCredito, CarteraEJB cartera) throws GenericBusinessException {
		Map valoresNotaCreditoDetalles = new HashMap();
		Double valorNotaCreditoDetallesRedondeoTotal = 0.0;
		Double valorNotaCreditoDetallesRedondeoParcial = 0.0;
		Double valorNotaCredito = 0.0;
		Double valorNotaCreditoSinRedondeo = 0.0;
		int secuencial = 0;
		Collection documentos = documentoLocal.findDocumentoByTipoDocumentoId(notaCredito.getTipoDocumentoId());
		Iterator itDocumentos = documentos.iterator();
		while (itDocumentos.hasNext()) {
			DocumentoIf documento = (DocumentoIf) itDocumentos.next();
			double valorCartera = 0D;

			for (NotaCreditoDetalleIf modelDetalle : modelDetalleList) {
				if (modelDetalle.getDocumentoId().compareTo(documento.getId()) == 0 && documento.getBonificacion().equals("N")) {
					double cantidad = modelDetalle.getCantidad().doubleValue();
					double valor = modelDetalle.getValor().doubleValue();
					double iva = modelDetalle.getIva().doubleValue();
					double ice = modelDetalle.getIce().doubleValue();
					double otrosImpuestos = modelDetalle.getOtroImpuesto().doubleValue();
					valorCartera = (cantidad * valor) + iva + ice + otrosImpuestos;
					valorNotaCredito += valorCartera;
					valorNotaCreditoSinRedondeo += valorCartera;
				}
			}

			if (valorNotaCredito > 0.0 && valorCartera > 0.0 && valorNotaCreditoSinRedondeo > 0.0) {
				CarteraDetalleData modelCarteraDetalle = new CarteraDetalleData();
				modelCarteraDetalle.setCarteraId(cartera.getPrimaryKey());
				modelCarteraDetalle.setDocumentoId(documento.getId());
				modelCarteraDetalle.setPreimpreso(notaCredito.getPreimpreso());
				modelCarteraDetalle.setFechaCreacion(utilitariosLocal.fromTimestampToSqlDate(notaCredito.getFechaEmision()));
				modelCarteraDetalle.setFechaCartera(utilitariosLocal.fromTimestampToSqlDate(notaCredito.getFechaEmision()));
				modelCarteraDetalle.setFechaVencimiento(utilitariosLocal.fromTimestampToSqlDate(notaCredito.getFechaVencimiento()));
				modelCarteraDetalle.setFechaUltimaActualizacion(utilitariosLocal.fromTimestampToSqlDate(notaCredito.getFechaEmision()));
				modelCarteraDetalle.setValor(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorNotaCredito)));
				modelCarteraDetalle.setSaldo(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorNotaCredito)));
				modelCarteraDetalle.setCartera("S");
				modelCarteraDetalle.setAutorizacion(notaCredito.getAutorizacion());
				modelCarteraDetalle.setSecuencial(++secuencial);
				CarteraDetalleEJB carteraDetalle = registrarCarteraDetalle(modelCarteraDetalle);
				valorNotaCreditoDetallesRedondeoTotal = modelCarteraDetalle.getValor().doubleValue();
				valorNotaCreditoDetallesRedondeoParcial = utilitariosLocal.redondeoValor(valorNotaCreditoSinRedondeo);
				manager.merge(carteraDetalle);
			}
		}

		valoresNotaCreditoDetalles.put(REDONDEO_TOTAL, valorNotaCreditoDetallesRedondeoTotal);
		valoresNotaCreditoDetalles.put(REDONDEO_PARCIAL, valorNotaCreditoDetallesRedondeoParcial);
		return valoresNotaCreditoDetalles;
	}

	public CarteraDetalleEJB registrarCarteraDetalle(CarteraDetalleIf modelDetalle) {
		CarteraDetalleEJB carteraDetalle = new CarteraDetalleEJB();				

		carteraDetalle.setId(modelDetalle.getId());
		carteraDetalle.setCarteraId(modelDetalle.getCarteraId());
		carteraDetalle.setDocumentoId(modelDetalle.getDocumentoId());
		carteraDetalle.setSecuencial(modelDetalle.getSecuencial());
		carteraDetalle.setLineaId(modelDetalle.getLineaId());
		carteraDetalle.setPreimpreso(modelDetalle.getPreimpreso());
		carteraDetalle.setFechaCreacion(modelDetalle.getFechaCreacion());
		carteraDetalle.setFechaCartera(modelDetalle.getFechaCartera());
		carteraDetalle.setFechaVencimiento(modelDetalle.getFechaVencimiento());
		carteraDetalle.setFechaUltimaActualizacion(modelDetalle.getFechaUltimaActualizacion());
		carteraDetalle.setValor(utilitariosLocal.redondeoValor(modelDetalle.getValor()));
		carteraDetalle.setSaldo(utilitariosLocal.redondeoValor(modelDetalle.getSaldo()));
		carteraDetalle.setCartera(modelDetalle.getCartera());
		carteraDetalle.setAutorizacion(modelDetalle.getAutorizacion());
		carteraDetalle.setSriSustentoTributarioId(modelDetalle.getSriSustentoTributarioId());

		return carteraDetalle;
	}

	private void verificacionValores(NotaCreditoEJB notaCredito, CarteraEJB cartera, Double valorNotaCreditoDetallesRedondeoTotal, Double valorNotaCreditoDetallesRedondeoParcial, AsientoIf asiento) throws GenericBusinessException {
		double totalNotaCredito = utilitariosLocal.redondeoValor(notaCredito.getValor().doubleValue() + notaCredito.getIva().doubleValue() 
				+ notaCredito.getIce().doubleValue() + notaCredito.getOtroImpuesto().doubleValue());
		if (  totalNotaCredito != valorNotaCreditoDetallesRedondeoTotal && totalNotaCredito != valorNotaCreditoDetallesRedondeoParcial )
			throw new GenericBusinessException("Error al guardar la nota de crédito!\nEl valor de la Nota de Crédito no coincide con el Detalle");

		if ( cartera.getValor().doubleValue() != valorNotaCreditoDetallesRedondeoTotal && cartera.getValor().doubleValue() != valorNotaCreditoDetallesRedondeoParcial )
			throw new GenericBusinessException("Error al guardar la nota de crédito!\nEl valor de la Cartera no coincide con los valores del Detalle de la Nota de Crédito.");

		if ( asiento != null && !asiento.getStatus().equals("_!#DuMbAsIeNtO_!#")) {
			double debe = 0.0; double haber = 0.0;
			Collection<AsientoDetalleIf> asientosDetalle = asientoDetalleLocal.findAsientoDetalleByAsientoId(asiento.getPrimaryKey());
			for ( Iterator itAsientos = asientosDetalle.iterator() ; itAsientos.hasNext() ; ){
				AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) itAsientos.next();
				debe += asientoDetalle.getDebe().doubleValue();
				haber += asientoDetalle.getHaber().doubleValue();
			}
			debe = utilitariosLocal.redondeoValor(debe);
			haber = utilitariosLocal.redondeoValor(haber);
			if ( debe != haber )
				throw new GenericBusinessException("Error al guardar la nota de crédito!\nEl valor del Debe y Haber no coinciden en el Asiento");
			else if (  debe != valorNotaCreditoDetallesRedondeoTotal && debe != valorNotaCreditoDetallesRedondeoParcial )
				throw new GenericBusinessException("Error al guardar la nota de crédito!\nEl valor del Debe y Haber no coinciden con el detalle de la Nota de Crédito");
		} else if (asiento == null) {
			int year = notaCredito.getFechaEmision().getYear() + 1900;
			int month = notaCredito.getFechaEmision().getMonth() + 1;
			if ((year == 2008 && month >= 9) || (year > 2008))
				throw new GenericBusinessException("No se gener\u00f3 el asiento de la Nota de Crédito");
		}
	}

	private int getCodigoNotaCredito(NotaCreditoIf model, Long idEmpresa) {
		String codigo = getMaximoCodigoNotaCredito(model.getCodigo(), idEmpresa);
		int codigoNotaCredito = 1;
		if (!codigo.equals("[null]")) {
			codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
			codigoNotaCredito = Integer.parseInt(codigo.split(model.getCodigo())[1]) + 1;
		}
		return codigoNotaCredito;
	}

	private String getMaximoCodigoNotaCredito(String codigoParcialNotaCredito, Long idEmpresa) {
		String queryString = "select max(nc.codigo) from NotaCreditoEJB nc, TipoDocumentoEJB td where nc.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa + " and nc.codigo like '" + codigoParcialNotaCredito + "%'";
		Query query = manager.createQuery(queryString);
		return query.getResultList().toString();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection getNotaCreditoByQueryList(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa) {
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct e from NotaCreditoEJB " + objectName + ", TipoDocumentoEJB td where " + where;
		queryString += " and e.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa + " order by e.codigo desc";
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
	public int getNotaCreditoByQueryListSize(Map aMap, java.lang.Long idEmpresa) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct count(*) from NotaCreditoEJB " 
			+ objectName + ", TipoDocumentoEJB td where " + where;

		queryString += " and e.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa;

		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}

	public Boolean verificarPreimpreso(Long operadorNegocioId, String preimpreso, String autorizacion, String tipoCartera) throws GenericBusinessException {
		if ( operadorNegocioId != null && preimpreso!= null && !preimpreso.equals("") ){
			Map aMap = new HashMap();
			aMap.put("preimpreso", preimpreso);
			aMap.put("autorizacion", autorizacion);
			if (tipoCartera.equals("P"))
				aMap.put("operadorNegocioId", operadorNegocioId);
			Collection<NotaCreditoIf> notasCredito = findNotaCreditoByQuery(aMap);
			if ( notasCredito.size() == 0 )
				return false;
			else if ( notasCredito.size() == 1 )
				return true;
			else if ( notasCredito.size() > 1 ){
				throw new GenericBusinessException("Preimpreso está duplicado");
			}
		}
		return true;
	}

	public Collection findNotasCredito(Long operadorNegocioId, Long documentoId, Long empresaId) throws GenericBusinessException {
		//select distinct nc.*, c.* from nota_credito nc, cartera c, cartera_detalle cd, tipo_documento td where td.ID = nc.TIPO_DOCUMENTO_ID and nc.TIPO_DOCUMENTO_ID = c.TIPODOCUMENTO_ID and c.REFERENCIA_ID = nc.ID and cd.CARTERA_ID = c.ID and cd.DOCUMENTO_ID = 140 and c.SALDO > 0 and nc.OPERADOR_NEGOCIO_ID = &a and td.EMPRESA_ID = 1 order by nc.PREIMPRESO asc
		String queryString = "select distinct nc, c from NotaCreditoEJB nc, CarteraEJB c, CarteraDetalleEJB cd, TipoDocumentoEJB td where nc.tipoDocumentoId = td.id and nc.tipoDocumentoId = c.tipodocumentoId and c.referenciaId = nc.id and cd.carteraId = c.id and cd.documentoId = " + documentoId + " and c.saldo > 0 and nc.operadorNegocioId = " + operadorNegocioId + " and td.empresaId = " + empresaId + " and nc.estado = 'A' order by nc.preimpreso asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	

	public java.util.Collection getNotaCreditoByQueryListFechas(Map aMap, java.lang.Long idEmpresa, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin) {
		 
		String objectName = "e";
		 
		String where = "";
		
		if (!aMap.isEmpty()) {
			where = QueryBuilder.buildWhere(aMap, objectName) + " and";
		}
		
		String queryString = "select distinct e from NotaCreditoEJB " + objectName + ", TipoDocumentoEJB td where " + where;
		queryString += " e.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa + " and e.fechaEmision >= :fechaInicio and e.fechaEmision <= :fechaFin";
	 
		
		String orderByPart = "";
		orderByPart += " order by e.codigo asc";
		queryString += orderByPart;
		
		
		Query query = manager.createQuery(queryString);

		fechaInicio = utilitariosLocal.resetTimestampStartDate(fechaInicio);
		query.setParameter("fechaInicio", fechaInicio);
		fechaFin = utilitariosLocal.resetTimestampEndDate(fechaFin);
		query.setParameter("fechaFin", fechaFin);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		 
		return query.getResultList();
	}
	
	  
	public List<AsientoIf> cruzarNotasCredito(List<NotaCreditoIf> notaCreditoSeleccionadaColeccion, Map carteraNotaCreditoMap, Map valorAplicaMap, CarteraIf carteraAfectada, java.sql.Date fechaAplicacion) throws GenericBusinessException {
		
		List<AsientoIf> asientosList = new ArrayList<AsientoIf>();
		
		try {			
			for (int i=0; i<notaCreditoSeleccionadaColeccion.size(); i++) {
				NotaCreditoIf notaCredito = (NotaCreditoIf) notaCreditoSeleccionadaColeccion.get(i);
				CarteraIf carteraNotaCredito = (CarteraIf) carteraNotaCreditoMap.get(notaCredito.getId());
				double valorAplica = ((Double) valorAplicaMap.get(notaCredito.getId())).doubleValue();
				Iterator carteraDetalleAfectadaIt = carteraDetalleLocal.findCarteraDetalleByCarteraId(carteraAfectada.getId()).iterator();
				if (carteraDetalleAfectadaIt.hasNext()) {
					CarteraDetalleIf carteraDetalleAfectada = (CarteraDetalleIf) carteraDetalleAfectadaIt.next();
					Iterator carteraDetalleNotaCreditoIt = carteraDetalleLocal.findCarteraDetalleByCarteraId(carteraNotaCredito.getId()).iterator();
					if (carteraDetalleNotaCreditoIt.hasNext()) {
						CarteraDetalleIf carteraDetalleNotaCredito = (CarteraDetalleIf) carteraDetalleNotaCreditoIt.next();
						if (carteraDetalleNotaCredito.getCartera().equals("S")) {
							carteraDetalleNotaCredito.setSaldo(utilitariosLocal.redondeoValor(carteraDetalleNotaCredito.getSaldo().subtract(BigDecimal.valueOf(valorAplica))));
							manager.merge(carteraDetalleNotaCredito);
							carteraDetalleAfectada.setSaldo(utilitariosLocal.redondeoValor(carteraDetalleAfectada.getSaldo().subtract(BigDecimal.valueOf(valorAplica))));
							manager.merge(carteraDetalleAfectada);
							CarteraAfectaIf modelCarteraAfecta = new CarteraAfectaEJB();
							modelCarteraAfecta.setCarteradetalleId(carteraDetalleNotaCredito.getPrimaryKey());
							modelCarteraAfecta.setCarteraafectaId(carteraDetalleAfectada.getPrimaryKey());
							modelCarteraAfecta.setUsuarioId(carteraNotaCredito.getUsuarioId());
							modelCarteraAfecta.setValor(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorAplica)));
							modelCarteraAfecta.setFechaCreacion(carteraDetalleNotaCredito.getFechaCreacion());
							modelCarteraAfecta.setFechaAplicacion(fechaAplicacion);
							modelCarteraAfecta.setCartera(carteraDetalleNotaCredito.getCartera());
							CarteraAfectaEJB carteraAfecta = registrarCarteraAfecta(modelCarteraAfecta);
							manager.merge(carteraAfecta);
						}
					}
				}

				manager.merge(carteraAfectada);
				manager.merge(carteraNotaCredito);
				
				
				
				//en caso de que la nota sea para anulacion se libera el presupuesto seteando estado Anulado en las tablas correspondientes
				
				Collection notasCreditoDetalle = notaCreditoDetalleLocal.findNotaCreditoDetalleByNotaCreditoId(notaCredito.getId());
				Iterator notasCreditoDetalleIt = notasCreditoDetalle.iterator();
				while(notasCreditoDetalleIt.hasNext()){
					NotaCreditoDetalleIf notaCreditoDetalleIf = (NotaCreditoDetalleIf)notasCreditoDetalleIt.next();
					
					if(notaCreditoDetalleIf.getTipoReferencia().equals("F") 
							&& notaCreditoDetalleIf.getTipoNota().equals("A")
							&& notaCreditoDetalleIf.getReferenciaId().compareTo(carteraAfectada.getReferenciaId()) == 0){ // ANULAR FACTURA
						Collection presupuestosFacturacion = presupuestoFacturacionLocal.findPresupuestoFacturacionByFacturaId(notaCreditoDetalleIf.getReferenciaId());
						//si es presupuesto
						if(presupuestosFacturacion.size() > 0){
							Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
							while(presupuestosFacturacionIt.hasNext()){
								PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
								presupuestoFacturacionIf.setEstado("A"); //ANULADO
								manager.merge(presupuestoFacturacionIf);
							}
							
							//seteo como aprobado al presupuesto para que vuelva a ser facturado
							if(notaCreditoDetalleIf.getPresupuestoId() != null){
								PresupuestoIf presupuestoIf = presupuestoLocal.getPresupuesto(notaCreditoDetalleIf.getPresupuestoId());
								presupuestoIf.setEstado("A"); //APROBADO
								manager.merge(presupuestoIf);
							}
							
						}
						//si es plan de medios
						else{
							FacturaIf facturaIf = facturaLocal.getFactura(notaCreditoDetalleIf.getReferenciaId());
							Collection planMedioFacturacion = planMedioFacturacionLocal.findPlanMedioFacturacionByPedidoId(facturaIf.getPedidoId());
							Iterator planMedioFacturacionIt = planMedioFacturacion.iterator();
							while(planMedioFacturacionIt.hasNext()){
								PlanMedioFacturacionIf planMedioFacturacionIf = (PlanMedioFacturacionIf)planMedioFacturacionIt.next();
								planMedioFacturacionIf.setEstado("A"); //ANULADO
								manager.merge(planMedioFacturacionIf);
							}
							
							Collection planMedioFormaPago = planMedioFormaPagoLocal.findPlanMedioFormaPagoByPedidoId(facturaIf.getPedidoId());
							Iterator planMedioFormaPagoIt = planMedioFormaPago.iterator();
							while(planMedioFormaPagoIt.hasNext()){
								PlanMedioFormaPagoIf planMedioFormaPagoIf = (PlanMedioFormaPagoIf)planMedioFormaPagoIt.next();
								planMedioFormaPagoIf.setEstado("A"); //ANULADO
								manager.merge(planMedioFormaPagoIf);
							}
							
							//seteo como aprobado al plan de medio para que vuelva a ser facturado
							if(notaCreditoDetalleIf.getPresupuestoId() != null){
								PlanMedioIf planMedioIf = planMedioLocal.getPlanMedio(notaCreditoDetalleIf.getPresupuestoId());
								planMedioIf.setEstado("A"); //APROBADO
								manager.merge(planMedioIf);
							}
						}
					}
				}
				
				
				
				

				int year = fechaAplicacion.getYear() + 1900;
				int month = fechaAplicacion.getMonth() + 1;
				AsientoIf asiento = null;
				long etapa = 2L;

				if ((year == 2008 && month >= 9) || (year > 2008)) {
					Iterator it = notaCreditoDetalleLocal.findNotaCreditoDetalleByNotaCreditoId(notaCredito.getId()).iterator();
					Vector<NotaCreditoDetalleIf> notaCreditoDetalleColeccion = new Vector<NotaCreditoDetalleIf>();
					while (it.hasNext()) {
						NotaCreditoDetalleIf notaCreditoDetalle = (NotaCreditoDetalleIf) it.next();
						notaCreditoDetalleColeccion.add(notaCreditoDetalle);
					}
					asiento = generarAsientoNotaCredito(notaCredito.getTipoDocumentoId(), (NotaCreditoEJB) notaCredito, notaCreditoDetalleColeccion, etapa, fechaAplicacion, valorAplica, notaCredito.getTipoCartera(), carteraAfectada);
					asientosList.add(asiento);
				}
			}
		} catch (GenericBusinessException e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			log.error("Error al guardar información", e);
			throw new GenericBusinessException(e.getMessage());
		}
		
		return asientosList;
	}

	private CarteraAfectaEJB registrarCarteraAfecta(CarteraAfectaIf modelAfecta) {
		CarteraAfectaEJB carteraAfecta = new CarteraAfectaEJB();

		carteraAfecta.setId(modelAfecta.getId());
		carteraAfecta.setCarteradetalleId(modelAfecta.getCarteradetalleId());
		carteraAfecta.setCarteraafectaId(modelAfecta.getCarteraafectaId());
		carteraAfecta.setUsuarioId(modelAfecta.getUsuarioId());
		carteraAfecta.setValor(utilitariosLocal.redondeoValor(modelAfecta.getValor()));
		carteraAfecta.setFechaCreacion(modelAfecta.getFechaCreacion());
		carteraAfecta.setFechaAplicacion(modelAfecta.getFechaAplicacion());
		carteraAfecta.setCartera(modelAfecta.getCartera());

		return carteraAfecta;
	}

	public Collection findNotaCreditoByEmpresaId(Long idEmpresa) throws GenericBusinessException {
		String queryString = "select distinct nc from NotaCreditoEJB nc, TipoDocumentoEJB td where nc.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection getNotaCreditoNoAnuladaList(int startIndex,
			int endIndex, Map aMap, java.lang.Long idEmpresa) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct e from NotaCreditoEJB " + objectName
		+ ", TipoDocumentoEJB td where " + where;
		queryString += " and e.tipoDocumentoId = td.id and td.empresaId = "
			+ idEmpresa + " and e.estado <> 'N'";
		queryString += " order by e.preimpreso asc";
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
	public int getNotaCreditoNoAnuladaListSize(Map aMap, java.lang.Long idEmpresa) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct count(*) from NotaCreditoEJB "
			+ objectName + ", TipoDocumentoEJB td where " + where;
		queryString += " and e.tipoDocumentoId = td.id and td.empresaId = "
			+ idEmpresa + " and e.estado <> 'N'";
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}
	
	public java.util.Collection getNotaCreditoByMapFechaInicioFechaFin(Map aMap,Date fechaInicio,Date fechaFin, java.lang.Long idEmpresa) 
	throws com.spirit.exception.GenericBusinessException{
		try{
			String objectName = "e";
			String queryString = "select distinct e from NotaCreditoEJB " + objectName + ", TipoDocumentoEJB td where " ;
	
			queryString += " e.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa ;
			 if (fechaInicio!=null && fechaFin!=null){
				 queryString += " and e.fechaEmision >= :fechaInicio and e.fechaEmision <= :fechaFin";
			 }
			 
			 if (aMap!=null && aMap.size()>0) {
				 String where = QueryBuilder.buildWhere(aMap, objectName);
				 queryString += (" and  "+where);
			 }
			 
			 queryString += " order by e.codigo asc";
			
			Query query = manager.createQuery(queryString);
			 
			if (aMap!=null){
				Iterator it = aMap.keySet().iterator();
				while (it.hasNext()) {
					String propertyKey = (String) it.next();
					Object property = aMap.get(propertyKey);
					query.setParameter(propertyKey, property);
				}
			}
			
			query.setParameter("fechaInicio",fechaInicio);
			query.setParameter("fechaFin",fechaFin);
			return query.getResultList();
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al consultar Compras por fecha ");
		}
	}
	
	public java.util.Collection findNotaCreditoNotaCreditoDetalleByClienteOficinaIdByTipoPresupuestoByPresupuestoIdByOrdenId(Long idClienteOficina, String tipoPresupuesto, Long idPresupuesto, Long idOrden) 
	throws com.spirit.exception.GenericBusinessException{
		try{
			String queryString = "select distinct nc, ncd from NotaCreditoEJB nc, NotaCreditoDetalleEJB ncd where nc.id = ncd.notaCreditoId";
	
			if(tipoPresupuesto != null && !tipoPresupuesto.equals("")){
				queryString += " and ncd.tipoPresupuesto = '" + tipoPresupuesto +"'";		
			}
					
			if(idClienteOficina != null && idClienteOficina != 0){
				queryString += " and nc.operadorNegocioId = " + idClienteOficina;		
			}
			
			if(idPresupuesto != null && idPresupuesto != 0){
				queryString += " and ncd.presupuestoId = " + idPresupuesto;		
			}
			
			if(idOrden != null && idOrden != 0){
				queryString += " and ncd.ordenId = " + idOrden;		
			}
				 
			queryString += " order by nc.id asc";
			
			Query query = manager.createQuery(queryString);
			
			return query.getResultList();
			
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al consultar Compras por fecha ");
		}
	}
}
