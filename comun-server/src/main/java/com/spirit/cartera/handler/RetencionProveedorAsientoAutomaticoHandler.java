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
import com.spirit.compras.entity.CompraIf;
import com.spirit.comun.util.Retencion;
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
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.session.ParametroEmpresaSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;

@Stateless
public class RetencionProveedorAsientoAutomaticoHandler implements RetencionProveedorAsientoAutomaticoHandlerLocal{
	@PersistenceContext(unitName = "spirit")
	
	@EJB private CuentaEntidadSessionLocal cuentaEntidadLocal;
	@EJB private AsientoSessionLocal asientoLocal;
	@EJB private PeriodoSessionLocal periodoLocal;
	@EJB private CuentaSessionLocal cuentaLocal;	
	@EJB private ClienteOficinaSessionLocal clienteOficinaLocal;
	@EJB private ClienteSessionLocal clienteLocal;
	@EJB private PlantillaSessionLocal plantillaLocal;
	@EJB private PlanCuentaSessionLocal planCuentaLocal;
	@EJB private SubTipoAsientoSessionLocal subTipoAsientoLocal;
	@EJB private UtilitariosSessionLocal utilitariosLocal;
	@EJB private ParametroEmpresaSessionLocal parametrosEmpresaLocal;
	
	private Map<String,Object> filtroMap = new HashMap<String,Object>();
	private PlanCuentaIf planCuenta = null;
	private PeriodoIf periodo = null;
	private static List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
	private EventoContableIf eventoContable = null;
	private Object referenceCompraBean = null;
	private Object referenceCarteraBean = null;
	private Object referenceRetencionBean = null;
	private Map parameterMap = null;
	private boolean procesarAsiento = false;
	
	private Long idEmpresa = null;
	private Long idOficina = null;
	
	public AsientoIf generarRetencionProveedorAsientoAutomaticoHandler(EventoContableIf eventoContable, Map parameterMap, boolean procesarAsiento, Map<String,Object> parametrosEmpresa) throws GenericBusinessException {
		this.idEmpresa = (Long)parametrosEmpresa.get("idEmpresa");
		this.idOficina = (Long)parametrosEmpresa.get("idOficina");
		this.eventoContable = eventoContable;
		try {
			this.planCuenta = planCuentaLocal.getPlanCuenta(eventoContable.getPlanCuentaId());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("No se encontro Plan de Cuenta de Evento Contable: "+eventoContable.getCodigo());
		}
		this.referenceCompraBean = parameterMap.get("COMPRA_BEAN");
		this.referenceCarteraBean = parameterMap.get("CARTERA_BEAN");
		this.referenceRetencionBean = parameterMap.get("RETENCION_BEAN");
		this.parameterMap = parameterMap;
		this.procesarAsiento = procesarAsiento;
		//asientoDetalleColeccion = generarAsientoContable();
		return generarAsientoContable();
	}
	
	public AsientoIf generarAsientoContable() throws GenericBusinessException {
		AsientoIf asientoRetornar = null;
		try {
			
			//Busco los valores de Parametro Empresa para el Preimpreso
			ParametroEmpresaIf parametroPreimpresoEstablecimiento = (ParametroEmpresaIf)parametrosEmpresaLocal.findParametroEmpresaByCodigo("MAXLONG_PREIMP_ESTABLECIMIENTO").iterator().next();
			int preimpresoEstablecimiento = Integer.valueOf(parametroPreimpresoEstablecimiento.getValor());
			ParametroEmpresaIf parametroPreimpresoPuntoEmision = (ParametroEmpresaIf)parametrosEmpresaLocal.findParametroEmpresaByCodigo("MAXLONG_PREIMP_PUNTOEMISION").iterator().next();
			int preimpresoPuntoEmision = Integer.valueOf(parametroPreimpresoPuntoEmision.getValor());
			
			//Estos seran los puntos inicio y fin solo del secuencial de la factura
			//Se acumenta 2 en el punto inicio por los 2 guiones del preimpreso.
			int inicioPreimpresoFactura = preimpresoEstablecimiento + preimpresoPuntoEmision + 2;
			int finPreimpresoFactura = ((CompraIf) referenceCompraBean).getPreimpreso().length();
						
			
			System.out.println("EVENTO CONTABLE*******:"+eventoContable.getId());
			System.out.println("planCuenta********:"+planCuenta.getId());
						
			Collection plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			Iterator plantillasIterator = plantillas.iterator();
			CuentaIf cuenta = null;
			ClienteOficinaIf proveedorOficina = null;
			ClienteIf proveedor = null;			
			proveedorOficina = clienteOficinaLocal.getClienteOficina(((CompraIf) referenceCompraBean).getProveedorId());
			proveedor = clienteLocal.getCliente(proveedorOficina.getClienteId());
			
			while (plantillasIterator.hasNext()) {
				PlantillaIf plantilla = (PlantillaIf) plantillasIterator.next();
				cuenta = cuentaLocal.getCuenta(plantilla.getCuentaId());
				if (cuenta.getImputable().equals("N")) {
					//if (plantilla.getNemonico().equals("RETEFUENTE") && this.parameterMap.get("ID_CUENTA_RETEFUENTE") != null) {
					if (plantilla.getNemonico().equals("RETERENTA")) {
						Long idCuentaRetefuente = (Long) this.parameterMap.get("ID_CUENTA_RETERENTA");
						if (idCuentaRetefuente != null)
							cuenta = cuentaLocal.getCuenta(idCuentaRetefuente);
					//} else if (plantilla.getNemonico().equals("RETEIVA") && this.parameterMap.get("ID_CUENTA_RETEIVA") != null)  {
					} else if (plantilla.getNemonico().equals("RETEIVA"))  {
						Long idCuentaReteiva = (Long) this.parameterMap.get("ID_CUENTA_RETEIVA");
						if (idCuentaReteiva != null)
							cuenta = cuentaLocal.getCuenta(idCuentaReteiva);
					} else if (plantilla.getNemonico().equals("CTAXPAG")) {
						Map parameterMap = new HashMap();
						parameterMap.put("entidadId", proveedor.getId());
						parameterMap.put("tipoEntidad", "P");
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
				}
				
				if (cuenta != null) {
					AsientoDetalleIf asientoDetalle = new AsientoDetalleData();
					asientoDetalle.setCuentaId(cuenta.getId());
					asientoDetalle.setReferencia(String.valueOf(((CompraIf) referenceCompraBean).getReferencia()));
					String glosa = "";
					if (plantilla.getNemonico().equals("RETERENTA"))
						glosa = "RET. RENTA";
					else if (plantilla.getNemonico().equals("RETEIVA"))
						glosa = "RET. IVA";
					else if (plantilla.getNemonico().equals("CTAXPAG"))
						glosa = "RET.";
					asientoDetalle.setGlosa(glosa + " F: " + ((CompraIf) referenceCompraBean).getPreimpreso().substring(inicioPreimpresoFactura, finPreimpresoFactura) + "; R: " + ((Retencion) referenceRetencionBean).getSecuencial() + " " + proveedor.getRazonSocial());
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
				AsientoIf asiento = registrarAsiento(proveedor);
				if (asiento != null) {
					if (eventoContable.getAgruparDetalles().equals("S"))
						asientoDetalleColeccion = asientoLocal.agruparDetalles(asientoDetalleColeccion);
					String numeroAsiento = asientoLocal.procesarAsiento(asiento, asientoDetalleColeccion, true);
					
					//Busco el asiento generado para retornarlo
					Collection asientosRetornar = asientoLocal.findAsientoByNumero(numeroAsiento);
					if ( asientosRetornar!=null && asientosRetornar.iterator().hasNext() ){
						asientoRetornar = (AsientoIf)asientosRetornar.iterator().next();
					} else
						throw new GenericBusinessException("Error en Handler al generar asiento para Comprobante de Ingreso");
				
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
	
	private AsientoIf registrarAsiento(ClienteIf proveedor) throws GenericBusinessException {
		AsientoData data = new AsientoData();
		try {
			//Busco los valores de Parametro Empresa para el Preimpreso
			ParametroEmpresaIf parametroPreimpresoEstablecimiento = (ParametroEmpresaIf)parametrosEmpresaLocal.findParametroEmpresaByCodigo("MAXLONG_PREIMP_ESTABLECIMIENTO").iterator().next();
			int preimpresoEstablecimiento = Integer.valueOf(parametroPreimpresoEstablecimiento.getValor());
			ParametroEmpresaIf parametroPreimpresoPuntoEmision = (ParametroEmpresaIf)parametrosEmpresaLocal.findParametroEmpresaByCodigo("MAXLONG_PREIMP_PUNTOEMISION").iterator().next();
			int preimpresoPuntoEmision = Integer.valueOf(parametroPreimpresoPuntoEmision.getValor());
			
			//Estos seran los puntos inicio y fin solo del secuencial de la factura
			//Se acumenta 2 en el punto inicio por los 2 guiones del preimpreso.
			int inicioPreimpresoFactura = preimpresoEstablecimiento + preimpresoPuntoEmision + 2;
			int finPreimpresoFactura = ((CompraIf) referenceCompraBean).getPreimpreso().length();
			
			
			Date fechaAsiento = utilitariosLocal.fromTimestampToSqlDate(((CarteraIf) referenceCarteraBean).getFechaEmision());
			if (validateAsientoAutomatico(idEmpresa, fechaAsiento)) {
				String strEstado = "P";	
				//Date fechaAsiento = Utilitarios.dateHoy(); 
				String unNumeroAsiento = asientoLocal.getNumeroAsiento(fechaAsiento, idEmpresa, planCuenta);
				data.setEmpresaId(idEmpresa);
				data.setNumero(unNumeroAsiento);
				data.setStatus(strEstado);
				data.setPeriodoId(periodo.getId());
				data.setPlancuentaId(planCuenta.getId());
				String glosa = "RET.";
				data.setObservacion(glosa + " F: " + ((CompraIf) referenceCompraBean).getPreimpreso().substring(inicioPreimpresoFactura, finPreimpresoFactura) + "; R: " + ((Retencion) referenceRetencionBean).getSecuencial() + " " + proveedor.getRazonSocial());
				data.setOficinaId(idOficina);
				data.setFecha(fechaAsiento);
				if (eventoContable.getSubtipoAsientoId() != null)
					data.setEfectivo("S");
				else
					data.setEfectivo("N");
				data.setSubtipoasientoId(eventoContable.getSubtipoAsientoId());
				data.setOficinaId(idOficina);
				data.setTipoDocumentoId((Long) this.parameterMap.get("TIPO_DOCUMENTO_ID"));
				data.setTransaccionId(((CarteraIf) referenceCarteraBean).getId());
				data.setElaboradoPorId(((CarteraIf) referenceCarteraBean).getUsuarioId());
				data.setEventoContableId(eventoContable.getId());
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
		RetencionProveedorAsientoAutomaticoHandler.asientoDetalleColeccion = asientoDetalleColeccion;
	}
	
}
