package com.spirit.crm.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "CLIENTE_WEB")
@Entity
public class ClienteWebEJB implements Serializable, ClienteWebIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String idExterno;
   private java.lang.String nombres;
   private java.lang.String apellidos;
   private java.lang.String email;
   private java.lang.String pais;
   private java.lang.String ciudad;
   private java.lang.String direccion;
   private java.lang.String telefono;
   private java.lang.String celular;

   public ClienteWebEJB() {
   }

   public ClienteWebEJB(com.spirit.crm.entity.ClienteWebIf value) {
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

   public java.lang.Long create(com.spirit.crm.entity.ClienteWebIf value) {
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
      return value.getPrimaryKey();
   }

   @javax.persistence.Transient public java.lang.Long getPrimaryKey() {
        return getId();
    }

   @javax.persistence.Transient public void setPrimaryKey(java.lang.Long primaryKey) {
       setId(primaryKey);
    }

   @Column(name = "ID")
@TableGenerator(name="SEQ_GEN",
			allocationSize=HibernateSequenceAllocationSize.allocationSize
)
   @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_GEN")
   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   @Column(name = "ID_EXTERNO")
   public java.lang.String getIdExterno() {
      return idExterno;
   }

   public void setIdExterno(java.lang.String idExterno) {
      this.idExterno = idExterno;
   }

   @Column(name = "NOMBRES")
   public java.lang.String getNombres() {
      return nombres;
   }

   public void setNombres(java.lang.String nombres) {
      this.nombres = nombres;
   }

   @Column(name = "APELLIDOS")
   public java.lang.String getApellidos() {
      return apellidos;
   }

   public void setApellidos(java.lang.String apellidos) {
      this.apellidos = apellidos;
   }

   @Column(name = "EMAIL")
   public java.lang.String getEmail() {
      return email;
   }

   public void setEmail(java.lang.String email) {
      this.email = email;
   }

   @Column(name = "PAIS")
   public java.lang.String getPais() {
      return pais;
   }

   public void setPais(java.lang.String pais) {
      this.pais = pais;
   }

   @Column(name = "CIUDAD")
   public java.lang.String getCiudad() {
      return ciudad;
   }

   public void setCiudad(java.lang.String ciudad) {
      this.ciudad = ciudad;
   }

   @Column(name = "DIRECCION")
   public java.lang.String getDireccion() {
      return direccion;
   }

   public void setDireccion(java.lang.String direccion) {
      this.direccion = direccion;
   }

   @Column(name = "TELEFONO")
   public java.lang.String getTelefono() {
      return telefono;
   }

   public void setTelefono(java.lang.String telefono) {
      this.telefono = telefono;
   }

   @Column(name = "CELULAR")
   public java.lang.String getCelular() {
      return celular;
   }

   public void setCelular(java.lang.String celular) {
      this.celular = celular;
   }
	public String toString() {
		return ToStringer.toString((ClienteWebIf)this);
	}
}
