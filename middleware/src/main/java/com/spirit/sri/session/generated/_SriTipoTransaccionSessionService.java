package com.spirit.sri.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SriTipoTransaccionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.sri.entity.SriTipoTransaccionIf addSriTipoTransaccion(com.spirit.sri.entity.SriTipoTransaccionIf model) throws GenericBusinessException;

   void saveSriTipoTransaccion(com.spirit.sri.entity.SriTipoTransaccionIf model) throws GenericBusinessException;

   void deleteSriTipoTransaccion(java.lang.Long id) throws GenericBusinessException;

   Collection findSriTipoTransaccionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.sri.entity.SriTipoTransaccionIf getSriTipoTransaccion(java.lang.Long id) throws GenericBusinessException;

   Collection getSriTipoTransaccionList() throws GenericBusinessException;

   Collection getSriTipoTransaccionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSriTipoTransaccionListSize() throws GenericBusinessException;
    java.util.Collection findSriTipoTransaccionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSriTipoTransaccionByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findSriTipoTransaccionByNombre(java.lang.String nombre) throws GenericBusinessException;

}
