package com.spirit.facturacion.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface FacturaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getOficinaId();

   void setOficinaId(java.lang.Long oficinaId);

   java.lang.Long getTipodocumentoId();

   void setTipodocumentoId(java.lang.Long tipodocumentoId);

   java.math.BigDecimal getNumero();

   void setNumero(java.math.BigDecimal numero);

   java.lang.Long getClienteoficinaId();

   void setClienteoficinaId(java.lang.Long clienteoficinaId);

   java.lang.Long getTipoidentificacionId();

   void setTipoidentificacionId(java.lang.Long tipoidentificacionId);

   java.lang.String getIdentificacion();

   void setIdentificacion(java.lang.String identificacion);

   java.lang.Long getFormapagoId();

   void setFormapagoId(java.lang.Long formapagoId);

   java.lang.Long getMonedaId();

   void setMonedaId(java.lang.Long monedaId);

   java.lang.Long getPuntoImpresionId();

   void setPuntoImpresionId(java.lang.Long puntoImpresionId);

   java.lang.Long getOrigendocumentoId();

   void setOrigendocumentoId(java.lang.Long origendocumentoId);

   java.lang.Long getVendedorId();

   void setVendedorId(java.lang.Long vendedorId);

   java.lang.Long getBodegaId();

   void setBodegaId(java.lang.Long bodegaId);

   java.lang.Long getPedidoId();

   void setPedidoId(java.lang.Long pedidoId);

   java.lang.Long getListaPrecioId();

   void setListaPrecioId(java.lang.Long listaPrecioId);

   java.sql.Timestamp getFechaCreacion();

   void setFechaCreacion(java.sql.Timestamp fechaCreacion);

   java.sql.Timestamp getFechaFactura();

   void setFechaFactura(java.sql.Timestamp fechaFactura);

   java.sql.Timestamp getFechaVencimiento();

   void setFechaVencimiento(java.sql.Timestamp fechaVencimiento);

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);

   java.lang.String getContacto();

   void setContacto(java.lang.String contacto);

   java.lang.String getDireccion();

   void setDireccion(java.lang.String direccion);

   java.lang.String getTelefono();

   void setTelefono(java.lang.String telefono);

   java.lang.String getPreimpresoNumero();

   void setPreimpresoNumero(java.lang.String preimpresoNumero);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.math.BigDecimal getDescuento();

   void setDescuento(java.math.BigDecimal descuento);

   java.math.BigDecimal getIva();

   void setIva(java.math.BigDecimal iva);

   java.math.BigDecimal getIce();

   void setIce(java.math.BigDecimal ice);

   java.math.BigDecimal getOtroImpuesto();

   void setOtroImpuesto(java.math.BigDecimal otroImpuesto);

   java.math.BigDecimal getCosto();

   void setCosto(java.math.BigDecimal costo);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.Long getFacturaaplId();

   void setFacturaaplId(java.lang.Long facturaaplId);

   java.math.BigDecimal getDescuentoGlobal();

   void setDescuentoGlobal(java.math.BigDecimal descuentoGlobal);

   java.math.BigDecimal getPorcentajeComisionAgencia();

   void setPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia);

   java.lang.Long getEquipoId();

   void setEquipoId(java.lang.Long equipoId);

   java.lang.Long getClienteNegociacionId();

   void setClienteNegociacionId(java.lang.Long clienteNegociacionId);

   java.lang.String getTipoNegociacion();

   void setTipoNegociacion(java.lang.String tipoNegociacion);

   java.math.BigDecimal getPorcentajeNegociacionDirecta();

   void setPorcentajeNegociacionDirecta(java.math.BigDecimal porcentajeNegociacionDirecta);

   java.math.BigDecimal getPorcentajeDescuentoNegociacion();

   void setPorcentajeDescuentoNegociacion(java.math.BigDecimal porcentajeDescuentoNegociacion);

   java.lang.String getAutorizacionSap();

   void setAutorizacionSap(java.lang.String autorizacionSap);

   java.math.BigDecimal getDescuentosVarios();

   void setDescuentosVarios(java.math.BigDecimal descuentosVarios);


}
