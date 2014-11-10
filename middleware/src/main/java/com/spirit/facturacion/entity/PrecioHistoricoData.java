package com.spirit.facturacion.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PrecioHistoricoData implements PrecioHistoricoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long precioId;

   public java.lang.Long getPrecioId() {
      return precioId;
   }

   public void setPrecioId(java.lang.Long precioId) {
      this.precioId = precioId;
   }

   private java.sql.Date fechaini;

   public java.sql.Date getFechaini() {
      return fechaini;
   }

   public void setFechaini(java.sql.Date fechaini) {
      this.fechaini = fechaini;
   }

   private java.sql.Date fechafin;

   public java.sql.Date getFechafin() {
      return fechafin;
   }

   public void setFechafin(java.sql.Date fechafin) {
      this.fechafin = fechafin;
   }

   private java.math.BigDecimal precioHis;

   public java.math.BigDecimal getPrecioHis() {
      return precioHis;
   }

   public void setPrecioHis(java.math.BigDecimal precioHis) {
      this.precioHis = precioHis;
   }

   private java.math.BigDecimal precio;

   public java.math.BigDecimal getPrecio() {
      return precio;
   }

   public void setPrecio(java.math.BigDecimal precio) {
      this.precio = precio;
   }
   public PrecioHistoricoData() {
   }

   public PrecioHistoricoData(com.spirit.facturacion.entity.PrecioHistoricoIf value) {
      setId(value.getId());
      setPrecioId(value.getPrecioId());
      setFechaini(value.getFechaini());
      setFechafin(value.getFechafin());
      setPrecioHis(value.getPrecioHis());
      setPrecio(value.getPrecio());
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
		return ToStringer.toString((PrecioHistoricoIf)this);
	}
}
