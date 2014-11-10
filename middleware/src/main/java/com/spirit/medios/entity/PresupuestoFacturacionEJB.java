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

@Table(name = "PRESUPUESTO_FACTURACION")
@Entity
public class PresupuestoFacturacionEJB implements Serializable, PresupuestoFacturacionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long presupuestoDetalleId;
   private java.lang.Long facturaId;
   private java.lang.String estado;
   private java.lang.String tipo;

   public PresupuestoFacturacionEJB() {
   }

   public PresupuestoFacturacionEJB(com.spirit.medios.entity.PresupuestoFacturacionIf value) {
      setId(value.getId());
      setPresupuestoDetalleId(value.getPresupuestoDetalleId());
      setFacturaId(value.getFacturaId());
      setEstado(value.getEstado());
      setTipo(value.getTipo());
   }

   public java.lang.Long create(com.spirit.medios.entity.PresupuestoFacturacionIf value) {
      setId(value.getId());
      setPresupuestoDetalleId(value.getPresupuestoDetalleId());
      setFacturaId(value.getFacturaId());
      setEstado(value.getEstado());
      setTipo(value.getTipo());
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

   @Column(name = "PRESUPUESTO_DETALLE_ID")
   public java.lang.Long getPresupuestoDetalleId() {
      return presupuestoDetalleId;
   }

   public void setPresupuestoDetalleId(java.lang.Long presupuestoDetalleId) {
      this.presupuestoDetalleId = presupuestoDetalleId;
   }

   @Column(name = "FACTURA_ID")
   public java.lang.Long getFacturaId() {
      return facturaId;
   }

   public void setFacturaId(java.lang.Long facturaId) {
      this.facturaId = facturaId;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "TIPO")
   public java.lang.String getTipo() {
      return tipo;
   }

   public void setTipo(java.lang.String tipo) {
      this.tipo = tipo;
   }
	public String toString() {
		return ToStringer.toString((PresupuestoFacturacionIf)this);
	}
}
