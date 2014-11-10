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
public abstract class _TiempoParcialDotSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TiempoParcialDotSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.TiempoParcialDotIf addTiempoParcialDot(com.spirit.medios.entity.TiempoParcialDotIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TiempoParcialDotEJB value = new TiempoParcialDotEJB();
      try {
      value.setId(model.getId());
      value.setDescripcion(model.getDescripcion());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      value.setTiempo(model.getTiempo());
      value.setUsuarioAsignadoId(model.getUsuarioAsignadoId());
      value.setIdOrdenTrabajoDetalle(model.getIdOrdenTrabajoDetalle());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tiempoParcialDot.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tiempoParcialDot.");
      }
     
      return getTiempoParcialDot(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTiempoParcialDot(com.spirit.medios.entity.TiempoParcialDotIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TiempoParcialDotEJB data = new TiempoParcialDotEJB();
      data.setId(model.getId());
      data.setDescripcion(model.getDescripcion());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
      data.setTiempo(model.getTiempo());
      data.setUsuarioAsignadoId(model.getUsuarioAsignadoId());
      data.setIdOrdenTrabajoDetalle(model.getIdOrdenTrabajoDetalle());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tiempoParcialDot.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tiempoParcialDot.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTiempoParcialDot(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TiempoParcialDotEJB data = manager.find(TiempoParcialDotEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tiempoParcialDot.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tiempoParcialDot.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.TiempoParcialDotIf getTiempoParcialDot(java.lang.Long id) {
      return (TiempoParcialDotEJB)queryManagerLocal.find(TiempoParcialDotEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTiempoParcialDotList() {
	  return queryManagerLocal.singleClassList(TiempoParcialDotEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTiempoParcialDotList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TiempoParcialDotEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTiempoParcialDotListSize() {
      Query countQuery = manager.createQuery("select count(*) from TiempoParcialDotEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTiempoParcialDotById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TiempoParcialDotEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTiempoParcialDotByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(TiempoParcialDotEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTiempoParcialDotByFechaInicio(java.lang.Long fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(TiempoParcialDotEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTiempoParcialDotByFechaFin(java.lang.Long fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(TiempoParcialDotEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTiempoParcialDotByTiempo(java.lang.Long tiempo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tiempo", tiempo);
		return queryManagerLocal.singleClassQueryList(TiempoParcialDotEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTiempoParcialDotByIdOrdenTrabajoDetalle(java.lang.Long idOrdenTrabajoDetalle) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("idOrdenTrabajoDetalle", idOrdenTrabajoDetalle);
		return queryManagerLocal.singleClassQueryList(TiempoParcialDotEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TiempoParcialDotIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTiempoParcialDotByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TiempoParcialDotEJB.class, aMap);      
    }

/////////////////
}
