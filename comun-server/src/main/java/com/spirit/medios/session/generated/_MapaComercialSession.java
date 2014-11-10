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
public abstract class _MapaComercialSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_MapaComercialSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.MapaComercialIf addMapaComercial(com.spirit.medios.entity.MapaComercialIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      MapaComercialEJB value = new MapaComercialEJB();
      try {
      value.setId(model.getId());
      value.setPlanMedioDetalleId(model.getPlanMedioDetalleId());
      value.setFecha(model.getFecha());
      value.setFrecuencia(model.getFrecuencia());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en mapaComercial.", e);
			throw new GenericBusinessException(
					"Error al insertar información en mapaComercial.");
      }
     
      return getMapaComercial(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveMapaComercial(com.spirit.medios.entity.MapaComercialIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      MapaComercialEJB data = new MapaComercialEJB();
      data.setId(model.getId());
      data.setPlanMedioDetalleId(model.getPlanMedioDetalleId());
      data.setFecha(model.getFecha());
      data.setFrecuencia(model.getFrecuencia());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en mapaComercial.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en mapaComercial.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteMapaComercial(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      MapaComercialEJB data = manager.find(MapaComercialEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en mapaComercial.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en mapaComercial.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.MapaComercialIf getMapaComercial(java.lang.Long id) {
      return (MapaComercialEJB)queryManagerLocal.find(MapaComercialEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMapaComercialList() {
	  return queryManagerLocal.singleClassList(MapaComercialEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMapaComercialList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(MapaComercialEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getMapaComercialListSize() {
      Query countQuery = manager.createQuery("select count(*) from MapaComercialEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMapaComercialById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(MapaComercialEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMapaComercialByPlanMedioDetalleId(java.lang.Long planMedioDetalleId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("planMedioDetalleId", planMedioDetalleId);
		return queryManagerLocal.singleClassQueryList(MapaComercialEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMapaComercialByFecha(java.sql.Date fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(MapaComercialEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMapaComercialByFrecuencia(java.lang.Integer frecuencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("frecuencia", frecuencia);
		return queryManagerLocal.singleClassQueryList(MapaComercialEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of MapaComercialIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMapaComercialByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(MapaComercialEJB.class, aMap);      
    }

/////////////////
}
