package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface MapaComercialIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getPlanMedioDetalleId();

   void setPlanMedioDetalleId(java.lang.Long planMedioDetalleId);

   java.sql.Date getFecha();

   void setFecha(java.sql.Date fecha);

   java.lang.Integer getFrecuencia();

   void setFrecuencia(java.lang.Integer frecuencia);


}
