package com.spirit.general.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.session.generated._UsuarioSession;
import com.spirit.server.QueryBuilder;

/**
 * The <code>UsuarioSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class UsuarioSessionEJB extends _UsuarioSession implements UsuarioSessionRemote, UsuarioSessionLocal  {
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	public Collection getUsuarioList(int startIndex,int endIndex,Map aMap) {
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select "+objectName+" from UsuarioEJB " + objectName + " where "
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
	
	public int getUsuarioListSize(Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select count(*) from UsuarioEJB " + objectName + " where " + where;
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
	public java.util.Collection findUsuarioAndEmpleadoAndRolesByUsuario(java.lang.String usuario) {
		String queryString = "select distinct u, e, ru from UsuarioEJB u, EmpleadoEJB e, RolUsuarioEJB ru where u.usuario = :usuario and ((u.tipousuario = 'U' and u.empleadoId = e.id) or (u.tipousuario <> 'U' and u.empleadoId is null)) and ru.usuarioId = u.id";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by u.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("usuario", usuario);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findUsuarioAndRolesByUsuario(java.lang.String usuario) {
		String queryString = "select distinct u, ru from UsuarioEJB u, RolUsuarioEJB ru where u.usuario = :usuario and ru.usuarioId = u.id";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by u.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("usuario", usuario);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findUsuarioAndRolesByUsuarioAndEmpresaId(java.lang.String usuario, java.lang.Long empresaId) {
		String queryString = "select distinct u, ru from UsuarioEJB u, RolUsuarioEJB ru where u.usuario = :usuario and ru.usuarioId = u.id and u.empresaId = :empresaId";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by u.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("usuario", usuario);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findUsuarioAndRolesByUsuarioTipoSupervisor(java.lang.String usuario) {
		String queryString = "select distinct u, ru from UsuarioEJB u, RolUsuarioEJB ru where u.usuario = :usuario and ru.usuarioId = u.id and u.tipousuario = 'S'";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by u.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("usuario", usuario);
		return query.getResultList();
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findUsuarioAndRolesByUsuario(java.lang.String usuario,Long empresaId) {
		String queryString = "select distinct u, ru from UsuarioEJB u, RolUsuarioEJB ru where u.usuario = :usuario and ru.usuarioId = u.id and u.empresaId = :empresaId";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by u.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("usuario", usuario);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}
	
	public Map mapearUsuarios(Long idEmpresa) {
		Map usuariosMap = new HashMap();
		Iterator usuariosIterator = findUsuarioByEmpresaId(idEmpresa).iterator();
		while (usuariosIterator.hasNext()) {
			UsuarioIf usuario = (UsuarioIf) usuariosIterator.next();
			usuariosMap.put(usuario.getId(), usuario);
		}
		return usuariosMap;
	}
	
	public Collection findUsuarioByCredencialesAcceso(Map queryMap) throws com.spirit.exception.GenericBusinessException {
		String usuario = (String) queryMap.get("usuario");
		String clave = (String) queryMap.get("clave");
		Long empresaId = (Long) queryMap.get("empresaId");
		Long oficinaId = (Long) queryMap.get("oficinaId");
		String tipoUsuario = (String) queryMap.get("tipoUsuario");
		String select = "select distinct u";
		String from = "from UsuarioEJB u";
		String where = "where u.usuario = :usuario and u.clave = :clave";
		if (empresaId != null) {
			where += " and u.empresaId = :empresaId";
		} else {
			where += " and u.empresaId is null";
		}
		if (oficinaId != null && !tipoUsuario.equals("A") && !tipoUsuario.equals("S")) {
			from += ", EmpleadoEJB e";
			where += " and u.empleadoId = e.id and e.oficinaId = :oficinaId";
		} else {
			where += " and u.empleadoId is null";
		}
		String queryString = select + " " + from + " " + where;
		Query query = manager.createQuery(queryString);
		query.setParameter("usuario", usuario);
		query.setParameter("clave", clave);
		if (empresaId != null)
			query.setParameter("empresaId", empresaId);
		if (oficinaId != null && !tipoUsuario.equals("A") && !tipoUsuario.equals("S"))
			query.setParameter("oficinaId", oficinaId);
		return query.getResultList();
	}
}
