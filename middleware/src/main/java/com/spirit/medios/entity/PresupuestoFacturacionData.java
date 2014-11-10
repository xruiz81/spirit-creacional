package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PresupuestoFacturacionData implements PresupuestoFacturacionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long presupuestoDetalleId;

   public java.lang.Long getPresupuestoDetalleId() {
      return presupuestoDetalleId;
   }

   public void setPresupuestoDetalleId(java.lang.Long presupuestoDetalleId) {
      this.presupuestoDetalleId = presupuestoDetalleId;
   }

   private java.lang.Long facturaId;

   public java.lang.Long getFacturaId() {
      return facturaId;
   }

   public void setFacturaId(java.lang.Long facturaId) {
      this.facturaId = facturaId;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String tipo;

   public java.lang.String getTipo() {
      return tipo;
   }

   public void setTipo(java.lang.String tipo) {
      this.tipo = tipo;
   }
   public PresupuestoFacturacionData() {
   }

   public PresupuestoFacturacionData(com.spirit.medios.entity.PresupuestoFacturacionIf value) {
      setId(value.getId());
      setPresupuestoDetalleId(value.getPresupuestoDetalleId());
      setFacturaId(value.getFacturaId());
      setEstado(value.getEstado());
      setTipo(value.getTipo());
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
		return ToStringer.toString((PresupuestoFacturacionIf)this);
	}
}
