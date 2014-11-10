package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SolicitudTransferenciaDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf addSolicitudTransferenciaDetalle(com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf model) throws GenericBusinessException;

   void saveSolicitudTransferenciaDetalle(com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf model) throws GenericBusinessException;

   void deleteSolicitudTransferenciaDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findSolicitudTransferenciaDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf getSolicitudTransferenciaDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getSolicitudTransferenciaDetalleList() throws GenericBusinessException;

   Collection getSolicitudTransferenciaDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSolicitudTransferenciaDetalleListSize() throws GenericBusinessException;
    java.util.Collection findSolicitudTransferenciaDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSolicitudTransferenciaDetalleBySolicitudTransferenciaId(java.lang.Long solicitudTransferenciaId) throws GenericBusinessException;
    java.util.Collection findSolicitudTransferenciaDetalleByProductoId(java.lang.Long productoId) throws GenericBusinessException;
    java.util.Collection findSolicitudTransferenciaDetalleByLoteId(java.lang.Long loteId) throws GenericBusinessException;
    java.util.Collection findSolicitudTransferenciaDetalleByCantidad(java.math.BigDecimal cantidad) throws GenericBusinessException;

}
