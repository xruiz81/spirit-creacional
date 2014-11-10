package com.spirit.compras.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OrdenCompraIf extends SpiritIf{


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

   java.lang.Long getProveedorId();

   void setProveedorId(java.lang.Long proveedorId);

   java.lang.Long getMonedaId();

   void setMonedaId(java.lang.Long monedaId);

   java.lang.Long getEmpleadoId();

   void setEmpleadoId(java.lang.Long empleadoId);

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);

   java.lang.Long getBodegaId();

   void setBodegaId(java.lang.Long bodegaId);

   java.lang.String getLocalimportada();

   void setLocalimportada(java.lang.String localimportada);

   java.sql.Date getFecha();

   void setFecha(java.sql.Date fecha);

   java.sql.Date getFechaRecepcion();

   void setFechaRecepcion(java.sql.Date fechaRecepcion);

   java.sql.Date getFechaVencimiento();

   void setFechaVencimiento(java.sql.Date fechaVencimiento);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.math.BigDecimal getDescuentoAgenciaCompra();

   void setDescuentoAgenciaCompra(java.math.BigDecimal descuentoAgenciaCompra);

   java.math.BigDecimal getIva();

   void setIva(java.math.BigDecimal iva);

   java.math.BigDecimal getIce();

   void setIce(java.math.BigDecimal ice);

   java.math.BigDecimal getOtroImpuesto();

   void setOtroImpuesto(java.math.BigDecimal otroImpuesto);

   java.lang.Long getSolicitudcompraId();

   void setSolicitudcompraId(java.lang.Long solicitudcompraId);

   java.lang.String getEstadoBpm();

   void setEstadoBpm(java.lang.String estadoBpm);

   java.math.BigDecimal getPorcentajeDescuentosVariosCompra();

   void setPorcentajeDescuentosVariosCompra(java.math.BigDecimal porcentajeDescuentosVariosCompra);

   java.math.BigDecimal getPorcentajeDescuentosVariosVenta();

   void setPorcentajeDescuentosVariosVenta(java.math.BigDecimal porcentajeDescuentosVariosVenta);

   java.math.BigDecimal getPorcentajeDescuentoEspecial();

   void setPorcentajeDescuentoEspecial(java.math.BigDecimal porcentajeDescuentoEspecial);

   java.lang.String getRevision();

   void setRevision(java.lang.String revision);


}
