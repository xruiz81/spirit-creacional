package com.spirit.seguridad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _RolUsuarioSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.seguridad.entity.RolUsuarioIf addRolUsuario(com.spirit.seguridad.entity.RolUsuarioIf model) throws GenericBusinessException;

   void saveRolUsuario(com.spirit.seguridad.entity.RolUsuarioIf model) throws GenericBusinessException;

   void deleteRolUsuario(java.lang.Long id) throws GenericBusinessException;

   Collection findRolUsuarioByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.seguridad.entity.RolUsuarioIf getRolUsuario(java.lang.Long id) throws GenericBusinessException;

   Collection getRolUsuarioList() throws GenericBusinessException;

   Collection getRolUsuarioList(int startIndex, int endIndex) throws GenericBusinessException;

   int getRolUsuarioListSize() throws GenericBusinessException;
    java.util.Collection findRolUsuarioById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findRolUsuarioByRolId(java.lang.Long rolId) throws GenericBusinessException;
    java.util.Collection findRolUsuarioByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;

}
