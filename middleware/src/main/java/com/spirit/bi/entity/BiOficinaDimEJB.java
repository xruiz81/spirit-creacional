package com.spirit.bi.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "BI_OFICINA_DIM")
@Entity
public class BiOficinaDimEJB implements Serializable{
private static final long serialVersionUID = 1L;

   public static final String ID = "id";
   public static final String ORIGENID = "origenId";
   public static final String DESCRIPCION = "descripcion";
   public static final String CIUDADID = "ciudadId";
   public static final String CIUDAD = "ciudad";
   public static final String PROVINCIAID = "provinciaId";
   public static final String PROVINCIA = "provincia";
   public static final String PAISID = "paisId";
   public static final String PAIS = "pais";

public static final String[] ALL_FIELDS=new String[]{
ID,ORIGENID,DESCRIPCION,CIUDADID,CIUDAD,PROVINCIAID,PROVINCIA,PAISID,PAIS
};

public static final String[] ALL_FIELDS_NO_ID=new String[]{
ORIGENID,DESCRIPCION,CIUDADID,CIUDAD,PROVINCIAID,PROVINCIA,PAISID,PAIS
};



   private java.lang.Long id;
   private java.lang.Long origenId;
   private java.lang.String descripcion;
   private java.lang.Long ciudadId;
   private java.lang.String ciudad;
   private java.lang.Long provinciaId;
   private java.lang.String provincia;
   private java.lang.Long paisId;
   private java.lang.String pais;

   public BiOficinaDimEJB() {
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

   @Column(name = "CIUDAD_ID")
   public java.lang.Long getCiudadId() {
      return ciudadId;
   }

   public void setCiudadId(java.lang.Long ciudadId) {
      this.ciudadId = ciudadId;
   }

   @Column(name = "CIUDAD")
   public java.lang.String getCiudad() {
      return ciudad;
   }

   public void setCiudad(java.lang.String ciudad) {
      this.ciudad = ciudad;
   }

   @Column(name = "PROVINCIA_ID")
   public java.lang.Long getProvinciaId() {
      return provinciaId;
   }

   public void setProvinciaId(java.lang.Long provinciaId) {
      this.provinciaId = provinciaId;
   }

   @Column(name = "PROVINCIA")
   public java.lang.String getProvincia() {
      return provincia;
   }

   public void setProvincia(java.lang.String provincia) {
      this.provincia = provincia;
   }

   @Column(name = "PAIS_ID")
   public java.lang.Long getPaisId() {
      return paisId;
   }

   public void setPaisId(java.lang.Long paisId) {
      this.paisId = paisId;
   }

   @Column(name = "PAIS")
   public java.lang.String getPais() {
      return pais;
   }

   public void setPais(java.lang.String pais) {
      this.pais = pais;
   }
	public String toString() {
		return super.toString();
	}
}
