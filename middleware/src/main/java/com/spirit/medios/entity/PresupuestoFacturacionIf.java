package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PresupuestoFacturacionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getPresupuestoDetalleId();

   void setPresupuestoDetalleId(java.lang.Long presupuestoDetalleId);

   java.lang.Long getFacturaId();

   void setFacturaId(java.lang.Long facturaId);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getTipo();

   void setTipo(java.lang.String tipo);


}
