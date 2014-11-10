package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ReunionArchivoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getReunionId();

   void setReunionId(java.lang.Long reunionId);

   java.lang.Long getTipoArchivoId();

   void setTipoArchivoId(java.lang.Long tipoArchivoId);

   java.sql.Date getFecha();

   void setFecha(java.sql.Date fecha);

   java.lang.String getUrlDescripcion();

   void setUrlDescripcion(java.lang.String urlDescripcion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);


}
