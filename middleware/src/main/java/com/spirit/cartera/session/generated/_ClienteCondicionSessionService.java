package com.spirit.cartera.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ClienteCondicionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.cartera.entity.ClienteCondicionIf addClienteCondicion(com.spirit.cartera.entity.ClienteCondicionIf model) throws GenericBusinessException;

   void saveClienteCondicion(com.spirit.cartera.entity.ClienteCondicionIf model) throws GenericBusinessException;

   void deleteClienteCondicion(java.lang.Long id) throws GenericBusinessException;

   Collection findClienteCondicionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.cartera.entity.ClienteCondicionIf getClienteCondicion(java.lang.Long id) throws GenericBusinessException;

   Collection getClienteCondicionList() throws GenericBusinessException;

   Collection getClienteCondicionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getClienteCondicionListSize() throws GenericBusinessException;
    java.util.Collection findClienteCondicionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findClienteCondicionByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findClienteCondicionBySubtipoordenId(java.lang.Long subtipoordenId) throws GenericBusinessException;
    java.util.Collection findClienteCondicionByFormapagoId(java.lang.Long formapagoId) throws GenericBusinessException;
    java.util.Collection findClienteCondicionByObservaciones(java.lang.String observaciones) throws GenericBusinessException;
    java.util.Collection findClienteCondicionByFechaini(java.sql.Date fechaini) throws GenericBusinessException;
    java.util.Collection findClienteCondicionByFechafin(java.sql.Date fechafin) throws GenericBusinessException;
    java.util.Collection findClienteCondicionByCodigo(java.lang.String codigo) throws GenericBusinessException;

}
