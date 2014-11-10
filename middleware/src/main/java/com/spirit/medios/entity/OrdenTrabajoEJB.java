package com.spirit.medios.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "ORDEN_TRABAJO")
@Entity
public class OrdenTrabajoEJB implements Serializable, OrdenTrabajoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String descripcion;
   private java.lang.Long oficinaId;
   private java.lang.Long clienteoficinaId;
   private java.lang.Long campanaId;
   private java.lang.Long empleadoId;
   private java.sql.Timestamp fecha;
   private java.sql.Timestamp fechalimite;
   private java.sql.Timestamp fechaentrega;
   private java.lang.String urlPropuesta;
   private java.lang.String estado;
   private java.lang.String observacion;
   private java.lang.Long usuarioCreacionId;
   private java.sql.Timestamp fechaCreacion;
   private java.lang.Long usuarioActualizacionId;
   private java.sql.Timestamp fechaActualizacion;
   private java.lang.Long equipoId;
   private java.lang.String timetracker;

   public OrdenTrabajoEJB() {
   }

   public OrdenTrabajoEJB(com.spirit.medios.entity.OrdenTrabajoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setDescripcion(value.getDescripcion());
      setOficinaId(value.getOficinaId());
      setClienteoficinaId(value.getClienteoficinaId());
      setCampanaId(value.getCampanaId());
      setEmpleadoId(value.getEmpleadoId());
      setFecha(value.getFecha());
      setFechalimite(value.getFechalimite());
      setFechaentrega(value.getFechaentrega());
      setUrlPropuesta(value.getUrlPropuesta());
      setEstado(value.getEstado());
      setObservacion(value.getObservacion());
      setUsuarioCreacionId(value.getUsuarioCreacionId());
      setFechaCreacion(value.getFechaCreacion());
      setUsuarioActualizacionId(value.getUsuarioActualizacionId());
      setFechaActualizacion(value.getFechaActualizacion());
      setEquipoId(value.getEquipoId());
      setTimetracker(value.getTimetracker());
   }

   public java.lang.Long create(com.spirit.medios.entity.OrdenTrabajoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setDescripcion(value.getDescripcion());
      setOficinaId(value.getOficinaId());
      setClienteoficinaId(value.getClienteoficinaId());
      setCampanaId(value.getCampanaId());
      setEmpleadoId(value.getEmpleadoId());
      setFecha(value.getFecha());
      setFechalimite(value.getFechalimite());
      setFechaentrega(value.getFechaentrega());
      setUrlPropuesta(value.getUrlPropuesta());
      setEstado(value.getEstado());
      setObservacion(value.getObservacion());
      setUsuarioCreacionId(value.getUsuarioCreacionId());
      setFechaCreacion(value.getFechaCreacion());
      setUsuarioActualizacionId(value.getUsuarioActualizacionId());
      setFechaActualizacion(value.getFechaActualizacion());
      setEquipoId(value.getEquipoId());
      setTimetracker(value.getTimetracker());
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

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "OFICINA_ID")
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   @Column(name = "CLIENTEOFICINA_ID")
   public java.lang.Long getClienteoficinaId() {
      return clienteoficinaId;
   }

   public void setClienteoficinaId(java.lang.Long clienteoficinaId) {
      this.clienteoficinaId = clienteoficinaId;
   }

   @Column(name = "CAMPANA_ID")
   public java.lang.Long getCampanaId() {
      return campanaId;
   }

   public void setCampanaId(java.lang.Long campanaId) {
      this.campanaId = campanaId;
   }

   @Column(name = "EMPLEADO_ID")
   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   @Column(name = "FECHA")
   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   @Column(name = "FECHALIMITE")
   public java.sql.Timestamp getFechalimite() {
      return fechalimite;
   }

   public void setFechalimite(java.sql.Timestamp fechalimite) {
      this.fechalimite = fechalimite;
   }

   @Column(name = "FECHAENTREGA")
   public java.sql.Timestamp getFechaentrega() {
      return fechaentrega;
   }

   public void setFechaentrega(java.sql.Timestamp fechaentrega) {
      this.fechaentrega = fechaentrega;
   }

   @Column(name = "URL_PROPUESTA")
   public java.lang.String getUrlPropuesta() {
      return urlPropuesta;
   }

   public void setUrlPropuesta(java.lang.String urlPropuesta) {
      this.urlPropuesta = urlPropuesta;
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

   @Column(name = "USUARIO_CREACION_ID")
   public java.lang.Long getUsuarioCreacionId() {
      return usuarioCreacionId;
   }

   public void setUsuarioCreacionId(java.lang.Long usuarioCreacionId) {
      this.usuarioCreacionId = usuarioCreacionId;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "USUARIO_ACTUALIZACION_ID")
   public java.lang.Long getUsuarioActualizacionId() {
      return usuarioActualizacionId;
   }

   public void setUsuarioActualizacionId(java.lang.Long usuarioActualizacionId) {
      this.usuarioActualizacionId = usuarioActualizacionId;
   }

   @Column(name = "FECHA_ACTUALIZACION")
   public java.sql.Timestamp getFechaActualizacion() {
      return fechaActualizacion;
   }

   public void setFechaActualizacion(java.sql.Timestamp fechaActualizacion) {
      this.fechaActualizacion = fechaActualizacion;
   }

   @Column(name = "EQUIPO_ID")
   public java.lang.Long getEquipoId() {
      return equipoId;
   }

   public void setEquipoId(java.lang.Long equipoId) {
      this.equipoId = equipoId;
   }

   @Column(name = "TIMETRACKER")
   public java.lang.String getTimetracker() {
      return timetracker;
   }

   public void setTimetracker(java.lang.String timetracker) {
      this.timetracker = timetracker;
   }
	public String toString() {
		return ToStringer.toString((OrdenTrabajoIf)this);
	}
}
