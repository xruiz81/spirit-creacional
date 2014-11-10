package com.spirit.rrhh.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class EmpleadoFormacionData implements EmpleadoFormacionIf, Serializable    {


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

   private java.lang.String nivel;

   public java.lang.String getNivel() {
      return nivel;
   }

   public void setNivel(java.lang.String nivel) {
      this.nivel = nivel;
   }

   private java.lang.String ultimoAnio;

   public java.lang.String getUltimoAnio() {
      return ultimoAnio;
   }

   public void setUltimoAnio(java.lang.String ultimoAnio) {
      this.ultimoAnio = ultimoAnio;
   }

   private java.sql.Timestamp fechaGraduacion;

   public java.sql.Timestamp getFechaGraduacion() {
      return fechaGraduacion;
   }

   public void setFechaGraduacion(java.sql.Timestamp fechaGraduacion) {
      this.fechaGraduacion = fechaGraduacion;
   }

   private java.lang.String tituloObtenido;

   public java.lang.String getTituloObtenido() {
      return tituloObtenido;
   }

   public void setTituloObtenido(java.lang.String tituloObtenido) {
      this.tituloObtenido = tituloObtenido;
   }

   private java.lang.String institucion;

   public java.lang.String getInstitucion() {
      return institucion;
   }

   public void setInstitucion(java.lang.String institucion) {
      this.institucion = institucion;
   }

   private java.lang.Long ciudadId;

   public java.lang.Long getCiudadId() {
      return ciudadId;
   }

   public void setCiudadId(java.lang.Long ciudadId) {
      this.ciudadId = ciudadId;
   }
   public EmpleadoFormacionData() {
   }

   public EmpleadoFormacionData(com.spirit.rrhh.entity.EmpleadoFormacionIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setNivel(value.getNivel());
      setUltimoAnio(value.getUltimoAnio());
      setFechaGraduacion(value.getFechaGraduacion());
      setTituloObtenido(value.getTituloObtenido());
      setInstitucion(value.getInstitucion());
      setCiudadId(value.getCiudadId());
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
		return ToStringer.toString((EmpleadoFormacionIf)this);
	}
}
