package com.spirit.pos.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "VENTAS_TRACK")
@Entity
public class VentasTrackEJB implements Serializable, VentasTrackIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.math.BigDecimal valorTotal;
   private java.lang.Long cajasesionId;
   private java.sql.Timestamp fechaVenta;

   public VentasTrackEJB() {
   }

   public VentasTrackEJB(com.spirit.pos.entity.VentasTrackIf value) {
      setId(value.getId());
      setValorTotal(value.getValorTotal());
      setCajasesionId(value.getCajasesionId());
      setFechaVenta(value.getFechaVenta());
   }

   public java.lang.Long create(com.spirit.pos.entity.VentasTrackIf value) {
      setId(value.getId());
      setValorTotal(value.getValorTotal());
      setCajasesionId(value.getCajasesionId());
      setFechaVenta(value.getFechaVenta());
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

   @Column(name = "VALOR_TOTAL")
   public java.math.BigDecimal getValorTotal() {
      return valorTotal;
   }

   public void setValorTotal(java.math.BigDecimal valorTotal) {
      this.valorTotal = valorTotal;
   }

   @Column(name = "CAJASESION_ID")
   public java.lang.Long getCajasesionId() {
      return cajasesionId;
   }

   public void setCajasesionId(java.lang.Long cajasesionId) {
      this.cajasesionId = cajasesionId;
   }

   @Column(name = "FECHA_VENTA")
   public java.sql.Timestamp getFechaVenta() {
      return fechaVenta;
   }

   public void setFechaVenta(java.sql.Timestamp fechaVenta) {
      this.fechaVenta = fechaVenta;
   }
	public String toString() {
		return ToStringer.toString((VentasTrackIf)this);
	}
}
