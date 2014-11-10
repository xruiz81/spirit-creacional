package com.spirit.inventario.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface StockIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getBodegaId();

   void setBodegaId(java.lang.Long bodegaId);

   java.lang.Long getProductoId();

   void setProductoId(java.lang.Long productoId);

   java.lang.Long getLoteId();

   void setLoteId(java.lang.Long loteId);

   java.lang.String getAnio();

   void setAnio(java.lang.String anio);

   java.lang.String getMes();

   void setMes(java.lang.String mes);

   java.math.BigDecimal getCantidad();

   void setCantidad(java.math.BigDecimal cantidad);

   java.math.BigDecimal getReserva();

   void setReserva(java.math.BigDecimal reserva);

   java.math.BigDecimal getTransito();

   void setTransito(java.math.BigDecimal transito);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.sql.Timestamp getFhUtlModificacion();

   void setFhUtlModificacion(java.sql.Timestamp fhUtlModificacion);


}
