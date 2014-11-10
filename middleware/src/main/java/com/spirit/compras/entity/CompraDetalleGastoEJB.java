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

@Table(name = "COMPRA_DETALLE_GASTO")
@Entity
public class CompraDetalleGastoEJB implements Serializable, CompraDetalleGastoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long compraGastoId;
   private java.lang.Long compraDetalleId;
   private java.math.BigDecimal valor;

   public CompraDetalleGastoEJB() {
   }

   public CompraDetalleGastoEJB(com.spirit.compras.entity.CompraDetalleGastoIf value) {
      setId(value.getId());
      setCompraGastoId(value.getCompraGastoId());
      setCompraDetalleId(value.getCompraDetalleId());
      setValor(value.getValor());
   }

   public java.lang.Long create(com.spirit.compras.entity.CompraDetalleGastoIf value) {
      setId(value.getId());
      setCompraGastoId(value.getCompraGastoId());
      setCompraDetalleId(value.getCompraDetalleId());
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

   @Column(name = "COMPRA_GASTO_ID")
   public java.lang.Long getCompraGastoId() {
      return compraGastoId;
   }

   public void setCompraGastoId(java.lang.Long compraGastoId) {
      this.compraGastoId = compraGastoId;
   }

   @Column(name = "COMPRA_DETALLE_ID")
   public java.lang.Long getCompraDetalleId() {
      return compraDetalleId;
   }

   public void setCompraDetalleId(java.lang.Long compraDetalleId) {
      this.compraDetalleId = compraDetalleId;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }
	public String toString() {
		return ToStringer.toString((CompraDetalleGastoIf)this);
	}
}
