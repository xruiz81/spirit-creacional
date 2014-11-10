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

@Table(name = "COMERCIAL")
@Entity
public class ComercialEJB implements Serializable, ComercialIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long empresaId;
   private java.lang.Long campanaId;
   private java.lang.String descripcion;
   private java.lang.Long derechoprogramaId;
   private java.lang.String version;
   private java.lang.String duracion;
   private java.lang.String estado;
   private java.lang.Long productoClienteId;
   private java.lang.Long campanaProductoVersionId;

   public ComercialEJB() {
   }

   public ComercialEJB(com.spirit.medios.entity.ComercialIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setCampanaId(value.getCampanaId());
      setDescripcion(value.getDescripcion());
      setDerechoprogramaId(value.getDerechoprogramaId());
      setVersion(value.getVersion());
      setDuracion(value.getDuracion());
      setEstado(value.getEstado());
      setProductoClienteId(value.getProductoClienteId());
      setCampanaProductoVersionId(value.getCampanaProductoVersionId());
   }

   public java.lang.Long create(com.spirit.medios.entity.ComercialIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setCampanaId(value.getCampanaId());
      setDescripcion(value.getDescripcion());
      setDerechoprogramaId(value.getDerechoprogramaId());
      setVersion(value.getVersion());
      setDuracion(value.getDuracion());
      setEstado(value.getEstado());
      setProductoClienteId(value.getProductoClienteId());
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

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "CAMPANA_ID")
   public java.lang.Long getCampanaId() {
      return campanaId;
   }

   public void setCampanaId(java.lang.Long campanaId) {
      this.campanaId = campanaId;
   }

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "DERECHOPROGRAMA_ID")
   public java.lang.Long getDerechoprogramaId() {
      return derechoprogramaId;
   }

   public void setDerechoprogramaId(java.lang.Long derechoprogramaId) {
      this.derechoprogramaId = derechoprogramaId;
   }

   @Column(name = "VERSION")
   public java.lang.String getVersion() {
      return version;
   }

   public void setVersion(java.lang.String version) {
      this.version = version;
   }

   @Column(name = "DURACION")
   public java.lang.String getDuracion() {
      return duracion;
   }

   public void setDuracion(java.lang.String duracion) {
      this.duracion = duracion;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "PRODUCTO_CLIENTE_ID")
   public java.lang.Long getProductoClienteId() {
      return productoClienteId;
   }

   public void setProductoClienteId(java.lang.Long productoClienteId) {
      this.productoClienteId = productoClienteId;
   }

   @Column(name = "CAMPANA_PRODUCTO_VERSION_ID")
   public java.lang.Long getCampanaProductoVersionId() {
      return campanaProductoVersionId;
   }

   public void setCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) {
      this.campanaProductoVersionId = campanaProductoVersionId;
   }
	public String toString() {
		return ToStringer.toString((ComercialIf)this);
	}
}
