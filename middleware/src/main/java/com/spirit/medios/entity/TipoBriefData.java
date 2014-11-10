package com.spirit.medios.entity;

import java.io.Serializable;


/**
 * The TipoBriefData objects extends the generated Value object.
 * Put here your presentation specific code.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:37 $
 */
public class TipoBriefData extends TipoBriefValue implements TipoBriefIf, Serializable    {

   /**
    * Default constructor.
    */
   public TipoBriefData() {
   }

   /**
    * Value object constructor.
    */
   public TipoBriefData(com.spirit.medios.entity.TipoBriefIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setObligatorio(value.getObligatorio());
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
	  str.append("codigo=" + getCodigo()  + " ");
	  str.append("nombre=" + getNombre()  + " ");
	  str.append("empresaId=" + getEmpresaId()  + " ");
	  str.append("obligatorio=" + getObligatorio()  + " ");
	  str.append('}');
	  return(str.toString());
   }
}
