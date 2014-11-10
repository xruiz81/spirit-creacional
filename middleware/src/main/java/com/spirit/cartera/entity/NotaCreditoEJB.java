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

@Table(name = "NOTA_CREDITO")
@Entity
public class NotaCreditoEJB implements Serializable, NotaCreditoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long oficinaId;
   private java.lang.Long tipoDocumentoId;
   private java.lang.String codigo;
   private java.lang.Long operadorNegocioId;
   private java.lang.Long monedaId;
   private java.lang.Long usuarioId;
   private java.sql.Timestamp fechaEmision;
   private java.sql.Timestamp fechaVencimiento;
   private java.sql.Timestamp fechaCaducidad;
   private java.lang.String preimpreso;
   private java.lang.String autorizacion;
   private java.lang.String referencia;
   private java.math.BigDecimal valor;
   private java.math.BigDecimal iva;
   private java.math.BigDecimal ice;
   private java.math.BigDecimal otroImpuesto;
   private java.lang.String observacion;
   private java.lang.String estado;
   private java.lang.String tipoCartera;
   private java.lang.Long referenciaId;

   public NotaCreditoEJB() {
   }

   public NotaCreditoEJB(com.spirit.cartera.entity.NotaCreditoIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setTipoDocumentoId(value.getTipoDocumentoId());
      setCodigo(value.getCodigo());
      setOperadorNegocioId(value.getOperadorNegocioId());
      setMonedaId(value.getMonedaId());
      setUsuarioId(value.getUsuarioId());
      setFechaEmision(value.getFechaEmision());
      setFechaVencimiento(value.getFechaVencimiento());
      setFechaCaducidad(value.getFechaCaducidad());
      setPreimpreso(value.getPreimpreso());
      setAutorizacion(value.getAutorizacion());
      setReferencia(value.getReferencia());
      setValor(value.getValor());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setTipoCartera(value.getTipoCartera());
      setReferenciaId(value.getReferenciaId());
   }

   public java.lang.Long create(com.spirit.cartera.entity.NotaCreditoIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setTipoDocumentoId(value.getTipoDocumentoId());
      setCodigo(value.getCodigo());
      setOperadorNegocioId(value.getOperadorNegocioId());
      setMonedaId(value.getMonedaId());
      setUsuarioId(value.getUsuarioId());
      setFechaEmision(value.getFechaEmision());
      setFechaVencimiento(value.getFechaVencimiento());
      setFechaCaducidad(value.getFechaCaducidad());
      setPreimpreso(value.getPreimpreso());
      setAutorizacion(value.getAutorizacion());
      setReferencia(value.getReferencia());
      setValor(value.getValor());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setTipoCartera(value.getTipoCartera());
      setReferenciaId(value.getReferenciaId());
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

   @Column(name = "OFICINA_ID")
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   @Column(name = "TIPO_DOCUMENTO_ID")
   public java.lang.Long getTipoDocumentoId() {
      return tipoDocumentoId;
   }

   public void setTipoDocumentoId(java.lang.Long tipoDocumentoId) {
      this.tipoDocumentoId = tipoDocumentoId;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "OPERADOR_NEGOCIO_ID")
   public java.lang.Long getOperadorNegocioId() {
      return operadorNegocioId;
   }

   public void setOperadorNegocioId(java.lang.Long operadorNegocioId) {
      this.operadorNegocioId = operadorNegocioId;
   }

   @Column(name = "MONEDA_ID")
   public java.lang.Long getMonedaId() {
      return monedaId;
   }

   public void setMonedaId(java.lang.Long monedaId) {
      this.monedaId = monedaId;
   }

   @Column(name = "USUARIO_ID")
   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   @Column(name = "FECHA_EMISION")
   public java.sql.Timestamp getFechaEmision() {
      return fechaEmision;
   }

   public void setFechaEmision(java.sql.Timestamp fechaEmision) {
      this.fechaEmision = fechaEmision;
   }

   @Column(name = "FECHA_VENCIMIENTO")
   public java.sql.Timestamp getFechaVencimiento() {
      return fechaVencimiento;
   }

   public void setFechaVencimiento(java.sql.Timestamp fechaVencimiento) {
      this.fechaVencimiento = fechaVencimiento;
   }

   @Column(name = "FECHA_CADUCIDAD")
   public java.sql.Timestamp getFechaCaducidad() {
      return fechaCaducidad;
   }

   public void setFechaCaducidad(java.sql.Timestamp fechaCaducidad) {
      this.fechaCaducidad = fechaCaducidad;
   }

   @Column(name = "PREIMPRESO")
   public java.lang.String getPreimpreso() {
      return preimpreso;
   }

   public void setPreimpreso(java.lang.String preimpreso) {
      this.preimpreso = preimpreso;
   }

   @Column(name = "AUTORIZACION")
   public java.lang.String getAutorizacion() {
      return autorizacion;
   }

   public void setAutorizacion(java.lang.String autorizacion) {
      this.autorizacion = autorizacion;
   }

   @Column(name = "REFERENCIA")
   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "IVA")
   public java.math.BigDecimal getIva() {
      return iva;
   }

   public void setIva(java.math.BigDecimal iva) {
      this.iva = iva;
   }

   @Column(name = "ICE")
   public java.math.BigDecimal getIce() {
      return ice;
   }

   public void setIce(java.math.BigDecimal ice) {
      this.ice = ice;
   }

   @Column(name = "OTRO_IMPUESTO")
   public java.math.BigDecimal getOtroImpuesto() {
      return otroImpuesto;
   }

   public void setOtroImpuesto(java.math.BigDecimal otroImpuesto) {
      this.otroImpuesto = otroImpuesto;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "TIPO_CARTERA")
   public java.lang.String getTipoCartera() {
      return tipoCartera;
   }

   public void setTipoCartera(java.lang.String tipoCartera) {
      this.tipoCartera = tipoCartera;
   }

   @Column(name = "REFERENCIA_ID")
   public java.lang.Long getReferenciaId() {
      return referenciaId;
   }

   public void setReferenciaId(java.lang.Long referenciaId) {
      this.referenciaId = referenciaId;
   }
	public String toString() {
		return ToStringer.toString((NotaCreditoIf)this);
	}
}
