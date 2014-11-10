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

@Table(name = "ORDEN_COMPRA")
@Entity
public class OrdenCompraEJB implements Serializable, OrdenCompraIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long oficinaId;
   private java.lang.Long tipodocumentoId;
   private java.lang.String codigo;
   private java.lang.Long proveedorId;
   private java.lang.Long monedaId;
   private java.lang.Long empleadoId;
   private java.lang.Long usuarioId;
   private java.lang.Long bodegaId;
   private java.lang.String localimportada;
   private java.sql.Date fecha;
   private java.sql.Date fechaRecepcion;
   private java.sql.Date fechaVencimiento;
   private java.lang.String estado;
   private java.lang.String observacion;
   private java.math.BigDecimal valor;
   private java.math.BigDecimal descuentoAgenciaCompra;
   private java.math.BigDecimal iva;
   private java.math.BigDecimal ice;
   private java.math.BigDecimal otroImpuesto;
   private java.lang.Long solicitudcompraId;
   private java.lang.String estadoBpm;
   private java.math.BigDecimal porcentajeDescuentosVariosCompra;
   private java.math.BigDecimal porcentajeDescuentosVariosVenta;
   private java.math.BigDecimal porcentajeDescuentoEspecial;
   private java.lang.String revision;

   public OrdenCompraEJB() {
   }

   public OrdenCompraEJB(com.spirit.compras.entity.OrdenCompraIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setTipodocumentoId(value.getTipodocumentoId());
      setCodigo(value.getCodigo());
      setProveedorId(value.getProveedorId());
      setMonedaId(value.getMonedaId());
      setEmpleadoId(value.getEmpleadoId());
      setUsuarioId(value.getUsuarioId());
      setBodegaId(value.getBodegaId());
      setLocalimportada(value.getLocalimportada());
      setFecha(value.getFecha());
      setFechaRecepcion(value.getFechaRecepcion());
      setFechaVencimiento(value.getFechaVencimiento());
      setEstado(value.getEstado());
      setObservacion(value.getObservacion());
      setValor(value.getValor());
      setDescuentoAgenciaCompra(value.getDescuentoAgenciaCompra());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setSolicitudcompraId(value.getSolicitudcompraId());
      setEstadoBpm(value.getEstadoBpm());
      setPorcentajeDescuentosVariosCompra(value.getPorcentajeDescuentosVariosCompra());
      setPorcentajeDescuentosVariosVenta(value.getPorcentajeDescuentosVariosVenta());
      setPorcentajeDescuentoEspecial(value.getPorcentajeDescuentoEspecial());
      setRevision(value.getRevision());
   }

   public java.lang.Long create(com.spirit.compras.entity.OrdenCompraIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setTipodocumentoId(value.getTipodocumentoId());
      setCodigo(value.getCodigo());
      setProveedorId(value.getProveedorId());
      setMonedaId(value.getMonedaId());
      setEmpleadoId(value.getEmpleadoId());
      setUsuarioId(value.getUsuarioId());
      setBodegaId(value.getBodegaId());
      setLocalimportada(value.getLocalimportada());
      setFecha(value.getFecha());
      setFechaRecepcion(value.getFechaRecepcion());
      setFechaVencimiento(value.getFechaVencimiento());
      setEstado(value.getEstado());
      setObservacion(value.getObservacion());
      setValor(value.getValor());
      setDescuentoAgenciaCompra(value.getDescuentoAgenciaCompra());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setSolicitudcompraId(value.getSolicitudcompraId());
      setEstadoBpm(value.getEstadoBpm());
      setPorcentajeDescuentosVariosCompra(value.getPorcentajeDescuentosVariosCompra());
      setPorcentajeDescuentosVariosVenta(value.getPorcentajeDescuentosVariosVenta());
      setPorcentajeDescuentoEspecial(value.getPorcentajeDescuentoEspecial());
      setRevision(value.getRevision());
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

   @Column(name = "EMPLEADO_ID")
   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   @Column(name = "USUARIO_ID")
   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   @Column(name = "BODEGA_ID")
   public java.lang.Long getBodegaId() {
      return bodegaId;
   }

   public void setBodegaId(java.lang.Long bodegaId) {
      this.bodegaId = bodegaId;
   }

   @Column(name = "LOCALIMPORTADA")
   public java.lang.String getLocalimportada() {
      return localimportada;
   }

   public void setLocalimportada(java.lang.String localimportada) {
      this.localimportada = localimportada;
   }

   @Column(name = "FECHA")
   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   @Column(name = "FECHA_RECEPCION")
   public java.sql.Date getFechaRecepcion() {
      return fechaRecepcion;
   }

   public void setFechaRecepcion(java.sql.Date fechaRecepcion) {
      this.fechaRecepcion = fechaRecepcion;
   }

   @Column(name = "FECHA_VENCIMIENTO")
   public java.sql.Date getFechaVencimiento() {
      return fechaVencimiento;
   }

   public void setFechaVencimiento(java.sql.Date fechaVencimiento) {
      this.fechaVencimiento = fechaVencimiento;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "DESCUENTO_AGENCIA_COMPRA")
   public java.math.BigDecimal getDescuentoAgenciaCompra() {
      return descuentoAgenciaCompra;
   }

   public void setDescuentoAgenciaCompra(java.math.BigDecimal descuentoAgenciaCompra) {
      this.descuentoAgenciaCompra = descuentoAgenciaCompra;
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

   @Column(name = "SOLICITUDCOMPRA_ID")
   public java.lang.Long getSolicitudcompraId() {
      return solicitudcompraId;
   }

   public void setSolicitudcompraId(java.lang.Long solicitudcompraId) {
      this.solicitudcompraId = solicitudcompraId;
   }

   @Column(name = "ESTADO_BPM")
   public java.lang.String getEstadoBpm() {
      return estadoBpm;
   }

   public void setEstadoBpm(java.lang.String estadoBpm) {
      this.estadoBpm = estadoBpm;
   }

   @Column(name = "PORCENTAJE_DESCUENTOS_VARIOS_COMPRA")
   public java.math.BigDecimal getPorcentajeDescuentosVariosCompra() {
      return porcentajeDescuentosVariosCompra;
   }

   public void setPorcentajeDescuentosVariosCompra(java.math.BigDecimal porcentajeDescuentosVariosCompra) {
      this.porcentajeDescuentosVariosCompra = porcentajeDescuentosVariosCompra;
   }

   @Column(name = "PORCENTAJE_DESCUENTOS_VARIOS_VENTA")
   public java.math.BigDecimal getPorcentajeDescuentosVariosVenta() {
      return porcentajeDescuentosVariosVenta;
   }

   public void setPorcentajeDescuentosVariosVenta(java.math.BigDecimal porcentajeDescuentosVariosVenta) {
      this.porcentajeDescuentosVariosVenta = porcentajeDescuentosVariosVenta;
   }

   @Column(name = "PORCENTAJE_DESCUENTO_ESPECIAL")
   public java.math.BigDecimal getPorcentajeDescuentoEspecial() {
      return porcentajeDescuentoEspecial;
   }

   public void setPorcentajeDescuentoEspecial(java.math.BigDecimal porcentajeDescuentoEspecial) {
      this.porcentajeDescuentoEspecial = porcentajeDescuentoEspecial;
   }

   @Column(name = "REVISION")
   public java.lang.String getRevision() {
      return revision;
   }

   public void setRevision(java.lang.String revision) {
      this.revision = revision;
   }
	public String toString() {
		return ToStringer.toString((OrdenCompraIf)this);
	}
}
