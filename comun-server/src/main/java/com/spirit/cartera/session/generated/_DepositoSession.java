package com.spirit.cartera.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.cartera.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _DepositoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_DepositoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.DepositoIf addDeposito(com.spirit.cartera.entity.DepositoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      DepositoEJB value = new DepositoEJB();
      try {
      value.setId(model.getId());
      value.setOficinaId(model.getOficinaId());
      value.setCuentabancariaId(model.getCuentabancariaId());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setFechaDeposito(model.getFechaDeposito());
      value.setValor(model.getValor());
      value.setEstado(model.getEstado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en deposito.", e);
			throw new GenericBusinessException(
					"Error al insertar información en deposito.");
      }
     
      return getDeposito(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveDeposito(com.spirit.cartera.entity.DepositoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      DepositoEJB data = new DepositoEJB();
      data.setId(model.getId());
      data.setOficinaId(model.getOficinaId());
      data.setCuentabancariaId(model.getCuentabancariaId());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setFechaDeposito(model.getFechaDeposito());
      data.setValor(model.getValor());
      data.setEstado(model.getEstado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en deposito.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en deposito.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteDeposito(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      DepositoEJB data = manager.find(DepositoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en deposito.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en deposito.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.DepositoIf getDeposito(java.lang.Long id) {
      return (DepositoEJB)queryManagerLocal.find(DepositoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getDepositoList() {
	  return queryManagerLocal.singleClassList(DepositoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getDepositoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(DepositoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getDepositoListSize() {
      Query countQuery = manager.createQuery("select count(*) from DepositoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDepositoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(DepositoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDepositoByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(DepositoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDepositoByCuentabancariaId(java.lang.Long cuentabancariaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentabancariaId", cuentabancariaId);
		return queryManagerLocal.singleClassQueryList(DepositoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDepositoByFechaCreacion(java.sql.Date fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(DepositoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDepositoByFechaDeposito(java.sql.Date fechaDeposito) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaDeposito", fechaDeposito);
		return queryManagerLocal.singleClassQueryList(DepositoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDepositoByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(DepositoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDepositoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(DepositoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of DepositoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDepositoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(DepositoEJB.class, aMap);      
    }

/////////////////
}
