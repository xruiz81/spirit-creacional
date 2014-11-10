package com.spirit.compras.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class SolicitudCompraData implements SolicitudCompraIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long tipodocumentoId;

   public java.lang.Long getTipodocumentoId() {
      return tipodocumentoId;
   }

   public void setTipodocumentoId(java.lang.Long tipodocumentoId) {
      this.tipodocumentoId = tipodocumentoId;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.Long oficinaId;

   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   private java.lang.Long bodegaId;

   public java.lang.Long getBodegaId() {
      return bodegaId;
   }

   public void setBodegaId(java.lang.Long bodegaId) {
      this.bodegaId = bodegaId;
   }

   private java.lang.Long empleadoId;

   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   private java.sql.Date fecha;

   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   private java.sql.Date fechaEntrega;

   public java.sql.Date getFechaEntrega() {
      return fechaEntrega;
   }

   public void setFechaEntrega(java.sql.Date fechaEntrega) {
      this.fechaEntrega = fechaEntrega;
   }

   private java.lang.Long usuarioId;

   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.lang.String estadoBpm;

   public java.lang.String getEstadoBpm() {
      return estadoBpm;
   }

   public void setEstadoBpm(java.lang.String estadoBpm) {
      this.estadoBpm = estadoBpm;
   }

   private java.lang.String tipoReferencia;

   public java.lang.String getTipoReferencia() {
      return tipoReferencia;
   }

   public void setTipoReferencia(java.lang.String tipoReferencia) {
      this.tipoReferencia = tipoReferencia;
   }

   private java.lang.String referencia;

   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }
   public SolicitudCompraData() {
   }

   public SolicitudCompraData(com.spirit.compras.entity.SolicitudCompraIf value) {
      setId(value.getId());
      setTipodocumentoId(value.getTipodocumentoId());
      setCodigo(value.getCodigo());
      setOficinaId(value.getOficinaId());
      setBodegaId(value.getBodegaId());
      setEmpleadoId(value.getEmpleadoId());
      setFecha(value.getFecha());
      setFechaEntrega(value.getFechaEntrega());
      setUsuarioId(value.getUsuarioId());
      setEstado(value.getEstado());
      setObservacion(value.getObservacion());
      setEstadoBpm(value.getEstadoBpm());
      setTipoReferencia(value.getTipoReferencia());
      setReferencia(value.getReferencia());
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
		return ToStringer.toString((SolicitudCompraIf)this);
	}
}
