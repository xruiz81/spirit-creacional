package com.spirit.pos.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "TARJETA")
@Entity
public class TarjetaEJB implements Serializable, TarjetaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.Long tipoId;
   private java.lang.Long clienteoficinaId;
   private java.lang.Long referidoporId;
   private java.lang.Long garante;
   private java.lang.Long fechaValidez;
   private java.lang.Long fechaEmision;
   private java.math.BigDecimal puntosAcumulados;
   private java.math.BigDecimal puntosUtilizados;
   private java.math.BigDecimal puntosComprometidos;
   private java.math.BigDecimal valor;
   private java.math.BigDecimal saldo;
   private java.math.BigDecimal cupo;
   private java.lang.String estado;
   private java.lang.String validador;
   private java.math.BigDecimal puntosAcumuladosStatus;
   private java.math.BigDecimal dineroAcumulado;
   private java.math.BigDecimal dineroUtilizado;
   private java.math.BigDecimal dineroComprometido;
   private java.math.BigDecimal dineroAcumuladoStatus;
   private java.lang.Long empresaId;
   private java.lang.Long fechaUltimoCambioStatus;
   private java.lang.Long productoId;

   public TarjetaEJB() {
   }

   public TarjetaEJB(com.spirit.pos.entity.TarjetaIf value) {
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

   public java.lang.Long create(com.spirit.pos.entity.TarjetaIf value) {
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
      return value.getPrimaryKey();
   }

   @javax.persistence.Transient public java.lang.Long getPrimaryKey() {
        return getId();
    }

   @javax.persistence.Transient public void setPrimaryKey(java.lang.Long primaryKey) {
       setId(primaryKey);
    }

   @Column(name = "ID")
@TableGenerator(name="SEQ_GEN",
			allocationSize=HibernateSequenceAllocationSize.allocationSize
)
   @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_GEN")
   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "TIPO_ID")
   public java.lang.Long getTipoId() {
      return tipoId;
   }

   public void setTipoId(java.lang.Long tipoId) {
      this.tipoId = tipoId;
   }

   @Column(name = "CLIENTEOFICINA_ID")
   public java.lang.Long getClienteoficinaId() {
      return clienteoficinaId;
   }

   public void setClienteoficinaId(java.lang.Long clienteoficinaId) {
      this.clienteoficinaId = clienteoficinaId;
   }

   @Column(name = "REFERIDOPOR_ID")
   public java.lang.Long getReferidoporId() {
      return referidoporId;
   }

   public void setReferidoporId(java.lang.Long referidoporId) {
      this.referidoporId = referidoporId;
   }

   @Column(name = "GARANTE")
   public java.lang.Long getGarante() {
      return garante;
   }

   public void setGarante(java.lang.Long garante) {
      this.garante = garante;
   }

   @Column(name = "FECHA_VALIDEZ")
   public java.lang.Long getFechaValidez() {
      return fechaValidez;
   }

   public void setFechaValidez(java.lang.Long fechaValidez) {
      this.fechaValidez = fechaValidez;
   }

   @Column(name = "FECHA_EMISION")
   public java.lang.Long getFechaEmision() {
      return fechaEmision;
   }

   public void setFechaEmision(java.lang.Long fechaEmision) {
      this.fechaEmision = fechaEmision;
   }

   @Column(name = "PUNTOS_ACUMULADOS")
   public java.math.BigDecimal getPuntosAcumulados() {
      return puntosAcumulados;
   }

   public void setPuntosAcumulados(java.math.BigDecimal puntosAcumulados) {
      this.puntosAcumulados = puntosAcumulados;
   }

   @Column(name = "PUNTOS_UTILIZADOS")
   public java.math.BigDecimal getPuntosUtilizados() {
      return puntosUtilizados;
   }

   public void setPuntosUtilizados(java.math.BigDecimal puntosUtilizados) {
      this.puntosUtilizados = puntosUtilizados;
   }

   @Column(name = "PUNTOS_COMPROMETIDOS")
   public java.math.BigDecimal getPuntosComprometidos() {
      return puntosComprometidos;
   }

   public void setPuntosComprometidos(java.math.BigDecimal puntosComprometidos) {
      this.puntosComprometidos = puntosComprometidos;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "SALDO")
   public java.math.BigDecimal getSaldo() {
      return saldo;
   }

   public void setSaldo(java.math.BigDecimal saldo) {
      this.saldo = saldo;
   }

   @Column(name = "CUPO")
   public java.math.BigDecimal getCupo() {
      return cupo;
   }

   public void setCupo(java.math.BigDecimal cupo) {
      this.cupo = cupo;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "VALIDADOR")
   public java.lang.String getValidador() {
      return validador;
   }

   public void setValidador(java.lang.String validador) {
      this.validador = validador;
   }

   @Column(name = "PUNTOS_ACUMULADOS_STATUS")
   public java.math.BigDecimal getPuntosAcumuladosStatus() {
      return puntosAcumuladosStatus;
   }

   public void setPuntosAcumuladosStatus(java.math.BigDecimal puntosAcumuladosStatus) {
      this.puntosAcumuladosStatus = puntosAcumuladosStatus;
   }

   @Column(name = "DINERO_ACUMULADO")
   public java.math.BigDecimal getDineroAcumulado() {
      return dineroAcumulado;
   }

   public void setDineroAcumulado(java.math.BigDecimal dineroAcumulado) {
      this.dineroAcumulado = dineroAcumulado;
   }

   @Column(name = "DINERO_UTILIZADO")
   public java.math.BigDecimal getDineroUtilizado() {
      return dineroUtilizado;
   }

   public void setDineroUtilizado(java.math.BigDecimal dineroUtilizado) {
      this.dineroUtilizado = dineroUtilizado;
   }

   @Column(name = "DINERO_COMPROMETIDO")
   public java.math.BigDecimal getDineroComprometido() {
      return dineroComprometido;
   }

   public void setDineroComprometido(java.math.BigDecimal dineroComprometido) {
      this.dineroComprometido = dineroComprometido;
   }

   @Column(name = "DINERO_ACUMULADO_STATUS")
   public java.math.BigDecimal getDineroAcumuladoStatus() {
      return dineroAcumuladoStatus;
   }

   public void setDineroAcumuladoStatus(java.math.BigDecimal dineroAcumuladoStatus) {
      this.dineroAcumuladoStatus = dineroAcumuladoStatus;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "FECHA_ULTIMO_CAMBIO_STATUS")
   public java.lang.Long getFechaUltimoCambioStatus() {
      return fechaUltimoCambioStatus;
   }

   public void setFechaUltimoCambioStatus(java.lang.Long fechaUltimoCambioStatus) {
      this.fechaUltimoCambioStatus = fechaUltimoCambioStatus;
   }

   @Column(name = "PRODUCTO_ID")
   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }
	public String toString() {
		return ToStringer.toString((TarjetaIf)this);
	}
}
