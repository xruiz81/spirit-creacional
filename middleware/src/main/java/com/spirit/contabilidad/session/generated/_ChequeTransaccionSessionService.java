package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ChequeTransaccionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.ChequeTransaccionIf addChequeTransaccion(com.spirit.contabilidad.entity.ChequeTransaccionIf model) throws GenericBusinessException;

   void saveChequeTransaccion(com.spirit.contabilidad.entity.ChequeTransaccionIf model) throws GenericBusinessException;

   void deleteChequeTransaccion(java.lang.Long id) throws GenericBusinessException;

   Collection findChequeTransaccionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.ChequeTransaccionIf getChequeTransaccion(java.lang.Long id) throws GenericBusinessException;

   Collection getChequeTransaccionList() throws GenericBusinessException;

   Collection getChequeTransaccionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getChequeTransaccionListSize() throws GenericBusinessException;
    java.util.Collection findChequeTransaccionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findChequeTransaccionByChequeEmitidoId(java.lang.Long chequeEmitidoId) throws GenericBusinessException;
    java.util.Collection findChequeTransaccionByTransaccionId(java.lang.Long transaccionId) throws GenericBusinessException;
    java.util.Collection findChequeTransaccionByOrigen(java.lang.String origen) throws GenericBusinessException;

}
