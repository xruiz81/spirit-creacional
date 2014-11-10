package com.spirit.pos.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class FacturastipoproductodetalleData implements FacturastipoproductodetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String modelo;

   public java.lang.String getModelo() {
      return modelo;
   }

   public void setModelo(java.lang.String modelo) {
      this.modelo = modelo;
   }

   private java.lang.String color;

   public java.lang.String getColor() {
      return color;
   }

   public void setColor(java.lang.String color) {
      this.color = color;
   }

   private java.lang.String talla;

   public java.lang.String getTalla() {
      return talla;
   }

   public void setTalla(java.lang.String talla) {
      this.talla = talla;
   }

   private java.lang.String tipo;

   public java.lang.String getTipo() {
      return tipo;
   }

   public void setTipo(java.lang.String tipo) {
      this.tipo = tipo;
   }

   private java.lang.Long producto;

   public java.lang.Long getProducto() {
      return producto;
   }

   public void setProducto(java.lang.Long producto) {
      this.producto = producto;
   }

   private java.sql.Timestamp fecha;

   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   private java.math.BigDecimal cant;

   public java.math.BigDecimal getCant() {
      return cant;
   }

   public void setCant(java.math.BigDecimal cant) {
      this.cant = cant;
   }

   private java.math.BigDecimal dev;

   public java.math.BigDecimal getDev() {
      return dev;
   }

   public void setDev(java.math.BigDecimal dev) {
      this.dev = dev;
   }

   private java.math.BigDecimal cantfinal;

   public java.math.BigDecimal getCantfinal() {
      return cantfinal;
   }

   public void setCantfinal(java.math.BigDecimal cantfinal) {
      this.cantfinal = cantfinal;
   }

   private java.math.BigDecimal preciouni;

   public java.math.BigDecimal getPreciouni() {
      return preciouni;
   }

   public void setPreciouni(java.math.BigDecimal preciouni) {
      this.preciouni = preciouni;
   }

   private java.math.BigDecimal valorsinivaventas;

   public java.math.BigDecimal getValorsinivaventas() {
      return valorsinivaventas;
   }

   public void setValorsinivaventas(java.math.BigDecimal valorsinivaventas) {
      this.valorsinivaventas = valorsinivaventas;
   }

   private java.math.BigDecimal descuentoventas;

   public java.math.BigDecimal getDescuentoventas() {
      return descuentoventas;
   }

   public void setDescuentoventas(java.math.BigDecimal descuentoventas) {
      this.descuentoventas = descuentoventas;
   }

   private java.math.BigDecimal ivaventas;

   public java.math.BigDecimal getIvaventas() {
      return ivaventas;
   }

   public void setIvaventas(java.math.BigDecimal ivaventas) {
      this.ivaventas = ivaventas;
   }

   private java.math.BigDecimal totalventas;

   public java.math.BigDecimal getTotalventas() {
      return totalventas;
   }

   public void setTotalventas(java.math.BigDecimal totalventas) {
      this.totalventas = totalventas;
   }

   private java.math.BigDecimal valorsinivadev;

   public java.math.BigDecimal getValorsinivadev() {
      return valorsinivadev;
   }

   public void setValorsinivadev(java.math.BigDecimal valorsinivadev) {
      this.valorsinivadev = valorsinivadev;
   }

   private java.math.BigDecimal ivadev;

   public java.math.BigDecimal getIvadev() {
      return ivadev;
   }

   public void setIvadev(java.math.BigDecimal ivadev) {
      this.ivadev = ivadev;
   }

   private java.math.BigDecimal totaldev;

   public java.math.BigDecimal getTotaldev() {
      return totaldev;
   }

   public void setTotaldev(java.math.BigDecimal totaldev) {
      this.totaldev = totaldev;
   }

   private java.lang.Long modeloId;

   public java.lang.Long getModeloId() {
      return modeloId;
   }

   public void setModeloId(java.lang.Long modeloId) {
      this.modeloId = modeloId;
   }

   private java.lang.Long colorId;

   public java.lang.Long getColorId() {
      return colorId;
   }

   public void setColorId(java.lang.Long colorId) {
      this.colorId = colorId;
   }

   private java.lang.Long tipoproducto;

   public java.lang.Long getTipoproducto() {
      return tipoproducto;
   }

   public void setTipoproducto(java.lang.Long tipoproducto) {
      this.tipoproducto = tipoproducto;
   }

   private java.lang.Long tallaId;

   public java.lang.Long getTallaId() {
      return tallaId;
   }

   public void setTallaId(java.lang.Long tallaId) {
      this.tallaId = tallaId;
   }
   public FacturastipoproductodetalleData() {
   }

   public FacturastipoproductodetalleData(com.spirit.pos.entity.FacturastipoproductodetalleIf value) {
      setId(value.getId());
      setModelo(value.getModelo());
      setColor(value.getColor());
      setTalla(value.getTalla());
      setTipo(value.getTipo());
      setProducto(value.getProducto());
      setFecha(value.getFecha());
      setCant(value.getCant());
      setDev(value.getDev());
      setCantfinal(value.getCantfinal());
      setPreciouni(value.getPreciouni());
      setValorsinivaventas(value.getValorsinivaventas());
      setDescuentoventas(value.getDescuentoventas());
      setIvaventas(value.getIvaventas());
      setTotalventas(value.getTotalventas());
      setValorsinivadev(value.getValorsinivadev());
      setIvadev(value.getIvadev());
      setTotaldev(value.getTotaldev());
      setModeloId(value.getModeloId());
      setColorId(value.getColorId());
      setTipoproducto(value.getTipoproducto());
      setTallaId(value.getTallaId());
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
		return ToStringer.toString((FacturastipoproductodetalleIf)this);
	}
}
