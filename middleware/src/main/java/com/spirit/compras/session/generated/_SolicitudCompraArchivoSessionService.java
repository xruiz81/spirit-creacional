package com.spirit.compras.session.generated;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SolicitudCompraArchivoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.compras.entity.SolicitudCompraArchivoIf addSolicitudCompraArchivo(com.spirit.compras.entity.SolicitudCompraArchivoIf model) throws GenericBusinessException;

   void saveSolicitudCompraArchivo(com.spirit.compras.entity.SolicitudCompraArchivoIf model) throws GenericBusinessException;

   void deleteSolicitudCompraArchivo(java.lang.Long id) throws GenericBusinessException;

   Collection findSolicitudCompraArchivoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.compras.entity.SolicitudCompraArchivoIf getSolicitudCompraArchivo(java.lang.Long id) throws GenericBusinessException;

   Collection getSolicitudCompraArchivoList() throws GenericBusinessException;

   Collection getSolicitudCompraArchivoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSolicitudCompraArchivoListSize() throws GenericBusinessException;
    java.util.Collection findSolicitudCompraArchivoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraArchivoBySolicitudCompraId(java.lang.Long solicitudCompraId) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraArchivoByTipoArchivoId(java.lang.Long tipoArchivoId) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraArchivoByUrl(java.lang.String url) throws GenericBusinessException;

}
