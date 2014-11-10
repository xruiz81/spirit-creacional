package com.spirit.general.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.session.generated._EmpleadoSession;
import com.spirit.server.QueryBuilder;

/**
 * The <code>EmpleadoSession</code> session bean, which acts as a facade to
 * the underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:16 $
 * 
 */
@Stateless
public class EmpleadoSessionEJB extends _EmpleadoSession implements EmpleadoSessionRemote,
		EmpleadoSessionLocal {

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getEmpleadoList(int startIndex, int endIndex, Map aMap)
			throws GenericBusinessException {
		if (startIndex < 1) {
			startIndex = 1;
		}
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from EmpleadoEJB " + objectName + " where "
				+ where;
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		query.setFirstResult(startIndex - 1);
		query.setMaxResults(endIndex - startIndex + 1);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getEmpleadoList(int startIndex, int endIndex,
			Long idEmpresa) {
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String queryString = "from EmpleadoEJB e" + " where e.empresaId = "
				+ idEmpresa;
		// Add a an order by on all primary keys to assure reproducable results.
		queryString += " order by e.nombres asc, e.apellidos asc";
		Query query = manager.createQuery(queryString);
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getEmpleadoList(int startIndex, int endIndex, Map aMap,
			Long idEmpresa) throws GenericBusinessException {
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from EmpleadoEJB " + objectName + " where "
				+ where + " and e.empresaId = " + idEmpresa;
		queryString += " order by e.nombres asc, e.apellidos asc";
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		// query.setFirstResult(startIndex - 1);
		query.setFirstResult(startIndex);
		// query.setMaxResults(endIndex - startIndex + 1);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}

	public int getEmpleadoListSize(Long idEmpresa)
			throws GenericBusinessException {
		Query countQuery = manager
				.createQuery("select count(*) from EmpleadoEJB e where e.empresaId = "
						+ idEmpresa);
		List countQueryResult = countQuery.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();
	}

	public int getEmpleadoListSize(Map aMap) throws GenericBusinessException {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select count(*) from EmpleadoEJB " + objectName
				+ " where " + where;
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

	public int getEmpleadoListSize(Map aMap, Long idEmpresa)
			throws GenericBusinessException {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select count(*) from EmpleadoEJB " + objectName
				+ " where " + where + " and e.empresaId = " + idEmpresa;
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
	public Collection findEmpleadoByEmpresaIdAndByUsuario(Long idEmpresa,
			String usuario) {
		// select distinct e.* from empleado e, usuario u where e.ID =
		// u.EMPLEADO_ID and e.EMPRESA_ID = 1 and u.USUARIO = 'xruiz'
		String queryString = " select distinct e from EmpleadoEJB e, UsuarioEJB u " +
			" where e.id = u.empleadoId and e.empresaId = :idEmpresa and u.usuario = :usuario";
		Query query = manager.createQuery(queryString);
		query.setParameter("usuario", usuario);
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findEmpleadoByEmpresaIdAndTipoEmpleadoVendedor(Long idEmpresa, String vendedor) {
		String queryString = " select distinct e from EmpleadoEJB e, TipoEmpleadoEJB te " +
			" where e.tipoempleadoId = te.id and te.empresaId = :idEmpresa and te.vendedor = '" + vendedor + "' and e.estado = 'A'";
		Query query = manager.createQuery(queryString);
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}

	/**
	 * 
	 * Retrieves a list of data object for the specified query Map.
	 * 
	 * //@param Map $field.Name the field
	 * 
	 * @return Collection of EmpleadoIf data objects, empty list in case no
	 *         results were found.
	 * 
	 * Devuelve todos los empleados que tengan en tipoempleado_id, un id que
	 * tenga en la tabla tipo_empleado.vendedor='S'
	 */
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findEmpleadoByQueryVariosId(Map aMap) {
		Vector<String> tipoempleadoId_varios = (Vector) aMap
				.get("tipoempleadoId");
		aMap.remove("tipoempleadoId");

		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);

		String queryString = "from EmpleadoEJB " + objectName + " where "
				+ where;

		if (tipoempleadoId_varios != null && tipoempleadoId_varios.size() > 0) {
			queryString += "and  (";
			for (String estado : tipoempleadoId_varios) {
				queryString += (" e.tipoempleadoId = '" + estado + "' or");
				System.out.println("tipo empleado ID" + estado);
			}
			queryString = queryString.substring(0, queryString.length() - 3);
			queryString += " )";
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
	public Collection findVendedoresByOficinaId(Long idOficina) {
		// select e.NOMBRES, e.APELLIDOS from EMPLEADO e, TIPO_EMPLEADO te where
		// e.TIPOEMPLEADO_ID = te.ID and te.VENDEDOR = 'S' and e.OFICINA_ID = 2
		// and e.ESTADO = 'A'
		String queryString = "select distinct e from EmpleadoEJB e, TipoEmpleadoEJB te where e.tipoempleadoId = te.id and te.vendedor = 'S' and e.oficinaId = "
				+ idOficina + " and e.estado = 'A'";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findVendedoresByEmpresaId(Long idEmpresa) {
		// select e.NOMBRES, e.APELLIDOS from EMPLEADO e, TIPO_EMPLEADO te where
		// e.TIPOEMPLEADO_ID = te.ID and te.VENDEDOR = 'S' and e.OFICINA_ID = 2
		// and e.ESTADO = 'A'
		String queryString = "select distinct e from EmpleadoEJB e, TipoEmpleadoEJB te where e.tipoempleadoId = te.id and te.vendedor = 'S' and e.empresaId = "
				+ idEmpresa + " and e.estado = 'A'";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findVendedoresEmpleados() {		
		String queryString = "select distinct e from EmpleadoEJB e, TipoEmpleadoEJB te where e.tipoempleadoId = te.id and te.vendedor = 'S' and e.estado = 'A'";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	public Map mapearEmpleados(Long idEmpresa) {
		Map empleadosMap = new HashMap();
		Iterator empleadosIterator = findEmpleadoByEmpresaId(idEmpresa).iterator();
		while (empleadosIterator.hasNext()) {
			EmpleadoIf empleado = (EmpleadoIf) empleadosIterator.next();
			empleadosMap.put(empleado.getId(), empleado);
		}
		return empleadosMap;
	}
}
