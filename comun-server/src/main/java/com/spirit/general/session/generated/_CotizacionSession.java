package com.spirit.general.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.general.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _CotizacionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CotizacionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.CotizacionIf addCotizacion(com.spirit.general.entity.CotizacionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CotizacionEJB value = new CotizacionEJB();
      try {
      value.setId(model.getId());
      value.setMonedaId(model.getMonedaId());
      value.setMonedaequivId(model.getMonedaequivId());
      value.setFecha(model.getFecha());
      value.setCotizacion(model.getCotizacion());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en cotizacion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en cotizacion.");
      }
     
      return getCotizacion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCotizacion(com.spirit.general.entity.CotizacionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CotizacionEJB data = new CotizacionEJB();
      data.setId(model.getId());
      data.setMonedaId(model.getMonedaId());
      data.setMonedaequivId(model.getMonedaequivId());
      data.setFecha(model.getFecha());
      data.setCotizacion(model.getCotizacion());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en cotizacion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en cotizacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCotizacion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CotizacionEJB data = manager.find(CotizacionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en cotizacion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en cotizacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.CotizacionIf getCotizacion(java.lang.Long id) {
      return (CotizacionEJB)queryManagerLocal.find(CotizacionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCotizacionList() {
	  return queryManagerLocal.singleClassList(CotizacionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCotizacionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CotizacionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCotizacionListSize() {
      Query countQuery = manager.createQuery("select count(*) from CotizacionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCotizacionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CotizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCotizacionByMonedaId(java.lang.Long monedaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("monedaId", monedaId);
		return queryManagerLocal.singleClassQueryList(CotizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCotizacionByMonedaequivId(java.lang.Long monedaequivId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("monedaequivId", monedaequivId);
		return queryManagerLocal.singleClassQueryList(CotizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCotizacionByFecha(java.sql.Date fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(CotizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCotizacionByCotizacion(java.math.BigDecimal cotizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cotizacion", cotizacion);
		return queryManagerLocal.singleClassQueryList(CotizacionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CotizacionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCotizacionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CotizacionEJB.class, aMap);      
    }

/////////////////
}
