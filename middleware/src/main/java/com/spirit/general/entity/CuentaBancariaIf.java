package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CuentaBancariaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.lang.Long getBancoId();

   void setBancoId(java.lang.Long bancoId);

   java.lang.String getCuenta();

   void setCuenta(java.lang.String cuenta);

   java.lang.String getTipocuenta();

   void setTipocuenta(java.lang.String tipocuenta);

   java.lang.String getNumeroCheque();

   void setNumeroCheque(java.lang.String numeroCheque);

   java.lang.String getCuentaCliente();

   void setCuentaCliente(java.lang.String cuentaCliente);

   java.lang.Long getClienteId();

   void setClienteId(java.lang.Long clienteId);


}
