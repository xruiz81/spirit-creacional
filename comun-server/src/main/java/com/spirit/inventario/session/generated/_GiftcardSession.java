package com.spirit.inventario.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.inventario.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _GiftcardSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_GiftcardSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.GiftcardIf addGiftcard(com.spirit.inventario.entity.GiftcardIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      GiftcardEJB value = new GiftcardEJB();
      try {
      value.setId(model.getId());
      value.setProductoId(model.getProductoId());
      value.setCodigoBarras(model.getCodigoBarras());
      value.setValor(model.getValor());
      value.setSaldo(model.getSaldo());
      value.setEstado(model.getEstado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en giftcard.", e);
			throw new GenericBusinessException(
					"Error al insertar información en giftcard.");
      }
     
      return getGiftcard(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveGiftcard(com.spirit.inventario.entity.GiftcardIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      GiftcardEJB data = new GiftcardEJB();
      data.setId(model.getId());
      data.setProductoId(model.getProductoId());
      data.setCodigoBarras(model.getCodigoBarras());
      data.setValor(model.getValor());
      data.setSaldo(model.getSaldo());
      data.setEstado(model.getEstado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en giftcard.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en giftcard.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteGiftcard(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      GiftcardEJB data = manager.find(GiftcardEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en giftcard.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en giftcard.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.GiftcardIf getGiftcard(java.lang.Long id) {
      return (GiftcardEJB)queryManagerLocal.find(GiftcardEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getGiftcardList() {
	  return queryManagerLocal.singleClassList(GiftcardEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getGiftcardList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(GiftcardEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getGiftcardListSize() {
      Query countQuery = manager.createQuery("select count(*) from GiftcardEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGiftcardById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(GiftcardEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGiftcardByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(GiftcardEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGiftcardByCodigoBarras(java.lang.String codigoBarras) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigoBarras", codigoBarras);
		return queryManagerLocal.singleClassQueryList(GiftcardEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGiftcardByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(GiftcardEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGiftcardBySaldo(java.math.BigDecimal saldo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("saldo", saldo);
		return queryManagerLocal.singleClassQueryList(GiftcardEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGiftcardByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(GiftcardEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of GiftcardIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGiftcardByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(GiftcardEJB.class, aMap);      
    }

/////////////////
}
