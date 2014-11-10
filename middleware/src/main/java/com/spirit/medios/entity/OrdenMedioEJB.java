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

@Table(name = "ORDEN_MEDIO")
@Entity
public class OrdenMedioEJB implements Serializable, OrdenMedioIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.Long clienteOficinaId;
   private java.lang.Long planMedioId;
   private java.lang.Long proveedorId;
   private java.lang.Long usuarioId;
   private java.lang.Long oficinaId;
   private java.lang.Long empleadoId;
   private java.sql.Timestamp fechaCreacion;
   private java.lang.String estado;
   private java.math.BigDecimal valorTotal;
   private java.lang.Long productoProveedorId;
   private java.sql.Timestamp fechaOrden;
   private java.math.BigDecimal valorDescuento;
   private java.math.BigDecimal valorIva;
   private java.math.BigDecimal valorSubtotal;
   private java.math.BigDecimal porcentajeDescuentoVenta;
   private java.math.BigDecimal porcentajeComisionAgencia;
   private java.math.BigDecimal valorDescuentoVenta;
   private java.math.BigDecimal valorComisionAgencia;
   private java.math.BigDecimal porcentajeCanje;
   private java.lang.Long productoClienteId;
   private java.lang.String ordenMedioTipo;
   private java.lang.String observacion;
   private java.lang.Long campanaProductoVersionId;
   private java.math.BigDecimal porcentajeNegociacionComision;
   private java.lang.String comisionPura;
   private java.math.BigDecimal porcentajeBonificacionCompra;
   private java.math.BigDecimal porcentajeBonificacionVenta;
   private java.lang.Integer numeroOrdenAgrupacion;
   private java.lang.String revision;
   private java.math.BigDecimal porcentajeComisionAdicional;

   public OrdenMedioEJB() {
   }

   public OrdenMedioEJB(com.spirit.medios.entity.OrdenMedioIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setClienteOficinaId(value.getClienteOficinaId());
      setPlanMedioId(value.getPlanMedioId());
      setProveedorId(value.getProveedorId());
      setUsuarioId(value.getUsuarioId());
      setOficinaId(value.getOficinaId());
      setEmpleadoId(value.getEmpleadoId());
      setFechaCreacion(value.getFechaCreacion());
      setEstado(value.getEstado());
      setValorTotal(value.getValorTotal());
      setProductoProveedorId(value.getProductoProveedorId());
      setFechaOrden(value.getFechaOrden());
      setValorDescuento(value.getValorDescuento());
      setValorIva(value.getValorIva());
      setValorSubtotal(value.getValorSubtotal());
      setPorcentajeDescuentoVenta(value.getPorcentajeDescuentoVenta());
      setPorcentajeComisionAgencia(value.getPorcentajeComisionAgencia());
      setValorDescuentoVenta(value.getValorDescuentoVenta());
      setValorComisionAgencia(value.getValorComisionAgencia());
      setPorcentajeCanje(value.getPorcentajeCanje());
      setProductoClienteId(value.getProductoClienteId());
      setOrdenMedioTipo(value.getOrdenMedioTipo());
      setObservacion(value.getObservacion());
      setCampanaProductoVersionId(value.getCampanaProductoVersionId());
      setPorcentajeNegociacionComision(value.getPorcentajeNegociacionComision());
      setComisionPura(value.getComisionPura());
      setPorcentajeBonificacionCompra(value.getPorcentajeBonificacionCompra());
      setPorcentajeBonificacionVenta(value.getPorcentajeBonificacionVenta());
      setNumeroOrdenAgrupacion(value.getNumeroOrdenAgrupacion());
      setRevision(value.getRevision());
      setPorcentajeComisionAdicional(value.getPorcentajeComisionAdicional());
   }

   public java.lang.Long create(com.spirit.medios.entity.OrdenMedioIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setClienteOficinaId(value.getClienteOficinaId());
      setPlanMedioId(value.getPlanMedioId());
      setProveedorId(value.getProveedorId());
      setUsuarioId(value.getUsuarioId());
      setOficinaId(value.getOficinaId());
      setEmpleadoId(value.getEmpleadoId());
      setFechaCreacion(value.getFechaCreacion());
      setEstado(value.getEstado());
      setValorTotal(value.getValorTotal());
      setProductoProveedorId(value.getProductoProveedorId());
      setFechaOrden(value.getFechaOrden());
      setValorDescuento(value.getValorDescuento());
      setValorIva(value.getValorIva());
      setValorSubtotal(value.getValorSubtotal());
      setPorcentajeDescuentoVenta(value.getPorcentajeDescuentoVenta());
      setPorcentajeComisionAgencia(value.getPorcentajeComisionAgencia());
      setValorDescuentoVenta(value.getValorDescuentoVenta());
      setValorComisionAgencia(value.getValorComisionAgencia());
      setPorcentajeCanje(value.getPorcentajeCanje());
      setProductoClienteId(value.getProductoClienteId());
      setOrdenMedioTipo(value.getOrdenMedioTipo());
      setObservacion(value.getObservacion());
      setCampanaProductoVersionId(value.getCampanaProductoVersionId());
      setPorcentajeNegociacionComision(value.getPorcentajeNegociacionComision());
      setComisionPura(value.getComisionPura());
      setPorcentajeBonificacionCompra(value.getPorcentajeBonificacionCompra());
      setPorcentajeBonificacionVenta(value.getPorcentajeBonificacionVenta());
      setNumeroOrdenAgrupacion(value.getNumeroOrdenAgrupacion());
      setRevision(value.getRevision());
      setPorcentajeComisionAdicional(value.getPorcentajeComisionAdicional());
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

   @Column(name = "CLIENTE_OFICINA_ID")
   public java.lang.Long getClienteOficinaId() {
      return clienteOficinaId;
   }

   public void setClienteOficinaId(java.lang.Long clienteOficinaId) {
      this.clienteOficinaId = clienteOficinaId;
   }

   @Column(name = "PLAN_MEDIO_ID")
   public java.lang.Long getPlanMedioId() {
      return planMedioId;
   }

   public void setPlanMedioId(java.lang.Long planMedioId) {
      this.planMedioId = planMedioId;
   }

   @Column(name = "PROVEEDOR_ID")
   public java.lang.Long getProveedorId() {
      return proveedorId;
   }

   public void setProveedorId(java.lang.Long proveedorId) {
      this.proveedorId = proveedorId;
   }

   @Column(name = "USUARIO_ID")
   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   @Column(name = "OFICINA_ID")
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   @Column(name = "EMPLEADO_ID")
   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "VALOR_TOTAL")
   public java.math.BigDecimal getValorTotal() {
      return valorTotal;
   }

   public void setValorTotal(java.math.BigDecimal valorTotal) {
      this.valorTotal = valorTotal;
   }

   @Column(name = "PRODUCTO_PROVEEDOR_ID")
   public java.lang.Long getProductoProveedorId() {
      return productoProveedorId;
   }

   public void setProductoProveedorId(java.lang.Long productoProveedorId) {
      this.productoProveedorId = productoProveedorId;
   }

   @Column(name = "FECHA_ORDEN")
   public java.sql.Timestamp getFechaOrden() {
      return fechaOrden;
   }

   public void setFechaOrden(java.sql.Timestamp fechaOrden) {
      this.fechaOrden = fechaOrden;
   }

   @Column(name = "VALOR_DESCUENTO")
   public java.math.BigDecimal getValorDescuento() {
      return valorDescuento;
   }

   public void setValorDescuento(java.math.BigDecimal valorDescuento) {
      this.valorDescuento = valorDescuento;
   }

   @Column(name = "VALOR_IVA")
   public java.math.BigDecimal getValorIva() {
      return valorIva;
   }

   public void setValorIva(java.math.BigDecimal valorIva) {
      this.valorIva = valorIva;
   }

   @Column(name = "VALOR_SUBTOTAL")
   public java.math.BigDecimal getValorSubtotal() {
      return valorSubtotal;
   }

   public void setValorSubtotal(java.math.BigDecimal valorSubtotal) {
      this.valorSubtotal = valorSubtotal;
   }

   @Column(name = "PORCENTAJE_DESCUENTO_VENTA")
   public java.math.BigDecimal getPorcentajeDescuentoVenta() {
      return porcentajeDescuentoVenta;
   }

   public void setPorcentajeDescuentoVenta(java.math.BigDecimal porcentajeDescuentoVenta) {
      this.porcentajeDescuentoVenta = porcentajeDescuentoVenta;
   }

   @Column(name = "PORCENTAJE_COMISION_AGENCIA")
   public java.math.BigDecimal getPorcentajeComisionAgencia() {
      return porcentajeComisionAgencia;
   }

   public void setPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) {
      this.porcentajeComisionAgencia = porcentajeComisionAgencia;
   }

   @Column(name = "VALOR_DESCUENTO_VENTA")
   public java.math.BigDecimal getValorDescuentoVenta() {
      return valorDescuentoVenta;
   }

   public void setValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta) {
      this.valorDescuentoVenta = valorDescuentoVenta;
   }

   @Column(name = "VALOR_COMISION_AGENCIA")
   public java.math.BigDecimal getValorComisionAgencia() {
      return valorComisionAgencia;
   }

   public void setValorComisionAgencia(java.math.BigDecimal valorComisionAgencia) {
      this.valorComisionAgencia = valorComisionAgencia;
   }

   @Column(name = "PORCENTAJE_CANJE")
   public java.math.BigDecimal getPorcentajeCanje() {
      return porcentajeCanje;
   }

   public void setPorcentajeCanje(java.math.BigDecimal porcentajeCanje) {
      this.porcentajeCanje = porcentajeCanje;
   }

   @Column(name = "PRODUCTO_CLIENTE_ID")
   public java.lang.Long getProductoClienteId() {
      return productoClienteId;
   }

   public void setProductoClienteId(java.lang.Long productoClienteId) {
      this.productoClienteId = productoClienteId;
   }

   @Column(name = "ORDEN_MEDIO_TIPO")
   public java.lang.String getOrdenMedioTipo() {
      return ordenMedioTipo;
   }

   public void setOrdenMedioTipo(java.lang.String ordenMedioTipo) {
      this.ordenMedioTipo = ordenMedioTipo;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "CAMPANA_PRODUCTO_VERSION_ID")
   public java.lang.Long getCampanaProductoVersionId() {
      return campanaProductoVersionId;
   }

   public void setCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) {
      this.campanaProductoVersionId = campanaProductoVersionId;
   }

   @Column(name = "PORCENTAJE_NEGOCIACION_COMISION")
   public java.math.BigDecimal getPorcentajeNegociacionComision() {
      return porcentajeNegociacionComision;
   }

   public void setPorcentajeNegociacionComision(java.math.BigDecimal porcentajeNegociacionComision) {
      this.porcentajeNegociacionComision = porcentajeNegociacionComision;
   }

   @Column(name = "COMISION_PURA")
   public java.lang.String getComisionPura() {
      return comisionPura;
   }

   public void setComisionPura(java.lang.String comisionPura) {
      this.comisionPura = comisionPura;
   }

   @Column(name = "PORCENTAJE_BONIFICACION_COMPRA")
   public java.math.BigDecimal getPorcentajeBonificacionCompra() {
      return porcentajeBonificacionCompra;
   }

   public void setPorcentajeBonificacionCompra(java.math.BigDecimal porcentajeBonificacionCompra) {
      this.porcentajeBonificacionCompra = porcentajeBonificacionCompra;
   }

   @Column(name = "PORCENTAJE_BONIFICACION_VENTA")
   public java.math.BigDecimal getPorcentajeBonificacionVenta() {
      return porcentajeBonificacionVenta;
   }

   public void setPorcentajeBonificacionVenta(java.math.BigDecimal porcentajeBonificacionVenta) {
      this.porcentajeBonificacionVenta = porcentajeBonificacionVenta;
   }

   @Column(name = "NUMERO_ORDEN_AGRUPACION")
   public java.lang.Integer getNumeroOrdenAgrupacion() {
      return numeroOrdenAgrupacion;
   }

   public void setNumeroOrdenAgrupacion(java.lang.Integer numeroOrdenAgrupacion) {
      this.numeroOrdenAgrupacion = numeroOrdenAgrupacion;
   }

   @Column(name = "REVISION")
   public java.lang.String getRevision() {
      return revision;
   }

   public void setRevision(java.lang.String revision) {
      this.revision = revision;
   }

   @Column(name = "PORCENTAJE_COMISION_ADICIONAL")
   public java.math.BigDecimal getPorcentajeComisionAdicional() {
      return porcentajeComisionAdicional;
   }

   public void setPorcentajeComisionAdicional(java.math.BigDecimal porcentajeComisionAdicional) {
      this.porcentajeComisionAdicional = porcentajeComisionAdicional;
   }
	public String toString() {
		return ToStringer.toString((OrdenMedioIf)this);
	}
}
