package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class TipoDocumentoData implements TipoDocumentoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String nombre;

   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   private java.lang.Long moduloId;

   public java.lang.Long getModuloId() {
      return moduloId;
   }

   public void setModuloId(java.lang.Long moduloId) {
      this.moduloId = moduloId;
   }

   private java.lang.String mascara;

   public java.lang.String getMascara() {
      return mascara;
   }

   public void setMascara(java.lang.String mascara) {
      this.mascara = mascara;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   private java.lang.Integer numerolineas;

   public java.lang.Integer getNumerolineas() {
      return numerolineas;
   }

   public void setNumerolineas(java.lang.Integer numerolineas) {
      this.numerolineas = numerolineas;
   }

   private java.lang.String afectacartera;

   public java.lang.String getAfectacartera() {
      return afectacartera;
   }

   public void setAfectacartera(java.lang.String afectacartera) {
      this.afectacartera = afectacartera;
   }

   private java.lang.String afectastock;

   public java.lang.String getAfectastock() {
      return afectastock;
   }

   public void setAfectastock(java.lang.String afectastock) {
      this.afectastock = afectastock;
   }

   private java.lang.String afectaventa;

   public java.lang.String getAfectaventa() {
      return afectaventa;
   }

   public void setAfectaventa(java.lang.String afectaventa) {
      this.afectaventa = afectaventa;
   }

   private java.lang.String exigemotivo;

   public java.lang.String getExigemotivo() {
      return exigemotivo;
   }

   public void setExigemotivo(java.lang.String exigemotivo) {
      this.exigemotivo = exigemotivo;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String formapago;

   public java.lang.String getFormapago() {
      return formapago;
   }

   public void setFormapago(java.lang.String formapago) {
      this.formapago = formapago;
   }

   private java.lang.String cliente;

   public java.lang.String getCliente() {
      return cliente;
   }

   public void setCliente(java.lang.String cliente) {
      this.cliente = cliente;
   }

   private java.lang.String caja;

   public java.lang.String getCaja() {
      return caja;
   }

   public void setCaja(java.lang.String caja) {
      this.caja = caja;
   }

   private java.lang.String permiteeliminacion;

   public java.lang.String getPermiteeliminacion() {
      return permiteeliminacion;
   }

   public void setPermiteeliminacion(java.lang.String permiteeliminacion) {
      this.permiteeliminacion = permiteeliminacion;
   }

   private java.lang.String reembolso;

   public java.lang.String getReembolso() {
      return reembolso;
   }

   public void setReembolso(java.lang.String reembolso) {
      this.reembolso = reembolso;
   }

   private java.lang.String signocartera;

   public java.lang.String getSignocartera() {
      return signocartera;
   }

   public void setSignocartera(java.lang.String signocartera) {
      this.signocartera = signocartera;
   }

   private java.lang.String signostock;

   public java.lang.String getSignostock() {
      return signostock;
   }

   public void setSignostock(java.lang.String signostock) {
      this.signostock = signostock;
   }

   private java.lang.String signoventa;

   public java.lang.String getSignoventa() {
      return signoventa;
   }

   public void setSignoventa(java.lang.String signoventa) {
      this.signoventa = signoventa;
   }

   private java.lang.String descuentoespecial;

   public java.lang.String getDescuentoespecial() {
      return descuentoespecial;
   }

   public void setDescuentoespecial(java.lang.String descuentoespecial) {
      this.descuentoespecial = descuentoespecial;
   }

   private java.lang.String tipocartera;

   public java.lang.String getTipocartera() {
      return tipocartera;
   }

   public void setTipocartera(java.lang.String tipocartera) {
      this.tipocartera = tipocartera;
   }

   private java.lang.Long idSriTipoComprobante;

   public java.lang.Long getIdSriTipoComprobante() {
      return idSriTipoComprobante;
   }

   public void setIdSriTipoComprobante(java.lang.Long idSriTipoComprobante) {
      this.idSriTipoComprobante = idSriTipoComprobante;
   }

   private java.lang.Long tipoTroqueladoId;

   public java.lang.Long getTipoTroqueladoId() {
      return tipoTroqueladoId;
   }

   public void setTipoTroqueladoId(java.lang.Long tipoTroqueladoId) {
      this.tipoTroqueladoId = tipoTroqueladoId;
   }

   private java.lang.String transferible;

   public java.lang.String getTransferible() {
      return transferible;
   }

   public void setTransferible(java.lang.String transferible) {
      this.transferible = transferible;
   }

   private java.lang.String factura;

   public java.lang.String getFactura() {
      return factura;
   }

   public void setFactura(java.lang.String factura) {
      this.factura = factura;
   }

   private java.lang.String notaVenta;

   public java.lang.String getNotaVenta() {
      return notaVenta;
   }

   public void setNotaVenta(java.lang.String notaVenta) {
      this.notaVenta = notaVenta;
   }

   private java.lang.String notaCredito;

   public java.lang.String getNotaCredito() {
      return notaCredito;
   }

   public void setNotaCredito(java.lang.String notaCredito) {
      this.notaCredito = notaCredito;
   }

   private java.lang.String notaDebito;

   public java.lang.String getNotaDebito() {
      return notaDebito;
   }

   public void setNotaDebito(java.lang.String notaDebito) {
      this.notaDebito = notaDebito;
   }

   private java.lang.String liquidacionCompras;

   public java.lang.String getLiquidacionCompras() {
      return liquidacionCompras;
   }

   public void setLiquidacionCompras(java.lang.String liquidacionCompras) {
      this.liquidacionCompras = liquidacionCompras;
   }

   private java.lang.String abreviatura;

   public java.lang.String getAbreviatura() {
      return abreviatura;
   }

   public void setAbreviatura(java.lang.String abreviatura) {
      this.abreviatura = abreviatura;
   }

   private java.lang.String egreso;

   public java.lang.String getEgreso() {
      return egreso;
   }

   public void setEgreso(java.lang.String egreso) {
      this.egreso = egreso;
   }

   private java.lang.String anticipo;

   public java.lang.String getAnticipo() {
      return anticipo;
   }

   public void setAnticipo(java.lang.String anticipo) {
      this.anticipo = anticipo;
   }
   public TipoDocumentoData() {
   }

   public TipoDocumentoData(com.spirit.general.entity.TipoDocumentoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setModuloId(value.getModuloId());
      setMascara(value.getMascara());
      setEmpresaId(value.getEmpresaId());
      setNumerolineas(value.getNumerolineas());
      setAfectacartera(value.getAfectacartera());
      setAfectastock(value.getAfectastock());
      setAfectaventa(value.getAfectaventa());
      setExigemotivo(value.getExigemotivo());
      setEstado(value.getEstado());
      setFormapago(value.getFormapago());
      setCliente(value.getCliente());
      setCaja(value.getCaja());
      setPermiteeliminacion(value.getPermiteeliminacion());
      setReembolso(value.getReembolso());
      setSignocartera(value.getSignocartera());
      setSignostock(value.getSignostock());
      setSignoventa(value.getSignoventa());
      setDescuentoespecial(value.getDescuentoespecial());
      setTipocartera(value.getTipocartera());
      setIdSriTipoComprobante(value.getIdSriTipoComprobante());
      setTipoTroqueladoId(value.getTipoTroqueladoId());
      setTransferible(value.getTransferible());
      setFactura(value.getFactura());
      setNotaVenta(value.getNotaVenta());
      setNotaCredito(value.getNotaCredito());
      setNotaDebito(value.getNotaDebito());
      setLiquidacionCompras(value.getLiquidacionCompras());
      setAbreviatura(value.getAbreviatura());
      setEgreso(value.getEgreso());
      setAnticipo(value.getAnticipo());
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
		return ToStringer.toString((TipoDocumentoIf)this);
	}
}
