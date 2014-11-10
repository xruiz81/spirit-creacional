package com.spirit.facturacion.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class FacturaData implements FacturaIf, Serializable    {


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

   private java.math.BigDecimal numero;

   public java.math.BigDecimal getNumero() {
      return numero;
   }

   public void setNumero(java.math.BigDecimal numero) {
      this.numero = numero;
   }

   private java.lang.Long clienteoficinaId;

   public java.lang.Long getClienteoficinaId() {
      return clienteoficinaId;
   }

   public void setClienteoficinaId(java.lang.Long clienteoficinaId) {
      this.clienteoficinaId = clienteoficinaId;
   }

   private java.lang.Long tipoidentificacionId;

   public java.lang.Long getTipoidentificacionId() {
      return tipoidentificacionId;
   }

   public void setTipoidentificacionId(java.lang.Long tipoidentificacionId) {
      this.tipoidentificacionId = tipoidentificacionId;
   }

   private java.lang.String identificacion;

   public java.lang.String getIdentificacion() {
      return identificacion;
   }

   public void setIdentificacion(java.lang.String identificacion) {
      this.identificacion = identificacion;
   }

   private java.lang.Long formapagoId;

   public java.lang.Long getFormapagoId() {
      return formapagoId;
   }

   public void setFormapagoId(java.lang.Long formapagoId) {
      this.formapagoId = formapagoId;
   }

   private java.lang.Long monedaId;

   public java.lang.Long getMonedaId() {
      return monedaId;
   }

   public void setMonedaId(java.lang.Long monedaId) {
      this.monedaId = monedaId;
   }

   private java.lang.Long puntoImpresionId;

   public java.lang.Long getPuntoImpresionId() {
      return puntoImpresionId;
   }

   public void setPuntoImpresionId(java.lang.Long puntoImpresionId) {
      this.puntoImpresionId = puntoImpresionId;
   }

   private java.lang.Long origendocumentoId;

   public java.lang.Long getOrigendocumentoId() {
      return origendocumentoId;
   }

   public void setOrigendocumentoId(java.lang.Long origendocumentoId) {
      this.origendocumentoId = origendocumentoId;
   }

   private java.lang.Long vendedorId;

   public java.lang.Long getVendedorId() {
      return vendedorId;
   }

   public void setVendedorId(java.lang.Long vendedorId) {
      this.vendedorId = vendedorId;
   }

   private java.lang.Long bodegaId;

   public java.lang.Long getBodegaId() {
      return bodegaId;
   }

   public void setBodegaId(java.lang.Long bodegaId) {
      this.bodegaId = bodegaId;
   }

   private java.lang.Long pedidoId;

   public java.lang.Long getPedidoId() {
      return pedidoId;
   }

   public void setPedidoId(java.lang.Long pedidoId) {
      this.pedidoId = pedidoId;
   }

   private java.lang.Long listaPrecioId;

   public java.lang.Long getListaPrecioId() {
      return listaPrecioId;
   }

   public void setListaPrecioId(java.lang.Long listaPrecioId) {
      this.listaPrecioId = listaPrecioId;
   }

   private java.sql.Timestamp fechaCreacion;

   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.sql.Timestamp fechaFactura;

   public java.sql.Timestamp getFechaFactura() {
      return fechaFactura;
   }

   public void setFechaFactura(java.sql.Timestamp fechaFactura) {
      this.fechaFactura = fechaFactura;
   }

   private java.sql.Timestamp fechaVencimiento;

   public java.sql.Timestamp getFechaVencimiento() {
      return fechaVencimiento;
   }

   public void setFechaVencimiento(java.sql.Timestamp fechaVencimiento) {
      this.fechaVencimiento = fechaVencimiento;
   }

   private java.lang.Long usuarioId;

   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   private java.lang.String contacto;

   public java.lang.String getContacto() {
      return contacto;
   }

   public void setContacto(java.lang.String contacto) {
      this.contacto = contacto;
   }

   private java.lang.String direccion;

   public java.lang.String getDireccion() {
      return direccion;
   }

   public void setDireccion(java.lang.String direccion) {
      this.direccion = direccion;
   }

   private java.lang.String telefono;

   public java.lang.String getTelefono() {
      return telefono;
   }

   public void setTelefono(java.lang.String telefono) {
      this.telefono = telefono;
   }

   private java.lang.String preimpresoNumero;

   public java.lang.String getPreimpresoNumero() {
      return preimpresoNumero;
   }

   public void setPreimpresoNumero(java.lang.String preimpresoNumero) {
      this.preimpresoNumero = preimpresoNumero;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.math.BigDecimal descuento;

   public java.math.BigDecimal getDescuento() {
      return descuento;
   }

   public void setDescuento(java.math.BigDecimal descuento) {
      this.descuento = descuento;
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

   private java.math.BigDecimal costo;

   public java.math.BigDecimal getCosto() {
      return costo;
   }

   public void setCosto(java.math.BigDecimal costo) {
      this.costo = costo;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.Long facturaaplId;

   public java.lang.Long getFacturaaplId() {
      return facturaaplId;
   }

   public void setFacturaaplId(java.lang.Long facturaaplId) {
      this.facturaaplId = facturaaplId;
   }

   private java.math.BigDecimal descuentoGlobal;

   public java.math.BigDecimal getDescuentoGlobal() {
      return descuentoGlobal;
   }

   public void setDescuentoGlobal(java.math.BigDecimal descuentoGlobal) {
      this.descuentoGlobal = descuentoGlobal;
   }

   private java.math.BigDecimal porcentajeComisionAgencia;

   public java.math.BigDecimal getPorcentajeComisionAgencia() {
      return porcentajeComisionAgencia;
   }

   public void setPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) {
      this.porcentajeComisionAgencia = porcentajeComisionAgencia;
   }

   private java.lang.Long equipoId;

   public java.lang.Long getEquipoId() {
      return equipoId;
   }

   public void setEquipoId(java.lang.Long equipoId) {
      this.equipoId = equipoId;
   }

   private java.lang.Long clienteNegociacionId;

   public java.lang.Long getClienteNegociacionId() {
      return clienteNegociacionId;
   }

   public void setClienteNegociacionId(java.lang.Long clienteNegociacionId) {
      this.clienteNegociacionId = clienteNegociacionId;
   }

   private java.lang.String tipoNegociacion;

   public java.lang.String getTipoNegociacion() {
      return tipoNegociacion;
   }

   public void setTipoNegociacion(java.lang.String tipoNegociacion) {
      this.tipoNegociacion = tipoNegociacion;
   }

   private java.math.BigDecimal porcentajeNegociacionDirecta;

   public java.math.BigDecimal getPorcentajeNegociacionDirecta() {
      return porcentajeNegociacionDirecta;
   }

   public void setPorcentajeNegociacionDirecta(java.math.BigDecimal porcentajeNegociacionDirecta) {
      this.porcentajeNegociacionDirecta = porcentajeNegociacionDirecta;
   }

   private java.math.BigDecimal porcentajeDescuentoNegociacion;

   public java.math.BigDecimal getPorcentajeDescuentoNegociacion() {
      return porcentajeDescuentoNegociacion;
   }

   public void setPorcentajeDescuentoNegociacion(java.math.BigDecimal porcentajeDescuentoNegociacion) {
      this.porcentajeDescuentoNegociacion = porcentajeDescuentoNegociacion;
   }

   private java.lang.String autorizacionSap;

   public java.lang.String getAutorizacionSap() {
      return autorizacionSap;
   }

   public void setAutorizacionSap(java.lang.String autorizacionSap) {
      this.autorizacionSap = autorizacionSap;
   }

   private java.math.BigDecimal descuentosVarios;

   public java.math.BigDecimal getDescuentosVarios() {
      return descuentosVarios;
   }

   public void setDescuentosVarios(java.math.BigDecimal descuentosVarios) {
      this.descuentosVarios = descuentosVarios;
   }
   public FacturaData() {
   }

   public FacturaData(com.spirit.facturacion.entity.FacturaIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setTipodocumentoId(value.getTipodocumentoId());
      setNumero(value.getNumero());
      setClienteoficinaId(value.getClienteoficinaId());
      setTipoidentificacionId(value.getTipoidentificacionId());
      setIdentificacion(value.getIdentificacion());
      setFormapagoId(value.getFormapagoId());
      setMonedaId(value.getMonedaId());
      setPuntoImpresionId(value.getPuntoImpresionId());
      setOrigendocumentoId(value.getOrigendocumentoId());
      setVendedorId(value.getVendedorId());
      setBodegaId(value.getBodegaId());
      setPedidoId(value.getPedidoId());
      setListaPrecioId(value.getListaPrecioId());
      setFechaCreacion(value.getFechaCreacion());
      setFechaFactura(value.getFechaFactura());
      setFechaVencimiento(value.getFechaVencimiento());
      setUsuarioId(value.getUsuarioId());
      setContacto(value.getContacto());
      setDireccion(value.getDireccion());
      setTelefono(value.getTelefono());
      setPreimpresoNumero(value.getPreimpresoNumero());
      setValor(value.getValor());
      setDescuento(value.getDescuento());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setCosto(value.getCosto());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setFacturaaplId(value.getFacturaaplId());
      setDescuentoGlobal(value.getDescuentoGlobal());
      setPorcentajeComisionAgencia(value.getPorcentajeComisionAgencia());
      setEquipoId(value.getEquipoId());
      setClienteNegociacionId(value.getClienteNegociacionId());
      setTipoNegociacion(value.getTipoNegociacion());
      setPorcentajeNegociacionDirecta(value.getPorcentajeNegociacionDirecta());
      setPorcentajeDescuentoNegociacion(value.getPorcentajeDescuentoNegociacion());
      setAutorizacionSap(value.getAutorizacionSap());
      setDescuentosVarios(value.getDescuentosVarios());
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
		return ToStringer.toString((FacturaIf)this);
	}
}
