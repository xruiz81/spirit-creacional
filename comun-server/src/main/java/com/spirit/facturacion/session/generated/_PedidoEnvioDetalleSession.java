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
public abstract class _PedidoEnvioDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PedidoEnvioDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PedidoEnvioDetalleIf addPedidoEnvioDetalle(com.spirit.facturacion.entity.PedidoEnvioDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PedidoEnvioDetalleEJB value = new PedidoEnvioDetalleEJB();
      try {
      value.setId(model.getId());
      value.setPedidoEnvioId(model.getPedidoEnvioId());
      value.setCodigoBarras(model.getCodigoBarras());
      value.setCantidad(model.getCantidad());
      value.setIncluyeIva(model.getIncluyeIva());
      value.setValor(model.getValor());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en pedidoEnvioDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en pedidoEnvioDetalle.");
      }
     
      return getPedidoEnvioDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePedidoEnvioDetalle(com.spirit.facturacion.entity.PedidoEnvioDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PedidoEnvioDetalleEJB data = new PedidoEnvioDetalleEJB();
      data.setId(model.getId());
      data.setPedidoEnvioId(model.getPedidoEnvioId());
      data.setCodigoBarras(model.getCodigoBarras());
      data.setCantidad(model.getCantidad());
      data.setIncluyeIva(model.getIncluyeIva());
      data.setValor(model.getValor());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en pedidoEnvioDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en pedidoEnvioDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePedidoEnvioDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PedidoEnvioDetalleEJB data = manager.find(PedidoEnvioDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en pedidoEnvioDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en pedidoEnvioDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PedidoEnvioDetalleIf getPedidoEnvioDetalle(java.lang.Long id) {
      return (PedidoEnvioDetalleEJB)queryManagerLocal.find(PedidoEnvioDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPedidoEnvioDetalleList() {
	  return queryManagerLocal.singleClassList(PedidoEnvioDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPedidoEnvioDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PedidoEnvioDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPedidoEnvioDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from PedidoEnvioDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioDetalleByPedidoEnvioId(java.lang.Long pedidoEnvioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("pedidoEnvioId", pedidoEnvioId);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioDetalleByCodigoBarras(java.lang.String codigoBarras) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigoBarras", codigoBarras);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioDetalleByCantidad(java.math.BigDecimal cantidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantidad", cantidad);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioDetalleByIncluyeIva(java.lang.String incluyeIva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("incluyeIva", incluyeIva);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioDetalleByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PedidoEnvioDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PedidoEnvioDetalleEJB.class, aMap);      
    }

/////////////////
}
