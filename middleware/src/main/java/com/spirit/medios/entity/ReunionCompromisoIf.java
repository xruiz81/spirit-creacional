package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ReunionCompromisoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getReunionId();

   void setReunionId(java.lang.Long reunionId);

   java.sql.Date getFecha();

   void setFecha(java.sql.Date fecha);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getTemaTratado();

   void setTemaTratado(java.lang.String temaTratado);


}
