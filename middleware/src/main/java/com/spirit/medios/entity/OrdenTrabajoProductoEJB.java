package com.spirit.medios.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "ORDEN_TRABAJO_PRODUCTO")
@Entity
public class OrdenTrabajoProductoEJB implements Serializable, OrdenTrabajoProductoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long productoClienteId;
   private java.lang.Long ordenTrabajoId;

   public OrdenTrabajoProductoEJB() {
   }

   public OrdenTrabajoProductoEJB(com.spirit.medios.entity.OrdenTrabajoProductoIf value) {
      setId(value.getId());
      setProductoClienteId(value.getProductoClienteId());
      setOrdenTrabajoId(value.getOrdenTrabajoId());
   }

   public java.lang.Long create(com.spirit.medios.entity.OrdenTrabajoProductoIf value) {
      setId(value.getId());
      setProductoClienteId(value.getProductoClienteId());
      setOrdenTrabajoId(value.getOrdenTrabajoId());
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

   @Column(name = "PRODUCTO_CLIENTE_ID")
   public java.lang.Long getProductoClienteId() {
      return productoClienteId;
   }

   public void setProductoClienteId(java.lang.Long productoClienteId) {
      this.productoClienteId = productoClienteId;
   }

   @Column(name = "ORDEN_TRABAJO_ID")
   public java.lang.Long getOrdenTrabajoId() {
      return ordenTrabajoId;
   }

   public void setOrdenTrabajoId(java.lang.Long ordenTrabajoId) {
      this.ordenTrabajoId = ordenTrabajoId;
   }
	public String toString() {
		return ToStringer.toString((OrdenTrabajoProductoIf)this);
	}
}
