package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class EmpresaData implements EmpresaIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String nombre;

   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   private java.lang.String logo;

   public java.lang.String getLogo() {
      return logo;
   }

   public void setLogo(java.lang.String logo) {
      this.logo = logo;
   }

   private java.lang.String ruc;

   public java.lang.String getRuc() {
      return ruc;
   }

   public void setRuc(java.lang.String ruc) {
      this.ruc = ruc;
   }

   private java.lang.String web;

   public java.lang.String getWeb() {
      return web;
   }

   public void setWeb(java.lang.String web) {
      this.web = web;
   }

   private java.lang.String emailContador;

   public java.lang.String getEmailContador() {
      return emailContador;
   }

   public void setEmailContador(java.lang.String emailContador) {
      this.emailContador = emailContador;
   }

   private java.lang.Long tipoIdRepresentante;

   public java.lang.Long getTipoIdRepresentante() {
      return tipoIdRepresentante;
   }

   public void setTipoIdRepresentante(java.lang.Long tipoIdRepresentante) {
      this.tipoIdRepresentante = tipoIdRepresentante;
   }

   private java.lang.String numeroIdentificacion;

   public java.lang.String getNumeroIdentificacion() {
      return numeroIdentificacion;
   }

   public void setNumeroIdentificacion(java.lang.String numeroIdentificacion) {
      this.numeroIdentificacion = numeroIdentificacion;
   }

   private java.lang.String rucContador;

   public java.lang.String getRucContador() {
      return rucContador;
   }

   public void setRucContador(java.lang.String rucContador) {
      this.rucContador = rucContador;
   }
   public EmpresaData() {
   }

   public EmpresaData(com.spirit.general.entity.EmpresaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setLogo(value.getLogo());
      setRuc(value.getRuc());
      setWeb(value.getWeb());
      setEmailContador(value.getEmailContador());
      setTipoIdRepresentante(value.getTipoIdRepresentante());
      setNumeroIdentificacion(value.getNumeroIdentificacion());
      setRucContador(value.getRucContador());
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
		return ToStringer.toString((EmpresaIf)this);
	}
}
