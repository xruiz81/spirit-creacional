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
public abstract class _OverComisionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_OverComisionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.OverComisionIf addOverComision(com.spirit.medios.entity.OverComisionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      OverComisionEJB value = new OverComisionEJB();
      try {
      value.setId(model.getId());
      value.setProveedorOficinaId(model.getProveedorOficinaId());
      value.setAnio(model.getAnio());
      value.setInversionDe(model.getInversionDe());
      value.setInversionA(model.getInversionA());
      value.setPorcentajeOver(model.getPorcentajeOver());
      value.setObjetivo(model.getObjetivo());
      value.setProveedorId(model.getProveedorId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en overComision.", e);
			throw new GenericBusinessException(
					"Error al insertar información en overComision.");
      }
     
      return getOverComision(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveOverComision(com.spirit.medios.entity.OverComisionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      OverComisionEJB data = new OverComisionEJB();
      data.setId(model.getId());
      data.setProveedorOficinaId(model.getProveedorOficinaId());
      data.setAnio(model.getAnio());
      data.setInversionDe(model.getInversionDe());
      data.setInversionA(model.getInversionA());
      data.setPorcentajeOver(model.getPorcentajeOver());
      data.setObjetivo(model.getObjetivo());
      data.setProveedorId(model.getProveedorId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en overComision.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en overComision.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteOverComision(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      OverComisionEJB data = manager.find(OverComisionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en overComision.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en overComision.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.OverComisionIf getOverComision(java.lang.Long id) {
      return (OverComisionEJB)queryManagerLocal.find(OverComisionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOverComisionList() {
	  return queryManagerLocal.singleClassList(OverComisionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOverComisionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(OverComisionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getOverComisionListSize() {
      Query countQuery = manager.createQuery("select count(*) from OverComisionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOverComisionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(OverComisionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOverComisionByProveedorOficinaId(java.lang.Long proveedorOficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("proveedorOficinaId", proveedorOficinaId);
		return queryManagerLocal.singleClassQueryList(OverComisionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOverComisionByAnio(java.sql.Timestamp anio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("anio", anio);
		return queryManagerLocal.singleClassQueryList(OverComisionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOverComisionByInversionDe(java.math.BigDecimal inversionDe) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("inversionDe", inversionDe);
		return queryManagerLocal.singleClassQueryList(OverComisionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOverComisionByInversionA(java.math.BigDecimal inversionA) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("inversionA", inversionA);
		return queryManagerLocal.singleClassQueryList(OverComisionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOverComisionByPorcentajeOver(java.math.BigDecimal porcentajeOver) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeOver", porcentajeOver);
		return queryManagerLocal.singleClassQueryList(OverComisionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOverComisionByObjetivo(java.lang.String objetivo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("objetivo", objetivo);
		return queryManagerLocal.singleClassQueryList(OverComisionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOverComisionByProveedorId(java.lang.Long proveedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("proveedorId", proveedorId);
		return queryManagerLocal.singleClassQueryList(OverComisionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of OverComisionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOverComisionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(OverComisionEJB.class, aMap);      
    }

/////////////////
}
