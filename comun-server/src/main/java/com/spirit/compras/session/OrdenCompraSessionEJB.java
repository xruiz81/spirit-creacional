package com.spirit.compras.session;

import java.sql.Date;
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

import com.spirit.compras.entity.OrdenCompraDetalleEJB;
import com.spirit.compras.entity.OrdenCompraDetalleIf;
import com.spirit.compras.entity.OrdenCompraEJB;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.compras.handler.EstadoOrdenCompra;
import com.spirit.compras.session.generated._OrdenCompraSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.NumeradoresSessionLocal;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.session.PresupuestoDetalleSessionLocal;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>OrdenCompraSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class OrdenCompraSessionEJB extends _OrdenCompraSession implements OrdenCompraSessionRemote, OrdenCompraSessionLocal {
	
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	@EJB private SolicitudCompraSessionLocal solicitudCompraLocal;
	
	@EJB private SolicitudCompraDetalleSessionLocal solicitudCompraDetalleLocal;
	
	@EJB private PresupuestoDetalleSessionLocal presupuestoDetalleLocal; 
	
	@EJB private OrdenCompraDetalleSessionLocal ordenCompraDetalleLocal; 
	
	@EJB private NumeradoresSessionLocal numeradoresLocal;
	
	//@EJB private ProcesoPrincipalCompraLocal procesoPrincipalCompraLocal;
	
	private DecimalFormat formatoSerial = new DecimalFormat("00000");
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(OrdenCompraSessionEJB.class);
	
	@Resource private SessionContext ctx;
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findOrdenCompraByFechaInicioByFechaFinByOficinaIdAndByProveedorId(Date fechaInicio, Date fechaFin, Long idOficina, Long idProveedor) {
		//select * from orden_compra oc where oc.FECHA between TO_Date('2007-11-01','YYYY-MM-DD') and TO_Date('2007-11-30','YYYY-MM-DD')
		String queryString = "select distinct oc from OrdenCompraEJB oc where oc.oficinaId = " + idOficina + " and oc.proveedorId = " + idProveedor + " and oc.fecha between :fechaInicio and :fechaFin";
		queryString += " order by oc.codigo asc";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findOrdenCompraByPresupuestoId(Long presupuestoId) {
		String queryString = "select distinct oc from OrdenCompraEJB oc, PresupuestoDetalleEJB pd " +
				"where pd.presupuestoId = " + presupuestoId + " and pd.ordenCompraId = oc.id ";
		queryString += " order by oc.codigo asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int findOrdenCompraByQueryByPresupuestoIdAndByEstadosSize(Map aMap, Long presupuestoId, String[] estados) {
		
		String objectName = "oc";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		
		String queryString = "select distinct count(*) from OrdenCompraEJB oc, PresupuestoDetalleEJB pd " +
				"where " +  where + " and pd.ordenCompraId = oc.id  ";
		
		if(presupuestoId != null && presupuestoId != 0){
			queryString += " and pd.presupuestoId = " + presupuestoId + " ";
		}
		
		for(int i = 0; i < estados.length; i++){
			if(i == 0 && estados.length == 1){
				queryString += " and oc.estado = '" + estados[i] + "'";
			}else if (i == 0 && estados.length > 1){
				queryString += " and (oc.estado = '" + estados[i] + "'";
			}else if (i > 0 && i <= (estados.length -2)){
				queryString += " or oc.estado = '" + estados[i] + "'";
			}else if (i > 0 && i == (estados.length -1)){
				queryString += " or oc.estado = '" + estados[i] + "')";
			}
		}
		
		queryString += " order by oc.codigo desc";
		
		Query query = manager.createQuery(queryString);
		String codigo = (String) aMap.get("codigo");
		query.setParameter("codigo", codigo);
		
		if(aMap.get("proveedorId") != null){
			Long proveedorId = (Long) aMap.get("proveedorId");
			query.setParameter("proveedorId", proveedorId);
		}	
				
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findOrdenCompraByQueryByPresupuestoIdAndByEstados(int startIndex, int endIndex, Map aMap, Long presupuestoId, String[] estados) {
		
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		
		String objectName = "oc";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		
		String queryString = "select distinct oc from OrdenCompraEJB oc, PresupuestoDetalleEJB pd " +
		"where " +  where + " and pd.ordenCompraId = oc.id  ";

		if(presupuestoId != null && presupuestoId != 0){
			queryString += " and pd.presupuestoId = " + presupuestoId + " ";
		}

		for(int i = 0; i < estados.length; i++){
			if(i == 0 && estados.length == 1){
				queryString += " and oc.estado = '" + estados[i] + "'";
			}else if (i == 0 && estados.length > 1){
				queryString += " and (oc.estado = '" + estados[i] + "'";
			}else if (i > 0 && i <= (estados.length -2)){
				queryString += " or oc.estado = '" + estados[i] + "'";
			}else if (i > 0 && i == (estados.length -1)){
				queryString += " or oc.estado = '" + estados[i] + "')";
			}
		}
		
		queryString += " order by oc.codigo desc";
		
		Query query = manager.createQuery(queryString);
		String codigo = (String) aMap.get("codigo");
		query.setParameter("codigo", codigo);
		
		if(aMap.get("proveedorId") != null){
			Long proveedorId = (Long) aMap.get("proveedorId");
			query.setParameter("proveedorId", proveedorId);
		}
		
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection getOrdenCompraByQueryList(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa, Boolean compra) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		
		String arregloSolicitudesId = "";
		List<SolicitudCompraIf> solicitudCompraList = new ArrayList<SolicitudCompraIf>();
		if(aMap.get("solicitudesCompra") != null){
			solicitudCompraList = (ArrayList)aMap.get("solicitudesCompra");
			aMap.remove("solicitudesCompra");
			for(SolicitudCompraIf solicitudCompraIf : solicitudCompraList){
				if(arregloSolicitudesId.equals(""))
					arregloSolicitudesId = "oc.solicitudcompraId = '" + solicitudCompraIf.getId().toString() + "'";
				else
					arregloSolicitudesId = arregloSolicitudesId + " or " + "oc.solicitudcompraId = '" + solicitudCompraIf.getId().toString() + "'";
			}
		}		
		
		String objectName = "oc";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct oc from OrdenCompraEJB " + objectName + ", OficinaEJB fc where " + where;
		queryString += " and oc.oficinaId = fc.id and fc.empresaId = " + idEmpresa;
		
		if(!solicitudCompraList.isEmpty())
			queryString +=  " and (" + arregloSolicitudesId + ")";
				
		if(compra)
			queryString +=  " and oc.id not in (select distinct o.id from OrdenCompraEJB o, CompraEJB c where c.ordencompraId = o.id and c.estado <> 'N')";
		
		queryString += " order by oc.codigo desc";
		
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
	public int getOrdenCompraByQueryListSize(Map aMap, java.lang.Long idEmpresa, boolean compra) {
		
		String arregloSolicitudesId = "";
		List<SolicitudCompraIf> solicitudCompraList = new ArrayList<SolicitudCompraIf>();
		if(aMap.get("solicitudesCompra") != null){
			solicitudCompraList = (ArrayList)aMap.get("solicitudesCompra");
			aMap.remove("solicitudesCompra");
			for(SolicitudCompraIf solicitudCompraIf : solicitudCompraList){
				if(arregloSolicitudesId.equals(""))
					arregloSolicitudesId = "oc.solicitudcompraId = '" + solicitudCompraIf.getId().toString() + "'";
				else
					arregloSolicitudesId = arregloSolicitudesId + " or " + "oc.solicitudcompraId = '" + solicitudCompraIf.getId().toString() + "'";
			}
		}
		
		String objectName = "oc";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct count(*) from OrdenCompraEJB " + objectName + ", OficinaEJB fc where " + where;
		queryString += " and oc.oficinaId = fc.id and fc.empresaId = " + idEmpresa;
		
		if(!solicitudCompraList.isEmpty())
			queryString +=  " and (" + arregloSolicitudesId + ")";
				
		if(compra)
			queryString +=  " and oc.id not in (select distinct o.id from OrdenCompraEJB o, CompraEJB c where c.ordencompraId = o.id and c.estado <> 'N')";
		
			
		
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
	public java.util.Collection getOrdenCompraByMapList(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		Query query = getOrdenCompraByMapList(aMap, idEmpresa);
		
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getOrdenCompraByMapSize(Map aMap, java.lang.Long idEmpresa) {

		Query query = getOrdenCompraByMapList(aMap, idEmpresa);
		
		List countQueryResult = query.getResultList();
		Long countResult = Long.valueOf(countQueryResult.size());
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}
	
	private Query getOrdenCompraByMapList(Map aMap, java.lang.Long idEmpresa) {
		String codigo = (String) aMap.get("codigo");
		String descripcionCliente = (String) aMap.get("descripcionCliente");
		String observacionCompra = (String) aMap.get("observacionCompra");
		String estado = (String) aMap.get("estado");
		String queryString = "select oc from OrdenCompraEJB oc, ClienteOficinaEJB co, TipoDocumentoEJB td ";
		queryString += (" where oc.proveedorId = co.id and oc.tipodocumentoId = td.id and td.empresaId = "+idEmpresa);
		queryString += (" and oc.codigo like '"+ codigo +"'");
		if ( descripcionCliente != null )
			queryString += (" and co.descripcion like '"+descripcionCliente+"'");
		if ( observacionCompra != null )
			queryString += (" and oc.observacion like '"+observacionCompra+"'");
		if (estado != null)
			queryString += (" and oc.estado = '"+estado+"'");

		Query query = manager.createQuery(queryString);
		return query;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public OrdenCompraIf procesarOrdenCompra(OrdenCompraIf model, List<OrdenCompraDetalleIf> modelDetalleList, Long idEmpresa, long idTarea) throws GenericBusinessException {
		try {
			String codigo = getMaximoCodigoOrdenCompra(model.getCodigo(), idEmpresa);
			int codigoOrdenCompra = 1;
			if (!codigo.equals("[null]")) {
				codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
				codigoOrdenCompra = Integer.parseInt(codigo.split(model.getCodigo())[1]) + 1;
			}
			model.setCodigo(model.getCodigo() + formatoSerial.format(codigoOrdenCompra));
			OrdenCompraEJB ordenCompra = registrarOrdenCompra(model);
			manager.persist(ordenCompra);
			
			for (OrdenCompraDetalleIf modelDetalle : modelDetalleList) {
				
				modelDetalle.setOrdencompraId(ordenCompra.getPrimaryKey());
				OrdenCompraDetalleEJB ordenCompraDetalle = registrarOrdenCompraDetalle(modelDetalle);
				manager.merge(ordenCompraDetalle);
			}
			
			//procesoPrincipalCompraLocal.guardarOrdenPrincipalCompra(idTarea, ordenCompra.getId().toString(), ordenCompra.getObservacion());
		
			return ordenCompra;
			
		} /*catch(ComprasBpmException e){
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		}*/ catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al guardar la Orden de Compra");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public OrdenCompraIf procesarOrdenCompraRevision(OrdenCompraIf model, List<OrdenCompraDetalleIf> modelDetalleList) throws GenericBusinessException {
		try {			
			OrdenCompraEJB ordenCompra = registrarOrdenCompra(model);
			manager.persist(ordenCompra);
			
			for (OrdenCompraDetalleIf modelDetalle : modelDetalleList) {
				
				modelDetalle.setOrdencompraId(ordenCompra.getPrimaryKey());
				OrdenCompraDetalleEJB ordenCompraDetalle = registrarOrdenCompraDetalle(modelDetalle);
				manager.merge(ordenCompraDetalle);
			}
			
			
			return ordenCompra;
			
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al guardar la Orden de Compra");
		}
	}
	
	private String getMaximoCodigoOrdenCompra(String codigoParcialOrdenCompra, Long idEmpresa) {
		String queryString = "select max(oc.codigo) from OrdenCompraEJB oc, OficinaEJB o where oc.oficinaId = o.id and o.empresaId = " + idEmpresa + " and oc.codigo like '" + codigoParcialOrdenCompra + "%'";
		Query query = manager.createQuery(queryString);
		return query.getResultList().toString();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public OrdenCompraIf actualizarOrdenCompra(OrdenCompraIf model, List<OrdenCompraDetalleIf> modelDetalleList, List<OrdenCompraDetalleIf> modelDetalleRemovidoList, long idTarea) throws GenericBusinessException {
		
		try{
			OrdenCompraIf ordenCompra = registrarOrdenCompra(model);
			saveOrdenCompra(ordenCompra);
			//manager.merge(ordenCompra);
			Long idOrdenCompra = ordenCompra.getId()!=null?ordenCompra.getId():ordenCompra.getPrimaryKey();
			if (idOrdenCompra == null)
				throw new GenericBusinessException("Error en identificacion Orden Compra !!");
			
			for (OrdenCompraDetalleIf modelDetalleEliminado : modelDetalleRemovidoList) {
				OrdenCompraDetalleIf data = ordenCompraDetalleLocal.getOrdenCompraDetalle(modelDetalleEliminado.getId());
				ordenCompraDetalleLocal.deleteOrdenCompraDetalle(data.getId());
				//manager.remove(data);
			}
			
			for (OrdenCompraDetalleIf modelDetalle : modelDetalleList) {
				modelDetalle.setOrdencompraId(idOrdenCompra);
				OrdenCompraDetalleIf ordenCompraDetalle = registrarOrdenCompraDetalle(modelDetalle);
				if ( modelDetalle != null )
					ordenCompraDetalleLocal.saveOrdenCompraDetalle(ordenCompraDetalle);
				else 
					ordenCompraDetalleLocal.addOrdenCompraDetalle(ordenCompraDetalle);
			}
			
			HashMap<String, String> parametros = new HashMap<String, String>(); 
			parametros.put("tipoDocumentoActual", String.valueOf(model.getTipodocumentoId()));
			parametros.put("referenciaActual", model.getObservacion());
			/*if (idTarea!=0L)
				procesoPrincipalCompraLocal.actualizarParametrosProceso(idTarea, "", parametros);
			else {
				if (ordenCompra.getId()!=null){
					procesoPrincipalCompraLocal.actualizarParametrosProceso(ordenCompra.getId(), "idOrdenCompra", parametros);
				} else
					throw new GenericBusinessException("No hay Id de Solicitud de Compra para actualizacion en BPM");
			}*/
			
			ordenCompra = getOrdenCompra(idOrdenCompra);
			
			return ordenCompra;
			
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al actualizar la Orden de Compra");
		}
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarOrdenCompra(Long ordenCompraId) throws GenericBusinessException {
		try {
			
			OrdenCompraIf data = getOrdenCompra(ordenCompraId);
			data.setEstado(EstadoOrdenCompra.ANULADA.getLetra());
			saveOrdenCompra(data);
			
			/*Collection<OrdenCompraDetalleIf> modelDetalleList = ordenCompraDetalleLocal.findOrdenCompraDetalleByOrdencompraId(ordenCompraId);
			
			for (OrdenCompraDetalleIf modelDetalle : modelDetalleList){
				ordenCompraDetalleLocal.deleteOrdenCompraDetalle(modelDetalle.getId());
			}			
			deleteOrdenCompra(data.getId());
			
			SolicitudCompraIf solicitud = solicitudCompraLocal
			.getSolicitudCompra(data.getSolicitudcompraId());
			
			Collection<SolicitudCompraDetalleIf> detallesSolicitud = solicitudCompraDetalleLocal
				.findSolicitudCompraDetalleBySolicitudcompraId(solicitud.getId());
			for (SolicitudCompraDetalleIf scd : detallesSolicitud){
				solicitudCompraDetalleLocal.deleteSolicitudCompraDetalle(scd.getId());
			}
			solicitudCompraLocal.deleteSolicitudCompra(solicitud.getId());*/
			
			Map<String,Object> mapa = new HashMap<String, Object>();
			mapa.put("ordenCompraId", ordenCompraId);
			Collection<PresupuestoDetalleIf> detalles = presupuestoDetalleLocal
				.findPresupuestoDetalleByOrdenCompraId(ordenCompraId);
			for (PresupuestoDetalleIf pd : detalles){
				pd.setOrdenCompraId(null);
				presupuestoDetalleLocal.savePresupuestoDetalle(pd);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error al eliminar información en OrdenCompraEJB.", e);
			throw new GenericBusinessException(
			"Error al eliminar información en OrdenCompra");
		}
		
	}
	
	/*@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void autorizarOrdenCompra(boolean autorizar,Long idTarea) throws GenericBusinessException{
		try {
			procesoPrincipalCompraLocal.autorizarOrdenPrincipalCompra(autorizar, idTarea);
		} catch (ComprasBpmException e) {
			throw new GenericBusinessException(e.getMessage());
		}
	}*/
	
	/**
	 * @param model
	 * @return
	 */
	private OrdenCompraEJB registrarOrdenCompra(OrdenCompraIf model) {
		OrdenCompraEJB ordenCompra = new OrdenCompraEJB();
		
		ordenCompra.setId(model.getId());
		ordenCompra.setOficinaId(model.getOficinaId());
		ordenCompra.setTipodocumentoId(model.getTipodocumentoId());
		ordenCompra.setCodigo(model.getCodigo());
		ordenCompra.setProveedorId(model.getProveedorId());
		ordenCompra.setMonedaId(model.getMonedaId());
		ordenCompra.setEmpleadoId(model.getEmpleadoId());
		ordenCompra.setUsuarioId(model.getUsuarioId());
		ordenCompra.setBodegaId(model.getBodegaId());
		ordenCompra.setLocalimportada(model.getLocalimportada());
		ordenCompra.setFecha(model.getFecha());
		ordenCompra.setFechaRecepcion(model.getFechaRecepcion());
		ordenCompra.setFechaVencimiento(model.getFechaVencimiento());
		ordenCompra.setEstado(model.getEstado());
		ordenCompra.setObservacion(model.getObservacion());
		ordenCompra.setValor(model.getValor());
		ordenCompra.setDescuentoAgenciaCompra(model.getDescuentoAgenciaCompra());
		ordenCompra.setIva(model.getIva());
		ordenCompra.setIce(model.getIce());
		ordenCompra.setOtroImpuesto(model.getOtroImpuesto());
		ordenCompra.setSolicitudcompraId(model.getSolicitudcompraId());
		ordenCompra.setPorcentajeDescuentosVariosCompra(model.getPorcentajeDescuentosVariosCompra());
		ordenCompra.setPorcentajeDescuentosVariosVenta(model.getPorcentajeDescuentosVariosVenta());
		ordenCompra.setPorcentajeDescuentoEspecial(model.getPorcentajeDescuentoEspecial());
		ordenCompra.setRevision(model.getRevision());
		
		return ordenCompra;
	}
	
	private OrdenCompraDetalleEJB registrarOrdenCompraDetalle(OrdenCompraDetalleIf modelDetalle) {
		OrdenCompraDetalleEJB ordenCompraDetalle = new OrdenCompraDetalleEJB();
		
		ordenCompraDetalle.setId(modelDetalle.getId());
		ordenCompraDetalle.setDocumentoId(modelDetalle.getDocumentoId());
		ordenCompraDetalle.setProductoId(modelDetalle.getProductoId());
		ordenCompraDetalle.setOrdencompraId(modelDetalle.getOrdencompraId());
		ordenCompraDetalle.setCantidad(modelDetalle.getCantidad());
		ordenCompraDetalle.setValor(modelDetalle.getValor());
		ordenCompraDetalle.setDescuentoAgenciaCompra(modelDetalle.getDescuentoAgenciaCompra());
		ordenCompraDetalle.setIva(modelDetalle.getIva());
		ordenCompraDetalle.setIce(modelDetalle.getIce());
		ordenCompraDetalle.setOtroImpuesto(modelDetalle.getOtroImpuesto());
		ordenCompraDetalle.setDescripcion(modelDetalle.getDescripcion());
		ordenCompraDetalle.setPorcentajeNegociacionDirecta(modelDetalle.getPorcentajeNegociacionDirecta());
		ordenCompraDetalle.setPorcentajeComisionPura(modelDetalle.getPorcentajeComisionPura());
		ordenCompraDetalle.setPorcentajeDescuentosVariosCompra(modelDetalle.getPorcentajeDescuentosVariosCompra());
		ordenCompraDetalle.setPorcentajeDescuentoEspecial(modelDetalle.getPorcentajeDescuentoEspecial());
		ordenCompraDetalle.setFechaPublicacion(modelDetalle.getFechaPublicacion());
		
		return ordenCompraDetalle;
	}
			
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findOrdenCompraByQuery(Map aMap, Long idEmpresa) {
		String objectName = "oc";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct oc from OrdenCompraEJB " + objectName + ", OficinaEJB fc where " + where;
		queryString += " and oc.oficinaId = fc.id and fc.empresaId = " + idEmpresa;
		queryString +=  " and oc.id not in (select distinct o.id from OrdenCompraEJB o, CompraEJB c where c.ordencompraId = o.id and c.estado <> 'N')";
		queryString += " order by oc.codigo asc";
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
	
	public java.util.Collection findPurchaseOrders(Long providerId) throws GenericBusinessException {
		String selectFromString = "select distinct oc, c from OrdenCompraEJB oc, ClienteOficinaEJB co, ClienteEJB c";
		String whereJoinsString = "where oc.proveedorId = co.id and co.clienteId = c.id";
		String whereConditionsString = "and oc.proveedorId = :providerId";
		String orderByString = "order by oc.fecha desc, oc.codigo asc";
		String queryString = selectFromString + " " + whereJoinsString + " " + whereConditionsString + " " + orderByString;
		Query query = manager.createQuery(queryString);
		query.setParameter("providerId", providerId);
		return query.getResultList();
	}
}
