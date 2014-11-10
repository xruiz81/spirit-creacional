package com.spirit.sri.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SriIvaServicioSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.sri.entity.SriIvaServicioIf addSriIvaServicio(com.spirit.sri.entity.SriIvaServicioIf model) throws GenericBusinessException;

   void saveSriIvaServicio(com.spirit.sri.entity.SriIvaServicioIf model) throws GenericBusinessException;

   void deleteSriIvaServicio(java.lang.Long id) throws GenericBusinessException;

   Collection findSriIvaServicioByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.sri.entity.SriIvaServicioIf getSriIvaServicio(java.lang.Long id) throws GenericBusinessException;

   Collection getSriIvaServicioList() throws GenericBusinessException;

   Collection getSriIvaServicioList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSriIvaServicioListSize() throws GenericBusinessException;
    java.util.Collection findSriIvaServicioById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSriIvaServicioByCodigo(java.lang.Integer codigo) throws GenericBusinessException;
    java.util.Collection findSriIvaServicioByDescripcionPorcentaje(java.lang.String descripcionPorcentaje) throws GenericBusinessException;

}
