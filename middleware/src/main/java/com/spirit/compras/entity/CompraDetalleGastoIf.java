package com.spirit.compras.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CompraDetalleGastoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getCompraGastoId();

   void setCompraGastoId(java.lang.Long compraGastoId);

   java.lang.Long getCompraDetalleId();

   void setCompraDetalleId(java.lang.Long compraDetalleId);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);


}
