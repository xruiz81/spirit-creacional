package com.spirit.inventario.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "GIFTCARD_MOVIMIENTO")
@Entity
public class GiftcardMovimientoEJB implements Serializable, GiftcardMovimientoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long giftcardId;
   private java.math.BigDecimal saldoAnterior;
   private java.math.BigDecimal valor;
   private java.sql.Timestamp fechaMovimiento;
   private java.lang.Long transaccionId;
   private java.lang.Long tipoDocumentoId;

   public GiftcardMovimientoEJB() {
   }

   public GiftcardMovimientoEJB(com.spirit.inventario.entity.GiftcardMovimientoIf value) {
      setId(value.getId());
      setGiftcardId(value.getGiftcardId());
      setSaldoAnterior(value.getSaldoAnterior());
      setValor(value.getValor());
      setFechaMovimiento(value.getFechaMovimiento());
      setTransaccionId(value.getTransaccionId());
      setTipoDocumentoId(value.getTipoDocumentoId());
   }

   public java.lang.Long create(com.spirit.inventario.entity.GiftcardMovimientoIf value) {
      setId(value.getId());
      setGiftcardId(value.getGiftcardId());
      setSaldoAnterior(value.getSaldoAnterior());
      setValor(value.getValor());
      setFechaMovimiento(value.getFechaMovimiento());
      setTransaccionId(value.getTransaccionId());
      setTipoDocumentoId(value.getTipoDocumentoId());
      return value.getPrimaryKey();
   }

   @javax.persistence.Transient public java.lang.Long getPrimaryKey() {
        return getId();
    }

   @javax.persistence.Transient public void setPrimaryKey(java.lang.Long primaryKey) {
       setId(primaryKey);
    }

   @Column(name = "ID")
@TableGenerator(name="SEQ_GEN",
			allocationSize=HibernateSequenceAllocationSize.allocationSize
)
   @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_GEN")
   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   @Column(name = "GIFTCARD_ID")
   public java.lang.Long getGiftcardId() {
      return giftcardId;
   }

   public void setGiftcardId(java.lang.Long giftcardId) {
      this.giftcardId = giftcardId;
   }

   @Column(name = "SALDO_ANTERIOR")
   public java.math.BigDecimal getSaldoAnterior() {
      return saldoAnterior;
   }

   public void setSaldoAnterior(java.math.BigDecimal saldoAnterior) {
      this.saldoAnterior = saldoAnterior;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "FECHA_MOVIMIENTO")
   public java.sql.Timestamp getFechaMovimiento() {
      return fechaMovimiento;
   }

   public void setFechaMovimiento(java.sql.Timestamp fechaMovimiento) {
      this.fechaMovimiento = fechaMovimiento;
   }

   @Column(name = "TRANSACCION_ID")
   public java.lang.Long getTransaccionId() {
      return transaccionId;
   }

   public void setTransaccionId(java.lang.Long transaccionId) {
      this.transaccionId = transaccionId;
   }

   @Column(name = "TIPO_DOCUMENTO_ID")
   public java.lang.Long getTipoDocumentoId() {
      return tipoDocumentoId;
   }

   public void setTipoDocumentoId(java.lang.Long tipoDocumentoId) {
      this.tipoDocumentoId = tipoDocumentoId;
   }
	public String toString() {
		return ToStringer.toString((GiftcardMovimientoIf)this);
	}
}
