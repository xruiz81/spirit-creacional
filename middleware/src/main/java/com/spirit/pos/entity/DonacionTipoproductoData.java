package com.spirit.pos.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class DonacionTipoproductoData implements DonacionTipoproductoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long tipoproductoId;

   public java.lang.Long getTipoproductoId() {
      return tipoproductoId;
   }

   public void setTipoproductoId(java.lang.Long tipoproductoId) {
      this.tipoproductoId = tipoproductoId;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }
   public DonacionTipoproductoData() {
   }

   public DonacionTipoproductoData(com.spirit.pos.entity.DonacionTipoproductoIf value) {
      setId(value.getId());
      setTipoproductoId(value.getTipoproductoId());
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
		return ToStringer.toString((DonacionTipoproductoIf)this);
	}
}
