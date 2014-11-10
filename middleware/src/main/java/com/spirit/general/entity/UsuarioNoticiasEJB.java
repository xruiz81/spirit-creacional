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

@Table(name = "USUARIO_NOTICIAS")
@Entity
public class UsuarioNoticiasEJB implements Serializable, UsuarioNoticiasIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long usuarioId;
   private java.lang.Long noticiasId;

   public UsuarioNoticiasEJB() {
   }

   public UsuarioNoticiasEJB(com.spirit.general.entity.UsuarioNoticiasIf value) {
      setId(value.getId());
      setUsuarioId(value.getUsuarioId());
      setNoticiasId(value.getNoticiasId());
   }

   public java.lang.Long create(com.spirit.general.entity.UsuarioNoticiasIf value) {
      setId(value.getId());
      setUsuarioId(value.getUsuarioId());
      setNoticiasId(value.getNoticiasId());
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

   @Column(name = "NOTICIAS_ID")
   public java.lang.Long getNoticiasId() {
      return noticiasId;
   }

   public void setNoticiasId(java.lang.Long noticiasId) {
      this.noticiasId = noticiasId;
   }
	public String toString() {
		return ToStringer.toString((UsuarioNoticiasIf)this);
	}
}
