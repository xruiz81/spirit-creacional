package com.spirit.facturacion.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PedidoData implements PedidoIf, Serializable    {


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

   private java.lang.Long puntoimpresionId;

   public java.lang.Long getPuntoimpresionId() {
      return puntoimpresionId;
   }

   public void setPuntoimpresionId(java.lang.Long puntoimpresionId) {
      this.puntoimpresionId = puntoimpresionId;
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

   private java.lang.Long listaprecioId;

   public java.lang.Long getListaprecioId() {
      return listaprecioId;
   }

   public void setListaprecioId(java.lang.Long listaprecioId) {
      this.listaprecioId = listaprecioId;
   }

   private java.sql.Timestamp fechaPedido;

   public java.sql.Timestamp getFechaPedido() {
      return fechaPedido;
   }

   public void setFechaPedido(java.sql.Timestamp fechaPedido) {
      this.fechaPedido = fechaPedido;
   }

   private java.sql.Timestamp fechaCreacion;

   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
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

   private java.lang.String tiporeferencia;

   public java.lang.String getTiporeferencia() {
      return tiporeferencia;
   }

   public void setTiporeferencia(java.lang.String tiporeferencia) {
      this.tiporeferencia = tiporeferencia;
   }

   private java.lang.String referencia;

   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }

   private java.lang.Integer diasvalidez;

   public java.lang.Integer getDiasvalidez() {
      return diasvalidez;
   }

   public void setDiasvalidez(java.lang.Integer diasvalidez) {
      this.diasvalidez = diasvalidez;
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

   private java.lang.Long pedidoaplId;

   public java.lang.Long getPedidoaplId() {
      return pedidoaplId;
   }

   public void setPedidoaplId(java.lang.Long pedidoaplId) {
      this.pedidoaplId = pedidoaplId;
   }

   private java.math.BigDecimal porcentajeComisionAgencia;

   public java.math.BigDecimal getPorcentajeComisionAgencia() {
      return porcentajeComisionAgencia;
   }

   public void setPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) {
      this.porcentajeComisionAgencia = porcentajeComisionAgencia;
   }

   private java.lang.Long tipopagoId;

   public java.lang.Long getTipopagoId() {
      return tipopagoId;
   }

   public void setTipopagoId(java.lang.Long tipopagoId) {
      this.tipopagoId = tipopagoId;
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

   private java.sql.Timestamp fechaVencimiento;

   public java.sql.Timestamp getFechaVencimiento() {
      return fechaVencimiento;
   }

   public void setFechaVencimiento(java.sql.Timestamp fechaVencimiento) {
      this.fechaVencimiento = fechaVencimiento;
   }
   public PedidoData() {
   }

   public PedidoData(com.spirit.facturacion.entity.PedidoIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setTipodocumentoId(value.getTipodocumentoId());
      setCodigo(value.getCodigo());
      setClienteoficinaId(value.getClienteoficinaId());
      setTipoidentificacionId(value.getTipoidentificacionId());
      setIdentificacion(value.getIdentificacion());
      setFormapagoId(value.getFormapagoId());
      setMonedaId(value.getMonedaId());
      setPuntoimpresionId(value.getPuntoimpresionId());
      setOrigendocumentoId(value.getOrigendocumentoId());
      setVendedorId(value.getVendedorId());
      setBodegaId(value.getBodegaId());
      setListaprecioId(value.getListaprecioId());
      setFechaPedido(value.getFechaPedido());
      setFechaCreacion(value.getFechaCreacion());
      setUsuarioId(value.getUsuarioId());
      setContacto(value.getContacto());
      setDireccion(value.getDireccion());
      setTelefono(value.getTelefono());
      setTiporeferencia(value.getTiporeferencia());
      setReferencia(value.getReferencia());
      setDiasvalidez(value.getDiasvalidez());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setPedidoaplId(value.getPedidoaplId());
      setPorcentajeComisionAgencia(value.getPorcentajeComisionAgencia());
      setTipopagoId(value.getTipopagoId());
      setEquipoId(value.getEquipoId());
      setClienteNegociacionId(value.getClienteNegociacionId());
      setTipoNegociacion(value.getTipoNegociacion());
      setPorcentajeNegociacionDirecta(value.getPorcentajeNegociacionDirecta());
      setPorcentajeDescuentoNegociacion(value.getPorcentajeDescuentoNegociacion());
      setAutorizacionSap(value.getAutorizacionSap());
      setFechaVencimiento(value.getFechaVencimiento());
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
		return ToStringer.toString((PedidoIf)this);
	}
}
