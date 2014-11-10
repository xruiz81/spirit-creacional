package com.spirit.compras.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CompraDetalleGastoData implements CompraDetalleGastoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long compraGastoId;

   public java.lang.Long getCompraGastoId() {
      return compraGastoId;
   }

   public void setCompraGastoId(java.lang.Long compraGastoId) {
      this.compraGastoId = compraGastoId;
   }

   private java.lang.Long compraDetalleId;

   public java.lang.Long getCompraDetalleId() {
      return compraDetalleId;
   }

   public void setCompraDetalleId(java.lang.Long compraDetalleId) {
      this.compraDetalleId = compraDetalleId;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }
   public CompraDetalleGastoData() {
   }

   public CompraDetalleGastoData(com.spirit.compras.entity.CompraDetalleGastoIf value) {
      setId(value.getId());
      setCompraGastoId(value.getCompraGastoId());
      setCompraDetalleId(value.getCompraDetalleId());
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
		return ToStringer.toString((CompraDetalleGastoIf)this);
	}
}
