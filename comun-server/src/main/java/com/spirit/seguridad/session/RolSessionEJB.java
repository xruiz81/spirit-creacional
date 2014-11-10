package com.spirit.seguridad.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
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
import javax.swing.tree.DefaultMutableTreeNode;

import com.spirit.exception.GenericBusinessException;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.seguridad.entity.RolEJB;
import com.spirit.seguridad.entity.RolIf;
import com.spirit.seguridad.entity.RolOpcionData;
import com.spirit.seguridad.entity.RolOpcionEJB;
import com.spirit.seguridad.entity.RolOpcionIf;
import com.spirit.seguridad.session.generated._RolSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.util.Funciones;

/**
 * The <code>RolSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:17 $
 *
 */
@Stateless
public class RolSessionEJB extends _RolSession implements RolSessionRemote  {
	
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	@EJB 
	private RolOpcionSessionLocal rolOpcionLocal; 
	
	@EJB
	private MenuSessionLocal menuLocal;
	
	//@EJB 
	//private RolOpcionFuncionSessionLocal rolOpcionFuncionLocal; 
	
	public Map existeNodoFuncionesMapMP = new HashMap();
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(RolSessionEJB.class);
	
	@Resource private SessionContext ctx;
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findRolByQuery(int startIndex,int endIndex,Map aMap) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from RolEJB " + objectName + " where "
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
	public int getRolListSize(Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select count(*) from RolEJB " + objectName + " where "
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
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}
	
	/* public void procesarRol(RolIf model, DefaultMutableTreeNode nodoRaiz, Map existeNodoFuncionesMapMP) throws GenericBusinessException {
	 try {
	 
	 this.existeNodoFuncionesMapMP = existeNodoFuncionesMapMP;
	 
	 RolIf rol = registrarRol(model);
	 manager.persist(rol);
	 
	 saveFuncionesNodos(nodoRaiz, rol.getPrimaryKey(), false);
	 saveFuncionesNodoRaiz(rol.getPrimaryKey(), false);
	 
	 } catch (Exception e) {
	 log.debug("Error al insertar información en Rol - RolOpcion. ", e);
	 e.printStackTrace();
	 throw new GenericBusinessException("Error al insertar información en Rol-RolOpcion");
	 }
	 }*/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void procesarRol(RolIf model, List<RolOpcionIf> modelRolOpcionList) throws GenericBusinessException {
		try {
			RolIf rol = registrarRol(model);
			manager.persist(rol);
			
			for (RolOpcionIf modelRolOpcion : modelRolOpcionList) {
				modelRolOpcion.setRolId(rol.getPrimaryKey());
				RolOpcionEJB rolOpcion = rolOpcionLocal.registrarRolOpcion(modelRolOpcion);
				manager.merge(rolOpcion);
			}
			
		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al guardar información en RolEJB.", e);
			throw new GenericBusinessException("Error al insertar información en Rol-RolOpcion");
		}
	}
	
	/*@TransactionAttribute(TransactionAttributeType.REQUIRED)
	 public void actualizarRol(RolIf model, DefaultMutableTreeNode nodoRaiz, Map existeNodoFuncionesMapMP) throws GenericBusinessException {
	 
	 this.existeNodoFuncionesMapMP = existeNodoFuncionesMapMP;
	 
	 RolIf rol = registrarRol(model);
	 //eliminarReferencias(rol.getId());
	  //manager.flush();
	   manager.merge(rol);
	   
	   saveFuncionesNodos(nodoRaiz, rol.getPrimaryKey(), true);
	   saveFuncionesNodoRaiz(rol.getPrimaryKey(), true);
	   
	   }*/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarRol(RolIf model, List<RolOpcionIf> modelRolOpcionList) throws GenericBusinessException {
		try{
			RolIf rol = registrarRol(model);
			eliminarReferencias(rol.getId());
			manager.flush();
			manager.merge(rol);
			
			for (RolOpcionIf modelRolOpcion : modelRolOpcionList) {
				modelRolOpcion.setRolId(rol.getPrimaryKey());
				RolOpcionEJB rolOpcion = rolOpcionLocal.registrarRolOpcion(modelRolOpcion);
				manager.merge(rolOpcion);
			}
		} catch(Exception e){
			ctx.setRollbackOnly();
			log.error("Error al actualizar información en RolEJB.", e);
			throw new GenericBusinessException("Error al actualizar información en Rol-RolOpcion");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarRol(Long rolId) throws GenericBusinessException {
		try {
			RolEJB data = manager.find(RolEJB.class, rolId);
			eliminarReferencias(rolId);
			manager.remove(data);
			manager.flush();
			
		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al eliminar información en RolEJB.", e);
			throw new GenericBusinessException("Error al eliminar información en Rol");
		}
	}
	
	private RolEJB registrarRol(RolIf model) {
		RolEJB rol = new RolEJB();
		
		rol.setId(model.getId());
		rol.setCodigo(model.getCodigo());
		rol.setNombre(model.getNombre());
		rol.setStatus(model.getStatus());
		rol.setTipoRolUsuario(model.getTipoRolUsuario());
		rol.setEmpresaId(model.getEmpresaId());
		
		return rol;
	}
	
	private void saveFuncionesNodos(DefaultMutableTreeNode nodo, Long idRol, boolean esActualizacion) {
		try {
			// Mando a insertar cada uno de los nodos hijos
			if (nodo.getChildCount() >= 0) {
				for (Enumeration e = nodo.children(); e.hasMoreElements();) {
					DefaultMutableTreeNode nodoHijoMP = (DefaultMutableTreeNode) e.nextElement();
					String codigoNodoHijoMP = (nodoHijoMP.toString().split("\\("))[1].substring(0, 6);
					// Obtengo el id del nodo hijo segun el codigo de este
					Long idNodoHijoMP = ((MenuIf) menuLocal.findMenuByCodigo(codigoNodoHijoMP).iterator().next()).getId();
					
					RolOpcionIf modelRolOpcion = new RolOpcionData();
					modelRolOpcion.setRolId(idRol);
					modelRolOpcion.setMenuId(idNodoHijoMP);
					Funciones oFunciones = (Funciones) existeNodoFuncionesMapMP.get(codigoNodoHijoMP);
					//RolOpcionIf rolOpcion = rolOpcionLocal.registrarRolOpcion(modelRolOpcion, oFunciones);
					
					//if (!esActualizacion)
					//manager.persist(rolOpcion);
					//else
					//manager.merge(rolOpcion);
					
					/*RolOpcionFuncionIf modelRolOpcionFuncion = new RolOpcionFuncionData();
					 modelRolOpcionFuncion.setRolopcionId(rolOpcion.getPrimaryKey());
					 Funciones oFunciones = (Funciones) existeNodoFuncionesMapMP.get(codigoNodoHijoMP);
					 RolOpcionFuncionIf rolOpcionFuncion = rolOpcionFuncionLocal.registrarRolOpcionFuncion(modelRolOpcionFuncion, oFunciones);
					 manager.merge(rolOpcionFuncion);*/
					
					// Mando a insertar los hijos del nodo hijo si es que tiene
					saveFuncionesNodos(nodoHijoMP, idRol, esActualizacion);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void saveFuncionesNodoRaiz(Long idRol, boolean esActualizacion) {
		try {
			RolOpcionIf modelRolOpcion = new RolOpcionData();
			modelRolOpcion.setRolId(idRol);
			MenuIf menuRaiz = (MenuIf) menuLocal.findMenuByCodigo("RAIZ").iterator().next();
			modelRolOpcion.setMenuId(menuRaiz.getId());
			Funciones oFunciones = (Funciones) existeNodoFuncionesMapMP.get("RAIZ");
			//RolOpcionIf rolOpcion = rolOpcionLocal.registrarRolOpcion(modelRolOpcion, oFunciones);
			
			//if (!esActualizacion)
			//manager.persist(rolOpcion);
			//else
			//manager.merge(rolOpcion);
			
			/*RolOpcionFuncionIf modelRolOpcionFuncion = new RolOpcionFuncionData();
			 modelRolOpcionFuncion.setRolopcionId(rolOpcion.getPrimaryKey());
			 Funciones oFunciones = (Funciones) existeNodoFuncionesMapMP.get("RAIZ");
			 RolOpcionFuncionIf rolOpcionFuncion = rolOpcionFuncionLocal.registrarRolOpcionFuncion(modelRolOpcionFuncion, oFunciones);
			 manager.merge(rolOpcionFuncion);*/
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void eliminarReferencias(Long idRol) {
		try {
			Collection<RolOpcionIf> modelRolOpcionList = rolOpcionLocal.findRolOpcionByRolId(idRol);
			
			for (RolOpcionIf modelRolOpcion : modelRolOpcionList) {
				manager.remove(modelRolOpcion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
