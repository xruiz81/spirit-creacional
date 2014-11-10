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

@Table(name = "SUBTIPO_ORDEN")
@Entity
public class SubtipoOrdenEJB implements Serializable, SubtipoOrdenIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long tipoordenId;
   private java.lang.Long tipoproveedorId;

   public SubtipoOrdenEJB() {
   }

   public SubtipoOrdenEJB(com.spirit.medios.entity.SubtipoOrdenIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setTipoordenId(value.getTipoordenId());
      setTipoproveedorId(value.getTipoproveedorId());
   }

   public java.lang.Long create(com.spirit.medios.entity.SubtipoOrdenIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setTipoordenId(value.getTipoordenId());
      setTipoproveedorId(value.getTipoproveedorId());
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

   @Column(name = "TIPOORDEN_ID")
   public java.lang.Long getTipoordenId() {
      return tipoordenId;
   }

   public void setTipoordenId(java.lang.Long tipoordenId) {
      this.tipoordenId = tipoordenId;
   }

   @Column(name = "TIPOPROVEEDOR_ID")
   public java.lang.Long getTipoproveedorId() {
      return tipoproveedorId;
   }

   public void setTipoproveedorId(java.lang.Long tipoproveedorId) {
      this.tipoproveedorId = tipoproveedorId;
   }
	public String toString() {
		return ToStringer.toString((SubtipoOrdenIf)this);
	}
}
