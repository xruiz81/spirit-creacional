package com.spirit.medios.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "PRESUPUESTO")
@Entity
public class PresupuestoEJB implements Serializable, PresupuestoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.Long ordentrabajodetId;
   private java.lang.Long clienteCondicionId;
   private java.lang.Long planmedioId;
   private java.lang.String concepto;
   private java.lang.Integer modificacion;
   private java.sql.Timestamp fecha;
   private java.sql.Timestamp fechaValidez;
   private java.sql.Timestamp fechaEnvio;
   private java.sql.Timestamp fechaCancelacion;
   private java.sql.Timestamp fechaAceptacion;
   private java.math.BigDecimal valorbruto;
   private java.math.BigDecimal descuento;
   private java.math.BigDecimal valor;
   private java.math.BigDecimal iva;
   private java.lang.String cabecera;
   private java.lang.String estado;
   private java.lang.Long formaPagoId;
   private java.lang.Integer diasValidez;
   private java.math.BigDecimal porcentajeComisionAgencia;
   private java.lang.Long usuarioCreacionId;
   private java.sql.Timestamp fechaCreacion;
   private java.lang.Long usuarioActualizacionId;
   private java.sql.Timestamp fechaActualizacion;
   private java.lang.String temaCampana;
   private java.lang.String autorizacionSap;
   private java.math.BigDecimal descuentosVarios;
   private java.math.BigDecimal descuentoEspecial;
   private java.lang.Long clienteOficinaId;
   private java.lang.String prepago;
   private java.lang.Long referenciaId;
   private java.lang.String tipoReferencia;

   public PresupuestoEJB() {
   }

   public PresupuestoEJB(com.spirit.medios.entity.PresupuestoIf value) {
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

   public java.lang.Long create(com.spirit.medios.entity.PresupuestoIf value) {
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

   @Column(name = "ORDENTRABAJODET_ID")
   public java.lang.Long getOrdentrabajodetId() {
      return ordentrabajodetId;
   }

   public void setOrdentrabajodetId(java.lang.Long ordentrabajodetId) {
      this.ordentrabajodetId = ordentrabajodetId;
   }

   @Column(name = "CLIENTE_CONDICION_ID")
   public java.lang.Long getClienteCondicionId() {
      return clienteCondicionId;
   }

   public void setClienteCondicionId(java.lang.Long clienteCondicionId) {
      this.clienteCondicionId = clienteCondicionId;
   }

   @Column(name = "PLANMEDIO_ID")
   public java.lang.Long getPlanmedioId() {
      return planmedioId;
   }

   public void setPlanmedioId(java.lang.Long planmedioId) {
      this.planmedioId = planmedioId;
   }

   @Column(name = "CONCEPTO")
   public java.lang.String getConcepto() {
      return concepto;
   }

   public void setConcepto(java.lang.String concepto) {
      this.concepto = concepto;
   }

   @Column(name = "MODIFICACION")
   public java.lang.Integer getModificacion() {
      return modificacion;
   }

   public void setModificacion(java.lang.Integer modificacion) {
      this.modificacion = modificacion;
   }

   @Column(name = "FECHA")
   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   @Column(name = "FECHA_VALIDEZ")
   public java.sql.Timestamp getFechaValidez() {
      return fechaValidez;
   }

   public void setFechaValidez(java.sql.Timestamp fechaValidez) {
      this.fechaValidez = fechaValidez;
   }

   @Column(name = "FECHA_ENVIO")
   public java.sql.Timestamp getFechaEnvio() {
      return fechaEnvio;
   }

   public void setFechaEnvio(java.sql.Timestamp fechaEnvio) {
      this.fechaEnvio = fechaEnvio;
   }

   @Column(name = "FECHA_CANCELACION")
   public java.sql.Timestamp getFechaCancelacion() {
      return fechaCancelacion;
   }

   public void setFechaCancelacion(java.sql.Timestamp fechaCancelacion) {
      this.fechaCancelacion = fechaCancelacion;
   }

   @Column(name = "FECHA_ACEPTACION")
   public java.sql.Timestamp getFechaAceptacion() {
      return fechaAceptacion;
   }

   public void setFechaAceptacion(java.sql.Timestamp fechaAceptacion) {
      this.fechaAceptacion = fechaAceptacion;
   }

   @Column(name = "VALORBRUTO")
   public java.math.BigDecimal getValorbruto() {
      return valorbruto;
   }

   public void setValorbruto(java.math.BigDecimal valorbruto) {
      this.valorbruto = valorbruto;
   }

   @Column(name = "DESCUENTO")
   public java.math.BigDecimal getDescuento() {
      return descuento;
   }

   public void setDescuento(java.math.BigDecimal descuento) {
      this.descuento = descuento;
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

   @Column(name = "CABECERA")
   public java.lang.String getCabecera() {
      return cabecera;
   }

   public void setCabecera(java.lang.String cabecera) {
      this.cabecera = cabecera;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "FORMA_PAGO_ID")
   public java.lang.Long getFormaPagoId() {
      return formaPagoId;
   }

   public void setFormaPagoId(java.lang.Long formaPagoId) {
      this.formaPagoId = formaPagoId;
   }

   @Column(name = "DIAS_VALIDEZ")
   public java.lang.Integer getDiasValidez() {
      return diasValidez;
   }

   public void setDiasValidez(java.lang.Integer diasValidez) {
      this.diasValidez = diasValidez;
   }

   @Column(name = "PORCENTAJE_COMISION_AGENCIA")
   public java.math.BigDecimal getPorcentajeComisionAgencia() {
      return porcentajeComisionAgencia;
   }

   public void setPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) {
      this.porcentajeComisionAgencia = porcentajeComisionAgencia;
   }

   @Column(name = "USUARIO_CREACION_ID")
   public java.lang.Long getUsuarioCreacionId() {
      return usuarioCreacionId;
   }

   public void setUsuarioCreacionId(java.lang.Long usuarioCreacionId) {
      this.usuarioCreacionId = usuarioCreacionId;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "USUARIO_ACTUALIZACION_ID")
   public java.lang.Long getUsuarioActualizacionId() {
      return usuarioActualizacionId;
   }

   public void setUsuarioActualizacionId(java.lang.Long usuarioActualizacionId) {
      this.usuarioActualizacionId = usuarioActualizacionId;
   }

   @Column(name = "FECHA_ACTUALIZACION")
   public java.sql.Timestamp getFechaActualizacion() {
      return fechaActualizacion;
   }

   public void setFechaActualizacion(java.sql.Timestamp fechaActualizacion) {
      this.fechaActualizacion = fechaActualizacion;
   }

   @Column(name = "TEMA_CAMPANA")
   public java.lang.String getTemaCampana() {
      return temaCampana;
   }

   public void setTemaCampana(java.lang.String temaCampana) {
      this.temaCampana = temaCampana;
   }

   @Column(name = "AUTORIZACION_SAP")
   public java.lang.String getAutorizacionSap() {
      return autorizacionSap;
   }

   public void setAutorizacionSap(java.lang.String autorizacionSap) {
      this.autorizacionSap = autorizacionSap;
   }

   @Column(name = "DESCUENTOS_VARIOS")
   public java.math.BigDecimal getDescuentosVarios() {
      return descuentosVarios;
   }

   public void setDescuentosVarios(java.math.BigDecimal descuentosVarios) {
      this.descuentosVarios = descuentosVarios;
   }

   @Column(name = "DESCUENTO_ESPECIAL")
   public java.math.BigDecimal getDescuentoEspecial() {
      return descuentoEspecial;
   }

   public void setDescuentoEspecial(java.math.BigDecimal descuentoEspecial) {
      this.descuentoEspecial = descuentoEspecial;
   }

   @Column(name = "CLIENTE_OFICINA_ID")
   public java.lang.Long getClienteOficinaId() {
      return clienteOficinaId;
   }

   public void setClienteOficinaId(java.lang.Long clienteOficinaId) {
      this.clienteOficinaId = clienteOficinaId;
   }

   @Column(name = "PREPAGO")
   public java.lang.String getPrepago() {
      return prepago;
   }

   public void setPrepago(java.lang.String prepago) {
      this.prepago = prepago;
   }

   @Column(name = "REFERENCIA_ID")
   public java.lang.Long getReferenciaId() {
      return referenciaId;
   }

   public void setReferenciaId(java.lang.Long referenciaId) {
      this.referenciaId = referenciaId;
   }

   @Column(name = "TIPO_REFERENCIA")
   public java.lang.String getTipoReferencia() {
      return tipoReferencia;
   }

   public void setTipoReferencia(java.lang.String tipoReferencia) {
      this.tipoReferencia = tipoReferencia;
   }
	public String toString() {
		return ToStringer.toString((PresupuestoIf)this);
	}
}
