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
public abstract class _SolicitudTransferenciaDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SolicitudTransferenciaDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf addSolicitudTransferenciaDetalle(com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SolicitudTransferenciaDetalleEJB value = new SolicitudTransferenciaDetalleEJB();
      try {
      value.setId(model.getId());
      value.setSolicitudTransferenciaId(model.getSolicitudTransferenciaId());
      value.setProductoId(model.getProductoId());
      value.setLoteId(model.getLoteId());
      value.setCantidad(model.getCantidad());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en solicitudTransferenciaDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en solicitudTransferenciaDetalle.");
      }
     
      return getSolicitudTransferenciaDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSolicitudTransferenciaDetalle(com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SolicitudTransferenciaDetalleEJB data = new SolicitudTransferenciaDetalleEJB();
      data.setId(model.getId());
      data.setSolicitudTransferenciaId(model.getSolicitudTransferenciaId());
      data.setProductoId(model.getProductoId());
      data.setLoteId(model.getLoteId());
      data.setCantidad(model.getCantidad());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en solicitudTransferenciaDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en solicitudTransferenciaDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSolicitudTransferenciaDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SolicitudTransferenciaDetalleEJB data = manager.find(SolicitudTransferenciaDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en solicitudTransferenciaDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en solicitudTransferenciaDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf getSolicitudTransferenciaDetalle(java.lang.Long id) {
      return (SolicitudTransferenciaDetalleEJB)queryManagerLocal.find(SolicitudTransferenciaDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSolicitudTransferenciaDetalleList() {
	  return queryManagerLocal.singleClassList(SolicitudTransferenciaDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSolicitudTransferenciaDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SolicitudTransferenciaDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSolicitudTransferenciaDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from SolicitudTransferenciaDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudTransferenciaDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SolicitudTransferenciaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudTransferenciaDetalleBySolicitudTransferenciaId(java.lang.Long solicitudTransferenciaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("solicitudTransferenciaId", solicitudTransferenciaId);
		return queryManagerLocal.singleClassQueryList(SolicitudTransferenciaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudTransferenciaDetalleByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(SolicitudTransferenciaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudTransferenciaDetalleByLoteId(java.lang.Long loteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("loteId", loteId);
		return queryManagerLocal.singleClassQueryList(SolicitudTransferenciaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudTransferenciaDetalleByCantidad(java.math.BigDecimal cantidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantidad", cantidad);
		return queryManagerLocal.singleClassQueryList(SolicitudTransferenciaDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SolicitudTransferenciaDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudTransferenciaDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SolicitudTransferenciaDetalleEJB.class, aMap);      
    }

/////////////////
}
