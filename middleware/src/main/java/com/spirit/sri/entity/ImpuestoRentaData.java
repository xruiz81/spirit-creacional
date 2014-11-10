package com.spirit.sri.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ImpuestoRentaData implements ImpuestoRentaIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.math.BigDecimal porcentaje;

   public java.math.BigDecimal getPorcentaje() {
      return porcentaje;
   }

   public void setPorcentaje(java.math.BigDecimal porcentaje) {
      this.porcentaje = porcentaje;
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
   public ImpuestoRentaData() {
   }

   public ImpuestoRentaData(com.spirit.sri.entity.ImpuestoRentaIf value) {
      setId(value.getId());
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
		return ToStringer.toString((ImpuestoRentaIf)this);
	}
}
