package com.spirit.nomina.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ContratoPlantillaData implements ContratoPlantillaIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long tipoContratoId;

   public java.lang.Long getTipoContratoId() {
      return tipoContratoId;
   }

   public void setTipoContratoId(java.lang.Long tipoContratoId) {
      this.tipoContratoId = tipoContratoId;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }
   public ContratoPlantillaData() {
   }

   public ContratoPlantillaData(com.spirit.nomina.entity.ContratoPlantillaIf value) {
      setId(value.getId());
      setTipoContratoId(value.getTipoContratoId());
      setCodigo(value.getCodigo());
      setObservacion(value.getObservacion());
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
		return ToStringer.toString((ContratoPlantillaIf)this);
	}
}
