package com.spirit.inventario.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface StockDiaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getBodegaId();

   void setBodegaId(java.lang.Long bodegaId);

   java.lang.Long getProductoId();

   void setProductoId(java.lang.Long productoId);

   java.lang.String getAnio();

   void setAnio(java.lang.String anio);

   java.lang.String getMes();

   void setMes(java.lang.String mes);

   java.lang.String getDia();

   void setDia(java.lang.String dia);

   java.math.BigDecimal getCantidad();

   void setCantidad(java.math.BigDecimal cantidad);


}
