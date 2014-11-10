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
public abstract class _CompraDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CompraDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.CompraDetalleIf addCompraDetalle(com.spirit.compras.entity.CompraDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CompraDetalleEJB value = new CompraDetalleEJB();
      try {
      value.setId(model.getId());
      value.setDocumentoId(model.getDocumentoId());
      value.setCompraId(model.getCompraId());
      value.setProductoId(model.getProductoId());
      value.setCantidad(model.getCantidad());
      value.setValor(model.getValor());
      value.setDescuento(model.getDescuento());
      value.setIva(model.getIva());
      value.setIce(model.getIce());
      value.setOtroImpuesto(model.getOtroImpuesto());
      value.setIdSriAir(model.getIdSriAir());
      value.setDescripcion(model.getDescripcion());
      value.setSriIvaRetencionId(model.getSriIvaRetencionId());
      value.setPorcentajeDescuentosVarios(model.getPorcentajeDescuentosVarios());
      value.setPorcentajeDescuentoEspecial(model.getPorcentajeDescuentoEspecial());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en compraDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en compraDetalle.");
      }
     
      return getCompraDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCompraDetalle(com.spirit.compras.entity.CompraDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CompraDetalleEJB data = new CompraDetalleEJB();
      data.setId(model.getId());
      data.setDocumentoId(model.getDocumentoId());
      data.setCompraId(model.getCompraId());
      data.setProductoId(model.getProductoId());
      data.setCantidad(model.getCantidad());
      data.setValor(model.getValor());
      data.setDescuento(model.getDescuento());
      data.setIva(model.getIva());
      data.setIce(model.getIce());
      data.setOtroImpuesto(model.getOtroImpuesto());
      data.setIdSriAir(model.getIdSriAir());
      data.setDescripcion(model.getDescripcion());
      data.setSriIvaRetencionId(model.getSriIvaRetencionId());
      data.setPorcentajeDescuentosVarios(model.getPorcentajeDescuentosVarios());
      data.setPorcentajeDescuentoEspecial(model.getPorcentajeDescuentoEspecial());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en compraDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en compraDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCompraDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CompraDetalleEJB data = manager.find(CompraDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en compraDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en compraDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.CompraDetalleIf getCompraDetalle(java.lang.Long id) {
      return (CompraDetalleEJB)queryManagerLocal.find(CompraDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCompraDetalleList() {
	  return queryManagerLocal.singleClassList(CompraDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCompraDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CompraDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCompraDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from CompraDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleByDocumentoId(java.lang.Long documentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoId", documentoId);
		return queryManagerLocal.singleClassQueryList(CompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleByCompraId(java.lang.Long compraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("compraId", compraId);
		return queryManagerLocal.singleClassQueryList(CompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(CompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleByCantidad(java.lang.Long cantidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantidad", cantidad);
		return queryManagerLocal.singleClassQueryList(CompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(CompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleByDescuento(java.math.BigDecimal descuento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuento", descuento);
		return queryManagerLocal.singleClassQueryList(CompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleByIva(java.math.BigDecimal iva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("iva", iva);
		return queryManagerLocal.singleClassQueryList(CompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleByIce(java.math.BigDecimal ice) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ice", ice);
		return queryManagerLocal.singleClassQueryList(CompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleByOtroImpuesto(java.math.BigDecimal otroImpuesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("otroImpuesto", otroImpuesto);
		return queryManagerLocal.singleClassQueryList(CompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleByIdSriAir(java.lang.Long idSriAir) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("idSriAir", idSriAir);
		return queryManagerLocal.singleClassQueryList(CompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(CompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleBySriIvaRetencionId(java.lang.Long sriIvaRetencionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("sriIvaRetencionId", sriIvaRetencionId);
		return queryManagerLocal.singleClassQueryList(CompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleByPorcentajeDescuentosVarios(java.math.BigDecimal porcentajeDescuentosVarios) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentosVarios", porcentajeDescuentosVarios);
		return queryManagerLocal.singleClassQueryList(CompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleByPorcentajeDescuentoEspecial(java.math.BigDecimal porcentajeDescuentoEspecial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentoEspecial", porcentajeDescuentoEspecial);
		return queryManagerLocal.singleClassQueryList(CompraDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CompraDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CompraDetalleEJB.class, aMap);      
    }

/////////////////
}
