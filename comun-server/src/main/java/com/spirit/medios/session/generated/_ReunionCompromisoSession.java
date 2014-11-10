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
public abstract class _ReunionCompromisoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ReunionCompromisoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.ReunionCompromisoIf addReunionCompromiso(com.spirit.medios.entity.ReunionCompromisoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ReunionCompromisoEJB value = new ReunionCompromisoEJB();
      try {
      value.setId(model.getId());
      value.setReunionId(model.getReunionId());
      value.setFecha(model.getFecha());
      value.setDescripcion(model.getDescripcion());
      value.setEstado(model.getEstado());
      value.setTemaTratado(model.getTemaTratado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en reunionCompromiso.", e);
			throw new GenericBusinessException(
					"Error al insertar información en reunionCompromiso.");
      }
     
      return getReunionCompromiso(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveReunionCompromiso(com.spirit.medios.entity.ReunionCompromisoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ReunionCompromisoEJB data = new ReunionCompromisoEJB();
      data.setId(model.getId());
      data.setReunionId(model.getReunionId());
      data.setFecha(model.getFecha());
      data.setDescripcion(model.getDescripcion());
      data.setEstado(model.getEstado());
      data.setTemaTratado(model.getTemaTratado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en reunionCompromiso.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en reunionCompromiso.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteReunionCompromiso(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ReunionCompromisoEJB data = manager.find(ReunionCompromisoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en reunionCompromiso.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en reunionCompromiso.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.ReunionCompromisoIf getReunionCompromiso(java.lang.Long id) {
      return (ReunionCompromisoEJB)queryManagerLocal.find(ReunionCompromisoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getReunionCompromisoList() {
	  return queryManagerLocal.singleClassList(ReunionCompromisoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getReunionCompromisoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ReunionCompromisoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getReunionCompromisoListSize() {
      Query countQuery = manager.createQuery("select count(*) from ReunionCompromisoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionCompromisoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ReunionCompromisoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionCompromisoByReunionId(java.lang.Long reunionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("reunionId", reunionId);
		return queryManagerLocal.singleClassQueryList(ReunionCompromisoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionCompromisoByFecha(java.sql.Date fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(ReunionCompromisoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionCompromisoByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(ReunionCompromisoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionCompromisoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(ReunionCompromisoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionCompromisoByTemaTratado(java.lang.String temaTratado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("temaTratado", temaTratado);
		return queryManagerLocal.singleClassQueryList(ReunionCompromisoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ReunionCompromisoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionCompromisoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ReunionCompromisoEJB.class, aMap);      
    }

/////////////////
}
