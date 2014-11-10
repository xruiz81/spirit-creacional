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

@Table(name = "PEDIDO_DETALLE_PERSONALIZACION")
@Entity
public class PedidoDetallePersonalizacionEJB implements Serializable, PedidoDetallePersonalizacionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long pedidoDetalleId;
   private java.lang.Long tipoPersonalizacionId;
   private java.lang.Long impresionPersonalizacionId;
   private java.lang.Long tamanioPersonalizacionId;
   private java.lang.Long colorPersonalizacionId;
   private java.lang.Long ubicacionPersonalizacionId;
   private java.lang.Long tipoLetraPersonalizacionId;
   private java.lang.String descripcion;
   private java.lang.Long logoDisenioPersonalizacionId;
   private java.lang.String mensaje;
   private java.lang.Long disenioPersonalizacionId;

   public PedidoDetallePersonalizacionEJB() {
   }

   public PedidoDetallePersonalizacionEJB(com.spirit.facturacion.entity.PedidoDetallePersonalizacionIf value) {
      setId(value.getId());
      setPedidoDetalleId(value.getPedidoDetalleId());
      setTipoPersonalizacionId(value.getTipoPersonalizacionId());
      setImpresionPersonalizacionId(value.getImpresionPersonalizacionId());
      setTamanioPersonalizacionId(value.getTamanioPersonalizacionId());
      setColorPersonalizacionId(value.getColorPersonalizacionId());
      setUbicacionPersonalizacionId(value.getUbicacionPersonalizacionId());
      setTipoLetraPersonalizacionId(value.getTipoLetraPersonalizacionId());
      setDescripcion(value.getDescripcion());
      setLogoDisenioPersonalizacionId(value.getLogoDisenioPersonalizacionId());
      setMensaje(value.getMensaje());
      setDisenioPersonalizacionId(value.getDisenioPersonalizacionId());
   }

   public java.lang.Long create(com.spirit.facturacion.entity.PedidoDetallePersonalizacionIf value) {
      setId(value.getId());
      setPedidoDetalleId(value.getPedidoDetalleId());
      setTipoPersonalizacionId(value.getTipoPersonalizacionId());
      setImpresionPersonalizacionId(value.getImpresionPersonalizacionId());
      setTamanioPersonalizacionId(value.getTamanioPersonalizacionId());
      setColorPersonalizacionId(value.getColorPersonalizacionId());
      setUbicacionPersonalizacionId(value.getUbicacionPersonalizacionId());
      setTipoLetraPersonalizacionId(value.getTipoLetraPersonalizacionId());
      setDescripcion(value.getDescripcion());
      setLogoDisenioPersonalizacionId(value.getLogoDisenioPersonalizacionId());
      setMensaje(value.getMensaje());
      setDisenioPersonalizacionId(value.getDisenioPersonalizacionId());
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

   @Column(name = "PEDIDO_DETALLE_ID")
   public java.lang.Long getPedidoDetalleId() {
      return pedidoDetalleId;
   }

   public void setPedidoDetalleId(java.lang.Long pedidoDetalleId) {
      this.pedidoDetalleId = pedidoDetalleId;
   }

   @Column(name = "TIPO_PERSONALIZACION_ID")
   public java.lang.Long getTipoPersonalizacionId() {
      return tipoPersonalizacionId;
   }

   public void setTipoPersonalizacionId(java.lang.Long tipoPersonalizacionId) {
      this.tipoPersonalizacionId = tipoPersonalizacionId;
   }

   @Column(name = "IMPRESION_PERSONALIZACION_ID")
   public java.lang.Long getImpresionPersonalizacionId() {
      return impresionPersonalizacionId;
   }

   public void setImpresionPersonalizacionId(java.lang.Long impresionPersonalizacionId) {
      this.impresionPersonalizacionId = impresionPersonalizacionId;
   }

   @Column(name = "TAMANIO_PERSONALIZACION_ID")
   public java.lang.Long getTamanioPersonalizacionId() {
      return tamanioPersonalizacionId;
   }

   public void setTamanioPersonalizacionId(java.lang.Long tamanioPersonalizacionId) {
      this.tamanioPersonalizacionId = tamanioPersonalizacionId;
   }

   @Column(name = "COLOR_PERSONALIZACION_ID")
   public java.lang.Long getColorPersonalizacionId() {
      return colorPersonalizacionId;
   }

   public void setColorPersonalizacionId(java.lang.Long colorPersonalizacionId) {
      this.colorPersonalizacionId = colorPersonalizacionId;
   }

   @Column(name = "UBICACION_PERSONALIZACION_ID")
   public java.lang.Long getUbicacionPersonalizacionId() {
      return ubicacionPersonalizacionId;
   }

   public void setUbicacionPersonalizacionId(java.lang.Long ubicacionPersonalizacionId) {
      this.ubicacionPersonalizacionId = ubicacionPersonalizacionId;
   }

   @Column(name = "TIPO_LETRA_PERSONALIZACION_ID")
   public java.lang.Long getTipoLetraPersonalizacionId() {
      return tipoLetraPersonalizacionId;
   }

   public void setTipoLetraPersonalizacionId(java.lang.Long tipoLetraPersonalizacionId) {
      this.tipoLetraPersonalizacionId = tipoLetraPersonalizacionId;
   }

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "LOGO_DISENIO_PERSONALIZACION_ID")
   public java.lang.Long getLogoDisenioPersonalizacionId() {
      return logoDisenioPersonalizacionId;
   }

   public void setLogoDisenioPersonalizacionId(java.lang.Long logoDisenioPersonalizacionId) {
      this.logoDisenioPersonalizacionId = logoDisenioPersonalizacionId;
   }

   @Column(name = "MENSAJE")
   public java.lang.String getMensaje() {
      return mensaje;
   }

   public void setMensaje(java.lang.String mensaje) {
      this.mensaje = mensaje;
   }

   @Column(name = "DISENIO_PERSONALIZACION_ID")
   public java.lang.Long getDisenioPersonalizacionId() {
      return disenioPersonalizacionId;
   }

   public void setDisenioPersonalizacionId(java.lang.Long disenioPersonalizacionId) {
      this.disenioPersonalizacionId = disenioPersonalizacionId;
   }
	public String toString() {
		return ToStringer.toString((PedidoDetallePersonalizacionIf)this);
	}
}
