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
public abstract class _ContratoUtilidadSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ContratoUtilidadSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.ContratoUtilidadIf addContratoUtilidad(com.spirit.nomina.entity.ContratoUtilidadIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ContratoUtilidadEJB value = new ContratoUtilidadEJB();
      try {
      value.setId(model.getId());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      value.setUtilidad(model.getUtilidad());
      value.setPorcentajeContrato(model.getPorcentajeContrato());
      value.setPorcentajeCarga(model.getPorcentajeCarga());
      value.setCodigo(model.getCodigo());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en contratoUtilidad.", e);
			throw new GenericBusinessException(
					"Error al insertar información en contratoUtilidad.");
      }
     
      return getContratoUtilidad(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveContratoUtilidad(com.spirit.nomina.entity.ContratoUtilidadIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ContratoUtilidadEJB data = new ContratoUtilidadEJB();
      data.setId(model.getId());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
      data.setUtilidad(model.getUtilidad());
      data.setPorcentajeContrato(model.getPorcentajeContrato());
      data.setPorcentajeCarga(model.getPorcentajeCarga());
      data.setCodigo(model.getCodigo());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en contratoUtilidad.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en contratoUtilidad.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteContratoUtilidad(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ContratoUtilidadEJB data = manager.find(ContratoUtilidadEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en contratoUtilidad.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en contratoUtilidad.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.ContratoUtilidadIf getContratoUtilidad(java.lang.Long id) {
      return (ContratoUtilidadEJB)queryManagerLocal.find(ContratoUtilidadEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getContratoUtilidadList() {
	  return queryManagerLocal.singleClassList(ContratoUtilidadEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getContratoUtilidadList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ContratoUtilidadEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getContratoUtilidadListSize() {
      Query countQuery = manager.createQuery("select count(*) from ContratoUtilidadEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoUtilidadById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ContratoUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoUtilidadByFechaInicio(java.sql.Date fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(ContratoUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoUtilidadByFechaFin(java.sql.Date fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(ContratoUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoUtilidadByUtilidad(java.math.BigDecimal utilidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("utilidad", utilidad);
		return queryManagerLocal.singleClassQueryList(ContratoUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoUtilidadByPorcentajeContrato(java.math.BigDecimal porcentajeContrato) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeContrato", porcentajeContrato);
		return queryManagerLocal.singleClassQueryList(ContratoUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoUtilidadByPorcentajeCarga(java.math.BigDecimal porcentajeCarga) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeCarga", porcentajeCarga);
		return queryManagerLocal.singleClassQueryList(ContratoUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoUtilidadByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(ContratoUtilidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoUtilidadByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(ContratoUtilidadEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ContratoUtilidadIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoUtilidadByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ContratoUtilidadEJB.class, aMap);      
    }

/////////////////
}
