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

@Table(name = "ORDEN_MEDIO_DETALLE")
@Entity
public class OrdenMedioDetalleEJB implements Serializable, OrdenMedioDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long ordenMedioId;
   private java.lang.Long comercialId;
   private java.lang.String programa;
   private java.lang.String hora;
   private java.lang.String comercial;
   private java.math.BigDecimal valorTotal;
   private java.math.BigDecimal porcentajeDescuento;
   private java.math.BigDecimal valorIva;
   private java.math.BigDecimal valorTarifa;
   private java.math.BigDecimal valorSubtotal;
   private java.math.BigDecimal valorDescuento;
   private java.math.BigDecimal porcentajeDescuentoVenta;
   private java.math.BigDecimal porcentajeComisionAgencia;
   private java.math.BigDecimal valorDescuentoVenta;
   private java.math.BigDecimal valorComisionAgencia;
   private java.lang.Integer totalCunias;
   private java.lang.Long productoProveedorId;
   private java.lang.Long productoClienteId;
   private java.lang.String pauta;
   private java.lang.String auspicioDescripcion;
   private java.lang.Long auspicioPadre;
   private java.lang.Long campanaProductoVersionId;

   public OrdenMedioDetalleEJB() {
   }

   public OrdenMedioDetalleEJB(com.spirit.medios.entity.OrdenMedioDetalleIf value) {
      setId(value.getId());
      setOrdenMedioId(value.getOrdenMedioId());
      setComercialId(value.getComercialId());
      setPrograma(value.getPrograma());
      setHora(value.getHora());
      setComercial(value.getComercial());
      setValorTotal(value.getValorTotal());
      setPorcentajeDescuento(value.getPorcentajeDescuento());
      setValorIva(value.getValorIva());
      setValorTarifa(value.getValorTarifa());
      setValorSubtotal(value.getValorSubtotal());
      setValorDescuento(value.getValorDescuento());
      setPorcentajeDescuentoVenta(value.getPorcentajeDescuentoVenta());
      setPorcentajeComisionAgencia(value.getPorcentajeComisionAgencia());
      setValorDescuentoVenta(value.getValorDescuentoVenta());
      setValorComisionAgencia(value.getValorComisionAgencia());
      setTotalCunias(value.getTotalCunias());
      setProductoProveedorId(value.getProductoProveedorId());
      setProductoClienteId(value.getProductoClienteId());
      setPauta(value.getPauta());
      setAuspicioDescripcion(value.getAuspicioDescripcion());
      setAuspicioPadre(value.getAuspicioPadre());
      setCampanaProductoVersionId(value.getCampanaProductoVersionId());
   }

   public java.lang.Long create(com.spirit.medios.entity.OrdenMedioDetalleIf value) {
      setId(value.getId());
      setOrdenMedioId(value.getOrdenMedioId());
      setComercialId(value.getComercialId());
      setPrograma(value.getPrograma());
      setHora(value.getHora());
      setComercial(value.getComercial());
      setValorTotal(value.getValorTotal());
      setPorcentajeDescuento(value.getPorcentajeDescuento());
      setValorIva(value.getValorIva());
      setValorTarifa(value.getValorTarifa());
      setValorSubtotal(value.getValorSubtotal());
      setValorDescuento(value.getValorDescuento());
      setPorcentajeDescuentoVenta(value.getPorcentajeDescuentoVenta());
      setPorcentajeComisionAgencia(value.getPorcentajeComisionAgencia());
      setValorDescuentoVenta(value.getValorDescuentoVenta());
      setValorComisionAgencia(value.getValorComisionAgencia());
      setTotalCunias(value.getTotalCunias());
      setProductoProveedorId(value.getProductoProveedorId());
      setProductoClienteId(value.getProductoClienteId());
      setPauta(value.getPauta());
      setAuspicioDescripcion(value.getAuspicioDescripcion());
      setAuspicioPadre(value.getAuspicioPadre());
      setCampanaProductoVersionId(value.getCampanaProductoVersionId());
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

   @Column(name = "ORDEN_MEDIO_ID")
   public java.lang.Long getOrdenMedioId() {
      return ordenMedioId;
   }

   public void setOrdenMedioId(java.lang.Long ordenMedioId) {
      this.ordenMedioId = ordenMedioId;
   }

   @Column(name = "COMERCIAL_ID")
   public java.lang.Long getComercialId() {
      return comercialId;
   }

   public void setComercialId(java.lang.Long comercialId) {
      this.comercialId = comercialId;
   }

   @Column(name = "PROGRAMA")
   public java.lang.String getPrograma() {
      return programa;
   }

   public void setPrograma(java.lang.String programa) {
      this.programa = programa;
   }

   @Column(name = "HORA")
   public java.lang.String getHora() {
      return hora;
   }

   public void setHora(java.lang.String hora) {
      this.hora = hora;
   }

   @Column(name = "COMERCIAL")
   public java.lang.String getComercial() {
      return comercial;
   }

   public void setComercial(java.lang.String comercial) {
      this.comercial = comercial;
   }

   @Column(name = "VALOR_TOTAL")
   public java.math.BigDecimal getValorTotal() {
      return valorTotal;
   }

   public void setValorTotal(java.math.BigDecimal valorTotal) {
      this.valorTotal = valorTotal;
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

   @Column(name = "VALOR_TARIFA")
   public java.math.BigDecimal getValorTarifa() {
      return valorTarifa;
   }

   public void setValorTarifa(java.math.BigDecimal valorTarifa) {
      this.valorTarifa = valorTarifa;
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

   @Column(name = "TOTAL_CUNIAS")
   public java.lang.Integer getTotalCunias() {
      return totalCunias;
   }

   public void setTotalCunias(java.lang.Integer totalCunias) {
      this.totalCunias = totalCunias;
   }

   @Column(name = "PRODUCTO_PROVEEDOR_ID")
   public java.lang.Long getProductoProveedorId() {
      return productoProveedorId;
   }

   public void setProductoProveedorId(java.lang.Long productoProveedorId) {
      this.productoProveedorId = productoProveedorId;
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
	public String toString() {
		return ToStringer.toString((OrdenMedioDetalleIf)this);
	}
}
