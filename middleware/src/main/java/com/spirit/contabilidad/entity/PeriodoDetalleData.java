package com.spirit.contabilidad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PeriodoDetalleData implements PeriodoDetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String status;

   public java.lang.String getStatus() {
      return status;
   }

   public void setStatus(java.lang.String status) {
      this.status = status;
   }

   private java.lang.Long periodoId;

   public java.lang.Long getPeriodoId() {
      return periodoId;
   }

   public void setPeriodoId(java.lang.Long periodoId) {
      this.periodoId = periodoId;
   }

   private java.lang.String mes;

   public java.lang.String getMes() {
      return mes;
   }

   public void setMes(java.lang.String mes) {
      this.mes = mes;
   }

   private java.lang.String anio;

   public java.lang.String getAnio() {
      return anio;
   }

   public void setAnio(java.lang.String anio) {
      this.anio = anio;
   }
   public PeriodoDetalleData() {
   }

   public PeriodoDetalleData(com.spirit.contabilidad.entity.PeriodoDetalleIf value) {
      setId(value.getId());
      setStatus(value.getStatus());
      setPeriodoId(value.getPeriodoId());
      setMes(value.getMes());
      setAnio(value.getAnio());
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
		return ToStringer.toString((PeriodoDetalleIf)this);
	}
}
