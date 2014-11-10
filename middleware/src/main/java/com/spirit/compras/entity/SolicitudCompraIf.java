package com.spirit.compras.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SolicitudCompraIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getTipodocumentoId();

   void setTipodocumentoId(java.lang.Long tipodocumentoId);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.Long getOficinaId();

   void setOficinaId(java.lang.Long oficinaId);

   java.lang.Long getBodegaId();

   void setBodegaId(java.lang.Long bodegaId);

   java.lang.Long getEmpleadoId();

   void setEmpleadoId(java.lang.Long empleadoId);

   java.sql.Date getFecha();

   void setFecha(java.sql.Date fecha);

   java.sql.Date getFechaEntrega();

   void setFechaEntrega(java.sql.Date fechaEntrega);

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.String getEstadoBpm();

   void setEstadoBpm(java.lang.String estadoBpm);

   java.lang.String getTipoReferencia();

   void setTipoReferencia(java.lang.String tipoReferencia);

   java.lang.String getReferencia();

   void setReferencia(java.lang.String referencia);


}
