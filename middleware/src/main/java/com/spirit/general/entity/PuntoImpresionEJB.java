package com.spirit.general.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "PUNTO_IMPRESION")
@Entity
public class PuntoImpresionEJB implements Serializable, PuntoImpresionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long tipodocumentoId;
   private java.lang.Long cajaId;
   private java.lang.String serie;
   private java.lang.String impresora;
   private java.lang.String estado;

   public PuntoImpresionEJB() {
   }

   public PuntoImpresionEJB(com.spirit.general.entity.PuntoImpresionIf value) {
      setId(value.getId());
      setTipodocumentoId(value.getTipodocumentoId());
      setCajaId(value.getCajaId());
      setSerie(value.getSerie());
      setImpresora(value.getImpresora());
      setEstado(value.getEstado());
   }

   public java.lang.Long create(com.spirit.general.entity.PuntoImpresionIf value) {
      setId(value.getId());
      setTipodocumentoId(value.getTipodocumentoId());
      setCajaId(value.getCajaId());
      setSerie(value.getSerie());
      setImpresora(value.getImpresora());
      setEstado(value.getEstado());
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

   @Column(name = "TIPODOCUMENTO_ID")
   public java.lang.Long getTipodocumentoId() {
      return tipodocumentoId;
   }

   public void setTipodocumentoId(java.lang.Long tipodocumentoId) {
      this.tipodocumentoId = tipodocumentoId;
   }

   @Column(name = "CAJA_ID")
   public java.lang.Long getCajaId() {
      return cajaId;
   }

   public void setCajaId(java.lang.Long cajaId) {
      this.cajaId = cajaId;
   }

   @Column(name = "SERIE")
   public java.lang.String getSerie() {
      return serie;
   }

   public void setSerie(java.lang.String serie) {
      this.serie = serie;
   }

   @Column(name = "IMPRESORA")
   public java.lang.String getImpresora() {
      return impresora;
   }

   public void setImpresora(java.lang.String impresora) {
      this.impresora = impresora;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
	public String toString() {
		return ToStringer.toString((PuntoImpresionIf)this);
	}
}
