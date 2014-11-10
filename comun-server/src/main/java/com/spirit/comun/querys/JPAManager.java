package com.spirit.comun.querys;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.bi.EJBDataSource;
import com.spirit.bi.entity.BiFacturacionFactEJB;
import com.spirit.facturacion.entity.FacturaDetalleEJB;
import com.spirit.facturacion.entity.FacturaEJB;
import com.spirit.server.QueryBuilder;
import com.truemesh.squiggle.Column;
import com.truemesh.squiggle.Order;
import com.truemesh.squiggle.SelectQuery;
import com.truemesh.squiggle.Table;

@Stateless
public class JPAManager implements JPAManagerLocal {
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	private void addParameters(SelectQuery select, Table table,
			Map<String, Object> parametros) {
		// ESTO SE PUEDE AMPLIAR PARA USAR WILDCARDS
		// LIKE NOT LIKE, BETWEEN, ETC
		for (String parametro : parametros.keySet()) {
			select.addParameter(table, parametro);
		}
	}

	private String buildQueryString(Class clase, Map<String, Object> parametros) {
		String objectName = "e";

		String queryString = "from " + clase.getSimpleName() + " " + objectName;
		if (parametros != null && parametros.size() > 0) {
			String where = QueryBuilder.buildWhere(parametros, objectName);
			queryString += " where " + where;
		}

		return queryString;
		/*
		 * SelectQuery select = new SelectQuery(); Table table = new
		 * Table(clase); select.addObject(table); if (parametros != null &&
		 * parametros.size() > 0) addParameters(select, table, parametros);
		 * select.addOrder(new Order(new Column(table, "id"), true)); return
		 * select.getQueryString();
		 */
	}

	private Query buildQuery(String queryString) {
		Query query = manager.createQuery(queryString);
		// AQUI SE LE PUEDE SETEAR CUALQUIER PROPIEDAD
		// QUERY CACHE=true
		return query;
	}

	private Query buildQueryPaginado(String queryString, int first, int last) {
		Query query = buildQuery(queryString);
		query.setFirstResult(first);
		query.setMaxResults(last);
		return query;
	}

	private void setParameters(Query query, Map<String, Object> parametros) {
		if (parametros != null) {
			for (String parametro : parametros.keySet()) {
				query.setParameter(parametro, parametros.get(parametro));
			}
		}
	}

	public List singleClassList(Class clase) {
		String queryString = buildQueryString(clase, null);
		Query query = buildQuery(queryString);
		return query.getResultList();
	}

	public List singleClassListPaginado(Class clase, int startIndex,
			int endIndex) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String queryString = buildQueryString(clase, null);
		Query query = buildQueryPaginado(queryString, startIndex, endIndex
				- startIndex);
		return query.getResultList();
	}

	public List singleClassQueryList(Class clase, Map<String, Object> parametros) {
		String queryString = buildQueryString(clase, parametros);
		Query query = buildQuery(queryString);
		setParameters(query, parametros);
		return query.getResultList();
	}

	public List executeQueryList(String queryString,
			Map<String, Object> parametros) {
		Query query = buildQuery(queryString);
		setParameters(query, parametros);
		return query.getResultList();
	}

	public Object executeQuerySingle(String queryString,
			Map<String, Object> parametros) {
		Query query = buildQuery(queryString);
		setParameters(query, parametros);

		try {
			Object out = query.getSingleResult();
			return out;
		} catch (NoResultException ex) {
			return null;
		}

	}

	public void persist(Object value) {
		manager.persist(value);
		manager.flush();
		manager.refresh(value);
	}

	public void save(Object value) {
		manager.merge(value);
		manager.flush();
	}

	public void remove(Object value) {
		manager.remove(value);
		manager.flush();
	}

	public Object find(Class clase, Long id) {
		return manager.find(clase, id);
	}

	public Object find(Class clase, Integer id) {
		return manager.find(clase, id);
	}

	public EntityManager getManager() {
		return manager;
	}

	public List outerJoin(Class clazz1, String campo1, Class clazz2,
			String campo2) {

		List<Long> listaId = EJBDataSource.getJpaManagerLocal()
				.executeQueryList(
						"SELECT " + campo1 + " FROM " + clazz1.getName(), null);

		List<Long> listaOrigenId = EJBDataSource.getJpaManagerLocal()
				.executeQueryList(
						"SELECT " + campo2 + " FROM " + clazz2.getName(), null);

		listaId.removeAll(listaOrigenId);
		List lista = new ArrayList();
		for (Long id : listaId) {
			lista.add(find(clazz1, id));
		}
		return lista;
	}

}
