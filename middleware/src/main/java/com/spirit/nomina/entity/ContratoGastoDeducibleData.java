package com.spirit.nomina.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ContratoGastoDeducibleData implements ContratoGastoDeducibleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long contratoId;

   public java.lang.Long getContratoId() {
      return contratoId;
   }

   public void setContratoId(java.lang.Long contratoId) {
      this.contratoId = contratoId;
   }

   private java.lang.Long gastoDeducibleId;

   public java.lang.Long getGastoDeducibleId() {
      return gastoDeducibleId;
   }

   public void setGastoDeducibleId(java.lang.Long gastoDeducibleId) {
      this.gastoDeducibleId = gastoDeducibleId;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.sql.Date fecha;

   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }
   public ContratoGastoDeducibleData() {
   }

   public ContratoGastoDeducibleData(com.spirit.nomina.entity.ContratoGastoDeducibleIf value) {
      setId(value.getId());
      setContratoId(value.getContratoId());
      setGastoDeducibleId(value.getGastoDeducibleId());
      setValor(value.getValor());
      setFecha(value.getFecha());
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
		return ToStringer.toString((ContratoGastoDeducibleIf)this);
	}
}
