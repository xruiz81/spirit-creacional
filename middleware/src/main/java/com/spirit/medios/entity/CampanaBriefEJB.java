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

@Table(name = "CAMPANA_BRIEF")
@Entity
public class CampanaBriefEJB implements Serializable, CampanaBriefIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long tipoBriefId;
   private java.lang.Long campanaId;
   private java.lang.String descripcion;
   private java.lang.String urlDescripcion;

   public CampanaBriefEJB() {
   }

   public CampanaBriefEJB(com.spirit.medios.entity.CampanaBriefIf value) {
      setId(value.getId());
      setTipoBriefId(value.getTipoBriefId());
      setCampanaId(value.getCampanaId());
      setDescripcion(value.getDescripcion());
      setUrlDescripcion(value.getUrlDescripcion());
   }

   public java.lang.Long create(com.spirit.medios.entity.CampanaBriefIf value) {
      setId(value.getId());
      setTipoBriefId(value.getTipoBriefId());
      setCampanaId(value.getCampanaId());
      setDescripcion(value.getDescripcion());
      setUrlDescripcion(value.getUrlDescripcion());
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

   @Column(name = "TIPO_BRIEF_ID")
   public java.lang.Long getTipoBriefId() {
      return tipoBriefId;
   }

   public void setTipoBriefId(java.lang.Long tipoBriefId) {
      this.tipoBriefId = tipoBriefId;
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

   @Column(name = "URL_DESCRIPCION")
   public java.lang.String getUrlDescripcion() {
      return urlDescripcion;
   }

   public void setUrlDescripcion(java.lang.String urlDescripcion) {
      this.urlDescripcion = urlDescripcion;
   }
	public String toString() {
		return ToStringer.toString((CampanaBriefIf)this);
	}
}
