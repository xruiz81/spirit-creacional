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

@Table(name = "PRODUCTO_CLIENTE")
@Entity
public class ProductoClienteEJB implements Serializable, ProductoClienteIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long clienteId;
   private java.lang.Long creativoId;
   private java.lang.Long ejecutivoId;
   private java.lang.String estado;
   private java.lang.Long marcaProductoId;
   private java.lang.String marcaProductoNombre;

   public ProductoClienteEJB() {
   }

   public ProductoClienteEJB(com.spirit.medios.entity.ProductoClienteIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setClienteId(value.getClienteId());
      setCreativoId(value.getCreativoId());
      setEjecutivoId(value.getEjecutivoId());
      setEstado(value.getEstado());
      setMarcaProductoId(value.getMarcaProductoId());
      setMarcaProductoNombre(value.getMarcaProductoNombre());
   }

   public java.lang.Long create(com.spirit.medios.entity.ProductoClienteIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setClienteId(value.getClienteId());
      setCreativoId(value.getCreativoId());
      setEjecutivoId(value.getEjecutivoId());
      setEstado(value.getEstado());
      setMarcaProductoId(value.getMarcaProductoId());
      setMarcaProductoNombre(value.getMarcaProductoNombre());
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

   @Column(name = "NOMBRE")
   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   @Column(name = "CLIENTE_ID")
   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }

   @Column(name = "CREATIVO_ID")
   public java.lang.Long getCreativoId() {
      return creativoId;
   }

   public void setCreativoId(java.lang.Long creativoId) {
      this.creativoId = creativoId;
   }

   @Column(name = "EJECUTIVO_ID")
   public java.lang.Long getEjecutivoId() {
      return ejecutivoId;
   }

   public void setEjecutivoId(java.lang.Long ejecutivoId) {
      this.ejecutivoId = ejecutivoId;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "MARCA_PRODUCTO_ID")
   public java.lang.Long getMarcaProductoId() {
      return marcaProductoId;
   }

   public void setMarcaProductoId(java.lang.Long marcaProductoId) {
      this.marcaProductoId = marcaProductoId;
   }

   @Column(name = "MARCA_PRODUCTO_NOMBRE")
   public java.lang.String getMarcaProductoNombre() {
      return marcaProductoNombre;
   }

   public void setMarcaProductoNombre(java.lang.String marcaProductoNombre) {
      this.marcaProductoNombre = marcaProductoNombre;
   }
	public String toString() {
		return ToStringer.toString((ProductoClienteIf)this);
	}
}
