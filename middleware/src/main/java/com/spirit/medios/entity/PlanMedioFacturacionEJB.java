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

@Table(name = "PLAN_MEDIO_FACTURACION")
@Entity
public class PlanMedioFacturacionEJB implements Serializable, PlanMedioFacturacionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long planMedioId;
   private java.lang.Long comercialId;
   private java.sql.Timestamp fechaInicio;
   private java.sql.Timestamp fechaFin;
   private java.lang.Long pedidoId;
   private java.lang.String canje;
   private java.lang.Long proveedorId;
   private java.lang.String estado;
   private java.math.BigDecimal porcentajeCanje;
   private java.lang.Long ordenMedioId;
   private java.lang.Long ordenMedioDetalleId;
   private java.lang.Long ordenMedioDetalleMapaId;
   private java.lang.Long productoClienteId;
   private java.lang.Long campanaProductoVersionId;

   public PlanMedioFacturacionEJB() {
   }

   public PlanMedioFacturacionEJB(com.spirit.medios.entity.PlanMedioFacturacionIf value) {
      setId(value.getId());
      setPlanMedioId(value.getPlanMedioId());
      setComercialId(value.getComercialId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setPedidoId(value.getPedidoId());
      setCanje(value.getCanje());
      setProveedorId(value.getProveedorId());
      setEstado(value.getEstado());
      setPorcentajeCanje(value.getPorcentajeCanje());
      setOrdenMedioId(value.getOrdenMedioId());
      setOrdenMedioDetalleId(value.getOrdenMedioDetalleId());
      setOrdenMedioDetalleMapaId(value.getOrdenMedioDetalleMapaId());
      setProductoClienteId(value.getProductoClienteId());
      setCampanaProductoVersionId(value.getCampanaProductoVersionId());
   }

   public java.lang.Long create(com.spirit.medios.entity.PlanMedioFacturacionIf value) {
      setId(value.getId());
      setPlanMedioId(value.getPlanMedioId());
      setComercialId(value.getComercialId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setPedidoId(value.getPedidoId());
      setCanje(value.getCanje());
      setProveedorId(value.getProveedorId());
      setEstado(value.getEstado());
      setPorcentajeCanje(value.getPorcentajeCanje());
      setOrdenMedioId(value.getOrdenMedioId());
      setOrdenMedioDetalleId(value.getOrdenMedioDetalleId());
      setOrdenMedioDetalleMapaId(value.getOrdenMedioDetalleMapaId());
      setProductoClienteId(value.getProductoClienteId());
      setCampanaProductoVersionId(value.getCampanaProductoVersionId());
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

   @Column(name = "PLAN_MEDIO_ID")
   public java.lang.Long getPlanMedioId() {
      return planMedioId;
   }

   public void setPlanMedioId(java.lang.Long planMedioId) {
      this.planMedioId = planMedioId;
   }

   @Column(name = "COMERCIAL_ID")
   public java.lang.Long getComercialId() {
      return comercialId;
   }

   public void setComercialId(java.lang.Long comercialId) {
      this.comercialId = comercialId;
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

   @Column(name = "PEDIDO_ID")
   public java.lang.Long getPedidoId() {
      return pedidoId;
   }

   public void setPedidoId(java.lang.Long pedidoId) {
      this.pedidoId = pedidoId;
   }

   @Column(name = "CANJE")
   public java.lang.String getCanje() {
      return canje;
   }

   public void setCanje(java.lang.String canje) {
      this.canje = canje;
   }

   @Column(name = "PROVEEDOR_ID")
   public java.lang.Long getProveedorId() {
      return proveedorId;
   }

   public void setProveedorId(java.lang.Long proveedorId) {
      this.proveedorId = proveedorId;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "PORCENTAJE_CANJE")
   public java.math.BigDecimal getPorcentajeCanje() {
      return porcentajeCanje;
   }

   public void setPorcentajeCanje(java.math.BigDecimal porcentajeCanje) {
      this.porcentajeCanje = porcentajeCanje;
   }

   @Column(name = "ORDEN_MEDIO_ID")
   public java.lang.Long getOrdenMedioId() {
      return ordenMedioId;
   }

   public void setOrdenMedioId(java.lang.Long ordenMedioId) {
      this.ordenMedioId = ordenMedioId;
   }

   @Column(name = "ORDEN_MEDIO_DETALLE_ID")
   public java.lang.Long getOrdenMedioDetalleId() {
      return ordenMedioDetalleId;
   }

   public void setOrdenMedioDetalleId(java.lang.Long ordenMedioDetalleId) {
      this.ordenMedioDetalleId = ordenMedioDetalleId;
   }

   @Column(name = "ORDEN_MEDIO_DETALLE_MAPA_ID")
   public java.lang.Long getOrdenMedioDetalleMapaId() {
      return ordenMedioDetalleMapaId;
   }

   public void setOrdenMedioDetalleMapaId(java.lang.Long ordenMedioDetalleMapaId) {
      this.ordenMedioDetalleMapaId = ordenMedioDetalleMapaId;
   }

   @Column(name = "PRODUCTO_CLIENTE_ID")
   public java.lang.Long getProductoClienteId() {
      return productoClienteId;
   }

   public void setProductoClienteId(java.lang.Long productoClienteId) {
      this.productoClienteId = productoClienteId;
   }

   @Column(name = "CAMPANA_PRODUCTO_VERSION_ID")
   public java.lang.Long getCampanaProductoVersionId() {
      return campanaProductoVersionId;
   }

   public void setCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) {
      this.campanaProductoVersionId = campanaProductoVersionId;
   }
	public String toString() {
		return ToStringer.toString((PlanMedioFacturacionIf)this);
	}
}
