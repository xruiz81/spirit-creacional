package com.spirit.contabilidad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PeriodoDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getStatus();

   void setStatus(java.lang.String status);

   java.lang.Long getPeriodoId();

   void setPeriodoId(java.lang.Long periodoId);

   java.lang.String getMes();

   void setMes(java.lang.String mes);

   java.lang.String getAnio();

   void setAnio(java.lang.String anio);


}
