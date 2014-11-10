package com.spirit.facturacion.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "FACTURA_DETALLE_COMPRA_ASOCIADA")
@Entity
public class FacturaDetalleCompraAsociadaEJB implements Serializable, FacturaDetalleCompraAsociadaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long facturaDetalleId;
   private java.lang.Long compraId;

   public FacturaDetalleCompraAsociadaEJB() {
   }

   public FacturaDetalleCompraAsociadaEJB(com.spirit.facturacion.entity.FacturaDetalleCompraAsociadaIf value) {
      setId(value.getId());
      setFacturaDetalleId(value.getFacturaDetalleId());
      setCompraId(value.getCompraId());
   }

   public java.lang.Long create(com.spirit.facturacion.entity.FacturaDetalleCompraAsociadaIf value) {
      setId(value.getId());
      setFacturaDetalleId(value.getFacturaDetalleId());
      setCompraId(value.getCompraId());
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

   @Column(name = "FACTURA_DETALLE_ID")
   public java.lang.Long getFacturaDetalleId() {
      return facturaDetalleId;
   }

   public void setFacturaDetalleId(java.lang.Long facturaDetalleId) {
      this.facturaDetalleId = facturaDetalleId;
   }

   @Column(name = "COMPRA_ID")
   public java.lang.Long getCompraId() {
      return compraId;
   }

   public void setCompraId(java.lang.Long compraId) {
      this.compraId = compraId;
   }
	public String toString() {
		return ToStringer.toString((FacturaDetalleCompraAsociadaIf)this);
	}
}
