package com.spirit.sri.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SriIvaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.sri.entity.SriIvaIf addSriIva(com.spirit.sri.entity.SriIvaIf model) throws GenericBusinessException;

   void saveSriIva(com.spirit.sri.entity.SriIvaIf model) throws GenericBusinessException;

   void deleteSriIva(java.lang.Long id) throws GenericBusinessException;

   Collection findSriIvaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.sri.entity.SriIvaIf getSriIva(java.lang.Long id) throws GenericBusinessException;

   Collection getSriIvaList() throws GenericBusinessException;

   Collection getSriIvaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSriIvaListSize() throws GenericBusinessException;
    java.util.Collection findSriIvaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSriIvaByCodigo(java.lang.Integer codigo) throws GenericBusinessException;
    java.util.Collection findSriIvaByPorcentaje(java.lang.Integer porcentaje) throws GenericBusinessException;
    java.util.Collection findSriIvaByFechaInicio(java.sql.Date fechaInicio) throws GenericBusinessException;
    java.util.Collection findSriIvaByFechaFin(java.sql.Date fechaFin) throws GenericBusinessException;

}
