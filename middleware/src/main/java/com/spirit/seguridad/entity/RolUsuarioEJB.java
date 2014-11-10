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

@Table(name = "ROL_USUARIO")
@Entity
public class RolUsuarioEJB implements Serializable, RolUsuarioIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long rolId;
   private java.lang.Long usuarioId;

   public RolUsuarioEJB() {
   }

   public RolUsuarioEJB(com.spirit.seguridad.entity.RolUsuarioIf value) {
      setId(value.getId());
      setRolId(value.getRolId());
      setUsuarioId(value.getUsuarioId());
   }

   public java.lang.Long create(com.spirit.seguridad.entity.RolUsuarioIf value) {
      setId(value.getId());
      setRolId(value.getRolId());
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

   @Column(name = "ROL_ID")
   public java.lang.Long getRolId() {
      return rolId;
   }

   public void setRolId(java.lang.Long rolId) {
      this.rolId = rolId;
   }

   @Column(name = "USUARIO_ID")
   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }
	public String toString() {
		return ToStringer.toString((RolUsuarioIf)this);
	}
}
