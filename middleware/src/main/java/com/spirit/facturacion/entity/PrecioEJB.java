package com.spirit.facturacion.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "PRECIO")
@Entity
public class PrecioEJB implements Serializable, PrecioIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long listaprecioId;
   private java.lang.Long productoId;
   private java.math.BigDecimal precio;
   private java.lang.String cambiarPrecio;
   private java.lang.String estado;

   public PrecioEJB() {
   }

   public PrecioEJB(com.spirit.facturacion.entity.PrecioIf value) {
      setId(value.getId());
      setListaprecioId(value.getListaprecioId());
      setProductoId(value.getProductoId());
      setPrecio(value.getPrecio());
      setCambiarPrecio(value.getCambiarPrecio());
      setEstado(value.getEstado());
   }

   public java.lang.Long create(com.spirit.facturacion.entity.PrecioIf value) {
      setId(value.getId());
      setListaprecioId(value.getListaprecioId());
      setProductoId(value.getProductoId());
      setPrecio(value.getPrecio());
      setCambiarPrecio(value.getCambiarPrecio());
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

   @Column(name = "LISTAPRECIO_ID")
   public java.lang.Long getListaprecioId() {
      return listaprecioId;
   }

   public void setListaprecioId(java.lang.Long listaprecioId) {
      this.listaprecioId = listaprecioId;
   }

   @Column(name = "PRODUCTO_ID")
   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   @Column(name = "PRECIO")
   public java.math.BigDecimal getPrecio() {
      return precio;
   }

   public void setPrecio(java.math.BigDecimal precio) {
      this.precio = precio;
   }

   @Column(name = "CAMBIAR_PRECIO")
   public java.lang.String getCambiarPrecio() {
      return cambiarPrecio;
   }

   public void setCambiarPrecio(java.lang.String cambiarPrecio) {
      this.cambiarPrecio = cambiarPrecio;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
	public String toString() {
		return ToStringer.toString((PrecioIf)this);
	}
}
