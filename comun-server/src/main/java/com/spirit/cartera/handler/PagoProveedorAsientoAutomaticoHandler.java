package com.spirit.cartera.handler;

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

import com.spirit.cartera.entity.CarteraIf;
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
import com.spirit.contabilidad.session.PeriodoSessionLocal;
import com.spirit.contabilidad.session.PlanCuentaSessionLocal;
import com.spirit.contabilidad.session.PlantillaSessionLocal;
import com.spirit.contabilidad.session.SubTipoAsientoSessionLocal;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.session.ClienteOficinaSessionLocal;
import com.spirit.crm.session.ClienteSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;

@Stateless
public class PagoProveedorAsientoAutomaticoHandler implements PagoProveedorAsientoAutomaticoHandlerLocal{
	@PersistenceContext(unitName = "spirit")
	
	@EJB
	private PlanCuentaSessionLocal planCuentaLocal;
	
	@EJB 
	private PlantillaSessionLocal plantillaLocal;
	
	@EJB
	private ClienteOficinaSessionLocal clienteOficinaLocal;
	
	@EJB
	private ClienteSessionLocal clienteLocal;
	
	@EJB
	private CuentaSessionLocal cuentaLocal;
	
	@EJB
	private SubTipoAsientoSessionLocal subtipoAsientoLocal;
	
	@EJB
	private PeriodoSessionLocal periodoLocal;
	
	@EJB
	private EmpresaSessionLocal empresaLocal;
	
	@EJB
	private UtilitariosSessionLocal utilitariosLocal;
	
	@EJB
	private	CuentaEntidadSessionLocal cuentaEntidadLocal;
	
	@EJB
	private	AsientoSessionLocal asientoLocal;
	
	private Map filtroMap = new HashMap();
	private PlanCuentaIf planCuenta = null;
	private PeriodoIf periodo = null;
	private static List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();;
	private EventoContableIf eventoContable = null;
	private Object referenceBean = null;
	private Map parameterMap = null;
	private boolean procesarAsiento = false;
	private static Map asientoMap = new HashMap();
	private Long empresaId = 0L;
	private Long oficinaId = 0L;
	private static final String TIPO_CUENTA_BANCARIA = "B";
	private static final String TIPO_OFICINA = "O";
	
	public void procesarAsientoAutomatico(EventoContableIf eventoContable, Map parameterMap, boolean procesarAsiento) throws GenericBusinessException {
		//asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
		this.eventoContable = eventoContable;
		try {
			this.planCuenta = planCuentaLocal.getPlanCuenta(eventoContable.getPlanCuentaId());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		this.referenceBean = parameterMap.get("BEAN");
		this.parameterMap = parameterMap;
		this.procesarAsiento = procesarAsiento;
		this.empresaId = eventoContable.getEmpresaId();
		this.oficinaId = (Long) parameterMap.get("OFICINA_ID");
		asientoDetalleColeccion = generarAsientoContable();
	}
	
	public List<AsientoDetalleIf> generarAsientoContable() throws GenericBusinessException {
		try {
			Collection plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			Iterator plantillasIterator = plantillas.iterator();
			CuentaIf cuenta = null;
			ClienteOficinaIf proveedorOficina = null;
			ClienteIf proveedor = null;			
			proveedorOficina = clienteOficinaLocal.getClienteOficina(((CarteraIf) referenceBean).getClienteoficinaId());
			proveedor = clienteLocal.getCliente(proveedorOficina.getClienteId());
			
			while (plantillasIterator.hasNext()) {
				PlantillaIf plantilla = (PlantillaIf) plantillasIterator.next();
				cuenta = cuentaLocal.getCuenta(plantilla.getCuentaId());
				Map parameterMap = new HashMap();
				
				if (cuenta.getImputable().equals("N")) {
					if (plantilla.getNemonico().equals("BANCO")) {
						Long idCuentaBancaria = (Long) this.parameterMap.get("CUENTA_BANCARIA_ID");
						parameterMap.put("entidadId", idCuentaBancaria);
						parameterMap.put("tipoEntidad", TIPO_CUENTA_BANCARIA);
					} else if (plantilla.getNemonico().equals("CTAXPAG")) {
						parameterMap.put("entidadId", proveedor.getId());
						parameterMap.put("tipoEntidad", "P");
					} else if (plantilla.getNemonico().equals("CAJA")) {
						parameterMap.put("entidadId", this.oficinaId);
						parameterMap.put("tipoEntidad", TIPO_OFICINA);
					}
					
					parameterMap.put("nemonico", plantilla.getNemonico());
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
						cuenta = (CuentaIf) cuentaLocal.findCuentaByQuery(parameterMap).iterator().next();
					}
				}
				
				AsientoDetalleIf asientoDetalle = new AsientoDetalleData();
				asientoDetalle.setCuentaId(cuenta.getId());
				asientoDetalle.setGlosa("COMP. EGR. # " + ((CarteraIf) referenceBean).getCodigo() + " " + ((CarteraIf) referenceBean).getComentario());
				
				double valor = ((BigDecimal) this.parameterMap.get(plantilla.getNemonico())).doubleValue();
				if (plantilla.getDebehaber().equals("D")) {
					asientoDetalle.setDebe(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valor)));
					asientoDetalle.setHaber(BigDecimal.ZERO);
				} else if (plantilla.getDebehaber().equals("H")) {
					asientoDetalle.setHaber(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valor)));
					asientoDetalle.setDebe(BigDecimal.ZERO);
				}
				
				if (valor > 0.0)
					asientoDetalleColeccion.add(asientoDetalle);
			}
			
			if (procesarAsiento && (asientoDetalleColeccion.size() > 0)) {
				AsientoIf asiento = registrarAsiento();
				if (asiento != null) {
					if (eventoContable.getAgruparDetalles().equals("S"))
						asientoDetalleColeccion = asientoLocal.agruparDetalles(asientoDetalleColeccion);
					asientoMap.put("ASIENTO", asiento);
					asientoMap.put("DETALLES", asientoDetalleColeccion);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error en la generaci\u00f3n de Asiento Contable");
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Ocurri\u00f3 un error general al actualizar el Asiento Contable");
		}
		
		return asientoDetalleColeccion;
	}
	
	public AsientoIf registrarAsiento() {
		AsientoData data = new AsientoData();
		try {			
			Date fechaAsiento = utilitariosLocal.fromTimestampToSqlDate(((CarteraIf) this.parameterMap.get("BEAN")).getFechaEmision());
			if (validateAsientoAutomatico(empresaId, fechaAsiento)) {
				String strEstado = "P";	
				String unNumeroAsiento = asientoLocal.getNumeroAsiento(fechaAsiento, empresaId, planCuenta);
				data.setEmpresaId(empresaId);
				data.setNumero(unNumeroAsiento);
				data.setStatus(strEstado);
				data.setPeriodoId(periodo.getId());
				data.setPlancuentaId(planCuenta.getId());
				data.setObservacion("COMP. EGR. # " + ((CarteraIf) referenceBean).getCodigo() + " " + parameterMap.get("OBSERVACION").toString());
				data.setOficinaId(oficinaId);
				data.setFecha(fechaAsiento);
				data.setElaboradoPorId(((CarteraIf) referenceBean).getUsuarioId());
				if (eventoContable.getSubtipoAsientoId() != null)
					data.setEfectivo("S");
				else
					data.setEfectivo("N");
				data.setSubtipoasientoId(eventoContable.getSubtipoAsientoId());
				data.setTipoDocumentoId((Long) this.parameterMap.get("TIPO_DOCUMENTO_ID"));
				data.setTransaccionId(((CarteraIf) referenceBean).getId());
				data.setEventoContableId(eventoContable.getId());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
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
	
	public static Map getAsientoMap() {
		return asientoMap;
	}
	
	public static void setAsientoMap(Map asientoMap) {
		PagoProveedorAsientoAutomaticoHandler.asientoMap = asientoMap;
	}
	
	public static List<AsientoDetalleIf> getAsientoDetalleColeccion() {
		return asientoDetalleColeccion;
	}
	
	public static void setAsientoDetalleColeccion(
			List<AsientoDetalleIf> asientoDetalleColeccion) {
		PagoProveedorAsientoAutomaticoHandler.asientoDetalleColeccion = asientoDetalleColeccion;
	}	
}
