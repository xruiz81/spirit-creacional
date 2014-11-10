package com.spirit.cartera.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "CARTERA")
@Entity
public class CarteraEJB implements Serializable, CarteraIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String tipo;
   private java.lang.Long oficinaId;
   private java.lang.Long tipodocumentoId;
   private java.lang.String codigo;
   private java.lang.Long referenciaId;
   private java.lang.Long clienteoficinaId;
   private java.lang.String preimpreso;
   private java.lang.Long usuarioId;
   private java.lang.Long vendedorId;
   private java.lang.Long monedaId;
   private java.sql.Timestamp fechaEmision;
   private java.math.BigDecimal valor;
   private java.math.BigDecimal saldo;
   private java.sql.Timestamp fechaUltimaActualizacion;
   private java.lang.String estado;
   private java.lang.String comentario;
   private java.lang.String aprobado;
   private java.sql.Timestamp fechaCreacion;
   private java.lang.String activarRetrocompatibilidad;

   public CarteraEJB() {
   }

   public CarteraEJB(com.spirit.cartera.entity.CarteraIf value) {
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

   public java.lang.Long create(com.spirit.cartera.entity.CarteraIf value) {
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

   @Column(name = "TIPO")
   public java.lang.String getTipo() {
      return tipo;
   }

   public void setTipo(java.lang.String tipo) {
      this.tipo = tipo;
   }

   @Column(name = "OFICINA_ID")
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   @Column(name = "TIPODOCUMENTO_ID")
   public java.lang.Long getTipodocumentoId() {
      return tipodocumentoId;
   }

   public void setTipodocumentoId(java.lang.Long tipodocumentoId) {
      this.tipodocumentoId = tipodocumentoId;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "REFERENCIA_ID")
   public java.lang.Long getReferenciaId() {
      return referenciaId;
   }

   public void setReferenciaId(java.lang.Long referenciaId) {
      this.referenciaId = referenciaId;
   }

   @Column(name = "CLIENTEOFICINA_ID")
   public java.lang.Long getClienteoficinaId() {
      return clienteoficinaId;
   }

   public void setClienteoficinaId(java.lang.Long clienteoficinaId) {
      this.clienteoficinaId = clienteoficinaId;
   }

   @Column(name = "PREIMPRESO")
   public java.lang.String getPreimpreso() {
      return preimpreso;
   }

   public void setPreimpreso(java.lang.String preimpreso) {
      this.preimpreso = preimpreso;
   }

   @Column(name = "USUARIO_ID")
   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   @Column(name = "VENDEDOR_ID")
   public java.lang.Long getVendedorId() {
      return vendedorId;
   }

   public void setVendedorId(java.lang.Long vendedorId) {
      this.vendedorId = vendedorId;
   }

   @Column(name = "MONEDA_ID")
   public java.lang.Long getMonedaId() {
      return monedaId;
   }

   public void setMonedaId(java.lang.Long monedaId) {
      this.monedaId = monedaId;
   }

   @Column(name = "FECHA_EMISION")
   public java.sql.Timestamp getFechaEmision() {
      return fechaEmision;
   }

   public void setFechaEmision(java.sql.Timestamp fechaEmision) {
      this.fechaEmision = fechaEmision;
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

   @Column(name = "FECHA_ULTIMA_ACTUALIZACION")
   public java.sql.Timestamp getFechaUltimaActualizacion() {
      return fechaUltimaActualizacion;
   }

   public void setFechaUltimaActualizacion(java.sql.Timestamp fechaUltimaActualizacion) {
      this.fechaUltimaActualizacion = fechaUltimaActualizacion;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "COMENTARIO")
   public java.lang.String getComentario() {
      return comentario;
   }

   public void setComentario(java.lang.String comentario) {
      this.comentario = comentario;
   }

   @Column(name = "APROBADO")
   public java.lang.String getAprobado() {
      return aprobado;
   }

   public void setAprobado(java.lang.String aprobado) {
      this.aprobado = aprobado;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "ACTIVAR_RETROCOMPATIBILIDAD")
   public java.lang.String getActivarRetrocompatibilidad() {
      return activarRetrocompatibilidad;
   }

   public void setActivarRetrocompatibilidad(java.lang.String activarRetrocompatibilidad) {
      this.activarRetrocompatibilidad = activarRetrocompatibilidad;
   }
	public String toString() {
		return ToStringer.toString((CarteraIf)this);
	}
}
