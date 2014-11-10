package com.spirit.cartera.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class DepositoData implements DepositoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long oficinaId;

   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   private java.lang.Long cuentabancariaId;

   public java.lang.Long getCuentabancariaId() {
      return cuentabancariaId;
   }

   public void setCuentabancariaId(java.lang.Long cuentabancariaId) {
      this.cuentabancariaId = cuentabancariaId;
   }

   private java.sql.Date fechaCreacion;

   public java.sql.Date getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Date fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.sql.Date fechaDeposito;

   public java.sql.Date getFechaDeposito() {
      return fechaDeposito;
   }

   public void setFechaDeposito(java.sql.Date fechaDeposito) {
      this.fechaDeposito = fechaDeposito;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
   public DepositoData() {
   }

   public DepositoData(com.spirit.cartera.entity.DepositoIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setCuentabancariaId(value.getCuentabancariaId());
      setFechaCreacion(value.getFechaCreacion());
      setFechaDeposito(value.getFechaDeposito());
      setValor(value.getValor());
      setEstado(value.getEstado());
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
		return ToStringer.toString((DepositoIf)this);
	}
}
