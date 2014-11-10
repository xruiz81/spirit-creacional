package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PropuestaDetalleData implements PropuestaDetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long propuestaId;

   public java.lang.Long getPropuestaId() {
      return propuestaId;
   }

   public void setPropuestaId(java.lang.Long propuestaId) {
      this.propuestaId = propuestaId;
   }

   private java.lang.Long presupuestoId;

   public java.lang.Long getPresupuestoId() {
      return presupuestoId;
   }

   public void setPresupuestoId(java.lang.Long presupuestoId) {
      this.presupuestoId = presupuestoId;
   }

   private java.lang.Long planmedioId;

   public java.lang.Long getPlanmedioId() {
      return planmedioId;
   }

   public void setPlanmedioId(java.lang.Long planmedioId) {
      this.planmedioId = planmedioId;
   }
   public PropuestaDetalleData() {
   }

   public PropuestaDetalleData(com.spirit.medios.entity.PropuestaDetalleIf value) {
      setId(value.getId());
      setPropuestaId(value.getPropuestaId());
      setPresupuestoId(value.getPresupuestoId());
      setPlanmedioId(value.getPlanmedioId());
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
		return ToStringer.toString((PropuestaDetalleIf)this);
	}
}
