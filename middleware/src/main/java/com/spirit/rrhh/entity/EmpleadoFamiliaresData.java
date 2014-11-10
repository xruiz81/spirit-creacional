package com.spirit.rrhh.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class EmpleadoFamiliaresData implements EmpleadoFamiliaresIf, Serializable    {


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

   private java.lang.String tipo;

   public java.lang.String getTipo() {
      return tipo;
   }

   public void setTipo(java.lang.String tipo) {
      this.tipo = tipo;
   }

   private java.lang.String apellidos;

   public java.lang.String getApellidos() {
      return apellidos;
   }

   public void setApellidos(java.lang.String apellidos) {
      this.apellidos = apellidos;
   }

   private java.lang.String nombres;

   public java.lang.String getNombres() {
      return nombres;
   }

   public void setNombres(java.lang.String nombres) {
      this.nombres = nombres;
   }

   private java.sql.Timestamp fechaNacimiento;

   public java.sql.Timestamp getFechaNacimiento() {
      return fechaNacimiento;
   }

   public void setFechaNacimiento(java.sql.Timestamp fechaNacimiento) {
      this.fechaNacimiento = fechaNacimiento;
   }

   private java.lang.String cedulaIdentidad;

   public java.lang.String getCedulaIdentidad() {
      return cedulaIdentidad;
   }

   public void setCedulaIdentidad(java.lang.String cedulaIdentidad) {
      this.cedulaIdentidad = cedulaIdentidad;
   }

   private java.lang.String ocupacion;

   public java.lang.String getOcupacion() {
      return ocupacion;
   }

   public void setOcupacion(java.lang.String ocupacion) {
      this.ocupacion = ocupacion;
   }

   private java.lang.String nivelEstudios;

   public java.lang.String getNivelEstudios() {
      return nivelEstudios;
   }

   public void setNivelEstudios(java.lang.String nivelEstudios) {
      this.nivelEstudios = nivelEstudios;
   }

   private java.lang.String trabaja;

   public java.lang.String getTrabaja() {
      return trabaja;
   }

   public void setTrabaja(java.lang.String trabaja) {
      this.trabaja = trabaja;
   }

   private java.lang.String nombreInstitucion;

   public java.lang.String getNombreInstitucion() {
      return nombreInstitucion;
   }

   public void setNombreInstitucion(java.lang.String nombreInstitucion) {
      this.nombreInstitucion = nombreInstitucion;
   }

   private java.lang.String embarazo;

   public java.lang.String getEmbarazo() {
      return embarazo;
   }

   public void setEmbarazo(java.lang.String embarazo) {
      this.embarazo = embarazo;
   }

   private java.sql.Timestamp fechaParto;

   public java.sql.Timestamp getFechaParto() {
      return fechaParto;
   }

   public void setFechaParto(java.sql.Timestamp fechaParto) {
      this.fechaParto = fechaParto;
   }

   private java.lang.String ultimoAnio;

   public java.lang.String getUltimoAnio() {
      return ultimoAnio;
   }

   public void setUltimoAnio(java.lang.String ultimoAnio) {
      this.ultimoAnio = ultimoAnio;
   }
   public EmpleadoFamiliaresData() {
   }

   public EmpleadoFamiliaresData(com.spirit.rrhh.entity.EmpleadoFamiliaresIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setTipo(value.getTipo());
      setApellidos(value.getApellidos());
      setNombres(value.getNombres());
      setFechaNacimiento(value.getFechaNacimiento());
      setCedulaIdentidad(value.getCedulaIdentidad());
      setOcupacion(value.getOcupacion());
      setNivelEstudios(value.getNivelEstudios());
      setTrabaja(value.getTrabaja());
      setNombreInstitucion(value.getNombreInstitucion());
      setEmbarazo(value.getEmbarazo());
      setFechaParto(value.getFechaParto());
      setUltimoAnio(value.getUltimoAnio());
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
		return ToStringer.toString((EmpleadoFamiliaresIf)this);
	}
}
