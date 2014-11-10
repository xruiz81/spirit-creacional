package com.spirit.general.session.generated;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _OrigenDocumentoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.OrigenDocumentoIf addOrigenDocumento(com.spirit.general.entity.OrigenDocumentoIf model) throws GenericBusinessException;

   void saveOrigenDocumento(com.spirit.general.entity.OrigenDocumentoIf model) throws GenericBusinessException;

   void deleteOrigenDocumento(java.lang.Long id) throws GenericBusinessException;

   Collection findOrigenDocumentoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.OrigenDocumentoIf getOrigenDocumento(java.lang.Long id) throws GenericBusinessException;

   Collection getOrigenDocumentoList() throws GenericBusinessException;

   Collection getOrigenDocumentoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getOrigenDocumentoListSize() throws GenericBusinessException;
    java.util.Collection findOrigenDocumentoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findOrigenDocumentoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findOrigenDocumentoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findOrigenDocumentoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findOrigenDocumentoByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;

}
