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
public abstract class _StockSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_StockSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.StockIf addStock(com.spirit.inventario.entity.StockIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      StockEJB value = new StockEJB();
      try {
      value.setId(model.getId());
      value.setBodegaId(model.getBodegaId());
      value.setProductoId(model.getProductoId());
      value.setLoteId(model.getLoteId());
      value.setAnio(model.getAnio());
      value.setMes(model.getMes());
      value.setCantidad(model.getCantidad());
      value.setReserva(model.getReserva());
      value.setTransito(model.getTransito());
      value.setEstado(model.getEstado());
      value.setFhUtlModificacion(model.getFhUtlModificacion());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en stock.", e);
			throw new GenericBusinessException(
					"Error al insertar información en stock.");
      }
     
      return getStock(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveStock(com.spirit.inventario.entity.StockIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      StockEJB data = new StockEJB();
      data.setId(model.getId());
      data.setBodegaId(model.getBodegaId());
      data.setProductoId(model.getProductoId());
      data.setLoteId(model.getLoteId());
      data.setAnio(model.getAnio());
      data.setMes(model.getMes());
      data.setCantidad(model.getCantidad());
      data.setReserva(model.getReserva());
      data.setTransito(model.getTransito());
      data.setEstado(model.getEstado());
      data.setFhUtlModificacion(model.getFhUtlModificacion());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en stock.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en stock.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteStock(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      StockEJB data = manager.find(StockEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en stock.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en stock.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.StockIf getStock(java.lang.Long id) {
      return (StockEJB)queryManagerLocal.find(StockEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getStockList() {
	  return queryManagerLocal.singleClassList(StockEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getStockList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(StockEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getStockListSize() {
      Query countQuery = manager.createQuery("select count(*) from StockEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(StockEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockByBodegaId(java.lang.Long bodegaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bodegaId", bodegaId);
		return queryManagerLocal.singleClassQueryList(StockEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(StockEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockByLoteId(java.lang.Long loteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("loteId", loteId);
		return queryManagerLocal.singleClassQueryList(StockEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockByAnio(java.lang.String anio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("anio", anio);
		return queryManagerLocal.singleClassQueryList(StockEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockByMes(java.lang.String mes) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("mes", mes);
		return queryManagerLocal.singleClassQueryList(StockEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockByCantidad(java.math.BigDecimal cantidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantidad", cantidad);
		return queryManagerLocal.singleClassQueryList(StockEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockByReserva(java.math.BigDecimal reserva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("reserva", reserva);
		return queryManagerLocal.singleClassQueryList(StockEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockByTransito(java.math.BigDecimal transito) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transito", transito);
		return queryManagerLocal.singleClassQueryList(StockEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(StockEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockByFhUtlModificacion(java.sql.Timestamp fhUtlModificacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fhUtlModificacion", fhUtlModificacion);
		return queryManagerLocal.singleClassQueryList(StockEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of StockIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findStockByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(StockEJB.class, aMap);      
    }

/////////////////
}
