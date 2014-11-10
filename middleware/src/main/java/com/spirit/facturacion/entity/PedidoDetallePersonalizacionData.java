package com.spirit.facturacion.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PedidoDetallePersonalizacionData implements PedidoDetallePersonalizacionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long pedidoDetalleId;

   public java.lang.Long getPedidoDetalleId() {
      return pedidoDetalleId;
   }

   public void setPedidoDetalleId(java.lang.Long pedidoDetalleId) {
      this.pedidoDetalleId = pedidoDetalleId;
   }

   private java.lang.Long tipoPersonalizacionId;

   public java.lang.Long getTipoPersonalizacionId() {
      return tipoPersonalizacionId;
   }

   public void setTipoPersonalizacionId(java.lang.Long tipoPersonalizacionId) {
      this.tipoPersonalizacionId = tipoPersonalizacionId;
   }

   private java.lang.Long impresionPersonalizacionId;

   public java.lang.Long getImpresionPersonalizacionId() {
      return impresionPersonalizacionId;
   }

   public void setImpresionPersonalizacionId(java.lang.Long impresionPersonalizacionId) {
      this.impresionPersonalizacionId = impresionPersonalizacionId;
   }

   private java.lang.Long tamanioPersonalizacionId;

   public java.lang.Long getTamanioPersonalizacionId() {
      return tamanioPersonalizacionId;
   }

   public void setTamanioPersonalizacionId(java.lang.Long tamanioPersonalizacionId) {
      this.tamanioPersonalizacionId = tamanioPersonalizacionId;
   }

   private java.lang.Long colorPersonalizacionId;

   public java.lang.Long getColorPersonalizacionId() {
      return colorPersonalizacionId;
   }

   public void setColorPersonalizacionId(java.lang.Long colorPersonalizacionId) {
      this.colorPersonalizacionId = colorPersonalizacionId;
   }

   private java.lang.Long ubicacionPersonalizacionId;

   public java.lang.Long getUbicacionPersonalizacionId() {
      return ubicacionPersonalizacionId;
   }

   public void setUbicacionPersonalizacionId(java.lang.Long ubicacionPersonalizacionId) {
      this.ubicacionPersonalizacionId = ubicacionPersonalizacionId;
   }

   private java.lang.Long tipoLetraPersonalizacionId;

   public java.lang.Long getTipoLetraPersonalizacionId() {
      return tipoLetraPersonalizacionId;
   }

   public void setTipoLetraPersonalizacionId(java.lang.Long tipoLetraPersonalizacionId) {
      this.tipoLetraPersonalizacionId = tipoLetraPersonalizacionId;
   }

   private java.lang.String descripcion;

   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   private java.lang.Long logoDisenioPersonalizacionId;

   public java.lang.Long getLogoDisenioPersonalizacionId() {
      return logoDisenioPersonalizacionId;
   }

   public void setLogoDisenioPersonalizacionId(java.lang.Long logoDisenioPersonalizacionId) {
      this.logoDisenioPersonalizacionId = logoDisenioPersonalizacionId;
   }

   private java.lang.String mensaje;

   public java.lang.String getMensaje() {
      return mensaje;
   }

   public void setMensaje(java.lang.String mensaje) {
      this.mensaje = mensaje;
   }

   private java.lang.Long disenioPersonalizacionId;

   public java.lang.Long getDisenioPersonalizacionId() {
      return disenioPersonalizacionId;
   }

   public void setDisenioPersonalizacionId(java.lang.Long disenioPersonalizacionId) {
      this.disenioPersonalizacionId = disenioPersonalizacionId;
   }
   public PedidoDetallePersonalizacionData() {
   }

   public PedidoDetallePersonalizacionData(com.spirit.facturacion.entity.PedidoDetallePersonalizacionIf value) {
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



    public java.lang.Long getPrimaryKey() {
        return getId();
    }

    public void setPrimaryKey(java.lang.Long pk) {
       setId(pk);
    }


   public String getPrimaryKeyParameters() {
      String parameters = "";
      parameters += "&id=" + getId();
      return parameters;
   }



	public String toString() {
		return ToStringer.toString((PedidoDetallePersonalizacionIf)this);
	}
}
