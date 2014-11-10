package com.spirit.bi.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "BI_CLIENTE_DIM")
@Entity
public class BiClienteDimEJB implements Serializable{
private static final long serialVersionUID = 1L;

   public static final String ID = "id";
   public static final String ORIGENID = "origenId";
   public static final String NOMBRE = "nombre";
   public static final String TIPOCLIENTEID = "tipoClienteId";
   public static final String TIPOCLIENTE = "tipoCliente";
   public static final String TIPOPROVEEDORID = "tipoProveedorId";
   public static final String TIPOPROVEEDOR = "tipoProveedor";

public static final String[] ALL_FIELDS=new String[]{
ID,ORIGENID,NOMBRE,TIPOCLIENTEID,TIPOCLIENTE,TIPOPROVEEDORID,TIPOPROVEEDOR
};

public static final String[] ALL_FIELDS_NO_ID=new String[]{
ORIGENID,NOMBRE,TIPOCLIENTEID,TIPOCLIENTE,TIPOPROVEEDORID,TIPOPROVEEDOR
};



   private java.lang.Long id;
   private java.lang.Long origenId;
   private java.lang.String nombre;
   private java.lang.Long tipoClienteId;
   private java.lang.String tipoCliente;
   private java.lang.Long tipoProveedorId;
   private java.lang.String tipoProveedor;

   public BiClienteDimEJB() {
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

   @Column(name = "NOMBRE")
   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   @Column(name = "TIPO_CLIENTE_ID")
   public java.lang.Long getTipoClienteId() {
      return tipoClienteId;
   }

   public void setTipoClienteId(java.lang.Long tipoClienteId) {
      this.tipoClienteId = tipoClienteId;
   }

   @Column(name = "TIPO_CLIENTE")
   public java.lang.String getTipoCliente() {
      return tipoCliente;
   }

   public void setTipoCliente(java.lang.String tipoCliente) {
      this.tipoCliente = tipoCliente;
   }

   @Column(name = "TIPO_PROVEEDOR_ID")
   public java.lang.Long getTipoProveedorId() {
      return tipoProveedorId;
   }

   public void setTipoProveedorId(java.lang.Long tipoProveedorId) {
      this.tipoProveedorId = tipoProveedorId;
   }

   @Column(name = "TIPO_PROVEEDOR")
   public java.lang.String getTipoProveedor() {
      return tipoProveedor;
   }

   public void setTipoProveedor(java.lang.String tipoProveedor) {
      this.tipoProveedor = tipoProveedor;
   }
	public String toString() {
		return super.toString();
	}
}
