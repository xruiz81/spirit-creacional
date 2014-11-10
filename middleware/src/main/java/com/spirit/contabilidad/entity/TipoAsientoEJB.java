package com.spirit.contabilidad.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "TIPO_ASIENTO")
@Entity
public class TipoAsientoEJB implements Serializable, TipoAsientoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long empresaId;
   private java.lang.Long orden;
   private java.lang.String status;

   public TipoAsientoEJB() {
   }

   public TipoAsientoEJB(com.spirit.contabilidad.entity.TipoAsientoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setOrden(value.getOrden());
      setStatus(value.getStatus());
   }

   public java.lang.Long create(com.spirit.contabilidad.entity.TipoAsientoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setOrden(value.getOrden());
      setStatus(value.getStatus());
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

   @Column(name = "ORDEN")
   public java.lang.Long getOrden() {
      return orden;
   }

   public void setOrden(java.lang.Long orden) {
      this.orden = orden;
   }

   @Column(name = "STATUS")
   public java.lang.String getStatus() {
      return status;
   }

   public void setStatus(java.lang.String status) {
      this.status = status;
   }
	public String toString() {
		return ToStringer.toString((TipoAsientoIf)this);
	}
}
