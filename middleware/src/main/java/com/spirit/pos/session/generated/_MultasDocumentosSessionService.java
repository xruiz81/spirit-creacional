package com.spirit.pos.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _MultasDocumentosSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.pos.entity.MultasDocumentosIf addMultasDocumentos(com.spirit.pos.entity.MultasDocumentosIf model) throws GenericBusinessException;

   void saveMultasDocumentos(com.spirit.pos.entity.MultasDocumentosIf model) throws GenericBusinessException;

   void deleteMultasDocumentos(java.lang.Long id) throws GenericBusinessException;

   Collection findMultasDocumentosByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.pos.entity.MultasDocumentosIf getMultasDocumentos(java.lang.Long id) throws GenericBusinessException;

   Collection getMultasDocumentosList() throws GenericBusinessException;

   Collection getMultasDocumentosList(int startIndex, int endIndex) throws GenericBusinessException;

   int getMultasDocumentosListSize() throws GenericBusinessException;
    java.util.Collection findMultasDocumentosById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findMultasDocumentosByMotivoId(java.lang.Long motivoId) throws GenericBusinessException;
    java.util.Collection findMultasDocumentosByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findMultasDocumentosByValorMulta(java.math.BigDecimal valorMulta) throws GenericBusinessException;

}
