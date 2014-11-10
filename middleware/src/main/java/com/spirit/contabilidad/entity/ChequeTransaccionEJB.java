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

@Table(name = "CHEQUE_TRANSACCION")
@Entity
public class ChequeTransaccionEJB implements Serializable, ChequeTransaccionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long chequeEmitidoId;
   private java.lang.Long transaccionId;
   private java.lang.String origen;

   public ChequeTransaccionEJB() {
   }

   public ChequeTransaccionEJB(com.spirit.contabilidad.entity.ChequeTransaccionIf value) {
      setId(value.getId());
      setChequeEmitidoId(value.getChequeEmitidoId());
      setTransaccionId(value.getTransaccionId());
      setOrigen(value.getOrigen());
   }

   public java.lang.Long create(com.spirit.contabilidad.entity.ChequeTransaccionIf value) {
      setId(value.getId());
      setChequeEmitidoId(value.getChequeEmitidoId());
      setTransaccionId(value.getTransaccionId());
      setOrigen(value.getOrigen());
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

   @Column(name = "CHEQUE_EMITIDO_ID")
   public java.lang.Long getChequeEmitidoId() {
      return chequeEmitidoId;
   }

   public void setChequeEmitidoId(java.lang.Long chequeEmitidoId) {
      this.chequeEmitidoId = chequeEmitidoId;
   }

   @Column(name = "TRANSACCION_ID")
   public java.lang.Long getTransaccionId() {
      return transaccionId;
   }

   public void setTransaccionId(java.lang.Long transaccionId) {
      this.transaccionId = transaccionId;
   }

   @Column(name = "ORIGEN")
   public java.lang.String getOrigen() {
      return origen;
   }

   public void setOrigen(java.lang.String origen) {
      this.origen = origen;
   }
	public String toString() {
		return ToStringer.toString((ChequeTransaccionIf)this);
	}
}
