package com.spirit.inventario.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.inventario.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _ProductoRetencionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ProductoRetencionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.ProductoRetencionIf addProductoRetencion(com.spirit.inventario.entity.ProductoRetencionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ProductoRetencionEJB value = new ProductoRetencionEJB();
      try {
      value.setId(model.getId());
      value.setProductoId(model.getProductoId());
      value.setRetencion(model.getRetencion());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      value.setEstado(model.getEstado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en productoRetencion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en productoRetencion.");
      }
     
      return getProductoRetencion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveProductoRetencion(com.spirit.inventario.entity.ProductoRetencionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ProductoRetencionEJB data = new ProductoRetencionEJB();
      data.setId(model.getId());
      data.setProductoId(model.getProductoId());
      data.setRetencion(model.getRetencion());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
      data.setEstado(model.getEstado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en productoRetencion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en productoRetencion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteProductoRetencion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ProductoRetencionEJB data = manager.find(ProductoRetencionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en productoRetencion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en productoRetencion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.ProductoRetencionIf getProductoRetencion(java.lang.Long id) {
      return (ProductoRetencionEJB)queryManagerLocal.find(ProductoRetencionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getProductoRetencionList() {
	  return queryManagerLocal.singleClassList(ProductoRetencionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getProductoRetencionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ProductoRetencionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getProductoRetencionListSize() {
      Query countQuery = manager.createQuery("select count(*) from ProductoRetencionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoRetencionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ProductoRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoRetencionByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(ProductoRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoRetencionByRetencion(java.lang.Long retencion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("retencion", retencion);
		return queryManagerLocal.singleClassQueryList(ProductoRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoRetencionByFechaInicio(java.sql.Timestamp fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(ProductoRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoRetencionByFechaFin(java.sql.Timestamp fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(ProductoRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoRetencionByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(ProductoRetencionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ProductoRetencionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoRetencionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ProductoRetencionEJB.class, aMap);      
    }

/////////////////
}
