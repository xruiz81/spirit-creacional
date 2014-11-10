package com.spirit.crm.entity;

import java.io.Serializable;


/**
 * The GastoElectoralData objects extends the generated Value object.
 * Put here your presentation specific code.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:38 $
 */
public class GastoElectoralData extends GastoElectoralValue implements GastoElectoralIf, Serializable    {

   /**
    * Default constructor.
    */
   public GastoElectoralData() {
   }

   /**
    * Value object constructor.
    */
   public GastoElectoralData(com.spirit.crm.entity.GastoElectoralIf value) {
      setId(value.getId());
      setCampana(value.getCampana());
      setFecha(value.getFecha());
      setTipo(value.getTipo());
      setProducto(value.getProducto());
      setProveedor(value.getProveedor());
      setDescripcion(value.getDescripcion());
      setTamano(value.getTamano());
      setCantidad(value.getCantidad());
      setCostoUnitario(value.getCostoUnitario());
      setInversionConFactura(value.getInversionConFactura());
      setInversionSinFactura(value.getInversionSinFactura());
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
	  str.append("campana=" + getCampana()  + " ");
	  str.append("fecha=" + getFecha()  + " ");
	  str.append("tipo=" + getTipo()  + " ");
	  str.append("producto=" + getProducto()  + " ");
	  str.append("proveedor=" + getProveedor()  + " ");
	  str.append("descripcion=" + getDescripcion()  + " ");
	  str.append("tamano=" + getTamano()  + " ");
	  str.append("cantidad=" + getCantidad()  + " ");
	  str.append("costoUnitario=" + getCostoUnitario()  + " ");
	  str.append("inversionConFactura=" + getInversionConFactura()  + " ");
	  str.append("inversionSinFactura=" + getInversionSinFactura()  + " ");
	  str.append('}');
	  return(str.toString());
   }
}
