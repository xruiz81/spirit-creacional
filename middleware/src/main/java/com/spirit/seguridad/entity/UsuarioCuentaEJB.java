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

@Table(name = "USUARIO_CUENTA")
@Entity
public class UsuarioCuentaEJB implements Serializable, UsuarioCuentaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long usuarioId;
   private java.lang.Long cuentaId;

   public UsuarioCuentaEJB() {
   }

   public UsuarioCuentaEJB(com.spirit.seguridad.entity.UsuarioCuentaIf value) {
      setId(value.getId());
      setUsuarioId(value.getUsuarioId());
      setCuentaId(value.getCuentaId());
   }

   public java.lang.Long create(com.spirit.seguridad.entity.UsuarioCuentaIf value) {
      setId(value.getId());
      setUsuarioId(value.getUsuarioId());
      setCuentaId(value.getCuentaId());
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

   @Column(name = "CUENTA_ID")
   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }
	public String toString() {
		return ToStringer.toString((UsuarioCuentaIf)this);
	}
}
