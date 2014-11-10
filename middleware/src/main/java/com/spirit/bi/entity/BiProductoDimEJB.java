package com.spirit.bi.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "BI_PRODUCTO_DIM")
@Entity
public class BiProductoDimEJB implements Serializable{
private static final long serialVersionUID = 1L;

   public static final String ID = "id";
   public static final String ORIGENID = "origenId";
   public static final String DESCRIPCION = "descripcion";
   public static final String SERVICIO = "servicio";
   public static final String GENERICOID = "genericoId";
   public static final String GENERICO = "generico";
   public static final String PRESENTACIONID = "presentacionId";
   public static final String PRESENTACION = "presentacion";
   public static final String PROVEEDORID = "proveedorId";
   public static final String PROVEEDOR = "proveedor";
   public static final String COLORID = "colorId";
   public static final String COLOR = "color";
   public static final String MARCAID = "marcaId";
   public static final String MARCA = "marca";
   public static final String MODELOID = "modeloId";
   public static final String MODELO = "modelo";
   public static final String TIPOPRODUCTOID = "tipoProductoId";
   public static final String TIPOPRODUCTO = "tipoProducto";

public static final String[] ALL_FIELDS=new String[]{
ID,ORIGENID,DESCRIPCION,SERVICIO,GENERICOID,GENERICO,PRESENTACIONID,PRESENTACION,PROVEEDORID,PROVEEDOR,COLORID,COLOR,MARCAID,MARCA,MODELOID,MODELO,TIPOPRODUCTOID,TIPOPRODUCTO
};

public static final String[] ALL_FIELDS_NO_ID=new String[]{
ORIGENID,DESCRIPCION,SERVICIO,GENERICOID,GENERICO,PRESENTACIONID,PRESENTACION,PROVEEDORID,PROVEEDOR,COLORID,COLOR,MARCAID,MARCA,MODELOID,MODELO,TIPOPRODUCTOID,TIPOPRODUCTO
};



   private java.lang.Long id;
   private java.lang.Long origenId;
   private java.lang.String descripcion;
   private java.lang.String servicio;
   private java.lang.Long genericoId;
   private java.lang.String generico;
   private java.lang.Long presentacionId;
   private java.lang.String presentacion;
   private java.lang.Long proveedorId;
   private java.lang.String proveedor;
   private java.lang.Long colorId;
   private java.lang.String color;
   private java.lang.Long marcaId;
   private java.lang.String marca;
   private java.lang.Long modeloId;
   private java.lang.String modelo;
   private java.lang.Long tipoProductoId;
   private java.lang.String tipoProducto;

   public BiProductoDimEJB() {
   }


   @Column(name = "ID")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   @Column(name = "ORIGEN_ID")
   public java.lang.Long getOrigenId() {
      return origenId;
   }

   public void setOrigenId(java.lang.Long origenId) {
      this.origenId = origenId;
   }

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "SERVICIO")
   public java.lang.String getServicio() {
      return servicio;
   }

   public void setServicio(java.lang.String servicio) {
      this.servicio = servicio;
   }

   @Column(name = "GENERICO_ID")
   public java.lang.Long getGenericoId() {
      return genericoId;
   }

   public void setGenericoId(java.lang.Long genericoId) {
      this.genericoId = genericoId;
   }

   @Column(name = "GENERICO")
   public java.lang.String getGenerico() {
      return generico;
   }

   public void setGenerico(java.lang.String generico) {
      this.generico = generico;
   }

   @Column(name = "PRESENTACION_ID")
   public java.lang.Long getPresentacionId() {
      return presentacionId;
   }

   public void setPresentacionId(java.lang.Long presentacionId) {
      this.presentacionId = presentacionId;
   }

   @Column(name = "PRESENTACION")
   public java.lang.String getPresentacion() {
      return presentacion;
   }

   public void setPresentacion(java.lang.String presentacion) {
      this.presentacion = presentacion;
   }

   @Column(name = "PROVEEDOR_ID")
   public java.lang.Long getProveedorId() {
      return proveedorId;
   }

   public void setProveedorId(java.lang.Long proveedorId) {
      this.proveedorId = proveedorId;
   }

   @Column(name = "PROVEEDOR")
   public java.lang.String getProveedor() {
      return proveedor;
   }

   public void setProveedor(java.lang.String proveedor) {
      this.proveedor = proveedor;
   }

   @Column(name = "COLOR_ID")
   public java.lang.Long getColorId() {
      return colorId;
   }

   public void setColorId(java.lang.Long colorId) {
      this.colorId = colorId;
   }

   @Column(name = "COLOR")
   public java.lang.String getColor() {
      return color;
   }

   public void setColor(java.lang.String color) {
      this.color = color;
   }

   @Column(name = "MARCA_ID")
   public java.lang.Long getMarcaId() {
      return marcaId;
   }

   public void setMarcaId(java.lang.Long marcaId) {
      this.marcaId = marcaId;
   }

   @Column(name = "MARCA")
   public java.lang.String getMarca() {
      return marca;
   }

   public void setMarca(java.lang.String marca) {
      this.marca = marca;
   }

   @Column(name = "MODELO_ID")
   public java.lang.Long getModeloId() {
      return modeloId;
   }

   public void setModeloId(java.lang.Long modeloId) {
      this.modeloId = modeloId;
   }

   @Column(name = "MODELO")
   public java.lang.String getModelo() {
      return modelo;
   }

   public void setModelo(java.lang.String modelo) {
      this.modelo = modelo;
   }

   @Column(name = "TIPO_PRODUCTO_ID")
   public java.lang.Long getTipoProductoId() {
      return tipoProductoId;
   }

   public void setTipoProductoId(java.lang.Long tipoProductoId) {
      this.tipoProductoId = tipoProductoId;
   }

   @Column(name = "TIPO_PRODUCTO")
   public java.lang.String getTipoProducto() {
      return tipoProducto;
   }

   public void setTipoProducto(java.lang.String tipoProducto) {
      this.tipoProducto = tipoProducto;
   }
	public String toString() {
		return super.toString();
	}
}
