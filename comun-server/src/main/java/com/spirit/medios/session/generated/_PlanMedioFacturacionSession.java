package com.spirit.medios.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.medios.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _PlanMedioFacturacionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PlanMedioFacturacionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PlanMedioFacturacionIf addPlanMedioFacturacion(com.spirit.medios.entity.PlanMedioFacturacionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PlanMedioFacturacionEJB value = new PlanMedioFacturacionEJB();
      try {
      value.setId(model.getId());
      value.setPlanMedioId(model.getPlanMedioId());
      value.setComercialId(model.getComercialId());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      value.setPedidoId(model.getPedidoId());
      value.setCanje(model.getCanje());
      value.setProveedorId(model.getProveedorId());
      value.setEstado(model.getEstado());
      value.setPorcentajeCanje(model.getPorcentajeCanje());
      value.setOrdenMedioId(model.getOrdenMedioId());
      value.setOrdenMedioDetalleId(model.getOrdenMedioDetalleId());
      value.setOrdenMedioDetalleMapaId(model.getOrdenMedioDetalleMapaId());
      value.setProductoClienteId(model.getProductoClienteId());
      value.setCampanaProductoVersionId(model.getCampanaProductoVersionId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en planMedioFacturacion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en planMedioFacturacion.");
      }
     
      return getPlanMedioFacturacion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePlanMedioFacturacion(com.spirit.medios.entity.PlanMedioFacturacionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PlanMedioFacturacionEJB data = new PlanMedioFacturacionEJB();
      data.setId(model.getId());
      data.setPlanMedioId(model.getPlanMedioId());
      data.setComercialId(model.getComercialId());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
      data.setPedidoId(model.getPedidoId());
      data.setCanje(model.getCanje());
      data.setProveedorId(model.getProveedorId());
      data.setEstado(model.getEstado());
      data.setPorcentajeCanje(model.getPorcentajeCanje());
      data.setOrdenMedioId(model.getOrdenMedioId());
      data.setOrdenMedioDetalleId(model.getOrdenMedioDetalleId());
      data.setOrdenMedioDetalleMapaId(model.getOrdenMedioDetalleMapaId());
      data.setProductoClienteId(model.getProductoClienteId());
      data.setCampanaProductoVersionId(model.getCampanaProductoVersionId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en planMedioFacturacion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en planMedioFacturacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePlanMedioFacturacion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PlanMedioFacturacionEJB data = manager.find(PlanMedioFacturacionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en planMedioFacturacion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en planMedioFacturacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PlanMedioFacturacionIf getPlanMedioFacturacion(java.lang.Long id) {
      return (PlanMedioFacturacionEJB)queryManagerLocal.find(PlanMedioFacturacionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPlanMedioFacturacionList() {
	  return queryManagerLocal.singleClassList(PlanMedioFacturacionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPlanMedioFacturacionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PlanMedioFacturacionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPlanMedioFacturacionListSize() {
      Query countQuery = manager.createQuery("select count(*) from PlanMedioFacturacionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFacturacionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PlanMedioFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFacturacionByPlanMedioId(java.lang.Long planMedioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("planMedioId", planMedioId);
		return queryManagerLocal.singleClassQueryList(PlanMedioFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFacturacionByComercialId(java.lang.Long comercialId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("comercialId", comercialId);
		return queryManagerLocal.singleClassQueryList(PlanMedioFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFacturacionByFechaInicio(java.sql.Timestamp fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(PlanMedioFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFacturacionByFechaFin(java.sql.Timestamp fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(PlanMedioFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFacturacionByPedidoId(java.lang.Long pedidoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("pedidoId", pedidoId);
		return queryManagerLocal.singleClassQueryList(PlanMedioFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFacturacionByCanje(java.lang.String canje) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("canje", canje);
		return queryManagerLocal.singleClassQueryList(PlanMedioFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFacturacionByProveedorId(java.lang.Long proveedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("proveedorId", proveedorId);
		return queryManagerLocal.singleClassQueryList(PlanMedioFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFacturacionByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(PlanMedioFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFacturacionByPorcentajeCanje(java.math.BigDecimal porcentajeCanje) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeCanje", porcentajeCanje);
		return queryManagerLocal.singleClassQueryList(PlanMedioFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFacturacionByOrdenMedioId(java.lang.Long ordenMedioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordenMedioId", ordenMedioId);
		return queryManagerLocal.singleClassQueryList(PlanMedioFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFacturacionByOrdenMedioDetalleId(java.lang.Long ordenMedioDetalleId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordenMedioDetalleId", ordenMedioDetalleId);
		return queryManagerLocal.singleClassQueryList(PlanMedioFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFacturacionByOrdenMedioDetalleMapaId(java.lang.Long ordenMedioDetalleMapaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordenMedioDetalleMapaId", ordenMedioDetalleMapaId);
		return queryManagerLocal.singleClassQueryList(PlanMedioFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFacturacionByProductoClienteId(java.lang.Long productoClienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoClienteId", productoClienteId);
		return queryManagerLocal.singleClassQueryList(PlanMedioFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFacturacionByCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("campanaProductoVersionId", campanaProductoVersionId);
		return queryManagerLocal.singleClassQueryList(PlanMedioFacturacionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PlanMedioFacturacionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioFacturacionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PlanMedioFacturacionEJB.class, aMap);      
    }

/////////////////
}
