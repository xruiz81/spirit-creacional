package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OrdenMedioIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.Long getClienteOficinaId();

   void setClienteOficinaId(java.lang.Long clienteOficinaId);

   java.lang.Long getPlanMedioId();

   void setPlanMedioId(java.lang.Long planMedioId);

   java.lang.Long getProveedorId();

   void setProveedorId(java.lang.Long proveedorId);

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);

   java.lang.Long getOficinaId();

   void setOficinaId(java.lang.Long oficinaId);

   java.lang.Long getEmpleadoId();

   void setEmpleadoId(java.lang.Long empleadoId);

   java.sql.Timestamp getFechaCreacion();

   void setFechaCreacion(java.sql.Timestamp fechaCreacion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.math.BigDecimal getValorTotal();

   void setValorTotal(java.math.BigDecimal valorTotal);

   java.lang.Long getProductoProveedorId();

   void setProductoProveedorId(java.lang.Long productoProveedorId);

   java.sql.Timestamp getFechaOrden();

   void setFechaOrden(java.sql.Timestamp fechaOrden);

   java.math.BigDecimal getValorDescuento();

   void setValorDescuento(java.math.BigDecimal valorDescuento);

   java.math.BigDecimal getValorIva();

   void setValorIva(java.math.BigDecimal valorIva);

   java.math.BigDecimal getValorSubtotal();

   void setValorSubtotal(java.math.BigDecimal valorSubtotal);

   java.math.BigDecimal getPorcentajeDescuentoVenta();

   void setPorcentajeDescuentoVenta(java.math.BigDecimal porcentajeDescuentoVenta);

   java.math.BigDecimal getPorcentajeComisionAgencia();

   void setPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia);

   java.math.BigDecimal getValorDescuentoVenta();

   void setValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta);

   java.math.BigDecimal getValorComisionAgencia();

   void setValorComisionAgencia(java.math.BigDecimal valorComisionAgencia);

   java.math.BigDecimal getPorcentajeCanje();

   void setPorcentajeCanje(java.math.BigDecimal porcentajeCanje);

   java.lang.Long getProductoClienteId();

   void setProductoClienteId(java.lang.Long productoClienteId);

   java.lang.String getOrdenMedioTipo();

   void setOrdenMedioTipo(java.lang.String ordenMedioTipo);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.Long getCampanaProductoVersionId();

   void setCampanaProductoVersionId(java.lang.Long campanaProductoVersionId);

   java.math.BigDecimal getPorcentajeNegociacionComision();

   void setPorcentajeNegociacionComision(java.math.BigDecimal porcentajeNegociacionComision);

   java.lang.String getComisionPura();

   void setComisionPura(java.lang.String comisionPura);

   java.math.BigDecimal getPorcentajeBonificacionCompra();

   void setPorcentajeBonificacionCompra(java.math.BigDecimal porcentajeBonificacionCompra);

   java.math.BigDecimal getPorcentajeBonificacionVenta();

   void setPorcentajeBonificacionVenta(java.math.BigDecimal porcentajeBonificacionVenta);

   java.lang.Integer getNumeroOrdenAgrupacion();

   void setNumeroOrdenAgrupacion(java.lang.Integer numeroOrdenAgrupacion);

   java.lang.String getRevision();

   void setRevision(java.lang.String revision);

   java.math.BigDecimal getPorcentajeComisionAdicional();

   void setPorcentajeComisionAdicional(java.math.BigDecimal porcentajeComisionAdicional);


}
