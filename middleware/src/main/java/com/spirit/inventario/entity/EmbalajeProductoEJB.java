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

@Table(name = "EMBALAJE_PRODUCTO")
@Entity
public class EmbalajeProductoEJB implements Serializable, EmbalajeProductoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long productoId;
   private java.lang.Long embalajeId;
   private java.math.BigDecimal cantidad;
   private java.math.BigDecimal areaCubica;
   private java.lang.String tipoManejo;

   public EmbalajeProductoEJB() {
   }

   public EmbalajeProductoEJB(com.spirit.inventario.entity.EmbalajeProductoIf value) {
      setId(value.getId());
      setProductoId(value.getProductoId());
      setEmbalajeId(value.getEmbalajeId());
      setCantidad(value.getCantidad());
      setAreaCubica(value.getAreaCubica());
      setTipoManejo(value.getTipoManejo());
   }

   public java.lang.Long create(com.spirit.inventario.entity.EmbalajeProductoIf value) {
      setId(value.getId());
      setProductoId(value.getProductoId());
      setEmbalajeId(value.getEmbalajeId());
      setCantidad(value.getCantidad());
      setAreaCubica(value.getAreaCubica());
      setTipoManejo(value.getTipoManejo());
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

   @Column(name = "PRODUCTO_ID")
   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   @Column(name = "EMBALAJE_ID")
   public java.lang.Long getEmbalajeId() {
      return embalajeId;
   }

   public void setEmbalajeId(java.lang.Long embalajeId) {
      this.embalajeId = embalajeId;
   }

   @Column(name = "CANTIDAD")
   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   @Column(name = "AREA_CUBICA")
   public java.math.BigDecimal getAreaCubica() {
      return areaCubica;
   }

   public void setAreaCubica(java.math.BigDecimal areaCubica) {
      this.areaCubica = areaCubica;
   }

   @Column(name = "TIPO_MANEJO")
   public java.lang.String getTipoManejo() {
      return tipoManejo;
   }

   public void setTipoManejo(java.lang.String tipoManejo) {
      this.tipoManejo = tipoManejo;
   }
	public String toString() {
		return ToStringer.toString((EmbalajeProductoIf)this);
	}
}
