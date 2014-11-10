package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PlanMedioDetalleData implements PlanMedioDetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long planMedioMesId;

   public java.lang.Long getPlanMedioMesId() {
      return planMedioMesId;
   }

   public void setPlanMedioMesId(java.lang.Long planMedioMesId) {
      this.planMedioMesId = planMedioMesId;
   }

   private java.lang.String programa;

   public java.lang.String getPrograma() {
      return programa;
   }

   public void setPrograma(java.lang.String programa) {
      this.programa = programa;
   }

   private java.lang.Long generoProgramaId;

   public java.lang.Long getGeneroProgramaId() {
      return generoProgramaId;
   }

   public void setGeneroProgramaId(java.lang.Long generoProgramaId) {
      this.generoProgramaId = generoProgramaId;
   }

   private java.lang.Long comercialId;

   public java.lang.Long getComercialId() {
      return comercialId;
   }

   public void setComercialId(java.lang.Long comercialId) {
      this.comercialId = comercialId;
   }

   private java.lang.Long proveedorId;

   public java.lang.Long getProveedorId() {
      return proveedorId;
   }

   public void setProveedorId(java.lang.Long proveedorId) {
      this.proveedorId = proveedorId;
   }

   private java.lang.String horaInicio;

   public java.lang.String getHoraInicio() {
      return horaInicio;
   }

   public void setHoraInicio(java.lang.String horaInicio) {
      this.horaInicio = horaInicio;
   }

   private java.math.BigDecimal raiting1;

   public java.math.BigDecimal getRaiting1() {
      return raiting1;
   }

   public void setRaiting1(java.math.BigDecimal raiting1) {
      this.raiting1 = raiting1;
   }

   private java.math.BigDecimal raiting2;

   public java.math.BigDecimal getRaiting2() {
      return raiting2;
   }

   public void setRaiting2(java.math.BigDecimal raiting2) {
      this.raiting2 = raiting2;
   }

   private java.math.BigDecimal raitingPonderado;

   public java.math.BigDecimal getRaitingPonderado() {
      return raitingPonderado;
   }

   public void setRaitingPonderado(java.math.BigDecimal raitingPonderado) {
      this.raitingPonderado = raitingPonderado;
   }

   private java.math.BigDecimal audiencia;

   public java.math.BigDecimal getAudiencia() {
      return audiencia;
   }

   public void setAudiencia(java.math.BigDecimal audiencia) {
      this.audiencia = audiencia;
   }

   private java.math.BigDecimal valorTarifa;

   public java.math.BigDecimal getValorTarifa() {
      return valorTarifa;
   }

   public void setValorTarifa(java.math.BigDecimal valorTarifa) {
      this.valorTarifa = valorTarifa;
   }

   private java.math.BigDecimal valorTotal;

   public java.math.BigDecimal getValorTotal() {
      return valorTotal;
   }

   public void setValorTotal(java.math.BigDecimal valorTotal) {
      this.valorTotal = valorTotal;
   }

   private java.lang.String seccion;

   public java.lang.String getSeccion() {
      return seccion;
   }

   public void setSeccion(java.lang.String seccion) {
      this.seccion = seccion;
   }

   private java.lang.String ubicacion;

   public java.lang.String getUbicacion() {
      return ubicacion;
   }

   public void setUbicacion(java.lang.String ubicacion) {
      this.ubicacion = ubicacion;
   }

   private java.lang.String tamanio;

   public java.lang.String getTamanio() {
      return tamanio;
   }

   public void setTamanio(java.lang.String tamanio) {
      this.tamanio = tamanio;
   }

   private java.lang.String color;

   public java.lang.String getColor() {
      return color;
   }

   public void setColor(java.lang.String color) {
      this.color = color;
   }

   private java.lang.Integer totalCunias;

   public java.lang.Integer getTotalCunias() {
      return totalCunias;
   }

   public void setTotalCunias(java.lang.Integer totalCunias) {
      this.totalCunias = totalCunias;
   }

   private java.lang.String comercial;

   public java.lang.String getComercial() {
      return comercial;
   }

   public void setComercial(java.lang.String comercial) {
      this.comercial = comercial;
   }

   private java.math.BigDecimal valorDescuento;

   public java.math.BigDecimal getValorDescuento() {
      return valorDescuento;
   }

   public void setValorDescuento(java.math.BigDecimal valorDescuento) {
      this.valorDescuento = valorDescuento;
   }

   private java.lang.Long productoProveedorId;

   public java.lang.Long getProductoProveedorId() {
      return productoProveedorId;
   }

   public void setProductoProveedorId(java.lang.Long productoProveedorId) {
      this.productoProveedorId = productoProveedorId;
   }

   private java.math.BigDecimal valorSubtotal;

   public java.math.BigDecimal getValorSubtotal() {
      return valorSubtotal;
   }

   public void setValorSubtotal(java.math.BigDecimal valorSubtotal) {
      this.valorSubtotal = valorSubtotal;
   }

   private java.math.BigDecimal porcentajeDescuento;

   public java.math.BigDecimal getPorcentajeDescuento() {
      return porcentajeDescuento;
   }

   public void setPorcentajeDescuento(java.math.BigDecimal porcentajeDescuento) {
      this.porcentajeDescuento = porcentajeDescuento;
   }

   private java.math.BigDecimal valorIva;

   public java.math.BigDecimal getValorIva() {
      return valorIva;
   }

   public void setValorIva(java.math.BigDecimal valorIva) {
      this.valorIva = valorIva;
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

   private java.math.BigDecimal porcentajeDescuentoVenta;

   public java.math.BigDecimal getPorcentajeDescuentoVenta() {
      return porcentajeDescuentoVenta;
   }

   public void setPorcentajeDescuentoVenta(java.math.BigDecimal porcentajeDescuentoVenta) {
      this.porcentajeDescuentoVenta = porcentajeDescuentoVenta;
   }

   private java.math.BigDecimal porcentajeComisionAgencia;

   public java.math.BigDecimal getPorcentajeComisionAgencia() {
      return porcentajeComisionAgencia;
   }

   public void setPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) {
      this.porcentajeComisionAgencia = porcentajeComisionAgencia;
   }

   private java.lang.Long productoClienteId;

   public java.lang.Long getProductoClienteId() {
      return productoClienteId;
   }

   public void setProductoClienteId(java.lang.Long productoClienteId) {
      this.productoClienteId = productoClienteId;
   }

   private java.lang.String pauta;

   public java.lang.String getPauta() {
      return pauta;
   }

   public void setPauta(java.lang.String pauta) {
      this.pauta = pauta;
   }

   private java.lang.String auspicioDescripcion;

   public java.lang.String getAuspicioDescripcion() {
      return auspicioDescripcion;
   }

   public void setAuspicioDescripcion(java.lang.String auspicioDescripcion) {
      this.auspicioDescripcion = auspicioDescripcion;
   }

   private java.lang.Long auspicioPadre;

   public java.lang.Long getAuspicioPadre() {
      return auspicioPadre;
   }

   public void setAuspicioPadre(java.lang.Long auspicioPadre) {
      this.auspicioPadre = auspicioPadre;
   }

   private java.lang.Long campanaProductoVersionId;

   public java.lang.Long getCampanaProductoVersionId() {
      return campanaProductoVersionId;
   }

   public void setCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) {
      this.campanaProductoVersionId = campanaProductoVersionId;
   }

   private java.math.BigDecimal porcentajeBonificacionCompra;

   public java.math.BigDecimal getPorcentajeBonificacionCompra() {
      return porcentajeBonificacionCompra;
   }

   public void setPorcentajeBonificacionCompra(java.math.BigDecimal porcentajeBonificacionCompra) {
      this.porcentajeBonificacionCompra = porcentajeBonificacionCompra;
   }

   private java.math.BigDecimal porcentajeBonificacionVenta;

   public java.math.BigDecimal getPorcentajeBonificacionVenta() {
      return porcentajeBonificacionVenta;
   }

   public void setPorcentajeBonificacionVenta(java.math.BigDecimal porcentajeBonificacionVenta) {
      this.porcentajeBonificacionVenta = porcentajeBonificacionVenta;
   }

   private java.lang.String negociacionComision;

   public java.lang.String getNegociacionComision() {
      return negociacionComision;
   }

   public void setNegociacionComision(java.lang.String negociacionComision) {
      this.negociacionComision = negociacionComision;
   }

   private java.lang.Integer numeroOrdenAgrupacion;

   public java.lang.Integer getNumeroOrdenAgrupacion() {
      return numeroOrdenAgrupacion;
   }

   public void setNumeroOrdenAgrupacion(java.lang.Integer numeroOrdenAgrupacion) {
      this.numeroOrdenAgrupacion = numeroOrdenAgrupacion;
   }

   private java.lang.String version;

   public java.lang.String getVersion() {
      return version;
   }

   public void setVersion(java.lang.String version) {
      this.version = version;
   }
   public PlanMedioDetalleData() {
   }

   public PlanMedioDetalleData(com.spirit.medios.entity.PlanMedioDetalleIf value) {
      setId(value.getId());
      setPlanMedioMesId(value.getPlanMedioMesId());
      setPrograma(value.getPrograma());
      setGeneroProgramaId(value.getGeneroProgramaId());
      setComercialId(value.getComercialId());
      setProveedorId(value.getProveedorId());
      setHoraInicio(value.getHoraInicio());
      setRaiting1(value.getRaiting1());
      setRaiting2(value.getRaiting2());
      setRaitingPonderado(value.getRaitingPonderado());
      setAudiencia(value.getAudiencia());
      setValorTarifa(value.getValorTarifa());
      setValorTotal(value.getValorTotal());
      setSeccion(value.getSeccion());
      setUbicacion(value.getUbicacion());
      setTamanio(value.getTamanio());
      setColor(value.getColor());
      setTotalCunias(value.getTotalCunias());
      setComercial(value.getComercial());
      setValorDescuento(value.getValorDescuento());
      setProductoProveedorId(value.getProductoProveedorId());
      setValorSubtotal(value.getValorSubtotal());
      setPorcentajeDescuento(value.getPorcentajeDescuento());
      setValorIva(value.getValorIva());
      setValorDescuentoVenta(value.getValorDescuentoVenta());
      setValorComisionAgencia(value.getValorComisionAgencia());
      setPorcentajeDescuentoVenta(value.getPorcentajeDescuentoVenta());
      setPorcentajeComisionAgencia(value.getPorcentajeComisionAgencia());
      setProductoClienteId(value.getProductoClienteId());
      setPauta(value.getPauta());
      setAuspicioDescripcion(value.getAuspicioDescripcion());
      setAuspicioPadre(value.getAuspicioPadre());
      setCampanaProductoVersionId(value.getCampanaProductoVersionId());
      setPorcentajeBonificacionCompra(value.getPorcentajeBonificacionCompra());
      setPorcentajeBonificacionVenta(value.getPorcentajeBonificacionVenta());
      setNegociacionComision(value.getNegociacionComision());
      setNumeroOrdenAgrupacion(value.getNumeroOrdenAgrupacion());
      setVersion(value.getVersion());
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
		return ToStringer.toString((PlanMedioDetalleIf)this);
	}
}
