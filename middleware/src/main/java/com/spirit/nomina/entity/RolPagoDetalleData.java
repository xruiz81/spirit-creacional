package com.spirit.nomina.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class RolPagoDetalleData implements RolPagoDetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long rolpagoId;

   public java.lang.Long getRolpagoId() {
      return rolpagoId;
   }

   public void setRolpagoId(java.lang.Long rolpagoId) {
      this.rolpagoId = rolpagoId;
   }

   private java.lang.Long contratoId;

   public java.lang.Long getContratoId() {
      return contratoId;
   }

   public void setContratoId(java.lang.Long contratoId) {
      this.contratoId = contratoId;
   }

   private java.lang.Long rubroId;

   public java.lang.Long getRubroId() {
      return rubroId;
   }

   public void setRubroId(java.lang.Long rubroId) {
      this.rubroId = rubroId;
   }

   private java.lang.Long rubroEventualId;

   public java.lang.Long getRubroEventualId() {
      return rubroEventualId;
   }

   public void setRubroEventualId(java.lang.Long rubroEventualId) {
      this.rubroEventualId = rubroEventualId;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.sql.Date fechaPago;

   public java.sql.Date getFechaPago() {
      return fechaPago;
   }

   public void setFechaPago(java.sql.Date fechaPago) {
      this.fechaPago = fechaPago;
   }

   private java.lang.Long tipoPagoId;

   public java.lang.Long getTipoPagoId() {
      return tipoPagoId;
   }

   public void setTipoPagoId(java.lang.Long tipoPagoId) {
      this.tipoPagoId = tipoPagoId;
   }

   private java.lang.Long cuentaBancariaId;

   public java.lang.Long getCuentaBancariaId() {
      return cuentaBancariaId;
   }

   public void setCuentaBancariaId(java.lang.Long cuentaBancariaId) {
      this.cuentaBancariaId = cuentaBancariaId;
   }

   private java.lang.String preimpreso;

   public java.lang.String getPreimpreso() {
      return preimpreso;
   }

   public void setPreimpreso(java.lang.String preimpreso) {
      this.preimpreso = preimpreso;
   }

   private java.lang.Long asientoId;

   public java.lang.Long getAsientoId() {
      return asientoId;
   }

   public void setAsientoId(java.lang.Long asientoId) {
      this.asientoId = asientoId;
   }
   public RolPagoDetalleData() {
   }

   public RolPagoDetalleData(com.spirit.nomina.entity.RolPagoDetalleIf value) {
      setId(value.getId());
      setRolpagoId(value.getRolpagoId());
      setContratoId(value.getContratoId());
      setRubroId(value.getRubroId());
      setRubroEventualId(value.getRubroEventualId());
      setValor(value.getValor());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setFechaPago(value.getFechaPago());
      setTipoPagoId(value.getTipoPagoId());
      setCuentaBancariaId(value.getCuentaBancariaId());
      setPreimpreso(value.getPreimpreso());
      setAsientoId(value.getAsientoId());
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
		return ToStringer.toString((RolPagoDetalleIf)this);
	}
}
