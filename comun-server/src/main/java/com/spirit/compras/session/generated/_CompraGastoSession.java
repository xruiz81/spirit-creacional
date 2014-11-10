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

import com.spirit.compras.entity.CompraGastoEJB;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _CompraGastoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CompraGastoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.CompraGastoIf addCompraGasto(com.spirit.compras.entity.CompraGastoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CompraGastoEJB value = new CompraGastoEJB();
      try {
      value.setId(model.getId());
      value.setGastoId(model.getGastoId());
      value.setCompraId(model.getCompraId());
      value.setValor(model.getValor());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en compraGasto.", e);
			throw new GenericBusinessException(
					"Error al insertar información en compraGasto.");
      }
     
      return getCompraGasto(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCompraGasto(com.spirit.compras.entity.CompraGastoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CompraGastoEJB data = new CompraGastoEJB();
      data.setId(model.getId());
      data.setGastoId(model.getGastoId());
      data.setCompraId(model.getCompraId());
      data.setValor(model.getValor());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en compraGasto.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en compraGasto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCompraGasto(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CompraGastoEJB data = manager.find(CompraGastoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en compraGasto.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en compraGasto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.CompraGastoIf getCompraGasto(java.lang.Long id) {
      return (CompraGastoEJB)queryManagerLocal.find(CompraGastoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCompraGastoList() {
	  return queryManagerLocal.singleClassList(CompraGastoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCompraGastoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CompraGastoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCompraGastoListSize() {
      Query countQuery = manager.createQuery("select count(*) from CompraGastoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraGastoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CompraGastoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraGastoByGastoId(java.lang.Long gastoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("gastoId", gastoId);
		return queryManagerLocal.singleClassQueryList(CompraGastoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraGastoByCompraId(java.lang.Long compraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("compraId", compraId);
		return queryManagerLocal.singleClassQueryList(CompraGastoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraGastoByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(CompraGastoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CompraGastoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraGastoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CompraGastoEJB.class, aMap);      
    }

/////////////////
}
