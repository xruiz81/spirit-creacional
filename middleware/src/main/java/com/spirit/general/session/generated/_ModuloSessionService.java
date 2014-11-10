package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ModuloSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.ModuloIf addModulo(com.spirit.general.entity.ModuloIf model) throws GenericBusinessException;

   void saveModulo(com.spirit.general.entity.ModuloIf model) throws GenericBusinessException;

   void deleteModulo(java.lang.Long id) throws GenericBusinessException;

   Collection findModuloByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.ModuloIf getModulo(java.lang.Long id) throws GenericBusinessException;

   Collection getModuloList() throws GenericBusinessException;

   Collection getModuloList(int startIndex, int endIndex) throws GenericBusinessException;

   int getModuloListSize() throws GenericBusinessException;
    java.util.Collection findModuloById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findModuloByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findModuloByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findModuloByStatus(java.lang.String status) throws GenericBusinessException;

}
