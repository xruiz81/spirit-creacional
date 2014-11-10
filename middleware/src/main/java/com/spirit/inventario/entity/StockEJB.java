package com.spirit.inventario.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "STOCK")
@Entity
public class StockEJB implements Serializable, StockIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long bodegaId;
   private java.lang.Long productoId;
   private java.lang.Long loteId;
   private java.lang.String anio;
   private java.lang.String mes;
   private java.math.BigDecimal cantidad;
   private java.math.BigDecimal reserva;
   private java.math.BigDecimal transito;
   private java.lang.String estado;
   private java.sql.Timestamp fhUtlModificacion;

   public StockEJB() {
   }

   public StockEJB(com.spirit.inventario.entity.StockIf value) {
      setId(value.getId());
      setBodegaId(value.getBodegaId());
      setProductoId(value.getProductoId());
      setLoteId(value.getLoteId());
      setAnio(value.getAnio());
      setMes(value.getMes());
      setCantidad(value.getCantidad());
      setReserva(value.getReserva());
      setTransito(value.getTransito());
      setEstado(value.getEstado());
      setFhUtlModificacion(value.getFhUtlModificacion());
   }

   public java.lang.Long create(com.spirit.inventario.entity.StockIf value) {
      setId(value.getId());
      setBodegaId(value.getBodegaId());
      setProductoId(value.getProductoId());
      setLoteId(value.getLoteId());
      setAnio(value.getAnio());
      setMes(value.getMes());
      setCantidad(value.getCantidad());
      setReserva(value.getReserva());
      setTransito(value.getTransito());
      setEstado(value.getEstado());
      setFhUtlModificacion(value.getFhUtlModificacion());
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

   @Column(name = "BODEGA_ID")
   public java.lang.Long getBodegaId() {
      return bodegaId;
   }

   public void setBodegaId(java.lang.Long bodegaId) {
      this.bodegaId = bodegaId;
   }

   @Column(name = "PRODUCTO_ID")
   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   @Column(name = "LOTE_ID")
   public java.lang.Long getLoteId() {
      return loteId;
   }

   public void setLoteId(java.lang.Long loteId) {
      this.loteId = loteId;
   }

   @Column(name = "ANIO")
   public java.lang.String getAnio() {
      return anio;
   }

   public void setAnio(java.lang.String anio) {
      this.anio = anio;
   }

   @Column(name = "MES")
   public java.lang.String getMes() {
      return mes;
   }

   public void setMes(java.lang.String mes) {
      this.mes = mes;
   }

   @Column(name = "CANTIDAD")
   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   @Column(name = "RESERVA")
   public java.math.BigDecimal getReserva() {
      return reserva;
   }

   public void setReserva(java.math.BigDecimal reserva) {
      this.reserva = reserva;
   }

   @Column(name = "TRANSITO")
   public java.math.BigDecimal getTransito() {
      return transito;
   }

   public void setTransito(java.math.BigDecimal transito) {
      this.transito = transito;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "FH_UTL_MODIFICACION")
   public java.sql.Timestamp getFhUtlModificacion() {
      return fhUtlModificacion;
   }

   public void setFhUtlModificacion(java.sql.Timestamp fhUtlModificacion) {
      this.fhUtlModificacion = fhUtlModificacion;
   }
	public String toString() {
		return ToStringer.toString((StockIf)this);
	}
}
