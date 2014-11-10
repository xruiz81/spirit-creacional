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
public abstract class _ProvinciaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ProvinciaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.ProvinciaIf addProvincia(com.spirit.general.entity.ProvinciaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ProvinciaEJB value = new ProvinciaEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setPaisId(model.getPaisId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en provincia.", e);
			throw new GenericBusinessException(
					"Error al insertar información en provincia.");
      }
     
      return getProvincia(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveProvincia(com.spirit.general.entity.ProvinciaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ProvinciaEJB data = new ProvinciaEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setPaisId(model.getPaisId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en provincia.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en provincia.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteProvincia(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ProvinciaEJB data = manager.find(ProvinciaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en provincia.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en provincia.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.ProvinciaIf getProvincia(java.lang.Long id) {
      return (ProvinciaEJB)queryManagerLocal.find(ProvinciaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getProvinciaList() {
	  return queryManagerLocal.singleClassList(ProvinciaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getProvinciaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ProvinciaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getProvinciaListSize() {
      Query countQuery = manager.createQuery("select count(*) from ProvinciaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProvinciaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ProvinciaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProvinciaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(ProvinciaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProvinciaByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(ProvinciaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProvinciaByPaisId(java.lang.Long paisId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("paisId", paisId);
		return queryManagerLocal.singleClassQueryList(ProvinciaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ProvinciaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProvinciaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ProvinciaEJB.class, aMap);      
    }

/////////////////
}
