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

@Table(name = "GIFTCARD")
@Entity
public class GiftcardEJB implements Serializable, GiftcardIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long productoId;
   private java.lang.String codigoBarras;
   private java.math.BigDecimal valor;
   private java.math.BigDecimal saldo;
   private java.lang.String estado;

   public GiftcardEJB() {
   }

   public GiftcardEJB(com.spirit.inventario.entity.GiftcardIf value) {
      setId(value.getId());
      setProductoId(value.getProductoId());
      setCodigoBarras(value.getCodigoBarras());
      setValor(value.getValor());
      setSaldo(value.getSaldo());
      setEstado(value.getEstado());
   }

   public java.lang.Long create(com.spirit.inventario.entity.GiftcardIf value) {
      setId(value.getId());
      setProductoId(value.getProductoId());
      setCodigoBarras(value.getCodigoBarras());
      setValor(value.getValor());
      setSaldo(value.getSaldo());
      setEstado(value.getEstado());
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

   @Column(name = "PRODUCTO_ID")
   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   @Column(name = "CODIGO_BARRAS")
   public java.lang.String getCodigoBarras() {
      return codigoBarras;
   }

   public void setCodigoBarras(java.lang.String codigoBarras) {
      this.codigoBarras = codigoBarras;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "SALDO")
   public java.math.BigDecimal getSaldo() {
      return saldo;
   }

   public void setSaldo(java.math.BigDecimal saldo) {
      this.saldo = saldo;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
	public String toString() {
		return ToStringer.toString((GiftcardIf)this);
	}
}
