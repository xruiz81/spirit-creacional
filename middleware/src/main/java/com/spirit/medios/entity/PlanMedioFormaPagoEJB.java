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

@Table(name = "PLAN_MEDIO_FORMA_PAGO")
@Entity
public class PlanMedioFormaPagoEJB implements Serializable, PlanMedioFormaPagoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long pedidoId;
   private java.lang.Long planMedioId;
   private java.sql.Timestamp fechaInicio;
   private java.sql.Timestamp fechaFin;
   private java.lang.String tipoFormaPago;
   private java.lang.Long formaPagoId;
   private java.lang.String estado;
   private java.lang.Long formaPagoCampanaProductoVersionId;

   public PlanMedioFormaPagoEJB() {
   }

   public PlanMedioFormaPagoEJB(com.spirit.medios.entity.PlanMedioFormaPagoIf value) {
      setId(value.getId());
      setPedidoId(value.getPedidoId());
      setPlanMedioId(value.getPlanMedioId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setTipoFormaPago(value.getTipoFormaPago());
      setFormaPagoId(value.getFormaPagoId());
      setEstado(value.getEstado());
      setFormaPagoCampanaProductoVersionId(value.getFormaPagoCampanaProductoVersionId());
   }

   public java.lang.Long create(com.spirit.medios.entity.PlanMedioFormaPagoIf value) {
      setId(value.getId());
      setPedidoId(value.getPedidoId());
      setPlanMedioId(value.getPlanMedioId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setTipoFormaPago(value.getTipoFormaPago());
      setFormaPagoId(value.getFormaPagoId());
      setEstado(value.getEstado());
      setFormaPagoCampanaProductoVersionId(value.getFormaPagoCampanaProductoVersionId());
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

   @Column(name = "PEDIDO_ID")
   public java.lang.Long getPedidoId() {
      return pedidoId;
   }

   public void setPedidoId(java.lang.Long pedidoId) {
      this.pedidoId = pedidoId;
   }

   @Column(name = "PLAN_MEDIO_ID")
   public java.lang.Long getPlanMedioId() {
      return planMedioId;
   }

   public void setPlanMedioId(java.lang.Long planMedioId) {
      this.planMedioId = planMedioId;
   }

   @Column(name = "FECHA_INICIO")
   public java.sql.Timestamp getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Timestamp fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   @Column(name = "FECHA_FIN")
   public java.sql.Timestamp getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Timestamp fechaFin) {
      this.fechaFin = fechaFin;
   }

   @Column(name = "TIPO_FORMA_PAGO")
   public java.lang.String getTipoFormaPago() {
      return tipoFormaPago;
   }

   public void setTipoFormaPago(java.lang.String tipoFormaPago) {
      this.tipoFormaPago = tipoFormaPago;
   }

   @Column(name = "FORMA_PAGO_ID")
   public java.lang.Long getFormaPagoId() {
      return formaPagoId;
   }

   public void setFormaPagoId(java.lang.Long formaPagoId) {
      this.formaPagoId = formaPagoId;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "FORMA_PAGO_CAMPANA_PRODUCTO_VERSION_ID")
   public java.lang.Long getFormaPagoCampanaProductoVersionId() {
      return formaPagoCampanaProductoVersionId;
   }

   public void setFormaPagoCampanaProductoVersionId(java.lang.Long formaPagoCampanaProductoVersionId) {
      this.formaPagoCampanaProductoVersionId = formaPagoCampanaProductoVersionId;
   }
	public String toString() {
		return ToStringer.toString((PlanMedioFormaPagoIf)this);
	}
}
