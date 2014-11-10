package com.spirit.facturacion.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PedidoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getOficinaId();

   void setOficinaId(java.lang.Long oficinaId);

   java.lang.Long getTipodocumentoId();

   void setTipodocumentoId(java.lang.Long tipodocumentoId);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

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

   java.lang.Long getPuntoimpresionId();

   void setPuntoimpresionId(java.lang.Long puntoimpresionId);

   java.lang.Long getOrigendocumentoId();

   void setOrigendocumentoId(java.lang.Long origendocumentoId);

   java.lang.Long getVendedorId();

   void setVendedorId(java.lang.Long vendedorId);

   java.lang.Long getBodegaId();

   void setBodegaId(java.lang.Long bodegaId);

   java.lang.Long getListaprecioId();

   void setListaprecioId(java.lang.Long listaprecioId);

   java.sql.Timestamp getFechaPedido();

   void setFechaPedido(java.sql.Timestamp fechaPedido);

   java.sql.Timestamp getFechaCreacion();

   void setFechaCreacion(java.sql.Timestamp fechaCreacion);

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);

   java.lang.String getContacto();

   void setContacto(java.lang.String contacto);

   java.lang.String getDireccion();

   void setDireccion(java.lang.String direccion);

   java.lang.String getTelefono();

   void setTelefono(java.lang.String telefono);

   java.lang.String getTiporeferencia();

   void setTiporeferencia(java.lang.String tiporeferencia);

   java.lang.String getReferencia();

   void setReferencia(java.lang.String referencia);

   java.lang.Integer getDiasvalidez();

   void setDiasvalidez(java.lang.Integer diasvalidez);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.Long getPedidoaplId();

   void setPedidoaplId(java.lang.Long pedidoaplId);

   java.math.BigDecimal getPorcentajeComisionAgencia();

   void setPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia);

   java.lang.Long getTipopagoId();

   void setTipopagoId(java.lang.Long tipopagoId);

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

   java.sql.Timestamp getFechaVencimiento();

   void setFechaVencimiento(java.sql.Timestamp fechaVencimiento);


}
