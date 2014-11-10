package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CiudadSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.CiudadIf addCiudad(com.spirit.general.entity.CiudadIf model) throws GenericBusinessException;

   void saveCiudad(com.spirit.general.entity.CiudadIf model) throws GenericBusinessException;

   void deleteCiudad(java.lang.Long id) throws GenericBusinessException;

   Collection findCiudadByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.CiudadIf getCiudad(java.lang.Long id) throws GenericBusinessException;

   Collection getCiudadList() throws GenericBusinessException;

   Collection getCiudadList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCiudadListSize() throws GenericBusinessException;
    java.util.Collection findCiudadById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCiudadByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findCiudadByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findCiudadByProvinciaId(java.lang.Long provinciaId) throws GenericBusinessException;

}
