package com.spirit.crm.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ClienteIndicadorData implements ClienteIndicadorIf, Serializable    {


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

   private java.lang.Long tipoindicadorId;

   public java.lang.Long getTipoindicadorId() {
      return tipoindicadorId;
   }

   public void setTipoindicadorId(java.lang.Long tipoindicadorId) {
      this.tipoindicadorId = tipoindicadorId;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }
   public ClienteIndicadorData() {
   }

   public ClienteIndicadorData(com.spirit.crm.entity.ClienteIndicadorIf value) {
      setId(value.getId());
      setClienteOficinaId(value.getClienteOficinaId());
      setTipoindicadorId(value.getTipoindicadorId());
      setValor(value.getValor());
      setCodigo(value.getCodigo());
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
		return ToStringer.toString((ClienteIndicadorIf)this);
	}
}
