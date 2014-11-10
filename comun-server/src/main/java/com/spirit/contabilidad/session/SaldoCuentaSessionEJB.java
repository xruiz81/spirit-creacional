package com.spirit.contabilidad.session;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.SaldoCuentaIf;
import com.spirit.contabilidad.handler.SaldoInicial;
import com.spirit.contabilidad.session.generated._SaldoCuentaSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.ParametroEmpresaSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 * The <code>SaldoCuentaSession</code> session bean, which acts as a facade to
 * the underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 * 
 */
@Stateless
public class SaldoCuentaSessionEJB extends _SaldoCuentaSession implements SaldoCuentaSessionRemote, SaldoCuentaSessionLocal {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	@EJB private CuentaSessionLocal cuentaLocal;
	
	@EJB private TipoCuentaSessionLocal tipoCuentaLocal;
	
	@EJB private PeriodoSessionLocal periodoLocal;
	
	@EJB private PeriodoDetalleSessionLocal periodoDetalleLocal;
	
	@EJB private AsientoDetalleSessionLocal asientoDetalleLocal; 
	
	@EJB private UtilitariosSessionLocal utilitariosLocal;
	
	@EJB private ParametroEmpresaSessionLocal parametroEmpresaLocal;

	private static Logger log = LogService.getLogger(SaldoCuentaSessionEJB.class);

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 **************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findSaldoCuentaByPeriodoIdByAnioAndByMes(Long idPeriodo, String anio, String mes) {
		String queryString = "select distinct sc from SaldoCuentaEJB sc where sc.periodoId = "
				+ idPeriodo
				+ " and sc.mes = '"
				+ mes
				+ "' and sc.anio = '"
				+ anio + "'";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findSaldoCuentaByPeriodoIdSortedByCuentaIdByAnioByMes(Long idPeriodo) {
		String queryString = "select distinct sc from SaldoCuentaEJB sc where sc.periodoId = "
				+ idPeriodo + " order by sc.cuentaId asc, sc.anio asc, sc.mes asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findSaldoCuentaByCuentaIdAndByPeriodoId(Long idCuenta,
			Long idPeriodo) {
		String objectName = "e";
		String queryString = "select e from SaldoCuentaEJB " + objectName
				+ " where e.cuentaId = " + idCuenta + " and e.periodoId = "
				+ idPeriodo + " order by e.cuentaId desc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findSaldoCuentaByPlanCuentaIdByCuentaEfectivoImputableAndByPeriodoId(
			Long idEmpresa, Long idPlanCuenta, Long idPeriodo) {

		String queryString = "select distinct sc from SaldoCuentaEJB sc, PlanCuentaEJB pc, CuentaEJB c where sc.cuentaId = c.id and c.plancuentaId = pc.id and pc.empresaId = "
				+ idEmpresa
				+ " and sc.periodoId = "
				+ idPeriodo
				+ " and pc.id = "
				+ idPlanCuenta
				+ " and c.efectivo = 'S' and c.imputable = 'S'";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findSaldoCuentaByPlanCuentaId(Long idPlanCuenta) {
		String queryString = "select distinct sc from SaldoCuentaEJB sc, PlanCuentaEJB pc, CuentaEJB c where sc.cuentaId = c.id and c.plancuentaId = pc.id and pc.id = " + idPlanCuenta;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	

	public Map<Long, SaldoInicial> verificarSaldosInicialesCuentasNuevo(Date fechaComboInicio,
			Map<Long, SaldoInicial> mapaSaldosIniciales, CuentaIf cuentaContable,Long empresaId)
			throws GenericBusinessException {
		
		if ( !mapaSaldosIniciales.containsKey(cuentaContable.getId()) ){
		//if ( mapaSaldosIniciales == null ){
			mapaSaldosIniciales = new HashMap<Long, SaldoInicial>();
			DecimalFormat formatoDosEnteros = new DecimalFormat("00");
			Calendar calMesAnteriorFechaInicio = new GregorianCalendar();
			calMesAnteriorFechaInicio.setTime(fechaComboInicio);
			calMesAnteriorFechaInicio.add(Calendar.MONTH, -1);
			java.sql.Date fechaPeriodo = new java.sql.Date(calMesAnteriorFechaInicio.getTime().getTime());
			Collection<PeriodoIf> periodos = periodoLocal.findPeriodoByRangoFechas(
					empresaId, fechaPeriodo, fechaPeriodo );
			if ( periodos.size() == 1 ){
				
				PeriodoIf periodo = periodos.iterator().next();
				Map<String,Object> mapaSaldoCuenta = new HashMap<String, Object>();
				mapaSaldoCuenta.put("periodoId", periodo.getId());
				mapaSaldoCuenta.put("cuentaId", cuentaContable.getId());
				mapaSaldoCuenta.put("mes", 
					formatoDosEnteros.format(calMesAnteriorFechaInicio.get(Calendar.MONTH)+1 ) );
				mapaSaldoCuenta.put("anio", 
					String.valueOf(calMesAnteriorFechaInicio.get(Calendar.YEAR)) );
				Collection<SaldoCuentaIf> saldos = findSaldoCuentaByQuery(mapaSaldoCuenta);
				if ( saldos.size() == 1 ){
					SaldoCuentaIf saldo = saldos.iterator().next();
					SaldoInicial si = new SaldoInicial();
					si.setDebeInicial(saldo.getValordebe());
					si.setHaberInicial(saldo.getValorhaber());
					mapaSaldosIniciales.put(cuentaContable.getId(), si); 
				}
			}
		}
		return mapaSaldosIniciales;
	}
	
	public Map<Long, SaldoInicial> verificarSaldosInicialesCuentas(Date fechaComboInicio,Long planCuentaId,
			Map<Long, SaldoInicial> mapaSaldosIniciales, CuentaIf cuentaContable,Long empresaId)
			throws GenericBusinessException {
		
		if ( !mapaSaldosIniciales.containsKey(cuentaContable.getId()) ){
		//if ( mapaSaldosIniciales == null ){
			mapaSaldosIniciales = new HashMap<Long, SaldoInicial>();
			DecimalFormat formatoDosEnteros = new DecimalFormat("00");
			Calendar calMesAnteriorFechaInicio = new GregorianCalendar();
			calMesAnteriorFechaInicio.setTime(fechaComboInicio);
			calMesAnteriorFechaInicio.add(Calendar.MONTH, -1);
			java.sql.Date fechaPeriodo = new java.sql.Date(calMesAnteriorFechaInicio.getTime().getTime());
			Collection<PeriodoIf> periodos = periodoLocal.findPeriodoByRangoFechas(
					empresaId, fechaPeriodo, fechaPeriodo );
			if ( periodos.size() == 1 ){
				
				PeriodoIf periodo = periodos.iterator().next();
				Map<String,Object> mapaSaldoCuenta = new HashMap<String, Object>();
				mapaSaldoCuenta.put("periodoId", periodo.getId());
				mapaSaldoCuenta.put("cuentaId", cuentaContable.getId());
				mapaSaldoCuenta.put("mes", 
					formatoDosEnteros.format(calMesAnteriorFechaInicio.get(Calendar.MONTH)+1 ) );
				mapaSaldoCuenta.put("anio", 
					String.valueOf(calMesAnteriorFechaInicio.get(Calendar.YEAR)) );
				Collection<SaldoCuentaIf> saldos = findSaldoCuentaByQuery(mapaSaldoCuenta);
				if ( saldos.size() == 1 ){
					SaldoCuentaIf saldo = saldos.iterator().next();
					SaldoInicial si = new SaldoInicial();
					si.setDebeInicial(saldo.getValordebe());
					si.setHaberInicial(saldo.getValorhaber());
					mapaSaldosIniciales.put(cuentaContable.getId(), si);
					
					Long idPeriodo = periodo.getId();
					Calendar calInicio = new GregorianCalendar();
					calInicio.set(Calendar.MONTH, calMesAnteriorFechaInicio.get(Calendar.MONTH));
					calInicio.set(Calendar.YEAR, calMesAnteriorFechaInicio.get(Calendar.YEAR));
					calInicio.set(Calendar.DATE, calInicio.getActualMaximum(Calendar.DATE));
					calInicio.add(Calendar.DATE, 1);
					
					Calendar calDiaAnteriorFechaInicio = new GregorianCalendar();
					calDiaAnteriorFechaInicio.setTime(fechaComboInicio);
					if ( calDiaAnteriorFechaInicio.get(Calendar.DATE) > 1 ){
						calDiaAnteriorFechaInicio.add(Calendar.DATE, -1);
						Calendar calFin = new GregorianCalendar();
						calFin.setTime(calDiaAnteriorFechaInicio.getTime());
						
						java.sql.Date fechaInicioMovimiento = new java.sql.Date(calInicio.getTime().getTime());
						java.sql.Date fechaFinMovimiento = new java.sql.Date(calFin.getTime().getTime());
						Collection<Object[]> detalles = asientoDetalleLocal
							.findAsientoDetalleByEmpresaByCuentaIdByPeriodoIdByPlanCuentaIdAndByFechaInicioAndFechaFin(
								empresaId, cuentaContable.getId(), idPeriodo, planCuentaId, 
								fechaInicioMovimiento, fechaFinMovimiento, true);
						for ( Object[] detalleObject : detalles ){
							AsientoDetalleIf detalle = (AsientoDetalleIf) detalleObject[0];
							if ( !detalle.getDebe().equals(BigDecimal.ZERO) ){
								si.setDebeInicial(si.getDebeInicial().add(detalle.getDebe()));
							} else {
								si.setHaberInicial(si.getHaberInicial().add(detalle.getHaber()));
							}
						}
					}
				}
			}
		}
		return mapaSaldosIniciales;
	}
	
	public Map mapearSaldosCuentasByPeriodosMap(Map periodosAutorizarMap) throws GenericBusinessException {
		Map saldosCuentasByPeriodoMap = new HashMap();
		Iterator it = periodosAutorizarMap.keySet().iterator();
		while (it.hasNext()) {
			Long periodoId = (Long) it.next();
			PeriodoIf periodo = (PeriodoIf) periodosAutorizarMap.get(periodoId);
			Iterator saldosCuentasIterator = findSaldoCuentaByPeriodoIdSortedByCuentaIdByAnioByMes(periodo.getId()).iterator();
			Map saldosCuentasMap = new HashMap();
			while (saldosCuentasIterator.hasNext()) {
				SaldoCuentaIf saldoCuenta = (SaldoCuentaIf) saldosCuentasIterator.next();
				Long cuentaId = saldoCuenta.getCuentaId();
				String anio = saldoCuenta.getAnio();
				String mes = saldoCuenta.getMes();
				Vector<SaldoCuentaIf> saldosCuentasVector = new Vector<SaldoCuentaIf>();
				if (saldosCuentasMap.get(cuentaId) != null)
					saldosCuentasVector = (Vector<SaldoCuentaIf>) saldosCuentasMap.get(cuentaId);
				saldosCuentasVector.add(saldoCuenta);
				saldosCuentasMap.put(cuentaId, saldosCuentasVector);
			}
			saldosCuentasByPeriodoMap.put(periodo.getId(), saldosCuentasMap); 
		}
		return saldosCuentasByPeriodoMap;
	}
	
	public Map mapearSaldosCuentasByPeriodosMapAndCuentasVector(Map periodosAutorizarMap, Vector<CuentaIf> cuentasVector) throws GenericBusinessException {
		Map saldosCuentasByPeriodoMap = new HashMap();
		Iterator it = periodosAutorizarMap.keySet().iterator();
		while (it.hasNext()) {
			Long periodoId = (Long) it.next();
			PeriodoIf periodo = (PeriodoIf) periodosAutorizarMap.get(periodoId);
			Iterator saldosCuentasIterator = findSaldoCuentaByPeriodoIdSortedByCuentaIdByAnioByMes(periodo.getId()).iterator();
			Map saldosCuentasMap = new HashMap();
			while (saldosCuentasIterator.hasNext()) {
				SaldoCuentaIf saldoCuenta = (SaldoCuentaIf) saldosCuentasIterator.next();
				Long cuentaId = saldoCuenta.getCuentaId();
				String anio = saldoCuenta.getAnio();
				String mes = saldoCuenta.getMes();
				Vector<SaldoCuentaIf> saldosCuentasVector = new Vector<SaldoCuentaIf>();
				for (int i=0; i<cuentasVector.size(); i++) {
					CuentaIf cuenta = cuentasVector.get(i);
					if (cuenta.getId().compareTo(cuentaId) == 0) {
						if (saldosCuentasMap.get(cuentaId) != null)
							saldosCuentasVector = (Vector<SaldoCuentaIf>) saldosCuentasMap.get(cuentaId);
						saldosCuentasVector.add(saldoCuenta);
						saldosCuentasMap.put(cuentaId, saldosCuentasVector);
						break;
					}
				}
			}
			saldosCuentasByPeriodoMap.put(periodo.getId(), saldosCuentasMap); 
		}
		return saldosCuentasByPeriodoMap;
	}
}
