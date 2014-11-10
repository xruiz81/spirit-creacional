package com.spirit.contabilidad.entity;

import java.io.Serializable;


/**
 * The EmpleadoCuentaData objects extends the generated Value object.
 * Put here your presentation specific code.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:31 $
 */
public class EmpleadoCuentaData extends EmpleadoCuentaValue implements EmpleadoCuentaIf, Serializable    {

   /**
    * Default constructor.
    */
   public EmpleadoCuentaData() {
   }

   /**
    * Value object constructor.
    */
   public EmpleadoCuentaData(com.spirit.contabilidad.entity.EmpleadoCuentaIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setCuentaId(value.getCuentaId());
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
	  str.append("empleadoId=" + getEmpleadoId()  + " ");
	  str.append("cuentaId=" + getCuentaId()  + " ");
	  str.append('}');
	  return(str.toString());
   }
}
