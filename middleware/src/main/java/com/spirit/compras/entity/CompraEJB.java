package com.spirit.compras.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "COMPRA")
@Entity
public class CompraEJB implements Serializable, CompraIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long oficinaId;
   private java.lang.Long tipodocumentoId;
   private java.lang.String codigo;
   private java.lang.Long proveedorId;
   private java.lang.Long monedaId;
   private java.lang.Long usuarioId;
   private java.sql.Date fecha;
   private java.sql.Date fechaVencimiento;
   private java.lang.String preimpreso;
   private java.lang.String autorizacion;
   private java.lang.String referencia;
   private java.lang.String localimportada;
   private java.math.BigDecimal valor;
   private java.math.BigDecimal descuento;
   private java.math.BigDecimal iva;
   private java.math.BigDecimal ice;
   private java.math.BigDecimal otroImpuesto;
   private java.lang.String observacion;
   private java.lang.String estado;
   private java.lang.String estadoBpm;
   private java.math.BigDecimal retencion;
   private java.lang.Long idSriSustentoTributario;
   private java.sql.Date fechaCaducidad;
   private java.lang.String tipoCompra;

   public CompraEJB() {
   }

   public CompraEJB(com.spirit.compras.entity.CompraIf value) {
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

   public java.lang.Long create(com.spirit.compras.entity.CompraIf value) {
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

   @Column(name = "PROVEEDOR_ID")
   public java.lang.Long getProveedorId() {
      return proveedorId;
   }

   public void setProveedorId(java.lang.Long proveedorId) {
      this.proveedorId = proveedorId;
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

   @Column(name = "FECHA")
   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   @Column(name = "FECHA_VENCIMIENTO")
   public java.sql.Date getFechaVencimiento() {
      return fechaVencimiento;
   }

   public void setFechaVencimiento(java.sql.Date fechaVencimiento) {
      this.fechaVencimiento = fechaVencimiento;
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

   @Column(name = "LOCALIMPORTADA")
   public java.lang.String getLocalimportada() {
      return localimportada;
   }

   public void setLocalimportada(java.lang.String localimportada) {
      this.localimportada = localimportada;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "DESCUENTO")
   public java.math.BigDecimal getDescuento() {
      return descuento;
   }

   public void setDescuento(java.math.BigDecimal descuento) {
      this.descuento = descuento;
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

   @Column(name = "ESTADO_BPM")
   public java.lang.String getEstadoBpm() {
      return estadoBpm;
   }

   public void setEstadoBpm(java.lang.String estadoBpm) {
      this.estadoBpm = estadoBpm;
   }

   @Column(name = "RETENCION")
   public java.math.BigDecimal getRetencion() {
      return retencion;
   }

   public void setRetencion(java.math.BigDecimal retencion) {
      this.retencion = retencion;
   }

   @Column(name = "ID_SRI_SUSTENTO_TRIBUTARIO")
   public java.lang.Long getIdSriSustentoTributario() {
      return idSriSustentoTributario;
   }

   public void setIdSriSustentoTributario(java.lang.Long idSriSustentoTributario) {
      this.idSriSustentoTributario = idSriSustentoTributario;
   }

   @Column(name = "FECHA_CADUCIDAD")
   public java.sql.Date getFechaCaducidad() {
      return fechaCaducidad;
   }

   public void setFechaCaducidad(java.sql.Date fechaCaducidad) {
      this.fechaCaducidad = fechaCaducidad;
   }

   @Column(name = "TIPO_COMPRA")
   public java.lang.String getTipoCompra() {
      return tipoCompra;
   }

   public void setTipoCompra(java.lang.String tipoCompra) {
      this.tipoCompra = tipoCompra;
   }
	public String toString() {
		return ToStringer.toString((CompraIf)this);
	}
}
