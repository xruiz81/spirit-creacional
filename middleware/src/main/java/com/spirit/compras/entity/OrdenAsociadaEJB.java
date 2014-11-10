package com.spirit.compras.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "ORDEN_ASOCIADA")
@Entity
public class OrdenAsociadaEJB implements Serializable, OrdenAsociadaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long compraId;
   private java.lang.String tipoOrden;
   private java.lang.Long ordenId;

   public OrdenAsociadaEJB() {
   }

   public OrdenAsociadaEJB(com.spirit.compras.entity.OrdenAsociadaIf value) {
      setId(value.getId());
      setCompraId(value.getCompraId());
      setTipoOrden(value.getTipoOrden());
      setOrdenId(value.getOrdenId());
   }

   public java.lang.Long create(com.spirit.compras.entity.OrdenAsociadaIf value) {
      setId(value.getId());
      setCompraId(value.getCompraId());
      setTipoOrden(value.getTipoOrden());
      setOrdenId(value.getOrdenId());
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

   @Column(name = "COMPRA_ID")
   public java.lang.Long getCompraId() {
      return compraId;
   }

   public void setCompraId(java.lang.Long compraId) {
      this.compraId = compraId;
   }

   @Column(name = "TIPO_ORDEN")
   public java.lang.String getTipoOrden() {
      return tipoOrden;
   }

   public void setTipoOrden(java.lang.String tipoOrden) {
      this.tipoOrden = tipoOrden;
   }

   @Column(name = "ORDEN_ID")
   public java.lang.Long getOrdenId() {
      return ordenId;
   }

   public void setOrdenId(java.lang.Long ordenId) {
      this.ordenId = ordenId;
   }
	public String toString() {
		return ToStringer.toString((OrdenAsociadaIf)this);
	}
}
