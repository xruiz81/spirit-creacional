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
public abstract class _AsientoDetalleTmpSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_AsientoDetalleTmpSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.AsientoDetalleTmpIf addAsientoDetalleTmp(com.spirit.contabilidad.entity.AsientoDetalleTmpIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      AsientoDetalleTmpEJB value = new AsientoDetalleTmpEJB();
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
        log.error("Error al insertar información en asientoDetalleTmp.", e);
			throw new GenericBusinessException(
					"Error al insertar información en asientoDetalleTmp.");
      }
     
      return getAsientoDetalleTmp(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveAsientoDetalleTmp(com.spirit.contabilidad.entity.AsientoDetalleTmpIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      AsientoDetalleTmpEJB data = new AsientoDetalleTmpEJB();
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
        log.error("Error al actualizar información en asientoDetalleTmp.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en asientoDetalleTmp.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteAsientoDetalleTmp(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      AsientoDetalleTmpEJB data = manager.find(AsientoDetalleTmpEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en asientoDetalleTmp.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en asientoDetalleTmp.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.AsientoDetalleTmpIf getAsientoDetalleTmp(java.lang.Long id) {
      return (AsientoDetalleTmpEJB)queryManagerLocal.find(AsientoDetalleTmpEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getAsientoDetalleTmpList() {
	  return queryManagerLocal.singleClassList(AsientoDetalleTmpEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getAsientoDetalleTmpList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(AsientoDetalleTmpEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getAsientoDetalleTmpListSize() {
      Query countQuery = manager.createQuery("select count(*) from AsientoDetalleTmpEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleTmpById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleTmpByCuentaId(java.lang.Long cuentaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaId", cuentaId);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleTmpByAsientoId(java.lang.Long asientoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("asientoId", asientoId);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleTmpByReferencia(java.lang.String referencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referencia", referencia);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleTmpByGlosa(java.lang.String glosa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("glosa", glosa);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleTmpByCentrogastoId(java.lang.Long centrogastoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("centrogastoId", centrogastoId);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleTmpByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleTmpByDepartamentoId(java.lang.Long departamentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("departamentoId", departamentoId);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleTmpByLineaId(java.lang.Long lineaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("lineaId", lineaId);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleTmpByClienteId(java.lang.Long clienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteId", clienteId);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleTmpByDebe(java.math.BigDecimal debe) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("debe", debe);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleTmpEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleTmpByHaber(java.math.BigDecimal haber) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("haber", haber);
		return queryManagerLocal.singleClassQueryList(AsientoDetalleTmpEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of AsientoDetalleTmpIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDetalleTmpByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(AsientoDetalleTmpEJB.class, aMap);      
    }

/////////////////
}
