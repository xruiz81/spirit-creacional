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
public abstract class _ReunionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ReunionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.ReunionIf addReunion(com.spirit.medios.entity.ReunionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ReunionEJB value = new ReunionEJB();
      try {
      value.setId(model.getId());
      value.setOficinaId(model.getOficinaId());
      value.setClienteId(model.getClienteId());
      value.setProspectocliente(model.getProspectocliente());
      value.setFecha(model.getFecha());
      value.setFechaEnvio(model.getFechaEnvio());
      value.setNumEnviados(model.getNumEnviados());
      value.setDescripcion(model.getDescripcion());
      value.setEstado(model.getEstado());
      value.setUsuarioCreacionId(model.getUsuarioCreacionId());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setUsuarioActualizacionId(model.getUsuarioActualizacionId());
      value.setFechaActualizacion(model.getFechaActualizacion());
      value.setEjecutivoId(model.getEjecutivoId());
      value.setConCopia(model.getConCopia());
      value.setLugarReunion(model.getLugarReunion());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en reunion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en reunion.");
      }
     
      return getReunion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveReunion(com.spirit.medios.entity.ReunionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ReunionEJB data = new ReunionEJB();
      data.setId(model.getId());
      data.setOficinaId(model.getOficinaId());
      data.setClienteId(model.getClienteId());
      data.setProspectocliente(model.getProspectocliente());
      data.setFecha(model.getFecha());
      data.setFechaEnvio(model.getFechaEnvio());
      data.setNumEnviados(model.getNumEnviados());
      data.setDescripcion(model.getDescripcion());
      data.setEstado(model.getEstado());
      data.setUsuarioCreacionId(model.getUsuarioCreacionId());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setUsuarioActualizacionId(model.getUsuarioActualizacionId());
      data.setFechaActualizacion(model.getFechaActualizacion());
      data.setEjecutivoId(model.getEjecutivoId());
      data.setConCopia(model.getConCopia());
      data.setLugarReunion(model.getLugarReunion());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en reunion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en reunion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteReunion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ReunionEJB data = manager.find(ReunionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en reunion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en reunion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.ReunionIf getReunion(java.lang.Long id) {
      return (ReunionEJB)queryManagerLocal.find(ReunionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getReunionList() {
	  return queryManagerLocal.singleClassList(ReunionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getReunionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ReunionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getReunionListSize() {
      Query countQuery = manager.createQuery("select count(*) from ReunionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ReunionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(ReunionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionByClienteId(java.lang.Long clienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteId", clienteId);
		return queryManagerLocal.singleClassQueryList(ReunionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionByProspectocliente(java.lang.String prospectocliente) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("prospectocliente", prospectocliente);
		return queryManagerLocal.singleClassQueryList(ReunionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionByFecha(java.sql.Date fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(ReunionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionByFechaEnvio(java.sql.Date fechaEnvio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaEnvio", fechaEnvio);
		return queryManagerLocal.singleClassQueryList(ReunionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionByNumEnviados(java.lang.Integer numEnviados) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numEnviados", numEnviados);
		return queryManagerLocal.singleClassQueryList(ReunionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(ReunionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(ReunionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionByUsuarioCreacionId(java.lang.Long usuarioCreacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioCreacionId", usuarioCreacionId);
		return queryManagerLocal.singleClassQueryList(ReunionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionByFechaCreacion(java.sql.Date fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(ReunionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionByUsuarioActualizacionId(java.lang.Long usuarioActualizacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioActualizacionId", usuarioActualizacionId);
		return queryManagerLocal.singleClassQueryList(ReunionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionByFechaActualizacion(java.sql.Date fechaActualizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaActualizacion", fechaActualizacion);
		return queryManagerLocal.singleClassQueryList(ReunionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionByEjecutivoId(java.lang.Long ejecutivoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ejecutivoId", ejecutivoId);
		return queryManagerLocal.singleClassQueryList(ReunionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionByConCopia(java.lang.String conCopia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("conCopia", conCopia);
		return queryManagerLocal.singleClassQueryList(ReunionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionByLugarReunion(java.lang.String lugarReunion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("lugarReunion", lugarReunion);
		return queryManagerLocal.singleClassQueryList(ReunionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ReunionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ReunionEJB.class, aMap);      
    }

/////////////////
}
