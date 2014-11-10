package com.spirit.inventario.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface MovimientoDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getMovimientoId();

   void setMovimientoId(java.lang.Long movimientoId);

   java.lang.Long getDocumentoId();

   void setDocumentoId(java.lang.Long documentoId);

   java.lang.Long getLoteId();

   void setLoteId(java.lang.Long loteId);

   java.math.BigDecimal getCantidad();

   void setCantidad(java.math.BigDecimal cantidad);

   java.math.BigDecimal getCosto();

   void setCosto(java.math.BigDecimal costo);

   java.math.BigDecimal getPrecio();

   void setPrecio(java.math.BigDecimal precio);

   java.math.BigDecimal getCostoLifo();

   void setCostoLifo(java.math.BigDecimal costoLifo);

   java.math.BigDecimal getCostoFifo();

   void setCostoFifo(java.math.BigDecimal costoFifo);

   java.math.BigDecimal getValorUnitario();

   void setValorUnitario(java.math.BigDecimal valorUnitario);

   java.math.BigDecimal getPromedioUnitario();

   void setPromedioUnitario(java.math.BigDecimal promedioUnitario);

   java.math.BigDecimal getStockValorizado();

   void setStockValorizado(java.math.BigDecimal stockValorizado);

   java.math.BigDecimal getStockAnterior();

   void setStockAnterior(java.math.BigDecimal stockAnterior);


}
