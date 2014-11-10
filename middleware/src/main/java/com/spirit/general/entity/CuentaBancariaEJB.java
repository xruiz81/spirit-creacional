package com.spirit.general.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "CUENTA_BANCARIA")
@Entity
public class CuentaBancariaEJB implements Serializable, CuentaBancariaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long empresaId;
   private java.lang.Long bancoId;
   private java.lang.String cuenta;
   private java.lang.String tipocuenta;
   private java.lang.String numeroCheque;
   private java.lang.String cuentaCliente;
   private java.lang.Long clienteId;

   public CuentaBancariaEJB() {
   }

   public CuentaBancariaEJB(com.spirit.general.entity.CuentaBancariaIf value) {
      setId(value.getId());
      setEmpresaId(value.getEmpresaId());
      setBancoId(value.getBancoId());
      setCuenta(value.getCuenta());
      setTipocuenta(value.getTipocuenta());
      setNumeroCheque(value.getNumeroCheque());
      setCuentaCliente(value.getCuentaCliente());
      setClienteId(value.getClienteId());
   }

   public java.lang.Long create(com.spirit.general.entity.CuentaBancariaIf value) {
      setId(value.getId());
      setEmpresaId(value.getEmpresaId());
      setBancoId(value.getBancoId());
      setCuenta(value.getCuenta());
      setTipocuenta(value.getTipocuenta());
      setNumeroCheque(value.getNumeroCheque());
      setCuentaCliente(value.getCuentaCliente());
      setClienteId(value.getClienteId());
      return value.getPrimaryKey();
   }

   @javax.persistence.Transient public java.lang.Long getPrimaryKey() {
        return getId();
    }

   @javax.persistence.Transient public void setPrimaryKey(java.lang.Long primaryKey) {
       setId(primaryKey);
    }

   @Column(name = "ID")
@TableGenerator(name="SEQ_GEN",
			allocationSize=HibernateSequenceAllocationSize.allocationSize
)
   @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_GEN")
   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "BANCO_ID")
   public java.lang.Long getBancoId() {
      return bancoId;
   }

   public void setBancoId(java.lang.Long bancoId) {
      this.bancoId = bancoId;
   }

   @Column(name = "CUENTA")
   public java.lang.String getCuenta() {
      return cuenta;
   }

   public void setCuenta(java.lang.String cuenta) {
      this.cuenta = cuenta;
   }

   @Column(name = "TIPOCUENTA")
   public java.lang.String getTipocuenta() {
      return tipocuenta;
   }

   public void setTipocuenta(java.lang.String tipocuenta) {
      this.tipocuenta = tipocuenta;
   }

   @Column(name = "NUMERO_CHEQUE")
   public java.lang.String getNumeroCheque() {
      return numeroCheque;
   }

   public void setNumeroCheque(java.lang.String numeroCheque) {
      this.numeroCheque = numeroCheque;
   }

   @Column(name = "CUENTA_CLIENTE")
   public java.lang.String getCuentaCliente() {
      return cuentaCliente;
   }

   public void setCuentaCliente(java.lang.String cuentaCliente) {
      this.cuentaCliente = cuentaCliente;
   }

   @Column(name = "CLIENTE_ID")
   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }
	public String toString() {
		return ToStringer.toString((CuentaBancariaIf)this);
	}
}
