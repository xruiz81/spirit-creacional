package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _AsientoDescuadradoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.AsientoDescuadradoIf addAsientoDescuadrado(com.spirit.contabilidad.entity.AsientoDescuadradoIf model) throws GenericBusinessException;

   void saveAsientoDescuadrado(com.spirit.contabilidad.entity.AsientoDescuadradoIf model) throws GenericBusinessException;

   void deleteAsientoDescuadrado(java.lang.Long id) throws GenericBusinessException;

   Collection findAsientoDescuadradoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.AsientoDescuadradoIf getAsientoDescuadrado(java.lang.Long id) throws GenericBusinessException;

   Collection getAsientoDescuadradoList() throws GenericBusinessException;

   Collection getAsientoDescuadradoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getAsientoDescuadradoListSize() throws GenericBusinessException;
    java.util.Collection findAsientoDescuadradoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findAsientoDescuadradoByAsientoNumero(java.lang.String asientoNumero) throws GenericBusinessException;

}
