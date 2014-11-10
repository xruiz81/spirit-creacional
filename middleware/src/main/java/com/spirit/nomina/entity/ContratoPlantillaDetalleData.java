package com.spirit.nomina.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ContratoPlantillaDetalleData implements ContratoPlantillaDetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long contratoPlantillaId;

   public java.lang.Long getContratoPlantillaId() {
      return contratoPlantillaId;
   }

   public void setContratoPlantillaId(java.lang.Long contratoPlantillaId) {
      this.contratoPlantillaId = contratoPlantillaId;
   }

   private java.lang.Long rubroId;

   public java.lang.Long getRubroId() {
      return rubroId;
   }

   public void setRubroId(java.lang.Long rubroId) {
      this.rubroId = rubroId;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }
   public ContratoPlantillaDetalleData() {
   }

   public ContratoPlantillaDetalleData(com.spirit.nomina.entity.ContratoPlantillaDetalleIf value) {
      setId(value.getId());
      setContratoPlantillaId(value.getContratoPlantillaId());
      setRubroId(value.getRubroId());
      setValor(value.getValor());
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
		return ToStringer.toString((ContratoPlantillaDetalleIf)this);
	}
}
