package com.spirit.rrhh.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class EmpleadoVacacionesData implements EmpleadoVacacionesIf, Serializable    {


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

   private java.lang.Float saldoDias;

   public java.lang.Float getSaldoDias() {
      return saldoDias;
   }

   public void setSaldoDias(java.lang.Float saldoDias) {
      this.saldoDias = saldoDias;
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

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.lang.String archivoAdjunto;

   public java.lang.String getArchivoAdjunto() {
      return archivoAdjunto;
   }

   public void setArchivoAdjunto(java.lang.String archivoAdjunto) {
      this.archivoAdjunto = archivoAdjunto;
   }
   public EmpleadoVacacionesData() {
   }

   public EmpleadoVacacionesData(com.spirit.rrhh.entity.EmpleadoVacacionesIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setSaldoDias(value.getSaldoDias());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setObservacion(value.getObservacion());
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
		return ToStringer.toString((EmpleadoVacacionesIf)this);
	}
}
