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
public abstract class _EmpleadoOficinaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_EmpleadoOficinaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.EmpleadoOficinaIf addEmpleadoOficina(com.spirit.general.entity.EmpleadoOficinaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      EmpleadoOficinaEJB value = new EmpleadoOficinaEJB();
      try {
      value.setId(model.getId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setOficinaId(model.getOficinaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en empleadoOficina.", e);
			throw new GenericBusinessException(
					"Error al insertar información en empleadoOficina.");
      }
     
      return getEmpleadoOficina(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveEmpleadoOficina(com.spirit.general.entity.EmpleadoOficinaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      EmpleadoOficinaEJB data = new EmpleadoOficinaEJB();
      data.setId(model.getId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setOficinaId(model.getOficinaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en empleadoOficina.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en empleadoOficina.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteEmpleadoOficina(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      EmpleadoOficinaEJB data = manager.find(EmpleadoOficinaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en empleadoOficina.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en empleadoOficina.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.EmpleadoOficinaIf getEmpleadoOficina(java.lang.Long id) {
      return (EmpleadoOficinaEJB)queryManagerLocal.find(EmpleadoOficinaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpleadoOficinaList() {
	  return queryManagerLocal.singleClassList(EmpleadoOficinaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpleadoOficinaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(EmpleadoOficinaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getEmpleadoOficinaListSize() {
      Query countQuery = manager.createQuery("select count(*) from EmpleadoOficinaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoOficinaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(EmpleadoOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoOficinaByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(EmpleadoOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoOficinaByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(EmpleadoOficinaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of EmpleadoOficinaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoOficinaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(EmpleadoOficinaEJB.class, aMap);      
    }

/////////////////
}
