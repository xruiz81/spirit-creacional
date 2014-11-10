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

@Table(name = "PRENSA_SECCION")
@Entity
public class PrensaSeccionEJB implements Serializable, PrensaSeccionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long clienteId;
   private java.lang.String codigo;
   private java.lang.String seccion;

   public PrensaSeccionEJB() {
   }

   public PrensaSeccionEJB(com.spirit.medios.entity.PrensaSeccionIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setCodigo(value.getCodigo());
      setSeccion(value.getSeccion());
   }

   public java.lang.Long create(com.spirit.medios.entity.PrensaSeccionIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setCodigo(value.getCodigo());
      setSeccion(value.getSeccion());
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

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "SECCION")
   public java.lang.String getSeccion() {
      return seccion;
   }

   public void setSeccion(java.lang.String seccion) {
      this.seccion = seccion;
   }
	public String toString() {
		return ToStringer.toString((PrensaSeccionIf)this);
	}
}
