package com.spirit.crm.entity;

import java.io.Serializable;


/**
 * The GastoElectoralAbonoData objects extends the generated Value object.
 * Put here your presentation specific code.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:39 $
 */
public class GastoElectoralAbonoData extends GastoElectoralAbonoValue implements GastoElectoralAbonoIf, Serializable    {

   /**
    * Default constructor.
    */
   public GastoElectoralAbonoData() {
   }

   /**
    * Value object constructor.
    */
   public GastoElectoralAbonoData(com.spirit.crm.entity.GastoElectoralAbonoIf value) {
      setId(value.getId());
      setFecha(value.getFecha());
      setEntregadoPor(value.getEntregadoPor());
      setValor(value.getValor());
      setCampana(value.getCampana());
   }



    /**
     * Return the primary key.
     *
     * @return java.lang.Long with the primary key.
     */
    public java.lang.Long getPrimaryKey() {
        return getId();
    }

    /**
     * Set the primary key.
     *
     * @param pk the primary key
     */
    public void setPrimaryKey(java.lang.Long pk) {
       setId(pk);
    }


   public String getPrimaryKeyParameters() {
      String parameters = "";
      parameters += "&id=" + getId();
      return parameters;
   }



   public String toString() {
	  StringBuffer str = new StringBuffer("{");
	  str.append("id=" + getId()  + " ");
	  str.append("fecha=" + getFecha()  + " ");
	  str.append("entregadoPor=" + getEntregadoPor()  + " ");
	  str.append("valor=" + getValor()  + " ");
	  str.append("campana=" + getCampana()  + " ");
	  str.append('}');
	  return(str.toString());
   }
}
