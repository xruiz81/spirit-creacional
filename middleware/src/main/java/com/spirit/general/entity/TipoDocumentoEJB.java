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

@Table(name = "TIPO_DOCUMENTO")
@Entity
public class TipoDocumentoEJB implements Serializable, TipoDocumentoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long moduloId;
   private java.lang.String mascara;
   private java.lang.Long empresaId;
   private java.lang.Integer numerolineas;
   private java.lang.String afectacartera;
   private java.lang.String afectastock;
   private java.lang.String afectaventa;
   private java.lang.String exigemotivo;
   private java.lang.String estado;
   private java.lang.String formapago;
   private java.lang.String cliente;
   private java.lang.String caja;
   private java.lang.String permiteeliminacion;
   private java.lang.String reembolso;
   private java.lang.String signocartera;
   private java.lang.String signostock;
   private java.lang.String signoventa;
   private java.lang.String descuentoespecial;
   private java.lang.String tipocartera;
   private java.lang.Long idSriTipoComprobante;
   private java.lang.Long tipoTroqueladoId;
   private java.lang.String transferible;
   private java.lang.String factura;
   private java.lang.String notaVenta;
   private java.lang.String notaCredito;
   private java.lang.String notaDebito;
   private java.lang.String liquidacionCompras;
   private java.lang.String abreviatura;
   private java.lang.String egreso;
   private java.lang.String anticipo;

   public TipoDocumentoEJB() {
   }

   public TipoDocumentoEJB(com.spirit.general.entity.TipoDocumentoIf value) {
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

   public java.lang.Long create(com.spirit.general.entity.TipoDocumentoIf value) {
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

   @Column(name = "MODULO_ID")
   public java.lang.Long getModuloId() {
      return moduloId;
   }

   public void setModuloId(java.lang.Long moduloId) {
      this.moduloId = moduloId;
   }

   @Column(name = "MASCARA")
   public java.lang.String getMascara() {
      return mascara;
   }

   public void setMascara(java.lang.String mascara) {
      this.mascara = mascara;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "NUMEROLINEAS")
   public java.lang.Integer getNumerolineas() {
      return numerolineas;
   }

   public void setNumerolineas(java.lang.Integer numerolineas) {
      this.numerolineas = numerolineas;
   }

   @Column(name = "AFECTACARTERA")
   public java.lang.String getAfectacartera() {
      return afectacartera;
   }

   public void setAfectacartera(java.lang.String afectacartera) {
      this.afectacartera = afectacartera;
   }

   @Column(name = "AFECTASTOCK")
   public java.lang.String getAfectastock() {
      return afectastock;
   }

   public void setAfectastock(java.lang.String afectastock) {
      this.afectastock = afectastock;
   }

   @Column(name = "AFECTAVENTA")
   public java.lang.String getAfectaventa() {
      return afectaventa;
   }

   public void setAfectaventa(java.lang.String afectaventa) {
      this.afectaventa = afectaventa;
   }

   @Column(name = "EXIGEMOTIVO")
   public java.lang.String getExigemotivo() {
      return exigemotivo;
   }

   public void setExigemotivo(java.lang.String exigemotivo) {
      this.exigemotivo = exigemotivo;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "FORMAPAGO")
   public java.lang.String getFormapago() {
      return formapago;
   }

   public void setFormapago(java.lang.String formapago) {
      this.formapago = formapago;
   }

   @Column(name = "CLIENTE")
   public java.lang.String getCliente() {
      return cliente;
   }

   public void setCliente(java.lang.String cliente) {
      this.cliente = cliente;
   }

   @Column(name = "CAJA")
   public java.lang.String getCaja() {
      return caja;
   }

   public void setCaja(java.lang.String caja) {
      this.caja = caja;
   }

   @Column(name = "PERMITEELIMINACION")
   public java.lang.String getPermiteeliminacion() {
      return permiteeliminacion;
   }

   public void setPermiteeliminacion(java.lang.String permiteeliminacion) {
      this.permiteeliminacion = permiteeliminacion;
   }

   @Column(name = "REEMBOLSO")
   public java.lang.String getReembolso() {
      return reembolso;
   }

   public void setReembolso(java.lang.String reembolso) {
      this.reembolso = reembolso;
   }

   @Column(name = "SIGNOCARTERA")
   public java.lang.String getSignocartera() {
      return signocartera;
   }

   public void setSignocartera(java.lang.String signocartera) {
      this.signocartera = signocartera;
   }

   @Column(name = "SIGNOSTOCK")
   public java.lang.String getSignostock() {
      return signostock;
   }

   public void setSignostock(java.lang.String signostock) {
      this.signostock = signostock;
   }

   @Column(name = "SIGNOVENTA")
   public java.lang.String getSignoventa() {
      return signoventa;
   }

   public void setSignoventa(java.lang.String signoventa) {
      this.signoventa = signoventa;
   }

   @Column(name = "DESCUENTOESPECIAL")
   public java.lang.String getDescuentoespecial() {
      return descuentoespecial;
   }

   public void setDescuentoespecial(java.lang.String descuentoespecial) {
      this.descuentoespecial = descuentoespecial;
   }

   @Column(name = "TIPOCARTERA")
   public java.lang.String getTipocartera() {
      return tipocartera;
   }

   public void setTipocartera(java.lang.String tipocartera) {
      this.tipocartera = tipocartera;
   }

   @Column(name = "ID_SRI_TIPO_COMPROBANTE")
   public java.lang.Long getIdSriTipoComprobante() {
      return idSriTipoComprobante;
   }

   public void setIdSriTipoComprobante(java.lang.Long idSriTipoComprobante) {
      this.idSriTipoComprobante = idSriTipoComprobante;
   }

   @Column(name = "TIPO_TROQUELADO_ID")
   public java.lang.Long getTipoTroqueladoId() {
      return tipoTroqueladoId;
   }

   public void setTipoTroqueladoId(java.lang.Long tipoTroqueladoId) {
      this.tipoTroqueladoId = tipoTroqueladoId;
   }

   @Column(name = "TRANSFERIBLE")
   public java.lang.String getTransferible() {
      return transferible;
   }

   public void setTransferible(java.lang.String transferible) {
      this.transferible = transferible;
   }

   @Column(name = "FACTURA")
   public java.lang.String getFactura() {
      return factura;
   }

   public void setFactura(java.lang.String factura) {
      this.factura = factura;
   }

   @Column(name = "NOTA_VENTA")
   public java.lang.String getNotaVenta() {
      return notaVenta;
   }

   public void setNotaVenta(java.lang.String notaVenta) {
      this.notaVenta = notaVenta;
   }

   @Column(name = "NOTA_CREDITO")
   public java.lang.String getNotaCredito() {
      return notaCredito;
   }

   public void setNotaCredito(java.lang.String notaCredito) {
      this.notaCredito = notaCredito;
   }

   @Column(name = "NOTA_DEBITO")
   public java.lang.String getNotaDebito() {
      return notaDebito;
   }

   public void setNotaDebito(java.lang.String notaDebito) {
      this.notaDebito = notaDebito;
   }

   @Column(name = "LIQUIDACION_COMPRAS")
   public java.lang.String getLiquidacionCompras() {
      return liquidacionCompras;
   }

   public void setLiquidacionCompras(java.lang.String liquidacionCompras) {
      this.liquidacionCompras = liquidacionCompras;
   }

   @Column(name = "ABREVIATURA")
   public java.lang.String getAbreviatura() {
      return abreviatura;
   }

   public void setAbreviatura(java.lang.String abreviatura) {
      this.abreviatura = abreviatura;
   }

   @Column(name = "EGRESO")
   public java.lang.String getEgreso() {
      return egreso;
   }

   public void setEgreso(java.lang.String egreso) {
      this.egreso = egreso;
   }

   @Column(name = "ANTICIPO")
   public java.lang.String getAnticipo() {
      return anticipo;
   }

   public void setAnticipo(java.lang.String anticipo) {
      this.anticipo = anticipo;
   }
	public String toString() {
		return ToStringer.toString((TipoDocumentoIf)this);
	}
}
