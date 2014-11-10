package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _MotivoDocumentoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.MotivoDocumentoIf addMotivoDocumento(com.spirit.facturacion.entity.MotivoDocumentoIf model) throws GenericBusinessException;

   void saveMotivoDocumento(com.spirit.facturacion.entity.MotivoDocumentoIf model) throws GenericBusinessException;

   void deleteMotivoDocumento(java.lang.Long id) throws GenericBusinessException;

   Collection findMotivoDocumentoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.MotivoDocumentoIf getMotivoDocumento(java.lang.Long id) throws GenericBusinessException;

   Collection getMotivoDocumentoList() throws GenericBusinessException;

   Collection getMotivoDocumentoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getMotivoDocumentoListSize() throws GenericBusinessException;
    java.util.Collection findMotivoDocumentoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findMotivoDocumentoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findMotivoDocumentoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findMotivoDocumentoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findMotivoDocumentoByMulta(java.lang.String multa) throws GenericBusinessException;

}
