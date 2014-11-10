package com.spirit.nomina.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class RolPagoDetalleUtilidadData implements RolPagoDetalleUtilidadIf, Serializable    {


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

   private java.lang.Long contratoUtilidadId;

   public java.lang.Long getContratoUtilidadId() {
      return contratoUtilidadId;
   }

   public void setContratoUtilidadId(java.lang.Long contratoUtilidadId) {
      this.contratoUtilidadId = contratoUtilidadId;
   }

   private java.lang.Long contratoId;

   public java.lang.Long getContratoId() {
      return contratoId;
   }

   public void setContratoId(java.lang.Long contratoId) {
      this.contratoId = contratoId;
   }

   private java.lang.String cargo;

   public java.lang.String getCargo() {
      return cargo;
   }

   public void setCargo(java.lang.String cargo) {
      this.cargo = cargo;
   }

   private java.sql.Date fechaIngreso;

   public java.sql.Date getFechaIngreso() {
      return fechaIngreso;
   }

   public void setFechaIngreso(java.sql.Date fechaIngreso) {
      this.fechaIngreso = fechaIngreso;
   }

   private java.sql.Date fechaSalida;

   public java.sql.Date getFechaSalida() {
      return fechaSalida;
   }

   public void setFechaSalida(java.sql.Date fechaSalida) {
      this.fechaSalida = fechaSalida;
   }

   private java.lang.String genero;

   public java.lang.String getGenero() {
      return genero;
   }

   public void setGenero(java.lang.String genero) {
      this.genero = genero;
   }

   private java.lang.Integer diasLaborados;

   public java.lang.Integer getDiasLaborados() {
      return diasLaborados;
   }

   public void setDiasLaborados(java.lang.Integer diasLaborados) {
      this.diasLaborados = diasLaborados;
   }

   private java.math.BigDecimal utilidadContrato;

   public java.math.BigDecimal getUtilidadContrato() {
      return utilidadContrato;
   }

   public void setUtilidadContrato(java.math.BigDecimal utilidadContrato) {
      this.utilidadContrato = utilidadContrato;
   }

   private java.lang.Integer numeroCargas;

   public java.lang.Integer getNumeroCargas() {
      return numeroCargas;
   }

   public void setNumeroCargas(java.lang.Integer numeroCargas) {
      this.numeroCargas = numeroCargas;
   }

   private java.lang.Integer diasCargas;

   public java.lang.Integer getDiasCargas() {
      return diasCargas;
   }

   public void setDiasCargas(java.lang.Integer diasCargas) {
      this.diasCargas = diasCargas;
   }

   private java.math.BigDecimal utilidadCargas;

   public java.math.BigDecimal getUtilidadCargas() {
      return utilidadCargas;
   }

   public void setUtilidadCargas(java.math.BigDecimal utilidadCargas) {
      this.utilidadCargas = utilidadCargas;
   }

   private java.math.BigDecimal total;

   public java.math.BigDecimal getTotal() {
      return total;
   }

   public void setTotal(java.math.BigDecimal total) {
      this.total = total;
   }
   public RolPagoDetalleUtilidadData() {
   }

   public RolPagoDetalleUtilidadData(com.spirit.nomina.entity.RolPagoDetalleUtilidadIf value) {
      setId(value.getId());
      setRolpagoId(value.getRolpagoId());
      setContratoUtilidadId(value.getContratoUtilidadId());
      setContratoId(value.getContratoId());
      setCargo(value.getCargo());
      setFechaIngreso(value.getFechaIngreso());
      setFechaSalida(value.getFechaSalida());
      setGenero(value.getGenero());
      setDiasLaborados(value.getDiasLaborados());
      setUtilidadContrato(value.getUtilidadContrato());
      setNumeroCargas(value.getNumeroCargas());
      setDiasCargas(value.getDiasCargas());
      setUtilidadCargas(value.getUtilidadCargas());
      setTotal(value.getTotal());
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
		return ToStringer.toString((RolPagoDetalleUtilidadIf)this);
	}
}
