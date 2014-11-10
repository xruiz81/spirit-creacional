package com.spirit.cartera.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.cartera.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _CarteraRelacionadaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CarteraRelacionadaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.CarteraRelacionadaIf addCarteraRelacionada(com.spirit.cartera.entity.CarteraRelacionadaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CarteraRelacionadaEJB value = new CarteraRelacionadaEJB();
      try {
      value.setId(model.getId());
      value.setCarteraOrigenId(model.getCarteraOrigenId());
      value.setCarteraRelacionadaId(model.getCarteraRelacionadaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en carteraRelacionada.", e);
			throw new GenericBusinessException(
					"Error al insertar información en carteraRelacionada.");
      }
     
      return getCarteraRelacionada(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCarteraRelacionada(com.spirit.cartera.entity.CarteraRelacionadaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CarteraRelacionadaEJB data = new CarteraRelacionadaEJB();
      data.setId(model.getId());
      data.setCarteraOrigenId(model.getCarteraOrigenId());
      data.setCarteraRelacionadaId(model.getCarteraRelacionadaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en carteraRelacionada.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en carteraRelacionada.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCarteraRelacionada(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CarteraRelacionadaEJB data = manager.find(CarteraRelacionadaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en carteraRelacionada.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en carteraRelacionada.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.CarteraRelacionadaIf getCarteraRelacionada(java.lang.Long id) {
      return (CarteraRelacionadaEJB)queryManagerLocal.find(CarteraRelacionadaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCarteraRelacionadaList() {
	  return queryManagerLocal.singleClassList(CarteraRelacionadaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCarteraRelacionadaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CarteraRelacionadaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCarteraRelacionadaListSize() {
      Query countQuery = manager.createQuery("select count(*) from CarteraRelacionadaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraRelacionadaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CarteraRelacionadaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraRelacionadaByCarteraOrigenId(java.lang.Long carteraOrigenId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("carteraOrigenId", carteraOrigenId);
		return queryManagerLocal.singleClassQueryList(CarteraRelacionadaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraRelacionadaByCarteraRelacionadaId(java.lang.Long carteraRelacionadaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("carteraRelacionadaId", carteraRelacionadaId);
		return queryManagerLocal.singleClassQueryList(CarteraRelacionadaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CarteraRelacionadaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraRelacionadaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CarteraRelacionadaEJB.class, aMap);      
    }

/////////////////
}
