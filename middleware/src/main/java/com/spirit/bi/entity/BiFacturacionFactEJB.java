package com.spirit.bi.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "BI_FACTURACION_FACT")
@Entity
public class BiFacturacionFactEJB implements Serializable{
private static final long serialVersionUID = 1L;

   public static final String ID = "id";
   public static final String BIVENDEDORDIMID = "biVendedorDimId";
   public static final String BICLIENTEDIMID = "biClienteDimId";
   public static final String BIOFICINADIMID = "biOficinaDimId";
   public static final String BIDOCUMENTODIMID = "biDocumentoDimId";
   public static final String BIPRODUCTODIMID = "biProductoDimId";
   public static final String BITIMEDIMID = "biTimeDimId";
   public static final String ORIGENID = "origenId";
   public static final String VALOR = "valor";
   public static final String CANTIDAD = "cantidad";

public static final String[] ALL_FIELDS=new String[]{
ID,BIVENDEDORDIMID,BICLIENTEDIMID,BIOFICINADIMID,BIDOCUMENTODIMID,BIPRODUCTODIMID,BITIMEDIMID,ORIGENID,VALOR,CANTIDAD
};

public static final String[] ALL_FIELDS_NO_ID=new String[]{
BIVENDEDORDIMID,BICLIENTEDIMID,BIOFICINADIMID,BIDOCUMENTODIMID,BIPRODUCTODIMID,BITIMEDIMID,ORIGENID,VALOR,CANTIDAD
};



   private java.lang.Long id;
   private java.lang.Long biVendedorDimId;
   private java.lang.Long biClienteDimId;
   private java.lang.Long biOficinaDimId;
   private java.lang.Long biDocumentoDimId;
   private java.lang.Long biProductoDimId;
   private java.lang.Long biTimeDimId;
   private java.lang.Long origenId;
   private java.math.BigDecimal valor;
   private java.math.BigDecimal cantidad;

   public BiFacturacionFactEJB() {
   }


   @Column(name = "ID")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   @Column(name = "BI_VENDEDOR_DIM_ID")
   public java.lang.Long getBiVendedorDimId() {
      return biVendedorDimId;
   }

   public void setBiVendedorDimId(java.lang.Long biVendedorDimId) {
      this.biVendedorDimId = biVendedorDimId;
   }

   @Column(name = "BI_CLIENTE_DIM_ID")
   public java.lang.Long getBiClienteDimId() {
      return biClienteDimId;
   }

   public void setBiClienteDimId(java.lang.Long biClienteDimId) {
      this.biClienteDimId = biClienteDimId;
   }

   @Column(name = "BI_OFICINA_DIM_ID")
   public java.lang.Long getBiOficinaDimId() {
      return biOficinaDimId;
   }

   public void setBiOficinaDimId(java.lang.Long biOficinaDimId) {
      this.biOficinaDimId = biOficinaDimId;
   }

   @Column(name = "BI_DOCUMENTO_DIM_ID")
   public java.lang.Long getBiDocumentoDimId() {
      return biDocumentoDimId;
   }

   public void setBiDocumentoDimId(java.lang.Long biDocumentoDimId) {
      this.biDocumentoDimId = biDocumentoDimId;
   }

   @Column(name = "BI_PRODUCTO_DIM_ID")
   public java.lang.Long getBiProductoDimId() {
      return biProductoDimId;
   }

   public void setBiProductoDimId(java.lang.Long biProductoDimId) {
      this.biProductoDimId = biProductoDimId;
   }

   @Column(name = "BI_TIME_DIM_ID")
   public java.lang.Long getBiTimeDimId() {
      return biTimeDimId;
   }

   public void setBiTimeDimId(java.lang.Long biTimeDimId) {
      this.biTimeDimId = biTimeDimId;
   }

   @Column(name = "ORIGEN_ID")
   public java.lang.Long getOrigenId() {
      return origenId;
   }

   public void setOrigenId(java.lang.Long origenId) {
      this.origenId = origenId;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "CANTIDAD")
   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }
	public String toString() {
		return super.toString();
	}
}
