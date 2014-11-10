package com.spirit.pos.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class TarjetaData implements TarjetaIf, Serializable    {


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

   private java.lang.Long tipoId;

   public java.lang.Long getTipoId() {
      return tipoId;
   }

   public void setTipoId(java.lang.Long tipoId) {
      this.tipoId = tipoId;
   }

   private java.lang.Long clienteoficinaId;

   public java.lang.Long getClienteoficinaId() {
      return clienteoficinaId;
   }

   public void setClienteoficinaId(java.lang.Long clienteoficinaId) {
      this.clienteoficinaId = clienteoficinaId;
   }

   private java.lang.Long referidoporId;

   public java.lang.Long getReferidoporId() {
      return referidoporId;
   }

   public void setReferidoporId(java.lang.Long referidoporId) {
      this.referidoporId = referidoporId;
   }

   private java.lang.Long garante;

   public java.lang.Long getGarante() {
      return garante;
   }

   public void setGarante(java.lang.Long garante) {
      this.garante = garante;
   }

   private java.lang.Long fechaValidez;

   public java.lang.Long getFechaValidez() {
      return fechaValidez;
   }

   public void setFechaValidez(java.lang.Long fechaValidez) {
      this.fechaValidez = fechaValidez;
   }

   private java.lang.Long fechaEmision;

   public java.lang.Long getFechaEmision() {
      return fechaEmision;
   }

   public void setFechaEmision(java.lang.Long fechaEmision) {
      this.fechaEmision = fechaEmision;
   }

   private java.math.BigDecimal puntosAcumulados;

   public java.math.BigDecimal getPuntosAcumulados() {
      return puntosAcumulados;
   }

   public void setPuntosAcumulados(java.math.BigDecimal puntosAcumulados) {
      this.puntosAcumulados = puntosAcumulados;
   }

   private java.math.BigDecimal puntosUtilizados;

   public java.math.BigDecimal getPuntosUtilizados() {
      return puntosUtilizados;
   }

   public void setPuntosUtilizados(java.math.BigDecimal puntosUtilizados) {
      this.puntosUtilizados = puntosUtilizados;
   }

   private java.math.BigDecimal puntosComprometidos;

   public java.math.BigDecimal getPuntosComprometidos() {
      return puntosComprometidos;
   }

   public void setPuntosComprometidos(java.math.BigDecimal puntosComprometidos) {
      this.puntosComprometidos = puntosComprometidos;
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

   private java.math.BigDecimal cupo;

   public java.math.BigDecimal getCupo() {
      return cupo;
   }

   public void setCupo(java.math.BigDecimal cupo) {
      this.cupo = cupo;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String validador;

   public java.lang.String getValidador() {
      return validador;
   }

   public void setValidador(java.lang.String validador) {
      this.validador = validador;
   }

   private java.math.BigDecimal puntosAcumuladosStatus;

   public java.math.BigDecimal getPuntosAcumuladosStatus() {
      return puntosAcumuladosStatus;
   }

   public void setPuntosAcumuladosStatus(java.math.BigDecimal puntosAcumuladosStatus) {
      this.puntosAcumuladosStatus = puntosAcumuladosStatus;
   }

   private java.math.BigDecimal dineroAcumulado;

   public java.math.BigDecimal getDineroAcumulado() {
      return dineroAcumulado;
   }

   public void setDineroAcumulado(java.math.BigDecimal dineroAcumulado) {
      this.dineroAcumulado = dineroAcumulado;
   }

   private java.math.BigDecimal dineroUtilizado;

   public java.math.BigDecimal getDineroUtilizado() {
      return dineroUtilizado;
   }

   public void setDineroUtilizado(java.math.BigDecimal dineroUtilizado) {
      this.dineroUtilizado = dineroUtilizado;
   }

   private java.math.BigDecimal dineroComprometido;

   public java.math.BigDecimal getDineroComprometido() {
      return dineroComprometido;
   }

   public void setDineroComprometido(java.math.BigDecimal dineroComprometido) {
      this.dineroComprometido = dineroComprometido;
   }

   private java.math.BigDecimal dineroAcumuladoStatus;

   public java.math.BigDecimal getDineroAcumuladoStatus() {
      return dineroAcumuladoStatus;
   }

   public void setDineroAcumuladoStatus(java.math.BigDecimal dineroAcumuladoStatus) {
      this.dineroAcumuladoStatus = dineroAcumuladoStatus;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   private java.lang.Long fechaUltimoCambioStatus;

   public java.lang.Long getFechaUltimoCambioStatus() {
      return fechaUltimoCambioStatus;
   }

   public void setFechaUltimoCambioStatus(java.lang.Long fechaUltimoCambioStatus) {
      this.fechaUltimoCambioStatus = fechaUltimoCambioStatus;
   }

   private java.lang.Long productoId;

   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }
   public TarjetaData() {
   }

   public TarjetaData(com.spirit.pos.entity.TarjetaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setTipoId(value.getTipoId());
      setClienteoficinaId(value.getClienteoficinaId());
      setReferidoporId(value.getReferidoporId());
      setGarante(value.getGarante());
      setFechaValidez(value.getFechaValidez());
      setFechaEmision(value.getFechaEmision());
      setPuntosAcumulados(value.getPuntosAcumulados());
      setPuntosUtilizados(value.getPuntosUtilizados());
      setPuntosComprometidos(value.getPuntosComprometidos());
      setValor(value.getValor());
      setSaldo(value.getSaldo());
      setCupo(value.getCupo());
      setEstado(value.getEstado());
      setValidador(value.getValidador());
      setPuntosAcumuladosStatus(value.getPuntosAcumuladosStatus());
      setDineroAcumulado(value.getDineroAcumulado());
      setDineroUtilizado(value.getDineroUtilizado());
      setDineroComprometido(value.getDineroComprometido());
      setDineroAcumuladoStatus(value.getDineroAcumuladoStatus());
      setEmpresaId(value.getEmpresaId());
      setFechaUltimoCambioStatus(value.getFechaUltimoCambioStatus());
      setProductoId(value.getProductoId());
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
		return ToStringer.toString((TarjetaIf)this);
	}
}
