package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PresupuestoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.Long getOrdentrabajodetId();

   void setOrdentrabajodetId(java.lang.Long ordentrabajodetId);

   java.lang.Long getClienteCondicionId();

   void setClienteCondicionId(java.lang.Long clienteCondicionId);

   java.lang.Long getPlanmedioId();

   void setPlanmedioId(java.lang.Long planmedioId);

   java.lang.String getConcepto();

   void setConcepto(java.lang.String concepto);

   java.lang.Integer getModificacion();

   void setModificacion(java.lang.Integer modificacion);

   java.sql.Timestamp getFecha();

   void setFecha(java.sql.Timestamp fecha);

   java.sql.Timestamp getFechaValidez();

   void setFechaValidez(java.sql.Timestamp fechaValidez);

   java.sql.Timestamp getFechaEnvio();

   void setFechaEnvio(java.sql.Timestamp fechaEnvio);

   java.sql.Timestamp getFechaCancelacion();

   void setFechaCancelacion(java.sql.Timestamp fechaCancelacion);

   java.sql.Timestamp getFechaAceptacion();

   void setFechaAceptacion(java.sql.Timestamp fechaAceptacion);

   java.math.BigDecimal getValorbruto();

   void setValorbruto(java.math.BigDecimal valorbruto);

   java.math.BigDecimal getDescuento();

   void setDescuento(java.math.BigDecimal descuento);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.math.BigDecimal getIva();

   void setIva(java.math.BigDecimal iva);

   java.lang.String getCabecera();

   void setCabecera(java.lang.String cabecera);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.Long getFormaPagoId();

   void setFormaPagoId(java.lang.Long formaPagoId);

   java.lang.Integer getDiasValidez();

   void setDiasValidez(java.lang.Integer diasValidez);

   java.math.BigDecimal getPorcentajeComisionAgencia();

   void setPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia);

   java.lang.Long getUsuarioCreacionId();

   void setUsuarioCreacionId(java.lang.Long usuarioCreacionId);

   java.sql.Timestamp getFechaCreacion();

   void setFechaCreacion(java.sql.Timestamp fechaCreacion);

   java.lang.Long getUsuarioActualizacionId();

   void setUsuarioActualizacionId(java.lang.Long usuarioActualizacionId);

   java.sql.Timestamp getFechaActualizacion();

   void setFechaActualizacion(java.sql.Timestamp fechaActualizacion);

   java.lang.String getTemaCampana();

   void setTemaCampana(java.lang.String temaCampana);

   java.lang.String getAutorizacionSap();

   void setAutorizacionSap(java.lang.String autorizacionSap);

   java.math.BigDecimal getDescuentosVarios();

   void setDescuentosVarios(java.math.BigDecimal descuentosVarios);

   java.math.BigDecimal getDescuentoEspecial();

   void setDescuentoEspecial(java.math.BigDecimal descuentoEspecial);

   java.lang.Long getClienteOficinaId();

   void setClienteOficinaId(java.lang.Long clienteOficinaId);

   java.lang.String getPrepago();

   void setPrepago(java.lang.String prepago);

   java.lang.Long getReferenciaId();

   void setReferenciaId(java.lang.Long referenciaId);

   java.lang.String getTipoReferencia();

   void setTipoReferencia(java.lang.String tipoReferencia);


}
