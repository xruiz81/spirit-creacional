package com.spirit.seguridad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _MenuSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.seguridad.entity.MenuIf addMenu(com.spirit.seguridad.entity.MenuIf model) throws GenericBusinessException;

   void saveMenu(com.spirit.seguridad.entity.MenuIf model) throws GenericBusinessException;

   void deleteMenu(java.lang.Long id) throws GenericBusinessException;

   Collection findMenuByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.seguridad.entity.MenuIf getMenu(java.lang.Long id) throws GenericBusinessException;

   Collection getMenuList() throws GenericBusinessException;

   Collection getMenuList(int startIndex, int endIndex) throws GenericBusinessException;

   int getMenuListSize() throws GenericBusinessException;
    java.util.Collection findMenuById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findMenuByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findMenuByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findMenuByPadreId(java.lang.Long padreId) throws GenericBusinessException;
    java.util.Collection findMenuByNivel(java.lang.Integer nivel) throws GenericBusinessException;
    java.util.Collection findMenuByFavorito(java.lang.Integer favorito) throws GenericBusinessException;
    java.util.Collection findMenuByPanel(java.lang.String panel) throws GenericBusinessException;

}
