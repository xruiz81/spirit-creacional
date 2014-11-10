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

@Table(name = "USUARIO_PUNTO_IMPRESION")
@Entity
public class UsuarioPuntoImpresionEJB implements Serializable, UsuarioPuntoImpresionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long puntoimpresionId;
   private java.lang.Long usuarioId;

   public UsuarioPuntoImpresionEJB() {
   }

   public UsuarioPuntoImpresionEJB(com.spirit.general.entity.UsuarioPuntoImpresionIf value) {
      setId(value.getId());
      setPuntoimpresionId(value.getPuntoimpresionId());
      setUsuarioId(value.getUsuarioId());
   }

   public java.lang.Long create(com.spirit.general.entity.UsuarioPuntoImpresionIf value) {
      setId(value.getId());
      setPuntoimpresionId(value.getPuntoimpresionId());
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

   @Column(name = "PUNTOIMPRESION_ID")
   public java.lang.Long getPuntoimpresionId() {
      return puntoimpresionId;
   }

   public void setPuntoimpresionId(java.lang.Long puntoimpresionId) {
      this.puntoimpresionId = puntoimpresionId;
   }

   @Column(name = "USUARIO_ID")
   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }
	public String toString() {
		return ToStringer.toString((UsuarioPuntoImpresionIf)this);
	}
}
