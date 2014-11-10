package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _UsuarioNoticiasSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.UsuarioNoticiasIf addUsuarioNoticias(com.spirit.general.entity.UsuarioNoticiasIf model) throws GenericBusinessException;

   void saveUsuarioNoticias(com.spirit.general.entity.UsuarioNoticiasIf model) throws GenericBusinessException;

   void deleteUsuarioNoticias(java.lang.Long id) throws GenericBusinessException;

   Collection findUsuarioNoticiasByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.UsuarioNoticiasIf getUsuarioNoticias(java.lang.Long id) throws GenericBusinessException;

   Collection getUsuarioNoticiasList() throws GenericBusinessException;

   Collection getUsuarioNoticiasList(int startIndex, int endIndex) throws GenericBusinessException;

   int getUsuarioNoticiasListSize() throws GenericBusinessException;
    java.util.Collection findUsuarioNoticiasById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findUsuarioNoticiasByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findUsuarioNoticiasByNoticiasId(java.lang.Long noticiasId) throws GenericBusinessException;

}
