package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PrensaInsertosData implements PrensaInsertosIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long clienteId;

   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.math.BigDecimal maxPaginas;

   public java.math.BigDecimal getMaxPaginas() {
      return maxPaginas;
   }

   public void setMaxPaginas(java.math.BigDecimal maxPaginas) {
      this.maxPaginas = maxPaginas;
   }

   private java.math.BigDecimal tarifa;

   public java.math.BigDecimal getTarifa() {
      return tarifa;
   }

   public void setTarifa(java.math.BigDecimal tarifa) {
      this.tarifa = tarifa;
   }
   public PrensaInsertosData() {
   }

   public PrensaInsertosData(com.spirit.medios.entity.PrensaInsertosIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setCodigo(value.getCodigo());
      setMaxPaginas(value.getMaxPaginas());
      setTarifa(value.getTarifa());
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
		return ToStringer.toString((PrensaInsertosIf)this);
	}
}
