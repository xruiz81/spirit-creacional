package com.spirit.cartera.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface NotaCreditoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getOficinaId();

   void setOficinaId(java.lang.Long oficinaId);

   java.lang.Long getTipoDocumentoId();

   void setTipoDocumentoId(java.lang.Long tipoDocumentoId);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.Long getOperadorNegocioId();

   void setOperadorNegocioId(java.lang.Long operadorNegocioId);

   java.lang.Long getMonedaId();

   void setMonedaId(java.lang.Long monedaId);

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);

   java.sql.Timestamp getFechaEmision();

   void setFechaEmision(java.sql.Timestamp fechaEmision);

   java.sql.Timestamp getFechaVencimiento();

   void setFechaVencimiento(java.sql.Timestamp fechaVencimiento);

   java.sql.Timestamp getFechaCaducidad();

   void setFechaCaducidad(java.sql.Timestamp fechaCaducidad);

   java.lang.String getPreimpreso();

   void setPreimpreso(java.lang.String preimpreso);

   java.lang.String getAutorizacion();

   void setAutorizacion(java.lang.String autorizacion);

   java.lang.String getReferencia();

   void setReferencia(java.lang.String referencia);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

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

   java.lang.String getTipoCartera();

   void setTipoCartera(java.lang.String tipoCartera);

   java.lang.Long getReferenciaId();

   void setReferenciaId(java.lang.Long referenciaId);


}
