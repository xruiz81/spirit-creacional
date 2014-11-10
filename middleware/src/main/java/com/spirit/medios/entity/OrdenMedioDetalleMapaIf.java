package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OrdenMedioDetalleMapaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getOrdenMedioDetalleId();

   void setOrdenMedioDetalleId(java.lang.Long ordenMedioDetalleId);

   java.sql.Timestamp getFecha();

   void setFecha(java.sql.Timestamp fecha);

   java.lang.Integer getFrecuencia();

   void setFrecuencia(java.lang.Integer frecuencia);


}
