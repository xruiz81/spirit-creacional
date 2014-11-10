package com.spirit.sri.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "SRI_SUSTENTO_TRIBUTARIO")
@Entity
public class SriSustentoTributarioEJB implements Serializable, SriSustentoTributarioIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String descripcion;
   private java.lang.String codigo;

   public SriSustentoTributarioEJB() {
   }

   public SriSustentoTributarioEJB(com.spirit.sri.entity.SriSustentoTributarioIf value) {
      setId(value.getId());
      setDescripcion(value.getDescripcion());
      setCodigo(value.getCodigo());
   }

   public java.lang.Long create(com.spirit.sri.entity.SriSustentoTributarioIf value) {
      setId(value.getId());
      setDescripcion(value.getDescripcion());
      setCodigo(value.getCodigo());
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

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }
	public String toString() {
		return ToStringer.toString((SriSustentoTributarioIf)this);
	}
}
