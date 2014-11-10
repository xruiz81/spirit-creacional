package com.spirit.medios.session;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
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

import com.spirit.bpm.campana.ProcesoOrdenTrabajoSessionService;
import com.spirit.bpm.procesos.ProcesoOrdenTrabajoLocal;
import com.spirit.bpm.process.elements.Tarea;
import com.spirit.compras.entity.OrdenCompraData;
import com.spirit.compras.entity.OrdenCompraDetalleData;
import com.spirit.compras.entity.OrdenCompraDetalleIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.entity.SolicitudCompraDetalleData;
import com.spirit.compras.entity.SolicitudCompraDetalleIf;
import com.spirit.compras.entity.SolicitudCompraEJB;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.compras.handler.EstadoOrdenCompra;
import com.spirit.compras.session.OrdenCompraDetalleSessionLocal;
import com.spirit.compras.session.OrdenCompraSessionLocal;
import com.spirit.compras.session.SolicitudCompraDetalleSessionLocal;
import com.spirit.compras.session.SolicitudCompraSessionLocal;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.session.ClienteOficinaSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.session.EmpleadoSessionLocal;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.MonedaSessionLocal;
import com.spirit.general.session.OficinaSessionLocal;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.session.BodegaSessionLocal;
import com.spirit.inventario.session.GenericoSessionLocal;
import com.spirit.inventario.session.ProductoSessionLocal;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PresupuestoArchivoIf;
import com.spirit.medios.entity.PresupuestoDetalleEJB;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoEJB;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.entity.PresupuestoProductoData;
import com.spirit.medios.entity.PresupuestoProductoEJB;
import com.spirit.medios.entity.PresupuestoProductoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.session.generated._PresupuestoSession;
import com.spirit.server.QueryBuilder;
import com.spirit.util.Utilitarios;

/**
 * The <code>PresupuestoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.2 $, $Date: 2014/04/01 00:22:45 $
 *
 */
@Stateless
public class PresupuestoSessionEJB extends _PresupuestoSession implements PresupuestoSessionRemote, PresupuestoSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

	@EJB private PresupuestoDetalleSessionLocal presupuestoDetalleLocal;
	
	@EJB private PresupuestoProductoSessionLocal presupuestoProductoLocal;
	
	@EJB private PresupuestoArchivoSessionLocal presupuestoArchivoLocal;
	
	@EJB private OrdenTrabajoSessionLocal ordenTrabajoLocal;
	
	@EJB private SolicitudCompraSessionLocal solicitudCompraLocal;
	
	@EJB private SolicitudCompraDetalleSessionLocal solicitudCompraDetalleLocal;
	
	@EJB private OrdenCompraSessionLocal ordenCompraLocal;
	
	@EJB private OrdenCompraDetalleSessionLocal ordenCompraDetalleLocal;
	
	@EJB private OrdenTrabajoDetalleSessionLocal ordenTrabajoDetalleLocal;
	
	@EJB private BodegaSessionLocal bodegaLocal;
	
	@EJB private EmpleadoSessionLocal empleadoLocal;
	
	@EJB private ClienteOficinaSessionLocal clienteOficinaLocal;
	
	@EJB private ProductoSessionLocal productoLocal;
	
	@EJB private GenericoSessionLocal genericoLocal;
	
	@EJB private MonedaSessionLocal monedaLocal;
	
	@EJB private OficinaSessionLocal oficinaLocal;
	
	@EJB private EmpresaSessionLocal empresaLocal;
	
	//@EJB private ProcesoOrdenTrabajoCreacionalSessionLocal procesoOrdenTrabajoLocal;
	@EJB private ProcesoOrdenTrabajoLocal procesoOrdenTrabajoLocal; 

   private DecimalFormat formatoSerial = new DecimalFormat("00000");
   
   private static final String AGRUPAR_POR_CLIENTE_TMEDIOS_MEDIOS_PRODUCTO = "CTMP";
   private static final String AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO = "TMCP";
   
   @Resource private SessionContext ctx;
   
   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPresupuestoByQuery(Map aMap, Long idEmpresa) {
		String objectName = "p";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct p from PresupuestoEJB " + objectName + ",OrdenTrabajoDetalleEJB otd, EmpleadoEJB e, EmpresaEJB em where ";
		if (aMap.size() > 0)
			queryString += where + " and ";
		queryString += "p.ordentrabajodetId = otd.id and otd.asignadoaId = e.id and e.empresaId = em.id and em.id = " + idEmpresa + " order by p.codigo desc";
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
	public int findPresupuestoByQueryAndEmpresaIdListSize(Map aMap, Long idEmpresa) {
		String objectName = "p";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct count(*) from PresupuestoEJB " + objectName + ",OrdenTrabajoDetalleEJB otd, EmpleadoEJB e, EmpresaEJB em where " + where;
		queryString += " and p.ordentrabajodetId = otd.id and otd.asignadoaId = e.id and e.empresaId = em.id and em.id = " + idEmpresa;
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
		return countResult.intValue();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPresupuestoByQueryAndEmpresaId(int startIndex, int endIndex, Map aMap, Long idEmpresa) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "p";
		String queryString = "select distinct " + objectName + " from PresupuestoEJB " + objectName + ",OrdenTrabajoDetalleEJB otd, EmpleadoEJB e, EmpresaEJB em where";
		queryString += " p.ordentrabajodetId = otd.id and otd.asignadoaId = e.id and e.empresaId = em.id and em.id = " + idEmpresa;
		if (aMap != null) {
			String where = QueryBuilder.buildWhere(aMap, objectName);
			queryString += " and " + where;
		}
		queryString +=  " order by p.codigo desc";
		Query query = manager.createQuery(queryString);
		
		if (aMap != null) {
			Set keys = aMap.keySet();
			Iterator it = keys.iterator();
			
			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
				
			}
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPresupuestoByQueryAndProveedorId(Map aMap, Long idProveedor, Long idEmpresa) {
		String objectName = "p";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct p from PresupuestoEJB " + objectName + ", PresupuestoDetalleEJB pd, OrdenTrabajoDetalleEJB otd, EmpleadoEJB e, EmpresaEJB em where " + where;
		queryString += " and p.id = pd.presupuestoId and p.ordentrabajodetId = otd.id and otd.asignadoaId = e.id and e.empresaId = em.id and pd.proveedorId = " + idProveedor + " and em.id = " + idEmpresa + " order by p.codigo asc";
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
	public java.util.Collection findPresupuestoByFilteredQuery(int startIndex, int endIndex, Map aMap, Long idCliente, Long idClienteOficina, Long idEmpresa) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		
		String objectName = "p";
		String queryString = "select distinct " + objectName + " from PresupuestoEJB " + objectName + ", OrdenTrabajoDetalleEJB otd, OrdenTrabajoEJB ot, ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + objectName + ".ordentrabajodetId = otd.id and otd.ordenId = ot.id and ot.clienteoficinaId = co.id and co.clienteId = c.id";
		
		if (aMap != null) {
			String where = QueryBuilder.buildWhere(aMap, objectName);
			queryString += " and " + where;
		}
		
		if (idCliente != 0L)
			queryString += " and c.id = " + idCliente;
		
		if (idClienteOficina != 0L)
			queryString += " and co.id = " + idClienteOficina;
		
		queryString += " and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " order by " + objectName + ".codigo desc";
		
		Query query = manager.createQuery(queryString);
		
		if (aMap != null) {
			Set keys = aMap.keySet();
			Iterator it = keys.iterator();
			
			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
				
			}
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int findPresupuestoByFilteredQuerySize(Map aMap, Long idCliente, Long idClienteOficina, Long idEmpresa) {
		
		String objectName = "p";
		String queryString = "select distinct count(*) from PresupuestoEJB " + objectName + ", OrdenTrabajoDetalleEJB otd, OrdenTrabajoEJB ot, ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + objectName + ".ordentrabajodetId = otd.id and otd.ordenId = ot.id and ot.clienteoficinaId = co.id and co.clienteId = c.id";
		
		if (aMap != null) {
			String where = QueryBuilder.buildWhere(aMap, objectName);
			queryString += " and " + where;
		}
		
		if (idCliente != 0L)
			queryString += " and c.id = " + idCliente;
		
		if (idClienteOficina != 0L)
			queryString += " and co.id = " + idClienteOficina;
		
		queryString += " and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " order by " + objectName + ".id";
		
		Query query = manager.createQuery(queryString);
		
		if (aMap != null) {
			Set keys = aMap.keySet();
			Iterator it = keys.iterator();
			
			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
				
			}
		}
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int findPresupuestoByCodigoByClienteByEmpresaAndByEstadosSize(String codigo, Long idCliente, Long idClienteOficina, Long idEmpresa, String[] estados) {
		
		String queryString = "select distinct count(*) from PresupuestoEJB p, OrdenTrabajoDetalleEJB otd, OrdenTrabajoEJB ot, ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where p.ordentrabajodetId = otd.id and otd.ordenId = ot.id and ot.clienteoficinaId = co.id and co.clienteId = c.id";
		
		if (idCliente != 0L)
			queryString += " and c.id = " + idCliente;
		
		if (idClienteOficina != 0L)
			queryString += " and co.id = " + idClienteOficina;
			
		for(int i = 0; i < estados.length; i++){
			if(i == 0 && estados.length == 1){
				queryString += " and p.estado = '" + estados[i] + "'";
			}else if (i == 0 && estados.length > 1){
				queryString += " and (p.estado = '" + estados[i] + "'";
			}else if (i > 0 && i <= (estados.length -2)){
				queryString += " or p.estado = '" + estados[i] + "'";
			}else if (i > 0 && i == (estados.length -1)){
				queryString += " or p.estado = '" + estados[i] + "')";
			}
		}
		
		//queryString += " and p.codigo like '" + codigo + "' and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " order by p.id desc";
		if(codigo != null && !codigo.equals("")){
			queryString += " and p.codigo like '" + codigo + "'";
		}
		
		queryString += " and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " order by p.id desc";
		
		Query query = manager.createQuery(queryString);
		
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPresupuestoByCodigoByClienteByEmpresaAndByEstados(int startIndex, int endIndex, String codigo, Long idCliente, Long idClienteOficina, Long idEmpresa, String[] estados) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		
		String queryString = "select distinct p from PresupuestoEJB p, OrdenTrabajoDetalleEJB otd, OrdenTrabajoEJB ot, ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where p.ordentrabajodetId = otd.id and otd.ordenId = ot.id and ot.clienteoficinaId = co.id and co.clienteId = c.id";
		
		if (idCliente != 0L)
			queryString += " and c.id = " + idCliente;
		
		if (idClienteOficina != 0L)
			queryString += " and co.id = " + idClienteOficina;
			
		for(int i = 0; i < estados.length; i++){
			if(i == 0 && estados.length == 1){
				queryString += " and p.estado = '" + estados[i] + "'";
			}else if (i == 0 && estados.length > 1){
				queryString += " and (p.estado = '" + estados[i] + "'";
			}else if (i > 0 && i <= (estados.length -2)){
				queryString += " or p.estado = '" + estados[i] + "'";
			}else if (i > 0 && i == (estados.length -1)){
				queryString += " or p.estado = '" + estados[i] + "')";
			}
		}
		
		//queryString += " and p.codigo like '" + codigo + "' and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " order by p.id desc";
		if(codigo != null && !codigo.equals("")){
			queryString += " and p.codigo like '" + codigo + "'";
		}
		
		queryString += " and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " order by p.id desc";
		
		Query query = manager.createQuery(queryString);
		
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int findPresupuestoByEmpresaIdByProveedorIdAndByEstadosSize(Long idProveedor, Long idEmpresa, String[] estados) {
		
		String queryString = "select distinct count(*) "
				+ "from PresupuestoEJB p, PresupuestoDetalleEJB pd, ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc "
				+ "where p.id = pd.presupuestoId and pd.proveedorId = " + idProveedor + " and p.clienteOficinaId = co.id and co.clienteId = c.id";
		
		for(int i = 0; i < estados.length; i++){
			if(i == 0 && estados.length == 1){
				queryString += " and p.estado = '" + estados[i] + "'";
			}else if (i == 0 && estados.length > 1){
				queryString += " and (p.estado = '" + estados[i] + "'";
			}else if (i > 0 && i <= (estados.length -2)){
				queryString += " or p.estado = '" + estados[i] + "'";
			}else if (i > 0 && i == (estados.length -1)){
				queryString += " or p.estado = '" + estados[i] + "')";
			}
		}
		
		queryString += " and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " order by p.id desc";
		
		Query query = manager.createQuery(queryString);
		
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPresupuestoByEmpresaIdByProveedorIdAndByEstados(int startIndex, int endIndex, Long idProveedor, Long idEmpresa, String[] estados) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		
		String queryString = "select distinct p "
				+ "from PresupuestoEJB p, PresupuestoDetalleEJB pd, ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc "
				+ "where p.id = pd.presupuestoId and pd.proveedorId = " + idProveedor + " and p.clienteOficinaId = co.id and co.clienteId = c.id";
		
		for(int i = 0; i < estados.length; i++){
			if(i == 0 && estados.length == 1){
				queryString += " and p.estado = '" + estados[i] + "'";
			}else if (i == 0 && estados.length > 1){
				queryString += " and (p.estado = '" + estados[i] + "'";
			}else if (i > 0 && i <= (estados.length -2)){
				queryString += " or p.estado = '" + estados[i] + "'";
			}else if (i > 0 && i == (estados.length -1)){
				queryString += " or p.estado = '" + estados[i] + "')";
			}
		}
		
		queryString += " and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " order by p.id desc";
		
		Query query = manager.createQuery(queryString);
		
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}
	
	// select distinct p.* from presupuesto p, orden_trabajo_detalle otd where
	// p.ORDENTRABAJODET_ID = otd.ID and otd.ORDEN_ID = '851968'
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPresupuestoByOrdenTrabajoId(java.lang.Long idOrden) throws GenericBusinessException {
		
		String objectName = "m";
		String queryString = "select distinct m from PresupuestoEJB "
			+ objectName
			+ ", OrdenTrabajoDetalleEJB otd where  m.ordentrabajodetId = otd.id and otd.ordenId = "
			+ idOrden + " order by m.id desc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	// select distinct p* from presupuesto p, orden_trabajo_detalle otd,
	// orden_trabajo ot where p.ORDENTRABAJODET_ID = otd.ID and otd.ORDEN_ID =
	// ot.ID and ot.CLIENTEOFICINA_ID = '4'
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPresupuestoByClienteOficinaId(java.lang.Long idClienteOficina) {
		
		String objectName = "m";
		String queryString = "select distinct m from PresupuestoEJB "
			+ objectName
			+ ", OrdenTrabajoDetalleEJB otd, OrdenTrabajoEJB ot where  m.ordentrabajodetId = otd.id and otd.ordenId = ot.id and ot.clienteoficinaId = "
			+ idClienteOficina + " order by m.id desc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPresupuestoByClienteOficinaIdAndEstado(java.lang.Long idClienteOficina) {
		String objectName = "m";
		String queryString = "select distinct m from PresupuestoEJB "
			+ objectName
			+ ", OrdenTrabajoDetalleEJB otd, OrdenTrabajoEJB ot where  m.ordentrabajodetId = otd.id and otd.ordenId = ot.id and ot.clienteoficinaId = "
			+ idClienteOficina + " and m.estado = 'A'";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPresupuestoByEmpresaId(java.lang.Long idEmpresa) throws GenericBusinessException {
		String queryString = "select distinct p from PresupuestoEJB p, OrdenTrabajoDetalleEJB otd, EmpleadoEJB e, EmpresaEJB em where p.ordentrabajodetId = otd.id and otd.asignadoaId = e.id and e.empresaId = em.id and em.id = " + idEmpresa + " order by p.id asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPresupuestoByCodigoAndEmpresaId(String codigo, java.lang.Long idEmpresa) throws GenericBusinessException {
		String queryString = "select distinct p from PresupuestoEJB p, OrdenTrabajoDetalleEJB otd, EmpleadoEJB e, EmpresaEJB em where p.ordentrabajodetId = otd.id and otd.asignadoaId = e.id and e.empresaId = em.id and p.codigo = '" + codigo + "' and em.id = " + idEmpresa + " order by p.id asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPresupuestoPresupuestoDetalleProductoClienteByFacturaIdByProveedorIdByProveedorOficinaId(Long idFactura, Long idProveedor, Long idProveedorOficina) throws GenericBusinessException {
		//Query sin medioId y medioOficinaId
		//select distinct p.*, pd.*, pc.* 
		//from PRESUPUESTO_FACTURACION pf, PRESUPUESTO_DETALLE pd, PRESUPUESTO p, PRESUPUESTO_PRODUCTO pp, PRODUCTO_CLIENTE pc 
		//where pf.FACTURA_ID = 25140 and pf.PRESUPUESTO_DETALLE_ID = pd.ID and pd.PRESUPUESTO_ID = p.ID 
		//and p.id = pp.PRESUPUESTO_ID and pp.PRODUCTO_CLIENTE_ID = pc.ID
		
		//Query con medioId y medioOficinaId
		//select distinct p.*, pd.*, pc.* 
		//from PRESUPUESTO_FACTURACION pf, PRESUPUESTO_DETALLE pd, PRESUPUESTO p, PRESUPUESTO_PRODUCTO pp, 
		//PRODUCTO_CLIENTE pc, CLIENTE me, CLIENTE_OFICINA mof 
		//where pf.FACTURA_ID = 25140 and pf.PRESUPUESTO_DETALLE_ID = pd.ID and pd.PRESUPUESTO_ID = p.ID 
		//and p.id = pp.PRESUPUESTO_ID and pp.PRODUCTO_CLIENTE_ID = pc.ID and
		//pd.PROVEEDOR_ID = mof.ID and mof.CLIENTE_ID = me.ID
		//and pd.PROVEEDOR_ID = 280 and me.id = 1340
		
		String queryString = "select distinct p, pc, pd from PresupuestoFacturacionEJB pf, PresupuestoDetalleEJB pd, PresupuestoEJB p, PresupuestoProductoEJB pp, ProductoClienteEJB pc, ClienteEJB me, ClienteOficinaEJB mof " +
				"where pf.facturaId = " + idFactura + " and pf.presupuestoDetalleId = pd.id and pd.presupuestoId = p.id and p.id = pp.presupuestoId and pp.productoClienteId = pc.id and pd.proveedorId = mof.id and mof.clienteId = me.id";
		
		if(idProveedorOficina != null && idProveedorOficina != 0L){
			queryString = queryString + " and pd.proveedorId = " + idProveedorOficina + "";
		}else if(idProveedor != null && idProveedor != 0L){
			queryString = queryString + " and me.id = " + idProveedor + "";
		}
		
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPresupuestoDetalleByFacturaIdByProveedorIdByProveedorOficinaId(Long idFactura, Long idProveedor, Long idProveedorOficina) throws GenericBusinessException {
		
		String queryString = "select distinct pd from PresupuestoFacturacionEJB pf, PresupuestoDetalleEJB pd, ClienteEJB me, ClienteOficinaEJB mof " +
				"where pf.facturaId = " + idFactura + " and pf.presupuestoDetalleId = pd.id and pd.proveedorId = mof.id and mof.clienteId = me.id";
		
		if(idProveedorOficina != null && idProveedorOficina != 0L){
			queryString = queryString + " and pd.proveedorId = " + idProveedorOficina + "";
		}else if(idProveedor != null && idProveedor != 0L){
			queryString = queryString + " and me.id = " + idProveedor + "";
		}
		
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPresupuestoDetalleByPedidoIdByProveedorIdByProveedorOficinaId(Long idPedido, Long idProveedor, Long idProveedorOficina) throws GenericBusinessException {
		
		String queryString = "select distinct pd from PedidoEJB pe, PresupuestoEJB p, PresupuestoDetalleEJB pd, ClienteEJB me, ClienteOficinaEJB mof " +
				"where pe.id = " + idPedido + " and pe.tiporeferencia = 'P' and pe.referencia = p.codigo and p.id = pd.presupuestoId and pd.reporte = 'N' and pd.proveedorId = mof.id and mof.clienteId = me.id";
		
		if(idProveedorOficina != null && idProveedorOficina != 0L){
			queryString = queryString + " and pd.proveedorId = " + idProveedorOficina + "";
		}else if(idProveedor != null && idProveedor != 0L){
			queryString = queryString + " and me.id = " + idProveedor + "";
		}
		
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPresupuestoProductoClienteByPresupuestoId(Long idPresupuesto) throws GenericBusinessException {
		
		String queryString = "select distinct p, pc from PresupuestoEJB p, PresupuestoProductoEJB pp, ProductoClienteEJB pc " +
				"where p.id = " + idPresupuesto + " and p.id = pp.presupuestoId and pp.productoClienteId = pc.id ";
		
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPresupuestoByPedido(Map<String,Object> queryMap) throws GenericBusinessException {
		//SELECT DISTINCT PR.CODIGO, PC.NOMBRE FROM PEDIDO P, PRESUPUESTO PR, PRESUPUESTO_PRODUCTO PRP, PRODUCTO_CLIENTE PC, TIPO_DOCUMENTO TD WHERE P.`REFERENCIA` = PR.`CODIGO` AND PR.`ID` = PRP.`PRESUPUESTO_ID` AND PRP.`PRODUCTO_CLIENTE_ID` = PC.`ID` AND P.`TIPODOCUMENTO_ID` = TD.`ID` AND TD.`EMPRESA_ID` = 1 AND P.`ID` = 13342
		String queryString = "select distinct pr, pc from PedidoEJB p, PresupuestoEJB pr, PresupuestoProductoEJB prp, ProductoClienteEJB pc, TipoDocumentoEJB td where p.referencia = pr.codigo and pr.id = prp.presupuestoId and prp.productoClienteId = pc.id and p.tipodocumentoId = td.id and td.empresaId = :empresaId and p.id = :pedidoId";
		Long empresaId = (Long) queryMap.get("enterpriseId");
		Long pedidoId = (Long) queryMap.get("orderId");
		Query query = manager.createQuery(queryString);
		query.setParameter("empresaId", empresaId);
		query.setParameter("pedidoId", pedidoId);
		return query.getResultList();
	}
	/*
	 @TransactionAttribute(TransactionAttributeType.REQUIRED)
	 public java.util.Collection findPresupuestoByQuery(Map aMap, Long idEmpresa) {
	 String objectName = "e";
	 String where = QueryBuilder.buildWhere(aMap, objectName);
	 String queryString = "select distinct e from PresupuestoEJB " + objectName + ", OrdenTrabajoDetalleEJB otd, OrdenTrabajoEJB ot, ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + objectName + ".ordentrabajodetId = otd.id and otd.ordenId = ot.id and ot.clienteoficinaId = co.id and co.clienteId = c.id and " + where;
	 queryString += " and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa;
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
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PresupuestoIf procesarPresupuesto(PresupuestoIf model, List<PresupuestoDetalleIf> modelDetalleList, 
			List<PresupuestoDetalleIf> modelDetalleListP, OrdenTrabajoIf ordenTrabajo, OrdenTrabajoDetalleIf ordenTrabajoDetalle, 
			List<ProductoClienteIf> modelProductoList, Tarea tarea) throws GenericBusinessException {

		String codigo = getMaximoCodigoPresupuesto(model.getCodigo());
		int codigoPresupuesto = 1;
		if (!codigo.equals("[null]")) {
			codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
			codigoPresupuesto = Integer.parseInt(codigo.split(model.getCodigo())[1]) + 1;
		}
		model.setCodigo(model.getCodigo() + formatoSerial.format(codigoPresupuesto));
		PresupuestoIf presupuesto = registrarPresupuesto(model);
		//manager.persist(presupuesto);
		presupuesto = addPresupuesto(presupuesto);

		for (ProductoClienteIf modelProducto : modelProductoList) {
			PresupuestoProductoData modelPresupuestoProducto = new PresupuestoProductoData();
			modelPresupuestoProducto.setPresupuestoId(presupuesto.getPrimaryKey());
			PresupuestoProductoIf presupuestoProducto = registrarPresupuestoProducto(modelPresupuestoProducto, modelProducto);
			//manager.merge(presupuestoProducto);
			presupuestoProductoLocal.savePresupuestoProducto(presupuestoProducto);
		}

		for (PresupuestoDetalleIf modelDetalle : modelDetalleList) {				
			modelDetalle.setPresupuestoId(presupuesto.getPrimaryKey());
			PresupuestoDetalleIf presupuestoDetalle = registrarPresupuestoDetalle(modelDetalle);
			//manager.merge(presupuestoDetalle);
			presupuestoDetalleLocal.savePresupuestoDetalle(presupuestoDetalle);
		}

		for (PresupuestoDetalleIf modelDetalleP : modelDetalleListP) {				
			modelDetalleP.setPresupuestoId(presupuesto.getPrimaryKey());
			PresupuestoDetalleIf presupuestoDetalleP = registrarPresupuestoDetalle(modelDetalleP);
			//manager.merge(presupuestoDetalleP);
			presupuestoDetalleLocal.savePresupuestoDetalle(presupuestoDetalleP);
		}

		//manager.merge(ordenTrabajo);
		ordenTrabajoLocal.saveOrdenTrabajo(ordenTrabajo);
		//manager.merge(ordenTrabajoDetalle);
		ordenTrabajoDetalleLocal.saveOrdenTrabajoDetalle(ordenTrabajoDetalle);
		

		return presupuesto;
	}
	
	public Map<String,Object> procesarPresupuestoServer(boolean generarOrdenesCompra,
			boolean eliminarOrdenesComprasPrevias,PresupuestoIf presupuesto, 
			List<PresupuestoDetalleIf> modelDetalleList, List<PresupuestoDetalleIf> modelDetalleListP, 
			OrdenTrabajoIf ordenTrabajo, OrdenTrabajoDetalleIf ordenTrabajoDetalle, 
			List<ProductoClienteIf> modelProductoList,Set<Integer> ordenesCompraParaModificar, 
			UsuarioIf usuario,Double IVA,String codigoMoneda,Long oficinaId, Tarea tarea) throws GenericBusinessException {
		try{
			Map<String,Object> mapaResultados = new HashMap<String, Object>();
			
			String ordenesComprasEliminadasConExito = null;
			Map<OrdenCompraIf, List<OrdenCompraDetalleIf>> ordenesCompraMap = new HashMap<OrdenCompraIf, List<OrdenCompraDetalleIf>>();
			mapaResultados.put("ordenesCompraMap", ordenesCompraMap);
			presupuesto = procesarPresupuesto(presupuesto, modelDetalleList, modelDetalleListP, 
				ordenTrabajo, ordenTrabajoDetalle, modelProductoList, tarea);
			
			Map<String,Object> mapaPresupeustoDetalle =  new HashMap<String, Object>();
			mapaPresupeustoDetalle.put("presupuestoId", presupuesto.getId());
			mapaPresupeustoDetalle.put("reporte", "N");
			Collection<PresupuestoDetalleIf> presupuestoDetalles = presupuestoDetalleLocal.findPresupuestoDetalleByQuery(mapaPresupeustoDetalle);
			mapaPresupeustoDetalle.put("reporte", "S");
			Collection<PresupuestoDetalleIf> presupuestoDetallesP = presupuestoDetalleLocal.findPresupuestoDetalleByQuery(mapaPresupeustoDetalle);
			
			mapaResultados.put("presupuestoDetalles", presupuestoDetalles);
			mapaResultados.put("presupuestoDetallesP", presupuestoDetallesP);
			
			ordenTrabajo = ordenTrabajoLocal.getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
			mapaResultados.put("ordenTrabajo", ordenTrabajo);
			ordenTrabajoDetalle = ordenTrabajoDetalleLocal.getOrdenTrabajoDetalle(ordenTrabajoDetalle.getId());
			mapaResultados.put("ordenTrabajoDetalle", ordenTrabajoDetalle);
			
			mapaResultados.put("presupuesto", presupuesto);
			
			crearOrdenesDeCompraServer(generarOrdenesCompra,// eliminarOrdenesComprasPrevias, 
					presupuesto, presupuestoDetalles, ordenesCompraParaModificar, 
					usuario, IVA, codigoMoneda, oficinaId, mapaResultados, 
					ordenesComprasEliminadasConExito, ordenesCompraMap, false);
			
	
			ClienteOficinaIf co = clienteOficinaLocal.getClienteOficina(ordenTrabajo.getClienteoficinaId());
			//procesoOrdenTrabajoLocal.procesarPresupuesto(presupuesto,co, tarea);
			OficinaIf oficina = oficinaLocal.getOficina(oficinaId);
			EmpresaIf empresa = empresaLocal.getEmpresa(oficina.getEmpresaId());
			ProcesoOrdenTrabajoSessionService procesoOrdenTrabajoSessionLocal = 
				procesoOrdenTrabajoLocal.getProcesoOrdenTrabajoSessionService(empresa);
			if ( procesoOrdenTrabajoSessionLocal != null )
				procesoOrdenTrabajoSessionLocal.procesarPresupuesto(presupuesto,co, tarea);
			
			return mapaResultados;
			
		} catch (Exception e) {
			//log.error(e.getMessage(),e);
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al procesar información en Presupuesto-PresupuestoDetalle");
		}
	}
	
	public void actualizarArchivosPresupuesto(PresupuestoIf model, List<PresupuestoArchivoIf> modelArchivoList, List<PresupuestoArchivoIf> archivosEliminadosList,String urlCarpetaSevidor) throws GenericBusinessException {
		try{
			PresupuestoIf presupuesto = registrarPresupuesto(model);
			manager.merge(presupuesto);
			
			for (PresupuestoArchivoIf modelArchivoEliminado : archivosEliminadosList) {
				PresupuestoArchivoIf data = presupuestoArchivoLocal.getPresupuestoArchivo(modelArchivoEliminado.getId());
				manager.remove(data);
			}
			
			for (PresupuestoArchivoIf modelArchivo : modelArchivoList) {
				modelArchivo.setPresupuestoId(presupuesto.getPrimaryKey());
				PresupuestoArchivoIf reunionArchivo = presupuestoArchivoLocal.registrarPresupuestoArchivo(modelArchivo,urlCarpetaSevidor);
				manager.merge(reunionArchivo);
			}
			
			manager.flush();
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al actualizar información en Reunion");
		}
	}
	
	private PresupuestoProductoEJB registrarPresupuestoProducto(PresupuestoProductoIf modelPresupuestoProducto, ProductoClienteIf modelProducto) 
	throws GenericBusinessException {
		try{
			PresupuestoProductoEJB presupuestoProducto = new PresupuestoProductoEJB();

			presupuestoProducto.setId(modelPresupuestoProducto.getId());
			presupuestoProducto.setProductoClienteId(modelProducto.getId());
			presupuestoProducto.setPresupuestoId(modelPresupuestoProducto.getPresupuestoId());

			return presupuestoProducto;
		} catch(Exception e){
			throw new GenericBusinessException("Error al registrar los productos de la Orden de Trabajo");
		}
	}
	
	private String getMaximoCodigoPresupuesto(String codigoParcialPresupuesto) {
		/*String queryString = "select max(codigo) from PresupuestoEJB p where p.codigo like '" + codigoParcialPresupuesto + "%'";
		Query query = manager.createQuery(queryString);
		return query.getResultList().toString();*/
		
		
		String queryString = "select max(codigo) from PresupuestoEJB p where p.codigo like '" + codigoParcialPresupuesto + "%'";
		Query query = manager.createQuery(queryString);
		String codigoPresupuesto = query.getResultList().toString();
		
		String queryString2 = "select max(codigo) from PlanMedioEJB pm where pm.codigo like '" + codigoParcialPresupuesto + "%'";
		Query query2 = manager.createQuery(queryString2);
		String codigoPlanMedio = query2.getResultList().toString();
		
		String codigo = codigoPresupuesto;
		if(codigoPlanMedio.compareTo(codigoPresupuesto) >= 1){
			codigo = codigoPlanMedio;
		}
		
		return codigo;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PresupuestoIf actualizarPresupuesto(PresupuestoIf model, List<PresupuestoDetalleIf> modelDetalleList, 
			List<PresupuestoDetalleIf> modelDetalleListP, OrdenTrabajoIf ordenTrabajo, 
			OrdenTrabajoDetalleIf ordenTrabajoDetalle, List<PresupuestoDetalleIf> presupuestoDetalleEliminadosList,
			List<ProductoClienteIf> productoClienteColeccion, Tarea tarea) throws GenericBusinessException {
		
			PresupuestoIf presupuesto = registrarPresupuesto(model);
			manager.merge(presupuesto);
			
			//Elimino todos los productos para volverlos a agregar luego en caso de que hayan cambiado.
			Collection<PresupuestoProductoIf> modelProductosEliminadosList = presupuestoProductoLocal.findPresupuestoProductoByPresupuestoId(presupuesto.getId());

			for (PresupuestoProductoIf modelProducto : modelProductosEliminadosList) {
				PresupuestoProductoIf data = presupuestoProductoLocal.getPresupuestoProducto(modelProducto.getId());
				manager.remove(data);
			}

			//Agrego los productos
			for (ProductoClienteIf modelProducto : productoClienteColeccion) {
				PresupuestoProductoData modelPresupuestoProducto = new PresupuestoProductoData();
				modelPresupuestoProducto.setPresupuestoId(presupuesto.getPrimaryKey());
				PresupuestoProductoIf presupuestoProducto = registrarPresupuestoProducto(modelPresupuestoProducto, modelProducto);
				manager.merge(presupuestoProducto);
			}
			
			for (PresupuestoDetalleIf modelDetalle : modelDetalleList) {
				modelDetalle.setPresupuestoId(presupuesto.getPrimaryKey());
				PresupuestoDetalleIf presupuestoDetalle = registrarPresupuestoDetalle(modelDetalle);
				manager.merge(presupuestoDetalle);
			}
			
			for (PresupuestoDetalleIf modelDetalleP : modelDetalleListP) {
				modelDetalleP.setPresupuestoId(presupuesto.getPrimaryKey());
				PresupuestoDetalleIf presupuestoDetalleP = registrarPresupuestoDetalle(modelDetalleP);
				manager.merge(presupuestoDetalleP);
			}
			
			for (PresupuestoDetalleIf modelDetalleEliminado : presupuestoDetalleEliminadosList) {
				PresupuestoDetalleEJB data = manager.find(PresupuestoDetalleEJB.class, modelDetalleEliminado.getId());
				manager.remove(data);
			}
			
			manager.merge(ordenTrabajo);
			manager.merge(ordenTrabajoDetalle);
			
			return presupuesto;
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarPresupuesto(Long presupuestoId,OrdenTrabajoDetalleIf ordenTrabajoDetalle) throws GenericBusinessException {
		try {
			PresupuestoEJB data = manager.find(PresupuestoEJB.class, presupuestoId);
			ordenTrabajoDetalleLocal.saveOrdenTrabajoDetalle(ordenTrabajoDetalle);
			
			Collection<PresupuestoProductoIf> modelProductoList = presupuestoProductoLocal.findPresupuestoProductoByPresupuestoId(presupuestoId);
			for (PresupuestoProductoIf modelProducto : modelProductoList) {
				manager.remove(modelProducto);
			}
			
			Collection<PresupuestoArchivoIf> modelArchivoList = presupuestoArchivoLocal.findPresupuestoArchivoByPresupuestoId(presupuestoId);
			for (PresupuestoArchivoIf modelArchivo : modelArchivoList) {
				manager.remove(modelArchivo);
			}
			
			Collection<PresupuestoDetalleIf> modelDetalleList = presupuestoDetalleLocal.findPresupuestoDetalleByPresupuestoId(presupuestoId);
			for (PresupuestoDetalleIf modelDetalle : modelDetalleList) {
				manager.remove(modelDetalle);
			}		
			
			manager.remove(data);
			manager.flush();
			
		} catch (Exception e) {
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al eliminar información en Presupuesto");
		}
		
	}

	public Map<String,Object> actualizarPresupuestoServer(boolean generarOrdenesCompra,//boolean modificarOrdenesComprasPrevias,
			PresupuestoIf presupuestoActualizado, List<PresupuestoDetalleIf> modelDetalleList, 
			List<PresupuestoDetalleIf> modelDetalleListP, OrdenTrabajoDetalleIf ordenDetalle, 
			List<PresupuestoDetalleIf> presupuestoDetalleEliminadosList, 
			List<ProductoClienteIf> productoClienteColeccion,Set<Integer> ordenesCompraParaModificar, 
			UsuarioIf usuario,Double IVA,String codigoMoneda,Long oficinaId,Tarea tarea, boolean esActualizacion) throws GenericBusinessException {
		
		try{
			Map<String,Object> mapaResultados =  new HashMap<String, Object>();
			
			//boolean ordenesComprasEliminadasConExito = false;
			String ordenesComprasEliminadasConExito = null;
			Map<OrdenCompraIf, List<OrdenCompraDetalleIf>> ordenesCompraMap = new HashMap<OrdenCompraIf, List<OrdenCompraDetalleIf>>();
			mapaResultados.put("ordenesCompraMap", ordenesCompraMap);
			
			OrdenTrabajoIf ordenTrabajo = ordenTrabajoLocal.getOrdenTrabajo(ordenDetalle.getOrdenId());
			mapaResultados.put("ordenTrabajo", ordenTrabajo);
			presupuestoActualizado = actualizarPresupuesto(presupuestoActualizado,
					modelDetalleList,modelDetalleListP, ordenTrabajo,ordenDetalle,
					presupuestoDetalleEliminadosList,productoClienteColeccion,tarea);
			//mapaResultados.put("presupuesto", presupuestoActualizado);
			
			Map<String,Object> mapaPresupeustoDetalle =  new HashMap<String, Object>();
			mapaPresupeustoDetalle.put("presupuestoId", presupuestoActualizado.getId());
			mapaPresupeustoDetalle.put("reporte", "N");
			Collection<PresupuestoDetalleIf> presupuestoDetalles = presupuestoDetalleLocal.findPresupuestoDetalleByQuery(mapaPresupeustoDetalle);
			mapaPresupeustoDetalle.put("reporte", "S");
			Collection<PresupuestoDetalleIf> presupuestoDetallesP = presupuestoDetalleLocal.findPresupuestoDetalleByQuery(mapaPresupeustoDetalle);
			
			mapaResultados.put("presupuestoDetalles", presupuestoDetalles);
			mapaResultados.put("presupuestoDetallesP", presupuestoDetallesP);
	
			crearOrdenesDeCompraServer(generarOrdenesCompra,//modificarOrdenesComprasPrevias, 
					presupuestoActualizado,presupuestoDetalles, ordenesCompraParaModificar, 
					usuario, IVA,codigoMoneda, oficinaId, mapaResultados,
					ordenesComprasEliminadasConExito, ordenesCompraMap, esActualizacion);
			
			presupuestoActualizado = getPresupuesto(presupuestoActualizado.getId());
			mapaResultados.put("presupuesto", presupuestoActualizado);
			
			ClienteOficinaIf co = clienteOficinaLocal.getClienteOficina(ordenTrabajo.getClienteoficinaId());
			//procesoOrdenTrabajoLocal.procesarPresupuesto(presupuestoActualizado,co, tarea);
			
			OficinaIf oficina = oficinaLocal.getOficina(oficinaId);
			EmpresaIf empresa = empresaLocal.getEmpresa(oficina.getEmpresaId());
			ProcesoOrdenTrabajoSessionService procesoOrdenTrabajoSessionLocal = 
				procesoOrdenTrabajoLocal.getProcesoOrdenTrabajoSessionService(empresa);
			if ( procesoOrdenTrabajoSessionLocal != null )
				procesoOrdenTrabajoSessionLocal.procesarPresupuesto(presupuestoActualizado,co, tarea);

			return mapaResultados;
			
		} catch(Exception e){
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Error al actualizar información en Presupuesto-PresupuestoDetalle");
		}
	}

	private void crearOrdenesDeCompraServer(boolean generarOrdenesCompra,
			//boolean modificarOrdenesComprasPrevias,
			PresupuestoIf presupuestoActualizado,Collection<PresupuestoDetalleIf> modelDetalleList,
			Set<Integer> ordenesCompraParaModificar, UsuarioIf usuario,
			Double IVA, String codigoMoneda, Long oficinaId,Map<String, Object> mapaResultados,
			String ordenesComprasEliminadasConExito,
			Map<OrdenCompraIf, List<OrdenCompraDetalleIf>> ordenesCompraMap, boolean esActualizacion)
			throws GenericBusinessException {
		
		Map<Integer, ArrayList<PresupuestoDetalleIf>> detalleCrear = new HashMap<Integer, ArrayList<PresupuestoDetalleIf>>();
		Map<Integer, ArrayList<PresupuestoDetalleIf>> detalleActualizar = new HashMap<Integer, ArrayList<PresupuestoDetalleIf>>();
		
		//Agrupo detalles por numero Orden
		Map<Integer, ArrayList<PresupuestoDetalleIf>> mapaDetallesAgrupados = new HashMap<Integer, ArrayList<PresupuestoDetalleIf>>();
		for (PresupuestoDetalleIf presupuestoDetalle : modelDetalleList) {
			ArrayList<PresupuestoDetalleIf> lista = mapaDetallesAgrupados.get(presupuestoDetalle.getOrden());
			if ( lista == null ){
				lista = new ArrayList<PresupuestoDetalleIf>();
				mapaDetallesAgrupados.put(presupuestoDetalle.getOrden(), lista);
			}
			lista.add(presupuestoDetalle);
		}
		
		//Creo grupo para actualizar ordenes de compras
		Map<Long,OrdenCompraIf> mapaOrdenesCompra = new HashMap<Long, OrdenCompraIf>();
		AgregarDetallesParaCrear:
		for ( Integer orden :  mapaDetallesAgrupados.keySet() ){
			ArrayList<PresupuestoDetalleIf> lista = mapaDetallesAgrupados.get(orden);
			for (PresupuestoDetalleIf pd : lista){
				if ( pd.getOrdenCompraId() != null ){
					//Si orden tiene estado INGRESADA, no puede ser modificada
					OrdenCompraIf oc = verificarMapaOrdenesCompra(mapaOrdenesCompra, pd.getOrdenCompraId());
					if (oc != null) {
						EstadoOrdenCompra estado = EstadoOrdenCompra.getEstadoOrdenCompraByLetra(oc.getEstado());
						if ( estado == EstadoOrdenCompra.INGRESADA ){
							continue AgregarDetallesParaCrear;
						}
					}
				}
			}
			if ( ordenesCompraParaModificar.contains(orden) ){
				detalleActualizar.put(orden, lista);
			}
		}
		//Retiro del grupo las que son para actualizar
		for ( Integer orden : detalleActualizar.keySet() ){
			mapaDetallesAgrupados.remove(orden);
		}
		
		//Creo grupo para nuevas ordenes de compras
		AgregarDetallesParaCrear:
		for ( Integer orden :  mapaDetallesAgrupados.keySet() ){
			ArrayList<PresupuestoDetalleIf> lista = mapaDetallesAgrupados.get(orden);
			for (PresupuestoDetalleIf pd : lista){
				if ( pd.getOrdenCompraId() != null )
					continue AgregarDetallesParaCrear;
			}
			detalleCrear.put(orden, lista);
		}
		
		Map<String,OrdenCompraIf> mapaOrdenesCreadas = new HashMap<String,OrdenCompraIf>();
		Map<String,OrdenCompraIf> mapaOrdenesModificadas = new HashMap<String,OrdenCompraIf>();
		mapaResultados.put("ordenesComprasCreadas", mapaOrdenesCreadas);
		mapaResultados.put("ordenesComprasModificadas", mapaOrdenesModificadas);
		
		Map<Long, ClienteOficinaIf> listaProveedoresMap = new HashMap<Long, ClienteOficinaIf>();
		if (generarOrdenesCompra) {
			crearActualizarOrdenesCompra(mapaResultados,
				presupuestoActualizado.getId(),ordenesCompraMap,usuario,
				IVA,codigoMoneda,oficinaId,listaProveedoresMap,
				detalleCrear,esActualizacion);
		} 
		if (detalleActualizar!= null && detalleActualizar.size() > 0){
			crearActualizarOrdenesCompra(mapaResultados,
				presupuestoActualizado.getId(),ordenesCompraMap,usuario,
				IVA,codigoMoneda,oficinaId,listaProveedoresMap,
				detalleActualizar,esActualizacion);
		}
		
	}

	private void crearActualizarOrdenesCompra(Map<String,Object> mapaResultados,
			Long presupuestoId,
			Map<OrdenCompraIf, List<OrdenCompraDetalleIf>> ordenesCompraMap,UsuarioIf usuario, 
			Double IVA,String codigoMoneda,Long oficinaId,
			Map<Long, ClienteOficinaIf> listaProveedoresMap,
			Map<Integer, ArrayList<PresupuestoDetalleIf>> mapaDetallesAgrupados, boolean esActualizacion)
	throws GenericBusinessException {
		
		Map<OrdenCompraIf,Collection<PresupuestoDetalleIf>> mapaPresupuestoOrden = new HashMap<OrdenCompraIf,Collection<PresupuestoDetalleIf>>();
		
		//Si genero ordenes es porque el presupuesto fue Aprobado, entonces
		//cambio su estado a Aprobado y seteo la fecha de aceptacion (aprobacion)
		PresupuestoIf presupuesto = getPresupuesto(presupuestoId);
		/*presupuesto.setEstado(EstadoPresupuesto.APROBADO.getLetra());
		
		if(presupuesto.getFechaAceptacion() == null)
			presupuesto.setFechaAceptacion(new Timestamp((new GregorianCalendar()).getTimeInMillis()));*/
		
		mapaResultados.put("presupuesto", presupuesto);
		//SessionServiceLocator.getPresupuestoSessionService().savePresupuesto(presupuesto);

		EmpresaIf empresaIf = empresaLocal.getEmpresa( usuario.getEmpresaId() );
		ordenesCompraMap.clear();
		
		//Creacion automatica de las Ordenes de Compra agrupadas por campo Orden
		GrupoDetallePorOrden:
		for ( Integer orden : mapaDetallesAgrupados.keySet() ){
			
			ArrayList<PresupuestoDetalleIf> listaDetalle = mapaDetallesAgrupados.get(orden); 
			
			//Verificar Si tiene una orden de Compra Asociada, si tiene, sigo 
			//con el otro grupo por Numero de Orden
			Long idOrdenCompra = null;
			for (PresupuestoDetalleIf pd : listaDetalle){
				if ( pd.getOrdenCompraId() != null ){
					idOrdenCompra = pd.getOrdenCompraId();
				}
			}
			
			Long proveedorId = listaDetalle.iterator().next().getProveedorId();
			
			//Long proveedorId = (Long) listaProveedoresIt.next();
			ClienteOficinaIf proveedorIf = verificarMapaProveedores(listaProveedoresMap, proveedorId);

			List bodegas = (List) bodegaLocal.findBodegaByOficinaId(oficinaId);
			
			// ORDENES DE COMPRA
			Long solicitudCompraId = null;
			OrdenCompraIf data = null;
			if ( idOrdenCompra == null ){
				data = new OrdenCompraData();
				solicitudCompraId = procesarSolicitudCompra(null,usuario, 
						listaDetalle,oficinaId, presupuesto, empresaIf, bodegas);
				String codigo = getCodigo(new java.sql.Date(
						new java.util.Date().getTime()));
				data.setCodigo(codigo);
			} else { 
				data = ordenCompraLocal.getOrdenCompra(idOrdenCompra);
				solicitudCompraId = data.getSolicitudcompraId();
			}
			
			data.setOficinaId(oficinaId);
			data.setProveedorId(proveedorIf.getId());
			MonedaIf moneda= (MonedaIf) monedaLocal.findMonedaByCodigo(
					codigoMoneda).iterator().next();
			data.setMonedaId(moneda.getId());
			// El usuario y el empleado seran el mismo, el que esta haciendo
			// el presupuesto
			data.setEmpleadoId( empleadoLocal.getEmpleado(
					usuario.getEmpleadoId()).getId());
			data.setUsuarioId(usuario.getId());

			// Se escoge la primera bodega de la lista.
			if (bodegas.size() > 0)
				data.setBodegaId(((BodegaIf) bodegas.get(0)).getId());

			// Siempre sera compra Local
			data.setLocalimportada("L");
			// Las fechas todas seran la misma, la actual
			data.setFecha(new java.sql.Date(presupuesto.getFecha().getTime()));
			data.setFechaRecepcion(new java.sql.Date(presupuesto.getFecha().getTime()));
			data.setFechaVencimiento(new java.sql.Date(presupuesto.getFecha().getTime()));
			
			// El estado siempre sera orden "Enviada"
			//data.setEstado("E");
			
			// El estado inicial desde ahora (abril 2013) sera "Emitida"
			data.setEstado(EstadoOrdenCompra.EMITIDA.getLetra());

			if (presupuesto.getCabecera() != null)
				data.setObservacion(presupuesto.getCabecera());

			data.setSolicitudcompraId(solicitudCompraId);
			data.setIce(new BigDecimal(0));
			data.setOtroImpuesto(new BigDecimal(0));

			// Detalles de la Orden de Compra
			double valor = 0D;
			double descuento = 0D;
			double iva = 0D;

			List<OrdenCompraDetalleIf> dataDetalleVector = new ArrayList<OrdenCompraDetalleIf>();
			for (PresupuestoDetalleIf presupuestoDetalle : listaDetalle) {
				OrdenCompraDetalleData dataDetalle = new OrdenCompraDetalleData();
				dataDetalle.setProductoId(presupuestoDetalle.getProductoId());
				if (presupuestoDetalle.getConcepto() != null) {
					dataDetalle.setDescripcion(presupuestoDetalle.getConcepto());
				}
				
				dataDetalle.setValor(presupuestoDetalle.getPrecioagencia());
				dataDetalle.setCantidad(presupuestoDetalle.getCantidad().longValue());
				valor = valor + (presupuestoDetalle.getPrecioagencia().doubleValue() * presupuestoDetalle.getCantidad().doubleValue());

				double valorDetalle = presupuestoDetalle.getPrecioagencia().doubleValue();
				double porcentajeDescuentoEspecial = presupuestoDetalle.getPorcentajeDescuentoEspecialCompra().doubleValue() / 100;
				valorDetalle = valorDetalle - (valorDetalle * porcentajeDescuentoEspecial);
				
				//Seteo en cada detalle los valores de negociacion directa y comision pura
				dataDetalle.setPorcentajeNegociacionDirecta(presupuestoDetalle.getPorcentajeNegociacionDirecta());
				dataDetalle.setPorcentajeComisionPura(presupuestoDetalle.getPorcentajeComisionPura());
							
				//Solo si Negociacion Directa es menos de 100% y no existe Comsion Pura se setea el valor de descuento proveedor
				//caso contrario este descuento se facturara al Proveedor
				double descuentoDetalle = 0D;
				if(presupuestoDetalle.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(100)) == -1
						&& presupuestoDetalle.getPorcentajeComisionPura().compareTo(new BigDecimal(0)) == 0){
					
					double porcentajeDescuentoAgencia = presupuestoDetalle.getPorcentajeDescuentoAgenciaCompra().doubleValue() / 100;
					descuentoDetalle = valorDetalle * presupuestoDetalle.getCantidad().doubleValue() * porcentajeDescuentoAgencia;
				}	
				
				//Solo si Negociacion Directa es menos de 100% y no existe Comsion Pura se setea el valor de descuentos varios
				//caso contrario este descuento se facturara al Proveedor
				double porcentajeBonificacionCompra = 0D;
				double bonificacionCompra = 0D;
				if(presupuestoDetalle.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(100)) == -1
						&& presupuestoDetalle.getPorcentajeComisionPura().compareTo(new BigDecimal(0)) == 0){
					
					porcentajeBonificacionCompra = presupuestoDetalle.getPorcentajeDescuentosVariosCompra().doubleValue();
					bonificacionCompra = valorDetalle * presupuestoDetalle.getCantidad().doubleValue() * (porcentajeBonificacionCompra / 100);
				}
				
				//double porcentajeBonificacionCompra = presupuestoDetalle.getPorcentajeDescuentosVariosCompra().doubleValue() / 100;
				//double bonificacionCompra = valorDetalle * presupuestoDetalle.getCantidad().doubleValue() * porcentajeBonificacionCompra; 
								
				dataDetalle.setDescuentoAgenciaCompra(BigDecimal.valueOf(descuentoDetalle));

				ProductoIf producto = productoLocal.getProducto(presupuestoDetalle.getProductoId());
				GenericoIf generico = genericoLocal.getGenerico(producto.getGenericoId());
				
				
				double ivaDetalle = 0D;
				if ("S".equals(generico.getCobraIva()))
					ivaDetalle = ((valorDetalle * presupuestoDetalle.getCantidad().doubleValue()) - descuentoDetalle - bonificacionCompra) * IVA;
				
				dataDetalle.setIva(BigDecimal.valueOf(ivaDetalle));

				dataDetalle.setIce(new BigDecimal(0));
				dataDetalle.setOtroImpuesto(new BigDecimal(0));
				dataDetalleVector.add(dataDetalle);

				descuento = descuento + descuentoDetalle;
				iva = iva + ivaDetalle;
				
				dataDetalle.setPorcentajeDescuentosVariosCompra(new BigDecimal(porcentajeBonificacionCompra));
				dataDetalle.setPorcentajeDescuentoEspecial(presupuestoDetalle.getPorcentajeDescuentoEspecialCompra());
				dataDetalle.setFechaPublicacion(presupuestoDetalle.getFechaPublicacion());
				
				data.setPorcentajeDescuentosVariosCompra(new BigDecimal(porcentajeBonificacionCompra));
				data.setPorcentajeDescuentoEspecial(presupuestoDetalle.getPorcentajeDescuentoEspecialCompra());
				data.setPorcentajeDescuentosVariosVenta(presupuestoDetalle.getPorcentajeDescuentosVariosVenta());
				
				//Relaciono en mapa presupuestoDetalle con OrdenCompra
				//donde varios detalles pueden corresponder a una misma
				//orden de compra
			}

			data.setValor(BigDecimal.valueOf(valor));
			data.setDescuentoAgenciaCompra(BigDecimal.valueOf(descuento));
			data.setIva(BigDecimal.valueOf(iva));			
						
			ordenesCompraMap.put(data, dataDetalleVector);
			mapaPresupuestoOrden.put(data,listaDetalle);
		}

		//Guardo las Ordenes de Compra
		//Iterator ordenesCompraMapIt = ordenesCompraMap.keySet().iterator();
		//while (ordenesCompraMapIt.hasNext()) {
		for (OrdenCompraIf orden : ordenesCompraMap.keySet() ){
			OrdenCompraIf ordenCompra = null;
			List<OrdenCompraDetalleIf> ordenesDetalle = ordenesCompraMap.get(orden);
			if ( orden.getId() == null ){
				orden.setRevision("01");
				Map<String,OrdenCompraIf> mapaOrdenesCreadas = (Map<String,OrdenCompraIf>)
					mapaResultados.get("ordenesComprasCreadas");
				ordenCompra = ordenCompraLocal.procesarOrdenCompra(
					orden,ordenesDetalle, empresaIf.getId(), 0L);
				String mensaje = "Orden de Compra: "+ ordenCompra.getCodigo() + " guardada con éxito";
				mapaOrdenesCreadas.put(mensaje,ordenCompra);
			} else if(!orden.getEstado().equals(EstadoOrdenCompra.INGRESADA.getLetra()) && esActualizacion) {
				
				//se cambia el estado a enviada
				//orden.setEstado(EstadoOrdenCompra.ENVIADA.getLetra());
				//El estado desde ahora (abril 2013) no se cambia al actualizar
				
				//aumento en uno el número de la revision
				String revision = "";
				int revisionNumero = Integer.valueOf(orden.getRevision());
				revisionNumero = revisionNumero + 1;
				if(revisionNumero < 10){
					revision = "0" + String.valueOf(revisionNumero);
				}else{
					revision = String.valueOf(revisionNumero);
				}
				orden.setRevision(revision);
				
				Map<String,OrdenCompraIf> mapaOrdenesModificadas = (Map<String,OrdenCompraIf>)
					mapaResultados.get("ordenesComprasModificadas");
				ArrayList<OrdenCompraDetalleIf> detalleAReemplazar = (ArrayList<OrdenCompraDetalleIf> ) 
					ordenCompraDetalleLocal.findOrdenCompraDetalleByOrdencompraId(orden.getId());
				ordenCompra = ordenCompraLocal.actualizarOrdenCompra(
					orden, ordenesDetalle, detalleAReemplazar, 0L);
				String mensaje = "Orden de Compra: "+ ordenCompra.getCodigo() + " actualizada con éxito";
				mapaOrdenesModificadas.put(mensaje,ordenCompra);
				
			}	
			
			//Se pone el id de la ordenCompra en el presupuestoDetalle
			if(ordenCompra != null){
				Collection<PresupuestoDetalleIf> detallesPrespuesto = mapaPresupuestoOrden.get(orden);
				for ( PresupuestoDetalleIf pd : detallesPrespuesto ){
					PresupuestoDetalleIf pdExistente = presupuestoDetalleLocal.getPresupuestoDetalle(pd.getId());
					pdExistente.setOrdenCompraId(ordenCompra.getId());
				}
			}			
		}		
	}
	
	private ClienteOficinaIf verificarMapaProveedores(Map<Long, ClienteOficinaIf> listaProveedoresMap,Long idProveedor) throws GenericBusinessException{
		ClienteOficinaIf proveedor = listaProveedoresMap.get(idProveedor);
		if ( proveedor == null ){
			proveedor = clienteOficinaLocal.getClienteOficina(idProveedor);
			listaProveedoresMap.put(proveedor.getId(), proveedor);
		}
		return proveedor;
	}
	
	private OrdenCompraIf verificarMapaOrdenesCompra(Map<Long, OrdenCompraIf> mapaOrdenesCompra,Long idOrdenCompra) throws GenericBusinessException{
		OrdenCompraIf proveedor = mapaOrdenesCompra.get(idOrdenCompra);
		if ( proveedor == null ){
			proveedor = ordenCompraLocal.getOrdenCompra(idOrdenCompra);
			if (proveedor != null)
				mapaOrdenesCompra.put(proveedor.getId(), proveedor);
		}
		return proveedor;
	}

	private Long procesarSolicitudCompra(Long idSolicitud,
			UsuarioIf usuario,Collection<PresupuestoDetalleIf> modelDetalleList, 
			Long oficinaId,PresupuestoIf presupuesto, EmpresaIf empresaIf, 
			List bodegas)
			throws GenericBusinessException {
		// SOLICITUD DE COMPRA
		SolicitudCompraIf dataSolicitud = null;
		if ( idSolicitud == null )
			dataSolicitud = new SolicitudCompraEJB();
		else 
			dataSolicitud = solicitudCompraLocal.getSolicitudCompra(idSolicitud);
		String codigoSolicitud = getCodigo(new java.sql.Date(
				new java.util.Date().getTime()));
		dataSolicitud.setCodigo(codigoSolicitud);
		dataSolicitud.setOficinaId(oficinaId);
		dataSolicitud.setFecha(new java.sql.Date(presupuesto.getFecha().getTime()));
		dataSolicitud.setUsuarioId(usuario.getId());
		dataSolicitud.setEmpleadoId(empleadoLocal.getEmpleado(usuario.getEmpleadoId()).getId());
		
		// Se escoge la primera bodega de la lista.
		if (bodegas.size() > 0)
			dataSolicitud.setBodegaId(((BodegaIf) bodegas.get(0)).getId());

		dataSolicitud.setFechaEntrega(new java.sql.Date(presupuesto.getFecha().getTime()));
		// La referencia en este caso siempre va a ser un presupuesto
		dataSolicitud.setTipoReferencia("P");
		dataSolicitud.setReferencia(presupuesto.getCodigo());
		// El estado siempre sera "Activo"
		dataSolicitud.setEstado("A");

		if (presupuesto.getCabecera() != null)
			dataSolicitud.setObservacion(presupuesto.getCabecera());

		List<SolicitudCompraDetalleIf> dataSolicitudDetalleVector = new ArrayList<SolicitudCompraDetalleIf>();

		for (PresupuestoDetalleIf presupuestoDetalle : modelDetalleList) {
			SolicitudCompraDetalleData dataSolicitudDetalle = new SolicitudCompraDetalleData();
			dataSolicitudDetalle.setProductoId(presupuestoDetalle.getProductoId());
			dataSolicitudDetalle.setCantidad(presupuestoDetalle.getCantidad());
			dataSolicitudDetalleVector.add(dataSolicitudDetalle);
		}
		
		Long solicitudCompraId = null;
		// Guardo la solicitud de compra
		if ( idSolicitud == null ){
			SolicitudCompraIf solicitudCompra = solicitudCompraLocal
				.procesarSolicitudCompra(dataSolicitud,dataSolicitudDetalleVector,empresaIf.getId());
			solicitudCompraId = solicitudCompra.getId();
		} else {
			List<SolicitudCompraDetalleIf> detalleExistente = 
				(ArrayList<SolicitudCompraDetalleIf>) solicitudCompraDetalleLocal
				.findSolicitudCompraDetalleBySolicitudcompraId(idSolicitud);
			solicitudCompraLocal
				.actualizarSolicitudCompra(dataSolicitud, dataSolicitudDetalleVector, 
					detalleExistente, 0L);
			solicitudCompraId = idSolicitud;
		}
		return solicitudCompraId;
	}
	
	private String getCodigo(java.sql.Date fecha) {
		String codigo = "";
		String anio = Utilitarios.getYearFromDate(fecha);
		codigo += anio + "-";
		return codigo;
	}
	
	
	private PresupuestoEJB registrarPresupuesto(PresupuestoIf model) {
		PresupuestoEJB presupuesto = new PresupuestoEJB();
		
		presupuesto.setId(model.getId());
		presupuesto.setCodigo(model.getCodigo());
		presupuesto.setOrdentrabajodetId(model.getOrdentrabajodetId());
		presupuesto.setClienteCondicionId(model.getClienteCondicionId());
		presupuesto.setPlanmedioId(model.getPlanmedioId());
		presupuesto.setConcepto(model.getConcepto());
		presupuesto.setModificacion(model.getModificacion());
		presupuesto.setFecha(model.getFecha());
		presupuesto.setFechaValidez(model.getFechaValidez());
		presupuesto.setFechaEnvio(model.getFechaEnvio());
		presupuesto.setFechaCancelacion(model.getFechaCancelacion());
		presupuesto.setFechaAceptacion(model.getFechaAceptacion());
		presupuesto.setValorbruto(model.getValorbruto());
		presupuesto.setDescuento(model.getDescuento());
		presupuesto.setValor(model.getValor());
		presupuesto.setIva(model.getIva());
		presupuesto.setCabecera(model.getCabecera());
		presupuesto.setDiasValidez(model.getDiasValidez());
		presupuesto.setFormaPagoId(model.getFormaPagoId());
		presupuesto.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
		presupuesto.setEstado(model.getEstado());
		presupuesto.setUsuarioCreacionId(model.getUsuarioCreacionId());
		presupuesto.setFechaCreacion(model.getFechaCreacion());
		presupuesto.setUsuarioActualizacionId(model.getUsuarioActualizacionId());
		presupuesto.setFechaActualizacion(model.getFechaActualizacion());
		presupuesto.setTemaCampana(model.getTemaCampana());
		presupuesto.setAutorizacionSap(model.getAutorizacionSap());
		presupuesto.setDescuentosVarios(model.getDescuentosVarios());
		presupuesto.setDescuentoEspecial(model.getDescuentoEspecial());
		
		//seteo clienteOficinaId
		try {
			OrdenTrabajoDetalleIf ordenTrabajoDetalle = ordenTrabajoDetalleLocal.getOrdenTrabajoDetalle(model.getOrdentrabajodetId());
			if(ordenTrabajoDetalle != null){
				OrdenTrabajoIf ordenTrabajo = ordenTrabajoLocal.getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
				if(ordenTrabajo != null){
					presupuesto.setClienteOficinaId(ordenTrabajo.getClienteoficinaId());
				}
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		if(model.getPrepago() != null){
			presupuesto.setPrepago(model.getPrepago());
		}else{
			presupuesto.setPrepago("N");
		}		
		
		presupuesto.setReferenciaId(model.getReferenciaId());
		presupuesto.setTipoReferencia(model.getTipoReferencia());
		
		return presupuesto;
	}
	
	private PresupuestoDetalleIf registrarPresupuestoDetalle(PresupuestoDetalleIf modelDetalle) {
		PresupuestoDetalleEJB presupuestoDetalle = new PresupuestoDetalleEJB();
		
		presupuestoDetalle.setId(modelDetalle.getId());
		presupuestoDetalle.setPresupuestoId(modelDetalle.getPresupuestoId());
		presupuestoDetalle.setProductoId(modelDetalle.getProductoId());
		presupuestoDetalle.setConcepto(modelDetalle.getConcepto());
		presupuestoDetalle.setCantidad(modelDetalle.getCantidad());
		presupuestoDetalle.setPrecioagencia(modelDetalle.getPrecioagencia());
		presupuestoDetalle.setPrecioventa(modelDetalle.getPrecioventa());
		presupuestoDetalle.setIva(modelDetalle.getIva());
		presupuestoDetalle.setProveedorId(modelDetalle.getProveedorId());
		presupuestoDetalle.setIvaCompra(modelDetalle.getIvaCompra());
		presupuestoDetalle.setReporte(modelDetalle.getReporte());
		presupuestoDetalle.setOrden(modelDetalle.getOrden());
		presupuestoDetalle.setOrdenCompraId(modelDetalle.getOrdenCompraId());
		
		//compra
		if(modelDetalle.getPorcentajeDescuentoEspecialCompra() != null){
			presupuestoDetalle.setPorcentajeDescuentoEspecialCompra(modelDetalle.getPorcentajeDescuentoEspecialCompra());
		}else{
			presupuestoDetalle.setPorcentajeDescuentoEspecialCompra(new BigDecimal(0));
		}
		
		if(modelDetalle.getPorcentajeDescuentoAgenciaCompra() != null){
			presupuestoDetalle.setPorcentajeDescuentoAgenciaCompra(modelDetalle.getPorcentajeDescuentoAgenciaCompra());
		}else{
			presupuestoDetalle.setPorcentajeDescuentoAgenciaCompra(new BigDecimal(0));
		}
		
		if(modelDetalle.getPorcentajeDescuentosVariosCompra() != null){
			presupuestoDetalle.setPorcentajeDescuentosVariosCompra(modelDetalle.getPorcentajeDescuentosVariosCompra());
		}else{
			presupuestoDetalle.setPorcentajeDescuentosVariosCompra(new BigDecimal(0));
		}
		
		if(modelDetalle.getPorcentajeNotaCreditoCompra() != null){
			presupuestoDetalle.setPorcentajeNotaCreditoCompra(modelDetalle.getPorcentajeNotaCreditoCompra());
		}else{
			presupuestoDetalle.setPorcentajeNotaCreditoCompra(new BigDecimal(0));
		}
		
		//venta
		if(modelDetalle.getPorcentajeDescuentoEspecialVenta() != null){
			presupuestoDetalle.setPorcentajeDescuentoEspecialVenta(modelDetalle.getPorcentajeDescuentoEspecialVenta());
		}else{
			presupuestoDetalle.setPorcentajeDescuentoEspecialVenta(new BigDecimal(0));
		}
		
		if(modelDetalle.getPorcentajeDescuentoAgenciaVenta() != null){
			presupuestoDetalle.setPorcentajeDescuentoAgenciaVenta(modelDetalle.getPorcentajeDescuentoAgenciaVenta());
		}else{
			presupuestoDetalle.setPorcentajeDescuentoAgenciaVenta(new BigDecimal(0));
		}
		
		if(modelDetalle.getPorcentajeDescuentosVariosVenta() != null){
			presupuestoDetalle.setPorcentajeDescuentosVariosVenta(modelDetalle.getPorcentajeDescuentosVariosVenta());
		}else{
			presupuestoDetalle.setPorcentajeDescuentosVariosVenta(new BigDecimal(0));
		}	
		
		//negociaciones
		if(modelDetalle.getPorcentajeNegociacionDirecta() != null){
			presupuestoDetalle.setPorcentajeNegociacionDirecta(modelDetalle.getPorcentajeNegociacionDirecta());
		}else{
			presupuestoDetalle.setPorcentajeNegociacionDirecta(new BigDecimal(0));
		}
		
		if(modelDetalle.getPorcentajeComisionPura() != null){
			presupuestoDetalle.setPorcentajeComisionPura(modelDetalle.getPorcentajeComisionPura());
		}else{
			presupuestoDetalle.setPorcentajeComisionPura(new BigDecimal(0));
		}
		
		if(modelDetalle.getPorcentajeComisionAdicional() != null){
			presupuestoDetalle.setPorcentajeComisionAdicional(modelDetalle.getPorcentajeComisionAdicional());
		}else{
			presupuestoDetalle.setPorcentajeComisionAdicional(new BigDecimal(0));
		}
		
		presupuestoDetalle.setFechaPublicacion(modelDetalle.getFechaPublicacion());
		
		return presupuestoDetalle;
	}
	
	public Collection findPresupuestosByQueryByFechaInicioAndByFechaFin(Map aMap,
			Date fechaInicio, Date fechaFin) throws GenericBusinessException {
		
		String objectName = "p";
		String where = "";
		String estados = "";
		Long clienteOficinaId = 0L;
		if (!aMap.isEmpty()) {
			if(aMap.get("clienteoficinaId")!=null){
				clienteOficinaId = (Long)aMap.get("clienteoficinaId");
				aMap.remove("clienteoficinaId");
			}
			if(aMap.get("versus")!=null){
				estados = " (p.estado = 'A' or p.estado = 'F') ";
				aMap.remove("versus");
			}
			if(aMap.get("estado") != null){
				where = QueryBuilder.buildWhere(aMap, objectName) + " and";
			}
		}
		
		String queryString = "select distinct p from PresupuestoEJB "
			+ objectName
			+ ", OrdenTrabajoDetalleEJB otd, OrdenTrabajoEJB ot, ClienteOficinaEJB clo, ClienteEJB cl where "
			+ where
			+ " p.ordentrabajodetId = otd.id and otd.ordenId = ot.id and ot.clienteoficinaId = clo.id and clo.clienteId = cl.id and p.fecha between :fechaInicio and :fechaFin";
		
		if(clienteOficinaId != 0L){
			queryString = queryString + " and clo.id = " + clienteOficinaId + "";
		}
		
		if(!estados.equals("")){
			queryString = queryString + " and " + estados;
		}
		
		String orderByPart = "";
		orderByPart += " order by cl.nombreLegal asc, p.codigo desc";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}

		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<PresupuestoIf> findPresupuestoByOrdenCompraId(Long ordenCompraId) throws GenericBusinessException {
		// SELECT DISTINCT P.* FROM PRESUPUESTO P, SOLICITUD_COMPRA SC, ORDEN_COMPRA OC WHERE SC.TIPO_REFERENCIA = 'P' AND SC.REFERENCIA = P.CODIGO AND SC.ID = OC.SOLICITUDCOMPRA_ID AND OC.ID = 2796
		String queryString = "select distinct p from PresupuestoEJB p, SolicitudCompraEJB sc, OrdenCompraEJB oc where sc.tipoReferencia = 'P' and sc.referencia = p.codigo and sc.id = oc.solicitudcompraId and oc.id = :ordenCompraId";
		Query query = manager.createQuery(queryString);
		query.setParameter("ordenCompraId", ordenCompraId);
		return query.getResultList();
	}
	
	/*
	 select distinct pr.CODIGO, pr.FECHA, pr.FECHA_ACEPTACION 
	from PRESUPUESTO_DETALLE prd, PRESUPUESTO pr, PRESUPUESTO_PRODUCTO pp, ORDEN_TRABAJO_DETALLE otd, 
	ORDEN_TRABAJO ot, CLIENTE_OFICINA clo, CLIENTE cl, PRODUCTO_CLIENTE pc, MARCA_PRODUCTO mp, OFICINA ofi, 
	CLIENTE_OFICINA provOfi, CLIENTE prov, TIPO_PROVEEDOR tp, CAMPANA cam 
	where prd.presupuesto_Id = pr.id and pr.ordentrabajodet_Id = otd.id and otd.orden_Id = ot.id 
	and ot.clienteoficina_Id = clo.id and clo.cliente_Id = cl.id and pp.presupuesto_Id = pr.id 
	and pp.producto_Cliente_Id = pc.id and pc.marca_Producto_Id = mp.id and ot.oficina_Id = ofi.id 
	and provOfi.cliente_Id = prov.id and prd.proveedor_Id = provOfi.id and prov.tipoproveedor_Id = tp.id 
	and ot.campana_Id = cam.id and ofi.empresa_Id = 1 and pr.estado <> 'C'  and pr.fecha_Aceptacion 
	between '2012-01-19' and '2012-01-20' and tp.tipo = 'M' 
	order by pr.CODIGO
	 */
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPresupuestosDetalleByQueryByProductoAndByFechas(
			Map<String,Long> mapaOrdenesCompraDetalle, Timestamp fechaInicio, Timestamp fechaFin, 
			boolean fechaAprobacion, Long empresaId, String agruparBy, String estado, String tipoProveedor, 
			boolean aprobadosFacturados, boolean buscarPresupuestosPrepago, String estadoOrden, boolean noMostrarOrdenesEmitidas) {
		
		/*select distinct clo.descripcion, clo.id, provOfi.descripcion, provOfi.id, pc.nombre, pc.id, tp.nombre, tp.id, 
		pr.fecha, prd.precioventa, prd.cantidad, prd.porcentaje_Descuento_Agencia_Venta, pr.porcentaje_Comision_Agencia,
		mp.nombre, mp.id, cam.id, pr.id, prov.nombre_Legal, prov.id, cam.nombre, pr.usuario_Creacion_Id, 
		pr.usuario_Actualizacion_Id, prd.porcentaje_Descuento_Especial_Venta, prd.porcentaje_Descuentos_Varios_Venta, 
		prd.orden_Compra_Id, prd.id, oc.*, prd.producto_Id 
		from PRESUPUESTO_DETALLE prd, PRESUPUESTO pr, PRESUPUESTO_PRODUCTO pp, ORDEN_TRABAJO_DETALLE otd, ORDEN_TRABAJO ot, 
		CLIENTE_OFICINA clo, CLIENTE cl, PRODUCTO_CLIENTE pc, MARCA_PRODUCTO mp, OFICINA ofi, CLIENTE_OFICINA provOfi, 
		CLIENTE prov, TIPO_PROVEEDOR tp, CAMPANA cam, ORDEN_COMPRA oc 
		where prd.presupuesto_id = pr.id and pr.ordentrabajodet_Id = otd.id and otd.orden_Id = ot.id 
		and ot.clienteoficina_Id = clo.id and clo.cliente_Id = cl.id and pp.presupuesto_Id = pr.id 
		and pp.producto_Cliente_Id = pc.id and pc.marca_Producto_Id = mp.id and ot.oficina_Id = ofi.id 
		and provOfi.cliente_Id = prov.id and prd.proveedor_Id = provOfi.id and prov.tipoproveedor_Id = tp.id 
		and ot.campana_Id = cam.id and ofi.empresa_Id = 1 and pr.estado <> 'C' and prd.orden_Compra_Id = oc.id 
		and oc.estado <> 'A'  and oc.estado <> 'M'  and clo.id = 115 and pr.fecha between '2013-01-14' and '2013-01-14'  
		and tp.tipo = 'M'  and pr.prepago = 'N'  order by tp.nombre, prov.nombre_Legal, cl.nombre_Legal, pc.nombre
		*/
		
		String datosRequeridos = "";
		
		//Es muy importante que en datos requeridos este prd.ordenCompraId porque si no el "distinct"
		//hace que no aparezcan datos repetidos y si hay casos de prensa donde todo es igual execto el id de la orden
		
		if (agruparBy.compareTo(AGRUPAR_POR_CLIENTE_TMEDIOS_MEDIOS_PRODUCTO) == 0){
			
			datosRequeridos = "cl.nombreLegal, cl.id, prov.nombreLegal, prov.id, pc.nombre, pc.id, " +
					"tp.nombre, tp.id, pr.fecha, prd.precioventa, prd.cantidad, prd.porcentajeDescuentoAgenciaVenta, " +
					"pr.porcentajeComisionAgencia, cl.identificacion, mp.nombre, mp.id, ot.campanaId, pr.usuarioCreacionId, " +
					"pr.usuarioActualizacionId, prd.porcentajeDescuentoEspecialVenta, prd.porcentajeDescuentosVariosVenta, prd.ordenCompraId, pr.id";
		}else{
			//los datos pr.id sirven para el reporte de facturacion "Inversion Clientes"
			datosRequeridos = "clo.descripcion, clo.id, provOfi.descripcion, provOfi.id, pc.nombre, pc.id, " +
					"tp.nombre, tp.id, pr.fecha, prd.precioventa, prd.cantidad, prd.porcentajeDescuentoAgenciaVenta, " +
					"pr.porcentajeComisionAgencia, mp.nombre, mp.id, cam.id, pr.id, prov.nombreLegal, prov.id, cam.nombre, " +
					"pr.usuarioCreacionId, pr.usuarioActualizacionId, prd.porcentajeDescuentoEspecialVenta, prd.porcentajeDescuentosVariosVenta, prd.ordenCompraId, prd.id, oc, prd.productoId";
		}
		
		String queryString = "select distinct " + datosRequeridos + " " +
				"from PresupuestoDetalleEJB prd, PresupuestoEJB pr, PresupuestoProductoEJB pp, " +
				"OrdenTrabajoDetalleEJB otd, OrdenTrabajoEJB ot, ClienteOficinaEJB clo, ClienteEJB cl, ProductoClienteEJB pc, MarcaProductoEJB mp, OficinaEJB ofi, " +
				"ClienteOficinaEJB provOfi, ClienteEJB prov, TipoProveedorEJB tp, CampanaEJB cam, OrdenCompraEJB oc " +
				"where prd.presupuestoId = pr.id and pr.ordentrabajodetId = otd.id " +
				"and otd.ordenId = ot.id and ot.clienteoficinaId = clo.id and clo.clienteId = cl.id and pp.presupuestoId = pr.id and pp.productoClienteId = pc.id " +
				"and pc.marcaProductoId = mp.id and ot.oficinaId = ofi.id and provOfi.clienteId = prov.id and prd.proveedorId = provOfi.id and prov.tipoproveedorId = tp.id " +
				"and ot.campanaId = cam.id and ofi.empresaId = " + empresaId + " and pr.estado <> 'C' and prd.ordenCompraId = oc.id and oc.estado <> 'A' "; //and (prd.ordenCompraId = oc.id or prd.ordenCompraId is null) and oc.estado <> 'A' 
		
		if(noMostrarOrdenesEmitidas){
			queryString += " and oc.estado <> 'M' ";
		}
				
		//estados de las ordenes
		if(!estadoOrden.equals("") && !estadoOrden.equals("TODOS")){
			//EMITIDO = "M", ORDENADO = "D", ENVIADO = "E", INGRESADO = "I", PREPAGADO = "R"
			if(estadoOrden.equals("EMITIDA")){
				queryString += " and oc.estado = 'M' ";
			}else if(estadoOrden.equals("ORDENADA")){
				queryString += " and oc.estado = 'D' ";
			}else if(estadoOrden.equals("ENVIADA")){
				queryString += " and oc.estado = 'E' ";
			}else if(estadoOrden.equals("INGRESADA")){
				queryString += " and oc.estado = 'G' ";
			}else if(estadoOrden.equals("PREPAGADA")){
				queryString += " and oc.estado = 'R' ";
			}
		} 		
		
		if(mapaOrdenesCompraDetalle.get("clienteOficinaId") != null){
			queryString += " and clo.id = " + mapaOrdenesCompraDetalle.get("clienteOficinaId") + "";
		}
		if(mapaOrdenesCompraDetalle.get("clienteId") != null){
			queryString += " and cl.id = " + mapaOrdenesCompraDetalle.get("clienteId") + "";
		}
		if(mapaOrdenesCompraDetalle.get("campanaId") != null){
			queryString += " and cam.id = " + mapaOrdenesCompraDetalle.get("campanaId") + "";
		}
		if(mapaOrdenesCompraDetalle.get("proveedorOficinaId") != null){
			queryString += " and prd.proveedorId = " + mapaOrdenesCompraDetalle.get("proveedorOficinaId") + "";
		}
		if(mapaOrdenesCompraDetalle.get("proveedorId") != null){
			queryString += " and prd.proveedorId in (select clof.id from ClienteOficinaEJB clof, ClienteEJB cli " +
					"where clof.clienteId = cli.id and cli.id = " + mapaOrdenesCompraDetalle.get("proveedorId") + " )";
		}
		if(mapaOrdenesCompraDetalle.get("productoClienteId") != null){
			queryString += " and pc.id = " + mapaOrdenesCompraDetalle.get("productoClienteId") + "";
		}
		if(mapaOrdenesCompraDetalle.get("marcaProductoId") != null){
			queryString += " and mp.id = " + mapaOrdenesCompraDetalle.get("marcaProductoId") + "";
		}
		
		if(mapaOrdenesCompraDetalle.get("tipo") != null){											
			queryString += " and prd.proveedorId in (select clofi.id from ClienteOficinaEJB clofi, ClienteEJB clie, TipoProveedorEJB tp " +
				"where clofi.clienteId = clie.id and clie.tipoproveedorId = tp.id and tp.tipo = '" + mapaOrdenesCompraDetalle.get("tipo") + "' )";
		}
			
		if(mapaOrdenesCompraDetalle.get("tipoProveedorId") != null){
			queryString += " and prd.proveedorId in (select clofi.id from ClienteOficinaEJB clofi, ClienteEJB clie " +
					"where clofi.clienteId = clie.id and clie.tipoproveedorId = " + mapaOrdenesCompraDetalle.get("tipoProveedorId") + " )";
		}
		
		if(mapaOrdenesCompraDetalle.get("tipoProductoId") != null){
			queryString += " and prd.productoId in (select ptemp.id from ProductoEJB ptemp, GenericoEJB gtemp " +
					"where ptemp.genericoId = gtemp.id and gtemp.tipoproductoId = " + mapaOrdenesCompraDetalle.get("tipoProductoId") + " )";
		}
		
		if(mapaOrdenesCompraDetalle.get("empleadoId") != null){
			queryString += " and oc.empleadoId = " + mapaOrdenesCompraDetalle.get("empleadoId") + "";
		}
		if(mapaOrdenesCompraDetalle.get("codigoUnicoPresupuesto") != null){
			queryString += " and pr.codigo = '" + mapaOrdenesCompraDetalle.get("codigoUnicoPresupuesto") + "'";
		}
		
		if(!estado.equals("") && !estado.equals("TODOS")){
			//MEDIOS, EN PROCESO = 'N', PENDIENTE = 'P', APROBADO = 'A', PEDIDO = 'D', FACTURADO = 'F'
			//PRESUPUESTO, COTIZADO = 'T', PENDIENTE = 'P', APROBADO = 'A', FACTURADO = 'F'
			if(estado.equals("COTIZADO")){
				queryString += " and (pr.estado = 'T' or pr.estado = 'P') ";
			}else if(estado.equals("APROBADO")){
				queryString += " and pr.estado = 'A' ";
			}else if(estado.equals("FACTURADO")){
				queryString += " and pr.estado = 'F' ";
			}else if(estado.equals("PREPAGADO")){
				queryString += " and pr.estado = 'R' ";
			}
		}else if(!estado.equals("") && estado.equals("TODOS") && aprobadosFacturados){
			queryString += " and (pr.estado = 'A' or pr.estado = 'F') ";
		}
		
		if(fechaAprobacion){
			queryString += " and pr.fechaAceptacion between :fechaInicio and :fechaFin ";
		}else{
			queryString += " and pr.fecha between :fechaInicio and :fechaFin ";
		}
		
		if(!tipoProveedor.equals("") && !tipoProveedor.equals("TODOS")){
			if(tipoProveedor.equals("MEDIOS")){
				queryString += " and tp.tipo = 'M' ";
			}else if(tipoProveedor.equals("PRODUCCION")){
				queryString += " and tp.tipo = 'P' ";
			}
		}
		
		if(!buscarPresupuestosPrepago){
			queryString += " and pr.prepago = 'N' ";
		}
		
		if (agruparBy.compareTo(AGRUPAR_POR_CLIENTE_TMEDIOS_MEDIOS_PRODUCTO) == 0){
			queryString = queryString + " order by cl.nombreLegal, tp.nombre, prov.nombreLegal, pc.nombre";
		} else if (agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO) == 0){
			queryString = queryString + " order by tp.nombre, prov.nombreLegal, cl.nombreLegal, pc.nombre";
		}else{
			queryString = queryString + " order by clo.descripcion, pc.nombre, tp.nombre, provOfi.descripcion";
		}
		
		
		//queryString += " order by oc.codigo asc";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		
		//presupuestos sin ordenes
		
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findPresupuestoDetalleByClienteByCodigoProductoProveedorByFechaInicioFechaFinAndByEstado(Long clienteId, Long clienteOficinaId, 
			String codigoProductoProveedor, Timestamp fechaInicio, Timestamp fechaFin, String estado, boolean estadosAprobadosFacturados) {

		String queryString = "select distinct pr, prd from PresupuestoDetalleEJB prd, PresupuestoEJB pr, ClienteOficinaEJB clo, ClienteEJB cl, ProductoEJB p, "
				+ "GenericoEJB g, TipoProductoEJB tp where prd.presupuestoId = pr.id and pr.clienteOficinaId = clo.id and clo.clienteId = cl.id and "
				+ "prd.productoId  = p.id and p.genericoId = g.id and g.tipoproductoId = tp.id and tp.codigo = '" + codigoProductoProveedor + "' and pr.estado <> 'C' "
				+ " and pr.fecha between :fechaInicio and :fechaFin ";
				  
		if(clienteOficinaId != null && clienteOficinaId.compareTo(0L) == 1){
			queryString += " and clo.id = " + clienteOficinaId + "";
		}else if(clienteId != null && clienteId.compareTo(0L) == 1){
			queryString += " and cl.id = " + clienteId + "";
		}
		
		if(!estado.equals("") && !estado.equals("TODOS")){
			//MEDIOS, EN PROCESO = 'N', PENDIENTE = 'P', APROBADO = 'A', PEDIDO = 'D', FACTURADO = 'F'
			//PRESUPUESTO, COTIZADO = 'T', PENDIENTE = 'P', APROBADO = 'A', FACTURADO = 'F'
			if(estado.equals("COTIZADO")){
				queryString += " and (pr.estado = 'T' or pr.estado = 'P') ";
			}else if(estado.equals("APROBADO")){
				queryString += " and pr.estado = 'A' ";
			}else if(estado.equals("FACTURADO")){
				queryString += " and pr.estado = 'F' ";
			}else if(estado.equals("PREPAGADO")){
				queryString += " and pr.estado = 'R' ";
			}
		}else if(!estado.equals("") && estado.equals("TODOS") && estadosAprobadosFacturados){
			queryString += " and (pr.estado = 'A' or pr.estado = 'F') ";
		}
				
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findPresupuestoDetalleByClienteOficinaByOrdenIdNullByFechaInicioFechaFinAndByEstado(Long clienteOficinaId, Timestamp fechaInicio, Timestamp fechaFin, String estado, boolean estadosAprobadosFacturados) {

		String queryString = "select distinct pr, prd from PresupuestoDetalleEJB prd, PresupuestoEJB pr "
				+ "where prd.presupuestoId = pr.id and prd.reporte = 'N' and prd.ordenCompraId is null and pr.estado <> 'C' and pr.fecha between :fechaInicio and :fechaFin ";
				  
		if(clienteOficinaId != null && clienteOficinaId.compareTo(0L) == 1){
			queryString += " and pr.clienteOficinaId = " + clienteOficinaId + "";
		}
		
		if(!estado.equals("") && !estado.equals("TODOS")){
			//MEDIOS, EN PROCESO = 'N', PENDIENTE = 'P', APROBADO = 'A', PEDIDO = 'D', FACTURADO = 'F'
			//PRESUPUESTO, COTIZADO = 'T', PENDIENTE = 'P', APROBADO = 'A', FACTURADO = 'F'
			if(estado.equals("COTIZADO")){
				queryString += " and (pr.estado = 'T' or pr.estado = 'P') ";
			}else if(estado.equals("APROBADO")){
				queryString += " and pr.estado = 'A' ";
			}else if(estado.equals("FACTURADO")){
				queryString += " and pr.estado = 'F' ";
			}else if(estado.equals("PREPAGADO")){
				queryString += " and pr.estado = 'R' ";
			}
		}else if(!estado.equals("") && estado.equals("TODOS") && estadosAprobadosFacturados){
			queryString += " and (pr.estado = 'A' or pr.estado = 'F') ";
		}
				
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPresupuestosDetalleByQueryByProductoAndByFechasSinOrdenes(
			Map<String,Long> mapaOrdenesCompraDetalle, Timestamp fechaInicio, Timestamp fechaFin, 
			boolean fechaAprobacion, Long empresaId, String agruparBy, String estado, String tipoProveedor, 
			boolean aprobadosFacturados, boolean buscarPresupuestosPrepago) {
		
		String datosRequeridos = "";
		
		datosRequeridos = "clo.descripcion, clo.id, provOfi.descripcion, provOfi.id, pc.nombre, pc.id, " +
		"tp.nombre, tp.id, pr.fecha, prd.precioventa, prd.cantidad, prd.porcentajeDescuentoAgenciaVenta, " +
		"pr.porcentajeComisionAgencia, mp.nombre, mp.id, cam.id, pr.id, prov.nombreLegal, prov.id, cam.nombre, " +
		"pr.usuarioCreacionId, pr.usuarioActualizacionId, prd.porcentajeDescuentoEspecialVenta, prd.porcentajeDescuentosVariosVenta, prd.id, prd.id, prd.id, prd.productoId";
		
		String queryString = "select distinct " + datosRequeridos + " " +
				"from PresupuestoDetalleEJB prd, PresupuestoEJB pr, PresupuestoProductoEJB pp, " +
				"OrdenTrabajoDetalleEJB otd, OrdenTrabajoEJB ot, ClienteOficinaEJB clo, ClienteEJB cl, ProductoClienteEJB pc, MarcaProductoEJB mp, OficinaEJB ofi, " +
				"ClienteOficinaEJB provOfi, ClienteEJB prov, TipoProveedorEJB tp, CampanaEJB cam " +
				"where prd.presupuestoId = pr.id and pr.ordentrabajodetId = otd.id " +
				"and otd.ordenId = ot.id and ot.clienteoficinaId = clo.id and clo.clienteId = cl.id and pp.presupuestoId = pr.id and pp.productoClienteId = pc.id " +
				"and pc.marcaProductoId = mp.id and ot.oficinaId = ofi.id and provOfi.clienteId = prov.id and prd.proveedorId = provOfi.id and prov.tipoproveedorId = tp.id " +
				"and ot.campanaId = cam.id and ofi.empresaId = " + empresaId + " and pr.estado <> 'C'"; 
		
		if(mapaOrdenesCompraDetalle.get("clienteOficinaId") != null){
			queryString += " and clo.id = " + mapaOrdenesCompraDetalle.get("clienteOficinaId") + "";
		}
		if(mapaOrdenesCompraDetalle.get("clienteId") != null){
			queryString += " and cl.id = " + mapaOrdenesCompraDetalle.get("clienteId") + "";
		}
		if(mapaOrdenesCompraDetalle.get("campanaId") != null){
			queryString += " and cam.id = " + mapaOrdenesCompraDetalle.get("campanaId") + "";
		}
		if(mapaOrdenesCompraDetalle.get("proveedorOficinaId") != null){
			queryString += " and prd.proveedorId = " + mapaOrdenesCompraDetalle.get("proveedorOficinaId") + "";
		}
		if(mapaOrdenesCompraDetalle.get("proveedorId") != null){
			queryString += " and prd.proveedorId in (select clof.id from ClienteOficinaEJB clof, ClienteEJB cli " +
					"where clof.clienteId = cli.id and cli.id = " + mapaOrdenesCompraDetalle.get("proveedorId") + " )";
		}
		if(mapaOrdenesCompraDetalle.get("productoClienteId") != null){
			queryString += " and pc.id = " + mapaOrdenesCompraDetalle.get("productoClienteId") + "";
		}
		if(mapaOrdenesCompraDetalle.get("marcaProductoId") != null){
			queryString += " and mp.id = " + mapaOrdenesCompraDetalle.get("marcaProductoId") + "";
		}
		
		if(mapaOrdenesCompraDetalle.get("tipo") != null){											
			queryString += " and prd.proveedorId in (select clofi.id from ClienteOficinaEJB clofi, ClienteEJB clie, TipoProveedorEJB tp " +
				"where clofi.clienteId = clie.id and clie.tipoproveedorId = tp.id and tp.tipo = '" + mapaOrdenesCompraDetalle.get("tipo") + "' )";
		}
			
		if(mapaOrdenesCompraDetalle.get("tipoProveedorId") != null){
			queryString += " and prd.proveedorId in (select clofi.id from ClienteOficinaEJB clofi, ClienteEJB clie " +
					"where clofi.clienteId = clie.id and clie.tipoproveedorId = " + mapaOrdenesCompraDetalle.get("tipoProveedorId") + " )";
		}
		
		if(mapaOrdenesCompraDetalle.get("tipoProductoId") != null){
			queryString += " and prd.productoId in (select ptemp.id from ProductoEJB ptemp, GenericoEJB gtemp " +
					"where ptemp.genericoId = gtemp.id and gtemp.tipoproductoId = " + mapaOrdenesCompraDetalle.get("tipoProductoId") + " )";
		}
		
		if(mapaOrdenesCompraDetalle.get("empleadoId") != null){
			queryString += " and oc.empleadoId = " + mapaOrdenesCompraDetalle.get("empleadoId") + "";
		}
		if(mapaOrdenesCompraDetalle.get("codigoUnicoPresupuesto") != null){
			queryString += " and pr.codigo = '" + mapaOrdenesCompraDetalle.get("codigoUnicoPresupuesto") + "'";
		}
		
		if(!estado.equals("") && !estado.equals("TODOS")){
			//MEDIOS, EN PROCESO = 'N', PENDIENTE = 'P', APROBADO = 'A', PEDIDO = 'D', FACTURADO = 'F'
			//PRESUPUESTO, COTIZADO = 'T', PENDIENTE = 'P', APROBADO = 'A', FACTURADO = 'F'
			if(estado.equals("COTIZADO")){
				queryString += " and (pr.estado = 'T' or pr.estado = 'P') ";
			}else if(estado.equals("APROBADO")){
				queryString += " and pr.estado = 'A' ";
			}else if(estado.equals("FACTURADO")){
				queryString += " and pr.estado = 'F' ";
			}else if(estado.equals("PREPAGADO")){
				queryString += " and pr.estado = 'R' ";
			}
		}else if(!estado.equals("") && estado.equals("TODOS") && aprobadosFacturados){
			queryString += " and (pr.estado = 'A' or pr.estado = 'F') ";
		}
		
		if(fechaAprobacion){
			queryString += " and pr.fechaAceptacion between :fechaInicio and :fechaFin ";
		}else{
			queryString += " and pr.fecha between :fechaInicio and :fechaFin ";
		}
		
		if(!tipoProveedor.equals("") && !tipoProveedor.equals("TODOS")){
			if(tipoProveedor.equals("MEDIOS")){
				queryString += " and tp.tipo = 'M' ";
			}else if(tipoProveedor.equals("PRODUCCION")){
				queryString += " and tp.tipo = 'P' ";
			}
		}
		
		if(!buscarPresupuestosPrepago){
			queryString += " and pr.prepago = 'N' ";
		}
		
		if (agruparBy.compareTo(AGRUPAR_POR_CLIENTE_TMEDIOS_MEDIOS_PRODUCTO) == 0){
			queryString = queryString + " order by cl.nombreLegal, tp.nombre, prov.nombreLegal, pc.nombre";
		} else if (agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO) == 0){
			queryString = queryString + " order by tp.nombre, prov.nombreLegal, cl.nombreLegal, pc.nombre";
		}else{
			queryString = queryString + " order by clo.descripcion, pc.nombre, tp.nombre, provOfi.descripcion";
		}		
		
		//queryString += " order by oc.codigo asc";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		
		//presupuestos sin ordenes
		
		return query.getResultList();
	}
 
	/* *
	select pr.CODIGO, clo.DESCRIPCION, oc.codigo, tp.NOMBRE, provOf.DESCRIPCION, em.NOMBRES, em.APELLIDOS, 
	oc.FECHA, oc.VALOR, oc.DESCUENTO, oc.IVA
	from ORDEN_COMPRA oc, SOLICITUD_COMPRA sc, PRESUPUESTO pr, ORDEN_TRABAJO_DETALLE otd, 
	ORDEN_TRABAJO ot, CLIENTE_OFICINA clo, EMPLEADO em, CLIENTE_OFICINA provOf, CLIENTE prov,
	TIPO_PROVEEDOR tp
	where oc.SOLICITUDCOMPRA_ID = sc.ID and sc.TIPO_REFERENCIA = 'P' and sc.REFERENCIA = pr.CODIGO and
	pr.ORDENTRABAJODET_ID = otd.ID and otd.ORDEN_ID = ot.ID and clo.ID = ot.CLIENTEOFICINA_ID and 
	ot.CLIENTEOFICINA_ID = 7280 and oc.PROVEEDOR_ID = provOf.ID and provOf.CLIENTE_ID = prov.ID and 
	prov.TIPOPROVEEDOR_ID = tp.ID and oc.EMPLEADO_ID = em.ID 
	order by tp.NOMBRE, pr.CODIGO
	* */
	//string, 
	   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	   public Collection findOrdenesClientesByPresupuestos(Map aMap, java.sql.Date fechaInicio, java.sql.Date fechaFin,Long idEmpresa, boolean ordenarPorFecha, Long creadoPorId, boolean ordenarPorCodigoOrden, boolean ordenarPorCodigoPresupuesto) {

		String queryString = "select distinct pr.codigo, clo.descripcion,oc.codigo,oc.estado,tp.nombre,provOf.descripcion,em.nombres,em.apellidos, " +
				"oc.fecha, oc.valor, oc.descuentoAgenciaCompra, oc.iva, mp.nombre ,pc.nombre, oc.porcentajeDescuentoEspecial, oc.porcentajeDescuentosVariosCompra, oc.id " +
				"from OrdenCompraEJB oc, SolicitudCompraEJB sc, PresupuestoEJB pr, OrdenTrabajoDetalleEJB otd, " +
				"OrdenTrabajoEJB ot, ClienteOficinaEJB clo, EmpleadoEJB em, ClienteOficinaEJB provOf, ClienteEJB prov, TipoProveedorEJB tp, " +
				"PresupuestoProductoEJB pp, MarcaProductoEJB mp, ProductoClienteEJB pc, ClienteEJB cli " +
				"where oc.solicitudcompraId = sc.id and sc.tipoReferencia = 'P' and sc.referencia = pr.codigo and  "+
				"pr.ordentrabajodetId = otd.id and otd.ordenId = ot.id and clo.id = ot.clienteoficinaId and "+
				"oc.proveedorId = provOf.id and provOf.clienteId = prov.id and "+
				"prov.tipoproveedorId = tp.id and oc.empleadoId = em.id "+
				"and mp.clienteId=pc.clienteId and pc.marcaProductoId=mp.id "+
				"and pp.productoClienteId=pc.id and pr.id=pp.presupuestoId "+
				"and cli.id=mp.clienteId and clo.clienteId=cli.id and oc.estado <> 'A' ";//and oc.proveedorId = 117
				  
		if(aMap.get("clienteoficinaId") != null){
			queryString += " and ot.clienteoficinaId = " + aMap.get("clienteoficinaId") + "";
		}
		
		if(creadoPorId != null){
			queryString += " and em.id = " + creadoPorId + "";
		}
				
		if(aMap.get("tipoProveedorId") != null){
			queryString += " and tp.id = " + aMap.get("tipoProveedorId") + "";
		}
				
		if (fechaInicio != null && fechaFin != null)			
			queryString += " and oc.fecha between :fechaInicio and :fechaFin";
			
		if(ordenarPorFecha){
			queryString += " order by oc.fecha asc, pr.codigo asc, oc.codigo asc";
		}else if(ordenarPorCodigoPresupuesto){
			queryString += " order by clo.descripcion, pr.codigo asc, oc.codigo asc";
		}else if(ordenarPorCodigoOrden){
			queryString += " order by clo.descripcion, oc.codigo asc";
		}
				
		Query query = manager.createQuery(queryString);			 
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
				
		return query.getResultList();
	}
}
 
