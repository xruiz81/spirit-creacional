package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class HerramientasMediosData implements HerramientasMediosIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long clienteOficinaId;

   public java.lang.Long getClienteOficinaId() {
      return clienteOficinaId;
   }

   public void setClienteOficinaId(java.lang.Long clienteOficinaId) {
      this.clienteOficinaId = clienteOficinaId;
   }

   private java.lang.Long proveedorOficinaId;

   public java.lang.Long getProveedorOficinaId() {
      return proveedorOficinaId;
   }

   public void setProveedorOficinaId(java.lang.Long proveedorOficinaId) {
      this.proveedorOficinaId = proveedorOficinaId;
   }

   private java.lang.String frecuencia;

   public java.lang.String getFrecuencia() {
      return frecuencia;
   }

   public void setFrecuencia(java.lang.String frecuencia) {
      this.frecuencia = frecuencia;
   }
   public HerramientasMediosData() {
   }

   public HerramientasMediosData(com.spirit.medios.entity.HerramientasMediosIf value) {
      setId(value.getId());
      setClienteOficinaId(value.getClienteOficinaId());
      setProveedorOficinaId(value.getProveedorOficinaId());
      setFrecuencia(value.getFrecuencia());
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
		return ToStringer.toString((HerramientasMediosIf)this);
	}
}
