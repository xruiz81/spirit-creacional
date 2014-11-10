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
public abstract class _StockDiaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_StockDiaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.StockDiaIf addStockDia(com.spirit.inventario.entity.StockDiaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      StockDiaEJB value = new StockDiaEJB();
      try {
      value.setId(model.getId());
      value.setBodegaId(model.getBodegaId());
      value.setProductoId(model.getProductoId());
      value.setAnio(model.getAnio());
      value.setMes(model.getMes());
      value.setDia(model.getDia());
      value.setCantidad(model.getCantidad());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en stockDia.", e);
			throw new GenericBusinessException(
					"Error al insertar información en stockDia.");
      }
     
      return getStockDia(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveStockDia(com.spirit.inventario.entity.StockDiaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      StockDiaEJB data = new StockDiaEJB();
      data.setId(model.getId());
      data.setBodegaId(model.getBodegaId());
      data.setProductoId(model.getProductoId());
      data.setAnio(model.getAnio());
      data.setMes(model.getMes());
      data.setDia(model.getDia());
      data.setCantidad(model.getCantidad());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en stockDia.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en stockDia.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteStockDia(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      StockDiaEJB data = manager.find(StockDiaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en stockDia.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en stockDia.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.StockDiaIf getStockDia(java.lang.Long id) {
      return (StockDiaEJB)queryManagerLocal.find(StockDiaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getStockDiaList() {
	  return queryManagerLocal.singleClassList(StockDiaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getStockDiaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(StockDiaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getStockDiaListSize() {
      Query countQuery = manager.createQuery("select count(*) from StockDiaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockDiaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(StockDiaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockDiaByBodegaId(java.lang.Long bodegaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bodegaId", bodegaId);
		return queryManagerLocal.singleClassQueryList(StockDiaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockDiaByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(StockDiaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockDiaByAnio(java.lang.String anio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("anio", anio);
		return queryManagerLocal.singleClassQueryList(StockDiaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockDiaByMes(java.lang.String mes) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("mes", mes);
		return queryManagerLocal.singleClassQueryList(StockDiaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockDiaByDia(java.lang.String dia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("dia", dia);
		return queryManagerLocal.singleClassQueryList(StockDiaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockDiaByCantidad(java.math.BigDecimal cantidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantidad", cantidad);
		return queryManagerLocal.singleClassQueryList(StockDiaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of StockDiaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockDiaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(StockDiaEJB.class, aMap);      
    }

/////////////////
}
