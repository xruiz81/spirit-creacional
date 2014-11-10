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
public abstract class _LogEquipoEmpleadoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_LogEquipoEmpleadoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.LogEquipoEmpleadoIf addLogEquipoEmpleado(com.spirit.medios.entity.LogEquipoEmpleadoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      LogEquipoEmpleadoEJB value = new LogEquipoEmpleadoEJB();
      try {
      value.setId(model.getId());
      value.setEquipoId(model.getEquipoId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setRol(model.getRol());
      value.setLog(model.getLog());
      value.setJefe(model.getJefe());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en logEquipoEmpleado.", e);
			throw new GenericBusinessException(
					"Error al insertar información en logEquipoEmpleado.");
      }
     
      return getLogEquipoEmpleado(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveLogEquipoEmpleado(com.spirit.medios.entity.LogEquipoEmpleadoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      LogEquipoEmpleadoEJB data = new LogEquipoEmpleadoEJB();
      data.setId(model.getId());
      data.setEquipoId(model.getEquipoId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setRol(model.getRol());
      data.setLog(model.getLog());
      data.setJefe(model.getJefe());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en logEquipoEmpleado.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en logEquipoEmpleado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteLogEquipoEmpleado(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      LogEquipoEmpleadoEJB data = manager.find(LogEquipoEmpleadoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en logEquipoEmpleado.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en logEquipoEmpleado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.LogEquipoEmpleadoIf getLogEquipoEmpleado(java.lang.Long id) {
      return (LogEquipoEmpleadoEJB)queryManagerLocal.find(LogEquipoEmpleadoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getLogEquipoEmpleadoList() {
	  return queryManagerLocal.singleClassList(LogEquipoEmpleadoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getLogEquipoEmpleadoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(LogEquipoEmpleadoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getLogEquipoEmpleadoListSize() {
      Query countQuery = manager.createQuery("select count(*) from LogEquipoEmpleadoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogEquipoEmpleadoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(LogEquipoEmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogEquipoEmpleadoByEquipoId(java.lang.Long equipoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("equipoId", equipoId);
		return queryManagerLocal.singleClassQueryList(LogEquipoEmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogEquipoEmpleadoByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(LogEquipoEmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogEquipoEmpleadoByRol(java.lang.String rol) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("rol", rol);
		return queryManagerLocal.singleClassQueryList(LogEquipoEmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogEquipoEmpleadoByLog(java.lang.String log) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("log", log);
		return queryManagerLocal.singleClassQueryList(LogEquipoEmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogEquipoEmpleadoByJefe(java.lang.String jefe) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("jefe", jefe);
		return queryManagerLocal.singleClassQueryList(LogEquipoEmpleadoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of LogEquipoEmpleadoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogEquipoEmpleadoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(LogEquipoEmpleadoEJB.class, aMap);      
    }

/////////////////
}
