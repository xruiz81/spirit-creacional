package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OrdenMedioDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getOrdenMedioId();

   void setOrdenMedioId(java.lang.Long ordenMedioId);

   java.lang.Long getComercialId();

   void setComercialId(java.lang.Long comercialId);

   java.lang.String getPrograma();

   void setPrograma(java.lang.String programa);

   java.lang.String getHora();

   void setHora(java.lang.String hora);

   java.lang.String getComercial();

   void setComercial(java.lang.String comercial);

   java.math.BigDecimal getValorTotal();

   void setValorTotal(java.math.BigDecimal valorTotal);

   java.math.BigDecimal getPorcentajeDescuento();

   void setPorcentajeDescuento(java.math.BigDecimal porcentajeDescuento);

   java.math.BigDecimal getValorIva();

   void setValorIva(java.math.BigDecimal valorIva);

   java.math.BigDecimal getValorTarifa();

   void setValorTarifa(java.math.BigDecimal valorTarifa);

   java.math.BigDecimal getValorSubtotal();

   void setValorSubtotal(java.math.BigDecimal valorSubtotal);

   java.math.BigDecimal getValorDescuento();

   void setValorDescuento(java.math.BigDecimal valorDescuento);

   java.math.BigDecimal getPorcentajeDescuentoVenta();

   void setPorcentajeDescuentoVenta(java.math.BigDecimal porcentajeDescuentoVenta);

   java.math.BigDecimal getPorcentajeComisionAgencia();

   void setPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia);

   java.math.BigDecimal getValorDescuentoVenta();

   void setValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta);

   java.math.BigDecimal getValorComisionAgencia();

   void setValorComisionAgencia(java.math.BigDecimal valorComisionAgencia);

   java.lang.Integer getTotalCunias();

   void setTotalCunias(java.lang.Integer totalCunias);

   java.lang.Long getProductoProveedorId();

   void setProductoProveedorId(java.lang.Long productoProveedorId);

   java.lang.Long getProductoClienteId();

   void setProductoClienteId(java.lang.Long productoClienteId);

   java.lang.String getPauta();

   void setPauta(java.lang.String pauta);

   java.lang.String getAuspicioDescripcion();

   void setAuspicioDescripcion(java.lang.String auspicioDescripcion);

   java.lang.Long getAuspicioPadre();

   void setAuspicioPadre(java.lang.Long auspicioPadre);

   java.lang.Long getCampanaProductoVersionId();

   void setCampanaProductoVersionId(java.lang.Long campanaProductoVersionId);


}
