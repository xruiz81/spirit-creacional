package com.spirit.cartera.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "CARTERA_RELACIONADA")
@Entity
public class CarteraRelacionadaEJB implements Serializable, CarteraRelacionadaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long carteraOrigenId;
   private java.lang.Long carteraRelacionadaId;

   public CarteraRelacionadaEJB() {
   }

   public CarteraRelacionadaEJB(com.spirit.cartera.entity.CarteraRelacionadaIf value) {
      setId(value.getId());
      setCarteraOrigenId(value.getCarteraOrigenId());
      setCarteraRelacionadaId(value.getCarteraRelacionadaId());
   }

   public java.lang.Long create(com.spirit.cartera.entity.CarteraRelacionadaIf value) {
      setId(value.getId());
      setCarteraOrigenId(value.getCarteraOrigenId());
      setCarteraRelacionadaId(value.getCarteraRelacionadaId());
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

   @Column(name = "CARTERA_ORIGEN_ID")
   public java.lang.Long getCarteraOrigenId() {
      return carteraOrigenId;
   }

   public void setCarteraOrigenId(java.lang.Long carteraOrigenId) {
      this.carteraOrigenId = carteraOrigenId;
   }

   @Column(name = "CARTERA_RELACIONADA_ID")
   public java.lang.Long getCarteraRelacionadaId() {
      return carteraRelacionadaId;
   }

   public void setCarteraRelacionadaId(java.lang.Long carteraRelacionadaId) {
      this.carteraRelacionadaId = carteraRelacionadaId;
   }
	public String toString() {
		return ToStringer.toString((CarteraRelacionadaIf)this);
	}
}
