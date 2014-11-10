package com.spirit.general.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.general.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _CiudadSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CiudadSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.CiudadIf addCiudad(com.spirit.general.entity.CiudadIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CiudadEJB value = new CiudadEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setProvinciaId(model.getProvinciaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en ciudad.", e);
			throw new GenericBusinessException(
					"Error al insertar información en ciudad.");
      }
     
      return getCiudad(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCiudad(com.spirit.general.entity.CiudadIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CiudadEJB data = new CiudadEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setProvinciaId(model.getProvinciaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en ciudad.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en ciudad.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCiudad(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CiudadEJB data = manager.find(CiudadEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en ciudad.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en ciudad.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.CiudadIf getCiudad(java.lang.Long id) {
      return (CiudadEJB)queryManagerLocal.find(CiudadEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCiudadList() {
	  return queryManagerLocal.singleClassList(CiudadEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCiudadList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CiudadEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCiudadListSize() {
      Query countQuery = manager.createQuery("select count(*) from CiudadEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCiudadById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CiudadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCiudadByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(CiudadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCiudadByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(CiudadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCiudadByProvinciaId(java.lang.Long provinciaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("provinciaId", provinciaId);
		return queryManagerLocal.singleClassQueryList(CiudadEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CiudadIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCiudadByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CiudadEJB.class, aMap);      
    }

/////////////////
}
