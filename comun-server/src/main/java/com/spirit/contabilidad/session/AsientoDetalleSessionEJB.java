package com.spirit.contabilidad.session;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.session.generated._AsientoDetalleSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.performance.session.PerformanceInterceptor;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>AsientoDetalleSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
@Interceptors( { PerformanceInterceptor.class })
public class AsientoDetalleSessionEJB extends _AsientoDetalleSession implements AsientoDetalleSessionRemote,AsientoDetalleSessionLocal  {
	
	@PersistenceContext(unitName="spirit")
	private EntityManager manager;
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(AsientoDetalleSessionEJB.class);
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<AsientoDetalleIf> findAsientoDetalleFechaInicioParaCuenta(
			AsientoIf asiento, java.sql.Date fechaInicio,
			java.lang.Long cuentaId) {
		String queryString = "select ad  from AsientoEJB a, AsientoDetalleEJB ad where a.id = ad.asientoId and a.status = 'A' "
			+ "and a.fecha >= :fechaInicio " 
			+ "and a.periodoId = "	
			+ asiento.getPeriodoId()
			+ "and a.plancuentaId = "
			+ asiento.getPlancuentaId()
			+ "and a.empresaId = "
			+ asiento.getEmpresaId()
			+ " and ad.cuentaId = "
			+ cuentaId
			+ " order by a.fecha,ad.id";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findAsientoDetalleByAsientoIdAndByCuentaId(
			java.lang.Long idAsiento, java.lang.Long idCuenta) {
		String queryString = "from AsientoDetalleEJB e where e.asientoId = "
			+ idAsiento + " and e.cuentaId = " + idCuenta;
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findAsientoDetalleByEmpresaIdAndByCuentaEfectivoImputable(Long idEmpresa){
		//select distinct ad.* from asiento_detalle ad, cuenta c
		//where ad.CUENTA_ID = c.ID and ad.ASIENTO_ID = 1 and c.EFECTIVO = 'S' and c.IMPUTABLE = 'S'
		String queryString = "select distinct ad from AsientoEJB a, AsientoDetalleEJB ad, CuentaEJB c where ad.cuentaId = c.id and ad.asientoId = a.id and a.empresaId = " + idEmpresa + " and c.efectivo = 'S' and c.imputable = 'S'";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<Object[]> findAsientoDetalleByEmpresaIdAndByCuentaEfectivoImputable_DebeHaber(Long idEmpresa){
		//select distinct ad.* from asiento_detalle ad, cuenta c
		//where ad.CUENTA_ID = c.ID and ad.ASIENTO_ID = 1 and c.EFECTIVO = 'S' and c.IMPUTABLE = 'S'
		String queryString = "select ad.asientoId,ad.debe,ad.haber from AsientoEJB a, AsientoDetalleEJB ad, CuentaEJB c where ad.cuentaId = c.id and ad.asientoId = a.id and a.empresaId = " + idEmpresa + " and c.efectivo = 'S' and c.imputable = 'S'";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	 
	
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findAsientoDetalleByEmpresaIdAndByCuentaEfectivoImputable_DebeHaberJuntosTipo(Map aMap, java.sql.Date fechaInicio, java.sql.Date fechaFin,Long idEmpresa,Long tipoasientoId) {
		String objectName = "a";
		String diarios = (String) aMap.get("diarios");
		aMap.remove("diarios");
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select ad.asientoId,ad.debe,ad.haber from AsientoEJB " + objectName + ", AsientoDetalleEJB ad, CuentaEJB c,SubtipoAsientoEJB sta, TipoAsientoEJB ta " +
				"where ad.cuentaId = c.id and ad.asientoId = a.id and a.empresaId = " + idEmpresa + " and c.efectivo = 'S' " +
				"and c.imputable = 'S' and sta.tipoId= ta.id and a.subtipoasientoId =sta.id and ta.id="+tipoasientoId+ " and "+
		        where + " and a.fecha between :fechaInicio and :fechaFin";
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
	public Collection findAsientoDetalleByEmpresaIdAndByCuentaEfectivoImputable_DebeHaberJuntos(Map aMap, java.sql.Date fechaInicio, java.sql.Date fechaFin,Long idEmpresa) {
		String objectName = "a";
		String diarios = (String) aMap.get("diarios");
		aMap.remove("diarios");
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select ad.asientoId,ad.debe,ad.haber from AsientoEJB " + objectName + ", AsientoDetalleEJB ad, CuentaEJB c " +
				"where ad.cuentaId = c.id and ad.asientoId = a.id and a.empresaId = " + idEmpresa + 
				" and c.efectivo = 'S' and c.imputable = 'S' and " + where + " and a.fecha between :fechaInicio and :fechaFin";
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
	public Collection findAsientoDetalleBySubTipoAsientoIdByPeriodoIdByPlanCuentaIdAndByAsientoEfectivoAndByCuentaEfectivoImputableAndByFechaInicioAndFechaFin(Long idSubTipoAsiento, Long idPeriodo, Long idPlanCuenta, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws GenericBusinessException {
		// select distinct ad.* from asiento_detalle ad, asiento e, cuenta c where
		// ad.ASIENTO_ID = e.ID and ad.CUENTA_ID = c.ID and e.EFECTIVO = 'S' and e.SUBTIPOASIENTO_ID = 1 and c.EFECTIVO = 'S'
		// and e.FECHA between TO_Date('2005-01-01', 'YYYY-MM-DD') and TO_Date('2005-01-04', 'YYYY-MM-DD')
		String queryString = "select distinct ad from AsientoDetalleEJB ad, AsientoEJB e, CuentaEJB c where ad.asientoId = e.id and ad.cuentaId = c.id and e.subtipoasientoId = " + idSubTipoAsiento + " and e.periodoId = " + idPeriodo + " and e.plancuentaId = " + idPlanCuenta + " and e.efectivo = 'S' and c.efectivo = 'S' and c.imputable = 'S' and e.fecha between :fechaInicio and :fechaFin and e.status='A'";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findAsientoDetalleByEmpresaByCuentaIdByPeriodoIdByPlanCuentaIdAndByFechaInicioAndFechaFin(Long idEmpresa,Long idCuenta, Long idPeriodo, Long idPlanCuenta, java.sql.Date fechaInicio, java.sql.Date fechaFin, boolean fechaInicioInclusive) throws GenericBusinessException {
		//select distinct ad.* from asiento_detalle ad, asiento e where
		//ad.ASIENTO_ID = e.ID and ad.CUENTA_ID = 19
		//and e.FECHA between TO_Date('2005-01-01', 'YYYY-MM-DD') and TO_Date('2005-12-31', 'YYYY-MM-DD')
		String queryString = "";
		
		if (fechaInicioInclusive)
			queryString = "select distinct ad, a from AsientoDetalleEJB ad, AsientoEJB a where a.empresaId = " + idEmpresa + " and ad.asientoId = a.id and ad.cuentaId = " + idCuenta + " and a.periodoId = " + idPeriodo + " and a.plancuentaId = " + idPlanCuenta + " and a.fecha >= :fechaInicio and a.fecha <= :fechaFin and a.status = 'A'";
		else
			queryString = "select distinct ad, a from AsientoDetalleEJB ad, AsientoEJB a where a.empresaId = " + idEmpresa + " and ad.asientoId = a.id and ad.cuentaId = " + idCuenta + " and a.periodoId = " + idPeriodo + " and a.plancuentaId = " + idPlanCuenta + " and a.fecha > :fechaInicio and a.fecha <= :fechaFin and a.status = 'A'";
		
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List findAsientoDetalleByAsientoIdOrderByDebeHaberAndCodigo(java.lang.Long asientoId) throws com.spirit.exception.GenericBusinessException {
		String queryString = "from AsientoDetalleEJB ad, CuentaEJB c where ad.asientoId = :asientoId and ad.cuentaId = c.id";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by ad.debe desc, c.codigo desc";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("asientoId", asientoId);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findAsientoDetalleByPlanCuentaIdByFechaInicioAndFechaFin(Long idPlanCuenta, java.sql.Date fechaInicio, java.sql.Date fechaFin) {
		String queryString = "select distinct ad from AsientoEJB a, AsientoDetalleEJB ad, CuentaEJB c where ad.asientoId = a.id and ad.cuentaId = c.id and c.plancuentaId = " + idPlanCuenta + " and a.fecha between :fechaInicio and :fechaFin and a.status = 'A'";
		queryString += " order by a.fecha, a.numero"; ;
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findAsientoDetalleByEmpresaByPeriodoIdByPlanCuentaIdByStatusAndByFechaInicioAndFechaFin(Long idEmpresa, Long idPeriodo, Long idPlanCuenta, String status, java.sql.Date fechaInicio, java.sql.Date fechaFin, boolean fechaInicioInclusive) throws GenericBusinessException {
		//select distinct ad.* from asiento_detalle ad, asiento e where
		//ad.ASIENTO_ID = e.ID and ad.CUENTA_ID = 19
		//and e.FECHA between TO_Date('2005-01-01', 'YYYY-MM-DD') and TO_Date('2005-12-31', 'YYYY-MM-DD')
		String queryString = "";
		
		if (fechaInicioInclusive)
			queryString = "select distinct e, ad from AsientoDetalleEJB ad, AsientoEJB e where e.empresaId = " + idEmpresa + " and ad.asientoId = e.id and e.periodoId = " + idPeriodo + " and e.plancuentaId = " + idPlanCuenta + " and e.fecha >= :fechaInicio and e.fecha <= :fechaFin";
		else
			queryString = "select distinct e, ad from AsientoDetalleEJB ad, AsientoEJB e where e.empresaId = " + idEmpresa + " and ad.asientoId = e.id and e.periodoId = " + idPeriodo + " and e.plancuentaId = " + idPlanCuenta + " and e.fecha > :fechaInicio and e.fecha <= :fechaFin";
		
		//if (status.equals("A") || status.equals("P"))
			 queryString += " and e.status = 'A'";
		
		//queryString += " order by ad.asientoId asc, ad.debe desc";
		queryString += " order by ad.debe desc, ad.haber desc";
		
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findAsientoDetalleByEmpresaByPeriodoIdByPlanCuentaIdByStatusAndByFechaInicioAndFechaFinOrderedByAsientoIdAndCuentaId(Long idEmpresa, Long idPeriodo, Long idPlanCuenta, String status, java.sql.Date fechaInicio, java.sql.Date fechaFin, boolean fechaInicioInclusive) throws GenericBusinessException {
		//select distinct ad.* from asiento_detalle ad, asiento e where
		//ad.ASIENTO_ID = e.ID and ad.CUENTA_ID = 19
		//and e.FECHA between TO_Date('2005-01-01', 'YYYY-MM-DD') and TO_Date('2005-12-31', 'YYYY-MM-DD')
		String queryString = "";
		
		if (fechaInicioInclusive)
			queryString = "select distinct e, ad, c from AsientoDetalleEJB ad, AsientoEJB e, CuentaEJB c where ad.cuentaId = c.id and e.empresaId = " + idEmpresa + " and ad.asientoId = e.id and e.periodoId = " + idPeriodo + " and e.plancuentaId = " + idPlanCuenta + " and e.fecha >= :fechaInicio and e.fecha <= :fechaFin";
		else
			queryString = "select distinct e, ad, c from AsientoDetalleEJB ad, AsientoEJB e, CuentaEJB c where ad.cuentaId = c.id and e.empresaId = " + idEmpresa + " and ad.asientoId = e.id and e.periodoId = " + idPeriodo + " and e.plancuentaId = " + idPlanCuenta + " and e.fecha > :fechaInicio and e.fecha <= :fechaFin";
		
		if (status.equals("A") || status.equals("P"))
			 queryString += " and e.status = '" + status + "'";
		
		//queryString += " order by ad.asientoId asc, ad.debe desc";
		queryString += " order by c.codigo asc, e.fecha asc, e.numero asc";
		
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		return query.getResultList();
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findAsiento_ADetalleByEmpresaByPeriodoIdByPlanCuentaIdByStatusAndByFechaInicioAndFechaFin(Long idEmpresa, Long idPeriodo, Long idPlanCuenta, String status, java.sql.Date fechaInicio, java.sql.Date fechaFin, boolean fechaInicioInclusive) throws GenericBusinessException {
		
		String queryString = "";
		
		if (fechaInicioInclusive)
			queryString = "select a.id,a.numero,a.observacion,ad.referencia,ad.debe,ad.haber,a.fecha,ad.cuentaId,a.tipoDocumentoId,ad.glosa,ad.id,a.transaccionId from AsientoDetalleEJB ad, AsientoEJB a " +
					"where a.empresaId = " + idEmpresa + " and ad.asientoId = a.id and a.periodoId = " + idPeriodo + " and " +
							"a.plancuentaId = " + idPlanCuenta + " and a.fecha >= :fechaInicio and a.fecha <= :fechaFin";
		else
			queryString = "select a.id,a.numero,a.observacion,ad.referencia,ad.debe,ad.haber,a.fecha,ad.cuentaId,a.tipoDocumentoId,ad.glosa,ad.id,a.transaccionId from AsientoDetalleEJB ad, AsientoEJB a " +
					"where a.empresaId = " + idEmpresa + " and ad.asientoId = a.id and a.periodoId = " + idPeriodo + " and " +
							"a.plancuentaId = " + idPlanCuenta + " and a.fecha > :fechaInicio and a.fecha <= :fechaFin";
		
		if (status.equals("A") || status.equals("P"))
			 queryString += " and a.status = '" + status + "'";
  
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		
		
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findAsientoDetalleByAsientoIdOrderedByCodigoCuenta(java.lang.Long asientoId) {	
		String queryString = "select distinct ad, c from AsientoDetalleEJB ad, CuentaEJB c where ad.asientoId = :asientoId and ad.cuentaId = c.id";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by c.codigo asc, ad.debe desc, ad.haber desc";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("asientoId", asientoId);
		return query.getResultList();
	}
	
	public Collection findAsientoDetalleFromAsientoCierreByQuery(Map queryMap) throws GenericBusinessException {
		String SELECT = "select distinct ad";
		String FROM = "from AsientoEJB a, AsientoDetalleEJB ad";
		String WHERE = "where a.id = ad.asientoId and a.asientoCierre = :asientoCierre and a.periodoId = :periodoId and a.empresaId = :empresaId and a.fecha = :fecha and a.plancuentaId = :plancuentaId and a.status = :status";
		String queryString = SELECT + " " + FROM + " " + WHERE;
		Query query = manager.createQuery(queryString);
		query.setParameter("asientoCierre", queryMap.get("asientoCierre"));
		query.setParameter("periodoId", queryMap.get("periodoId"));
		query.setParameter("empresaId", queryMap.get("empresaId"));
		query.setParameter("fecha", queryMap.get("fecha"));
		query.setParameter("plancuentaId", queryMap.get("plancuentaId"));
		query.setParameter("status", queryMap.get("status"));
		return query.getResultList();
	}
}
