package com.spirit.seguridad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _RolSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.seguridad.entity.RolIf addRol(com.spirit.seguridad.entity.RolIf model) throws GenericBusinessException;

   void saveRol(com.spirit.seguridad.entity.RolIf model) throws GenericBusinessException;

   void deleteRol(java.lang.Long id) throws GenericBusinessException;

   Collection findRolByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.seguridad.entity.RolIf getRol(java.lang.Long id) throws GenericBusinessException;

   Collection getRolList() throws GenericBusinessException;

   Collection getRolList(int startIndex, int endIndex) throws GenericBusinessException;

   int getRolListSize() throws GenericBusinessException;
    java.util.Collection findRolById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findRolByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findRolByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findRolByStatus(java.lang.String status) throws GenericBusinessException;
    java.util.Collection findRolByTipoRolUsuario(java.lang.String tipoRolUsuario) throws GenericBusinessException;
    java.util.Collection findRolByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
