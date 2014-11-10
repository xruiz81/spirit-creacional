package com.spirit.pos.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PagoTarjetaData implements PagoTarjetaIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String tipoTarjeta;

   public java.lang.String getTipoTarjeta() {
      return tipoTarjeta;
   }

   public void setTipoTarjeta(java.lang.String tipoTarjeta) {
      this.tipoTarjeta = tipoTarjeta;
   }

   private java.lang.String nombreCliente;

   public java.lang.String getNombreCliente() {
      return nombreCliente;
   }

   public void setNombreCliente(java.lang.String nombreCliente) {
      this.nombreCliente = nombreCliente;
   }

   private java.lang.String identificacion;

   public java.lang.String getIdentificacion() {
      return identificacion;
   }

   public void setIdentificacion(java.lang.String identificacion) {
      this.identificacion = identificacion;
   }

   private java.lang.String telefono;

   public java.lang.String getTelefono() {
      return telefono;
   }

   public void setTelefono(java.lang.String telefono) {
      this.telefono = telefono;
   }

   private java.lang.String noReferencia;

   public java.lang.String getNoReferencia() {
      return noReferencia;
   }

   public void setNoReferencia(java.lang.String noReferencia) {
      this.noReferencia = noReferencia;
   }

   private java.lang.String noVoucher;

   public java.lang.String getNoVoucher() {
      return noVoucher;
   }

   public void setNoVoucher(java.lang.String noVoucher) {
      this.noVoucher = noVoucher;
   }

   private java.lang.String noAutorizacion;

   public java.lang.String getNoAutorizacion() {
      return noAutorizacion;
   }

   public void setNoAutorizacion(java.lang.String noAutorizacion) {
      this.noAutorizacion = noAutorizacion;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.sql.Timestamp fecha;

   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }
   public PagoTarjetaData() {
   }

   public PagoTarjetaData(com.spirit.pos.entity.PagoTarjetaIf value) {
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
		return ToStringer.toString((PagoTarjetaIf)this);
	}
}
