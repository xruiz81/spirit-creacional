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
public abstract class _AsientoTmpSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_AsientoTmpSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.AsientoTmpIf addAsientoTmp(com.spirit.contabilidad.entity.AsientoTmpIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      AsientoTmpEJB value = new AsientoTmpEJB();
      try {
      value.setId(model.getId());
      value.setAsientoCierre(model.getAsientoCierre());
      value.setNumero(model.getNumero());
      value.setEmpresaId(model.getEmpresaId());
      value.setPeriodoId(model.getPeriodoId());
      value.setPlancuentaId(model.getPlancuentaId());
      value.setFecha(model.getFecha());
      value.setStatus(model.getStatus());
      value.setEfectivo(model.getEfectivo());
      value.setSubtipoasientoId(model.getSubtipoasientoId());
      value.setObservacion(model.getObservacion());
      value.setOficinaId(model.getOficinaId());
      value.setTipoDocumentoId(model.getTipoDocumentoId());
      value.setTransaccionId(model.getTransaccionId());
      value.setElaboradoPorId(model.getElaboradoPorId());
      value.setAutorizadoPorId(model.getAutorizadoPorId());
      value.setCarteraAfectaId(model.getCarteraAfectaId());
      value.setEventoContableId(model.getEventoContableId());
      value.setUsarNota(model.getUsarNota());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en asientoTmp.", e);
			throw new GenericBusinessException(
					"Error al insertar información en asientoTmp.");
      }
     
      return getAsientoTmp(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveAsientoTmp(com.spirit.contabilidad.entity.AsientoTmpIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      AsientoTmpEJB data = new AsientoTmpEJB();
      data.setId(model.getId());
      data.setAsientoCierre(model.getAsientoCierre());
      data.setNumero(model.getNumero());
      data.setEmpresaId(model.getEmpresaId());
      data.setPeriodoId(model.getPeriodoId());
      data.setPlancuentaId(model.getPlancuentaId());
      data.setFecha(model.getFecha());
      data.setStatus(model.getStatus());
      data.setEfectivo(model.getEfectivo());
      data.setSubtipoasientoId(model.getSubtipoasientoId());
      data.setObservacion(model.getObservacion());
      data.setOficinaId(model.getOficinaId());
      data.setTipoDocumentoId(model.getTipoDocumentoId());
      data.setTransaccionId(model.getTransaccionId());
      data.setElaboradoPorId(model.getElaboradoPorId());
      data.setAutorizadoPorId(model.getAutorizadoPorId());
      data.setCarteraAfectaId(model.getCarteraAfectaId());
      data.setEventoContableId(model.getEventoContableId());
      data.setUsarNota(model.getUsarNota());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en asientoTmp.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en asientoTmp.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteAsientoTmp(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      AsientoTmpEJB data = manager.find(AsientoTmpEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en asientoTmp.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en asientoTmp.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.AsientoTmpIf getAsientoTmp(java.lang.Long id) {
      return (AsientoTmpEJB)queryManagerLocal.find(AsientoTmpEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getAsientoTmpList() {
	  return queryManagerLocal.singleClassList(AsientoTmpEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getAsientoTmpList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(AsientoTmpEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getAsientoTmpListSize() {
      Query countQuery = manager.createQuery("select count(*) from AsientoTmpEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByAsientoCierre(java.lang.String asientoCierre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("asientoCierre", asientoCierre);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByNumero(java.lang.String numero) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numero", numero);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByPeriodoId(java.lang.Long periodoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("periodoId", periodoId);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByPlancuentaId(java.lang.Long plancuentaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("plancuentaId", plancuentaId);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByFecha(java.sql.Timestamp fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByStatus(java.lang.String status) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("status", status);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByEfectivo(java.lang.String efectivo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("efectivo", efectivo);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpBySubtipoasientoId(java.lang.Long subtipoasientoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("subtipoasientoId", subtipoasientoId);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByTipoDocumentoId(java.lang.Long tipoDocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoDocumentoId", tipoDocumentoId);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByTransaccionId(java.lang.Long transaccionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transaccionId", transaccionId);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByElaboradoPorId(java.lang.Long elaboradoPorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("elaboradoPorId", elaboradoPorId);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByAutorizadoPorId(java.lang.Long autorizadoPorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("autorizadoPorId", autorizadoPorId);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByCarteraAfectaId(java.lang.Long carteraAfectaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("carteraAfectaId", carteraAfectaId);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByEventoContableId(java.lang.Long eventoContableId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("eventoContableId", eventoContableId);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByUsarNota(java.lang.String usarNota) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usarNota", usarNota);
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of AsientoTmpIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoTmpByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(AsientoTmpEJB.class, aMap);      
    }

/////////////////
}
