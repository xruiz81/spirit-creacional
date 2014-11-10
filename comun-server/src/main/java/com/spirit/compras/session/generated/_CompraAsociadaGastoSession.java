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

import com.spirit.compras.entity.CompraAsociadaGastoEJB;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _CompraAsociadaGastoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CompraAsociadaGastoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.CompraAsociadaGastoIf addCompraAsociadaGasto(com.spirit.compras.entity.CompraAsociadaGastoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CompraAsociadaGastoEJB value = new CompraAsociadaGastoEJB();
      try {
      value.setId(model.getId());
      value.setCompraGastoId(model.getCompraGastoId());
      value.setCompraId(model.getCompraId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en compraAsociadaGasto.", e);
			throw new GenericBusinessException(
					"Error al insertar información en compraAsociadaGasto.");
      }
     
      return getCompraAsociadaGasto(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCompraAsociadaGasto(com.spirit.compras.entity.CompraAsociadaGastoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CompraAsociadaGastoEJB data = new CompraAsociadaGastoEJB();
      data.setId(model.getId());
      data.setCompraGastoId(model.getCompraGastoId());
      data.setCompraId(model.getCompraId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en compraAsociadaGasto.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en compraAsociadaGasto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCompraAsociadaGasto(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CompraAsociadaGastoEJB data = manager.find(CompraAsociadaGastoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en compraAsociadaGasto.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en compraAsociadaGasto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.CompraAsociadaGastoIf getCompraAsociadaGasto(java.lang.Long id) {
      return (CompraAsociadaGastoEJB)queryManagerLocal.find(CompraAsociadaGastoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCompraAsociadaGastoList() {
	  return queryManagerLocal.singleClassList(CompraAsociadaGastoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCompraAsociadaGastoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CompraAsociadaGastoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCompraAsociadaGastoListSize() {
      Query countQuery = manager.createQuery("select count(*) from CompraAsociadaGastoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraAsociadaGastoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CompraAsociadaGastoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraAsociadaGastoByCompraGastoId(java.lang.Long compraGastoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("compraGastoId", compraGastoId);
		return queryManagerLocal.singleClassQueryList(CompraAsociadaGastoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraAsociadaGastoByCompraId(java.lang.Long compraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("compraId", compraId);
		return queryManagerLocal.singleClassQueryList(CompraAsociadaGastoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CompraAsociadaGastoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraAsociadaGastoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CompraAsociadaGastoEJB.class, aMap);      
    }

/////////////////
}
