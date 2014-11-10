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
public abstract class _EventoContableSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_EventoContableSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.EventoContableIf addEventoContable(com.spirit.contabilidad.entity.EventoContableIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      EventoContableEJB value = new EventoContableEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      value.setModuloId(model.getModuloId());
      value.setOficinaId(model.getOficinaId());
      value.setDocumentoId(model.getDocumentoId());
      value.setLineaId(model.getLineaId());
      value.setPlanCuentaId(model.getPlanCuentaId());
      value.setEtapa(model.getEtapa());
      value.setAutorizacionRequerida(model.getAutorizacionRequerida());
      value.setAgruparDetalles(model.getAgruparDetalles());
      value.setUsarDetalleDescripcion(model.getUsarDetalleDescripcion());
      value.setValidoAlGuardar(model.getValidoAlGuardar());
      value.setValidoAlActualizar(model.getValidoAlActualizar());
      value.setSubtipoAsientoId(model.getSubtipoAsientoId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en eventoContable.", e);
			throw new GenericBusinessException(
					"Error al insertar información en eventoContable.");
      }
     
      return getEventoContable(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveEventoContable(com.spirit.contabilidad.entity.EventoContableIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      EventoContableEJB data = new EventoContableEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
      data.setModuloId(model.getModuloId());
      data.setOficinaId(model.getOficinaId());
      data.setDocumentoId(model.getDocumentoId());
      data.setLineaId(model.getLineaId());
      data.setPlanCuentaId(model.getPlanCuentaId());
      data.setEtapa(model.getEtapa());
      data.setAutorizacionRequerida(model.getAutorizacionRequerida());
      data.setAgruparDetalles(model.getAgruparDetalles());
      data.setUsarDetalleDescripcion(model.getUsarDetalleDescripcion());
      data.setValidoAlGuardar(model.getValidoAlGuardar());
      data.setValidoAlActualizar(model.getValidoAlActualizar());
      data.setSubtipoAsientoId(model.getSubtipoAsientoId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en eventoContable.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en eventoContable.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteEventoContable(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      EventoContableEJB data = manager.find(EventoContableEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en eventoContable.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en eventoContable.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.EventoContableIf getEventoContable(java.lang.Long id) {
      return (EventoContableEJB)queryManagerLocal.find(EventoContableEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEventoContableList() {
	  return queryManagerLocal.singleClassList(EventoContableEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEventoContableList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(EventoContableEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getEventoContableListSize() {
      Query countQuery = manager.createQuery("select count(*) from EventoContableEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEventoContableById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(EventoContableEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEventoContableByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(EventoContableEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEventoContableByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(EventoContableEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEventoContableByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(EventoContableEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEventoContableByModuloId(java.lang.Long moduloId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("moduloId", moduloId);
		return queryManagerLocal.singleClassQueryList(EventoContableEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEventoContableByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(EventoContableEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEventoContableByDocumentoId(java.lang.Long documentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoId", documentoId);
		return queryManagerLocal.singleClassQueryList(EventoContableEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEventoContableByLineaId(java.lang.Long lineaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("lineaId", lineaId);
		return queryManagerLocal.singleClassQueryList(EventoContableEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEventoContableByPlanCuentaId(java.lang.Long planCuentaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("planCuentaId", planCuentaId);
		return queryManagerLocal.singleClassQueryList(EventoContableEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEventoContableByEtapa(java.lang.Long etapa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("etapa", etapa);
		return queryManagerLocal.singleClassQueryList(EventoContableEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEventoContableByAutorizacionRequerida(java.lang.String autorizacionRequerida) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("autorizacionRequerida", autorizacionRequerida);
		return queryManagerLocal.singleClassQueryList(EventoContableEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEventoContableByAgruparDetalles(java.lang.String agruparDetalles) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("agruparDetalles", agruparDetalles);
		return queryManagerLocal.singleClassQueryList(EventoContableEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEventoContableByUsarDetalleDescripcion(java.lang.String usarDetalleDescripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usarDetalleDescripcion", usarDetalleDescripcion);
		return queryManagerLocal.singleClassQueryList(EventoContableEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEventoContableByValidoAlGuardar(java.lang.String validoAlGuardar) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("validoAlGuardar", validoAlGuardar);
		return queryManagerLocal.singleClassQueryList(EventoContableEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEventoContableByValidoAlActualizar(java.lang.String validoAlActualizar) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("validoAlActualizar", validoAlActualizar);
		return queryManagerLocal.singleClassQueryList(EventoContableEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEventoContableBySubtipoAsientoId(java.lang.Long subtipoAsientoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("subtipoAsientoId", subtipoAsientoId);
		return queryManagerLocal.singleClassQueryList(EventoContableEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of EventoContableIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEventoContableByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(EventoContableEJB.class, aMap);      
    }

/////////////////
}
