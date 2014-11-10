package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class NumeradoresData implements NumeradoresIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.math.BigDecimal ultimoValor;

   public java.math.BigDecimal getUltimoValor() {
      return ultimoValor;
   }

   public void setUltimoValor(java.math.BigDecimal ultimoValor) {
      this.ultimoValor = ultimoValor;
   }

   private java.lang.String nombreTabla;

   public java.lang.String getNombreTabla() {
      return nombreTabla;
   }

   public void setNombreTabla(java.lang.String nombreTabla) {
      this.nombreTabla = nombreTabla;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }
   public NumeradoresData() {
   }

   public NumeradoresData(com.spirit.general.entity.NumeradoresIf value) {
      setId(value.getId());
      setUltimoValor(value.getUltimoValor());
      setNombreTabla(value.getNombreTabla());
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
		return ToStringer.toString((NumeradoresIf)this);
	}
}
