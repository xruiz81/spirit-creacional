package com.spirit.nomina.handler;


import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
import com.spirit.contabilidad.session.AsientoSessionLocal;
import com.spirit.contabilidad.session.CuentaEntidadSessionLocal;
import com.spirit.contabilidad.session.CuentaSessionLocal;
import com.spirit.contabilidad.session.PeriodoSessionLocal;
import com.spirit.contabilidad.session.PlanCuentaSessionLocal;
import com.spirit.contabilidad.session.PlantillaSessionLocal;
import com.spirit.contabilidad.session.SubTipoAsientoSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.EmpleadoSessionLocal;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.nomina.entity.RolPagoDetalleIf;

@Stateless
public class RolAsientoAutomaticoHandler implements RolAsientoAutomaticoHandlerLocal {
	
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	@EJB
	private CuentaEntidadSessionLocal cuentaEntidadLocal;
	
	@EJB
	private AsientoSessionLocal asientoLocal;
	
	@EJB
	private PeriodoSessionLocal periodoLocal;
	
	@EJB
	private CuentaSessionLocal cuentaLocal;
	
	@EJB
	private EmpleadoSessionLocal empleadoLocal;
	
	@EJB
	private PlantillaSessionLocal plantillaLocal;
	
	@EJB
	private PlanCuentaSessionLocal planCuentaLocal;
	
	@EJB
	private SubTipoAsientoSessionLocal subTipoAsientoLocal;
	
	@EJB
	private EmpresaSessionLocal empresaLocal;
	
	@EJB
	private UtilitariosSessionLocal utilitariosLocal;
	
	private Map<String,Object> filtroMap = new HashMap<String,Object>();
	private PlanCuentaIf planCuenta = null;
	private PeriodoIf periodo = null;
	private List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
	
	private EventoContableIf eventoContable = null;
	private Object referenceBean = null;
	private Map parameterMap = null;
	
	private Long empresaId = 0L;
	private Long oficinaId = 0L;
	
	public AsientoIf generarRolAsientoAutomaticoHandler(EventoContableIf eventoContable, Map parameterMap) throws GenericBusinessException {
		this.eventoContable = eventoContable;
		try {
			this.planCuenta = planCuentaLocal.getPlanCuenta(eventoContable.getPlanCuentaId());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error al obtener el Plan de Cuenta del Evento Contable");
		}
		this.referenceBean = parameterMap.get("BEAN");
		this.parameterMap = parameterMap;
		
		this.empresaId = eventoContable.getEmpresaId();
		this.oficinaId = eventoContable.getOficinaId();
		
		return generarAsientoContable();
	}
	
	public AsientoIf generarAsientoContable() throws GenericBusinessException {
		AsientoIf asientoRetornar = null;
		try {
			AsientoIf asiento = registrarAsiento();
			Collection plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			Iterator plantillasIterator = plantillas.iterator();
			//EmpleadoIf empleado = empleadoLocal.getEmpleado(((RolPagoIf) referenceBean).getEmpleadoId());
			CuentaIf cuenta = null;
			
			while (plantillasIterator.hasNext()) {
				PlantillaIf plantilla = (PlantillaIf) plantillasIterator.next();
				cuenta = cuentaLocal.getCuenta(plantilla.getCuentaId());
				if (cuenta.getImputable().equals("N")) {
					Map<String,Object> parameterMap = new HashMap<String,Object>();
					//parameterMap.put("entidadId", empleado.getId());
					parameterMap.put("tipoEntidad", "E");
					parameterMap.put("eventoContableId", plantilla.getEventocontableId());
					parameterMap.put("nemonico", plantilla.getNemonico());
					Iterator cuentaEntidadIterator = cuentaEntidadLocal.findCuentaEntidadByQuery(parameterMap).iterator();
					if (cuentaEntidadIterator.hasNext()) {
						CuentaEntidadIf cuentaEntidad = (CuentaEntidadIf) cuentaEntidadIterator.next();
						cuenta = cuentaLocal.getCuenta(cuentaEntidad.getCuentaId());
					} else
						cuenta = (CuentaIf) cuentaLocal.findCuentaByCodigo("DEFING").iterator().next();
				}
				
				AsientoDetalleIf asientoDetalle = new AsientoDetalleData(); 
				asientoDetalle.setCuentaId(cuenta.getId());
				//asientoDetalle.setReferencia(String.valueOf(((RolPagoIf) referenceBean).getContratoId()); Qué es esta referencia
				asientoDetalle.setGlosa(((RolPagoDetalleIf) referenceBean).getObservacion());
				//asientoDetalle.setCentrogastoId();
				//asientoDetalle.setEmpleadoId(empleado.getId()); 					// En el caso del asiento automático por rol, 
				//asientoDetalle.setDepartamentoId(empleado.getDepartamentoId());   // quién viene a ser este empleado.
				//asientoDetalle.setLineaId();
				//asientoDetalle.setClienteId(empleado.getId()); // Quién es este cliente????
			
				double valor = ((Double) parameterMap.get(plantilla.getNemonico())).doubleValue();
				if (plantilla.getDebehaber().equals("D"))
					asientoDetalle.setDebe(BigDecimal.valueOf(valor));
				else if (plantilla.getDebehaber().equals("H"))
					asientoDetalle.setHaber(BigDecimal.valueOf(valor));
			
				asientoDetalleColeccion.add(asientoDetalle);
			}
			
			
			String numeroAsiento = asientoLocal.procesarAsiento(asiento, asientoDetalleColeccion, true);

			Collection asientosRetornar = asientoLocal.findAsientoByNumero(numeroAsiento);
			if ( asientosRetornar!=null && asientosRetornar.iterator().hasNext() ){
				asientoRetornar = (AsientoIf)asientosRetornar.iterator().next();
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
			java.sql.Date fechaAsiento = new java.sql.Date(utilitariosLocal.dateHoy().getTime());
			if (validateAsientoAutomatico(empresaId, fechaAsiento)) {
				String strEstado = "P";	
				String unNumeroAsiento = asientoLocal.getNumeroAsiento(fechaAsiento, empresaId, planCuenta);
				data.setEmpresaId(empresaId);
				data.setNumero(unNumeroAsiento);
				data.setStatus(strEstado);
				data.setPeriodoId(periodo.getId());
				data.setPlancuentaId(planCuenta.getId());
				data.setObservacion("ASIENTO AUTOMÁTICO POR " + parameterMap.get("OBSERVACION").toString());
				data.setOficinaId(oficinaId);
				data.setFecha(fechaAsiento);
				if (eventoContable.getSubtipoAsientoId() != null)
					data.setEfectivo("S");
				else
					data.setEfectivo("N");
				data.setSubtipoasientoId(eventoContable.getSubtipoAsientoId());
				data.setEventoContableId(eventoContable.getId());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al registrar el Asiento");
		} catch (ParseException e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error en la generaci\u00f3n de fecha para registrar el Asiento");
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
}
