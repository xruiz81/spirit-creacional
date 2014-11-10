package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _MovimientoDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.MovimientoDetalleIf addMovimientoDetalle(com.spirit.inventario.entity.MovimientoDetalleIf model) throws GenericBusinessException;

   void saveMovimientoDetalle(com.spirit.inventario.entity.MovimientoDetalleIf model) throws GenericBusinessException;

   void deleteMovimientoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findMovimientoDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.MovimientoDetalleIf getMovimientoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getMovimientoDetalleList() throws GenericBusinessException;

   Collection getMovimientoDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getMovimientoDetalleListSize() throws GenericBusinessException;
    java.util.Collection findMovimientoDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findMovimientoDetalleByMovimientoId(java.lang.Long movimientoId) throws GenericBusinessException;
    java.util.Collection findMovimientoDetalleByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findMovimientoDetalleByLoteId(java.lang.Long loteId) throws GenericBusinessException;
    java.util.Collection findMovimientoDetalleByCantidad(java.math.BigDecimal cantidad) throws GenericBusinessException;
    java.util.Collection findMovimientoDetalleByCosto(java.math.BigDecimal costo) throws GenericBusinessException;
    java.util.Collection findMovimientoDetalleByPrecio(java.math.BigDecimal precio) throws GenericBusinessException;
    java.util.Collection findMovimientoDetalleByCostoLifo(java.math.BigDecimal costoLifo) throws GenericBusinessException;
    java.util.Collection findMovimientoDetalleByCostoFifo(java.math.BigDecimal costoFifo) throws GenericBusinessException;
    java.util.Collection findMovimientoDetalleByValorUnitario(java.math.BigDecimal valorUnitario) throws GenericBusinessException;
    java.util.Collection findMovimientoDetalleByPromedioUnitario(java.math.BigDecimal promedioUnitario) throws GenericBusinessException;
    java.util.Collection findMovimientoDetalleByStockValorizado(java.math.BigDecimal stockValorizado) throws GenericBusinessException;

}
