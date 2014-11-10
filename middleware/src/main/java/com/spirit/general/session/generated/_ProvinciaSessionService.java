package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ProvinciaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.ProvinciaIf addProvincia(com.spirit.general.entity.ProvinciaIf model) throws GenericBusinessException;

   void saveProvincia(com.spirit.general.entity.ProvinciaIf model) throws GenericBusinessException;

   void deleteProvincia(java.lang.Long id) throws GenericBusinessException;

   Collection findProvinciaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.ProvinciaIf getProvincia(java.lang.Long id) throws GenericBusinessException;

   Collection getProvinciaList() throws GenericBusinessException;

   Collection getProvinciaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getProvinciaListSize() throws GenericBusinessException;
    java.util.Collection findProvinciaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findProvinciaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findProvinciaByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findProvinciaByPaisId(java.lang.Long paisId) throws GenericBusinessException;

}
