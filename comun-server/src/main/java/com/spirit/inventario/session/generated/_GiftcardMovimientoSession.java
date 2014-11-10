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
public abstract class _GiftcardMovimientoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_GiftcardMovimientoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.GiftcardMovimientoIf addGiftcardMovimiento(com.spirit.inventario.entity.GiftcardMovimientoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      GiftcardMovimientoEJB value = new GiftcardMovimientoEJB();
      try {
      value.setId(model.getId());
      value.setGiftcardId(model.getGiftcardId());
      value.setSaldoAnterior(model.getSaldoAnterior());
      value.setValor(model.getValor());
      value.setFechaMovimiento(model.getFechaMovimiento());
      value.setTransaccionId(model.getTransaccionId());
      value.setTipoDocumentoId(model.getTipoDocumentoId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en giftcardMovimiento.", e);
			throw new GenericBusinessException(
					"Error al insertar información en giftcardMovimiento.");
      }
     
      return getGiftcardMovimiento(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveGiftcardMovimiento(com.spirit.inventario.entity.GiftcardMovimientoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      GiftcardMovimientoEJB data = new GiftcardMovimientoEJB();
      data.setId(model.getId());
      data.setGiftcardId(model.getGiftcardId());
      data.setSaldoAnterior(model.getSaldoAnterior());
      data.setValor(model.getValor());
      data.setFechaMovimiento(model.getFechaMovimiento());
      data.setTransaccionId(model.getTransaccionId());
      data.setTipoDocumentoId(model.getTipoDocumentoId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en giftcardMovimiento.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en giftcardMovimiento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteGiftcardMovimiento(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      GiftcardMovimientoEJB data = manager.find(GiftcardMovimientoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en giftcardMovimiento.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en giftcardMovimiento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.GiftcardMovimientoIf getGiftcardMovimiento(java.lang.Long id) {
      return (GiftcardMovimientoEJB)queryManagerLocal.find(GiftcardMovimientoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getGiftcardMovimientoList() {
	  return queryManagerLocal.singleClassList(GiftcardMovimientoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getGiftcardMovimientoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(GiftcardMovimientoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getGiftcardMovimientoListSize() {
      Query countQuery = manager.createQuery("select count(*) from GiftcardMovimientoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGiftcardMovimientoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(GiftcardMovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGiftcardMovimientoByGiftcardId(java.lang.Long giftcardId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("giftcardId", giftcardId);
		return queryManagerLocal.singleClassQueryList(GiftcardMovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGiftcardMovimientoBySaldoAnterior(java.math.BigDecimal saldoAnterior) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("saldoAnterior", saldoAnterior);
		return queryManagerLocal.singleClassQueryList(GiftcardMovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGiftcardMovimientoByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(GiftcardMovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGiftcardMovimientoByFechaMovimiento(java.sql.Timestamp fechaMovimiento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaMovimiento", fechaMovimiento);
		return queryManagerLocal.singleClassQueryList(GiftcardMovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGiftcardMovimientoByTransaccionId(java.lang.Long transaccionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transaccionId", transaccionId);
		return queryManagerLocal.singleClassQueryList(GiftcardMovimientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGiftcardMovimientoByTipoDocumentoId(java.lang.Long tipoDocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoDocumentoId", tipoDocumentoId);
		return queryManagerLocal.singleClassQueryList(GiftcardMovimientoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of GiftcardMovimientoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGiftcardMovimientoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(GiftcardMovimientoEJB.class, aMap);      
    }

/////////////////
}
