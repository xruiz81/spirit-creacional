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
public abstract class _SriIvaRetencionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SriIvaRetencionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriIvaRetencionIf addSriIvaRetencion(com.spirit.sri.entity.SriIvaRetencionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SriIvaRetencionEJB value = new SriIvaRetencionEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setConcepto(model.getConcepto());
      value.setPorcentaje(model.getPorcentaje());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en sriIvaRetencion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en sriIvaRetencion.");
      }
     
      return getSriIvaRetencion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSriIvaRetencion(com.spirit.sri.entity.SriIvaRetencionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SriIvaRetencionEJB data = new SriIvaRetencionEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setConcepto(model.getConcepto());
      data.setPorcentaje(model.getPorcentaje());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en sriIvaRetencion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en sriIvaRetencion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSriIvaRetencion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SriIvaRetencionEJB data = manager.find(SriIvaRetencionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en sriIvaRetencion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en sriIvaRetencion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriIvaRetencionIf getSriIvaRetencion(java.lang.Long id) {
      return (SriIvaRetencionEJB)queryManagerLocal.find(SriIvaRetencionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriIvaRetencionList() {
	  return queryManagerLocal.singleClassList(SriIvaRetencionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriIvaRetencionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SriIvaRetencionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSriIvaRetencionListSize() {
      Query countQuery = manager.createQuery("select count(*) from SriIvaRetencionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaRetencionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SriIvaRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaRetencionByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(SriIvaRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaRetencionByConcepto(java.lang.String concepto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("concepto", concepto);
		return queryManagerLocal.singleClassQueryList(SriIvaRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaRetencionByPorcentaje(java.math.BigDecimal porcentaje) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentaje", porcentaje);
		return queryManagerLocal.singleClassQueryList(SriIvaRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaRetencionByFechaInicio(java.sql.Date fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(SriIvaRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaRetencionByFechaFin(java.sql.Date fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(SriIvaRetencionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SriIvaRetencionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaRetencionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SriIvaRetencionEJB.class, aMap);      
    }

/////////////////
}
