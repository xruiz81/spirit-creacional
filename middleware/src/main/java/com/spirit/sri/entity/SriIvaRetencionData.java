package com.spirit.sri.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class SriIvaRetencionData implements SriIvaRetencionIf, Serializable    {


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

   private java.lang.String concepto;

   public java.lang.String getConcepto() {
      return concepto;
   }

   public void setConcepto(java.lang.String concepto) {
      this.concepto = concepto;
   }

   private java.math.BigDecimal porcentaje;

   public java.math.BigDecimal getPorcentaje() {
      return porcentaje;
   }

   public void setPorcentaje(java.math.BigDecimal porcentaje) {
      this.porcentaje = porcentaje;
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
   public SriIvaRetencionData() {
   }

   public SriIvaRetencionData(com.spirit.sri.entity.SriIvaRetencionIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setConcepto(value.getConcepto());
      setPorcentaje(value.getPorcentaje());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
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
		return ToStringer.toString((SriIvaRetencionIf)this);
	}
}
