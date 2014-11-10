package com.spirit.cartera.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.cartera.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _CarteraPagoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CarteraPagoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.CarteraPagoIf addCarteraPago(com.spirit.cartera.entity.CarteraPagoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CarteraPagoEJB value = new CarteraPagoEJB();
      try {
      value.setId(model.getId());
      value.setCarteraPagoId(model.getCarteraPagoId());
      value.setFechaAprobacion(model.getFechaAprobacion());
      value.setUsuarioAprobacionId(model.getUsuarioAprobacionId());
      value.setFechaPago(model.getFechaPago());
      value.setUsuarioPagoId(model.getUsuarioPagoId());
      value.setSecuencialMulticash(model.getSecuencialMulticash());
      value.setArchivoMulticash(model.getArchivoMulticash());
      value.setEstado(model.getEstado());
      value.setFechaEmision(model.getFechaEmision());
      value.setUsuarioEmisionId(model.getUsuarioEmisionId());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en carteraPago.", e);
			throw new GenericBusinessException(
					"Error al insertar información en carteraPago.");
      }
     
      return getCarteraPago(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCarteraPago(com.spirit.cartera.entity.CarteraPagoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CarteraPagoEJB data = new CarteraPagoEJB();
      data.setId(model.getId());
      data.setCarteraPagoId(model.getCarteraPagoId());
      data.setFechaAprobacion(model.getFechaAprobacion());
      data.setUsuarioAprobacionId(model.getUsuarioAprobacionId());
      data.setFechaPago(model.getFechaPago());
      data.setUsuarioPagoId(model.getUsuarioPagoId());
      data.setSecuencialMulticash(model.getSecuencialMulticash());
      data.setArchivoMulticash(model.getArchivoMulticash());
      data.setEstado(model.getEstado());
      data.setFechaEmision(model.getFechaEmision());
      data.setUsuarioEmisionId(model.getUsuarioEmisionId());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en carteraPago.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en carteraPago.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCarteraPago(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CarteraPagoEJB data = manager.find(CarteraPagoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en carteraPago.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en carteraPago.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.CarteraPagoIf getCarteraPago(java.lang.Long id) {
      return (CarteraPagoEJB)queryManagerLocal.find(CarteraPagoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCarteraPagoList() {
	  return queryManagerLocal.singleClassList(CarteraPagoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCarteraPagoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CarteraPagoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCarteraPagoListSize() {
      Query countQuery = manager.createQuery("select count(*) from CarteraPagoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraPagoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CarteraPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraPagoByCarteraPagoId(java.lang.Long carteraPagoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("carteraPagoId", carteraPagoId);
		return queryManagerLocal.singleClassQueryList(CarteraPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraPagoByFechaAprobacion(java.sql.Timestamp fechaAprobacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaAprobacion", fechaAprobacion);
		return queryManagerLocal.singleClassQueryList(CarteraPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraPagoByUsuarioAprobacionId(java.lang.Long usuarioAprobacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioAprobacionId", usuarioAprobacionId);
		return queryManagerLocal.singleClassQueryList(CarteraPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraPagoByFechaPago(java.sql.Timestamp fechaPago) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaPago", fechaPago);
		return queryManagerLocal.singleClassQueryList(CarteraPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraPagoByUsuarioPagoId(java.lang.Long usuarioPagoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioPagoId", usuarioPagoId);
		return queryManagerLocal.singleClassQueryList(CarteraPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraPagoBySecuencialMulticash(java.lang.String secuencialMulticash) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("secuencialMulticash", secuencialMulticash);
		return queryManagerLocal.singleClassQueryList(CarteraPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraPagoByArchivoMulticash(java.lang.String archivoMulticash) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("archivoMulticash", archivoMulticash);
		return queryManagerLocal.singleClassQueryList(CarteraPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraPagoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(CarteraPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraPagoByFechaEmision(java.sql.Timestamp fechaEmision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaEmision", fechaEmision);
		return queryManagerLocal.singleClassQueryList(CarteraPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraPagoByUsuarioEmisionId(java.lang.Long usuarioEmisionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioEmisionId", usuarioEmisionId);
		return queryManagerLocal.singleClassQueryList(CarteraPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraPagoByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(CarteraPagoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CarteraPagoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraPagoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CarteraPagoEJB.class, aMap);      
    }

/////////////////
}
