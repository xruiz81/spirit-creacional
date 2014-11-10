package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PaisSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.PaisIf addPais(com.spirit.general.entity.PaisIf model) throws GenericBusinessException;

   void savePais(com.spirit.general.entity.PaisIf model) throws GenericBusinessException;

   void deletePais(java.lang.Long id) throws GenericBusinessException;

   Collection findPaisByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.PaisIf getPais(java.lang.Long id) throws GenericBusinessException;

   Collection getPaisList() throws GenericBusinessException;

   Collection getPaisList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPaisListSize() throws GenericBusinessException;
    java.util.Collection findPaisById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPaisByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPaisByNombre(java.lang.String nombre) throws GenericBusinessException;

}
