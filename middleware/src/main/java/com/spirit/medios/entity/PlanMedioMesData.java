package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PlanMedioMesData implements PlanMedioMesIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long planMedioId;

   public java.lang.Long getPlanMedioId() {
      return planMedioId;
   }

   public void setPlanMedioId(java.lang.Long planMedioId) {
      this.planMedioId = planMedioId;
   }

   private java.sql.Timestamp fechaInicio;

   public java.sql.Timestamp getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Timestamp fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   private java.sql.Timestamp fechaFin;

   public java.sql.Timestamp getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Timestamp fechaFin) {
      this.fechaFin = fechaFin;
   }

   private java.math.BigDecimal valorSubtotal;

   public java.math.BigDecimal getValorSubtotal() {
      return valorSubtotal;
   }

   public void setValorSubtotal(java.math.BigDecimal valorSubtotal) {
      this.valorSubtotal = valorSubtotal;
   }

   private java.math.BigDecimal valorDescuento;

   public java.math.BigDecimal getValorDescuento() {
      return valorDescuento;
   }

   public void setValorDescuento(java.math.BigDecimal valorDescuento) {
      this.valorDescuento = valorDescuento;
   }

   private java.lang.String tipo;

   public java.lang.String getTipo() {
      return tipo;
   }

   public void setTipo(java.lang.String tipo) {
      this.tipo = tipo;
   }

   private java.math.BigDecimal valorIva;

   public java.math.BigDecimal getValorIva() {
      return valorIva;
   }

   public void setValorIva(java.math.BigDecimal valorIva) {
      this.valorIva = valorIva;
   }

   private java.math.BigDecimal valorTotal;

   public java.math.BigDecimal getValorTotal() {
      return valorTotal;
   }

   public void setValorTotal(java.math.BigDecimal valorTotal) {
      this.valorTotal = valorTotal;
   }

   private java.math.BigDecimal valorDescuentoVenta;

   public java.math.BigDecimal getValorDescuentoVenta() {
      return valorDescuentoVenta;
   }

   public void setValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta) {
      this.valorDescuentoVenta = valorDescuentoVenta;
   }

   private java.math.BigDecimal valorComisionAgencia;

   public java.math.BigDecimal getValorComisionAgencia() {
      return valorComisionAgencia;
   }

   public void setValorComisionAgencia(java.math.BigDecimal valorComisionAgencia) {
      this.valorComisionAgencia = valorComisionAgencia;
   }
   public PlanMedioMesData() {
   }

   public PlanMedioMesData(com.spirit.medios.entity.PlanMedioMesIf value) {
      setId(value.getId());
      setPlanMedioId(value.getPlanMedioId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setValorSubtotal(value.getValorSubtotal());
      setValorDescuento(value.getValorDescuento());
      setTipo(value.getTipo());
      setValorIva(value.getValorIva());
      setValorTotal(value.getValorTotal());
      setValorDescuentoVenta(value.getValorDescuentoVenta());
      setValorComisionAgencia(value.getValorComisionAgencia());
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
		return ToStringer.toString((PlanMedioMesIf)this);
	}
}
