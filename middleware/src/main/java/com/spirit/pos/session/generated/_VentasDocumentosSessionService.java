package com.spirit.pos.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _VentasDocumentosSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.pos.entity.VentasDocumentosIf addVentasDocumentos(com.spirit.pos.entity.VentasDocumentosIf model) throws GenericBusinessException;

   void saveVentasDocumentos(com.spirit.pos.entity.VentasDocumentosIf model) throws GenericBusinessException;

   void deleteVentasDocumentos(java.lang.Long id) throws GenericBusinessException;

   Collection findVentasDocumentosByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.pos.entity.VentasDocumentosIf getVentasDocumentos(java.lang.Long id) throws GenericBusinessException;

   Collection getVentasDocumentosList() throws GenericBusinessException;

   Collection getVentasDocumentosList(int startIndex, int endIndex) throws GenericBusinessException;

   int getVentasDocumentosListSize() throws GenericBusinessException;
    java.util.Collection findVentasDocumentosById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findVentasDocumentosByVentastrackId(java.lang.Long ventastrackId) throws GenericBusinessException;
    java.util.Collection findVentasDocumentosByTablaNombre(java.lang.String tablaNombre) throws GenericBusinessException;
    java.util.Collection findVentasDocumentosByTablaId(java.lang.Long tablaId) throws GenericBusinessException;
    java.util.Collection findVentasDocumentosByRevisado(java.lang.String revisado) throws GenericBusinessException;
    java.util.Collection findVentasDocumentosByNumDoc(java.lang.String numDoc) throws GenericBusinessException;

}
