package com.spirit.facturacion.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PedidoDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getDocumentoId();

   void setDocumentoId(java.lang.Long documentoId);

   java.lang.Long getPedidoId();

   void setPedidoId(java.lang.Long pedidoId);

   java.lang.Long getProductoId();

   void setProductoId(java.lang.Long productoId);

   java.lang.Long getLoteId();

   void setLoteId(java.lang.Long loteId);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.lang.Long getMotivodocumentoId();

   void setMotivodocumentoId(java.lang.Long motivodocumentoId);

   java.math.BigDecimal getCantidadpedida();

   void setCantidadpedida(java.math.BigDecimal cantidadpedida);

   java.math.BigDecimal getCantidad();

   void setCantidad(java.math.BigDecimal cantidad);

   java.math.BigDecimal getPrecio();

   void setPrecio(java.math.BigDecimal precio);

   java.math.BigDecimal getPrecioreal();

   void setPrecioreal(java.math.BigDecimal precioreal);

   java.math.BigDecimal getDescuento();

   void setDescuento(java.math.BigDecimal descuento);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.math.BigDecimal getIva();

   void setIva(java.math.BigDecimal iva);

   java.math.BigDecimal getIce();

   void setIce(java.math.BigDecimal ice);

   java.math.BigDecimal getOtroimpuesto();

   void setOtroimpuesto(java.math.BigDecimal otroimpuesto);

   java.math.BigDecimal getDescuentoGlobal();

   void setDescuentoGlobal(java.math.BigDecimal descuentoGlobal);

   java.lang.String getCodigoPersonalizacion();

   void setCodigoPersonalizacion(java.lang.String codigoPersonalizacion);

   java.lang.Long getGiftcardId();

   void setGiftcardId(java.lang.Long giftcardId);

   java.math.BigDecimal getPorcentajeDescuentosVarios();

   void setPorcentajeDescuentosVarios(java.math.BigDecimal porcentajeDescuentosVarios);

   java.lang.String getComprasReembolsoAsociadas();

   void setComprasReembolsoAsociadas(java.lang.String comprasReembolsoAsociadas);


}
