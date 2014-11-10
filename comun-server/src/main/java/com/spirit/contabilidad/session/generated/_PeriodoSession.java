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
public abstract class _PeriodoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PeriodoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.PeriodoIf addPeriodo(com.spirit.contabilidad.entity.PeriodoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PeriodoEJB value = new PeriodoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setEmpresaId(model.getEmpresaId());
      value.setFechaini(model.getFechaini());
      value.setFechafin(model.getFechafin());
      value.setStatus(model.getStatus());
      value.setOrden(model.getOrden());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en periodo.", e);
			throw new GenericBusinessException(
					"Error al insertar información en periodo.");
      }
     
      return getPeriodo(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePeriodo(com.spirit.contabilidad.entity.PeriodoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PeriodoEJB data = new PeriodoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setEmpresaId(model.getEmpresaId());
      data.setFechaini(model.getFechaini());
      data.setFechafin(model.getFechafin());
      data.setStatus(model.getStatus());
      data.setOrden(model.getOrden());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en periodo.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en periodo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePeriodo(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PeriodoEJB data = manager.find(PeriodoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en periodo.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en periodo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.PeriodoIf getPeriodo(java.lang.Long id) {
      return (PeriodoEJB)queryManagerLocal.find(PeriodoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPeriodoList() {
	  return queryManagerLocal.singleClassList(PeriodoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPeriodoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PeriodoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPeriodoListSize() {
      Query countQuery = manager.createQuery("select count(*) from PeriodoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPeriodoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PeriodoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPeriodoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PeriodoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPeriodoByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(PeriodoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPeriodoByFechaini(java.sql.Date fechaini) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaini", fechaini);
		return queryManagerLocal.singleClassQueryList(PeriodoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPeriodoByFechafin(java.sql.Date fechafin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechafin", fechafin);
		return queryManagerLocal.singleClassQueryList(PeriodoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPeriodoByStatus(java.lang.String status) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("status", status);
		return queryManagerLocal.singleClassQueryList(PeriodoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPeriodoByOrden(java.lang.Long orden) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("orden", orden);
		return queryManagerLocal.singleClassQueryList(PeriodoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PeriodoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPeriodoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PeriodoEJB.class, aMap);      
    }

/////////////////
}
