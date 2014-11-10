package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PresupuestosIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getTipo();

   void setTipo(java.lang.String tipo);

   java.lang.Long getClienteOficinaId();

   void setClienteOficinaId(java.lang.Long clienteOficinaId);

   java.lang.String getConcepto();

   void setConcepto(java.lang.String concepto);

   java.sql.Timestamp getFechaAprobacion();

   void setFechaAprobacion(java.sql.Timestamp fechaAprobacion);

   java.math.BigDecimal getSubtotal();

   void setSubtotal(java.math.BigDecimal subtotal);

   java.math.BigDecimal getDescuento();

   void setDescuento(java.math.BigDecimal descuento);

   java.math.BigDecimal getComision();

   void setComision(java.math.BigDecimal comision);

   java.math.BigDecimal getIva();

   void setIva(java.math.BigDecimal iva);

   java.math.BigDecimal getTotal();

   void setTotal(java.math.BigDecimal total);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getPrepago();

   void setPrepago(java.lang.String prepago);

   java.lang.String getRevision();

   void setRevision(java.lang.String revision);

   java.sql.Timestamp getFecha();

   void setFecha(java.sql.Timestamp fecha);


}
