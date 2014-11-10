package com.spirit.nomina.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class TipoRolData implements TipoRolIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
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

   private java.lang.String nemonico;

   public java.lang.String getNemonico() {
      return nemonico;
   }

   public void setNemonico(java.lang.String nemonico) {
      this.nemonico = nemonico;
   }

   private java.lang.String rubroEventual;

   public java.lang.String getRubroEventual() {
      return rubroEventual;
   }

   public void setRubroEventual(java.lang.String rubroEventual) {
      this.rubroEventual = rubroEventual;
   }

   private java.lang.Long documentoId;

   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
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

   private java.lang.String rubroProvisionado;

   public java.lang.String getRubroProvisionado() {
      return rubroProvisionado;
   }

   public void setRubroProvisionado(java.lang.String rubroProvisionado) {
      this.rubroProvisionado = rubroProvisionado;
   }

   private java.lang.String formaPago;

   public java.lang.String getFormaPago() {
      return formaPago;
   }

   public void setFormaPago(java.lang.String formaPago) {
      this.formaPago = formaPago;
   }
   public TipoRolData() {
   }

   public TipoRolData(com.spirit.nomina.entity.TipoRolIf value) {
      setId(value.getId());
      setEmpresaId(value.getEmpresaId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setNemonico(value.getNemonico());
      setRubroEventual(value.getRubroEventual());
      setDocumentoId(value.getDocumentoId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setRubroProvisionado(value.getRubroProvisionado());
      setFormaPago(value.getFormaPago());
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
		return ToStringer.toString((TipoRolIf)this);
	}
}
