package com.spirit.comun.querys;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.persistence.EntityManager;

@Local
public interface JPAManagerLocal {

	public List singleClassQueryList(Class clase,
			Map<String, Object> parametros);
	
	public List singleClassList(Class clase);

	public List singleClassListPaginado(Class clase, int start, int last);

	public void persist(Object value);

	public void save(Object value);

	public void remove(Object value);
	
	public Object find(Class clase, Long id);
	
	public Object find(Class clase, Integer id);
	
	public List executeQueryList(String queryString,
			Map<String, Object> parametros);
	
	public Object executeQuerySingle(String queryString,
			Map<String, Object> parametros);
	
	public EntityManager getManager();
	
	public List outerJoin(Class clazz1, String campo1, Class clazz2,
			String campo2);

}
