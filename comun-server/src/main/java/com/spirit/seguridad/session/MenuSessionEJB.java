package com.spirit.seguridad.session;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.seguridad.entity.MenuEJB;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.seguridad.session.generated._MenuSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;


@Stateless
public class MenuSessionEJB extends _MenuSession implements MenuSessionRemote,MenuSessionLocal  {

	@PersistenceContext(unitName="spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(MenuSessionEJB.class);

   
   public java.util.Collection findMenuByCodigoAndRolIdAndNivelMinimo(String codigo, Long rolId, int nivelMinimo) throws com.spirit.exception.GenericBusinessException {
	   // select m.* from menu m, rol_opcion ro where m.ID = ro.MENU_ID and ro.ROL_ID = 360448 and m.CODIGO like '%' and m.NIVEL >= 3 order by m.codigo
	   String queryString = "select distinct m from MenuEJB m, RolOpcionEJB ro where m.id = ro.menuId and ro.rolId = " + rolId + " and m.codigo like '" + codigo + "%' and m.nivel >= " + nivelMinimo + " and m.panel is not null order by m.codigo asc";
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }
   
   public java.util.Collection findMenuByCodigoAndNivelMinimo(String codigo, int nivelMinimo) throws com.spirit.exception.GenericBusinessException {
	   String queryString = "select distinct m from MenuEJB m where m.codigo = '" + codigo + "' and m.nivel >= " + nivelMinimo + " and m.panel is not null order by m.codigo asc";
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMenuListSortedByFavorito() {
      String queryString = "from MenuEJB e";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.padreId, e.codigo, e.favorito";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      return query.getResultList();
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMenuListSortedByCodigo() {
      String queryString = "from MenuEJB e";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.padreId, e.codigo";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      return query.getResultList();
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findTreeMenuByPadreId(java.lang.Long idMenuRaiz) {
	   String objectName = "m";
	   String queryString = "select distinct m from MenuEJB " + objectName + " start with m.id = " + idMenuRaiz + " connect by prior id = padreId order by m.favorito asc";
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findMenuByPadreIdAndByRolId(Long idRol, Long idMenuPadre){
	   String objectName = "m";
	   String queryString = "select distinct m from MenuEJB " + objectName + ", RolOpcionEJB ro where m.id = ro.menuId and m.padreId = " + idMenuPadre + " and ro.rolId = " + idRol + " order by m.favorito asc";
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findMenuByRolId(Long idRol) throws GenericBusinessException {
	   String objectName = "m";
	   String queryString = "select distinct m from MenuEJB " + objectName + ", RolOpcionEJB ro where m.id = ro.menuId and ro.rolId = " + idRol + " order by m.favorito asc";
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findMenuByGrandFatherId(java.lang.Long id) {
	   //select * from menu where padre_id in (select id from menu where id in (select id from menu where padre_id = 10))
	   String queryString = "select m from MenuEJB m where m.padreId in (select m.id from MenuEJB m where m.id in (select m.id from MenuEJB m where m.padreId = " + id + " )) order by m.favorito";
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findMenuByNombreAndByPadreId(String nombre, java.lang.Long padreId){
	   //select * from menu m where m.NOMBRE = 'MANTENIMIENTO' and m.PADRE_ID = 11
	   String queryString = "from MenuEJB m where m.nombre = '" + nombre + "' and m.padreId = " + padreId + "";
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findSubMenuByNombre(String nombre) {
	   String queryString = "from MenuEJB m where m.nombre = '" + nombre + "' and m.nivel >= 3";
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }
	
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void actualizarMenu(List<MenuIf> modelMenuList) throws GenericBusinessException{
	   try {
		   for (MenuIf modelMenu : modelMenuList) {

				MenuEJB menu = registrarMenuForUpdate(modelMenu);
				manager.merge(menu);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error al actualizar información en Menú");
		}
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void eliminarMenu(Long menuId, List<MenuIf> modelMenuList) throws GenericBusinessException{
	   try {
			MenuEJB data = manager.find(MenuEJB.class, menuId);

			for (MenuIf modelMenu : modelMenuList) {

				MenuEJB menu = registrarMenuForUpdate(modelMenu);
				manager.merge(menu);
			}

			manager.remove(data);
			manager.flush();

		} catch (Exception e) {
			log.error("Error al eliminar información en MenuEJB.", e);
			throw new GenericBusinessException("Error al eliminar información en Menu");
		}
   }
	   
   private MenuEJB registrarMenuForUpdate(MenuIf modelMenu) {
	    MenuEJB menu = new MenuEJB();
		
	    menu.setCodigo(modelMenu.getCodigo());
		menu.setFavorito(modelMenu.getFavorito());
		menu.setId(modelMenu.getId());
		menu.setNivel(modelMenu.getNivel());
		menu.setNombre(modelMenu.getNombre());
		menu.setPadreId(modelMenu.getPadreId());
		menu.setPanel(modelMenu.getPanel());
			
		return menu;
	}

}
