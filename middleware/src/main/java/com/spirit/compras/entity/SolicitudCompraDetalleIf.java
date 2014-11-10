package com.spirit.compras.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SolicitudCompraDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getSolicitudcompraId();

   void setSolicitudcompraId(java.lang.Long solicitudcompraId);

   java.lang.Long getDocumentoId();

   void setDocumentoId(java.lang.Long documentoId);

   java.lang.Long getProductoId();

   void setProductoId(java.lang.Long productoId);

   java.math.BigDecimal getCantidad();

   void setCantidad(java.math.BigDecimal cantidad);


}
