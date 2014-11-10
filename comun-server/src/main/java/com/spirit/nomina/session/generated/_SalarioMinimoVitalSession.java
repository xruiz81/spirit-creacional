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
public abstract class _SalarioMinimoVitalSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SalarioMinimoVitalSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.SalarioMinimoVitalIf addSalarioMinimoVital(com.spirit.nomina.entity.SalarioMinimoVitalIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SalarioMinimoVitalEJB value = new SalarioMinimoVitalEJB();
      try {
      value.setId(model.getId());
      value.setValor(model.getValor());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en salarioMinimoVital.", e);
			throw new GenericBusinessException(
					"Error al insertar información en salarioMinimoVital.");
      }
     
      return getSalarioMinimoVital(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSalarioMinimoVital(com.spirit.nomina.entity.SalarioMinimoVitalIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SalarioMinimoVitalEJB data = new SalarioMinimoVitalEJB();
      data.setId(model.getId());
      data.setValor(model.getValor());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en salarioMinimoVital.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en salarioMinimoVital.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSalarioMinimoVital(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SalarioMinimoVitalEJB data = manager.find(SalarioMinimoVitalEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en salarioMinimoVital.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en salarioMinimoVital.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.SalarioMinimoVitalIf getSalarioMinimoVital(java.lang.Long id) {
      return (SalarioMinimoVitalEJB)queryManagerLocal.find(SalarioMinimoVitalEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSalarioMinimoVitalList() {
	  return queryManagerLocal.singleClassList(SalarioMinimoVitalEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSalarioMinimoVitalList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SalarioMinimoVitalEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSalarioMinimoVitalListSize() {
      Query countQuery = manager.createQuery("select count(*) from SalarioMinimoVitalEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSalarioMinimoVitalById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SalarioMinimoVitalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSalarioMinimoVitalByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(SalarioMinimoVitalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSalarioMinimoVitalByFechaInicio(java.sql.Date fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(SalarioMinimoVitalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSalarioMinimoVitalByFechaFin(java.sql.Date fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(SalarioMinimoVitalEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SalarioMinimoVitalIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSalarioMinimoVitalByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SalarioMinimoVitalEJB.class, aMap);      
    }

/////////////////
}
