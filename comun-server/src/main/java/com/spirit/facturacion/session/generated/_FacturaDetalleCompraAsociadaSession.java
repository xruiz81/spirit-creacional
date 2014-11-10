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
public abstract class _FacturaDetalleCompraAsociadaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_FacturaDetalleCompraAsociadaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.FacturaDetalleCompraAsociadaIf addFacturaDetalleCompraAsociada(com.spirit.facturacion.entity.FacturaDetalleCompraAsociadaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      FacturaDetalleCompraAsociadaEJB value = new FacturaDetalleCompraAsociadaEJB();
      try {
      value.setId(model.getId());
      value.setFacturaDetalleId(model.getFacturaDetalleId());
      value.setCompraId(model.getCompraId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en facturaDetalleCompraAsociada.", e);
			throw new GenericBusinessException(
					"Error al insertar información en facturaDetalleCompraAsociada.");
      }
     
      return getFacturaDetalleCompraAsociada(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveFacturaDetalleCompraAsociada(com.spirit.facturacion.entity.FacturaDetalleCompraAsociadaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      FacturaDetalleCompraAsociadaEJB data = new FacturaDetalleCompraAsociadaEJB();
      data.setId(model.getId());
      data.setFacturaDetalleId(model.getFacturaDetalleId());
      data.setCompraId(model.getCompraId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en facturaDetalleCompraAsociada.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en facturaDetalleCompraAsociada.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteFacturaDetalleCompraAsociada(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      FacturaDetalleCompraAsociadaEJB data = manager.find(FacturaDetalleCompraAsociadaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en facturaDetalleCompraAsociada.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en facturaDetalleCompraAsociada.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.FacturaDetalleCompraAsociadaIf getFacturaDetalleCompraAsociada(java.lang.Long id) {
      return (FacturaDetalleCompraAsociadaEJB)queryManagerLocal.find(FacturaDetalleCompraAsociadaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getFacturaDetalleCompraAsociadaList() {
	  return queryManagerLocal.singleClassList(FacturaDetalleCompraAsociadaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getFacturaDetalleCompraAsociadaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(FacturaDetalleCompraAsociadaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getFacturaDetalleCompraAsociadaListSize() {
      Query countQuery = manager.createQuery("select count(*) from FacturaDetalleCompraAsociadaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleCompraAsociadaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleCompraAsociadaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleCompraAsociadaByFacturaDetalleId(java.lang.Long facturaDetalleId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("facturaDetalleId", facturaDetalleId);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleCompraAsociadaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleCompraAsociadaByCompraId(java.lang.Long compraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("compraId", compraId);
		return queryManagerLocal.singleClassQueryList(FacturaDetalleCompraAsociadaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of FacturaDetalleCompraAsociadaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaDetalleCompraAsociadaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(FacturaDetalleCompraAsociadaEJB.class, aMap);      
    }

/////////////////
}
