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
public abstract class _SolicitudTransferenciaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SolicitudTransferenciaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.SolicitudTransferenciaIf addSolicitudTransferencia(com.spirit.inventario.entity.SolicitudTransferenciaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SolicitudTransferenciaEJB value = new SolicitudTransferenciaEJB();
      try {
      value.setId(model.getId());
      value.setFechaDocumento(model.getFechaDocumento());
      value.setFechaIngreso(model.getFechaIngreso());
      value.setCodigo(model.getCodigo());
      value.setBodegaDesdeId(model.getBodegaDesdeId());
      value.setBodegaHaciaId(model.getBodegaHaciaId());
      value.setObservacion(model.getObservacion());
      value.setEstado(model.getEstado());
      value.setUsuarioId(model.getUsuarioId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en solicitudTransferencia.", e);
			throw new GenericBusinessException(
					"Error al insertar información en solicitudTransferencia.");
      }
     
      return getSolicitudTransferencia(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSolicitudTransferencia(com.spirit.inventario.entity.SolicitudTransferenciaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SolicitudTransferenciaEJB data = new SolicitudTransferenciaEJB();
      data.setId(model.getId());
      data.setFechaDocumento(model.getFechaDocumento());
      data.setFechaIngreso(model.getFechaIngreso());
      data.setCodigo(model.getCodigo());
      data.setBodegaDesdeId(model.getBodegaDesdeId());
      data.setBodegaHaciaId(model.getBodegaHaciaId());
      data.setObservacion(model.getObservacion());
      data.setEstado(model.getEstado());
      data.setUsuarioId(model.getUsuarioId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en solicitudTransferencia.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en solicitudTransferencia.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSolicitudTransferencia(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SolicitudTransferenciaEJB data = manager.find(SolicitudTransferenciaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en solicitudTransferencia.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en solicitudTransferencia.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.SolicitudTransferenciaIf getSolicitudTransferencia(java.lang.Long id) {
      return (SolicitudTransferenciaEJB)queryManagerLocal.find(SolicitudTransferenciaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSolicitudTransferenciaList() {
	  return queryManagerLocal.singleClassList(SolicitudTransferenciaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSolicitudTransferenciaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SolicitudTransferenciaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSolicitudTransferenciaListSize() {
      Query countQuery = manager.createQuery("select count(*) from SolicitudTransferenciaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudTransferenciaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SolicitudTransferenciaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudTransferenciaByFechaDocumento(java.sql.Timestamp fechaDocumento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaDocumento", fechaDocumento);
		return queryManagerLocal.singleClassQueryList(SolicitudTransferenciaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudTransferenciaByFechaIngreso(java.sql.Timestamp fechaIngreso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaIngreso", fechaIngreso);
		return queryManagerLocal.singleClassQueryList(SolicitudTransferenciaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudTransferenciaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(SolicitudTransferenciaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudTransferenciaByBodegaDesdeId(java.lang.Long bodegaDesdeId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bodegaDesdeId", bodegaDesdeId);
		return queryManagerLocal.singleClassQueryList(SolicitudTransferenciaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudTransferenciaByBodegaHaciaId(java.lang.Long bodegaHaciaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bodegaHaciaId", bodegaHaciaId);
		return queryManagerLocal.singleClassQueryList(SolicitudTransferenciaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudTransferenciaByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(SolicitudTransferenciaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudTransferenciaByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(SolicitudTransferenciaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudTransferenciaByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(SolicitudTransferenciaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SolicitudTransferenciaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudTransferenciaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SolicitudTransferenciaEJB.class, aMap);      
    }

/////////////////
}
