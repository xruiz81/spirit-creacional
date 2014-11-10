package com.spirit.contabilidad.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "CHEQUE_EMITIDO")
@Entity
public class ChequeEmitidoEJB implements Serializable, ChequeEmitidoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.sql.Date fechaEmision;
   private java.lang.Long cuentaBancariaId;
   private java.lang.String numero;
   private java.lang.String detalle;
   private java.math.BigDecimal valor;
   private java.lang.String estado;
   private java.lang.Long tipoDocumentoId;
   private java.lang.Long transaccionId;
   private java.lang.String beneficiario;
   private java.sql.Date fechaCobro;
   private java.lang.String origen;

   public ChequeEmitidoEJB() {
   }

   public ChequeEmitidoEJB(com.spirit.contabilidad.entity.ChequeEmitidoIf value) {
      setId(value.getId());
      setFechaEmision(value.getFechaEmision());
      setCuentaBancariaId(value.getCuentaBancariaId());
      setNumero(value.getNumero());
      setDetalle(value.getDetalle());
      setValor(value.getValor());
      setEstado(value.getEstado());
      setTipoDocumentoId(value.getTipoDocumentoId());
      setTransaccionId(value.getTransaccionId());
      setBeneficiario(value.getBeneficiario());
      setFechaCobro(value.getFechaCobro());
      setOrigen(value.getOrigen());
   }

   public java.lang.Long create(com.spirit.contabilidad.entity.ChequeEmitidoIf value) {
      setId(value.getId());
      setFechaEmision(value.getFechaEmision());
      setCuentaBancariaId(value.getCuentaBancariaId());
      setNumero(value.getNumero());
      setDetalle(value.getDetalle());
      setValor(value.getValor());
      setEstado(value.getEstado());
      setTipoDocumentoId(value.getTipoDocumentoId());
      setTransaccionId(value.getTransaccionId());
      setBeneficiario(value.getBeneficiario());
      setFechaCobro(value.getFechaCobro());
      setOrigen(value.getOrigen());
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

   @Column(name = "FECHA_EMISION")
   public java.sql.Date getFechaEmision() {
      return fechaEmision;
   }

   public void setFechaEmision(java.sql.Date fechaEmision) {
      this.fechaEmision = fechaEmision;
   }

   @Column(name = "CUENTA_BANCARIA_ID")
   public java.lang.Long getCuentaBancariaId() {
      return cuentaBancariaId;
   }

   public void setCuentaBancariaId(java.lang.Long cuentaBancariaId) {
      this.cuentaBancariaId = cuentaBancariaId;
   }

   @Column(name = "NUMERO")
   public java.lang.String getNumero() {
      return numero;
   }

   public void setNumero(java.lang.String numero) {
      this.numero = numero;
   }

   @Column(name = "DETALLE")
   public java.lang.String getDetalle() {
      return detalle;
   }

   public void setDetalle(java.lang.String detalle) {
      this.detalle = detalle;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "TIPO_DOCUMENTO_ID")
   public java.lang.Long getTipoDocumentoId() {
      return tipoDocumentoId;
   }

   public void setTipoDocumentoId(java.lang.Long tipoDocumentoId) {
      this.tipoDocumentoId = tipoDocumentoId;
   }

   @Column(name = "TRANSACCION_ID")
   public java.lang.Long getTransaccionId() {
      return transaccionId;
   }

   public void setTransaccionId(java.lang.Long transaccionId) {
      this.transaccionId = transaccionId;
   }

   @Column(name = "BENEFICIARIO")
   public java.lang.String getBeneficiario() {
      return beneficiario;
   }

   public void setBeneficiario(java.lang.String beneficiario) {
      this.beneficiario = beneficiario;
   }

   @Column(name = "FECHA_COBRO")
   public java.sql.Date getFechaCobro() {
      return fechaCobro;
   }

   public void setFechaCobro(java.sql.Date fechaCobro) {
      this.fechaCobro = fechaCobro;
   }

   @Column(name = "ORIGEN")
   public java.lang.String getOrigen() {
      return origen;
   }

   public void setOrigen(java.lang.String origen) {
      this.origen = origen;
   }
	public String toString() {
		return ToStringer.toString((ChequeEmitidoIf)this);
	}
}
