package com.spirit.nomina.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "CONTRATO_PLANTILLA_DETALLE")
@Entity
public class ContratoPlantillaDetalleEJB implements Serializable, ContratoPlantillaDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long contratoPlantillaId;
   private java.lang.Long rubroId;
   private java.math.BigDecimal valor;

   public ContratoPlantillaDetalleEJB() {
   }

   public ContratoPlantillaDetalleEJB(com.spirit.nomina.entity.ContratoPlantillaDetalleIf value) {
      setId(value.getId());
      setContratoPlantillaId(value.getContratoPlantillaId());
      setRubroId(value.getRubroId());
      setValor(value.getValor());
   }

   public java.lang.Long create(com.spirit.nomina.entity.ContratoPlantillaDetalleIf value) {
      setId(value.getId());
      setContratoPlantillaId(value.getContratoPlantillaId());
      setRubroId(value.getRubroId());
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

   @Column(name = "CONTRATO_PLANTILLA_ID")
   public java.lang.Long getContratoPlantillaId() {
      return contratoPlantillaId;
   }

   public void setContratoPlantillaId(java.lang.Long contratoPlantillaId) {
      this.contratoPlantillaId = contratoPlantillaId;
   }

   @Column(name = "RUBRO_ID")
   public java.lang.Long getRubroId() {
      return rubroId;
   }

   public void setRubroId(java.lang.Long rubroId) {
      this.rubroId = rubroId;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }
	public String toString() {
		return ToStringer.toString((ContratoPlantillaDetalleIf)this);
	}
}
