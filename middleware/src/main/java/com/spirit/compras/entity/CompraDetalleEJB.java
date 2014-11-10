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

@Table(name = "COMPRA_DETALLE")
@Entity
public class CompraDetalleEJB implements Serializable, CompraDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long documentoId;
   private java.lang.Long compraId;
   private java.lang.Long productoId;
   private java.lang.Long cantidad;
   private java.math.BigDecimal valor;
   private java.math.BigDecimal descuento;
   private java.math.BigDecimal iva;
   private java.math.BigDecimal ice;
   private java.math.BigDecimal otroImpuesto;
   private java.lang.Long idSriAir;
   private java.lang.String descripcion;
   private java.lang.Long sriIvaRetencionId;
   private java.math.BigDecimal porcentajeDescuentosVarios;
   private java.math.BigDecimal porcentajeDescuentoEspecial;

   public CompraDetalleEJB() {
   }

   public CompraDetalleEJB(com.spirit.compras.entity.CompraDetalleIf value) {
      setId(value.getId());
      setDocumentoId(value.getDocumentoId());
      setCompraId(value.getCompraId());
      setProductoId(value.getProductoId());
      setCantidad(value.getCantidad());
      setValor(value.getValor());
      setDescuento(value.getDescuento());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setIdSriAir(value.getIdSriAir());
      setDescripcion(value.getDescripcion());
      setSriIvaRetencionId(value.getSriIvaRetencionId());
      setPorcentajeDescuentosVarios(value.getPorcentajeDescuentosVarios());
      setPorcentajeDescuentoEspecial(value.getPorcentajeDescuentoEspecial());
   }

   public java.lang.Long create(com.spirit.compras.entity.CompraDetalleIf value) {
      setId(value.getId());
      setDocumentoId(value.getDocumentoId());
      setCompraId(value.getCompraId());
      setProductoId(value.getProductoId());
      setCantidad(value.getCantidad());
      setValor(value.getValor());
      setDescuento(value.getDescuento());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setIdSriAir(value.getIdSriAir());
      setDescripcion(value.getDescripcion());
      setSriIvaRetencionId(value.getSriIvaRetencionId());
      setPorcentajeDescuentosVarios(value.getPorcentajeDescuentosVarios());
      setPorcentajeDescuentoEspecial(value.getPorcentajeDescuentoEspecial());
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

   @Column(name = "COMPRA_ID")
   public java.lang.Long getCompraId() {
      return compraId;
   }

   public void setCompraId(java.lang.Long compraId) {
      this.compraId = compraId;
   }

   @Column(name = "PRODUCTO_ID")
   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   @Column(name = "CANTIDAD")
   public java.lang.Long getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.lang.Long cantidad) {
      this.cantidad = cantidad;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "DESCUENTO")
   public java.math.BigDecimal getDescuento() {
      return descuento;
   }

   public void setDescuento(java.math.BigDecimal descuento) {
      this.descuento = descuento;
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

   @Column(name = "ID_SRI_AIR")
   public java.lang.Long getIdSriAir() {
      return idSriAir;
   }

   public void setIdSriAir(java.lang.Long idSriAir) {
      this.idSriAir = idSriAir;
   }

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "SRI_IVA_RETENCION_ID")
   public java.lang.Long getSriIvaRetencionId() {
      return sriIvaRetencionId;
   }

   public void setSriIvaRetencionId(java.lang.Long sriIvaRetencionId) {
      this.sriIvaRetencionId = sriIvaRetencionId;
   }

   @Column(name = "PORCENTAJE_DESCUENTOS_VARIOS")
   public java.math.BigDecimal getPorcentajeDescuentosVarios() {
      return porcentajeDescuentosVarios;
   }

   public void setPorcentajeDescuentosVarios(java.math.BigDecimal porcentajeDescuentosVarios) {
      this.porcentajeDescuentosVarios = porcentajeDescuentosVarios;
   }

   @Column(name = "PORCENTAJE_DESCUENTO_ESPECIAL")
   public java.math.BigDecimal getPorcentajeDescuentoEspecial() {
      return porcentajeDescuentoEspecial;
   }

   public void setPorcentajeDescuentoEspecial(java.math.BigDecimal porcentajeDescuentoEspecial) {
      this.porcentajeDescuentoEspecial = porcentajeDescuentoEspecial;
   }
	public String toString() {
		return ToStringer.toString((CompraDetalleIf)this);
	}
}
