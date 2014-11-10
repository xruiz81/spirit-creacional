package com.spirit.nomina.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ImpuestoRentaPersNatData implements ImpuestoRentaPersNatIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.math.BigDecimal fraccionBasica;

   public java.math.BigDecimal getFraccionBasica() {
      return fraccionBasica;
   }

   public void setFraccionBasica(java.math.BigDecimal fraccionBasica) {
      this.fraccionBasica = fraccionBasica;
   }

   private java.math.BigDecimal excesoHasta;

   public java.math.BigDecimal getExcesoHasta() {
      return excesoHasta;
   }

   public void setExcesoHasta(java.math.BigDecimal excesoHasta) {
      this.excesoHasta = excesoHasta;
   }

   private java.math.BigDecimal impFraccionBasica;

   public java.math.BigDecimal getImpFraccionBasica() {
      return impFraccionBasica;
   }

   public void setImpFraccionBasica(java.math.BigDecimal impFraccionBasica) {
      this.impFraccionBasica = impFraccionBasica;
   }

   private java.math.BigDecimal porcentajeImpFraccionBasica;

   public java.math.BigDecimal getPorcentajeImpFraccionBasica() {
      return porcentajeImpFraccionBasica;
   }

   public void setPorcentajeImpFraccionBasica(java.math.BigDecimal porcentajeImpFraccionBasica) {
      this.porcentajeImpFraccionBasica = porcentajeImpFraccionBasica;
   }

   private java.sql.Date fechaInicio;

   public java.sql.Date getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Date fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   private java.sql.Date fechaFin;

   public java.sql.Date getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Date fechaFin) {
      this.fechaFin = fechaFin;
   }
   public ImpuestoRentaPersNatData() {
   }

   public ImpuestoRentaPersNatData(com.spirit.nomina.entity.ImpuestoRentaPersNatIf value) {
      setId(value.getId());
      setFraccionBasica(value.getFraccionBasica());
      setExcesoHasta(value.getExcesoHasta());
      setImpFraccionBasica(value.getImpFraccionBasica());
      setPorcentajeImpFraccionBasica(value.getPorcentajeImpFraccionBasica());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
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
		return ToStringer.toString((ImpuestoRentaPersNatIf)this);
	}
}
