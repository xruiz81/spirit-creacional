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
public abstract class _ReunionProductoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ReunionProductoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.ReunionProductoIf addReunionProducto(com.spirit.medios.entity.ReunionProductoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ReunionProductoEJB value = new ReunionProductoEJB();
      try {
      value.setId(model.getId());
      value.setReunionId(model.getReunionId());
      value.setProductoClienteId(model.getProductoClienteId());
      value.setProductoCliente(model.getProductoCliente());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en reunionProducto.", e);
			throw new GenericBusinessException(
					"Error al insertar información en reunionProducto.");
      }
     
      return getReunionProducto(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveReunionProducto(com.spirit.medios.entity.ReunionProductoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ReunionProductoEJB data = new ReunionProductoEJB();
      data.setId(model.getId());
      data.setReunionId(model.getReunionId());
      data.setProductoClienteId(model.getProductoClienteId());
      data.setProductoCliente(model.getProductoCliente());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en reunionProducto.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en reunionProducto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteReunionProducto(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ReunionProductoEJB data = manager.find(ReunionProductoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en reunionProducto.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en reunionProducto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.ReunionProductoIf getReunionProducto(java.lang.Long id) {
      return (ReunionProductoEJB)queryManagerLocal.find(ReunionProductoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getReunionProductoList() {
	  return queryManagerLocal.singleClassList(ReunionProductoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getReunionProductoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ReunionProductoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getReunionProductoListSize() {
      Query countQuery = manager.createQuery("select count(*) from ReunionProductoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionProductoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ReunionProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionProductoByReunionId(java.lang.Long reunionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("reunionId", reunionId);
		return queryManagerLocal.singleClassQueryList(ReunionProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionProductoByProductoClienteId(java.lang.Long productoClienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoClienteId", productoClienteId);
		return queryManagerLocal.singleClassQueryList(ReunionProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionProductoByProductoCliente(java.lang.String productoCliente) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoCliente", productoCliente);
		return queryManagerLocal.singleClassQueryList(ReunionProductoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ReunionProductoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionProductoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ReunionProductoEJB.class, aMap);      
    }

/////////////////
}
