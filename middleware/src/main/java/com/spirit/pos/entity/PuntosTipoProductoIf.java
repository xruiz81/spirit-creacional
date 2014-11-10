package com.spirit.pos.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PuntosTipoProductoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getTipoProductoId();

   void setTipoProductoId(java.lang.Long tipoProductoId);

   java.lang.Long getTarjetaTipoId();

   void setTarjetaTipoId(java.lang.Long tarjetaTipoId);

   java.math.BigDecimal getPuntosReferido();

   void setPuntosReferido(java.math.BigDecimal puntosReferido);

   java.math.BigDecimal getPuntosCompras();

   void setPuntosCompras(java.math.BigDecimal puntosCompras);

   java.math.BigDecimal getPorcentajeDineroReferido();

   void setPorcentajeDineroReferido(java.math.BigDecimal porcentajeDineroReferido);

   java.math.BigDecimal getPorcentajeDineroCompras();

   void setPorcentajeDineroCompras(java.math.BigDecimal porcentajeDineroCompras);


}
