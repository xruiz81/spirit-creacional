package com.spirit.nomina.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class RolPagoData implements RolPagoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long tiporolId;

   public java.lang.Long getTiporolId() {
      return tiporolId;
   }

   public void setTiporolId(java.lang.Long tiporolId) {
      this.tiporolId = tiporolId;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
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

   private java.sql.Date fecha;

   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   private java.lang.Long tipocontratoId;

   public java.lang.Long getTipocontratoId() {
      return tipocontratoId;
   }

   public void setTipocontratoId(java.lang.Long tipocontratoId) {
      this.tipocontratoId = tipocontratoId;
   }

   private java.lang.String asientoGenerado;

   public java.lang.String getAsientoGenerado() {
      return asientoGenerado;
   }

   public void setAsientoGenerado(java.lang.String asientoGenerado) {
      this.asientoGenerado = asientoGenerado;
   }
   public RolPagoData() {
   }

   public RolPagoData(com.spirit.nomina.entity.RolPagoIf value) {
      setId(value.getId());
      setTiporolId(value.getTiporolId());
      setEstado(value.getEstado());
      setMes(value.getMes());
      setAnio(value.getAnio());
      setFecha(value.getFecha());
      setTipocontratoId(value.getTipocontratoId());
      setAsientoGenerado(value.getAsientoGenerado());
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
		return ToStringer.toString((RolPagoIf)this);
	}
}
