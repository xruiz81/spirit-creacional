package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PresupuestoDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getPresupuestoId();

   void setPresupuestoId(java.lang.Long presupuestoId);

   java.lang.Long getProductoId();

   void setProductoId(java.lang.Long productoId);

   java.lang.String getConcepto();

   void setConcepto(java.lang.String concepto);

   java.math.BigDecimal getCantidad();

   void setCantidad(java.math.BigDecimal cantidad);

   java.math.BigDecimal getPrecioagencia();

   void setPrecioagencia(java.math.BigDecimal precioagencia);

   java.math.BigDecimal getPrecioventa();

   void setPrecioventa(java.math.BigDecimal precioventa);

   java.math.BigDecimal getPorcentajeDescuentoAgenciaVenta();

   void setPorcentajeDescuentoAgenciaVenta(java.math.BigDecimal porcentajeDescuentoAgenciaVenta);

   java.math.BigDecimal getIva();

   void setIva(java.math.BigDecimal iva);

   java.lang.Long getProveedorId();

   void setProveedorId(java.lang.Long proveedorId);

   java.math.BigDecimal getPorcentajeDescuentoAgenciaCompra();

   void setPorcentajeDescuentoAgenciaCompra(java.math.BigDecimal porcentajeDescuentoAgenciaCompra);

   java.math.BigDecimal getIvaCompra();

   void setIvaCompra(java.math.BigDecimal ivaCompra);

   java.lang.String getReporte();

   void setReporte(java.lang.String reporte);

   java.lang.Integer getOrden();

   void setOrden(java.lang.Integer orden);

   java.lang.Long getOrdenCompraId();

   void setOrdenCompraId(java.lang.Long ordenCompraId);

   java.math.BigDecimal getPorcentajeNegociacionDirecta();

   void setPorcentajeNegociacionDirecta(java.math.BigDecimal porcentajeNegociacionDirecta);

   java.math.BigDecimal getPorcentajeComisionPura();

   void setPorcentajeComisionPura(java.math.BigDecimal porcentajeComisionPura);

   java.math.BigDecimal getPorcentajeDescuentosVariosCompra();

   void setPorcentajeDescuentosVariosCompra(java.math.BigDecimal porcentajeDescuentosVariosCompra);

   java.math.BigDecimal getPorcentajeDescuentosVariosVenta();

   void setPorcentajeDescuentosVariosVenta(java.math.BigDecimal porcentajeDescuentosVariosVenta);

   java.math.BigDecimal getPorcentajeDescuentoEspecialCompra();

   void setPorcentajeDescuentoEspecialCompra(java.math.BigDecimal porcentajeDescuentoEspecialCompra);

   java.math.BigDecimal getPorcentajeDescuentoEspecialVenta();

   void setPorcentajeDescuentoEspecialVenta(java.math.BigDecimal porcentajeDescuentoEspecialVenta);

   java.sql.Timestamp getFechaPublicacion();

   void setFechaPublicacion(java.sql.Timestamp fechaPublicacion);

   java.math.BigDecimal getPorcentajeNotaCreditoCompra();

   void setPorcentajeNotaCreditoCompra(java.math.BigDecimal porcentajeNotaCreditoCompra);

   java.math.BigDecimal getPorcentajeComisionAdicional();

   void setPorcentajeComisionAdicional(java.math.BigDecimal porcentajeComisionAdicional);


}
