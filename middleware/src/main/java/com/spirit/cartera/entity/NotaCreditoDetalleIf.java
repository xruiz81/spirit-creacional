package com.spirit.cartera.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface NotaCreditoDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getDocumentoId();

   void setDocumentoId(java.lang.Long documentoId);

   java.lang.Long getNotaCreditoId();

   void setNotaCreditoId(java.lang.Long notaCreditoId);

   java.lang.Long getProductoId();

   void setProductoId(java.lang.Long productoId);

   java.math.BigDecimal getCantidad();

   void setCantidad(java.math.BigDecimal cantidad);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.math.BigDecimal getIva();

   void setIva(java.math.BigDecimal iva);

   java.math.BigDecimal getIce();

   void setIce(java.math.BigDecimal ice);

   java.math.BigDecimal getOtroImpuesto();

   void setOtroImpuesto(java.math.BigDecimal otroImpuesto);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.lang.String getTipoReferencia();

   void setTipoReferencia(java.lang.String tipoReferencia);

   java.lang.Long getReferenciaId();

   void setReferenciaId(java.lang.Long referenciaId);

   java.lang.String getTipoNota();

   void setTipoNota(java.lang.String tipoNota);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.String getTipoPresupuesto();

   void setTipoPresupuesto(java.lang.String tipoPresupuesto);

   java.lang.Long getPresupuestoId();

   void setPresupuestoId(java.lang.Long presupuestoId);

   java.lang.Long getOrdenId();

   void setOrdenId(java.lang.Long ordenId);


}
