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

@Table(name = "SOLICITUD_COMPRA_DETALLE")
@Entity
public class SolicitudCompraDetalleEJB implements Serializable, SolicitudCompraDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long solicitudcompraId;
   private java.lang.Long documentoId;
   private java.lang.Long productoId;
   private java.math.BigDecimal cantidad;

   public SolicitudCompraDetalleEJB() {
   }

   public SolicitudCompraDetalleEJB(com.spirit.compras.entity.SolicitudCompraDetalleIf value) {
      setId(value.getId());
      setSolicitudcompraId(value.getSolicitudcompraId());
      setDocumentoId(value.getDocumentoId());
      setProductoId(value.getProductoId());
      setCantidad(value.getCantidad());
   }

   public java.lang.Long create(com.spirit.compras.entity.SolicitudCompraDetalleIf value) {
      setId(value.getId());
      setSolicitudcompraId(value.getSolicitudcompraId());
      setDocumentoId(value.getDocumentoId());
      setProductoId(value.getProductoId());
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

   @Column(name = "SOLICITUDCOMPRA_ID")
   public java.lang.Long getSolicitudcompraId() {
      return solicitudcompraId;
   }

   public void setSolicitudcompraId(java.lang.Long solicitudcompraId) {
      this.solicitudcompraId = solicitudcompraId;
   }

   @Column(name = "DOCUMENTO_ID")
   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   @Column(name = "PRODUCTO_ID")
   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   @Column(name = "CANTIDAD")
   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }
	public String toString() {
		return ToStringer.toString((SolicitudCompraDetalleIf)this);
	}
}
