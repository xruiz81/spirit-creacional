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
public abstract class _ContratoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ContratoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.ContratoIf addContrato(com.spirit.nomina.entity.ContratoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ContratoEJB value = new ContratoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setFechaElaboracion(model.getFechaElaboracion());
      value.setTipocontratoId(model.getTipocontratoId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      value.setEstado(model.getEstado());
      value.setObservacion(model.getObservacion());
      value.setUrl(model.getUrl());
      value.setFondoReservaDiasPrevios(model.getFondoReservaDiasPrevios());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en contrato.", e);
			throw new GenericBusinessException(
					"Error al insertar información en contrato.");
      }
     
      return getContrato(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveContrato(com.spirit.nomina.entity.ContratoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ContratoEJB data = new ContratoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setFechaElaboracion(model.getFechaElaboracion());
      data.setTipocontratoId(model.getTipocontratoId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
      data.setEstado(model.getEstado());
      data.setObservacion(model.getObservacion());
      data.setUrl(model.getUrl());
      data.setFondoReservaDiasPrevios(model.getFondoReservaDiasPrevios());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en contrato.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en contrato.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteContrato(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ContratoEJB data = manager.find(ContratoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en contrato.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en contrato.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.ContratoIf getContrato(java.lang.Long id) {
      return (ContratoEJB)queryManagerLocal.find(ContratoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getContratoList() {
	  return queryManagerLocal.singleClassList(ContratoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getContratoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ContratoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getContratoListSize() {
      Query countQuery = manager.createQuery("select count(*) from ContratoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ContratoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(ContratoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoByFechaElaboracion(java.sql.Date fechaElaboracion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaElaboracion", fechaElaboracion);
		return queryManagerLocal.singleClassQueryList(ContratoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoByTipocontratoId(java.lang.Long tipocontratoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipocontratoId", tipocontratoId);
		return queryManagerLocal.singleClassQueryList(ContratoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(ContratoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoByFechaInicio(java.sql.Date fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(ContratoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoByFechaFin(java.sql.Date fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(ContratoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(ContratoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(ContratoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoByUrl(java.lang.String url) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("url", url);
		return queryManagerLocal.singleClassQueryList(ContratoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoByFondoReservaDiasPrevios(java.lang.Integer fondoReservaDiasPrevios) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fondoReservaDiasPrevios", fondoReservaDiasPrevios);
		return queryManagerLocal.singleClassQueryList(ContratoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ContratoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ContratoEJB.class, aMap);      
    }

/////////////////
}
