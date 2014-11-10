package com.spirit.inventario.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface StockOperativoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getBodegaId();

   void setBodegaId(java.lang.Long bodegaId);

   java.lang.String getMes();

   void setMes(java.lang.String mes);

   java.lang.String getAnio();

   void setAnio(java.lang.String anio);

   java.lang.Long getProductoId();

   void setProductoId(java.lang.Long productoId);

   java.math.BigDecimal getMin();

   void setMin(java.math.BigDecimal min);

   java.math.BigDecimal getMax();

   void setMax(java.math.BigDecimal max);

   java.lang.Integer getTiempoMinimoReposicion();

   void setTiempoMinimoReposicion(java.lang.Integer tiempoMinimoReposicion);


}
