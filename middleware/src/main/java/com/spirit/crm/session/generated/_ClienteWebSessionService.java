package com.spirit.crm.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ClienteWebSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.crm.entity.ClienteWebIf addClienteWeb(com.spirit.crm.entity.ClienteWebIf model) throws GenericBusinessException;

   void saveClienteWeb(com.spirit.crm.entity.ClienteWebIf model) throws GenericBusinessException;

   void deleteClienteWeb(java.lang.Long id) throws GenericBusinessException;

   Collection findClienteWebByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.crm.entity.ClienteWebIf getClienteWeb(java.lang.Long id) throws GenericBusinessException;

   Collection getClienteWebList() throws GenericBusinessException;

   Collection getClienteWebList(int startIndex, int endIndex) throws GenericBusinessException;

   int getClienteWebListSize() throws GenericBusinessException;
    java.util.Collection findClienteWebById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findClienteWebByIdExterno(java.lang.String idExterno) throws GenericBusinessException;
    java.util.Collection findClienteWebByNombres(java.lang.String nombres) throws GenericBusinessException;
    java.util.Collection findClienteWebByApellidos(java.lang.String apellidos) throws GenericBusinessException;
    java.util.Collection findClienteWebByEmail(java.lang.String email) throws GenericBusinessException;
    java.util.Collection findClienteWebByPais(java.lang.String pais) throws GenericBusinessException;
    java.util.Collection findClienteWebByCiudad(java.lang.String ciudad) throws GenericBusinessException;
    java.util.Collection findClienteWebByDireccion(java.lang.String direccion) throws GenericBusinessException;
    java.util.Collection findClienteWebByTelefono(java.lang.String telefono) throws GenericBusinessException;
    java.util.Collection findClienteWebByCelular(java.lang.String celular) throws GenericBusinessException;

}
