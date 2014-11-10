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
public abstract class _LogAsientoDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_LogAsientoDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.LogAsientoDetalleIf addLogAsientoDetalle(com.spirit.contabilidad.entity.LogAsientoDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      LogAsientoDetalleEJB value = new LogAsientoDetalleEJB();
      try {
      value.setId(model.getId());
      value.setCuentaId(model.getCuentaId());
      value.setLogAsientoId(model.getLogAsientoId());
      value.setReferencia(model.getReferencia());
      value.setGlosa(model.getGlosa());
      value.setCentrogastoId(model.getCentrogastoId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setDepartamentoId(model.getDepartamentoId());
      value.setLineaId(model.getLineaId());
      value.setClienteId(model.getClienteId());
      value.setDebe(model.getDebe());
      value.setHaber(model.getHaber());
      value.setLog(model.getLog());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en logAsientoDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en logAsientoDetalle.");
      }
     
      return getLogAsientoDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveLogAsientoDetalle(com.spirit.contabilidad.entity.LogAsientoDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      LogAsientoDetalleEJB data = new LogAsientoDetalleEJB();
      data.setId(model.getId());
      data.setCuentaId(model.getCuentaId());
      data.setLogAsientoId(model.getLogAsientoId());
      data.setReferencia(model.getReferencia());
      data.setGlosa(model.getGlosa());
      data.setCentrogastoId(model.getCentrogastoId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setDepartamentoId(model.getDepartamentoId());
      data.setLineaId(model.getLineaId());
      data.setClienteId(model.getClienteId());
      data.setDebe(model.getDebe());
      data.setHaber(model.getHaber());
      data.setLog(model.getLog());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en logAsientoDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en logAsientoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteLogAsientoDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      LogAsientoDetalleEJB data = manager.find(LogAsientoDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en logAsientoDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en logAsientoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.LogAsientoDetalleIf getLogAsientoDetalle(java.lang.Long id) {
      return (LogAsientoDetalleEJB)queryManagerLocal.find(LogAsientoDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getLogAsientoDetalleList() {
	  return queryManagerLocal.singleClassList(LogAsientoDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getLogAsientoDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(LogAsientoDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getLogAsientoDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from LogAsientoDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(LogAsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoDetalleByCuentaId(java.lang.Long cuentaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaId", cuentaId);
		return queryManagerLocal.singleClassQueryList(LogAsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoDetalleByLogAsientoId(java.lang.Long logAsientoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("logAsientoId", logAsientoId);
		return queryManagerLocal.singleClassQueryList(LogAsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoDetalleByReferencia(java.lang.String referencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referencia", referencia);
		return queryManagerLocal.singleClassQueryList(LogAsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoDetalleByGlosa(java.lang.String glosa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("glosa", glosa);
		return queryManagerLocal.singleClassQueryList(LogAsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoDetalleByCentrogastoId(java.lang.Long centrogastoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("centrogastoId", centrogastoId);
		return queryManagerLocal.singleClassQueryList(LogAsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoDetalleByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(LogAsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoDetalleByDepartamentoId(java.lang.Long departamentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("departamentoId", departamentoId);
		return queryManagerLocal.singleClassQueryList(LogAsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoDetalleByLineaId(java.lang.Long lineaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("lineaId", lineaId);
		return queryManagerLocal.singleClassQueryList(LogAsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoDetalleByClienteId(java.lang.Long clienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteId", clienteId);
		return queryManagerLocal.singleClassQueryList(LogAsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoDetalleByDebe(java.math.BigDecimal debe) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("debe", debe);
		return queryManagerLocal.singleClassQueryList(LogAsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoDetalleByHaber(java.math.BigDecimal haber) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("haber", haber);
		return queryManagerLocal.singleClassQueryList(LogAsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoDetalleByLog(java.lang.String log) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("log", log);
		return queryManagerLocal.singleClassQueryList(LogAsientoDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of LogAsientoDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogAsientoDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(LogAsientoDetalleEJB.class, aMap);      
    }

/////////////////
}
