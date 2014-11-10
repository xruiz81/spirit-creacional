package com.spirit.inventario.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "MOVIMIENTO_DETALLE")
@Entity
public class MovimientoDetalleEJB implements Serializable, MovimientoDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long movimientoId;
   private java.lang.Long documentoId;
   private java.lang.Long loteId;
   private java.math.BigDecimal cantidad;
   private java.math.BigDecimal costo;
   private java.math.BigDecimal precio;
   private java.math.BigDecimal costoLifo;
   private java.math.BigDecimal costoFifo;
   private java.math.BigDecimal valorUnitario;
   private java.math.BigDecimal promedioUnitario;
   private java.math.BigDecimal stockValorizado;
   private java.math.BigDecimal stockAnterior;

   public MovimientoDetalleEJB() {
   }

   public MovimientoDetalleEJB(com.spirit.inventario.entity.MovimientoDetalleIf value) {
      setId(value.getId());
      setMovimientoId(value.getMovimientoId());
      setDocumentoId(value.getDocumentoId());
      setLoteId(value.getLoteId());
      setCantidad(value.getCantidad());
      setCosto(value.getCosto());
      setPrecio(value.getPrecio());
      setCostoLifo(value.getCostoLifo());
      setCostoFifo(value.getCostoFifo());
      setValorUnitario(value.getValorUnitario());
      setPromedioUnitario(value.getPromedioUnitario());
      setStockValorizado(value.getStockValorizado());
      setStockAnterior(value.getStockAnterior());
   }

   public java.lang.Long create(com.spirit.inventario.entity.MovimientoDetalleIf value) {
      setId(value.getId());
      setMovimientoId(value.getMovimientoId());
      setDocumentoId(value.getDocumentoId());
      setLoteId(value.getLoteId());
      setCantidad(value.getCantidad());
      setCosto(value.getCosto());
      setPrecio(value.getPrecio());
      setCostoLifo(value.getCostoLifo());
      setCostoFifo(value.getCostoFifo());
      setValorUnitario(value.getValorUnitario());
      setPromedioUnitario(value.getPromedioUnitario());
      setStockValorizado(value.getStockValorizado());
      setStockAnterior(value.getStockAnterior());
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

   @Column(name = "MOVIMIENTO_ID")
   public java.lang.Long getMovimientoId() {
      return movimientoId;
   }

   public void setMovimientoId(java.lang.Long movimientoId) {
      this.movimientoId = movimientoId;
   }

   @Column(name = "DOCUMENTO_ID")
   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   @Column(name = "LOTE_ID")
   public java.lang.Long getLoteId() {
      return loteId;
   }

   public void setLoteId(java.lang.Long loteId) {
      this.loteId = loteId;
   }

   @Column(name = "CANTIDAD")
   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   @Column(name = "COSTO")
   public java.math.BigDecimal getCosto() {
      return costo;
   }

   public void setCosto(java.math.BigDecimal costo) {
      this.costo = costo;
   }

   @Column(name = "PRECIO")
   public java.math.BigDecimal getPrecio() {
      return precio;
   }

   public void setPrecio(java.math.BigDecimal precio) {
      this.precio = precio;
   }

   @Column(name = "COSTO_LIFO")
   public java.math.BigDecimal getCostoLifo() {
      return costoLifo;
   }

   public void setCostoLifo(java.math.BigDecimal costoLifo) {
      this.costoLifo = costoLifo;
   }

   @Column(name = "COSTO_FIFO")
   public java.math.BigDecimal getCostoFifo() {
      return costoFifo;
   }

   public void setCostoFifo(java.math.BigDecimal costoFifo) {
      this.costoFifo = costoFifo;
   }

   @Column(name = "VALOR_UNITARIO")
   public java.math.BigDecimal getValorUnitario() {
      return valorUnitario;
   }

   public void setValorUnitario(java.math.BigDecimal valorUnitario) {
      this.valorUnitario = valorUnitario;
   }

   @Column(name = "PROMEDIO_UNITARIO")
   public java.math.BigDecimal getPromedioUnitario() {
      return promedioUnitario;
   }

   public void setPromedioUnitario(java.math.BigDecimal promedioUnitario) {
      this.promedioUnitario = promedioUnitario;
   }

   @Column(name = "STOCK_VALORIZADO")
   public java.math.BigDecimal getStockValorizado() {
      return stockValorizado;
   }

   public void setStockValorizado(java.math.BigDecimal stockValorizado) {
      this.stockValorizado = stockValorizado;
   }

   @Column(name = "STOCK_ANTERIOR")
   public java.math.BigDecimal getStockAnterior() {
      return stockAnterior;
   }

   public void setStockAnterior(java.math.BigDecimal stockAnterior) {
      this.stockAnterior = stockAnterior;
   }
	public String toString() {
		return ToStringer.toString((MovimientoDetalleIf)this);
	}
}
