package com.spirit.rrhh.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _IdiomaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.rrhh.entity.IdiomaIf addIdioma(com.spirit.rrhh.entity.IdiomaIf model) throws GenericBusinessException;

   void saveIdioma(com.spirit.rrhh.entity.IdiomaIf model) throws GenericBusinessException;

   void deleteIdioma(java.lang.Long id) throws GenericBusinessException;

   Collection findIdiomaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.rrhh.entity.IdiomaIf getIdioma(java.lang.Long id) throws GenericBusinessException;

   Collection getIdiomaList() throws GenericBusinessException;

   Collection getIdiomaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getIdiomaListSize() throws GenericBusinessException;
    java.util.Collection findIdiomaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findIdiomaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findIdiomaByNombre(java.lang.String nombre) throws GenericBusinessException;

}
