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

@Table(name = "CLIENTE_CONTACTO")
@Entity
public class ClienteContactoEJB implements Serializable, ClienteContactoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long tipocontactoId;
   private java.lang.Long clienteoficinaId;
   private java.lang.String nombre;
   private java.lang.String direccion;
   private java.lang.String telefonoOfic;
   private java.lang.String telefonoCasa;
   private java.lang.String celular;
   private java.lang.String mail;
   private java.sql.Timestamp cumpleanos;
   private java.lang.String codigo;

   public ClienteContactoEJB() {
   }

   public ClienteContactoEJB(com.spirit.crm.entity.ClienteContactoIf value) {
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

   public java.lang.Long create(com.spirit.crm.entity.ClienteContactoIf value) {
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

   @Column(name = "TIPOCONTACTO_ID")
   public java.lang.Long getTipocontactoId() {
      return tipocontactoId;
   }

   public void setTipocontactoId(java.lang.Long tipocontactoId) {
      this.tipocontactoId = tipocontactoId;
   }

   @Column(name = "CLIENTEOFICINA_ID")
   public java.lang.Long getClienteoficinaId() {
      return clienteoficinaId;
   }

   public void setClienteoficinaId(java.lang.Long clienteoficinaId) {
      this.clienteoficinaId = clienteoficinaId;
   }

   @Column(name = "NOMBRE")
   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   @Column(name = "DIRECCION")
   public java.lang.String getDireccion() {
      return direccion;
   }

   public void setDireccion(java.lang.String direccion) {
      this.direccion = direccion;
   }

   @Column(name = "TELEFONO_OFIC")
   public java.lang.String getTelefonoOfic() {
      return telefonoOfic;
   }

   public void setTelefonoOfic(java.lang.String telefonoOfic) {
      this.telefonoOfic = telefonoOfic;
   }

   @Column(name = "TELEFONO_CASA")
   public java.lang.String getTelefonoCasa() {
      return telefonoCasa;
   }

   public void setTelefonoCasa(java.lang.String telefonoCasa) {
      this.telefonoCasa = telefonoCasa;
   }

   @Column(name = "CELULAR")
   public java.lang.String getCelular() {
      return celular;
   }

   public void setCelular(java.lang.String celular) {
      this.celular = celular;
   }

   @Column(name = "MAIL")
   public java.lang.String getMail() {
      return mail;
   }

   public void setMail(java.lang.String mail) {
      this.mail = mail;
   }

   @Column(name = "CUMPLEANOS")
   public java.sql.Timestamp getCumpleanos() {
      return cumpleanos;
   }

   public void setCumpleanos(java.sql.Timestamp cumpleanos) {
      this.cumpleanos = cumpleanos;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }
	public String toString() {
		return ToStringer.toString((ClienteContactoIf)this);
	}
}
