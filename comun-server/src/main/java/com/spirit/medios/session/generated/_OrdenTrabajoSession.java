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
public abstract class _OrdenTrabajoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_OrdenTrabajoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.OrdenTrabajoIf addOrdenTrabajo(com.spirit.medios.entity.OrdenTrabajoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      OrdenTrabajoEJB value = new OrdenTrabajoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setDescripcion(model.getDescripcion());
      value.setOficinaId(model.getOficinaId());
      value.setClienteoficinaId(model.getClienteoficinaId());
      value.setCampanaId(model.getCampanaId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setFecha(model.getFecha());
      value.setFechalimite(model.getFechalimite());
      value.setFechaentrega(model.getFechaentrega());
      value.setUrlPropuesta(model.getUrlPropuesta());
      value.setEstado(model.getEstado());
      value.setObservacion(model.getObservacion());
      value.setUsuarioCreacionId(model.getUsuarioCreacionId());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setUsuarioActualizacionId(model.getUsuarioActualizacionId());
      value.setFechaActualizacion(model.getFechaActualizacion());
      value.setEquipoId(model.getEquipoId());
      value.setTimetracker(model.getTimetracker());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en ordenTrabajo.", e);
			throw new GenericBusinessException(
					"Error al insertar información en ordenTrabajo.");
      }
     
      return getOrdenTrabajo(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveOrdenTrabajo(com.spirit.medios.entity.OrdenTrabajoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      OrdenTrabajoEJB data = new OrdenTrabajoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setDescripcion(model.getDescripcion());
      data.setOficinaId(model.getOficinaId());
      data.setClienteoficinaId(model.getClienteoficinaId());
      data.setCampanaId(model.getCampanaId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setFecha(model.getFecha());
      data.setFechalimite(model.getFechalimite());
      data.setFechaentrega(model.getFechaentrega());
      data.setUrlPropuesta(model.getUrlPropuesta());
      data.setEstado(model.getEstado());
      data.setObservacion(model.getObservacion());
      data.setUsuarioCreacionId(model.getUsuarioCreacionId());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setUsuarioActualizacionId(model.getUsuarioActualizacionId());
      data.setFechaActualizacion(model.getFechaActualizacion());
      data.setEquipoId(model.getEquipoId());
      data.setTimetracker(model.getTimetracker());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en ordenTrabajo.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en ordenTrabajo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteOrdenTrabajo(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      OrdenTrabajoEJB data = manager.find(OrdenTrabajoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en ordenTrabajo.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en ordenTrabajo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.OrdenTrabajoIf getOrdenTrabajo(java.lang.Long id) {
      return (OrdenTrabajoEJB)queryManagerLocal.find(OrdenTrabajoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenTrabajoList() {
	  return queryManagerLocal.singleClassList(OrdenTrabajoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenTrabajoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(OrdenTrabajoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getOrdenTrabajoListSize() {
      Query countQuery = manager.createQuery("select count(*) from OrdenTrabajoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByClienteoficinaId(java.lang.Long clienteoficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteoficinaId", clienteoficinaId);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByCampanaId(java.lang.Long campanaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("campanaId", campanaId);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByFecha(java.sql.Timestamp fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByFechalimite(java.sql.Timestamp fechalimite) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechalimite", fechalimite);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByFechaentrega(java.sql.Timestamp fechaentrega) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaentrega", fechaentrega);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByUrlPropuesta(java.lang.String urlPropuesta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("urlPropuesta", urlPropuesta);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByUsuarioCreacionId(java.lang.Long usuarioCreacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioCreacionId", usuarioCreacionId);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByUsuarioActualizacionId(java.lang.Long usuarioActualizacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioActualizacionId", usuarioActualizacionId);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByFechaActualizacion(java.sql.Timestamp fechaActualizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaActualizacion", fechaActualizacion);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByEquipoId(java.lang.Long equipoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("equipoId", equipoId);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByTimetracker(java.lang.String timetracker) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("timetracker", timetracker);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of OrdenTrabajoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoEJB.class, aMap);      
    }

/////////////////
}
