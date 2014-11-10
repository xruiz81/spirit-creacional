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
public abstract class _SriProveedorRetencionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SriProveedorRetencionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriProveedorRetencionIf addSriProveedorRetencion(com.spirit.sri.entity.SriProveedorRetencionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SriProveedorRetencionEJB value = new SriProveedorRetencionEJB();
      try {
      value.setId(model.getId());
      value.setTipoPersona(model.getTipoPersona());
      value.setLlevaContabilidad(model.getLlevaContabilidad());
      value.setBienServicio(model.getBienServicio());
      value.setRetefuente(model.getRetefuente());
      value.setReteiva(model.getReteiva());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      value.setEstado(model.getEstado());
      value.setIdCuentaRetefuente(model.getIdCuentaRetefuente());
      value.setIdCuentaReteiva(model.getIdCuentaReteiva());
      value.setProfesional(model.getProfesional());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
    	  e.printStackTrace();
        log.error("Error al insertar información en sriProveedorRetencion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en sriProveedorRetencion.");
      }
     
      return getSriProveedorRetencion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSriProveedorRetencion(com.spirit.sri.entity.SriProveedorRetencionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SriProveedorRetencionEJB data = new SriProveedorRetencionEJB();
      data.setId(model.getId());
      data.setTipoPersona(model.getTipoPersona());
      data.setLlevaContabilidad(model.getLlevaContabilidad());
      data.setBienServicio(model.getBienServicio());
      data.setRetefuente(model.getRetefuente());
      data.setReteiva(model.getReteiva());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
      data.setEstado(model.getEstado());
      data.setIdCuentaRetefuente(model.getIdCuentaRetefuente());
      data.setIdCuentaReteiva(model.getIdCuentaReteiva());
      data.setProfesional(model.getProfesional());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en sriProveedorRetencion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en sriProveedorRetencion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSriProveedorRetencion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SriProveedorRetencionEJB data = manager.find(SriProveedorRetencionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en sriProveedorRetencion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en sriProveedorRetencion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriProveedorRetencionIf getSriProveedorRetencion(java.lang.Long id) {
      return (SriProveedorRetencionEJB)queryManagerLocal.find(SriProveedorRetencionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriProveedorRetencionList() {
	  return queryManagerLocal.singleClassList(SriProveedorRetencionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriProveedorRetencionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SriProveedorRetencionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSriProveedorRetencionListSize() {
      Query countQuery = manager.createQuery("select count(*) from SriProveedorRetencionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriProveedorRetencionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SriProveedorRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriProveedorRetencionByTipoPersona(java.lang.String tipoPersona) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoPersona", tipoPersona);
		return queryManagerLocal.singleClassQueryList(SriProveedorRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriProveedorRetencionByLlevaContabilidad(java.lang.String llevaContabilidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("llevaContabilidad", llevaContabilidad);
		return queryManagerLocal.singleClassQueryList(SriProveedorRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriProveedorRetencionByBienServicio(java.lang.String bienServicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bienServicio", bienServicio);
		return queryManagerLocal.singleClassQueryList(SriProveedorRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriProveedorRetencionByRetefuente(java.math.BigDecimal retefuente) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("retefuente", retefuente);
		return queryManagerLocal.singleClassQueryList(SriProveedorRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriProveedorRetencionByReteiva(java.math.BigDecimal reteiva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("reteiva", reteiva);
		return queryManagerLocal.singleClassQueryList(SriProveedorRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriProveedorRetencionByFechaInicio(java.sql.Date fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(SriProveedorRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriProveedorRetencionByFechaFin(java.sql.Date fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(SriProveedorRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriProveedorRetencionByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(SriProveedorRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriProveedorRetencionByIdCuentaRetefuente(java.lang.Long idCuentaRetefuente) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("idCuentaRetefuente", idCuentaRetefuente);
		return queryManagerLocal.singleClassQueryList(SriProveedorRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriProveedorRetencionByIdCuentaReteiva(java.lang.Long idCuentaReteiva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("idCuentaReteiva", idCuentaReteiva);
		return queryManagerLocal.singleClassQueryList(SriProveedorRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriProveedorRetencionByProfesional(java.lang.String profesional) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("profesional", profesional);
		return queryManagerLocal.singleClassQueryList(SriProveedorRetencionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SriProveedorRetencionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriProveedorRetencionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SriProveedorRetencionEJB.class, aMap);      
    }

/////////////////
}
