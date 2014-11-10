package com.spirit.cartera.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CarteraRelacionadaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.cartera.entity.CarteraRelacionadaIf addCarteraRelacionada(com.spirit.cartera.entity.CarteraRelacionadaIf model) throws GenericBusinessException;

   void saveCarteraRelacionada(com.spirit.cartera.entity.CarteraRelacionadaIf model) throws GenericBusinessException;

   void deleteCarteraRelacionada(java.lang.Long id) throws GenericBusinessException;

   Collection findCarteraRelacionadaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.cartera.entity.CarteraRelacionadaIf getCarteraRelacionada(java.lang.Long id) throws GenericBusinessException;

   Collection getCarteraRelacionadaList() throws GenericBusinessException;

   Collection getCarteraRelacionadaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCarteraRelacionadaListSize() throws GenericBusinessException;
    java.util.Collection findCarteraRelacionadaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCarteraRelacionadaByCarteraOrigenId(java.lang.Long carteraOrigenId) throws GenericBusinessException;
    java.util.Collection findCarteraRelacionadaByCarteraRelacionadaId(java.lang.Long carteraRelacionadaId) throws GenericBusinessException;

}
