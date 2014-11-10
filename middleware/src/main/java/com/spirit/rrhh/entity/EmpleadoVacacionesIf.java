package com.spirit.rrhh.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EmpleadoVacacionesIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getEmpleadoId();

   void setEmpleadoId(java.lang.Long empleadoId);

   java.lang.Float getSaldoDias();

   void setSaldoDias(java.lang.Float saldoDias);

   java.sql.Timestamp getFechaInicio();

   void setFechaInicio(java.sql.Timestamp fechaInicio);

   java.sql.Timestamp getFechaFin();

   void setFechaFin(java.sql.Timestamp fechaFin);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.String getArchivoAdjunto();

   void setArchivoAdjunto(java.lang.String archivoAdjunto);


}
