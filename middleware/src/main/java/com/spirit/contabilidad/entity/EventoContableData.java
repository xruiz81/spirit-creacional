package com.spirit.contabilidad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class EventoContableData implements EventoContableIf, Serializable    {


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

   private java.lang.Long moduloId;

   public java.lang.Long getModuloId() {
      return moduloId;
   }

   public void setModuloId(java.lang.Long moduloId) {
      this.moduloId = moduloId;
   }

   private java.lang.Long oficinaId;

   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   private java.lang.Long documentoId;

   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   private java.lang.Long lineaId;

   public java.lang.Long getLineaId() {
      return lineaId;
   }

   public void setLineaId(java.lang.Long lineaId) {
      this.lineaId = lineaId;
   }

   private java.lang.Long planCuentaId;

   public java.lang.Long getPlanCuentaId() {
      return planCuentaId;
   }

   public void setPlanCuentaId(java.lang.Long planCuentaId) {
      this.planCuentaId = planCuentaId;
   }

   private java.lang.Long etapa;

   public java.lang.Long getEtapa() {
      return etapa;
   }

   public void setEtapa(java.lang.Long etapa) {
      this.etapa = etapa;
   }

   private java.lang.String autorizacionRequerida;

   public java.lang.String getAutorizacionRequerida() {
      return autorizacionRequerida;
   }

   public void setAutorizacionRequerida(java.lang.String autorizacionRequerida) {
      this.autorizacionRequerida = autorizacionRequerida;
   }

   private java.lang.String agruparDetalles;

   public java.lang.String getAgruparDetalles() {
      return agruparDetalles;
   }

   public void setAgruparDetalles(java.lang.String agruparDetalles) {
      this.agruparDetalles = agruparDetalles;
   }

   private java.lang.String usarDetalleDescripcion;

   public java.lang.String getUsarDetalleDescripcion() {
      return usarDetalleDescripcion;
   }

   public void setUsarDetalleDescripcion(java.lang.String usarDetalleDescripcion) {
      this.usarDetalleDescripcion = usarDetalleDescripcion;
   }

   private java.lang.String validoAlGuardar;

   public java.lang.String getValidoAlGuardar() {
      return validoAlGuardar;
   }

   public void setValidoAlGuardar(java.lang.String validoAlGuardar) {
      this.validoAlGuardar = validoAlGuardar;
   }

   private java.lang.String validoAlActualizar;

   public java.lang.String getValidoAlActualizar() {
      return validoAlActualizar;
   }

   public void setValidoAlActualizar(java.lang.String validoAlActualizar) {
      this.validoAlActualizar = validoAlActualizar;
   }

   private java.lang.Long subtipoAsientoId;

   public java.lang.Long getSubtipoAsientoId() {
      return subtipoAsientoId;
   }

   public void setSubtipoAsientoId(java.lang.Long subtipoAsientoId) {
      this.subtipoAsientoId = subtipoAsientoId;
   }
   public EventoContableData() {
   }

   public EventoContableData(com.spirit.contabilidad.entity.EventoContableIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setModuloId(value.getModuloId());
      setOficinaId(value.getOficinaId());
      setDocumentoId(value.getDocumentoId());
      setLineaId(value.getLineaId());
      setPlanCuentaId(value.getPlanCuentaId());
      setEtapa(value.getEtapa());
      setAutorizacionRequerida(value.getAutorizacionRequerida());
      setAgruparDetalles(value.getAgruparDetalles());
      setUsarDetalleDescripcion(value.getUsarDetalleDescripcion());
      setValidoAlGuardar(value.getValidoAlGuardar());
      setValidoAlActualizar(value.getValidoAlActualizar());
      setSubtipoAsientoId(value.getSubtipoAsientoId());
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
		return ToStringer.toString((EventoContableIf)this);
	}
}
