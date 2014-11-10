package com.spirit.crm.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ClienteContactoData implements ClienteContactoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long tipocontactoId;

   public java.lang.Long getTipocontactoId() {
      return tipocontactoId;
   }

   public void setTipocontactoId(java.lang.Long tipocontactoId) {
      this.tipocontactoId = tipocontactoId;
   }

   private java.lang.Long clienteoficinaId;

   public java.lang.Long getClienteoficinaId() {
      return clienteoficinaId;
   }

   public void setClienteoficinaId(java.lang.Long clienteoficinaId) {
      this.clienteoficinaId = clienteoficinaId;
   }

   private java.lang.String nombre;

   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   private java.lang.String direccion;

   public java.lang.String getDireccion() {
      return direccion;
   }

   public void setDireccion(java.lang.String direccion) {
      this.direccion = direccion;
   }

   private java.lang.String telefonoOfic;

   public java.lang.String getTelefonoOfic() {
      return telefonoOfic;
   }

   public void setTelefonoOfic(java.lang.String telefonoOfic) {
      this.telefonoOfic = telefonoOfic;
   }

   private java.lang.String telefonoCasa;

   public java.lang.String getTelefonoCasa() {
      return telefonoCasa;
   }

   public void setTelefonoCasa(java.lang.String telefonoCasa) {
      this.telefonoCasa = telefonoCasa;
   }

   private java.lang.String celular;

   public java.lang.String getCelular() {
      return celular;
   }

   public void setCelular(java.lang.String celular) {
      this.celular = celular;
   }

   private java.lang.String mail;

   public java.lang.String getMail() {
      return mail;
   }

   public void setMail(java.lang.String mail) {
      this.mail = mail;
   }

   private java.sql.Timestamp cumpleanos;

   public java.sql.Timestamp getCumpleanos() {
      return cumpleanos;
   }

   public void setCumpleanos(java.sql.Timestamp cumpleanos) {
      this.cumpleanos = cumpleanos;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }
   public ClienteContactoData() {
   }

   public ClienteContactoData(com.spirit.crm.entity.ClienteContactoIf value) {
      setId(value.getId());
      setTipocontactoId(value.getTipocontactoId());
      setClienteoficinaId(value.getClienteoficinaId());
      setNombre(value.getNombre());
      setDireccion(value.getDireccion());
      setTelefonoOfic(value.getTelefonoOfic());
      setTelefonoCasa(value.getTelefonoCasa());
      setCelular(value.getCelular());
      setMail(value.getMail());
      setCumpleanos(value.getCumpleanos());
      setCodigo(value.getCodigo());
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
		return ToStringer.toString((ClienteContactoIf)this);
	}
}
