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
public abstract class _CampanaDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CampanaDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.CampanaDetalleIf addCampanaDetalle(com.spirit.medios.entity.CampanaDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CampanaDetalleEJB value = new CampanaDetalleEJB();
      try {
      value.setId(model.getId());
      value.setClienteZonaId(model.getClienteZonaId());
      value.setCampanaId(model.getCampanaId());
      value.setParticipacion(model.getParticipacion());
      value.setDescripcion(model.getDescripcion());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en campanaDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en campanaDetalle.");
      }
     
      return getCampanaDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCampanaDetalle(com.spirit.medios.entity.CampanaDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CampanaDetalleEJB data = new CampanaDetalleEJB();
      data.setId(model.getId());
      data.setClienteZonaId(model.getClienteZonaId());
      data.setCampanaId(model.getCampanaId());
      data.setParticipacion(model.getParticipacion());
      data.setDescripcion(model.getDescripcion());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en campanaDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en campanaDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCampanaDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CampanaDetalleEJB data = manager.find(CampanaDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en campanaDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en campanaDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.CampanaDetalleIf getCampanaDetalle(java.lang.Long id) {
      return (CampanaDetalleEJB)queryManagerLocal.find(CampanaDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCampanaDetalleList() {
	  return queryManagerLocal.singleClassList(CampanaDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCampanaDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CampanaDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCampanaDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from CampanaDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CampanaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaDetalleByClienteZonaId(java.lang.Long clienteZonaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteZonaId", clienteZonaId);
		return queryManagerLocal.singleClassQueryList(CampanaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaDetalleByCampanaId(java.lang.Long campanaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("campanaId", campanaId);
		return queryManagerLocal.singleClassQueryList(CampanaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaDetalleByParticipacion(java.math.BigDecimal participacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("participacion", participacion);
		return queryManagerLocal.singleClassQueryList(CampanaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaDetalleByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(CampanaDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CampanaDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCampanaDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CampanaDetalleEJB.class, aMap);      
    }

/////////////////
}
