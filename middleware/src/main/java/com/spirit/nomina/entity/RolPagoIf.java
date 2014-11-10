package com.spirit.nomina.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface RolPagoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getTiporolId();

   void setTiporolId(java.lang.Long tiporolId);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getMes();

   void setMes(java.lang.String mes);

   java.lang.String getAnio();

   void setAnio(java.lang.String anio);

   java.sql.Date getFecha();

   void setFecha(java.sql.Date fecha);

   java.lang.Long getTipocontratoId();

   void setTipocontratoId(java.lang.Long tipocontratoId);

   java.lang.String getAsientoGenerado();

   void setAsientoGenerado(java.lang.String asientoGenerado);


}
