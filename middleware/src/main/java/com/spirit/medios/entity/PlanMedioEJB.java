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

@Table(name = "PLAN_MEDIO")
@Entity
public class PlanMedioEJB implements Serializable, PlanMedioIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String concepto;
   private java.lang.Long ordenTrabajoDetalleId;
   private java.lang.Long grupoObjetivoId;
   private java.lang.Long tipoProveedorId;
   private java.lang.Long usuarioId;
   private java.lang.Integer modificacion;
   private java.sql.Timestamp fechaCreacion;
   private java.sql.Timestamp fechaInicio;
   private java.sql.Timestamp fechaFin;
   private java.math.BigDecimal valorSubtotal;
   private java.math.BigDecimal valorDescuento;
   private java.lang.Long planMedioOrigenId;
   private java.math.BigDecimal ciudad1;
   private java.math.BigDecimal ciudad2;
   private java.math.BigDecimal ciudad3;
   private java.lang.String estado;
   private java.lang.String cobertura;
   private java.lang.String consideraciones;
   private java.math.BigDecimal valorIva;
   private java.lang.Long usuarioActualizacionId;
   private java.sql.Timestamp fechaActualizacion;
   private java.math.BigDecimal porcentajeDescuento;
   private java.math.BigDecimal valorTotal;
   private java.math.BigDecimal valorDescuentoVenta;
   private java.math.BigDecimal valorComisionAgencia;
   private java.lang.Long planMedioHermanoId;
   private java.lang.String ordenMedioTipo;
   private java.lang.String planMedioTipo;
   private java.lang.String autorizacionSap;
   private java.sql.Timestamp fechaAprobacion;
   private java.lang.String revision;
   private java.lang.String prepago;

   public PlanMedioEJB() {
   }

   public PlanMedioEJB(com.spirit.medios.entity.PlanMedioIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setConcepto(value.getConcepto());
      setOrdenTrabajoDetalleId(value.getOrdenTrabajoDetalleId());
      setGrupoObjetivoId(value.getGrupoObjetivoId());
      setTipoProveedorId(value.getTipoProveedorId());
      setUsuarioId(value.getUsuarioId());
      setModificacion(value.getModificacion());
      setFechaCreacion(value.getFechaCreacion());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setValorSubtotal(value.getValorSubtotal());
      setValorDescuento(value.getValorDescuento());
      setPlanMedioOrigenId(value.getPlanMedioOrigenId());
      setCiudad1(value.getCiudad1());
      setCiudad2(value.getCiudad2());
      setCiudad3(value.getCiudad3());
      setEstado(value.getEstado());
      setCobertura(value.getCobertura());
      setConsideraciones(value.getConsideraciones());
      setValorIva(value.getValorIva());
      setUsuarioActualizacionId(value.getUsuarioActualizacionId());
      setFechaActualizacion(value.getFechaActualizacion());
      setPorcentajeDescuento(value.getPorcentajeDescuento());
      setValorTotal(value.getValorTotal());
      setValorDescuentoVenta(value.getValorDescuentoVenta());
      setValorComisionAgencia(value.getValorComisionAgencia());
      setPlanMedioHermanoId(value.getPlanMedioHermanoId());
      setOrdenMedioTipo(value.getOrdenMedioTipo());
      setPlanMedioTipo(value.getPlanMedioTipo());
      setAutorizacionSap(value.getAutorizacionSap());
      setFechaAprobacion(value.getFechaAprobacion());
      setRevision(value.getRevision());
      setPrepago(value.getPrepago());
   }

   public java.lang.Long create(com.spirit.medios.entity.PlanMedioIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setConcepto(value.getConcepto());
      setOrdenTrabajoDetalleId(value.getOrdenTrabajoDetalleId());
      setGrupoObjetivoId(value.getGrupoObjetivoId());
      setTipoProveedorId(value.getTipoProveedorId());
      setUsuarioId(value.getUsuarioId());
      setModificacion(value.getModificacion());
      setFechaCreacion(value.getFechaCreacion());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setValorSubtotal(value.getValorSubtotal());
      setValorDescuento(value.getValorDescuento());
      setPlanMedioOrigenId(value.getPlanMedioOrigenId());
      setCiudad1(value.getCiudad1());
      setCiudad2(value.getCiudad2());
      setCiudad3(value.getCiudad3());
      setEstado(value.getEstado());
      setCobertura(value.getCobertura());
      setConsideraciones(value.getConsideraciones());
      setValorIva(value.getValorIva());
      setUsuarioActualizacionId(value.getUsuarioActualizacionId());
      setFechaActualizacion(value.getFechaActualizacion());
      setPorcentajeDescuento(value.getPorcentajeDescuento());
      setValorTotal(value.getValorTotal());
      setValorDescuentoVenta(value.getValorDescuentoVenta());
      setValorComisionAgencia(value.getValorComisionAgencia());
      setPlanMedioHermanoId(value.getPlanMedioHermanoId());
      setOrdenMedioTipo(value.getOrdenMedioTipo());
      setPlanMedioTipo(value.getPlanMedioTipo());
      setAutorizacionSap(value.getAutorizacionSap());
      setFechaAprobacion(value.getFechaAprobacion());
      setRevision(value.getRevision());
      setPrepago(value.getPrepago());
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

   @Column(name = "CONCEPTO")
   public java.lang.String getConcepto() {
      return concepto;
   }

   public void setConcepto(java.lang.String concepto) {
      this.concepto = concepto;
   }

   @Column(name = "ORDEN_TRABAJO_DETALLE_ID")
   public java.lang.Long getOrdenTrabajoDetalleId() {
      return ordenTrabajoDetalleId;
   }

   public void setOrdenTrabajoDetalleId(java.lang.Long ordenTrabajoDetalleId) {
      this.ordenTrabajoDetalleId = ordenTrabajoDetalleId;
   }

   @Column(name = "GRUPO_OBJETIVO_ID")
   public java.lang.Long getGrupoObjetivoId() {
      return grupoObjetivoId;
   }

   public void setGrupoObjetivoId(java.lang.Long grupoObjetivoId) {
      this.grupoObjetivoId = grupoObjetivoId;
   }

   @Column(name = "TIPO_PROVEEDOR_ID")
   public java.lang.Long getTipoProveedorId() {
      return tipoProveedorId;
   }

   public void setTipoProveedorId(java.lang.Long tipoProveedorId) {
      this.tipoProveedorId = tipoProveedorId;
   }

   @Column(name = "USUARIO_ID")
   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   @Column(name = "MODIFICACION")
   public java.lang.Integer getModificacion() {
      return modificacion;
   }

   public void setModificacion(java.lang.Integer modificacion) {
      this.modificacion = modificacion;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "FECHA_INICIO")
   public java.sql.Timestamp getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Timestamp fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   @Column(name = "FECHA_FIN")
   public java.sql.Timestamp getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Timestamp fechaFin) {
      this.fechaFin = fechaFin;
   }

   @Column(name = "VALOR_SUBTOTAL")
   public java.math.BigDecimal getValorSubtotal() {
      return valorSubtotal;
   }

   public void setValorSubtotal(java.math.BigDecimal valorSubtotal) {
      this.valorSubtotal = valorSubtotal;
   }

   @Column(name = "VALOR_DESCUENTO")
   public java.math.BigDecimal getValorDescuento() {
      return valorDescuento;
   }

   public void setValorDescuento(java.math.BigDecimal valorDescuento) {
      this.valorDescuento = valorDescuento;
   }

   @Column(name = "PLAN_MEDIO_ORIGEN_ID")
   public java.lang.Long getPlanMedioOrigenId() {
      return planMedioOrigenId;
   }

   public void setPlanMedioOrigenId(java.lang.Long planMedioOrigenId) {
      this.planMedioOrigenId = planMedioOrigenId;
   }

   @Column(name = "CIUDAD1")
   public java.math.BigDecimal getCiudad1() {
      return ciudad1;
   }

   public void setCiudad1(java.math.BigDecimal ciudad1) {
      this.ciudad1 = ciudad1;
   }

   @Column(name = "CIUDAD2")
   public java.math.BigDecimal getCiudad2() {
      return ciudad2;
   }

   public void setCiudad2(java.math.BigDecimal ciudad2) {
      this.ciudad2 = ciudad2;
   }

   @Column(name = "CIUDAD3")
   public java.math.BigDecimal getCiudad3() {
      return ciudad3;
   }

   public void setCiudad3(java.math.BigDecimal ciudad3) {
      this.ciudad3 = ciudad3;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "COBERTURA")
   public java.lang.String getCobertura() {
      return cobertura;
   }

   public void setCobertura(java.lang.String cobertura) {
      this.cobertura = cobertura;
   }

   @Column(name = "CONSIDERACIONES")
   public java.lang.String getConsideraciones() {
      return consideraciones;
   }

   public void setConsideraciones(java.lang.String consideraciones) {
      this.consideraciones = consideraciones;
   }

   @Column(name = "VALOR_IVA")
   public java.math.BigDecimal getValorIva() {
      return valorIva;
   }

   public void setValorIva(java.math.BigDecimal valorIva) {
      this.valorIva = valorIva;
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

   @Column(name = "PORCENTAJE_DESCUENTO")
   public java.math.BigDecimal getPorcentajeDescuento() {
      return porcentajeDescuento;
   }

   public void setPorcentajeDescuento(java.math.BigDecimal porcentajeDescuento) {
      this.porcentajeDescuento = porcentajeDescuento;
   }

   @Column(name = "VALOR_TOTAL")
   public java.math.BigDecimal getValorTotal() {
      return valorTotal;
   }

   public void setValorTotal(java.math.BigDecimal valorTotal) {
      this.valorTotal = valorTotal;
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

   @Column(name = "PLAN_MEDIO_HERMANO_ID")
   public java.lang.Long getPlanMedioHermanoId() {
      return planMedioHermanoId;
   }

   public void setPlanMedioHermanoId(java.lang.Long planMedioHermanoId) {
      this.planMedioHermanoId = planMedioHermanoId;
   }

   @Column(name = "ORDEN_MEDIO_TIPO")
   public java.lang.String getOrdenMedioTipo() {
      return ordenMedioTipo;
   }

   public void setOrdenMedioTipo(java.lang.String ordenMedioTipo) {
      this.ordenMedioTipo = ordenMedioTipo;
   }

   @Column(name = "PLAN_MEDIO_TIPO")
   public java.lang.String getPlanMedioTipo() {
      return planMedioTipo;
   }

   public void setPlanMedioTipo(java.lang.String planMedioTipo) {
      this.planMedioTipo = planMedioTipo;
   }

   @Column(name = "AUTORIZACION_SAP")
   public java.lang.String getAutorizacionSap() {
      return autorizacionSap;
   }

   public void setAutorizacionSap(java.lang.String autorizacionSap) {
      this.autorizacionSap = autorizacionSap;
   }

   @Column(name = "FECHA_APROBACION")
   public java.sql.Timestamp getFechaAprobacion() {
      return fechaAprobacion;
   }

   public void setFechaAprobacion(java.sql.Timestamp fechaAprobacion) {
      this.fechaAprobacion = fechaAprobacion;
   }

   @Column(name = "REVISION")
   public java.lang.String getRevision() {
      return revision;
   }

   public void setRevision(java.lang.String revision) {
      this.revision = revision;
   }

   @Column(name = "PREPAGO")
   public java.lang.String getPrepago() {
      return prepago;
   }

   public void setPrepago(java.lang.String prepago) {
      this.prepago = prepago;
   }
	public String toString() {
		return ToStringer.toString((PlanMedioIf)this);
	}
}
