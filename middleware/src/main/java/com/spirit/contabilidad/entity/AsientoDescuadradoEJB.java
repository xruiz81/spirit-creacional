package com.spirit.contabilidad.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "ASIENTO_DESCUADRADO")
@Entity
public class AsientoDescuadradoEJB implements Serializable, AsientoDescuadradoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String asientoNumero;

   public AsientoDescuadradoEJB() {
   }

   public AsientoDescuadradoEJB(com.spirit.contabilidad.entity.AsientoDescuadradoIf value) {
      setId(value.getId());
      setAsientoNumero(value.getAsientoNumero());
   }

   public java.lang.Long create(com.spirit.contabilidad.entity.AsientoDescuadradoIf value) {
      setId(value.getId());
      setAsientoNumero(value.getAsientoNumero());
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

   @Column(name = "ASIENTO_NUMERO")
   public java.lang.String getAsientoNumero() {
      return asientoNumero;
   }

   public void setAsientoNumero(java.lang.String asientoNumero) {
      this.asientoNumero = asientoNumero;
   }
	public String toString() {
		return ToStringer.toString((AsientoDescuadradoIf)this);
	}
}
