package com.spirit.pos.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class MultasDocumentosData implements MultasDocumentosIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long motivoId;

   public java.lang.Long getMotivoId() {
      return motivoId;
   }

   public void setMotivoId(java.lang.Long motivoId) {
      this.motivoId = motivoId;
   }

   private java.lang.Long documentoId;

   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   private java.math.BigDecimal valorMulta;

   public java.math.BigDecimal getValorMulta() {
      return valorMulta;
   }

   public void setValorMulta(java.math.BigDecimal valorMulta) {
      this.valorMulta = valorMulta;
   }
   public MultasDocumentosData() {
   }

   public MultasDocumentosData(com.spirit.pos.entity.MultasDocumentosIf value) {
      setId(value.getId());
      setMotivoId(value.getMotivoId());
      setDocumentoId(value.getDocumentoId());
      setValorMulta(value.getValorMulta());
   }



    public java.lang.Long getPrimaryKey() {
        return getId();
    }

    public void setPrimaryKey(java.lang.Long pk) {
       setId(pk);
    }


   public String getPrimaryKeyParameters() {
      String parameters = "";
      parameters += "&id=" + getId();
      return parameters;
   }



	public String toString() {
		return ToStringer.toString((MultasDocumentosIf)this);
	}
}
