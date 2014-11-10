package com.spirit.contabilidad.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.contabilidad.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _ChequeTransaccionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ChequeTransaccionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.ChequeTransaccionIf addChequeTransaccion(com.spirit.contabilidad.entity.ChequeTransaccionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ChequeTransaccionEJB value = new ChequeTransaccionEJB();
      try {
      value.setId(model.getId());
      value.setChequeEmitidoId(model.getChequeEmitidoId());
      value.setTransaccionId(model.getTransaccionId());
      value.setOrigen(model.getOrigen());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en chequeTransaccion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en chequeTransaccion.");
      }
     
      return getChequeTransaccion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveChequeTransaccion(com.spirit.contabilidad.entity.ChequeTransaccionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ChequeTransaccionEJB data = new ChequeTransaccionEJB();
      data.setId(model.getId());
      data.setChequeEmitidoId(model.getChequeEmitidoId());
      data.setTransaccionId(model.getTransaccionId());
      data.setOrigen(model.getOrigen());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en chequeTransaccion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en chequeTransaccion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteChequeTransaccion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ChequeTransaccionEJB data = manager.find(ChequeTransaccionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en chequeTransaccion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en chequeTransaccion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.ChequeTransaccionIf getChequeTransaccion(java.lang.Long id) {
      return (ChequeTransaccionEJB)queryManagerLocal.find(ChequeTransaccionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getChequeTransaccionList() {
	  return queryManagerLocal.singleClassList(ChequeTransaccionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getChequeTransaccionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ChequeTransaccionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getChequeTransaccionListSize() {
      Query countQuery = manager.createQuery("select count(*) from ChequeTransaccionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeTransaccionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ChequeTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeTransaccionByChequeEmitidoId(java.lang.Long chequeEmitidoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("chequeEmitidoId", chequeEmitidoId);
		return queryManagerLocal.singleClassQueryList(ChequeTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeTransaccionByTransaccionId(java.lang.Long transaccionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transaccionId", transaccionId);
		return queryManagerLocal.singleClassQueryList(ChequeTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeTransaccionByOrigen(java.lang.String origen) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("origen", origen);
		return queryManagerLocal.singleClassQueryList(ChequeTransaccionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ChequeTransaccionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeTransaccionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ChequeTransaccionEJB.class, aMap);      
    }

/////////////////
}
