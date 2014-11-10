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

@Table(name = "LOTE")
@Entity
public class LoteEJB implements Serializable, LoteIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.Long productoId;
   private java.sql.Timestamp fechaCreacion;
   private java.sql.Timestamp fechaElaboracion;
   private java.sql.Timestamp fechaVencimiento;
   private java.lang.String estado;

   public LoteEJB() {
   }

   public LoteEJB(com.spirit.inventario.entity.LoteIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setProductoId(value.getProductoId());
      setFechaCreacion(value.getFechaCreacion());
      setFechaElaboracion(value.getFechaElaboracion());
      setFechaVencimiento(value.getFechaVencimiento());
      setEstado(value.getEstado());
   }

   public java.lang.Long create(com.spirit.inventario.entity.LoteIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setProductoId(value.getProductoId());
      setFechaCreacion(value.getFechaCreacion());
      setFechaElaboracion(value.getFechaElaboracion());
      setFechaVencimiento(value.getFechaVencimiento());
      setEstado(value.getEstado());
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

   @Column(name = "PRODUCTO_ID")
   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "FECHA_ELABORACION")
   public java.sql.Timestamp getFechaElaboracion() {
      return fechaElaboracion;
   }

   public void setFechaElaboracion(java.sql.Timestamp fechaElaboracion) {
      this.fechaElaboracion = fechaElaboracion;
   }

   @Column(name = "FECHA_VENCIMIENTO")
   public java.sql.Timestamp getFechaVencimiento() {
      return fechaVencimiento;
   }

   public void setFechaVencimiento(java.sql.Timestamp fechaVencimiento) {
      this.fechaVencimiento = fechaVencimiento;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
	public String toString() {
		return ToStringer.toString((LoteIf)this);
	}
}
