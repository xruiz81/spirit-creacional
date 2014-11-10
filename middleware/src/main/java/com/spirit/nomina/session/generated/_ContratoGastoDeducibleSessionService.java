package com.spirit.nomina.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ContratoGastoDeducibleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.nomina.entity.ContratoGastoDeducibleIf addContratoGastoDeducible(com.spirit.nomina.entity.ContratoGastoDeducibleIf model) throws GenericBusinessException;

   void saveContratoGastoDeducible(com.spirit.nomina.entity.ContratoGastoDeducibleIf model) throws GenericBusinessException;

   void deleteContratoGastoDeducible(java.lang.Long id) throws GenericBusinessException;

   Collection findContratoGastoDeducibleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.nomina.entity.ContratoGastoDeducibleIf getContratoGastoDeducible(java.lang.Long id) throws GenericBusinessException;

   Collection getContratoGastoDeducibleList() throws GenericBusinessException;

   Collection getContratoGastoDeducibleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getContratoGastoDeducibleListSize() throws GenericBusinessException;
    java.util.Collection findContratoGastoDeducibleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findContratoGastoDeducibleByContratoId(java.lang.Long contratoId) throws GenericBusinessException;
    java.util.Collection findContratoGastoDeducibleByGastoDeducibleId(java.lang.Long gastoDeducibleId) throws GenericBusinessException;
    java.util.Collection findContratoGastoDeducibleByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findContratoGastoDeducibleByFecha(java.sql.Date fecha) throws GenericBusinessException;

}
