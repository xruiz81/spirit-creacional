package com.spirit.nomina.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.nomina.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _ContratoGastoDeducibleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ContratoGastoDeducibleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.ContratoGastoDeducibleIf addContratoGastoDeducible(com.spirit.nomina.entity.ContratoGastoDeducibleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ContratoGastoDeducibleEJB value = new ContratoGastoDeducibleEJB();
      try {
      value.setId(model.getId());
      value.setContratoId(model.getContratoId());
      value.setGastoDeducibleId(model.getGastoDeducibleId());
      value.setValor(model.getValor());
      value.setFecha(model.getFecha());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en contratoGastoDeducible.", e);
			throw new GenericBusinessException(
					"Error al insertar información en contratoGastoDeducible.");
      }
     
      return getContratoGastoDeducible(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveContratoGastoDeducible(com.spirit.nomina.entity.ContratoGastoDeducibleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ContratoGastoDeducibleEJB data = new ContratoGastoDeducibleEJB();
      data.setId(model.getId());
      data.setContratoId(model.getContratoId());
      data.setGastoDeducibleId(model.getGastoDeducibleId());
      data.setValor(model.getValor());
      data.setFecha(model.getFecha());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en contratoGastoDeducible.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en contratoGastoDeducible.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteContratoGastoDeducible(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ContratoGastoDeducibleEJB data = manager.find(ContratoGastoDeducibleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en contratoGastoDeducible.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en contratoGastoDeducible.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.ContratoGastoDeducibleIf getContratoGastoDeducible(java.lang.Long id) {
      return (ContratoGastoDeducibleEJB)queryManagerLocal.find(ContratoGastoDeducibleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getContratoGastoDeducibleList() {
	  return queryManagerLocal.singleClassList(ContratoGastoDeducibleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getContratoGastoDeducibleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ContratoGastoDeducibleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getContratoGastoDeducibleListSize() {
      Query countQuery = manager.createQuery("select count(*) from ContratoGastoDeducibleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoGastoDeducibleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ContratoGastoDeducibleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoGastoDeducibleByContratoId(java.lang.Long contratoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("contratoId", contratoId);
		return queryManagerLocal.singleClassQueryList(ContratoGastoDeducibleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoGastoDeducibleByGastoDeducibleId(java.lang.Long gastoDeducibleId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("gastoDeducibleId", gastoDeducibleId);
		return queryManagerLocal.singleClassQueryList(ContratoGastoDeducibleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoGastoDeducibleByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(ContratoGastoDeducibleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoGastoDeducibleByFecha(java.sql.Date fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(ContratoGastoDeducibleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ContratoGastoDeducibleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoGastoDeducibleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ContratoGastoDeducibleEJB.class, aMap);      
    }

/////////////////
}
