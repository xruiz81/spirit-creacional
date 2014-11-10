package com.spirit.seguridad.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.seguridad.entity.RolOpcionEJB;
import com.spirit.seguridad.entity.RolOpcionIf;
import com.spirit.seguridad.session.generated._RolOpcionSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>RolOpcionSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:18 $
 *
 */
@Stateless
public class RolOpcionSessionEJB extends _RolOpcionSession implements RolOpcionSessionRemote, RolOpcionSessionLocal {
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(RolOpcionSessionEJB.class);
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findRolOpcionByMenuAndUsuarioId(Long usuarioId,Long menuId) {
		String queryString = "SELECT distinct " +
				"rolOpcion " +
				"from " +
				"RolOpcionEJB rolOpcion, " +
				"RolUsuarioEJB rolUsuario " +
				"where rolOpcion.rolId = rolUsuario.rolId " +
				"and rolUsuario.usuarioId = :usuarioId "+
				"and rolOpcion.menuId = :menuId";
		
		Query query = manager.createQuery(queryString);
		query.setParameter("usuarioId", usuarioId);
		query.setParameter("menuId", menuId);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findRolOpcionByMenuCodigoAndByRolId(String codigoMenu, Long idRol) {
		String queryString = "select distinct ro from RolOpcionEJB ro, MenuEJB m where ro.menuId = m.id and m.codigo = :codigoMenu and ro.rolId = :idRol";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by ro.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("codigoMenu", codigoMenu);
		query.setParameter("idRol", idRol);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public RolOpcionEJB registrarRolOpcion(RolOpcionIf modelRolOpcion) {
		RolOpcionEJB rolOpcion = new RolOpcionEJB();
		
		rolOpcion.setId(modelRolOpcion.getId());
		rolOpcion.setRolId(modelRolOpcion.getRolId());
		rolOpcion.setMenuId(modelRolOpcion.getMenuId());
		rolOpcion.setGrabarActualizar(modelRolOpcion.getGrabarActualizar());
		rolOpcion.setBorrar(modelRolOpcion.getBorrar());
		rolOpcion.setConsultar(modelRolOpcion.getConsultar());
		rolOpcion.setAutorizar(modelRolOpcion.getAutorizar());
		rolOpcion.setDuplicar(modelRolOpcion.getDuplicar());
		rolOpcion.setImprimir(modelRolOpcion.getImprimir());
		rolOpcion.setGenerarGrafico(modelRolOpcion.getGenerarGrafico());
		rolOpcion.setNinguno(modelRolOpcion.getNinguno());
		
		return rolOpcion;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findRolOpcionAndMenuList() {
		String queryString = "select distinct ro, m from RolOpcionEJB ro, MenuEJB m where ro.menuId = m.id";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by m.favorito asc";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	
}
