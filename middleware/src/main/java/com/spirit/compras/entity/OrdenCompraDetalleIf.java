package com.spirit.compras.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OrdenCompraDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getDocumentoId();

   void setDocumentoId(java.lang.Long documentoId);

   java.lang.Long getProductoId();

   void setProductoId(java.lang.Long productoId);

   java.lang.Long getOrdencompraId();

   void setOrdencompraId(java.lang.Long ordencompraId);

   java.lang.Long getCantidad();

   void setCantidad(java.lang.Long cantidad);

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

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.math.BigDecimal getPorcentajeNegociacionDirecta();

   void setPorcentajeNegociacionDirecta(java.math.BigDecimal porcentajeNegociacionDirecta);

   java.math.BigDecimal getPorcentajeComisionPura();

   void setPorcentajeComisionPura(java.math.BigDecimal porcentajeComisionPura);

   java.math.BigDecimal getPorcentajeDescuentosVariosCompra();

   void setPorcentajeDescuentosVariosCompra(java.math.BigDecimal porcentajeDescuentosVariosCompra);

   java.math.BigDecimal getPorcentajeDescuentoEspecial();

   void setPorcentajeDescuentoEspecial(java.math.BigDecimal porcentajeDescuentoEspecial);

   java.sql.Timestamp getFechaPublicacion();

   void setFechaPublicacion(java.sql.Timestamp fechaPublicacion);


}
