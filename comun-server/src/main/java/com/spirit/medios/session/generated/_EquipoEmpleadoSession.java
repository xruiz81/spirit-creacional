package com.spirit.medios.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.medios.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _EquipoEmpleadoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_EquipoEmpleadoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.EquipoEmpleadoIf addEquipoEmpleado(com.spirit.medios.entity.EquipoEmpleadoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      EquipoEmpleadoEJB value = new EquipoEmpleadoEJB();
      try {
      value.setId(model.getId());
      value.setEquipoId(model.getEquipoId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setRol(model.getRol());
      value.setJefe(model.getJefe());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en equipoEmpleado.", e);
			throw new GenericBusinessException(
					"Error al insertar información en equipoEmpleado.");
      }
     
      return getEquipoEmpleado(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveEquipoEmpleado(com.spirit.medios.entity.EquipoEmpleadoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      EquipoEmpleadoEJB data = new EquipoEmpleadoEJB();
      data.setId(model.getId());
      data.setEquipoId(model.getEquipoId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setRol(model.getRol());
      data.setJefe(model.getJefe());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en equipoEmpleado.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en equipoEmpleado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteEquipoEmpleado(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      EquipoEmpleadoEJB data = manager.find(EquipoEmpleadoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en equipoEmpleado.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en equipoEmpleado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.EquipoEmpleadoIf getEquipoEmpleado(java.lang.Long id) {
      return (EquipoEmpleadoEJB)queryManagerLocal.find(EquipoEmpleadoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEquipoEmpleadoList() {
	  return queryManagerLocal.singleClassList(EquipoEmpleadoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEquipoEmpleadoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(EquipoEmpleadoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getEquipoEmpleadoListSize() {
      Query countQuery = manager.createQuery("select count(*) from EquipoEmpleadoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEquipoEmpleadoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(EquipoEmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEquipoEmpleadoByEquipoId(java.lang.Long equipoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("equipoId", equipoId);
		return queryManagerLocal.singleClassQueryList(EquipoEmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEquipoEmpleadoByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(EquipoEmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEquipoEmpleadoByRol(java.lang.String rol) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("rol", rol);
		return queryManagerLocal.singleClassQueryList(EquipoEmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEquipoEmpleadoByJefe(java.lang.String jefe) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("jefe", jefe);
		return queryManagerLocal.singleClassQueryList(EquipoEmpleadoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of EquipoEmpleadoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEquipoEmpleadoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(EquipoEmpleadoEJB.class, aMap);      
    }

/////////////////
}
