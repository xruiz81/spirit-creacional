package com.spirit.compras.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CompraData implements CompraIf, Serializable    {


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

   private java.lang.Long usuarioId;

   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   private java.sql.Date fecha;

   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   private java.sql.Date fechaVencimiento;

   public java.sql.Date getFechaVencimiento() {
      return fechaVencimiento;
   }

   public void setFechaVencimiento(java.sql.Date fechaVencimiento) {
      this.fechaVencimiento = fechaVencimiento;
   }

   private java.lang.String preimpreso;

   public java.lang.String getPreimpreso() {
      return preimpreso;
   }

   public void setPreimpreso(java.lang.String preimpreso) {
      this.preimpreso = preimpreso;
   }

   private java.lang.String autorizacion;

   public java.lang.String getAutorizacion() {
      return autorizacion;
   }

   public void setAutorizacion(java.lang.String autorizacion) {
      this.autorizacion = autorizacion;
   }

   private java.lang.String referencia;

   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }

   private java.lang.String localimportada;

   public java.lang.String getLocalimportada() {
      return localimportada;
   }

   public void setLocalimportada(java.lang.String localimportada) {
      this.localimportada = localimportada;
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

   private java.lang.String estadoBpm;

   public java.lang.String getEstadoBpm() {
      return estadoBpm;
   }

   public void setEstadoBpm(java.lang.String estadoBpm) {
      this.estadoBpm = estadoBpm;
   }

   private java.math.BigDecimal retencion;

   public java.math.BigDecimal getRetencion() {
      return retencion;
   }

   public void setRetencion(java.math.BigDecimal retencion) {
      this.retencion = retencion;
   }

   private java.lang.Long idSriSustentoTributario;

   public java.lang.Long getIdSriSustentoTributario() {
      return idSriSustentoTributario;
   }

   public void setIdSriSustentoTributario(java.lang.Long idSriSustentoTributario) {
      this.idSriSustentoTributario = idSriSustentoTributario;
   }

   private java.sql.Date fechaCaducidad;

   public java.sql.Date getFechaCaducidad() {
      return fechaCaducidad;
   }

   public void setFechaCaducidad(java.sql.Date fechaCaducidad) {
      this.fechaCaducidad = fechaCaducidad;
   }

   private java.lang.String tipoCompra;

   public java.lang.String getTipoCompra() {
      return tipoCompra;
   }

   public void setTipoCompra(java.lang.String tipoCompra) {
      this.tipoCompra = tipoCompra;
   }
   public CompraData() {
   }

   public CompraData(com.spirit.compras.entity.CompraIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setTipodocumentoId(value.getTipodocumentoId());
      setCodigo(value.getCodigo());
      setProveedorId(value.getProveedorId());
      setMonedaId(value.getMonedaId());
      setUsuarioId(value.getUsuarioId());
      setFecha(value.getFecha());
      setFechaVencimiento(value.getFechaVencimiento());
      setPreimpreso(value.getPreimpreso());
      setAutorizacion(value.getAutorizacion());
      setReferencia(value.getReferencia());
      setLocalimportada(value.getLocalimportada());
      setValor(value.getValor());
      setDescuento(value.getDescuento());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setEstadoBpm(value.getEstadoBpm());
      setRetencion(value.getRetencion());
      setIdSriSustentoTributario(value.getIdSriSustentoTributario());
      setFechaCaducidad(value.getFechaCaducidad());
      setTipoCompra(value.getTipoCompra());
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
		return ToStringer.toString((CompraIf)this);
	}
}
