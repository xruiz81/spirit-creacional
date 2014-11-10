package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SolicitudTransferenciaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.SolicitudTransferenciaIf addSolicitudTransferencia(com.spirit.inventario.entity.SolicitudTransferenciaIf model) throws GenericBusinessException;

   void saveSolicitudTransferencia(com.spirit.inventario.entity.SolicitudTransferenciaIf model) throws GenericBusinessException;

   void deleteSolicitudTransferencia(java.lang.Long id) throws GenericBusinessException;

   Collection findSolicitudTransferenciaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.SolicitudTransferenciaIf getSolicitudTransferencia(java.lang.Long id) throws GenericBusinessException;

   Collection getSolicitudTransferenciaList() throws GenericBusinessException;

   Collection getSolicitudTransferenciaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSolicitudTransferenciaListSize() throws GenericBusinessException;
    java.util.Collection findSolicitudTransferenciaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSolicitudTransferenciaByFechaDocumento(java.sql.Timestamp fechaDocumento) throws GenericBusinessException;
    java.util.Collection findSolicitudTransferenciaByFechaIngreso(java.sql.Timestamp fechaIngreso) throws GenericBusinessException;
    java.util.Collection findSolicitudTransferenciaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findSolicitudTransferenciaByBodegaDesdeId(java.lang.Long bodegaDesdeId) throws GenericBusinessException;
    java.util.Collection findSolicitudTransferenciaByBodegaHaciaId(java.lang.Long bodegaHaciaId) throws GenericBusinessException;
    java.util.Collection findSolicitudTransferenciaByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findSolicitudTransferenciaByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findSolicitudTransferenciaByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;

}
