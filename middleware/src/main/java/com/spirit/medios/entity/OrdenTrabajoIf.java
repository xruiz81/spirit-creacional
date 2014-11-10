package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OrdenTrabajoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.lang.Long getOficinaId();

   void setOficinaId(java.lang.Long oficinaId);

   java.lang.Long getClienteoficinaId();

   void setClienteoficinaId(java.lang.Long clienteoficinaId);

   java.lang.Long getCampanaId();

   void setCampanaId(java.lang.Long campanaId);

   java.lang.Long getEmpleadoId();

   void setEmpleadoId(java.lang.Long empleadoId);

   java.sql.Timestamp getFecha();

   void setFecha(java.sql.Timestamp fecha);

   java.sql.Timestamp getFechalimite();

   void setFechalimite(java.sql.Timestamp fechalimite);

   java.sql.Timestamp getFechaentrega();

   void setFechaentrega(java.sql.Timestamp fechaentrega);

   java.lang.String getUrlPropuesta();

   void setUrlPropuesta(java.lang.String urlPropuesta);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.Long getUsuarioCreacionId();

   void setUsuarioCreacionId(java.lang.Long usuarioCreacionId);

   java.sql.Timestamp getFechaCreacion();

   void setFechaCreacion(java.sql.Timestamp fechaCreacion);

   java.lang.Long getUsuarioActualizacionId();

   void setUsuarioActualizacionId(java.lang.Long usuarioActualizacionId);

   java.sql.Timestamp getFechaActualizacion();

   void setFechaActualizacion(java.sql.Timestamp fechaActualizacion);

   java.lang.Long getEquipoId();

   void setEquipoId(java.lang.Long equipoId);

   java.lang.String getTimetracker();

   void setTimetracker(java.lang.String timetracker);


}
