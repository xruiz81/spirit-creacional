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
public abstract class _PlanMedioFormaPagoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PlanMedioFormaPagoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PlanMedioFormaPagoIf addPlanMedioFormaPago(com.spirit.medios.entity.PlanMedioFormaPagoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PlanMedioFormaPagoEJB value = new PlanMedioFormaPagoEJB();
      try {
      value.setId(model.getId());
      value.setPedidoId(model.getPedidoId());
      value.setPlanMedioId(model.getPlanMedioId());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      value.setTipoFormaPago(model.getTipoFormaPago());
      value.setFormaPagoId(model.getFormaPagoId());
      value.setEstado(model.getEstado());
      value.setFormaPagoCampanaProductoVersionId(model.getFormaPagoCampanaProductoVersionId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en planMedioFormaPago.", e);
			throw new GenericBusinessException(
					"Error al insertar información en planMedioFormaPago.");
      }
     
      return getPlanMedioFormaPago(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePlanMedioFormaPago(com.spirit.medios.entity.PlanMedioFormaPagoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PlanMedioFormaPagoEJB data = new PlanMedioFormaPagoEJB();
      data.setId(model.getId());
      data.setPedidoId(model.getPedidoId());
      data.setPlanMedioId(model.getPlanMedioId());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
      data.setTipoFormaPago(model.getTipoFormaPago());
      data.setFormaPagoId(model.getFormaPagoId());
      data.setEstado(model.getEstado());
      data.setFormaPagoCampanaProductoVersionId(model.getFormaPagoCampanaProductoVersionId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en planMedioFormaPago.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en planMedioFormaPago.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePlanMedioFormaPago(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PlanMedioFormaPagoEJB data = manager.find(PlanMedioFormaPagoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en planMedioFormaPago.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en planMedioFormaPago.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PlanMedioFormaPagoIf getPlanMedioFormaPago(java.lang.Long id) {
      return (PlanMedioFormaPagoEJB)queryManagerLocal.find(PlanMedioFormaPagoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPlanMedioFormaPagoList() {
	  return queryManagerLocal.singleClassList(PlanMedioFormaPagoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPlanMedioFormaPagoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PlanMedioFormaPagoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPlanMedioFormaPagoListSize() {
      Query countQuery = manager.createQuery("select count(*) from PlanMedioFormaPagoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFormaPagoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PlanMedioFormaPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFormaPagoByPedidoId(java.lang.Long pedidoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("pedidoId", pedidoId);
		return queryManagerLocal.singleClassQueryList(PlanMedioFormaPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFormaPagoByPlanMedioId(java.lang.Long planMedioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("planMedioId", planMedioId);
		return queryManagerLocal.singleClassQueryList(PlanMedioFormaPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFormaPagoByFechaInicio(java.sql.Timestamp fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(PlanMedioFormaPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFormaPagoByFechaFin(java.sql.Timestamp fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(PlanMedioFormaPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFormaPagoByTipoFormaPago(java.lang.String tipoFormaPago) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoFormaPago", tipoFormaPago);
		return queryManagerLocal.singleClassQueryList(PlanMedioFormaPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFormaPagoByFormaPagoId(java.lang.Long formaPagoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("formaPagoId", formaPagoId);
		return queryManagerLocal.singleClassQueryList(PlanMedioFormaPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFormaPagoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(PlanMedioFormaPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFormaPagoByFormaPagoCampanaProductoVersionId(java.lang.Long formaPagoCampanaProductoVersionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("formaPagoCampanaProductoVersionId", formaPagoCampanaProductoVersionId);
		return queryManagerLocal.singleClassQueryList(PlanMedioFormaPagoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PlanMedioFormaPagoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFormaPagoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PlanMedioFormaPagoEJB.class, aMap);      
    }

/////////////////
}
