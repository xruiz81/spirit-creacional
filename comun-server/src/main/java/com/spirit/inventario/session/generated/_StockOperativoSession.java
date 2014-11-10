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
public abstract class _StockOperativoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_StockOperativoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.StockOperativoIf addStockOperativo(com.spirit.inventario.entity.StockOperativoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      StockOperativoEJB value = new StockOperativoEJB();
      try {
      value.setId(model.getId());
      value.setBodegaId(model.getBodegaId());
      value.setMes(model.getMes());
      value.setAnio(model.getAnio());
      value.setProductoId(model.getProductoId());
      value.setMin(model.getMin());
      value.setMax(model.getMax());
      value.setTiempoMinimoReposicion(model.getTiempoMinimoReposicion());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en stockOperativo.", e);
			throw new GenericBusinessException(
					"Error al insertar información en stockOperativo.");
      }
     
      return getStockOperativo(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveStockOperativo(com.spirit.inventario.entity.StockOperativoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      StockOperativoEJB data = new StockOperativoEJB();
      data.setId(model.getId());
      data.setBodegaId(model.getBodegaId());
      data.setMes(model.getMes());
      data.setAnio(model.getAnio());
      data.setProductoId(model.getProductoId());
      data.setMin(model.getMin());
      data.setMax(model.getMax());
      data.setTiempoMinimoReposicion(model.getTiempoMinimoReposicion());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en stockOperativo.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en stockOperativo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteStockOperativo(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      StockOperativoEJB data = manager.find(StockOperativoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en stockOperativo.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en stockOperativo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.StockOperativoIf getStockOperativo(java.lang.Long id) {
      return (StockOperativoEJB)queryManagerLocal.find(StockOperativoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getStockOperativoList() {
	  return queryManagerLocal.singleClassList(StockOperativoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getStockOperativoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(StockOperativoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getStockOperativoListSize() {
      Query countQuery = manager.createQuery("select count(*) from StockOperativoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockOperativoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(StockOperativoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockOperativoByBodegaId(java.lang.Long bodegaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bodegaId", bodegaId);
		return queryManagerLocal.singleClassQueryList(StockOperativoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockOperativoByMes(java.lang.String mes) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("mes", mes);
		return queryManagerLocal.singleClassQueryList(StockOperativoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockOperativoByAnio(java.lang.String anio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("anio", anio);
		return queryManagerLocal.singleClassQueryList(StockOperativoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockOperativoByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(StockOperativoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockOperativoByMin(java.math.BigDecimal min) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("min", min);
		return queryManagerLocal.singleClassQueryList(StockOperativoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockOperativoByMax(java.math.BigDecimal max) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("max", max);
		return queryManagerLocal.singleClassQueryList(StockOperativoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockOperativoByTiempoMinimoReposicion(java.lang.Integer tiempoMinimoReposicion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tiempoMinimoReposicion", tiempoMinimoReposicion);
		return queryManagerLocal.singleClassQueryList(StockOperativoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of StockOperativoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockOperativoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(StockOperativoEJB.class, aMap);      
    }

/////////////////
}
