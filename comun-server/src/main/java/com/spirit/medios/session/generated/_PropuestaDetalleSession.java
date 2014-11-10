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
public abstract class _PropuestaDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PropuestaDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PropuestaDetalleIf addPropuestaDetalle(com.spirit.medios.entity.PropuestaDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PropuestaDetalleEJB value = new PropuestaDetalleEJB();
      try {
      value.setId(model.getId());
      value.setPropuestaId(model.getPropuestaId());
      value.setPresupuestoId(model.getPresupuestoId());
      value.setPlanmedioId(model.getPlanmedioId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en propuestaDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en propuestaDetalle.");
      }
     
      return getPropuestaDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePropuestaDetalle(com.spirit.medios.entity.PropuestaDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PropuestaDetalleEJB data = new PropuestaDetalleEJB();
      data.setId(model.getId());
      data.setPropuestaId(model.getPropuestaId());
      data.setPresupuestoId(model.getPresupuestoId());
      data.setPlanmedioId(model.getPlanmedioId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en propuestaDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en propuestaDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePropuestaDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PropuestaDetalleEJB data = manager.find(PropuestaDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en propuestaDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en propuestaDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PropuestaDetalleIf getPropuestaDetalle(java.lang.Long id) {
      return (PropuestaDetalleEJB)queryManagerLocal.find(PropuestaDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPropuestaDetalleList() {
	  return queryManagerLocal.singleClassList(PropuestaDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPropuestaDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PropuestaDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPropuestaDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from PropuestaDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPropuestaDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PropuestaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPropuestaDetalleByPropuestaId(java.lang.Long propuestaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("propuestaId", propuestaId);
		return queryManagerLocal.singleClassQueryList(PropuestaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPropuestaDetalleByPresupuestoId(java.lang.Long presupuestoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("presupuestoId", presupuestoId);
		return queryManagerLocal.singleClassQueryList(PropuestaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPropuestaDetalleByPlanmedioId(java.lang.Long planmedioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("planmedioId", planmedioId);
		return queryManagerLocal.singleClassQueryList(PropuestaDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PropuestaDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPropuestaDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PropuestaDetalleEJB.class, aMap);      
    }

/////////////////
}
