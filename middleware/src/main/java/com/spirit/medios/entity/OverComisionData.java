package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class OverComisionData implements OverComisionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long proveedorOficinaId;

   public java.lang.Long getProveedorOficinaId() {
      return proveedorOficinaId;
   }

   public void setProveedorOficinaId(java.lang.Long proveedorOficinaId) {
      this.proveedorOficinaId = proveedorOficinaId;
   }

   private java.sql.Timestamp anio;

   public java.sql.Timestamp getAnio() {
      return anio;
   }

   public void setAnio(java.sql.Timestamp anio) {
      this.anio = anio;
   }

   private java.math.BigDecimal inversionDe;

   public java.math.BigDecimal getInversionDe() {
      return inversionDe;
   }

   public void setInversionDe(java.math.BigDecimal inversionDe) {
      this.inversionDe = inversionDe;
   }

   private java.math.BigDecimal inversionA;

   public java.math.BigDecimal getInversionA() {
      return inversionA;
   }

   public void setInversionA(java.math.BigDecimal inversionA) {
      this.inversionA = inversionA;
   }

   private java.math.BigDecimal porcentajeOver;

   public java.math.BigDecimal getPorcentajeOver() {
      return porcentajeOver;
   }

   public void setPorcentajeOver(java.math.BigDecimal porcentajeOver) {
      this.porcentajeOver = porcentajeOver;
   }

   private java.lang.String objetivo;

   public java.lang.String getObjetivo() {
      return objetivo;
   }

   public void setObjetivo(java.lang.String objetivo) {
      this.objetivo = objetivo;
   }

   private java.lang.Long proveedorId;

   public java.lang.Long getProveedorId() {
      return proveedorId;
   }

   public void setProveedorId(java.lang.Long proveedorId) {
      this.proveedorId = proveedorId;
   }
   public OverComisionData() {
   }

   public OverComisionData(com.spirit.medios.entity.OverComisionIf value) {
      setId(value.getId());
      setProveedorOficinaId(value.getProveedorOficinaId());
      setAnio(value.getAnio());
      setInversionDe(value.getInversionDe());
      setInversionA(value.getInversionA());
      setPorcentajeOver(value.getPorcentajeOver());
      setObjetivo(value.getObjetivo());
      setProveedorId(value.getProveedorId());
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
		return ToStringer.toString((OverComisionIf)this);
	}
}
