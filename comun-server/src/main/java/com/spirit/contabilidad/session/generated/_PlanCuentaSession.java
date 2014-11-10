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
public abstract class _PlanCuentaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PlanCuentaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.PlanCuentaIf addPlanCuenta(com.spirit.contabilidad.entity.PlanCuentaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PlanCuentaEJB value = new PlanCuentaEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      value.setFecha(model.getFecha());
      value.setMonedaId(model.getMonedaId());
      value.setEstado(model.getEstado());
      value.setMascara(model.getMascara());
      value.setPredeterminado(model.getPredeterminado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en planCuenta.", e);
			throw new GenericBusinessException(
					"Error al insertar información en planCuenta.");
      }
     
      return getPlanCuenta(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePlanCuenta(com.spirit.contabilidad.entity.PlanCuentaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PlanCuentaEJB data = new PlanCuentaEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
      data.setFecha(model.getFecha());
      data.setMonedaId(model.getMonedaId());
      data.setEstado(model.getEstado());
      data.setMascara(model.getMascara());
      data.setPredeterminado(model.getPredeterminado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en planCuenta.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en planCuenta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePlanCuenta(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PlanCuentaEJB data = manager.find(PlanCuentaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en planCuenta.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en planCuenta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.PlanCuentaIf getPlanCuenta(java.lang.Long id) {
      return (PlanCuentaEJB)queryManagerLocal.find(PlanCuentaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPlanCuentaList() {
	  return queryManagerLocal.singleClassList(PlanCuentaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPlanCuentaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PlanCuentaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPlanCuentaListSize() {
      Query countQuery = manager.createQuery("select count(*) from PlanCuentaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanCuentaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PlanCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanCuentaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PlanCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanCuentaByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(PlanCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanCuentaByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(PlanCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanCuentaByFecha(java.sql.Date fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(PlanCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanCuentaByMonedaId(java.lang.Long monedaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("monedaId", monedaId);
		return queryManagerLocal.singleClassQueryList(PlanCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanCuentaByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(PlanCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanCuentaByMascara(java.lang.String mascara) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("mascara", mascara);
		return queryManagerLocal.singleClassQueryList(PlanCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanCuentaByPredeterminado(java.lang.String predeterminado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("predeterminado", predeterminado);
		return queryManagerLocal.singleClassQueryList(PlanCuentaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PlanCuentaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanCuentaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PlanCuentaEJB.class, aMap);      
    }

/////////////////
}
