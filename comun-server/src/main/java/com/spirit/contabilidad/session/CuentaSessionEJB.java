package com.spirit.contabilidad.session;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.CuentaEJB;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.session.generated._CuentaSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.seguridad.entity.UsuarioCuentaEJB;
import com.spirit.seguridad.entity.UsuarioCuentaIf;
import com.spirit.seguridad.session.UsuarioCuentaSessionLocal;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>CuentaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class CuentaSessionEJB extends _CuentaSession implements CuentaSessionRemote, CuentaSessionLocal {
	
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	@EJB private UsuarioCuentaSessionLocal usuarioCuentaLocal;
	@EJB private AsientoDetalleSessionLocal asientoDetalleLocal;
	@Resource private SessionContext ctx;
	private DecimalFormat formatoSerialMes = new DecimalFormat("00");
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(CuentaSessionEJB.class);
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void procesarCuenta(CuentaIf modelCuenta, Long idUsuario) throws GenericBusinessException {
		try {
			CuentaEJB cuenta = registrarCuenta(modelCuenta);
			manager.persist(cuenta);
			UsuarioCuentaEJB usuarioCuenta = new UsuarioCuentaEJB();
			usuarioCuenta.setUsuarioId(idUsuario);
			usuarioCuenta.setCuentaId(cuenta.getPrimaryKey());
			manager.merge(usuarioCuenta);
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			if (e instanceof GenericBusinessException )
				throw new GenericBusinessException(e.getMessage());
			throw new GenericBusinessException("Se ha producido un error al guardar los datos");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean procesarEliminarCuenta(Long idCuenta) throws GenericBusinessException {
		boolean cuentaEliminada = false;
		try {
			CuentaIf cuenta = getCuenta(idCuenta);
			Collection <UsuarioCuentaIf> modelUsuarioCuentaList = usuarioCuentaLocal.findUsuarioCuentaByCuentaId(cuenta.getId());
			Collection <AsientoDetalleIf> modelAsientoDetalleList = asientoDetalleLocal.findAsientoDetalleByCuentaId(cuenta.getId());
			
			if((modelUsuarioCuentaList.size() < 2) && (modelAsientoDetalleList.size() == 0)){
				for (UsuarioCuentaIf modelUsuarioCuenta : modelUsuarioCuentaList){
					manager.remove(modelUsuarioCuenta);
				}
				manager.remove(cuenta);
				manager.flush();
				cuentaEliminada = true;
			}
		} catch(Exception e){
			ctx.setRollbackOnly();
			e.printStackTrace();
			
			if (e instanceof GenericBusinessException)
				throw new GenericBusinessException(e.getMessage());
			throw new GenericBusinessException("Se ha produciso un error al eliminar la cuenta");
		}
		return cuentaEliminada;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCuentaByQueryForBalance(Map aMap) throws GenericBusinessException {
		String objectName = "c";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct c from CuentaEJB " + objectName + ", TipoCuentaEJB tc where " + where;
		queryString += " and c.tipocuentaId = tc.id and tc.codigo in ('A','P','C')";
		Query query = manager.createQuery(queryString);
		
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
	public Collection getCuentaList(int startIndex, int endIndex, Map aMap, Long idUsuario) {
		if ((endIndex - startIndex) < 0)
			return new ArrayList();
		
		String objectName = "c";
		String codigoNombreCuenta = "";
		if (aMap != null) {
			if (aMap.containsKey("codigoNombreCuenta")) {
				codigoNombreCuenta = aMap.get("codigoNombreCuenta").toString();
				aMap.remove("codigoNombreCuenta");
			}
		}
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "";
		
		if (idUsuario.compareTo(0L) == 0)
			queryString = "select distinct c from CuentaEJB " + objectName + " where " + where;
		else
			queryString = "select distinct c from CuentaEJB " + objectName + ", UsuarioCuentaEJB uc, UsuarioEJB u where " + where + " and c.id = uc.cuentaId and u.id = uc.usuarioId and u.id = " + idUsuario;
		
		if (!codigoNombreCuenta.equals("")) {
			queryString += " and (c.codigo like '" + codigoNombreCuenta + "' or c.nombre like '" + codigoNombreCuenta + "')";
		}
		
		queryString += " order by c.codigo asc";
		
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
	public int getCuentaListSize(Map aMap) {
		String objectName = "c";
		String codigoNombreCuenta = "";
		if (aMap != null) {
			if (aMap.containsKey("codigoNombreCuenta")) {
				codigoNombreCuenta = aMap.get("codigoNombreCuenta").toString();
				aMap.remove("codigoNombreCuenta");
			}
		}
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select count(*) from CuentaEJB " + objectName + " where " + where;
		
		if (!codigoNombreCuenta.equals("")) {
			queryString += " and (c.codigo like '" + codigoNombreCuenta + "' or c.nombre like '" + codigoNombreCuenta + "')";
		}
		
		Query query = manager.createQuery(queryString);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getCuentaListSize(Map aMap, Long idUsuario) {
		String objectName = "c";
		String codigoNombreCuenta = "";
		if (aMap != null) {
			if (aMap.containsKey("codigoNombreCuenta")) {
				codigoNombreCuenta = aMap.get("codigoNombreCuenta").toString();
				aMap.remove("codigoNombreCuenta");
			}
		}
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "";
		
		if (idUsuario.compareTo(0L) == 0)
			queryString = "select count(*) from CuentaEJB " + objectName + " where " + where;
		else
			queryString = "select count(*) from CuentaEJB " + objectName + ", UsuarioCuentaEJB uc, UsuarioEJB u where " + where + " and c.id = uc.cuentaId and u.id = uc.usuarioId and u.id = " + idUsuario;
		
		if (!codigoNombreCuenta.equals("")) {
			queryString += " and (c.codigo like '" + codigoNombreCuenta + "' or c.nombre like '" + codigoNombreCuenta + "')";
		}
		
		Query query = manager.createQuery(queryString);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCuentaByPadreIdAndByUsuarioId(
			java.lang.Long idUsuario, java.lang.Long idCuentaPadre) {
		String objectName = "c";
		String queryString = "select distinct c from CuentaEJB " + objectName + ", UsuarioCuentaEJB uc where c.id = uc.cuentaId and uc.usuarioId = " + idUsuario;
		if (idCuentaPadre.compareTo(0L) != 0)
			queryString += " and c.padreId = " + idCuentaPadre; 
		
		queryString	+= " order by c.id asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCuentaByPadreIdByUsuarioIdAndNivel(java.lang.Long idUsuario, java.lang.Long idCuentaPadre, int nivel) {
		String objectName = "c";
		String queryString = "select distinct c from CuentaEJB " + objectName + ", UsuarioCuentaEJB uc where c.id = uc.cuentaId and uc.usuarioId = " + idUsuario + " and c.nivel = " + nivel;
		if (idCuentaPadre.compareTo(0L) != 0)
			queryString += " and c.padreId = " + idCuentaPadre; 
		
		queryString	+= " order by c.id asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	public Collection findCuentaByQueryByUsuarioIdAndCodigoOrNombre(Map aMap, Long idUsuario) throws com.spirit.exception.GenericBusinessException {
		String objectName = "c";
		String codigoNombreCuenta = "";
		if (aMap.containsKey("codigoNombreCuenta")) {
			codigoNombreCuenta = aMap.get("codigoNombreCuenta").toString();
			aMap.remove("codigoNombreCuenta");
		}
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct c from CuentaEJB " + objectName + ", UsuarioCuentaEJB uc, UsuarioEJB u where " + where + " and c.id = uc.cuentaId and u.id = uc.usuarioId and u.id = " + idUsuario;
		
		if (!codigoNombreCuenta.equals("")) {
			queryString += " and (c.codigo like '" + codigoNombreCuenta + "' or c.nombre like '" + codigoNombreCuenta + "')";
		}
		
		Query query = manager.createQuery(queryString);
		
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
	public int findCuentaByQueryByUsuarioIdAndCodigoOrNombreListSize(Map aMap, Long idUsuario) {
		String objectName = "c";
		String codigoNombreCuenta = "";
		if (aMap.containsKey("codigoNombreCuenta")) {
			codigoNombreCuenta = aMap.get("codigoNombreCuenta").toString();
			aMap.remove("codigoNombreCuenta");
		}
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select count(*) from CuentaEJB " + objectName + ", UsuarioCuentaEJB uc, UsuarioEJB u where " + where + " and c.id = uc.cuentaId and u.id = uc.usuarioId and u.id = " + idUsuario;;
		
		if (!codigoNombreCuenta.equals("")) {
			queryString += " and (c.codigo like '" + codigoNombreCuenta + "' or c.nombre like '" + codigoNombreCuenta + "')";
		}
		
		Query query = manager.createQuery(queryString);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
		
	}
	
	public Collection findCuentasForBalanceGeneral(Long planCuentaId, Long periodoId, java.sql.Date fechaFinMovimiento) throws com.spirit.exception.GenericBusinessException {
		//SELECT DISTINCT C.* FROM PLAN_CUENTA PC, CUENTA C, ASIENTO A, ASIENTO_DETALLE AD WHERE C.PLANCUENTA_ID = PC.ID AND AD.ASIENTO_ID = A.ID AND AD.CUENTA_ID = C.ID AND A.PERIODO_ID = 1741 AND A.FECHA <= TO_Date('2007-09-24','YYYY-MM-DD') AND PC.ID = 160
		//String queryString = "select distinct c from PlanCuentaEJB pc, CuentaEJB c, TipoCuentaEJB tc, AsientoEJB a, AsientoDetalleEJB ad where c.plancuentaId = pc.id and ad.asientoId = a.id and ad.cuentaId = c.id and a.periodoId = " + periodoId + " and a.fecha <= :fechaFinMovimiento and pc.id = " + planCuentaId + " and a.status = 'A' and c.tipocuentaId = tc.id and (tc.codigo = 'A' or tc.codigo = 'P' or tc.codigo = 'C')";
		String queryString = "select distinct c from PlanCuentaEJB pc, CuentaEJB c, TipoCuentaEJB tc, AsientoEJB a, AsientoDetalleEJB ad where c.plancuentaId = pc.id and ad.asientoId = a.id and ad.cuentaId = c.id and a.periodoId = " + periodoId + " and a.fecha <= :fechaFinMovimiento and pc.id = " + planCuentaId + " and a.status = 'A' and c.tipocuentaId = tc.id";
		String orderByPart = "";
		orderByPart += " order by c.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaFinMovimiento",fechaFinMovimiento);
		return query.getResultList();
	}
	
	public Collection findCuentasAdicionalesForBalanceGeneral(Long planCuentaId, Long periodoId, java.sql.Date fechaFinMovimiento) throws com.spirit.exception.GenericBusinessException {
		String year = String.valueOf(fechaFinMovimiento.getYear() + 1900);
		String month = formatoSerialMes.format(fechaFinMovimiento.getMonth() + 1);
		String queryString = "select distinct cu from CuentaEJB cu, PlanCuentaEJB plc, SaldoCuentaEJB sc where cu.plancuentaId = plc.id and plc.id = " + planCuentaId + " and cu.id = sc.cuentaId and sc.periodoId = " + periodoId + " and sc.anio = '" + year + "' and sc.mes = '" + month + "' and cu.id not in (select distinct c.id from PlanCuentaEJB pc, CuentaEJB c, TipoCuentaEJB tc, AsientoEJB a, AsientoDetalleEJB ad where c.plancuentaId = pc.id and ad.asientoId = a.id and ad.cuentaId = c.id and a.periodoId = " + periodoId + " and a.fecha <= :fechaFinMovimiento and pc.id = " + planCuentaId + " and a.status = 'A' and c.tipocuentaId = tc.id)";
		String orderByPart = "";
		orderByPart += " order by cu.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaFinMovimiento",fechaFinMovimiento);
		return query.getResultList();
	}
	
	public Collection findCuentasForEstadoResultados(Long planCuentaId, Long periodoId, java.sql.Date fechaInicioMovimiento, java.sql.Date fechaFinMovimiento) throws com.spirit.exception.GenericBusinessException {
		//SELECT DISTINCT C.* FROM PLAN_CUENTA PC, CUENTA C, ASIENTO A, ASIENTO_DETALLE AD, TIPO_RESULTADO TR WHERE C.PLANCUENTA_ID = PC.ID AND AD.ASIENTO_ID = A.ID AND AD.CUENTA_ID = C.ID AND C.TIPORESULTADO_ID = TR.ID AND TR.ID = 1 AND A.PERIODO_ID = 1741 AND A.FECHA >= TO_Date('2007-08-01','YYYY-MM-DD') AND A.FECHA <= TO_Date('2007-09-30','YYYY-MM-DD') AND PC.ID = 160
		//String queryString = "select distinct c from PlanCuentaEJB pc, CuentaEJB c, AsientoEJB a, AsientoDetalleEJB ad, TipoResultadoEJB tr where c.plancuentaId = pc.id and ad.asientoId = a.id and ad.cuentaId = c.id and c.tiporesultadoId = tr.id and tr.id = " + tipoResultadoId + " and a.periodoId = " + periodoId + " and a.fecha >= :fechaInicioMovimiento and a.fecha <= :fechaFinMovimiento and pc.id = " + planCuentaId + " and a.status = 'A'";
		String queryString = "select distinct c from PlanCuentaEJB pc, CuentaEJB c, AsientoEJB a, AsientoDetalleEJB ad where c.plancuentaId = pc.id and ad.asientoId = a.id and ad.cuentaId = c.id and a.periodoId = " + periodoId + " and a.fecha >= :fechaInicioMovimiento and a.fecha <= :fechaFinMovimiento and pc.id = " + planCuentaId + " and a.status = 'A'";
		String orderByPart = " order by c.codigo asc";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicioMovimiento",fechaInicioMovimiento);
		query.setParameter("fechaFinMovimiento",fechaFinMovimiento);
		return query.getResultList();
	}
	
	public Collection findCuentaByPlanCuentaIdBetweenCuentaInicialCodigoAndCuentaFinalCodigoByUsuarioId(Long idPlanCuenta, Long idUsuario, String codigoCuentaInicial, String codigoCuentaFinal, String imputable) throws GenericBusinessException {
		String queryString = "select distinct c from PlanCuentaEJB pc, CuentaEJB c, UsuarioCuentaEJB uc where uc.cuentaId = c.id and uc.usuarioId = " + idUsuario + " and c.plancuentaId = pc.id and c.codigo >= '" + codigoCuentaInicial + "' and c.codigo <= '" + codigoCuentaFinal + "' and c.imputable = '" + imputable + "'";
		String orderByPart = "";
		orderByPart += " order by c.codigo asc";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();	
	}
	
	public Collection findCuentaByQueryAndUsuarioId(Map aMap, Long idUsuario) throws GenericBusinessException {
		String objectName = "c";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct c from CuentaEJB " + objectName + ", UsuarioCuentaEJB uc, UsuarioEJB u where " + where + " and c.id = uc.cuentaId and u.id = uc.usuarioId and u.id = " + idUsuario + " order by c.codigo asc";
		Query query = manager.createQuery(queryString);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		
		return query.getResultList();
	}
	
	public Collection findCuentaByEmpresaIdAndQuery(Long idEmpresa, Map aMap) throws GenericBusinessException {
		//select c.* from cuenta c, plan_cuenta pc where c.PLANCUENTA_ID = pc.ID and pc.EMPRESA_ID = 1 and c.CODIGO = '31030100001' 
		String objectName = "c";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct c from CuentaEJB " + objectName + ", PlanCuentaEJB pc where " + where + " and c.plancuentaId = pc.id and pc.empresaId = " + idEmpresa + " order by c.codigo asc";
		Query query = manager.createQuery(queryString);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		
		return query.getResultList();
	}
	
	private CuentaEJB registrarCuenta(CuentaIf model) {
		CuentaEJB cuenta = new CuentaEJB();
		cuenta.setId(model.getId());
		cuenta.setPlancuentaId(model.getPlancuentaId());
		cuenta.setCodigo(model.getCodigo());
		cuenta.setNombre(model.getNombre());
		cuenta.setNombreCorto(model.getNombreCorto());
		cuenta.setTipocuentaId(model.getTipocuentaId());
		cuenta.setPadreId(model.getPadreId());
		cuenta.setRelacionada(model.getRelacionada());
		cuenta.setImputable(model.getImputable());
		cuenta.setCuentaBanco(model.getCuentaBanco());
		cuenta.setNivel(model.getNivel());
		cuenta.setTiporesultadoId(model.getTiporesultadoId());
		cuenta.setEfectivo(model.getEfectivo());
		cuenta.setActivofijo(model.getActivofijo());
		cuenta.setDepartamento(model.getDepartamento());
		cuenta.setLinea(model.getLinea());
		cuenta.setEmpleado(model.getEmpleado());
		cuenta.setCentrogasto(model.getCentrogasto());
		cuenta.setCliente(model.getCliente());
		cuenta.setGasto(model.getGasto());
		cuenta.setEstado(model.getEstado());
		return cuenta;
	}
	
	public int getNivelMaximoByPlanCuentaId(Long idPlanCuenta) {
		String queryString = "select max(nivel) from CuentaEJB c where c.plancuentaId = " + idPlanCuenta;
		Query query = manager.createQuery(queryString);
		String data = query.getResultList().toString();
		int indexOfCierreCorchete = data.indexOf("]");
		return Integer.parseInt(data.substring(1, indexOfCierreCorchete));
	}
	
	public Collection findCuentaByEmpresaId(Long idEmpresa) throws GenericBusinessException {
		String queryString = "select distinct c from CuentaEJB c, PlanCuentaEJB pc where c.plancuentaId = pc.id and pc.empresaId = " + idEmpresa;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	public Collection<CuentaIf> findCuentasDeCuentasBancarias(Long planCuentaId,String tipoEntidad,Long empresaId) throws GenericBusinessException {
		//Long planCuentaId = (Long) aMap.get("planCuentaId");
		//Long tipoCuenta = (Long) aMap.get("tipoCuenta");
		//Long empresaId = (Long) aMap.get("empresaId");
		String queryString = 
			" select c from PlanCuentaEJB pc,CuentaEJB c,CuentaEntidadEJB ce " +
			" where pc.id = c.plancuentaId and c.id = ce.cuentaId " +
			" and ce.tipoEntidad = '"+tipoEntidad+"' and pc.id = " +planCuentaId+
			" and pc.empresaId = "+empresaId;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	public Map mapearCuentas(Long idEmpresa) throws GenericBusinessException {
		Map cuentasMap = new HashMap();
		Iterator cuentasIterator = findCuentaByEmpresaId(idEmpresa).iterator();
		while (cuentasIterator.hasNext()) {
			CuentaIf cuenta = (CuentaIf) cuentasIterator.next();
			cuentasMap.put(cuenta.getId(), cuenta);
		}
		return cuentasMap;
	}
}
