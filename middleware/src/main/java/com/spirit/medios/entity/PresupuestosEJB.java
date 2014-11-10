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

@Table(name = "PRESUPUESTOS")
@Entity
public class PresupuestosEJB implements Serializable, PresupuestosIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String tipo;
   private java.lang.Long clienteOficinaId;
   private java.lang.String concepto;
   private java.sql.Timestamp fechaAprobacion;
   private java.math.BigDecimal subtotal;
   private java.math.BigDecimal descuento;
   private java.math.BigDecimal comision;
   private java.math.BigDecimal iva;
   private java.math.BigDecimal total;
   private java.lang.String estado;
   private java.lang.String prepago;
   private java.lang.String revision;
   private java.sql.Timestamp fecha;

   public PresupuestosEJB() {
   }

   public PresupuestosEJB(com.spirit.medios.entity.PresupuestosIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setTipo(value.getTipo());
      setClienteOficinaId(value.getClienteOficinaId());
      setConcepto(value.getConcepto());
      setFechaAprobacion(value.getFechaAprobacion());
      setSubtotal(value.getSubtotal());
      setDescuento(value.getDescuento());
      setComision(value.getComision());
      setIva(value.getIva());
      setTotal(value.getTotal());
      setEstado(value.getEstado());
      setPrepago(value.getPrepago());
      setRevision(value.getRevision());
      setFecha(value.getFecha());
   }

   public java.lang.Long create(com.spirit.medios.entity.PresupuestosIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setTipo(value.getTipo());
      setClienteOficinaId(value.getClienteOficinaId());
      setConcepto(value.getConcepto());
      setFechaAprobacion(value.getFechaAprobacion());
      setSubtotal(value.getSubtotal());
      setDescuento(value.getDescuento());
      setComision(value.getComision());
      setIva(value.getIva());
      setTotal(value.getTotal());
      setEstado(value.getEstado());
      setPrepago(value.getPrepago());
      setRevision(value.getRevision());
      setFecha(value.getFecha());
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

   @Column(name = "TIPO")
   public java.lang.String getTipo() {
      return tipo;
   }

   public void setTipo(java.lang.String tipo) {
      this.tipo = tipo;
   }

   @Column(name = "CLIENTE_OFICINA_ID")
   public java.lang.Long getClienteOficinaId() {
      return clienteOficinaId;
   }

   public void setClienteOficinaId(java.lang.Long clienteOficinaId) {
      this.clienteOficinaId = clienteOficinaId;
   }

   @Column(name = "CONCEPTO")
   public java.lang.String getConcepto() {
      return concepto;
   }

   public void setConcepto(java.lang.String concepto) {
      this.concepto = concepto;
   }

   @Column(name = "FECHA_APROBACION")
   public java.sql.Timestamp getFechaAprobacion() {
      return fechaAprobacion;
   }

   public void setFechaAprobacion(java.sql.Timestamp fechaAprobacion) {
      this.fechaAprobacion = fechaAprobacion;
   }

   @Column(name = "SUBTOTAL")
   public java.math.BigDecimal getSubtotal() {
      return subtotal;
   }

   public void setSubtotal(java.math.BigDecimal subtotal) {
      this.subtotal = subtotal;
   }

   @Column(name = "DESCUENTO")
   public java.math.BigDecimal getDescuento() {
      return descuento;
   }

   public void setDescuento(java.math.BigDecimal descuento) {
      this.descuento = descuento;
   }

   @Column(name = "COMISION")
   public java.math.BigDecimal getComision() {
      return comision;
   }

   public void setComision(java.math.BigDecimal comision) {
      this.comision = comision;
   }

   @Column(name = "IVA")
   public java.math.BigDecimal getIva() {
      return iva;
   }

   public void setIva(java.math.BigDecimal iva) {
      this.iva = iva;
   }

   @Column(name = "TOTAL")
   public java.math.BigDecimal getTotal() {
      return total;
   }

   public void setTotal(java.math.BigDecimal total) {
      this.total = total;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "PREPAGO")
   public java.lang.String getPrepago() {
      return prepago;
   }

   public void setPrepago(java.lang.String prepago) {
      this.prepago = prepago;
   }

   @Column(name = "REVISION")
   public java.lang.String getRevision() {
      return revision;
   }

   public void setRevision(java.lang.String revision) {
      this.revision = revision;
   }

   @Column(name = "FECHA")
   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }
	public String toString() {
		return ToStringer.toString((PresupuestosIf)this);
	}
}
