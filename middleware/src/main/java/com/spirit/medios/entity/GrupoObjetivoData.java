package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class GrupoObjetivoData implements GrupoObjetivoIf, Serializable    {


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

   private java.lang.String nivelSocioEconomico;

   public java.lang.String getNivelSocioEconomico() {
      return nivelSocioEconomico;
   }

   public void setNivelSocioEconomico(java.lang.String nivelSocioEconomico) {
      this.nivelSocioEconomico = nivelSocioEconomico;
   }

   private java.math.BigDecimal ciudad1;

   public java.math.BigDecimal getCiudad1() {
      return ciudad1;
   }

   public void setCiudad1(java.math.BigDecimal ciudad1) {
      this.ciudad1 = ciudad1;
   }

   private java.math.BigDecimal ciudad2;

   public java.math.BigDecimal getCiudad2() {
      return ciudad2;
   }

   public void setCiudad2(java.math.BigDecimal ciudad2) {
      this.ciudad2 = ciudad2;
   }

   private java.math.BigDecimal ciudad3;

   public java.math.BigDecimal getCiudad3() {
      return ciudad3;
   }

   public void setCiudad3(java.math.BigDecimal ciudad3) {
      this.ciudad3 = ciudad3;
   }
   public GrupoObjetivoData() {
   }

   public GrupoObjetivoData(com.spirit.medios.entity.GrupoObjetivoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setNivelSocioEconomico(value.getNivelSocioEconomico());
      setCiudad1(value.getCiudad1());
      setCiudad2(value.getCiudad2());
      setCiudad3(value.getCiudad3());
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
		return ToStringer.toString((GrupoObjetivoIf)this);
	}
}
