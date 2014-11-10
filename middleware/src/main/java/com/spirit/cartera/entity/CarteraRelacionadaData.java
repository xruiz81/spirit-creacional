package com.spirit.cartera.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CarteraRelacionadaData implements CarteraRelacionadaIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long carteraOrigenId;

   public java.lang.Long getCarteraOrigenId() {
      return carteraOrigenId;
   }

   public void setCarteraOrigenId(java.lang.Long carteraOrigenId) {
      this.carteraOrigenId = carteraOrigenId;
   }

   private java.lang.Long carteraRelacionadaId;

   public java.lang.Long getCarteraRelacionadaId() {
      return carteraRelacionadaId;
   }

   public void setCarteraRelacionadaId(java.lang.Long carteraRelacionadaId) {
      this.carteraRelacionadaId = carteraRelacionadaId;
   }
   public CarteraRelacionadaData() {
   }

   public CarteraRelacionadaData(com.spirit.cartera.entity.CarteraRelacionadaIf value) {
      setId(value.getId());
      setCarteraOrigenId(value.getCarteraOrigenId());
      setCarteraRelacionadaId(value.getCarteraRelacionadaId());
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
		return ToStringer.toString((CarteraRelacionadaIf)this);
	}
}
