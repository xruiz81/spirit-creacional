package com.spirit.crm.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ClienteZonaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.crm.entity.ClienteZonaIf addClienteZona(com.spirit.crm.entity.ClienteZonaIf model) throws GenericBusinessException;

   void saveClienteZona(com.spirit.crm.entity.ClienteZonaIf model) throws GenericBusinessException;

   void deleteClienteZona(java.lang.Long id) throws GenericBusinessException;

   Collection findClienteZonaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.crm.entity.ClienteZonaIf getClienteZona(java.lang.Long id) throws GenericBusinessException;

   Collection getClienteZonaList() throws GenericBusinessException;

   Collection getClienteZonaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getClienteZonaListSize() throws GenericBusinessException;
    java.util.Collection findClienteZonaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findClienteZonaByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findClienteZonaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findClienteZonaByNombre(java.lang.String nombre) throws GenericBusinessException;

}
