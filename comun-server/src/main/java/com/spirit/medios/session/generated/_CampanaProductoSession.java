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
public abstract class _CampanaProductoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CampanaProductoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.CampanaProductoIf addCampanaProducto(com.spirit.medios.entity.CampanaProductoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CampanaProductoEJB value = new CampanaProductoEJB();
      try {
      value.setId(model.getId());
      value.setProductoClienteId(model.getProductoClienteId());
      value.setCampanaId(model.getCampanaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en campanaProducto.", e);
			throw new GenericBusinessException(
					"Error al insertar información en campanaProducto.");
      }
     
      return getCampanaProducto(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCampanaProducto(com.spirit.medios.entity.CampanaProductoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CampanaProductoEJB data = new CampanaProductoEJB();
      data.setId(model.getId());
      data.setProductoClienteId(model.getProductoClienteId());
      data.setCampanaId(model.getCampanaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en campanaProducto.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en campanaProducto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCampanaProducto(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CampanaProductoEJB data = manager.find(CampanaProductoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en campanaProducto.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en campanaProducto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.CampanaProductoIf getCampanaProducto(java.lang.Long id) {
      return (CampanaProductoEJB)queryManagerLocal.find(CampanaProductoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCampanaProductoList() {
	  return queryManagerLocal.singleClassList(CampanaProductoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCampanaProductoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CampanaProductoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCampanaProductoListSize() {
      Query countQuery = manager.createQuery("select count(*) from CampanaProductoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaProductoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CampanaProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaProductoByProductoClienteId(java.lang.Long productoClienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoClienteId", productoClienteId);
		return queryManagerLocal.singleClassQueryList(CampanaProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaProductoByCampanaId(java.lang.Long campanaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("campanaId", campanaId);
		return queryManagerLocal.singleClassQueryList(CampanaProductoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CampanaProductoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaProductoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CampanaProductoEJB.class, aMap);      
    }

/////////////////
}
