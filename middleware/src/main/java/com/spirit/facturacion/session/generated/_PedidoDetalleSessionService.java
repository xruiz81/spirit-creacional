package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PedidoDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.PedidoDetalleIf addPedidoDetalle(com.spirit.facturacion.entity.PedidoDetalleIf model) throws GenericBusinessException;

   void savePedidoDetalle(com.spirit.facturacion.entity.PedidoDetalleIf model) throws GenericBusinessException;

   void deletePedidoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findPedidoDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.PedidoDetalleIf getPedidoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getPedidoDetalleList() throws GenericBusinessException;

   Collection getPedidoDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPedidoDetalleListSize() throws GenericBusinessException;
    java.util.Collection findPedidoDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByPedidoId(java.lang.Long pedidoId) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByProductoId(java.lang.Long productoId) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByLoteId(java.lang.Long loteId) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByMotivodocumentoId(java.lang.Long motivodocumentoId) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByCantidadpedida(java.math.BigDecimal cantidadpedida) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByCantidad(java.math.BigDecimal cantidad) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByPrecio(java.math.BigDecimal precio) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByPrecioreal(java.math.BigDecimal precioreal) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByDescuento(java.math.BigDecimal descuento) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByIva(java.math.BigDecimal iva) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByIce(java.math.BigDecimal ice) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByOtroimpuesto(java.math.BigDecimal otroimpuesto) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByDescuentoGlobal(java.math.BigDecimal descuentoGlobal) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByCodigoPersonalizacion(java.lang.String codigoPersonalizacion) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByGiftcardId(java.lang.Long giftcardId) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByPorcentajeDescuentosVarios(java.math.BigDecimal porcentajeDescuentosVarios) throws GenericBusinessException;
    java.util.Collection findPedidoDetalleByComprasReembolsoAsociadas(java.lang.String comprasReembolsoAsociadas) throws GenericBusinessException;

}
