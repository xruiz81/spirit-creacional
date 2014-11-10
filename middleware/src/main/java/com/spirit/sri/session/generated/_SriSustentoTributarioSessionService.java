package com.spirit.sri.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SriSustentoTributarioSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.sri.entity.SriSustentoTributarioIf addSriSustentoTributario(com.spirit.sri.entity.SriSustentoTributarioIf model) throws GenericBusinessException;

   void saveSriSustentoTributario(com.spirit.sri.entity.SriSustentoTributarioIf model) throws GenericBusinessException;

   void deleteSriSustentoTributario(java.lang.Long id) throws GenericBusinessException;

   Collection findSriSustentoTributarioByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.sri.entity.SriSustentoTributarioIf getSriSustentoTributario(java.lang.Long id) throws GenericBusinessException;

   Collection getSriSustentoTributarioList() throws GenericBusinessException;

   Collection getSriSustentoTributarioList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSriSustentoTributarioListSize() throws GenericBusinessException;
    java.util.Collection findSriSustentoTributarioById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSriSustentoTributarioByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findSriSustentoTributarioByCodigo(java.lang.String codigo) throws GenericBusinessException;

}
