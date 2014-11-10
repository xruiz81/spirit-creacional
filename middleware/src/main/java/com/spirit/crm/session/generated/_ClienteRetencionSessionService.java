package com.spirit.crm.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ClienteRetencionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.crm.entity.ClienteRetencionIf addClienteRetencion(com.spirit.crm.entity.ClienteRetencionIf model) throws GenericBusinessException;

   void saveClienteRetencion(com.spirit.crm.entity.ClienteRetencionIf model) throws GenericBusinessException;

   void deleteClienteRetencion(java.lang.Long id) throws GenericBusinessException;

   Collection findClienteRetencionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.crm.entity.ClienteRetencionIf getClienteRetencion(java.lang.Long id) throws GenericBusinessException;

   Collection getClienteRetencionList() throws GenericBusinessException;

   Collection getClienteRetencionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getClienteRetencionListSize() throws GenericBusinessException;
    java.util.Collection findClienteRetencionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findClienteRetencionByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findClienteRetencionBySriAirId(java.lang.Long sriAirId) throws GenericBusinessException;
    java.util.Collection findClienteRetencionBySriIvaRetencionId(java.lang.Long sriIvaRetencionId) throws GenericBusinessException;

}
