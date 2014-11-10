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
public abstract class _OrdenMedioDetalleMapaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_OrdenMedioDetalleMapaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.OrdenMedioDetalleMapaIf addOrdenMedioDetalleMapa(com.spirit.medios.entity.OrdenMedioDetalleMapaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      OrdenMedioDetalleMapaEJB value = new OrdenMedioDetalleMapaEJB();
      try {
      value.setId(model.getId());
      value.setOrdenMedioDetalleId(model.getOrdenMedioDetalleId());
      value.setFecha(model.getFecha());
      value.setFrecuencia(model.getFrecuencia());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en ordenMedioDetalleMapa.", e);
			throw new GenericBusinessException(
					"Error al insertar información en ordenMedioDetalleMapa.");
      }
     
      return getOrdenMedioDetalleMapa(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveOrdenMedioDetalleMapa(com.spirit.medios.entity.OrdenMedioDetalleMapaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      OrdenMedioDetalleMapaEJB data = new OrdenMedioDetalleMapaEJB();
      data.setId(model.getId());
      data.setOrdenMedioDetalleId(model.getOrdenMedioDetalleId());
      data.setFecha(model.getFecha());
      data.setFrecuencia(model.getFrecuencia());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en ordenMedioDetalleMapa.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en ordenMedioDetalleMapa.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteOrdenMedioDetalleMapa(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      OrdenMedioDetalleMapaEJB data = manager.find(OrdenMedioDetalleMapaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en ordenMedioDetalleMapa.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en ordenMedioDetalleMapa.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.OrdenMedioDetalleMapaIf getOrdenMedioDetalleMapa(java.lang.Long id) {
      return (OrdenMedioDetalleMapaEJB)queryManagerLocal.find(OrdenMedioDetalleMapaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenMedioDetalleMapaList() {
	  return queryManagerLocal.singleClassList(OrdenMedioDetalleMapaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenMedioDetalleMapaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(OrdenMedioDetalleMapaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getOrdenMedioDetalleMapaListSize() {
      Query countQuery = manager.createQuery("select count(*) from OrdenMedioDetalleMapaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleMapaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleMapaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleMapaByOrdenMedioDetalleId(java.lang.Long ordenMedioDetalleId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordenMedioDetalleId", ordenMedioDetalleId);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleMapaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleMapaByFecha(java.sql.Timestamp fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleMapaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleMapaByFrecuencia(java.lang.Integer frecuencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("frecuencia", frecuencia);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleMapaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of OrdenMedioDetalleMapaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleMapaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleMapaEJB.class, aMap);      
    }

/////////////////
}
