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
import com.spirit.contabilidad.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _AsientoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_AsientoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.AsientoIf addAsiento(com.spirit.contabilidad.entity.AsientoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      AsientoEJB value = new AsientoEJB();
      try {
      value.setId(model.getId());
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
      value.setAsientoCierre(model.getAsientoCierre());
      value.setUsarNota(model.getUsarNota());
      value.setNota(model.getNota());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en asiento.", e);
			throw new GenericBusinessException(
					"Error al insertar información en asiento.");
      }
     
      return getAsiento(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveAsiento(com.spirit.contabilidad.entity.AsientoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      AsientoEJB data = new AsientoEJB();
      data.setId(model.getId());
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
      data.setAsientoCierre(model.getAsientoCierre());
      data.setUsarNota(model.getUsarNota());
      data.setNota(model.getNota());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en asiento.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en asiento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteAsiento(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      AsientoEJB data = manager.find(AsientoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en asiento.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en asiento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.LogAsientoIf addLogAsiento(com.spirit.contabilidad.entity.LogAsientoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      LogAsientoEJB value = new LogAsientoEJB();
      try {
      value.setId(model.getId());
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
      value.setLog(model.getLog());
      value.setElaboradoPorId(model.getElaboradoPorId());
      value.setAutorizadoPorId(model.getAutorizadoPorId());
      value.setCarteraAfectaId(model.getCarteraAfectaId());
      value.setEventoContableId(model.getEventoContableId());
      value.setAsientoCierre(model.getAsientoCierre());
      value.setUsarNota(model.getUsarNota());
      value.setNota(model.getNota());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en logAsiento.", e);
			throw new GenericBusinessException(
					"Error al insertar información en logAsiento.");
      }
     
      return getLogAsiento(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveLogAsiento(com.spirit.contabilidad.entity.LogAsientoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      LogAsientoEJB data = new LogAsientoEJB();
      data.setId(model.getId());
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
      data.setLog(model.getLog());
      data.setElaboradoPorId(model.getElaboradoPorId());
      data.setAutorizadoPorId(model.getAutorizadoPorId());
      data.setCarteraAfectaId(model.getCarteraAfectaId());
      data.setEventoContableId(model.getEventoContableId());
      data.setAsientoCierre(model.getAsientoCierre());
      data.setUsarNota(model.getUsarNota());
      data.setNota(model.getNota());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en logAsiento.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en logAsiento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteLogAsiento(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      LogAsientoEJB data = manager.find(LogAsientoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en logAsiento.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en logAsiento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.AsientoIf getAsiento(java.lang.Long id) {
      return (AsientoEJB)queryManagerLocal.find(AsientoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getAsientoList() {
	  return queryManagerLocal.singleClassList(AsientoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getAsientoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(AsientoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getAsientoListSize() {
      Query countQuery = manager.createQuery("select count(*) from AsientoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.LogAsientoIf getLogAsiento(java.lang.Long id) {
      return (LogAsientoEJB)queryManagerLocal.find(LogAsientoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getLogAsientoList() {
	  return queryManagerLocal.singleClassList(LogAsientoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getLogAsientoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(LogAsientoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getLogAsientoListSize() {
      Query countQuery = manager.createQuery("select count(*) from LogAsientoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByNumero(java.lang.String numero) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numero", numero);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByPeriodoId(java.lang.Long periodoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("periodoId", periodoId);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByPlancuentaId(java.lang.Long plancuentaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("plancuentaId", plancuentaId);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByFecha(java.sql.Date fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByStatus(java.lang.String status) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("status", status);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByEfectivo(java.lang.String efectivo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("efectivo", efectivo);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoBySubtipoasientoId(java.lang.Long subtipoasientoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("subtipoasientoId", subtipoasientoId);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByTipoDocumentoId(java.lang.Long tipoDocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoDocumentoId", tipoDocumentoId);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByTransaccionId(java.lang.Long transaccionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transaccionId", transaccionId);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByElaboradoPorId(java.lang.Long elaboradoPorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("elaboradoPorId", elaboradoPorId);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByAutorizadoPorId(java.lang.Long autorizadoPorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("autorizadoPorId", autorizadoPorId);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByCarteraAfectaId(java.lang.Long carteraAfectaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("carteraAfectaId", carteraAfectaId);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByEventoContableId(java.lang.Long eventoContableId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("eventoContableId", eventoContableId);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByAsientoCierre(java.lang.String asientoCierre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("asientoCierre", asientoCierre);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByUsarNota(java.lang.String usarNota) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usarNota", usarNota);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByNota(java.lang.String nota) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nota", nota);
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of AsientoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(AsientoEJB.class, aMap);      
    }

/////////////////


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByNumero(java.lang.String numero) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numero", numero);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByPeriodoId(java.lang.Long periodoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("periodoId", periodoId);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByPlancuentaId(java.lang.Long plancuentaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("plancuentaId", plancuentaId);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByFecha(java.sql.Date fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByStatus(java.lang.String status) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("status", status);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByEfectivo(java.lang.String efectivo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("efectivo", efectivo);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoBySubtipoasientoId(java.lang.Long subtipoasientoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("subtipoasientoId", subtipoasientoId);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByTipoDocumentoId(java.lang.Long tipoDocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoDocumentoId", tipoDocumentoId);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByTransaccionId(java.lang.Long transaccionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transaccionId", transaccionId);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByLog(java.lang.String log) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("log", log);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByElaboradoPorId(java.lang.Long elaboradoPorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("elaboradoPorId", elaboradoPorId);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByAutorizadoPorId(java.lang.Long autorizadoPorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("autorizadoPorId", autorizadoPorId);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByCarteraAfectaId(java.lang.Long carteraAfectaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("carteraAfectaId", carteraAfectaId);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByEventoContableId(java.lang.Long eventoContableId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("eventoContableId", eventoContableId);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByAsientoCierre(java.lang.String asientoCierre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("asientoCierre", asientoCierre);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByUsarNota(java.lang.String usarNota) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usarNota", usarNota);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByNota(java.lang.String nota) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nota", nota);
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of LogAsientoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(LogAsientoEJB.class, aMap);      
    }

/////////////////
}
