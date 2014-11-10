package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface UsuarioPuntoImpresionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getPuntoimpresionId();

   void setPuntoimpresionId(java.lang.Long puntoimpresionId);

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);


}
