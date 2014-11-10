package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OrdenTrabajoDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getOrdenId();

   void setOrdenId(java.lang.Long ordenId);

   java.lang.Long getSubtipoId();

   void setSubtipoId(java.lang.Long subtipoId);

   java.lang.Long getEquipoId();

   void setEquipoId(java.lang.Long equipoId);

   java.lang.Long getAsignadoaId();

   void setAsignadoaId(java.lang.Long asignadoaId);

   java.sql.Date getFechalimite();

   void setFechalimite(java.sql.Date fechalimite);

   java.sql.Date getFechaentrega();

   void setFechaentrega(java.sql.Date fechaentrega);

   java.lang.String getUrlDescripcion();

   void setUrlDescripcion(java.lang.String urlDescripcion);

   java.lang.String getUrlPropuesta();

   void setUrlPropuesta(java.lang.String urlPropuesta);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.sql.Timestamp getFecha();

   void setFecha(java.sql.Timestamp fecha);


}
