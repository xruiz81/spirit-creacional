package com.spirit.compras.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "COMPRA_GASTO")
@Entity
public class CompraGastoEJB implements Serializable, CompraGastoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long gastoId;
   private java.lang.Long compraId;
   private java.math.BigDecimal valor;

   public CompraGastoEJB() {
   }

   public CompraGastoEJB(com.spirit.compras.entity.CompraGastoIf value) {
      setId(value.getId());
      setGastoId(value.getGastoId());
      setCompraId(value.getCompraId());
      setValor(value.getValor());
   }

   public java.lang.Long create(com.spirit.compras.entity.CompraGastoIf value) {
      setId(value.getId());
      setGastoId(value.getGastoId());
      setCompraId(value.getCompraId());
      setValor(value.getValor());
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

   @Column(name = "GASTO_ID")
   public java.lang.Long getGastoId() {
      return gastoId;
   }

   public void setGastoId(java.lang.Long gastoId) {
      this.gastoId = gastoId;
   }

   @Column(name = "COMPRA_ID")
   public java.lang.Long getCompraId() {
      return compraId;
   }

   public void setCompraId(java.lang.Long compraId) {
      this.compraId = compraId;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }
	public String toString() {
		return ToStringer.toString((CompraGastoIf)this);
	}
}
