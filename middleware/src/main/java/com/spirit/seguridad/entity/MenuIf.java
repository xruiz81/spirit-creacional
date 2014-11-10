package com.spirit.seguridad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface MenuIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.Long getPadreId();

   void setPadreId(java.lang.Long padreId);

   java.lang.Integer getNivel();

   void setNivel(java.lang.Integer nivel);

   java.lang.Integer getFavorito();

   void setFavorito(java.lang.Integer favorito);

   java.lang.String getPanel();

   void setPanel(java.lang.String panel);


}
