package com.spirit.general.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "OFICINA")
@Entity
public class OficinaEJB implements Serializable, OficinaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long empresaId;
   private java.lang.Long ciudadId;
   private java.lang.Long administradorId;
   private java.lang.String direccion;
   private java.lang.String telefono;
   private java.lang.String fax;
   private java.lang.String preimpresoSerie;
   private java.lang.Long servidorId;

   public OficinaEJB() {
   }

   public OficinaEJB(com.spirit.general.entity.OficinaIf value) {
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

   public java.lang.Long create(com.spirit.general.entity.OficinaIf value) {
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

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "NOMBRE")
   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "CIUDAD_ID")
   public java.lang.Long getCiudadId() {
      return ciudadId;
   }

   public void setCiudadId(java.lang.Long ciudadId) {
      this.ciudadId = ciudadId;
   }

   @Column(name = "ADMINISTRADOR_ID")
   public java.lang.Long getAdministradorId() {
      return administradorId;
   }

   public void setAdministradorId(java.lang.Long administradorId) {
      this.administradorId = administradorId;
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

   @Column(name = "FAX")
   public java.lang.String getFax() {
      return fax;
   }

   public void setFax(java.lang.String fax) {
      this.fax = fax;
   }

   @Column(name = "PREIMPRESO_SERIE")
   public java.lang.String getPreimpresoSerie() {
      return preimpresoSerie;
   }

   public void setPreimpresoSerie(java.lang.String preimpresoSerie) {
      this.preimpresoSerie = preimpresoSerie;
   }

   @Column(name = "SERVIDOR_ID")
   public java.lang.Long getServidorId() {
      return servidorId;
   }

   public void setServidorId(java.lang.Long servidorId) {
      this.servidorId = servidorId;
   }
	public String toString() {
		return ToStringer.toString((OficinaIf)this);
	}
}
