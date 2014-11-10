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

@Table(name = "PLAN_MEDIO_DETALLE")
@Entity
public class PlanMedioDetalleEJB implements Serializable, PlanMedioDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long planMedioMesId;
   private java.lang.String programa;
   private java.lang.Long generoProgramaId;
   private java.lang.Long comercialId;
   private java.lang.Long proveedorId;
   private java.lang.String horaInicio;
   private java.math.BigDecimal raiting1;
   private java.math.BigDecimal raiting2;
   private java.math.BigDecimal raitingPonderado;
   private java.math.BigDecimal audiencia;
   private java.math.BigDecimal valorTarifa;
   private java.math.BigDecimal valorTotal;
   private java.lang.String seccion;
   private java.lang.String ubicacion;
   private java.lang.String tamanio;
   private java.lang.String color;
   private java.lang.Integer totalCunias;
   private java.lang.String comercial;
   private java.math.BigDecimal valorDescuento;
   private java.lang.Long productoProveedorId;
   private java.math.BigDecimal valorSubtotal;
   private java.math.BigDecimal porcentajeDescuento;
   private java.math.BigDecimal valorIva;
   private java.math.BigDecimal valorDescuentoVenta;
   private java.math.BigDecimal valorComisionAgencia;
   private java.math.BigDecimal porcentajeDescuentoVenta;
   private java.math.BigDecimal porcentajeComisionAgencia;
   private java.lang.Long productoClienteId;
   private java.lang.String pauta;
   private java.lang.String auspicioDescripcion;
   private java.lang.Long auspicioPadre;
   private java.lang.Long campanaProductoVersionId;
   private java.math.BigDecimal porcentajeBonificacionCompra;
   private java.math.BigDecimal porcentajeBonificacionVenta;
   private java.lang.String negociacionComision;
   private java.lang.Integer numeroOrdenAgrupacion;
   private java.lang.String version;

   public PlanMedioDetalleEJB() {
   }

   public PlanMedioDetalleEJB(com.spirit.medios.entity.PlanMedioDetalleIf value) {
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

   public java.lang.Long create(com.spirit.medios.entity.PlanMedioDetalleIf value) {
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

   @Column(name = "PLAN_MEDIO_MES_ID")
   public java.lang.Long getPlanMedioMesId() {
      return planMedioMesId;
   }

   public void setPlanMedioMesId(java.lang.Long planMedioMesId) {
      this.planMedioMesId = planMedioMesId;
   }

   @Column(name = "PROGRAMA")
   public java.lang.String getPrograma() {
      return programa;
   }

   public void setPrograma(java.lang.String programa) {
      this.programa = programa;
   }

   @Column(name = "GENERO_PROGRAMA_ID")
   public java.lang.Long getGeneroProgramaId() {
      return generoProgramaId;
   }

   public void setGeneroProgramaId(java.lang.Long generoProgramaId) {
      this.generoProgramaId = generoProgramaId;
   }

   @Column(name = "COMERCIAL_ID")
   public java.lang.Long getComercialId() {
      return comercialId;
   }

   public void setComercialId(java.lang.Long comercialId) {
      this.comercialId = comercialId;
   }

   @Column(name = "PROVEEDOR_ID")
   public java.lang.Long getProveedorId() {
      return proveedorId;
   }

   public void setProveedorId(java.lang.Long proveedorId) {
      this.proveedorId = proveedorId;
   }

   @Column(name = "HORA_INICIO")
   public java.lang.String getHoraInicio() {
      return horaInicio;
   }

   public void setHoraInicio(java.lang.String horaInicio) {
      this.horaInicio = horaInicio;
   }

   @Column(name = "RAITING1")
   public java.math.BigDecimal getRaiting1() {
      return raiting1;
   }

   public void setRaiting1(java.math.BigDecimal raiting1) {
      this.raiting1 = raiting1;
   }

   @Column(name = "RAITING2")
   public java.math.BigDecimal getRaiting2() {
      return raiting2;
   }

   public void setRaiting2(java.math.BigDecimal raiting2) {
      this.raiting2 = raiting2;
   }

   @Column(name = "RAITING_PONDERADO")
   public java.math.BigDecimal getRaitingPonderado() {
      return raitingPonderado;
   }

   public void setRaitingPonderado(java.math.BigDecimal raitingPonderado) {
      this.raitingPonderado = raitingPonderado;
   }

   @Column(name = "AUDIENCIA")
   public java.math.BigDecimal getAudiencia() {
      return audiencia;
   }

   public void setAudiencia(java.math.BigDecimal audiencia) {
      this.audiencia = audiencia;
   }

   @Column(name = "VALOR_TARIFA")
   public java.math.BigDecimal getValorTarifa() {
      return valorTarifa;
   }

   public void setValorTarifa(java.math.BigDecimal valorTarifa) {
      this.valorTarifa = valorTarifa;
   }

   @Column(name = "VALOR_TOTAL")
   public java.math.BigDecimal getValorTotal() {
      return valorTotal;
   }

   public void setValorTotal(java.math.BigDecimal valorTotal) {
      this.valorTotal = valorTotal;
   }

   @Column(name = "SECCION")
   public java.lang.String getSeccion() {
      return seccion;
   }

   public void setSeccion(java.lang.String seccion) {
      this.seccion = seccion;
   }

   @Column(name = "UBICACION")
   public java.lang.String getUbicacion() {
      return ubicacion;
   }

   public void setUbicacion(java.lang.String ubicacion) {
      this.ubicacion = ubicacion;
   }

   @Column(name = "TAMANIO")
   public java.lang.String getTamanio() {
      return tamanio;
   }

   public void setTamanio(java.lang.String tamanio) {
      this.tamanio = tamanio;
   }

   @Column(name = "COLOR")
   public java.lang.String getColor() {
      return color;
   }

   public void setColor(java.lang.String color) {
      this.color = color;
   }

   @Column(name = "TOTAL_CUNIAS")
   public java.lang.Integer getTotalCunias() {
      return totalCunias;
   }

   public void setTotalCunias(java.lang.Integer totalCunias) {
      this.totalCunias = totalCunias;
   }

   @Column(name = "COMERCIAL")
   public java.lang.String getComercial() {
      return comercial;
   }

   public void setComercial(java.lang.String comercial) {
      this.comercial = comercial;
   }

   @Column(name = "VALOR_DESCUENTO")
   public java.math.BigDecimal getValorDescuento() {
      return valorDescuento;
   }

   public void setValorDescuento(java.math.BigDecimal valorDescuento) {
      this.valorDescuento = valorDescuento;
   }

   @Column(name = "PRODUCTO_PROVEEDOR_ID")
   public java.lang.Long getProductoProveedorId() {
      return productoProveedorId;
   }

   public void setProductoProveedorId(java.lang.Long productoProveedorId) {
      this.productoProveedorId = productoProveedorId;
   }

   @Column(name = "VALOR_SUBTOTAL")
   public java.math.BigDecimal getValorSubtotal() {
      return valorSubtotal;
   }

   public void setValorSubtotal(java.math.BigDecimal valorSubtotal) {
      this.valorSubtotal = valorSubtotal;
   }

   @Column(name = "PORCENTAJE_DESCUENTO")
   public java.math.BigDecimal getPorcentajeDescuento() {
      return porcentajeDescuento;
   }

   public void setPorcentajeDescuento(java.math.BigDecimal porcentajeDescuento) {
      this.porcentajeDescuento = porcentajeDescuento;
   }

   @Column(name = "VALOR_IVA")
   public java.math.BigDecimal getValorIva() {
      return valorIva;
   }

   public void setValorIva(java.math.BigDecimal valorIva) {
      this.valorIva = valorIva;
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

   @Column(name = "PRODUCTO_CLIENTE_ID")
   public java.lang.Long getProductoClienteId() {
      return productoClienteId;
   }

   public void setProductoClienteId(java.lang.Long productoClienteId) {
      this.productoClienteId = productoClienteId;
   }

   @Column(name = "PAUTA")
   public java.lang.String getPauta() {
      return pauta;
   }

   public void setPauta(java.lang.String pauta) {
      this.pauta = pauta;
   }

   @Column(name = "AUSPICIO_DESCRIPCION")
   public java.lang.String getAuspicioDescripcion() {
      return auspicioDescripcion;
   }

   public void setAuspicioDescripcion(java.lang.String auspicioDescripcion) {
      this.auspicioDescripcion = auspicioDescripcion;
   }

   @Column(name = "AUSPICIO_PADRE")
   public java.lang.Long getAuspicioPadre() {
      return auspicioPadre;
   }

   public void setAuspicioPadre(java.lang.Long auspicioPadre) {
      this.auspicioPadre = auspicioPadre;
   }

   @Column(name = "CAMPANA_PRODUCTO_VERSION_ID")
   public java.lang.Long getCampanaProductoVersionId() {
      return campanaProductoVersionId;
   }

   public void setCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) {
      this.campanaProductoVersionId = campanaProductoVersionId;
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

   @Column(name = "NEGOCIACION_COMISION")
   public java.lang.String getNegociacionComision() {
      return negociacionComision;
   }

   public void setNegociacionComision(java.lang.String negociacionComision) {
      this.negociacionComision = negociacionComision;
   }

   @Column(name = "NUMERO_ORDEN_AGRUPACION")
   public java.lang.Integer getNumeroOrdenAgrupacion() {
      return numeroOrdenAgrupacion;
   }

   public void setNumeroOrdenAgrupacion(java.lang.Integer numeroOrdenAgrupacion) {
      this.numeroOrdenAgrupacion = numeroOrdenAgrupacion;
   }

   @Column(name = "VERSION")
   public java.lang.String getVersion() {
      return version;
   }

   public void setVersion(java.lang.String version) {
      this.version = version;
   }
	public String toString() {
		return ToStringer.toString((PlanMedioDetalleIf)this);
	}
}
