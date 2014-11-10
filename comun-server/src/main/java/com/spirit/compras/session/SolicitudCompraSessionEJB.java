package com.spirit.compras.session;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
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

import com.spirit.bpm.compras.exception.ComprasBpmException;
import com.spirit.compras.entity.SolicitudCompraArchivoIf;
import com.spirit.compras.entity.SolicitudCompraDetalleEJB;
import com.spirit.compras.entity.SolicitudCompraDetalleIf;
import com.spirit.compras.entity.SolicitudCompraEJB;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.compras.session.generated._SolicitudCompraSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>SolicitudCompraSession</code> session bean, which acts as a
 * facade to the underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 * 
 */
@Stateless
public class SolicitudCompraSessionEJB extends _SolicitudCompraSession
		implements SolicitudCompraSessionLocal, SolicitudCompraSessionRemote {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	@EJB
	private SolicitudCompraDetalleSessionLocal solicitudCompraDetalleLocal;

	//@EJB
	//private ProcesoPrincipalCompraLocal procesoPrincipalCompraLocal;

	@EJB
	private SolicitudCompraArchivoSessionLocal solicitudCompraArchivoLocal;

	/**
	 * The logger object.
	 */
	private static Logger log = LogService
			.getLogger(SolicitudCompraSessionEJB.class);
	private DecimalFormat formatoSerial = new DecimalFormat("00000");

	@Resource
	private SessionContext ctx;

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 **************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public SolicitudCompraIf procesarSolicitudCompra(SolicitudCompraIf model,
			List<SolicitudCompraDetalleIf> modelSolicitudCompraDetalleList,
			Long idEmpresa) throws GenericBusinessException {
		SolicitudCompraEJB solicitudCompra = null;
		String mensajeError = "Error al insertar información en SolicitudCompra - SolicitudCompraDetalle\n";
		try {
			String codigo = getMaximoCodigoSolicitudCompra(model.getCodigo(),
					idEmpresa);
			int codigoSolicitudCompra = 1;
			if (!codigo.equals("[null]")) {
				codigo = codigo.substring(1, codigo.length()).replaceAll("]",
						"");
				codigoSolicitudCompra = Integer.parseInt(codigo.split(model
						.getCodigo())[1]) + 1;
			}
			model.setCodigo(model.getCodigo()
					+ formatoSerial.format(codigoSolicitudCompra));
			solicitudCompra = registrarSolicitudCompra(model);
			manager.persist(solicitudCompra);
			for (SolicitudCompraDetalleIf modelSolicitudCompraDetalle : modelSolicitudCompraDetalleList) {
				modelSolicitudCompraDetalle
						.setSolicitudcompraId(solicitudCompra.getPrimaryKey());
				SolicitudCompraDetalleEJB solicitudCompraDetalle = registrarSolicitudCompraDetalle(modelSolicitudCompraDetalle);
				manager.merge(solicitudCompraDetalle);
			}
			// procesoPrincipalCompraLocal.iniciarInstanciaPrincipalCompras(solicitudCompra.getId().toString(),model.getTipodocumentoId().toString(),model.getObservacion());
		} /*
			 * catch (ComprasBpmException e) { ctx.setRollbackOnly();
			 * log.error(mensajeError, e); throw new
			 * GenericBusinessException(mensajeError+e.getMessage()); }
			 */catch (Exception e) {
			ctx.setRollbackOnly();
			log.error(mensajeError, e);
			throw new GenericBusinessException(mensajeError);
		}

		return solicitudCompra;
	}

	private String getMaximoCodigoSolicitudCompra(
			String codigoParcialSolicitudCompra, Long idEmpresa) {
		String queryString = "select max(sc.codigo) from SolicitudCompraEJB sc, OficinaEJB fc where sc.oficinaId = fc.id and fc.empresaId = "
				+ idEmpresa
				+ " and sc.codigo like '"
				+ codigoParcialSolicitudCompra + "%'";
		Query query = manager.createQuery(queryString);
		return query.getResultList().toString();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarSolicitudCompra(
			SolicitudCompraIf model,
			List<SolicitudCompraDetalleIf> modelSolicitudCompraDetalleList,
			List<SolicitudCompraDetalleIf> modelSolicitudCompraDetalleRemovidosList,
			long idTarea) throws GenericBusinessException {
		try {
			SolicitudCompraEJB solicitudCompra = registrarSolicitudCompra(model);
			manager.merge(solicitudCompra);

			for (SolicitudCompraDetalleIf modelSolicitudCompraDetalleRemovidos : modelSolicitudCompraDetalleRemovidosList) {
				SolicitudCompraDetalleEJB data = manager.find(
						SolicitudCompraDetalleEJB.class,
						modelSolicitudCompraDetalleRemovidos.getId());
				manager.remove(data);
			}
			for (SolicitudCompraDetalleIf modelSolicitudCompraDetalle : modelSolicitudCompraDetalleList) {
				SolicitudCompraDetalleEJB solicitudCompraDetalle = registrarSolicitudCompraDetalle(modelSolicitudCompraDetalle);
				manager.merge(solicitudCompraDetalle);
			}

			/*
			 * HashMap<String, String> parametros = new HashMap<String,
			 * String>(); parametros.put("tipoDocumentoActual",
			 * String.valueOf(model.getTipodocumentoId()));
			 * parametros.put("referenciaActual", model.getObservacion()); if
			 * (idTarea!=0L) {
			 * procesoPrincipalCompraLocal.actualizarParametrosProceso(idTarea,
			 * "", parametros); } else { if (solicitudCompra.getId()!=null){
			 * procesoPrincipalCompraLocal.actualizarParametrosProceso(solicitudCompra.getId(),
			 * "idSolicitudCompra", parametros); } else throw new
			 * GenericBusinessException("No hay Id de Solicitud de Compra para
			 * actualizacion en BPM"); }
			 */

		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(
					"Se ha producido un error al actualizar la Solicitud de Compra");
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarSolicitudCompra(Long solicitudCompraId, long idTarea)
			throws GenericBusinessException {
		String mensajeError = "Error al eliminar información en SolicitudCompra\n";
		try {
			SolicitudCompraEJB data = manager.find(SolicitudCompraEJB.class,
					solicitudCompraId);
			Collection<SolicitudCompraDetalleIf> modelDetalleList = solicitudCompraDetalleLocal
					.findSolicitudCompraDetalleBySolicitudcompraId(solicitudCompraId);
			for (SolicitudCompraDetalleIf modelDetalle : modelDetalleList) {
				manager.remove(modelDetalle);
			}

			manager.remove(data);
			manager.flush();

			/*if (idTarea != 0L) {
				procesoPrincipalCompraLocal.borrarInstanciaPrincipalCompras(
						idTarea, "",
						ProcesoPrincipalCompraEJB.AUTORIZAR_SOLICITUD_COMPRA);
			} else {
				procesoPrincipalCompraLocal.borrarInstanciaPrincipalCompras(
						solicitudCompraId.longValue(), "idSolicitudCompra",
						ProcesoPrincipalCompraEJB.AUTORIZAR_SOLICITUD_COMPRA);
			}*/

		} /*catch (ComprasBpmException e) {
			log.error("Error al eliminar información en SolicitudCompraEJB.",e);
			ctx.setRollbackOnly();
			throw new GenericBusinessException(mensajeError + e.getMessage());
		} */catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al eliminar información en SolicitudCompraEJB.",e);
			throw new GenericBusinessException(mensajeError);
		}
	}

	/*@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void autorizarSolicitudCompra(boolean autorizar, long idTarea)
			throws GenericBusinessException {
		try {
			procesoPrincipalCompraLocal.autorizarSolicitudPrincipalCompra(
					autorizar, idTarea);
		} catch (ComprasBpmException e) {
			throw new GenericBusinessException(e.getMessage());
		}
	}*/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	private SolicitudCompraEJB registrarSolicitudCompra(SolicitudCompraIf model) {
		SolicitudCompraEJB solicitudCompra = new SolicitudCompraEJB();

		solicitudCompra.setBodegaId(model.getBodegaId());
		solicitudCompra.setCodigo(model.getCodigo());
		solicitudCompra.setEmpleadoId(model.getEmpleadoId());
		solicitudCompra.setEstado(model.getEstado());
		solicitudCompra.setFecha(model.getFecha());
		solicitudCompra.setFechaEntrega(model.getFechaEntrega());
		solicitudCompra.setId(model.getId());
		solicitudCompra.setObservacion(model.getObservacion());
		solicitudCompra.setOficinaId(model.getOficinaId());
		solicitudCompra.setTipodocumentoId(model.getTipodocumentoId());
		solicitudCompra.setUsuarioId(model.getUsuarioId());
		solicitudCompra.setTipoReferencia(model.getTipoReferencia());
		solicitudCompra.setReferencia(model.getReferencia());

		return solicitudCompra;
	}

	private SolicitudCompraDetalleEJB registrarSolicitudCompraDetalle(
			SolicitudCompraDetalleIf modelSolicitudCompraDetalle) {
		SolicitudCompraDetalleEJB solicitudCompraDetalle = new SolicitudCompraDetalleEJB();

		solicitudCompraDetalle.setCantidad(modelSolicitudCompraDetalle
				.getCantidad());
		solicitudCompraDetalle.setDocumentoId(modelSolicitudCompraDetalle
				.getDocumentoId());
		solicitudCompraDetalle.setId(modelSolicitudCompraDetalle.getId());
		solicitudCompraDetalle.setProductoId(modelSolicitudCompraDetalle
				.getProductoId());
		solicitudCompraDetalle.setSolicitudcompraId(modelSolicitudCompraDetalle
				.getSolicitudcompraId());

		return solicitudCompraDetalle;
	}

	public java.util.Collection findSolicitudCompraByQuery(int startIndex,
			int endIndex, Map aMap) {
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from SolicitudCompraEJB " + objectName
				+ " where " + where;
		queryString += " order by e.codigo asc";
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

	public int findSolicitudCompraByQuerySize(Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct count(*) from SolicitudCompraEJB "
				+ objectName + " where " + where;
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

	public int findSolicitudCompraByQueryAndEmpresaIdSize(Map aMap,
			Long idEmpresa) {
		String objectName = "sc";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct count(*) from SolicitudCompraEJB "
				+ objectName + ", OficinaEJB o, EmpresaEJB e where " + where;
		queryString += " and sc.oficinaId = o.id and o.empresaId = e.id and e.id = "
				+ idEmpresa;
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

	public int findSolicitudCompraByQueryAndEmpresaIdSize(Map aMap,
			Long idEmpresa, Long idProveedor) {
		String objectName = "sc";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct sc from SolicitudCompraEJB "
				+ objectName
				+ ", SolicitudCompraDetalleEJB scd, OficinaEJB o, EmpresaEJB e, ProductoEJB p where "
				+ where;
		queryString += " and sc.id = scd.solicitudcompraId and sc.oficinaId = o.id and o.empresaId = e.id and e.id = "
				+ idEmpresa + " and scd.productoId = p.id";
		if (idProveedor != 0)
			queryString += " and p.proveedorId = " + idProveedor;

		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}
		List countQueryResult = query.getResultList();
		log.debug("The list size is: " + countQueryResult.size());
		return countQueryResult.size();
	}

	public java.util.Collection findSolicitudCompraByQueryAndEmpresaId(
			int startIndex, int endIndex, Map aMap, Long idEmpresa,
			Boolean ordenCompra) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "sc";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct sc from SolicitudCompraEJB "
				+ objectName + ", OficinaEJB o, EmpresaEJB e where " + where;
		queryString += " and sc.oficinaId = o.id and o.empresaId = e.id and e.id = "
				+ idEmpresa + " order by sc.codigo desc";
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

	public java.util.Collection findSolicitudCompraByQueryAndEmpresaId(
			int startIndex, int endIndex, Map aMap, Long idEmpresa,
			Long idProveedor, Boolean ordenCompra) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "sc";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct sc from SolicitudCompraEJB "
				+ objectName
				+ ", SolicitudCompraDetalleEJB scd, OficinaEJB o, EmpresaEJB e, ProductoEJB p where "
				+ where;
		queryString += " and sc.id = scd.solicitudcompraId and sc.oficinaId = o.id and o.empresaId = e.id and e.id = "
				+ idEmpresa + " and scd.productoId = p.id";
		if (idProveedor != 0)
			queryString += " and p.proveedorId = " + idProveedor;

		queryString += " order by sc.codigo desc";
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

	public java.util.Collection findSolicitudCompraByQueryAndEmpresaId(
			Map aMap, Long idEmpresa, Long idProveedor) {
		String objectName = "sc";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct sc from SolicitudCompraEJB "
				+ objectName
				+ ", SolicitudCompraDetalleEJB scd, OficinaEJB o, EmpresaEJB e, ProductoEJB p where "
				+ where;
		queryString += " and sc.id = scd.solicitudcompraId and sc.oficinaId = o.id and o.empresaId = e.id and e.id = "
				+ idEmpresa + " and scd.productoId = p.id";
		if (idProveedor != null)
			queryString += " and p.proveedorId = " + idProveedor;
		// queryString += " and sc.id not in (select distinct s.id from
		// SolicitudCompraEJB s, OrdenCompraEJB o where o.solicitudcompraId =
		// s.id)";
		queryString += " order by sc.codigo asc";
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

	public void actualizarArchivosSolicitudCompra(SolicitudCompraIf model,
			List<SolicitudCompraArchivoIf> modelArchivoList,
			List<SolicitudCompraArchivoIf> archivosEliminadosList,
			String urlCarpetaSevidor) throws GenericBusinessException {
		try {
			SolicitudCompraIf solicitudCompra = registrarSolicitudCompra(model);
			manager.merge(solicitudCompra);

			for (SolicitudCompraArchivoIf modelArchivoEliminado : archivosEliminadosList) {
				SolicitudCompraArchivoIf data = solicitudCompraArchivoLocal
						.getSolicitudCompraArchivo(modelArchivoEliminado
								.getId());
				manager.remove(data);
			}

			for (SolicitudCompraArchivoIf modelArchivo : modelArchivoList) {
				modelArchivo.setSolicitudCompraId(solicitudCompra
						.getPrimaryKey());
				SolicitudCompraArchivoIf solicitudArchivo = solicitudCompraArchivoLocal
						.registrarSolicitudCompraArchivo(modelArchivo,
								urlCarpetaSevidor);
				manager.merge(solicitudArchivo);
			}

			manager.flush();
		} catch (Exception e) {
			ctx.setRollbackOnly();
			throw new GenericBusinessException(
					"Error al actualizar información en Reunion");
		}
	}

}
