package com.spirit.sri.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SriIdentifTransaccionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.sri.entity.SriIdentifTransaccionIf addSriIdentifTransaccion(com.spirit.sri.entity.SriIdentifTransaccionIf model) throws GenericBusinessException;

   void saveSriIdentifTransaccion(com.spirit.sri.entity.SriIdentifTransaccionIf model) throws GenericBusinessException;

   void deleteSriIdentifTransaccion(java.lang.Long id) throws GenericBusinessException;

   Collection findSriIdentifTransaccionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.sri.entity.SriIdentifTransaccionIf getSriIdentifTransaccion(java.lang.Long id) throws GenericBusinessException;

   Collection getSriIdentifTransaccionList() throws GenericBusinessException;

   Collection getSriIdentifTransaccionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSriIdentifTransaccionListSize() throws GenericBusinessException;
    java.util.Collection findSriIdentifTransaccionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSriIdentifTransaccionByIdTipoTransaccion(java.lang.Long idTipoTransaccion) throws GenericBusinessException;
    java.util.Collection findSriIdentifTransaccionByIdTipoIdentificacion(java.lang.Long idTipoIdentificacion) throws GenericBusinessException;
    java.util.Collection findSriIdentifTransaccionByCodigo(java.lang.String codigo) throws GenericBusinessException;

}
