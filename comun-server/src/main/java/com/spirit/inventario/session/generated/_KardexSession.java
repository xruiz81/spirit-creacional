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
public abstract class _KardexSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_KardexSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.KardexIf addKardex(com.spirit.inventario.entity.KardexIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      KardexEJB value = new KardexEJB();
      try {
      value.setMovimientoId(model.getMovimientoId());
      value.setMovimientoCodigo(model.getMovimientoCodigo());
      value.setId(model.getId());
      value.setEmpresaId(model.getEmpresaId());
      value.setEmpresaNombre(model.getEmpresaNombre());
      value.setOficinaId(model.getOficinaId());
      value.setOficinaCodigo(model.getOficinaCodigo());
      value.setOficinaNombre(model.getOficinaNombre());
      value.setBodegaId(model.getBodegaId());
      value.setBodegaCodigo(model.getBodegaCodigo());
      value.setBodegaNombre(model.getBodegaNombre());
      value.setProductoId(model.getProductoId());
      value.setProductoCodigo(model.getProductoCodigo());
      value.setProductoNombre(model.getProductoNombre());
      value.setFechaMovimiento(model.getFechaMovimiento());
      value.setTipodocumentoCodigo(model.getTipodocumentoCodigo());
      value.setTipodocumentoNombre(model.getTipodocumentoNombre());
      value.setTipodocumentoSignostock(model.getTipodocumentoSignostock());
      value.setCantidadDetalle(model.getCantidadDetalle());
      value.setLoteId(model.getLoteId());
      value.setLoteCodigo(model.getLoteCodigo());
      value.setDocumentoCodigo(model.getDocumentoCodigo());
      value.setDocumentoDescripcion(model.getDocumentoDescripcion());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en kardex.", e);
			throw new GenericBusinessException(
					"Error al insertar información en kardex.");
      }
     
      return getKardex(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveKardex(com.spirit.inventario.entity.KardexIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      KardexEJB data = new KardexEJB();
      data.setMovimientoId(model.getMovimientoId());
      data.setMovimientoCodigo(model.getMovimientoCodigo());
      data.setId(model.getId());
      data.setEmpresaId(model.getEmpresaId());
      data.setEmpresaNombre(model.getEmpresaNombre());
      data.setOficinaId(model.getOficinaId());
      data.setOficinaCodigo(model.getOficinaCodigo());
      data.setOficinaNombre(model.getOficinaNombre());
      data.setBodegaId(model.getBodegaId());
      data.setBodegaCodigo(model.getBodegaCodigo());
      data.setBodegaNombre(model.getBodegaNombre());
      data.setProductoId(model.getProductoId());
      data.setProductoCodigo(model.getProductoCodigo());
      data.setProductoNombre(model.getProductoNombre());
      data.setFechaMovimiento(model.getFechaMovimiento());
      data.setTipodocumentoCodigo(model.getTipodocumentoCodigo());
      data.setTipodocumentoNombre(model.getTipodocumentoNombre());
      data.setTipodocumentoSignostock(model.getTipodocumentoSignostock());
      data.setCantidadDetalle(model.getCantidadDetalle());
      data.setLoteId(model.getLoteId());
      data.setLoteCodigo(model.getLoteCodigo());
      data.setDocumentoCodigo(model.getDocumentoCodigo());
      data.setDocumentoDescripcion(model.getDocumentoDescripcion());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en kardex.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en kardex.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteKardex(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      KardexEJB data = manager.find(KardexEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en kardex.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en kardex.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.KardexIf getKardex(java.lang.Long id) {
      return (KardexEJB)queryManagerLocal.find(KardexEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getKardexList() {
	  return queryManagerLocal.singleClassList(KardexEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getKardexList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(KardexEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getKardexListSize() {
      Query countQuery = manager.createQuery("select count(*) from KardexEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByMovimientoId(java.lang.Long movimientoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("movimientoId", movimientoId);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByMovimientoCodigo(java.lang.String movimientoCodigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("movimientoCodigo", movimientoCodigo);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByEmpresaNombre(java.lang.String empresaNombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaNombre", empresaNombre);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByOficinaCodigo(java.lang.String oficinaCodigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaCodigo", oficinaCodigo);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByOficinaNombre(java.lang.String oficinaNombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaNombre", oficinaNombre);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByBodegaId(java.lang.Long bodegaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bodegaId", bodegaId);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByBodegaCodigo(java.lang.String bodegaCodigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bodegaCodigo", bodegaCodigo);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByBodegaNombre(java.lang.String bodegaNombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bodegaNombre", bodegaNombre);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByProductoCodigo(java.lang.String productoCodigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoCodigo", productoCodigo);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByProductoNombre(java.lang.String productoNombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoNombre", productoNombre);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByFechaMovimiento(java.sql.Timestamp fechaMovimiento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaMovimiento", fechaMovimiento);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByTipodocumentoCodigo(java.lang.String tipodocumentoCodigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipodocumentoCodigo", tipodocumentoCodigo);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByTipodocumentoNombre(java.lang.String tipodocumentoNombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipodocumentoNombre", tipodocumentoNombre);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByTipodocumentoSignostock(java.lang.String tipodocumentoSignostock) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipodocumentoSignostock", tipodocumentoSignostock);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByCantidadDetalle(java.math.BigDecimal cantidadDetalle) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantidadDetalle", cantidadDetalle);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByLoteId(java.lang.Long loteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("loteId", loteId);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByLoteCodigo(java.lang.String loteCodigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("loteCodigo", loteCodigo);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByDocumentoCodigo(java.lang.String documentoCodigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoCodigo", documentoCodigo);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByDocumentoDescripcion(java.lang.String documentoDescripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoDescripcion", documentoDescripcion);
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of KardexIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findKardexByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(KardexEJB.class, aMap);      
    }

/////////////////
}
