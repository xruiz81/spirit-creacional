package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TiempoParcialDotIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.lang.Long getFechaInicio();

   void setFechaInicio(java.lang.Long fechaInicio);

   java.lang.Long getFechaFin();

   void setFechaFin(java.lang.Long fechaFin);

   java.lang.Long getTiempo();

   void setTiempo(java.lang.Long tiempo);

   java.lang.Long getIdOrdenTrabajoDetalle();

   void setIdOrdenTrabajoDetalle(java.lang.Long idOrdenTrabajoDetalle);
   
   void setUsuarioAsignadoId(java.lang.Long usuarioAsignadoId);
   
   java.lang.Long getUsuarioAsignadoId();


}
