package com.spirit.facturacion.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.facturacion.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _PrecioHistoricoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PrecioHistoricoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PrecioHistoricoIf addPrecioHistorico(com.spirit.facturacion.entity.PrecioHistoricoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PrecioHistoricoEJB value = new PrecioHistoricoEJB();
      try {
      value.setId(model.getId());
      value.setPrecioId(model.getPrecioId());
      value.setFechaini(model.getFechaini());
      value.setFechafin(model.getFechafin());
      value.setPrecioHis(model.getPrecioHis());
      value.setPrecio(model.getPrecio());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en precioHistorico.", e);
			throw new GenericBusinessException(
					"Error al insertar información en precioHistorico.");
      }
     
      return getPrecioHistorico(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePrecioHistorico(com.spirit.facturacion.entity.PrecioHistoricoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PrecioHistoricoEJB data = new PrecioHistoricoEJB();
      data.setId(model.getId());
      data.setPrecioId(model.getPrecioId());
      data.setFechaini(model.getFechaini());
      data.setFechafin(model.getFechafin());
      data.setPrecioHis(model.getPrecioHis());
      data.setPrecio(model.getPrecio());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en precioHistorico.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en precioHistorico.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePrecioHistorico(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PrecioHistoricoEJB data = manager.find(PrecioHistoricoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en precioHistorico.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en precioHistorico.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PrecioHistoricoIf getPrecioHistorico(java.lang.Long id) {
      return (PrecioHistoricoEJB)queryManagerLocal.find(PrecioHistoricoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPrecioHistoricoList() {
	  return queryManagerLocal.singleClassList(PrecioHistoricoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPrecioHistoricoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PrecioHistoricoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPrecioHistoricoListSize() {
      Query countQuery = manager.createQuery("select count(*) from PrecioHistoricoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrecioHistoricoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PrecioHistoricoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrecioHistoricoByPrecioId(java.lang.Long precioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("precioId", precioId);
		return queryManagerLocal.singleClassQueryList(PrecioHistoricoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrecioHistoricoByFechaini(java.sql.Date fechaini) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaini", fechaini);
		return queryManagerLocal.singleClassQueryList(PrecioHistoricoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrecioHistoricoByFechafin(java.sql.Date fechafin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechafin", fechafin);
		return queryManagerLocal.singleClassQueryList(PrecioHistoricoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrecioHistoricoByPrecioHis(java.math.BigDecimal precioHis) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("precioHis", precioHis);
		return queryManagerLocal.singleClassQueryList(PrecioHistoricoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrecioHistoricoByPrecio(java.math.BigDecimal precio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("precio", precio);
		return queryManagerLocal.singleClassQueryList(PrecioHistoricoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PrecioHistoricoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrecioHistoricoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PrecioHistoricoEJB.class, aMap);      
    }

/////////////////
}
