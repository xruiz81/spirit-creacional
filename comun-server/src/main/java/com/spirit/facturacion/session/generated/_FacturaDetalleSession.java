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
public abstract class _FacturaDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_FacturaDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.FacturaDetalleIf addFacturaDetalle(com.spirit.facturacion.entity.FacturaDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      FacturaDetalleEJB value = new FacturaDetalleEJB();
      try {
      value.setId(model.getId());
      value.setDocumentoId(model.getDocumentoId());
      value.setFacturaId(model.getFacturaId());
      value.setProductoId(model.getProductoId());
      value.setLoteId(model.getLoteId());
      value.setDescripcion(model.getDescripcion());
      value.setMotivodocumentoId(model.getMotivodocumentoId());
      value.setCantidad(model.getCantidad());
      value.setPrecio(model.getPrecio());
      value.setPrecioReal(model.getPrecioReal());
      value.setDescuento(model.getDescuento());
      value.setValor(model.getValor());
      value.setIva(model.getIva());
      value.setIce(model.getIce());
      value.setOtroImpuesto(model.getOtroImpuesto());
      value.setCosto(model.getCosto());
      value.setLineaId(model.getLineaId());
      value.setCantidadDevuelta(model.getCantidadDevuelta());
      value.setDescuentoGlobal(model.getDescuentoGlobal());
      value.setIdSriClienteRetencion(model.getIdSriClienteRetencion());
      value.setPorcentajeDescuentosVarios(model.getPorcentajeDescuentosVarios());
      value.setComprasReembolsoAsociadas(model.getComprasReembolsoAsociadas());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en facturaDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en facturaDetalle.");
      }
     
      return getFacturaDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveFacturaDetalle(com.spirit.facturacion.entity.FacturaDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      FacturaDetalleEJB data = new FacturaDetalleEJB();
      data.setId(model.getId());
      data.setDocumentoId(model.getDocumentoId());
      data.setFacturaId(model.getFacturaId());
      data.setProductoId(model.getProductoId());
      data.setLoteId(model.getLoteId());
      data.setDescripcion(model.getDescripcion());
      data.setMotivodocumentoId(model.getMotivodocumentoId());
      data.setCantidad(model.getCantidad());
      data.setPrecio(model.getPrecio());
      data.setPrecioReal(model.getPrecioReal());
      data.setDescuento(model.getDescuento());
      data.setValor(model.getValor());
      data.setIva(model.getIva());
      data.setIce(model.getIce());
      data.setOtroImpuesto(model.getOtroImpuesto());
      data.setCosto(model.getCosto());
      data.setLineaId(model.getLineaId());
      data.setCantidadDevuelta(model.getCantidadDevuelta());
      data.setDescuentoGlobal(model.getDescuentoGlobal());
      data.setIdSriClienteRetencion(model.getIdSriClienteRetencion());
      data.setPorcentajeDescuentosVarios(model.getPorcentajeDescuentosVarios());
      data.setComprasReembolsoAsociadas(model.getComprasReembolsoAsociadas());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en facturaDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en facturaDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteFacturaDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      FacturaDetalleEJB data = manager.find(FacturaDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en facturaDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en facturaDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.FacturaDetalleIf getFacturaDetalle(java.lang.Long id) {
      return (FacturaDetalleEJB)queryManagerLocal.find(FacturaDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getFacturaDetalleList() {
	  return queryManagerLocal.singleClassList(FacturaDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getFacturaDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(FacturaDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getFacturaDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from FacturaDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByDocumentoId(java.lang.Long documentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoId", documentoId);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByFacturaId(java.lang.Long facturaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("facturaId", facturaId);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByLoteId(java.lang.Long loteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("loteId", loteId);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByMotivodocumentoId(java.lang.Long motivodocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("motivodocumentoId", motivodocumentoId);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByCantidad(java.math.BigDecimal cantidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantidad", cantidad);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByPrecio(java.math.BigDecimal precio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("precio", precio);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByPrecioReal(java.math.BigDecimal precioReal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("precioReal", precioReal);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByDescuento(java.math.BigDecimal descuento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuento", descuento);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByIva(java.math.BigDecimal iva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("iva", iva);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByIce(java.math.BigDecimal ice) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ice", ice);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByOtroImpuesto(java.math.BigDecimal otroImpuesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("otroImpuesto", otroImpuesto);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByCosto(java.math.BigDecimal costo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("costo", costo);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByLineaId(java.lang.Long lineaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("lineaId", lineaId);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByCantidadDevuelta(java.math.BigDecimal cantidadDevuelta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantidadDevuelta", cantidadDevuelta);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByDescuentoGlobal(java.math.BigDecimal descuentoGlobal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuentoGlobal", descuentoGlobal);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByIdSriClienteRetencion(java.lang.Long idSriClienteRetencion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("idSriClienteRetencion", idSriClienteRetencion);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByPorcentajeDescuentosVarios(java.math.BigDecimal porcentajeDescuentosVarios) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentosVarios", porcentajeDescuentosVarios);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByComprasReembolsoAsociadas(java.lang.String comprasReembolsoAsociadas) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("comprasReembolsoAsociadas", comprasReembolsoAsociadas);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of FacturaDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(FacturaDetalleEJB.class, aMap);      
    }

/////////////////
}
