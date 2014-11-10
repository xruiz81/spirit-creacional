package com.spirit.medios.session;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
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

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.session.ProductoSessionLocal;
import com.spirit.medios.entity.OrdenMedioDetalleEJB;
import com.spirit.medios.entity.OrdenMedioDetalleIf;
import com.spirit.medios.entity.OrdenMedioDetalleMapaEJB;
import com.spirit.medios.entity.OrdenMedioDetalleMapaIf;
import com.spirit.medios.entity.OrdenMedioEJB;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.session.generated._OrdenMedioSession;
import com.spirit.server.QueryBuilder;

/**
 * The <code>OrdenMedioSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class OrdenMedioSessionEJB extends _OrdenMedioSession implements OrdenMedioSessionRemote, OrdenMedioSessionLocal  {
	
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	@EJB 
	private OrdenMedioDetalleSessionLocal ordenMedioDetalleLocal;
	
	@EJB 
	private OrdenMedioDetalleMapaSessionLocal ordenMedioDetalleMapaLocal;
	
	@EJB 
	private PlanMedioSessionLocal planMedioLocal;
	
	@EJB
	private ProductoSessionLocal productoSessionLocal;
	
	@EJB 
	private UtilitariosSessionLocal utilitariosLocal;
	
	private DecimalFormat formatoSerial = new DecimalFormat("00000");
	
	private static final String ESTADO_ORDEN_INGRESADA = "I";
	
	@Resource private SessionContext ctx;
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findOrdenMedioByQuery(Map aMap, Long idEmpresa) {
		String objectName = "om";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct om from OrdenMedioEJB " + objectName + ", ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + where;
		queryString += " and om.clienteOficinaId = co.id and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " order by om.codigo asc";
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
	
	public int findOrdenMedioByQueryAndEmpresaIdListSize(Map aMap, Long idEmpresa) throws GenericBusinessException {
		String objectName = "om";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct count(*) from OrdenMedioEJB " + objectName + ", ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + where;
		queryString += " and om.clienteOficinaId = co.id and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa;
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
	
	public Collection findOrdenMedioByQueryAndEmpresaId(int startIndex, int endIndex, Map aMap, Long idEmpresa) throws GenericBusinessException {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "om";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct om from OrdenMedioEJB " + objectName + ", ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + where;
		queryString += " and om.clienteOficinaId = co.id and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " order by om.codigo desc";
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
	public int findOrdenMedioByQueryAndByEstadosSize(Map aMap, String[] estados) {
		
		String objectName = "om";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		
		String queryString = "select distinct count(*) from OrdenMedioEJB om " +
				"where " +  where + " ";
		
		for(int i = 0; i < estados.length; i++){
			if(i == 0 && estados.length == 1){
				queryString += " and om.estado = '" + estados[i] + "'";
			}else if (i == 0 && estados.length > 1){
				queryString += " and (om.estado = '" + estados[i] + "'";
			}else if (i > 0 && i <= (estados.length -2)){
				queryString += " or om.estado = '" + estados[i] + "'";
			}else if (i > 0 && i == (estados.length -1)){
				queryString += " or om.estado = '" + estados[i] + "')";
			}
		}
		
		queryString += " order by om.codigo desc";
		
		Query query = manager.createQuery(queryString);
		String codigo = (String) aMap.get("codigo");
		query.setParameter("codigo", codigo);
		
		if(aMap.get("proveedorId") != null){
			Long proveedorId = (Long) aMap.get("proveedorId");
			query.setParameter("proveedorId", proveedorId);
		}
		
		if(aMap.get("planMedioId") != null){
			Long planMedioId = (Long) aMap.get("planMedioId");
			query.setParameter("planMedioId", planMedioId);
		}
				
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findOrdenMedioByQueryAndByEstados(int startIndex, int endIndex, Map aMap, String[] estados) {
		
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		
		String objectName = "om";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		
		String queryString = "select distinct om from OrdenMedioEJB om " +
				"where " +  where + " ";
		
		for(int i = 0; i < estados.length; i++){
			if(i == 0 && estados.length == 1){
				queryString += " and om.estado = '" + estados[i] + "'";
			}else if (i == 0 && estados.length > 1){
				queryString += " and (om.estado = '" + estados[i] + "'";
			}else if (i > 0 && i <= (estados.length -2)){
				queryString += " or om.estado = '" + estados[i] + "'";
			}else if (i > 0 && i == (estados.length -1)){
				queryString += " or om.estado = '" + estados[i] + "')";
			}
		}
		
		queryString += " order by om.codigo desc";
		
		Query query = manager.createQuery(queryString);
		String codigo = (String) aMap.get("codigo");
		query.setParameter("codigo", codigo);
		
		if(aMap.get("proveedorId") != null){
			Long proveedorId = (Long) aMap.get("proveedorId");
			query.setParameter("proveedorId", proveedorId);
		}
		
		if(aMap.get("planMedioId") != null){
			Long planMedioId = (Long) aMap.get("planMedioId");
			query.setParameter("planMedioId", planMedioId);
		}
				
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}
	
	public Collection findOrdenMedioPlanMedioProductoClienteByPedidoIdByMedioIdByMedioOficinaId(Long idPedido, Long idMedio, Long idMedioOficina) throws GenericBusinessException {
		//Query sin medioId ni medioOficinaId
		//select distinct om.*, pc.*, pm.* from PLAN_MEDIO_FACTURACION pmf, PRODUCTO_CLIENTE pc, PLAN_MEDIO pm, ORDEN_MEDIO om
		//where pmf.PEDIDO_ID = 24420 and pmf.PRODUCTO_CLIENTE_ID = pc.ID and pmf.ORDEN_MEDIO_ID = om.ID and om.PLAN_MEDIO_ID = pm.ID
		
		//Query con medioId ni medioOficinaId
		//select distinct om.*, pc.*, pm.* 
		//from PLAN_MEDIO_FACTURACION pmf, PRODUCTO_CLIENTE pc, PLAN_MEDIO pm, ORDEN_MEDIO om,
		//CLIENTE me, CLIENTE_OFICINA mof
		//where pmf.PEDIDO_ID = 24461 and pmf.PRODUCTO_CLIENTE_ID = pc.ID and pmf.ORDEN_MEDIO_ID = om.ID 
		//and om.PLAN_MEDIO_ID = pm.ID and om.PROVEEDOR_ID = mof.ID and mof.CLIENTE_ID = me.ID
		//and om.PROVEEDOR_ID = 6704 and me.ID = 1443
		
		String queryString = "select distinct pm, pc, om from PlanMedioFacturacionEJB pmf, ProductoClienteEJB pc, PlanMedioEJB pm, OrdenMedioEJB om, ClienteEJB me, ClienteOficinaEJB mof " +
				"where pmf.pedidoId = " + idPedido + " and pmf.productoClienteId = pc.id and pmf.ordenMedioId = om.id and om.planMedioId = pm.id and om.proveedorId = mof.id and mof.clienteId = me.id";
		
		if(idMedioOficina != null && idMedioOficina != 0L){
			queryString = queryString + " and om.proveedorId = " + idMedioOficina + "";
		}else if(idMedio != null && idMedio != 0L){
			queryString = queryString + " and me.id = " + idMedio + "";
		}
		
		//String queryString = "select distinct pm, pc, om from PlanMedioFacturacionEJB pmf, ProductoClienteEJB pc, PlanMedioEJB pm, OrdenMedioEJB om " +
		//"where pmf.pedidoId = " + idPedido + " and pmf.productoClienteId = pc.id and pmf.ordenMedioId = om.id and om.planMedioId = pm.id ";
		
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	public Collection findOrdenMedioByQueryAndByClienteId(Map aMap, Long idCliente, Long idEmpresa) throws GenericBusinessException {
		String objectName = "m";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct m from OrdenMedioEJB " + objectName + ", ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + where;
		queryString += " and m.clienteOficinaId = co.id and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and co.clienteId = " + idCliente + " order by m.codigo desc";
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
	
	public Collection findOrdenMedioByQueryAndByClienteId(int startIndex,int endIndex,Map aMap, Long idCliente, Long idEmpresa) throws GenericBusinessException {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "m";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct m from OrdenMedioEJB " + objectName + ", ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + where;
		queryString += " and m.clienteOficinaId = co.id and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and co.clienteId = " + idCliente + " order by m.id desc";
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
	
	public int findOrdenMedioByQueryAndByClienteIdSize(Map aMap, Long idCliente, Long idEmpresa) throws GenericBusinessException {
		String objectName = "m";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct count(*) from OrdenMedioEJB " + objectName + ", ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + where;
		queryString += " and m.clienteOficinaId = co.id and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and co.clienteId = " + idCliente + " order by m.id desc";
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
	
	//ADD 28 JULIO
	//public Collection findOrdenMedioByQueryClienteOficinaAndQueryProductoClienteAndByFechas(Map aMapOrdenMedio, Map aMapClienteOficina, Map aMapProductoCliente, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFinal, Long idEmpresa) throws GenericBusinessException {
		public Collection findOrdenMedioByQueryClienteOficinaAndQueryProductoClienteAndByFechas(Map aMapOrdenMedio, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFinal, Long idEmpresa) throws GenericBusinessException {	
		String objectNameOM = "om";//put clienteOficinaId, proveedorId,productoClienteId
		String whereOM = QueryBuilder.buildWhere(aMapOrdenMedio, objectNameOM);//Convierte el Mapa a string(estructura query) del string objeto enviado
		
		String queryString = "select distinct om from OrdenMedioEJB " + objectNameOM + ", ClienteOficinaEJB co," +
							 " ClienteEJB c, TipoClienteEJB tc, ProductoClienteEJB pc, PlanMedioEJB pm " +
							 " where " + whereOM + "";// and " +  wherePC ;
		queryString += " and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + 
					   " and om.clienteOficinaId = co.id and om.productoClienteId = pc.id and om.planMedioId = pm.id" +
					   " and pm.ordenesXCanal = 'NO' and om.estado <> 'A' and pm.estado <> 'H' "+
					   " and pm.fechaInicio >= :fechaInicio and pm.fechaFin <= :fechaFinal order by om.codigo desc";
		
				
		System.out.println("DATA>>>>>>>>>>>>>>>>>>>"+queryString);
		
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFinal",fechaFinal);
		
		Set keys = aMapOrdenMedio.keySet();
		Iterator itOrdenMedio = keys.iterator();
		
		while (itOrdenMedio.hasNext()) {
			String propertyKey = (String) itOrdenMedio.next();
			Object property = aMapOrdenMedio.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
					
		return query.getResultList();
	}
				
		//mapaGeneralAgregado con keyClienteId, keyMarcaProductoId, keyTipoProveedorId, keyProveedorId ORDENES AGRUPADAS POR PRODUCTO 
		public Collection findOrdenMedioByQueryAndQueryGeneralByProductoClienteAndByFechas(Map aMapOrdenMedio, Map aMapGeneral, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFinal, Long idEmpresa) throws GenericBusinessException {	
									
			String objectNameOM = "om";//put clienteOficinaId, proveedorId,productoClienteId
			String whereOM = QueryBuilder.buildWhere(aMapOrdenMedio, objectNameOM);//Convierte el Mapa a string(estructura query) del string objeto enviado
			
			//ADD 28 JULIO
			String keyClienteId   = "keyClienteId";
			String keyMarcaProductoId = "keyMarcaProductoId";
			String keyTipoProveedorId = "keyTipoProveedorId";
			String keyProveedorId     = "keyProveedorId";
			String whereGeneral   = "";
			
			Set keysMapGeneral = aMapGeneral.keySet();
			Iterator itMapGeneral = keysMapGeneral.iterator();
			int propertyNumber = keysMapGeneral.size();
			int i = 1;
			while (itMapGeneral.hasNext()) {
				String propertyKey = (String) itMapGeneral.next();
				if (propertyKey.compareTo(keyClienteId) == 0){
					whereGeneral = whereGeneral + "c" + "." + "id" + " = :"
					+ "keyClienteId";
				}
				else if (propertyKey.compareTo(keyMarcaProductoId) == 0){
						whereGeneral = whereGeneral + "pc" + "." + "marcaProductoId" + " = :"
										+ "keyMarcaProductoId";
				}//ADD 29 JULIO
				else if (propertyKey.compareTo(keyTipoProveedorId) == 0){
										
					whereGeneral = whereGeneral + " om.proveedorId in (select distinct omtemp.proveedorId from OrdenMedioEJB omtemp, ClienteOficinaEJB cotemp, ClienteEJB ctemp, TipoClienteEJB tctemp " +
									" where omtemp.proveedorId = cotemp.id and " +
									" cotemp.clienteId = ctemp.id and ctemp.tipoclienteId = tctemp.id and tctemp.empresaId = " + idEmpresa + 
									" and ctemp.tipoproveedorId" + " = :" + "keyTipoProveedorId"+ ")";
				}
				else if (propertyKey.compareTo(keyProveedorId) == 0){
					whereGeneral = whereGeneral + " om.proveedorId in (select distinct omtemp.proveedorId from OrdenMedioEJB omtemp, ClienteOficinaEJB cotemp, ClienteEJB ctemp, TipoClienteEJB tctemp " +
									" where omtemp.proveedorId = cotemp.id and " +
									" cotemp.clienteId = ctemp.id and ctemp.tipoclienteId = tctemp.id and tctemp.empresaId = " + idEmpresa + 
									" and ctemp.id" + " = :" + "keyProveedorId"+ ")";	
				}//END 29 JULIO	
				
				if (i == propertyNumber) {
					whereGeneral = whereGeneral + " ";
				} else {
					whereGeneral = whereGeneral + " and ";
				}

				i++;
			}
			//END 28 JULIO
			
			String queryString = "select distinct om from OrdenMedioEJB " + objectNameOM + ", ClienteOficinaEJB co," +
								 " ClienteEJB c, TipoClienteEJB tc, ProductoClienteEJB pc, PlanMedioEJB pm " +
								 " where " + whereOM; 
			//ADD 29 JULIO
			if (whereOM.length() > 2)
				queryString += " and ";
			
			queryString += whereGeneral;
			
			if (whereGeneral.length() > 0)
				queryString += " and ";
			//END 29 JULIO
			
			queryString += " co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + 
						   " and om.clienteOficinaId = co.id and om.productoClienteId = pc.id and om.planMedioId = pm.id" +
						   " and pm.ordenesXCanal = 'NO' and om.estado <> 'A' and pm.estado <> 'H' "+
						   " and pm.fechaInicio >= :fechaInicio and pm.fechaFin <= :fechaFinal order by om.codigo desc";
								
			System.out.println("DATA>>>>>>>>>>>>>>>>>>>"+queryString);
			
			Query query = manager.createQuery(queryString);
			query.setParameter("fechaInicio",fechaInicio);
			query.setParameter("fechaFinal",fechaFinal);
			
			Set keys = aMapOrdenMedio.keySet();
			Iterator itOrdenMedio = keys.iterator();
			
			while (itOrdenMedio.hasNext()) {
				String propertyKey = (String) itOrdenMedio.next();
				Object property = aMapOrdenMedio.get(propertyKey);
				query.setParameter(propertyKey, property);
			}
			
			//ADD 28 JULIO
			Set keysGeneralIds = aMapGeneral.keySet();
			Iterator itGeneralIds = keysGeneralIds.iterator();
			
			while (itGeneralIds.hasNext()) {
				String propertyKey = (String) itGeneralIds.next();
				Object property = aMapGeneral.get(propertyKey);
				query.setParameter(propertyKey, property);			
			}				
			//END 28 JULIO
							
			return query.getResultList();
		}		
		
		public Collection findOrdenMedioDetalleByQueryAndQueryGeneralByProductoAndByFechas(
				Map aMapOrdenMedio, Map aMapGeneral, String pauta, java.sql.Timestamp fechaInicio, 
				java.sql.Timestamp fechaFinal, boolean fechaAprobacion, Long idEmpresa, String estado, 
				boolean aprobadosFacturados, String estadoOrden, boolean noMostrarOrdenesEmitidas, boolean buscarPresupuestosPrepago) throws GenericBusinessException {	
				
			String objectNameOM = "om";
			String whereOM = QueryBuilder.buildWhere(aMapOrdenMedio, objectNameOM);
				
			String keyCampanaId   = "keyCampanaId";
			String keyClienteId   = "keyClienteId";
			String keyMarcaProductoId = "keyMarcaProductoId";
			String keyTipoProveedorId = "keyTipoProveedorId";
			String keyTipoProductoId = "keyTipoProductoId";
			String keyProveedorId     = "keyProveedorId";
			String keyProductoClienteId = "keyProductoClienteId"; 
			String keyDerechoProgramaId = "keyDerechoProgramaId"; 
			String keyCodigoUnicoPresupuesto = "keyCodigoUnicoPresupuesto"; 
			String whereGeneral   = "";
				
			Set keysMapGeneral = aMapGeneral.keySet();
			Iterator itMapGeneral = keysMapGeneral.iterator();
			int propertyNumber = keysMapGeneral.size();
			int i = 1;
			
			while (itMapGeneral.hasNext()) {
				String propertyKey = (String) itMapGeneral.next();
				if (propertyKey.compareTo(keyClienteId) == 0){
					whereGeneral = whereGeneral + "c" + "." + "id" + " = :"
					+ "keyClienteId";
				}
				else if (propertyKey.compareTo(keyMarcaProductoId) == 0){
						whereGeneral = whereGeneral + "pc" + "." + "marcaProductoId" + " = :"
										+ "keyMarcaProductoId";
				}
				else if (propertyKey.compareTo(keyProductoClienteId) == 0){
					whereGeneral = whereGeneral + "omd" + "." + "productoClienteId" + " = :"
									+ "keyProductoClienteId";		
				}
				
				else if (propertyKey.compareTo(keyTipoProveedorId) == 0){											
					whereGeneral = whereGeneral + " om.proveedorId in (select distinct omtemp.proveedorId from OrdenMedioEJB omtemp, ClienteOficinaEJB cotemp, ClienteEJB ctemp, TipoClienteEJB tctemp " +
									" where omtemp.proveedorId = cotemp.id and " +
									" cotemp.clienteId = ctemp.id and ctemp.tipoclienteId = tctemp.id and tctemp.empresaId = " + idEmpresa + 
									" and ctemp.tipoproveedorId" + " = :" + "keyTipoProveedorId"+ ")";
				}
				
				else if (propertyKey.compareTo(keyTipoProductoId) == 0){											
					whereGeneral = whereGeneral + " omd.productoProveedorId in (select distinct ptemp.id from ProductoEJB ptemp, GenericoEJB gtemp " +
									" where ptemp.genericoId = gtemp.id and gtemp.tipoproductoId = :keyTipoProductoId)";
				}				
				
				else if (propertyKey.compareTo(keyProveedorId) == 0){
					whereGeneral = whereGeneral + " om.proveedorId in (select distinct omtemp.proveedorId from OrdenMedioEJB omtemp, ClienteOficinaEJB cotemp, ClienteEJB ctemp, TipoClienteEJB tctemp " +
									" where omtemp.proveedorId = cotemp.id and " +
									" cotemp.clienteId = ctemp.id and ctemp.tipoclienteId = tctemp.id and tctemp.empresaId = " + idEmpresa + 
									" and ctemp.id" + " = :" + "keyProveedorId"+ ")";	
				}						
				else if (propertyKey.compareTo(keyDerechoProgramaId) == 0){
					whereGeneral = whereGeneral + "cm" + "." + "derechoprogramaId" + " = :"
									+ "keyDerechoProgramaId";		
				}
				else if (propertyKey.compareTo(keyCampanaId) == 0){
					whereGeneral = whereGeneral + "cm" + "." + "campanaId" + " = :"
									+ "keyCampanaId";		
				}
				else if (propertyKey.compareTo(keyCodigoUnicoPresupuesto) == 0){
					whereGeneral = whereGeneral + "pm" + "." + "codigo" + " = :"
									+ "keyCodigoUnicoPresupuesto";		
				}
				
				if (i == propertyNumber) {
					whereGeneral = whereGeneral + " ";
				} else {
					whereGeneral = whereGeneral + " and ";
				}
	
				i++;
			}
			
			String queryString = "select distinct om, omd, cm, pm  from OrdenMedioEJB " + objectNameOM + ", ClienteOficinaEJB co," +
			 					 " ClienteEJB c, TipoClienteEJB tc, ProductoClienteEJB pc, PlanMedioEJB pm," +
			 					 " OrdenMedioDetalleEJB omd, ComercialEJB cm where " + whereOM; 
			 								
			if (whereOM.length() > 2)
				queryString += " and ";
				
			queryString += whereGeneral;
				
			if (whereGeneral.length() > 0)
				queryString += " and ";
			
			if (pauta.compareTo("B") != 0)
				queryString +=  " omd.pauta = '" + pauta + "' and ";
			
			//estados del plan
			if(!estado.equals("") && !estado.equals("TODOS")){
				//MEDIOS, EN PROCESO = 'N', PENDIENTE = 'P', APROBADO = 'A', PEDIDO = 'D', FACTURADO = 'F'
				//PRESUPUESTO, COTIZADO = 'T', PENDIENTE = 'P', APROBADO = 'A', FACTURADO = 'F'
				if(estado.equals("COTIZADO")){
					queryString += " (pm.estado = 'N' or pm.estado = 'P') and ";
				}else if(estado.equals("APROBADO")){
					queryString += " pm.estado = 'A' and ";
				}else if(estado.equals("FACTURADO")){
					queryString += " (pm.estado = 'D' or pm.estado = 'F') and ";
				}else if(estado.equals("PREPAGADO")){
					queryString += " pm.estado = 'R' and ";
				}
			} else if(!estado.equals("") && estado.equals("TODOS") && aprobadosFacturados){
					queryString += " (pm.estado = 'A' or pm.estado = 'D' or pm.estado = 'F') and ";
			}
			
			if(!buscarPresupuestosPrepago){
				queryString += " pm.prepago = 'N' and ";
			}
			
			//si la busqueda en por fecha de aprobacion
			if(fechaAprobacion){
				queryString += " pm.fechaAprobacion >= :fechaInicio and pm.fechaAprobacion <= :fechaFinal and ";
			}else{
				queryString += " pm.fechaInicio >= :fechaInicio and pm.fechaFin <= :fechaFinal and ";
			}
			
			if(noMostrarOrdenesEmitidas){
				queryString += " om.estado <> 'M' and ";
			}
			
			//estados de las ordenes
			if(!estadoOrden.equals("") && !estadoOrden.equals("TODOS")){
				//EMITIDO = "M", ORDENADO = "D", ENVIADO = "E", INGRESADO = "I", PREPAGADO = "R"
				if(estadoOrden.equals("EMITIDA")){
					queryString += " om.estado = 'M' and ";
				}else if(estadoOrden.equals("ORDENADA")){
					queryString += " om.estado = 'D' and ";
				}else if(estadoOrden.equals("ENVIADA")){
					queryString += " om.estado = 'E' and ";
				}else if(estadoOrden.equals("INGRESADA")){
					queryString += " om.estado = 'I' and ";
				}else if(estadoOrden.equals("PREPAGADA")){
					queryString += " om.estado = 'R' and ";
				}
			} 
			
			queryString += " co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + 
			   			   " and om.clienteOficinaId = co.id and om.planMedioId = pm.id" +
			   			   " and omd.productoClienteId = pc.id and om.id = omd.ordenMedioId" + 
			   			   " and omd.comercialId = cm.id" + 
			   			   " and om.estado <> 'A' and pm.estado <> 'H' "+ 
			   			   " order by omd.id desc";
														
			Query query = manager.createQuery(queryString);
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFinal", fechaFinal);
				
			Set keys = aMapOrdenMedio.keySet();
			Iterator itOrdenMedio = keys.iterator();
				
			while (itOrdenMedio.hasNext()) {
				String propertyKey = (String) itOrdenMedio.next();
				Object property = aMapOrdenMedio.get(propertyKey);
				query.setParameter(propertyKey, property);
			}
				
			Set keysGeneralIds = aMapGeneral.keySet();
			Iterator itGeneralIds = keysGeneralIds.iterator();
			
			while (itGeneralIds.hasNext()) {
				String propertyKey = (String) itGeneralIds.next();
				Object property = aMapGeneral.get(propertyKey);
				query.setParameter(propertyKey, property);			
			}				
								
			return query.getResultList();
		}		
		
		
		public Collection findOrdenMedioDetalleByQueryAndQueryGeneralByCanalAndByFechas(Map aMapOrdenMedio, Map aMapGeneral, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFinal, Long idEmpresa) throws GenericBusinessException {	
									
			String objectNameOM = "om";
			String whereOM = QueryBuilder.buildWhere(aMapOrdenMedio, objectNameOM);//Convierte el Mapa a string(estructura query) del string objeto enviado
			
			String keyClienteId   = "keyClienteId";
			String keyMarcaProductoId = "keyMarcaProductoId";
			String keyProductoClienteId = "keyProductoClienteId"; //ADD 29 JULIO
			String keyTipoProveedorId = "keyTipoProveedorId";
			String keyProveedorId     = "keyProveedorId";
			String whereGeneral   = "";
			
			Set keysMapGeneral = aMapGeneral.keySet();
			Iterator itMapGeneral = keysMapGeneral.iterator();
			int propertyNumber = keysMapGeneral.size();
			int i = 1;
			while (itMapGeneral.hasNext()) {
				String propertyKey = (String) itMapGeneral.next();
				if (propertyKey.compareTo(keyClienteId) == 0){
					whereGeneral = whereGeneral + "c" + "." + "id" + " = :"
					+ "keyClienteId";
				}
				else if (propertyKey.compareTo(keyMarcaProductoId) == 0){
						whereGeneral = whereGeneral + "pc" + "." + "marcaProductoId" + " = :"
										+ "keyMarcaProductoId";
				}//ADD 29 JULIO
				else if (propertyKey.compareTo(keyProductoClienteId) == 0){
					whereGeneral = whereGeneral + "omd" + "." + "productoClienteId" + " = :"
									+ "keyProductoClienteId";				
				}				
				else if (propertyKey.compareTo(keyTipoProveedorId) == 0){
										
					whereGeneral = whereGeneral + " om.proveedorId in (select distinct omtemp.proveedorId from OrdenMedioEJB omtemp, ClienteOficinaEJB cotemp, ClienteEJB ctemp, TipoClienteEJB tctemp " +
									" where omtemp.proveedorId = cotemp.id and " +
									" cotemp.clienteId = ctemp.id and ctemp.tipoclienteId = tctemp.id and tctemp.empresaId = " + idEmpresa + 
									" and ctemp.tipoproveedorId" + " = :" + "keyTipoProveedorId"+ ")";
				}
				else if (propertyKey.compareTo(keyProveedorId) == 0){
					whereGeneral = whereGeneral + " om.proveedorId in (select distinct omtemp.proveedorId from OrdenMedioEJB omtemp, ClienteOficinaEJB cotemp, ClienteEJB ctemp, TipoClienteEJB tctemp " +
									" where omtemp.proveedorId = cotemp.id and " +
									" cotemp.clienteId = ctemp.id and ctemp.tipoclienteId = tctemp.id and tctemp.empresaId = " + idEmpresa + 
									" and ctemp.id" + " = :" + "keyProveedorId"+ ")";	
				}//END 29 JULIO	
				
				if (i == propertyNumber) {
					whereGeneral = whereGeneral + " ";
				} else {
					whereGeneral = whereGeneral + " and ";
				}

				i++;
			}
			//END 28 JULIO
			
			//COMENTED 4 AGOSTO
			/*String queryString = "select distinct om from OrdenMedioEJB " + objectNameOM + ", ClienteOficinaEJB co," +
								 " ClienteEJB c, TipoClienteEJB tc, ProductoClienteEJB pc, PlanMedioEJB pm," +
								 " OrdenMedioDetalleEJB omd where " + whereOM; */
			
			String queryString = "select distinct omd from OrdenMedioEJB " + objectNameOM + ", ClienteOficinaEJB co," +
			 " ClienteEJB c, TipoClienteEJB tc, ProductoClienteEJB pc, PlanMedioEJB pm," +
			 " OrdenMedioDetalleEJB omd where " + whereOM; 
			
			//ADD 29 JULIO
			if (whereOM.length() > 2)
				queryString += " and ";
			
			queryString += whereGeneral;
			
			if (whereGeneral.length() > 0)
				queryString += " and ";
			//END 29 JULIO
			
			queryString += " co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + 
						   " and om.clienteOficinaId = co.id and om.planMedioId = pm.id" +
						   " and omd.ordenMedioId = om.id and omd.productoClienteId = pc.id" +
						   " and pm.ordenMedioTipo = 'C' and om.estado <> 'A' and om.estado <> 'M' and pm.estado <> 'H' "+
						   " and pm.fechaInicio >= :fechaInicio and pm.fechaFin <= :fechaFinal " +
						   " order by omd.id desc"; //MODIFIED 11 AGOSTO
						  // " group by pc.marcaProductoId, omd.pauta order by omd.id desc"; //COMENTED 11 AGOSTO
			
			//and om.productoClienteId = pc.id
								
			System.out.println("DATA>>>>>>>>>>>>>>>>>>>"+queryString);
			
			Query query = manager.createQuery(queryString);
			query.setParameter("fechaInicio",fechaInicio);
			query.setParameter("fechaFinal",fechaFinal);
			
			Set keys = aMapOrdenMedio.keySet();
			Iterator itOrdenMedio = keys.iterator();
			
			while (itOrdenMedio.hasNext()) {
				String propertyKey = (String) itOrdenMedio.next();
				Object property = aMapOrdenMedio.get(propertyKey);
				query.setParameter(propertyKey, property);
			}
			
			//ADD 28 JULIO
			Set keysGeneralIds = aMapGeneral.keySet();
			Iterator itGeneralIds = keysGeneralIds.iterator();
			
			while (itGeneralIds.hasNext()) {
				String propertyKey = (String) itGeneralIds.next();
				Object property = aMapGeneral.get(propertyKey);
				query.setParameter(propertyKey, property);			
			}				
			//END 28 JULIO
							
			return query.getResultList();
		}		
		//END 29 JULIO
		
		
		//ADD 4 AGOSTO
		//mapaGeneralAgregado con keyClienteId, keyMarcaProductoId, keyProductoClienteId, keyTipoProveedorId, keyProveedorId ORDENES AGRUPADAS POR CANAL 
		public Collection findOrdenMedioByQueryAndQueryGeneralByCanalAndByFechas(Map aMapOrdenMedio, Map aMapGeneral, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFinal, Long idEmpresa) throws GenericBusinessException {	
									
			String objectNameOM = "om";//put clienteOficinaId, proveedorId
			String whereOM = QueryBuilder.buildWhere(aMapOrdenMedio, objectNameOM);//Convierte el Mapa a string(estructura query) del string objeto enviado
			
			//ADD 28 JULIO
			String keyClienteId   = "keyClienteId";
			String keyMarcaProductoId = "keyMarcaProductoId";
			String keyProductoClienteId = "keyProductoClienteId"; //ADD 29 JULIO
			String keyTipoProveedorId = "keyTipoProveedorId";
			String keyProveedorId     = "keyProveedorId";
			String whereGeneral   = "";
			
			Set keysMapGeneral = aMapGeneral.keySet();
			Iterator itMapGeneral = keysMapGeneral.iterator();
			int propertyNumber = keysMapGeneral.size();
			int i = 1;
			while (itMapGeneral.hasNext()) {
				String propertyKey = (String) itMapGeneral.next();
				if (propertyKey.compareTo(keyClienteId) == 0){
					whereGeneral = whereGeneral + "c" + "." + "id" + " = :"
					+ "keyClienteId";
				}
				else if (propertyKey.compareTo(keyMarcaProductoId) == 0){
						whereGeneral = whereGeneral + "pc" + "." + "marcaProductoId" + " = :"
										+ "keyMarcaProductoId";
				}//ADD 29 JULIO
				else if (propertyKey.compareTo(keyProductoClienteId) == 0){
					whereGeneral = whereGeneral + "omd" + "." + "productoClienteId" + " = :"
									+ "keyProductoClienteId";				
				}				
				else if (propertyKey.compareTo(keyTipoProveedorId) == 0){
										
					whereGeneral = whereGeneral + " om.proveedorId in (select distinct omtemp.proveedorId from OrdenMedioEJB omtemp, ClienteOficinaEJB cotemp, ClienteEJB ctemp, TipoClienteEJB tctemp " +
									" where omtemp.proveedorId = cotemp.id and " +
									" cotemp.clienteId = ctemp.id and ctemp.tipoclienteId = tctemp.id and tctemp.empresaId = " + idEmpresa + 
									" and ctemp.tipoproveedorId" + " = :" + "keyTipoProveedorId"+ ")";
				}
				else if (propertyKey.compareTo(keyProveedorId) == 0){
					whereGeneral = whereGeneral + " om.proveedorId in (select distinct omtemp.proveedorId from OrdenMedioEJB omtemp, ClienteOficinaEJB cotemp, ClienteEJB ctemp, TipoClienteEJB tctemp " +
									" where omtemp.proveedorId = cotemp.id and " +
									" cotemp.clienteId = ctemp.id and ctemp.tipoclienteId = tctemp.id and tctemp.empresaId = " + idEmpresa + 
									" and ctemp.id" + " = :" + "keyProveedorId"+ ")";	
				}//END 29 JULIO	
				
				if (i == propertyNumber) {
					whereGeneral = whereGeneral + " ";
				} else {
					whereGeneral = whereGeneral + " and ";
				}

				i++;
			}
			//END 28 JULIO
			
			String queryString = "select distinct om from OrdenMedioEJB " + objectNameOM + ", ClienteOficinaEJB co," +
								 " ClienteEJB c, TipoClienteEJB tc, ProductoClienteEJB pc, PlanMedioEJB pm," +
								 " OrdenMedioDetalleEJB omd where " + whereOM; 
			//ADD 29 JULIO
			if (whereOM.length() > 2)
				queryString += " and ";
			
			queryString += whereGeneral;
			
			if (whereGeneral.length() > 0)
				queryString += " and ";
			//END 29 JULIO
			
			queryString += " co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + 
						   " and om.clienteOficinaId = co.id and om.planMedioId = pm.id" +
						   " and omd.ordenMedioId = om.id and omd.productoClienteId = pc.id" +
						   " and pm.ordenesXCanal = 'SI' and om.estado <> 'A' and pm.estado <> 'H' "+
						   " and pm.fechaInicio >= :fechaInicio and pm.fechaFin <= :fechaFinal order by om.codigo desc";
			
			//and om.productoClienteId = pc.id
								
			System.out.println("DATA>>>>>>>>>>>>>>>>>>>"+queryString);
			
			Query query = manager.createQuery(queryString);
			query.setParameter("fechaInicio",fechaInicio);
			query.setParameter("fechaFinal",fechaFinal);
			
			Set keys = aMapOrdenMedio.keySet();
			Iterator itOrdenMedio = keys.iterator();
			
			while (itOrdenMedio.hasNext()) {
				String propertyKey = (String) itOrdenMedio.next();
				Object property = aMapOrdenMedio.get(propertyKey);
				query.setParameter(propertyKey, property);
			}
			
			//ADD 28 JULIO
			Set keysGeneralIds = aMapGeneral.keySet();
			Iterator itGeneralIds = keysGeneralIds.iterator();
			
			while (itGeneralIds.hasNext()) {
				String propertyKey = (String) itGeneralIds.next();
				Object property = aMapGeneral.get(propertyKey);
				query.setParameter(propertyKey, property);			
			}				
			//END 28 JULIO
							
			return query.getResultList();
		}		
		//END 4 AGOSTO
		
	
	public Collection findOrdenMedioByQueryAndByFechas(Map aMap, String fechaInicio, String fechaFinal, Long idEmpresa) throws GenericBusinessException {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct e from OrdenMedioEJB " + objectName + ", ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + where;
		queryString += " and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and fechaini >= :fechaInicio and fechafin <= :fechaFinal order by e.codigo desc";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFinal",fechaFinal);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
			
		}
		
		return query.getResultList();
	}
	
	public Collection findOrdenMedioByQueryAndByFechas(int startIndex,int endIndex,Map aMap, String fechaInicio, String fechaFinal, Long idEmpresa) throws GenericBusinessException {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct e from OrdenMedioEJB " + objectName + ", ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + where;
		queryString += " and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and fechaini >= :fechaInicio and fechafin <= :fechaFinal";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFinal",fechaFinal);
		
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
	
	public int findOrdenMedioByQueryAndByFechasSize(Map aMap, String fechaInicio, String fechaFinal, Long idEmpresa) throws GenericBusinessException {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct count(*) from OrdenMedioEJB " + objectName + ", ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + where;
		queryString += " and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and fechaini >= :fechaInicio and fechafin <= :fechaFinal";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFinal",fechaFinal);
		
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
	
	public Collection findOrdenMedioByQueryAndByFechasAndByClienteId(Map aMap, String fechaInicio, String fechaFinal, Long idCliente, Long idEmpresa) throws GenericBusinessException {
		String objectName = "m";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct m from OrdenMedioEJB " + objectName + ", ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + where;
		queryString += " and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and m.fechaini >= :fechaInicio and m.fechafin <= :fechaFinal and m.clienteOficinaId = co.id and co.clienteId = " + idCliente + " order by m.codigo desc";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFinal",fechaFinal);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
			
		}
		
		return query.getResultList();
	}
	
	public Collection findOrdenMedioByQueryAndByFechasAndByClienteId(int startIndex,int endIndex,Map aMap, String fechaInicio, String fechaFinal, Long idCliente, Long idEmpresa) throws GenericBusinessException {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "m";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct m from OrdenMedioEJB " + objectName + ", ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + where;
		queryString += " and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and m.fechaini >= :fechaInicio and m.fechafin <= :fechaFinal and m.clienteOficinaId = co.id and co.clienteId = " + idCliente + " order by m.id desc";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFinal",fechaFinal);
		
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
	
	public int findOrdenMedioByQueryAndByFechasAndByClienteIdSize(Map aMap, String fechaInicio, String fechaFinal, Long idCliente, Long idEmpresa) throws GenericBusinessException {
		String objectName = "m";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct count (*) from OrdenMedioEJB " + objectName + ", ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + where;
		queryString += " and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and m.fechaini >= :fechaInicio and m.fechafin <= :fechaFinal and m.clienteOficinaId = co.id and co.clienteId = " + idCliente + " order by m.id desc";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFinal",fechaFinal);
		
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
	
	
	
	
	//MODIFIED 28 JUNIO
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Map<OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> procesarOrdenMedioXNuevaVersion(PlanMedioIf planMedioIf, Map<String,Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> mapaClienteOficinaOrdenes,ArrayList<Map<Long,Map<String,String>>> listIdsEstadosOrdenesMedioToAnular) throws GenericBusinessException {
		try {
			Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> mapaComercialOrdenesCompCreadas= new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>();
			PlanMedioIf planMedio = planMedioLocal.registrarPlanMedio(planMedioIf);//me devuelve creado un plan de medio ejb
			planMedioLocal.savePlanMedio(planMedio);//actualiza el plan de medio en la base
			Map<String,String> ordenesIngresadasMap = new HashMap<String,String>();
			
			for (Map mapIdOrdenCodigoEstado : listIdsEstadosOrdenesMedioToAnular){
				Long idOrden = (Long) mapIdOrdenCodigoEstado.keySet().iterator().next();
				Map<String,String> mapCodigoEstado = new HashMap<String,String>();
				mapCodigoEstado = (Map<String, String>) mapIdOrdenCodigoEstado.get(idOrden);
				String codigoOrden = (String) mapCodigoEstado.keySet().iterator().next();
				String estadoOrden = (String) mapCodigoEstado.get(codigoOrden);
				//String estadoOrden = (String) mapIdOrdenEstado.get(idOrden);
				//if (estadoOrden.compareTo("I") != 0)
				
				if(!estadoOrden.equals(ESTADO_ORDEN_INGRESADA)){
					this.actualizarOrdenMedioEstado(idOrden,"A");//ESTADO ANULADA
					ordenesIngresadasMap.put(codigoOrden, estadoOrden);
				}
				else{
					//Si esta ingresada a la orden se le actualiza el plan de Medio
					OrdenMedioIf ordenMedioIf = getOrdenMedio(idOrden);
					ordenMedioIf.setPlanMedioId(planMedioIf.getId());
					manager.merge(ordenMedioIf);
					
					Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> mapaComercialOrdenMedioDetalle = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();
					ArrayList <OrdenMedioDetalleMapaIf> mapaComercialOrdenMedioDetalleMapa = new ArrayList<OrdenMedioDetalleMapaIf>();
				
					Collection ordenMedioDetalleColeccion = ordenMedioDetalleLocal.findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
					Iterator ordenMedioDetalleColeccionIt = ordenMedioDetalleColeccion.iterator();
					while(ordenMedioDetalleColeccionIt.hasNext()){
						OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleColeccionIt.next();
						
						Collection ordenMedioDetalleMapaColeccion = ordenMedioDetalleMapaLocal.findOrdenMedioDetalleMapaByOrdenMedioDetalleId(ordenMedioDetalleIf.getId());
						Iterator ordenMedioDetalleMapaColeccionIt = ordenMedioDetalleMapaColeccion.iterator();
						while(ordenMedioDetalleMapaColeccionIt.hasNext()){
							OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf = (OrdenMedioDetalleMapaIf)ordenMedioDetalleMapaColeccionIt.next();
							
							mapaComercialOrdenMedioDetalleMapa.add(ordenMedioDetalleMapaIf);
						}
						
						mapaComercialOrdenMedioDetalle.put(ordenMedioDetalleIf,mapaComercialOrdenMedioDetalleMapa);
					}					
					
					mapaComercialOrdenesCompCreadas.put(ordenMedioIf, mapaComercialOrdenMedioDetalle);
				}		
			}
			//END 28 JUNIO
			
			String codigo = "";
			Iterator mapaClienteOficinaOrdenesIt = mapaClienteOficinaOrdenes.keySet().iterator();
			while(mapaClienteOficinaOrdenesIt.hasNext()){
				String[] key = ((String) mapaClienteOficinaOrdenesIt.next()).split("-");
				Long proveedorIf = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> mapaOrdenes = mapaClienteOficinaOrdenes.get(String.valueOf(proveedorIf)+"-"+numeroOrden);
				Iterator mapaOrdenesIt = mapaOrdenes.keySet().iterator();
				while(mapaOrdenesIt.hasNext()){
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf)mapaOrdenesIt.next();
					//AKI PREGUNTAR ESTADO DE LAS ORDENES 28 JUNIO
					
					//si no esta ingresada o si no tiene codigo 
					if(ordenMedioIf.getEstado().compareTo("I") != 0 &&
							(ordenMedioIf.getCodigo().split("-").length == 1 || ordenesIngresadasMap.get(ordenMedioIf.getCodigo()) != null)){//si el estado de la Orden es diferente a Ingresada
					
						Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenMedioDetallesHashM = mapaOrdenes.get(ordenMedioIf);
						Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> mapaComercialOrdenMedioDetalle = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();
						
						//COMENTED 22 JUNIO
						/*
						 * codigo = getMaximoCodigoOrdenMedio(ordenMedioIf.getCodigo());
							int codigoOrdenMedio = 1;
							if (!codigo.equals("[null]")) {
								codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
								codigoOrdenMedio = Integer.parseInt(codigo.split(ordenMedioIf.getCodigo())[1]) + 1;
							}
							ordenMedioIf.setCodigo(ordenMedioIf.getCodigo() + formatoSerial.format(codigoOrdenMedio));
						*/
					
						//ADD 22 JUNIO
						String codigoOrden = ordenMedioIf.getCodigo().trim();
						if (codigoOrden.length() < 10){
							codigo = getMaximoCodigoOrdenMedio(ordenMedioIf.getCodigo());
							int codigoOrdenMedio = 1;
							if (!codigo.equals("[null]")) {
								codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
								codigoOrdenMedio = Integer.parseInt(codigo.split(ordenMedioIf.getCodigo())[1]) + 1;
							}
							ordenMedioIf.setCodigo(ordenMedioIf.getCodigo() + formatoSerial.format(codigoOrdenMedio));
						}
						//END 22 JUNIO
					
						ordenMedioIf.setPlanMedioId(planMedio.getPrimaryKey());	
						
						//ADD 6 JULIO
						ordenMedioIf.setFechaOrden(planMedioIf.getFechaInicio());
						//END 6 JULIO
						
						ordenMedioIf.setRevision(planMedioIf.getRevision());
						
						
						//busco y compara la nueva orden con las anteriores para ver si son iguales
						//y no es necesario aumentarle la versión
											
						/*int revisionOrden = 1;
						OrdenMedioIf ordenAnterior = null;
						String codigoOrdenNueva = ordenMedioIf.getCodigo();
						Collection ordenesConMismoCodigo = findOrdenMedioByCodigo(codigoOrdenNueva);
						Iterator ordenesConMismoCodigoIt = ordenesConMismoCodigo.iterator();
						while(ordenesConMismoCodigoIt.hasNext()){
							OrdenMedioIf ordenAnteriorTemp = (OrdenMedioIf)ordenesConMismoCodigoIt.next();
							int revisionOrdenAnterior = Integer.valueOf(ordenAnteriorTemp.getRevision());
							if(revisionOrdenAnterior > revisionOrden){
								revisionOrden = revisionOrdenAnterior;
								ordenAnterior = ordenAnteriorTemp;
							}else if(ordenAnterior == null){
								ordenAnterior = ordenAnteriorTemp;
							}
						}
						
						if(ordenAnterior != null){
							//si son iguales se queda con la revision anterior
							//si no aumenta en uno la revision
							
							if(ordenMedioIf.getComisionPura() == null){
								ordenMedioIf.setComisionPura("N");
							}
							
							if(ordenMedioIf.getClienteOficinaId().compareTo(ordenAnterior.getClienteOficinaId()) == 0
									&& ordenMedioIf.getProveedorId().compareTo(ordenAnterior.getProveedorId()) == 0
									&& utilitariosLocal.redondeoValor(ordenMedioIf.getValorTotal()).compareTo(utilitariosLocal.redondeoValor(ordenAnterior.getValorTotal())) == 0
									&& ordenMedioIf.getProductoProveedorId().compareTo(ordenAnterior.getProductoProveedorId()) == 0
									&& utilitariosLocal.redondeoValor(ordenMedioIf.getValorDescuento()).compareTo(utilitariosLocal.redondeoValor(ordenAnterior.getValorDescuento())) == 0
									&& utilitariosLocal.redondeoValor(ordenMedioIf.getValorIva()).compareTo(utilitariosLocal.redondeoValor(ordenAnterior.getValorIva())) == 0
									&& utilitariosLocal.redondeoValor(ordenMedioIf.getValorSubtotal()).compareTo(utilitariosLocal.redondeoValor(ordenAnterior.getValorSubtotal())) == 0
									&& utilitariosLocal.redondeoValor(ordenMedioIf.getPorcentajeDescuentoVenta()).compareTo(utilitariosLocal.redondeoValor(ordenAnterior.getPorcentajeDescuentoVenta())) == 0
									&& utilitariosLocal.redondeoValor(ordenMedioIf.getPorcentajeComisionAgencia()).compareTo(utilitariosLocal.redondeoValor(ordenAnterior.getPorcentajeComisionAgencia())) == 0
									&& utilitariosLocal.redondeoValor(ordenMedioIf.getValorDescuentoVenta()).compareTo(utilitariosLocal.redondeoValor(ordenAnterior.getValorDescuentoVenta())) == 0
									&& utilitariosLocal.redondeoValor(ordenMedioIf.getValorComisionAgencia()).compareTo(utilitariosLocal.redondeoValor(ordenAnterior.getValorComisionAgencia())) == 0
									&& utilitariosLocal.redondeoValor(ordenMedioIf.getPorcentajeCanje()).compareTo(utilitariosLocal.redondeoValor(ordenAnterior.getPorcentajeCanje())) == 0
									&& ordenMedioIf.getOrdenMedioTipo().equals(ordenAnterior.getOrdenMedioTipo())
									&& ordenMedioIf.getObservacion().equals(ordenAnterior.getObservacion())
									&& ordenMedioIf.getComisionPura().equals(ordenAnterior.getComisionPura())
									&& utilitariosLocal.redondeoValor(ordenMedioIf.getPorcentajeBonificacionCompra()).compareTo(utilitariosLocal.redondeoValor(ordenAnterior.getPorcentajeBonificacionCompra())) == 0
									&& utilitariosLocal.redondeoValor(ordenMedioIf.getPorcentajeBonificacionVenta()).compareTo(utilitariosLocal.redondeoValor(ordenAnterior.getPorcentajeBonificacionVenta())) == 0
									&& ordenMedioIf.getNumeroOrdenAgrupacion().compareTo(ordenAnterior.getNumeroOrdenAgrupacion()) == 0){
								ordenMedioIf.setRevision(ordenAnterior.getRevision());							
							}
							else{
								revisionOrden++;
								if(revisionOrden >= 10){
									ordenMedioIf.setRevision(String.valueOf(revisionOrden));
								}else{
									ordenMedioIf.setRevision("0" + String.valueOf(revisionOrden));
								}
							}
							
						}else{
							ordenMedioIf.setRevision("01");
						}*/
						
						
						
						OrdenMedioIf ordenMedio = registrarOrdenMedio(ordenMedioIf);
						
						ordenMedio = addOrdenMedio(ordenMedio);
	
						Iterator ordenMedioDetallesHashMIt = ordenMedioDetallesHashM.keySet().iterator();
						while(ordenMedioDetallesHashMIt.hasNext()){
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetallesHashMIt.next();
							ArrayList<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaList = ordenMedioDetallesHashM.get(ordenMedioDetalleIf);
							ArrayList <OrdenMedioDetalleMapaIf> mapaComercialOrdenMedioDetalleMapa = new ArrayList<OrdenMedioDetalleMapaIf>();
							
							ordenMedioDetalleIf.setOrdenMedioId(ordenMedio.getPrimaryKey());
							//ordenMedioDetalleIf.setComercialId(comercialIf);
							OrdenMedioDetalleIf ordenMedioDetalle = registrarOrdenMedioDetalle(ordenMedioDetalleIf);
							ordenMedioDetalle = ordenMedioDetalleLocal.addOrdenMedioDetalle(ordenMedioDetalle);//guarda en la base el ordenMedioDetalleif
	
							for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf : ordenMedioDetalleMapaList){
								ordenMedioDetalleMapaIf.setOrdenMedioDetalleId(ordenMedioDetalle.getId());
								OrdenMedioDetalleMapaIf ordenMedioDetalleMapa = registrarOrdenMedioDetalleMapa(ordenMedioDetalleMapaIf);
								//OrdenMedioDetalleMapaSessionLocal ordenMedioDetalleMapaLocal;
								//guarda en la base la OrdenMedioDetalleMapa
								ordenMedioDetalleMapa = ordenMedioDetalleMapaLocal.addOrdenMedioDetalleMapa(ordenMedioDetalleMapa);//crear la función addOrdenMedioDetalleMapa en ordenmediodetallesession
								mapaComercialOrdenMedioDetalleMapa.add(ordenMedioDetalleMapa);
							}
							mapaComercialOrdenMedioDetalle.put(ordenMedioDetalle,mapaComercialOrdenMedioDetalleMapa);
						}
						mapaComercialOrdenesCompCreadas.put(ordenMedio, mapaComercialOrdenMedioDetalle);
					
					}
				}				
			}

			return mapaComercialOrdenesCompCreadas;
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Error al insertar información en OrdenMedio-OrdenMedioDetalle");
		}
	}	
	
	//COMENTED 28 JUNIO
	//MODIFIED 27 JUNIO
	/*@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Map<OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> procesarOrdenMedioXNuevaVersion(PlanMedioIf planMedioIf, Map<Long,Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> mapaClienteOficinaOrdenes,ArrayList<Map<Long,String>> listIdsEstadosOrdenesMedioToAnular) throws GenericBusinessException {
		try {
			Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> mapaComercialOrdenesCompCreadas= new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>();
			PlanMedioIf planMedio = planMedioLocal.registrarPlanMedio(planMedioIf);//me devuelve creado un plan de medio ejb
			planMedioLocal.savePlanMedio(planMedio);//actualiza el plan de medio en la base
			
			//ADD 22 JUNIO
			for (Map mapIdOrdenEstado : listIdsEstadosOrdenesMedioToAnular){
				Long idOrden = (Long) mapIdOrdenEstado.keySet().iterator().next();
				String estadoOrden = (String) mapIdOrdenEstado.get(idOrden);
				if (estadoOrden.compareTo("I") != 0)
					this.actualizarOrdenMedioEstado(idOrden,"A");//ESTADO ANULADA
			}				
			//END 22 JUNIO			
			
			String codigo = "";
			Iterator mapaClienteOficinaOrdenesIt = mapaClienteOficinaOrdenes.keySet().iterator();
			while(mapaClienteOficinaOrdenesIt.hasNext()){
				Long proveedorIf = (Long)mapaClienteOficinaOrdenesIt.next();
				Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> mapaOrdenes = mapaClienteOficinaOrdenes.get(proveedorIf);
				Iterator mapaOrdenesIt = mapaOrdenes.keySet().iterator();
				while(mapaOrdenesIt.hasNext()){
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf)mapaOrdenesIt.next();
					//AKI PREGUNTAR ESTADO DE LAS ORDENES 28 JUNIO
					
					//ADD 28 JUNIO
					if(ordenMedioIf.getEstado().compareTo("I") != 0){//si el estado de la Orden es diferente a Ingresada
					
						Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenMedioDetallesHashM = mapaOrdenes.get(ordenMedioIf);
						Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> mapaComercialOrdenMedioDetalle = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();
						
						//COMENTED 22 JUNIO
						/*
						 * codigo = getMaximoCodigoOrdenMedio(ordenMedioIf.getCodigo());
							int codigoOrdenMedio = 1;
							if (!codigo.equals("[null]")) {
								codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
								codigoOrdenMedio = Integer.parseInt(codigo.split(ordenMedioIf.getCodigo())[1]) + 1;
							}
							ordenMedioIf.setCodigo(ordenMedioIf.getCodigo() + formatoSerial.format(codigoOrdenMedio));
						* /
					
						//ADD 22 JUNIO
						String codigoOrden = ordenMedioIf.getCodigo().trim();
						if (codigoOrden.length() < 10){
							codigo = getMaximoCodigoOrdenMedio(ordenMedioIf.getCodigo());
							int codigoOrdenMedio = 1;
							if (!codigo.equals("[null]")) {
								codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
								codigoOrdenMedio = Integer.parseInt(codigo.split(ordenMedioIf.getCodigo())[1]) + 1;
							}
							ordenMedioIf.setCodigo(ordenMedioIf.getCodigo() + formatoSerial.format(codigoOrdenMedio));
						}
						//END 22 JUNIO
					
						ordenMedioIf.setPlanMedioId(planMedio.getPrimaryKey());	
						
						OrdenMedioIf ordenMedio = registrarOrdenMedio(ordenMedioIf);
						ordenMedio = addOrdenMedio(ordenMedio);
	
						Iterator ordenMedioDetallesHashMIt = ordenMedioDetallesHashM.keySet().iterator();
						while(ordenMedioDetallesHashMIt.hasNext()){
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetallesHashMIt.next();
							ArrayList<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaList = ordenMedioDetallesHashM.get(ordenMedioDetalleIf);
							ArrayList <OrdenMedioDetalleMapaIf> mapaComercialOrdenMedioDetalleMapa = new ArrayList<OrdenMedioDetalleMapaIf>();
							
							ordenMedioDetalleIf.setOrdenMedioId(ordenMedio.getPrimaryKey());
							//ordenMedioDetalleIf.setComercialId(comercialIf);
							OrdenMedioDetalleIf ordenMedioDetalle = registrarOrdenMedioDetalle(ordenMedioDetalleIf);
							ordenMedioDetalle = ordenMedioDetalleLocal.addOrdenMedioDetalle(ordenMedioDetalle);//guarda en la base el ordenMedioDetalleif
	
							for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf : ordenMedioDetalleMapaList){
								ordenMedioDetalleMapaIf.setOrdenMedioDetalleId(ordenMedioDetalle.getId());
								OrdenMedioDetalleMapaIf ordenMedioDetalleMapa = registrarOrdenMedioDetalleMapa(ordenMedioDetalleMapaIf);
								//OrdenMedioDetalleMapaSessionLocal ordenMedioDetalleMapaLocal;
								//guarda en la base la OrdenMedioDetalleMapa
								ordenMedioDetalleMapa = ordenMedioDetalleMapaLocal.addOrdenMedioDetalleMapa(ordenMedioDetalleMapa);//crear la función addOrdenMedioDetalleMapa en ordenmediodetallesession
								mapaComercialOrdenMedioDetalleMapa.add(ordenMedioDetalleMapa);
							}
							mapaComercialOrdenMedioDetalle.put(ordenMedioDetalle,mapaComercialOrdenMedioDetalleMapa);
						}
						mapaComercialOrdenesCompCreadas.put(ordenMedio, mapaComercialOrdenMedioDetalle);
					
					}//END ADD 28 JUNIO	
				}
				
			}

			return mapaComercialOrdenesCompCreadas;
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Error al insertar información en OrdenMedio-OrdenMedioDetalle");
		}
	}*/
	
	//COMENTED 27 JUNIO
	/*@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Map<OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> procesarOrdenMedioXNuevaVersion(PlanMedioIf planMedioIf, Map<Long,Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> mapaClienteOficinaOrdenes,ArrayList<Long> listIdsOrdenesMedioToAnular) throws GenericBusinessException {
		try {
			Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> mapaComercialOrdenesCompCreadas= new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>();
			PlanMedioIf planMedio = planMedioLocal.registrarPlanMedio(planMedioIf);//me devuelve creado un plan de medio ejb
			planMedioLocal.savePlanMedio(planMedio);//actualiza el plan de medio en la base
			
			//ADD 22 JUNIO
			for (Long idOrden : listIdsOrdenesMedioToAnular){
				this.actualizarOrdenMedioEstado(idOrden,"A");//ESTADO ANULADA
			}				
			//END 22 JUNIO			
			
			String codigo = "";
			Iterator mapaClienteOficinaOrdenesIt = mapaClienteOficinaOrdenes.keySet().iterator();
			while(mapaClienteOficinaOrdenesIt.hasNext()){
				Long proveedorIf = (Long)mapaClienteOficinaOrdenesIt.next();
				Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> mapaOrdenes = mapaClienteOficinaOrdenes.get(proveedorIf);
				Iterator mapaOrdenesIt = mapaOrdenes.keySet().iterator();
				while(mapaOrdenesIt.hasNext()){
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf)mapaOrdenesIt.next();
					Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenMedioDetallesHashM = mapaOrdenes.get(ordenMedioIf);
					Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> mapaComercialOrdenMedioDetalle = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();
					
					//COMENTED 22 JUNIO
					/*
					 * codigo = getMaximoCodigoOrdenMedio(ordenMedioIf.getCodigo());
						int codigoOrdenMedio = 1;
						if (!codigo.equals("[null]")) {
							codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
							codigoOrdenMedio = Integer.parseInt(codigo.split(ordenMedioIf.getCodigo())[1]) + 1;
						}
						ordenMedioIf.setCodigo(ordenMedioIf.getCodigo() + formatoSerial.format(codigoOrdenMedio));
					* /
					
					//ADD 22 JUNIO
					String codigoOrden = ordenMedioIf.getCodigo().trim();
					if (codigoOrden.length() < 10){
						codigo = getMaximoCodigoOrdenMedio(ordenMedioIf.getCodigo());
						int codigoOrdenMedio = 1;
						if (!codigo.equals("[null]")) {
							codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
							codigoOrdenMedio = Integer.parseInt(codigo.split(ordenMedioIf.getCodigo())[1]) + 1;
						}
						ordenMedioIf.setCodigo(ordenMedioIf.getCodigo() + formatoSerial.format(codigoOrdenMedio));
					}
					//END 22 JUNIO
					
					ordenMedioIf.setPlanMedioId(planMedio.getPrimaryKey());	
					
					OrdenMedioIf ordenMedio = registrarOrdenMedio(ordenMedioIf);
					ordenMedio = addOrdenMedio(ordenMedio);

					Iterator ordenMedioDetallesHashMIt = ordenMedioDetallesHashM.keySet().iterator();
					while(ordenMedioDetallesHashMIt.hasNext()){
						OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetallesHashMIt.next();
						ArrayList<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaList = ordenMedioDetallesHashM.get(ordenMedioDetalleIf);
						ArrayList <OrdenMedioDetalleMapaIf> mapaComercialOrdenMedioDetalleMapa = new ArrayList<OrdenMedioDetalleMapaIf>();
						
						ordenMedioDetalleIf.setOrdenMedioId(ordenMedio.getPrimaryKey());
						//ordenMedioDetalleIf.setComercialId(comercialIf);
						OrdenMedioDetalleIf ordenMedioDetalle = registrarOrdenMedioDetalle(ordenMedioDetalleIf);
						ordenMedioDetalle = ordenMedioDetalleLocal.addOrdenMedioDetalle(ordenMedioDetalle);//guarda en la base el ordenMedioDetalleif

						for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf : ordenMedioDetalleMapaList){
							ordenMedioDetalleMapaIf.setOrdenMedioDetalleId(ordenMedioDetalle.getId());
							OrdenMedioDetalleMapaIf ordenMedioDetalleMapa = registrarOrdenMedioDetalleMapa(ordenMedioDetalleMapaIf);
							//OrdenMedioDetalleMapaSessionLocal ordenMedioDetalleMapaLocal;
							//guarda en la base la OrdenMedioDetalleMapa
							ordenMedioDetalleMapa = ordenMedioDetalleMapaLocal.addOrdenMedioDetalleMapa(ordenMedioDetalleMapa);//crear la función addOrdenMedioDetalleMapa en ordenmediodetallesession
							mapaComercialOrdenMedioDetalleMapa.add(ordenMedioDetalleMapa);
						}
						mapaComercialOrdenMedioDetalle.put(ordenMedioDetalle,mapaComercialOrdenMedioDetalleMapa);
					}
					mapaComercialOrdenesCompCreadas.put(ordenMedio, mapaComercialOrdenMedioDetalle);
				}
				
			}

			return mapaComercialOrdenesCompCreadas;
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Error al insertar información en OrdenMedio-OrdenMedioDetalle");
		}
	}*/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Map<OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> procesarOrdenMedio(PlanMedioIf planMedioIf, Map<String,Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> mapaClienteOficinaOrdenes) throws GenericBusinessException {
		try {
			Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> mapaComercialOrdenesCompCreadas= new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>();
			PlanMedioIf planMedio = planMedioLocal.registrarPlanMedio(planMedioIf);//me devuelve creado un plan de medio ejb
			planMedioLocal.savePlanMedio(planMedio);//actualiza el plan de medio en la base
			
			String codigo = "";
			Iterator mapaClienteOficinaOrdenesIt = mapaClienteOficinaOrdenes.keySet().iterator();
			while(mapaClienteOficinaOrdenesIt.hasNext()){
				String[] key = ((String) mapaClienteOficinaOrdenesIt.next()).split("-");
				Long proveedorIf = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> mapaOrdenes = mapaClienteOficinaOrdenes.get(String.valueOf(proveedorIf)+"-"+numeroOrden);
				Iterator mapaOrdenesIt = mapaOrdenes.keySet().iterator();
				while(mapaOrdenesIt.hasNext()){
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf)mapaOrdenesIt.next();
					Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenMedioDetallesHashM = mapaOrdenes.get(ordenMedioIf);
					Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> mapaComercialOrdenMedioDetalle = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();
					
					//codigo = getMaximoCodigoOrdenMedio(ordenMedioIf.getCodigo());
					codigo = getMaximoCodigoOrdenMedio(ordenMedioIf.getCodigo().trim());
					int codigoOrdenMedio = 1;
					if (!codigo.equals("[null]")) {
						codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
						codigoOrdenMedio = Integer.parseInt(codigo.split(ordenMedioIf.getCodigo())[1]) + 1;
					}
					ordenMedioIf.setCodigo(ordenMedioIf.getCodigo() + formatoSerial.format(codigoOrdenMedio));
					ordenMedioIf.setPlanMedioId(planMedio.getPrimaryKey());	
					
					//ADD 6 JULIO
					ordenMedioIf.setFechaOrden(planMedioIf.getFechaInicio());
					//END 6 JULIO
					ordenMedioIf.setNumeroOrdenAgrupacion(Integer.parseInt(numeroOrden));
					
					ordenMedioIf.setRevision("01");
					
					OrdenMedioIf ordenMedio = registrarOrdenMedio(ordenMedioIf);
					ordenMedio = addOrdenMedio(ordenMedio);

					Iterator ordenMedioDetallesHashMIt = ordenMedioDetallesHashM.keySet().iterator();
					while(ordenMedioDetallesHashMIt.hasNext()){
						OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetallesHashMIt.next();
						ArrayList<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaList = ordenMedioDetallesHashM.get(ordenMedioDetalleIf);
						ArrayList <OrdenMedioDetalleMapaIf> mapaComercialOrdenMedioDetalleMapa = new ArrayList<OrdenMedioDetalleMapaIf>();
						
						ordenMedioDetalleIf.setOrdenMedioId(ordenMedio.getPrimaryKey());
						//ordenMedioDetalleIf.setComercialId(comercialIf);
						OrdenMedioDetalleIf ordenMedioDetalle = registrarOrdenMedioDetalle(ordenMedioDetalleIf);
						ordenMedioDetalle = ordenMedioDetalleLocal.addOrdenMedioDetalle(ordenMedioDetalle);//guarda en la base el ordenMedioDetalleif

						for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf : ordenMedioDetalleMapaList){
							ordenMedioDetalleMapaIf.setOrdenMedioDetalleId(ordenMedioDetalle.getId());
							OrdenMedioDetalleMapaIf ordenMedioDetalleMapa = registrarOrdenMedioDetalleMapa(ordenMedioDetalleMapaIf);
							//OrdenMedioDetalleMapaSessionLocal ordenMedioDetalleMapaLocal;
							//guarda en la base la OrdenMedioDetalleMapa
							ordenMedioDetalleMapa = ordenMedioDetalleMapaLocal.addOrdenMedioDetalleMapa(ordenMedioDetalleMapa);//crear la función addOrdenMedioDetalleMapa en ordenmediodetallesession
							mapaComercialOrdenMedioDetalleMapa.add(ordenMedioDetalleMapa);
						}
						mapaComercialOrdenMedioDetalle.put(ordenMedioDetalle,mapaComercialOrdenMedioDetalleMapa);
					}
					mapaComercialOrdenesCompCreadas.put(ordenMedio, mapaComercialOrdenMedioDetalle);
				}
				
			}

			return mapaComercialOrdenesCompCreadas;
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Error al insertar información en OrdenMedio-OrdenMedioDetalle");
		}
	}
	
	
	public Map<OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> actualizarOrdenMedio(PlanMedioIf planMedioIf, Map<String,Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> mapaClienteOficinaOrdenes,boolean nuevoPlan, GenericoIf genericoPautaRegular) throws GenericBusinessException {
		try{
			Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> mapaComercialOrdenesCompCreadas= new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>();
			
			if(nuevoPlan){
				Collection<OrdenMedioIf>ordenMedioColl = (Collection<OrdenMedioIf>)findOrdenMedioByPlanMedioId(planMedioIf.getId());
				Iterator ordenMedioIt = ordenMedioColl.iterator();
				//ELIMINA LAS ORDENES DE MEDIO :S
				while(ordenMedioIt.hasNext()){
					OrdenMedioIf ordenMedioEliminado = (OrdenMedioIf)ordenMedioIt.next();
					if(!ordenMedioEliminado.getEstado().equals(ESTADO_ORDEN_INGRESADA)){
						this.eliminarOrdenMedio(ordenMedioEliminado.getId());
					}				
				}
			}	
			
			//actualizar los porcentajes con la funcion del save
			String codigo = "";
			int contador = 1;
			Iterator proveedorIdIt = mapaClienteOficinaOrdenes.keySet().iterator();
			while(proveedorIdIt.hasNext()){
				String[] key = ((String) proveedorIdIt.next()).split("-");
				Long proveedorId = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				Map listOrdenMedio = (Map)mapaClienteOficinaOrdenes.get(String.valueOf(proveedorId)+"-"+numeroOrden);
				Iterator ordenesMedioMapIt = listOrdenMedio.keySet().iterator();
				
				while(ordenesMedioMapIt.hasNext()){
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenesMedioMapIt.next();
					
					if(!ordenMedioIf.getEstado().equals(ESTADO_ORDEN_INGRESADA)){
						Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> mapaComercialOrdenMedioDetalle = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();
						
						if(nuevoPlan){
							codigo = getMaximoCodigoOrdenMedio(ordenMedioIf.getCodigo());
							int codigoOrdenMedio = 1;
							if (!codigo.equals("[null]")) {
								codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
								codigoOrdenMedio = Integer.parseInt(codigo.split(ordenMedioIf.getCodigo())[1]) + 1;
							}
							ordenMedioIf.setCodigo(ordenMedioIf.getCodigo() + formatoSerial.format(codigoOrdenMedio));
						}
						//ADD 6 JULIO
						ordenMedioIf.setFechaOrden(planMedioIf.getFechaInicio());
						//END 6 JULIO
						
						OrdenMedioEJB ordenMedio = registrarOrdenMedio(ordenMedioIf);  //revisar si está bien esa funcion
						ordenMedio.setPlanMedioId(planMedioIf.getId());
						if(ordenMedio.getId() != null)
							saveOrdenMedio(ordenMedio);
						else
							manager.persist(ordenMedio);
						
						ProductoIf productoPautaRegular = null;
						Map mapOrdenMedioDetalle = (Map)listOrdenMedio.get(ordenMedioIf);
						Iterator mapOrdenMedioDetalleIt = mapOrdenMedioDetalle.keySet().iterator();
						while(mapOrdenMedioDetalleIt.hasNext()){
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)mapOrdenMedioDetalleIt.next();
							ordenMedioDetalleIf.setOrdenMedioId(ordenMedio.getId());
							ArrayList <OrdenMedioDetalleMapaIf> mapaComercialOrdenMedioDetalleMapa = new ArrayList<OrdenMedioDetalleMapaIf>();
							if (productoPautaRegular == null) {
								/*Map mapProductoPautaRegular = new HashMap();
								mapProductoPautaRegular.put("genericoId", genericoPautaRegular.getId());
								mapProductoPautaRegular.put("proveedorId", ordenMedio.getProveedorId());
								productoPautaRegular = (ProductoIf) productoSessionLocal.findProductoByQuery(mapProductoPautaRegular).iterator().next();*/
								productoPautaRegular = (ProductoIf) productoSessionLocal.getProducto(ordenMedioIf.getProductoProveedorId());
							}
							ordenMedioDetalleIf.setProductoProveedorId(productoPautaRegular.getId());
							OrdenMedioDetalleEJB ordenMedioDetalle = registrarOrdenMedioDetalle(ordenMedioDetalleIf);
							if(ordenMedioDetalle.getId() != null) {
								ordenMedioDetalleLocal.saveOrdenMedioDetalle(ordenMedioDetalle);
							} else
								manager.persist(ordenMedioDetalle);
							
							ArrayList<OrdenMedioDetalleMapaIf> mapOrdenMedioDetalleMapa = (ArrayList<OrdenMedioDetalleMapaIf>)mapOrdenMedioDetalle.get(ordenMedioDetalleIf);
							
							for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf:mapOrdenMedioDetalleMapa){
								OrdenMedioDetalleMapaEJB ordenMedioDetalleMapa = registrarOrdenMedioDetalleMapa(ordenMedioDetalleMapaIf);
								ordenMedioDetalleMapa.setOrdenMedioDetalleId(ordenMedioDetalle.getId());
								if(ordenMedioDetalleMapa.getId() != null)
									ordenMedioDetalleMapaLocal.saveOrdenMedioDetalleMapa(ordenMedioDetalleMapa);
								else
									manager.persist(ordenMedioDetalleMapa);
								mapaComercialOrdenMedioDetalleMapa.add(ordenMedioDetalleMapa);
							}
							mapaComercialOrdenMedioDetalle.put(ordenMedioDetalle,mapaComercialOrdenMedioDetalleMapa);
						}
						ordenMedio.setProductoProveedorId(productoPautaRegular.getId());
						saveOrdenMedio(ordenMedio);
						mapaComercialOrdenesCompCreadas.put(ordenMedio, mapaComercialOrdenMedioDetalle);
					}
					else{
						//Si esta ingresada a la orden se le actualiza el plan de Medio
						OrdenMedioEJB ordenMedio = registrarOrdenMedio(ordenMedioIf);
						ordenMedio.setPlanMedioId(planMedioIf.getId());
						//manager.merge(ordenMedio);
						saveOrdenMedio(ordenMedio);
						
						Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> mapaComercialOrdenMedioDetalle = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();
						ArrayList <OrdenMedioDetalleMapaIf> mapaComercialOrdenMedioDetalleMapa = new ArrayList<OrdenMedioDetalleMapaIf>();
					
						Collection ordenMedioDetalleColeccion = ordenMedioDetalleLocal.findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
						Iterator ordenMedioDetalleColeccionIt = ordenMedioDetalleColeccion.iterator();
						while(ordenMedioDetalleColeccionIt.hasNext()){
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleColeccionIt.next();
							
							Collection ordenMedioDetalleMapaColeccion = ordenMedioDetalleMapaLocal.findOrdenMedioDetalleMapaByOrdenMedioDetalleId(ordenMedioDetalleIf.getId());
							Iterator ordenMedioDetalleMapaColeccionIt = ordenMedioDetalleMapaColeccion.iterator();
							while(ordenMedioDetalleMapaColeccionIt.hasNext()){
								OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf = (OrdenMedioDetalleMapaIf)ordenMedioDetalleMapaColeccionIt.next();
								
								mapaComercialOrdenMedioDetalleMapa.add(ordenMedioDetalleMapaIf);
							}
							
							mapaComercialOrdenMedioDetalle.put(ordenMedioDetalleIf,mapaComercialOrdenMedioDetalleMapa);
						}					
						
						mapaComercialOrdenesCompCreadas.put(ordenMedioIf, mapaComercialOrdenMedioDetalle);
					}			
				}
			}	
		
			return mapaComercialOrdenesCompCreadas;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error al actualizar informacion en Orden Medio");
		}
		
	}
	
	
	/*@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Map<OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> procesarOrdenMedio(PlanMedioIf planMedioIf, Map<Long,Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> mapaClienteOficinaOrdenes) throws GenericBusinessException {
		try {
			Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> mapaComercialOrdenesCompCreadas= new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>();
			PlanMedioIf planMedio = planMedioLocal.registrarPlanMedio(planMedioIf);//me devuelve creado un plan de medio ebj
			planMedioLocal.savePlanMedio(planMedio);//actualiza el plan de medio en la base
			
			String codigo = "";
			Iterator mapaClienteOficinaOrdenesIt = mapaClienteOficinaOrdenes.keySet().iterator();
			while(mapaClienteOficinaOrdenesIt.hasNext()){
				Long comercialIf = (Long)mapaClienteOficinaOrdenesIt.next();
				Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> mapaOrdenes = mapaClienteOficinaOrdenes.get(comercialIf);
				Iterator mapaOrdenesIt = mapaOrdenes.keySet().iterator();
				while(mapaOrdenesIt.hasNext()){
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf)mapaOrdenesIt.next();
					Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenMedioDetallesHashM = mapaOrdenes.get(ordenMedioIf);
					Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> mapaComercialOrdenMedioDetalle = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();
					
					codigo = getMaximoCodigoOrdenMedio(ordenMedioIf.getCodigo());
					int codigoOrdenMedio = 1;
					if (!codigo.equals("[null]")) {
						codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
						codigoOrdenMedio = Integer.parseInt(codigo.split(ordenMedioIf.getCodigo())[1]) + 1;
					}
					ordenMedioIf.setCodigo(ordenMedioIf.getCodigo() + formatoSerial.format(codigoOrdenMedio));
					ordenMedioIf.setPlanMedioId(planMedio.getPrimaryKey());	
					
					OrdenMedioIf ordenMedio = registrarOrdenMedio(ordenMedioIf);
					ordenMedio = addOrdenMedio(ordenMedio);

					Iterator ordenMedioDetallesHashMIt = ordenMedioDetallesHashM.keySet().iterator();
					while(ordenMedioDetallesHashMIt.hasNext()){
						OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetallesHashMIt.next();
						ArrayList<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaList = ordenMedioDetallesHashM.get(ordenMedioDetalleIf);
						ArrayList <OrdenMedioDetalleMapaIf> mapaComercialOrdenMedioDetalleMapa = new ArrayList<OrdenMedioDetalleMapaIf>();
						
						ordenMedioDetalleIf.setOrdenMedioId(ordenMedio.getPrimaryKey());
						ordenMedioDetalleIf.setComercialId(comercialIf);
						OrdenMedioDetalleIf ordenMedioDetalle = registrarOrdenMedioDetalle(ordenMedioDetalleIf);
						ordenMedioDetalle = ordenMedioDetalleLocal.addOrdenMedioDetalle(ordenMedioDetalle);//guarda en la base el ordenMedioDetalleif
						//REVISAR ZULLY
						//ordenMedioDetalle.setDescuentoPorcentaje(ordenMedio.getp);
						//ordenMedioDetalleLista.add(ordenMedioDetalleIf);
						for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf : ordenMedioDetalleMapaList){
							ordenMedioDetalleMapaIf.setOrdenMedioDetalleId(ordenMedioDetalle.getId());
							OrdenMedioDetalleMapaIf ordenMedioDetalleMapa = registrarOrdenMedioDetalleMapa(ordenMedioDetalleMapaIf);
							//OrdenMedioDetalleMapaSessionLocal ordenMedioDetalleMapaLocal;
							ordenMedioDetalleMapa = ordenMedioDetalleMapaLocal.addOrdenMedioDetalleMapa(ordenMedioDetalleMapa);//crear la función addOrdenMedioDetalleMapa en ordenmediodetallesession
							mapaComercialOrdenMedioDetalleMapa.add(ordenMedioDetalleMapa);
						}
						mapaComercialOrdenMedioDetalle.put(ordenMedioDetalle,mapaComercialOrdenMedioDetalleMapa);
					}
					mapaComercialOrdenesCompCreadas.put(ordenMedio, mapaComercialOrdenMedioDetalle);
				}
				
			}

			return mapaComercialOrdenesCompCreadas;
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Error al insertar información en OrdenMedio-OrdenMedioDetalle");
		}
	}*/
	
	
	/*@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Map<OrdenMedioIf, List<OrdenMedioDetalleIf>> procesarOrdenMedio(PlanMedioIf planMedioIf, Map<Long, Map<OrdenMedioIf,List<OrdenMedioDetalleIf>>> mapaClienteOficinaOrdenes) throws GenericBusinessException {
		
		try {
			Map<OrdenMedioIf, List<OrdenMedioDetalleIf>> mapaOrdenesCreadas = new HashMap<OrdenMedioIf, List<OrdenMedioDetalleIf>>();
			
			PlanMedioIf planMedio = planMedioLocal.registrarPlanMedio(planMedioIf);//me devuelve creado un plan de medio ebj
			planMedioLocal.savePlanMedio(planMedio);//actualiza el plan de medio en la base
			
			String codigo = "";
			Iterator mapaClienteOficinaOrdenesIt = mapaClienteOficinaOrdenes.keySet().iterator();
			while(mapaClienteOficinaOrdenesIt.hasNext()){
				Long comercialIf = (Long)mapaClienteOficinaOrdenesIt.next();
				Map<OrdenMedioIf,List<OrdenMedioDetalleIf>> mapaOrdenes = mapaClienteOficinaOrdenes.get(comercialIf);
				
				Iterator mapaOrdenesIt = mapaOrdenes.keySet().iterator();
				while(mapaOrdenesIt.hasNext()){
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf)mapaOrdenesIt.next();
					List<OrdenMedioDetalleIf> ordenMedioDetallesLista = mapaOrdenes.get(ordenMedioIf);
										
					codigo = getMaximoCodigoOrdenMedio(ordenMedioIf.getCodigo());
					int codigoOrdenMedio = 1;
					if (!codigo.equals("[null]")) {
						codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
						codigoOrdenMedio = Integer.parseInt(codigo.split(ordenMedioIf.getCodigo())[1]) + 1;
					}
					ordenMedioIf.setCodigo(ordenMedioIf.getCodigo() + formatoSerial.format(codigoOrdenMedio));
					ordenMedioIf.setPlanMedioId(planMedio.getPrimaryKey());	
					
					OrdenMedioIf ordenMedio = registrarOrdenMedio(ordenMedioIf);
					//manager.persist(ordenMedio);
					ordenMedio = addOrdenMedio(ordenMedio);
					
					List<OrdenMedioDetalleIf> ordenMedioDetalleLista = new ArrayList<OrdenMedioDetalleIf>();					
					for(OrdenMedioDetalleIf ordenMedioDetalleIf : ordenMedioDetallesLista){
						ordenMedioDetalleIf.setOrdenMedioId(ordenMedio.getPrimaryKey());
						ordenMedioDetalleIf.setComercialId(comercialIf);
						ordenMedioDetalleLocal.addOrdenMedioDetalle(registrarOrdenMedioDetalle(ordenMedioDetalleIf));//guarda en la base el ordenMedioDetalleif
						ordenMedioDetalleLista.add(ordenMedioDetalleIf);
					}
					
					mapaOrdenesCreadas.put(ordenMedio, ordenMedioDetalleLista);
				}
			}
			
			return mapaOrdenesCreadas;
			
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Error al insertar información en OrdenMedio-OrdenMedioDetalle");
		}
	}*/
	
	//MODIFIED 22 JUNIO
	private String getMaximoCodigoOrdenMedio(String codigoParcialOrdenMedio) {
		String queryString = "select max(codigo) from OrdenMedioEJB ot where ot.codigo like '" + codigoParcialOrdenMedio + "%'";// and ot.estado = '"+ "A" +"' ";
		Query query = manager.createQuery(queryString);
		return query.getResultList().toString();
	}
	//END MODIFIED 22 JUNIO
	
	//COMENTED 22 JUNIO
	/*private String getMaximoCodigoOrdenMedio(String codigoParcialOrdenMedio) {
		String queryString = "select max(codigo) from OrdenMedioEJB ot where ot.codigo like '" + codigoParcialOrdenMedio + "%'";
		Query query = manager.createQuery(queryString);
		return query.getResultList().toString();
	}*/
	
	private OrdenMedioEJB registrarOrdenMedio(OrdenMedioIf model) {
		OrdenMedioEJB ordenMedio = new OrdenMedioEJB();
		
		ordenMedio.setId(model.getId());
		ordenMedio.setCodigo(model.getCodigo());
		ordenMedio.setClienteOficinaId(model.getClienteOficinaId());
		ordenMedio.setPlanMedioId(model.getPlanMedioId());
		ordenMedio.setProveedorId(model.getProveedorId());
		ordenMedio.setUsuarioId(model.getUsuarioId());
		ordenMedio.setFechaCreacion(model.getFechaCreacion());
		ordenMedio.setProductoClienteId(model.getProductoClienteId());
		ordenMedio.setCampanaProductoVersionId(model.getCampanaProductoVersionId());
		ordenMedio.setEstado(model.getEstado());
		ordenMedio.setEmpleadoId(model.getEmpleadoId());
		ordenMedio.setFechaOrden(model.getFechaOrden());
		ordenMedio.setOficinaId(model.getOficinaId());
		ordenMedio.setProductoProveedorId(model.getProductoProveedorId());
		ordenMedio.setPorcentajeCanje(model.getPorcentajeCanje());
		ordenMedio.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
		ordenMedio.setPorcentajeDescuentoVenta(model.getPorcentajeDescuentoVenta());
		ordenMedio.setValorSubtotal(model.getValorSubtotal());
		ordenMedio.setValorDescuento(model.getValorDescuento());		
		ordenMedio.setValorIva(model.getValorIva());
		ordenMedio.setValorTotal(model.getValorTotal());
		ordenMedio.setValorDescuentoVenta(model.getValorDescuentoVenta());
		ordenMedio.setValorComisionAgencia(model.getValorComisionAgencia());
		ordenMedio.setOrdenMedioTipo(model.getOrdenMedioTipo());
		ordenMedio.setObservacion(model.getObservacion());
		
		if(model.getProductoProveedorId() != null)
			ordenMedio.setProductoProveedorId(model.getProductoProveedorId());
				
		ordenMedio.setPorcentajeNegociacionComision(model.getPorcentajeNegociacionComision());
		ordenMedio.setComisionPura(model.getComisionPura()!=null?model.getComisionPura():"N");
		ordenMedio.setPorcentajeBonificacionVenta(model.getPorcentajeBonificacionVenta());
		ordenMedio.setPorcentajeBonificacionCompra(model.getPorcentajeBonificacionCompra());
		ordenMedio.setNumeroOrdenAgrupacion(model.getNumeroOrdenAgrupacion());
		ordenMedio.setRevision(model.getRevision());
		
		if(model.getPorcentajeComisionAdicional() != null){
			ordenMedio.setPorcentajeComisionAdicional(model.getPorcentajeComisionAdicional());
		}else{
			ordenMedio.setPorcentajeComisionAdicional(new BigDecimal(0));
		}
		
		return ordenMedio;
	}
	
	private OrdenMedioDetalleEJB registrarOrdenMedioDetalle(OrdenMedioDetalleIf modelDetalle) {
		OrdenMedioDetalleEJB ordenMedioDetalle = new OrdenMedioDetalleEJB();

		ordenMedioDetalle.setId(modelDetalle.getId());
		ordenMedioDetalle.setOrdenMedioId(modelDetalle.getOrdenMedioId());
		ordenMedioDetalle.setComercialId(modelDetalle.getComercialId());
		ordenMedioDetalle.setPrograma(modelDetalle.getPrograma());
		ordenMedioDetalle.setHora(modelDetalle.getHora());
		ordenMedioDetalle.setComercial(modelDetalle.getComercial());
		ordenMedioDetalle.setValorTarifa(modelDetalle.getValorTarifa());
		ordenMedioDetalle.setValorSubtotal(modelDetalle.getValorSubtotal());
		ordenMedioDetalle.setValorDescuento(modelDetalle.getValorDescuento());
		ordenMedioDetalle.setValorIva(modelDetalle.getValorIva());
		ordenMedioDetalle.setValorTotal(modelDetalle.getValorTotal());
		ordenMedioDetalle.setPorcentajeDescuento(modelDetalle.getPorcentajeDescuento());
		ordenMedioDetalle.setPorcentajeDescuentoVenta(modelDetalle.getPorcentajeDescuentoVenta());
		ordenMedioDetalle.setPorcentajeComisionAgencia(modelDetalle.getPorcentajeComisionAgencia());
		ordenMedioDetalle.setValorDescuentoVenta(modelDetalle.getValorDescuentoVenta());
		ordenMedioDetalle.setValorComisionAgencia(modelDetalle.getValorComisionAgencia());
		ordenMedioDetalle.setTotalCunias(modelDetalle.getTotalCunias());
		ordenMedioDetalle.setProductoProveedorId(modelDetalle.getProductoProveedorId());
		ordenMedioDetalle.setProductoClienteId(modelDetalle.getProductoClienteId());
		ordenMedioDetalle.setCampanaProductoVersionId(modelDetalle.getCampanaProductoVersionId());
		ordenMedioDetalle.setPauta(modelDetalle.getPauta());
		ordenMedioDetalle.setAuspicioDescripcion(modelDetalle.getAuspicioDescripcion());
		
		return ordenMedioDetalle;
	}
	private OrdenMedioDetalleMapaEJB registrarOrdenMedioDetalleMapa(OrdenMedioDetalleMapaIf modelDetalleMapa) {
		OrdenMedioDetalleMapaEJB ordenMedioDetalleMapa = new OrdenMedioDetalleMapaEJB();
		ordenMedioDetalleMapa.setId(modelDetalleMapa.getId());
		ordenMedioDetalleMapa.setFecha(modelDetalleMapa.getFecha());
		ordenMedioDetalleMapa.setFrecuencia(modelDetalleMapa.getFrecuencia());
		ordenMedioDetalleMapa.setOrdenMedioDetalleId(modelDetalleMapa.getOrdenMedioDetalleId());

		return ordenMedioDetalleMapa;
	}
	
	//ADD 22 JUNIO
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarOrdenMedioEstado(Long ordenMedioId,String estado) throws GenericBusinessException {
		try{
			//OrdenMedioIf ordenMedio = registrarOrdenMedio(model);
			OrdenMedioEJB data = manager.find(OrdenMedioEJB.class, ordenMedioId);
			data.setEstado(estado);
			manager.merge(data);			
			
		} catch(Exception e){
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al actualizar el estado de la OrdenMedio");
		}
	}
	//END ADD 22 JUNIO
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarOrdenMedio(OrdenMedioIf model,List<OrdenMedioDetalleIf> modelDetalleList) throws GenericBusinessException {
		try{
			OrdenMedioIf ordenMedio = registrarOrdenMedio(model);
			manager.merge(ordenMedio);
			
			for (OrdenMedioDetalleIf modelDetalle : modelDetalleList) {
				
				modelDetalle.setOrdenMedioId(ordenMedio.getPrimaryKey());
				OrdenMedioDetalleIf ordenMedioDetalle = registrarOrdenMedioDetalle(modelDetalle);
				manager.merge(ordenMedioDetalle);
			}
		} catch(Exception e){
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al actualizar información en OrdenMedio-OrdenMedioDetalle");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarOrdenMedio(Long ordenMedioId) throws GenericBusinessException {
		try {
			OrdenMedioEJB data = manager.find(OrdenMedioEJB.class, ordenMedioId);
			Collection<OrdenMedioDetalleIf> modelDetalleList = ordenMedioDetalleLocal.findOrdenMedioDetalleByOrdenMedioId(ordenMedioId);
			
			for (OrdenMedioDetalleIf modelDetalle : modelDetalleList) {				
				Collection<OrdenMedioDetalleMapaIf> modelDetalleMapaList = ordenMedioDetalleMapaLocal.findOrdenMedioDetalleMapaByOrdenMedioDetalleId(modelDetalle.getId());
				for(OrdenMedioDetalleMapaIf modelDetalleMapa : modelDetalleMapaList){
					manager.remove(modelDetalleMapa);
				}
				manager.remove(modelDetalle);
			}			
			manager.remove(data);
			manager.flush();
			
		} catch (Exception e) {
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al eliminar información en Orden de Medios");
		}		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findOrdenMedioByQuery(int startIndex,int endIndex,Map aMap) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from OrdenMedioEJB " + objectName + " where "
		+ where;
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
	public int findOrdenMedioByQuerySize(Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = " select count (*) from OrdenMedioEJB " + objectName + " where "
		+ where;
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
	
	//ADD 14 JULIO MODIFIED 15 JULIO
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	//public java.util.Collection findOrdenMedioByQueryToCompra(Map aMap, Long idEmpresa) {
	public java.util.Collection findOrdenMedioByQueryToCompra(Map aMap, Long idEmpresa,Boolean medio) {
		
		String objectNameOM = "om";
		String whereOM = QueryBuilder.buildWhere(aMap, objectNameOM);
		
		String queryString = "select distinct om from OrdenMedioEJB " + objectNameOM + ", OficinaEJB fc where " + whereOM;
	
		queryString += " and om.oficinaId = fc.id and fc.empresaId = " + idEmpresa;
		
		if(medio)
			queryString +=  " and om.id not in (select distinct omd.id from OrdenMedioEJB omd, CompraEJB cp where cp.ordenMedioId = omd.id and cp.estado <> 'N')";
				
		queryString += " order by om.codigo desc";
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
	//END ADD 14 JULIO MODIFIED 15 JULIO
	
	//ADD 14 JULIO MODIFIED 15 JULIO
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getOrdenMedioByQueryListSize(Map aMap, java.lang.Long idEmpresa, boolean medio) {
		
		String objectNameOM = "om";
		String whereOM = QueryBuilder.buildWhere(aMap, objectNameOM);
		
		String queryString = "select distinct count(*) from OrdenMedioEJB " + objectNameOM + ", OficinaEJB fc where " + whereOM;
		
		queryString += " and om.oficinaId = fc.id and fc.empresaId = " + idEmpresa;
		
		if(medio)			
			queryString +=  " and om.id not in (select distinct omd.id from OrdenMedioEJB omd, CompraEJB cp where cp.ordenMedioId = omd.id and cp.estado <> 'N')";
				
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
		//log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}
	//ADD 14 JULIO MODIFIED 15 JULIO
	
	//ADD 18 JULIO
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection getOrdenMedioByQueryList(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa, Boolean medio) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
						
		String objectNameOM = "om";
		String where = QueryBuilder.buildWhere(aMap, objectNameOM);
		String queryString = "select distinct om from OrdenMedioEJB " + objectNameOM + ", OficinaEJB fc where " + where;
				
		queryString += " and om.oficinaId = fc.id and fc.empresaId = " + idEmpresa;
							
		if(medio)
			queryString +=  " and om.id not in (select distinct omd.id from OrdenMedioEJB omd, CompraEJB cp where cp.ordenMedioId = omd.id and cp.estado <> 'N')";
			//queryString +=  " and om.id not in (select distinct o.id from OrdenMedioEJB o, CompraEJB c where c.ordencompraId = o.id and c.estado <> 'N')";
		
					
		queryString += " order by om.codigo asc";
		
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
	//END 18 JULIO
	
	public java.util.Collection findMediaOrders(Long providerId) throws GenericBusinessException {
		String selectFromString = "select distinct om, c from OrdenMedioEJB om, ClienteOficinaEJB co, ClienteEJB c";
		String whereJoinsString = "where om.proveedorId = co.id and co.clienteId = c.id";
		String whereConditionsString = "and om.proveedorId = :providerId";
		String orderByString = "order by om.fechaOrden desc, om.codigo asc";
		String queryString = selectFromString + " " + whereJoinsString + " " + whereConditionsString + " " + orderByString;
		Query query = manager.createQuery(queryString);
		query.setParameter("providerId", providerId);
		return query.getResultList();
	}
	
	/*
	select pm.CODIGO, clo.DESCRIPCION, om.codigo, om.ESTADO, tp.NOMBRE, provOf.DESCRIPCION, 
	em.NOMBRES, em.APELLIDOS, om.FECHA_ORDEN, om.VALOR_SUBTOTAL, om.VALOR_DESCUENTO, om.VALOR_IVA, 
	om.VALOR_TOTAL
	from ORDEN_MEDIO om, PLAN_MEDIO pm, CLIENTE_OFICINA clo, EMPLEADO em, CLIENTE_OFICINA provOf, 
	CLIENTE prov, TIPO_PROVEEDOR tp, PRODUCTO_CLIENTE pc, MARCA_PRODUCTO mp, PLAN_MEDIO_MES pmm, 
	PLAN_MEDIO_DETALLE pmd
	where om.PLAN_MEDIO_ID = pm.ID and om.CLIENTE_OFICINA_ID = clo.ID and clo.ID = 7280 
	and om.PROVEEDOR_ID = provOf.ID and provOf.CLIENTE_ID = prov.ID and prov.TIPOPROVEEDOR_ID = tp.ID and 
	om.EMPLEADO_ID = em.ID and om.ESTADO <> 'A' and pmm.PLAN_MEDIO_ID = pm.ID and pmm.ID = pmd.PLAN_MEDIO_MES_ID
	and pmd.PRODUCTO_CLIENTE_ID = pc.ID and pc.MARCA_PRODUCTO_ID = mp.ID
	order by tp.NOMBRE, pm.CODIGO
	*/
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findOrdenesClientesByOrdenesMedios(Map aMap, java.sql.Date fechaInicio, java.sql.Date fechaFin, Long idEmpresa, boolean ordenarPorFecha, Long creadoPorId, boolean ordenarPorCodigoOrden, boolean ordenarPorCodigoPresupuesto) {
		String queryString = "select distinct pm.codigo, clo.descripcion, om.codigo, om.estado, tp.nombre, provOf.descripcion," +
				"em.nombres, em.apellidos, om.fechaOrden, om.valorSubtotal, om.valorDescuento, om.valorIva, mp.nombre ,pc.nombre, om.porcentajeBonificacionCompra, om.porcentajeBonificacionCompra, om.id " +
				"from OrdenMedioEJB om, PlanMedioEJB pm, ClienteOficinaEJB clo, EmpleadoEJB em, ClienteOficinaEJB provOf, " +
				"ClienteEJB prov, TipoProveedorEJB tp, ProductoClienteEJB pc, MarcaProductoEJB mp, PlanMedioMesEJB pmm, PlanMedioDetalleEJB pmd " +
				"where om.planMedioId = pm.id and om.clienteOficinaId = clo.id and " +
				"om.proveedorId = provOf.id and provOf.clienteId = prov.id and prov.tipoproveedorId = tp.id and " +
				"om.empleadoId = em.id and om.estado <> 'A' and pm.id = pmm.planMedioId and pmm.id = pmd.planMedioMesId and pmd.productoClienteId = pc.id " +
				"and pc.marcaProductoId = mp.id ";
		
		if(aMap.get("clienteoficinaId") != null){
			queryString += " and clo.id = " + aMap.get("clienteoficinaId") + "";
		}
		
		if(creadoPorId != null){
			queryString += " and em.id = " + creadoPorId + "";
		}
				
		if(aMap.get("tipoProveedorId") != null){
			queryString += " and tp.id = " + aMap.get("tipoProveedorId") + "";
		}
				
		if (fechaInicio != null && fechaFin != null)			
			queryString += " and om.fechaOrden between :fechaInicio and :fechaFin";
			
		if(ordenarPorFecha){
			queryString += " order by om.fechaOrden asc, pm.codigo, om.codigo asc";
		}else if(ordenarPorCodigoPresupuesto){
			queryString += " order by clo.descripcion, pm.codigo asc, om.codigo asc";
		}else if(ordenarPorCodigoOrden){
			queryString += " order by clo.descripcion, om.codigo asc";
		}
				
		Query query = manager.createQuery(queryString);			 
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
				
		return query.getResultList();
	}
}
