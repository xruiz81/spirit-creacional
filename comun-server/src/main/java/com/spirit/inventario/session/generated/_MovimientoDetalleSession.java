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
public abstract class _MovimientoDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_MovimientoDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.MovimientoDetalleIf addMovimientoDetalle(com.spirit.inventario.entity.MovimientoDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      MovimientoDetalleEJB value = new MovimientoDetalleEJB();
      try {
      value.setId(model.getId());
      value.setMovimientoId(model.getMovimientoId());
      value.setDocumentoId(model.getDocumentoId());
      value.setLoteId(model.getLoteId());
      value.setCantidad(model.getCantidad());
      value.setCosto(model.getCosto());
      value.setPrecio(model.getPrecio());
      value.setCostoLifo(model.getCostoLifo());
      value.setCostoFifo(model.getCostoFifo());
      value.setValorUnitario(model.getValorUnitario());
      value.setPromedioUnitario(model.getPromedioUnitario());
      value.setStockValorizado(model.getStockValorizado());
      value.setStockAnterior(model.getStockAnterior());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en movimientoDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en movimientoDetalle.");
      }
     
      return getMovimientoDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveMovimientoDetalle(com.spirit.inventario.entity.MovimientoDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      MovimientoDetalleEJB data = new MovimientoDetalleEJB();
      data.setId(model.getId());
      data.setMovimientoId(model.getMovimientoId());
      data.setDocumentoId(model.getDocumentoId());
      data.setLoteId(model.getLoteId());
      data.setCantidad(model.getCantidad());
      data.setCosto(model.getCosto());
      data.setPrecio(model.getPrecio());
      data.setCostoLifo(model.getCostoLifo());
      data.setCostoFifo(model.getCostoFifo());
      data.setValorUnitario(model.getValorUnitario());
      data.setPromedioUnitario(model.getPromedioUnitario());
      data.setStockValorizado(model.getStockValorizado());
      data.setStockAnterior(model.getStockAnterior());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en movimientoDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en movimientoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteMovimientoDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      MovimientoDetalleEJB data = manager.find(MovimientoDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en movimientoDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en movimientoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.MovimientoDetalleIf getMovimientoDetalle(java.lang.Long id) {
      return (MovimientoDetalleEJB)queryManagerLocal.find(MovimientoDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMovimientoDetalleList() {
	  return queryManagerLocal.singleClassList(MovimientoDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMovimientoDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(MovimientoDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getMovimientoDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from MovimientoDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(MovimientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoDetalleByMovimientoId(java.lang.Long movimientoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("movimientoId", movimientoId);
		return queryManagerLocal.singleClassQueryList(MovimientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoDetalleByDocumentoId(java.lang.Long documentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoId", documentoId);
		return queryManagerLocal.singleClassQueryList(MovimientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoDetalleByLoteId(java.lang.Long loteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("loteId", loteId);
		return queryManagerLocal.singleClassQueryList(MovimientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoDetalleByCantidad(java.math.BigDecimal cantidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantidad", cantidad);
		return queryManagerLocal.singleClassQueryList(MovimientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoDetalleByCosto(java.math.BigDecimal costo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("costo", costo);
		return queryManagerLocal.singleClassQueryList(MovimientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoDetalleByPrecio(java.math.BigDecimal precio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("precio", precio);
		return queryManagerLocal.singleClassQueryList(MovimientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoDetalleByCostoLifo(java.math.BigDecimal costoLifo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("costoLifo", costoLifo);
		return queryManagerLocal.singleClassQueryList(MovimientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoDetalleByCostoFifo(java.math.BigDecimal costoFifo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("costoFifo", costoFifo);
		return queryManagerLocal.singleClassQueryList(MovimientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoDetalleByValorUnitario(java.math.BigDecimal valorUnitario) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorUnitario", valorUnitario);
		return queryManagerLocal.singleClassQueryList(MovimientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoDetalleByPromedioUnitario(java.math.BigDecimal promedioUnitario) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("promedioUnitario", promedioUnitario);
		return queryManagerLocal.singleClassQueryList(MovimientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoDetalleByStockValorizado(java.math.BigDecimal stockValorizado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("stockValorizado", stockValorizado);
		return queryManagerLocal.singleClassQueryList(MovimientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoDetalleByStockAnterior(java.math.BigDecimal stockAnterior) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("stockAnterior", stockAnterior);
		return queryManagerLocal.singleClassQueryList(MovimientoDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of MovimientoDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(MovimientoDetalleEJB.class, aMap);      
    }

/////////////////
}
