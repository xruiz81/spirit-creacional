package com.spirit.facturacion.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PedidoEnvioDetalleData implements PedidoEnvioDetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long pedidoEnvioId;

   public java.lang.Long getPedidoEnvioId() {
      return pedidoEnvioId;
   }

   public void setPedidoEnvioId(java.lang.Long pedidoEnvioId) {
      this.pedidoEnvioId = pedidoEnvioId;
   }

   private java.lang.String codigoBarras;

   public java.lang.String getCodigoBarras() {
      return codigoBarras;
   }

   public void setCodigoBarras(java.lang.String codigoBarras) {
      this.codigoBarras = codigoBarras;
   }

   private java.math.BigDecimal cantidad;

   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   private java.lang.String incluyeIva;

   public java.lang.String getIncluyeIva() {
      return incluyeIva;
   }

   public void setIncluyeIva(java.lang.String incluyeIva) {
      this.incluyeIva = incluyeIva;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }
   public PedidoEnvioDetalleData() {
   }

   public PedidoEnvioDetalleData(com.spirit.facturacion.entity.PedidoEnvioDetalleIf value) {
      setId(value.getId());
      setPedidoEnvioId(value.getPedidoEnvioId());
      setCodigoBarras(value.getCodigoBarras());
      setCantidad(value.getCantidad());
      setIncluyeIva(value.getIncluyeIva());
      setValor(value.getValor());
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
		return ToStringer.toString((PedidoEnvioDetalleIf)this);
	}
}
