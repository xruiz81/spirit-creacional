package com.spirit.compras.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class SolicitudCompraDetalleData implements SolicitudCompraDetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long solicitudcompraId;

   public java.lang.Long getSolicitudcompraId() {
      return solicitudcompraId;
   }

   public void setSolicitudcompraId(java.lang.Long solicitudcompraId) {
      this.solicitudcompraId = solicitudcompraId;
   }

   private java.lang.Long documentoId;

   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   private java.lang.Long productoId;

   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   private java.math.BigDecimal cantidad;

   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }
   public SolicitudCompraDetalleData() {
   }

   public SolicitudCompraDetalleData(com.spirit.compras.entity.SolicitudCompraDetalleIf value) {
      setId(value.getId());
      setSolicitudcompraId(value.getSolicitudcompraId());
      setDocumentoId(value.getDocumentoId());
      setProductoId(value.getProductoId());
      setCantidad(value.getCantidad());
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
		return ToStringer.toString((SolicitudCompraDetalleIf)this);
	}
}
