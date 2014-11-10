package com.spirit.facturacion.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "FACTURA")
@Entity
public class FacturaEJB implements Serializable, FacturaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long oficinaId;
   private java.lang.Long tipodocumentoId;
   private java.math.BigDecimal numero;
   private java.lang.Long clienteoficinaId;
   private java.lang.Long tipoidentificacionId;
   private java.lang.String identificacion;
   private java.lang.Long formapagoId;
   private java.lang.Long monedaId;
   private java.lang.Long puntoImpresionId;
   private java.lang.Long origendocumentoId;
   private java.lang.Long vendedorId;
   private java.lang.Long bodegaId;
   private java.lang.Long pedidoId;
   private java.lang.Long listaPrecioId;
   private java.sql.Timestamp fechaCreacion;
   private java.sql.Timestamp fechaFactura;
   private java.sql.Timestamp fechaVencimiento;
   private java.lang.Long usuarioId;
   private java.lang.String contacto;
   private java.lang.String direccion;
   private java.lang.String telefono;
   private java.lang.String preimpresoNumero;
   private java.math.BigDecimal valor;
   private java.math.BigDecimal descuento;
   private java.math.BigDecimal iva;
   private java.math.BigDecimal ice;
   private java.math.BigDecimal otroImpuesto;
   private java.math.BigDecimal costo;
   private java.lang.String observacion;
   private java.lang.String estado;
   private java.lang.Long facturaaplId;
   private java.math.BigDecimal descuentoGlobal;
   private java.math.BigDecimal porcentajeComisionAgencia;
   private java.lang.Long equipoId;
   private java.lang.Long clienteNegociacionId;
   private java.lang.String tipoNegociacion;
   private java.math.BigDecimal porcentajeNegociacionDirecta;
   private java.math.BigDecimal porcentajeDescuentoNegociacion;
   private java.lang.String autorizacionSap;
   private java.math.BigDecimal descuentosVarios;

   public FacturaEJB() {
   }

   public FacturaEJB(com.spirit.facturacion.entity.FacturaIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setTipodocumentoId(value.getTipodocumentoId());
      setNumero(value.getNumero());
      setClienteoficinaId(value.getClienteoficinaId());
      setTipoidentificacionId(value.getTipoidentificacionId());
      setIdentificacion(value.getIdentificacion());
      setFormapagoId(value.getFormapagoId());
      setMonedaId(value.getMonedaId());
      setPuntoImpresionId(value.getPuntoImpresionId());
      setOrigendocumentoId(value.getOrigendocumentoId());
      setVendedorId(value.getVendedorId());
      setBodegaId(value.getBodegaId());
      setPedidoId(value.getPedidoId());
      setListaPrecioId(value.getListaPrecioId());
      setFechaCreacion(value.getFechaCreacion());
      setFechaFactura(value.getFechaFactura());
      setFechaVencimiento(value.getFechaVencimiento());
      setUsuarioId(value.getUsuarioId());
      setContacto(value.getContacto());
      setDireccion(value.getDireccion());
      setTelefono(value.getTelefono());
      setPreimpresoNumero(value.getPreimpresoNumero());
      setValor(value.getValor());
      setDescuento(value.getDescuento());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setCosto(value.getCosto());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setFacturaaplId(value.getFacturaaplId());
      setDescuentoGlobal(value.getDescuentoGlobal());
      setPorcentajeComisionAgencia(value.getPorcentajeComisionAgencia());
      setEquipoId(value.getEquipoId());
      setClienteNegociacionId(value.getClienteNegociacionId());
      setTipoNegociacion(value.getTipoNegociacion());
      setPorcentajeNegociacionDirecta(value.getPorcentajeNegociacionDirecta());
      setPorcentajeDescuentoNegociacion(value.getPorcentajeDescuentoNegociacion());
      setAutorizacionSap(value.getAutorizacionSap());
      setDescuentosVarios(value.getDescuentosVarios());
   }

   public java.lang.Long create(com.spirit.facturacion.entity.FacturaIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setTipodocumentoId(value.getTipodocumentoId());
      setNumero(value.getNumero());
      setClienteoficinaId(value.getClienteoficinaId());
      setTipoidentificacionId(value.getTipoidentificacionId());
      setIdentificacion(value.getIdentificacion());
      setFormapagoId(value.getFormapagoId());
      setMonedaId(value.getMonedaId());
      setPuntoImpresionId(value.getPuntoImpresionId());
      setOrigendocumentoId(value.getOrigendocumentoId());
      setVendedorId(value.getVendedorId());
      setBodegaId(value.getBodegaId());
      setPedidoId(value.getPedidoId());
      setListaPrecioId(value.getListaPrecioId());
      setFechaCreacion(value.getFechaCreacion());
      setFechaFactura(value.getFechaFactura());
      setFechaVencimiento(value.getFechaVencimiento());
      setUsuarioId(value.getUsuarioId());
      setContacto(value.getContacto());
      setDireccion(value.getDireccion());
      setTelefono(value.getTelefono());
      setPreimpresoNumero(value.getPreimpresoNumero());
      setValor(value.getValor());
      setDescuento(value.getDescuento());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setCosto(value.getCosto());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setFacturaaplId(value.getFacturaaplId());
      setDescuentoGlobal(value.getDescuentoGlobal());
      setPorcentajeComisionAgencia(value.getPorcentajeComisionAgencia());
      setEquipoId(value.getEquipoId());
      setClienteNegociacionId(value.getClienteNegociacionId());
      setTipoNegociacion(value.getTipoNegociacion());
      setPorcentajeNegociacionDirecta(value.getPorcentajeNegociacionDirecta());
      setPorcentajeDescuentoNegociacion(value.getPorcentajeDescuentoNegociacion());
      setAutorizacionSap(value.getAutorizacionSap());
      setDescuentosVarios(value.getDescuentosVarios());
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

   @Column(name = "OFICINA_ID")
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   @Column(name = "TIPODOCUMENTO_ID")
   public java.lang.Long getTipodocumentoId() {
      return tipodocumentoId;
   }

   public void setTipodocumentoId(java.lang.Long tipodocumentoId) {
      this.tipodocumentoId = tipodocumentoId;
   }

   @Column(name = "NUMERO")
   public java.math.BigDecimal getNumero() {
      return numero;
   }

   public void setNumero(java.math.BigDecimal numero) {
      this.numero = numero;
   }

   @Column(name = "CLIENTEOFICINA_ID")
   public java.lang.Long getClienteoficinaId() {
      return clienteoficinaId;
   }

   public void setClienteoficinaId(java.lang.Long clienteoficinaId) {
      this.clienteoficinaId = clienteoficinaId;
   }

   @Column(name = "TIPOIDENTIFICACION_ID")
   public java.lang.Long getTipoidentificacionId() {
      return tipoidentificacionId;
   }

   public void setTipoidentificacionId(java.lang.Long tipoidentificacionId) {
      this.tipoidentificacionId = tipoidentificacionId;
   }

   @Column(name = "IDENTIFICACION")
   public java.lang.String getIdentificacion() {
      return identificacion;
   }

   public void setIdentificacion(java.lang.String identificacion) {
      this.identificacion = identificacion;
   }

   @Column(name = "FORMAPAGO_ID")
   public java.lang.Long getFormapagoId() {
      return formapagoId;
   }

   public void setFormapagoId(java.lang.Long formapagoId) {
      this.formapagoId = formapagoId;
   }

   @Column(name = "MONEDA_ID")
   public java.lang.Long getMonedaId() {
      return monedaId;
   }

   public void setMonedaId(java.lang.Long monedaId) {
      this.monedaId = monedaId;
   }

   @Column(name = "PUNTO_IMPRESION_ID")
   public java.lang.Long getPuntoImpresionId() {
      return puntoImpresionId;
   }

   public void setPuntoImpresionId(java.lang.Long puntoImpresionId) {
      this.puntoImpresionId = puntoImpresionId;
   }

   @Column(name = "ORIGENDOCUMENTO_ID")
   public java.lang.Long getOrigendocumentoId() {
      return origendocumentoId;
   }

   public void setOrigendocumentoId(java.lang.Long origendocumentoId) {
      this.origendocumentoId = origendocumentoId;
   }

   @Column(name = "VENDEDOR_ID")
   public java.lang.Long getVendedorId() {
      return vendedorId;
   }

   public void setVendedorId(java.lang.Long vendedorId) {
      this.vendedorId = vendedorId;
   }

   @Column(name = "BODEGA_ID")
   public java.lang.Long getBodegaId() {
      return bodegaId;
   }

   public void setBodegaId(java.lang.Long bodegaId) {
      this.bodegaId = bodegaId;
   }

   @Column(name = "PEDIDO_ID")
   public java.lang.Long getPedidoId() {
      return pedidoId;
   }

   public void setPedidoId(java.lang.Long pedidoId) {
      this.pedidoId = pedidoId;
   }

   @Column(name = "LISTA_PRECIO_ID")
   public java.lang.Long getListaPrecioId() {
      return listaPrecioId;
   }

   public void setListaPrecioId(java.lang.Long listaPrecioId) {
      this.listaPrecioId = listaPrecioId;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "FECHA_FACTURA")
   public java.sql.Timestamp getFechaFactura() {
      return fechaFactura;
   }

   public void setFechaFactura(java.sql.Timestamp fechaFactura) {
      this.fechaFactura = fechaFactura;
   }

   @Column(name = "FECHA_VENCIMIENTO")
   public java.sql.Timestamp getFechaVencimiento() {
      return fechaVencimiento;
   }

   public void setFechaVencimiento(java.sql.Timestamp fechaVencimiento) {
      this.fechaVencimiento = fechaVencimiento;
   }

   @Column(name = "USUARIO_ID")
   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   @Column(name = "CONTACTO")
   public java.lang.String getContacto() {
      return contacto;
   }

   public void setContacto(java.lang.String contacto) {
      this.contacto = contacto;
   }

   @Column(name = "DIRECCION")
   public java.lang.String getDireccion() {
      return direccion;
   }

   public void setDireccion(java.lang.String direccion) {
      this.direccion = direccion;
   }

   @Column(name = "TELEFONO")
   public java.lang.String getTelefono() {
      return telefono;
   }

   public void setTelefono(java.lang.String telefono) {
      this.telefono = telefono;
   }

   @Column(name = "PREIMPRESO_NUMERO")
   public java.lang.String getPreimpresoNumero() {
      return preimpresoNumero;
   }

   public void setPreimpresoNumero(java.lang.String preimpresoNumero) {
      this.preimpresoNumero = preimpresoNumero;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "DESCUENTO")
   public java.math.BigDecimal getDescuento() {
      return descuento;
   }

   public void setDescuento(java.math.BigDecimal descuento) {
      this.descuento = descuento;
   }

   @Column(name = "IVA")
   public java.math.BigDecimal getIva() {
      return iva;
   }

   public void setIva(java.math.BigDecimal iva) {
      this.iva = iva;
   }

   @Column(name = "ICE")
   public java.math.BigDecimal getIce() {
      return ice;
   }

   public void setIce(java.math.BigDecimal ice) {
      this.ice = ice;
   }

   @Column(name = "OTRO_IMPUESTO")
   public java.math.BigDecimal getOtroImpuesto() {
      return otroImpuesto;
   }

   public void setOtroImpuesto(java.math.BigDecimal otroImpuesto) {
      this.otroImpuesto = otroImpuesto;
   }

   @Column(name = "COSTO")
   public java.math.BigDecimal getCosto() {
      return costo;
   }

   public void setCosto(java.math.BigDecimal costo) {
      this.costo = costo;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "FACTURAAPL_ID")
   public java.lang.Long getFacturaaplId() {
      return facturaaplId;
   }

   public void setFacturaaplId(java.lang.Long facturaaplId) {
      this.facturaaplId = facturaaplId;
   }

   @Column(name = "DESCUENTO_GLOBAL")
   public java.math.BigDecimal getDescuentoGlobal() {
      return descuentoGlobal;
   }

   public void setDescuentoGlobal(java.math.BigDecimal descuentoGlobal) {
      this.descuentoGlobal = descuentoGlobal;
   }

   @Column(name = "PORCENTAJE_COMISION_AGENCIA")
   public java.math.BigDecimal getPorcentajeComisionAgencia() {
      return porcentajeComisionAgencia;
   }

   public void setPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) {
      this.porcentajeComisionAgencia = porcentajeComisionAgencia;
   }

   @Column(name = "EQUIPO_ID")
   public java.lang.Long getEquipoId() {
      return equipoId;
   }

   public void setEquipoId(java.lang.Long equipoId) {
      this.equipoId = equipoId;
   }

   @Column(name = "CLIENTE_NEGOCIACION_ID")
   public java.lang.Long getClienteNegociacionId() {
      return clienteNegociacionId;
   }

   public void setClienteNegociacionId(java.lang.Long clienteNegociacionId) {
      this.clienteNegociacionId = clienteNegociacionId;
   }

   @Column(name = "TIPO_NEGOCIACION")
   public java.lang.String getTipoNegociacion() {
      return tipoNegociacion;
   }

   public void setTipoNegociacion(java.lang.String tipoNegociacion) {
      this.tipoNegociacion = tipoNegociacion;
   }

   @Column(name = "PORCENTAJE_NEGOCIACION_DIRECTA")
   public java.math.BigDecimal getPorcentajeNegociacionDirecta() {
      return porcentajeNegociacionDirecta;
   }

   public void setPorcentajeNegociacionDirecta(java.math.BigDecimal porcentajeNegociacionDirecta) {
      this.porcentajeNegociacionDirecta = porcentajeNegociacionDirecta;
   }

   @Column(name = "PORCENTAJE_DESCUENTO_NEGOCIACION")
   public java.math.BigDecimal getPorcentajeDescuentoNegociacion() {
      return porcentajeDescuentoNegociacion;
   }

   public void setPorcentajeDescuentoNegociacion(java.math.BigDecimal porcentajeDescuentoNegociacion) {
      this.porcentajeDescuentoNegociacion = porcentajeDescuentoNegociacion;
   }

   @Column(name = "AUTORIZACION_SAP")
   public java.lang.String getAutorizacionSap() {
      return autorizacionSap;
   }

   public void setAutorizacionSap(java.lang.String autorizacionSap) {
      this.autorizacionSap = autorizacionSap;
   }

   @Column(name = "DESCUENTOS_VARIOS")
   public java.math.BigDecimal getDescuentosVarios() {
      return descuentosVarios;
   }

   public void setDescuentosVarios(java.math.BigDecimal descuentosVarios) {
      this.descuentosVarios = descuentosVarios;
   }
	public String toString() {
		return ToStringer.toString((FacturaIf)this);
	}
}
