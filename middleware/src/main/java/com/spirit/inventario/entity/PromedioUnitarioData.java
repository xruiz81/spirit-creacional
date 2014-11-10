package com.spirit.inventario.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PromedioUnitarioData implements PromedioUnitarioIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long pid;

   public java.lang.Long getPid() {
      return pid;
   }

   public void setPid(java.lang.Long pid) {
      this.pid = pid;
   }

   private java.lang.String sku;

   public java.lang.String getSku() {
      return sku;
   }

   public void setSku(java.lang.String sku) {
      this.sku = sku;
   }

   private java.lang.String modelo;

   public java.lang.String getModelo() {
      return modelo;
   }

   public void setModelo(java.lang.String modelo) {
      this.modelo = modelo;
   }

   private java.lang.String talla;

   public java.lang.String getTalla() {
      return talla;
   }

   public void setTalla(java.lang.String talla) {
      this.talla = talla;
   }

   private java.lang.String color;

   public java.lang.String getColor() {
      return color;
   }

   public void setColor(java.lang.String color) {
      this.color = color;
   }

   private java.sql.Timestamp fecha;

   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   private java.lang.Long bodegaId;

   public java.lang.Long getBodegaId() {
      return bodegaId;
   }

   public void setBodegaId(java.lang.Long bodegaId) {
      this.bodegaId = bodegaId;
   }

   private java.math.BigDecimal promedioUnitario;

   public java.math.BigDecimal getPromedioUnitario() {
      return promedioUnitario;
   }

   public void setPromedioUnitario(java.math.BigDecimal promedioUnitario) {
      this.promedioUnitario = promedioUnitario;
   }
   public PromedioUnitarioData() {
   }

   public PromedioUnitarioData(com.spirit.inventario.entity.PromedioUnitarioIf value) {
      setId(value.getId());
      setPid(value.getPid());
      setSku(value.getSku());
      setModelo(value.getModelo());
      setTalla(value.getTalla());
      setColor(value.getColor());
      setFecha(value.getFecha());
      setBodegaId(value.getBodegaId());
      setPromedioUnitario(value.getPromedioUnitario());
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
		return ToStringer.toString((PromedioUnitarioIf)this);
	}
}
