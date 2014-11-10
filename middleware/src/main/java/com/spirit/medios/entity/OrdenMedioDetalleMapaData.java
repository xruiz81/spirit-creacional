package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class OrdenMedioDetalleMapaData implements OrdenMedioDetalleMapaIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long ordenMedioDetalleId;

   public java.lang.Long getOrdenMedioDetalleId() {
      return ordenMedioDetalleId;
   }

   public void setOrdenMedioDetalleId(java.lang.Long ordenMedioDetalleId) {
      this.ordenMedioDetalleId = ordenMedioDetalleId;
   }

   private java.sql.Timestamp fecha;

   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   private java.lang.Integer frecuencia;

   public java.lang.Integer getFrecuencia() {
      return frecuencia;
   }

   public void setFrecuencia(java.lang.Integer frecuencia) {
      this.frecuencia = frecuencia;
   }
   public OrdenMedioDetalleMapaData() {
   }

   public OrdenMedioDetalleMapaData(com.spirit.medios.entity.OrdenMedioDetalleMapaIf value) {
      setId(value.getId());
      setOrdenMedioDetalleId(value.getOrdenMedioDetalleId());
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
		return ToStringer.toString((OrdenMedioDetalleMapaIf)this);
	}
}
