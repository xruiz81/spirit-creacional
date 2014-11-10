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
public abstract class _ReunionArchivoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ReunionArchivoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.ReunionArchivoIf addReunionArchivo(com.spirit.medios.entity.ReunionArchivoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ReunionArchivoEJB value = new ReunionArchivoEJB();
      try {
      value.setId(model.getId());
      value.setReunionId(model.getReunionId());
      value.setTipoArchivoId(model.getTipoArchivoId());
      value.setFecha(model.getFecha());
      value.setUrlDescripcion(model.getUrlDescripcion());
      value.setEstado(model.getEstado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en reunionArchivo.", e);
			throw new GenericBusinessException(
					"Error al insertar información en reunionArchivo.");
      }
     
      return getReunionArchivo(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveReunionArchivo(com.spirit.medios.entity.ReunionArchivoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ReunionArchivoEJB data = new ReunionArchivoEJB();
      data.setId(model.getId());
      data.setReunionId(model.getReunionId());
      data.setTipoArchivoId(model.getTipoArchivoId());
      data.setFecha(model.getFecha());
      data.setUrlDescripcion(model.getUrlDescripcion());
      data.setEstado(model.getEstado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en reunionArchivo.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en reunionArchivo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteReunionArchivo(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ReunionArchivoEJB data = manager.find(ReunionArchivoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en reunionArchivo.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en reunionArchivo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.ReunionArchivoIf getReunionArchivo(java.lang.Long id) {
      return (ReunionArchivoEJB)queryManagerLocal.find(ReunionArchivoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getReunionArchivoList() {
	  return queryManagerLocal.singleClassList(ReunionArchivoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getReunionArchivoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ReunionArchivoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getReunionArchivoListSize() {
      Query countQuery = manager.createQuery("select count(*) from ReunionArchivoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionArchivoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ReunionArchivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionArchivoByReunionId(java.lang.Long reunionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("reunionId", reunionId);
		return queryManagerLocal.singleClassQueryList(ReunionArchivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionArchivoByTipoArchivoId(java.lang.Long tipoArchivoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoArchivoId", tipoArchivoId);
		return queryManagerLocal.singleClassQueryList(ReunionArchivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionArchivoByFecha(java.sql.Date fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(ReunionArchivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionArchivoByUrlDescripcion(java.lang.String urlDescripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("urlDescripcion", urlDescripcion);
		return queryManagerLocal.singleClassQueryList(ReunionArchivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionArchivoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(ReunionArchivoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ReunionArchivoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionArchivoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ReunionArchivoEJB.class, aMap);      
    }

/////////////////
}
