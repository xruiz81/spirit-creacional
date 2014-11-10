package com.spirit.sri.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class SriClienteRetencionData implements SriClienteRetencionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String tipoRetencion;

   public java.lang.String getTipoRetencion() {
      return tipoRetencion;
   }

   public void setTipoRetencion(java.lang.String tipoRetencion) {
      this.tipoRetencion = tipoRetencion;
   }

   private java.math.BigDecimal porcentajeRetencion;

   public java.math.BigDecimal getPorcentajeRetencion() {
      return porcentajeRetencion;
   }

   public void setPorcentajeRetencion(java.math.BigDecimal porcentajeRetencion) {
      this.porcentajeRetencion = porcentajeRetencion;
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

   private java.lang.Long cuentaId;

   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }
   public SriClienteRetencionData() {
   }

   public SriClienteRetencionData(com.spirit.sri.entity.SriClienteRetencionIf value) {
      setId(value.getId());
      setTipoRetencion(value.getTipoRetencion());
      setPorcentajeRetencion(value.getPorcentajeRetencion());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setEstado(value.getEstado());
      setCuentaId(value.getCuentaId());
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
		return ToStringer.toString((SriClienteRetencionIf)this);
	}
}
