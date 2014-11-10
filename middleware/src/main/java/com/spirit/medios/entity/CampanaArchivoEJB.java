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

@Table(name = "CAMPANA_ARCHIVO")
@Entity
public class CampanaArchivoEJB implements Serializable, CampanaArchivoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long tipoArchivoId;
   private java.lang.Long campanaId;
   private java.sql.Date fecha;
   private java.lang.String urlDescripcion;

   public CampanaArchivoEJB() {
   }

   public CampanaArchivoEJB(com.spirit.medios.entity.CampanaArchivoIf value) {
      setId(value.getId());
      setTipoArchivoId(value.getTipoArchivoId());
      setCampanaId(value.getCampanaId());
      setFecha(value.getFecha());
      setUrlDescripcion(value.getUrlDescripcion());
   }

   public java.lang.Long create(com.spirit.medios.entity.CampanaArchivoIf value) {
      setId(value.getId());
      setTipoArchivoId(value.getTipoArchivoId());
      setCampanaId(value.getCampanaId());
      setFecha(value.getFecha());
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

   @Column(name = "TIPO_ARCHIVO_ID")
   public java.lang.Long getTipoArchivoId() {
      return tipoArchivoId;
   }

   public void setTipoArchivoId(java.lang.Long tipoArchivoId) {
      this.tipoArchivoId = tipoArchivoId;
   }

   @Column(name = "CAMPANA_ID")
   public java.lang.Long getCampanaId() {
      return campanaId;
   }

   public void setCampanaId(java.lang.Long campanaId) {
      this.campanaId = campanaId;
   }

   @Column(name = "FECHA")
   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   @Column(name = "URL_DESCRIPCION")
   public java.lang.String getUrlDescripcion() {
      return urlDescripcion;
   }

   public void setUrlDescripcion(java.lang.String urlDescripcion) {
      this.urlDescripcion = urlDescripcion;
   }
	public String toString() {
		return ToStringer.toString((CampanaArchivoIf)this);
	}
}
