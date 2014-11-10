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
public abstract class _CampanaArchivoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CampanaArchivoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.CampanaArchivoIf addCampanaArchivo(com.spirit.medios.entity.CampanaArchivoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CampanaArchivoEJB value = new CampanaArchivoEJB();
      try {
      value.setId(model.getId());
      value.setTipoArchivoId(model.getTipoArchivoId());
      value.setCampanaId(model.getCampanaId());
      value.setFecha(model.getFecha());
      value.setUrlDescripcion(model.getUrlDescripcion());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en campanaArchivo.", e);
			throw new GenericBusinessException(
					"Error al insertar información en campanaArchivo.");
      }
     
      return getCampanaArchivo(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCampanaArchivo(com.spirit.medios.entity.CampanaArchivoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CampanaArchivoEJB data = new CampanaArchivoEJB();
      data.setId(model.getId());
      data.setTipoArchivoId(model.getTipoArchivoId());
      data.setCampanaId(model.getCampanaId());
      data.setFecha(model.getFecha());
      data.setUrlDescripcion(model.getUrlDescripcion());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en campanaArchivo.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en campanaArchivo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCampanaArchivo(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CampanaArchivoEJB data = manager.find(CampanaArchivoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en campanaArchivo.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en campanaArchivo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.CampanaArchivoIf getCampanaArchivo(java.lang.Long id) {
      return (CampanaArchivoEJB)queryManagerLocal.find(CampanaArchivoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCampanaArchivoList() {
	  return queryManagerLocal.singleClassList(CampanaArchivoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCampanaArchivoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CampanaArchivoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCampanaArchivoListSize() {
      Query countQuery = manager.createQuery("select count(*) from CampanaArchivoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaArchivoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CampanaArchivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaArchivoByTipoArchivoId(java.lang.Long tipoArchivoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoArchivoId", tipoArchivoId);
		return queryManagerLocal.singleClassQueryList(CampanaArchivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaArchivoByCampanaId(java.lang.Long campanaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("campanaId", campanaId);
		return queryManagerLocal.singleClassQueryList(CampanaArchivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaArchivoByFecha(java.sql.Date fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(CampanaArchivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaArchivoByUrlDescripcion(java.lang.String urlDescripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("urlDescripcion", urlDescripcion);
		return queryManagerLocal.singleClassQueryList(CampanaArchivoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CampanaArchivoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaArchivoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CampanaArchivoEJB.class, aMap);      
    }

/////////////////
}
