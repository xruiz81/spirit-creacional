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

@Table(name = "PROMEDIO_UNITARIO")
@Entity
public class PromedioUnitarioEJB implements Serializable, PromedioUnitarioIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long pid;
   private java.lang.String sku;
   private java.lang.String modelo;
   private java.lang.String talla;
   private java.lang.String color;
   private java.sql.Timestamp fecha;
   private java.lang.Long bodegaId;
   private java.math.BigDecimal promedioUnitario;

   public PromedioUnitarioEJB() {
   }

   public PromedioUnitarioEJB(com.spirit.inventario.entity.PromedioUnitarioIf value) {
      setId(value.getId());
      setPid(value.getPid());
      setSku(value.getSku());
      setModelo(value.getModelo());
      setTalla(value.getTalla());
      setColor(value.getColor());
      setFecha(value.getFecha());
      setBodegaId(value.getBodegaId());
      setPromedioUnitario(value.getPromedioUnitario());
   }

   public java.lang.Long create(com.spirit.inventario.entity.PromedioUnitarioIf value) {
      setId(value.getId());
      setPid(value.getPid());
      setSku(value.getSku());
      setModelo(value.getModelo());
      setTalla(value.getTalla());
      setColor(value.getColor());
      setFecha(value.getFecha());
      setBodegaId(value.getBodegaId());
      setPromedioUnitario(value.getPromedioUnitario());
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

   @Column(name = "PID")
   public java.lang.Long getPid() {
      return pid;
   }

   public void setPid(java.lang.Long pid) {
      this.pid = pid;
   }

   @Column(name = "SKU")
   public java.lang.String getSku() {
      return sku;
   }

   public void setSku(java.lang.String sku) {
      this.sku = sku;
   }

   @Column(name = "MODELO")
   public java.lang.String getModelo() {
      return modelo;
   }

   public void setModelo(java.lang.String modelo) {
      this.modelo = modelo;
   }

   @Column(name = "TALLA")
   public java.lang.String getTalla() {
      return talla;
   }

   public void setTalla(java.lang.String talla) {
      this.talla = talla;
   }

   @Column(name = "COLOR")
   public java.lang.String getColor() {
      return color;
   }

   public void setColor(java.lang.String color) {
      this.color = color;
   }

   @Column(name = "FECHA")
   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   @Column(name = "BODEGA_ID")
   public java.lang.Long getBodegaId() {
      return bodegaId;
   }

   public void setBodegaId(java.lang.Long bodegaId) {
      this.bodegaId = bodegaId;
   }

   @Column(name = "PROMEDIO_UNITARIO")
   public java.math.BigDecimal getPromedioUnitario() {
      return promedioUnitario;
   }

   public void setPromedioUnitario(java.math.BigDecimal promedioUnitario) {
      this.promedioUnitario = promedioUnitario;
   }
	public String toString() {
		return ToStringer.toString((PromedioUnitarioIf)this);
	}
}
