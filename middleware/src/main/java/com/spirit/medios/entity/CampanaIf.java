package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CampanaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.Long getClienteId();

   void setClienteId(java.lang.Long clienteId);

   java.sql.Date getFechaini();

   void setFechaini(java.sql.Date fechaini);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getPublicoObjetivo();

   void setPublicoObjetivo(java.lang.String publicoObjetivo);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.Long getUsuarioCreacionId();

   void setUsuarioCreacionId(java.lang.Long usuarioCreacionId);

   java.sql.Date getFechaCreacion();

   void setFechaCreacion(java.sql.Date fechaCreacion);

   java.lang.Long getUsuarioActualizacionId();

   void setUsuarioActualizacionId(java.lang.Long usuarioActualizacionId);

   java.sql.Date getFechaActualizacion();

   void setFechaActualizacion(java.sql.Date fechaActualizacion);


}
