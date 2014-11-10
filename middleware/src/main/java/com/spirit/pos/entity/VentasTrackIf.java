package com.spirit.pos.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface VentasTrackIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.math.BigDecimal getValorTotal();

   void setValorTotal(java.math.BigDecimal valorTotal);

   java.lang.Long getCajasesionId();

   void setCajasesionId(java.lang.Long cajasesionId);

   java.sql.Timestamp getFechaVenta();

   void setFechaVenta(java.sql.Timestamp fechaVenta);


}
