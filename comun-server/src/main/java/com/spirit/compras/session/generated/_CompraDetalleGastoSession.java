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

import com.spirit.compras.entity.CompraDetalleGastoEJB;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _CompraDetalleGastoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CompraDetalleGastoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.CompraDetalleGastoIf addCompraDetalleGasto(com.spirit.compras.entity.CompraDetalleGastoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CompraDetalleGastoEJB value = new CompraDetalleGastoEJB();
      try {
      value.setId(model.getId());
      value.setCompraGastoId(model.getCompraGastoId());
      value.setCompraDetalleId(model.getCompraDetalleId());
      value.setValor(model.getValor());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en compraDetalleGasto.", e);
			throw new GenericBusinessException(
					"Error al insertar información en compraDetalleGasto.");
      }
     
      return getCompraDetalleGasto(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCompraDetalleGasto(com.spirit.compras.entity.CompraDetalleGastoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CompraDetalleGastoEJB data = new CompraDetalleGastoEJB();
      data.setId(model.getId());
      data.setCompraGastoId(model.getCompraGastoId());
      data.setCompraDetalleId(model.getCompraDetalleId());
      data.setValor(model.getValor());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en compraDetalleGasto.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en compraDetalleGasto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCompraDetalleGasto(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CompraDetalleGastoEJB data = manager.find(CompraDetalleGastoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en compraDetalleGasto.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en compraDetalleGasto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.CompraDetalleGastoIf getCompraDetalleGasto(java.lang.Long id) {
      return (CompraDetalleGastoEJB)queryManagerLocal.find(CompraDetalleGastoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCompraDetalleGastoList() {
	  return queryManagerLocal.singleClassList(CompraDetalleGastoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCompraDetalleGastoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CompraDetalleGastoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCompraDetalleGastoListSize() {
      Query countQuery = manager.createQuery("select count(*) from CompraDetalleGastoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleGastoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CompraDetalleGastoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleGastoByCompraGastoId(java.lang.Long compraGastoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("compraGastoId", compraGastoId);
		return queryManagerLocal.singleClassQueryList(CompraDetalleGastoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleGastoByCompraDetalleId(java.lang.Long compraDetalleId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("compraDetalleId", compraDetalleId);
		return queryManagerLocal.singleClassQueryList(CompraDetalleGastoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleGastoByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(CompraDetalleGastoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CompraDetalleGastoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraDetalleGastoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CompraDetalleGastoEJB.class, aMap);      
    }

/////////////////
}
