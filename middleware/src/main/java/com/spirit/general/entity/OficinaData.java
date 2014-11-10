package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class OficinaData implements OficinaIf, Serializable    {


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

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   private java.lang.Long ciudadId;

   public java.lang.Long getCiudadId() {
      return ciudadId;
   }

   public void setCiudadId(java.lang.Long ciudadId) {
      this.ciudadId = ciudadId;
   }

   private java.lang.Long administradorId;

   public java.lang.Long getAdministradorId() {
      return administradorId;
   }

   public void setAdministradorId(java.lang.Long administradorId) {
      this.administradorId = administradorId;
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

   private java.lang.String fax;

   public java.lang.String getFax() {
      return fax;
   }

   public void setFax(java.lang.String fax) {
      this.fax = fax;
   }

   private java.lang.String preimpresoSerie;

   public java.lang.String getPreimpresoSerie() {
      return preimpresoSerie;
   }

   public void setPreimpresoSerie(java.lang.String preimpresoSerie) {
      this.preimpresoSerie = preimpresoSerie;
   }

   private java.lang.Long servidorId;

   public java.lang.Long getServidorId() {
      return servidorId;
   }

   public void setServidorId(java.lang.Long servidorId) {
      this.servidorId = servidorId;
   }
   public OficinaData() {
   }

   public OficinaData(com.spirit.general.entity.OficinaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setCiudadId(value.getCiudadId());
      setAdministradorId(value.getAdministradorId());
      setDireccion(value.getDireccion());
      setTelefono(value.getTelefono());
      setFax(value.getFax());
      setPreimpresoSerie(value.getPreimpresoSerie());
      setServidorId(value.getServidorId());
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
		return ToStringer.toString((OficinaIf)this);
	}
}
