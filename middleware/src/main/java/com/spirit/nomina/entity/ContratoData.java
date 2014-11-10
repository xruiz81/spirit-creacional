package com.spirit.nomina.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ContratoData implements ContratoIf, Serializable    {


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

   private java.sql.Date fechaElaboracion;

   public java.sql.Date getFechaElaboracion() {
      return fechaElaboracion;
   }

   public void setFechaElaboracion(java.sql.Date fechaElaboracion) {
      this.fechaElaboracion = fechaElaboracion;
   }

   private java.lang.Long tipocontratoId;

   public java.lang.Long getTipocontratoId() {
      return tipocontratoId;
   }

   public void setTipocontratoId(java.lang.Long tipocontratoId) {
      this.tipocontratoId = tipocontratoId;
   }

   private java.lang.Long empleadoId;

   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   private java.sql.Date fechaInicio;

   public java.sql.Date getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Date fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   private java.sql.Date fechaFin;

   public java.sql.Date getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Date fechaFin) {
      this.fechaFin = fechaFin;
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

   private java.lang.String url;

   public java.lang.String getUrl() {
      return url;
   }

   public void setUrl(java.lang.String url) {
      this.url = url;
   }

   private java.lang.Integer fondoReservaDiasPrevios;

   public java.lang.Integer getFondoReservaDiasPrevios() {
      return fondoReservaDiasPrevios;
   }

   public void setFondoReservaDiasPrevios(java.lang.Integer fondoReservaDiasPrevios) {
      this.fondoReservaDiasPrevios = fondoReservaDiasPrevios;
   }
   public ContratoData() {
   }

   public ContratoData(com.spirit.nomina.entity.ContratoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setFechaElaboracion(value.getFechaElaboracion());
      setTipocontratoId(value.getTipocontratoId());
      setEmpleadoId(value.getEmpleadoId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setEstado(value.getEstado());
      setObservacion(value.getObservacion());
      setUrl(value.getUrl());
      setFondoReservaDiasPrevios(value.getFondoReservaDiasPrevios());
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
		return ToStringer.toString((ContratoIf)this);
	}
}
