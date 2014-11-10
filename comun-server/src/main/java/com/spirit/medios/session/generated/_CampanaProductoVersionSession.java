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
public abstract class _CampanaProductoVersionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CampanaProductoVersionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.CampanaProductoVersionIf addCampanaProductoVersion(com.spirit.medios.entity.CampanaProductoVersionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CampanaProductoVersionEJB value = new CampanaProductoVersionEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEstado(model.getEstado());
      value.setCampanaProductoId(model.getCampanaProductoId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en campanaProductoVersion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en campanaProductoVersion.");
      }
     
      return getCampanaProductoVersion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCampanaProductoVersion(com.spirit.medios.entity.CampanaProductoVersionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CampanaProductoVersionEJB data = new CampanaProductoVersionEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEstado(model.getEstado());
      data.setCampanaProductoId(model.getCampanaProductoId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en campanaProductoVersion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en campanaProductoVersion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCampanaProductoVersion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CampanaProductoVersionEJB data = manager.find(CampanaProductoVersionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en campanaProductoVersion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en campanaProductoVersion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.CampanaProductoVersionIf getCampanaProductoVersion(java.lang.Long id) {
      return (CampanaProductoVersionEJB)queryManagerLocal.find(CampanaProductoVersionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCampanaProductoVersionList() {
	  return queryManagerLocal.singleClassList(CampanaProductoVersionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCampanaProductoVersionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CampanaProductoVersionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCampanaProductoVersionListSize() {
      Query countQuery = manager.createQuery("select count(*) from CampanaProductoVersionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaProductoVersionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CampanaProductoVersionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaProductoVersionByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(CampanaProductoVersionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaProductoVersionByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(CampanaProductoVersionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaProductoVersionByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(CampanaProductoVersionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaProductoVersionByCampanaProductoId(java.lang.Long campanaProductoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("campanaProductoId", campanaProductoId);
		return queryManagerLocal.singleClassQueryList(CampanaProductoVersionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CampanaProductoVersionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaProductoVersionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CampanaProductoVersionEJB.class, aMap);      
    }

/////////////////
}
