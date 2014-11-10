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

@Table(name = "STOCK_DIA")
@Entity
public class StockDiaEJB implements Serializable, StockDiaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long bodegaId;
   private java.lang.Long productoId;
   private java.lang.String anio;
   private java.lang.String mes;
   private java.lang.String dia;
   private java.math.BigDecimal cantidad;

   public StockDiaEJB() {
   }

   public StockDiaEJB(com.spirit.inventario.entity.StockDiaIf value) {
      setId(value.getId());
      setBodegaId(value.getBodegaId());
      setProductoId(value.getProductoId());
      setAnio(value.getAnio());
      setMes(value.getMes());
      setDia(value.getDia());
      setCantidad(value.getCantidad());
   }

   public java.lang.Long create(com.spirit.inventario.entity.StockDiaIf value) {
      setId(value.getId());
      setBodegaId(value.getBodegaId());
      setProductoId(value.getProductoId());
      setAnio(value.getAnio());
      setMes(value.getMes());
      setDia(value.getDia());
      setCantidad(value.getCantidad());
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

   @Column(name = "DIA")
   public java.lang.String getDia() {
      return dia;
   }

   public void setDia(java.lang.String dia) {
      this.dia = dia;
   }

   @Column(name = "CANTIDAD")
   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }
	public String toString() {
		return ToStringer.toString((StockDiaIf)this);
	}
}
