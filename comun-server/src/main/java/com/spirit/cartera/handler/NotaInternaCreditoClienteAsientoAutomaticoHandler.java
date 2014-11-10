package com.spirit.cartera.handler;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

import com.spirit.cartera.entity.CarteraDetalleIf;
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
public class NotaInternaCreditoClienteAsientoAutomaticoHandler implements NotaInternaCreditoClienteAsientoAutomaticoHandlerLocal {
	
	@PersistenceContext(unitName = "spirit")
	
	@EJB
	private CuentaEntidadSessionLocal cuentaEntidadLocal;
	
	@EJB
	private AsientoSessionLocal asientoLocal;
	
	@EJB
	private PeriodoSessionLocal periodoLocal;
	
	@EJB
	private CuentaSessionLocal cuentaLocal;
	
	@EJB
	private ClienteOficinaSessionLocal clienteOficinaLocal;
	
	@EJB
	private ClienteSessionLocal clienteLocal;
	
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
	private static List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();	
	private EventoContableIf eventoContable = null;
	private Object referenceBeanCartera = null;
	private Object referenceBeanCarteraDetalle = null;
	private Map parameterMap = null;
	private boolean procesarAsiento = false;
	
	private Long idEmpresa = 0L;
	private Long idOficina = 0L;
	
	public AsientoIf generarNotaInternaCreditoClienteAsientoAutomaticoHandler(EventoContableIf eventoContable, Map parameterMap, boolean procesarAsiento) throws GenericBusinessException {
		this.eventoContable = eventoContable;
		try {
			this.planCuenta = planCuentaLocal.getPlanCuenta(eventoContable.getPlanCuentaId());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("No se encontro Plan de Cuenta de Evento Contable: "+eventoContable.getCodigo());
		}
		this.referenceBeanCartera = parameterMap.get("BEAN_CARTERA");
		this.referenceBeanCarteraDetalle = parameterMap.get("BEAN_CARTERA_DETALLE");
		this.parameterMap = parameterMap;
		this.idEmpresa = eventoContable.getEmpresaId();
		this.idOficina = (Long) parameterMap.get("OFICINA_ID");
		this.procesarAsiento = procesarAsiento;
		return generarAsientoContable();
	}
	
	public AsientoIf generarAsientoContable() throws GenericBusinessException {
		AsientoIf asientoRetornar = null;
		try {
			Collection plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			Iterator plantillasIterator = plantillas.iterator();
			CuentaIf cuenta = null;
			Long clienteOficinaId = ((CarteraIf) this.referenceBeanCartera).getClienteoficinaId();
			ClienteOficinaIf clienteOficina = clienteOficinaLocal.getClienteOficina(clienteOficinaId);
			ClienteIf cliente = clienteLocal.getCliente(clienteOficina.getClienteId());
			while (plantillasIterator.hasNext()) {
				PlantillaIf plantilla = (PlantillaIf) plantillasIterator.next();
				cuenta = cuentaLocal.getCuenta(plantilla.getCuentaId());
				if (cuenta.getImputable().equals("N")) {
					Map<String,Object> parameterMap = new HashMap<String,Object>();
					if (plantilla.getNemonico().equals("CTAXCOB")) {
						parameterMap.put("entidadId", cliente.getId());
						parameterMap.put("tipoEntidad", "C");
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
				/*String codigo = ((CarteraIf) referenceBeanCartera).getCodigo();
				String glosa = codigo;*/
				if (eventoContable.getAgruparDetalles().equals("S")) {
					asientoDetalle.setGlosa(((CarteraIf) referenceBeanCartera).getComentario());
				} else {
					String codigo = ((CarteraIf) referenceBeanCartera).getCodigo();
					asientoDetalle.setReferencia("N/C INTERNA # " + codigo);
					asientoDetalle.setGlosa(((CarteraDetalleIf) referenceBeanCarteraDetalle).getObservacion());
				}
				
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
					String numeroAsiento = asientoLocal.procesarAsiento(asiento, asientoDetalleColeccion, true);
					
					//Busco el asiento generado para retornarlo
					Collection asientosRetornar = asientoLocal.findAsientoByNumero(numeroAsiento);
					if ( asientosRetornar!=null && asientosRetornar.iterator().hasNext() ){
						asientoRetornar = (AsientoIf)asientosRetornar.iterator().next();
					} else
						throw new GenericBusinessException("Error en Handler al generar asiento para Nota Interna de Credito");
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error en la generaci\u00f3n de Asiento Contable");
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Ocurri\u00f3 un error general al actualizar el Asiento Contable");
		}
		
		return asientoRetornar;
	}
	
	private AsientoIf registrarAsiento() throws GenericBusinessException {
		AsientoData data = new AsientoData();
		try {			
			java.sql.Date fechaAsiento = utilitariosLocal.fromTimestampToSqlDate(((CarteraIf) referenceBeanCartera).getFechaEmision());
			if (validateAsientoAutomatico(idEmpresa, fechaAsiento)) {
				String strEfectivo = "S";
				String strEstado = "P";	
				String unNumeroAsiento = asientoLocal.getNumeroAsiento(new java.sql.Date(fechaAsiento.getTime()), idEmpresa, planCuenta);
				if (unNumeroAsiento.length() == 1)
					unNumeroAsiento = "00" + unNumeroAsiento;
				else if (unNumeroAsiento.length() == 2)
					unNumeroAsiento = "0" + unNumeroAsiento;
				data.setEmpresaId(idEmpresa);
				data.setNumero(unNumeroAsiento);
				data.setStatus(strEstado);
				data.setEfectivo(strEfectivo);
				data.setPeriodoId(periodo.getId());
				data.setPlancuentaId(planCuenta.getId());
				/*String codigo = ((CarteraIf) referenceBeanCartera).getCodigo();
				String observacion = codigo;*/
				data.setObservacion(((CarteraIf) referenceBeanCartera).getComentario());
				data.setOficinaId(idOficina);
				data.setFecha(fechaAsiento);
				data.setSubtipoasientoId(eventoContable.getSubtipoAsientoId());
				data.setTipoDocumentoId(((CarteraIf) referenceBeanCartera).getTipodocumentoId());
				data.setTransaccionId(((CarteraIf) referenceBeanCartera).getId());
				data.setElaboradoPorId(((CarteraIf) referenceBeanCartera).getUsuarioId());
				data.setEventoContableId(this.eventoContable.getId());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al registrar el Asiento");
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
		NotaInternaCreditoClienteAsientoAutomaticoHandler.asientoDetalleColeccion = asientoDetalleColeccion;
	}
}
