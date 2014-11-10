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
public abstract class _SriIvaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SriIvaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriIvaIf addSriIva(com.spirit.sri.entity.SriIvaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SriIvaEJB value = new SriIvaEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setPorcentaje(model.getPorcentaje());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en sriIva.", e);
			throw new GenericBusinessException(
					"Error al insertar información en sriIva.");
      }
     
      return getSriIva(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSriIva(com.spirit.sri.entity.SriIvaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SriIvaEJB data = new SriIvaEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setPorcentaje(model.getPorcentaje());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en sriIva.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en sriIva.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSriIva(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SriIvaEJB data = manager.find(SriIvaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en sriIva.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en sriIva.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriIvaIf getSriIva(java.lang.Long id) {
      return (SriIvaEJB)queryManagerLocal.find(SriIvaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriIvaList() {
	  return queryManagerLocal.singleClassList(SriIvaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriIvaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SriIvaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSriIvaListSize() {
      Query countQuery = manager.createQuery("select count(*) from SriIvaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SriIvaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaByCodigo(java.lang.Integer codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(SriIvaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaByPorcentaje(java.lang.Integer porcentaje) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentaje", porcentaje);
		return queryManagerLocal.singleClassQueryList(SriIvaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaByFechaInicio(java.sql.Date fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(SriIvaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaByFechaFin(java.sql.Date fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(SriIvaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SriIvaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SriIvaEJB.class, aMap);      
    }

/////////////////
}
