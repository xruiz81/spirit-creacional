package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CampanaDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getClienteZonaId();

   void setClienteZonaId(java.lang.Long clienteZonaId);

   java.lang.Long getCampanaId();

   void setCampanaId(java.lang.Long campanaId);

   java.math.BigDecimal getParticipacion();

   void setParticipacion(java.math.BigDecimal participacion);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);


}
