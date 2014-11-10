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

@Table(name = "STOCK_OPERATIVO")
@Entity
public class StockOperativoEJB implements Serializable, StockOperativoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long bodegaId;
   private java.lang.String mes;
   private java.lang.String anio;
   private java.lang.Long productoId;
   private java.math.BigDecimal min;
   private java.math.BigDecimal max;
   private java.lang.Integer tiempoMinimoReposicion;

   public StockOperativoEJB() {
   }

   public StockOperativoEJB(com.spirit.inventario.entity.StockOperativoIf value) {
      setId(value.getId());
      setBodegaId(value.getBodegaId());
      setMes(value.getMes());
      setAnio(value.getAnio());
      setProductoId(value.getProductoId());
      setMin(value.getMin());
      setMax(value.getMax());
      setTiempoMinimoReposicion(value.getTiempoMinimoReposicion());
   }

   public java.lang.Long create(com.spirit.inventario.entity.StockOperativoIf value) {
      setId(value.getId());
      setBodegaId(value.getBodegaId());
      setMes(value.getMes());
      setAnio(value.getAnio());
      setProductoId(value.getProductoId());
      setMin(value.getMin());
      setMax(value.getMax());
      setTiempoMinimoReposicion(value.getTiempoMinimoReposicion());
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

   @Column(name = "MES")
   public java.lang.String getMes() {
      return mes;
   }

   public void setMes(java.lang.String mes) {
      this.mes = mes;
   }

   @Column(name = "ANIO")
   public java.lang.String getAnio() {
      return anio;
   }

   public void setAnio(java.lang.String anio) {
      this.anio = anio;
   }

   @Column(name = "PRODUCTO_ID")
   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   @Column(name = "MIN")
   public java.math.BigDecimal getMin() {
      return min;
   }

   public void setMin(java.math.BigDecimal min) {
      this.min = min;
   }

   @Column(name = "MAX")
   public java.math.BigDecimal getMax() {
      return max;
   }

   public void setMax(java.math.BigDecimal max) {
      this.max = max;
   }

   @Column(name = "TIEMPO_MINIMO_REPOSICION")
   public java.lang.Integer getTiempoMinimoReposicion() {
      return tiempoMinimoReposicion;
   }

   public void setTiempoMinimoReposicion(java.lang.Integer tiempoMinimoReposicion) {
      this.tiempoMinimoReposicion = tiempoMinimoReposicion;
   }
	public String toString() {
		return ToStringer.toString((StockOperativoIf)this);
	}
}
