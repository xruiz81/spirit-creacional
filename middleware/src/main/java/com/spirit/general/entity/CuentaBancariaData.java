package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CuentaBancariaData implements CuentaBancariaIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   private java.lang.Long bancoId;

   public java.lang.Long getBancoId() {
      return bancoId;
   }

   public void setBancoId(java.lang.Long bancoId) {
      this.bancoId = bancoId;
   }

   private java.lang.String cuenta;

   public java.lang.String getCuenta() {
      return cuenta;
   }

   public void setCuenta(java.lang.String cuenta) {
      this.cuenta = cuenta;
   }

   private java.lang.String tipocuenta;

   public java.lang.String getTipocuenta() {
      return tipocuenta;
   }

   public void setTipocuenta(java.lang.String tipocuenta) {
      this.tipocuenta = tipocuenta;
   }

   private java.lang.String numeroCheque;

   public java.lang.String getNumeroCheque() {
      return numeroCheque;
   }

   public void setNumeroCheque(java.lang.String numeroCheque) {
      this.numeroCheque = numeroCheque;
   }

   private java.lang.String cuentaCliente;

   public java.lang.String getCuentaCliente() {
      return cuentaCliente;
   }

   public void setCuentaCliente(java.lang.String cuentaCliente) {
      this.cuentaCliente = cuentaCliente;
   }

   private java.lang.Long clienteId;

   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }
   public CuentaBancariaData() {
   }

   public CuentaBancariaData(com.spirit.general.entity.CuentaBancariaIf value) {
      setId(value.getId());
      setEmpresaId(value.getEmpresaId());
      setBancoId(value.getBancoId());
      setCuenta(value.getCuenta());
      setTipocuenta(value.getTipocuenta());
      setNumeroCheque(value.getNumeroCheque());
      setCuentaCliente(value.getCuentaCliente());
      setClienteId(value.getClienteId());
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
		return ToStringer.toString((CuentaBancariaIf)this);
	}
}
