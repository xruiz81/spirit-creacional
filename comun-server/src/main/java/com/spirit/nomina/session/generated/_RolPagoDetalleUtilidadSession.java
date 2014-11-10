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
public abstract class _RolPagoDetalleUtilidadSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_RolPagoDetalleUtilidadSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.RolPagoDetalleUtilidadIf addRolPagoDetalleUtilidad(com.spirit.nomina.entity.RolPagoDetalleUtilidadIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      RolPagoDetalleUtilidadEJB value = new RolPagoDetalleUtilidadEJB();
      try {
      value.setId(model.getId());
      value.setRolpagoId(model.getRolpagoId());
      value.setContratoUtilidadId(model.getContratoUtilidadId());
      value.setContratoId(model.getContratoId());
      value.setCargo(model.getCargo());
      value.setFechaIngreso(model.getFechaIngreso());
      value.setFechaSalida(model.getFechaSalida());
      value.setGenero(model.getGenero());
      value.setDiasLaborados(model.getDiasLaborados());
      value.setUtilidadContrato(model.getUtilidadContrato());
      value.setNumeroCargas(model.getNumeroCargas());
      value.setDiasCargas(model.getDiasCargas());
      value.setUtilidadCargas(model.getUtilidadCargas());
      value.setTotal(model.getTotal());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en rolPagoDetalleUtilidad.", e);
			throw new GenericBusinessException(
					"Error al insertar información en rolPagoDetalleUtilidad.");
      }
     
      return getRolPagoDetalleUtilidad(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveRolPagoDetalleUtilidad(com.spirit.nomina.entity.RolPagoDetalleUtilidadIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      RolPagoDetalleUtilidadEJB data = new RolPagoDetalleUtilidadEJB();
      data.setId(model.getId());
      data.setRolpagoId(model.getRolpagoId());
      data.setContratoUtilidadId(model.getContratoUtilidadId());
      data.setContratoId(model.getContratoId());
      data.setCargo(model.getCargo());
      data.setFechaIngreso(model.getFechaIngreso());
      data.setFechaSalida(model.getFechaSalida());
      data.setGenero(model.getGenero());
      data.setDiasLaborados(model.getDiasLaborados());
      data.setUtilidadContrato(model.getUtilidadContrato());
      data.setNumeroCargas(model.getNumeroCargas());
      data.setDiasCargas(model.getDiasCargas());
      data.setUtilidadCargas(model.getUtilidadCargas());
      data.setTotal(model.getTotal());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en rolPagoDetalleUtilidad.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en rolPagoDetalleUtilidad.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteRolPagoDetalleUtilidad(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      RolPagoDetalleUtilidadEJB data = manager.find(RolPagoDetalleUtilidadEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en rolPagoDetalleUtilidad.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en rolPagoDetalleUtilidad.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.RolPagoDetalleUtilidadIf getRolPagoDetalleUtilidad(java.lang.Long id) {
      return (RolPagoDetalleUtilidadEJB)queryManagerLocal.find(RolPagoDetalleUtilidadEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getRolPagoDetalleUtilidadList() {
	  return queryManagerLocal.singleClassList(RolPagoDetalleUtilidadEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getRolPagoDetalleUtilidadList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(RolPagoDetalleUtilidadEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getRolPagoDetalleUtilidadListSize() {
      Query countQuery = manager.createQuery("select count(*) from RolPagoDetalleUtilidadEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleUtilidadById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleUtilidadByRolpagoId(java.lang.Long rolpagoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("rolpagoId", rolpagoId);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleUtilidadByContratoUtilidadId(java.lang.Long contratoUtilidadId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("contratoUtilidadId", contratoUtilidadId);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleUtilidadByContratoId(java.lang.Long contratoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("contratoId", contratoId);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleUtilidadByCargo(java.lang.String cargo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cargo", cargo);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleUtilidadByFechaIngreso(java.sql.Date fechaIngreso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaIngreso", fechaIngreso);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleUtilidadByFechaSalida(java.sql.Date fechaSalida) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaSalida", fechaSalida);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleUtilidadByGenero(java.lang.String genero) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("genero", genero);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleUtilidadByDiasLaborados(java.lang.Integer diasLaborados) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("diasLaborados", diasLaborados);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleUtilidadByUtilidadContrato(java.math.BigDecimal utilidadContrato) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("utilidadContrato", utilidadContrato);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleUtilidadByNumeroCargas(java.lang.Integer numeroCargas) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numeroCargas", numeroCargas);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleUtilidadByDiasCargas(java.lang.Integer diasCargas) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("diasCargas", diasCargas);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleUtilidadByUtilidadCargas(java.math.BigDecimal utilidadCargas) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("utilidadCargas", utilidadCargas);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleUtilidadByTotal(java.math.BigDecimal total) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("total", total);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleUtilidadEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of RolPagoDetalleUtilidadIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleUtilidadByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleUtilidadEJB.class, aMap);      
    }

/////////////////
}
