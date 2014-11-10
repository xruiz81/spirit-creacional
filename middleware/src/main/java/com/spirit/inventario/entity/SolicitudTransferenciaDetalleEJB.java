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

@Table(name = "SOLICITUD_TRANSFERENCIA_DETALLE")
@Entity
public class SolicitudTransferenciaDetalleEJB implements Serializable, SolicitudTransferenciaDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long solicitudTransferenciaId;
   private java.lang.Long productoId;
   private java.lang.Long loteId;
   private java.math.BigDecimal cantidad;

   public SolicitudTransferenciaDetalleEJB() {
   }

   public SolicitudTransferenciaDetalleEJB(com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf value) {
      setId(value.getId());
      setSolicitudTransferenciaId(value.getSolicitudTransferenciaId());
      setProductoId(value.getProductoId());
      setLoteId(value.getLoteId());
      setCantidad(value.getCantidad());
   }

   public java.lang.Long create(com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf value) {
      setId(value.getId());
      setSolicitudTransferenciaId(value.getSolicitudTransferenciaId());
      setProductoId(value.getProductoId());
      setLoteId(value.getLoteId());
      setCantidad(value.getCantidad());
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

   @Column(name = "SOLICITUD_TRANSFERENCIA_ID")
   public java.lang.Long getSolicitudTransferenciaId() {
      return solicitudTransferenciaId;
   }

   public void setSolicitudTransferenciaId(java.lang.Long solicitudTransferenciaId) {
      this.solicitudTransferenciaId = solicitudTransferenciaId;
   }

   @Column(name = "PRODUCTO_ID")
   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   @Column(name = "LOTE_ID")
   public java.lang.Long getLoteId() {
      return loteId;
   }

   public void setLoteId(java.lang.Long loteId) {
      this.loteId = loteId;
   }

   @Column(name = "CANTIDAD")
   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }
	public String toString() {
		return ToStringer.toString((SolicitudTransferenciaDetalleIf)this);
	}
}
