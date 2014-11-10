package com.spirit.inventario.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PromedioUnitarioIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getPid();

   void setPid(java.lang.Long pid);

   java.lang.String getSku();

   void setSku(java.lang.String sku);

   java.lang.String getModelo();

   void setModelo(java.lang.String modelo);

   java.lang.String getTalla();

   void setTalla(java.lang.String talla);

   java.lang.String getColor();

   void setColor(java.lang.String color);

   java.sql.Timestamp getFecha();

   void setFecha(java.sql.Timestamp fecha);

   java.lang.Long getBodegaId();

   void setBodegaId(java.lang.Long bodegaId);

   java.math.BigDecimal getPromedioUnitario();

   void setPromedioUnitario(java.math.BigDecimal promedioUnitario);


}
