package com.spirit.seguridad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class RolOpcionData implements RolOpcionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long rolId;

   public java.lang.Long getRolId() {
      return rolId;
   }

   public void setRolId(java.lang.Long rolId) {
      this.rolId = rolId;
   }

   private java.lang.Long menuId;

   public java.lang.Long getMenuId() {
      return menuId;
   }

   public void setMenuId(java.lang.Long menuId) {
      this.menuId = menuId;
   }

   private java.lang.String ninguno;

   public java.lang.String getNinguno() {
      return ninguno;
   }

   public void setNinguno(java.lang.String ninguno) {
      this.ninguno = ninguno;
   }

   private java.lang.String grabarActualizar;

   public java.lang.String getGrabarActualizar() {
      return grabarActualizar;
   }

   public void setGrabarActualizar(java.lang.String grabarActualizar) {
      this.grabarActualizar = grabarActualizar;
   }

   private java.lang.String borrar;

   public java.lang.String getBorrar() {
      return borrar;
   }

   public void setBorrar(java.lang.String borrar) {
      this.borrar = borrar;
   }

   private java.lang.String consultar;

   public java.lang.String getConsultar() {
      return consultar;
   }

   public void setConsultar(java.lang.String consultar) {
      this.consultar = consultar;
   }

   private java.lang.String autorizar;

   public java.lang.String getAutorizar() {
      return autorizar;
   }

   public void setAutorizar(java.lang.String autorizar) {
      this.autorizar = autorizar;
   }

   private java.lang.String imprimir;

   public java.lang.String getImprimir() {
      return imprimir;
   }

   public void setImprimir(java.lang.String imprimir) {
      this.imprimir = imprimir;
   }

   private java.lang.String generarGrafico;

   public java.lang.String getGenerarGrafico() {
      return generarGrafico;
   }

   public void setGenerarGrafico(java.lang.String generarGrafico) {
      this.generarGrafico = generarGrafico;
   }

   private java.lang.String duplicar;

   public java.lang.String getDuplicar() {
      return duplicar;
   }

   public void setDuplicar(java.lang.String duplicar) {
      this.duplicar = duplicar;
   }
   public RolOpcionData() {
   }

   public RolOpcionData(com.spirit.seguridad.entity.RolOpcionIf value) {
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
		return ToStringer.toString((RolOpcionIf)this);
	}
}
