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

@Table(name = "COMPRA_ASOCIADA_GASTO")
@Entity
public class CompraAsociadaGastoEJB implements Serializable, CompraAsociadaGastoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long compraGastoId;
   private java.lang.Long compraId;

   public CompraAsociadaGastoEJB() {
   }

   public CompraAsociadaGastoEJB(com.spirit.compras.entity.CompraAsociadaGastoIf value) {
      setId(value.getId());
      setCompraGastoId(value.getCompraGastoId());
      setCompraId(value.getCompraId());
   }

   public java.lang.Long create(com.spirit.compras.entity.CompraAsociadaGastoIf value) {
      setId(value.getId());
      setCompraGastoId(value.getCompraGastoId());
      setCompraId(value.getCompraId());
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

   @Column(name = "COMPRA_ID")
   public java.lang.Long getCompraId() {
      return compraId;
   }

   public void setCompraId(java.lang.Long compraId) {
      this.compraId = compraId;
   }
	public String toString() {
		return ToStringer.toString((CompraAsociadaGastoIf)this);
	}
}
