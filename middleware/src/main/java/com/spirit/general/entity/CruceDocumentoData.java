package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CruceDocumentoData implements CruceDocumentoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long documentoId;

   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   private java.lang.Long documentoaplId;

   public java.lang.Long getDocumentoaplId() {
      return documentoaplId;
   }

   public void setDocumentoaplId(java.lang.Long documentoaplId) {
      this.documentoaplId = documentoaplId;
   }

   private java.lang.String validoAlGuardar;

   public java.lang.String getValidoAlGuardar() {
      return validoAlGuardar;
   }

   public void setValidoAlGuardar(java.lang.String validoAlGuardar) {
      this.validoAlGuardar = validoAlGuardar;
   }

   private java.lang.String validoAlActualizar;

   public java.lang.String getValidoAlActualizar() {
      return validoAlActualizar;
   }

   public void setValidoAlActualizar(java.lang.String validoAlActualizar) {
      this.validoAlActualizar = validoAlActualizar;
   }
   public CruceDocumentoData() {
   }

   public CruceDocumentoData(com.spirit.general.entity.CruceDocumentoIf value) {
      setId(value.getId());
      setDocumentoId(value.getDocumentoId());
      setDocumentoaplId(value.getDocumentoaplId());
      setValidoAlGuardar(value.getValidoAlGuardar());
      setValidoAlActualizar(value.getValidoAlActualizar());
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
		return ToStringer.toString((CruceDocumentoIf)this);
	}
}
