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

@Table(name = "REUNION_PRODUCTO")
@Entity
public class ReunionProductoEJB implements Serializable, ReunionProductoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long reunionId;
   private java.lang.Long productoClienteId;
   private java.lang.String productoCliente;

   public ReunionProductoEJB() {
   }

   public ReunionProductoEJB(com.spirit.medios.entity.ReunionProductoIf value) {
      setId(value.getId());
      setReunionId(value.getReunionId());
      setProductoClienteId(value.getProductoClienteId());
      setProductoCliente(value.getProductoCliente());
   }

   public java.lang.Long create(com.spirit.medios.entity.ReunionProductoIf value) {
      setId(value.getId());
      setReunionId(value.getReunionId());
      setProductoClienteId(value.getProductoClienteId());
      setProductoCliente(value.getProductoCliente());
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

   @Column(name = "REUNION_ID")
   public java.lang.Long getReunionId() {
      return reunionId;
   }

   public void setReunionId(java.lang.Long reunionId) {
      this.reunionId = reunionId;
   }

   @Column(name = "PRODUCTO_CLIENTE_ID")
   public java.lang.Long getProductoClienteId() {
      return productoClienteId;
   }

   public void setProductoClienteId(java.lang.Long productoClienteId) {
      this.productoClienteId = productoClienteId;
   }

   @Column(name = "PRODUCTO_CLIENTE")
   public java.lang.String getProductoCliente() {
      return productoCliente;
   }

   public void setProductoCliente(java.lang.String productoCliente) {
      this.productoCliente = productoCliente;
   }
	public String toString() {
		return ToStringer.toString((ReunionProductoIf)this);
	}
}
