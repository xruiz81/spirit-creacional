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
public abstract class _MovimientoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_MovimientoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.MovimientoIf addMovimiento(com.spirit.inventario.entity.MovimientoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      MovimientoEJB value = new MovimientoEJB();
      try {
      value.setId(model.getId());
      value.setTipodocumentoId(model.getTipodocumentoId());
      value.setCodigo(model.getCodigo());
      value.setBodegaId(model.getBodegaId());
      value.setBodegarefId(model.getBodegarefId());
      value.setOrdencompraId(model.getOrdencompraId());
      value.setCompraId(model.getCompraId());
      value.setReferenciaId(model.getReferenciaId());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setFechaDocumento(model.getFechaDocumento());
      value.setCosto(model.getCosto());
      value.setPrecio(model.getPrecio());
      value.setPreimpreso(model.getPreimpreso());
      value.setUsuarioId(model.getUsuarioId());
      value.setUsuarioautId(model.getUsuarioautId());
      value.setObservacion(model.getObservacion());
      value.setEstado(model.getEstado());
      value.setTipodocumentoOrigenId(model.getTipodocumentoOrigenId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en movimiento.", e);
			throw new GenericBusinessException(
					"Error al insertar información en movimiento.");
      }
     
      return getMovimiento(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveMovimiento(com.spirit.inventario.entity.MovimientoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      MovimientoEJB data = new MovimientoEJB();
      data.setId(model.getId());
      data.setTipodocumentoId(model.getTipodocumentoId());
      data.setCodigo(model.getCodigo());
      data.setBodegaId(model.getBodegaId());
      data.setBodegarefId(model.getBodegarefId());
      data.setOrdencompraId(model.getOrdencompraId());
      data.setCompraId(model.getCompraId());
      data.setReferenciaId(model.getReferenciaId());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setFechaDocumento(model.getFechaDocumento());
      data.setCosto(model.getCosto());
      data.setPrecio(model.getPrecio());
      data.setPreimpreso(model.getPreimpreso());
      data.setUsuarioId(model.getUsuarioId());
      data.setUsuarioautId(model.getUsuarioautId());
      data.setObservacion(model.getObservacion());
      data.setEstado(model.getEstado());
      data.setTipodocumentoOrigenId(model.getTipodocumentoOrigenId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en movimiento.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en movimiento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteMovimiento(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      MovimientoEJB data = manager.find(MovimientoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en movimiento.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en movimiento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.MovimientoIf getMovimiento(java.lang.Long id) {
      return (MovimientoEJB)queryManagerLocal.find(MovimientoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMovimientoList() {
	  return queryManagerLocal.singleClassList(MovimientoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMovimientoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(MovimientoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getMovimientoListSize() {
      Query countQuery = manager.createQuery("select count(*) from MovimientoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByTipodocumentoId(java.lang.Long tipodocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipodocumentoId", tipodocumentoId);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByBodegaId(java.lang.Long bodegaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bodegaId", bodegaId);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByBodegarefId(java.lang.Long bodegarefId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bodegarefId", bodegarefId);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByOrdencompraId(java.lang.Long ordencompraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordencompraId", ordencompraId);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByCompraId(java.lang.Long compraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("compraId", compraId);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByReferenciaId(java.lang.Long referenciaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referenciaId", referenciaId);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByFechaDocumento(java.sql.Timestamp fechaDocumento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaDocumento", fechaDocumento);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByCosto(java.math.BigDecimal costo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("costo", costo);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByPrecio(java.math.BigDecimal precio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("precio", precio);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByPreimpreso(java.lang.String preimpreso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("preimpreso", preimpreso);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByUsuarioautId(java.lang.Long usuarioautId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioautId", usuarioautId);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByTipodocumentoOrigenId(java.lang.Long tipodocumentoOrigenId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipodocumentoOrigenId", tipodocumentoOrigenId);
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of MovimientoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(MovimientoEJB.class, aMap);      
    }

/////////////////
}
