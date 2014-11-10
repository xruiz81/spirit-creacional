package com.spirit.poscola.entity;

import java.io.Serializable;


/**
 * The PosColaData objects extends the generated Value object.
 * Put here your presentation specific code.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:42 $
 */
public class PosColaData extends PosColaValue implements PosColaIf, Serializable    {

   /**
    * Default constructor.
    */
   public PosColaData() {
   }

   /**
    * Value object constructor.
    */
   public PosColaData(com.spirit.poscola.entity.PosColaIf value) {
      setId(value.getId());
      setDireccionIp(value.getDireccionIp());
      setHostName(value.getHostName());
      setPort(value.getPort());
      setFactory(value.getFactory());
      setQname(value.getQname());
      setPosName(value.getPosName());
      setBodegaId(value.getBodegaId());
      setOficinaId(value.getOficinaId());
      setTipoServer(value.getTipoServer());
      setMe(value.getMe());
   }



    /**
     * Return the primary key.
     *
     * @return java.lang.Integer with the primary key.
     */
    public java.lang.Integer getPrimaryKey() {
        return getId();
    }

    /**
     * Set the primary key.
     *
     * @param pk the primary key
     */
    public void setPrimaryKey(java.lang.Integer pk) {
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
	  str.append("direccionIp=" + getDireccionIp()  + " ");
	  str.append("hostName=" + getHostName()  + " ");
	  str.append("port=" + getPort()  + " ");
	  str.append("factory=" + getFactory()  + " ");
	  str.append("qname=" + getQname()  + " ");
	  str.append("posName=" + getPosName()  + " ");
	  str.append("bodegaId=" + getBodegaId()  + " ");
	  str.append("oficinaId=" + getOficinaId()  + " ");
	  str.append("tipoServer=" + getTipoServer()  + " ");
	  str.append("me=" + getMe()  + " ");
	  str.append('}');
	  return(str.toString());
   }
}
