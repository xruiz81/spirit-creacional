package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _MonedaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.MonedaIf addMoneda(com.spirit.general.entity.MonedaIf model) throws GenericBusinessException;

   void saveMoneda(com.spirit.general.entity.MonedaIf model) throws GenericBusinessException;

   void deleteMoneda(java.lang.Long id) throws GenericBusinessException;

   Collection findMonedaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.MonedaIf getMoneda(java.lang.Long id) throws GenericBusinessException;

   Collection getMonedaList() throws GenericBusinessException;

   Collection getMonedaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getMonedaListSize() throws GenericBusinessException;
    java.util.Collection findMonedaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findMonedaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findMonedaByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findMonedaBySimbolo(java.lang.String simbolo) throws GenericBusinessException;
    java.util.Collection findMonedaByPlural(java.lang.String plural) throws GenericBusinessException;
    java.util.Collection findMonedaBySufijoCantidadLetras(java.lang.String sufijoCantidadLetras) throws GenericBusinessException;
    java.util.Collection findMonedaByPredeterminada(java.lang.String predeterminada) throws GenericBusinessException;

}
