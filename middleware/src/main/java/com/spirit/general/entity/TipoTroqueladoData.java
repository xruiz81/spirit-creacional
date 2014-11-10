package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class TipoTroqueladoData implements TipoTroqueladoIf, Serializable    {


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

   private java.lang.String descripcion;

   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   private java.lang.Integer numeroSeccionesHoja;

   public java.lang.Integer getNumeroSeccionesHoja() {
      return numeroSeccionesHoja;
   }

   public void setNumeroSeccionesHoja(java.lang.Integer numeroSeccionesHoja) {
      this.numeroSeccionesHoja = numeroSeccionesHoja;
   }
   public TipoTroqueladoData() {
   }

   public TipoTroqueladoData(com.spirit.general.entity.TipoTroqueladoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setDescripcion(value.getDescripcion());
      setNumeroSeccionesHoja(value.getNumeroSeccionesHoja());
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
		return ToStringer.toString((TipoTroqueladoIf)this);
	}
}
