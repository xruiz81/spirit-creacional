package com.spirit.inventario.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "SOLICITUD_TRANSFERENCIA")
@Entity
public class SolicitudTransferenciaEJB implements Serializable, SolicitudTransferenciaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.sql.Timestamp fechaDocumento;
   private java.sql.Timestamp fechaIngreso;
   private java.lang.String codigo;
   private java.lang.Long bodegaDesdeId;
   private java.lang.Long bodegaHaciaId;
   private java.lang.String observacion;
   private java.lang.String estado;
   private java.lang.Long usuarioId;

   public SolicitudTransferenciaEJB() {
   }

   public SolicitudTransferenciaEJB(com.spirit.inventario.entity.SolicitudTransferenciaIf value) {
      setId(value.getId());
      setFechaDocumento(value.getFechaDocumento());
      setFechaIngreso(value.getFechaIngreso());
      setCodigo(value.getCodigo());
      setBodegaDesdeId(value.getBodegaDesdeId());
      setBodegaHaciaId(value.getBodegaHaciaId());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setUsuarioId(value.getUsuarioId());
   }

   public java.lang.Long create(com.spirit.inventario.entity.SolicitudTransferenciaIf value) {
      setId(value.getId());
      setFechaDocumento(value.getFechaDocumento());
      setFechaIngreso(value.getFechaIngreso());
      setCodigo(value.getCodigo());
      setBodegaDesdeId(value.getBodegaDesdeId());
      setBodegaHaciaId(value.getBodegaHaciaId());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setUsuarioId(value.getUsuarioId());
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

   @Column(name = "FECHA_DOCUMENTO")
   public java.sql.Timestamp getFechaDocumento() {
      return fechaDocumento;
   }

   public void setFechaDocumento(java.sql.Timestamp fechaDocumento) {
      this.fechaDocumento = fechaDocumento;
   }

   @Column(name = "FECHA_INGRESO")
   public java.sql.Timestamp getFechaIngreso() {
      return fechaIngreso;
   }

   public void setFechaIngreso(java.sql.Timestamp fechaIngreso) {
      this.fechaIngreso = fechaIngreso;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "BODEGA_DESDE_ID")
   public java.lang.Long getBodegaDesdeId() {
      return bodegaDesdeId;
   }

   public void setBodegaDesdeId(java.lang.Long bodegaDesdeId) {
      this.bodegaDesdeId = bodegaDesdeId;
   }

   @Column(name = "BODEGA_HACIA_ID")
   public java.lang.Long getBodegaHaciaId() {
      return bodegaHaciaId;
   }

   public void setBodegaHaciaId(java.lang.Long bodegaHaciaId) {
      this.bodegaHaciaId = bodegaHaciaId;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "USUARIO_ID")
   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }
	public String toString() {
		return ToStringer.toString((SolicitudTransferenciaIf)this);
	}
}
