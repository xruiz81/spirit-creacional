package com.spirit.medios.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.medios.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _PlanMedioMesSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PlanMedioMesSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PlanMedioMesIf addPlanMedioMes(com.spirit.medios.entity.PlanMedioMesIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PlanMedioMesEJB value = new PlanMedioMesEJB();
      try {
      value.setId(model.getId());
      value.setPlanMedioId(model.getPlanMedioId());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      value.setValorSubtotal(model.getValorSubtotal());
      value.setValorDescuento(model.getValorDescuento());
      value.setTipo(model.getTipo());
      value.setValorIva(model.getValorIva());
      value.setValorTotal(model.getValorTotal());
      value.setValorDescuentoVenta(model.getValorDescuentoVenta());
      value.setValorComisionAgencia(model.getValorComisionAgencia());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en planMedioMes.", e);
			throw new GenericBusinessException(
					"Error al insertar información en planMedioMes.");
      }
     
      return getPlanMedioMes(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePlanMedioMes(com.spirit.medios.entity.PlanMedioMesIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PlanMedioMesEJB data = new PlanMedioMesEJB();
      data.setId(model.getId());
      data.setPlanMedioId(model.getPlanMedioId());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
      data.setValorSubtotal(model.getValorSubtotal());
      data.setValorDescuento(model.getValorDescuento());
      data.setTipo(model.getTipo());
      data.setValorIva(model.getValorIva());
      data.setValorTotal(model.getValorTotal());
      data.setValorDescuentoVenta(model.getValorDescuentoVenta());
      data.setValorComisionAgencia(model.getValorComisionAgencia());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en planMedioMes.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en planMedioMes.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePlanMedioMes(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PlanMedioMesEJB data = manager.find(PlanMedioMesEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en planMedioMes.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en planMedioMes.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PlanMedioMesIf getPlanMedioMes(java.lang.Long id) {
      return (PlanMedioMesEJB)queryManagerLocal.find(PlanMedioMesEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPlanMedioMesList() {
	  return queryManagerLocal.singleClassList(PlanMedioMesEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPlanMedioMesList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PlanMedioMesEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPlanMedioMesListSize() {
      Query countQuery = manager.createQuery("select count(*) from PlanMedioMesEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PlanMedioMesEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByPlanMedioId(java.lang.Long planMedioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("planMedioId", planMedioId);
		return queryManagerLocal.singleClassQueryList(PlanMedioMesEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByFechaInicio(java.sql.Timestamp fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(PlanMedioMesEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByFechaFin(java.sql.Timestamp fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(PlanMedioMesEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByValorSubtotal(java.math.BigDecimal valorSubtotal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorSubtotal", valorSubtotal);
		return queryManagerLocal.singleClassQueryList(PlanMedioMesEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByValorDescuento(java.math.BigDecimal valorDescuento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorDescuento", valorDescuento);
		return queryManagerLocal.singleClassQueryList(PlanMedioMesEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByTipo(java.lang.String tipo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipo", tipo);
		return queryManagerLocal.singleClassQueryList(PlanMedioMesEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByValorIva(java.math.BigDecimal valorIva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorIva", valorIva);
		return queryManagerLocal.singleClassQueryList(PlanMedioMesEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByValorTotal(java.math.BigDecimal valorTotal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorTotal", valorTotal);
		return queryManagerLocal.singleClassQueryList(PlanMedioMesEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorDescuentoVenta", valorDescuentoVenta);
		return queryManagerLocal.singleClassQueryList(PlanMedioMesEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByValorComisionAgencia(java.math.BigDecimal valorComisionAgencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorComisionAgencia", valorComisionAgencia);
		return queryManagerLocal.singleClassQueryList(PlanMedioMesEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PlanMedioMesIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PlanMedioMesEJB.class, aMap);      
    }

/////////////////
}
