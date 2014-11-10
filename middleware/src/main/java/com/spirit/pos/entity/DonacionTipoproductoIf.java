package com.spirit.pos.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface DonacionTipoproductoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getTipoproductoId();

   void setTipoproductoId(java.lang.Long tipoproductoId);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);


}
