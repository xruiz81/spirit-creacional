package com.spirit.contabilidad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class SaldoCuentaData implements SaldoCuentaIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long cuentaId;

   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }

   private java.lang.Long periodoId;

   public java.lang.Long getPeriodoId() {
      return periodoId;
   }

   public void setPeriodoId(java.lang.Long periodoId) {
      this.periodoId = periodoId;
   }

   private java.lang.String anio;

   public java.lang.String getAnio() {
      return anio;
   }

   public void setAnio(java.lang.String anio) {
      this.anio = anio;
   }

   private java.lang.String mes;

   public java.lang.String getMes() {
      return mes;
   }

   public void setMes(java.lang.String mes) {
      this.mes = mes;
   }

   private java.math.BigDecimal valordebe;

   public java.math.BigDecimal getValordebe() {
      return valordebe;
   }

   public void setValordebe(java.math.BigDecimal valordebe) {
      this.valordebe = valordebe;
   }

   private java.math.BigDecimal valorhaber;

   public java.math.BigDecimal getValorhaber() {
      return valorhaber;
   }

   public void setValorhaber(java.math.BigDecimal valorhaber) {
      this.valorhaber = valorhaber;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }
   public SaldoCuentaData() {
   }

   public SaldoCuentaData(com.spirit.contabilidad.entity.SaldoCuentaIf value) {
      setId(value.getId());
      setCuentaId(value.getCuentaId());
      setPeriodoId(value.getPeriodoId());
      setAnio(value.getAnio());
      setMes(value.getMes());
      setValordebe(value.getValordebe());
      setValorhaber(value.getValorhaber());
      setValor(value.getValor());
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
		return ToStringer.toString((SaldoCuentaIf)this);
	}
}
