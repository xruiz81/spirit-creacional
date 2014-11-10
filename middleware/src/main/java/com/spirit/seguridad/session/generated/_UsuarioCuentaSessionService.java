package com.spirit.seguridad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _UsuarioCuentaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.seguridad.entity.UsuarioCuentaIf addUsuarioCuenta(com.spirit.seguridad.entity.UsuarioCuentaIf model) throws GenericBusinessException;

   void saveUsuarioCuenta(com.spirit.seguridad.entity.UsuarioCuentaIf model) throws GenericBusinessException;

   void deleteUsuarioCuenta(java.lang.Long id) throws GenericBusinessException;

   Collection findUsuarioCuentaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.seguridad.entity.UsuarioCuentaIf getUsuarioCuenta(java.lang.Long id) throws GenericBusinessException;

   Collection getUsuarioCuentaList() throws GenericBusinessException;

   Collection getUsuarioCuentaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getUsuarioCuentaListSize() throws GenericBusinessException;
    java.util.Collection findUsuarioCuentaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findUsuarioCuentaByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findUsuarioCuentaByCuentaId(java.lang.Long cuentaId) throws GenericBusinessException;

}
