package com.spirit.rrhh.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.rrhh.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _EmpleadoVacacionesSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_EmpleadoVacacionesSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.rrhh.entity.EmpleadoVacacionesIf addEmpleadoVacaciones(com.spirit.rrhh.entity.EmpleadoVacacionesIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      EmpleadoVacacionesEJB value = new EmpleadoVacacionesEJB();
      try {
      value.setId(model.getId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setSaldoDias(model.getSaldoDias());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      value.setObservacion(model.getObservacion());
      value.setArchivoAdjunto(model.getArchivoAdjunto());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en empleadoVacaciones.", e);
			throw new GenericBusinessException(
					"Error al insertar información en empleadoVacaciones.");
      }
     
      return getEmpleadoVacaciones(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveEmpleadoVacaciones(com.spirit.rrhh.entity.EmpleadoVacacionesIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      EmpleadoVacacionesEJB data = new EmpleadoVacacionesEJB();
      data.setId(model.getId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setSaldoDias(model.getSaldoDias());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
      data.setObservacion(model.getObservacion());
      data.setArchivoAdjunto(model.getArchivoAdjunto());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en empleadoVacaciones.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en empleadoVacaciones.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteEmpleadoVacaciones(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      EmpleadoVacacionesEJB data = manager.find(EmpleadoVacacionesEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en empleadoVacaciones.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en empleadoVacaciones.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.rrhh.entity.EmpleadoVacacionesIf getEmpleadoVacaciones(java.lang.Long id) {
      return (EmpleadoVacacionesEJB)queryManagerLocal.find(EmpleadoVacacionesEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpleadoVacacionesList() {
	  return queryManagerLocal.singleClassList(EmpleadoVacacionesEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpleadoVacacionesList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(EmpleadoVacacionesEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getEmpleadoVacacionesListSize() {
      Query countQuery = manager.createQuery("select count(*) from EmpleadoVacacionesEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoVacacionesById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(EmpleadoVacacionesEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoVacacionesByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(EmpleadoVacacionesEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoVacacionesBySaldoDias(java.lang.Float saldoDias) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("saldoDias", saldoDias);
		return queryManagerLocal.singleClassQueryList(EmpleadoVacacionesEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoVacacionesByFechaInicio(java.sql.Timestamp fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(EmpleadoVacacionesEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoVacacionesByFechaFin(java.sql.Timestamp fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(EmpleadoVacacionesEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoVacacionesByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(EmpleadoVacacionesEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoVacacionesByArchivoAdjunto(java.lang.String archivoAdjunto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("archivoAdjunto", archivoAdjunto);
		return queryManagerLocal.singleClassQueryList(EmpleadoVacacionesEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of EmpleadoVacacionesIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoVacacionesByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(EmpleadoVacacionesEJB.class, aMap);      
    }

/////////////////
}
