package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TiempoParcialDotDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getFecha();

   void setFecha(java.lang.Long fecha);

   java.lang.Long getHoraInicio();

   void setHoraInicio(java.lang.Long horaInicio);

   java.lang.Long getHoraFin();

   void setHoraFin(java.lang.Long horaFin);

   java.lang.Long getTiempo();

   void setTiempo(java.lang.Long tiempo);

   java.lang.Long getIdTiempoParcialDot();

   void setIdTiempoParcialDot(java.lang.Long idTiempoParcialDot);


}
