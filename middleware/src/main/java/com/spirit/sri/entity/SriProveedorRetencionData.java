package com.spirit.sri.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class SriProveedorRetencionData implements SriProveedorRetencionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String tipoPersona;

   public java.lang.String getTipoPersona() {
      return tipoPersona;
   }

   public void setTipoPersona(java.lang.String tipoPersona) {
      this.tipoPersona = tipoPersona;
   }

   private java.lang.String llevaContabilidad;

   public java.lang.String getLlevaContabilidad() {
      return llevaContabilidad;
   }

   public void setLlevaContabilidad(java.lang.String llevaContabilidad) {
      this.llevaContabilidad = llevaContabilidad;
   }

   private java.lang.String bienServicio;

   public java.lang.String getBienServicio() {
      return bienServicio;
   }

   public void setBienServicio(java.lang.String bienServicio) {
      this.bienServicio = bienServicio;
   }

   private java.math.BigDecimal retefuente;

   public java.math.BigDecimal getRetefuente() {
      return retefuente;
   }

   public void setRetefuente(java.math.BigDecimal retefuente) {
      this.retefuente = retefuente;
   }

   private java.math.BigDecimal reteiva;

   public java.math.BigDecimal getReteiva() {
      return reteiva;
   }

   public void setReteiva(java.math.BigDecimal reteiva) {
      this.reteiva = reteiva;
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

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.Long idCuentaRetefuente;

   public java.lang.Long getIdCuentaRetefuente() {
      return idCuentaRetefuente;
   }

   public void setIdCuentaRetefuente(java.lang.Long idCuentaRetefuente) {
      this.idCuentaRetefuente = idCuentaRetefuente;
   }

   private java.lang.Long idCuentaReteiva;

   public java.lang.Long getIdCuentaReteiva() {
      return idCuentaReteiva;
   }

   public void setIdCuentaReteiva(java.lang.Long idCuentaReteiva) {
      this.idCuentaReteiva = idCuentaReteiva;
   }

   private java.lang.String profesional;

   public java.lang.String getProfesional() {
      return profesional;
   }

   public void setProfesional(java.lang.String profesional) {
      this.profesional = profesional;
   }
   public SriProveedorRetencionData() {
   }

   public SriProveedorRetencionData(com.spirit.sri.entity.SriProveedorRetencionIf value) {
      setId(value.getId());
      setTipoPersona(value.getTipoPersona());
      setLlevaContabilidad(value.getLlevaContabilidad());
      setBienServicio(value.getBienServicio());
      setRetefuente(value.getRetefuente());
      setReteiva(value.getReteiva());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setEstado(value.getEstado());
      setIdCuentaRetefuente(value.getIdCuentaRetefuente());
      setIdCuentaReteiva(value.getIdCuentaReteiva());
      setProfesional(value.getProfesional());
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
		return ToStringer.toString((SriProveedorRetencionIf)this);
	}
}
