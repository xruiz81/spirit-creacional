package com.spirit.facturacion.handler;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

import com.spirit.contabilidad.entity.AsientoData;
import com.spirit.contabilidad.entity.AsientoDetalleData;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaEntidadIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.PlantillaIf;
import com.spirit.contabilidad.session.AsientoDetalleSessionLocal;
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
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.TipoDocumentoSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.session.ProductoSessionLocal;

@Stateless
public class DevolucionAsientoAutomaticoHandler implements DevolucionAsientoAutomaticoHandlerLocal {
	@PersistenceContext(unitName = "spirit")
	@EJB private CuentaEntidadSessionLocal cuentaEntidadLocal;
	@EJB private AsientoSessionLocal asientoLocal;
	@EJB private AsientoDetalleSessionLocal asientoDetalleLocal;
	@EJB private PeriodoSessionLocal periodoLocal;
	@EJB private CuentaSessionLocal cuentaLocal;
	@EJB private ClienteOficinaSessionLocal clienteOficinaLocal;
	@EJB private ClienteSessionLocal clienteLocal;
	@EJB private PlantillaSessionLocal plantillaLocal;
	@EJB private PlanCuentaSessionLocal planCuentaLocal;
	@EJB private SubTipoAsientoSessionLocal subTipoAsientoLocal;
	@EJB private EmpresaSessionLocal empresaLocal;
	@EJB private EventoContableSessionLocal eventoContableLocal;
	@EJB private ProductoSessionLocal productoLocal;
	@EJB private UtilitariosSessionLocal utilitariosLocal;
	@EJB private TipoDocumentoSessionLocal tipoDocumentoLocal;
	
	private static final String OPCION_NO = "N";
	private static final String OPCION_SI = "S";
	private Map filtroMap = new HashMap();
	private PlanCuentaIf planCuenta = null;
	private PeriodoIf periodo = null;
	private static List<AsientoDetalleIf> asientoDetalleColeccion = null;	
	private EventoContableIf eventoContable = null;
	private Object referenceBean = null;
	private Map parameterMap = null;
	private boolean procesarAsiento = false;
	private DecimalFormat formatoSerial = new DecimalFormat("0000000");
	private Long empresaId = 0L;
	private Long oficinaId = 0L;
	private UsuarioIf usuario;
	
	public AsientoIf generarAsientoAutomatico(FacturaIf factura, FacturaDetalleIf facturaDetalle, Long tipoDocumentoAnteriorId, boolean procesarAsiento, boolean actualizar, UsuarioIf usuario, String tipoReferencia, boolean contabilizarDescuento) throws GenericBusinessException {
		Map parameterMap = new HashMap();
		this.usuario = usuario;
		if (factura.getTipodocumentoId() != null) {
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(factura.getTipodocumentoId());
			if (facturaDetalle.getDocumentoId() != null) {
				parameterMap.put("etapa", 1L);
				parameterMap.put("documentoId", facturaDetalle.getDocumentoId());
				Iterator eventoContableIterator = eventoContableLocal.findEventoContableByQuery(parameterMap).iterator();
				if (eventoContableIterator.hasNext()) {
					EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator.next();
					if (eventoContable != null) {
						Double descuento = Double.valueOf(facturaDetalle.getDescuento().doubleValue());
						Double descuentoGlobal = Double.valueOf(facturaDetalle.getDescuentoGlobal().doubleValue());
						Double iva = Double.valueOf(facturaDetalle.getIva().doubleValue());
						Double valor = Double.valueOf(facturaDetalle.getValor().doubleValue());
						Double porcentajeComision = Double.valueOf(factura.getPorcentajeComisionAgencia().doubleValue());
						Double comision = ((valor - descuento - descuentoGlobal) * porcentajeComision) / 100D;
						Double total = valor - descuento - descuentoGlobal + comision;
						parameterMap.clear();
						parameterMap.put("OFICINA_ID", factura.getOficinaId());
						parameterMap.put("BEAN", factura);
						parameterMap.put("TIPO_DOCUMENTO_ANTERIOR_ID", tipoDocumentoAnteriorId);
						parameterMap.put("OBSERVACION", "DEVOLUCION");
						parameterMap.put("DEVOLUCION", (contabilizarDescuento)?(total + descuento + descuentoGlobal):total);
						parameterMap.put("IVA", iva);
						parameterMap.put("CTAXPAG", (total + iva));
						if (contabilizarDescuento)
							parameterMap.put("DESCUENTO", descuento + descuentoGlobal);
						else
							parameterMap.put("DESCUENTO", 0D);
						parameterMap.put("PRODUCTO_ID", facturaDetalle.getProductoId());
						ProductoIf producto = productoLocal.getProducto(facturaDetalle.getProductoId());
						parameterMap.put("PROVEEDOR_ID", producto.getProveedorId());
						parameterMap.put("TIPO_DOCUMENTO_ID", tipoDocumento.getId());
						parameterMap.put("TIPO_DOCUMENTO_CODIGO", tipoDocumento.getCodigo());
						parameterMap.put("ORIGEN_DOCUMENTO_ID", factura.getOrigendocumentoId());
						return devolucionAsientoAutomaticoHandler(eventoContable, parameterMap, tipoDocumento, tipoReferencia, procesarAsiento, actualizar);
					}
				}
			}
		}
		return null;
	}
	
	public AsientoIf devolucionAsientoAutomaticoHandler(EventoContableIf eventoContable, Map parameterMap, TipoDocumentoIf tipoDocumento, String tipoReferencia, boolean procesarAsiento, boolean actualizar) throws GenericBusinessException {
		this.eventoContable = eventoContable;
		try {
			this.planCuenta = planCuentaLocal.getPlanCuenta(eventoContable.getPlanCuentaId());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error al obtener el Plan de Cuenta del Evento Contable");
		}
		this.referenceBean = parameterMap.get("BEAN");
		this.parameterMap = parameterMap;
		this.procesarAsiento = procesarAsiento;
		this.empresaId = eventoContable.getEmpresaId();
		this.oficinaId = (Long) parameterMap.get("OFICINA_ID");
		
		return generarAsientoContable(actualizar);
	}
	
	public AsientoIf generarAsientoContable(boolean actualizar) throws GenericBusinessException {
		AsientoIf asientoRetornar = null;
		try {
			Collection plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			Iterator plantillasIterator = plantillas.iterator();
			ClienteOficinaIf clienteOficina = clienteOficinaLocal.getClienteOficina(((FacturaIf) referenceBean).getClienteoficinaId());
			ClienteIf cliente = clienteLocal.getCliente(clienteOficina.getClienteId());
			CuentaIf cuenta = null;
			ClienteOficinaIf proveedorOficina = clienteOficinaLocal.getClienteOficina((Long) parameterMap.get("PROVEEDOR_ID"));
			ClienteIf proveedor = clienteLocal.getCliente(proveedorOficina.getClienteId());
			
			while (plantillasIterator.hasNext()) {
				PlantillaIf plantilla = (PlantillaIf) plantillasIterator.next();
				cuenta = cuentaLocal.getCuenta(plantilla.getCuentaId());
				if (cuenta.getImputable().equals("N")) {
					Map<String,Object> parameterMap = new HashMap<String,Object>();
					if (plantilla.getNemonico().equals("CTAXPAG")) {
						parameterMap.put("entidadId", proveedor.getId());
						parameterMap.put("tipoEntidad", "P");
					}
					
					if (plantilla.getNemonico().equals("DEVOLUCION")) {
						Long idOrigenDocumento = (Long) this.parameterMap.get("ORIGEN_DOCUMENTO_ID");
						parameterMap.put("entidadId", idOrigenDocumento);
						parameterMap.put("tipoEntidad", "N");
					}
					
					//parameterMap.put("eventoContableId", plantilla.getEventocontableId());
					parameterMap.put("nemonico", plantilla.getNemonico());
					if (plantilla.getNemonico().equals("DESCUENTO")) {
						Long idOrigenDocumento = (Long) this.parameterMap.get("ORIGEN_DOCUMENTO_ID");
						parameterMap.put("entidadId", idOrigenDocumento);
						parameterMap.put("tipoEntidad", "N");
					}
					Iterator cuentaEntidadIterator = cuentaEntidadLocal.findCuentaEntidadByQuery(parameterMap).iterator();
					if (cuentaEntidadIterator.hasNext()) {
						CuentaEntidadIf cuentaEntidad = (CuentaEntidadIf) cuentaEntidadIterator.next();
						cuenta = cuentaLocal.getCuenta(cuentaEntidad.getCuentaId());
					} else if (plantilla.getNemonico().equals("DEVOLUCION")) {
						Long idProducto = (Long) this.parameterMap.get("PRODUCTO_ID");
						parameterMap.put("entidadId", idProducto);
						parameterMap.put("tipoEntidad", "I");
						cuentaEntidadIterator = cuentaEntidadLocal.findCuentaEntidadByQuery(parameterMap).iterator();
						if (cuentaEntidadIterator.hasNext()) {
							CuentaEntidadIf cuentaEntidad = (CuentaEntidadIf) cuentaEntidadIterator.next();
							cuenta = cuentaLocal.getCuenta(cuentaEntidad.getCuentaId());
						} else {
							parameterMap.clear();
							parameterMap.put("plancuentaId", planCuenta.getId());
							if (plantilla.getDebehaber().equals("D"))
								parameterMap.put("codigo", "DEFING");
							else if (plantilla.getDebehaber().equals("H"))
								parameterMap.put("codigo", "DEFEGR");
							cuenta = (CuentaIf) cuentaLocal.findCuentaByQuery(parameterMap).iterator().next();
						}
					} else {
						parameterMap.clear();
						parameterMap.put("plancuentaId", planCuenta.getId());
						if (plantilla.getDebehaber().equals("D"))
							parameterMap.put("codigo", "DEFING");
						else if (plantilla.getDebehaber().equals("H"))
							parameterMap.put("codigo", "DEFEGR");
						cuenta = (CuentaIf) cuentaLocal.findCuentaByQuery(parameterMap).iterator().next();
					}
				}
				
				AsientoDetalleIf asientoDetalle = new AsientoDetalleData(); 
				asientoDetalle.setCuentaId(cuenta.getId());
				asientoDetalle.setReferencia("");
				asientoDetalle.setGlosa(((FacturaIf) referenceBean).getObservacion());
				
				double valor = ((Double) parameterMap.get(plantilla.getNemonico())).doubleValue();
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
			
			if (procesarAsiento) {
				AsientoIf asiento = registrarAsiento();
				if (asiento != null) {
					if (eventoContable.getAgruparDetalles().equals("S"))
						asientoDetalleColeccion = asientoLocal.agruparDetalles(asientoDetalleColeccion);
					asientoDetalleColeccion = asientoLocal.redondearDetallesAsiento(asientoDetalleColeccion);
					String numeroAsiento = "";
					if (!actualizar)
						numeroAsiento = asientoLocal.procesarAsiento(asiento, asientoDetalleColeccion, true);
					else {
						Long tipoDocumentoAnteriorId = (Long) this.parameterMap.get("TIPO_DOCUMENTO_ANTERIOR_ID");
						boolean reversarSaldos = false;
						Map parameterMap = new HashMap();
						parameterMap.put("tipoDocumentoId", tipoDocumentoAnteriorId);
						parameterMap.put("transaccionId", ((FacturaIf) referenceBean).getId());
						Iterator it = asientoLocal.findAsientoByQuery(parameterMap).iterator();
						if (it.hasNext()) {
							AsientoIf asientoAnterior = (AsientoIf) it.next();
							List<AsientoDetalleIf> asientoDetalleAnteriorColeccion = (ArrayList<AsientoDetalleIf>) asientoDetalleLocal.findAsientoDetalleByAsientoId(asientoAnterior.getId());
							boolean actualizarNumeroAsiento = true;
							asiento.setId(asientoAnterior.getId());
							java.sql.Date fechaAsientoAnterior = new java.sql.Date(asientoAnterior.getFecha().getTime());
							int yearAsientoAnterior = fechaAsientoAnterior.getYear() + 1900;
							int monthAsientoAnterior = fechaAsientoAnterior.getMonth() + 1;
							java.sql.Date fechaAsiento = new java.sql.Date(asiento.getFecha().getTime());
							int yearAsiento = fechaAsiento.getYear() + 1900;
							int monthAsiento = fechaAsiento.getMonth() + 1;
							if (yearAsientoAnterior == yearAsiento && monthAsientoAnterior == monthAsiento) {
								asiento.setNumero(asientoAnterior.getNumero());
								actualizarNumeroAsiento = false;
							}
							if (asientoAnterior.getStatus().equals("A"))
								reversarSaldos = true;
							numeroAsiento = ((AsientoIf) asientoLocal.actualizarAsiento(asiento, asientoDetalleColeccion, asientoAnterior, asientoDetalleAnteriorColeccion, asientoDetalleAnteriorColeccion, reversarSaldos, actualizarNumeroAsiento, this.usuario, new HashMap(), new HashMap(), new HashMap(), new HashMap(), false).get("ASIENTO")).getNumero();
						}
					}
					
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
			throw new GenericBusinessException("Ocurri\u00f3 un error al generar/actualizar el asiento");
		}
		
		return asientoRetornar;
	}
	
	private AsientoIf registrarAsiento() throws GenericBusinessException {
		AsientoData data = new AsientoData();
		try {			
			FacturaIf factura = (FacturaIf) referenceBean;
			java.sql.Timestamp fechaAsiento = factura.getFechaFactura();
			if (validateAsientoAutomatico(empresaId, new java.sql.Date(fechaAsiento.getTime()))) {
				String strEstado = "P";	
				String unNumeroAsiento = asientoLocal.getNumeroAsiento(new java.sql.Date(fechaAsiento.getTime()), this.empresaId, planCuenta);
				if (unNumeroAsiento.length() == 1)
					unNumeroAsiento = "00" + unNumeroAsiento;
				else if (unNumeroAsiento.length() == 2)
					unNumeroAsiento = "0" + unNumeroAsiento;
				data.setEmpresaId(this.empresaId);
				data.setNumero(unNumeroAsiento);
				data.setStatus(strEstado);
				data.setPeriodoId(periodo.getId());
				data.setPlancuentaId(planCuenta.getId());
				data.setObservacion(((FacturaIf) referenceBean).getObservacion());
				data.setOficinaId(this.oficinaId);
				data.setFecha(new java.sql.Date(fechaAsiento.getTime()));
				if (eventoContable.getSubtipoAsientoId() != null)
					data.setEfectivo("S");
				else
					data.setEfectivo("N");
				data.setSubtipoasientoId(eventoContable.getSubtipoAsientoId());
				data.setTipoDocumentoId((Long) this.parameterMap.get("TIPO_DOCUMENTO_ID"));
				data.setTransaccionId(((FacturaIf) referenceBean).getId());
				data.setElaboradoPorId(((FacturaIf) referenceBean).getUsuarioId());
				data.setEventoContableId(eventoContable.getId());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al registrar el asiento");
			
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
	
	public static List<AsientoDetalleIf> getAsientoDetalleColeccion() {
		return asientoDetalleColeccion;
	}
	
	public void setAsientoDetalleColeccion(List<AsientoDetalleIf> asientoDetalleColeccion) {
		DevolucionAsientoAutomaticoHandler.asientoDetalleColeccion = asientoDetalleColeccion;
	}
}
