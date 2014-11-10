package com.spirit.contabilidad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CuentaEntidadData implements CuentaEntidadIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long entidadId;

   public java.lang.Long getEntidadId() {
      return entidadId;
   }

   public void setEntidadId(java.lang.Long entidadId) {
      this.entidadId = entidadId;
   }

   private java.lang.String tipoEntidad;

   public java.lang.String getTipoEntidad() {
      return tipoEntidad;
   }

   public void setTipoEntidad(java.lang.String tipoEntidad) {
      this.tipoEntidad = tipoEntidad;
   }

   private java.lang.String nemonico;

   public java.lang.String getNemonico() {
      return nemonico;
   }

   public void setNemonico(java.lang.String nemonico) {
      this.nemonico = nemonico;
   }

   private java.lang.Long cuentaId;

   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }

   private java.lang.Long oficinaId;

   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }
   public CuentaEntidadData() {
   }

   public CuentaEntidadData(com.spirit.contabilidad.entity.CuentaEntidadIf value) {
      setId(value.getId());
      setEntidadId(value.getEntidadId());
      setTipoEntidad(value.getTipoEntidad());
      setNemonico(value.getNemonico());
      setCuentaId(value.getCuentaId());
      setOficinaId(value.getOficinaId());
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
		return ToStringer.toString((CuentaEntidadIf)this);
	}
}
