package com.spirit.inventario.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface GiftcardIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getProductoId();

   void setProductoId(java.lang.Long productoId);

   java.lang.String getCodigoBarras();

   void setCodigoBarras(java.lang.String codigoBarras);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.math.BigDecimal getSaldo();

   void setSaldo(java.math.BigDecimal saldo);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);


}
