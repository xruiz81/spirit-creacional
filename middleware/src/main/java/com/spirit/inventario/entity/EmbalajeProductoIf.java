package com.spirit.inventario.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EmbalajeProductoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getProductoId();

   void setProductoId(java.lang.Long productoId);

   java.lang.Long getEmbalajeId();

   void setEmbalajeId(java.lang.Long embalajeId);

   java.math.BigDecimal getCantidad();

   void setCantidad(java.math.BigDecimal cantidad);

   java.math.BigDecimal getAreaCubica();

   void setAreaCubica(java.math.BigDecimal areaCubica);

   java.lang.String getTipoManejo();

   void setTipoManejo(java.lang.String tipoManejo);


}
