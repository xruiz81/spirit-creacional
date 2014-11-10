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

@Table(name = "CONTRATO_GASTO_DEDUCIBLE")
@Entity
public class ContratoGastoDeducibleEJB implements Serializable, ContratoGastoDeducibleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long contratoId;
   private java.lang.Long gastoDeducibleId;
   private java.math.BigDecimal valor;
   private java.sql.Date fecha;

   public ContratoGastoDeducibleEJB() {
   }

   public ContratoGastoDeducibleEJB(com.spirit.nomina.entity.ContratoGastoDeducibleIf value) {
      setId(value.getId());
      setContratoId(value.getContratoId());
      setGastoDeducibleId(value.getGastoDeducibleId());
      setValor(value.getValor());
      setFecha(value.getFecha());
   }

   public java.lang.Long create(com.spirit.nomina.entity.ContratoGastoDeducibleIf value) {
      setId(value.getId());
      setContratoId(value.getContratoId());
      setGastoDeducibleId(value.getGastoDeducibleId());
      setValor(value.getValor());
      setFecha(value.getFecha());
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

   @Column(name = "CONTRATO_ID")
   public java.lang.Long getContratoId() {
      return contratoId;
   }

   public void setContratoId(java.lang.Long contratoId) {
      this.contratoId = contratoId;
   }

   @Column(name = "GASTO_DEDUCIBLE_ID")
   public java.lang.Long getGastoDeducibleId() {
      return gastoDeducibleId;
   }

   public void setGastoDeducibleId(java.lang.Long gastoDeducibleId) {
      this.gastoDeducibleId = gastoDeducibleId;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "FECHA")
   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }
	public String toString() {
		return ToStringer.toString((ContratoGastoDeducibleIf)this);
	}
}
