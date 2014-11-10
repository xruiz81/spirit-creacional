package com.spirit.pos.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _DonacionTipoproductoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.pos.entity.DonacionTipoproductoIf addDonacionTipoproducto(com.spirit.pos.entity.DonacionTipoproductoIf model) throws GenericBusinessException;

   void saveDonacionTipoproducto(com.spirit.pos.entity.DonacionTipoproductoIf model) throws GenericBusinessException;

   void deleteDonacionTipoproducto(java.lang.Long id) throws GenericBusinessException;

   Collection findDonacionTipoproductoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.pos.entity.DonacionTipoproductoIf getDonacionTipoproducto(java.lang.Long id) throws GenericBusinessException;

   Collection getDonacionTipoproductoList() throws GenericBusinessException;

   Collection getDonacionTipoproductoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getDonacionTipoproductoListSize() throws GenericBusinessException;
    java.util.Collection findDonacionTipoproductoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findDonacionTipoproductoByTipoproductoId(java.lang.Long tipoproductoId) throws GenericBusinessException;
    java.util.Collection findDonacionTipoproductoByValor(java.math.BigDecimal valor) throws GenericBusinessException;

}
