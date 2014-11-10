package com.spirit.compras.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.compras.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _OrdenAsociadaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_OrdenAsociadaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.OrdenAsociadaIf addOrdenAsociada(com.spirit.compras.entity.OrdenAsociadaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      OrdenAsociadaEJB value = new OrdenAsociadaEJB();
      try {
      value.setId(model.getId());
      value.setCompraId(model.getCompraId());
      value.setTipoOrden(model.getTipoOrden());
      value.setOrdenId(model.getOrdenId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en ordenAsociada.", e);
			throw new GenericBusinessException(
					"Error al insertar información en ordenAsociada.");
      }
     
      return getOrdenAsociada(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveOrdenAsociada(com.spirit.compras.entity.OrdenAsociadaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      OrdenAsociadaEJB data = new OrdenAsociadaEJB();
      data.setId(model.getId());
      data.setCompraId(model.getCompraId());
      data.setTipoOrden(model.getTipoOrden());
      data.setOrdenId(model.getOrdenId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en ordenAsociada.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en ordenAsociada.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteOrdenAsociada(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      OrdenAsociadaEJB data = manager.find(OrdenAsociadaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en ordenAsociada.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en ordenAsociada.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.OrdenAsociadaIf getOrdenAsociada(java.lang.Long id) {
      return (OrdenAsociadaEJB)queryManagerLocal.find(OrdenAsociadaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenAsociadaList() {
	  return queryManagerLocal.singleClassList(OrdenAsociadaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenAsociadaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(OrdenAsociadaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getOrdenAsociadaListSize() {
      Query countQuery = manager.createQuery("select count(*) from OrdenAsociadaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenAsociadaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(OrdenAsociadaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenAsociadaByCompraId(java.lang.Long compraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("compraId", compraId);
		return queryManagerLocal.singleClassQueryList(OrdenAsociadaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenAsociadaByTipoOrden(java.lang.String tipoOrden) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoOrden", tipoOrden);
		return queryManagerLocal.singleClassQueryList(OrdenAsociadaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenAsociadaByOrdenId(java.lang.Long ordenId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordenId", ordenId);
		return queryManagerLocal.singleClassQueryList(OrdenAsociadaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of OrdenAsociadaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenAsociadaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(OrdenAsociadaEJB.class, aMap);      
    }

/////////////////
}
