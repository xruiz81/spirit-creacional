package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _FacturaDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.FacturaDetalleIf addFacturaDetalle(com.spirit.facturacion.entity.FacturaDetalleIf model) throws GenericBusinessException;

   void saveFacturaDetalle(com.spirit.facturacion.entity.FacturaDetalleIf model) throws GenericBusinessException;

   void deleteFacturaDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findFacturaDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.FacturaDetalleIf getFacturaDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getFacturaDetalleList() throws GenericBusinessException;

   Collection getFacturaDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getFacturaDetalleListSize() throws GenericBusinessException;
    java.util.Collection findFacturaDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByFacturaId(java.lang.Long facturaId) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByProductoId(java.lang.Long productoId) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByLoteId(java.lang.Long loteId) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByMotivodocumentoId(java.lang.Long motivodocumentoId) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByCantidad(java.math.BigDecimal cantidad) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByPrecio(java.math.BigDecimal precio) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByPrecioReal(java.math.BigDecimal precioReal) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByDescuento(java.math.BigDecimal descuento) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByIva(java.math.BigDecimal iva) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByIce(java.math.BigDecimal ice) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByOtroImpuesto(java.math.BigDecimal otroImpuesto) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByCosto(java.math.BigDecimal costo) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByLineaId(java.lang.Long lineaId) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByCantidadDevuelta(java.math.BigDecimal cantidadDevuelta) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByDescuentoGlobal(java.math.BigDecimal descuentoGlobal) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByIdSriClienteRetencion(java.lang.Long idSriClienteRetencion) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByPorcentajeDescuentosVarios(java.math.BigDecimal porcentajeDescuentosVarios) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleByComprasReembolsoAsociadas(java.lang.String comprasReembolsoAsociadas) throws GenericBusinessException;

}
