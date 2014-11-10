package com.spirit.sri.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.sri.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _ImpuestoRentaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ImpuestoRentaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.ImpuestoRentaIf addImpuestoRenta(com.spirit.sri.entity.ImpuestoRentaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ImpuestoRentaEJB value = new ImpuestoRentaEJB();
      try {
      value.setId(model.getId());
      value.setPorcentaje(model.getPorcentaje());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en impuestoRenta.", e);
			throw new GenericBusinessException(
					"Error al insertar información en impuestoRenta.");
      }
     
      return getImpuestoRenta(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveImpuestoRenta(com.spirit.sri.entity.ImpuestoRentaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ImpuestoRentaEJB data = new ImpuestoRentaEJB();
      data.setId(model.getId());
      data.setPorcentaje(model.getPorcentaje());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en impuestoRenta.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en impuestoRenta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteImpuestoRenta(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ImpuestoRentaEJB data = manager.find(ImpuestoRentaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en impuestoRenta.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en impuestoRenta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.ImpuestoRentaIf getImpuestoRenta(java.lang.Long id) {
      return (ImpuestoRentaEJB)queryManagerLocal.find(ImpuestoRentaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getImpuestoRentaList() {
	  return queryManagerLocal.singleClassList(ImpuestoRentaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getImpuestoRentaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ImpuestoRentaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getImpuestoRentaListSize() {
      Query countQuery = manager.createQuery("select count(*) from ImpuestoRentaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findImpuestoRentaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ImpuestoRentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findImpuestoRentaByPorcentaje(java.math.BigDecimal porcentaje) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentaje", porcentaje);
		return queryManagerLocal.singleClassQueryList(ImpuestoRentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findImpuestoRentaByFechaInicio(java.sql.Timestamp fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(ImpuestoRentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findImpuestoRentaByFechaFin(java.sql.Timestamp fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(ImpuestoRentaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ImpuestoRentaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findImpuestoRentaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ImpuestoRentaEJB.class, aMap);      
    }

/////////////////
}
