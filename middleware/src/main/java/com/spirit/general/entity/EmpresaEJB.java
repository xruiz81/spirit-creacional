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

@Table(name = "EMPRESA")
@Entity
public class EmpresaEJB implements Serializable, EmpresaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.String logo;
   private java.lang.String ruc;
   private java.lang.String web;
   private java.lang.String emailContador;
   private java.lang.Long tipoIdRepresentante;
   private java.lang.String numeroIdentificacion;
   private java.lang.String rucContador;

   public EmpresaEJB() {
   }

   public EmpresaEJB(com.spirit.general.entity.EmpresaIf value) {
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

   public java.lang.Long create(com.spirit.general.entity.EmpresaIf value) {
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

   @Column(name = "LOGO")
   public java.lang.String getLogo() {
      return logo;
   }

   public void setLogo(java.lang.String logo) {
      this.logo = logo;
   }

   @Column(name = "RUC")
   public java.lang.String getRuc() {
      return ruc;
   }

   public void setRuc(java.lang.String ruc) {
      this.ruc = ruc;
   }

   @Column(name = "WEB")
   public java.lang.String getWeb() {
      return web;
   }

   public void setWeb(java.lang.String web) {
      this.web = web;
   }

   @Column(name = "EMAIL_CONTADOR")
   public java.lang.String getEmailContador() {
      return emailContador;
   }

   public void setEmailContador(java.lang.String emailContador) {
      this.emailContador = emailContador;
   }

   @Column(name = "TIPO_ID_REPRESENTANTE")
   public java.lang.Long getTipoIdRepresentante() {
      return tipoIdRepresentante;
   }

   public void setTipoIdRepresentante(java.lang.Long tipoIdRepresentante) {
      this.tipoIdRepresentante = tipoIdRepresentante;
   }

   @Column(name = "NUMERO_IDENTIFICACION")
   public java.lang.String getNumeroIdentificacion() {
      return numeroIdentificacion;
   }

   public void setNumeroIdentificacion(java.lang.String numeroIdentificacion) {
      this.numeroIdentificacion = numeroIdentificacion;
   }

   @Column(name = "RUC_CONTADOR")
   public java.lang.String getRucContador() {
      return rucContador;
   }

   public void setRucContador(java.lang.String rucContador) {
      this.rucContador = rucContador;
   }
	public String toString() {
		return ToStringer.toString((EmpresaIf)this);
	}
}
