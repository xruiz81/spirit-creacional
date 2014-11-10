package com.spirit.crm.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "CLIENTE_RETENCION")
@Entity
public class ClienteRetencionEJB implements Serializable, ClienteRetencionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long clienteId;
   private java.lang.Long sriAirId;
   private java.lang.Long sriIvaRetencionId;

   public ClienteRetencionEJB() {
   }

   public ClienteRetencionEJB(com.spirit.crm.entity.ClienteRetencionIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setSriAirId(value.getSriAirId());
      setSriIvaRetencionId(value.getSriIvaRetencionId());
   }

   public java.lang.Long create(com.spirit.crm.entity.ClienteRetencionIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setSriAirId(value.getSriAirId());
      setSriIvaRetencionId(value.getSriIvaRetencionId());
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

   @Column(name = "CLIENTE_ID")
   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }

   @Column(name = "SRI_AIR_ID")
   public java.lang.Long getSriAirId() {
      return sriAirId;
   }

   public void setSriAirId(java.lang.Long sriAirId) {
      this.sriAirId = sriAirId;
   }

   @Column(name = "SRI_IVA_RETENCION_ID")
   public java.lang.Long getSriIvaRetencionId() {
      return sriIvaRetencionId;
   }

   public void setSriIvaRetencionId(java.lang.Long sriIvaRetencionId) {
      this.sriIvaRetencionId = sriIvaRetencionId;
   }
	public String toString() {
		return ToStringer.toString((ClienteRetencionIf)this);
	}
}
