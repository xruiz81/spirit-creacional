package com.spirit.facturacion.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.facturacion.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _PedidoDetallePersonalizacionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PedidoDetallePersonalizacionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PedidoDetallePersonalizacionIf addPedidoDetallePersonalizacion(com.spirit.facturacion.entity.PedidoDetallePersonalizacionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PedidoDetallePersonalizacionEJB value = new PedidoDetallePersonalizacionEJB();
      try {
      value.setId(model.getId());
      value.setPedidoDetalleId(model.getPedidoDetalleId());
      value.setTipoPersonalizacionId(model.getTipoPersonalizacionId());
      value.setImpresionPersonalizacionId(model.getImpresionPersonalizacionId());
      value.setTamanioPersonalizacionId(model.getTamanioPersonalizacionId());
      value.setColorPersonalizacionId(model.getColorPersonalizacionId());
      value.setUbicacionPersonalizacionId(model.getUbicacionPersonalizacionId());
      value.setTipoLetraPersonalizacionId(model.getTipoLetraPersonalizacionId());
      value.setDescripcion(model.getDescripcion());
      value.setLogoDisenioPersonalizacionId(model.getLogoDisenioPersonalizacionId());
      value.setMensaje(model.getMensaje());
      value.setDisenioPersonalizacionId(model.getDisenioPersonalizacionId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en pedidoDetallePersonalizacion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en pedidoDetallePersonalizacion.");
      }
     
      return getPedidoDetallePersonalizacion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePedidoDetallePersonalizacion(com.spirit.facturacion.entity.PedidoDetallePersonalizacionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PedidoDetallePersonalizacionEJB data = new PedidoDetallePersonalizacionEJB();
      data.setId(model.getId());
      data.setPedidoDetalleId(model.getPedidoDetalleId());
      data.setTipoPersonalizacionId(model.getTipoPersonalizacionId());
      data.setImpresionPersonalizacionId(model.getImpresionPersonalizacionId());
      data.setTamanioPersonalizacionId(model.getTamanioPersonalizacionId());
      data.setColorPersonalizacionId(model.getColorPersonalizacionId());
      data.setUbicacionPersonalizacionId(model.getUbicacionPersonalizacionId());
      data.setTipoLetraPersonalizacionId(model.getTipoLetraPersonalizacionId());
      data.setDescripcion(model.getDescripcion());
      data.setLogoDisenioPersonalizacionId(model.getLogoDisenioPersonalizacionId());
      data.setMensaje(model.getMensaje());
      data.setDisenioPersonalizacionId(model.getDisenioPersonalizacionId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en pedidoDetallePersonalizacion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en pedidoDetallePersonalizacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePedidoDetallePersonalizacion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PedidoDetallePersonalizacionEJB data = manager.find(PedidoDetallePersonalizacionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en pedidoDetallePersonalizacion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en pedidoDetallePersonalizacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PedidoDetallePersonalizacionIf getPedidoDetallePersonalizacion(java.lang.Long id) {
      return (PedidoDetallePersonalizacionEJB)queryManagerLocal.find(PedidoDetallePersonalizacionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPedidoDetallePersonalizacionList() {
	  return queryManagerLocal.singleClassList(PedidoDetallePersonalizacionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPedidoDetallePersonalizacionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PedidoDetallePersonalizacionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPedidoDetallePersonalizacionListSize() {
      Query countQuery = manager.createQuery("select count(*) from PedidoDetallePersonalizacionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetallePersonalizacionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PedidoDetallePersonalizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetallePersonalizacionByPedidoDetalleId(java.lang.Long pedidoDetalleId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("pedidoDetalleId", pedidoDetalleId);
		return queryManagerLocal.singleClassQueryList(PedidoDetallePersonalizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetallePersonalizacionByTipoPersonalizacionId(java.lang.Long tipoPersonalizacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoPersonalizacionId", tipoPersonalizacionId);
		return queryManagerLocal.singleClassQueryList(PedidoDetallePersonalizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetallePersonalizacionByImpresionPersonalizacionId(java.lang.Long impresionPersonalizacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("impresionPersonalizacionId", impresionPersonalizacionId);
		return queryManagerLocal.singleClassQueryList(PedidoDetallePersonalizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetallePersonalizacionByTamanioPersonalizacionId(java.lang.Long tamanioPersonalizacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tamanioPersonalizacionId", tamanioPersonalizacionId);
		return queryManagerLocal.singleClassQueryList(PedidoDetallePersonalizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetallePersonalizacionByColorPersonalizacionId(java.lang.Long colorPersonalizacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("colorPersonalizacionId", colorPersonalizacionId);
		return queryManagerLocal.singleClassQueryList(PedidoDetallePersonalizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetallePersonalizacionByUbicacionPersonalizacionId(java.lang.Long ubicacionPersonalizacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ubicacionPersonalizacionId", ubicacionPersonalizacionId);
		return queryManagerLocal.singleClassQueryList(PedidoDetallePersonalizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetallePersonalizacionByTipoLetraPersonalizacionId(java.lang.Long tipoLetraPersonalizacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoLetraPersonalizacionId", tipoLetraPersonalizacionId);
		return queryManagerLocal.singleClassQueryList(PedidoDetallePersonalizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetallePersonalizacionByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(PedidoDetallePersonalizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetallePersonalizacionByLogoDisenioPersonalizacionId(java.lang.Long logoDisenioPersonalizacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("logoDisenioPersonalizacionId", logoDisenioPersonalizacionId);
		return queryManagerLocal.singleClassQueryList(PedidoDetallePersonalizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetallePersonalizacionByMensaje(java.lang.String mensaje) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("mensaje", mensaje);
		return queryManagerLocal.singleClassQueryList(PedidoDetallePersonalizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetallePersonalizacionByDisenioPersonalizacionId(java.lang.Long disenioPersonalizacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("disenioPersonalizacionId", disenioPersonalizacionId);
		return queryManagerLocal.singleClassQueryList(PedidoDetallePersonalizacionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PedidoDetallePersonalizacionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoDetallePersonalizacionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PedidoDetallePersonalizacionEJB.class, aMap);      
    }

/////////////////
}
