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

@Table(name = "REUNION")
@Entity
public class ReunionEJB implements Serializable, ReunionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long oficinaId;
   private java.lang.Long clienteId;
   private java.lang.String prospectocliente;
   private java.sql.Date fecha;
   private java.sql.Date fechaEnvio;
   private java.lang.Integer numEnviados;
   private java.lang.String descripcion;
   private java.lang.String estado;
   private java.lang.Long usuarioCreacionId;
   private java.sql.Date fechaCreacion;
   private java.lang.Long usuarioActualizacionId;
   private java.sql.Date fechaActualizacion;
   private java.lang.Long ejecutivoId;
   private java.lang.String conCopia;
   private java.lang.String lugarReunion;

   public ReunionEJB() {
   }

   public ReunionEJB(com.spirit.medios.entity.ReunionIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setClienteId(value.getClienteId());
      setProspectocliente(value.getProspectocliente());
      setFecha(value.getFecha());
      setFechaEnvio(value.getFechaEnvio());
      setNumEnviados(value.getNumEnviados());
      setDescripcion(value.getDescripcion());
      setEstado(value.getEstado());
      setUsuarioCreacionId(value.getUsuarioCreacionId());
      setFechaCreacion(value.getFechaCreacion());
      setUsuarioActualizacionId(value.getUsuarioActualizacionId());
      setFechaActualizacion(value.getFechaActualizacion());
      setEjecutivoId(value.getEjecutivoId());
      setConCopia(value.getConCopia());
      setLugarReunion(value.getLugarReunion());
   }

   public java.lang.Long create(com.spirit.medios.entity.ReunionIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setClienteId(value.getClienteId());
      setProspectocliente(value.getProspectocliente());
      setFecha(value.getFecha());
      setFechaEnvio(value.getFechaEnvio());
      setNumEnviados(value.getNumEnviados());
      setDescripcion(value.getDescripcion());
      setEstado(value.getEstado());
      setUsuarioCreacionId(value.getUsuarioCreacionId());
      setFechaCreacion(value.getFechaCreacion());
      setUsuarioActualizacionId(value.getUsuarioActualizacionId());
      setFechaActualizacion(value.getFechaActualizacion());
      setEjecutivoId(value.getEjecutivoId());
      setConCopia(value.getConCopia());
      setLugarReunion(value.getLugarReunion());
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

   @Column(name = "OFICINA_ID")
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   @Column(name = "CLIENTE_ID")
   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }

   @Column(name = "PROSPECTOCLIENTE")
   public java.lang.String getProspectocliente() {
      return prospectocliente;
   }

   public void setProspectocliente(java.lang.String prospectocliente) {
      this.prospectocliente = prospectocliente;
   }

   @Column(name = "FECHA")
   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   @Column(name = "FECHA_ENVIO")
   public java.sql.Date getFechaEnvio() {
      return fechaEnvio;
   }

   public void setFechaEnvio(java.sql.Date fechaEnvio) {
      this.fechaEnvio = fechaEnvio;
   }

   @Column(name = "NUM_ENVIADOS")
   public java.lang.Integer getNumEnviados() {
      return numEnviados;
   }

   public void setNumEnviados(java.lang.Integer numEnviados) {
      this.numEnviados = numEnviados;
   }

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "USUARIO_CREACION_ID")
   public java.lang.Long getUsuarioCreacionId() {
      return usuarioCreacionId;
   }

   public void setUsuarioCreacionId(java.lang.Long usuarioCreacionId) {
      this.usuarioCreacionId = usuarioCreacionId;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Date getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Date fechaCreacion) {
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
   public java.sql.Date getFechaActualizacion() {
      return fechaActualizacion;
   }

   public void setFechaActualizacion(java.sql.Date fechaActualizacion) {
      this.fechaActualizacion = fechaActualizacion;
   }

   @Column(name = "EJECUTIVO_ID")
   public java.lang.Long getEjecutivoId() {
      return ejecutivoId;
   }

   public void setEjecutivoId(java.lang.Long ejecutivoId) {
      this.ejecutivoId = ejecutivoId;
   }

   @Column(name = "CON_COPIA")
   public java.lang.String getConCopia() {
      return conCopia;
   }

   public void setConCopia(java.lang.String conCopia) {
      this.conCopia = conCopia;
   }

   @Column(name = "LUGAR_REUNION")
   public java.lang.String getLugarReunion() {
      return lugarReunion;
   }

   public void setLugarReunion(java.lang.String lugarReunion) {
      this.lugarReunion = lugarReunion;
   }
	public String toString() {
		return ToStringer.toString((ReunionIf)this);
	}
}
