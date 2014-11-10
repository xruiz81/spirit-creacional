package com.spirit.cartera.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CarteraAfectaData implements CarteraAfectaIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long carteradetalleId;

   public java.lang.Long getCarteradetalleId() {
      return carteradetalleId;
   }

   public void setCarteradetalleId(java.lang.Long carteradetalleId) {
      this.carteradetalleId = carteradetalleId;
   }

   private java.lang.Long carteraafectaId;

   public java.lang.Long getCarteraafectaId() {
      return carteraafectaId;
   }

   public void setCarteraafectaId(java.lang.Long carteraafectaId) {
      this.carteraafectaId = carteraafectaId;
   }

   private java.lang.Long usuarioId;

   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.sql.Date fechaCreacion;

   public java.sql.Date getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Date fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.sql.Date fechaAplicacion;

   public java.sql.Date getFechaAplicacion() {
      return fechaAplicacion;
   }

   public void setFechaAplicacion(java.sql.Date fechaAplicacion) {
      this.fechaAplicacion = fechaAplicacion;
   }

   private java.lang.String cartera;

   public java.lang.String getCartera() {
      return cartera;
   }

   public void setCartera(java.lang.String cartera) {
      this.cartera = cartera;
   }
   public CarteraAfectaData() {
   }

   public CarteraAfectaData(com.spirit.cartera.entity.CarteraAfectaIf value) {
      setId(value.getId());
      setCarteradetalleId(value.getCarteradetalleId());
      setCarteraafectaId(value.getCarteraafectaId());
      setUsuarioId(value.getUsuarioId());
      setValor(value.getValor());
      setFechaCreacion(value.getFechaCreacion());
      setFechaAplicacion(value.getFechaAplicacion());
      setCartera(value.getCartera());
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
		return ToStringer.toString((CarteraAfectaIf)this);
	}
}
