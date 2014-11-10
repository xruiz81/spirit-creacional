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
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.session.DocumentoSessionLocal;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.TipoDocumentoSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;

@Stateless
public class ReciboCajaAsientoAutomaticoHandler implements ReciboCajaAsientoAutomaticoHandlerLocal {
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
	@EJB private EmpresaSessionLocal empresaLocal;
	@EJB private DocumentoSessionLocal documentoLocal;
	@EJB private TipoDocumentoSessionLocal tipoDocumentoLocal;
	@EJB private UtilitariosSessionLocal utilitariosLocal;

	private static final String TIPO_CUENTA_BANCARIA = "B";
	private static final String TIPO_CLIENTE = "C";
	private static final String TIPO_OFICINA = "O";
	private Map<String,Object> filtroMap = new HashMap<String,Object>();
	private PlanCuentaIf planCuenta = null;
	private PeriodoIf periodo = null;
	private static List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();	
	private EventoContableIf eventoContable = null;
	private Object referenceBean = null;
	private Map parameterMap = null;
	private boolean procesarAsiento = false;
	private Long idEmpresa = null;
	private Long idOficina = null;
	private Object carteraDetalle = null;
	private ClienteIf cliente = null;

	public AsientoIf generarReciboCajaAsientoAutomaticoHandler(EventoContableIf eventoContable, Map parameterMap, boolean procesarAsiento, Map<String,Object> parametrosEmpresa) throws GenericBusinessException {
		this.idEmpresa = (Long)parametrosEmpresa.get("EMPRESA_ID");
		this.idOficina = (Long)parametrosEmpresa.get("OFICINA_ID");
		this.eventoContable = eventoContable;
		try {
			this.planCuenta = planCuentaLocal.getPlanCuenta(eventoContable.getPlanCuentaId());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("No se encontr\u00f3 Plan de Cuenta de Evento Contable: "+eventoContable.getCodigo());
		}
		this.referenceBean = parameterMap.get("BEAN");
		this.carteraDetalle = parameterMap.get("CARTERA_DETALLE");
		this.parameterMap = parameterMap;
		this.procesarAsiento = procesarAsiento;
		return generarAsientoContable();
	}

	public AsientoIf generarAsientoContable() throws GenericBusinessException {
		AsientoIf asientoRetornar = null;
		try {
			Collection plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			DocumentoIf documento = documentoLocal.getDocumento(eventoContable.getDocumentoId());
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(documento.getTipoDocumentoId());
			Iterator plantillasIterator = plantillas.iterator();
			CuentaIf cuenta = null;
			ClienteOficinaIf operadorNegocioOficina = null;
			ClienteIf operadorNegocio = null;			
			operadorNegocioOficina = clienteOficinaLocal.getClienteOficina(((CarteraIf) referenceBean).getClienteoficinaId());
			operadorNegocio = clienteLocal.getCliente(operadorNegocioOficina.getClienteId());
			this.cliente = operadorNegocio;

			while (plantillasIterator.hasNext()) {
				PlantillaIf plantilla = (PlantillaIf) plantillasIterator.next();
				cuenta = cuentaLocal.getCuenta(plantilla.getCuentaId());
				if (cuenta.getImputable().equals("N")) {
					Map<String,Object> parameterMap = new HashMap<String,Object>();
					if (plantilla.getNemonico().startsWith("CTAXCOB")) {
						parameterMap.put("entidadId", operadorNegocio.getId());
						parameterMap.put("tipoEntidad", TIPO_CLIENTE);
					} else if (plantilla.getNemonico().equals("BANCO")) {
						Long idCuentaBancaria = (Long) this.parameterMap.get("CUENTA_BANCARIA_ID");
						parameterMap.put("entidadId", idCuentaBancaria);
						parameterMap.put("tipoEntidad", TIPO_CUENTA_BANCARIA);
					} else if (plantilla.getNemonico().equals("CAJA")) {
						parameterMap.put("entidadId", this.idOficina);
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
				String glosa = "";
				if(((CarteraIf) referenceBean).getCodigo().length() >= 20) {
					String codigoTipoDocumento = "RCA: ";
					String codigoCartera = ((CarteraIf) referenceBean).getCodigo();
					if (codigoCartera.length() == 25)
						codigoCartera = codigoCartera.substring(12,25);
					else
						codigoCartera = codigoCartera.substring(8,21);
					glosa = codigoTipoDocumento + codigoCartera;
				} else {
					String codigoTipoDocumento = "RCA: ";
					codigoTipoDocumento = tipoDocumento.getCodigo() + ": ";
					glosa = codigoTipoDocumento + ((CarteraIf) referenceBean).getCodigo();
				}

				asientoDetalle.setGlosa(glosa + " " + cliente.getRazonSocial());

				double valor = ((Double) this.parameterMap.get(plantilla.getNemonico())).doubleValue();
				if (plantilla.getDebehaber().equals("D")) {
					asientoDetalle.setDebe(BigDecimal.valueOf(valor));
					asientoDetalle.setHaber(BigDecimal.ZERO);
				} else if (plantilla.getDebehaber().equals("H")) {
					asientoDetalle.setHaber(BigDecimal.valueOf(valor));
					asientoDetalle.setDebe(BigDecimal.ZERO);
				}

				if (valor > 0.0){
					asientoDetalleColeccion.add(asientoDetalle);
				}
			}

			if (procesarAsiento && (asientoDetalleColeccion.size() > 0)) {
				AsientoIf asiento = registrarAsiento(tipoDocumento);
				if (asiento != null) {
					if (eventoContable.getAgruparDetalles().equals("S"))
						asientoDetalleColeccion = asientoLocal.agruparDetalles(asientoDetalleColeccion);
					asientoDetalleColeccion = asientoLocal.redondearDetallesAsiento(asientoDetalleColeccion);
					String numeroAsiento = asientoLocal.procesarAsiento(asiento, asientoDetalleColeccion, true);

					//Busco el asiento generado para retornarlo
					Collection asientosRetornar = asientoLocal.findAsientoByNumero(numeroAsiento);
					if ( asientosRetornar!=null && asientosRetornar.iterator().hasNext() ){
						asientoRetornar = (AsientoIf)asientosRetornar.iterator().next();
					} else
						throw new GenericBusinessException("Error en Handler al generar asiento para Recibo de Caja");
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new GenericBusinessException("Se ha producido un error en la generaci\u00f3n de Asiento Contable");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new GenericBusinessException("Ocurri\u00f3 un error general al actualizar el Asiento Contable");
		}

		return asientoRetornar;
	}

	private AsientoIf registrarAsiento(TipoDocumentoIf tipoDocumento) throws GenericBusinessException {
		AsientoData data = new AsientoData();
		try {		
			java.sql.Date fechaAsiento = utilitariosLocal.fromTimestampToSqlDate(((CarteraIf) referenceBean).getFechaEmision());
			if (validateAsientoAutomatico(idEmpresa, fechaAsiento)) {
				String strEstado = "P";	
				String unNumeroAsiento = getNumeroAsiento(new java.sql.Date(fechaAsiento.getTime()));
				String subtipoAsientoCodigo = "";
				data.setEmpresaId(idEmpresa);
				data.setNumero(unNumeroAsiento);
				data.setStatus(strEstado);
				data.setPeriodoId(periodo.getId());
				data.setPlancuentaId(planCuenta.getId());
				String codigoTipoDocumento = "RCA: ";
				String codigoCartera = ((CarteraIf) referenceBean).getCodigo();
				if(codigoCartera.length() >= 20){
					if (codigoCartera.length() == 25)
						codigoCartera = codigoCartera.substring(12,25);
					else
						codigoCartera = codigoCartera.substring(8,21);
					data.setObservacion(codigoTipoDocumento + codigoCartera + " " + cliente.getRazonSocial());
				} else {
					data.setObservacion(codigoTipoDocumento + ((CarteraIf) referenceBean).getCodigo() + " " + cliente.getRazonSocial());
				}

				data.setOficinaId(idOficina);
				data.setFecha(fechaAsiento);
				if (eventoContable.getSubtipoAsientoId() != null)
					data.setEfectivo("S");
				else
					data.setEfectivo("N");
				data.setSubtipoasientoId(eventoContable.getSubtipoAsientoId());
				data.setOficinaId(idOficina);
				data.setTipoDocumentoId((Long) this.parameterMap.get("TIPO_DOCUMENTO_ID"));
				data.setTransaccionId(((CarteraIf) referenceBean).getId());
				data.setElaboradoPorId(((CarteraIf) referenceBean).getUsuarioId());
				data.setEventoContableId(eventoContable.getId());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al registrar el Asiento");
		}

		return data;
	}

	/*private AsientoIf registrarAsientoForUpdate() {
		try {
			if (validateAsientoAutomatico()) {
				String strEfectivo = "S";
				String strEstado = "P";
				Date fechaAsiento = Utilitarios.dateHoy();
				String unNumeroAsiento = getNumeroAsiento();
				String subtipoAsientoCodigo = "";
				if (((CarteraIf) referenceBean).getTipo().equals("P"))
					subtipoAsientoCodigo = "PP";
				else if (((CarteraIf) referenceBean).getTipo().equals("C"))
					subtipoAsientoCodigo = "CC";
				SubtipoAsientoIf subtipoAsiento = (SubtipoAsientoIf) getSubtipoAsientoSessionService().findSubtipoAsientoByCodigo(subtipoAsientoCodigo).iterator().next();
				asiento.setNumero(unNumeroAsiento);
				asiento.setStatus(strEstado);
				asiento.setEfectivo(strEfectivo);
				asiento.setPeriodoId(periodo.getId());
				asiento.setPlancuentaId(planCuenta.getId());
				asiento.setObservacion("ASIENTO AUTOMÁTICO POR " + parameterMap.get("OBSERVACION").toString());
				asiento.setOficinaId(Parametros.getIdOficina());
				asiento.setFecha(new java.sql.Date(fechaAsiento.getTime()));

				if (subtipoAsiento != null)
					asiento.setSubtipoasientoId(subtipoAsiento.getId());

				asiento.setOficinaId(Parametros.getIdOficina());
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return asiento;
	}*/

	private String getNumeroAsiento(java.sql.Date fechaAsiento) throws GenericBusinessException {
		// "CREA-PC01-20070912-1012-013"
		// CODIGO_EMPRESA - CODIGO_PLAN_CUENTA - CODIGO_PERIODO - MES_AÑO - NUMERO_ASIENTO
		String codigo = "";

		try {
			EmpresaIf empresa = empresaLocal.getEmpresa(idEmpresa);
			//PeriodoIf periodo = (PeriodoIf) getCmbPeriodo().getSelectedItem();
			String mesAsiento = utilitariosLocal.getMonthFromDate(fechaAsiento);
			String anioAsiento = utilitariosLocal.getYearFromDate(fechaAsiento);
			codigo = empresa.getCodigo() + "-";
			codigo += planCuenta.getCodigo() + "-";
			//codigo += periodo.getCodigo() + "-";
			codigo += mesAsiento + "-";
			codigo += anioAsiento + "-";
			return codigo;
		} catch (GenericBusinessException e1) {
			e1.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al obtener el n\u00famero del asiento");
		}
	}

	private boolean validateAsientoAutomatico(Long empresaId, java.sql.Date fechaAsiento) throws GenericBusinessException {
		try {
			filtroMap.clear();
			filtroMap.put("empresaId", idEmpresa);
			filtroMap.put("estado", "A");
			Iterator planCuentaIterator = planCuentaLocal.findPlanCuentaByQuery(filtroMap).iterator();

			if (planCuentaIterator.hasNext())
				planCuenta = (PlanCuentaIf) planCuentaIterator.next();
			else {
				throw new GenericBusinessException("No existe un plan de cuentas activo.\nNo se ha podido generar el asiento contable.");
				//return false;
			}

			Iterator periodoIterator = periodoLocal.findPeriodoForAsientoAutomatico(empresaId, fechaAsiento).iterator();

			if (periodoIterator.hasNext())
				periodo = (PeriodoIf) periodoIterator.next();
			else
				return false;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error general al validar Asiento Autom\u00e1tico");
		}

		return true;
	}

	public static List<AsientoDetalleIf> getAsientoDetalleColeccion() {
		return asientoDetalleColeccion;
	}

	public void setAsientoDetalleColeccion(List<AsientoDetalleIf> asientoDetalleColeccion) {
		ReciboCajaAsientoAutomaticoHandler.asientoDetalleColeccion = asientoDetalleColeccion;
	}	
}
