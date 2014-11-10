package com.spirit.pos.session.generated;

import com.spirit.exception.GenericBusinessException;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TarjetaCreditoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.pos.entity.TarjetaCreditoIf addTarjetaCredito(com.spirit.pos.entity.TarjetaCreditoIf model) throws GenericBusinessException;

   void saveTarjetaCredito(com.spirit.pos.entity.TarjetaCreditoIf model) throws GenericBusinessException;

   void deleteTarjetaCredito(java.lang.Long id) throws GenericBusinessException;

   Collection findTarjetaCreditoByQuery(Map aMap) throws GenericBusinessException;


   com.spirit.pos.entity.MultasDocumentosIf addMultasDocumentos(com.spirit.pos.entity.MultasDocumentosIf model) throws GenericBusinessException;

   void saveMultasDocumentos(com.spirit.pos.entity.MultasDocumentosIf model) throws GenericBusinessException;

   void deleteMultasDocumentos(java.lang.Long id) throws GenericBusinessException;

   Collection findMultasDocumentosByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.pos.entity.TarjetaCreditoIf getTarjetaCredito(java.lang.Long id) throws GenericBusinessException;

   Collection getTarjetaCreditoList() throws GenericBusinessException;

   Collection getTarjetaCreditoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTarjetaCreditoListSize() throws GenericBusinessException;

   com.spirit.pos.entity.MultasDocumentosIf getMultasDocumentos(java.lang.Long id) throws GenericBusinessException;

   Collection getMultasDocumentosList() throws GenericBusinessException;

   Collection getMultasDocumentosList(int startIndex, int endIndex) throws GenericBusinessException;

   int getMultasDocumentosListSize() throws GenericBusinessException;
    java.util.Collection findTarjetaCreditoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTarjetaCreditoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTarjetaCreditoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTarjetaCreditoByBancoId(java.lang.Long bancoId) throws GenericBusinessException;

    java.util.Collection findMultasDocumentosById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findMultasDocumentosByMotivoId(java.lang.Long motivoId) throws GenericBusinessException;
    java.util.Collection findMultasDocumentosByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findMultasDocumentosByValorMulta(java.math.BigDecimal valorMulta) throws GenericBusinessException;

}
