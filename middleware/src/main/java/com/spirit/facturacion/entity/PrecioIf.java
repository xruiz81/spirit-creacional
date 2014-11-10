package com.spirit.facturacion.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PrecioIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getListaprecioId();

   void setListaprecioId(java.lang.Long listaprecioId);

   java.lang.Long getProductoId();

   void setProductoId(java.lang.Long productoId);

   java.math.BigDecimal getPrecio();

   void setPrecio(java.math.BigDecimal precio);

   java.lang.String getCambiarPrecio();

   void setCambiarPrecio(java.lang.String cambiarPrecio);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);


}
