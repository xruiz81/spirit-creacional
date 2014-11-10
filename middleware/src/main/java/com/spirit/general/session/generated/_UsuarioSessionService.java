package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _UsuarioSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.UsuarioIf addUsuario(com.spirit.general.entity.UsuarioIf model) throws GenericBusinessException;

   void saveUsuario(com.spirit.general.entity.UsuarioIf model) throws GenericBusinessException;

   void deleteUsuario(java.lang.Long id) throws GenericBusinessException;

   Collection findUsuarioByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.UsuarioIf getUsuario(java.lang.Long id) throws GenericBusinessException;

   Collection getUsuarioList() throws GenericBusinessException;

   Collection getUsuarioList(int startIndex, int endIndex) throws GenericBusinessException;

   int getUsuarioListSize() throws GenericBusinessException;
    java.util.Collection findUsuarioById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findUsuarioByUsuario(java.lang.String usuario) throws GenericBusinessException;
    java.util.Collection findUsuarioByClave(java.lang.String clave) throws GenericBusinessException;
    java.util.Collection findUsuarioByTipousuario(java.lang.String tipousuario) throws GenericBusinessException;
    java.util.Collection findUsuarioByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findUsuarioByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findUsuarioByTipousuarioTimetracker(java.lang.String tipousuarioTimetracker) throws GenericBusinessException;

}
