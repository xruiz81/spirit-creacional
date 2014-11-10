package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _GiftcardMovimientoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.GiftcardMovimientoIf addGiftcardMovimiento(com.spirit.inventario.entity.GiftcardMovimientoIf model) throws GenericBusinessException;

   void saveGiftcardMovimiento(com.spirit.inventario.entity.GiftcardMovimientoIf model) throws GenericBusinessException;

   void deleteGiftcardMovimiento(java.lang.Long id) throws GenericBusinessException;

   Collection findGiftcardMovimientoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.GiftcardMovimientoIf getGiftcardMovimiento(java.lang.Long id) throws GenericBusinessException;

   Collection getGiftcardMovimientoList() throws GenericBusinessException;

   Collection getGiftcardMovimientoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getGiftcardMovimientoListSize() throws GenericBusinessException;
    java.util.Collection findGiftcardMovimientoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findGiftcardMovimientoByGiftcardId(java.lang.Long giftcardId) throws GenericBusinessException;
    java.util.Collection findGiftcardMovimientoBySaldoAnterior(java.math.BigDecimal saldoAnterior) throws GenericBusinessException;
    java.util.Collection findGiftcardMovimientoByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findGiftcardMovimientoByFechaMovimiento(java.sql.Timestamp fechaMovimiento) throws GenericBusinessException;
    java.util.Collection findGiftcardMovimientoByTransaccionId(java.lang.Long transaccionId) throws GenericBusinessException;
    java.util.Collection findGiftcardMovimientoByTipoDocumentoId(java.lang.Long tipoDocumentoId) throws GenericBusinessException;

}
