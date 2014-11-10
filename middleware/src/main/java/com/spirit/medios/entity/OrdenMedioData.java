package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class OrdenMedioData implements OrdenMedioIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.Long clienteOficinaId;

   public java.lang.Long getClienteOficinaId() {
      return clienteOficinaId;
   }

   public void setClienteOficinaId(java.lang.Long clienteOficinaId) {
      this.clienteOficinaId = clienteOficinaId;
   }

   private java.lang.Long planMedioId;

   public java.lang.Long getPlanMedioId() {
      return planMedioId;
   }

   public void setPlanMedioId(java.lang.Long planMedioId) {
      this.planMedioId = planMedioId;
   }

   private java.lang.Long proveedorId;

   public java.lang.Long getProveedorId() {
      return proveedorId;
   }

   public void setProveedorId(java.lang.Long proveedorId) {
      this.proveedorId = proveedorId;
   }

   private java.lang.Long usuarioId;

   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   private java.lang.Long oficinaId;

   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   private java.lang.Long empleadoId;

   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   private java.sql.Timestamp fechaCreacion;

   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.math.BigDecimal valorTotal;

   public java.math.BigDecimal getValorTotal() {
      return valorTotal;
   }

   public void setValorTotal(java.math.BigDecimal valorTotal) {
      this.valorTotal = valorTotal;
   }

   private java.lang.Long productoProveedorId;

   public java.lang.Long getProductoProveedorId() {
      return productoProveedorId;
   }

   public void setProductoProveedorId(java.lang.Long productoProveedorId) {
      this.productoProveedorId = productoProveedorId;
   }

   private java.sql.Timestamp fechaOrden;

   public java.sql.Timestamp getFechaOrden() {
      return fechaOrden;
   }

   public void setFechaOrden(java.sql.Timestamp fechaOrden) {
      this.fechaOrden = fechaOrden;
   }

   private java.math.BigDecimal valorDescuento;

   public java.math.BigDecimal getValorDescuento() {
      return valorDescuento;
   }

   public void setValorDescuento(java.math.BigDecimal valorDescuento) {
      this.valorDescuento = valorDescuento;
   }

   private java.math.BigDecimal valorIva;

   public java.math.BigDecimal getValorIva() {
      return valorIva;
   }

   public void setValorIva(java.math.BigDecimal valorIva) {
      this.valorIva = valorIva;
   }

   private java.math.BigDecimal valorSubtotal;

   public java.math.BigDecimal getValorSubtotal() {
      return valorSubtotal;
   }

   public void setValorSubtotal(java.math.BigDecimal valorSubtotal) {
      this.valorSubtotal = valorSubtotal;
   }

   private java.math.BigDecimal porcentajeDescuentoVenta;

   public java.math.BigDecimal getPorcentajeDescuentoVenta() {
      return porcentajeDescuentoVenta;
   }

   public void setPorcentajeDescuentoVenta(java.math.BigDecimal porcentajeDescuentoVenta) {
      this.porcentajeDescuentoVenta = porcentajeDescuentoVenta;
   }

   private java.math.BigDecimal porcentajeComisionAgencia;

   public java.math.BigDecimal getPorcentajeComisionAgencia() {
      return porcentajeComisionAgencia;
   }

   public void setPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) {
      this.porcentajeComisionAgencia = porcentajeComisionAgencia;
   }

   private java.math.BigDecimal valorDescuentoVenta;

   public java.math.BigDecimal getValorDescuentoVenta() {
      return valorDescuentoVenta;
   }

   public void setValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta) {
      this.valorDescuentoVenta = valorDescuentoVenta;
   }

   private java.math.BigDecimal valorComisionAgencia;

   public java.math.BigDecimal getValorComisionAgencia() {
      return valorComisionAgencia;
   }

   public void setValorComisionAgencia(java.math.BigDecimal valorComisionAgencia) {
      this.valorComisionAgencia = valorComisionAgencia;
   }

   private java.math.BigDecimal porcentajeCanje;

   public java.math.BigDecimal getPorcentajeCanje() {
      return porcentajeCanje;
   }

   public void setPorcentajeCanje(java.math.BigDecimal porcentajeCanje) {
      this.porcentajeCanje = porcentajeCanje;
   }

   private java.lang.Long productoClienteId;

   public java.lang.Long getProductoClienteId() {
      return productoClienteId;
   }

   public void setProductoClienteId(java.lang.Long productoClienteId) {
      this.productoClienteId = productoClienteId;
   }

   private java.lang.String ordenMedioTipo;

   public java.lang.String getOrdenMedioTipo() {
      return ordenMedioTipo;
   }

   public void setOrdenMedioTipo(java.lang.String ordenMedioTipo) {
      this.ordenMedioTipo = ordenMedioTipo;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.lang.Long campanaProductoVersionId;

   public java.lang.Long getCampanaProductoVersionId() {
      return campanaProductoVersionId;
   }

   public void setCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) {
      this.campanaProductoVersionId = campanaProductoVersionId;
   }

   private java.math.BigDecimal porcentajeNegociacionComision;

   public java.math.BigDecimal getPorcentajeNegociacionComision() {
      return porcentajeNegociacionComision;
   }

   public void setPorcentajeNegociacionComision(java.math.BigDecimal porcentajeNegociacionComision) {
      this.porcentajeNegociacionComision = porcentajeNegociacionComision;
   }

   private java.lang.String comisionPura;

   public java.lang.String getComisionPura() {
      return comisionPura;
   }

   public void setComisionPura(java.lang.String comisionPura) {
      this.comisionPura = comisionPura;
   }

   private java.math.BigDecimal porcentajeBonificacionCompra;

   public java.math.BigDecimal getPorcentajeBonificacionCompra() {
      return porcentajeBonificacionCompra;
   }

   public void setPorcentajeBonificacionCompra(java.math.BigDecimal porcentajeBonificacionCompra) {
      this.porcentajeBonificacionCompra = porcentajeBonificacionCompra;
   }

   private java.math.BigDecimal porcentajeBonificacionVenta;

   public java.math.BigDecimal getPorcentajeBonificacionVenta() {
      return porcentajeBonificacionVenta;
   }

   public void setPorcentajeBonificacionVenta(java.math.BigDecimal porcentajeBonificacionVenta) {
      this.porcentajeBonificacionVenta = porcentajeBonificacionVenta;
   }

   private java.lang.Integer numeroOrdenAgrupacion;

   public java.lang.Integer getNumeroOrdenAgrupacion() {
      return numeroOrdenAgrupacion;
   }

   public void setNumeroOrdenAgrupacion(java.lang.Integer numeroOrdenAgrupacion) {
      this.numeroOrdenAgrupacion = numeroOrdenAgrupacion;
   }

   private java.lang.String revision;

   public java.lang.String getRevision() {
      return revision;
   }

   public void setRevision(java.lang.String revision) {
      this.revision = revision;
   }

   private java.math.BigDecimal porcentajeComisionAdicional;

   public java.math.BigDecimal getPorcentajeComisionAdicional() {
      return porcentajeComisionAdicional;
   }

   public void setPorcentajeComisionAdicional(java.math.BigDecimal porcentajeComisionAdicional) {
      this.porcentajeComisionAdicional = porcentajeComisionAdicional;
   }
   public OrdenMedioData() {
   }

   public OrdenMedioData(com.spirit.medios.entity.OrdenMedioIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setClienteOficinaId(value.getClienteOficinaId());
      setPlanMedioId(value.getPlanMedioId());
      setProveedorId(value.getProveedorId());
      setUsuarioId(value.getUsuarioId());
      setOficinaId(value.getOficinaId());
      setEmpleadoId(value.getEmpleadoId());
      setFechaCreacion(value.getFechaCreacion());
      setEstado(value.getEstado());
      setValorTotal(value.getValorTotal());
      setProductoProveedorId(value.getProductoProveedorId());
      setFechaOrden(value.getFechaOrden());
      setValorDescuento(value.getValorDescuento());
      setValorIva(value.getValorIva());
      setValorSubtotal(value.getValorSubtotal());
      setPorcentajeDescuentoVenta(value.getPorcentajeDescuentoVenta());
      setPorcentajeComisionAgencia(value.getPorcentajeComisionAgencia());
      setValorDescuentoVenta(value.getValorDescuentoVenta());
      setValorComisionAgencia(value.getValorComisionAgencia());
      setPorcentajeCanje(value.getPorcentajeCanje());
      setProductoClienteId(value.getProductoClienteId());
      setOrdenMedioTipo(value.getOrdenMedioTipo());
      setObservacion(value.getObservacion());
      setCampanaProductoVersionId(value.getCampanaProductoVersionId());
      setPorcentajeNegociacionComision(value.getPorcentajeNegociacionComision());
      setComisionPura(value.getComisionPura());
      setPorcentajeBonificacionCompra(value.getPorcentajeBonificacionCompra());
      setPorcentajeBonificacionVenta(value.getPorcentajeBonificacionVenta());
      setNumeroOrdenAgrupacion(value.getNumeroOrdenAgrupacion());
      setRevision(value.getRevision());
      setPorcentajeComisionAdicional(value.getPorcentajeComisionAdicional());
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
		return ToStringer.toString((OrdenMedioIf)this);
	}
}
