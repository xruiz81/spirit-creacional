package com.spirit.compras.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SolicitudCompraArchivoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getSolicitudCompraId();

   void setSolicitudCompraId(java.lang.Long solicitudCompraId);

   java.lang.Long getTipoArchivoId();

   void setTipoArchivoId(java.lang.Long tipoArchivoId);

   java.lang.String getUrl();

   void setUrl(java.lang.String url);


}
