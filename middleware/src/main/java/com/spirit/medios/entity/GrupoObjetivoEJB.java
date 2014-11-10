package com.spirit.medios.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "GRUPO_OBJETIVO")
@Entity
public class GrupoObjetivoEJB implements Serializable, GrupoObjetivoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long empresaId;
   private java.lang.String nivelSocioEconomico;
   private java.math.BigDecimal ciudad1;
   private java.math.BigDecimal ciudad2;
   private java.math.BigDecimal ciudad3;

   public GrupoObjetivoEJB() {
   }

   public GrupoObjetivoEJB(com.spirit.medios.entity.GrupoObjetivoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setNivelSocioEconomico(value.getNivelSocioEconomico());
      setCiudad1(value.getCiudad1());
      setCiudad2(value.getCiudad2());
      setCiudad3(value.getCiudad3());
   }

   public java.lang.Long create(com.spirit.medios.entity.GrupoObjetivoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setNivelSocioEconomico(value.getNivelSocioEconomico());
      setCiudad1(value.getCiudad1());
      setCiudad2(value.getCiudad2());
      setCiudad3(value.getCiudad3());
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

   @Column(name = "NIVEL_SOCIO_ECONOMICO")
   public java.lang.String getNivelSocioEconomico() {
      return nivelSocioEconomico;
   }

   public void setNivelSocioEconomico(java.lang.String nivelSocioEconomico) {
      this.nivelSocioEconomico = nivelSocioEconomico;
   }

   @Column(name = "CIUDAD1")
   public java.math.BigDecimal getCiudad1() {
      return ciudad1;
   }

   public void setCiudad1(java.math.BigDecimal ciudad1) {
      this.ciudad1 = ciudad1;
   }

   @Column(name = "CIUDAD2")
   public java.math.BigDecimal getCiudad2() {
      return ciudad2;
   }

   public void setCiudad2(java.math.BigDecimal ciudad2) {
      this.ciudad2 = ciudad2;
   }

   @Column(name = "CIUDAD3")
   public java.math.BigDecimal getCiudad3() {
      return ciudad3;
   }

   public void setCiudad3(java.math.BigDecimal ciudad3) {
      this.ciudad3 = ciudad3;
   }
	public String toString() {
		return ToStringer.toString((GrupoObjetivoIf)this);
	}
}
