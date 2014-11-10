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
public abstract class _RolUsuarioSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_RolUsuarioSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.seguridad.entity.RolUsuarioIf addRolUsuario(com.spirit.seguridad.entity.RolUsuarioIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      RolUsuarioEJB value = new RolUsuarioEJB();
      try {
      value.setId(model.getId());
      value.setRolId(model.getRolId());
      value.setUsuarioId(model.getUsuarioId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en rolUsuario.", e);
			throw new GenericBusinessException(
					"Error al insertar información en rolUsuario.");
      }
     
      return getRolUsuario(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveRolUsuario(com.spirit.seguridad.entity.RolUsuarioIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      RolUsuarioEJB data = new RolUsuarioEJB();
      data.setId(model.getId());
      data.setRolId(model.getRolId());
      data.setUsuarioId(model.getUsuarioId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en rolUsuario.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en rolUsuario.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteRolUsuario(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      RolUsuarioEJB data = manager.find(RolUsuarioEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en rolUsuario.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en rolUsuario.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.seguridad.entity.RolUsuarioIf getRolUsuario(java.lang.Long id) {
      return (RolUsuarioEJB)queryManagerLocal.find(RolUsuarioEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getRolUsuarioList() {
	  return queryManagerLocal.singleClassList(RolUsuarioEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getRolUsuarioList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(RolUsuarioEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getRolUsuarioListSize() {
      Query countQuery = manager.createQuery("select count(*) from RolUsuarioEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolUsuarioById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(RolUsuarioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolUsuarioByRolId(java.lang.Long rolId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("rolId", rolId);
		return queryManagerLocal.singleClassQueryList(RolUsuarioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolUsuarioByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(RolUsuarioEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of RolUsuarioIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolUsuarioByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(RolUsuarioEJB.class, aMap);      
    }

/////////////////
}
