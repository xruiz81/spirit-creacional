package com.spirit.medios.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.EquipoEmpleadoEJB;
import com.spirit.medios.entity.EquipoEmpleadoIf;
import com.spirit.medios.entity.EquipoTrabajoEJB;
import com.spirit.medios.entity.EquipoTrabajoIf;
import com.spirit.medios.session.generated._EquipoTrabajoSession;
import com.spirit.server.QueryBuilder;
import com.spirit.util.FindQuery;

/**
 * The <code>EquipoTrabajoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class EquipoTrabajoSessionEJB extends _EquipoTrabajoSession implements EquipoTrabajoSessionRemote  {

	@PersistenceContext(unitName="spirit")
	private EntityManager manager;


	@EJB 
	private EquipoEmpleadoSessionLocal equipoEmpleadoLocal;

	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void procesarEquipoTrabajo(EquipoTrabajoIf model,List<EquipoEmpleadoIf> modelDetalleList) throws GenericBusinessException {
		try {
			EquipoTrabajoEJB equipoTrabajo = registrarEquipoTrabajo(model);
			manager.persist(equipoTrabajo);

			for (EquipoEmpleadoIf modelDetalle : modelDetalleList) {

				modelDetalle.setEquipoId(equipoTrabajo.getPrimaryKey());
				EquipoEmpleadoEJB equipoEmpleado = registrarEquipoEmpleado(modelDetalle);
				manager.merge(equipoEmpleado);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error al insertar información en EquipoTrabajo-EquipoEmpleado");
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarEquipoTrabajo(EquipoTrabajoIf model,List<EquipoEmpleadoIf> modelDetalleList) throws GenericBusinessException {

		EquipoTrabajoIf equipoTrabajo = registrarEquipoTrabajo(model);
		manager.merge(equipoTrabajo);

		for (EquipoEmpleadoIf modelDetalle : modelDetalleList) {

			modelDetalle.setEquipoId(equipoTrabajo.getPrimaryKey());
			EquipoEmpleadoIf equipoEmpleado = registrarEquipoEmpleado(modelDetalle);
			manager.merge(equipoEmpleado);

		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarEquipoTrabajo(Long equipoTrabajoId) throws GenericBusinessException {
		try {
			EquipoTrabajoEJB data = manager.find(EquipoTrabajoEJB.class, equipoTrabajoId);
			Collection<EquipoEmpleadoIf> modelDetalleList = equipoEmpleadoLocal.findEquipoEmpleadoByEquipoId(equipoTrabajoId);

			for (EquipoEmpleadoIf modelDetalle : modelDetalleList) {
				manager.remove(modelDetalle);
			}
			manager.remove(data);
			manager.flush();

		} catch (Exception e) {
			throw new GenericBusinessException("Error al eliminar información en EquipoTrabajo");
		}
	}

	/**
	 * @param model
	 * @return
	 */
	private EquipoTrabajoEJB registrarEquipoTrabajo(EquipoTrabajoIf model) {
		EquipoTrabajoEJB equipoTrabajo = new EquipoTrabajoEJB();

		equipoTrabajo.setId(model.getId());
		equipoTrabajo.setCodigo(model.getCodigo());
		equipoTrabajo.setTipoordenId(model.getTipoordenId());
		equipoTrabajo.setEmpresaId(model.getEmpresaId());
		equipoTrabajo.setFechaini(model.getFechaini());
		equipoTrabajo.setFechafin(model.getFechafin());
		equipoTrabajo.setEstado(model.getEstado());

		return equipoTrabajo;
	}

	private EquipoEmpleadoEJB registrarEquipoEmpleado(EquipoEmpleadoIf modelDetalle) {
		EquipoEmpleadoEJB equipoEmpleado = new EquipoEmpleadoEJB();

		equipoEmpleado.setId(modelDetalle.getId());
		equipoEmpleado.setEquipoId(modelDetalle.getEquipoId());
		equipoEmpleado.setEmpleadoId(modelDetalle.getEmpleadoId());
		equipoEmpleado.setRol(modelDetalle.getRol());

		return equipoEmpleado;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection getEquipoTrabajoList(int startIndex, int endIndex,Map aMap) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
		String cadenaQuery = "from EquipoTrabajoEJB " + objectName + " where ";
		String orden = "";
		Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);

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
	public int getEquipoTrabajoListSize(Map aMap) {

		String objectName = "e";
		String cadenaQuery = "select distinct count(*) from EquipoTrabajoEJB " + objectName + " where ";
		String orden = "";
		Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);

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

	public String getMaximoCodigoEquipoTrabajo(Long empresaId, String tipoOrden) throws GenericBusinessException {
		String queryString = "select max(et.codigo) from EquipoTrabajoEJB et where et.empresaId = " + empresaId + " and et.codigo like '" + tipoOrden + "%'";
		//String queryString = "select max(ca.campanaId) from CampanaArchivoEJB ca";
		Query query = manager.createQuery(queryString);
		String codigo = "";
		if(query.getResultList().get(0)!=null){
			codigo = query.getResultList().toString().split(tipoOrden)[1].replaceAll("]","");
		}
		return codigo;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findEquipoTrabajoByEmpresaIdAndTipoOrdenId(java.lang.Long empresaId, java.lang.Long tipoOrdenId) {

		// Add a an order by on all primary keys to assure reproducable results.
		String objectName = "et";
		//String queryString = "select e from EmpleadoEJB " + objectName + ", ProductoClienteEJB pc where (e.id = pc.creativoId or e.id = pc.ejecutivoId) and pc.id = " + productoClienteId + " order by e.id ";
		String queryString = "select et from EquipoTrabajoEJB " + objectName + " where et.empresaId = " + empresaId + " and et.tipoordenId = " + tipoOrdenId + " and et.estado = 'A'" ;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getEquipoTrabajoList(int startIndex, int endIndex, Long empresaId, java.lang.Long tipoOrdenId) {
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String queryString = "select et from EquipoTrabajoEJB et where et.empresaId = " + empresaId + " and et.tipoordenId = " + tipoOrdenId ;
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by et.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}  

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findEquipoTrabajoByQueryAndByEmpleadoId(Map aMap, java.lang.Long empleadoId) {

		String objectName = "et";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select et from EquipoTrabajoEJB " + objectName + ", EquipoEmpleadoEJB ee where " + where + " and et.id = ee.equipoId and ee.empleadoId = " + empleadoId + "";
		
		Query query = manager.createQuery(queryString);
		Long empresaId = (Long)aMap.get("empresaId");
		query.setParameter("empresaId", empresaId);
		Long tipoordenId = (Long)aMap.get("tipoordenId");
		query.setParameter("tipoordenId", tipoordenId);
		String estado = (String)aMap.get("estado");
		query.setParameter("estado", estado);
		
		return query.getResultList();
	}
	
}
