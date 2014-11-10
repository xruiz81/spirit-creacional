package com.spirit.pos.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "PAGO_TARJETA")
@Entity
public class PagoTarjetaEJB implements Serializable, PagoTarjetaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String tipoTarjeta;
   private java.lang.String nombreCliente;
   private java.lang.String identificacion;
   private java.lang.String telefono;
   private java.lang.String noReferencia;
   private java.lang.String noVoucher;
   private java.lang.String noAutorizacion;
   private java.math.BigDecimal valor;
   private java.sql.Timestamp fecha;

   public PagoTarjetaEJB() {
   }

   public PagoTarjetaEJB(com.spirit.pos.entity.PagoTarjetaIf value) {
      setId(value.getId());
      setTipoTarjeta(value.getTipoTarjeta());
      setNombreCliente(value.getNombreCliente());
      setIdentificacion(value.getIdentificacion());
      setTelefono(value.getTelefono());
      setNoReferencia(value.getNoReferencia());
      setNoVoucher(value.getNoVoucher());
      setNoAutorizacion(value.getNoAutorizacion());
      setValor(value.getValor());
      setFecha(value.getFecha());
   }

   public java.lang.Long create(com.spirit.pos.entity.PagoTarjetaIf value) {
      setId(value.getId());
      setTipoTarjeta(value.getTipoTarjeta());
      setNombreCliente(value.getNombreCliente());
      setIdentificacion(value.getIdentificacion());
      setTelefono(value.getTelefono());
      setNoReferencia(value.getNoReferencia());
      setNoVoucher(value.getNoVoucher());
      setNoAutorizacion(value.getNoAutorizacion());
      setValor(value.getValor());
      setFecha(value.getFecha());
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

   @Column(name = "TIPO_TARJETA")
   public java.lang.String getTipoTarjeta() {
      return tipoTarjeta;
   }

   public void setTipoTarjeta(java.lang.String tipoTarjeta) {
      this.tipoTarjeta = tipoTarjeta;
   }

   @Column(name = "NOMBRE_CLIENTE")
   public java.lang.String getNombreCliente() {
      return nombreCliente;
   }

   public void setNombreCliente(java.lang.String nombreCliente) {
      this.nombreCliente = nombreCliente;
   }

   @Column(name = "IDENTIFICACION")
   public java.lang.String getIdentificacion() {
      return identificacion;
   }

   public void setIdentificacion(java.lang.String identificacion) {
      this.identificacion = identificacion;
   }

   @Column(name = "TELEFONO")
   public java.lang.String getTelefono() {
      return telefono;
   }

   public void setTelefono(java.lang.String telefono) {
      this.telefono = telefono;
   }

   @Column(name = "NO_REFERENCIA")
   public java.lang.String getNoReferencia() {
      return noReferencia;
   }

   public void setNoReferencia(java.lang.String noReferencia) {
      this.noReferencia = noReferencia;
   }

   @Column(name = "NO_VOUCHER")
   public java.lang.String getNoVoucher() {
      return noVoucher;
   }

   public void setNoVoucher(java.lang.String noVoucher) {
      this.noVoucher = noVoucher;
   }

   @Column(name = "NO_AUTORIZACION")
   public java.lang.String getNoAutorizacion() {
      return noAutorizacion;
   }

   public void setNoAutorizacion(java.lang.String noAutorizacion) {
      this.noAutorizacion = noAutorizacion;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "FECHA")
   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }
	public String toString() {
		return ToStringer.toString((PagoTarjetaIf)this);
	}
}
