package com.spirit.inventario.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface KardexIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getMovimientoId();

   void setMovimientoId(java.lang.Long movimientoId);

   java.lang.String getMovimientoCodigo();

   void setMovimientoCodigo(java.lang.String movimientoCodigo);

   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.lang.String getEmpresaNombre();

   void setEmpresaNombre(java.lang.String empresaNombre);

   java.lang.Long getOficinaId();

   void setOficinaId(java.lang.Long oficinaId);

   java.lang.String getOficinaCodigo();

   void setOficinaCodigo(java.lang.String oficinaCodigo);

   java.lang.String getOficinaNombre();

   void setOficinaNombre(java.lang.String oficinaNombre);

   java.lang.Long getBodegaId();

   void setBodegaId(java.lang.Long bodegaId);

   java.lang.String getBodegaCodigo();

   void setBodegaCodigo(java.lang.String bodegaCodigo);

   java.lang.String getBodegaNombre();

   void setBodegaNombre(java.lang.String bodegaNombre);

   java.lang.Long getProductoId();

   void setProductoId(java.lang.Long productoId);

   java.lang.String getProductoCodigo();

   void setProductoCodigo(java.lang.String productoCodigo);

   java.lang.String getProductoNombre();

   void setProductoNombre(java.lang.String productoNombre);

   java.sql.Timestamp getFechaMovimiento();

   void setFechaMovimiento(java.sql.Timestamp fechaMovimiento);

   java.lang.String getTipodocumentoCodigo();

   void setTipodocumentoCodigo(java.lang.String tipodocumentoCodigo);

   java.lang.String getTipodocumentoNombre();

   void setTipodocumentoNombre(java.lang.String tipodocumentoNombre);

   java.lang.String getTipodocumentoSignostock();

   void setTipodocumentoSignostock(java.lang.String tipodocumentoSignostock);

   java.math.BigDecimal getCantidadDetalle();

   void setCantidadDetalle(java.math.BigDecimal cantidadDetalle);

   java.lang.Long getLoteId();

   void setLoteId(java.lang.Long loteId);

   java.lang.String getLoteCodigo();

   void setLoteCodigo(java.lang.String loteCodigo);

   java.lang.String getDocumentoCodigo();

   void setDocumentoCodigo(java.lang.String documentoCodigo);

   java.lang.String getDocumentoDescripcion();

   void setDocumentoDescripcion(java.lang.String documentoDescripcion);

   java.math.BigDecimal getPromedioUnitario();

   void setPromedioUnitario(java.math.BigDecimal promedioUnitario);

   java.math.BigDecimal getValorUnitario();

   void setValorUnitario(java.math.BigDecimal valorUnitario);

   java.math.BigDecimal getStockValorizado();

   void setStockValorizado(java.math.BigDecimal stockValorizado);

   java.math.BigDecimal getStockAnterior();

   void setStockAnterior(java.math.BigDecimal stockAnterior);


}
