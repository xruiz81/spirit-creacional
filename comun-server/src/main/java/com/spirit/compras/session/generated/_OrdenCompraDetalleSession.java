package com.spirit.compras.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.compras.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _OrdenCompraDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_OrdenCompraDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.OrdenCompraDetalleIf addOrdenCompraDetalle(com.spirit.compras.entity.OrdenCompraDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      OrdenCompraDetalleEJB value = new OrdenCompraDetalleEJB();
      try {
      value.setId(model.getId());
      value.setDocumentoId(model.getDocumentoId());
      value.setProductoId(model.getProductoId());
      value.setOrdencompraId(model.getOrdencompraId());
      value.setCantidad(model.getCantidad());
      value.setValor(model.getValor());
      value.setDescuentoAgenciaCompra(model.getDescuentoAgenciaCompra());
      value.setIva(model.getIva());
      value.setIce(model.getIce());
      value.setOtroImpuesto(model.getOtroImpuesto());
      value.setDescripcion(model.getDescripcion());
      value.setPorcentajeNegociacionDirecta(model.getPorcentajeNegociacionDirecta());
      value.setPorcentajeComisionPura(model.getPorcentajeComisionPura());
      value.setPorcentajeDescuentosVariosCompra(model.getPorcentajeDescuentosVariosCompra());
      value.setPorcentajeDescuentoEspecial(model.getPorcentajeDescuentoEspecial());
      value.setFechaPublicacion(model.getFechaPublicacion());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en ordenCompraDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en ordenCompraDetalle.");
      }
     
      return getOrdenCompraDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveOrdenCompraDetalle(com.spirit.compras.entity.OrdenCompraDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      OrdenCompraDetalleEJB data = new OrdenCompraDetalleEJB();
      data.setId(model.getId());
      data.setDocumentoId(model.getDocumentoId());
      data.setProductoId(model.getProductoId());
      data.setOrdencompraId(model.getOrdencompraId());
      data.setCantidad(model.getCantidad());
      data.setValor(model.getValor());
      data.setDescuentoAgenciaCompra(model.getDescuentoAgenciaCompra());
      data.setIva(model.getIva());
      data.setIce(model.getIce());
      data.setOtroImpuesto(model.getOtroImpuesto());
      data.setDescripcion(model.getDescripcion());
      data.setPorcentajeNegociacionDirecta(model.getPorcentajeNegociacionDirecta());
      data.setPorcentajeComisionPura(model.getPorcentajeComisionPura());
      data.setPorcentajeDescuentosVariosCompra(model.getPorcentajeDescuentosVariosCompra());
      data.setPorcentajeDescuentoEspecial(model.getPorcentajeDescuentoEspecial());
      data.setFechaPublicacion(model.getFechaPublicacion());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en ordenCompraDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en ordenCompraDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteOrdenCompraDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      OrdenCompraDetalleEJB data = manager.find(OrdenCompraDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en ordenCompraDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en ordenCompraDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.OrdenCompraDetalleIf getOrdenCompraDetalle(java.lang.Long id) {
      return (OrdenCompraDetalleEJB)queryManagerLocal.find(OrdenCompraDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenCompraDetalleList() {
	  return queryManagerLocal.singleClassList(OrdenCompraDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenCompraDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(OrdenCompraDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getOrdenCompraDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from OrdenCompraDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(OrdenCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraDetalleByDocumentoId(java.lang.Long documentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoId", documentoId);
		return queryManagerLocal.singleClassQueryList(OrdenCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraDetalleByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(OrdenCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraDetalleByOrdencompraId(java.lang.Long ordencompraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordencompraId", ordencompraId);
		return queryManagerLocal.singleClassQueryList(OrdenCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraDetalleByCantidad(java.lang.Long cantidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantidad", cantidad);
		return queryManagerLocal.singleClassQueryList(OrdenCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraDetalleByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(OrdenCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraDetalleByDescuentoAgenciaCompra(java.math.BigDecimal descuentoAgenciaCompra) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuentoAgenciaCompra", descuentoAgenciaCompra);
		return queryManagerLocal.singleClassQueryList(OrdenCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraDetalleByIva(java.math.BigDecimal iva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("iva", iva);
		return queryManagerLocal.singleClassQueryList(OrdenCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraDetalleByIce(java.math.BigDecimal ice) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ice", ice);
		return queryManagerLocal.singleClassQueryList(OrdenCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraDetalleByOtroImpuesto(java.math.BigDecimal otroImpuesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("otroImpuesto", otroImpuesto);
		return queryManagerLocal.singleClassQueryList(OrdenCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraDetalleByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(OrdenCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraDetalleByPorcentajeNegociacionDirecta(java.math.BigDecimal porcentajeNegociacionDirecta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeNegociacionDirecta", porcentajeNegociacionDirecta);
		return queryManagerLocal.singleClassQueryList(OrdenCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraDetalleByPorcentajeComisionPura(java.math.BigDecimal porcentajeComisionPura) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeComisionPura", porcentajeComisionPura);
		return queryManagerLocal.singleClassQueryList(OrdenCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraDetalleByPorcentajeDescuentosVariosCompra(java.math.BigDecimal porcentajeDescuentosVariosCompra) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentosVariosCompra", porcentajeDescuentosVariosCompra);
		return queryManagerLocal.singleClassQueryList(OrdenCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraDetalleByPorcentajeDescuentoEspecial(java.math.BigDecimal porcentajeDescuentoEspecial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentoEspecial", porcentajeDescuentoEspecial);
		return queryManagerLocal.singleClassQueryList(OrdenCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraDetalleByFechaPublicacion(java.sql.Timestamp fechaPublicacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaPublicacion", fechaPublicacion);
		return queryManagerLocal.singleClassQueryList(OrdenCompraDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of OrdenCompraDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(OrdenCompraDetalleEJB.class, aMap);      
    }

/////////////////
}
