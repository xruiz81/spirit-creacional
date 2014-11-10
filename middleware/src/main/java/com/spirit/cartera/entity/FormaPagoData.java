package com.spirit.cartera.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class FormaPagoData implements FormaPagoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String nombre;

   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   private java.lang.Integer diasInicio;

   public java.lang.Integer getDiasInicio() {
      return diasInicio;
   }

   public void setDiasInicio(java.lang.Integer diasInicio) {
      this.diasInicio = diasInicio;
   }

   private java.lang.Integer diasFin;

   public java.lang.Integer getDiasFin() {
      return diasFin;
   }

   public void setDiasFin(java.lang.Integer diasFin) {
      this.diasFin = diasFin;
   }

   private java.math.BigDecimal descuentoVenta;

   public java.math.BigDecimal getDescuentoVenta() {
      return descuentoVenta;
   }

   public void setDescuentoVenta(java.math.BigDecimal descuentoVenta) {
      this.descuentoVenta = descuentoVenta;
   }

   private java.math.BigDecimal descuentoCartera;

   public java.math.BigDecimal getDescuentoCartera() {
      return descuentoCartera;
   }

   public void setDescuentoCartera(java.math.BigDecimal descuentoCartera) {
      this.descuentoCartera = descuentoCartera;
   }

   private java.math.BigDecimal interes;

   public java.math.BigDecimal getInteres() {
      return interes;
   }

   public void setInteres(java.math.BigDecimal interes) {
      this.interes = interes;
   }
   public FormaPagoData() {
   }

   public FormaPagoData(com.spirit.cartera.entity.FormaPagoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setDiasInicio(value.getDiasInicio());
      setDiasFin(value.getDiasFin());
      setDescuentoVenta(value.getDescuentoVenta());
      setDescuentoCartera(value.getDescuentoCartera());
      setInteres(value.getInteres());
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
		return ToStringer.toString((FormaPagoIf)this);
	}
}
