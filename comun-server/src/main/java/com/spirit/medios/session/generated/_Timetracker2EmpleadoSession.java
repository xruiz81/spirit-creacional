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
public abstract class _Timetracker2EmpleadoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_Timetracker2EmpleadoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.Timetracker2EmpleadoIf addTimetracker2Empleado(com.spirit.medios.entity.Timetracker2EmpleadoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      Timetracker2EmpleadoEJB value = new Timetracker2EmpleadoEJB();
      try {
      value.setId(model.getId());
      value.setTimetracker2Id(model.getTimetracker2Id());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setEstado(model.getEstado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en timetracker2Empleado.", e);
			throw new GenericBusinessException(
					"Error al insertar información en timetracker2Empleado.");
      }
     
      return getTimetracker2Empleado(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTimetracker2Empleado(com.spirit.medios.entity.Timetracker2EmpleadoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      Timetracker2EmpleadoEJB data = new Timetracker2EmpleadoEJB();
      data.setId(model.getId());
      data.setTimetracker2Id(model.getTimetracker2Id());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setEstado(model.getEstado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en timetracker2Empleado.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en timetracker2Empleado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTimetracker2Empleado(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      Timetracker2EmpleadoEJB data = manager.find(Timetracker2EmpleadoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en timetracker2Empleado.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en timetracker2Empleado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.Timetracker2EmpleadoIf getTimetracker2Empleado(java.lang.Long id) {
      return (Timetracker2EmpleadoEJB)queryManagerLocal.find(Timetracker2EmpleadoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTimetracker2EmpleadoList() {
	  return queryManagerLocal.singleClassList(Timetracker2EmpleadoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTimetracker2EmpleadoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(Timetracker2EmpleadoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTimetracker2EmpleadoListSize() {
      Query countQuery = manager.createQuery("select count(*) from Timetracker2EmpleadoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2EmpleadoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(Timetracker2EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2EmpleadoByTimetracker2Id(java.lang.Long timetracker2Id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("timetracker2Id", timetracker2Id);
		return queryManagerLocal.singleClassQueryList(Timetracker2EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2EmpleadoByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(Timetracker2EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2EmpleadoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(Timetracker2EmpleadoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of Timetracker2EmpleadoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2EmpleadoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(Timetracker2EmpleadoEJB.class, aMap);      
    }

/////////////////
}
