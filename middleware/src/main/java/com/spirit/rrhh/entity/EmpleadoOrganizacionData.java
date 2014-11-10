package com.spirit.rrhh.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class EmpleadoOrganizacionData implements EmpleadoOrganizacionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long empleadoId;

   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   private java.lang.Long departamento;

   public java.lang.Long getDepartamento() {
      return departamento;
   }

   public void setDepartamento(java.lang.Long departamento) {
      this.departamento = departamento;
   }

   private java.lang.Long tipoEmpleadoId;

   public java.lang.Long getTipoEmpleadoId() {
      return tipoEmpleadoId;
   }

   public void setTipoEmpleadoId(java.lang.Long tipoEmpleadoId) {
      this.tipoEmpleadoId = tipoEmpleadoId;
   }

   private java.sql.Timestamp fechaInicio;

   public java.sql.Timestamp getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Timestamp fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   private java.sql.Timestamp fechaFin;

   public java.sql.Timestamp getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Timestamp fechaFin) {
      this.fechaFin = fechaFin;
   }

   private java.lang.String descripcion;

   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   private java.lang.String archivoAdjunto;

   public java.lang.String getArchivoAdjunto() {
      return archivoAdjunto;
   }

   public void setArchivoAdjunto(java.lang.String archivoAdjunto) {
      this.archivoAdjunto = archivoAdjunto;
   }
   public EmpleadoOrganizacionData() {
   }

   public EmpleadoOrganizacionData(com.spirit.rrhh.entity.EmpleadoOrganizacionIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setDepartamento(value.getDepartamento());
      setTipoEmpleadoId(value.getTipoEmpleadoId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setDescripcion(value.getDescripcion());
      setArchivoAdjunto(value.getArchivoAdjunto());
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
		return ToStringer.toString((EmpleadoOrganizacionIf)this);
	}
}
