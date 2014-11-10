package com.spirit.medios.entity;

import java.io.Serializable;


/**
 * The PresupuestoArchivoData objects extends the generated Value object.
 * Put here your presentation specific code.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:37 $
 */
public class PresupuestoArchivoData extends PresupuestoArchivoValue implements PresupuestoArchivoIf, Serializable    {

   /**
    * Default constructor.
    */
   public PresupuestoArchivoData() {
   }

   /**
    * Value object constructor.
    */
   public PresupuestoArchivoData(com.spirit.medios.entity.PresupuestoArchivoIf value) {
      setId(value.getId());
      setPresupuestoId(value.getPresupuestoId());
      setTipoArchivoId(value.getTipoArchivoId());
      setUrl(value.getUrl());
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
	  str.append("presupuestoId=" + getPresupuestoId()  + " ");
	  str.append("tipoArchivoId=" + getTipoArchivoId()  + " ");
	  str.append("url=" + getUrl()  + " ");
	  str.append('}');
	  return(str.toString());
   }
}
