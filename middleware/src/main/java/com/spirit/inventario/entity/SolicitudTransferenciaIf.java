package com.spirit.inventario.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SolicitudTransferenciaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.sql.Timestamp getFechaDocumento();

   void setFechaDocumento(java.sql.Timestamp fechaDocumento);

   java.sql.Timestamp getFechaIngreso();

   void setFechaIngreso(java.sql.Timestamp fechaIngreso);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.Long getBodegaDesdeId();

   void setBodegaDesdeId(java.lang.Long bodegaDesdeId);

   java.lang.Long getBodegaHaciaId();

   void setBodegaHaciaId(java.lang.Long bodegaHaciaId);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);


}
