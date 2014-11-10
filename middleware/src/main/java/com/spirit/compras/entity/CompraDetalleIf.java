package com.spirit.compras.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CompraDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getDocumentoId();

   void setDocumentoId(java.lang.Long documentoId);

   java.lang.Long getCompraId();

   void setCompraId(java.lang.Long compraId);

   java.lang.Long getProductoId();

   void setProductoId(java.lang.Long productoId);

   java.lang.Long getCantidad();

   void setCantidad(java.lang.Long cantidad);

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

   java.lang.Long getIdSriAir();

   void setIdSriAir(java.lang.Long idSriAir);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.lang.Long getSriIvaRetencionId();

   void setSriIvaRetencionId(java.lang.Long sriIvaRetencionId);

   java.math.BigDecimal getPorcentajeDescuentosVarios();

   void setPorcentajeDescuentosVarios(java.math.BigDecimal porcentajeDescuentosVarios);

   java.math.BigDecimal getPorcentajeDescuentoEspecial();

   void setPorcentajeDescuentoEspecial(java.math.BigDecimal porcentajeDescuentoEspecial);


}
