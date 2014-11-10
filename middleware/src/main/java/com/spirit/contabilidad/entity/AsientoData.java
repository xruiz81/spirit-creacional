package com.spirit.contabilidad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class AsientoData implements AsientoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String numero;

   public java.lang.String getNumero() {
      return numero;
   }

   public void setNumero(java.lang.String numero) {
      this.numero = numero;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   private java.lang.Long periodoId;

   public java.lang.Long getPeriodoId() {
      return periodoId;
   }

   public void setPeriodoId(java.lang.Long periodoId) {
      this.periodoId = periodoId;
   }

   private java.lang.Long plancuentaId;

   public java.lang.Long getPlancuentaId() {
      return plancuentaId;
   }

   public void setPlancuentaId(java.lang.Long plancuentaId) {
      this.plancuentaId = plancuentaId;
   }

   private java.sql.Date fecha;

   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   private java.lang.String status;

   public java.lang.String getStatus() {
      return status;
   }

   public void setStatus(java.lang.String status) {
      this.status = status;
   }

   private java.lang.String efectivo;

   public java.lang.String getEfectivo() {
      return efectivo;
   }

   public void setEfectivo(java.lang.String efectivo) {
      this.efectivo = efectivo;
   }

   private java.lang.Long subtipoasientoId;

   public java.lang.Long getSubtipoasientoId() {
      return subtipoasientoId;
   }

   public void setSubtipoasientoId(java.lang.Long subtipoasientoId) {
      this.subtipoasientoId = subtipoasientoId;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.lang.Long oficinaId;

   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   private java.lang.Long tipoDocumentoId;

   public java.lang.Long getTipoDocumentoId() {
      return tipoDocumentoId;
   }

   public void setTipoDocumentoId(java.lang.Long tipoDocumentoId) {
      this.tipoDocumentoId = tipoDocumentoId;
   }

   private java.lang.Long transaccionId;

   public java.lang.Long getTransaccionId() {
      return transaccionId;
   }

   public void setTransaccionId(java.lang.Long transaccionId) {
      this.transaccionId = transaccionId;
   }

   private java.lang.Long elaboradoPorId;

   public java.lang.Long getElaboradoPorId() {
      return elaboradoPorId;
   }

   public void setElaboradoPorId(java.lang.Long elaboradoPorId) {
      this.elaboradoPorId = elaboradoPorId;
   }

   private java.lang.Long autorizadoPorId;

   public java.lang.Long getAutorizadoPorId() {
      return autorizadoPorId;
   }

   public void setAutorizadoPorId(java.lang.Long autorizadoPorId) {
      this.autorizadoPorId = autorizadoPorId;
   }

   private java.lang.Long carteraAfectaId;

   public java.lang.Long getCarteraAfectaId() {
      return carteraAfectaId;
   }

   public void setCarteraAfectaId(java.lang.Long carteraAfectaId) {
      this.carteraAfectaId = carteraAfectaId;
   }

   private java.lang.Long eventoContableId;

   public java.lang.Long getEventoContableId() {
      return eventoContableId;
   }

   public void setEventoContableId(java.lang.Long eventoContableId) {
      this.eventoContableId = eventoContableId;
   }

   private java.lang.String asientoCierre;

   public java.lang.String getAsientoCierre() {
      return asientoCierre;
   }

   public void setAsientoCierre(java.lang.String asientoCierre) {
      this.asientoCierre = asientoCierre;
   }

   private java.lang.String usarNota;

   public java.lang.String getUsarNota() {
      return usarNota;
   }

   public void setUsarNota(java.lang.String usarNota) {
      this.usarNota = usarNota;
   }

   private java.lang.String nota;

   public java.lang.String getNota() {
      return nota;
   }

   public void setNota(java.lang.String nota) {
      this.nota = nota;
   }
   public AsientoData() {
   }

   public AsientoData(com.spirit.contabilidad.entity.AsientoIf value) {
      setId(value.getId());
      setNumero(value.getNumero());
      setEmpresaId(value.getEmpresaId());
      setPeriodoId(value.getPeriodoId());
      setPlancuentaId(value.getPlancuentaId());
      setFecha(value.getFecha());
      setStatus(value.getStatus());
      setEfectivo(value.getEfectivo());
      setSubtipoasientoId(value.getSubtipoasientoId());
      setObservacion(value.getObservacion());
      setOficinaId(value.getOficinaId());
      setTipoDocumentoId(value.getTipoDocumentoId());
      setTransaccionId(value.getTransaccionId());
      setElaboradoPorId(value.getElaboradoPorId());
      setAutorizadoPorId(value.getAutorizadoPorId());
      setCarteraAfectaId(value.getCarteraAfectaId());
      setEventoContableId(value.getEventoContableId());
      setAsientoCierre(value.getAsientoCierre());
      setUsarNota(value.getUsarNota());
      setNota(value.getNota());
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
		return ToStringer.toString((AsientoIf)this);
	}
}
