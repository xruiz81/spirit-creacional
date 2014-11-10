package com.spirit.medios.session;

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

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.medios.entity.PropuestaDetalleEJB;
import com.spirit.medios.entity.PropuestaDetalleIf;
import com.spirit.medios.entity.PropuestaEJB;
import com.spirit.medios.entity.PropuestaIf;
import com.spirit.medios.session.generated._PropuestaSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>PropuestaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class PropuestaSessionEJB extends _PropuestaSession implements PropuestaSessionRemote  {

	@PersistenceContext(unitName="spirit")
	private EntityManager manager;
	
	@EJB 
	private PropuestaDetalleSessionLocal propuestaDetalleLocal; 
	
	@EJB 
	private UtilitariosSessionLocal utilitariosLocal;

   @Resource private SessionContext ctx;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
      
   public Collection findPropuestaByQueryAndByIdCliente(int startIndex,int endIndex,Map aMap, Long idCliente, Long idEmpresa) throws GenericBusinessException {
	   if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
	   String objectName = "m";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "select distinct m from PropuestaEJB " + objectName + ", OrdenTrabajoEJB ot, ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + where;
	 	queryString += " and m.ordentrabajoId = ot.id and ot.clienteoficinaId = co.id and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and co.clienteId = " + idCliente + " order by m.id desc";
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
   
   public int findPropuestaByQueryAndByIdClienteSize(Map aMap, Long idCliente, Long idEmpresa) throws GenericBusinessException {
	   String objectName = "m";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "select distinct count(*) from PropuestaEJB " + objectName + ", OrdenTrabajoEJB ot, ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + where;
	 	queryString += " and m.ordentrabajoId = ot.id and ot.clienteoficinaId = co.id and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and co.clienteId = " + idCliente + " order by m.id desc";
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
   public java.util.Collection findPropuestaByQuery(int startIndex,int endIndex,Map aMap, Long idEmpresa) {
	   if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
	   String objectName = "e";
	String where = QueryBuilder.buildWhere(aMap, objectName);
	String queryString = "select distinct e from PropuestaEJB " + objectName + ", OrdenTrabajoEJB ot, ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + where;
	queryString += " and e.ordentrabajoId = ot.id and ot.clienteoficinaId = co.id and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa;
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
   public int findPropuestaByQuerySize(Map aMap, Long idEmpresa) {
	   
	   String objectName = "e";
	String where = QueryBuilder.buildWhere(aMap, objectName);
	String queryString = "select distinct count(*) from PropuestaEJB " + objectName + ", OrdenTrabajoEJB ot, ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where " + where;
	queryString += " and e.ordentrabajoId = ot.id and ot.clienteoficinaId = co.id and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa;
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
   public Collection findPropuestaByEmpresaId(Long idEmpresa) throws GenericBusinessException {
	 	String queryString = "select distinct p from PropuestaEJB p, OrdenTrabajoEJB ot, EmpleadoEJB e, EmpresaEJB em where  p.ordentrabajoId = ot.id and ot.empleadoId = e.id and e.empresaId = em.id and em.id = " + idEmpresa + " order by p.id asc";
	 	Query query = manager.createQuery(queryString);
	 	return query.getResultList();	   
   }
   
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void procesarPropuesta(PropuestaIf model,List<PropuestaDetalleIf> modelDetalleList) throws GenericBusinessException {
		try {
			PropuestaIf propuesta = registrarPropuesta(model);
			manager.persist(propuesta);

			for (PropuestaDetalleIf modelDetalle : modelDetalleList) {

				modelDetalle.setPropuestaId(propuesta.getPrimaryKey());
				PropuestaDetalleIf propuestaDetalle = registrarPropuestaDetalle(modelDetalle);
				manager.merge(propuestaDetalle);
			}
		} catch (Exception e) {
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al insertar información en Propuesta-PropuestaDetalle");
		}
	}
	
	private PropuestaEJB registrarPropuesta(PropuestaIf model) {
		PropuestaEJB propuesta = new PropuestaEJB();
		
		propuesta.setId(model.getId());
		propuesta.setCodigo(model.getCodigo());
		propuesta.setOrdentrabajoId(model.getOrdentrabajoId());
		propuesta.setUsuarioId(model.getUsuarioId());
		propuesta.setFecha(model.getFecha());
		propuesta.setValor(utilitariosLocal.redondeoValor(model.getValor()));
		propuesta.setObservacion(model.getObservacion());
		propuesta.setEstado(model.getEstado());
				
		return propuesta;
	}
	
	private PropuestaDetalleIf registrarPropuestaDetalle(PropuestaDetalleIf modelDetalle) {
		PropuestaDetalleEJB propuestaDetalle = new PropuestaDetalleEJB();

		propuestaDetalle.setId(modelDetalle.getId());
		propuestaDetalle.setPropuestaId(modelDetalle.getPropuestaId());
		
		if(modelDetalle.getPresupuestoId() != null)
			propuestaDetalle.setPresupuestoId(modelDetalle.getPresupuestoId());
		if(modelDetalle.getPlanmedioId() != null)
			propuestaDetalle.setPlanmedioId(modelDetalle.getPlanmedioId());

		return propuestaDetalle;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarPropuesta(PropuestaIf model,List<PropuestaDetalleIf> modelDetalleList) throws GenericBusinessException {
		try {
			PropuestaIf propuesta = registrarPropuesta(model);
			manager.merge(propuesta);
	
			for (PropuestaDetalleIf modelDetalle : modelDetalleList) {
	
				modelDetalle.setPropuestaId(propuesta.getPrimaryKey());
				PropuestaDetalleIf propuestaDetalle = registrarPropuestaDetalle(modelDetalle);
				manager.merge(propuestaDetalle);
			}
		} catch (Exception e) {
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al actualizar información en Propuesta-PropuestaDetalle");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarPropuesta(Long propuestaId) throws GenericBusinessException {
		try {
			PropuestaEJB data = manager.find(PropuestaEJB.class, propuestaId);
			Collection<PropuestaDetalleIf> modelDetalleList = propuestaDetalleLocal.findPropuestaDetalleByPropuestaId(propuestaId);
			for (PropuestaDetalleIf modelDetalle : modelDetalleList) {
				manager.remove(modelDetalle);
			}
			manager.remove(data);
			manager.flush();
		} catch (Exception e) {
			ctx.setRollbackOnly();
			throw new GenericBusinessException(
					"Error al eliminar información en Propuesta");
		}

	}

}
