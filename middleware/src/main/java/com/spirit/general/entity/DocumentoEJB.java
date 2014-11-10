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

@Table(name = "DOCUMENTO")
@Entity
public class DocumentoEJB implements Serializable, DocumentoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.String abreviado;
   private java.lang.Long tipoDocumentoId;
   private java.lang.String pideAutorizacion;
   private java.lang.String estado;
   private java.lang.String diferido;
   private java.lang.String bonificacion;
   private java.lang.String precioEspecial;
   private java.lang.String descuentoEspecial;
   private java.lang.String multa;
   private java.lang.String interes;
   private java.lang.String protesto;
   private java.lang.String cheque;
   private java.lang.String retencionRenta;
   private java.lang.String retencionIva;
   private java.lang.String efectivo;
   private java.lang.String debitoBancario;
   private java.lang.String tarjetaCredito;
   private java.lang.String transaccionElectronica;
   private java.lang.String transferenciaBancaria;
   private java.lang.String nivelacion;
   private java.lang.String anticipo;

   public DocumentoEJB() {
   }

   public DocumentoEJB(com.spirit.general.entity.DocumentoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setAbreviado(value.getAbreviado());
      setTipoDocumentoId(value.getTipoDocumentoId());
      setPideAutorizacion(value.getPideAutorizacion());
      setEstado(value.getEstado());
      setDiferido(value.getDiferido());
      setBonificacion(value.getBonificacion());
      setPrecioEspecial(value.getPrecioEspecial());
      setDescuentoEspecial(value.getDescuentoEspecial());
      setMulta(value.getMulta());
      setInteres(value.getInteres());
      setProtesto(value.getProtesto());
      setCheque(value.getCheque());
      setRetencionRenta(value.getRetencionRenta());
      setRetencionIva(value.getRetencionIva());
      setEfectivo(value.getEfectivo());
      setDebitoBancario(value.getDebitoBancario());
      setTarjetaCredito(value.getTarjetaCredito());
      setTransaccionElectronica(value.getTransaccionElectronica());
      setTransferenciaBancaria(value.getTransferenciaBancaria());
      setNivelacion(value.getNivelacion());
      setAnticipo(value.getAnticipo());
   }

   public java.lang.Long create(com.spirit.general.entity.DocumentoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setAbreviado(value.getAbreviado());
      setTipoDocumentoId(value.getTipoDocumentoId());
      setPideAutorizacion(value.getPideAutorizacion());
      setEstado(value.getEstado());
      setDiferido(value.getDiferido());
      setBonificacion(value.getBonificacion());
      setPrecioEspecial(value.getPrecioEspecial());
      setDescuentoEspecial(value.getDescuentoEspecial());
      setMulta(value.getMulta());
      setInteres(value.getInteres());
      setProtesto(value.getProtesto());
      setCheque(value.getCheque());
      setRetencionRenta(value.getRetencionRenta());
      setRetencionIva(value.getRetencionIva());
      setEfectivo(value.getEfectivo());
      setDebitoBancario(value.getDebitoBancario());
      setTarjetaCredito(value.getTarjetaCredito());
      setTransaccionElectronica(value.getTransaccionElectronica());
      setTransferenciaBancaria(value.getTransferenciaBancaria());
      setNivelacion(value.getNivelacion());
      setAnticipo(value.getAnticipo());
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

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "NOMBRE")
   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   @Column(name = "ABREVIADO")
   public java.lang.String getAbreviado() {
      return abreviado;
   }

   public void setAbreviado(java.lang.String abreviado) {
      this.abreviado = abreviado;
   }

   @Column(name = "TIPO_DOCUMENTO_ID")
   public java.lang.Long getTipoDocumentoId() {
      return tipoDocumentoId;
   }

   public void setTipoDocumentoId(java.lang.Long tipoDocumentoId) {
      this.tipoDocumentoId = tipoDocumentoId;
   }

   @Column(name = "PIDE_AUTORIZACION")
   public java.lang.String getPideAutorizacion() {
      return pideAutorizacion;
   }

   public void setPideAutorizacion(java.lang.String pideAutorizacion) {
      this.pideAutorizacion = pideAutorizacion;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "DIFERIDO")
   public java.lang.String getDiferido() {
      return diferido;
   }

   public void setDiferido(java.lang.String diferido) {
      this.diferido = diferido;
   }

   @Column(name = "BONIFICACION")
   public java.lang.String getBonificacion() {
      return bonificacion;
   }

   public void setBonificacion(java.lang.String bonificacion) {
      this.bonificacion = bonificacion;
   }

   @Column(name = "PRECIO_ESPECIAL")
   public java.lang.String getPrecioEspecial() {
      return precioEspecial;
   }

   public void setPrecioEspecial(java.lang.String precioEspecial) {
      this.precioEspecial = precioEspecial;
   }

   @Column(name = "DESCUENTO_ESPECIAL")
   public java.lang.String getDescuentoEspecial() {
      return descuentoEspecial;
   }

   public void setDescuentoEspecial(java.lang.String descuentoEspecial) {
      this.descuentoEspecial = descuentoEspecial;
   }

   @Column(name = "MULTA")
   public java.lang.String getMulta() {
      return multa;
   }

   public void setMulta(java.lang.String multa) {
      this.multa = multa;
   }

   @Column(name = "INTERES")
   public java.lang.String getInteres() {
      return interes;
   }

   public void setInteres(java.lang.String interes) {
      this.interes = interes;
   }

   @Column(name = "PROTESTO")
   public java.lang.String getProtesto() {
      return protesto;
   }

   public void setProtesto(java.lang.String protesto) {
      this.protesto = protesto;
   }

   @Column(name = "CHEQUE")
   public java.lang.String getCheque() {
      return cheque;
   }

   public void setCheque(java.lang.String cheque) {
      this.cheque = cheque;
   }

   @Column(name = "RETENCION_RENTA")
   public java.lang.String getRetencionRenta() {
      return retencionRenta;
   }

   public void setRetencionRenta(java.lang.String retencionRenta) {
      this.retencionRenta = retencionRenta;
   }

   @Column(name = "RETENCION_IVA")
   public java.lang.String getRetencionIva() {
      return retencionIva;
   }

   public void setRetencionIva(java.lang.String retencionIva) {
      this.retencionIva = retencionIva;
   }

   @Column(name = "EFECTIVO")
   public java.lang.String getEfectivo() {
      return efectivo;
   }

   public void setEfectivo(java.lang.String efectivo) {
      this.efectivo = efectivo;
   }

   @Column(name = "DEBITO_BANCARIO")
   public java.lang.String getDebitoBancario() {
      return debitoBancario;
   }

   public void setDebitoBancario(java.lang.String debitoBancario) {
      this.debitoBancario = debitoBancario;
   }

   @Column(name = "TARJETA_CREDITO")
   public java.lang.String getTarjetaCredito() {
      return tarjetaCredito;
   }

   public void setTarjetaCredito(java.lang.String tarjetaCredito) {
      this.tarjetaCredito = tarjetaCredito;
   }

   @Column(name = "TRANSACCION_ELECTRONICA")
   public java.lang.String getTransaccionElectronica() {
      return transaccionElectronica;
   }

   public void setTransaccionElectronica(java.lang.String transaccionElectronica) {
      this.transaccionElectronica = transaccionElectronica;
   }

   @Column(name = "TRANSFERENCIA_BANCARIA")
   public java.lang.String getTransferenciaBancaria() {
      return transferenciaBancaria;
   }

   public void setTransferenciaBancaria(java.lang.String transferenciaBancaria) {
      this.transferenciaBancaria = transferenciaBancaria;
   }

   @Column(name = "NIVELACION")
   public java.lang.String getNivelacion() {
      return nivelacion;
   }

   public void setNivelacion(java.lang.String nivelacion) {
      this.nivelacion = nivelacion;
   }

   @Column(name = "ANTICIPO")
   public java.lang.String getAnticipo() {
      return anticipo;
   }

   public void setAnticipo(java.lang.String anticipo) {
      this.anticipo = anticipo;
   }
	public String toString() {
		return ToStringer.toString((DocumentoIf)this);
	}
}
