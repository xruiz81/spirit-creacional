package com.spirit.compras.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CompraGastoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getGastoId();

   void setGastoId(java.lang.Long gastoId);

   java.lang.Long getCompraId();

   void setCompraId(java.lang.Long compraId);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);


}
