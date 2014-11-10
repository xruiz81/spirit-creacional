package com.spirit.compras.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "SOLICITUD_COMPRA")
@Entity
public class SolicitudCompraEJB implements Serializable, SolicitudCompraIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long tipodocumentoId;
   private java.lang.String codigo;
   private java.lang.Long oficinaId;
   private java.lang.Long bodegaId;
   private java.lang.Long empleadoId;
   private java.sql.Date fecha;
   private java.sql.Date fechaEntrega;
   private java.lang.Long usuarioId;
   private java.lang.String estado;
   private java.lang.String observacion;
   private java.lang.String estadoBpm;
   private java.lang.String tipoReferencia;
   private java.lang.String referencia;

   public SolicitudCompraEJB() {
   }

   public SolicitudCompraEJB(com.spirit.compras.entity.SolicitudCompraIf value) {
      setId(value.getId());
      setTipodocumentoId(value.getTipodocumentoId());
      setCodigo(value.getCodigo());
      setOficinaId(value.getOficinaId());
      setBodegaId(value.getBodegaId());
      setEmpleadoId(value.getEmpleadoId());
      setFecha(value.getFecha());
      setFechaEntrega(value.getFechaEntrega());
      setUsuarioId(value.getUsuarioId());
      setEstado(value.getEstado());
      setObservacion(value.getObservacion());
      setEstadoBpm(value.getEstadoBpm());
      setTipoReferencia(value.getTipoReferencia());
      setReferencia(value.getReferencia());
   }

   public java.lang.Long create(com.spirit.compras.entity.SolicitudCompraIf value) {
      setId(value.getId());
      setTipodocumentoId(value.getTipodocumentoId());
      setCodigo(value.getCodigo());
      setOficinaId(value.getOficinaId());
      setBodegaId(value.getBodegaId());
      setEmpleadoId(value.getEmpleadoId());
      setFecha(value.getFecha());
      setFechaEntrega(value.getFechaEntrega());
      setUsuarioId(value.getUsuarioId());
      setEstado(value.getEstado());
      setObservacion(value.getObservacion());
      setEstadoBpm(value.getEstadoBpm());
      setTipoReferencia(value.getTipoReferencia());
      setReferencia(value.getReferencia());
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

   @Column(name = "TIPODOCUMENTO_ID")
   public java.lang.Long getTipodocumentoId() {
      return tipodocumentoId;
   }

   public void setTipodocumentoId(java.lang.Long tipodocumentoId) {
      this.tipodocumentoId = tipodocumentoId;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "OFICINA_ID")
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   @Column(name = "BODEGA_ID")
   public java.lang.Long getBodegaId() {
      return bodegaId;
   }

   public void setBodegaId(java.lang.Long bodegaId) {
      this.bodegaId = bodegaId;
   }

   @Column(name = "EMPLEADO_ID")
   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   @Column(name = "FECHA")
   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   @Column(name = "FECHA_ENTREGA")
   public java.sql.Date getFechaEntrega() {
      return fechaEntrega;
   }

   public void setFechaEntrega(java.sql.Date fechaEntrega) {
      this.fechaEntrega = fechaEntrega;
   }

   @Column(name = "USUARIO_ID")
   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "ESTADO_BPM")
   public java.lang.String getEstadoBpm() {
      return estadoBpm;
   }

   public void setEstadoBpm(java.lang.String estadoBpm) {
      this.estadoBpm = estadoBpm;
   }

   @Column(name = "TIPO_REFERENCIA")
   public java.lang.String getTipoReferencia() {
      return tipoReferencia;
   }

   public void setTipoReferencia(java.lang.String tipoReferencia) {
      this.tipoReferencia = tipoReferencia;
   }

   @Column(name = "REFERENCIA")
   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }
	public String toString() {
		return ToStringer.toString((SolicitudCompraIf)this);
	}
}
