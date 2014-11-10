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
public abstract class _OrdenTrabajoDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_OrdenTrabajoDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.OrdenTrabajoDetalleIf addOrdenTrabajoDetalle(com.spirit.medios.entity.OrdenTrabajoDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      OrdenTrabajoDetalleEJB value = new OrdenTrabajoDetalleEJB();
      try {
      value.setId(model.getId());
      value.setOrdenId(model.getOrdenId());
      value.setSubtipoId(model.getSubtipoId());
      value.setEquipoId(model.getEquipoId());
      value.setAsignadoaId(model.getAsignadoaId());
      value.setFechalimite(model.getFechalimite());
      value.setFechaentrega(model.getFechaentrega());
      value.setUrlDescripcion(model.getUrlDescripcion());
      value.setUrlPropuesta(model.getUrlPropuesta());
      value.setDescripcion(model.getDescripcion());
      value.setEstado(model.getEstado());
      value.setFecha(model.getFecha());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en ordenTrabajoDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en ordenTrabajoDetalle.");
      }
     
      return getOrdenTrabajoDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveOrdenTrabajoDetalle(com.spirit.medios.entity.OrdenTrabajoDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      OrdenTrabajoDetalleEJB data = new OrdenTrabajoDetalleEJB();
      data.setId(model.getId());
      data.setOrdenId(model.getOrdenId());
      data.setSubtipoId(model.getSubtipoId());
      data.setEquipoId(model.getEquipoId());
      data.setAsignadoaId(model.getAsignadoaId());
      data.setFechalimite(model.getFechalimite());
      data.setFechaentrega(model.getFechaentrega());
      data.setUrlDescripcion(model.getUrlDescripcion());
      data.setUrlPropuesta(model.getUrlPropuesta());
      data.setDescripcion(model.getDescripcion());
      data.setEstado(model.getEstado());
      data.setFecha(model.getFecha());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en ordenTrabajoDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en ordenTrabajoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteOrdenTrabajoDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      OrdenTrabajoDetalleEJB data = manager.find(OrdenTrabajoDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en ordenTrabajoDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en ordenTrabajoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.OrdenTrabajoDetalleIf getOrdenTrabajoDetalle(java.lang.Long id) {
      return (OrdenTrabajoDetalleEJB)queryManagerLocal.find(OrdenTrabajoDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenTrabajoDetalleList() {
	  return queryManagerLocal.singleClassList(OrdenTrabajoDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenTrabajoDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(OrdenTrabajoDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getOrdenTrabajoDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from OrdenTrabajoDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoDetalleByOrdenId(java.lang.Long ordenId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordenId", ordenId);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoDetalleBySubtipoId(java.lang.Long subtipoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("subtipoId", subtipoId);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoDetalleByEquipoId(java.lang.Long equipoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("equipoId", equipoId);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoDetalleByAsignadoaId(java.lang.Long asignadoaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("asignadoaId", asignadoaId);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoDetalleByFechalimite(java.sql.Date fechalimite) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechalimite", fechalimite);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoDetalleByFechaentrega(java.sql.Date fechaentrega) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaentrega", fechaentrega);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoDetalleByUrlDescripcion(java.lang.String urlDescripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("urlDescripcion", urlDescripcion);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoDetalleByUrlPropuesta(java.lang.String urlPropuesta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("urlPropuesta", urlPropuesta);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoDetalleByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoDetalleByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoDetalleByFecha(java.sql.Timestamp fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of OrdenTrabajoDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoDetalleEJB.class, aMap);      
    }

/////////////////
}
