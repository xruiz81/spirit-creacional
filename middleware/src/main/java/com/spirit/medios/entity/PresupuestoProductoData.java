package com.spirit.medios.entity;

import java.io.Serializable;


/**
 * The PresupuestoProductoData objects extends the generated Value object.
 * Put here your presentation specific code.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:37 $
 */
public class PresupuestoProductoData extends PresupuestoProductoValue implements PresupuestoProductoIf, Serializable    {

   /**
    * Default constructor.
    */
   public PresupuestoProductoData() {
   }

   /**
    * Value object constructor.
    */
   public PresupuestoProductoData(com.spirit.medios.entity.PresupuestoProductoIf value) {
      setId(value.getId());
      setProductoClienteId(value.getProductoClienteId());
      setPresupuestoId(value.getPresupuestoId());
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
	  str.append("productoClienteId=" + getProductoClienteId()  + " ");
	  str.append("presupuestoId=" + getPresupuestoId()  + " ");
	  str.append('}');
	  return(str.toString());
   }
}
