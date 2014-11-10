package com.spirit.sri.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.sri.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _SriClienteRetencionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SriClienteRetencionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriClienteRetencionIf addSriClienteRetencion(com.spirit.sri.entity.SriClienteRetencionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SriClienteRetencionEJB value = new SriClienteRetencionEJB();
      try {
      value.setId(model.getId());
      value.setTipoRetencion(model.getTipoRetencion());
      value.setPorcentajeRetencion(model.getPorcentajeRetencion());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      value.setEstado(model.getEstado());
      value.setCuentaId(model.getCuentaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en sriClienteRetencion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en sriClienteRetencion.");
      }
     
      return getSriClienteRetencion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSriClienteRetencion(com.spirit.sri.entity.SriClienteRetencionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SriClienteRetencionEJB data = new SriClienteRetencionEJB();
      data.setId(model.getId());
      data.setTipoRetencion(model.getTipoRetencion());
      data.setPorcentajeRetencion(model.getPorcentajeRetencion());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
      data.setEstado(model.getEstado());
      data.setCuentaId(model.getCuentaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en sriClienteRetencion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en sriClienteRetencion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSriClienteRetencion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SriClienteRetencionEJB data = manager.find(SriClienteRetencionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en sriClienteRetencion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en sriClienteRetencion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriClienteRetencionIf getSriClienteRetencion(java.lang.Long id) {
      return (SriClienteRetencionEJB)queryManagerLocal.find(SriClienteRetencionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriClienteRetencionList() {
	  return queryManagerLocal.singleClassList(SriClienteRetencionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriClienteRetencionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SriClienteRetencionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSriClienteRetencionListSize() {
      Query countQuery = manager.createQuery("select count(*) from SriClienteRetencionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriClienteRetencionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SriClienteRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriClienteRetencionByTipoRetencion(java.lang.String tipoRetencion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoRetencion", tipoRetencion);
		return queryManagerLocal.singleClassQueryList(SriClienteRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriClienteRetencionByPorcentajeRetencion(java.math.BigDecimal porcentajeRetencion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeRetencion", porcentajeRetencion);
		return queryManagerLocal.singleClassQueryList(SriClienteRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriClienteRetencionByFechaInicio(java.sql.Date fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(SriClienteRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriClienteRetencionByFechaFin(java.sql.Date fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(SriClienteRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriClienteRetencionByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(SriClienteRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriClienteRetencionByCuentaId(java.lang.Long cuentaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaId", cuentaId);
		return queryManagerLocal.singleClassQueryList(SriClienteRetencionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SriClienteRetencionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriClienteRetencionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SriClienteRetencionEJB.class, aMap);      
    }

/////////////////
}
