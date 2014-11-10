package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PresupuestoData implements PresupuestoIf, Serializable    {


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

   private java.lang.Long ordentrabajodetId;

   public java.lang.Long getOrdentrabajodetId() {
      return ordentrabajodetId;
   }

   public void setOrdentrabajodetId(java.lang.Long ordentrabajodetId) {
      this.ordentrabajodetId = ordentrabajodetId;
   }

   private java.lang.Long clienteCondicionId;

   public java.lang.Long getClienteCondicionId() {
      return clienteCondicionId;
   }

   public void setClienteCondicionId(java.lang.Long clienteCondicionId) {
      this.clienteCondicionId = clienteCondicionId;
   }

   private java.lang.Long planmedioId;

   public java.lang.Long getPlanmedioId() {
      return planmedioId;
   }

   public void setPlanmedioId(java.lang.Long planmedioId) {
      this.planmedioId = planmedioId;
   }

   private java.lang.String concepto;

   public java.lang.String getConcepto() {
      return concepto;
   }

   public void setConcepto(java.lang.String concepto) {
      this.concepto = concepto;
   }

   private java.lang.Integer modificacion;

   public java.lang.Integer getModificacion() {
      return modificacion;
   }

   public void setModificacion(java.lang.Integer modificacion) {
      this.modificacion = modificacion;
   }

   private java.sql.Timestamp fecha;

   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   private java.sql.Timestamp fechaValidez;

   public java.sql.Timestamp getFechaValidez() {
      return fechaValidez;
   }

   public void setFechaValidez(java.sql.Timestamp fechaValidez) {
      this.fechaValidez = fechaValidez;
   }

   private java.sql.Timestamp fechaEnvio;

   public java.sql.Timestamp getFechaEnvio() {
      return fechaEnvio;
   }

   public void setFechaEnvio(java.sql.Timestamp fechaEnvio) {
      this.fechaEnvio = fechaEnvio;
   }

   private java.sql.Timestamp fechaCancelacion;

   public java.sql.Timestamp getFechaCancelacion() {
      return fechaCancelacion;
   }

   public void setFechaCancelacion(java.sql.Timestamp fechaCancelacion) {
      this.fechaCancelacion = fechaCancelacion;
   }

   private java.sql.Timestamp fechaAceptacion;

   public java.sql.Timestamp getFechaAceptacion() {
      return fechaAceptacion;
   }

   public void setFechaAceptacion(java.sql.Timestamp fechaAceptacion) {
      this.fechaAceptacion = fechaAceptacion;
   }

   private java.math.BigDecimal valorbruto;

   public java.math.BigDecimal getValorbruto() {
      return valorbruto;
   }

   public void setValorbruto(java.math.BigDecimal valorbruto) {
      this.valorbruto = valorbruto;
   }

   private java.math.BigDecimal descuento;

   public java.math.BigDecimal getDescuento() {
      return descuento;
   }

   public void setDescuento(java.math.BigDecimal descuento) {
      this.descuento = descuento;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.math.BigDecimal iva;

   public java.math.BigDecimal getIva() {
      return iva;
   }

   public void setIva(java.math.BigDecimal iva) {
      this.iva = iva;
   }

   private java.lang.String cabecera;

   public java.lang.String getCabecera() {
      return cabecera;
   }

   public void setCabecera(java.lang.String cabecera) {
      this.cabecera = cabecera;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.Long formaPagoId;

   public java.lang.Long getFormaPagoId() {
      return formaPagoId;
   }

   public void setFormaPagoId(java.lang.Long formaPagoId) {
      this.formaPagoId = formaPagoId;
   }

   private java.lang.Integer diasValidez;

   public java.lang.Integer getDiasValidez() {
      return diasValidez;
   }

   public void setDiasValidez(java.lang.Integer diasValidez) {
      this.diasValidez = diasValidez;
   }

   private java.math.BigDecimal porcentajeComisionAgencia;

   public java.math.BigDecimal getPorcentajeComisionAgencia() {
      return porcentajeComisionAgencia;
   }

   public void setPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) {
      this.porcentajeComisionAgencia = porcentajeComisionAgencia;
   }

   private java.lang.Long usuarioCreacionId;

   public java.lang.Long getUsuarioCreacionId() {
      return usuarioCreacionId;
   }

   public void setUsuarioCreacionId(java.lang.Long usuarioCreacionId) {
      this.usuarioCreacionId = usuarioCreacionId;
   }

   private java.sql.Timestamp fechaCreacion;

   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.lang.Long usuarioActualizacionId;

   public java.lang.Long getUsuarioActualizacionId() {
      return usuarioActualizacionId;
   }

   public void setUsuarioActualizacionId(java.lang.Long usuarioActualizacionId) {
      this.usuarioActualizacionId = usuarioActualizacionId;
   }

   private java.sql.Timestamp fechaActualizacion;

   public java.sql.Timestamp getFechaActualizacion() {
      return fechaActualizacion;
   }

   public void setFechaActualizacion(java.sql.Timestamp fechaActualizacion) {
      this.fechaActualizacion = fechaActualizacion;
   }

   private java.lang.String temaCampana;

   public java.lang.String getTemaCampana() {
      return temaCampana;
   }

   public void setTemaCampana(java.lang.String temaCampana) {
      this.temaCampana = temaCampana;
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

   private java.math.BigDecimal descuentoEspecial;

   public java.math.BigDecimal getDescuentoEspecial() {
      return descuentoEspecial;
   }

   public void setDescuentoEspecial(java.math.BigDecimal descuentoEspecial) {
      this.descuentoEspecial = descuentoEspecial;
   }

   private java.lang.Long clienteOficinaId;

   public java.lang.Long getClienteOficinaId() {
      return clienteOficinaId;
   }

   public void setClienteOficinaId(java.lang.Long clienteOficinaId) {
      this.clienteOficinaId = clienteOficinaId;
   }

   private java.lang.String prepago;

   public java.lang.String getPrepago() {
      return prepago;
   }

   public void setPrepago(java.lang.String prepago) {
      this.prepago = prepago;
   }

   private java.lang.Long referenciaId;

   public java.lang.Long getReferenciaId() {
      return referenciaId;
   }

   public void setReferenciaId(java.lang.Long referenciaId) {
      this.referenciaId = referenciaId;
   }

   private java.lang.String tipoReferencia;

   public java.lang.String getTipoReferencia() {
      return tipoReferencia;
   }

   public void setTipoReferencia(java.lang.String tipoReferencia) {
      this.tipoReferencia = tipoReferencia;
   }
   public PresupuestoData() {
   }

   public PresupuestoData(com.spirit.medios.entity.PresupuestoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setOrdentrabajodetId(value.getOrdentrabajodetId());
      setClienteCondicionId(value.getClienteCondicionId());
      setPlanmedioId(value.getPlanmedioId());
      setConcepto(value.getConcepto());
      setModificacion(value.getModificacion());
      setFecha(value.getFecha());
      setFechaValidez(value.getFechaValidez());
      setFechaEnvio(value.getFechaEnvio());
      setFechaCancelacion(value.getFechaCancelacion());
      setFechaAceptacion(value.getFechaAceptacion());
      setValorbruto(value.getValorbruto());
      setDescuento(value.getDescuento());
      setValor(value.getValor());
      setIva(value.getIva());
      setCabecera(value.getCabecera());
      setEstado(value.getEstado());
      setFormaPagoId(value.getFormaPagoId());
      setDiasValidez(value.getDiasValidez());
      setPorcentajeComisionAgencia(value.getPorcentajeComisionAgencia());
      setUsuarioCreacionId(value.getUsuarioCreacionId());
      setFechaCreacion(value.getFechaCreacion());
      setUsuarioActualizacionId(value.getUsuarioActualizacionId());
      setFechaActualizacion(value.getFechaActualizacion());
      setTemaCampana(value.getTemaCampana());
      setAutorizacionSap(value.getAutorizacionSap());
      setDescuentosVarios(value.getDescuentosVarios());
      setDescuentoEspecial(value.getDescuentoEspecial());
      setClienteOficinaId(value.getClienteOficinaId());
      setPrepago(value.getPrepago());
      setReferenciaId(value.getReferenciaId());
      setTipoReferencia(value.getTipoReferencia());
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
		return ToStringer.toString((PresupuestoIf)this);
	}
}
