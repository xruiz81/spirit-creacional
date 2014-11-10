package com.spirit.compras.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CompraRetencionData implements CompraRetencionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String establecimiento;

   public java.lang.String getEstablecimiento() {
      return establecimiento;
   }

   public void setEstablecimiento(java.lang.String establecimiento) {
      this.establecimiento = establecimiento;
   }

   private java.lang.String puntoEmision;

   public java.lang.String getPuntoEmision() {
      return puntoEmision;
   }

   public void setPuntoEmision(java.lang.String puntoEmision) {
      this.puntoEmision = puntoEmision;
   }

   private java.lang.String secuencial;

   public java.lang.String getSecuencial() {
      return secuencial;
   }

   public void setSecuencial(java.lang.String secuencial) {
      this.secuencial = secuencial;
   }

   private java.lang.String autorizacion;

   public java.lang.String getAutorizacion() {
      return autorizacion;
   }

   public void setAutorizacion(java.lang.String autorizacion) {
      this.autorizacion = autorizacion;
   }

   private java.sql.Date fechaEmision;

   public java.sql.Date getFechaEmision() {
      return fechaEmision;
   }

   public void setFechaEmision(java.sql.Date fechaEmision) {
      this.fechaEmision = fechaEmision;
   }

   private java.lang.Long compraId;

   public java.lang.Long getCompraId() {
      return compraId;
   }

   public void setCompraId(java.lang.Long compraId) {
      this.compraId = compraId;
   }

   private java.lang.String ejercicioFiscal;

   public java.lang.String getEjercicioFiscal() {
      return ejercicioFiscal;
   }

   public void setEjercicioFiscal(java.lang.String ejercicioFiscal) {
      this.ejercicioFiscal = ejercicioFiscal;
   }

   private java.math.BigDecimal baseImponible;

   public java.math.BigDecimal getBaseImponible() {
      return baseImponible;
   }

   public void setBaseImponible(java.math.BigDecimal baseImponible) {
      this.baseImponible = baseImponible;
   }

   private java.lang.String impuesto;

   public java.lang.String getImpuesto() {
      return impuesto;
   }

   public void setImpuesto(java.lang.String impuesto) {
      this.impuesto = impuesto;
   }

   private java.math.BigDecimal porcentajeRetencion;

   public java.math.BigDecimal getPorcentajeRetencion() {
      return porcentajeRetencion;
   }

   public void setPorcentajeRetencion(java.math.BigDecimal porcentajeRetencion) {
      this.porcentajeRetencion = porcentajeRetencion;
   }

   private java.math.BigDecimal valorRetenido;

   public java.math.BigDecimal getValorRetenido() {
      return valorRetenido;
   }

   public void setValorRetenido(java.math.BigDecimal valorRetenido) {
      this.valorRetenido = valorRetenido;
   }

   private java.lang.String codigoImpuesto;

   public java.lang.String getCodigoImpuesto() {
      return codigoImpuesto;
   }

   public void setCodigoImpuesto(java.lang.String codigoImpuesto) {
      this.codigoImpuesto = codigoImpuesto;
   }
   public CompraRetencionData() {
   }

   public CompraRetencionData(com.spirit.compras.entity.CompraRetencionIf value) {
      setId(value.getId());
      setEstablecimiento(value.getEstablecimiento());
      setPuntoEmision(value.getPuntoEmision());
      setSecuencial(value.getSecuencial());
      setAutorizacion(value.getAutorizacion());
      setFechaEmision(value.getFechaEmision());
      setCompraId(value.getCompraId());
      setEjercicioFiscal(value.getEjercicioFiscal());
      setBaseImponible(value.getBaseImponible());
      setImpuesto(value.getImpuesto());
      setPorcentajeRetencion(value.getPorcentajeRetencion());
      setValorRetenido(value.getValorRetenido());
      setCodigoImpuesto(value.getCodigoImpuesto());
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
		return ToStringer.toString((CompraRetencionIf)this);
	}
}
