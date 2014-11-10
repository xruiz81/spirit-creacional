package com.spirit.compras.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class OrdenCompraData implements OrdenCompraIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long oficinaId;

   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   private java.lang.Long tipodocumentoId;

   public java.lang.Long getTipodocumentoId() {
      return tipodocumentoId;
   }

   public void setTipodocumentoId(java.lang.Long tipodocumentoId) {
      this.tipodocumentoId = tipodocumentoId;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.Long proveedorId;

   public java.lang.Long getProveedorId() {
      return proveedorId;
   }

   public void setProveedorId(java.lang.Long proveedorId) {
      this.proveedorId = proveedorId;
   }

   private java.lang.Long monedaId;

   public java.lang.Long getMonedaId() {
      return monedaId;
   }

   public void setMonedaId(java.lang.Long monedaId) {
      this.monedaId = monedaId;
   }

   private java.lang.Long empleadoId;

   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   private java.lang.Long usuarioId;

   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   private java.lang.Long bodegaId;

   public java.lang.Long getBodegaId() {
      return bodegaId;
   }

   public void setBodegaId(java.lang.Long bodegaId) {
      this.bodegaId = bodegaId;
   }

   private java.lang.String localimportada;

   public java.lang.String getLocalimportada() {
      return localimportada;
   }

   public void setLocalimportada(java.lang.String localimportada) {
      this.localimportada = localimportada;
   }

   private java.sql.Date fecha;

   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   private java.sql.Date fechaRecepcion;

   public java.sql.Date getFechaRecepcion() {
      return fechaRecepcion;
   }

   public void setFechaRecepcion(java.sql.Date fechaRecepcion) {
      this.fechaRecepcion = fechaRecepcion;
   }

   private java.sql.Date fechaVencimiento;

   public java.sql.Date getFechaVencimiento() {
      return fechaVencimiento;
   }

   public void setFechaVencimiento(java.sql.Date fechaVencimiento) {
      this.fechaVencimiento = fechaVencimiento;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.math.BigDecimal descuentoAgenciaCompra;

   public java.math.BigDecimal getDescuentoAgenciaCompra() {
      return descuentoAgenciaCompra;
   }

   public void setDescuentoAgenciaCompra(java.math.BigDecimal descuentoAgenciaCompra) {
      this.descuentoAgenciaCompra = descuentoAgenciaCompra;
   }

   private java.math.BigDecimal iva;

   public java.math.BigDecimal getIva() {
      return iva;
   }

   public void setIva(java.math.BigDecimal iva) {
      this.iva = iva;
   }

   private java.math.BigDecimal ice;

   public java.math.BigDecimal getIce() {
      return ice;
   }

   public void setIce(java.math.BigDecimal ice) {
      this.ice = ice;
   }

   private java.math.BigDecimal otroImpuesto;

   public java.math.BigDecimal getOtroImpuesto() {
      return otroImpuesto;
   }

   public void setOtroImpuesto(java.math.BigDecimal otroImpuesto) {
      this.otroImpuesto = otroImpuesto;
   }

   private java.lang.Long solicitudcompraId;

   public java.lang.Long getSolicitudcompraId() {
      return solicitudcompraId;
   }

   public void setSolicitudcompraId(java.lang.Long solicitudcompraId) {
      this.solicitudcompraId = solicitudcompraId;
   }

   private java.lang.String estadoBpm;

   public java.lang.String getEstadoBpm() {
      return estadoBpm;
   }

   public void setEstadoBpm(java.lang.String estadoBpm) {
      this.estadoBpm = estadoBpm;
   }

   private java.math.BigDecimal porcentajeDescuentosVariosCompra;

   public java.math.BigDecimal getPorcentajeDescuentosVariosCompra() {
      return porcentajeDescuentosVariosCompra;
   }

   public void setPorcentajeDescuentosVariosCompra(java.math.BigDecimal porcentajeDescuentosVariosCompra) {
      this.porcentajeDescuentosVariosCompra = porcentajeDescuentosVariosCompra;
   }

   private java.math.BigDecimal porcentajeDescuentosVariosVenta;

   public java.math.BigDecimal getPorcentajeDescuentosVariosVenta() {
      return porcentajeDescuentosVariosVenta;
   }

   public void setPorcentajeDescuentosVariosVenta(java.math.BigDecimal porcentajeDescuentosVariosVenta) {
      this.porcentajeDescuentosVariosVenta = porcentajeDescuentosVariosVenta;
   }

   private java.math.BigDecimal porcentajeDescuentoEspecial;

   public java.math.BigDecimal getPorcentajeDescuentoEspecial() {
      return porcentajeDescuentoEspecial;
   }

   public void setPorcentajeDescuentoEspecial(java.math.BigDecimal porcentajeDescuentoEspecial) {
      this.porcentajeDescuentoEspecial = porcentajeDescuentoEspecial;
   }

   private java.lang.String revision;

   public java.lang.String getRevision() {
      return revision;
   }

   public void setRevision(java.lang.String revision) {
      this.revision = revision;
   }
   public OrdenCompraData() {
   }

   public OrdenCompraData(com.spirit.compras.entity.OrdenCompraIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setTipodocumentoId(value.getTipodocumentoId());
      setCodigo(value.getCodigo());
      setProveedorId(value.getProveedorId());
      setMonedaId(value.getMonedaId());
      setEmpleadoId(value.getEmpleadoId());
      setUsuarioId(value.getUsuarioId());
      setBodegaId(value.getBodegaId());
      setLocalimportada(value.getLocalimportada());
      setFecha(value.getFecha());
      setFechaRecepcion(value.getFechaRecepcion());
      setFechaVencimiento(value.getFechaVencimiento());
      setEstado(value.getEstado());
      setObservacion(value.getObservacion());
      setValor(value.getValor());
      setDescuentoAgenciaCompra(value.getDescuentoAgenciaCompra());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setSolicitudcompraId(value.getSolicitudcompraId());
      setEstadoBpm(value.getEstadoBpm());
      setPorcentajeDescuentosVariosCompra(value.getPorcentajeDescuentosVariosCompra());
      setPorcentajeDescuentosVariosVenta(value.getPorcentajeDescuentosVariosVenta());
      setPorcentajeDescuentoEspecial(value.getPorcentajeDescuentoEspecial());
      setRevision(value.getRevision());
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
		return ToStringer.toString((OrdenCompraIf)this);
	}
}
