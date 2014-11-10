package com.spirit.inventario.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class KardexData implements KardexIf, Serializable    {


   private java.lang.Long movimientoId;

   public java.lang.Long getMovimientoId() {
      return movimientoId;
   }

   public void setMovimientoId(java.lang.Long movimientoId) {
      this.movimientoId = movimientoId;
   }

   private java.lang.String movimientoCodigo;

   public java.lang.String getMovimientoCodigo() {
      return movimientoCodigo;
   }

   public void setMovimientoCodigo(java.lang.String movimientoCodigo) {
      this.movimientoCodigo = movimientoCodigo;
   }

   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   private java.lang.String empresaNombre;

   public java.lang.String getEmpresaNombre() {
      return empresaNombre;
   }

   public void setEmpresaNombre(java.lang.String empresaNombre) {
      this.empresaNombre = empresaNombre;
   }

   private java.lang.Long oficinaId;

   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   private java.lang.String oficinaCodigo;

   public java.lang.String getOficinaCodigo() {
      return oficinaCodigo;
   }

   public void setOficinaCodigo(java.lang.String oficinaCodigo) {
      this.oficinaCodigo = oficinaCodigo;
   }

   private java.lang.String oficinaNombre;

   public java.lang.String getOficinaNombre() {
      return oficinaNombre;
   }

   public void setOficinaNombre(java.lang.String oficinaNombre) {
      this.oficinaNombre = oficinaNombre;
   }

   private java.lang.Long bodegaId;

   public java.lang.Long getBodegaId() {
      return bodegaId;
   }

   public void setBodegaId(java.lang.Long bodegaId) {
      this.bodegaId = bodegaId;
   }

   private java.lang.String bodegaCodigo;

   public java.lang.String getBodegaCodigo() {
      return bodegaCodigo;
   }

   public void setBodegaCodigo(java.lang.String bodegaCodigo) {
      this.bodegaCodigo = bodegaCodigo;
   }

   private java.lang.String bodegaNombre;

   public java.lang.String getBodegaNombre() {
      return bodegaNombre;
   }

   public void setBodegaNombre(java.lang.String bodegaNombre) {
      this.bodegaNombre = bodegaNombre;
   }

   private java.lang.Long productoId;

   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   private java.lang.String productoCodigo;

   public java.lang.String getProductoCodigo() {
      return productoCodigo;
   }

   public void setProductoCodigo(java.lang.String productoCodigo) {
      this.productoCodigo = productoCodigo;
   }

   private java.lang.String productoNombre;

   public java.lang.String getProductoNombre() {
      return productoNombre;
   }

   public void setProductoNombre(java.lang.String productoNombre) {
      this.productoNombre = productoNombre;
   }

   private java.sql.Timestamp fechaMovimiento;

   public java.sql.Timestamp getFechaMovimiento() {
      return fechaMovimiento;
   }

   public void setFechaMovimiento(java.sql.Timestamp fechaMovimiento) {
      this.fechaMovimiento = fechaMovimiento;
   }

   private java.lang.String tipodocumentoCodigo;

   public java.lang.String getTipodocumentoCodigo() {
      return tipodocumentoCodigo;
   }

   public void setTipodocumentoCodigo(java.lang.String tipodocumentoCodigo) {
      this.tipodocumentoCodigo = tipodocumentoCodigo;
   }

   private java.lang.String tipodocumentoNombre;

   public java.lang.String getTipodocumentoNombre() {
      return tipodocumentoNombre;
   }

   public void setTipodocumentoNombre(java.lang.String tipodocumentoNombre) {
      this.tipodocumentoNombre = tipodocumentoNombre;
   }

   private java.lang.String tipodocumentoSignostock;

   public java.lang.String getTipodocumentoSignostock() {
      return tipodocumentoSignostock;
   }

   public void setTipodocumentoSignostock(java.lang.String tipodocumentoSignostock) {
      this.tipodocumentoSignostock = tipodocumentoSignostock;
   }

   private java.math.BigDecimal cantidadDetalle;

   public java.math.BigDecimal getCantidadDetalle() {
      return cantidadDetalle;
   }

   public void setCantidadDetalle(java.math.BigDecimal cantidadDetalle) {
      this.cantidadDetalle = cantidadDetalle;
   }

   private java.lang.Long loteId;

   public java.lang.Long getLoteId() {
      return loteId;
   }

   public void setLoteId(java.lang.Long loteId) {
      this.loteId = loteId;
   }

   private java.lang.String loteCodigo;

   public java.lang.String getLoteCodigo() {
      return loteCodigo;
   }

   public void setLoteCodigo(java.lang.String loteCodigo) {
      this.loteCodigo = loteCodigo;
   }

   private java.lang.String documentoCodigo;

   public java.lang.String getDocumentoCodigo() {
      return documentoCodigo;
   }

   public void setDocumentoCodigo(java.lang.String documentoCodigo) {
      this.documentoCodigo = documentoCodigo;
   }

   private java.lang.String documentoDescripcion;

   public java.lang.String getDocumentoDescripcion() {
      return documentoDescripcion;
   }

   public void setDocumentoDescripcion(java.lang.String documentoDescripcion) {
      this.documentoDescripcion = documentoDescripcion;
   }

   private java.math.BigDecimal promedioUnitario;

   public java.math.BigDecimal getPromedioUnitario() {
      return promedioUnitario;
   }

   public void setPromedioUnitario(java.math.BigDecimal promedioUnitario) {
      this.promedioUnitario = promedioUnitario;
   }

   private java.math.BigDecimal valorUnitario;

   public java.math.BigDecimal getValorUnitario() {
      return valorUnitario;
   }

   public void setValorUnitario(java.math.BigDecimal valorUnitario) {
      this.valorUnitario = valorUnitario;
   }

   private java.math.BigDecimal stockValorizado;

   public java.math.BigDecimal getStockValorizado() {
      return stockValorizado;
   }

   public void setStockValorizado(java.math.BigDecimal stockValorizado) {
      this.stockValorizado = stockValorizado;
   }

   private java.math.BigDecimal stockAnterior;

   public java.math.BigDecimal getStockAnterior() {
      return stockAnterior;
   }

   public void setStockAnterior(java.math.BigDecimal stockAnterior) {
      this.stockAnterior = stockAnterior;
   }
   public KardexData() {
   }

   public KardexData(com.spirit.inventario.entity.KardexIf value) {
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



    public java.lang.Long getPrimaryKey() {
        return getId();
    }

    public void setPrimaryKey(java.lang.Long pk) {
       setId(pk);
    }


   public String getPrimaryKeyParameters() {
      String parameters = "";
      parameters += "&id=" + getId();
      return parameters;
   }



	public String toString() {
		return ToStringer.toString((KardexIf)this);
	}
}
