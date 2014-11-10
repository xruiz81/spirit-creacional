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
public abstract class _AsientoDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_AsientoDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.AsientoDetalleIf addAsientoDetalle(com.spirit.contabilidad.entity.AsientoDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      AsientoDetalleEJB value = new AsientoDetalleEJB();
      try {
      value.setId(model.getId());
      value.setCuentaId(model.getCuentaId());
      value.setAsientoId(model.getAsientoId());
      value.setReferencia(model.getReferencia());
      value.setGlosa(model.getGlosa());
      value.setCentrogastoId(model.getCentrogastoId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setDepartamentoId(model.getDepartamentoId());
      value.setLineaId(model.getLineaId());
      value.setClienteId(model.getClienteId());
      value.setDebe(model.getDebe());
      value.setHaber(model.getHaber());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en asientoDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en asientoDetalle.");
      }
     
      return getAsientoDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveAsientoDetalle(com.spirit.contabilidad.entity.AsientoDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      AsientoDetalleEJB data = new AsientoDetalleEJB();
      data.setId(model.getId());
      data.setCuentaId(model.getCuentaId());
      data.setAsientoId(model.getAsientoId());
      data.setReferencia(model.getReferencia());
      data.setGlosa(model.getGlosa());
      data.setCentrogastoId(model.getCentrogastoId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setDepartamentoId(model.getDepartamentoId());
      data.setLineaId(model.getLineaId());
      data.setClienteId(model.getClienteId());
      data.setDebe(model.getDebe());
      data.setHaber(model.getHaber());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en asientoDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en asientoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteAsientoDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      AsientoDetalleEJB data = manager.find(AsientoDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en asientoDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en asientoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.AsientoDetalleIf getAsientoDetalle(java.lang.Long id) {
      return (AsientoDetalleEJB)queryManagerLocal.find(AsientoDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getAsientoDetalleList() {
	  return queryManagerLocal.singleClassList(AsientoDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getAsientoDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(AsientoDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getAsientoDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from AsientoDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleByCuentaId(java.lang.Long cuentaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaId", cuentaId);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleByAsientoId(java.lang.Long asientoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("asientoId", asientoId);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleByReferencia(java.lang.String referencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referencia", referencia);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleByGlosa(java.lang.String glosa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("glosa", glosa);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleByCentrogastoId(java.lang.Long centrogastoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("centrogastoId", centrogastoId);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleByDepartamentoId(java.lang.Long departamentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("departamentoId", departamentoId);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleByLineaId(java.lang.Long lineaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("lineaId", lineaId);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleByClienteId(java.lang.Long clienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteId", clienteId);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleByDebe(java.math.BigDecimal debe) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("debe", debe);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleByHaber(java.math.BigDecimal haber) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("haber", haber);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of AsientoDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(AsientoDetalleEJB.class, aMap);      
    }

/////////////////
}
