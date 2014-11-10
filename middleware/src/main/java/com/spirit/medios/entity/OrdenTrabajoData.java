package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class OrdenTrabajoData implements OrdenTrabajoIf, Serializable    {


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

   private java.lang.String descripcion;

   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   private java.lang.Long oficinaId;

   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   private java.lang.Long clienteoficinaId;

   public java.lang.Long getClienteoficinaId() {
      return clienteoficinaId;
   }

   public void setClienteoficinaId(java.lang.Long clienteoficinaId) {
      this.clienteoficinaId = clienteoficinaId;
   }

   private java.lang.Long campanaId;

   public java.lang.Long getCampanaId() {
      return campanaId;
   }

   public void setCampanaId(java.lang.Long campanaId) {
      this.campanaId = campanaId;
   }

   private java.lang.Long empleadoId;

   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   private java.sql.Timestamp fecha;

   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   private java.sql.Timestamp fechalimite;

   public java.sql.Timestamp getFechalimite() {
      return fechalimite;
   }

   public void setFechalimite(java.sql.Timestamp fechalimite) {
      this.fechalimite = fechalimite;
   }

   private java.sql.Timestamp fechaentrega;

   public java.sql.Timestamp getFechaentrega() {
      return fechaentrega;
   }

   public void setFechaentrega(java.sql.Timestamp fechaentrega) {
      this.fechaentrega = fechaentrega;
   }

   private java.lang.String urlPropuesta;

   public java.lang.String getUrlPropuesta() {
      return urlPropuesta;
   }

   public void setUrlPropuesta(java.lang.String urlPropuesta) {
      this.urlPropuesta = urlPropuesta;
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

   private java.lang.Long usuarioCreacionId;

   public java.lang.Long getUsuarioCreacionId() {
      return usuarioCreacionId;
   }

   public void setUsuarioCreacionId(java.lang.Long usuarioCreacionId) {
      this.usuarioCreacionId = usuarioCreacionId;
   }

   private java.sql.Timestamp fechaCreacion;

   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.lang.Long usuarioActualizacionId;

   public java.lang.Long getUsuarioActualizacionId() {
      return usuarioActualizacionId;
   }

   public void setUsuarioActualizacionId(java.lang.Long usuarioActualizacionId) {
      this.usuarioActualizacionId = usuarioActualizacionId;
   }

   private java.sql.Timestamp fechaActualizacion;

   public java.sql.Timestamp getFechaActualizacion() {
      return fechaActualizacion;
   }

   public void setFechaActualizacion(java.sql.Timestamp fechaActualizacion) {
      this.fechaActualizacion = fechaActualizacion;
   }

   private java.lang.Long equipoId;

   public java.lang.Long getEquipoId() {
      return equipoId;
   }

   public void setEquipoId(java.lang.Long equipoId) {
      this.equipoId = equipoId;
   }

   private java.lang.String timetracker;

   public java.lang.String getTimetracker() {
      return timetracker;
   }

   public void setTimetracker(java.lang.String timetracker) {
      this.timetracker = timetracker;
   }
   public OrdenTrabajoData() {
   }

   public OrdenTrabajoData(com.spirit.medios.entity.OrdenTrabajoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setDescripcion(value.getDescripcion());
      setOficinaId(value.getOficinaId());
      setClienteoficinaId(value.getClienteoficinaId());
      setCampanaId(value.getCampanaId());
      setEmpleadoId(value.getEmpleadoId());
      setFecha(value.getFecha());
      setFechalimite(value.getFechalimite());
      setFechaentrega(value.getFechaentrega());
      setUrlPropuesta(value.getUrlPropuesta());
      setEstado(value.getEstado());
      setObservacion(value.getObservacion());
      setUsuarioCreacionId(value.getUsuarioCreacionId());
      setFechaCreacion(value.getFechaCreacion());
      setUsuarioActualizacionId(value.getUsuarioActualizacionId());
      setFechaActualizacion(value.getFechaActualizacion());
      setEquipoId(value.getEquipoId());
      setTimetracker(value.getTimetracker());
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
		return ToStringer.toString((OrdenTrabajoIf)this);
	}
}
