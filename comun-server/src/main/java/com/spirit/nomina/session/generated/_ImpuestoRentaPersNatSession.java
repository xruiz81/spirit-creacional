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
public abstract class _ImpuestoRentaPersNatSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ImpuestoRentaPersNatSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.ImpuestoRentaPersNatIf addImpuestoRentaPersNat(com.spirit.nomina.entity.ImpuestoRentaPersNatIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ImpuestoRentaPersNatEJB value = new ImpuestoRentaPersNatEJB();
      try {
      value.setId(model.getId());
      value.setFraccionBasica(model.getFraccionBasica());
      value.setExcesoHasta(model.getExcesoHasta());
      value.setImpFraccionBasica(model.getImpFraccionBasica());
      value.setPorcentajeImpFraccionBasica(model.getPorcentajeImpFraccionBasica());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en impuestoRentaPersNat.", e);
			throw new GenericBusinessException(
					"Error al insertar información en impuestoRentaPersNat.");
      }
     
      return getImpuestoRentaPersNat(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveImpuestoRentaPersNat(com.spirit.nomina.entity.ImpuestoRentaPersNatIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ImpuestoRentaPersNatEJB data = new ImpuestoRentaPersNatEJB();
      data.setId(model.getId());
      data.setFraccionBasica(model.getFraccionBasica());
      data.setExcesoHasta(model.getExcesoHasta());
      data.setImpFraccionBasica(model.getImpFraccionBasica());
      data.setPorcentajeImpFraccionBasica(model.getPorcentajeImpFraccionBasica());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en impuestoRentaPersNat.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en impuestoRentaPersNat.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteImpuestoRentaPersNat(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ImpuestoRentaPersNatEJB data = manager.find(ImpuestoRentaPersNatEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en impuestoRentaPersNat.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en impuestoRentaPersNat.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.ImpuestoRentaPersNatIf getImpuestoRentaPersNat(java.lang.Long id) {
      return (ImpuestoRentaPersNatEJB)queryManagerLocal.find(ImpuestoRentaPersNatEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getImpuestoRentaPersNatList() {
	  return queryManagerLocal.singleClassList(ImpuestoRentaPersNatEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getImpuestoRentaPersNatList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ImpuestoRentaPersNatEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getImpuestoRentaPersNatListSize() {
      Query countQuery = manager.createQuery("select count(*) from ImpuestoRentaPersNatEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findImpuestoRentaPersNatById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ImpuestoRentaPersNatEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findImpuestoRentaPersNatByFraccionBasica(java.math.BigDecimal fraccionBasica) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fraccionBasica", fraccionBasica);
		return queryManagerLocal.singleClassQueryList(ImpuestoRentaPersNatEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findImpuestoRentaPersNatByExcesoHasta(java.math.BigDecimal excesoHasta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("excesoHasta", excesoHasta);
		return queryManagerLocal.singleClassQueryList(ImpuestoRentaPersNatEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findImpuestoRentaPersNatByImpFraccionBasica(java.math.BigDecimal impFraccionBasica) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("impFraccionBasica", impFraccionBasica);
		return queryManagerLocal.singleClassQueryList(ImpuestoRentaPersNatEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findImpuestoRentaPersNatByPorcentajeImpFraccionBasica(java.math.BigDecimal porcentajeImpFraccionBasica) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeImpFraccionBasica", porcentajeImpFraccionBasica);
		return queryManagerLocal.singleClassQueryList(ImpuestoRentaPersNatEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findImpuestoRentaPersNatByFechaInicio(java.sql.Date fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(ImpuestoRentaPersNatEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findImpuestoRentaPersNatByFechaFin(java.sql.Date fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(ImpuestoRentaPersNatEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ImpuestoRentaPersNatIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findImpuestoRentaPersNatByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ImpuestoRentaPersNatEJB.class, aMap);      
    }

/////////////////
}
