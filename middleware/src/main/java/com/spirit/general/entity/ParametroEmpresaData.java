package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ParametroEmpresaData implements ParametroEmpresaIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long tipoparametroId;

   public java.lang.Long getTipoparametroId() {
      return tipoparametroId;
   }

   public void setTipoparametroId(java.lang.Long tipoparametroId) {
      this.tipoparametroId = tipoparametroId;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   private java.lang.String valor;

   public java.lang.String getValor() {
      return valor;
   }

   public void setValor(java.lang.String valor) {
      this.valor = valor;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String descripcion;

   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }
   public ParametroEmpresaData() {
   }

   public ParametroEmpresaData(com.spirit.general.entity.ParametroEmpresaIf value) {
      setId(value.getId());
      setTipoparametroId(value.getTipoparametroId());
      setEmpresaId(value.getEmpresaId());
      setValor(value.getValor());
      setCodigo(value.getCodigo());
      setDescripcion(value.getDescripcion());
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
		return ToStringer.toString((ParametroEmpresaIf)this);
	}
}
