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
public abstract class _TiempoParcialDotDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TiempoParcialDotDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.TiempoParcialDotDetalleIf addTiempoParcialDotDetalle(com.spirit.medios.entity.TiempoParcialDotDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TiempoParcialDotDetalleEJB value = new TiempoParcialDotDetalleEJB();
      try {
      value.setId(model.getId());
      value.setFecha(model.getFecha());
      value.setHoraInicio(model.getHoraInicio());
      value.setHoraFin(model.getHoraFin());
      value.setTiempo(model.getTiempo());
      value.setIdTiempoParcialDot(model.getIdTiempoParcialDot());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tiempoParcialDotDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tiempoParcialDotDetalle.");
      }
     
      return getTiempoParcialDotDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTiempoParcialDotDetalle(com.spirit.medios.entity.TiempoParcialDotDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TiempoParcialDotDetalleEJB data = new TiempoParcialDotDetalleEJB();
      data.setId(model.getId());
      data.setFecha(model.getFecha());
      data.setHoraInicio(model.getHoraInicio());
      data.setHoraFin(model.getHoraFin());
      data.setTiempo(model.getTiempo());
      data.setIdTiempoParcialDot(model.getIdTiempoParcialDot());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tiempoParcialDotDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tiempoParcialDotDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTiempoParcialDotDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TiempoParcialDotDetalleEJB data = manager.find(TiempoParcialDotDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tiempoParcialDotDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tiempoParcialDotDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.TiempoParcialDotDetalleIf getTiempoParcialDotDetalle(java.lang.Long id) {
      return (TiempoParcialDotDetalleEJB)queryManagerLocal.find(TiempoParcialDotDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTiempoParcialDotDetalleList() {
	  return queryManagerLocal.singleClassList(TiempoParcialDotDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTiempoParcialDotDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TiempoParcialDotDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTiempoParcialDotDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from TiempoParcialDotDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTiempoParcialDotDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TiempoParcialDotDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTiempoParcialDotDetalleByFecha(java.lang.Long fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(TiempoParcialDotDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTiempoParcialDotDetalleByHoraInicio(java.lang.Long horaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("horaInicio", horaInicio);
		return queryManagerLocal.singleClassQueryList(TiempoParcialDotDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTiempoParcialDotDetalleByHoraFin(java.lang.Long horaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("horaFin", horaFin);
		return queryManagerLocal.singleClassQueryList(TiempoParcialDotDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTiempoParcialDotDetalleByTiempo(java.lang.Long tiempo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tiempo", tiempo);
		return queryManagerLocal.singleClassQueryList(TiempoParcialDotDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTiempoParcialDotDetalleByIdTiempoParcialDot(java.lang.Long idTiempoParcialDot) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("idTiempoParcialDot", idTiempoParcialDot);
		return queryManagerLocal.singleClassQueryList(TiempoParcialDotDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TiempoParcialDotDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTiempoParcialDotDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TiempoParcialDotDetalleEJB.class, aMap);      
    }

/////////////////
}
