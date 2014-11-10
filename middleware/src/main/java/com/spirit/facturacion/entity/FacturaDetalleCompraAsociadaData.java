package com.spirit.facturacion.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class FacturaDetalleCompraAsociadaData implements FacturaDetalleCompraAsociadaIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long facturaDetalleId;

   public java.lang.Long getFacturaDetalleId() {
      return facturaDetalleId;
   }

   public void setFacturaDetalleId(java.lang.Long facturaDetalleId) {
      this.facturaDetalleId = facturaDetalleId;
   }

   private java.lang.Long compraId;

   public java.lang.Long getCompraId() {
      return compraId;
   }

   public void setCompraId(java.lang.Long compraId) {
      this.compraId = compraId;
   }
   public FacturaDetalleCompraAsociadaData() {
   }

   public FacturaDetalleCompraAsociadaData(com.spirit.facturacion.entity.FacturaDetalleCompraAsociadaIf value) {
      setId(value.getId());
      setFacturaDetalleId(value.getFacturaDetalleId());
      setCompraId(value.getCompraId());
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
		return ToStringer.toString((FacturaDetalleCompraAsociadaIf)this);
	}
}
