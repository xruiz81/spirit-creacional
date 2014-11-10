package com.spirit.facturacion.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PedidoEnvioDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getPedidoEnvioId();

   void setPedidoEnvioId(java.lang.Long pedidoEnvioId);

   java.lang.String getCodigoBarras();

   void setCodigoBarras(java.lang.String codigoBarras);

   java.math.BigDecimal getCantidad();

   void setCantidad(java.math.BigDecimal cantidad);

   java.lang.String getIncluyeIva();

   void setIncluyeIva(java.lang.String incluyeIva);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);


}
