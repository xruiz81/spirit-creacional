package com.spirit.pos.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class VentasTrackData implements VentasTrackIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.math.BigDecimal valorTotal;

   public java.math.BigDecimal getValorTotal() {
      return valorTotal;
   }

   public void setValorTotal(java.math.BigDecimal valorTotal) {
      this.valorTotal = valorTotal;
   }

   private java.lang.Long cajasesionId;

   public java.lang.Long getCajasesionId() {
      return cajasesionId;
   }

   public void setCajasesionId(java.lang.Long cajasesionId) {
      this.cajasesionId = cajasesionId;
   }

   private java.sql.Timestamp fechaVenta;

   public java.sql.Timestamp getFechaVenta() {
      return fechaVenta;
   }

   public void setFechaVenta(java.sql.Timestamp fechaVenta) {
      this.fechaVenta = fechaVenta;
   }
   public VentasTrackData() {
   }

   public VentasTrackData(com.spirit.pos.entity.VentasTrackIf value) {
      setId(value.getId());
      setValorTotal(value.getValorTotal());
      setCajasesionId(value.getCajasesionId());
      setFechaVenta(value.getFechaVenta());
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
		return ToStringer.toString((VentasTrackIf)this);
	}
}
