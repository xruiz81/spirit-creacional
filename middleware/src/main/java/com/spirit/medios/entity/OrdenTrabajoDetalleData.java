package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class OrdenTrabajoDetalleData implements OrdenTrabajoDetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long ordenId;

   public java.lang.Long getOrdenId() {
      return ordenId;
   }

   public void setOrdenId(java.lang.Long ordenId) {
      this.ordenId = ordenId;
   }

   private java.lang.Long subtipoId;

   public java.lang.Long getSubtipoId() {
      return subtipoId;
   }

   public void setSubtipoId(java.lang.Long subtipoId) {
      this.subtipoId = subtipoId;
   }

   private java.lang.Long equipoId;

   public java.lang.Long getEquipoId() {
      return equipoId;
   }

   public void setEquipoId(java.lang.Long equipoId) {
      this.equipoId = equipoId;
   }

   private java.lang.Long asignadoaId;

   public java.lang.Long getAsignadoaId() {
      return asignadoaId;
   }

   public void setAsignadoaId(java.lang.Long asignadoaId) {
      this.asignadoaId = asignadoaId;
   }

   private java.sql.Date fechalimite;

   public java.sql.Date getFechalimite() {
      return fechalimite;
   }

   public void setFechalimite(java.sql.Date fechalimite) {
      this.fechalimite = fechalimite;
   }

   private java.sql.Date fechaentrega;

   public java.sql.Date getFechaentrega() {
      return fechaentrega;
   }

   public void setFechaentrega(java.sql.Date fechaentrega) {
      this.fechaentrega = fechaentrega;
   }

   private java.lang.String urlDescripcion;

   public java.lang.String getUrlDescripcion() {
      return urlDescripcion;
   }

   public void setUrlDescripcion(java.lang.String urlDescripcion) {
      this.urlDescripcion = urlDescripcion;
   }

   private java.lang.String urlPropuesta;

   public java.lang.String getUrlPropuesta() {
      return urlPropuesta;
   }

   public void setUrlPropuesta(java.lang.String urlPropuesta) {
      this.urlPropuesta = urlPropuesta;
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

   private java.sql.Timestamp fecha;

   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }
   public OrdenTrabajoDetalleData() {
   }

   public OrdenTrabajoDetalleData(com.spirit.medios.entity.OrdenTrabajoDetalleIf value) {
      setId(value.getId());
      setOrdenId(value.getOrdenId());
      setSubtipoId(value.getSubtipoId());
      setEquipoId(value.getEquipoId());
      setAsignadoaId(value.getAsignadoaId());
      setFechalimite(value.getFechalimite());
      setFechaentrega(value.getFechaentrega());
      setUrlDescripcion(value.getUrlDescripcion());
      setUrlPropuesta(value.getUrlPropuesta());
      setDescripcion(value.getDescripcion());
      setEstado(value.getEstado());
      setFecha(value.getFecha());
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
		return ToStringer.toString((OrdenTrabajoDetalleIf)this);
	}
}
