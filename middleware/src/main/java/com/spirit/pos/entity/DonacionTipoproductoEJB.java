package com.spirit.pos.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "DONACION_TIPOPRODUCTO")
@Entity
public class DonacionTipoproductoEJB implements Serializable, DonacionTipoproductoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long tipoproductoId;
   private java.math.BigDecimal valor;

   public DonacionTipoproductoEJB() {
   }

   public DonacionTipoproductoEJB(com.spirit.pos.entity.DonacionTipoproductoIf value) {
      setId(value.getId());
      setTipoproductoId(value.getTipoproductoId());
      setValor(value.getValor());
   }

   public java.lang.Long create(com.spirit.pos.entity.DonacionTipoproductoIf value) {
      setId(value.getId());
      setTipoproductoId(value.getTipoproductoId());
      setValor(value.getValor());
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

   @Column(name = "TIPOPRODUCTO_ID")
   public java.lang.Long getTipoproductoId() {
      return tipoproductoId;
   }

   public void setTipoproductoId(java.lang.Long tipoproductoId) {
      this.tipoproductoId = tipoproductoId;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }
	public String toString() {
		return ToStringer.toString((DonacionTipoproductoIf)this);
	}
}
