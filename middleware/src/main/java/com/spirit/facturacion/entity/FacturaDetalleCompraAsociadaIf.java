package com.spirit.facturacion.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface FacturaDetalleCompraAsociadaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getFacturaDetalleId();

   void setFacturaDetalleId(java.lang.Long facturaDetalleId);

   java.lang.Long getCompraId();

   void setCompraId(java.lang.Long compraId);


}
