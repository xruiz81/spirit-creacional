package com.spirit.crm.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ClienteIndicadorSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.crm.entity.ClienteIndicadorIf addClienteIndicador(com.spirit.crm.entity.ClienteIndicadorIf model) throws GenericBusinessException;

   void saveClienteIndicador(com.spirit.crm.entity.ClienteIndicadorIf model) throws GenericBusinessException;

   void deleteClienteIndicador(java.lang.Long id) throws GenericBusinessException;

   Collection findClienteIndicadorByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.crm.entity.ClienteIndicadorIf getClienteIndicador(java.lang.Long id) throws GenericBusinessException;

   Collection getClienteIndicadorList() throws GenericBusinessException;

   Collection getClienteIndicadorList(int startIndex, int endIndex) throws GenericBusinessException;

   int getClienteIndicadorListSize() throws GenericBusinessException;
    java.util.Collection findClienteIndicadorById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findClienteIndicadorByClienteOficinaId(java.lang.Long clienteOficinaId) throws GenericBusinessException;
    java.util.Collection findClienteIndicadorByTipoindicadorId(java.lang.Long tipoindicadorId) throws GenericBusinessException;
    java.util.Collection findClienteIndicadorByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findClienteIndicadorByCodigo(java.lang.String codigo) throws GenericBusinessException;

}
