package com.spirit.sri.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SriTipoComprobanteSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.sri.entity.SriTipoComprobanteIf addSriTipoComprobante(com.spirit.sri.entity.SriTipoComprobanteIf model) throws GenericBusinessException;

   void saveSriTipoComprobante(com.spirit.sri.entity.SriTipoComprobanteIf model) throws GenericBusinessException;

   void deleteSriTipoComprobante(java.lang.Long id) throws GenericBusinessException;

   Collection findSriTipoComprobanteByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.sri.entity.SriTipoComprobanteIf getSriTipoComprobante(java.lang.Long id) throws GenericBusinessException;

   Collection getSriTipoComprobanteList() throws GenericBusinessException;

   Collection getSriTipoComprobanteList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSriTipoComprobanteListSize() throws GenericBusinessException;
    java.util.Collection findSriTipoComprobanteById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSriTipoComprobanteByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findSriTipoComprobanteByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findSriTipoComprobanteByAnulacionTipoComprobanteId(java.lang.Long anulacionTipoComprobanteId) throws GenericBusinessException;

}
