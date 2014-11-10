package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CotizacionData implements CotizacionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long monedaId;

   public java.lang.Long getMonedaId() {
      return monedaId;
   }

   public void setMonedaId(java.lang.Long monedaId) {
      this.monedaId = monedaId;
   }

   private java.lang.Long monedaequivId;

   public java.lang.Long getMonedaequivId() {
      return monedaequivId;
   }

   public void setMonedaequivId(java.lang.Long monedaequivId) {
      this.monedaequivId = monedaequivId;
   }

   private java.sql.Date fecha;

   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   private java.math.BigDecimal cotizacion;

   public java.math.BigDecimal getCotizacion() {
      return cotizacion;
   }

   public void setCotizacion(java.math.BigDecimal cotizacion) {
      this.cotizacion = cotizacion;
   }
   public CotizacionData() {
   }

   public CotizacionData(com.spirit.general.entity.CotizacionIf value) {
      setId(value.getId());
      setMonedaId(value.getMonedaId());
      setMonedaequivId(value.getMonedaequivId());
      setFecha(value.getFecha());
      setCotizacion(value.getCotizacion());
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
		return ToStringer.toString((CotizacionIf)this);
	}
}
