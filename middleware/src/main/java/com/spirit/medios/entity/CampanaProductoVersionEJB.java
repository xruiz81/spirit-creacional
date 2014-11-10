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

@Table(name = "CAMPANA_PRODUCTO_VERSION")
@Entity
public class CampanaProductoVersionEJB implements Serializable, CampanaProductoVersionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.String estado;
   private java.lang.Long campanaProductoId;

   public CampanaProductoVersionEJB() {
   }

   public CampanaProductoVersionEJB(com.spirit.medios.entity.CampanaProductoVersionIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEstado(value.getEstado());
      setCampanaProductoId(value.getCampanaProductoId());
   }

   public java.lang.Long create(com.spirit.medios.entity.CampanaProductoVersionIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEstado(value.getEstado());
      setCampanaProductoId(value.getCampanaProductoId());
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

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "CAMPANA_PRODUCTO_ID")
   public java.lang.Long getCampanaProductoId() {
      return campanaProductoId;
   }

   public void setCampanaProductoId(java.lang.Long campanaProductoId) {
      this.campanaProductoId = campanaProductoId;
   }
	public String toString() {
		return ToStringer.toString((CampanaProductoVersionIf)this);
	}
}
