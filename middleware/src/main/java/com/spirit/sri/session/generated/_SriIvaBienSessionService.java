package com.spirit.sri.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SriIvaBienSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.sri.entity.SriIvaBienIf addSriIvaBien(com.spirit.sri.entity.SriIvaBienIf model) throws GenericBusinessException;

   void saveSriIvaBien(com.spirit.sri.entity.SriIvaBienIf model) throws GenericBusinessException;

   void deleteSriIvaBien(java.lang.Long id) throws GenericBusinessException;

   Collection findSriIvaBienByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.sri.entity.SriIvaBienIf getSriIvaBien(java.lang.Long id) throws GenericBusinessException;

   Collection getSriIvaBienList() throws GenericBusinessException;

   Collection getSriIvaBienList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSriIvaBienListSize() throws GenericBusinessException;
    java.util.Collection findSriIvaBienById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSriIvaBienByCodigo(java.lang.Integer codigo) throws GenericBusinessException;
    java.util.Collection findSriIvaBienByDescripcionPorcentaje(java.lang.String descripcionPorcentaje) throws GenericBusinessException;

}
