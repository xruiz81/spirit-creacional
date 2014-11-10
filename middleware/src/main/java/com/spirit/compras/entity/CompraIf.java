package com.spirit.compras.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CompraIf extends SpiritIf{


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

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);

   java.sql.Date getFecha();

   void setFecha(java.sql.Date fecha);

   java.sql.Date getFechaVencimiento();

   void setFechaVencimiento(java.sql.Date fechaVencimiento);

   java.lang.String getPreimpreso();

   void setPreimpreso(java.lang.String preimpreso);

   java.lang.String getAutorizacion();

   void setAutorizacion(java.lang.String autorizacion);

   java.lang.String getReferencia();

   void setReferencia(java.lang.String referencia);

   java.lang.String getLocalimportada();

   void setLocalimportada(java.lang.String localimportada);

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

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getEstadoBpm();

   void setEstadoBpm(java.lang.String estadoBpm);

   java.math.BigDecimal getRetencion();

   void setRetencion(java.math.BigDecimal retencion);

   java.lang.Long getIdSriSustentoTributario();

   void setIdSriSustentoTributario(java.lang.Long idSriSustentoTributario);

   java.sql.Date getFechaCaducidad();

   void setFechaCaducidad(java.sql.Date fechaCaducidad);

   java.lang.String getTipoCompra();

   void setTipoCompra(java.lang.String tipoCompra);


}
