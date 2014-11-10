package com.spirit.pos.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.pos.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _DonacionTipoproductoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_DonacionTipoproductoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.DonacionTipoproductoIf addDonacionTipoproducto(com.spirit.pos.entity.DonacionTipoproductoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      DonacionTipoproductoEJB value = new DonacionTipoproductoEJB();
      try {
      value.setId(model.getId());
      value.setTipoproductoId(model.getTipoproductoId());
      value.setValor(model.getValor());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en donacionTipoproducto.", e);
			throw new GenericBusinessException(
					"Error al insertar información en donacionTipoproducto.");
      }
     
      return getDonacionTipoproducto(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveDonacionTipoproducto(com.spirit.pos.entity.DonacionTipoproductoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      DonacionTipoproductoEJB data = new DonacionTipoproductoEJB();
      data.setId(model.getId());
      data.setTipoproductoId(model.getTipoproductoId());
      data.setValor(model.getValor());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en donacionTipoproducto.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en donacionTipoproducto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteDonacionTipoproducto(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      DonacionTipoproductoEJB data = manager.find(DonacionTipoproductoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en donacionTipoproducto.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en donacionTipoproducto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.DonacionTipoproductoIf getDonacionTipoproducto(java.lang.Long id) {
      return (DonacionTipoproductoEJB)queryManagerLocal.find(DonacionTipoproductoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getDonacionTipoproductoList() {
	  return queryManagerLocal.singleClassList(DonacionTipoproductoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getDonacionTipoproductoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(DonacionTipoproductoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getDonacionTipoproductoListSize() {
      Query countQuery = manager.createQuery("select count(*) from DonacionTipoproductoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonacionTipoproductoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(DonacionTipoproductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonacionTipoproductoByTipoproductoId(java.lang.Long tipoproductoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoproductoId", tipoproductoId);
		return queryManagerLocal.singleClassQueryList(DonacionTipoproductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonacionTipoproductoByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(DonacionTipoproductoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of DonacionTipoproductoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonacionTipoproductoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(DonacionTipoproductoEJB.class, aMap);      
    }

/////////////////
}
