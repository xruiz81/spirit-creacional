package com.spirit.contabilidad.session;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
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
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.contabilidad.entity.AsientoData;
import com.spirit.contabilidad.entity.AsientoDetalleData;
import com.spirit.contabilidad.entity.AsientoDetalleEJB;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoEJB;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.ChequeEmitidoEJB;
import com.spirit.contabilidad.entity.ChequeEmitidoIf;
import com.spirit.contabilidad.entity.CuentaEntidadIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.LogAsientoDetalleEJB;
import com.spirit.contabilidad.entity.LogAsientoEJB;
import com.spirit.contabilidad.entity.LogAsientoIf;
import com.spirit.contabilidad.entity.PeriodoDetalleIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.SaldoCuentaData;
import com.spirit.contabilidad.entity.SaldoCuentaEJB;
import com.spirit.contabilidad.entity.SaldoCuentaIf;
import com.spirit.contabilidad.entity.TipoCuentaIf;
import com.spirit.contabilidad.handler.ChequeDatos;
import com.spirit.contabilidad.handler.EstadoChequeEmitido;
import com.spirit.contabilidad.handler.EstadoPeriodo;
import com.spirit.contabilidad.handler.OrigenCheque;
import com.spirit.contabilidad.handler.TipoEntidadEnum;
import com.spirit.contabilidad.session.generated._AsientoSession;
import com.spirit.exception.CuentaNoImputableException;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.SaldoCuentaNegativoException;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoDocumentoEJB;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.session.CuentaBancariaSessionLocal;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.ModuloSessionLocal;
import com.spirit.general.session.ParametroEmpresaSessionLocal;
import com.spirit.general.session.TipoDocumentoSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.RolPagoEJB;
import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.handler.AsientoGenerado;
import com.spirit.nomina.handler.EstadoRolPagoDetalle;
import com.spirit.nomina.handler.EstadoRubroEventual;
import com.spirit.nomina.session.RolPagoDetalleSessionLocal;
import com.spirit.nomina.session.RubroEventualSessionLocal;
import com.spirit.performance.session.PerformanceInterceptor;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.util.DeepCopy;
import com.spirit.util.Utilitarios;
import com.truemesh.squiggle.SelectQuery;
import com.truemesh.squiggle.Table;
import com.truemesh.squiggle.criteria.MatchCriteria;

/**
 * The <code>AsientoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
@Interceptors( { PerformanceInterceptor.class })
public class AsientoSessionEJB extends _AsientoSession implements AsientoSessionRemote, AsientoSessionLocal {
	
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	@EJB private AsientoDetalleSessionLocal asientoDetalleLocal;
	@EJB private CuentaSessionLocal cuentaLocal;
	@EJB private EmpresaSessionLocal empresaLocal;
	@EJB private TipoCuentaSessionLocal tipoCuentaLocal;
	@EJB private PeriodoSessionLocal periodoLocal;
	@EJB private PeriodoDetalleSessionLocal periodoDetalleLocal;
	@EJB private CuentaEntidadSessionLocal cuentaEntidadLocal;
	@EJB private CuentaBancariaSessionLocal cuentaBancariaLocal;
	@EJB private ChequeEmitidoSessionLocal chequeEmitidoLocal;
	@EJB private UtilitariosSessionLocal utilitariosLocal;
	@EJB private LogAsientoSessionLocal logAsientoLocal;
	@EJB private TipoDocumentoSessionLocal tipoDocumentoLocal;
	@EJB private SaldoCuentaSessionLocal saldoCuentaLocal;
	
	@EJB private RolPagoDetalleSessionLocal rolPagoDetalleLocal;
	@EJB private RubroEventualSessionLocal rubroEventualLocal;
	
	@EJB private ModuloSessionLocal moduloLocal;
	@EJB private ParametroEmpresaSessionLocal parametroEmpresaLocal;
	
	@EJB private JPAManagerLocal jpManagerLocal;
	@Resource private SessionContext ctx; 
	
	private static Logger log = LogService.getLogger(AsientoSessionEJB.class);
	private DecimalFormat formatoSerial = new DecimalFormat("00000");
	private DecimalFormat formatoMonth = new DecimalFormat("00");
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findAsientoByQueryByFechaInicioAndFechaFin_Id(Map aMap, java.sql.Date fechaInicio, java.sql.Date fechaFin) {
		String objectName = "a";
		String diarios = (String) aMap.get("diarios");
		aMap.remove("diarios");
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select a.id,a.subtipoasientoId from AsientoEJB " + objectName + " where " + where + " and a.fecha between :fechaInicio and :fechaFin";
		if (diarios != null && diarios.equals("S"))
			queryString += " and a.transaccionId is null";
		queryString += " order by a.numero asc";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findAsientoByQueryByFechaInicioAndFechaFin(Map aMap, java.sql.Date fechaInicio, java.sql.Date fechaFin) {
		String objectName = "a";
		String diarios = (String) aMap.get("diarios");
		aMap.remove("diarios");
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct a from AsientoEJB " + objectName + " where " + where;
		if (diarios != null && diarios.equals("S"))
			queryString += " and a.transaccionId is null";
		if (fechaInicio != null)
			queryString += " and a.fecha >= :fechaInicio";
		if (fechaFin != null)
			queryString += " and a.fecha <= :fechaFin";
		queryString += " order by a.numero asc";
		Query query = manager.createQuery(queryString);
		if (fechaInicio != null)
			query.setParameter("fechaInicio",fechaInicio);
		if (fechaFin != null)
			query.setParameter("fechaFin",fechaFin);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findAsientoByPeriodoIdAndByPlanCuentaIdAndCuentaIdAndFechaInicioAndFechaFin(
			java.lang.Long idPeriodo, java.lang.Long idPlanCuenta, java.lang.Long idCuenta,
			java.sql.Date fechaInicio, java.sql.Date fechaFin) {
		String queryString = "select distinct a from AsientoEJB a, AsientoDetalleEJB ad where ad.asientoId = a.id and a.periodoId = "
			+ idPeriodo + " and a.plancuentaId = " + idPlanCuenta
			+ " and ad.cuentaId = " + idCuenta
			+ " and a.fecha between :fechaInicio and :fechaFin and a.status = 'A'";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by a.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findAsientoByTipoAsientoIdByEfectivoByPeriodoIdByPlanCuentaAndByFechaInicioAndFechaFin(
			java.lang.Long idTipoAsiento, java.lang.Long idPeriodo,
			java.lang.Long idPlanCuenta, java.sql.Date fechaInicio,
			java.sql.Date fechaFin) {
		String queryString = "select distinct e from AsientoEJB e, SubtipoAsientoEJB sa where e.subtipoasientoId = sa.id and sa.tipoId = "
			+ idTipoAsiento
			+ " and e.efectivo = 'S' and e.periodoId = "
			+ idPeriodo
			+ " and e.plancuentaId = "
			+ idPlanCuenta
			+ " and e.fecha between :fechaInicio and :fechaFin and e.status = 'A'";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Map<String, Object> actualizarAsiento(AsientoIf model, List<AsientoDetalleIf> modelDetalleList, AsientoIf modelAnterior, List<AsientoDetalleIf> modelDetalleAnteriorList, List<AsientoDetalleIf> modelDetalleRemovidoList, boolean reversarSaldos, boolean actualizarNumeroAsiento, UsuarioIf usuario, Map cuentasMap, Map tiposCuentaMap, Map saldosCuentasMap, Map periodosDetallesActivosMap, boolean mayorizacion) throws SaldoCuentaNegativoException, CuentaNoImputableException, GenericBusinessException {
		CuentaIf cuenta = null;
		Map<String,ChequeDatos> mapaChequesEliminados = new HashMap<String, ChequeDatos>();
		try {
			if (actualizarNumeroAsiento)
				model.setNumero(model.getNumero() + formatoSerial.format(getMaximoNumeroAsiento(model)));
			AsientoIf asiento = registrarAsiento(model);
			AsientoIf asientoAnterior = null;
			
			if (modelAnterior != null) {
				String log = "ASIENTO ACTUALIZADO ID: " + modelAnterior.getId();
				if (actualizarNumeroAsiento)
					log += ", CON CAMBIO DE CÓDIGO INCLUIDO: [" + modelAnterior.getNumero() + "] POR [" + model.getNumero() + "]";
				logAsientoLocal.procesarLogAsiento(modelAnterior, modelDetalleAnteriorList, log, usuario);
				asientoAnterior = registrarAsiento(modelAnterior);
			}
			
			Collection<CuentaIf> cuentasDeCuentasbancarias = (cuentasMap.size() > 0)?getCuentasDeCuentasBancariasList(asiento.getPlancuentaId(), cuentasMap):cuentaLocal.findCuentasDeCuentasBancarias(asiento.getPlancuentaId(), TipoEntidadEnum.CUENTA_BANCARIA.getLetra(), asiento.getEmpresaId());
			Set<String> setCuentasDeCuentasBancarias = crearSetCuentasDeCuentasBancarias(cuentasDeCuentasbancarias);
			
			boolean eliminarCheques = true;
			//if (modelDetalleAnteriorList != null && reversarSaldos) {
			if (modelDetalleAnteriorList != null ) {
     				for (AsientoDetalleIf modelDetalleAnterior : modelDetalleAnteriorList) {
					if(modelDetalleAnterior.getHaber().compareTo(new BigDecimal(0)) == 1){
						if ( eliminarCheques ){
							String cuentaCodigo = (cuentasMap.size() > 0)?((CuentaIf) cuentasMap.get(modelDetalleAnterior.getCuentaId())).getCodigo():cuentaLocal.getCuenta(modelDetalleAnterior.getCuentaId()).getCodigo();
							if( !setCuentasDeCuentasBancarias.contains(cuentaCodigo) ){
								eliminarCheques = false;
							} else {
								try{
									if ( modelDetalleAnterior.getReferencia() != null && !"".equals(modelDetalleAnterior.getReferencia().trim()) ){
										Integer.valueOf(modelDetalleAnterior.getReferencia());
										registrarChequeEmitido(mapaChequesEliminados, asiento, modelDetalleAnterior, cuentaCodigo);
									} else 
										eliminarCheques = false;
								} catch(Exception e){
									eliminarCheques = false;
								}
							}
						}
					}
				}
				
				if (reversarSaldos){
					for (AsientoDetalleIf modelDetalleAnterior : modelDetalleAnteriorList) {
						List<SaldoCuentaIf> saldoCuentaAfectados = afectarSaldoCuentaParaEliminar(asientoAnterior, modelDetalleAnterior, cuentasMap, tiposCuentaMap, saldosCuentasMap);
						for (SaldoCuentaIf saldo : saldoCuentaAfectados) {
							saldo = manager.merge(saldo);
							saldosCuentasMap = actualizarSaldosCuentasMap(saldosCuentasMap, saldo);
						}
					}
				}
			}
			
			if (modelDetalleRemovidoList != null) {
				for (AsientoDetalleIf modelAsientoDetalle : modelDetalleRemovidoList) {
					if (!esPreasiento(asiento)) {
						if (!esDetalleAnterior(modelDetalleAnteriorList, modelAsientoDetalle)) {
							cuenta = (cuentasMap.size() > 0)?(CuentaIf) cuentasMap.get(modelAsientoDetalle.getCuentaId()):cuentaLocal.getCuenta(modelAsientoDetalle.getCuentaId());
							if (cuenta.getImputable().equals("N"))
								throw new CuentaNoImputableException("No se puede autorizar el asiento: [" + asiento.getNumero() + "]\nCuenta no imputable: [" + cuenta.getCodigo() + "] " + cuenta.getNombre() + "\nEdite el asiento y seleccione la cuenta correcta");
							List<SaldoCuentaIf> saldoCuentaAfectados = afectarSaldoCuentaParaEliminar(asiento, modelAsientoDetalle, cuentasMap, tiposCuentaMap, saldosCuentasMap);
							for (SaldoCuentaIf saldo : saldoCuentaAfectados) {
								saldo = manager.merge(saldo);
								saldosCuentasMap = actualizarSaldosCuentasMap(saldosCuentasMap, saldo);
							}
						}
					}		
					AsientoDetalleEJB data = manager.find(AsientoDetalleEJB.class, modelAsientoDetalle.getId());
					manager.remove(data);
				}
			}
			
			manager.merge(asiento);
			SaldoCuentaIf saldoCuenta = null;
			boolean registrarCheques = (!mayorizacion)?true:false;
			Map<String, ChequeDatos> mapaChequesEmitidos = new HashMap<String, ChequeDatos>();
			
			if (modelDetalleList.size() > 0) {
				for (AsientoDetalleIf modelDetalle : modelDetalleList) {
					modelDetalle.setAsientoId(asiento.getPrimaryKey());
					AsientoDetalleIf asientoDetalle = registrarAsientoDetalle(modelDetalle);
					manager.merge(asientoDetalle);
					if (!esPreasiento(model)) {
						cuenta = (cuentasMap.size()>0)?(CuentaIf) cuentasMap.get(asientoDetalle.getCuentaId()):cuentaLocal.getCuenta(asientoDetalle.getCuentaId());
						if (cuenta.getImputable().equals("N"))
							throw new CuentaNoImputableException("No se puede autorizar el asiento: [" + asiento.getNumero() + "]\nCuenta no imputable: [" + cuenta.getCodigo() + "] " + cuenta.getNombre() + "\nEdite el asiento y seleccione la cuenta correcta");
						saldoCuenta = afectarSaldoCuenta(asiento,asientoDetalle, cuentasMap, tiposCuentaMap, saldosCuentasMap);
						saldoCuenta = manager.merge(saldoCuenta);
						saldosCuentasMap = actualizarSaldosCuentasMap(saldosCuentasMap, saldoCuenta);
						List<SaldoCuentaIf> saldoCuentaAfectados = afectarSaldosCuentas(asiento, modelDetalle, cuentasMap, tiposCuentaMap, saldosCuentasMap, periodosDetallesActivosMap);
						try {
							for (SaldoCuentaIf saldo : saldoCuentaAfectados) {
								saldo = manager.merge(saldo);
								saldosCuentasMap = actualizarSaldosCuentasMap(saldosCuentasMap, saldo);
							}
						} catch (Exception e1) {
							//ctx.setRollbackOnly();
							e1.printStackTrace();
							throw new GenericBusinessException("Se ha producido un error al afectar las cuentas");
						}
					}

					if(asientoDetalle.getHaber().compareTo(new BigDecimal(0)) == 1){
						if ( registrarCheques ){
							String cuentaCodigo = (cuentasMap.size() > 0)?((CuentaIf) cuentasMap.get(asientoDetalle.getCuentaId())).getCodigo():cuentaLocal.getCuenta(asientoDetalle.getCuentaId()).getCodigo();
							if( !setCuentasDeCuentasBancarias.contains(cuentaCodigo) ){
								registrarCheques = false;
							} else {
								try{
									//Para el caso en que se hace un credito y no se emite cheque
									if ( asientoDetalle.getReferencia() != null && !"".equals(asientoDetalle.getReferencia().trim()) ){
										Integer.valueOf(asientoDetalle.getReferencia().trim());
										registrarChequeEmitido(mapaChequesEmitidos, asiento, asientoDetalle, cuentaCodigo);
									} else 
										registrarCheques = false;
								} catch(Exception e){
									registrarCheques = false;
								}
							}
						}
					}
				}

				if ( registrarCheques ){
					Long transaccionId = null;
					Long tipoDocumentoId = null;
					if ( modelAnterior!= null ){
						tipoDocumentoId = modelAnterior.getTipoDocumentoId();
						if ( modelAnterior.getTransaccionId()!=null  )
							transaccionId = modelAnterior.getTransaccionId();
						else
							transaccionId = modelAnterior.getId();
					}
					else if ( asiento != null ){
						tipoDocumentoId = asiento.getTipoDocumentoId();
						if ( asiento.getTransaccionId()!=null  )
							transaccionId =asiento.getTransaccionId(); 
						else
							transaccionId = asiento.getId();
					}
					if (transaccionId.compareTo(1300L) == 0)
						System.out.println("HERE > " + asiento.getNumero());
					String origenCheque = OrigenCheque.ASIENTO.getLetra();
					if (tipoDocumentoId != null) { 
						TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(tipoDocumentoId);
						ModuloIf modulo = moduloLocal.getModulo(tipoDocumento.getModuloId());
						if (modulo.getCodigo().equals("CART"))
							origenCheque = "C";
						else if (modulo.getCodigo().equals("NOMI"))
							origenCheque = "N";
					}
					chequeEmitidoLocal.actualizarChequeEmitido(
							transaccionId, tipoDocumentoId, mapaChequesEmitidos,
							origenCheque,mapaChequesEliminados);
				}

				/************* Comentada la validación de saldo negativo*/
				//if (saldoCuenta!=null &&  saldoCuenta.getValor() < 0.0 && cuenta.getRelacionada() == null) {
				//ctx.setRollbackOnly();
				//throw new SaldoCuentaNegativoException(
				//"El saldo de la cuenta " + cuenta.getNombre() +" con c\u00f3digo: "+cuenta.getCodigo()+", se vuelve negativo.\n"+
				//"No se ha podido guardar el asiento");
				//}
			} else {
				AsientoIf asientoRemover = manager.find(AsientoEJB.class, asiento.getPrimaryKey());
				manager.remove(asientoRemover);
				manager.flush();
				asiento = null;
			}
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("ASIENTO", asiento);
			resultMap.put("SALDOS", saldosCuentasMap);
			return resultMap;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			ctx.setRollbackOnly();
			//manager.clear();
			//manager.close();
			if (ex instanceof SaldoCuentaNegativoException)
				throw new SaldoCuentaNegativoException(ex.getMessage());
			else if (ex instanceof CuentaNoImputableException)
				throw new CuentaNoImputableException(ex.getMessage());
			else
				throw new GenericBusinessException(ex.getMessage());
		}
	}
	
	private Collection<CuentaIf> getCuentasDeCuentasBancariasList(Long planCuentaId, Map cuentasMap) {
		Collection<CuentaIf> cuentas = new ArrayList<CuentaIf>();
		Iterator it = cuentasMap.keySet().iterator();
		while (it.hasNext()) {
			Long cuentaId = (Long) it.next();
			CuentaIf cuenta = (CuentaIf) cuentasMap.get(cuentaId);
			if (cuenta.getPlancuentaId().compareTo(planCuentaId) == 0 && cuenta.getCuentaBanco() != null && cuenta.getCuentaBanco().equals("S"))
				cuentas.add(cuenta);
		}
		return cuentas;
	}
	
	private boolean esDetalleAnterior(List<AsientoDetalleIf> modelDetalleAnteriorList, AsientoDetalleIf modelAsientoDetalle) {
		for (AsientoDetalleIf modelDetalleAnterior : modelDetalleAnteriorList) {
			if (modelDetalleAnterior.getCuentaId().compareTo(modelAsientoDetalle.getCuentaId()) == 0)
				return true;
		}
		
		return false;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String procesarAsiento(AsientoIf model,List<AsientoDetalleIf> modelDetalleList, boolean modificarNumeroAsiento) throws GenericBusinessException {
		AsientoIf asiento = null;
		CuentaIf cuenta = null;
		
		boolean registrarCheques = true;
		Map<String, ChequeDatos> mapaChequesEmitidos = new HashMap<String, ChequeDatos>();
		Set<String> setCuentasDeCuentasBancarias = null;		
		
		try {
			if (modificarNumeroAsiento)
				model.setNumero(model.getNumero() + formatoSerial.format(getMaximoNumeroAsiento(model)));
			asiento = registrarAsiento(model);
			manager.persist(asiento);
			
			Collection<CuentaIf> cuentasDeCuentasbancarias = cuentaLocal.findCuentasDeCuentasBancarias(
					asiento.getPlancuentaId(), TipoEntidadEnum.CUENTA_BANCARIA.getLetra(), asiento.getEmpresaId());
			setCuentasDeCuentasBancarias = crearSetCuentasDeCuentasBancarias(cuentasDeCuentasbancarias);
			
			for (AsientoDetalleIf modelDetalle : modelDetalleList) {
				modelDetalle.setAsientoId(asiento.getPrimaryKey());
				AsientoDetalleIf asientoDetalle = registrarAsientoDetalle(modelDetalle);
				manager.merge(asientoDetalle);
				
				if (!esPreasiento(model)) {
					cuenta = cuentaLocal.getCuenta(asientoDetalle.getCuentaId());
					
					System.out.println("ASIENTO>"+asiento);
					SaldoCuentaIf saldoCuenta = afectarSaldoCuenta(asiento,asientoDetalle, new HashMap(), new HashMap(), new HashMap());
					manager.merge(saldoCuenta);
					List<SaldoCuentaIf> saldoCuentaAfectados = afectarSaldosCuentas(asiento, modelDetalle, new HashMap(), new HashMap(), new HashMap(), new HashMap());
					try {
						for (SaldoCuentaIf saldo : saldoCuentaAfectados)
							manager.merge(saldo);
					} catch (Exception e1) {
						e1.printStackTrace();
						throw new GenericBusinessException("Se ha producido un error al afectar las cuentas");
					}
				}
				
				if((asientoDetalle.getHaber()!=null) && (asientoDetalle.getHaber().compareTo(new BigDecimal(0)) == 1)){
					if ( registrarCheques && asiento.getTipoDocumentoId() == null && asiento.getTransaccionId()== null ){
						CuentaIf cuentaDetalle = cuentaLocal.getCuenta(asientoDetalle.getCuentaId());
						String cuentaCodigo = cuentaDetalle.getCodigo();
						/*Map<String, Object> mapaBusqCheque = new HashMap<String, Object>();
						 mapaBusqCheque.put("cuentaId", cuentaDetalle.getId());
						 mapaBusqCheque.put("tipoEntidad", "B");
						 mapaBusqCheque.put("nemonico", "BANCO");
						 Collection<CuentaEntidadIf> cuentasEntidades = cuentaEntidadLocal.findCuentaEntidadByQuery(mapaBusqCheque);
						 if ( cuentasEntidades.size() > 0 ){
						 registrarChequeEmitido(mapaChequesEmitidos, asiento, asientoDetalle, cuentaCodigo);
						 } else {
						 registrarCheques = false;
						 }*/
						
						//if( !cuentaCodigo.equals("11010100001") && !cuentaCodigo.equals("11010100002") && !cuentaCodigo.equals("11010100003")){
						if( !setCuentasDeCuentasBancarias.contains(cuentaCodigo) ){
							registrarCheques = false;
						} else{
							registrarChequeEmitido(mapaChequesEmitidos, asiento, asientoDetalle, cuentaCodigo);
						}
					}
				}
			}
			//Se registra los cheques cuando todas las cuentas del haber sea de una de las 3 cuentas de banco
			if ( registrarCheques ){
				for ( String numeroCheque : mapaChequesEmitidos.keySet() ){
					ChequeDatos chequeDatos = mapaChequesEmitidos.get(numeroCheque);
					ChequeEmitidoIf chequeEmitido = chequeDatos.getChequeEmitido();
					if ( chequeEmitido.getValor().doubleValue() > 0D )
						chequeEmitidoLocal.procesarChequeEmitido(chequeDatos);
				}
			}
			
			return asiento.getNumero();
			
		} catch (GenericBusinessException e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (SaldoCuentaNegativoException e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("El saldo de la cuenta " + cuenta.getNombre() + " se vuelve negativo.\n" + "No se ha podido guardar el asiento");
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al guardar el asiento");
		}
	}
	
	private Set<String> crearSetCuentasDeCuentasBancarias(Collection<CuentaIf> cuentas){
		Set<String> setCuentas = new HashSet<String>();
		for ( CuentaIf c : cuentas )
			setCuentas.add(c.getCodigo());
		return setCuentas;
	}
	
	private void registrarChequeEmitido(Map<String, ChequeDatos> mapaChequesEmitidos, AsientoIf asientoIf,
			AsientoDetalleIf asientoDetalle,String codigoCuenta) throws GenericBusinessException {
		
		//Para el caso en que se hace un credito y no se emite cheque
		if ( asientoDetalle.getReferencia() == null || "".equals(asientoDetalle.getReferencia().trim()) )
			return;
		
		ChequeDatos chequeDatos = null;
		if ( !mapaChequesEmitidos.containsKey(asientoDetalle.getReferencia()) ){
			CuentaBancariaIf cuentaBancaria = buscarCuentaBancaria(asientoDetalle, codigoCuenta);
			 
			ChequeEmitidoIf chequeEmitidoIf = new ChequeEmitidoEJB();
			chequeEmitidoIf.setFechaEmision(new java.sql.Date(asientoIf.getFecha().getTime()));
			chequeEmitidoIf.setCuentaBancariaId(cuentaBancaria.getId());
			chequeEmitidoIf.setNumero(asientoDetalle.getReferencia().trim());
			chequeEmitidoIf.setDetalle(asientoDetalle.getGlosa());
			chequeEmitidoIf.setValor(utilitariosLocal.redondeoValor(asientoDetalle.getHaber()));
			chequeEmitidoIf.setEstado(chequeEmitidoLocal.getLetraEstadoChequeEmitido(EstadoChequeEmitido.EMITIDO));
								
			if ( asientoIf.getTipoDocumentoId() != null )
				chequeEmitidoIf.setTipoDocumentoId(asientoIf.getTipoDocumentoId());
			
			//Se revisa si el asiento es generado desde Nomina, entonces los registros cheque_emitido 
			//que se crean deben indicar que vienen de nomina y en transaccion_id el id del rol.
			boolean vieneDeNomina = false;
			if ( asientoIf.getTipoDocumentoId() != null ){
				TipoDocumentoIf tipoDocumentoNomina = tipoDocumentoLocal.getTipoDocumento(asientoIf.getTipoDocumentoId());
				ModuloIf moduloNomina = moduloLocal.getModulo(tipoDocumentoNomina.getModuloId());				
				if(moduloNomina.getNombre().equals(OrigenCheque.NOMINA.name())){
					vieneDeNomina = true;
				}
			}
			
			if(vieneDeNomina){
				chequeEmitidoIf.setTransaccionId(asientoIf.getTransaccionId());
				chequeEmitidoIf.setOrigen(OrigenCheque.NOMINA.getLetra());
			}else{
				chequeEmitidoIf.setTransaccionId(asientoIf.getPrimaryKey());
				chequeEmitidoIf.setOrigen(OrigenCheque.ASIENTO.getLetra());
			}			
			
			chequeEmitidoIf.setBeneficiario(asientoIf.getObservacion());
						
			chequeDatos =  new ChequeDatos();
			chequeDatos.setChequeEmitido(chequeEmitidoIf);
			
			if(vieneDeNomina){
				chequeDatos.setOrigen(OrigenCheque.NOMINA.getLetra());
				chequeDatos.getTransaccionesIds().add(asientoIf.getTransaccionId());
			}else{
				chequeDatos.setOrigen(OrigenCheque.ASIENTO.getLetra());
				chequeDatos.getTransaccionesIds().add(asientoIf.getPrimaryKey()!=null?asientoIf.getPrimaryKey():asientoIf.getId());
			}			
			
			mapaChequesEmitidos.put(asientoDetalle.getReferencia(), chequeDatos);
			
		} else {
			chequeDatos = mapaChequesEmitidos.get(asientoDetalle.getReferencia());
			ChequeEmitidoIf chequeEmitidoIf = chequeDatos.getChequeEmitido();
			BigDecimal valor =  chequeEmitidoIf.getValor();
			valor = valor.add(asientoDetalle.getHaber());
			chequeEmitidoIf.setValor( utilitariosLocal.redondeoValor(valor) );
			chequeDatos.setChequeEmitido(chequeEmitidoIf);
			chequeDatos.getTransaccionesIds().add(asientoIf.getPrimaryKey()!=null?asientoIf.getPrimaryKey():asientoIf.getId());
			mapaChequesEmitidos.put(asientoDetalle.getReferencia(), chequeDatos);
		}
	}
	
	private CuentaBancariaIf buscarCuentaBancaria(
			AsientoDetalleIf asientoDetalle, String codigoCuenta)
			throws GenericBusinessException {
		Map<String, Object> mapaCuentaEntidad = new HashMap<String, Object>();
		mapaCuentaEntidad.put("tipoEntidad","B");
		mapaCuentaEntidad.put("nemonico","BANCO");
		mapaCuentaEntidad.put("cuentaId",asientoDetalle.getCuentaId());
		
		Collection<CuentaEntidadIf> cuentasEntidades = cuentaEntidadLocal.findCuentaEntidadByQuery(mapaCuentaEntidad);
		if ( cuentasEntidades.size() == 0 )
			throw new GenericBusinessException("Cuenta No. \""+codigoCuenta+"\" no esta asociada a una cuanta Bancaria !!");
		else if ( cuentasEntidades.size() > 1 )
			throw new GenericBusinessException("Cuenta No. \""+codigoCuenta+"\" tiene asociada mas de una cuanta Bancaria !!");
		CuentaEntidadIf cuentaEntidad = cuentasEntidades.iterator().next();
		CuentaBancariaIf cuentaBancaria = cuentaBancariaLocal.getCuentaBancaria(cuentaEntidad.getEntidadId());
		return cuentaBancaria;
	}
	
	private int getMaximoNumeroAsiento(AsientoIf modelAsiento) {
		String queryString = "select max(numero) from AsientoEJB a where a.numero like '" + modelAsiento.getNumero() + "%'";
		Query query = manager.createQuery(queryString);
		String maxNumeroAsiento = query.getResultList().toString();
		queryString = "select max(numero) from LogAsientoEJB la where la.numero like '" + modelAsiento.getNumero() + "%'";
		query = manager.createQuery(queryString);
		String maxNumeroLog = query.getResultList().toString();
		String numero = (maxNumeroAsiento.compareTo(maxNumeroLog) >= 0 || maxNumeroLog.equals("[null]"))?maxNumeroAsiento:maxNumeroLog;
		int numeroAsiento = 1;
		if (!numero.equals("[null]")) {
			numero = numero.substring(1, numero.length()).replaceAll("]", "");
			numeroAsiento = Integer.parseInt(numero.split(modelAsiento.getNumero())[1]) + 1;
		}
		return numeroAsiento;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public SaldoCuentaIf afectarSaldoCuenta(AsientoIf asiento, AsientoDetalleIf asientoDetalle, Map cuentasMap, Map tiposCuentaMap, Map saldosCuentasMap) throws SaldoCuentaNegativoException,GenericBusinessException {
		CuentaIf cuenta = (cuentasMap.size() > 0)?(CuentaIf) cuentasMap.get(asientoDetalle.getCuentaId()):cuentaLocal.getCuenta(asientoDetalle.getCuentaId());
		TipoCuentaIf tipoCuentaIf = (tiposCuentaMap.size() > 0)?(TipoCuentaIf) tiposCuentaMap.get(cuenta.getTipocuentaId()):tipoCuentaLocal.getTipoCuenta(cuenta.getTipocuentaId());
		String tipoCuentaSegunAsiento = determinarTipoCuentaSegunAsiento(asientoDetalle);
		SaldoCuentaIf saldoCuenta = obtenerSaldoCuenta(asiento, asientoDetalle, saldosCuentasMap);
		aplicarAfectacionSaldoCuenta(asientoDetalle, tipoCuentaIf, tipoCuentaSegunAsiento, saldoCuenta);
		
		//TODO
		/*if (saldoCuenta.getValor()!=null &&  saldoCuenta.getValor() < 0.0 && cuenta.getRelacionada() == null) {
		 //ctx.setRollbackOnly();
		  throw new SaldoCuentaNegativoException("El saldo de la cuenta " + cuenta.getNombre() + " se vuelve negativo.\n" + 
		  "No se ha podido guardar el asiento");
		  }*/
		return saldoCuenta;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<SaldoCuentaIf> afectarSaldosCuentas(AsientoIf asiento, AsientoDetalleIf asientoDetalle, Map cuentasMap, Map tiposCuentaMap, Map saldosCuentasMap, Map periodosDetallesNoInactivosMap) throws SaldoCuentaNegativoException, GenericBusinessException{
		CuentaIf cuenta = (cuentasMap.size() > 0)?(CuentaIf) cuentasMap.get(asientoDetalle.getCuentaId()):cuentaLocal.getCuenta(asientoDetalle.getCuentaId());
		TipoCuentaIf tipoCuentaIf = (tiposCuentaMap.size() > 0)?(TipoCuentaIf) tiposCuentaMap.get(cuenta.getTipocuentaId()):tipoCuentaLocal.getTipoCuenta(cuenta.getTipocuentaId());
		String tipoCuentaSegunAsiento = determinarTipoCuentaSegunAsiento(asientoDetalle);
		
		List<SaldoCuentaIf> saldosCuentas = obtenerSaldosCuentas(asiento, asientoDetalle, false, saldosCuentasMap);
		Map mapaSaldosCuentas = generarMapaSaldosCuentas(saldosCuentas);
		//if (saldosCuentas.size() <= 0)
		generarSaldosCuentaSiguientesByPeriodoIdAndMesAndAnio(saldosCuentas, asiento, asientoDetalle, mapaSaldosCuentas, periodosDetallesNoInactivosMap);
		
		//if (saldosCuentas != null) {
		for (SaldoCuentaIf saldoCuenta : saldosCuentas) {
			aplicarAfectacionSaldoCuenta(asientoDetalle, tipoCuentaIf, tipoCuentaSegunAsiento, saldoCuenta);
			/**** Comentada validación de saldo cuenta negativo */
			//if (saldoCuenta.getValor() < 0.0 && cuenta.getRelacionada() == null) {
			//ctx.setRollbackOnly();
			//manager.clear();
			//manager.close();
			//throw new SaldoCuentaNegativoException(
			//"El saldo de la cuenta " + cuenta.getNombre() +" con c\u00f3digo: "+cuenta.getCodigo()+", se vuelve negativo.\n"+
			//"No se ha podido guardar el asiento");
			//}
		}
		//}
		
		return saldosCuentas;
	}
	
	private Map actualizarSaldosCuentasMap(Map saldosCuentasMap, SaldoCuentaIf saldoCuenta) {
		boolean saldoCuentaActualizado = false;
		Map saldosCuentasByCuentaIdMap = ((Map) saldosCuentasMap.get(saldoCuenta.getPeriodoId()) != null)?(Map) saldosCuentasMap.get(saldoCuenta.getPeriodoId()):new HashMap();
		Vector<SaldoCuentaIf> saldosCuentasVector = (saldosCuentasByCuentaIdMap != null && saldosCuentasByCuentaIdMap.get(saldoCuenta.getCuentaId())!=null)?(Vector<SaldoCuentaIf>) saldosCuentasByCuentaIdMap.get(saldoCuenta.getCuentaId()):new Vector<SaldoCuentaIf>();
		if (saldosCuentasVector.size() > 0) {
			for (int i=0; i<saldosCuentasVector.size(); i++) {
				SaldoCuentaIf saldo = saldosCuentasVector.get(i);
				if (saldo.getMes().compareTo(saldoCuenta.getMes()) == 0 && saldo.getAnio().compareTo(saldoCuenta.getAnio()) == 0) {
					saldosCuentasVector.set(i, saldoCuenta);
					saldoCuentaActualizado = true;
					break;
				}
			}
		}
		
		if (!saldoCuentaActualizado)
			saldosCuentasVector.add(saldoCuenta);
		
		saldosCuentasByCuentaIdMap.put(saldoCuenta.getCuentaId(), saldosCuentasVector);
		saldosCuentasMap.put(saldoCuenta.getPeriodoId(), saldosCuentasByCuentaIdMap);
		return saldosCuentasMap;
	}
	
	private Map generarMapaSaldosCuentas(List<SaldoCuentaIf> saldosCuentas) {
		Map mapaSaldosCuentas = new HashMap();
		Iterator it = saldosCuentas.iterator();
		while (it.hasNext()) {
			SaldoCuentaIf saldoCuenta = (SaldoCuentaIf)it.next();
			String yearMonthAccount = saldoCuenta.getAnio() + saldoCuenta.getMes() + String.valueOf(saldoCuenta.getCuentaId());
			mapaSaldosCuentas.put(yearMonthAccount, saldoCuenta);
		}
		
		return mapaSaldosCuentas;
	}
	
	private SaldoCuentaEJB obtenerSaldoCuenta(AsientoIf asiento, AsientoDetalleIf asientoDetalle, Map saldosCuentasMap) {
		SaldoCuentaEJB saldoCuenta = null;
		
		//System.out.println("asiento FECHA>"+asiento.getFecha());
		
		String mesAsiento = utilitariosLocal.getMonthFromDate(asiento.getFecha());
		
		//System.out.println("MES ASIENTO>"+mesAsiento);
		String anioAsiento = utilitariosLocal.getYearFromDate(asiento.getFecha());
		Collection c;
		c = findSaldoCuentaByPeriodoIdByMesAndByCuentaId(asiento.getPeriodoId(), mesAsiento, anioAsiento, asientoDetalle.getCuentaId());
		//c = findSaldoCuentaByPeriodoIdByMesAndByCuentaId(asiento.getPeriodoId(), mesAsiento, anioAsiento, asientoDetalle.getCuentaId(), saldosCuentasMap);
		if (c.size() == 1)
			saldoCuenta = (SaldoCuentaEJB) c.iterator().next();
		
		if (saldoCuenta == null) {
			saldoCuenta = new SaldoCuentaEJB();
			saldoCuenta.setMes(mesAsiento);
			saldoCuenta.setPeriodoId(asiento.getPeriodoId());
			saldoCuenta.setAnio(utilitariosLocal.getYearFromDate(asiento.getFecha()));
			saldoCuenta.setCuentaId(asientoDetalle.getCuentaId());
			saldoCuenta.setValordebe(new BigDecimal(0));
			saldoCuenta.setValorhaber(new BigDecimal(0));
			saldoCuenta.setValor(new BigDecimal(0));
		}
		
		return saldoCuenta;
	}
	
	private List<SaldoCuentaIf> obtenerSaldosCuentas(AsientoIf asiento, AsientoDetalleIf asientoDetalle, boolean mesAsientoIncluido, Map saldosCuentasMap) throws GenericBusinessException {
		List<SaldoCuentaIf> saldosCuentas = null;
		try {
			String mesAsiento = utilitariosLocal.getMonthFromDate(asiento.getFecha());
			String anioAsiento = utilitariosLocal.getYearFromDate(asiento.getFecha());
			//TODO: La búsqueda de saldos hay que hacerla desde aquí porque se está produciendo un error.
			//saldosCuentas = (List<SaldoCuentaIf>) saldoCuentaLocal.findSaldoCuentaSiguientesByPeriodoIdByMesAndByCuentaId(asiento.getPeriodoId(), mesAsiento, anioAsiento, asientoDetalle.getCuentaId(), mesAsientoIncluido);
			saldosCuentas = (List<SaldoCuentaIf>) findSaldoCuentaSiguientesByPeriodoIdByMesAndByCuentaId(asiento.getPeriodoId(), mesAsiento, anioAsiento, asientoDetalle.getCuentaId(), mesAsientoIncluido, saldosCuentasMap);
		} catch (Exception e) {
			//ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al obtener los saldos");
		}
		return saldosCuentas;
	}
	
	private List<SaldoCuentaIf> generarSaldosCuentaSiguientesByPeriodoIdAndMesAndAnio(List<SaldoCuentaIf> saldosCuentas, AsientoIf asiento, AsientoDetalleIf asientoDetalle, Map mapaSaldosCuentas, Map periodosDetallesNoInactivosMap) 
	throws GenericBusinessException {
		try {
			String mesAsiento = utilitariosLocal.getMonthFromDate(asiento.getFecha());
			String anioAsiento = utilitariosLocal.getYearFromDate(asiento.getFecha());
			Iterator periodosMesIterator = (periodosDetallesNoInactivosMap.size() > 0)?findPeriodoDetalleSiguientesNoInactivoByPeriodoIdAndMesAndAnio((Vector<PeriodoDetalleIf>) periodosDetallesNoInactivosMap.get(asiento.getPeriodoId()), mesAsiento, anioAsiento).iterator():periodoDetalleLocal.findPeriodoDetalleSiguientesNoInactivoByPeriodoIdAndMesAndAnio(asiento.getPeriodoId(), mesAsiento, anioAsiento, false).iterator();
			while (periodosMesIterator.hasNext()) {
				PeriodoDetalleIf periodoMes = (PeriodoDetalleIf) periodosMesIterator.next();
				String yearMonthAccount = periodoMes.getAnio() + periodoMes.getMes() + String.valueOf(asientoDetalle.getCuentaId());
				if (mapaSaldosCuentas.get(yearMonthAccount) == null) {
					SaldoCuentaEJB saldoCuentaEJB = new SaldoCuentaEJB();
					saldoCuentaEJB.setCuentaId(asientoDetalle.getCuentaId());
					saldoCuentaEJB.setPeriodoId(asiento.getPeriodoId());
					saldoCuentaEJB.setMes(periodoMes.getMes());
					saldoCuentaEJB.setAnio(periodoMes.getAnio());
					saldoCuentaEJB.setValordebe(new BigDecimal(0));
					saldoCuentaEJB.setValorhaber(new BigDecimal(0));
					saldosCuentas.add(saldoCuentaEJB);
				}
			}
		} catch (GenericBusinessException e) {
			//ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al Generar los Saldos");
		}
		
		return saldosCuentas;
	}
	
	private Collection findPeriodoDetalleSiguientesNoInactivoByPeriodoIdAndMesAndAnio(Vector<PeriodoDetalleIf> periodosDetallesVector, String mes, String anio) {
		Collection periodosDetalles = new ArrayList();
		for (int i=0; i<periodosDetallesVector.size(); i++) {
			PeriodoDetalleIf periodoDetalle = periodosDetallesVector.get(i);
			if (periodoDetalle.getMes().compareTo(mes) > 0 && periodoDetalle.getAnio().compareTo(anio) >= 0)
				periodosDetalles.add(periodoDetalle);
		}
		return periodosDetalles;
	}
	
	private void aplicarAfectacionSaldoCuenta(AsientoDetalleIf asientoDetalle, TipoCuentaIf tipoCuentaIf, String tipoCuentaSegunAsiento, SaldoCuentaIf saldoCuenta) {
		if (("D".equals(tipoCuentaIf.getDebehaber())) && ("D".equals(tipoCuentaSegunAsiento)))
			saldoCuenta.setValordebe(utilitariosLocal.redondeoValor(saldoCuenta.getValordebe().add(asientoDetalle.getDebe())));
		
		if (("D".equals(tipoCuentaIf.getDebehaber())) && ("H".equals(tipoCuentaSegunAsiento)))
			saldoCuenta.setValorhaber(utilitariosLocal.redondeoValor(saldoCuenta.getValorhaber().add(asientoDetalle.getHaber())));
		
		if (("H".equals(tipoCuentaIf.getDebehaber())) && ("D".equals(tipoCuentaSegunAsiento)))
			saldoCuenta.setValordebe(utilitariosLocal.redondeoValor(saldoCuenta.getValordebe().add(asientoDetalle.getDebe())));
		
		if (("H".equals(tipoCuentaIf.getDebehaber())) && ("H".equals(tipoCuentaSegunAsiento)))
			saldoCuenta.setValorhaber(utilitariosLocal.redondeoValor(saldoCuenta.getValorhaber().add(asientoDetalle.getHaber())));
		
		if ("D".equals(tipoCuentaIf.getDebehaber()))
			saldoCuenta.setValor(utilitariosLocal.redondeoValor( saldoCuenta.getValordebe().subtract(saldoCuenta.getValorhaber())) );
		else
			saldoCuenta.setValor(utilitariosLocal.redondeoValor( saldoCuenta.getValorhaber().subtract(saldoCuenta.getValordebe())) );
	}
	
	private String determinarTipoCuentaSegunAsiento(AsientoDetalleIf asientoDetalle) {
		// determinar si el valor esta en el debe o el haber
		BigDecimal zero = new BigDecimal(0.00);
		if (asientoDetalle.getDebe().compareTo(zero) == 1) {
			return "D";
		} else
			return "H";
	}
	
	public void verificarSaldoCuenta(Long asientoId, SaldoCuentaIf saldoCuenta, AsientoDetalleIf ad, Map cuentasMap) 
	throws SaldoCuentaNegativoException, GenericBusinessException {
		try{
			CuentaIf cuenta = (cuentasMap.size() > 0)?(CuentaIf) cuentasMap.get(ad.getCuentaId()):cuentaLocal.getCuenta(ad.getCuentaId());
			TipoCuentaIf tipoCuenta = tipoCuentaLocal.getTipoCuenta(cuenta.getTipocuentaId());
			String tipoCuentaSegunAsiento = determinarTipoCuentaSegunAsiento(ad);
			aplicarAfectacionSaldoCuenta(ad, tipoCuenta, tipoCuentaSegunAsiento, saldoCuenta);
			/******* Comentada validación saldo cuenta negativo ***/
			//if (saldoCuenta.getValor() < 0.0 && cuenta.getRelacionada() == null) {
			//ctx.setRollbackOnly();
			//throw new SaldoCuentaNegativoException("Saldo Cuenta Negativa " + "Cuenta:" + cuenta.getNombre() + " Asiento:" + asientoId);
			//}
		} catch(GenericBusinessException e){
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al verificar los saldos");
		}
	}
	
	/*@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findSaldoCuentaByPeriodoIdByMesAndByCuentaId(Long idPeriodo, String mes, String anio, Long idCuenta) {
		String queryString = "select distinct sc from SaldoCuentaEJB sc where sc.periodoId = "
			+ idPeriodo
			+ " and sc.mes = '"
			+ mes
			+ "' and sc.anio= '"
			+ anio + "' and sc.cuentaId = " + idCuenta + "";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}*/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findSaldoCuentaByPeriodoIdByMesAndByCuentaId(Long idPeriodo, String mes, String anio, Long idCuenta, Map saldosCuentasByPeriodoIdMap) {
		if (saldosCuentasByPeriodoIdMap.size() > 0) {
			List saldosCuentasList = new ArrayList();
			Map saldosCuentasByCuentaIdMap = (Map) saldosCuentasByPeriodoIdMap.get(idPeriodo);
			Vector<SaldoCuentaIf> saldosCuentasVector = (saldosCuentasByCuentaIdMap.get(idCuenta)!=null)?(Vector<SaldoCuentaIf>) saldosCuentasByCuentaIdMap.get(idCuenta):new Vector<SaldoCuentaIf>();
			if (saldosCuentasVector.size() > 0) {
				for (int i=0; i<saldosCuentasVector.size(); i++) {
					SaldoCuentaIf saldoCuenta = saldosCuentasVector.get(i);
					if (saldoCuenta.getMes().compareTo(mes) == 0 && saldoCuenta.getAnio().compareTo(anio) == 0)
						saldosCuentasList.add(saldoCuenta);
				}
				return saldosCuentasList;
			}
		}
		
		return findSaldoCuentaByPeriodoIdByMesAndByCuentaId(idPeriodo, mes, anio, idCuenta);
	}

	private Collection findSaldoCuentaByPeriodoIdByMesAndByCuentaId(
			Long idPeriodo, String mes, String anio, Long idCuenta) {
		String queryString = "select distinct sc from SaldoCuentaEJB sc where sc.periodoId = "
			+ idPeriodo
			+ " and sc.mes = '"
			+ mes
			+ "' and sc.anio = '"
			+ anio + "' and sc.cuentaId = " + idCuenta + "";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	/*@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List findSaldoCuentaSiguientesByPeriodoIdByMesAndByCuentaId(Long idPeriodo, String mes, String anio, Long idCuenta, boolean mesAsientoIncluido) {
		Query query = null;
		String queryString = "select distinct sc from SaldoCuentaEJB sc where sc.periodoId = " + idPeriodo;
		
		if (mesAsientoIncluido)
			queryString += " and sc.mes >= '" + mes;
		else
			queryString += " and sc.mes > '" + mes;
		
		queryString += "' and sc.anio >= '" + anio + "' and sc.cuentaId = " + idCuenta;
		query = manager.createQuery(queryString);
		
		return query.getResultList();
	}*/
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List findSaldoCuentaSiguientesByPeriodoIdByMesAndByCuentaId(Long idPeriodo, String mes, String anio, Long idCuenta, boolean mesAsientoIncluido, Map saldosCuentasByPeriodoIdMap) {
		if (saldosCuentasByPeriodoIdMap.size() > 0) {
			List saldosCuentasList = new ArrayList();
			Map saldosCuentasByCuentaIdMap = (Map) saldosCuentasByPeriodoIdMap.get(idPeriodo);
			//Vector<SaldoCuentaIf> saldosCuentasVector = (saldosCuentasByCuentaIdMap.get(idCuenta)!=null)?(Vector<SaldoCuentaIf>) saldosCuentasByCuentaIdMap.get(idCuenta):new Vector<SaldoCuentaIf>();
			Vector<SaldoCuentaIf> saldosCuentasVector = (saldosCuentasByCuentaIdMap != null && saldosCuentasByCuentaIdMap.get(idCuenta)!=null)?(Vector<SaldoCuentaIf>) saldosCuentasByCuentaIdMap.get(idCuenta):new Vector<SaldoCuentaIf>();
			if (saldosCuentasVector.size() > 0) {
				for (int i=0; i<saldosCuentasVector.size(); i++) {
					SaldoCuentaIf saldoCuenta = saldosCuentasVector.get(i);
					if (mesAsientoIncluido) {
						if (saldoCuenta.getMes().compareTo(mes) >= 0 && saldoCuenta.getAnio().compareTo(anio) >= 0)
							saldosCuentasList.add(saldoCuenta);
					} else if (saldoCuenta.getMes().compareTo(mes) > 0 && saldoCuenta.getAnio().compareTo(anio) >= 0)
						saldosCuentasList.add(saldoCuenta);

				}
				return saldosCuentasList;
			}
		}

		return findSaldoCuentaSiguientesByPeriodoIdByMesAndByCuentaId(idPeriodo, mes, anio, idCuenta, mesAsientoIncluido);
	}

	private List findSaldoCuentaSiguientesByPeriodoIdByMesAndByCuentaId(
			Long idPeriodo, String mes, String anio, Long idCuenta,
			boolean mesAsientoIncluido) {
		Query query = null;
		String queryString = "select distinct sc from SaldoCuentaEJB sc where sc.periodoId = " + idPeriodo;
		
		if (mesAsientoIncluido)
			queryString += " and sc.mes >= '" + mes;
		else
			queryString += " and sc.mes > '" + mes;
		
		queryString += "' and sc.anio >= '" + anio + "' and sc.cuentaId = " + idCuenta;
		query = manager.createQuery(queryString);
		
		return query.getResultList();
	}
	
	public List<SaldoCuentaIf> afectarSaldoCuentaParaEliminar(AsientoIf asiento, AsientoDetalleIf asientoDetalle, Map cuentasMap, Map tiposCuentaMap, Map saldosCuentasMap) 
	throws SaldoCuentaNegativoException, GenericBusinessException {
		List<SaldoCuentaIf> saldoCuentaSiguientes = null;
		try{	
			CuentaIf cuenta = (cuentasMap.size() > 0)?(CuentaIf) cuentasMap.get(asientoDetalle.getCuentaId()):cuentaLocal.getCuenta(asientoDetalle.getCuentaId());
			TipoCuentaIf tipoCuentaIf = (tiposCuentaMap.size() > 0)?(TipoCuentaIf) tiposCuentaMap.get(cuenta.getTipocuentaId()):tipoCuentaLocal.getTipoCuenta(cuenta.getTipocuentaId());
			String tipoCuentaSegunAsiento = determinarTipoCuentaSegunAsiento(asientoDetalle);
			saldoCuentaSiguientes = obtenerSaldosCuentas(asiento, asientoDetalle, true, saldosCuentasMap);
			if (saldoCuentaSiguientes !=  null) {
				try{
					for (SaldoCuentaIf saldoCuenta : saldoCuentaSiguientes)
						reversarAfectacionSaldoCuenta(asientoDetalle, tipoCuentaIf, tipoCuentaSegunAsiento, saldoCuenta);
				} catch(SaldoCuentaNegativoException e){
					throw new SaldoCuentaNegativoException(e.getMessage()+" "+cuenta.getCodigo() + " - " + cuenta.getNombre());
				}
			}
		} catch(GenericBusinessException e){
			throw new GenericBusinessException("Se ha producido un error al afectar el Saldo de la cuenta para eliminación");
		}
		return saldoCuentaSiguientes;
	}
	
	private void reversarAfectacionSaldoCuenta(AsientoDetalleIf asientoDetalle, TipoCuentaIf tipoCuentaIf, String tipoCuentaSegunAsiento, SaldoCuentaIf saldoCuenta) 
	throws SaldoCuentaNegativoException {
		double saldoCuentaTotal = 0.0;
		if (("D".equals(tipoCuentaIf.getDebehaber())) && ("D".equals(tipoCuentaSegunAsiento)))
			saldoCuenta.setValordebe(utilitariosLocal.redondeoValor(saldoCuenta.getValordebe().subtract(asientoDetalle.getDebe())));
		
		if (("D".equals(tipoCuentaIf.getDebehaber())) && ("H".equals(tipoCuentaSegunAsiento)))
			saldoCuenta.setValorhaber(utilitariosLocal.redondeoValor(saldoCuenta.getValorhaber().subtract(asientoDetalle.getHaber())));
		
		if (("H".equals(tipoCuentaIf.getDebehaber())) && ("D".equals(tipoCuentaSegunAsiento)))
			saldoCuenta.setValordebe(utilitariosLocal.redondeoValor(saldoCuenta.getValordebe().subtract(asientoDetalle.getDebe())));
		
		if (("H".equals(tipoCuentaIf.getDebehaber())) && ("H".equals(tipoCuentaSegunAsiento)))
			saldoCuenta.setValorhaber(utilitariosLocal.redondeoValor(saldoCuenta.getValorhaber().subtract(asientoDetalle.getHaber())));
		
		if ("D".equals(tipoCuentaIf.getDebehaber())) {
			saldoCuentaTotal = saldoCuenta.getValordebe().doubleValue() - saldoCuenta.getValorhaber().doubleValue();
			/**** Comentada validación de saldo de cuenta negativo*/
			//if ( saldoCuentaTotal < 0.0){
			//throw new SaldoCuentaNegativoException("Saldo negativo al reversar cuenta ");
			//}
			saldoCuenta.setValor(utilitariosLocal.redondeoValor(BigDecimal.valueOf(saldoCuentaTotal)));
		} else {
			saldoCuentaTotal = saldoCuenta.getValorhaber().doubleValue() - saldoCuenta.getValordebe().doubleValue();
			/******** Comentada validación de saldo de cuenta negativo */
			//if ( saldoCuentaTotal < 0.0){
			//throw new SaldoCuentaNegativoException("Saldo negativo al reversar cuenta ");
			//}
			saldoCuenta.setValor(utilitariosLocal.redondeoValor(BigDecimal.valueOf(saldoCuentaTotal)));
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findNumeradoresByNombreTablaAndByEmpresaId(String nombreTabla, Long idEmpresa) {
		String queryString = "from NumeradoresEJB e where e.nombreTabla = '" + nombreTabla + "' and e.empresaId = " + idEmpresa;
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void procesarEliminacionAsiento(AsientoIf asiento, String usuario, String log, boolean forzarEliminacionAsientoCierre) throws GenericBusinessException {
		try {
			//AsientoIf asiento = getAsiento(asientoId);
			
			//TODO: Esta validación de Período Activo o Parcial no está haciendo nada pues el método siempre devuelve true
			if (!periodoLocal.periodoActivoOParcial(asiento)) {
				String m = "No se puede eliminar asientos que no están dentro de un período mes activo o parcial";
				throw new GenericBusinessException(m);
			}
			
			if (!forzarEliminacionAsientoCierre && periodoDetalleLocal.periodoDetalleCerrado(utilitariosLocal.getYearFromDate(asiento.getFecha()), utilitariosLocal.getMonthFromDate(asiento.getFecha()), asiento.getPeriodoId())) {
				String m = "No se puede eliminar asientos dentro un período cerrado";
				throw new GenericBusinessException(m);
			}
			
			LogAsientoEJB logAsiento = null;
			if( log != null ){
				log = log + (", REALIZADO POR: " + usuario + ", FECHA: " + utilitariosLocal.getFechaUppercase(utilitariosLocal.dateHoy()) );
				logAsiento = registrarLogAsiento(asiento,log);
				manager.persist(logAsiento);
				manager.flush();
			}			
			
			Collection<CuentaIf> cuentasDeCuentasbancarias = cuentaLocal.findCuentasDeCuentasBancarias(
					asiento.getPlancuentaId(), TipoEntidadEnum.CUENTA_BANCARIA.getLetra(), asiento.getEmpresaId());
			Set<String> setCuentasDeCuentasBancarias = crearSetCuentasDeCuentasBancarias(cuentasDeCuentasbancarias);
			
			Set<Object[]> numerosYCuentaCheques =  new HashSet<Object[]>();
			Collection <AsientoDetalleIf> modelDetalleList = asientoDetalleLocal.findAsientoDetalleByAsientoId(asiento.getId());
			for (AsientoDetalleIf modelDetalle : modelDetalleList) {
				if (!esPreasiento(asiento)) {
					List<SaldoCuentaIf> saldoCuentaAfectados = afectarSaldoCuentaParaEliminar(asiento, modelDetalle, new HashMap(), new HashMap(), new HashMap());
					for (SaldoCuentaIf saldo : saldoCuentaAfectados)
						manager.merge(saldo);
				}
				
				if((modelDetalle.getHaber()!=null) && (modelDetalle.getHaber().compareTo(new BigDecimal(0)) == 1)){
					Map<String, Object> mapaBusqCheque = new HashMap<String, Object>();
					mapaBusqCheque.put("cuentaId", modelDetalle.getCuentaId());
					mapaBusqCheque.put("tipoEntidad", "B");
					mapaBusqCheque.put("nemonico", "BANCO");
					Collection<CuentaEntidadIf> cuentasEntidades = cuentaEntidadLocal.findCuentaEntidadByQuery(mapaBusqCheque);
					if ( cuentasEntidades.size() > 0 ){
						if ( cuentasEntidades.size() == 1 ){
							CuentaEntidadIf ce = cuentasEntidades.iterator().next();
							numerosYCuentaCheques.add(new Object[]{modelDetalle.getReferencia(),ce.getEntidadId()});
						} else {
							throw new GenericBusinessException("Existe mas de una Cuenta de Cheque para un Banco !!");
						}
					}
				} 
				
				if(logAsiento != null){
					LogAsientoDetalleEJB logAsientoDetalle = registrarLogAsientoDetalle(modelDetalle, usuario, asiento.getId(), log);
					logAsientoDetalle.setLogAsientoId(logAsiento.getPrimaryKey());
					manager.merge(logAsientoDetalle);
				}				
				
				manager.remove(modelDetalle);
			}
			
			for( Object[] o : numerosYCuentaCheques ){
				String numeroCheque = (String) o[0];
				Long cuentaBancariaId = (Long) o[1];
				chequeEmitidoLocal.anularChequesEmitidos(numeroCheque,cuentaBancariaId, logAsiento.getPrimaryKey());
				
				Map<String, Object> mapaCheques = new HashMap<String, Object>();
				mapaCheques.put("numero", numeroCheque);
				mapaCheques.put("cuentaBancariaId", cuentaBancariaId);
				Collection<ChequeEmitidoIf> cheques = chequeEmitidoLocal.findChequeEmitidoByQuery(mapaCheques); 
				if ( cheques.size() == 1 ){
					ChequeEmitidoIf ce = cheques.iterator().next();
					if ( ce.getOrigen().equals(OrigenCheque.NOMINA.getLetra()) ){
						mapaCheques = new HashMap<String, Object>();
						mapaCheques.put("preimpreso", numeroCheque);
						mapaCheques.put("cuentaBancariaId", cuentaBancariaId);
						Collection<RolPagoDetalleIf> rolDetalles = rolPagoDetalleLocal.findRolPagoDetalleByQuery(mapaCheques);
						Set<Long> setEventuales = new HashSet<Long>();
						for (RolPagoDetalleIf rpd : rolDetalles){
							if ( //rpd.getRubroEventualId() != null &&
								 EstadoRolPagoDetalle.PAGADO.getLetra().equals(rpd.getEstado()) ){
								rpd.setEstado(EstadoRolPagoDetalle.EMITIDO.getLetra());
								rpd.setFechaPago(null);
								rpd.setTipoPagoId(null);
								rpd.setCuentaBancariaId(null);
								rpd.setPreimpreso(null);
								verificarRubroEventual(setEventuales, rpd.getRubroEventualId());
							}
						}
						
						for ( Long rubroEventualId : setEventuales ){
							RubroEventualIf re = rubroEventualLocal.getRubroEventual(rubroEventualId);
							re.setEstado(EstadoRubroEventual.EMITIDO.getLetra());
						}
						
					}
					
				} else if ( cheques.size() > 1 ){
					throw new GenericBusinessException("Existe mas de un cheque del mismo numero con la misma cuenta !!");
				}
			}
			
			
			//Modificacion para nomina, se cambia el estado de asiento_generado
			//en tabla Rol_Pago a NO
			if ( asiento.getTipoDocumentoId() != null ){
				Collection<ModuloIf> modulos = moduloLocal.findModuloByNombre("NOMINA");
				if ( modulos.size() == 1 ){
					ModuloIf modulo = modulos.iterator().next();
					Collection<TipoDocumentoIf> tiposDocumentos = tipoDocumentoLocal.findTipoDocumentoByModuloId(modulo.getId());
					for ( TipoDocumentoIf td : tiposDocumentos ){
						if ( asiento.getTipoDocumentoId().equals(td.getId()) ){
							//RolPagoIf rolPagoIf = rolPagoLocal.getRolPago(asiento.getTransaccionId());
							RolPagoEJB rolPagoIf = manager.find(RolPagoEJB.class, asiento.getTransaccionId());
							rolPagoIf.setAsientoGenerado(AsientoGenerado.NO.getLetra());
						}
					}
				}
			}
			
			AsientoEJB asientoEJB = manager.find(AsientoEJB.class, asiento.getId());
			manager.remove(asientoEJB);
			manager.flush();	
		} catch(Exception e){
			ctx.setRollbackOnly();
			e.printStackTrace();
			
			if (e instanceof SaldoCuentaNegativoException)
				throw new GenericBusinessException(e.getMessage());
			if (e instanceof GenericBusinessException)
				throw new GenericBusinessException(e.getMessage());
			throw new GenericBusinessException("Se ha producido un error al eliminar información del saldo");
		}
	}
	
	private void verificarRubroEventual(Set<Long> setEventuales,Long rubroEventualId) throws GenericBusinessException{
		if ( rubroEventualId != null ){
			if ( !setEventuales.contains(rubroEventualId) ){
				setEventuales.add(rubroEventualId);
			} else
				throw new GenericBusinessException("Rubro Eventual repetido en detalle de Rol de Pago !!");
		}
	}
	
	/*public SaldoCuentaIf getSaldoCuentaMesAnterior(AsientoDetalleIf asientoDetalle, Date fechaAsiento, Long periodoId, Map saldosCuentasMap) {
		SaldoCuentaEJB saldoCuenta = null;
		String mesAnteriorAsiento = utilitariosLocal.getMonthBeforeFromDate(fechaAsiento);
		String anioAnteriorAsiento = utilitariosLocal.getYearBeforeFromDate(fechaAsiento);
		
		//Collection c = saldoCuentaLocal.findSaldoCuentaByPeriodoIdByMesAndByCuentaId(periodoId, mesAnteriorAsiento, anioAnteriorAsiento, asientoDetalle.getCuentaId());
		Collection c = findSaldoCuentaByPeriodoIdByMesAndByCuentaId(periodoId, mesAnteriorAsiento, anioAnteriorAsiento, asientoDetalle.getCuentaId(), saldosCuentasMap);
		if (c.size() == 1)
			saldoCuenta = (SaldoCuentaEJB) c.iterator().next();
		
		if (c.size() == 0) {
			saldoCuenta = new SaldoCuentaEJB();
			saldoCuenta.setValordebe(new BigDecimal(0));
			saldoCuenta.setValorhaber(new BigDecimal(0));
		}
		
		return saldoCuenta;
	}*/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarAsiento(Long asientoId) throws GenericBusinessException {
		try {
			AsientoEJB data = manager.find(AsientoEJB.class, asientoId);
			Collection<AsientoDetalleIf> modelDetalleList = asientoDetalleLocal.findAsientoDetalleByAsientoId(asientoId);
			
			for (AsientoDetalleIf modelDetalle : modelDetalleList){
				manager.remove(modelDetalle);
			}			
			manager.remove(data);
			manager.flush();
		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al eliminar información en AsientoEJB.", e);
			throw new GenericBusinessException("Error al eliminar información en Asiento");
		}
	}
	
	private boolean esPreasiento(AsientoIf asiento) {
		if ("P".equals(asiento.getStatus())) {
			return true;
		}
		return false;
	}
	
	private AsientoDetalleIf registrarAsientoDetalle(AsientoDetalleIf modelDetalle) {
		AsientoDetalleEJB asientoDetalle = new AsientoDetalleEJB();
		asientoDetalle.setId(modelDetalle.getId());
		asientoDetalle.setCuentaId(modelDetalle.getCuentaId());
		asientoDetalle.setAsientoId(modelDetalle.getAsientoId());
		asientoDetalle.setReferencia(modelDetalle.getReferencia());
		asientoDetalle.setGlosa(modelDetalle.getGlosa());
		asientoDetalle.setCentrogastoId(modelDetalle.getCentrogastoId());
		asientoDetalle.setEmpleadoId(modelDetalle.getEmpleadoId());
		asientoDetalle.setDepartamentoId(modelDetalle.getDepartamentoId());
		asientoDetalle.setLineaId(modelDetalle.getLineaId());
		asientoDetalle.setClienteId(modelDetalle.getClienteId());
		asientoDetalle.setDebe(utilitariosLocal.redondeoValor(modelDetalle.getDebe()));
		asientoDetalle.setHaber(utilitariosLocal.redondeoValor(modelDetalle.getHaber()));
		return asientoDetalle;
	}
	
	public LogAsientoDetalleEJB registrarLogAsientoDetalle(AsientoDetalleIf modelDetalle, String usuario, Long asientoId,String log) {
		LogAsientoDetalleEJB logAsientoDetalle = new LogAsientoDetalleEJB();
		logAsientoDetalle.setCuentaId(modelDetalle.getCuentaId());
		logAsientoDetalle.setLogAsientoId(modelDetalle.getAsientoId());
		logAsientoDetalle.setReferencia(modelDetalle.getReferencia());
		logAsientoDetalle.setGlosa(modelDetalle.getGlosa());
		logAsientoDetalle.setCentrogastoId(modelDetalle.getCentrogastoId());
		logAsientoDetalle.setEmpleadoId(modelDetalle.getEmpleadoId());
		logAsientoDetalle.setDepartamentoId(modelDetalle.getDepartamentoId());
		logAsientoDetalle.setLineaId(modelDetalle.getLineaId());
		logAsientoDetalle.setClienteId(modelDetalle.getClienteId());
		logAsientoDetalle.setDebe(modelDetalle.getDebe());
		logAsientoDetalle.setHaber(modelDetalle.getHaber());
		logAsientoDetalle.setLog("ASIENTO_DETALLE_ID: " + modelDetalle.getId() + ", DEL "+ log );
		
		return logAsientoDetalle;
	}
	
	public AsientoEJB registrarAsiento(AsientoIf model) {
		AsientoEJB asiento = new AsientoEJB();
		asiento.setId(model.getId());
		asiento.setNumero(model.getNumero());
		asiento.setEmpresaId(model.getEmpresaId());
		asiento.setPeriodoId(model.getPeriodoId());
		asiento.setPlancuentaId(model.getPlancuentaId());
		asiento.setFecha(model.getFecha());
		asiento.setStatus(model.getStatus());
		asiento.setEfectivo(model.getEfectivo());
		asiento.setSubtipoasientoId(model.getSubtipoasientoId());
		asiento.setObservacion(model.getObservacion());
		asiento.setOficinaId(model.getOficinaId());
		asiento.setTipoDocumentoId(model.getTipoDocumentoId());
		asiento.setTransaccionId(model.getTransaccionId());
		asiento.setElaboradoPorId(model.getElaboradoPorId());
		asiento.setAutorizadoPorId(model.getAutorizadoPorId());
		asiento.setEventoContableId(model.getEventoContableId());
		asiento.setAsientoCierre(model.getAsientoCierre());
		asiento.setUsarNota(model.getUsarNota());
		asiento.setNota(model.getNota());
		return asiento;
	}
	
	public AsientoEJB registrarAsiento(LogAsientoIf model) {
		AsientoEJB asiento = new AsientoEJB();
		asiento.setNumero(model.getNumero());
		asiento.setEmpresaId(model.getEmpresaId());
		asiento.setPeriodoId(model.getPeriodoId());
		asiento.setPlancuentaId(model.getPlancuentaId());
		asiento.setFecha(model.getFecha());
		asiento.setStatus(model.getStatus());
		asiento.setEfectivo(model.getEfectivo());
		asiento.setSubtipoasientoId(model.getSubtipoasientoId());
		asiento.setObservacion(model.getObservacion());
		asiento.setOficinaId(model.getOficinaId());
		asiento.setTipoDocumentoId(model.getTipoDocumentoId());
		asiento.setTransaccionId(model.getTransaccionId());
		asiento.setElaboradoPorId(model.getElaboradoPorId());
		asiento.setAutorizadoPorId(model.getAutorizadoPorId());
		asiento.setCarteraAfectaId(model.getCarteraAfectaId());
		asiento.setEventoContableId(model.getEventoContableId());
		asiento.setAsientoCierre(model.getAsientoCierre());
		asiento.setUsarNota(model.getUsarNota());
		asiento.setNota(model.getNota());
		return asiento;
	}
	
	public LogAsientoEJB registrarLogAsiento(AsientoIf model, String log) {
		LogAsientoEJB logAsiento = new LogAsientoEJB();
		logAsiento.setNumero(model.getNumero());
		logAsiento.setEmpresaId(model.getEmpresaId());
		logAsiento.setPeriodoId(model.getPeriodoId());
		logAsiento.setPlancuentaId(model.getPlancuentaId());
		logAsiento.setFecha(new java.sql.Date(model.getFecha().getTime()));
		logAsiento.setStatus(model.getStatus());
		logAsiento.setEfectivo(model.getEfectivo());
		logAsiento.setSubtipoasientoId(model.getSubtipoasientoId());
		logAsiento.setObservacion(model.getObservacion());
		logAsiento.setOficinaId(model.getOficinaId());
		logAsiento.setTipoDocumentoId(model.getTipoDocumentoId());
		logAsiento.setTransaccionId(model.getTransaccionId());
		logAsiento.setElaboradoPorId(model.getElaboradoPorId());
		logAsiento.setAutorizadoPorId(model.getAutorizadoPorId());
		logAsiento.setCarteraAfectaId(model.getCarteraAfectaId());
		logAsiento.setEventoContableId(model.getEventoContableId());
		logAsiento.setLog(log);
		logAsiento.setAsientoCierre(model.getAsientoCierre());
		logAsiento.setUsarNota(model.getUsarNota());
		return logAsiento;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getAsientoList(int startIndex, int endIndex, Map aMap) {
		//if (startIndex < 1) {
		//   startIndex = 1;
		//}
		if ( (endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct "+objectName+
		" from AsientoEJB e where " + where;
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
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
	public int getAsientoListSize(Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		Query countQuery = manager.createQuery("select count(*) from AsientoEJB "+objectName+" where "+where);
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			countQuery.setParameter(propertyKey, property);
		}
		List countQueryResult = countQuery.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}
	
	
	/********************* CONCILIACION BANCARIA *****************************/	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<Object[]> findAsientosConciliacionBancaria(Long cuentaId, java.sql.Date fechaInicio, java.sql.Date fechaFin) {
		
		//sum(ad.debe), sum(ad.haber) from ASIENTO a, ASIENTO_DETALLE ad where ad.CUENTA_ID = 11 and a.ID = ad.ASIENTO_ID and a.FECHA >= '2011-01-01' and a.STATUS = 'A'
		String select = "select distinct a.numero, a.fecha, a.observacion, ad.referencia, sum(ad.debe), sum(ad.haber), a.tipoDocumentoId, a.transaccionId";
		String from = "from AsientoEJB a, AsientoDetalleEJB ad";
		String where = "where a.id = ad.asientoId and a.fecha >= :fechaInicio and a.fecha <= :fechaFin and ad.cuentaId = :cuentaId and a.status = 'A'";
		String groupBy = "group by a.numero, ad.referencia";
		String orderBy = "order by ad.referencia, a.numero";
		String queryString = select + " " + from + " " + where + " " + groupBy + " " + orderBy;
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		query.setParameter("cuentaId", cuentaId);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<Object[]> findAsientosConciliacionBancariaExtendida(Long cuentaId, java.sql.Date fechaInicio, java.sql.Date fechaFin) {
		
		//sum(ad.debe), sum(ad.haber) from ASIENTO a, ASIENTO_DETALLE ad where ad.CUENTA_ID = 11 and a.ID = ad.ASIENTO_ID and a.FECHA >= '2011-01-01' and a.STATUS = 'A'
		String select = "select distinct a.numero, a.fecha, ad.glosa, ad.referencia, ad.debe, ad.haber, a.tipoDocumentoId, a.transaccionId, ad.id, ad.cuentaId";
		String from = "from AsientoEJB a, AsientoDetalleEJB ad";
		String where = "where a.id = ad.asientoId and a.fecha >= :fechaInicio and a.fecha <= :fechaFin and ad.cuentaId = :cuentaId and a.status = 'A'";
		//String groupBy = "group by a.numero, ad.referencia";
		String orderBy = "order by ad.referencia, a.numero";
		String queryString = select + " " + from + " " + where + " " + orderBy;
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		query.setParameter("cuentaId", cuentaId);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<Object[]> findAsientosPendientesPorCobrarConciliacionFondoRotativo(Long cuentaId, java.sql.Date fechaFin) throws GenericBusinessException {
		
		String select = "select distinct a.numero, a.fecha, ad.glosa, ad.referencia, ad.debe, ad.haber, a.tipoDocumentoId, a.transaccionId, ad.id, ad.cuentaId";
		String from = "from AsientoEJB a, AsientoDetalleEJB ad";
		String where = "where a.id = ad.asientoId and a.fecha <= :fechaFin and ad.cuentaId = :cuentaId and a.status = 'A'";
		String orderBy = "order by ad.referencia, a.numero";
		String queryString = select + " " + from + " " + where + " " + orderBy;
		
		Query query = manager.createQuery(queryString);		
		query.setParameter("fechaFin", fechaFin);
		query.setParameter("cuentaId", cuentaId);
		
		return query.getResultList();
	}
	
	private List<Object[]> filtrarListaByPreimpreso ( List<Object[]> lista ){
		String chequeAnterior = null;
		BigDecimal valorAnterior = BigDecimal.ZERO; 
		Object[] oAnterior = null;
		for ( Iterator<Object[]> itLista = lista.iterator() ; itLista.hasNext() ;  ){
			Object[] o = itLista.next();
			if ( chequeAnterior != null && chequeAnterior.equals( (String)o[3] ) ){
				oAnterior[1] = oAnterior[1]+","+((String)o[1]); 
				valorAnterior = valorAnterior.add( (BigDecimal) o[4] );
				oAnterior[4] = valorAnterior;
				itLista.remove();
			}else {
				oAnterior = o;
				chequeAnterior = (String)o[3];
				valorAnterior = (BigDecimal) o[4];
			}
		}
		return lista;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findDiariosByQueryAndByFechaInicioAndFechaFin(Map aMap, java.sql.Date fechaInicio, java.sql.Date fechaFin, boolean fechaInicioInclusive) throws GenericBusinessException {
		String diarios = (String) aMap.get("diarios");
		String queryString = "";
		Long idEmpresa = (Long) aMap.get("empresaId");
		Long idPeriodo = (Long) aMap.get("periodoId");
		Long idPlanCuenta = (Long)aMap.get("plancuentaId");
		String status = (String) aMap.get("status");
		Long idTipoDocumento = null;
		if (aMap.get("tipoDocumentoId") != null)
			idTipoDocumento = (Long) aMap.get("tipoDocumentoId");
		
		if (fechaInicioInclusive)
			queryString = "select distinct a, ad from AsientoEJB a, AsientoDetalleEJB ad where a.empresaId = " + idEmpresa + " and ad.asientoId = a.id and a.periodoId = " + idPeriodo + " and a.plancuentaId = " + idPlanCuenta + " and a.fecha between :fechaInicio and :fechaFin";
		else
			queryString = "select distinct a, ad from AsientoEJB a, AsientoDetalleEJB ad where a.empresaId = " + idEmpresa + " and ad.asientoId = a.id and a.periodoId = " + idPeriodo + " and a.plancuentaId = " + idPlanCuenta + " and a.fecha > :fechaInicio and a.fecha <= :fechaFin";
		
		if (status.equals("A") || status.equals("P"))
			queryString += " and a.status = '" + status + "'";
		
		if (idTipoDocumento != null)
			queryString += " and a.tipoDocumentoId = " + idTipoDocumento;
		
		if (diarios != null && diarios.equals("S"))
			queryString += " and a.transaccionId is null";
		
		queryString += " order by a.numero asc, ad.debe desc, ad.haber desc";
		
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		return query.getResultList();
	}
	
	public List<AsientoDetalleIf> agruparDetalles(List<AsientoDetalleIf> asientoDetalleColeccion) {
		List<AsientoDetalleIf> detallesAgrupados = new ArrayList<AsientoDetalleIf>();
		Iterator asientoDetalleIterator = asientoDetalleColeccion.iterator();
		
		while(asientoDetalleIterator.hasNext()) {
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) asientoDetalleIterator.next();
			Iterator detallesAgrupadosIterator = detallesAgrupados.iterator();
			boolean agrupado = false;
			while (detallesAgrupadosIterator.hasNext()) {
				AsientoDetalleIf detalleAgrupado = (AsientoDetalleIf) detallesAgrupadosIterator.next();
				if (asientoDetalle.getCuentaId().compareTo(detalleAgrupado.getCuentaId()) == 0 && ((asientoDetalle.getDebe().doubleValue() > 0D && detalleAgrupado.getDebe().doubleValue() > 0D) || (asientoDetalle.getHaber().doubleValue() > 0D && detalleAgrupado.getHaber().doubleValue() > 0D))) {
					double valorDebe = 0D;
					double valorHaber = 0D;
					if (detalleAgrupado.getDebe() != null && detalleAgrupado.getDebe().compareTo(BigDecimal.ZERO) != 0)
						valorDebe = detalleAgrupado.getDebe().doubleValue();
					else if (detalleAgrupado.getHaber() != null && detalleAgrupado.getHaber().compareTo(BigDecimal.ZERO) != 0)
						valorHaber = detalleAgrupado.getHaber().doubleValue();
					
					if (asientoDetalle.getDebe() != null && asientoDetalle.getDebe().compareTo(BigDecimal.ZERO) != 0)
						valorDebe += asientoDetalle.getDebe().doubleValue();
					else if (asientoDetalle.getHaber() != null && asientoDetalle.getHaber().compareTo(BigDecimal.ZERO) != 0)
						valorHaber += asientoDetalle.getHaber().doubleValue();
					
					if (valorDebe >= 0.0){
						detalleAgrupado.setDebe(BigDecimal.valueOf(valorDebe));
						//detalleAgrupado.setDebe(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorDebe)));
					}
					
					if (valorHaber >= 0.0){
						detalleAgrupado.setHaber(BigDecimal.valueOf(valorHaber));
						//detalleAgrupado.setHaber(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorHaber)));
					}
					
					agrupado = true;
				}
			}
			
			if (!agrupado)
				detallesAgrupados.add(asientoDetalle);
		}
		
		asientoDetalleColeccion = detallesAgrupados;
		
		return asientoDetalleColeccion;
	}
	
	public List<AsientoDetalleIf> desagruparDetalles(List<AsientoDetalleIf> asientoDetalleColeccion) {
		List<AsientoDetalleIf> detallesDesagrupados = new ArrayList<AsientoDetalleIf>();
		Iterator asientoDetalleIterator = asientoDetalleColeccion.iterator();
		
		while(asientoDetalleIterator.hasNext()) {
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) asientoDetalleIterator.next();
			if (asientoDetalle.getDebe().doubleValue() > 0D && asientoDetalle.getHaber().doubleValue() > 0D) {
				AsientoDetalleIf asientoDetalleDesagrupado = (AsientoDetalleIf) DeepCopy.copy(asientoDetalle);
				asientoDetalle.setHaber(BigDecimal.ZERO);
				detallesDesagrupados.add(asientoDetalle);
				asientoDetalleDesagrupado.setDebe(BigDecimal.ZERO);
				detallesDesagrupados.add(asientoDetalleDesagrupado);
			} else {
				detallesDesagrupados.add(asientoDetalle);
			}
		}
		
		asientoDetalleColeccion = detallesDesagrupados;
		
		return asientoDetalleColeccion;
	}
	
	public List<AsientoDetalleIf> redondearDetallesAsiento(List<AsientoDetalleIf> asientoDetalleColeccion) {
		Iterator it = asientoDetalleColeccion.iterator();
		List<AsientoDetalleIf> asientoDetallesList = new ArrayList<AsientoDetalleIf>();
		while (it.hasNext()) {
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) it.next();
			BigDecimal debe = utilitariosLocal.redondeoValor(asientoDetalle.getDebe());
			asientoDetalle.setDebe(debe);
			BigDecimal haber = utilitariosLocal.redondeoValor(asientoDetalle.getHaber());
			asientoDetalle.setHaber(haber);
			asientoDetallesList.add(asientoDetalle);
		}
		
		return asientoDetallesList;
	}
	
	public String getNumeroAsiento(java.sql.Date fechaAsiento, Long empresaId, PlanCuentaIf planCuenta) throws GenericBusinessException {
		// "CREA-PC01-20070912-1012-013"
		// CODIGO_EMPRESA - CODIGO_PLAN_CUENTA - CODIGO_PERIODO - MES_AÑO - NUMERO_ASIENTO
		String codigo = "";
		//PeriodoIf periodo = (PeriodoIf) getCmbPeriodo().getSelectedItem();
		EmpresaIf empresa = empresaLocal.getEmpresa(empresaId);
		String mesAsiento = utilitariosLocal.getMonthFromDate(fechaAsiento);
		String anioAsiento = utilitariosLocal.getYearFromDate(fechaAsiento);
		codigo = empresa.getCodigo() + "-";
		codigo += planCuenta.getCodigo() + "-";
		//codigo += periodo.getCodigo() + "-";
		codigo += mesAsiento + "-";
		codigo += anioAsiento + "-";
		return codigo;
	}
	
	public Collection findAsientoCostoVentaByEmpresaId(Long idEmpresa) {
		//select distinct * from ASIENTO a, TIPO_DOCUMENTO td where a.TIPO_DOCUMENTO_ID = td.ID and (td.CODIGO = 'EGM' or td.CODIGO = 'INM')
		SelectQuery select = new SelectQuery();
		Table asientoTable = new Table(AsientoEJB.class);
		Table tipoDocumentoTable = new Table(TipoDocumentoEJB.class);
		select.addObject(asientoTable);
		select.addJoin(asientoTable, "tipoDocumentoId", tipoDocumentoTable, "id");
		/*MatchCriteria egm = new MatchCriteria(tipoDocumentoTable, "codigo", MatchCriteria.EQUALS, "EGM");
		MatchCriteria inm = new MatchCriteria(tipoDocumentoTable, "codigo", MatchCriteria.EQUALS, "INM");
		MatchCriteria itr = new MatchCriteria(tipoDocumentoTable, "codigo", MatchCriteria.EQUALS, "ITR");
		MatchCriteria etr = new MatchCriteria(tipoDocumentoTable, "codigo", MatchCriteria.EQUALS, "ETR");
		MatchCriteria ajn = new MatchCriteria(tipoDocumentoTable, "codigo", MatchCriteria.EQUALS, "AJN");
		MatchCriteria ajp = new MatchCriteria(tipoDocumentoTable, "codigo", MatchCriteria.EQUALS, "AJP");*/
		//select.addCriteria(new OR(new OR(new OR(egm, inm), new OR(itr, etr)), new OR(ajn, ajp)));
		//select.addCriteria(new OR(egm, inm));
		select.addCriteria(new MatchCriteria(tipoDocumentoTable, "codigo", MatchCriteria.EQUALS, "EGM"));
		select.addCriteria(new MatchCriteria(tipoDocumentoTable, "empresaId", MatchCriteria.EQUALS, idEmpresa));
		return jpManagerLocal.executeQueryList(select.getQueryString(), null);
	}
	
	
	
	/**********************************************************************************/
	/************************** MAYORIZACION  *****************************************/
	/**
	 * @throws CuentaNoImputableException 
	 * @throws SaldoCuentaNegativoException ********************************************************************************/

	/*@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void mayorizarPeriodos(PeriodoIf periodoIf,PeriodoDetalleIf periodoDetalleIf,Boolean progresivo) throws GenericBusinessException {

		Map<Long,String> estadoPeriodos = new HashMap<Long, String>();
		Map<Long,String> estadoPeriodoDetalles = new HashMap<Long, String>();

		//Creo la lista de periodos
		Collection<PeriodoIf> periodos = new ArrayList<PeriodoIf>();
		Map periodosAutorizarMap = new HashMap();

		if ( progresivo && periodoDetalleIf == null ){

		} else {
			periodoIf = periodoLocal.getPeriodo(periodoIf.getId());
			periodos.add(periodoIf);
			periodosAutorizarMap.put(periodoIf.getId(), periodoIf);
		}
		
		PeriodoDetalleIf periodoDetalleAnterior = null;
		try {
			for ( PeriodoIf periodo : periodos ){
				
				ArrayList<PeriodoDetalleIf> periodosDetalles = null;
				if (periodoDetalleIf == null){
					periodosDetalles = (ArrayList<PeriodoDetalleIf>) periodoDetalleLocal.findPeriodoDetalleByPeriodoId(periodo.getId());
					//Ordeno los periodos detalles por mes y anio
					Collections.sort((ArrayList<PeriodoDetalleIf>)periodosDetalles,ordenadorPeriodoDetalle);
				} else {
					periodosDetalles = new ArrayList<PeriodoDetalleIf>();
					periodosDetalles.add(periodoDetalleIf);
				}
				
				System.out.println("*** 1 ****");
				//1) Encerar Saldos Cuentas del periodo que se esta procesando
				Map<String,Object> mapaSaldoCuenta = new HashMap<String, Object>();
				mapaSaldoCuenta.put("periodoId", periodo.getId());
				if ( periodoDetalleIf != null ){
					mapaSaldoCuenta.put("mes", periodoDetalleIf.getMes());
					mapaSaldoCuenta.put("anio", periodoDetalleIf.getAnio());
				}
				Collection<SaldoCuentaIf> saldos = saldoCuentaLocal.findSaldoCuentaByQuery(mapaSaldoCuenta);
				for ( SaldoCuentaIf saldo : saldos ){
					saldo.setValordebe(BigDecimal.ZERO);
					saldo.setValorhaber(BigDecimal.ZERO);
					saldo.setValor(BigDecimal.ZERO);
				}
				Thread.sleep(3000);
				System.out.println("*** 2 y 3 ****");
				//2 y 3) Cambiar estados de Preasientos y Asientos
				java.sql.Date fechaInicio = null;
				java.sql.Date fechaFin = null;
				if ( periodoDetalleIf == null ){
					fechaInicio = periodo.getFechaini();
					fechaFin = periodo.getFechafin();
				} else {
					int anio = Integer.valueOf( periodoDetalleIf.getAnio() );
					int mes = Integer.valueOf(periodoDetalleIf.getMes())-1;
					Calendar calendar = new GregorianCalendar(anio,mes,1);
					fechaInicio = new java.sql.Date(calendar.getTime().getTime());
					calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
					fechaFin = new java.sql.Date(calendar.getTime().getTime());
				}
				
				Map<String,Object> mapaAsientos = new HashMap<String,Object>();
				cambiarEstadoDeAsientos(mapaAsientos,fechaInicio,fechaFin);
				Thread.sleep(3000);
				
				System.out.println("*** 4 y 5 ****");
				//4.1) Guardo el estado actual del periodo
				estadoPeriodos.put(periodo.getId(), periodo.getStatus());
				
				int contadorDetalle = 1;
				Map cuentasMap = cuentaLocal.mapearCuentas(periodo.getEmpresaId());
				Map tiposCuentaMap = tipoCuentaLocal.mapearTiposCuenta();
				Map saldosCuentasMap = saldoCuentaLocal.mapearSaldosCuentasByPeriodosMap(periodosAutorizarMap);
				Map periodosDetallesNoInactivosMap = periodoDetalleLocal.mapearPeriodosDetallesNoInactivosByPeriodosMap(periodosAutorizarMap);
				
				for ( PeriodoDetalleIf periodoDetalle : periodosDetalles ){
					//4.2) Guardo el estado actual del periodo detalle
					estadoPeriodoDetalles.put(periodoDetalle.getId(), periodoDetalle.getStatus());
					
					//5) Poner a estado parcial todos los periodo detalles menos el ultimo
					// que va como cerrado
					if ( contadorDetalle == periodosDetalles.size() )
						periodoDetalle.setStatus(EstadoPeriodo.ACTIVO.getLetra());
					else 
						periodoDetalle.setStatus(EstadoPeriodo.PARCIAL.getLetra());
					
					//Si es el primer mes agarro el ultimo detalle del 
					//periodo anterior 
					if ( contadorDetalle == 1 ){
						//Actualizo los saldos Cuentas
						//Calculo para el presente anio
						Calendar calendarPeriodoAnterior = new GregorianCalendar();
						calendarPeriodoAnterior.setTime(new Date(periodo.getFechaini().getTime()));
						calendarPeriodoAnterior.add(Calendar.MONDAY, -1);
						calendarPeriodoAnterior.set(Calendar.DAY_OF_MONTH, calendarPeriodoAnterior.getActualMaximum(Calendar.DAY_OF_MONTH));
						Collection<PeriodoIf> periodosAnteriores  = periodoLocal.findPeriodoByEmpresaIdAndByFechaFin(
								periodo.getEmpresaId(), new java.sql.Date(calendarPeriodoAnterior.getTime().getTime()));
						if ( periodosAnteriores.size() == 1 ){
							PeriodoIf periodoAnterior = periodosAnteriores.iterator().next(); 
							ArrayList<PeriodoDetalleIf> detalles = (ArrayList<PeriodoDetalleIf>) 
								periodoDetalleLocal.findPeriodoDetalleByPeriodoId(periodoAnterior.getId());
							if ( detalles.size() > 0 ){
								Collections.sort(detalles,ordenadorPeriodoDetalle);
								periodoDetalleAnterior = detalles.get(detalles.size()-1);
							}
						}
					}
					
					if (periodoDetalleAnterior != null)
						actualizacionEspecial(periodoDetalleAnterior, periodo, periodoDetalle);
					
					periodoDetalleAnterior = periodoDetalle;
					
					contadorDetalle++;
				}
				Thread.sleep(3000);
				
				System.out.println("*** 6 ****");
				//6) Reprocesar asientos con actualizar
				mapaAsientos.put("status", "P");
				Collection<AsientoIf> asientos = findAsientoByQueryByFechaInicioAndFechaFinConFechaAsc(mapaAsientos, 
					fechaInicio, fechaFin);
				Thread.sleep(3000);
				
				System.out.println("Numero Asientos = "+asientos.size());
				Thread.sleep(3000);
				
				for ( AsientoIf asiento : asientos ) {
					asiento.setStatus("A");
					ArrayList<AsientoDetalleIf> asientoDetalles = (ArrayList<AsientoDetalleIf>) asientoDetalleLocal.findAsientoDetalleByAsientoId(asiento.getId());
					actualizarAsiento(asiento, asientoDetalles, null,null, null, false, false, null, cuentasMap, tiposCuentaMap, saldosCuentasMap, periodosDetallesNoInactivosMap);
				}
				
				System.out.println("*** 8 ****");
				// 8)Reestablezco los estados
				for ( Long periodoId : estadoPeriodos.keySet() ){
					PeriodoIf p = periodoLocal.getPeriodo(periodoId);
					p.setStatus(estadoPeriodos.get(periodoId));
				}
				for ( Long periodoDetalleId : estadoPeriodoDetalles.keySet() ){
					PeriodoDetalleIf pd = periodoDetalleLocal.getPeriodoDetalle(periodoDetalleId);
					pd.setStatus(estadoPeriodos.get(periodoDetalleId));
				}
				
				mapaAsientos.put("status", "X");
				asientos = findAsientoByQueryByFechaInicioAndFechaFin(mapaAsientos, fechaInicio, fechaFin);
				//Cambiar estado de 'P' (Preasiento) a 'X'
				for ( AsientoIf asiento : asientos ){
					asiento.setStatus("P");
				}
				Thread.sleep(3000);
				
			}
			
			//editado Carlos
			//Para el siguiente periodo despues del ultimo
			Calendar calendarSiguiente = new GregorianCalendar();
			calendarSiguiente.setTime(new Date(periodoIf.getFechafin().getTime()));
			calendarSiguiente.add(Calendar.MONTH , 1);
			java.sql.Date fechaSiguiente = new java.sql.Date(calendarSiguiente.getTime().getTime());
			PeriodoIf periodoSiguiente = null;
			Collection<PeriodoIf> periodosSiguientes = periodoLocal.findPeriodoByEmpresaIdAndByFechaFin(
					periodoIf.getEmpresaId(), fechaSiguiente);
			if ( periodosSiguientes.size() > 0 ){
				for ( PeriodoIf periodoSgte : periodosSiguientes ){
					periodoSiguiente = periodoSgte;
				}
				if ( periodoDetalleAnterior != null ){
					ArrayList<PeriodoDetalleIf> detalles = (ArrayList<PeriodoDetalleIf>)
						periodoDetalleLocal.findPeriodoDetalleByPeriodoId(periodoSiguiente.getId());
					Collections.sort(detalles, ordenadorPeriodoDetalle);
					actualizacionEspecial(periodoDetalleAnterior, periodoSiguiente,detalles.get(0));
				}
			}
			

		} catch( GenericBusinessException e ) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch( Exception e ) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Error general en la mayorizacion !!");
		}

	}*/

	private void actualizacionEspecial(PeriodoDetalleIf periodoDetalleAnterior, PeriodoIf periodoSiguiente,PeriodoDetalleIf periodoDetalleActual, Map<String, Object> beansMap, boolean mayorizacionPorApertura) throws GenericBusinessException, SaldoCuentaNegativoException, CuentaNoImputableException {
		ArrayList<PeriodoDetalleIf> periodoDetallesSiguiente = (ArrayList<PeriodoDetalleIf>) periodoDetalleLocal.findPeriodoDetalleByPeriodoId(periodoSiguiente.getId());
		Collections.sort(periodoDetallesSiguiente,ordenadorPeriodoDetalle);
		Vector<Object> activado = new Vector<Object>();
		activado.add("ACTIVAR");
		activado.add(periodoDetalleAnterior.getPeriodoId());
		activado.add(periodoDetalleAnterior.getAnio());
		activado.add(periodoDetalleAnterior.getMes());
		activado.add(periodoDetalleActual.getAnio());
		activado.add(periodoDetalleActual.getMes());
		actualizarPeriodoServer(periodoSiguiente, periodoDetallesSiguiente, activado, null, beansMap, mayorizacionPorApertura);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void mayorizarPeriodos(PeriodoIf periodoIf,PeriodoDetalleIf periodoDetalleIf,Boolean progresivo, Map<String, Object> beansMap, boolean mayorizacionPorApertura) throws GenericBusinessException {

		Map<Long,String> estadoPeriodos = new HashMap<Long, String>();
		Map<Long,String> estadoPeriodoDetalles = new HashMap<Long, String>();

		//Creo la lista de periodos
		Collection<PeriodoIf> periodos = new ArrayList<PeriodoIf>();
		Map periodosAutorizarMap = new HashMap();

		if ( progresivo && periodoDetalleIf == null ){

		} else {
			periodoIf = periodoLocal.getPeriodo(periodoIf.getId());
			periodos.add(periodoIf);
			periodosAutorizarMap.put(periodoIf.getId(), periodoIf);
		}
		
		PeriodoDetalleIf periodoDetalleAnterior = null;
		try {
			for ( PeriodoIf periodo : periodos ){
				
				ArrayList<PeriodoDetalleIf> periodosDetalles = null;
				if (periodoDetalleIf == null){
					periodosDetalles = (ArrayList<PeriodoDetalleIf>) periodoDetalleLocal.findPeriodoDetalleByPeriodoId(periodo.getId());
					//Ordeno los periodos detalles por mes y anio
					Collections.sort((ArrayList<PeriodoDetalleIf>)periodosDetalles,ordenadorPeriodoDetalle);
				} else {
					periodosDetalles = new ArrayList<PeriodoDetalleIf>();
					periodosDetalles.add(periodoDetalleIf);
				}
				
				System.out.println("*** 1 ****");
				//1) Encerar Saldos Cuentas del periodo que se esta procesando
				Map<String,Object> mapaSaldoCuenta = new HashMap<String, Object>();
				mapaSaldoCuenta.put("periodoId", periodo.getId());
				if ( periodoDetalleIf != null ){
					mapaSaldoCuenta.put("mes", periodoDetalleIf.getMes());
					mapaSaldoCuenta.put("anio", periodoDetalleIf.getAnio());
				}
				Collection<SaldoCuentaIf> saldos = saldoCuentaLocal.findSaldoCuentaByQuery(mapaSaldoCuenta);
				for ( SaldoCuentaIf saldo : saldos ) {
					saldo.setValordebe(BigDecimal.ZERO);
					saldo.setValorhaber(BigDecimal.ZERO);
					saldo.setValor(BigDecimal.ZERO);
				}
				System.out.println("*** 2 y 3 ****");
				//2 y 3) Cambiar estados de Preasientos y Asientos
				java.sql.Date fechaInicio = null;
				java.sql.Date fechaFin = null;
				if ( periodoDetalleIf == null ){
					fechaInicio = periodo.getFechaini();
					fechaFin = periodo.getFechafin();
				} else {
					int anio = Integer.valueOf( periodoDetalleIf.getAnio() );
					int mes = Integer.valueOf(periodoDetalleIf.getMes())-1;
					Calendar calendar = new GregorianCalendar(anio,mes,1);
					fechaInicio = new java.sql.Date(calendar.getTime().getTime());
					calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
					fechaFin = new java.sql.Date(calendar.getTime().getTime());
				}
				
				Map<String,Object> mapaAsientos = new HashMap<String,Object>();
				cambiarEstadoDeAsientos(mapaAsientos,fechaInicio,fechaFin);
				
				System.out.println("*** 4 y 5 ****");
				//4.1) Guardo el estado actual del periodo
				estadoPeriodos.put(periodo.getId(), periodo.getStatus());
				
				int contadorDetalle = 1;
				Map cuentasMap = cuentaLocal.mapearCuentas(periodo.getEmpresaId());
				Map tiposCuentaMap = tipoCuentaLocal.mapearTiposCuenta();
				Map saldosCuentasMap = saldoCuentaLocal.mapearSaldosCuentasByPeriodosMap(periodosAutorizarMap);
				Map periodosDetallesNoInactivosMap = periodoDetalleLocal.mapearPeriodosDetallesNoInactivosByPeriodosMap(periodosAutorizarMap);
				
				for ( PeriodoDetalleIf periodoDetalle : periodosDetalles ){
					//4.2) Guardo el estado actual del periodo detalle
					estadoPeriodoDetalles.put(periodoDetalle.getId(), periodoDetalle.getStatus());
					
					//5) Poner a estado parcial todos los periodo detalles menos el ultimo que va como cerrado
					if ( contadorDetalle == periodosDetalles.size())
						periodoDetalle.setStatus(EstadoPeriodo.CERRADO.getLetra());
					else 
						periodoDetalle.setStatus(EstadoPeriodo.PARCIAL.getLetra());
					
					int monthPeriodoDetalle = Integer.parseInt(periodoDetalle.getMes());
					int yearPeriodoDetalle = Integer.parseInt(periodoDetalle.getAnio());
					Calendar calendarPeriodoDetalleAnterior = new GregorianCalendar(yearPeriodoDetalle, monthPeriodoDetalle - 1, 1);
					calendarPeriodoDetalleAnterior.add(Calendar.DAY_OF_MONTH, -1);
					int monthPeriodoDetalleAnterior = calendarPeriodoDetalleAnterior.getTime().getMonth() + 1;
					int yearPeriodoDetalleAnterior = calendarPeriodoDetalleAnterior.getTime().getYear() + 1900;
					Collection<PeriodoIf> periodosAnteriores  = periodoLocal.findPeriodoByMesAndAnio(formatoMonth.format(monthPeriodoDetalleAnterior), String.valueOf(yearPeriodoDetalleAnterior));
					if ( periodosAnteriores.size() == 1 ){
						PeriodoIf periodoAnterior = periodosAnteriores.iterator().next();
						Map queryMap = new HashMap();
						queryMap.put("periodoId", periodoAnterior.getId());
						queryMap.put("mes", formatoMonth.format(monthPeriodoDetalleAnterior));
						queryMap.put("anio", String.valueOf(yearPeriodoDetalleAnterior));
						Iterator detallesIterator = periodoDetalleLocal.findPeriodoDetalleByQuery(queryMap).iterator();
						if (detallesIterator.hasNext()) {
							periodoDetalleAnterior = (PeriodoDetalleIf) detallesIterator.next();
						}
					}
					System.out.println("");
					//Si es el primer mes agarro el ultimo detalle del periodo anterior 
					/*if ( contadorDetalle == 1 ) {
						//Actualizo los saldos Cuentas
						//Calculo para el presente anio
						Calendar calendarPeriodoAnterior = new GregorianCalendar();
						calendarPeriodoAnterior.setTime(new Date(periodo.getFechaini().getTime()));
						calendarPeriodoAnterior.add(Calendar.MONTH, -1);
						calendarPeriodoAnterior.set(Calendar.DAY_OF_MONTH, calendarPeriodoAnterior.getActualMaximum(Calendar.DAY_OF_MONTH));
						Collection<PeriodoIf> periodosAnteriores  = periodoLocal.findPeriodoByEmpresaIdAndByFechaFin(periodo.getEmpresaId(), new java.sql.Date(calendarPeriodoAnterior.getTime().getTime()));
						if ( periodosAnteriores.size() == 1 ){
							PeriodoIf periodoAnterior = periodosAnteriores.iterator().next(); 
							ArrayList<PeriodoDetalleIf> detalles = (ArrayList<PeriodoDetalleIf>) 
								periodoDetalleLocal.findPeriodoDetalleByPeriodoId(periodoAnterior.getId());
							if ( detalles.size() > 0 ){
								Collections.sort(detalles,ordenadorPeriodoDetalle);
								periodoDetalleAnterior = detalles.get(detalles.size()-1);
							}
						}
					} else {
						int mesActual = Integer.parseInt(periodoDetalle.getMes());
						ArrayList<PeriodoDetalleIf> detalles = (ArrayList<PeriodoDetalleIf>) periodoDetalleLocal.findPeriodoDetalleByPeriodoId(periodo.getId());
						if ( detalles.size() > 0 ) {
							Collections.sort(detalles,ordenadorPeriodoDetalle);
							periodoDetalleAnterior = detalles.get(mesActual-1);
						}
					}*/
					
					if (periodoDetalleAnterior != null && !mayorizacionPorApertura)
						actualizacionEspecial(periodoDetalleAnterior, periodo, periodoDetalle, beansMap, mayorizacionPorApertura);
					
					periodoDetalleAnterior = periodoDetalle;
					
					contadorDetalle++;
				}
				System.out.println("*** 6 ****");
				//6) Reprocesar asientos con actualizar
				mapaAsientos.put("status", "P");
				ArrayList<Object[]> asientos = (ArrayList<Object[]>) findAsientoByQueryByFechaInicioAndFechaFinConFechaAsc(mapaAsientos, fechaInicio, fechaFin);
				System.out.println("Numero Asientos = "+asientos.size());
				AsientoIf asientoAnterior = null;
				ArrayList<AsientoDetalleIf> asientoDetalles = new ArrayList<AsientoDetalleIf>();
				/*for ( Object[] object : asientos ) {
					AsientoIf asiento = (AsientoIf) object[0];
					AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) object[1];
					asientoDetalles.add(asientoDetalle);
					if (!asiento.getNumero().equals(numeroAsientoAnterior)) {
						numeroAsientoAnterior = asiento.getNumero();
						asiento.setStatus("A");
						if (asiento.getNumero().equals("CRE-PC-02-2011-00393"))
							System.out.println("HERE");
						actualizarento(asiento, asientoDetalles, null, null, null, false, false, null, cuentasMap, tiposCuentaMap, saldosCuentasMap, periodosDetallesNoInactivosMap, true);
						asientoDetalles = new ArrayList<AsientoDetalleIf>();
					}
				}*/
				for (Object[] object : asientos) {
					AsientoIf asiento = (AsientoIf) object[0];
					AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) object[1];
					if (asientoAnterior == null || asiento.getId().compareTo(asientoAnterior.getId()) == 0)
						asientoDetalles.add(asientoDetalle);
					else {
						asientoAnterior.setStatus("A");
						actualizarAsiento(asientoAnterior, asientoDetalles, null, null, null, false, false, null, cuentasMap, tiposCuentaMap, saldosCuentasMap, periodosDetallesNoInactivosMap, true);
						asientoDetalles = new ArrayList<AsientoDetalleIf>();
						asientoDetalles.add(asientoDetalle);
					}
					asientoAnterior = (AsientoIf) DeepCopy.copy(asiento);
				}
				// Estas dos líneas permiten actualizar el último asiento recuperado en cada período
				if (asientoAnterior != null) {
					asientoAnterior.setStatus("A");
					actualizarAsiento(asientoAnterior, asientoDetalles, null, null, null, false, false, null, cuentasMap, tiposCuentaMap, saldosCuentasMap, periodosDetallesNoInactivosMap, true);
				}
				
				System.out.println("*** 7 ****");
				// 8)Reestablezco los estados
				for ( Long periodoId : estadoPeriodos.keySet() ){
					PeriodoIf p = periodoLocal.getPeriodo(periodoId);
					p.setStatus(estadoPeriodos.get(periodoId));
				}
				for ( Long periodoDetalleId : estadoPeriodoDetalles.keySet() ){
					PeriodoDetalleIf pd = periodoDetalleLocal.getPeriodoDetalle(periodoDetalleId);
					pd.setStatus(estadoPeriodoDetalles.get(periodoDetalleId));
				}
				
				mapaAsientos.put("status", "X");
				Collection<AsientoIf> asientosColeccion = findAsientoByQueryByFechaInicioAndFechaFin(mapaAsientos, fechaInicio, fechaFin);
				//Cambiar estado de 'P' (Preasiento) a 'X'
				for ( AsientoIf asiento : asientosColeccion ){
					asiento.setStatus("P");
				}
			}
			
			//editado Carlos
			//Para el siguiente periodo despues del ultimo
			Calendar calendarSiguiente = new GregorianCalendar();
			calendarSiguiente.setTime(new Date(periodoIf.getFechafin().getTime()));
			calendarSiguiente.add(Calendar.MONTH , 1);
			java.sql.Date fechaSiguiente = new java.sql.Date(calendarSiguiente.getTime().getTime());
			PeriodoIf periodoSiguiente = null;
			Collection<PeriodoIf> periodosSiguientes = periodoLocal.findPeriodoByEmpresaIdAndByFechaFin(
					periodoIf.getEmpresaId(), fechaSiguiente);
			if ( periodosSiguientes.size() > 0 ){
				for ( PeriodoIf periodoSgte : periodosSiguientes ){
					periodoSiguiente = periodoSgte;
				}
				if ( periodoDetalleAnterior != null ){
					ArrayList<PeriodoDetalleIf> detalles = (ArrayList<PeriodoDetalleIf>)
						periodoDetalleLocal.findPeriodoDetalleByPeriodoId(periodoSiguiente.getId());
					Collections.sort(detalles, ordenadorPeriodoDetalle);
					if (!mayorizacionPorApertura)
						actualizacionEspecial(periodoDetalleAnterior, periodoSiguiente,detalles.get(0), beansMap, mayorizacionPorApertura);
				}
			}
			

		} catch( GenericBusinessException e ) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch( Exception e ) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Error general en la mayorizacion !!");
		}

	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findAsientoByQueryByFechaInicioAndFechaFinConFechaAsc(Map aMap, java.sql.Date fechaInicio, java.sql.Date fechaFin) {
		String objectName = "a";
		String diarios = (String) aMap.get("diarios");
		aMap.remove("diarios");
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct a, ad from AsientoEJB " + objectName + ", AsientoDetalleEJB ad where " + where + " and ad.asientoId = a.id and a.fecha >= :fechaInicio and a.fecha <= :fechaFin";
		if (diarios != null && diarios.equals("S"))
			queryString += " and a.transaccionId is null";
		queryString += " order by a.id asc";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		
		return query.getResultList();
	}

	private void cambiarEstadoDeAsientos(
			Map<String, Object> mapaAsientos,java.sql.Date fechaInicio,java.sql.Date fechaFin) {
		mapaAsientos.put("status", "P");
		Collection<AsientoIf> asientos = findAsientoByQueryByFechaInicioAndFechaFin(mapaAsientos, fechaInicio, fechaFin);
		//Cambiar estado de 'P' (Preasiento) a 'X'
		for ( AsientoIf asiento : asientos ){
			asiento.setStatus("X");
		}
		mapaAsientos.put("status", "A");
		asientos = findAsientoByQueryByFechaInicioAndFechaFin(mapaAsientos, fechaInicio, fechaFin);
		//Cambiar estado de 'A' (Asiento) a 'P'
		for ( AsientoIf asiento : asientos ){
			asiento.setStatus("P");
		}
	}

	Comparator<PeriodoDetalleIf> ordenadorPeriodoDetalle = new Comparator<PeriodoDetalleIf>(){
		@Override
		public int compare(PeriodoDetalleIf o1, PeriodoDetalleIf o2) {
			Integer p1 = Integer.valueOf(o1.getMes())+Integer.valueOf(o1.getAnio());
			Integer p2 = Integer.valueOf(o2.getMes())+Integer.valueOf(o2.getAnio());
			return p1.compareTo(p2);
		}
	};
	
	Comparator<AsientoIf> ordenadorAsiento = new Comparator<AsientoIf>(){
		@Override
		public int compare(AsientoIf o1, AsientoIf o2) {
			return o1.getFecha().compareTo(o2.getFecha());
		}
	};
	
	private void guardarEstadoPeriodos(Map<Long,String> estados , Collection<PeriodoIf> periodos){
		for (PeriodoIf periodo : periodos){
			estados.put(periodo.getId(), periodo.getStatus());
		}
	}

	private void guardarEstadoPeriodosDetalles(Map<Long,String> estados , Collection<PeriodoDetalleIf> detalles){
		for (PeriodoDetalleIf detalle : detalles){
			estados.put(detalle.getId(), detalle.getStatus());
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	   public void actualizarPeriodo(PeriodoIf model,List<PeriodoDetalleIf> modelDetalleList) throws GenericBusinessException {
		   try {
			   PeriodoIf periodo = periodoLocal.registrarPeriodo(model);
			   manager.merge(periodo);
			   Iterator<PeriodoDetalleIf> modelDetalleIt = modelDetalleList.iterator();
			   while (modelDetalleIt.hasNext()) {
				   PeriodoDetalleIf modelDetalle = modelDetalleIt.next();
				   modelDetalle.setPeriodoId(periodo.getPrimaryKey());
				   PeriodoDetalleIf periodoDetalle = periodoLocal.registrarPeriodoDetalle(modelDetalle);
				   manager.merge(periodoDetalle);
			   }
		   } catch(Exception e){
			   ctx.setRollbackOnly();
			   log.error("Error al actualizar información en PeriodoEJB.", e);
			   throw new GenericBusinessException("Se ha producido un error al actualizar el Periodo");
		   }
	   }
	
	public void actualizarPeriodoServer(PeriodoIf periodo,List<PeriodoDetalleIf> periodoDetalleColeccion, Vector<Object> activado, AsientoIf asientoCierre, Map<String, Object> beansMap, boolean mayorizacionPorApertura) throws GenericBusinessException, SaldoCuentaNegativoException, CuentaNoImputableException {
		PlanCuentaIf planCuenta = (PlanCuentaIf) beansMap.get("PLAN_CUENTA");
		EmpresaIf empresa = (EmpresaIf) beansMap.get("EMPRESA");
		OficinaIf oficina = (OficinaIf) beansMap.get("OFICINA");
		UsuarioIf usuario = (UsuarioIf) beansMap.get("USUARIO");
		String numeroAsiento = "";
		if (periodo.getStatus().equals("C")) {
			if (asientoCierre != null) {
				numeroAsiento = asientoCierre.getNumero();
				procesarEliminacionAsiento(asientoCierre, usuario.getUsuario(), "ANULACIÓN DE ASIENTO DE CIERRE PERIODO " + periodo.toString(), true);
			}
		}
		actualizarPeriodo(periodo, periodoDetalleColeccion);
		Map<Long,CuentaIf> mapaCuentas = cuentaLocal.mapearCuentas(empresa.getId());
		Map<Long,TipoCuentaIf> mapaTipoCuentas = tipoCuentaLocal.mapearTiposCuenta();
		String mesAsiento = utilitariosLocal.getMonthFromDate(new java.util.Date(periodo.getFechafin().getTime()));
		String anioAsiento = Utilitarios.getYearFromDate(new java.util.Date(periodo.getFechafin().getTime()));
		if (periodo.getStatus().equals("C")) {
			List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
			boolean modificarNumeroAsiento = false;
			/* Crear asiento de cierre */
			asientoCierre = new AsientoData();
			asientoCierre.setId(null);
			if (numeroAsiento.equals("")) {
				numeroAsiento = empresa.getCodigo() + "-";
				numeroAsiento += planCuenta.getCodigo() + "-";
				numeroAsiento += mesAsiento + "-";
				numeroAsiento += anioAsiento + "-";
				modificarNumeroAsiento = true;
			}
			asientoCierre.setNumero(numeroAsiento);
			asientoCierre.setEmpresaId(periodo.getEmpresaId());
			asientoCierre.setPeriodoId(periodo.getId());
			asientoCierre.setPlancuentaId(planCuenta.getId());
			asientoCierre.setFecha(periodo.getFechafin());
			asientoCierre.setStatus("A");
			asientoCierre.setEfectivo("N");
			asientoCierre.setObservacion("ASIENTO DE CIERRE PERIODO " + periodo.toString());
			asientoCierre.setOficinaId(oficina.getId());
			asientoCierre.setElaboradoPorId(usuario.getId());
			asientoCierre.setAutorizadoPorId(usuario.getId());
			asientoCierre.setAsientoCierre("S");
			double resultado = 0D;
			double ingresos = 0D;
			double egresos = 0D;
			Collection<SaldoCuentaIf> saldosCuentaResultados = saldoCuentaLocal.findSaldoCuentaByPeriodoIdByAnioAndByMes(periodo.getId(), anioAsiento, mesAsiento);
			Iterator<SaldoCuentaIf> it = saldosCuentaResultados.iterator();
			while (it.hasNext()) {
				AsientoDetalleIf asientoDetalle = new AsientoDetalleData();
				SaldoCuentaIf saldoCuenta = it.next();
				CuentaIf cuenta = verificarCuenta(mapaCuentas, saldoCuenta.getCuentaId());
				if (cuenta.getTiporesultadoId() != null && cuenta.getImputable().equals("S")) {
					TipoCuentaIf tipoCuenta = verificarTipoCuenta(mapaTipoCuentas, cuenta.getTipocuentaId());
					asientoDetalle.setCuentaId(cuenta.getId());
					asientoDetalle.setGlosa("ASIENTO DE CIERRE PERIODO " + periodo.toString());
					if (tipoCuenta.getCodigo().equals("I") || tipoCuenta.getCodigo().equals("OI")) {
						if (saldoCuenta.getValor().doubleValue() > 0D) {
							asientoDetalle.setDebe(saldoCuenta.getValor());
							asientoDetalle.setHaber(BigDecimal.ZERO);
						} else {
							asientoDetalle.setDebe(BigDecimal.ZERO);
							asientoDetalle.setHaber(saldoCuenta.getValor().abs());
						}
						ingresos += saldoCuenta.getValor().doubleValue();
					} else if (tipoCuenta.getCodigo().equals("G") || tipoCuenta.getCodigo().equals("OO")) {
						if (saldoCuenta.getValor().doubleValue() > 0D) {
							asientoDetalle.setDebe(BigDecimal.ZERO);
							asientoDetalle.setHaber(saldoCuenta.getValor());
						} else {
							asientoDetalle.setDebe(saldoCuenta.getValor().abs());
							asientoDetalle.setHaber(BigDecimal.ZERO);
						}
						egresos += saldoCuenta.getValor().doubleValue();
					}
				}
				if (asientoDetalle.getCuentaId() != null && (asientoDetalle.getDebe().doubleValue() > 0D || asientoDetalle.getHaber().doubleValue() > 0D))
					asientoDetalleColeccion.add(asientoDetalle);
			}
			resultado = Utilitarios.redondeoValor(ingresos - egresos);
			/* Crear asiento detalle correspondiente a resultado del ejercicio */
			AsientoDetalleIf asientoDetalle = new AsientoDetalleData();
			Map queryMap = new HashMap();
			queryMap.put("codigo", resultado>0D?"UTILIDAD":"PERDIDA");
			queryMap.put("empresaId", empresa.getId());
			Iterator<ParametroEmpresaIf> parameterIt = parametroEmpresaLocal.findParametroEmpresaByQuery(queryMap).iterator();
			if (parameterIt.hasNext()) {
				ParametroEmpresaIf parameter = parameterIt.next();
				queryMap.clear();
				queryMap.put("codigo", parameter.getValor());
				queryMap.put("plancuentaId", planCuenta.getId());
				asientoDetalle.setCuentaId(((CuentaIf) cuentaLocal.findCuentaByQuery(queryMap).iterator().next()).getId());
				if (resultado >= 0) {
					asientoDetalle.setDebe(BigDecimal.ZERO);
					asientoDetalle.setHaber(BigDecimal.valueOf(resultado));
					asientoDetalle.setGlosa("UTILIDAD PERIODO " + periodo.toString());
				} else {
					asientoDetalle.setDebe(BigDecimal.valueOf(resultado).abs());
					asientoDetalle.setHaber(BigDecimal.ZERO);
					asientoDetalle.setGlosa("PÉRDIDA PERIODO " + periodo.toString());
				}
			}
			if (asientoDetalle.getCuentaId() != null && (asientoDetalle.getDebe().doubleValue() > 0D || asientoDetalle.getHaber().doubleValue() > 0D))
				asientoDetalleColeccion.add(asientoDetalle);
			if (asientoDetalleColeccion.size() > 0) {
				/*if (asientoCierre.getId() != null) {
					Map periodosAutorizarMap = new HashMap();
					periodosAutorizarMap.put(periodo.getId(), periodo);
					Map saldosCuentasMap = saldoCuentaLocal.mapearSaldosCuentasByPeriodosMap(periodosAutorizarMap);
					actualizarAsiento(asientoCierre, asientoDetalleColeccion, asientoCierre, (List<AsientoDetalleIf>)asientoDetalleLocal.findAsientoDetalleByAsientoId(asientoCierre.getId()), null, true, false, usuario, new HashMap(), new HashMap(), saldosCuentasMap, new HashMap());
				} else*/
					procesarAsiento(asientoCierre, asientoDetalleColeccion, modificarNumeroAsiento);
			}
		}
		if(activado.size() > 0){
			if(activado.get(0).equals("ACTIVAR")) {
				Long idPeriodo = (Long)activado.get(1);
				String anioAnterior = (String)activado.get(2);
				String mesAnterior = (String)activado.get(3);
				String anioActivado = (String)activado.get(4);
				String mesActivado = (String)activado.get(5);
				// Aquí hay que traer el periodo detalle inicial del periodo
				Iterator periodoDetalleIterator = periodoDetalleLocal.findPeriodoDetalleInicialByPeriodoId(periodo.getId()).iterator();
				PeriodoDetalleIf periodoDetalleInicial = (periodoDetalleIterator.hasNext())?(PeriodoDetalleIf) periodoDetalleIterator.next():null;
				String anioInicial = (periodoDetalleInicial!=null)?periodoDetalleInicial.getAnio():"";
				String mesInicial = (periodoDetalleInicial!=null)?periodoDetalleInicial.getMes():"";
				Collection<SaldoCuentaIf> saldosCuenta = saldoCuentaLocal.findSaldoCuentaByPeriodoIdByAnioAndByMes(idPeriodo, anioAnterior, mesAnterior);
				for (SaldoCuentaIf saldoCuentaIf : saldosCuenta ){
					CuentaIf cuenta = verificarCuenta(mapaCuentas, saldoCuentaIf.getCuentaId());
					SaldoCuentaIf data = verificarSaldoCuenta(saldoCuentaIf.getCuentaId(), periodo.getId(),mesActivado, anioActivado);
					data.setValordebe(saldoCuentaIf.getValordebe());
					data.setValorhaber(saldoCuentaIf.getValorhaber());
					data.setValor(saldoCuentaIf.getValor());	
					if ( data.getId() == null )
						saldoCuentaLocal.addSaldoCuenta(data);
					else 
						saldoCuentaLocal.saveSaldoCuenta(data);
				}
				
				/*if (mayorizacionPorApertura) {
					Iterator<PeriodoIf> periodoAnteriorIt = periodoLocal.findPeriodoByMesAndAnio(mesAnterior, anioAnterior).iterator();
					if (periodoAnteriorIt.hasNext()) {
						PeriodoIf periodoAnterior = periodoAnteriorIt.next();
						Iterator it = periodoDetalleLocal.findPeriodoDetalleSiguientesNoInactivoByPeriodoIdAndMesAndAnio(periodoAnterior.getId(), mesAnterior, anioAnterior, true).iterator();
						while (it.hasNext()) {
							PeriodoDetalleIf pd = (PeriodoDetalleIf) it.next();
							System.out.println("Iniciando mayorización " + utilitariosLocal.getNombreMes(Integer.valueOf(pd.getMes())) + " " + pd.getAnio() + "...");
							mayorizarPeriodos(periodoAnterior, pd, false, beansMap, !mayorizacionPorApertura);
							System.out.println("Finalizado");
						}
					}
				}*/
			}
		}
	}
	
	private SaldoCuentaIf verificarSaldoCuenta(Long cuentaId,Long periodoId,String mes,String anio) throws GenericBusinessException {
		SaldoCuentaIf saldoCuenta = new SaldoCuentaData();
		Map<String,Object> mapaSaldoCuenta = new HashMap<String, Object>();
		mapaSaldoCuenta.put("cuentaId", cuentaId);
		mapaSaldoCuenta.put("periodoId", periodoId);
		mapaSaldoCuenta.put("mes", mes);
		mapaSaldoCuenta.put("anio", anio);
		try {
			Collection<SaldoCuentaIf> saldos = saldoCuentaLocal.findSaldoCuentaByQuery(mapaSaldoCuenta);
			if ( saldos.size() == 1 )
				saldoCuenta = saldos.iterator().next();
			else {
				saldoCuenta.setCuentaId(cuentaId);
				saldoCuenta.setPeriodoId(periodoId);
				saldoCuenta.setAnio(anio);
				saldoCuenta.setMes(mes);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al verificar saldos de cuentas");
		}
		return saldoCuenta;
	}

	private CuentaIf verificarCuenta(Map<Long, CuentaIf> mapaCuentas,Long cuentaId) throws GenericBusinessException{
		CuentaIf cuenta = mapaCuentas.get(cuentaId);
		if ( cuenta == null ){
			cuenta = cuentaLocal.getCuenta(cuentaId);
			mapaCuentas.put(cuenta.getId(), cuenta);
		}
		return cuenta;
	}
	
	private TipoCuentaIf verificarTipoCuenta(Map<Long, TipoCuentaIf> mapaTipoCuentas,Long tipoCuentaId) throws GenericBusinessException{
		TipoCuentaIf tipoCuenta = mapaTipoCuentas.get(tipoCuentaId);
		if ( tipoCuenta == null ){
			tipoCuenta = tipoCuentaLocal.getTipoCuenta(tipoCuentaId);
			mapaTipoCuentas.put(tipoCuenta.getId(), tipoCuenta);
		}
		return tipoCuenta;
	}
}
