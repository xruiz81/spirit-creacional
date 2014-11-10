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

@Table(name = "PAUTA_GENERICO_CLIENTE")
@Entity
public class PautaGenericoClienteEJB implements Serializable, PautaGenericoClienteIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long clienteId;
   private java.lang.Long tipoProductoId;
   private java.lang.Long genericoId;

   public PautaGenericoClienteEJB() {
   }

   public PautaGenericoClienteEJB(com.spirit.medios.entity.PautaGenericoClienteIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setTipoProductoId(value.getTipoProductoId());
      setGenericoId(value.getGenericoId());
   }

   public java.lang.Long create(com.spirit.medios.entity.PautaGenericoClienteIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setTipoProductoId(value.getTipoProductoId());
      setGenericoId(value.getGenericoId());
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

   @Column(name = "TIPO_PRODUCTO_ID")
   public java.lang.Long getTipoProductoId() {
      return tipoProductoId;
   }

   public void setTipoProductoId(java.lang.Long tipoProductoId) {
      this.tipoProductoId = tipoProductoId;
   }

   @Column(name = "GENERICO_ID")
   public java.lang.Long getGenericoId() {
      return genericoId;
   }

   public void setGenericoId(java.lang.Long genericoId) {
      this.genericoId = genericoId;
   }
	public String toString() {
		return ToStringer.toString((PautaGenericoClienteIf)this);
	}
}
