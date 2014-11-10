package com.spirit.inventario.handler;

import java.math.BigDecimal;
import java.sql.Date;
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
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.session.DocumentoSessionLocal;
import com.spirit.general.session.EmpleadoSessionLocal;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.TipoDocumentoSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.LoteIf;
import com.spirit.inventario.entity.MovimientoDetalleIf;
import com.spirit.inventario.entity.MovimientoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.session.GenericoSessionLocal;
import com.spirit.inventario.session.LoteSessionLocal;
import com.spirit.inventario.session.ProductoSessionLocal;

@Stateless
public class MovimientoAsientoAutomaticoHandler implements MovimientoAsientoAutomaticoHandlerLocal {
	@PersistenceContext(unitName = "spirit")
	@EJB private GenericoSessionLocal genericoLocal;
	@EJB private ProductoSessionLocal productoLocal;
	@EJB private LoteSessionLocal loteLocal;
	@EJB private CuentaEntidadSessionLocal cuentaEntidadLocal;
	@EJB private AsientoSessionLocal asientoLocal;
	@EJB private AsientoDetalleSessionLocal asientoDetalleLocal;
	@EJB private PeriodoSessionLocal periodoLocal;
	@EJB private CuentaSessionLocal cuentaLocal;
	@EJB private PlantillaSessionLocal plantillaLocal;	
	@EJB private PlanCuentaSessionLocal planCuentaLocal;	
	@EJB private SubTipoAsientoSessionLocal subTipoAsientoLocal;
	@EJB private EmpresaSessionLocal empresaLocal;
	@EJB private EmpleadoSessionLocal empleadoLocal;
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
	private Long empresaId = 0L;
	private Long oficinaId = 0L;
	private UsuarioIf usuario;
	
	public AsientoIf generarAsientoAutomatico(MovimientoIf movimiento, MovimientoDetalleIf movimientoDetalle, MovimientoIf movimientoAnterior, boolean procesarAsiento, boolean actualizar, UsuarioIf usuario) throws GenericBusinessException {
		Map parameterMap = new HashMap();
		DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(movimientoDetalle.getDocumentoId());
		this.usuario = usuario;
		if ( documento != null ) {
			Iterator eventoContableIterator = eventoContableLocal.findEventoContableByDocumentoId(documento.getId()).iterator();
			if (eventoContableIterator.hasNext()) {
				EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator.next();
				if (eventoContable != null) {
					Double cantidad = Double.valueOf(movimientoDetalle.getCantidad().doubleValue());
					Double costo = Double.valueOf(movimientoDetalle.getPromedioUnitario().doubleValue());
					double total = cantidad * costo;
					parameterMap.clear();
					EmpleadoIf empleado = empleadoLocal.getEmpleado(usuario.getEmpleadoId());
					parameterMap.put("OFICINA_ID", empleado.getOficinaId());
					parameterMap.put("COSTO", total);
					parameterMap.put("CTAXCOB", total);
					parameterMap.put("INVENTARIO", total);
					parameterMap.put("BEAN", movimiento);
					parameterMap.put("MOVIMIENTO_ANTERIOR", movimientoAnterior);
					parameterMap.put("OBSERVACION", "movimiento");
					LoteIf lote = loteLocal.getLote(movimientoDetalle.getLoteId());
					parameterMap.put("PRODUCTO_ID", lote.getProductoId());
					parameterMap.put("TIPO_DOCUMENTO_ID", movimiento.getTipodocumentoId());
					TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tipoDocumentoLocal.getTipoDocumento(movimiento.getTipodocumentoId());
					parameterMap.put("TIPO_DOCUMENTO_CODIGO", tipoDocumento.getCodigo());
					
					return procesarmovimientoAsientoAutomaticoHandler(eventoContable, parameterMap, procesarAsiento, actualizar);
				}
			}
		}
		return null;
	}
	
	public AsientoIf procesarmovimientoAsientoAutomaticoHandler(EventoContableIf eventoContable, Map parameterMap, boolean procesarAsiento, boolean actualizar) throws GenericBusinessException {
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
		
		return generarAsientoContable(actualizar);
	}
	
	public AsientoIf generarAsientoContable(boolean actualizar) throws GenericBusinessException {
		AsientoIf asientoRetornar = null;
		try {
			Collection plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			Iterator plantillasIterator = plantillas.iterator();
			CuentaIf cuenta = null;

			while (plantillasIterator.hasNext()) {
				PlantillaIf plantilla = (PlantillaIf) plantillasIterator.next();
				if (((Double) this.parameterMap.get(plantilla.getNemonico())) > 0D) {
					cuenta = cuentaLocal.getCuenta(plantilla.getCuentaId());
					Map parameterMap = new HashMap();

					if (cuenta.getImputable().equals("N")) {
						if (plantilla.getNemonico().equals("COSTO")) {
							Long idProducto = (Long) this.parameterMap.get("PRODUCTO_ID");
							ProductoIf producto = productoLocal.getProducto(idProducto);
							GenericoIf generico = genericoLocal.getGenerico(producto.getGenericoId());
							parameterMap.put("entidadId", idProducto);
							parameterMap.put("tipoEntidad", "I");
							parameterMap.put("nemonico", "COSTO");
						}

						if (plantilla.getNemonico().equals("INVENTARIO")) {
							Long idProducto = (Long) this.parameterMap.get("PRODUCTO_ID");
							ProductoIf producto = productoLocal.getProducto(idProducto);
							GenericoIf generico = genericoLocal.getGenerico(producto.getGenericoId());
							parameterMap.put("entidadId", idProducto);
							parameterMap.put("tipoEntidad", "I");
							parameterMap.put("nemonico", "INVENTARIO");
						}

						Iterator cuentaEntidadIterator = cuentaEntidadLocal.findCuentaEntidadByQuery(parameterMap).iterator();
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
							Collection cuentas = cuentaLocal.findCuentaByQuery(parameterMap);
							if (cuentas.size() > 0)
								cuenta = (CuentaIf) cuentaLocal.findCuentaByQuery(parameterMap).iterator().next();
							else
								throw new GenericBusinessException("No existe cuenta Asociada");
						}
					}

					if (cuenta != null) {
						AsientoDetalleIf asientoDetalle = new AsientoDetalleData();
						asientoDetalle.setCuentaId(cuenta.getId());
						String numeroFactura = ((MovimientoIf) referenceBean).getPreimpreso();
						//asientoDetalle.setReferencia("");
						//asientoDetalle.setGlosa(((String) this.parameterMap.get("TIPO_DOCUMENTO_CODIGO")) + ": " + ((MovimientoIf) referenceBean).getCodigo() + " " + ((MovimientoIf) referenceBean).getObservacion());
						asientoDetalle.setGlosa(((MovimientoIf) referenceBean).getObservacion());
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
			}
			
			if (procesarAsiento) {
				AsientoIf asiento = registrarAsiento();
				if (asiento != null && asientoDetalleColeccion.size() > 0) {
					if (eventoContable.getAgruparDetalles().equals("S"))
						asientoDetalleColeccion = asientoLocal.agruparDetalles(asientoDetalleColeccion);
					String numeroAsiento = "";
					if (!actualizar)
						numeroAsiento = asientoLocal.procesarAsiento(asiento, asientoDetalleColeccion, true);
					else {
						MovimientoIf movimientoAnterior = (MovimientoIf) this.parameterMap.get("MOVIMIENTO_ANTERIOR");
						boolean reversarSaldos = false;
						Map parameterMap = new HashMap();
						parameterMap.put("tipoDocumentoId", movimientoAnterior.getTipodocumentoId());
						parameterMap.put("transaccionId", ((MovimientoIf) referenceBean).getId());
						Iterator it = asientoLocal.findAsientoByQuery(parameterMap).iterator();
						if (it.hasNext()) {
							AsientoIf asientoAnterior = (AsientoIf) it.next();
							List<AsientoDetalleIf> asientoDetalleAnteriorColeccion = (ArrayList<AsientoDetalleIf>) asientoDetalleLocal.findAsientoDetalleByAsientoId(asientoAnterior.getId());
							boolean actualizarNumeroAsiento = true;
							asiento.setId(asientoAnterior.getId());
							java.sql.Date fechaAsientoAnterior = asientoAnterior.getFecha();
							int yearAsientoAnterior = fechaAsientoAnterior.getYear() + 1900;
							int monthAsientoAnterior = fechaAsientoAnterior.getMonth() + 1;
							java.sql.Date fechaAsiento = asiento.getFecha();
							int yearAsiento = fechaAsiento.getYear() + 1900;
							int monthAsiento = fechaAsiento.getMonth() + 1;
							if (yearAsientoAnterior == yearAsiento && monthAsientoAnterior == monthAsiento) {
								asiento.setNumero(asientoAnterior.getNumero());
								actualizarNumeroAsiento = false;
							}
							if (asientoAnterior.getStatus().equals("A"))
								reversarSaldos = true;
							numeroAsiento = ((AsientoIf) asientoLocal.actualizarAsiento(asiento, asientoDetalleColeccion, asientoAnterior, asientoDetalleAnteriorColeccion, asientoDetalleAnteriorColeccion, reversarSaldos, actualizarNumeroAsiento, this.usuario, new HashMap(), new HashMap(), new HashMap(), new HashMap(), false).get("ASIENTO")).getNumero();
						} else
							numeroAsiento = asientoLocal.procesarAsiento(asiento, asientoDetalleColeccion, true);
					}
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
			Date fechaAsiento = new Date(((MovimientoIf) this.parameterMap.get("BEAN")).getFechaDocumento().getTime());
			if (validateAsientoAutomatico(empresaId, fechaAsiento)) {
				String strEstado = "P";	
				String unNumeroAsiento = asientoLocal.getNumeroAsiento(fechaAsiento, empresaId, planCuenta);
				data.setEmpresaId(this.empresaId);
				data.setNumero(unNumeroAsiento);
				data.setStatus(strEstado);
				data.setPeriodoId(periodo.getId());
				data.setPlancuentaId(planCuenta.getId());
				data.setObservacion(((MovimientoIf) referenceBean).getObservacion());
				data.setOficinaId(this.oficinaId);
				data.setFecha(fechaAsiento);
				if (eventoContable.getSubtipoAsientoId() != null)
					data.setEfectivo("S");
				else
					data.setEfectivo("N");
				data.setSubtipoasientoId(eventoContable.getSubtipoAsientoId());
				data.setTipoDocumentoId((Long) this.parameterMap.get("TIPO_DOCUMENTO_ID"));
				data.setTransaccionId(((MovimientoIf) referenceBean).getId());
				data.setElaboradoPorId(((MovimientoIf) referenceBean).getUsuarioId());
				data.setEventoContableId(eventoContable.getId());
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
		MovimientoAsientoAutomaticoHandler.asientoDetalleColeccion = asientoDetalleColeccion;
	}
}
