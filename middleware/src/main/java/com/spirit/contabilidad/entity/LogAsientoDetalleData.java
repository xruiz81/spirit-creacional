package com.spirit.contabilidad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class LogAsientoDetalleData implements LogAsientoDetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long cuentaId;

   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }

   private java.lang.Long logAsientoId;

   public java.lang.Long getLogAsientoId() {
      return logAsientoId;
   }

   public void setLogAsientoId(java.lang.Long logAsientoId) {
      this.logAsientoId = logAsientoId;
   }

   private java.lang.String referencia;

   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }

   private java.lang.String glosa;

   public java.lang.String getGlosa() {
      return glosa;
   }

   public void setGlosa(java.lang.String glosa) {
      this.glosa = glosa;
   }

   private java.lang.Long centrogastoId;

   public java.lang.Long getCentrogastoId() {
      return centrogastoId;
   }

   public void setCentrogastoId(java.lang.Long centrogastoId) {
      this.centrogastoId = centrogastoId;
   }

   private java.lang.Long empleadoId;

   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   private java.lang.Long departamentoId;

   public java.lang.Long getDepartamentoId() {
      return departamentoId;
   }

   public void setDepartamentoId(java.lang.Long departamentoId) {
      this.departamentoId = departamentoId;
   }

   private java.lang.Long lineaId;

   public java.lang.Long getLineaId() {
      return lineaId;
   }

   public void setLineaId(java.lang.Long lineaId) {
      this.lineaId = lineaId;
   }

   private java.lang.Long clienteId;

   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }

   private java.math.BigDecimal debe;

   public java.math.BigDecimal getDebe() {
      return debe;
   }

   public void setDebe(java.math.BigDecimal debe) {
      this.debe = debe;
   }

   private java.math.BigDecimal haber;

   public java.math.BigDecimal getHaber() {
      return haber;
   }

   public void setHaber(java.math.BigDecimal haber) {
      this.haber = haber;
   }

   private java.lang.String log;

   public java.lang.String getLog() {
      return log;
   }

   public void setLog(java.lang.String log) {
      this.log = log;
   }
   public LogAsientoDetalleData() {
   }

   public LogAsientoDetalleData(com.spirit.contabilidad.entity.LogAsientoDetalleIf value) {
      setId(value.getId());
      setCuentaId(value.getCuentaId());
      setLogAsientoId(value.getLogAsientoId());
      setReferencia(value.getReferencia());
      setGlosa(value.getGlosa());
      setCentrogastoId(value.getCentrogastoId());
      setEmpleadoId(value.getEmpleadoId());
      setDepartamentoId(value.getDepartamentoId());
      setLineaId(value.getLineaId());
      setClienteId(value.getClienteId());
      setDebe(value.getDebe());
      setHaber(value.getHaber());
      setLog(value.getLog());
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
		return ToStringer.toString((LogAsientoDetalleIf)this);
	}
}
