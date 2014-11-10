package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class MapaComercialData implements MapaComercialIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long planMedioDetalleId;

   public java.lang.Long getPlanMedioDetalleId() {
      return planMedioDetalleId;
   }

   public void setPlanMedioDetalleId(java.lang.Long planMedioDetalleId) {
      this.planMedioDetalleId = planMedioDetalleId;
   }

   private java.sql.Date fecha;

   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   private java.lang.Integer frecuencia;

   public java.lang.Integer getFrecuencia() {
      return frecuencia;
   }

   public void setFrecuencia(java.lang.Integer frecuencia) {
      this.frecuencia = frecuencia;
   }
   public MapaComercialData() {
   }

   public MapaComercialData(com.spirit.medios.entity.MapaComercialIf value) {
      setId(value.getId());
      setPlanMedioDetalleId(value.getPlanMedioDetalleId());
      setFecha(value.getFecha());
      setFrecuencia(value.getFrecuencia());
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
		return ToStringer.toString((MapaComercialIf)this);
	}
}
