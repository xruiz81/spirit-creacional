package com.spirit.seguridad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _FuncionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.seguridad.entity.FuncionIf addFuncion(com.spirit.seguridad.entity.FuncionIf model) throws GenericBusinessException;

   void saveFuncion(com.spirit.seguridad.entity.FuncionIf model) throws GenericBusinessException;

   void deleteFuncion(java.lang.Long id) throws GenericBusinessException;

   Collection findFuncionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.seguridad.entity.FuncionIf getFuncion(java.lang.Long id) throws GenericBusinessException;

   Collection getFuncionList() throws GenericBusinessException;

   Collection getFuncionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getFuncionListSize() throws GenericBusinessException;
    java.util.Collection findFuncionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findFuncionByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findFuncionByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findFuncionByIcon(java.lang.String icon) throws GenericBusinessException;

}
