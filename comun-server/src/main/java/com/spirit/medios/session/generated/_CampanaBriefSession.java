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
public abstract class _CampanaBriefSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CampanaBriefSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.CampanaBriefIf addCampanaBrief(com.spirit.medios.entity.CampanaBriefIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CampanaBriefEJB value = new CampanaBriefEJB();
      try {
      value.setId(model.getId());
      value.setTipoBriefId(model.getTipoBriefId());
      value.setCampanaId(model.getCampanaId());
      value.setDescripcion(model.getDescripcion());
      value.setUrlDescripcion(model.getUrlDescripcion());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en campanaBrief.", e);
			throw new GenericBusinessException(
					"Error al insertar información en campanaBrief.");
      }
     
      return getCampanaBrief(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCampanaBrief(com.spirit.medios.entity.CampanaBriefIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CampanaBriefEJB data = new CampanaBriefEJB();
      data.setId(model.getId());
      data.setTipoBriefId(model.getTipoBriefId());
      data.setCampanaId(model.getCampanaId());
      data.setDescripcion(model.getDescripcion());
      data.setUrlDescripcion(model.getUrlDescripcion());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en campanaBrief.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en campanaBrief.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCampanaBrief(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CampanaBriefEJB data = manager.find(CampanaBriefEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en campanaBrief.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en campanaBrief.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.CampanaBriefIf getCampanaBrief(java.lang.Long id) {
      return (CampanaBriefEJB)queryManagerLocal.find(CampanaBriefEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCampanaBriefList() {
	  return queryManagerLocal.singleClassList(CampanaBriefEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCampanaBriefList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CampanaBriefEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCampanaBriefListSize() {
      Query countQuery = manager.createQuery("select count(*) from CampanaBriefEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaBriefById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CampanaBriefEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaBriefByTipoBriefId(java.lang.Long tipoBriefId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoBriefId", tipoBriefId);
		return queryManagerLocal.singleClassQueryList(CampanaBriefEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaBriefByCampanaId(java.lang.Long campanaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("campanaId", campanaId);
		return queryManagerLocal.singleClassQueryList(CampanaBriefEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaBriefByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(CampanaBriefEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaBriefByUrlDescripcion(java.lang.String urlDescripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("urlDescripcion", urlDescripcion);
		return queryManagerLocal.singleClassQueryList(CampanaBriefEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CampanaBriefIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaBriefByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CampanaBriefEJB.class, aMap);      
    }

/////////////////
}
