package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PropuestaDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getPropuestaId();

   void setPropuestaId(java.lang.Long propuestaId);

   java.lang.Long getPresupuestoId();

   void setPresupuestoId(java.lang.Long presupuestoId);

   java.lang.Long getPlanmedioId();

   void setPlanmedioId(java.lang.Long planmedioId);


}
