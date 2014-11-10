package com.spirit.general.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.general.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _ModuloSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ModuloSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.ModuloIf addModulo(com.spirit.general.entity.ModuloIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ModuloEJB value = new ModuloEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setStatus(model.getStatus());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en modulo.", e);
			throw new GenericBusinessException(
					"Error al insertar información en modulo.");
      }
     
      return getModulo(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveModulo(com.spirit.general.entity.ModuloIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ModuloEJB data = new ModuloEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setStatus(model.getStatus());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en modulo.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en modulo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteModulo(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ModuloEJB data = manager.find(ModuloEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en modulo.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en modulo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.ModuloIf getModulo(java.lang.Long id) {
      return (ModuloEJB)queryManagerLocal.find(ModuloEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getModuloList() {
	  return queryManagerLocal.singleClassList(ModuloEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getModuloList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ModuloEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getModuloListSize() {
      Query countQuery = manager.createQuery("select count(*) from ModuloEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findModuloById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ModuloEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findModuloByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(ModuloEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findModuloByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(ModuloEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findModuloByStatus(java.lang.String status) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("status", status);
		return queryManagerLocal.singleClassQueryList(ModuloEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ModuloIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findModuloByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ModuloEJB.class, aMap);      
    }

/////////////////
}
