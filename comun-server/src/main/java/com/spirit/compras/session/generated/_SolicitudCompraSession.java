package com.spirit.compras.session.generated;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.compras.entity.SolicitudCompraEJB;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _SolicitudCompraSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SolicitudCompraSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.SolicitudCompraIf addSolicitudCompra(com.spirit.compras.entity.SolicitudCompraIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SolicitudCompraEJB value = new SolicitudCompraEJB();
      try {
      value.setId(model.getId());
      value.setTipodocumentoId(model.getTipodocumentoId());
      value.setCodigo(model.getCodigo());
      value.setOficinaId(model.getOficinaId());
      value.setBodegaId(model.getBodegaId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setFecha(model.getFecha());
      value.setFechaEntrega(model.getFechaEntrega());
      value.setUsuarioId(model.getUsuarioId());
      value.setEstado(model.getEstado());
      value.setObservacion(model.getObservacion());
      value.setEstadoBpm(model.getEstadoBpm());
      value.setTipoReferencia(model.getTipoReferencia());
      value.setReferencia(model.getReferencia());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en solicitudCompra.", e);
			throw new GenericBusinessException(
					"Error al insertar información en solicitudCompra.");
      }
     
      return getSolicitudCompra(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSolicitudCompra(com.spirit.compras.entity.SolicitudCompraIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SolicitudCompraEJB data = new SolicitudCompraEJB();
      data.setId(model.getId());
      data.setTipodocumentoId(model.getTipodocumentoId());
      data.setCodigo(model.getCodigo());
      data.setOficinaId(model.getOficinaId());
      data.setBodegaId(model.getBodegaId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setFecha(model.getFecha());
      data.setFechaEntrega(model.getFechaEntrega());
      data.setUsuarioId(model.getUsuarioId());
      data.setEstado(model.getEstado());
      data.setObservacion(model.getObservacion());
      data.setEstadoBpm(model.getEstadoBpm());
      data.setTipoReferencia(model.getTipoReferencia());
      data.setReferencia(model.getReferencia());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en solicitudCompra.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en solicitudCompra.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSolicitudCompra(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SolicitudCompraEJB data = manager.find(SolicitudCompraEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en solicitudCompra.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en solicitudCompra.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.SolicitudCompraIf getSolicitudCompra(java.lang.Long id) {
      return (SolicitudCompraEJB)queryManagerLocal.find(SolicitudCompraEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSolicitudCompraList() {
	  return queryManagerLocal.singleClassList(SolicitudCompraEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSolicitudCompraList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SolicitudCompraEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSolicitudCompraListSize() {
      Query countQuery = manager.createQuery("select count(*) from SolicitudCompraEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraByTipodocumentoId(java.lang.Long tipodocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipodocumentoId", tipodocumentoId);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraByBodegaId(java.lang.Long bodegaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bodegaId", bodegaId);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraByFecha(java.sql.Date fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraByFechaEntrega(java.sql.Date fechaEntrega) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaEntrega", fechaEntrega);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraByEstadoBpm(java.lang.String estadoBpm) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estadoBpm", estadoBpm);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraByTipoReferencia(java.lang.String tipoReferencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoReferencia", tipoReferencia);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraByReferencia(java.lang.String referencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referencia", referencia);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SolicitudCompraIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SolicitudCompraEJB.class, aMap);      
    }

/////////////////
}
