package com.spirit.nomina.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ContratoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.sql.Date getFechaElaboracion();

   void setFechaElaboracion(java.sql.Date fechaElaboracion);

   java.lang.Long getTipocontratoId();

   void setTipocontratoId(java.lang.Long tipocontratoId);

   java.lang.Long getEmpleadoId();

   void setEmpleadoId(java.lang.Long empleadoId);

   java.sql.Date getFechaInicio();

   void setFechaInicio(java.sql.Date fechaInicio);

   java.sql.Date getFechaFin();

   void setFechaFin(java.sql.Date fechaFin);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.String getUrl();

   void setUrl(java.lang.String url);

   java.lang.Integer getFondoReservaDiasPrevios();

   void setFondoReservaDiasPrevios(java.lang.Integer fondoReservaDiasPrevios);


}
