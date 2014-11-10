package com.spirit.inventario.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class GiftcardMovimientoData implements GiftcardMovimientoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long giftcardId;

   public java.lang.Long getGiftcardId() {
      return giftcardId;
   }

   public void setGiftcardId(java.lang.Long giftcardId) {
      this.giftcardId = giftcardId;
   }

   private java.math.BigDecimal saldoAnterior;

   public java.math.BigDecimal getSaldoAnterior() {
      return saldoAnterior;
   }

   public void setSaldoAnterior(java.math.BigDecimal saldoAnterior) {
      this.saldoAnterior = saldoAnterior;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.sql.Timestamp fechaMovimiento;

   public java.sql.Timestamp getFechaMovimiento() {
      return fechaMovimiento;
   }

   public void setFechaMovimiento(java.sql.Timestamp fechaMovimiento) {
      this.fechaMovimiento = fechaMovimiento;
   }

   private java.lang.Long transaccionId;

   public java.lang.Long getTransaccionId() {
      return transaccionId;
   }

   public void setTransaccionId(java.lang.Long transaccionId) {
      this.transaccionId = transaccionId;
   }

   private java.lang.Long tipoDocumentoId;

   public java.lang.Long getTipoDocumentoId() {
      return tipoDocumentoId;
   }

   public void setTipoDocumentoId(java.lang.Long tipoDocumentoId) {
      this.tipoDocumentoId = tipoDocumentoId;
   }
   public GiftcardMovimientoData() {
   }

   public GiftcardMovimientoData(com.spirit.inventario.entity.GiftcardMovimientoIf value) {
      setId(value.getId());
      setGiftcardId(value.getGiftcardId());
      setSaldoAnterior(value.getSaldoAnterior());
      setValor(value.getValor());
      setFechaMovimiento(value.getFechaMovimiento());
      setTransaccionId(value.getTransaccionId());
      setTipoDocumentoId(value.getTipoDocumentoId());
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
		return ToStringer.toString((GiftcardMovimientoIf)this);
	}
}
