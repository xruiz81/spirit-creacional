package com.spirit.compras.handler;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
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
import com.spirit.inventario.session.GenericoSessionLocal;
import com.spirit.inventario.session.ProductoSessionLocal;

@Stateless
public class CompraReembolsoAsientoAutomaticoHandler implements CompraReembolsoAsientoAutomaticoHandlerLocal {
	
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
	
	private Long empresaId = 0L;
	private Long oficinaId = 0L;
	
	public AsientoIf generarAsientoAutomatico(CompraIf compra, CompraDetalleIf compraDetalle, boolean procesarAsiento,Long tipoDocumentoIfId)
	throws GenericBusinessException {
		Map parameterMap = new HashMap();
		DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(compraDetalle.getDocumentoId());
		if ( documento != null ) {
			Iterator eventoContableIterator = eventoContableLocal.findEventoContableByDocumentoId(documento.getId()).iterator();
			if (eventoContableIterator.hasNext()) {
				EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator.next();;
				if (eventoContable != null) {
					Double descuento = Double.valueOf(compraDetalle.getDescuento().doubleValue());
					Double iva = Double.valueOf(compraDetalle.getIva().doubleValue());
					Double ice = 0D;
					if (compraDetalle.getValor() != null)
						ice = compraDetalle.getValor().doubleValue();
					Double valor = Double.valueOf(compraDetalle.getValor().doubleValue());
					double cantidad = compraDetalle.getCantidad().doubleValue();
					double subtotal = cantidad * valor;
					double otroImpuesto = Double.valueOf(compraDetalle.getOtroImpuesto().doubleValue());
					Double total = subtotal + iva + ice + otroImpuesto - descuento;
					parameterMap.clear();
					parameterMap.put("OFICINA_ID", compra.getOficinaId());
					parameterMap.put("BEAN", compra);
					parameterMap.put("OBSERVACION", "COMPRA");
					/*if (generico.getMediosProduccion().equals("G"))
						parameterMap.put("GASTO", total - iva);
					else if (generico.getMediosProduccion().equals("M") || generico.getMediosProduccion().equals("P"))*/
					parameterMap.put("REEMBOLSO", total);
					parameterMap.put("CTAXPAG", total);
					//parameterMap.put("RETEFUENTE", retencion_fuente);
					//parameterMap.put("RETEIVA", retencion_iva);
					//parameterMap.put("ID_CUENTA_RETEFUENTE", spr.getIdCuentaRetefuente());
					//parameterMap.put("ID_CUENTA_RETEIVA", spr.getIdCuentaReteiva());
					parameterMap.put("PROVEEDOR_ID", compra.getProveedorId());
					parameterMap.put("TIPO_DOCUMENTO_ID", tipoDocumentoIfId);
					TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tipoDocumentoLocal.getTipoDocumento(tipoDocumentoIfId);
					parameterMap.put("TIPO_DOCUMENTO_CODIGO", tipoDocumento.getCodigo());
					
					//new CompraAsientoAutomaticoHandler(eventoContable, parameterMap, procesarAsiento);
					return procesarCompraReembolsoAsientoAutomaticoHandler(eventoContable, parameterMap, procesarAsiento);
				}
			}
		}
		return null;
	}
	
	public AsientoIf procesarCompraReembolsoAsientoAutomaticoHandler(EventoContableIf eventoContable, Map parameterMap, boolean procesarAsiento)
	throws GenericBusinessException {
		this.eventoContable = eventoContable;
		//asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
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
		
		//asientoDetalleColeccion = generarAsientoContable();
		return generarAsientoContable();
	}
	
	//public List<AsientoDetalleIf> generarAsientoContable() throws GenericBusinessException {
	public AsientoIf generarAsientoContable() throws GenericBusinessException {

		AsientoIf asientoRetornar = null;
		try {
			Collection plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			Iterator plantillasIterator = plantillas.iterator();
			CuentaIf cuenta = null;
			//ClienteOficinaIf proveedorOficina = clienteOficinaLocal.getClienteOficina(((CompraIf) referenceBean).getProveedorId());
			//ClienteIf proveedor = clienteLocal.getCliente(proveedorOficina.getClienteId());

			while (plantillasIterator.hasNext()) {
				PlantillaIf plantilla = (PlantillaIf) plantillasIterator.next();
				cuenta = cuentaLocal.getCuenta(plantilla.getCuentaId());
				Map parameterMap = new HashMap();
				
				if (cuenta.getImputable().equals("N") && !this.parameterMap.get("TIPO_DOCUMENTO_CODIGO").toString().equals("LIC")) {
					//if (plantilla.getNemonico().equals("RETEFUENTE") && this.parameterMap.get("ID_CUENTA_RETEFUENTE") != null) {
					if (plantilla.getNemonico().equals("RETEFUENTE")) {
						Long idCuentaRetefuente = (Long) this.parameterMap.get("ID_CUENTA_RETEFUENTE");
						if (idCuentaRetefuente != null)
							cuenta = cuentaLocal.getCuenta(idCuentaRetefuente);
					//} else if (plantilla.getNemonico().equals("RETEIVA") && this.parameterMap.get("ID_CUENTA_RETEIVA") != null)  {
					} else if (plantilla.getNemonico().equals("RETEIVA"))  {
						Long idCuentaReteiva = (Long) this.parameterMap.get("ID_CUENTA_RETEIVA");
						if (idCuentaReteiva != null)
							cuenta = cuentaLocal.getCuenta(idCuentaReteiva);
					} else {
						if (plantilla.getNemonico().equals("CTAXPAG")) {
							Long idProveedor = (Long) this.parameterMap.get("PROVEEDOR_ID");
							ClienteOficinaIf proveedorOficina = clienteOficinaLocal.getClienteOficina(idProveedor);
							ClienteIf proveedor = clienteLocal.getCliente(proveedorOficina.getClienteId());
							if (proveedor != null) {
								parameterMap.put("entidadId", proveedor.getId());
								parameterMap.put("tipoEntidad", "C");
								parameterMap.put("nemonico", "CTAXPAG");
							}
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
				}
				
				if (cuenta != null) {
					AsientoDetalleIf asientoDetalle = new AsientoDetalleData();
					asientoDetalle.setCuentaId(cuenta.getId());
					String numeroFactura = ((CompraIf) referenceBean).getPreimpreso();
					asientoDetalle.setReferencia("FACT. # " + numeroFactura);
					asientoDetalle.setGlosa("COMPRA/FACT. # " + ((CompraIf) referenceBean).getPreimpreso() + " " + ((CompraIf) referenceBean).getObservacion());
					//asientoDetalle.setCentrogastoId();
					//asientoDetalle.setEmpleadoId(empleado.getId());
					//asientoDetalle.setDepartamentoId(empleado.getDepartamentoId());
					//asientoDetalle.setLineaId();
					//asientoDetalle.setClienteId(cliente.getId());
					
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
		
		//return asientoDetalleColeccion;
		return asientoRetornar;
	}
	
	private AsientoIf registrarAsiento() throws GenericBusinessException {
		AsientoData data = new AsientoData();
		try {			
			Date fechaAsiento = ((CompraIf) this.parameterMap.get("BEAN")).getFecha();
			if (validateAsientoAutomatico(empresaId, fechaAsiento)) {
				String strEstado = "P";	
				//Date fechaAsiento = Utilitarios.dateHoy(); 
				String unNumeroAsiento = asientoLocal.getNumeroAsiento(fechaAsiento, empresaId, planCuenta);
				data.setEmpresaId(this.empresaId);
				data.setNumero(unNumeroAsiento);
				data.setStatus(strEstado);
				data.setPeriodoId(periodo.getId());
				data.setPlancuentaId(planCuenta.getId());
				data.setObservacion(((CompraIf) referenceBean).getObservacion());
				data.setOficinaId(this.oficinaId);
				data.setFecha(fechaAsiento);
				if (eventoContable.getSubtipoAsientoId() != null)
					data.setEfectivo("S");
				else
					data.setEfectivo("N");
				data.setSubtipoasientoId(eventoContable.getSubtipoAsientoId());
				data.setTipoDocumentoId((Long) this.parameterMap.get("TIPO_DOCUMENTO_ID"));
				data.setTransaccionId(((CompraIf) referenceBean).getId());
				data.setElaboradoPorId(((CompraIf) referenceBean).getUsuarioId());
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

	public void setAsientoDetalleColeccion(
			List<AsientoDetalleIf> asientoDetalleColeccion) {
		CompraReembolsoAsientoAutomaticoHandler.asientoDetalleColeccion = asientoDetalleColeccion;
	}
	
}
