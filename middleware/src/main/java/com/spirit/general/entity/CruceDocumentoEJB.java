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

@Table(name = "CRUCE_DOCUMENTO")
@Entity
public class CruceDocumentoEJB implements Serializable, CruceDocumentoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long documentoId;
   private java.lang.Long documentoaplId;
   private java.lang.String validoAlGuardar;
   private java.lang.String validoAlActualizar;

   public CruceDocumentoEJB() {
   }

   public CruceDocumentoEJB(com.spirit.general.entity.CruceDocumentoIf value) {
      setId(value.getId());
      setDocumentoId(value.getDocumentoId());
      setDocumentoaplId(value.getDocumentoaplId());
      setValidoAlGuardar(value.getValidoAlGuardar());
      setValidoAlActualizar(value.getValidoAlActualizar());
   }

   public java.lang.Long create(com.spirit.general.entity.CruceDocumentoIf value) {
      setId(value.getId());
      setDocumentoId(value.getDocumentoId());
      setDocumentoaplId(value.getDocumentoaplId());
      setValidoAlGuardar(value.getValidoAlGuardar());
      setValidoAlActualizar(value.getValidoAlActualizar());
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

   @Column(name = "DOCUMENTO_ID")
   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   @Column(name = "DOCUMENTOAPL_ID")
   public java.lang.Long getDocumentoaplId() {
      return documentoaplId;
   }

   public void setDocumentoaplId(java.lang.Long documentoaplId) {
      this.documentoaplId = documentoaplId;
   }

   @Column(name = "VALIDO_AL_GUARDAR")
   public java.lang.String getValidoAlGuardar() {
      return validoAlGuardar;
   }

   public void setValidoAlGuardar(java.lang.String validoAlGuardar) {
      this.validoAlGuardar = validoAlGuardar;
   }

   @Column(name = "VALIDO_AL_ACTUALIZAR")
   public java.lang.String getValidoAlActualizar() {
      return validoAlActualizar;
   }

   public void setValidoAlActualizar(java.lang.String validoAlActualizar) {
      this.validoAlActualizar = validoAlActualizar;
   }
	public String toString() {
		return ToStringer.toString((CruceDocumentoIf)this);
	}
}
