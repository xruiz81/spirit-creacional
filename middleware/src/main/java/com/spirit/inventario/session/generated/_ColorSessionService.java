package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ColorSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.ColorIf addColor(com.spirit.inventario.entity.ColorIf model) throws GenericBusinessException;

   void saveColor(com.spirit.inventario.entity.ColorIf model) throws GenericBusinessException;

   void deleteColor(java.lang.Long id) throws GenericBusinessException;

   Collection findColorByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.ColorIf getColor(java.lang.Long id) throws GenericBusinessException;

   Collection getColorList() throws GenericBusinessException;

   Collection getColorList(int startIndex, int endIndex) throws GenericBusinessException;

   int getColorListSize() throws GenericBusinessException;
    java.util.Collection findColorById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findColorByNombre(java.lang.String nombre) throws GenericBusinessException;

}
