package com.spirit.nomina.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ContratoUtilidadData implements ContratoUtilidadIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
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

   private java.math.BigDecimal utilidad;

   public java.math.BigDecimal getUtilidad() {
      return utilidad;
   }

   public void setUtilidad(java.math.BigDecimal utilidad) {
      this.utilidad = utilidad;
   }

   private java.math.BigDecimal porcentajeContrato;

   public java.math.BigDecimal getPorcentajeContrato() {
      return porcentajeContrato;
   }

   public void setPorcentajeContrato(java.math.BigDecimal porcentajeContrato) {
      this.porcentajeContrato = porcentajeContrato;
   }

   private java.math.BigDecimal porcentajeCarga;

   public java.math.BigDecimal getPorcentajeCarga() {
      return porcentajeCarga;
   }

   public void setPorcentajeCarga(java.math.BigDecimal porcentajeCarga) {
      this.porcentajeCarga = porcentajeCarga;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }
   public ContratoUtilidadData() {
   }

   public ContratoUtilidadData(com.spirit.nomina.entity.ContratoUtilidadIf value) {
      setId(value.getId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setUtilidad(value.getUtilidad());
      setPorcentajeContrato(value.getPorcentajeContrato());
      setPorcentajeCarga(value.getPorcentajeCarga());
      setCodigo(value.getCodigo());
      setEmpresaId(value.getEmpresaId());
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
		return ToStringer.toString((ContratoUtilidadIf)this);
	}
}
