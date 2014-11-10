package com.spirit.bi.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "BI_VENDEDOR_DIM")
@Entity
public class BiVendedorDimEJB implements Serializable{
private static final long serialVersionUID = 1L;

   public static final String ID = "id";
   public static final String ORIGENID = "origenId";
   public static final String NOMBRE = "nombre";

public static final String[] ALL_FIELDS=new String[]{
ID,ORIGENID,NOMBRE
};

public static final String[] ALL_FIELDS_NO_ID=new String[]{
ORIGENID,NOMBRE
};



   private java.lang.Long id;
   private java.lang.Long origenId;
   private java.lang.String nombre;

   public BiVendedorDimEJB() {
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
	public String toString() {
		return super.toString();
	}
}
