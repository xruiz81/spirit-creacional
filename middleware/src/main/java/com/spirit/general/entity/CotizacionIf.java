package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CotizacionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getMonedaId();

   void setMonedaId(java.lang.Long monedaId);

   java.lang.Long getMonedaequivId();

   void setMonedaequivId(java.lang.Long monedaequivId);

   java.sql.Date getFecha();

   void setFecha(java.sql.Date fecha);

   java.math.BigDecimal getCotizacion();

   void setCotizacion(java.math.BigDecimal cotizacion);


}
