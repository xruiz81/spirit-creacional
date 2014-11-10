package com.spirit.cartera.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.cartera.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _NotaCreditoDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_NotaCreditoDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.NotaCreditoDetalleIf addNotaCreditoDetalle(com.spirit.cartera.entity.NotaCreditoDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      NotaCreditoDetalleEJB value = new NotaCreditoDetalleEJB();
      try {
      value.setId(model.getId());
      value.setDocumentoId(model.getDocumentoId());
      value.setNotaCreditoId(model.getNotaCreditoId());
      value.setProductoId(model.getProductoId());
      value.setCantidad(model.getCantidad());
      value.setValor(model.getValor());
      value.setIva(model.getIva());
      value.setIce(model.getIce());
      value.setOtroImpuesto(model.getOtroImpuesto());
      value.setDescripcion(model.getDescripcion());
      value.setTipoReferencia(model.getTipoReferencia());
      value.setReferenciaId(model.getReferenciaId());
      value.setTipoNota(model.getTipoNota());
      value.setObservacion(model.getObservacion());
      value.setTipoPresupuesto(model.getTipoPresupuesto());
      value.setPresupuestoId(model.getPresupuestoId());
      value.setOrdenId(model.getOrdenId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en notaCreditoDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en notaCreditoDetalle.");
      }
     
      return getNotaCreditoDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveNotaCreditoDetalle(com.spirit.cartera.entity.NotaCreditoDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      NotaCreditoDetalleEJB data = new NotaCreditoDetalleEJB();
      data.setId(model.getId());
      data.setDocumentoId(model.getDocumentoId());
      data.setNotaCreditoId(model.getNotaCreditoId());
      data.setProductoId(model.getProductoId());
      data.setCantidad(model.getCantidad());
      data.setValor(model.getValor());
      data.setIva(model.getIva());
      data.setIce(model.getIce());
      data.setOtroImpuesto(model.getOtroImpuesto());
      data.setDescripcion(model.getDescripcion());
      data.setTipoReferencia(model.getTipoReferencia());
      data.setReferenciaId(model.getReferenciaId());
      data.setTipoNota(model.getTipoNota());
      data.setObservacion(model.getObservacion());
      data.setTipoPresupuesto(model.getTipoPresupuesto());
      data.setPresupuestoId(model.getPresupuestoId());
      data.setOrdenId(model.getOrdenId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en notaCreditoDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en notaCreditoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteNotaCreditoDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      NotaCreditoDetalleEJB data = manager.find(NotaCreditoDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en notaCreditoDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en notaCreditoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.NotaCreditoDetalleIf getNotaCreditoDetalle(java.lang.Long id) {
      return (NotaCreditoDetalleEJB)queryManagerLocal.find(NotaCreditoDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getNotaCreditoDetalleList() {
	  return queryManagerLocal.singleClassList(NotaCreditoDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getNotaCreditoDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(NotaCreditoDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getNotaCreditoDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from NotaCreditoDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleByDocumentoId(java.lang.Long documentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoId", documentoId);
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleByNotaCreditoId(java.lang.Long notaCreditoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("notaCreditoId", notaCreditoId);
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleByCantidad(java.math.BigDecimal cantidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantidad", cantidad);
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleByIva(java.math.BigDecimal iva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("iva", iva);
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleByIce(java.math.BigDecimal ice) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ice", ice);
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleByOtroImpuesto(java.math.BigDecimal otroImpuesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("otroImpuesto", otroImpuesto);
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleByTipoReferencia(java.lang.String tipoReferencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoReferencia", tipoReferencia);
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleByReferenciaId(java.lang.Long referenciaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referenciaId", referenciaId);
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleByTipoNota(java.lang.String tipoNota) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoNota", tipoNota);
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleByTipoPresupuesto(java.lang.String tipoPresupuesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoPresupuesto", tipoPresupuesto);
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleByPresupuestoId(java.lang.Long presupuestoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("presupuestoId", presupuestoId);
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleByOrdenId(java.lang.Long ordenId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordenId", ordenId);
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of NotaCreditoDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(NotaCreditoDetalleEJB.class, aMap);      
    }

/////////////////
}
