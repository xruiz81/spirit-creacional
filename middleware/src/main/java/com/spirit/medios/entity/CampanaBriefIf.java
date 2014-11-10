package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CampanaBriefIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getTipoBriefId();

   void setTipoBriefId(java.lang.Long tipoBriefId);

   java.lang.Long getCampanaId();

   void setCampanaId(java.lang.Long campanaId);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.lang.String getUrlDescripcion();

   void setUrlDescripcion(java.lang.String urlDescripcion);


}
