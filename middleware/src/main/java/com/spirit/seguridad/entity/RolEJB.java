package com.spirit.seguridad.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "ROL")
@Entity
public class RolEJB implements Serializable, RolIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.String status;
   private java.lang.String tipoRolUsuario;
   private java.lang.Long empresaId;

   public RolEJB() {
   }

   public RolEJB(com.spirit.seguridad.entity.RolIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setStatus(value.getStatus());
      setTipoRolUsuario(value.getTipoRolUsuario());
      setEmpresaId(value.getEmpresaId());
   }

   public java.lang.Long create(com.spirit.seguridad.entity.RolIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setStatus(value.getStatus());
      setTipoRolUsuario(value.getTipoRolUsuario());
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

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "NOMBRE")
   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   @Column(name = "STATUS")
   public java.lang.String getStatus() {
      return status;
   }

   public void setStatus(java.lang.String status) {
      this.status = status;
   }

   @Column(name = "TIPO_ROL_USUARIO")
   public java.lang.String getTipoRolUsuario() {
      return tipoRolUsuario;
   }

   public void setTipoRolUsuario(java.lang.String tipoRolUsuario) {
      this.tipoRolUsuario = tipoRolUsuario;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }
	public String toString() {
		return ToStringer.toString((RolIf)this);
	}
}
