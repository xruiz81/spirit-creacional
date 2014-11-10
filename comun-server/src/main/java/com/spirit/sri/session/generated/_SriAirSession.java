package com.spirit.sri.session.generated;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.sri.entity.SriAirEJB;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _SriAirSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SriAirSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriAirIf addSriAir(com.spirit.sri.entity.SriAirIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SriAirEJB value = new SriAirEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setConcepto(model.getConcepto());
      value.setPorcentaje(model.getPorcentaje());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en sriAir.", e);
			throw new GenericBusinessException(
					"Error al insertar información en sriAir.");
      }
     
      return getSriAir(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSriAir(com.spirit.sri.entity.SriAirIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SriAirEJB data = new SriAirEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setConcepto(model.getConcepto());
      data.setPorcentaje(model.getPorcentaje());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en sriAir.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en sriAir.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSriAir(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SriAirEJB data = manager.find(SriAirEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en sriAir.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en sriAir.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriAirIf getSriAir(java.lang.Long id) {
      return (SriAirEJB)queryManagerLocal.find(SriAirEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriAirList() {
	  return queryManagerLocal.singleClassList(SriAirEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriAirList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SriAirEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSriAirListSize() {
      Query countQuery = manager.createQuery("select count(*) from SriAirEJB");
      List countQueryResult = countQuery.getResultList();
      Long countResult = (Long)countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriAirById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SriAirEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriAirByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(SriAirEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriAirByConcepto(java.lang.String concepto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("concepto", concepto);
		return queryManagerLocal.singleClassQueryList(SriAirEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriAirByPorcentaje(java.math.BigDecimal porcentaje) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentaje", porcentaje);
		return queryManagerLocal.singleClassQueryList(SriAirEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriAirByFechaInicio(java.sql.Date fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(SriAirEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriAirByFechaFin(java.sql.Date fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(SriAirEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SriAirIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriAirByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SriAirEJB.class, aMap);      
    }

/////////////////
}
