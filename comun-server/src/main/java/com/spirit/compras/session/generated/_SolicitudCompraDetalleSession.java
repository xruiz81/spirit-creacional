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

import com.spirit.compras.entity.SolicitudCompraDetalleEJB;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _SolicitudCompraDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SolicitudCompraDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.SolicitudCompraDetalleIf addSolicitudCompraDetalle(com.spirit.compras.entity.SolicitudCompraDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SolicitudCompraDetalleEJB value = new SolicitudCompraDetalleEJB();
      try {
      value.setId(model.getId());
      value.setSolicitudcompraId(model.getSolicitudcompraId());
      value.setDocumentoId(model.getDocumentoId());
      value.setProductoId(model.getProductoId());
      value.setCantidad(model.getCantidad());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en solicitudCompraDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en solicitudCompraDetalle.");
      }
     
      return getSolicitudCompraDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSolicitudCompraDetalle(com.spirit.compras.entity.SolicitudCompraDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SolicitudCompraDetalleEJB data = new SolicitudCompraDetalleEJB();
      data.setId(model.getId());
      data.setSolicitudcompraId(model.getSolicitudcompraId());
      data.setDocumentoId(model.getDocumentoId());
      data.setProductoId(model.getProductoId());
      data.setCantidad(model.getCantidad());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en solicitudCompraDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en solicitudCompraDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSolicitudCompraDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SolicitudCompraDetalleEJB data = manager.find(SolicitudCompraDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en solicitudCompraDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en solicitudCompraDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.SolicitudCompraDetalleIf getSolicitudCompraDetalle(java.lang.Long id) {
      return (SolicitudCompraDetalleEJB)queryManagerLocal.find(SolicitudCompraDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSolicitudCompraDetalleList() {
	  return queryManagerLocal.singleClassList(SolicitudCompraDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSolicitudCompraDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SolicitudCompraDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSolicitudCompraDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from SolicitudCompraDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraDetalleBySolicitudcompraId(java.lang.Long solicitudcompraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("solicitudcompraId", solicitudcompraId);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraDetalleByDocumentoId(java.lang.Long documentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoId", documentoId);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraDetalleByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraDetalleByCantidad(java.math.BigDecimal cantidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantidad", cantidad);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SolicitudCompraDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SolicitudCompraDetalleEJB.class, aMap);      
    }

/////////////////
}
