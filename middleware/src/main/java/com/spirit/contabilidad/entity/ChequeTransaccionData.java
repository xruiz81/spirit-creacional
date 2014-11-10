package com.spirit.contabilidad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ChequeTransaccionData implements ChequeTransaccionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long chequeEmitidoId;

   public java.lang.Long getChequeEmitidoId() {
      return chequeEmitidoId;
   }

   public void setChequeEmitidoId(java.lang.Long chequeEmitidoId) {
      this.chequeEmitidoId = chequeEmitidoId;
   }

   private java.lang.Long transaccionId;

   public java.lang.Long getTransaccionId() {
      return transaccionId;
   }

   public void setTransaccionId(java.lang.Long transaccionId) {
      this.transaccionId = transaccionId;
   }

   private java.lang.String origen;

   public java.lang.String getOrigen() {
      return origen;
   }

   public void setOrigen(java.lang.String origen) {
      this.origen = origen;
   }
   public ChequeTransaccionData() {
   }

   public ChequeTransaccionData(com.spirit.contabilidad.entity.ChequeTransaccionIf value) {
      setId(value.getId());
      setChequeEmitidoId(value.getChequeEmitidoId());
      setTransaccionId(value.getTransaccionId());
      setOrigen(value.getOrigen());
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
		return ToStringer.toString((ChequeTransaccionIf)this);
	}
}
