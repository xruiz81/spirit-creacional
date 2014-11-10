package com.spirit.medios.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "HERRAMIENTAS_MEDIOS")
@Entity
public class HerramientasMediosEJB implements Serializable, HerramientasMediosIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long clienteOficinaId;
   private java.lang.Long proveedorOficinaId;
   private java.lang.String frecuencia;

   public HerramientasMediosEJB() {
   }

   public HerramientasMediosEJB(com.spirit.medios.entity.HerramientasMediosIf value) {
      setId(value.getId());
      setClienteOficinaId(value.getClienteOficinaId());
      setProveedorOficinaId(value.getProveedorOficinaId());
      setFrecuencia(value.getFrecuencia());
   }

   public java.lang.Long create(com.spirit.medios.entity.HerramientasMediosIf value) {
      setId(value.getId());
      setClienteOficinaId(value.getClienteOficinaId());
      setProveedorOficinaId(value.getProveedorOficinaId());
      setFrecuencia(value.getFrecuencia());
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

   @Column(name = "CLIENTE_OFICINA_ID")
   public java.lang.Long getClienteOficinaId() {
      return clienteOficinaId;
   }

   public void setClienteOficinaId(java.lang.Long clienteOficinaId) {
      this.clienteOficinaId = clienteOficinaId;
   }

   @Column(name = "PROVEEDOR_OFICINA_ID")
   public java.lang.Long getProveedorOficinaId() {
      return proveedorOficinaId;
   }

   public void setProveedorOficinaId(java.lang.Long proveedorOficinaId) {
      this.proveedorOficinaId = proveedorOficinaId;
   }

   @Column(name = "FRECUENCIA")
   public java.lang.String getFrecuencia() {
      return frecuencia;
   }

   public void setFrecuencia(java.lang.String frecuencia) {
      this.frecuencia = frecuencia;
   }
	public String toString() {
		return ToStringer.toString((HerramientasMediosIf)this);
	}
}
