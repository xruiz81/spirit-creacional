package com.spirit.facturacion.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface FacturaDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getDocumentoId();

   void setDocumentoId(java.lang.Long documentoId);

   java.lang.Long getFacturaId();

   void setFacturaId(java.lang.Long facturaId);

   java.lang.Long getProductoId();

   void setProductoId(java.lang.Long productoId);

   java.lang.Long getLoteId();

   void setLoteId(java.lang.Long loteId);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.lang.Long getMotivodocumentoId();

   void setMotivodocumentoId(java.lang.Long motivodocumentoId);

   java.math.BigDecimal getCantidad();

   void setCantidad(java.math.BigDecimal cantidad);

   java.math.BigDecimal getPrecio();

   void setPrecio(java.math.BigDecimal precio);

   java.math.BigDecimal getPrecioReal();

   void setPrecioReal(java.math.BigDecimal precioReal);

   java.math.BigDecimal getDescuento();

   void setDescuento(java.math.BigDecimal descuento);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.math.BigDecimal getIva();

   void setIva(java.math.BigDecimal iva);

   java.math.BigDecimal getIce();

   void setIce(java.math.BigDecimal ice);

   java.math.BigDecimal getOtroImpuesto();

   void setOtroImpuesto(java.math.BigDecimal otroImpuesto);

   java.math.BigDecimal getCosto();

   void setCosto(java.math.BigDecimal costo);

   java.lang.Long getLineaId();

   void setLineaId(java.lang.Long lineaId);

   java.math.BigDecimal getCantidadDevuelta();

   void setCantidadDevuelta(java.math.BigDecimal cantidadDevuelta);

   java.math.BigDecimal getDescuentoGlobal();

   void setDescuentoGlobal(java.math.BigDecimal descuentoGlobal);

   java.lang.Long getIdSriClienteRetencion();

   void setIdSriClienteRetencion(java.lang.Long idSriClienteRetencion);

   java.math.BigDecimal getPorcentajeDescuentosVarios();

   void setPorcentajeDescuentosVarios(java.math.BigDecimal porcentajeDescuentosVarios);

   java.lang.String getComprasReembolsoAsociadas();

   void setComprasReembolsoAsociadas(java.lang.String comprasReembolsoAsociadas);


}
