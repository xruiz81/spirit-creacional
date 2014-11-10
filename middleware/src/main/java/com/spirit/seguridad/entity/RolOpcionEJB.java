package com.spirit.seguridad.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "ROL_OPCION")
@Entity
public class RolOpcionEJB implements Serializable, RolOpcionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long rolId;
   private java.lang.Long menuId;
   private java.lang.String ninguno;
   private java.lang.String grabarActualizar;
   private java.lang.String borrar;
   private java.lang.String consultar;
   private java.lang.String autorizar;
   private java.lang.String imprimir;
   private java.lang.String generarGrafico;
   private java.lang.String duplicar;

   public RolOpcionEJB() {
   }

   public RolOpcionEJB(com.spirit.seguridad.entity.RolOpcionIf value) {
      setId(value.getId());
      setRolId(value.getRolId());
      setMenuId(value.getMenuId());
      setNinguno(value.getNinguno());
      setGrabarActualizar(value.getGrabarActualizar());
      setBorrar(value.getBorrar());
      setConsultar(value.getConsultar());
      setAutorizar(value.getAutorizar());
      setImprimir(value.getImprimir());
      setGenerarGrafico(value.getGenerarGrafico());
      setDuplicar(value.getDuplicar());
   }

   public java.lang.Long create(com.spirit.seguridad.entity.RolOpcionIf value) {
      setId(value.getId());
      setRolId(value.getRolId());
      setMenuId(value.getMenuId());
      setNinguno(value.getNinguno());
      setGrabarActualizar(value.getGrabarActualizar());
      setBorrar(value.getBorrar());
      setConsultar(value.getConsultar());
      setAutorizar(value.getAutorizar());
      setImprimir(value.getImprimir());
      setGenerarGrafico(value.getGenerarGrafico());
      setDuplicar(value.getDuplicar());
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

   @Column(name = "ROL_ID")
   public java.lang.Long getRolId() {
      return rolId;
   }

   public void setRolId(java.lang.Long rolId) {
      this.rolId = rolId;
   }

   @Column(name = "MENU_ID")
   public java.lang.Long getMenuId() {
      return menuId;
   }

   public void setMenuId(java.lang.Long menuId) {
      this.menuId = menuId;
   }

   @Column(name = "NINGUNO")
   public java.lang.String getNinguno() {
      return ninguno;
   }

   public void setNinguno(java.lang.String ninguno) {
      this.ninguno = ninguno;
   }

   @Column(name = "GRABAR_ACTUALIZAR")
   public java.lang.String getGrabarActualizar() {
      return grabarActualizar;
   }

   public void setGrabarActualizar(java.lang.String grabarActualizar) {
      this.grabarActualizar = grabarActualizar;
   }

   @Column(name = "BORRAR")
   public java.lang.String getBorrar() {
      return borrar;
   }

   public void setBorrar(java.lang.String borrar) {
      this.borrar = borrar;
   }

   @Column(name = "CONSULTAR")
   public java.lang.String getConsultar() {
      return consultar;
   }

   public void setConsultar(java.lang.String consultar) {
      this.consultar = consultar;
   }

   @Column(name = "AUTORIZAR")
   public java.lang.String getAutorizar() {
      return autorizar;
   }

   public void setAutorizar(java.lang.String autorizar) {
      this.autorizar = autorizar;
   }

   @Column(name = "IMPRIMIR")
   public java.lang.String getImprimir() {
      return imprimir;
   }

   public void setImprimir(java.lang.String imprimir) {
      this.imprimir = imprimir;
   }

   @Column(name = "GENERAR_GRAFICO")
   public java.lang.String getGenerarGrafico() {
      return generarGrafico;
   }

   public void setGenerarGrafico(java.lang.String generarGrafico) {
      this.generarGrafico = generarGrafico;
   }

   @Column(name = "DUPLICAR")
   public java.lang.String getDuplicar() {
      return duplicar;
   }

   public void setDuplicar(java.lang.String duplicar) {
      this.duplicar = duplicar;
   }
	public String toString() {
		return ToStringer.toString((RolOpcionIf)this);
	}
}
