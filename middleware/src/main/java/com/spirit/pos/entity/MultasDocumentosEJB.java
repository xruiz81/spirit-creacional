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

@Table(name = "MULTAS_DOCUMENTOS")
@Entity
public class MultasDocumentosEJB implements Serializable, MultasDocumentosIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long motivoId;
   private java.lang.Long documentoId;
   private java.math.BigDecimal valorMulta;

   public MultasDocumentosEJB() {
   }

   public MultasDocumentosEJB(com.spirit.pos.entity.MultasDocumentosIf value) {
      setId(value.getId());
      setMotivoId(value.getMotivoId());
      setDocumentoId(value.getDocumentoId());
      setValorMulta(value.getValorMulta());
   }

   public java.lang.Long create(com.spirit.pos.entity.MultasDocumentosIf value) {
      setId(value.getId());
      setMotivoId(value.getMotivoId());
      setDocumentoId(value.getDocumentoId());
      setValorMulta(value.getValorMulta());
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

   @Column(name = "MOTIVO_ID")
   public java.lang.Long getMotivoId() {
      return motivoId;
   }

   public void setMotivoId(java.lang.Long motivoId) {
      this.motivoId = motivoId;
   }

   @Column(name = "DOCUMENTO_ID")
   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   @Column(name = "VALOR_MULTA")
   public java.math.BigDecimal getValorMulta() {
      return valorMulta;
   }

   public void setValorMulta(java.math.BigDecimal valorMulta) {
      this.valorMulta = valorMulta;
   }
	public String toString() {
		return ToStringer.toString((MultasDocumentosIf)this);
	}
}
