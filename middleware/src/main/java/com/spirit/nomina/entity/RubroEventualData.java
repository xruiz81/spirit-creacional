package com.spirit.nomina.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class RubroEventualData implements RubroEventualIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
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

   private java.lang.Long tipoRolIdCobro;

   public java.lang.Long getTipoRolIdCobro() {
      return tipoRolIdCobro;
   }

   public void setTipoRolIdCobro(java.lang.Long tipoRolIdCobro) {
      this.tipoRolIdCobro = tipoRolIdCobro;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.sql.Date fechaCobro;

   public java.sql.Date getFechaCobro() {
      return fechaCobro;
   }

   public void setFechaCobro(java.sql.Date fechaCobro) {
      this.fechaCobro = fechaCobro;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.lang.Long tipoRolIdPago;

   public java.lang.Long getTipoRolIdPago() {
      return tipoRolIdPago;
   }

   public void setTipoRolIdPago(java.lang.Long tipoRolIdPago) {
      this.tipoRolIdPago = tipoRolIdPago;
   }

   private java.sql.Date fechaPago;

   public java.sql.Date getFechaPago() {
      return fechaPago;
   }

   public void setFechaPago(java.sql.Date fechaPago) {
      this.fechaPago = fechaPago;
   }

   private java.lang.Long identificacion;

   public java.lang.Long getIdentificacion() {
      return identificacion;
   }

   public void setIdentificacion(java.lang.Long identificacion) {
      this.identificacion = identificacion;
   }
   public RubroEventualData() {
   }

   public RubroEventualData(com.spirit.nomina.entity.RubroEventualIf value) {
      setId(value.getId());
      setContratoId(value.getContratoId());
      setRubroId(value.getRubroId());
      setTipoRolIdCobro(value.getTipoRolIdCobro());
      setValor(value.getValor());
      setFechaCobro(value.getFechaCobro());
      setEstado(value.getEstado());
      setObservacion(value.getObservacion());
      setTipoRolIdPago(value.getTipoRolIdPago());
      setFechaPago(value.getFechaPago());
      setIdentificacion(value.getIdentificacion());
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
		return ToStringer.toString((RubroEventualIf)this);
	}
}
