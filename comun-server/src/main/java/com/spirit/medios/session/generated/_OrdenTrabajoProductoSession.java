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
public abstract class _OrdenTrabajoProductoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_OrdenTrabajoProductoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.OrdenTrabajoProductoIf addOrdenTrabajoProducto(com.spirit.medios.entity.OrdenTrabajoProductoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      OrdenTrabajoProductoEJB value = new OrdenTrabajoProductoEJB();
      try {
      value.setId(model.getId());
      value.setProductoClienteId(model.getProductoClienteId());
      value.setOrdenTrabajoId(model.getOrdenTrabajoId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en ordenTrabajoProducto.", e);
			throw new GenericBusinessException(
					"Error al insertar información en ordenTrabajoProducto.");
      }
     
      return getOrdenTrabajoProducto(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveOrdenTrabajoProducto(com.spirit.medios.entity.OrdenTrabajoProductoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      OrdenTrabajoProductoEJB data = new OrdenTrabajoProductoEJB();
      data.setId(model.getId());
      data.setProductoClienteId(model.getProductoClienteId());
      data.setOrdenTrabajoId(model.getOrdenTrabajoId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en ordenTrabajoProducto.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en ordenTrabajoProducto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteOrdenTrabajoProducto(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      OrdenTrabajoProductoEJB data = manager.find(OrdenTrabajoProductoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en ordenTrabajoProducto.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en ordenTrabajoProducto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.OrdenTrabajoProductoIf getOrdenTrabajoProducto(java.lang.Long id) {
      return (OrdenTrabajoProductoEJB)queryManagerLocal.find(OrdenTrabajoProductoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenTrabajoProductoList() {
	  return queryManagerLocal.singleClassList(OrdenTrabajoProductoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenTrabajoProductoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(OrdenTrabajoProductoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getOrdenTrabajoProductoListSize() {
      Query countQuery = manager.createQuery("select count(*) from OrdenTrabajoProductoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoProductoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoProductoByProductoClienteId(java.lang.Long productoClienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoClienteId", productoClienteId);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoProductoByOrdenTrabajoId(java.lang.Long ordenTrabajoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordenTrabajoId", ordenTrabajoId);
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoProductoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of OrdenTrabajoProductoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenTrabajoProductoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(OrdenTrabajoProductoEJB.class, aMap);      
    }

/////////////////
}
