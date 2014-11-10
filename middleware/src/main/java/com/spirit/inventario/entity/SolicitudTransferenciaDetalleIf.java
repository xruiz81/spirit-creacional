package com.spirit.inventario.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SolicitudTransferenciaDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getSolicitudTransferenciaId();

   void setSolicitudTransferenciaId(java.lang.Long solicitudTransferenciaId);

   java.lang.Long getProductoId();

   void setProductoId(java.lang.Long productoId);

   java.lang.Long getLoteId();

   void setLoteId(java.lang.Long loteId);

   java.math.BigDecimal getCantidad();

   void setCantidad(java.math.BigDecimal cantidad);


}
