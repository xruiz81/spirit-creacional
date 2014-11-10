package com.spirit.pos.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface FacturastipoproductodetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getModelo();

   void setModelo(java.lang.String modelo);

   java.lang.String getColor();

   void setColor(java.lang.String color);

   java.lang.String getTalla();

   void setTalla(java.lang.String talla);

   java.lang.String getTipo();

   void setTipo(java.lang.String tipo);

   java.lang.Long getProducto();

   void setProducto(java.lang.Long producto);

   java.sql.Timestamp getFecha();

   void setFecha(java.sql.Timestamp fecha);

   java.math.BigDecimal getCant();

   void setCant(java.math.BigDecimal cant);

   java.math.BigDecimal getDev();

   void setDev(java.math.BigDecimal dev);

   java.math.BigDecimal getCantfinal();

   void setCantfinal(java.math.BigDecimal cantfinal);

   java.math.BigDecimal getPreciouni();

   void setPreciouni(java.math.BigDecimal preciouni);

   java.math.BigDecimal getValorsinivaventas();

   void setValorsinivaventas(java.math.BigDecimal valorsinivaventas);

   java.math.BigDecimal getDescuentoventas();

   void setDescuentoventas(java.math.BigDecimal descuentoventas);

   java.math.BigDecimal getIvaventas();

   void setIvaventas(java.math.BigDecimal ivaventas);

   java.math.BigDecimal getTotalventas();

   void setTotalventas(java.math.BigDecimal totalventas);

   java.math.BigDecimal getValorsinivadev();

   void setValorsinivadev(java.math.BigDecimal valorsinivadev);

   java.math.BigDecimal getIvadev();

   void setIvadev(java.math.BigDecimal ivadev);

   java.math.BigDecimal getTotaldev();

   void setTotaldev(java.math.BigDecimal totaldev);

   java.lang.Long getModeloId();

   void setModeloId(java.lang.Long modeloId);

   java.lang.Long getColorId();

   void setColorId(java.lang.Long colorId);

   java.lang.Long getTipoproducto();

   void setTipoproducto(java.lang.Long tipoproducto);

   java.lang.Long getTallaId();

   void setTallaId(java.lang.Long tallaId);


}
