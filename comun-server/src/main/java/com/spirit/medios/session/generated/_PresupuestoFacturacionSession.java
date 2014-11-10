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
public abstract class _PresupuestoFacturacionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PresupuestoFacturacionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PresupuestoFacturacionIf addPresupuestoFacturacion(com.spirit.medios.entity.PresupuestoFacturacionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PresupuestoFacturacionEJB value = new PresupuestoFacturacionEJB();
      try {
      value.setId(model.getId());
      value.setPresupuestoDetalleId(model.getPresupuestoDetalleId());
      value.setFacturaId(model.getFacturaId());
      value.setEstado(model.getEstado());
      value.setTipo(model.getTipo());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en presupuestoFacturacion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en presupuestoFacturacion.");
      }
     
      return getPresupuestoFacturacion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePresupuestoFacturacion(com.spirit.medios.entity.PresupuestoFacturacionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PresupuestoFacturacionEJB data = new PresupuestoFacturacionEJB();
      data.setId(model.getId());
      data.setPresupuestoDetalleId(model.getPresupuestoDetalleId());
      data.setFacturaId(model.getFacturaId());
      data.setEstado(model.getEstado());
      data.setTipo(model.getTipo());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en presupuestoFacturacion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en presupuestoFacturacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePresupuestoFacturacion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PresupuestoFacturacionEJB data = manager.find(PresupuestoFacturacionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en presupuestoFacturacion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en presupuestoFacturacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PresupuestoFacturacionIf getPresupuestoFacturacion(java.lang.Long id) {
      return (PresupuestoFacturacionEJB)queryManagerLocal.find(PresupuestoFacturacionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPresupuestoFacturacionList() {
	  return queryManagerLocal.singleClassList(PresupuestoFacturacionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPresupuestoFacturacionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PresupuestoFacturacionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPresupuestoFacturacionListSize() {
      Query countQuery = manager.createQuery("select count(*) from PresupuestoFacturacionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoFacturacionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PresupuestoFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoFacturacionByPresupuestoDetalleId(java.lang.Long presupuestoDetalleId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("presupuestoDetalleId", presupuestoDetalleId);
		return queryManagerLocal.singleClassQueryList(PresupuestoFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoFacturacionByFacturaId(java.lang.Long facturaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("facturaId", facturaId);
		return queryManagerLocal.singleClassQueryList(PresupuestoFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoFacturacionByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(PresupuestoFacturacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoFacturacionByTipo(java.lang.String tipo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipo", tipo);
		return queryManagerLocal.singleClassQueryList(PresupuestoFacturacionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PresupuestoFacturacionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoFacturacionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PresupuestoFacturacionEJB.class, aMap);      
    }

/////////////////
}
