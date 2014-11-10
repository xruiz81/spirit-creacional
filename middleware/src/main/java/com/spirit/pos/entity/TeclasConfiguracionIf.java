package com.spirit.pos.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TeclasConfiguracionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getTeclasNombre();

   void setTeclasNombre(java.lang.String teclasNombre);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getMascara();

   void setMascara(java.lang.String mascara);


}
