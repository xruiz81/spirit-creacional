package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PlanMedioData implements PlanMedioIf, Serializable    {


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

   private java.lang.String concepto;

   public java.lang.String getConcepto() {
      return concepto;
   }

   public void setConcepto(java.lang.String concepto) {
      this.concepto = concepto;
   }

   private java.lang.Long ordenTrabajoDetalleId;

   public java.lang.Long getOrdenTrabajoDetalleId() {
      return ordenTrabajoDetalleId;
   }

   public void setOrdenTrabajoDetalleId(java.lang.Long ordenTrabajoDetalleId) {
      this.ordenTrabajoDetalleId = ordenTrabajoDetalleId;
   }

   private java.lang.Long grupoObjetivoId;

   public java.lang.Long getGrupoObjetivoId() {
      return grupoObjetivoId;
   }

   public void setGrupoObjetivoId(java.lang.Long grupoObjetivoId) {
      this.grupoObjetivoId = grupoObjetivoId;
   }

   private java.lang.Long tipoProveedorId;

   public java.lang.Long getTipoProveedorId() {
      return tipoProveedorId;
   }

   public void setTipoProveedorId(java.lang.Long tipoProveedorId) {
      this.tipoProveedorId = tipoProveedorId;
   }

   private java.lang.Long usuarioId;

   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   private java.lang.Integer modificacion;

   public java.lang.Integer getModificacion() {
      return modificacion;
   }

   public void setModificacion(java.lang.Integer modificacion) {
      this.modificacion = modificacion;
   }

   private java.sql.Timestamp fechaCreacion;

   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.sql.Timestamp fechaInicio;

   public java.sql.Timestamp getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Timestamp fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   private java.sql.Timestamp fechaFin;

   public java.sql.Timestamp getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Timestamp fechaFin) {
      this.fechaFin = fechaFin;
   }

   private java.math.BigDecimal valorSubtotal;

   public java.math.BigDecimal getValorSubtotal() {
      return valorSubtotal;
   }

   public void setValorSubtotal(java.math.BigDecimal valorSubtotal) {
      this.valorSubtotal = valorSubtotal;
   }

   private java.math.BigDecimal valorDescuento;

   public java.math.BigDecimal getValorDescuento() {
      return valorDescuento;
   }

   public void setValorDescuento(java.math.BigDecimal valorDescuento) {
      this.valorDescuento = valorDescuento;
   }

   private java.lang.Long planMedioOrigenId;

   public java.lang.Long getPlanMedioOrigenId() {
      return planMedioOrigenId;
   }

   public void setPlanMedioOrigenId(java.lang.Long planMedioOrigenId) {
      this.planMedioOrigenId = planMedioOrigenId;
   }

   private java.math.BigDecimal ciudad1;

   public java.math.BigDecimal getCiudad1() {
      return ciudad1;
   }

   public void setCiudad1(java.math.BigDecimal ciudad1) {
      this.ciudad1 = ciudad1;
   }

   private java.math.BigDecimal ciudad2;

   public java.math.BigDecimal getCiudad2() {
      return ciudad2;
   }

   public void setCiudad2(java.math.BigDecimal ciudad2) {
      this.ciudad2 = ciudad2;
   }

   private java.math.BigDecimal ciudad3;

   public java.math.BigDecimal getCiudad3() {
      return ciudad3;
   }

   public void setCiudad3(java.math.BigDecimal ciudad3) {
      this.ciudad3 = ciudad3;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String cobertura;

   public java.lang.String getCobertura() {
      return cobertura;
   }

   public void setCobertura(java.lang.String cobertura) {
      this.cobertura = cobertura;
   }

   private java.lang.String consideraciones;

   public java.lang.String getConsideraciones() {
      return consideraciones;
   }

   public void setConsideraciones(java.lang.String consideraciones) {
      this.consideraciones = consideraciones;
   }

   private java.math.BigDecimal valorIva;

   public java.math.BigDecimal getValorIva() {
      return valorIva;
   }

   public void setValorIva(java.math.BigDecimal valorIva) {
      this.valorIva = valorIva;
   }

   private java.lang.Long usuarioActualizacionId;

   public java.lang.Long getUsuarioActualizacionId() {
      return usuarioActualizacionId;
   }

   public void setUsuarioActualizacionId(java.lang.Long usuarioActualizacionId) {
      this.usuarioActualizacionId = usuarioActualizacionId;
   }

   private java.sql.Timestamp fechaActualizacion;

   public java.sql.Timestamp getFechaActualizacion() {
      return fechaActualizacion;
   }

   public void setFechaActualizacion(java.sql.Timestamp fechaActualizacion) {
      this.fechaActualizacion = fechaActualizacion;
   }

   private java.math.BigDecimal porcentajeDescuento;

   public java.math.BigDecimal getPorcentajeDescuento() {
      return porcentajeDescuento;
   }

   public void setPorcentajeDescuento(java.math.BigDecimal porcentajeDescuento) {
      this.porcentajeDescuento = porcentajeDescuento;
   }

   private java.math.BigDecimal valorTotal;

   public java.math.BigDecimal getValorTotal() {
      return valorTotal;
   }

   public void setValorTotal(java.math.BigDecimal valorTotal) {
      this.valorTotal = valorTotal;
   }

   private java.math.BigDecimal valorDescuentoVenta;

   public java.math.BigDecimal getValorDescuentoVenta() {
      return valorDescuentoVenta;
   }

   public void setValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta) {
      this.valorDescuentoVenta = valorDescuentoVenta;
   }

   private java.math.BigDecimal valorComisionAgencia;

   public java.math.BigDecimal getValorComisionAgencia() {
      return valorComisionAgencia;
   }

   public void setValorComisionAgencia(java.math.BigDecimal valorComisionAgencia) {
      this.valorComisionAgencia = valorComisionAgencia;
   }

   private java.lang.Long planMedioHermanoId;

   public java.lang.Long getPlanMedioHermanoId() {
      return planMedioHermanoId;
   }

   public void setPlanMedioHermanoId(java.lang.Long planMedioHermanoId) {
      this.planMedioHermanoId = planMedioHermanoId;
   }

   private java.lang.String ordenMedioTipo;

   public java.lang.String getOrdenMedioTipo() {
      return ordenMedioTipo;
   }

   public void setOrdenMedioTipo(java.lang.String ordenMedioTipo) {
      this.ordenMedioTipo = ordenMedioTipo;
   }

   private java.lang.String planMedioTipo;

   public java.lang.String getPlanMedioTipo() {
      return planMedioTipo;
   }

   public void setPlanMedioTipo(java.lang.String planMedioTipo) {
      this.planMedioTipo = planMedioTipo;
   }

   private java.lang.String autorizacionSap;

   public java.lang.String getAutorizacionSap() {
      return autorizacionSap;
   }

   public void setAutorizacionSap(java.lang.String autorizacionSap) {
      this.autorizacionSap = autorizacionSap;
   }

   private java.sql.Timestamp fechaAprobacion;

   public java.sql.Timestamp getFechaAprobacion() {
      return fechaAprobacion;
   }

   public void setFechaAprobacion(java.sql.Timestamp fechaAprobacion) {
      this.fechaAprobacion = fechaAprobacion;
   }

   private java.lang.String revision;

   public java.lang.String getRevision() {
      return revision;
   }

   public void setRevision(java.lang.String revision) {
      this.revision = revision;
   }

   private java.lang.String prepago;

   public java.lang.String getPrepago() {
      return prepago;
   }

   public void setPrepago(java.lang.String prepago) {
      this.prepago = prepago;
   }
   public PlanMedioData() {
   }

   public PlanMedioData(com.spirit.medios.entity.PlanMedioIf value) {
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
		return ToStringer.toString((PlanMedioIf)this);
	}
}
