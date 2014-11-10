package com.spirit.inventario.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class SolicitudTransferenciaData implements SolicitudTransferenciaIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.sql.Timestamp fechaDocumento;

   public java.sql.Timestamp getFechaDocumento() {
      return fechaDocumento;
   }

   public void setFechaDocumento(java.sql.Timestamp fechaDocumento) {
      this.fechaDocumento = fechaDocumento;
   }

   private java.sql.Timestamp fechaIngreso;

   public java.sql.Timestamp getFechaIngreso() {
      return fechaIngreso;
   }

   public void setFechaIngreso(java.sql.Timestamp fechaIngreso) {
      this.fechaIngreso = fechaIngreso;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.Long bodegaDesdeId;

   public java.lang.Long getBodegaDesdeId() {
      return bodegaDesdeId;
   }

   public void setBodegaDesdeId(java.lang.Long bodegaDesdeId) {
      this.bodegaDesdeId = bodegaDesdeId;
   }

   private java.lang.Long bodegaHaciaId;

   public java.lang.Long getBodegaHaciaId() {
      return bodegaHaciaId;
   }

   public void setBodegaHaciaId(java.lang.Long bodegaHaciaId) {
      this.bodegaHaciaId = bodegaHaciaId;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.Long usuarioId;

   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }
   public SolicitudTransferenciaData() {
   }

   public SolicitudTransferenciaData(com.spirit.inventario.entity.SolicitudTransferenciaIf value) {
      setId(value.getId());
      setFechaDocumento(value.getFechaDocumento());
      setFechaIngreso(value.getFechaIngreso());
      setCodigo(value.getCodigo());
      setBodegaDesdeId(value.getBodegaDesdeId());
      setBodegaHaciaId(value.getBodegaHaciaId());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setUsuarioId(value.getUsuarioId());
   }



    public java.lang.Long getPrimaryKey() {
        return getId();
    }

    public void setPrimaryKey(java.lang.Long pk) {
       setId(pk);
    }


   public String getPrimaryKeyParameters() {
      String parameters = "";
      parameters += "&id=" + getId();
      return parameters;
   }



	public String toString() {
		return ToStringer.toString((SolicitudTransferenciaIf)this);
	}
}
