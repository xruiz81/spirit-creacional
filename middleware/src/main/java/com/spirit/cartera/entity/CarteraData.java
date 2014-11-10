package com.spirit.cartera.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CarteraData implements CarteraIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String tipo;

   public java.lang.String getTipo() {
      return tipo;
   }

   public void setTipo(java.lang.String tipo) {
      this.tipo = tipo;
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

   private java.lang.Long referenciaId;

   public java.lang.Long getReferenciaId() {
      return referenciaId;
   }

   public void setReferenciaId(java.lang.Long referenciaId) {
      this.referenciaId = referenciaId;
   }

   private java.lang.Long clienteoficinaId;

   public java.lang.Long getClienteoficinaId() {
      return clienteoficinaId;
   }

   public void setClienteoficinaId(java.lang.Long clienteoficinaId) {
      this.clienteoficinaId = clienteoficinaId;
   }

   private java.lang.String preimpreso;

   public java.lang.String getPreimpreso() {
      return preimpreso;
   }

   public void setPreimpreso(java.lang.String preimpreso) {
      this.preimpreso = preimpreso;
   }

   private java.lang.Long usuarioId;

   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   private java.lang.Long vendedorId;

   public java.lang.Long getVendedorId() {
      return vendedorId;
   }

   public void setVendedorId(java.lang.Long vendedorId) {
      this.vendedorId = vendedorId;
   }

   private java.lang.Long monedaId;

   public java.lang.Long getMonedaId() {
      return monedaId;
   }

   public void setMonedaId(java.lang.Long monedaId) {
      this.monedaId = monedaId;
   }

   private java.sql.Timestamp fechaEmision;

   public java.sql.Timestamp getFechaEmision() {
      return fechaEmision;
   }

   public void setFechaEmision(java.sql.Timestamp fechaEmision) {
      this.fechaEmision = fechaEmision;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.math.BigDecimal saldo;

   public java.math.BigDecimal getSaldo() {
      return saldo;
   }

   public void setSaldo(java.math.BigDecimal saldo) {
      this.saldo = saldo;
   }

   private java.sql.Timestamp fechaUltimaActualizacion;

   public java.sql.Timestamp getFechaUltimaActualizacion() {
      return fechaUltimaActualizacion;
   }

   public void setFechaUltimaActualizacion(java.sql.Timestamp fechaUltimaActualizacion) {
      this.fechaUltimaActualizacion = fechaUltimaActualizacion;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String comentario;

   public java.lang.String getComentario() {
      return comentario;
   }

   public void setComentario(java.lang.String comentario) {
      this.comentario = comentario;
   }

   private java.lang.String aprobado;

   public java.lang.String getAprobado() {
      return aprobado;
   }

   public void setAprobado(java.lang.String aprobado) {
      this.aprobado = aprobado;
   }

   private java.sql.Timestamp fechaCreacion;

   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.lang.String activarRetrocompatibilidad;

   public java.lang.String getActivarRetrocompatibilidad() {
      return activarRetrocompatibilidad;
   }

   public void setActivarRetrocompatibilidad(java.lang.String activarRetrocompatibilidad) {
      this.activarRetrocompatibilidad = activarRetrocompatibilidad;
   }
   public CarteraData() {
   }

   public CarteraData(com.spirit.cartera.entity.CarteraIf value) {
      setId(value.getId());
      setTipo(value.getTipo());
      setOficinaId(value.getOficinaId());
      setTipodocumentoId(value.getTipodocumentoId());
      setCodigo(value.getCodigo());
      setReferenciaId(value.getReferenciaId());
      setClienteoficinaId(value.getClienteoficinaId());
      setPreimpreso(value.getPreimpreso());
      setUsuarioId(value.getUsuarioId());
      setVendedorId(value.getVendedorId());
      setMonedaId(value.getMonedaId());
      setFechaEmision(value.getFechaEmision());
      setValor(value.getValor());
      setSaldo(value.getSaldo());
      setFechaUltimaActualizacion(value.getFechaUltimaActualizacion());
      setEstado(value.getEstado());
      setComentario(value.getComentario());
      setAprobado(value.getAprobado());
      setFechaCreacion(value.getFechaCreacion());
      setActivarRetrocompatibilidad(value.getActivarRetrocompatibilidad());
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
		return ToStringer.toString((CarteraIf)this);
	}
}
