package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ReunionData implements ReunionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long oficinaId;

   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   private java.lang.Long clienteId;

   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }

   private java.lang.String prospectocliente;

   public java.lang.String getProspectocliente() {
      return prospectocliente;
   }

   public void setProspectocliente(java.lang.String prospectocliente) {
      this.prospectocliente = prospectocliente;
   }

   private java.sql.Date fecha;

   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   private java.sql.Date fechaEnvio;

   public java.sql.Date getFechaEnvio() {
      return fechaEnvio;
   }

   public void setFechaEnvio(java.sql.Date fechaEnvio) {
      this.fechaEnvio = fechaEnvio;
   }

   private java.lang.Integer numEnviados;

   public java.lang.Integer getNumEnviados() {
      return numEnviados;
   }

   public void setNumEnviados(java.lang.Integer numEnviados) {
      this.numEnviados = numEnviados;
   }

   private java.lang.String descripcion;

   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.Long usuarioCreacionId;

   public java.lang.Long getUsuarioCreacionId() {
      return usuarioCreacionId;
   }

   public void setUsuarioCreacionId(java.lang.Long usuarioCreacionId) {
      this.usuarioCreacionId = usuarioCreacionId;
   }

   private java.sql.Date fechaCreacion;

   public java.sql.Date getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Date fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.lang.Long usuarioActualizacionId;

   public java.lang.Long getUsuarioActualizacionId() {
      return usuarioActualizacionId;
   }

   public void setUsuarioActualizacionId(java.lang.Long usuarioActualizacionId) {
      this.usuarioActualizacionId = usuarioActualizacionId;
   }

   private java.sql.Date fechaActualizacion;

   public java.sql.Date getFechaActualizacion() {
      return fechaActualizacion;
   }

   public void setFechaActualizacion(java.sql.Date fechaActualizacion) {
      this.fechaActualizacion = fechaActualizacion;
   }

   private java.lang.Long ejecutivoId;

   public java.lang.Long getEjecutivoId() {
      return ejecutivoId;
   }

   public void setEjecutivoId(java.lang.Long ejecutivoId) {
      this.ejecutivoId = ejecutivoId;
   }

   private java.lang.String conCopia;

   public java.lang.String getConCopia() {
      return conCopia;
   }

   public void setConCopia(java.lang.String conCopia) {
      this.conCopia = conCopia;
   }

   private java.lang.String lugarReunion;

   public java.lang.String getLugarReunion() {
      return lugarReunion;
   }

   public void setLugarReunion(java.lang.String lugarReunion) {
      this.lugarReunion = lugarReunion;
   }
   public ReunionData() {
   }

   public ReunionData(com.spirit.medios.entity.ReunionIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setClienteId(value.getClienteId());
      setProspectocliente(value.getProspectocliente());
      setFecha(value.getFecha());
      setFechaEnvio(value.getFechaEnvio());
      setNumEnviados(value.getNumEnviados());
      setDescripcion(value.getDescripcion());
      setEstado(value.getEstado());
      setUsuarioCreacionId(value.getUsuarioCreacionId());
      setFechaCreacion(value.getFechaCreacion());
      setUsuarioActualizacionId(value.getUsuarioActualizacionId());
      setFechaActualizacion(value.getFechaActualizacion());
      setEjecutivoId(value.getEjecutivoId());
      setConCopia(value.getConCopia());
      setLugarReunion(value.getLugarReunion());
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
		return ToStringer.toString((ReunionIf)this);
	}
}
