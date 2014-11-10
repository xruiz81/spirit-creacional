package com.spirit.general.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "USUARIO_DOCUMENTO")
@Entity
public class UsuarioDocumentoEJB implements Serializable, UsuarioDocumentoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long usuarioId;
   private java.lang.Long documentoId;
   private java.lang.String permisoImpresion;
   private java.lang.String permisoRegistro;
   private java.lang.String permisoBorrado;
   private java.lang.String permisoAutorizar;
   private java.lang.String estado;

   public UsuarioDocumentoEJB() {
   }

   public UsuarioDocumentoEJB(com.spirit.general.entity.UsuarioDocumentoIf value) {
      setId(value.getId());
      setUsuarioId(value.getUsuarioId());
      setDocumentoId(value.getDocumentoId());
      setPermisoImpresion(value.getPermisoImpresion());
      setPermisoRegistro(value.getPermisoRegistro());
      setPermisoBorrado(value.getPermisoBorrado());
      setPermisoAutorizar(value.getPermisoAutorizar());
      setEstado(value.getEstado());
   }

   public java.lang.Long create(com.spirit.general.entity.UsuarioDocumentoIf value) {
      setId(value.getId());
      setUsuarioId(value.getUsuarioId());
      setDocumentoId(value.getDocumentoId());
      setPermisoImpresion(value.getPermisoImpresion());
      setPermisoRegistro(value.getPermisoRegistro());
      setPermisoBorrado(value.getPermisoBorrado());
      setPermisoAutorizar(value.getPermisoAutorizar());
      setEstado(value.getEstado());
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

   @Column(name = "USUARIO_ID")
   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   @Column(name = "DOCUMENTO_ID")
   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   @Column(name = "PERMISO_IMPRESION")
   public java.lang.String getPermisoImpresion() {
      return permisoImpresion;
   }

   public void setPermisoImpresion(java.lang.String permisoImpresion) {
      this.permisoImpresion = permisoImpresion;
   }

   @Column(name = "PERMISO_REGISTRO")
   public java.lang.String getPermisoRegistro() {
      return permisoRegistro;
   }

   public void setPermisoRegistro(java.lang.String permisoRegistro) {
      this.permisoRegistro = permisoRegistro;
   }

   @Column(name = "PERMISO_BORRADO")
   public java.lang.String getPermisoBorrado() {
      return permisoBorrado;
   }

   public void setPermisoBorrado(java.lang.String permisoBorrado) {
      this.permisoBorrado = permisoBorrado;
   }

   @Column(name = "PERMISO_AUTORIZAR")
   public java.lang.String getPermisoAutorizar() {
      return permisoAutorizar;
   }

   public void setPermisoAutorizar(java.lang.String permisoAutorizar) {
      this.permisoAutorizar = permisoAutorizar;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
	public String toString() {
		return ToStringer.toString((UsuarioDocumentoIf)this);
	}
}
