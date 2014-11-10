package com.spirit.compras.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "SOLICITUD_COMPRA_ARCHIVO")
@Entity
public class SolicitudCompraArchivoEJB implements Serializable, SolicitudCompraArchivoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long solicitudCompraId;
   private java.lang.Long tipoArchivoId;
   private java.lang.String url;

   public SolicitudCompraArchivoEJB() {
   }

   public SolicitudCompraArchivoEJB(com.spirit.compras.entity.SolicitudCompraArchivoIf value) {
      setId(value.getId());
      setSolicitudCompraId(value.getSolicitudCompraId());
      setTipoArchivoId(value.getTipoArchivoId());
      setUrl(value.getUrl());
   }

   public java.lang.Long create(com.spirit.compras.entity.SolicitudCompraArchivoIf value) {
      setId(value.getId());
      setSolicitudCompraId(value.getSolicitudCompraId());
      setTipoArchivoId(value.getTipoArchivoId());
      setUrl(value.getUrl());
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

   @Column(name = "SOLICITUD_COMPRA_ID")
   public java.lang.Long getSolicitudCompraId() {
      return solicitudCompraId;
   }

   public void setSolicitudCompraId(java.lang.Long solicitudCompraId) {
      this.solicitudCompraId = solicitudCompraId;
   }

   @Column(name = "TIPO_ARCHIVO_ID")
   public java.lang.Long getTipoArchivoId() {
      return tipoArchivoId;
   }

   public void setTipoArchivoId(java.lang.Long tipoArchivoId) {
      this.tipoArchivoId = tipoArchivoId;
   }

   @Column(name = "URL")
   public java.lang.String getUrl() {
      return url;
   }

   public void setUrl(java.lang.String url) {
      this.url = url;
   }
	public String toString() {
		return ToStringer.toString((SolicitudCompraArchivoIf)this);
	}
}
