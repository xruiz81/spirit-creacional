package com.spirit.pos.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "VENTAS_DOCUMENTOS")
@Entity
public class VentasDocumentosEJB implements Serializable, VentasDocumentosIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long ventastrackId;
   private java.lang.String tablaNombre;
   private java.lang.Long tablaId;
   private java.lang.String revisado;
   private java.lang.String numDoc;

   public VentasDocumentosEJB() {
   }

   public VentasDocumentosEJB(com.spirit.pos.entity.VentasDocumentosIf value) {
      setId(value.getId());
      setVentastrackId(value.getVentastrackId());
      setTablaNombre(value.getTablaNombre());
      setTablaId(value.getTablaId());
      setRevisado(value.getRevisado());
      setNumDoc(value.getNumDoc());
   }

   public java.lang.Long create(com.spirit.pos.entity.VentasDocumentosIf value) {
      setId(value.getId());
      setVentastrackId(value.getVentastrackId());
      setTablaNombre(value.getTablaNombre());
      setTablaId(value.getTablaId());
      setRevisado(value.getRevisado());
      setNumDoc(value.getNumDoc());
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

   @Column(name = "VENTASTRACK_ID")
   public java.lang.Long getVentastrackId() {
      return ventastrackId;
   }

   public void setVentastrackId(java.lang.Long ventastrackId) {
      this.ventastrackId = ventastrackId;
   }

   @Column(name = "TABLA_NOMBRE")
   public java.lang.String getTablaNombre() {
      return tablaNombre;
   }

   public void setTablaNombre(java.lang.String tablaNombre) {
      this.tablaNombre = tablaNombre;
   }

   @Column(name = "TABLA_ID")
   public java.lang.Long getTablaId() {
      return tablaId;
   }

   public void setTablaId(java.lang.Long tablaId) {
      this.tablaId = tablaId;
   }

   @Column(name = "REVISADO")
   public java.lang.String getRevisado() {
      return revisado;
   }

   public void setRevisado(java.lang.String revisado) {
      this.revisado = revisado;
   }

   @Column(name = "NUM_DOC")
   public java.lang.String getNumDoc() {
      return numDoc;
   }

   public void setNumDoc(java.lang.String numDoc) {
      this.numDoc = numDoc;
   }
	public String toString() {
		return ToStringer.toString((VentasDocumentosIf)this);
	}
}
