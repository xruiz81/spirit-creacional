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

@Table(name = "PEDIDO")
@Entity
public class PedidoEJB implements Serializable, PedidoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long oficinaId;
   private java.lang.Long tipodocumentoId;
   private java.lang.String codigo;
   private java.lang.Long clienteoficinaId;
   private java.lang.Long tipoidentificacionId;
   private java.lang.String identificacion;
   private java.lang.Long formapagoId;
   private java.lang.Long monedaId;
   private java.lang.Long puntoimpresionId;
   private java.lang.Long origendocumentoId;
   private java.lang.Long vendedorId;
   private java.lang.Long bodegaId;
   private java.lang.Long listaprecioId;
   private java.sql.Timestamp fechaPedido;
   private java.sql.Timestamp fechaCreacion;
   private java.lang.Long usuarioId;
   private java.lang.String contacto;
   private java.lang.String direccion;
   private java.lang.String telefono;
   private java.lang.String tiporeferencia;
   private java.lang.String referencia;
   private java.lang.Integer diasvalidez;
   private java.lang.String observacion;
   private java.lang.String estado;
   private java.lang.Long pedidoaplId;
   private java.math.BigDecimal porcentajeComisionAgencia;
   private java.lang.Long tipopagoId;
   private java.lang.Long equipoId;
   private java.lang.Long clienteNegociacionId;
   private java.lang.String tipoNegociacion;
   private java.math.BigDecimal porcentajeNegociacionDirecta;
   private java.math.BigDecimal porcentajeDescuentoNegociacion;
   private java.lang.String autorizacionSap;
   private java.sql.Timestamp fechaVencimiento;

   public PedidoEJB() {
   }

   public PedidoEJB(com.spirit.facturacion.entity.PedidoIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setTipodocumentoId(value.getTipodocumentoId());
      setCodigo(value.getCodigo());
      setClienteoficinaId(value.getClienteoficinaId());
      setTipoidentificacionId(value.getTipoidentificacionId());
      setIdentificacion(value.getIdentificacion());
      setFormapagoId(value.getFormapagoId());
      setMonedaId(value.getMonedaId());
      setPuntoimpresionId(value.getPuntoimpresionId());
      setOrigendocumentoId(value.getOrigendocumentoId());
      setVendedorId(value.getVendedorId());
      setBodegaId(value.getBodegaId());
      setListaprecioId(value.getListaprecioId());
      setFechaPedido(value.getFechaPedido());
      setFechaCreacion(value.getFechaCreacion());
      setUsuarioId(value.getUsuarioId());
      setContacto(value.getContacto());
      setDireccion(value.getDireccion());
      setTelefono(value.getTelefono());
      setTiporeferencia(value.getTiporeferencia());
      setReferencia(value.getReferencia());
      setDiasvalidez(value.getDiasvalidez());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setPedidoaplId(value.getPedidoaplId());
      setPorcentajeComisionAgencia(value.getPorcentajeComisionAgencia());
      setTipopagoId(value.getTipopagoId());
      setEquipoId(value.getEquipoId());
      setClienteNegociacionId(value.getClienteNegociacionId());
      setTipoNegociacion(value.getTipoNegociacion());
      setPorcentajeNegociacionDirecta(value.getPorcentajeNegociacionDirecta());
      setPorcentajeDescuentoNegociacion(value.getPorcentajeDescuentoNegociacion());
      setAutorizacionSap(value.getAutorizacionSap());
      setFechaVencimiento(value.getFechaVencimiento());
   }

   public java.lang.Long create(com.spirit.facturacion.entity.PedidoIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setTipodocumentoId(value.getTipodocumentoId());
      setCodigo(value.getCodigo());
      setClienteoficinaId(value.getClienteoficinaId());
      setTipoidentificacionId(value.getTipoidentificacionId());
      setIdentificacion(value.getIdentificacion());
      setFormapagoId(value.getFormapagoId());
      setMonedaId(value.getMonedaId());
      setPuntoimpresionId(value.getPuntoimpresionId());
      setOrigendocumentoId(value.getOrigendocumentoId());
      setVendedorId(value.getVendedorId());
      setBodegaId(value.getBodegaId());
      setListaprecioId(value.getListaprecioId());
      setFechaPedido(value.getFechaPedido());
      setFechaCreacion(value.getFechaCreacion());
      setUsuarioId(value.getUsuarioId());
      setContacto(value.getContacto());
      setDireccion(value.getDireccion());
      setTelefono(value.getTelefono());
      setTiporeferencia(value.getTiporeferencia());
      setReferencia(value.getReferencia());
      setDiasvalidez(value.getDiasvalidez());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setPedidoaplId(value.getPedidoaplId());
      setPorcentajeComisionAgencia(value.getPorcentajeComisionAgencia());
      setTipopagoId(value.getTipopagoId());
      setEquipoId(value.getEquipoId());
      setClienteNegociacionId(value.getClienteNegociacionId());
      setTipoNegociacion(value.getTipoNegociacion());
      setPorcentajeNegociacionDirecta(value.getPorcentajeNegociacionDirecta());
      setPorcentajeDescuentoNegociacion(value.getPorcentajeDescuentoNegociacion());
      setAutorizacionSap(value.getAutorizacionSap());
      setFechaVencimiento(value.getFechaVencimiento());
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

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
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

   @Column(name = "PUNTOIMPRESION_ID")
   public java.lang.Long getPuntoimpresionId() {
      return puntoimpresionId;
   }

   public void setPuntoimpresionId(java.lang.Long puntoimpresionId) {
      this.puntoimpresionId = puntoimpresionId;
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

   @Column(name = "LISTAPRECIO_ID")
   public java.lang.Long getListaprecioId() {
      return listaprecioId;
   }

   public void setListaprecioId(java.lang.Long listaprecioId) {
      this.listaprecioId = listaprecioId;
   }

   @Column(name = "FECHA_PEDIDO")
   public java.sql.Timestamp getFechaPedido() {
      return fechaPedido;
   }

   public void setFechaPedido(java.sql.Timestamp fechaPedido) {
      this.fechaPedido = fechaPedido;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
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

   @Column(name = "TIPOREFERENCIA")
   public java.lang.String getTiporeferencia() {
      return tiporeferencia;
   }

   public void setTiporeferencia(java.lang.String tiporeferencia) {
      this.tiporeferencia = tiporeferencia;
   }

   @Column(name = "REFERENCIA")
   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }

   @Column(name = "DIASVALIDEZ")
   public java.lang.Integer getDiasvalidez() {
      return diasvalidez;
   }

   public void setDiasvalidez(java.lang.Integer diasvalidez) {
      this.diasvalidez = diasvalidez;
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

   @Column(name = "PEDIDOAPL_ID")
   public java.lang.Long getPedidoaplId() {
      return pedidoaplId;
   }

   public void setPedidoaplId(java.lang.Long pedidoaplId) {
      this.pedidoaplId = pedidoaplId;
   }

   @Column(name = "PORCENTAJE_COMISION_AGENCIA")
   public java.math.BigDecimal getPorcentajeComisionAgencia() {
      return porcentajeComisionAgencia;
   }

   public void setPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) {
      this.porcentajeComisionAgencia = porcentajeComisionAgencia;
   }

   @Column(name = "TIPOPAGO_ID")
   public java.lang.Long getTipopagoId() {
      return tipopagoId;
   }

   public void setTipopagoId(java.lang.Long tipopagoId) {
      this.tipopagoId = tipopagoId;
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

   @Column(name = "FECHA_VENCIMIENTO")
   public java.sql.Timestamp getFechaVencimiento() {
      return fechaVencimiento;
   }

   public void setFechaVencimiento(java.sql.Timestamp fechaVencimiento) {
      this.fechaVencimiento = fechaVencimiento;
   }
	public String toString() {
		return ToStringer.toString((PedidoIf)this);
	}
}
