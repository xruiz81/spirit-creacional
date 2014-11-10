package com.spirit.crm.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ClienteContactoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.crm.entity.ClienteContactoIf addClienteContacto(com.spirit.crm.entity.ClienteContactoIf model) throws GenericBusinessException;

   void saveClienteContacto(com.spirit.crm.entity.ClienteContactoIf model) throws GenericBusinessException;

   void deleteClienteContacto(java.lang.Long id) throws GenericBusinessException;

   Collection findClienteContactoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.crm.entity.ClienteContactoIf getClienteContacto(java.lang.Long id) throws GenericBusinessException;

   Collection getClienteContactoList() throws GenericBusinessException;

   Collection getClienteContactoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getClienteContactoListSize() throws GenericBusinessException;
    java.util.Collection findClienteContactoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findClienteContactoByTipocontactoId(java.lang.Long tipocontactoId) throws GenericBusinessException;
    java.util.Collection findClienteContactoByClienteoficinaId(java.lang.Long clienteoficinaId) throws GenericBusinessException;
    java.util.Collection findClienteContactoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findClienteContactoByDireccion(java.lang.String direccion) throws GenericBusinessException;
    java.util.Collection findClienteContactoByTelefonoOfic(java.lang.String telefonoOfic) throws GenericBusinessException;
    java.util.Collection findClienteContactoByTelefonoCasa(java.lang.String telefonoCasa) throws GenericBusinessException;
    java.util.Collection findClienteContactoByCelular(java.lang.String celular) throws GenericBusinessException;
    java.util.Collection findClienteContactoByMail(java.lang.String mail) throws GenericBusinessException;
    java.util.Collection findClienteContactoByCumpleanos(java.sql.Timestamp cumpleanos) throws GenericBusinessException;
    java.util.Collection findClienteContactoByCodigo(java.lang.String codigo) throws GenericBusinessException;

}
