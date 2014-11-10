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
public abstract class _PresupuestoDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PresupuestoDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PresupuestoDetalleIf addPresupuestoDetalle(com.spirit.medios.entity.PresupuestoDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PresupuestoDetalleEJB value = new PresupuestoDetalleEJB();
      try {
      value.setId(model.getId());
      value.setPresupuestoId(model.getPresupuestoId());
      value.setProductoId(model.getProductoId());
      value.setConcepto(model.getConcepto());
      value.setCantidad(model.getCantidad());
      value.setPrecioagencia(model.getPrecioagencia());
      value.setPrecioventa(model.getPrecioventa());
      value.setPorcentajeDescuentoAgenciaVenta(model.getPorcentajeDescuentoAgenciaVenta());
      value.setIva(model.getIva());
      value.setProveedorId(model.getProveedorId());
      value.setPorcentajeDescuentoAgenciaCompra(model.getPorcentajeDescuentoAgenciaCompra());
      value.setIvaCompra(model.getIvaCompra());
      value.setReporte(model.getReporte());
      value.setOrden(model.getOrden());
      value.setOrdenCompraId(model.getOrdenCompraId());
      value.setPorcentajeNegociacionDirecta(model.getPorcentajeNegociacionDirecta());
      value.setPorcentajeComisionPura(model.getPorcentajeComisionPura());
      value.setPorcentajeDescuentosVariosCompra(model.getPorcentajeDescuentosVariosCompra());
      value.setPorcentajeDescuentosVariosVenta(model.getPorcentajeDescuentosVariosVenta());
      value.setPorcentajeDescuentoEspecialCompra(model.getPorcentajeDescuentoEspecialCompra());
      value.setPorcentajeDescuentoEspecialVenta(model.getPorcentajeDescuentoEspecialVenta());
      value.setFechaPublicacion(model.getFechaPublicacion());
      value.setPorcentajeNotaCreditoCompra(model.getPorcentajeNotaCreditoCompra());
      value.setPorcentajeComisionAdicional(model.getPorcentajeComisionAdicional());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en presupuestoDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en presupuestoDetalle.");
      }
     
      return getPresupuestoDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePresupuestoDetalle(com.spirit.medios.entity.PresupuestoDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PresupuestoDetalleEJB data = new PresupuestoDetalleEJB();
      data.setId(model.getId());
      data.setPresupuestoId(model.getPresupuestoId());
      data.setProductoId(model.getProductoId());
      data.setConcepto(model.getConcepto());
      data.setCantidad(model.getCantidad());
      data.setPrecioagencia(model.getPrecioagencia());
      data.setPrecioventa(model.getPrecioventa());
      data.setPorcentajeDescuentoAgenciaVenta(model.getPorcentajeDescuentoAgenciaVenta());
      data.setIva(model.getIva());
      data.setProveedorId(model.getProveedorId());
      data.setPorcentajeDescuentoAgenciaCompra(model.getPorcentajeDescuentoAgenciaCompra());
      data.setIvaCompra(model.getIvaCompra());
      data.setReporte(model.getReporte());
      data.setOrden(model.getOrden());
      data.setOrdenCompraId(model.getOrdenCompraId());
      data.setPorcentajeNegociacionDirecta(model.getPorcentajeNegociacionDirecta());
      data.setPorcentajeComisionPura(model.getPorcentajeComisionPura());
      data.setPorcentajeDescuentosVariosCompra(model.getPorcentajeDescuentosVariosCompra());
      data.setPorcentajeDescuentosVariosVenta(model.getPorcentajeDescuentosVariosVenta());
      data.setPorcentajeDescuentoEspecialCompra(model.getPorcentajeDescuentoEspecialCompra());
      data.setPorcentajeDescuentoEspecialVenta(model.getPorcentajeDescuentoEspecialVenta());
      data.setFechaPublicacion(model.getFechaPublicacion());
      data.setPorcentajeNotaCreditoCompra(model.getPorcentajeNotaCreditoCompra());
      data.setPorcentajeComisionAdicional(model.getPorcentajeComisionAdicional());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en presupuestoDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en presupuestoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePresupuestoDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PresupuestoDetalleEJB data = manager.find(PresupuestoDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en presupuestoDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en presupuestoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PresupuestoDetalleIf getPresupuestoDetalle(java.lang.Long id) {
      return (PresupuestoDetalleEJB)queryManagerLocal.find(PresupuestoDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPresupuestoDetalleList() {
	  return queryManagerLocal.singleClassList(PresupuestoDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPresupuestoDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PresupuestoDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPresupuestoDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from PresupuestoDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByPresupuestoId(java.lang.Long presupuestoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("presupuestoId", presupuestoId);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByConcepto(java.lang.String concepto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("concepto", concepto);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByCantidad(java.math.BigDecimal cantidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantidad", cantidad);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByPrecioagencia(java.math.BigDecimal precioagencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("precioagencia", precioagencia);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByPrecioventa(java.math.BigDecimal precioventa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("precioventa", precioventa);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByPorcentajeDescuentoAgenciaVenta(java.math.BigDecimal porcentajeDescuentoAgenciaVenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentoAgenciaVenta", porcentajeDescuentoAgenciaVenta);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByIva(java.math.BigDecimal iva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("iva", iva);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByProveedorId(java.lang.Long proveedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("proveedorId", proveedorId);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByPorcentajeDescuentoAgenciaCompra(java.math.BigDecimal porcentajeDescuentoAgenciaCompra) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentoAgenciaCompra", porcentajeDescuentoAgenciaCompra);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByIvaCompra(java.math.BigDecimal ivaCompra) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ivaCompra", ivaCompra);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByReporte(java.lang.String reporte) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("reporte", reporte);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByOrden(java.lang.Integer orden) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("orden", orden);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByOrdenCompraId(java.lang.Long ordenCompraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordenCompraId", ordenCompraId);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByPorcentajeNegociacionDirecta(java.math.BigDecimal porcentajeNegociacionDirecta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeNegociacionDirecta", porcentajeNegociacionDirecta);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByPorcentajeComisionPura(java.math.BigDecimal porcentajeComisionPura) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeComisionPura", porcentajeComisionPura);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByPorcentajeDescuentosVariosCompra(java.math.BigDecimal porcentajeDescuentosVariosCompra) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentosVariosCompra", porcentajeDescuentosVariosCompra);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByPorcentajeDescuentosVariosVenta(java.math.BigDecimal porcentajeDescuentosVariosVenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentosVariosVenta", porcentajeDescuentosVariosVenta);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByPorcentajeDescuentoEspecialCompra(java.math.BigDecimal porcentajeDescuentoEspecialCompra) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentoEspecialCompra", porcentajeDescuentoEspecialCompra);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByPorcentajeDescuentoEspecialVenta(java.math.BigDecimal porcentajeDescuentoEspecialVenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentoEspecialVenta", porcentajeDescuentoEspecialVenta);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByFechaPublicacion(java.sql.Timestamp fechaPublicacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaPublicacion", fechaPublicacion);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByPorcentajeNotaCreditoCompra(java.math.BigDecimal porcentajeNotaCreditoCompra) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeNotaCreditoCompra", porcentajeNotaCreditoCompra);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByPorcentajeComisionAdicional(java.math.BigDecimal porcentajeComisionAdicional) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeComisionAdicional", porcentajeComisionAdicional);
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PresupuestoDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PresupuestoDetalleEJB.class, aMap);      
    }

/////////////////
}
