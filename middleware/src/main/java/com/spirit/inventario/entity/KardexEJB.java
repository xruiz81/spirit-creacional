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

@Table(name = "KARDEX")
@Entity
public class KardexEJB implements Serializable, KardexIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long movimientoId;
   private java.lang.String movimientoCodigo;
   private java.lang.Long id;
   private java.lang.Long empresaId;
   private java.lang.String empresaNombre;
   private java.lang.Long oficinaId;
   private java.lang.String oficinaCodigo;
   private java.lang.String oficinaNombre;
   private java.lang.Long bodegaId;
   private java.lang.String bodegaCodigo;
   private java.lang.String bodegaNombre;
   private java.lang.Long productoId;
   private java.lang.String productoCodigo;
   private java.lang.String productoNombre;
   private java.sql.Timestamp fechaMovimiento;
   private java.lang.String tipodocumentoCodigo;
   private java.lang.String tipodocumentoNombre;
   private java.lang.String tipodocumentoSignostock;
   private java.math.BigDecimal cantidadDetalle;
   private java.lang.Long loteId;
   private java.lang.String loteCodigo;
   private java.lang.String documentoCodigo;
   private java.lang.String documentoDescripcion;
   private java.math.BigDecimal promedioUnitario;
   private java.math.BigDecimal valorUnitario;
   private java.math.BigDecimal stockValorizado;
   private java.math.BigDecimal stockAnterior;

   public KardexEJB() {
   }

   public KardexEJB(com.spirit.inventario.entity.KardexIf value) {
      setMovimientoId(value.getMovimientoId());
      setMovimientoCodigo(value.getMovimientoCodigo());
      setId(value.getId());
      setEmpresaId(value.getEmpresaId());
      setEmpresaNombre(value.getEmpresaNombre());
      setOficinaId(value.getOficinaId());
      setOficinaCodigo(value.getOficinaCodigo());
      setOficinaNombre(value.getOficinaNombre());
      setBodegaId(value.getBodegaId());
      setBodegaCodigo(value.getBodegaCodigo());
      setBodegaNombre(value.getBodegaNombre());
      setProductoId(value.getProductoId());
      setProductoCodigo(value.getProductoCodigo());
      setProductoNombre(value.getProductoNombre());
      setFechaMovimiento(value.getFechaMovimiento());
      setTipodocumentoCodigo(value.getTipodocumentoCodigo());
      setTipodocumentoNombre(value.getTipodocumentoNombre());
      setTipodocumentoSignostock(value.getTipodocumentoSignostock());
      setCantidadDetalle(value.getCantidadDetalle());
      setLoteId(value.getLoteId());
      setLoteCodigo(value.getLoteCodigo());
      setDocumentoCodigo(value.getDocumentoCodigo());
      setDocumentoDescripcion(value.getDocumentoDescripcion());
      setPromedioUnitario(value.getPromedioUnitario());
      setValorUnitario(value.getValorUnitario());
      setStockValorizado(value.getStockValorizado());
      setStockAnterior(value.getStockAnterior());
   }

   public java.lang.Long create(com.spirit.inventario.entity.KardexIf value) {
      setMovimientoId(value.getMovimientoId());
      setMovimientoCodigo(value.getMovimientoCodigo());
      setId(value.getId());
      setEmpresaId(value.getEmpresaId());
      setEmpresaNombre(value.getEmpresaNombre());
      setOficinaId(value.getOficinaId());
      setOficinaCodigo(value.getOficinaCodigo());
      setOficinaNombre(value.getOficinaNombre());
      setBodegaId(value.getBodegaId());
      setBodegaCodigo(value.getBodegaCodigo());
      setBodegaNombre(value.getBodegaNombre());
      setProductoId(value.getProductoId());
      setProductoCodigo(value.getProductoCodigo());
      setProductoNombre(value.getProductoNombre());
      setFechaMovimiento(value.getFechaMovimiento());
      setTipodocumentoCodigo(value.getTipodocumentoCodigo());
      setTipodocumentoNombre(value.getTipodocumentoNombre());
      setTipodocumentoSignostock(value.getTipodocumentoSignostock());
      setCantidadDetalle(value.getCantidadDetalle());
      setLoteId(value.getLoteId());
      setLoteCodigo(value.getLoteCodigo());
      setDocumentoCodigo(value.getDocumentoCodigo());
      setDocumentoDescripcion(value.getDocumentoDescripcion());
      setPromedioUnitario(value.getPromedioUnitario());
      setValorUnitario(value.getValorUnitario());
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

   @Column(name = "MOVIMIENTO_ID")
   public java.lang.Long getMovimientoId() {
      return movimientoId;
   }

   public void setMovimientoId(java.lang.Long movimientoId) {
      this.movimientoId = movimientoId;
   }

   @Column(name = "MOVIMIENTO_CODIGO")
   public java.lang.String getMovimientoCodigo() {
      return movimientoCodigo;
   }

   public void setMovimientoCodigo(java.lang.String movimientoCodigo) {
      this.movimientoCodigo = movimientoCodigo;
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

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "EMPRESA_NOMBRE")
   public java.lang.String getEmpresaNombre() {
      return empresaNombre;
   }

   public void setEmpresaNombre(java.lang.String empresaNombre) {
      this.empresaNombre = empresaNombre;
   }

   @Column(name = "OFICINA_ID")
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   @Column(name = "OFICINA_CODIGO")
   public java.lang.String getOficinaCodigo() {
      return oficinaCodigo;
   }

   public void setOficinaCodigo(java.lang.String oficinaCodigo) {
      this.oficinaCodigo = oficinaCodigo;
   }

   @Column(name = "OFICINA_NOMBRE")
   public java.lang.String getOficinaNombre() {
      return oficinaNombre;
   }

   public void setOficinaNombre(java.lang.String oficinaNombre) {
      this.oficinaNombre = oficinaNombre;
   }

   @Column(name = "BODEGA_ID")
   public java.lang.Long getBodegaId() {
      return bodegaId;
   }

   public void setBodegaId(java.lang.Long bodegaId) {
      this.bodegaId = bodegaId;
   }

   @Column(name = "BODEGA_CODIGO")
   public java.lang.String getBodegaCodigo() {
      return bodegaCodigo;
   }

   public void setBodegaCodigo(java.lang.String bodegaCodigo) {
      this.bodegaCodigo = bodegaCodigo;
   }

   @Column(name = "BODEGA_NOMBRE")
   public java.lang.String getBodegaNombre() {
      return bodegaNombre;
   }

   public void setBodegaNombre(java.lang.String bodegaNombre) {
      this.bodegaNombre = bodegaNombre;
   }

   @Column(name = "PRODUCTO_ID")
   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   @Column(name = "PRODUCTO_CODIGO")
   public java.lang.String getProductoCodigo() {
      return productoCodigo;
   }

   public void setProductoCodigo(java.lang.String productoCodigo) {
      this.productoCodigo = productoCodigo;
   }

   @Column(name = "PRODUCTO_NOMBRE")
   public java.lang.String getProductoNombre() {
      return productoNombre;
   }

   public void setProductoNombre(java.lang.String productoNombre) {
      this.productoNombre = productoNombre;
   }

   @Column(name = "FECHA_MOVIMIENTO")
   public java.sql.Timestamp getFechaMovimiento() {
      return fechaMovimiento;
   }

   public void setFechaMovimiento(java.sql.Timestamp fechaMovimiento) {
      this.fechaMovimiento = fechaMovimiento;
   }

   @Column(name = "TIPODOCUMENTO_CODIGO")
   public java.lang.String getTipodocumentoCodigo() {
      return tipodocumentoCodigo;
   }

   public void setTipodocumentoCodigo(java.lang.String tipodocumentoCodigo) {
      this.tipodocumentoCodigo = tipodocumentoCodigo;
   }

   @Column(name = "TIPODOCUMENTO_NOMBRE")
   public java.lang.String getTipodocumentoNombre() {
      return tipodocumentoNombre;
   }

   public void setTipodocumentoNombre(java.lang.String tipodocumentoNombre) {
      this.tipodocumentoNombre = tipodocumentoNombre;
   }

   @Column(name = "TIPODOCUMENTO_SIGNOSTOCK")
   public java.lang.String getTipodocumentoSignostock() {
      return tipodocumentoSignostock;
   }

   public void setTipodocumentoSignostock(java.lang.String tipodocumentoSignostock) {
      this.tipodocumentoSignostock = tipodocumentoSignostock;
   }

   @Column(name = "CANTIDAD_DETALLE")
   public java.math.BigDecimal getCantidadDetalle() {
      return cantidadDetalle;
   }

   public void setCantidadDetalle(java.math.BigDecimal cantidadDetalle) {
      this.cantidadDetalle = cantidadDetalle;
   }

   @Column(name = "LOTE_ID")
   public java.lang.Long getLoteId() {
      return loteId;
   }

   public void setLoteId(java.lang.Long loteId) {
      this.loteId = loteId;
   }

   @Column(name = "LOTE_CODIGO")
   public java.lang.String getLoteCodigo() {
      return loteCodigo;
   }

   public void setLoteCodigo(java.lang.String loteCodigo) {
      this.loteCodigo = loteCodigo;
   }

   @Column(name = "DOCUMENTO_CODIGO")
   public java.lang.String getDocumentoCodigo() {
      return documentoCodigo;
   }

   public void setDocumentoCodigo(java.lang.String documentoCodigo) {
      this.documentoCodigo = documentoCodigo;
   }

   @Column(name = "DOCUMENTO_DESCRIPCION")
   public java.lang.String getDocumentoDescripcion() {
      return documentoDescripcion;
   }

   public void setDocumentoDescripcion(java.lang.String documentoDescripcion) {
      this.documentoDescripcion = documentoDescripcion;
   }

   @Column(name = "PROMEDIO_UNITARIO")
   public java.math.BigDecimal getPromedioUnitario() {
      return promedioUnitario;
   }

   public void setPromedioUnitario(java.math.BigDecimal promedioUnitario) {
      this.promedioUnitario = promedioUnitario;
   }

   @Column(name = "VALOR_UNITARIO")
   public java.math.BigDecimal getValorUnitario() {
      return valorUnitario;
   }

   public void setValorUnitario(java.math.BigDecimal valorUnitario) {
      this.valorUnitario = valorUnitario;
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
		return ToStringer.toString((KardexIf)this);
	}
}
