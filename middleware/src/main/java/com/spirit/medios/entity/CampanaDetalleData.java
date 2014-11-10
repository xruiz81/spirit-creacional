package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CampanaDetalleData implements CampanaDetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long clienteZonaId;

   public java.lang.Long getClienteZonaId() {
      return clienteZonaId;
   }

   public void setClienteZonaId(java.lang.Long clienteZonaId) {
      this.clienteZonaId = clienteZonaId;
   }

   private java.lang.Long campanaId;

   public java.lang.Long getCampanaId() {
      return campanaId;
   }

   public void setCampanaId(java.lang.Long campanaId) {
      this.campanaId = campanaId;
   }

   private java.math.BigDecimal participacion;

   public java.math.BigDecimal getParticipacion() {
      return participacion;
   }

   public void setParticipacion(java.math.BigDecimal participacion) {
      this.participacion = participacion;
   }

   private java.lang.String descripcion;

   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }
   public CampanaDetalleData() {
   }

   public CampanaDetalleData(com.spirit.medios.entity.CampanaDetalleIf value) {
      setId(value.getId());
      setClienteZonaId(value.getClienteZonaId());
      setCampanaId(value.getCampanaId());
      setParticipacion(value.getParticipacion());
      setDescripcion(value.getDescripcion());
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
		return ToStringer.toString((CampanaDetalleIf)this);
	}
}
