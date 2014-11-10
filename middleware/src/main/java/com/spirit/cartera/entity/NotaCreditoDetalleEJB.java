package com.spirit.cartera.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "NOTA_CREDITO_DETALLE")
@Entity
public class NotaCreditoDetalleEJB implements Serializable, NotaCreditoDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long documentoId;
   private java.lang.Long notaCreditoId;
   private java.lang.Long productoId;
   private java.math.BigDecimal cantidad;
   private java.math.BigDecimal valor;
   private java.math.BigDecimal iva;
   private java.math.BigDecimal ice;
   private java.math.BigDecimal otroImpuesto;
   private java.lang.String descripcion;
   private java.lang.String tipoReferencia;
   private java.lang.Long referenciaId;
   private java.lang.String tipoNota;
   private java.lang.String observacion;
   private java.lang.String tipoPresupuesto;
   private java.lang.Long presupuestoId;
   private java.lang.Long ordenId;

   public NotaCreditoDetalleEJB() {
   }

   public NotaCreditoDetalleEJB(com.spirit.cartera.entity.NotaCreditoDetalleIf value) {
      setId(value.getId());
      setDocumentoId(value.getDocumentoId());
      setNotaCreditoId(value.getNotaCreditoId());
      setProductoId(value.getProductoId());
      setCantidad(value.getCantidad());
      setValor(value.getValor());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setDescripcion(value.getDescripcion());
      setTipoReferencia(value.getTipoReferencia());
      setReferenciaId(value.getReferenciaId());
      setTipoNota(value.getTipoNota());
      setObservacion(value.getObservacion());
      setTipoPresupuesto(value.getTipoPresupuesto());
      setPresupuestoId(value.getPresupuestoId());
      setOrdenId(value.getOrdenId());
   }

   public java.lang.Long create(com.spirit.cartera.entity.NotaCreditoDetalleIf value) {
      setId(value.getId());
      setDocumentoId(value.getDocumentoId());
      setNotaCreditoId(value.getNotaCreditoId());
      setProductoId(value.getProductoId());
      setCantidad(value.getCantidad());
      setValor(value.getValor());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setDescripcion(value.getDescripcion());
      setTipoReferencia(value.getTipoReferencia());
      setReferenciaId(value.getReferenciaId());
      setTipoNota(value.getTipoNota());
      setObservacion(value.getObservacion());
      setTipoPresupuesto(value.getTipoPresupuesto());
      setPresupuestoId(value.getPresupuestoId());
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

   @Column(name = "DOCUMENTO_ID")
   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   @Column(name = "NOTA_CREDITO_ID")
   public java.lang.Long getNotaCreditoId() {
      return notaCreditoId;
   }

   public void setNotaCreditoId(java.lang.Long notaCreditoId) {
      this.notaCreditoId = notaCreditoId;
   }

   @Column(name = "PRODUCTO_ID")
   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   @Column(name = "CANTIDAD")
   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "IVA")
   public java.math.BigDecimal getIva() {
      return iva;
   }

   public void setIva(java.math.BigDecimal iva) {
      this.iva = iva;
   }

   @Column(name = "ICE")
   public java.math.BigDecimal getIce() {
      return ice;
   }

   public void setIce(java.math.BigDecimal ice) {
      this.ice = ice;
   }

   @Column(name = "OTRO_IMPUESTO")
   public java.math.BigDecimal getOtroImpuesto() {
      return otroImpuesto;
   }

   public void setOtroImpuesto(java.math.BigDecimal otroImpuesto) {
      this.otroImpuesto = otroImpuesto;
   }

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "TIPO_REFERENCIA")
   public java.lang.String getTipoReferencia() {
      return tipoReferencia;
   }

   public void setTipoReferencia(java.lang.String tipoReferencia) {
      this.tipoReferencia = tipoReferencia;
   }

   @Column(name = "REFERENCIA_ID")
   public java.lang.Long getReferenciaId() {
      return referenciaId;
   }

   public void setReferenciaId(java.lang.Long referenciaId) {
      this.referenciaId = referenciaId;
   }

   @Column(name = "TIPO_NOTA")
   public java.lang.String getTipoNota() {
      return tipoNota;
   }

   public void setTipoNota(java.lang.String tipoNota) {
      this.tipoNota = tipoNota;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "TIPO_PRESUPUESTO")
   public java.lang.String getTipoPresupuesto() {
      return tipoPresupuesto;
   }

   public void setTipoPresupuesto(java.lang.String tipoPresupuesto) {
      this.tipoPresupuesto = tipoPresupuesto;
   }

   @Column(name = "PRESUPUESTO_ID")
   public java.lang.Long getPresupuestoId() {
      return presupuestoId;
   }

   public void setPresupuestoId(java.lang.Long presupuestoId) {
      this.presupuestoId = presupuestoId;
   }

   @Column(name = "ORDEN_ID")
   public java.lang.Long getOrdenId() {
      return ordenId;
   }

   public void setOrdenId(java.lang.Long ordenId) {
      this.ordenId = ordenId;
   }
	public String toString() {
		return ToStringer.toString((NotaCreditoDetalleIf)this);
	}
}
