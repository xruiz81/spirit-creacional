package com.spirit.nomina.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.nomina.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _RolPagoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_RolPagoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.RolPagoIf addRolPago(com.spirit.nomina.entity.RolPagoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      RolPagoEJB value = new RolPagoEJB();
      try {
      value.setId(model.getId());
      value.setTiporolId(model.getTiporolId());
      value.setEstado(model.getEstado());
      value.setMes(model.getMes());
      value.setAnio(model.getAnio());
      value.setFecha(model.getFecha());
      value.setTipocontratoId(model.getTipocontratoId());
      value.setAsientoGenerado(model.getAsientoGenerado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en rolPago.", e);
			throw new GenericBusinessException(
					"Error al insertar información en rolPago.");
      }
     
      return getRolPago(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveRolPago(com.spirit.nomina.entity.RolPagoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      RolPagoEJB data = new RolPagoEJB();
      data.setId(model.getId());
      data.setTiporolId(model.getTiporolId());
      data.setEstado(model.getEstado());
      data.setMes(model.getMes());
      data.setAnio(model.getAnio());
      data.setFecha(model.getFecha());
      data.setTipocontratoId(model.getTipocontratoId());
      data.setAsientoGenerado(model.getAsientoGenerado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en rolPago.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en rolPago.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteRolPago(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      RolPagoEJB data = manager.find(RolPagoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en rolPago.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en rolPago.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.RolPagoIf getRolPago(java.lang.Long id) {
      return (RolPagoEJB)queryManagerLocal.find(RolPagoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getRolPagoList() {
	  return queryManagerLocal.singleClassList(RolPagoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getRolPagoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(RolPagoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getRolPagoListSize() {
      Query countQuery = manager.createQuery("select count(*) from RolPagoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(RolPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoByTiporolId(java.lang.Long tiporolId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tiporolId", tiporolId);
		return queryManagerLocal.singleClassQueryList(RolPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(RolPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoByMes(java.lang.String mes) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("mes", mes);
		return queryManagerLocal.singleClassQueryList(RolPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoByAnio(java.lang.String anio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("anio", anio);
		return queryManagerLocal.singleClassQueryList(RolPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoByFecha(java.sql.Date fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(RolPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoByTipocontratoId(java.lang.Long tipocontratoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipocontratoId", tipocontratoId);
		return queryManagerLocal.singleClassQueryList(RolPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoByAsientoGenerado(java.lang.String asientoGenerado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("asientoGenerado", asientoGenerado);
		return queryManagerLocal.singleClassQueryList(RolPagoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of RolPagoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(RolPagoEJB.class, aMap);      
    }

/////////////////
}
