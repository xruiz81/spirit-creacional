package com.spirit.crm.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ClienteWebData implements ClienteWebIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String idExterno;

   public java.lang.String getIdExterno() {
      return idExterno;
   }

   public void setIdExterno(java.lang.String idExterno) {
      this.idExterno = idExterno;
   }

   private java.lang.String nombres;

   public java.lang.String getNombres() {
      return nombres;
   }

   public void setNombres(java.lang.String nombres) {
      this.nombres = nombres;
   }

   private java.lang.String apellidos;

   public java.lang.String getApellidos() {
      return apellidos;
   }

   public void setApellidos(java.lang.String apellidos) {
      this.apellidos = apellidos;
   }

   private java.lang.String email;

   public java.lang.String getEmail() {
      return email;
   }

   public void setEmail(java.lang.String email) {
      this.email = email;
   }

   private java.lang.String pais;

   public java.lang.String getPais() {
      return pais;
   }

   public void setPais(java.lang.String pais) {
      this.pais = pais;
   }

   private java.lang.String ciudad;

   public java.lang.String getCiudad() {
      return ciudad;
   }

   public void setCiudad(java.lang.String ciudad) {
      this.ciudad = ciudad;
   }

   private java.lang.String direccion;

   public java.lang.String getDireccion() {
      return direccion;
   }

   public void setDireccion(java.lang.String direccion) {
      this.direccion = direccion;
   }

   private java.lang.String telefono;

   public java.lang.String getTelefono() {
      return telefono;
   }

   public void setTelefono(java.lang.String telefono) {
      this.telefono = telefono;
   }

   private java.lang.String celular;

   public java.lang.String getCelular() {
      return celular;
   }

   public void setCelular(java.lang.String celular) {
      this.celular = celular;
   }
   public ClienteWebData() {
   }

   public ClienteWebData(com.spirit.crm.entity.ClienteWebIf value) {
      setId(value.getId());
      setIdExterno(value.getIdExterno());
      setNombres(value.getNombres());
      setApellidos(value.getApellidos());
      setEmail(value.getEmail());
      setPais(value.getPais());
      setCiudad(value.getCiudad());
      setDireccion(value.getDireccion());
      setTelefono(value.getTelefono());
      setCelular(value.getCelular());
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
		return ToStringer.toString((ClienteWebIf)this);
	}
}
