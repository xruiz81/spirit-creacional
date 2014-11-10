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

@Table(name = "SRI_IVA_SERVICIO")
@Entity
public class SriIvaServicioEJB implements Serializable, SriIvaServicioIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Integer codigo;
   private java.lang.String descripcionPorcentaje;

   public SriIvaServicioEJB() {
   }

   public SriIvaServicioEJB(com.spirit.sri.entity.SriIvaServicioIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setDescripcionPorcentaje(value.getDescripcionPorcentaje());
   }

   public java.lang.Long create(com.spirit.sri.entity.SriIvaServicioIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setDescripcionPorcentaje(value.getDescripcionPorcentaje());
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
   public java.lang.Integer getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.Integer codigo) {
      this.codigo = codigo;
   }

   @Column(name = "DESCRIPCION_PORCENTAJE")
   public java.lang.String getDescripcionPorcentaje() {
      return descripcionPorcentaje;
   }

   public void setDescripcionPorcentaje(java.lang.String descripcionPorcentaje) {
      this.descripcionPorcentaje = descripcionPorcentaje;
   }
	public String toString() {
		return ToStringer.toString((SriIvaServicioIf)this);
	}
}
