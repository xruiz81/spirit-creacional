package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ReunionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getOficinaId();

   void setOficinaId(java.lang.Long oficinaId);

   java.lang.Long getClienteId();

   void setClienteId(java.lang.Long clienteId);

   java.lang.String getProspectocliente();

   void setProspectocliente(java.lang.String prospectocliente);

   java.sql.Date getFecha();

   void setFecha(java.sql.Date fecha);

   java.sql.Date getFechaEnvio();

   void setFechaEnvio(java.sql.Date fechaEnvio);

   java.lang.Integer getNumEnviados();

   void setNumEnviados(java.lang.Integer numEnviados);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.Long getUsuarioCreacionId();

   void setUsuarioCreacionId(java.lang.Long usuarioCreacionId);

   java.sql.Date getFechaCreacion();

   void setFechaCreacion(java.sql.Date fechaCreacion);

   java.lang.Long getUsuarioActualizacionId();

   void setUsuarioActualizacionId(java.lang.Long usuarioActualizacionId);

   java.sql.Date getFechaActualizacion();

   void setFechaActualizacion(java.sql.Date fechaActualizacion);

   java.lang.Long getEjecutivoId();

   void setEjecutivoId(java.lang.Long ejecutivoId);

   java.lang.String getConCopia();

   void setConCopia(java.lang.String conCopia);

   java.lang.String getLugarReunion();

   void setLugarReunion(java.lang.String lugarReunion);


}
