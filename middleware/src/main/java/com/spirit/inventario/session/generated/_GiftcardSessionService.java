package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _GiftcardSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.GiftcardIf addGiftcard(com.spirit.inventario.entity.GiftcardIf model) throws GenericBusinessException;

   void saveGiftcard(com.spirit.inventario.entity.GiftcardIf model) throws GenericBusinessException;

   void deleteGiftcard(java.lang.Long id) throws GenericBusinessException;

   Collection findGiftcardByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.GiftcardIf getGiftcard(java.lang.Long id) throws GenericBusinessException;

   Collection getGiftcardList() throws GenericBusinessException;

   Collection getGiftcardList(int startIndex, int endIndex) throws GenericBusinessException;

   int getGiftcardListSize() throws GenericBusinessException;
    java.util.Collection findGiftcardById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findGiftcardByProductoId(java.lang.Long productoId) throws GenericBusinessException;
    java.util.Collection findGiftcardByCodigoBarras(java.lang.String codigoBarras) throws GenericBusinessException;
    java.util.Collection findGiftcardByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findGiftcardBySaldo(java.math.BigDecimal saldo) throws GenericBusinessException;
    java.util.Collection findGiftcardByEstado(java.lang.String estado) throws GenericBusinessException;

}
