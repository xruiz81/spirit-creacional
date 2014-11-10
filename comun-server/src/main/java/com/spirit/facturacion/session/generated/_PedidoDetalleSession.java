package com.spirit.facturacion.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.facturacion.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _PedidoDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PedidoDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PedidoDetalleIf addPedidoDetalle(com.spirit.facturacion.entity.PedidoDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PedidoDetalleEJB value = new PedidoDetalleEJB();
      try {
      value.setId(model.getId());
      value.setDocumentoId(model.getDocumentoId());
      value.setPedidoId(model.getPedidoId());
      value.setProductoId(model.getProductoId());
      value.setLoteId(model.getLoteId());
      value.setDescripcion(model.getDescripcion());
      value.setMotivodocumentoId(model.getMotivodocumentoId());
      value.setCantidadpedida(model.getCantidadpedida());
      value.setCantidad(model.getCantidad());
      value.setPrecio(model.getPrecio());
      value.setPrecioreal(model.getPrecioreal());
      value.setDescuento(model.getDescuento());
      value.setValor(model.getValor());
      value.setIva(model.getIva());
      value.setIce(model.getIce());
      value.setOtroimpuesto(model.getOtroimpuesto());
      value.setDescuentoGlobal(model.getDescuentoGlobal());
      value.setCodigoPersonalizacion(model.getCodigoPersonalizacion());
      value.setGiftcardId(model.getGiftcardId());
      value.setPorcentajeDescuentosVarios(model.getPorcentajeDescuentosVarios());
      value.setComprasReembolsoAsociadas(model.getComprasReembolsoAsociadas());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en pedidoDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en pedidoDetalle.");
      }
     
      return getPedidoDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePedidoDetalle(com.spirit.facturacion.entity.PedidoDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PedidoDetalleEJB data = new PedidoDetalleEJB();
      data.setId(model.getId());
      data.setDocumentoId(model.getDocumentoId());
      data.setPedidoId(model.getPedidoId());
      data.setProductoId(model.getProductoId());
      data.setLoteId(model.getLoteId());
      data.setDescripcion(model.getDescripcion());
      data.setMotivodocumentoId(model.getMotivodocumentoId());
      data.setCantidadpedida(model.getCantidadpedida());
      data.setCantidad(model.getCantidad());
      data.setPrecio(model.getPrecio());
      data.setPrecioreal(model.getPrecioreal());
      data.setDescuento(model.getDescuento());
      data.setValor(model.getValor());
      data.setIva(model.getIva());
      data.setIce(model.getIce());
      data.setOtroimpuesto(model.getOtroimpuesto());
      data.setDescuentoGlobal(model.getDescuentoGlobal());
      data.setCodigoPersonalizacion(model.getCodigoPersonalizacion());
      data.setGiftcardId(model.getGiftcardId());
      data.setPorcentajeDescuentosVarios(model.getPorcentajeDescuentosVarios());
      data.setComprasReembolsoAsociadas(model.getComprasReembolsoAsociadas());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en pedidoDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en pedidoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePedidoDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PedidoDetalleEJB data = manager.find(PedidoDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en pedidoDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en pedidoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PedidoDetalleIf getPedidoDetalle(java.lang.Long id) {
      return (PedidoDetalleEJB)queryManagerLocal.find(PedidoDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPedidoDetalleList() {
	  return queryManagerLocal.singleClassList(PedidoDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPedidoDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PedidoDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPedidoDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from PedidoDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByDocumentoId(java.lang.Long documentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoId", documentoId);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByPedidoId(java.lang.Long pedidoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("pedidoId", pedidoId);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByLoteId(java.lang.Long loteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("loteId", loteId);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByMotivodocumentoId(java.lang.Long motivodocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("motivodocumentoId", motivodocumentoId);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByCantidadpedida(java.math.BigDecimal cantidadpedida) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantidadpedida", cantidadpedida);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByCantidad(java.math.BigDecimal cantidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantidad", cantidad);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByPrecio(java.math.BigDecimal precio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("precio", precio);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByPrecioreal(java.math.BigDecimal precioreal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("precioreal", precioreal);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByDescuento(java.math.BigDecimal descuento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuento", descuento);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByIva(java.math.BigDecimal iva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("iva", iva);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByIce(java.math.BigDecimal ice) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ice", ice);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByOtroimpuesto(java.math.BigDecimal otroimpuesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("otroimpuesto", otroimpuesto);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByDescuentoGlobal(java.math.BigDecimal descuentoGlobal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuentoGlobal", descuentoGlobal);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByCodigoPersonalizacion(java.lang.String codigoPersonalizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigoPersonalizacion", codigoPersonalizacion);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByGiftcardId(java.lang.Long giftcardId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("giftcardId", giftcardId);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByPorcentajeDescuentosVarios(java.math.BigDecimal porcentajeDescuentosVarios) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentosVarios", porcentajeDescuentosVarios);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByComprasReembolsoAsociadas(java.lang.String comprasReembolsoAsociadas) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("comprasReembolsoAsociadas", comprasReembolsoAsociadas);
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PedidoDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PedidoDetalleEJB.class, aMap);      
    }

/////////////////
}
