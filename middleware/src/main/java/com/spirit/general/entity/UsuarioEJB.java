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

@Table(name = "USUARIO")
@Entity
public class UsuarioEJB implements Serializable, UsuarioIf {

	private static final long serialVersionUID = -2376947863057637887L;

@PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String usuario;
   private java.lang.String clave;
   private java.lang.String tipousuario;
   private java.lang.Long empleadoId;
   private java.lang.Long empresaId;
   private java.lang.String tipousuarioTimetracker;

   public UsuarioEJB() {
   }

   public UsuarioEJB(com.spirit.general.entity.UsuarioIf value) {
      setId(value.getId());
      setUsuario(value.getUsuario());
      setClave(value.getClave());
      setTipousuario(value.getTipousuario());
      setEmpleadoId(value.getEmpleadoId());
      setEmpresaId(value.getEmpresaId());
      setTipousuarioTimetracker(value.getTipousuarioTimetracker());
   }

   public java.lang.Long create(com.spirit.general.entity.UsuarioIf value) {
      setId(value.getId());
      setUsuario(value.getUsuario());
      setClave(value.getClave());
      setTipousuario(value.getTipousuario());
      setEmpleadoId(value.getEmpleadoId());
      setEmpresaId(value.getEmpresaId());
      setTipousuarioTimetracker(value.getTipousuarioTimetracker());
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

   @Column(name = "USUARIO")
   public java.lang.String getUsuario() {
      return usuario;
   }

   public void setUsuario(java.lang.String usuario) {
      this.usuario = usuario;
   }

   @Column(name = "CLAVE")
   public java.lang.String getClave() {
      return clave;
   }

   public void setClave(java.lang.String clave) {
      this.clave = clave;
   }

   @Column(name = "TIPOUSUARIO")
   public java.lang.String getTipousuario() {
      return tipousuario;
   }

   public void setTipousuario(java.lang.String tipousuario) {
      this.tipousuario = tipousuario;
   }

   @Column(name = "EMPLEADO_ID")
   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "TIPOUSUARIO_TIMETRACKER")
   public java.lang.String getTipousuarioTimetracker() {
      return tipousuarioTimetracker;
   }

   public void setTipousuarioTimetracker(java.lang.String tipousuarioTimetracker) {
      this.tipousuarioTimetracker = tipousuarioTimetracker;
   }
	public String toString() {
		return ToStringer.toString((UsuarioIf)this);
	}
}
