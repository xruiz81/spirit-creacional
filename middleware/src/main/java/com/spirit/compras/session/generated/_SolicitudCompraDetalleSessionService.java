package com.spirit.compras.session.generated;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SolicitudCompraDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.compras.entity.SolicitudCompraDetalleIf addSolicitudCompraDetalle(com.spirit.compras.entity.SolicitudCompraDetalleIf model) throws GenericBusinessException;

   void saveSolicitudCompraDetalle(com.spirit.compras.entity.SolicitudCompraDetalleIf model) throws GenericBusinessException;

   void deleteSolicitudCompraDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findSolicitudCompraDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.compras.entity.SolicitudCompraDetalleIf getSolicitudCompraDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getSolicitudCompraDetalleList() throws GenericBusinessException;

   Collection getSolicitudCompraDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSolicitudCompraDetalleListSize() throws GenericBusinessException;
    java.util.Collection findSolicitudCompraDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraDetalleBySolicitudcompraId(java.lang.Long solicitudcompraId) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraDetalleByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraDetalleByProductoId(java.lang.Long productoId) throws GenericBusinessException;
    java.util.Collection findSolicitudCompraDetalleByCantidad(java.math.BigDecimal cantidad) throws GenericBusinessException;

}
