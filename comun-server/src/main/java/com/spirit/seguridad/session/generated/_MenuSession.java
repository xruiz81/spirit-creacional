package com.spirit.seguridad.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.seguridad.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _MenuSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_MenuSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.seguridad.entity.MenuIf addMenu(com.spirit.seguridad.entity.MenuIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      MenuEJB value = new MenuEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setPadreId(model.getPadreId());
      value.setNivel(model.getNivel());
      value.setFavorito(model.getFavorito());
      value.setPanel(model.getPanel());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en menu.", e);
			throw new GenericBusinessException(
					"Error al insertar información en menu.");
      }
     
      return getMenu(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveMenu(com.spirit.seguridad.entity.MenuIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      MenuEJB data = new MenuEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setPadreId(model.getPadreId());
      data.setNivel(model.getNivel());
      data.setFavorito(model.getFavorito());
      data.setPanel(model.getPanel());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en menu.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en menu.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteMenu(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      MenuEJB data = manager.find(MenuEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en menu.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en menu.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.seguridad.entity.MenuIf getMenu(java.lang.Long id) {
      return (MenuEJB)queryManagerLocal.find(MenuEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMenuList() {
	  return queryManagerLocal.singleClassList(MenuEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMenuList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(MenuEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getMenuListSize() {
      Query countQuery = manager.createQuery("select count(*) from MenuEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMenuById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(MenuEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMenuByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(MenuEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMenuByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(MenuEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMenuByPadreId(java.lang.Long padreId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("padreId", padreId);
		return queryManagerLocal.singleClassQueryList(MenuEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMenuByNivel(java.lang.Integer nivel) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nivel", nivel);
		return queryManagerLocal.singleClassQueryList(MenuEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMenuByFavorito(java.lang.Integer favorito) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("favorito", favorito);
		return queryManagerLocal.singleClassQueryList(MenuEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMenuByPanel(java.lang.String panel) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("panel", panel);
		return queryManagerLocal.singleClassQueryList(MenuEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of MenuIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMenuByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(MenuEJB.class, aMap);      
    }

/////////////////
}
