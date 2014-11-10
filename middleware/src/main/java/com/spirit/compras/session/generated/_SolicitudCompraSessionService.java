package com.spirit.compras.session.generated;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SolicitudCompraSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.compras.entity.SolicitudCompraIf addSolicitudCompra(com.spirit.compras.entity.SolicitudCompraIf model) throws GenericBusinessException;

   void saveSolicitudCompra(com.spirit.compras.entity.SolicitudCompraIf model) throws GenericBusinessException;

   void deleteSolicitudCompra(java.lang.Long id) throws GenericBusinessException;

   Collection findSolicitudCompraByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.compras.entity.SolicitudCompraIf getSolicitudCompra(java.lang.Long id) throws GenericBusinessException;

   Collection getSolicitudCompraList() throws GenericBusinessException;

   Collection getSolicitudCompraList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSolicitudCompraListSize() throws GenericBusinessException;
    java.util.Collection findSolicitudCompraById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraByTipodocumentoId(java.lang.Long tipodocumentoId) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraByBodegaId(java.lang.Long bodegaId) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraByFecha(java.sql.Date fecha) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraByFechaEntrega(java.sql.Date fechaEntrega) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraByEstadoBpm(java.lang.String estadoBpm) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraByTipoReferencia(java.lang.String tipoReferencia) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraByReferencia(java.lang.String referencia) throws GenericBusinessException;

}
