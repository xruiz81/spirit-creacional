package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CampanaData implements CampanaIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String nombre;

   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   private java.lang.Long clienteId;

   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }

   private java.sql.Date fechaini;

   public java.sql.Date getFechaini() {
      return fechaini;
   }

   public void setFechaini(java.sql.Date fechaini) {
      this.fechaini = fechaini;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String publicoObjetivo;

   public java.lang.String getPublicoObjetivo() {
      return publicoObjetivo;
   }

   public void setPublicoObjetivo(java.lang.String publicoObjetivo) {
      this.publicoObjetivo = publicoObjetivo;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
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
   public CampanaData() {
   }

   public CampanaData(com.spirit.medios.entity.CampanaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setClienteId(value.getClienteId());
      setFechaini(value.getFechaini());
      setEstado(value.getEstado());
      setPublicoObjetivo(value.getPublicoObjetivo());
      setObservacion(value.getObservacion());
      setUsuarioCreacionId(value.getUsuarioCreacionId());
      setFechaCreacion(value.getFechaCreacion());
      setUsuarioActualizacionId(value.getUsuarioActualizacionId());
      setFechaActualizacion(value.getFechaActualizacion());
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
		return ToStringer.toString((CampanaIf)this);
	}
}
