package com.spirit.cartera.handler;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.NotaCreditoDetalleIf;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.contabilidad.entity.AsientoData;
import com.spirit.contabilidad.entity.AsientoDetalleData;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoEJB;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaEntidadIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.PlantillaIf;
import com.spirit.contabilidad.session.AsientoSessionLocal;
import com.spirit.contabilidad.session.CuentaEntidadSessionLocal;
import com.spirit.contabilidad.session.CuentaSessionLocal;
import com.spirit.contabilidad.session.EventoContableSessionLocal;
import com.spirit.contabilidad.session.PeriodoSessionLocal;
import com.spirit.contabilidad.session.PlanCuentaSessionLocal;
import com.spirit.contabilidad.session.PlantillaSessionLocal;
import com.spirit.contabilidad.session.SubTipoAsientoSessionLocal;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.session.ClienteOficinaSessionLocal;
import com.spirit.crm.session.ClienteSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.session.DocumentoSessionLocal;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.TipoDocumentoSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.session.GenericoSessionLocal;
import com.spirit.inventario.session.ProductoSessionLocal;

@Stateless
public class NotaCreditoAsientoAutomaticoHandler implements NotaCreditoAsientoAutomaticoHandlerLocal {
	
	@PersistenceContext(unitName = "spirit")
	
	@EJB private GenericoSessionLocal genericoLocal;
	@EJB private ProductoSessionLocal productoLocal;
	@EJB private CuentaEntidadSessionLocal cuentaEntidadLocal;
	@EJB private AsientoSessionLocal asientoLocal;
	@EJB private PeriodoSessionLocal periodoLocal;
	@EJB private CuentaSessionLocal cuentaLocal;
	@EJB private ClienteOficinaSessionLocal clienteOficinaLocal;
	@EJB private ClienteSessionLocal clienteLocal;
	@EJB private PlantillaSessionLocal plantillaLocal;
	@EJB private PlanCuentaSessionLocal planCuentaLocal;
	@EJB private SubTipoAsientoSessionLocal subTipoAsientoLocal;
	@EJB private EmpresaSessionLocal empresaLocal;
	@EJB private EventoContableSessionLocal eventoContableLocal;
	@EJB private DocumentoSessionLocal documentoLocal;
	@EJB private TipoDocumentoSessionLocal tipoDocumentoLocal;
	@EJB private UtilitariosSessionLocal utilitariosLocal;
	
	private PlanCuentaIf planCuenta = null;
	private PeriodoIf periodo = null;
	private static List<AsientoDetalleIf> asientoDetalleColeccion = null;
	private EventoContableIf eventoContable = null;
	private Object referenceBean = null;
	private Map parameterMap = null;
	private boolean procesarAsiento = false;
	private java.sql.Date fechaAplicacion = null;
	private Long empresaId = 0L;
	private Long oficinaId = 0L;
	private static final String TIPO_CARTERA_CLIENTE = "C";
	private static final String TIPO_CARTERA_PROVEEDOR = "P";
	
	public AsientoIf generarAsientoAutomatico(NotaCreditoIf notaCredito, NotaCreditoDetalleIf notaCreditoDetalle, boolean procesarAsiento, Long tipoDocumentoId, long etapa, java.sql.Date fechaAplicacion, double valorAplica, String tipoCartera, CarteraIf carteraAfectada) throws GenericBusinessException {
		AsientoIf asientoGenerado = null;
		Map parameterMap = new HashMap();
		DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(notaCreditoDetalle.getDocumentoId());
		
		if (!documento.getCodigo().equals("NCCG") && etapa <= 1L) {
			asientoGenerado = new AsientoEJB();
			asientoGenerado.setStatus("_!#DuMbAsIeNtO_!#");
		}
		
		if (tipoCartera.equals(TIPO_CARTERA_PROVEEDOR)) {
			//estas validaciones hacen que la notas de credito NCCG y NCPR solo generen asientos cuando se crean y no cuando se cruzan
			/*if (documento != null 
					&& (documento.getCodigo().equals("NCCG") 
							|| (documento.getCodigo().equals("NCPR") 
									&& etapa < 2L) 
							|| (!documento.getCodigo().equals("NCCG") 
									&& !documento.getCodigo().equals("NCPR") 
									&& etapa == 2L))) {*/
			
			//Para Proveedor hay 3 documentos, el asiento se realiza cuando se crea la nota de crédito
			//solo el documento NCCG tiene un asiento cuando se crea y otro cuando se cruza la nota de crédito.
			if (documento != null 
					&& (documento.getCodigo().equals("NCCG") 
							|| (documento.getCodigo().equals("NCPR") && etapa < 2L)
							|| (documento.getCodigo().equals("NCCR") && etapa < 2L))){			
			
				Map aMap = new HashMap();
				aMap.put("documentoId", documento.getId());
				if (etapa > 0 && documento.getCodigo().equals("NCCG"))
					aMap.put("etapa", etapa);
				Iterator eventoContableIterator = eventoContableLocal.findEventoContableByQuery(aMap).iterator();
				if (eventoContableIterator.hasNext()) {
					EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator.next();;
					if (eventoContable != null) {
						Double iva = Double.valueOf(notaCreditoDetalle.getIva().doubleValue());
						Double ice = 0D;
						if (notaCreditoDetalle.getIce() != null)
							ice = Double.valueOf(notaCreditoDetalle.getIce().doubleValue());
						Double valor = Double.valueOf(notaCreditoDetalle.getValor().doubleValue());
						double cantidad = notaCreditoDetalle.getCantidad().doubleValue();
						double subtotal = cantidad * valor;
						double otroImpuesto = Double.valueOf(notaCreditoDetalle.getOtroImpuesto().doubleValue());
						Double total = subtotal + iva + ice + otroImpuesto;
						if (valorAplica > 0D && !documento.getCodigo().equals("NCPR"))
							total = valorAplica;
						parameterMap.clear();
						parameterMap.put("OFICINA_ID", notaCredito.getOficinaId());
						parameterMap.put("BEAN", notaCredito);
						parameterMap.put("OBSERVACION", "NOTA DE CREDITO");
						
						if(documento.getCodigo().equals("NCPR")) {
							parameterMap.put("COSTO", total - iva);
							parameterMap.put("IVA", iva);
						} else if (documento.getCodigo().equals("NCCR")) {
							parameterMap.put("REEMBOLSO", total);
						}
						
						parameterMap.put("OTROS_INGRESOS", total);
						
						if(documento.getCodigo().equals("NCCG")) {
							parameterMap.put("IVA", iva);
							parameterMap.put("OTROS_INGRESOS", total-iva);
						}
						
						parameterMap.put("ANTICIPO", total);
						parameterMap.put("CTAXPAG", total);
						parameterMap.put("PRODUCTO_ID", notaCreditoDetalle.getProductoId());
						parameterMap.put("OPERADOR_NEGOCIO_ID", notaCredito.getOperadorNegocioId());
						parameterMap.put("TIPO_DOCUMENTO_ID", tipoDocumentoId);
						TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tipoDocumentoLocal.getTipoDocumento(tipoDocumentoId);
						parameterMap.put("TIPO_DOCUMENTO_CODIGO", tipoDocumento.getCodigo());
						parameterMap.put("FECHA_APLICACION", fechaAplicacion);
						return procesarNotaCreditoAsientoAutomaticoHandler(eventoContable, parameterMap, procesarAsiento);
					}
				}
			}
		} 
		else if (tipoCartera.equals(TIPO_CARTERA_CLIENTE)) {
			if ((etapa == 1L && !documento.getCodigo().equals("NCAF")) 
					|| (documento.getCodigo().equals("NCAC")) 
					|| (etapa == 2L && documento.getCodigo().equals("NCAF"))) {
				
				Map aMap = new HashMap();
				aMap.put("documentoId", documento.getId());
				
				if (documento.getCodigo().equals("NCAC"))
					aMap.put("etapa", etapa);
				
				//if (etapa == 2L || (documento.getCodigo().equals("NCAC"))) {
								
				Iterator eventoContableIterator = eventoContableLocal.findEventoContableByQuery(aMap).iterator();
				if (eventoContableIterator.hasNext()) {
					EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator.next();
					if (eventoContable != null) {
						Double iva = Double.valueOf(notaCreditoDetalle.getIva().doubleValue());
						Double ice = 0D;
						if (notaCreditoDetalle.getIce() != null)
							ice = Double.valueOf(notaCreditoDetalle.getIce().doubleValue());
						Double valor = Double.valueOf(notaCreditoDetalle.getValor().doubleValue());
						double cantidad = notaCreditoDetalle.getCantidad().doubleValue();
						double subtotal = cantidad * valor;
						double otroImpuesto = Double.valueOf(notaCreditoDetalle.getOtroImpuesto().doubleValue());
						Double total = subtotal + iva + ice + otroImpuesto;
						
						parameterMap.clear();
						parameterMap.put("OFICINA_ID", notaCredito.getOficinaId());
						parameterMap.put("BEAN", notaCredito);
						parameterMap.put("OBSERVACION", "NOTA DE CREDITO");
						parameterMap.put("CTAXCOB", total);
						parameterMap.put("IVA", iva);
						
						//DESCUENTO PRONTO PAGO, 		DEPP	IVA
						//N/C FACTURA AL EXTERIOR, 		NCFE	
						//N/C FACTURA DE REEMBOLSO, 	NCFR
						//N/C POR ANTICIPO CLIENTE, 	NCAC	IVA
						//N/C POR ANULACION DE FACTURA, NCAF	IVA
						//NOTA DE CREDITO CLIENTE, 		NCFA	IVA
						if(documento.getCodigo().equals("DEPP")){
							parameterMap.put("REEMBOLSO", 0D);
							parameterMap.put("DESCUENTO", total - iva);
						}
						if(documento.getCodigo().equals("NCFA")){
							parameterMap.put("REEMBOLSO", 0D);
							parameterMap.put("INGRESO", total - iva);
						}
						if(documento.getCodigo().equals("NCFE")){
							parameterMap.put("REEMBOLSO", 0D);
							parameterMap.put("INGRESO", total);
						}
						if(documento.getCodigo().equals("NCFR")){
							parameterMap.put("REEMBOLSO", total);
						}
						if(documento.getCodigo().equals("NCAC")){
							parameterMap.put("REEMBOLSO", 0D);
							parameterMap.put("INGRESO", total - iva);
							parameterMap.put("ANTICIPOCL", total);
						}
						if(documento.getCodigo().equals("NCAF")){
							if(carteraAfectada != null){
								TipoDocumentoIf tipoDocumentoAfectado = (TipoDocumentoIf) tipoDocumentoLocal.getTipoDocumento(carteraAfectada.getTipodocumentoId());
								if(tipoDocumentoAfectado.getReembolso().equals("S")){
									parameterMap.put("REEMBOLSO", total);
									parameterMap.put("INGRESO", 0D);
									parameterMap.put("IVA", 0D);
								}else {
									parameterMap.put("REEMBOLSO", 0D);
									parameterMap.put("INGRESO", total - iva);
								}
							}
						}
												
						parameterMap.put("PRODUCTO_ID", notaCreditoDetalle.getProductoId());
						parameterMap.put("OPERADOR_NEGOCIO_ID", notaCredito.getOperadorNegocioId());
						parameterMap.put("TIPO_DOCUMENTO_ID", tipoDocumentoId);
						TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tipoDocumentoLocal.getTipoDocumento(tipoDocumentoId);
						parameterMap.put("TIPO_DOCUMENTO_CODIGO", tipoDocumento.getCodigo());
						parameterMap.put("FECHA_APLICACION", fechaAplicacion);
						ClienteOficinaIf operadorNegocioOficina = clienteOficinaLocal.getClienteOficina(notaCredito.getOperadorNegocioId());
						ClienteIf operadorNegocio = clienteLocal.getCliente(operadorNegocioOficina.getClienteId());
						parameterMap.put("ID_CLIENTE", operadorNegocio.getId());
						return procesarNotaCreditoAsientoAutomaticoHandler(eventoContable, parameterMap, procesarAsiento);
					}
				}
			}
		} /*else if (tipoCartera.equals(TIPO_CARTERA_CLIENTE)) {
			if (etapa == 2L || (documento.getCodigo().equals("NCAC"))) {
				Map aMap = new HashMap();
				aMap.put("documentoId", documento.getId());
				if (documento.getCodigo().equals("NCAC"))
					aMap.put("etapa", etapa);
				Iterator eventoContableIterator = eventoContableLocal.findEventoContableByQuery(aMap).iterator();
				if (eventoContableIterator.hasNext()) {
					EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator.next();
					if (eventoContable != null) {
						Double iva = Double.valueOf(notaCreditoDetalle.getIva().doubleValue());
						Double ice = 0D;
						if (notaCreditoDetalle.getIce() != null)
							ice = Double.valueOf(notaCreditoDetalle.getIce().doubleValue());
						Double valor = Double.valueOf(notaCreditoDetalle.getValor().doubleValue());
						double cantidad = notaCreditoDetalle.getCantidad().doubleValue();
						double subtotal = cantidad * valor;
						double otroImpuesto = Double.valueOf(notaCreditoDetalle.getOtroImpuesto().doubleValue());
						Double total = subtotal + iva + ice + otroImpuesto;
						
						parameterMap.clear();
						parameterMap.put("OFICINA_ID", notaCredito.getOficinaId());
						parameterMap.put("BEAN", notaCredito);
						parameterMap.put("OBSERVACION", "NOTA DE CREDITO");
						if (!documento.getCodigo().equals("NCAF")) {
							parameterMap.put("CTAXCOB", (total));
							parameterMap.put("IVA", iva);
							if (documento.getCodigo().equals("NCFR"))
								parameterMap.put("REEMBOLSO", total);
							else {
								parameterMap.put("REEMBOLSO", 0D);
								if (documento.getCodigo().equals("NCFE"))
									parameterMap.put("INGRESO", total);
								else
									parameterMap.put("INGRESO", total - iva);
								if (documento.getCodigo().equals("NCAC"))
									parameterMap.put("ANTICIPOCL", total);
							}
						} else {
							if (carteraAfectada != null) {
								TipoDocumentoIf tipoDocumentoAfectado = (TipoDocumentoIf) tipoDocumentoLocal.getTipoDocumento(carteraAfectada.getTipodocumentoId());
								parameterMap.put("CTAXCOB", total);
								parameterMap.put("IVA", iva);

								if (tipoDocumentoAfectado.getReembolso().equals("S")) {
									parameterMap.put("REEMBOLSO", total);
									parameterMap.put("INGRESO", 0D);
									parameterMap.put("IVA", 0D);
								} else {
									parameterMap.put("REEMBOLSO", 0D);
									if (tipoDocumentoAfectado.getCodigo().equals("FAE")) {
										parameterMap.put("IVA", 0D);
										parameterMap.put("INGRESO", total);
									} else
										parameterMap.put("INGRESO", total - iva);
								}
							}
						}
						parameterMap.put("PRODUCTO_ID", notaCreditoDetalle.getProductoId());
						parameterMap.put("OPERADOR_NEGOCIO_ID", notaCredito.getOperadorNegocioId());
						parameterMap.put("TIPO_DOCUMENTO_ID", tipoDocumentoId);
						TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tipoDocumentoLocal.getTipoDocumento(tipoDocumentoId);
						parameterMap.put("TIPO_DOCUMENTO_CODIGO", tipoDocumento.getCodigo());
						parameterMap.put("FECHA_APLICACION", fechaAplicacion);
						ClienteOficinaIf operadorNegocioOficina = clienteOficinaLocal.getClienteOficina(notaCredito.getOperadorNegocioId());
						ClienteIf operadorNegocio = clienteLocal.getCliente(operadorNegocioOficina.getClienteId());
						parameterMap.put("ID_CLIENTE", operadorNegocio.getId());
						return procesarNotaCreditoAsientoAutomaticoHandler(eventoContable, parameterMap, procesarAsiento);
					}
				}
			}
		}*/
		
		return asientoGenerado;
	}
	
	public AsientoIf procesarNotaCreditoAsientoAutomaticoHandler(EventoContableIf eventoContable, Map parameterMap, boolean procesarAsiento)
	throws GenericBusinessException {
		this.eventoContable = eventoContable;
		
		try {
			this.planCuenta = planCuentaLocal.getPlanCuenta(eventoContable.getPlanCuentaId());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error al obtener Plan de Cuenta de Evento Contable");
		}
		
		this.referenceBean = parameterMap.get("BEAN");
		this.parameterMap = parameterMap;
		this.procesarAsiento = procesarAsiento;
		this.empresaId = eventoContable.getEmpresaId();
		this.oficinaId = (Long) parameterMap.get("OFICINA_ID");
		this.fechaAplicacion = (java.sql.Date) parameterMap.get("FECHA_APLICACION");
		
		return generarAsientoContable();
	}
	
	public AsientoIf generarAsientoContable() throws GenericBusinessException {
		AsientoIf asientoRetornar = null;
		try {
			Collection plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			Iterator plantillasIterator = plantillas.iterator();
			CuentaIf cuenta = null;
			
			while (plantillasIterator.hasNext()) {
				PlantillaIf plantilla = (PlantillaIf) plantillasIterator.next();
				cuenta = cuentaLocal.getCuenta(plantilla.getCuentaId());
				Map parameterMap = new HashMap();
				
				if (cuenta.getImputable().equals("N")) {
					if (plantilla.getNemonico().equals("ANTICIPO")) {
						Long idOperadorNegocio = (Long) this.parameterMap.get("OPERADOR_NEGOCIO_ID");
						ClienteOficinaIf operadorNegocioOficina = clienteOficinaLocal.getClienteOficina(idOperadorNegocio);
						ClienteIf operadorNegocio = clienteLocal.getCliente(operadorNegocioOficina.getClienteId());
						parameterMap.put("entidadId", operadorNegocio.getId());
						parameterMap.put("tipoEntidad", "P");
						parameterMap.put("nemonico", "ANTICIPO");
					}
					
					if (plantilla.getNemonico().equals("CTAXCOB")) {
						Long idOperadorNegocio = (Long) this.parameterMap.get("OPERADOR_NEGOCIO_ID");
						ClienteOficinaIf operadorNegocioOficina = clienteOficinaLocal.getClienteOficina(idOperadorNegocio);
						ClienteIf operadorNegocio = clienteLocal.getCliente(operadorNegocioOficina.getClienteId());
						parameterMap.put("entidadId", operadorNegocio.getId());
						parameterMap.put("tipoEntidad", "C");
						parameterMap.put("nemonico", "CTAXCOB");
					}
					
					if (plantilla.getNemonico().equals("CTAXPAG")) {
						Long idOperadorNegocio = (Long) this.parameterMap.get("OPERADOR_NEGOCIO_ID");
						ClienteOficinaIf operadorNegocioOficina = clienteOficinaLocal.getClienteOficina(idOperadorNegocio);
						ClienteIf operadorNegocio = clienteLocal.getCliente(operadorNegocioOficina.getClienteId());
						parameterMap.put("entidadId", operadorNegocio.getId());
						parameterMap.put("tipoEntidad", "P");
						parameterMap.put("nemonico", "CTAXPAG");
					}
					
					if (plantilla.getNemonico().equals("COSTO")) {
						Long idProducto = (Long) this.parameterMap.get("PRODUCTO_ID");
						ProductoIf producto = productoLocal.getProducto(idProducto);
						GenericoIf generico = genericoLocal.getGenerico(producto.getGenericoId());
						parameterMap.put("entidadId", idProducto);
						parameterMap.put("tipoEntidad", "I");
						parameterMap.put("oficinaId", this.oficinaId);
						if (generico != null) {
							if (generico.getMediosProduccion().equals("M") || generico.getMediosProduccion().equals("P"))
								parameterMap.put("nemonico", "COSTO");
							else if (generico.getMediosProduccion().equals("G"))
								parameterMap.put("nemonico", "GASTO");
						}
						Iterator cuentaEntidadIterator = cuentaEntidadLocal.findCuentaEntidadByQuery(parameterMap).iterator();
						if (!cuentaEntidadIterator.hasNext()) {
							parameterMap.put("entidadId", generico.getId());
							parameterMap.put("tipoEntidad", "G");
						}
					}
					
					if (plantilla.getNemonico().equals("INGRESO")) {
						Long idProducto = (Long) this.parameterMap.get("PRODUCTO_ID");
						ProductoIf producto = productoLocal.getProducto(idProducto);
						GenericoIf generico = genericoLocal.getGenerico(producto.getGenericoId());
						parameterMap.put("entidadId", idProducto);
						parameterMap.put("tipoEntidad", "I");
						parameterMap.put("oficinaId", this.oficinaId);
						parameterMap.put("nemonico", "INGRESO");
						Iterator cuentaEntidadIterator = cuentaEntidadLocal.findCuentaEntidadByQuery(parameterMap).iterator();
						if (!cuentaEntidadIterator.hasNext()) {
							parameterMap.put("entidadId", generico.getId());
							parameterMap.put("tipoEntidad", "G");
						}
					}
					
					if (plantilla.getNemonico().equals("REEMBOLSO")) {
						Long idCliente = (Long) this.parameterMap.get("ID_CLIENTE");
						if (idCliente != null) {
							parameterMap.put("entidadId", idCliente);
							parameterMap.put("tipoEntidad", "C");
							parameterMap.put("nemonico", "REEMBOLSO");
						}
					}
					
					Iterator cuentaEntidadIterator = cuentaEntidadLocal.findCuentaEntidadByQuery(parameterMap).iterator();
					if (cuentaEntidadIterator.hasNext()) {
						CuentaEntidadIf cuentaEntidad = (CuentaEntidadIf) cuentaEntidadIterator.next();
						cuenta = cuentaLocal.getCuenta(cuentaEntidad.getCuentaId());
					}
				}
			
				if (cuenta == null) {
					if (plantilla.getCuentaPredeterminadaId() != null)
						cuenta = cuentaLocal.getCuenta(plantilla.getCuentaPredeterminadaId());
					else
						throw new GenericBusinessException("No existe cuenta asociada para nemónico " + (String) parameterMap.get("NEMONICO"));
				}
				
				if (cuenta != null) {
					AsientoDetalleIf asientoDetalle = new AsientoDetalleData();
					asientoDetalle.setCuentaId(cuenta.getId());
					String numeroNotaCredito = ((NotaCreditoIf) referenceBean).getPreimpreso();
					asientoDetalle.setReferencia("N/C # " + numeroNotaCredito);
					asientoDetalle.setGlosa("N/C # " + ((NotaCreditoIf) referenceBean).getPreimpreso() + " " + ((NotaCreditoIf) referenceBean).getObservacion());
					
					if(this.parameterMap.get(plantilla.getNemonico()) == null){
						System.out.println("abc");
					}
					
					double valor = ((Double) this.parameterMap.get(plantilla.getNemonico())).doubleValue();
					if (plantilla.getDebehaber().equals("D")) {
						asientoDetalle.setDebe(BigDecimal.valueOf(valor));
						asientoDetalle.setHaber(BigDecimal.ZERO);
					} else if (plantilla.getDebehaber().equals("H")) {
						asientoDetalle.setHaber(BigDecimal.valueOf(valor));
						asientoDetalle.setDebe(BigDecimal.ZERO);
					}
					
					if (valor > 0.0)
						asientoDetalleColeccion.add(asientoDetalle);
				}
			}
			
			if (procesarAsiento) {
				AsientoIf asiento = registrarAsiento();
				if (asiento != null) {
					if (eventoContable.getAgruparDetalles().equals("S"))
						asientoDetalleColeccion = asientoLocal.agruparDetalles(asientoDetalleColeccion);
					String numeroAsiento = asientoLocal.procesarAsiento(asiento, asientoDetalleColeccion, true);
					//Busco el asiento generado para retornarlo
					Collection asientosRetornar = asientoLocal.findAsientoByNumero(numeroAsiento);
					if ( asientosRetornar!=null && asientosRetornar.iterator().hasNext() ){
						asientoRetornar = (AsientoIf)asientosRetornar.iterator().next();
					}
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Ocurri\u00f3 un error al generaci\u00f3n/actualizar del asiento");
		}
		
		return asientoRetornar;
	}
	
	private AsientoIf registrarAsiento() throws GenericBusinessException {
		AsientoData data = new AsientoData();
		try {			
			Date fechaAsiento; 
			if (this.fechaAplicacion == null){
				Timestamp fechaTemp = ((NotaCreditoIf) this.parameterMap.get("BEAN")).getFechaEmision();
				fechaAsiento = utilitariosLocal.fromTimestampToSqlDate(fechaTemp);
			}else{
				fechaAsiento = this.fechaAplicacion;
			}
			
			if (validateAsientoAutomatico(empresaId, fechaAsiento)) {
				String strEstado = "P";	
				String unNumeroAsiento = asientoLocal.getNumeroAsiento(fechaAsiento, empresaId, planCuenta);
				data.setEmpresaId(this.empresaId);
				data.setNumero(unNumeroAsiento);
				data.setStatus(strEstado);
				data.setPeriodoId(periodo.getId());
				data.setPlancuentaId(planCuenta.getId());
				data.setObservacion(((NotaCreditoIf) referenceBean).getObservacion());
				data.setOficinaId(this.oficinaId);
				data.setFecha(fechaAsiento);
				if (eventoContable.getSubtipoAsientoId() != null)
					data.setEfectivo("S");
				else
					data.setEfectivo("N");
				data.setSubtipoasientoId(eventoContable.getSubtipoAsientoId());
				data.setTipoDocumentoId((Long) this.parameterMap.get("TIPO_DOCUMENTO_ID"));
				data.setTransaccionId(((NotaCreditoIf) referenceBean).getId());
				data.setElaboradoPorId(((NotaCreditoIf) referenceBean).getUsuarioId());
				data.setEventoContableId(this.eventoContable.getId());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error en el registro del asiento");
		}
		
		return data;
	}
	
	public boolean validateAsientoAutomatico(Long empresaId, java.sql.Date fechaAsiento) {
		try {
			//se debe hacer clear a filtroMap, cuando existe mas de un registro que validar.
			Map filtroMap = new HashMap();
			filtroMap.put("empresaId", empresaId);
			filtroMap.put("estado", "A");
			Iterator planCuentaIterator = planCuentaLocal.findPlanCuentaByQuery(filtroMap).iterator();
			
			if (planCuentaIterator.hasNext())
				planCuenta = (PlanCuentaIf) planCuentaIterator.next();
			else {
				return false;
			}
				
			Iterator periodoIterator = periodoLocal.findPeriodoForAsientoAutomatico(empresaId, fechaAsiento).iterator();
			
			if (periodoIterator.hasNext())
				periodo = (PeriodoIf) periodoIterator.next();
			else
				return false;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public List<AsientoDetalleIf> getAsientoDetalleColeccion() {
		return asientoDetalleColeccion;
	}
	
	public void setAsientoDetalleColeccion(List<AsientoDetalleIf> asientoDetalleColeccion) {
		NotaCreditoAsientoAutomaticoHandler.asientoDetalleColeccion = asientoDetalleColeccion;
	}
}
