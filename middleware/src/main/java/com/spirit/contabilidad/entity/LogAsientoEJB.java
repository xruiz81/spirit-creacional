package com.spirit.contabilidad.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "LOG_ASIENTO")
@Entity
public class LogAsientoEJB implements Serializable, LogAsientoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String numero;
   private java.lang.Long empresaId;
   private java.lang.Long periodoId;
   private java.lang.Long plancuentaId;
   private java.sql.Date fecha;
   private java.lang.String status;
   private java.lang.String efectivo;
   private java.lang.Long subtipoasientoId;
   private java.lang.String observacion;
   private java.lang.Long oficinaId;
   private java.lang.Long tipoDocumentoId;
   private java.lang.Long transaccionId;
   private java.lang.String log;
   private java.lang.Long elaboradoPorId;
   private java.lang.Long autorizadoPorId;
   private java.lang.Long carteraAfectaId;
   private java.lang.Long eventoContableId;
   private java.lang.String asientoCierre;
   private java.lang.String usarNota;
   private java.lang.String nota;

   public LogAsientoEJB() {
   }

   public LogAsientoEJB(com.spirit.contabilidad.entity.LogAsientoIf value) {
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
      setLog(value.getLog());
      setElaboradoPorId(value.getElaboradoPorId());
      setAutorizadoPorId(value.getAutorizadoPorId());
      setCarteraAfectaId(value.getCarteraAfectaId());
      setEventoContableId(value.getEventoContableId());
      setAsientoCierre(value.getAsientoCierre());
      setUsarNota(value.getUsarNota());
      setNota(value.getNota());
   }

   public java.lang.Long create(com.spirit.contabilidad.entity.LogAsientoIf value) {
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
      setLog(value.getLog());
      setElaboradoPorId(value.getElaboradoPorId());
      setAutorizadoPorId(value.getAutorizadoPorId());
      setCarteraAfectaId(value.getCarteraAfectaId());
      setEventoContableId(value.getEventoContableId());
      setAsientoCierre(value.getAsientoCierre());
      setUsarNota(value.getUsarNota());
      setNota(value.getNota());
      return value.getPrimaryKey();
   }

   @javax.persistence.Transient public java.lang.Long getPrimaryKey() {
        return getId();
    }

   @javax.persistence.Transient public void setPrimaryKey(java.lang.Long primaryKey) {
       setId(primaryKey);
    }

   @Column(name = "ID")
@TableGenerator(name="SEQ_GEN",
			allocationSize=HibernateSequenceAllocationSize.allocationSize
)
   @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_GEN")
   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   @Column(name = "NUMERO")
   public java.lang.String getNumero() {
      return numero;
   }

   public void setNumero(java.lang.String numero) {
      this.numero = numero;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "PERIODO_ID")
   public java.lang.Long getPeriodoId() {
      return periodoId;
   }

   public void setPeriodoId(java.lang.Long periodoId) {
      this.periodoId = periodoId;
   }

   @Column(name = "PLANCUENTA_ID")
   public java.lang.Long getPlancuentaId() {
      return plancuentaId;
   }

   public void setPlancuentaId(java.lang.Long plancuentaId) {
      this.plancuentaId = plancuentaId;
   }

   @Column(name = "FECHA")
   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   @Column(name = "STATUS")
   public java.lang.String getStatus() {
      return status;
   }

   public void setStatus(java.lang.String status) {
      this.status = status;
   }

   @Column(name = "EFECTIVO")
   public java.lang.String getEfectivo() {
      return efectivo;
   }

   public void setEfectivo(java.lang.String efectivo) {
      this.efectivo = efectivo;
   }

   @Column(name = "SUBTIPOASIENTO_ID")
   public java.lang.Long getSubtipoasientoId() {
      return subtipoasientoId;
   }

   public void setSubtipoasientoId(java.lang.Long subtipoasientoId) {
      this.subtipoasientoId = subtipoasientoId;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "OFICINA_ID")
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   @Column(name = "TIPO_DOCUMENTO_ID")
   public java.lang.Long getTipoDocumentoId() {
      return tipoDocumentoId;
   }

   public void setTipoDocumentoId(java.lang.Long tipoDocumentoId) {
      this.tipoDocumentoId = tipoDocumentoId;
   }

   @Column(name = "TRANSACCION_ID")
   public java.lang.Long getTransaccionId() {
      return transaccionId;
   }

   public void setTransaccionId(java.lang.Long transaccionId) {
      this.transaccionId = transaccionId;
   }

   @Column(name = "LOG")
   public java.lang.String getLog() {
      return log;
   }

   public void setLog(java.lang.String log) {
      this.log = log;
   }

   @Column(name = "ELABORADO_POR_ID")
   public java.lang.Long getElaboradoPorId() {
      return elaboradoPorId;
   }

   public void setElaboradoPorId(java.lang.Long elaboradoPorId) {
      this.elaboradoPorId = elaboradoPorId;
   }

   @Column(name = "AUTORIZADO_POR_ID")
   public java.lang.Long getAutorizadoPorId() {
      return autorizadoPorId;
   }

   public void setAutorizadoPorId(java.lang.Long autorizadoPorId) {
      this.autorizadoPorId = autorizadoPorId;
   }

   @Column(name = "CARTERA_AFECTA_ID")
   public java.lang.Long getCarteraAfectaId() {
      return carteraAfectaId;
   }

   public void setCarteraAfectaId(java.lang.Long carteraAfectaId) {
      this.carteraAfectaId = carteraAfectaId;
   }

   @Column(name = "EVENTO_CONTABLE_ID")
   public java.lang.Long getEventoContableId() {
      return eventoContableId;
   }

   public void setEventoContableId(java.lang.Long eventoContableId) {
      this.eventoContableId = eventoContableId;
   }

   @Column(name = "ASIENTO_CIERRE")
   public java.lang.String getAsientoCierre() {
      return asientoCierre;
   }

   public void setAsientoCierre(java.lang.String asientoCierre) {
      this.asientoCierre = asientoCierre;
   }

   @Column(name = "USAR_NOTA")
   public java.lang.String getUsarNota() {
      return usarNota;
   }

   public void setUsarNota(java.lang.String usarNota) {
      this.usarNota = usarNota;
   }

   @Column(name = "NOTA")
   public java.lang.String getNota() {
      return nota;
   }

   public void setNota(java.lang.String nota) {
      this.nota = nota;
   }
	public String toString() {
		return ToStringer.toString((LogAsientoIf)this);
	}
}
