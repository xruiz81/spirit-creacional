package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class TipoParametroData implements TipoParametroIf, Serializable    {


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

   private java.lang.String tipo;

   public java.lang.String getTipo() {
      return tipo;
   }

   public void setTipo(java.lang.String tipo) {
      this.tipo = tipo;
   }

   private java.lang.String mascara;

   public java.lang.String getMascara() {
      return mascara;
   }

   public void setMascara(java.lang.String mascara) {
      this.mascara = mascara;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }
   public TipoParametroData() {
   }

   public TipoParametroData(com.spirit.general.entity.TipoParametroIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setTipo(value.getTipo());
      setMascara(value.getMascara());
      setEmpresaId(value.getEmpresaId());
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
		return ToStringer.toString((TipoParametroIf)this);
	}
}
