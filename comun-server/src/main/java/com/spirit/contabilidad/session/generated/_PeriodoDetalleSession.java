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
public abstract class _PeriodoDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PeriodoDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.PeriodoDetalleIf addPeriodoDetalle(com.spirit.contabilidad.entity.PeriodoDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PeriodoDetalleEJB value = new PeriodoDetalleEJB();
      try {
      value.setId(model.getId());
      value.setStatus(model.getStatus());
      value.setPeriodoId(model.getPeriodoId());
      value.setMes(model.getMes());
      value.setAnio(model.getAnio());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en periodoDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en periodoDetalle.");
      }
     
      return getPeriodoDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePeriodoDetalle(com.spirit.contabilidad.entity.PeriodoDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PeriodoDetalleEJB data = new PeriodoDetalleEJB();
      data.setId(model.getId());
      data.setStatus(model.getStatus());
      data.setPeriodoId(model.getPeriodoId());
      data.setMes(model.getMes());
      data.setAnio(model.getAnio());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en periodoDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en periodoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePeriodoDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PeriodoDetalleEJB data = manager.find(PeriodoDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en periodoDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en periodoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.PeriodoDetalleIf getPeriodoDetalle(java.lang.Long id) {
      return (PeriodoDetalleEJB)queryManagerLocal.find(PeriodoDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPeriodoDetalleList() {
	  return queryManagerLocal.singleClassList(PeriodoDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPeriodoDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PeriodoDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPeriodoDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from PeriodoDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPeriodoDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PeriodoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPeriodoDetalleByStatus(java.lang.String status) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("status", status);
		return queryManagerLocal.singleClassQueryList(PeriodoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPeriodoDetalleByPeriodoId(java.lang.Long periodoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("periodoId", periodoId);
		return queryManagerLocal.singleClassQueryList(PeriodoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPeriodoDetalleByMes(java.lang.String mes) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("mes", mes);
		return queryManagerLocal.singleClassQueryList(PeriodoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPeriodoDetalleByAnio(java.lang.String anio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("anio", anio);
		return queryManagerLocal.singleClassQueryList(PeriodoDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PeriodoDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPeriodoDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PeriodoDetalleEJB.class, aMap);      
    }

/////////////////
}
