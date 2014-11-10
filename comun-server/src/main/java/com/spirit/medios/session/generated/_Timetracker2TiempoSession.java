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
public abstract class _Timetracker2TiempoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_Timetracker2TiempoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.Timetracker2TiempoIf addTimetracker2Tiempo(com.spirit.medios.entity.Timetracker2TiempoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      Timetracker2TiempoEJB value = new Timetracker2TiempoEJB();
      try {
      value.setId(model.getId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setClienteOficinaId(model.getClienteOficinaId());
      value.setTiempoDesignado(model.getTiempoDesignado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en timetracker2Tiempo.", e);
			throw new GenericBusinessException(
					"Error al insertar información en timetracker2Tiempo.");
      }
     
      return getTimetracker2Tiempo(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTimetracker2Tiempo(com.spirit.medios.entity.Timetracker2TiempoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      Timetracker2TiempoEJB data = new Timetracker2TiempoEJB();
      data.setId(model.getId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setClienteOficinaId(model.getClienteOficinaId());
      data.setTiempoDesignado(model.getTiempoDesignado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en timetracker2Tiempo.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en timetracker2Tiempo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTimetracker2Tiempo(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      Timetracker2TiempoEJB data = manager.find(Timetracker2TiempoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en timetracker2Tiempo.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en timetracker2Tiempo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.Timetracker2TiempoIf getTimetracker2Tiempo(java.lang.Long id) {
      return (Timetracker2TiempoEJB)queryManagerLocal.find(Timetracker2TiempoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTimetracker2TiempoList() {
	  return queryManagerLocal.singleClassList(Timetracker2TiempoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTimetracker2TiempoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(Timetracker2TiempoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTimetracker2TiempoListSize() {
      Query countQuery = manager.createQuery("select count(*) from Timetracker2TiempoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2TiempoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(Timetracker2TiempoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2TiempoByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(Timetracker2TiempoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2TiempoByClienteOficinaId(java.lang.Long clienteOficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteOficinaId", clienteOficinaId);
		return queryManagerLocal.singleClassQueryList(Timetracker2TiempoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2TiempoByTiempoDesignado(java.lang.Integer tiempoDesignado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tiempoDesignado", tiempoDesignado);
		return queryManagerLocal.singleClassQueryList(Timetracker2TiempoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of Timetracker2TiempoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2TiempoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(Timetracker2TiempoEJB.class, aMap);      
    }

/////////////////
}
