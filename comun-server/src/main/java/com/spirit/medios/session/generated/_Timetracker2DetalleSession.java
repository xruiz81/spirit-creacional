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
public abstract class _Timetracker2DetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_Timetracker2DetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.Timetracker2DetalleIf addTimetracker2Detalle(com.spirit.medios.entity.Timetracker2DetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      Timetracker2DetalleEJB value = new Timetracker2DetalleEJB();
      try {
      value.setId(model.getId());
      value.setTimetracker2EmpleadoId(model.getTimetracker2EmpleadoId());
      value.setFecha(model.getFecha());
      value.setTiempo(model.getTiempo());
      value.setEstado(model.getEstado());
      value.setClienteOficinaId(model.getClienteOficinaId());
      value.setTiempoDesignado(model.getTiempoDesignado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en timetracker2Detalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en timetracker2Detalle.");
      }
     
      return getTimetracker2Detalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTimetracker2Detalle(com.spirit.medios.entity.Timetracker2DetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      Timetracker2DetalleEJB data = new Timetracker2DetalleEJB();
      data.setId(model.getId());
      data.setTimetracker2EmpleadoId(model.getTimetracker2EmpleadoId());
      data.setFecha(model.getFecha());
      data.setTiempo(model.getTiempo());
      data.setEstado(model.getEstado());
      data.setClienteOficinaId(model.getClienteOficinaId());
      data.setTiempoDesignado(model.getTiempoDesignado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en timetracker2Detalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en timetracker2Detalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTimetracker2Detalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      Timetracker2DetalleEJB data = manager.find(Timetracker2DetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en timetracker2Detalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en timetracker2Detalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.Timetracker2DetalleIf getTimetracker2Detalle(java.lang.Long id) {
      return (Timetracker2DetalleEJB)queryManagerLocal.find(Timetracker2DetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTimetracker2DetalleList() {
	  return queryManagerLocal.singleClassList(Timetracker2DetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTimetracker2DetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(Timetracker2DetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTimetracker2DetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from Timetracker2DetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2DetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(Timetracker2DetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2DetalleByTimetracker2EmpleadoId(java.lang.Long timetracker2EmpleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("timetracker2EmpleadoId", timetracker2EmpleadoId);
		return queryManagerLocal.singleClassQueryList(Timetracker2DetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2DetalleByFecha(java.sql.Timestamp fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(Timetracker2DetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2DetalleByTiempo(java.lang.Float tiempo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tiempo", tiempo);
		return queryManagerLocal.singleClassQueryList(Timetracker2DetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2DetalleByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(Timetracker2DetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2DetalleByClienteOficinaId(java.lang.Long clienteOficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteOficinaId", clienteOficinaId);
		return queryManagerLocal.singleClassQueryList(Timetracker2DetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2DetalleByTiempoDesignado(java.lang.Integer tiempoDesignado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tiempoDesignado", tiempoDesignado);
		return queryManagerLocal.singleClassQueryList(Timetracker2DetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of Timetracker2DetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2DetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(Timetracker2DetalleEJB.class, aMap);      
    }

/////////////////
}
