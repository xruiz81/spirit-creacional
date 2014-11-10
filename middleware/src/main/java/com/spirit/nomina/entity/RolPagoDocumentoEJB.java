package com.spirit.nomina.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "ROL_PAGO_DOCUMENTO")
@Entity
public class RolPagoDocumentoEJB implements Serializable, RolPagoDocumentoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long tipoRolId;
   private java.lang.Long tipoContratoId;
   private java.lang.Long documentoId;
   private java.sql.Date fechaCreacion;
   private java.lang.Long creacionUsuarioId;
   private java.sql.Date fechaActualizacion;
   private java.lang.Long actualizacionUsuarioId;
   private java.lang.Long empresaId;

   public RolPagoDocumentoEJB() {
   }

   public RolPagoDocumentoEJB(com.spirit.nomina.entity.RolPagoDocumentoIf value) {
      setId(value.getId());
      setTipoRolId(value.getTipoRolId());
      setTipoContratoId(value.getTipoContratoId());
      setDocumentoId(value.getDocumentoId());
      setFechaCreacion(value.getFechaCreacion());
      setCreacionUsuarioId(value.getCreacionUsuarioId());
      setFechaActualizacion(value.getFechaActualizacion());
      setActualizacionUsuarioId(value.getActualizacionUsuarioId());
      setEmpresaId(value.getEmpresaId());
   }

   public java.lang.Long create(com.spirit.nomina.entity.RolPagoDocumentoIf value) {
      setId(value.getId());
      setTipoRolId(value.getTipoRolId());
      setTipoContratoId(value.getTipoContratoId());
      setDocumentoId(value.getDocumentoId());
      setFechaCreacion(value.getFechaCreacion());
      setCreacionUsuarioId(value.getCreacionUsuarioId());
      setFechaActualizacion(value.getFechaActualizacion());
      setActualizacionUsuarioId(value.getActualizacionUsuarioId());
      setEmpresaId(value.getEmpresaId());
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

   @Column(name = "TIPO_ROL_ID")
   public java.lang.Long getTipoRolId() {
      return tipoRolId;
   }

   public void setTipoRolId(java.lang.Long tipoRolId) {
      this.tipoRolId = tipoRolId;
   }

   @Column(name = "TIPO_CONTRATO_ID")
   public java.lang.Long getTipoContratoId() {
      return tipoContratoId;
   }

   public void setTipoContratoId(java.lang.Long tipoContratoId) {
      this.tipoContratoId = tipoContratoId;
   }

   @Column(name = "DOCUMENTO_ID")
   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Date getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Date fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "CREACION_USUARIO_ID")
   public java.lang.Long getCreacionUsuarioId() {
      return creacionUsuarioId;
   }

   public void setCreacionUsuarioId(java.lang.Long creacionUsuarioId) {
      this.creacionUsuarioId = creacionUsuarioId;
   }

   @Column(name = "FECHA_ACTUALIZACION")
   public java.sql.Date getFechaActualizacion() {
      return fechaActualizacion;
   }

   public void setFechaActualizacion(java.sql.Date fechaActualizacion) {
      this.fechaActualizacion = fechaActualizacion;
   }

   @Column(name = "ACTUALIZACION_USUARIO_ID")
   public java.lang.Long getActualizacionUsuarioId() {
      return actualizacionUsuarioId;
   }

   public void setActualizacionUsuarioId(java.lang.Long actualizacionUsuarioId) {
      this.actualizacionUsuarioId = actualizacionUsuarioId;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }
	public String toString() {
		return ToStringer.toString((RolPagoDocumentoIf)this);
	}
}
